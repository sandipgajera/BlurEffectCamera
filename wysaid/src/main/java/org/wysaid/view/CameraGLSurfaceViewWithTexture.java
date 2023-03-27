package org.wysaid.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.opengl.GLES20;
import android.util.AttributeSet;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.wysaid.camera.CameraInstance;
import org.wysaid.common.Common;
import org.wysaid.common.FrameBufferObject;
import org.wysaid.nativePort.CGEFrameRecorder;
import org.wysaid.nativePort.CGENativeLibrary;

public class CameraGLSurfaceViewWithTexture extends CameraGLSurfaceView implements SurfaceTexture.OnFrameAvailableListener {
    static final  boolean $assertionsDisabled = false;
    protected CGEFrameRecorder mFrameRecorder;
    protected boolean mIsTransformMatrixSet = false;
    protected SurfaceTexture mSurfaceTexture;
    protected int mTextureID;
    protected float[] mTransformMatrix = new float[16];

    public CGEFrameRecorder getRecorder() {
        return this.mFrameRecorder;
    }

    public synchronized void setFilterWithConfig(final String str) {
        queueEvent(new Runnable() {
            public void run() {
                if (CameraGLSurfaceViewWithTexture.this.mFrameRecorder != null) {
                    CameraGLSurfaceViewWithTexture.this.mFrameRecorder.setFilterWidthConfig(str);
                } else {
                    Log.e("libCGE_java", "setFilterWithConfig after release!!");
                }
            }
        });
    }

    public void setFilterIntensity(final float f) {
        queueEvent(new Runnable() {
            public void run() {
                if (CameraGLSurfaceViewWithTexture.this.mFrameRecorder != null) {
                    CameraGLSurfaceViewWithTexture.this.mFrameRecorder.setFilterIntensity(f);
                } else {
                    Log.e("libCGE_java", "setFilterIntensity after release!!");
                }
            }
        });
    }

    public void setOnCreateCallback(final CameraGLSurfaceView.OnCreateCallback onCreateCallback) {
        if (this.mFrameRecorder == null || onCreateCallback == null) {
            this.mOnCreateCallback = onCreateCallback;
        } else {
            queueEvent(new Runnable() {
                public void run() {
                    onCreateCallback.createOver();
                }
            });
        }
    }

