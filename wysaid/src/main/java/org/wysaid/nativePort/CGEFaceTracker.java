package org.wysaid.nativePort;

import android.graphics.Bitmap;
import android.graphics.PointF;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class CGEFaceTracker {
    protected static boolean sIsTrackerSetup = false;
    protected FaceResult mFaceResult = new FaceResult();
    protected long mNativeAddress = nativeCreateFaceTracker();

    public static class FaceResult {
        public FloatBuffer faceKeyPoints = ByteBuffer.allocateDirect(528).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    public static class FaceResultSimple {
        public PointF jawPos;
        public PointF leftEyePos;
        public PointF mouthPos;
        public PointF nosePos;
        public PointF rightEyepos;
    }

    private static native void nativeSetupTracker(String str, String str2, String str3);


    public native long nativeCreateFaceTracker();


    public native boolean nativeDetectFaceWithBuffer(long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4, FloatBuffer floatBuffer);


    public native float[] nativeDetectFaceWithSimpleResult(long j, Bitmap bitmap, boolean z);


    public native void nativeRelease(long j);

    static {
        System.loadLibrary("FaceTracker");
    }

    public static boolean isTrackerSetup() {
        return sIsTrackerSetup;
    }

    private CGEFaceTracker() {
    }

    public static CGEFaceTracker createFaceTracker() {
        if (!sIsTrackerSetup) {
            nativeSetupTracker((String) null, (String) null, (String) null);
            sIsTrackerSetup = true;
        }
        return new CGEFaceTracker();
    }

    public void release() {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeRelease(j);
            this.mNativeAddress = 0;
        }
    }


    public void finalize() throws Throwable {
        release();
        super.finalize();
    }

    public FaceResultSimple detectFaceWithSimpleResult(Bitmap bitmap, boolean z) {
        float[] nativeDetectFaceWithSimpleResult = nativeDetectFaceWithSimpleResult(this.mNativeAddress, bitmap, z);
        if (nativeDetectFaceWithSimpleResult == null) {
            return null;
        }
        FaceResultSimple faceResultSimple = new FaceResultSimple();
        faceResultSimple.leftEyePos = new PointF(nativeDetectFaceWithSimpleResult[0], nativeDetectFaceWithSimpleResult[1]);
        faceResultSimple.rightEyepos = new PointF(nativeDetectFaceWithSimpleResult[2], nativeDetectFaceWithSimpleResult[3]);
        faceResultSimple.nosePos = new PointF(nativeDetectFaceWithSimpleResult[4], nativeDetectFaceWithSimpleResult[5]);
        faceResultSimple.mouthPos = new PointF(nativeDetectFaceWithSimpleResult[6], nativeDetectFaceWithSimpleResult[7]);
        faceResultSimple.jawPos = new PointF(nativeDetectFaceWithSimpleResult[8], nativeDetectFaceWithSimpleResult[9]);
        return faceResultSimple;
    }

    public FaceResult getFaceResult() {
        return this.mFaceResult;
    }

    public boolean detectFaceWithGrayBuffer(ByteBuffer byteBuffer, int i, int i2, int i3) {
        return nativeDetectFaceWithBuffer(this.mNativeAddress, byteBuffer, i, i2, 1, i3, this.mFaceResult.faceKeyPoints);
    }

    public boolean detectFaceWithBGRABuffer(ByteBuffer byteBuffer, int i, int i2, int i3) {
        return nativeDetectFaceWithBuffer(this.mNativeAddress, byteBuffer, i, i2, 4, i3, this.mFaceResult.faceKeyPoints);
    }

    public boolean detectFaceWithBGRBuffer(ByteBuffer byteBuffer, int i, int i2, int i3) {
        return nativeDetectFaceWithBuffer(this.mNativeAddress, byteBuffer, i, i2, 3, i3, this.mFaceResult.faceKeyPoints);
    }
}
