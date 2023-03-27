package org.wysaid.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.wysaid.camera.CameraInstance;

public class CameraGLSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer {
    public static final String LOG_TAG = "libCGE_java";
    protected Viewport mDrawViewport = new Viewport();
    protected boolean mFitFullView = false;
    protected boolean mIsCameraBackForward = true;
    protected int mMaxPreviewHeight = 1280;
    protected int mMaxPreviewWidth = 1280;
    public int mMaxTextureSize = 0;
    protected OnCreateCallback mOnCreateCallback;
    protected int mRecordHeight = 640;
    protected int mRecordWidth = 480;
    protected int mViewHeight;
    protected int mViewWidth;

    public interface OnCreateCallback {
        void createOver();
    }

    public interface ReleaseOKCallback {
        void releaseOK();
    }

    public interface TakePictureCallback {
        void takePictureOK(Bitmap bitmap);
    }

    public static class Viewport {
        public int height;
        public int width;
        public int x;
        public int y;
    }

    public void onDrawFrame(GL10 gl10) {
    }


    public void onRelease() {
    }


    public void onSwitchCamera() {
    }

    public void resumePreview() {
    }

    public void takeShot(TakePictureCallback takePictureCallback) {
    }

    public CameraGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 0, 0);
        getHolder().setFormat(1);
        setRenderer(this);
        setRenderMode(0);
    }

    public void setPictureSize(int i, int i2, boolean z) {
        cameraInstance().setPictureSize(i2, i, z);
    }

    public synchronized boolean setFlashLightMode(String str) {
        if (!getContext().getPackageManager().hasSystemFeature("android.hardware.camera.flash")) {
            Log.e("libCGE_java", "No flash light is supported by current device!");
            return false;
        } else if (!this.mIsCameraBackForward) {
            return false;
        } else {
            Camera.Parameters params = cameraInstance().getParams();
            if (params == null) {
                return false;
            }
            try {
                if (!params.getSupportedFlashModes().contains(str)) {
                    Log.e("libCGE_java", "Invalid Flash Light Mode!!!");
                    return false;
                }
                params.setFlashMode(str);
                cameraInstance().setParams(params);
                return true;
            } catch (Exception unused) {
                Log.e("libCGE_java", "Switch flash light failed, check if you're using front camera.");
                return false;
            }
        }
    }

    public Viewport getDrawViewport() {
        return this.mDrawViewport;
    }

    /* access modifiers changed from: package-private */
    public void setMaxPreviewSize(int i, int i2) {
        this.mMaxPreviewWidth = i;
        this.mMaxPreviewHeight = i2;
    }

    public void setFitFullView(boolean z) {
        this.mFitFullView = z;
        calcViewport();
    }

    public boolean isCameraBackForward() {
        return this.mIsCameraBackForward;
    }

    public CameraInstance cameraInstance() {
        return CameraInstance.getInstance();
    }

    public void presetCameraForward(boolean z) {
        this.mIsCameraBackForward = z;
    }

    public void presetRecordingSize(int i, int i2) {
        if (i > this.mMaxPreviewWidth || i2 > this.mMaxPreviewHeight) {
            float f = (float) i;
            float f2 = (float) i2;
            float min = Math.min(((float) this.mMaxPreviewWidth) / f, ((float) this.mMaxPreviewHeight) / f2);
            i = (int) (f * min);
            i2 = (int) (f2 * min);
        }
        this.mRecordWidth = i;
        this.mRecordHeight = i2;
        cameraInstance().setPreferPreviewSize(i, i2);
    }

    public void stopPreview() {
        queueEvent(new Runnable() {
            public void run() {
                CameraGLSurfaceView.this.cameraInstance().stopPreview();
            }
        });
    }

    public final void switchCamera() {
        this.mIsCameraBackForward = !this.mIsCameraBackForward;
        queueEvent(new Runnable() {
            public void run() {
                CameraGLSurfaceView.this.cameraInstance().stopCamera();
                CameraGLSurfaceView.this.onSwitchCamera();
                CameraGLSurfaceView.this.cameraInstance().tryOpenCamera(new CameraInstance.CameraOpenCallback() {
                    public void cameraReady() {
                        CameraGLSurfaceView.this.resumePreview();
                    }
                }, CameraGLSurfaceView.this.mIsCameraBackForward ^ true ? 1 : 0);
                CameraGLSurfaceView.this.requestRender();
            }
        });
    }

    public void focusAtPoint(float f, float f2, Camera.AutoFocusCallback autoFocusCallback) {
        cameraInstance().focusAtPoint(f2, 1.0f - f, autoFocusCallback);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        super.surfaceDestroyed(surfaceHolder);
        cameraInstance().stopCamera();
    }

    public void setOnCreateCallback(OnCreateCallback onCreateCallback) {
        this.mOnCreateCallback = onCreateCallback;
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        Log.i("libCGE_java", "onSurfaceCreated...");
        GLES20.glDisable(2929);
        GLES20.glDisable(2960);
        GLES20.glBlendFunc(770, 771);
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(3379, iArr, 0);
        this.mMaxTextureSize = iArr[0];
        OnCreateCallback onCreateCallback = this.mOnCreateCallback;
        if (onCreateCallback != null) {
            onCreateCallback.createOver();
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        Log.i("libCGE_java", String.format("onSurfaceChanged: %d x %d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        this.mViewWidth = i;
        this.mViewHeight = i2;
        calcViewport();
    }

    public void onResume() {
        super.onResume();
        Log.i("libCGE_java", "glsurfaceview onResume...");
    }

    public void onPause() {
        Log.i("libCGE_java", "glsurfaceview onPause in...");
        cameraInstance().stopCamera();
        super.onPause();
        Log.i("libCGE_java", "glsurfaceview onPause out...");
    }

    public final void release(final ReleaseOKCallback callback) {
        queueEvent(new Runnable() {
            @Override
            public void run() {

                onRelease();

                Log.i(LOG_TAG, "GLSurfaceview release...");
                if (callback != null)
                    callback.releaseOK();
            }
        });
    }


    public void calcViewport() {
        float f = ((float) this.mRecordWidth) / ((float) this.mRecordHeight);
        int i = this.mViewWidth;
        int i2 = this.mViewHeight;
        float f2 = f / (((float) i) / ((float) i2));
        if (!this.mFitFullView ? ((double) f2) <= 1.0d : ((double) f2) > 1.0d) {
            i = (int) (((float) i2) * f);
        } else {
            i2 = (int) (((float) i) / f);
        }
        this.mDrawViewport.width = i;
        this.mDrawViewport.height = i2;
        Viewport viewport = this.mDrawViewport;
        viewport.x = (this.mViewWidth - viewport.width) / 2;
        Viewport viewport2 = this.mDrawViewport;
        viewport2.y = (this.mViewHeight - viewport2.height) / 2;
        Log.i("libCGE_java", String.format("View port: %d, %d, %d, %d", new Object[]{Integer.valueOf(this.mDrawViewport.x), Integer.valueOf(this.mDrawViewport.y), Integer.valueOf(this.mDrawViewport.width), Integer.valueOf(this.mDrawViewport.height)}));
    }
}