    public CameraGLSurfaceViewWithTexture(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        CGEFrameRecorder cGEFrameRecorder = new CGEFrameRecorder();
        this.mFrameRecorder = cGEFrameRecorder;
        this.mIsTransformMatrixSet = false;
        if (!cGEFrameRecorder.init(this.mRecordWidth, this.mRecordHeight, this.mRecordWidth, this.mRecordHeight)) {
            Log.e("libCGE_java", "Frame Recorder init failed!");
        }
        this.mFrameRecorder.setSrcRotation(1.5707964f);
        this.mFrameRecorder.setSrcFlipScale(1.0f, -1.0f);
        this.mFrameRecorder.setRenderFlipScale(1.0f, -1.0f);
        this.mTextureID = Common.genSurfaceTextureID();
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mTextureID);
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        super.onSurfaceCreated(gl10, eGLConfig);
    }


    public void onRelease() {
        super.onRelease();
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.mSurfaceTexture = null;
        }
        int i = this.mTextureID;
        if (i != 0) {
            Common.deleteTextureID(i);
            this.mTextureID = 0;
        }
        CGEFrameRecorder cGEFrameRecorder = this.mFrameRecorder;
        if (cGEFrameRecorder != null) {
            cGEFrameRecorder.release();
            this.mFrameRecorder = null;
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        super.onSurfaceChanged(gl10, i, i2);
        if (!cameraInstance().isPreviewing()) {
            resumePreview();
        }
    }

    public void resumePreview() {
        if (this.mFrameRecorder == null) {
            Log.e("libCGE_java", "resumePreview after release!!");
            return;
        }
        if (!cameraInstance().isCameraOpened()) {
            cameraInstance().tryOpenCamera(new CameraInstance.CameraOpenCallback() {
                public void cameraReady() {
                    Log.i("libCGE_java", "tryOpenCamera OK...");
                }
            }, this.mIsCameraBackForward ^ true ? 1 : 0);
        }
        if (!cameraInstance().isPreviewing()) {
            cameraInstance().startPreview(this.mSurfaceTexture);
            this.mFrameRecorder.srcResize(cameraInstance().previewHeight(), cameraInstance().previewWidth());
        }
        requestRender();
    }

    public void onDrawFrame(GL10 gl10) {
        if (this.mSurfaceTexture != null && cameraInstance().isPreviewing()) {
            this.mSurfaceTexture.updateTexImage();
            this.mSurfaceTexture.getTransformMatrix(this.mTransformMatrix);
            this.mFrameRecorder.update(this.mTextureID, this.mTransformMatrix);
            this.mFrameRecorder.runProc();
            GLES20.glBindFramebuffer(36160, 0);
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GLES20.glClear(16384);
            this.mFrameRecorder.render(this.mDrawViewport.x, this.mDrawViewport.y, this.mDrawViewport.width, this.mDrawViewport.height);
        }
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        requestRender();
    }


    public void onSwitchCamera() {
        super.onSwitchCamera();
        CGEFrameRecorder cGEFrameRecorder = this.mFrameRecorder;
        if (cGEFrameRecorder != null) {
            cGEFrameRecorder.setSrcRotation(1.5707964f);
            this.mFrameRecorder.setRenderFlipScale(1.0f, -1.0f);
        }
    }

    public void takeShot(final CameraGLSurfaceView.TakePictureCallback takePictureCallback) {
        if (this.mFrameRecorder == null) {
            Log.e("libCGE_java", "Recorder not initialized!");
            takePictureCallback.takePictureOK((Bitmap) null);
            return;
        }
        queueEvent(new Runnable() {
            public void run() {
                FrameBufferObject frameBufferObject = new FrameBufferObject();
                int genBlankTextureID = Common.genBlankTextureID(CameraGLSurfaceViewWithTexture.this.mRecordWidth, CameraGLSurfaceViewWithTexture.this.mRecordHeight);
                frameBufferObject.bindTexture(genBlankTextureID);
                GLES20.glViewport(0, 0, CameraGLSurfaceViewWithTexture.this.mRecordWidth, CameraGLSurfaceViewWithTexture.this.mRecordHeight);
                CameraGLSurfaceViewWithTexture.this.mFrameRecorder.drawCache();
                IntBuffer allocate = IntBuffer.allocate(CameraGLSurfaceViewWithTexture.this.mRecordWidth * CameraGLSurfaceViewWithTexture.this.mRecordHeight);
                GLES20.glReadPixels(0, 0, CameraGLSurfaceViewWithTexture.this.mRecordWidth, CameraGLSurfaceViewWithTexture.this.mRecordHeight, 6408, 5121, allocate);
                Bitmap createBitmap = Bitmap.createBitmap(CameraGLSurfaceViewWithTexture.this.mRecordWidth, CameraGLSurfaceViewWithTexture.this.mRecordHeight, Bitmap.Config.ARGB_8888);
                createBitmap.copyPixelsFromBuffer(allocate);
                Log.i("libCGE_java", String.format("w: %d, h: %d", new Object[]{Integer.valueOf(CameraGLSurfaceViewWithTexture.this.mRecordWidth), Integer.valueOf(CameraGLSurfaceViewWithTexture.this.mRecordHeight)}));
                frameBufferObject.release();
                GLES20.glDeleteTextures(1, new int[]{genBlankTextureID}, 0);
                takePictureCallback.takePictureOK(createBitmap);
            }
        });
    }

    public void setPictureSize(int i, int i2, boolean z) {
        cameraInstance().setPictureSize(i2, i, z);
    }



    public synchronized void takePicture(final TakePictureCallback photoCallback, Camera.ShutterCallback shutterCallback, final String config, final float intensity, final boolean isFrontMirror) {

        Camera.Parameters params = cameraInstance().getParams();

        if (photoCallback == null || params == null) {
            Log.e(LOG_TAG, "takePicture after release!");
            if (photoCallback != null) {
                photoCallback.takePictureOK(null);
            }
            return;
        }

        try {
            params.setRotation(90);
            cameraInstance().setParams(params);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error when takePicture: " + e.toString());
            if (photoCallback != null) {
                photoCallback.takePictureOK(null);
            }
            return;
        }

        cameraInstance().getCameraDevice().takePicture(shutterCallback, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(final byte[] data, Camera camera) {

                Camera.Parameters params = camera.getParameters();
                Camera.Size sz = params.getPictureSize();

                boolean shouldRotate;

                Bitmap bmp;
                int width, height;

                //当拍出相片不为正方形时， 可以判断图片是否旋转
                if (sz.width != sz.height) {
                    //默认数据格式已经设置为 JPEG
                    bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                    width = bmp.getWidth();
                    height = bmp.getHeight();
                    shouldRotate = (sz.width > sz.height && width > height) || (sz.width < sz.height && width < height);
                } else {
                    Log.i(LOG_TAG, "Cache image to get exif.");

                    try {
                        String tmpFilename = getContext().getExternalCacheDir() + "/picture_cache000.jpg";
                        FileOutputStream fileout = new FileOutputStream(tmpFilename);
                        BufferedOutputStream bufferOutStream = new BufferedOutputStream(fileout);
                        bufferOutStream.write(data);
                        bufferOutStream.flush();
                        bufferOutStream.close();

                        ExifInterface exifInterface = new ExifInterface(tmpFilename);
                        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                        switch (orientation) {
                            //被保存图片exif记录只有旋转90度， 和不旋转两种情况
                            case ExifInterface.ORIENTATION_ROTATE_90:
                                shouldRotate = true;
                                break;
                            default:
                                shouldRotate = false;
                                break;
                        }

                        bmp = BitmapFactory.decodeFile(tmpFilename);
                        width = bmp.getWidth();
                        height = bmp.getHeight();

                    } catch (IOException e) {
                        Log.e(LOG_TAG, "Err when saving bitmap...");
                        e.printStackTrace();
                        return;
                    }
                }


                if (width > mMaxTextureSize || height > mMaxTextureSize) {
                    float scaling = Math.max(width / (float) mMaxTextureSize, height / (float) mMaxTextureSize);
                    Log.i(LOG_TAG, String.format("目标尺寸(%d x %d)超过当前设备OpenGL 能够处理的最大范围(%d x %d)， 现在将图片压缩至合理大小!", width, height, mMaxTextureSize, mMaxTextureSize));

                    bmp = Bitmap.createScaledBitmap(bmp, (int) (width / scaling), (int) (height / scaling), false);

                    width = bmp.getWidth();
                    height = bmp.getHeight();
                }

                Bitmap bmp2;

                if (shouldRotate) {
                    bmp2 = Bitmap.createBitmap(height, width, Bitmap.Config.ARGB_8888);

                    Canvas canvas = new Canvas(bmp2);

                    if (cameraInstance().getFacing() == Camera.CameraInfo.CAMERA_FACING_BACK) {
                        Matrix mat = new Matrix();
                        int halfLen = Math.min(width, height) / 2;
                        mat.setRotate(90, halfLen, halfLen);
                        canvas.drawBitmap(bmp, mat, null);
                    } else {
                        Matrix mat = new Matrix();

                        if (isFrontMirror) {
                            mat.postTranslate(-width / 2, -height / 2);
                            mat.postScale(-1.0f, 1.0f);
                            mat.postTranslate(width / 2, height / 2);
                            int halfLen = Math.min(width, height) / 2;
                            mat.postRotate(90, halfLen, halfLen);
                        } else {
                            int halfLen = Math.max(width, height) / 2;
                            mat.postRotate(-90, halfLen, halfLen);
                        }

                        canvas.drawBitmap(bmp, mat, null);
                    }

                    bmp.recycle();
                } else {
                    if (cameraInstance().getFacing() == Camera.CameraInfo.CAMERA_FACING_BACK) {
                        bmp2 = bmp;
                    } else {

                        bmp2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bmp2);
                        Matrix mat = new Matrix();
                        if (isFrontMirror) {
                            mat.postTranslate(-width / 2, -height / 2);
                            mat.postScale(1.0f, -1.0f);
                            mat.postTranslate(width / 2, height / 2);
                        } else {
                            mat.postTranslate(-width / 2, -height / 2);
                            mat.postScale(-1.0f, -1.0f);
                            mat.postTranslate(width / 2, height / 2);
                        }

                        canvas.drawBitmap(bmp, mat, null);
                    }

                }

                if (config != null) {
                    CGENativeLibrary.filterImage_MultipleEffectsWriteBack(bmp2, config, intensity);
                }

                photoCallback.takePictureOK(bmp2);

                cameraInstance().getCameraDevice().startPreview();
            }
        });
    }
}
