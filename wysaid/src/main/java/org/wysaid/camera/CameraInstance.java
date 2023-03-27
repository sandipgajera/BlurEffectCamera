package org.wysaid.camera;

import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CameraInstance {

    public static final int DEFAULT_PREVIEW_RATE = 30;
    public static final String LOG_TAG = "libCGE_java";
    private static CameraInstance mThisInstance;
    private Comparator<Camera.Size> comparatorBigger = new Comparator<Camera.Size>() {
        public int compare(Camera.Size size, Camera.Size size2) {
            int i = size2.width - size.width;
            return i == 0 ? size2.height - size.height : i;
        }
    };
    private Comparator<Camera.Size> comparatorSmaller = new Comparator<Camera.Size>() {
        public int compare(Camera.Size size, Camera.Size size2) {
            int i = size.width - size2.width;
            return i == 0 ? size.height - size2.height : i;
        }
    };
    private Camera mCameraDevice;
    private int mDefaultCameraID = -1;
    private int mFacing = 0;
    private boolean mIsPreviewing = false;
    private Camera.Parameters mParams;
    private int mPictureHeight = 1000;
    private int mPictureWidth = 1000;
    private int mPreferPreviewHeight = 640;
    private int mPreferPreviewWidth = 640;
    private int mPreviewHeight;
    private int mPreviewWidth;

    public interface CameraOpenCallback {
        void cameraReady();
    }

    private CameraInstance() {
    }

    public static synchronized CameraInstance getInstance() {
        CameraInstance cameraInstance;
        synchronized (CameraInstance.class) {
            if (mThisInstance == null) {
                mThisInstance = new CameraInstance();
            }
            cameraInstance = mThisInstance;
        }
        return cameraInstance;
    }

    public int previewWidth() {
        return this.mPreviewWidth;
    }

    public void setPreferPreviewSize(int i, int i2) {
        this.mPreferPreviewHeight = i;
        this.mPreferPreviewWidth = i2;
    }

    public int previewHeight() {
        return this.mPreviewHeight;
    }

    public boolean isPreviewing() {
        return this.mIsPreviewing;
    }

    public int pictureWidth() {
        return this.mPictureWidth;
    }

    public boolean tryOpenCamera(CameraOpenCallback cameraOpenCallback) {
        return tryOpenCamera(cameraOpenCallback, 0);
    }

    public int pictureHeight() {
        return this.mPictureHeight;
    }


    public int getFacing() {
        return this.mFacing;
    }

    public synchronized boolean tryOpenCamera(CameraOpenCallback callback, int facing) {
        Log.i(LOG_TAG, "try open camera...");

        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
                int numberOfCameras = Camera.getNumberOfCameras();

                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                for (int i = 0; i < numberOfCameras; i++) {
                    Camera.getCameraInfo(i, cameraInfo);
                    if (cameraInfo.facing == facing) {
                        mDefaultCameraID = i;
                        mFacing = facing;
                        break;
                    }
                }
            }
            stopPreview();
            if (mCameraDevice != null)
                mCameraDevice.release();

            if (mDefaultCameraID >= 0) {
                mCameraDevice = Camera.open(mDefaultCameraID);
            } else {
                mCameraDevice = Camera.open();
                mFacing = Camera.CameraInfo.CAMERA_FACING_BACK; //default: back facing
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Open Camera Failed!");
            e.printStackTrace();
            mCameraDevice = null;
            return false;
        }

        if (mCameraDevice != null) {
            Log.i(LOG_TAG, "Camera opened!");

            try {
                initCamera(DEFAULT_PREVIEW_RATE);
            } catch (Exception e) {
                mCameraDevice.release();
                mCameraDevice = null;
                return false;
            }

            if (callback != null) {
                callback.cameraReady();
            }

            return true;
        }

        return false;
    }

    public synchronized void stopCamera() {
        if (this.mCameraDevice != null) {
            this.mIsPreviewing = false;
            this.mCameraDevice.stopPreview();
            this.mCameraDevice.setPreviewCallback((Camera.PreviewCallback) null);
            this.mCameraDevice.release();
            this.mCameraDevice = null;
        }
    }

    public boolean isCameraOpened() {
        return this.mCameraDevice != null;
    }

    public synchronized void startPreview(SurfaceTexture surfaceTexture, Camera.PreviewCallback previewCallback) {
        Log.i("libCGE_java", "Camera startPreview...");
        if (this.mIsPreviewing) {
            Log.e("libCGE_java", "Err: camera is previewing...");
        } else if (this.mCameraDevice != null) {
            try {
                this.mCameraDevice.setPreviewTexture(surfaceTexture);
                this.mCameraDevice.setPreviewCallbackWithBuffer(previewCallback);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.mCameraDevice.startPreview();
            this.mIsPreviewing = true;
        }
    }

    public void startPreview(SurfaceTexture surfaceTexture) {
        startPreview(surfaceTexture, (Camera.PreviewCallback) null);
    }

    public void startPreview(Camera.PreviewCallback previewCallback) {
        startPreview((SurfaceTexture) null, previewCallback);
    }

    public synchronized Camera.Parameters getParams() {
        if (this.mCameraDevice == null) {
            return null;
        }
        return this.mCameraDevice.getParameters();
    }

    public synchronized void setParams(Camera.Parameters parameters) {
        if (this.mCameraDevice != null) {
            this.mParams = parameters;
            this.mCameraDevice.setParameters(parameters);
        }
    }

    public Camera getCameraDevice() {
        return this.mCameraDevice;
    }

    public synchronized void setFocusMode(String str) {
        if (this.mCameraDevice != null) {
            Camera.Parameters parameters = this.mCameraDevice.getParameters();
            this.mParams = parameters;
            if (parameters.getSupportedFocusModes().contains(str)) {
                this.mParams.setFocusMode(str);
            }
        }
    }

    public synchronized void setPictureSize(int i, int i2, boolean z) {
        if (this.mCameraDevice == null) {
            this.mPictureWidth = i;
            this.mPictureHeight = i2;
            return;
        }
        Camera.Parameters parameters = this.mCameraDevice.getParameters();
        this.mParams = parameters;
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        Camera.Size size = null;
        if (z) {
            Collections.sort(supportedPictureSizes, this.comparatorBigger);
            for (Camera.Size next : supportedPictureSizes) {
                if (size == null || (next.width >= i && next.height >= i2)) {
                    size = next;
                }
            }
        } else {
            Collections.sort(supportedPictureSizes, this.comparatorSmaller);
            for (Camera.Size next2 : supportedPictureSizes) {
                if (size == null || (next2.width <= i && next2.height <= i2)) {
                    size = next2;
                }
            }
        }
        this.mPictureWidth = size.width;
        int i3 = size.height;
        this.mPictureHeight = i3;
        try {
            this.mParams.setPictureSize(this.mPictureWidth, i3);
            this.mCameraDevice.setParameters(this.mParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public void initCamera(int i) {
        Camera camera = this.mCameraDevice;
        if (camera == null) {
            Log.e("libCGE_java", "initCamera: Camera is not opened!");
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        this.mParams = parameters;
        for (Integer intValue : parameters.getSupportedPictureFormats()) {
            Log.i("libCGE_java", String.format("Picture Format: %x", new Object[]{Integer.valueOf(intValue.intValue())}));
        }
        this.mParams.setPictureFormat(256);
        List<Camera.Size> supportedPictureSizes = this.mParams.getSupportedPictureSizes();
        Collections.sort(supportedPictureSizes, this.comparatorBigger);
        Camera.Size size = null;
        Camera.Size size2 = null;
        for (Camera.Size next : supportedPictureSizes) {
            Log.i("libCGE_java", String.format("Supported picture size: %d x %d", new Object[]{Integer.valueOf(next.width), Integer.valueOf(next.height)}));
            if (size2 == null || (next.width >= this.mPictureWidth && next.height >= this.mPictureHeight)) {
                size2 = next;
            }
        }
        List<Camera.Size> supportedPreviewSizes = this.mParams.getSupportedPreviewSizes();
        Collections.sort(supportedPreviewSizes, this.comparatorBigger);
        for (Camera.Size next2 : supportedPreviewSizes) {
            Log.i("libCGE_java", String.format("Supported preview size: %d x %d", new Object[]{Integer.valueOf(next2.width), Integer.valueOf(next2.height)}));
            if (size == null || (next2.width >= this.mPreferPreviewWidth && next2.height >= this.mPreferPreviewHeight)) {
                size = next2;
            }
        }
        int i2 = 0;
        for (Integer next3 : this.mParams.getSupportedPreviewFrameRates()) {
            Log.i("libCGE_java", "Supported frame rate: " + next3);
            if (i2 < next3.intValue()) {
                i2 = next3.intValue();
            }
        }
        this.mParams.setPreviewSize(size.width, size.height);
        this.mParams.setPictureSize(size2.width, size2.height);
        if (this.mParams.getSupportedFocusModes().contains("continuous-video")) {
            this.mParams.setFocusMode("continuous-video");
        }
        this.mParams.setPreviewFrameRate(i2);
        try {
            this.mCameraDevice.setParameters(this.mParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Camera.Parameters parameters2 = this.mCameraDevice.getParameters();
        this.mParams = parameters2;
        Camera.Size pictureSize = parameters2.getPictureSize();
        Camera.Size previewSize = this.mParams.getPreviewSize();
        this.mPreviewWidth = previewSize.width;
        this.mPreviewHeight = previewSize.height;
        this.mPictureWidth = pictureSize.width;
        this.mPictureHeight = pictureSize.height;
        Log.i("libCGE_java", String.format("Camera Picture Size: %d x %d", new Object[]{Integer.valueOf(pictureSize.width), Integer.valueOf(pictureSize.height)}));
        Log.i("libCGE_java", String.format("Camera Preview Size: %d x %d", new Object[]{Integer.valueOf(previewSize.width), Integer.valueOf(previewSize.height)}));
    }

    public void focusAtPoint(float f, float f2, Camera.AutoFocusCallback autoFocusCallback) {
        focusAtPoint(f, f2, 0.2f, autoFocusCallback);
    }

    public synchronized void stopPreview() {
        if (this.mIsPreviewing && this.mCameraDevice != null) {
            Log.i("libCGE_java", "Camera stopPreview...");
            this.mIsPreviewing = false;
            this.mCameraDevice.stopPreview();
        }
    }

    public synchronized void focusAtPoint(float f, float f2, float f3, Camera.AutoFocusCallback autoFocusCallback) {
        if (this.mCameraDevice == null) {
            Log.e("libCGE_java", "Error: focus after release.");
            return;
        }
        Camera.Parameters parameters = this.mCameraDevice.getParameters();
        this.mParams = parameters;
        if (parameters.getMaxNumMeteringAreas() > 0) {
            int i = (int) (f3 * 1000.0f);
            int i2 = ((int) ((f * 2000.0f) - 1000.0f)) - i;
            int i3 = ((int) ((f2 * 2000.0f) - 1000.0f)) - i;
            Rect rect = new Rect();
            rect.left = Math.max(i2, NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
            rect.top = Math.max(i3, NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
            rect.right = Math.min(i2 + i, 1000);
            rect.bottom = Math.min(i3 + i, 1000);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Camera.Area(rect, 800));
            try {
                this.mCameraDevice.cancelAutoFocus();
                this.mParams.setFocusMode("auto");
                this.mParams.setFocusAreas(arrayList);
                this.mCameraDevice.setParameters(this.mParams);
                this.mCameraDevice.autoFocus(autoFocusCallback);
            } catch (Exception e) {
                Log.e("libCGE_java", "Error: focusAtPoint failed: " + e.toString());
            }
        } else {
            Log.i("libCGE_java", "The device does not support metering areas...");
            try {
                this.mCameraDevice.autoFocus(autoFocusCallback);
            } catch (Exception e2) {
                Log.e("libCGE_java", "Error: focusAtPoint failed: " + e2.toString());
            }
        }
        return;
    }
}
