package org.wysaid.nativePort;

import android.util.Log;

public class CGEDeformFilterWrapper {
    protected long mNativeAddress;


    public native void nativeBloatDeform(long j, float f, float f2, float f3, float f4, float f5, float f6);


    public native boolean nativeCanRedo(long j);


    public native boolean nativeCanUndo(long j);


    public native long nativeCreate(int i, int i2, float f);


    public native void nativeForwardDeform(long j, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8);


    public native boolean nativePushDeformStep(long j);


    public native boolean nativeRedo(long j);


    public native void nativeRelease(long j);


    public native void nativeRestore(long j);


    public native void nativeRestoreWithIntensity(long j, float f);


    public native void nativeRestoreWithPoint(long j, float f, float f2, float f3, float f4, float f5, float f6);


    public native void nativeSetUndoSteps(long j, int i);


    public native void nativeShowMesh(long j, boolean z);


    public native boolean nativeUndo(long j);


    public native void nativeWrinkleDeform(long j, float f, float f2, float f3, float f4, float f5, float f6);

    static {
        NativeLibraryLoader.load();
    }

    private CGEDeformFilterWrapper(int i, int i2, float f) {
        this.mNativeAddress = nativeCreate(i, i2, f);
    }

    public static CGEDeformFilterWrapper create(int i, int i2, float f) {
        CGEDeformFilterWrapper cGEDeformFilterWrapper = new CGEDeformFilterWrapper(i, i2, f);
        if (cGEDeformFilterWrapper.mNativeAddress != 0) {
            return cGEDeformFilterWrapper;
        }
        cGEDeformFilterWrapper.release(true);
        Log.e("libCGE_java", "CGEDeformFilterWrapper.create failed!");
        return null;
    }

    public void release(boolean z) {
        long j = this.mNativeAddress;
        if (j != 0) {
            if (z) {
                nativeRelease(j);
            }
            this.mNativeAddress = 0;
        }
    }

    public void restore() {
        nativeRestore(this.mNativeAddress);
    }

    public void restoreWithIntensity(float f) {
        nativeRestoreWithIntensity(this.mNativeAddress, f);
    }

    public void forwardDeform(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        nativeForwardDeform(this.mNativeAddress, f, f2, f3, f4, f5, f6, f7, f8);
    }

    public void restoreWithPoint(float f, float f2, float f3, float f4, float f5, float f6) {
        nativeRestoreWithPoint(this.mNativeAddress, f, f2, f3, f4, f5, f6);
    }

    public void bloatDeform(float f, float f2, float f3, float f4, float f5, float f6) {
        nativeBloatDeform(this.mNativeAddress, f, f2, f3, f4, f5, f6);
    }

    public void wrinkleDeform(float f, float f2, float f3, float f4, float f5, float f6) {
        nativeWrinkleDeform(this.mNativeAddress, f, f2, f3, f4, f5, f6);
    }

    public void setUndoSteps(int i) {
        nativeSetUndoSteps(this.mNativeAddress, i);
    }

    public boolean canUndo() {
        return nativeCanUndo(this.mNativeAddress);
    }

    public boolean canRedo() {
        return nativeCanRedo(this.mNativeAddress);
    }

    public boolean undo() {
        return nativeUndo(this.mNativeAddress);
    }

    public boolean redo() {
        return nativeRedo(this.mNativeAddress);
    }

    public boolean pushDeformStep() {
        return nativePushDeformStep(this.mNativeAddress);
    }

    public void showMesh(boolean z) {
        nativeShowMesh(this.mNativeAddress, z);
    }

    public long getNativeAddress() {
        return this.mNativeAddress;
    }
}
