package org.wysaid.nativePort;

public class CGEFrameRenderer {
    protected long mNativeAddress;


    public native void nativeBindImageFBO(long j);


    public native long nativeCreateRenderer();


    public native void nativeDrawCache(long j);


    public native long nativeGetImageHandler(long j);


    public native boolean nativeInit(long j, int i, int i2, int i3, int i4);


    public native void nativeProcessWithFilter(long j, long j2);


    public native int nativeQueryBufferTexture(long j);


    public native void nativeRelease(long j);


    public native void nativeRender(long j, int i, int i2, int i3, int i4);


    public native void nativeRunProc(long j);


    public native void nativeSetFilterIntensity(long j, float f);


    public native void nativeSetFilterWithAddr(long j, long j2);


    public native void nativeSetFilterWithConfig(long j, String str);


    public native void nativeSetMaskFlipScale(long j, float f, float f2);


    public native void nativeSetMaskRotation(long j, float f);


    public native void nativeSetMaskTexture(long j, int i, float f);


    public native void nativeSetMaskTextureRatio(long j, float f);


    public native void nativeSetRenderFlipScale(long j, float f, float f2);


    public native void nativeSetRenderRotation(long j, float f);


    public native void nativeSetSrcFlipScale(long j, float f, float f2);


    public native void nativeSetSrcRotation(long j, float f);


    public native void nativeSrcResize(long j, int i, int i2);


    public native void nativeSwapBufferFBO(long j);


    public native void nativeUpdate(long j, int i, float[] fArr);

    static {
        NativeLibraryLoader.load();
    }

    public CGEFrameRenderer() {
        this.mNativeAddress = nativeCreateRenderer();
    }

    protected CGEFrameRenderer(int i) {
    }

    public boolean init(int i, int i2, int i3, int i4) {
        long j = this.mNativeAddress;
        if (j != 0) {
            return nativeInit(j, i, i2, i3, i4);
        }
        return false;
    }

    public void update(int i, float[] fArr) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeUpdate(j, i, fArr);
        }
    }

    public void runProc() {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeRunProc(j);
        }
    }

    public void render(int i, int i2, int i3, int i4) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeRender(j, i, i2, i3, i4);
        }
    }

    public void drawCache() {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeDrawCache(j);
        }
    }

    public void setSrcRotation(float f) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeSetSrcRotation(j, f);
        }
    }

    public void setSrcFlipScale(float f, float f2) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeSetSrcFlipScale(j, f, f2);
        }
    }

    public void setRenderRotation(float f) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeSetRenderRotation(j, f);
        }
    }

    public void setRenderFlipScale(float f, float f2) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeSetRenderFlipScale(j, f, f2);
        }
    }

    public void setFilterWidthConfig(String str) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeSetFilterWithConfig(j, str);
        }
    }

    public void setMaskRotation(float f) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeSetMaskRotation(j, f);
        }
    }

    public void setMaskFlipScale(float f, float f2) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeSetMaskFlipScale(j, f, f2);
        }
    }

    public void setFilterIntensity(float f) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeSetFilterIntensity(j, f);
        }
    }

    public void srcResize(int i, int i2) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeSrcResize(j, i, i2);
        }
    }

    public void release() {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeRelease(j);
            this.mNativeAddress = 0;
        }
    }

    public void setMaskTexture(int i, float f) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeSetMaskTexture(j, i, f);
        }
    }

    public void setMaskTextureRatio(float f) {
        long j = this.mNativeAddress;
        if (j != 0) {
            nativeSetMaskTextureRatio(j, f);
        }
    }

    public int queryBufferTexture() {
        long j = this.mNativeAddress;
        if (j != 0) {
            return nativeQueryBufferTexture(j);
        }
        return 0;
    }

    public long getImageHandler() {
        return nativeGetImageHandler(this.mNativeAddress);
    }

    public void bindImageFBO() {
        nativeBindImageFBO(this.mNativeAddress);
    }

    public void swapImageFBO() {
        nativeSwapBufferFBO(this.mNativeAddress);
    }

    public void processWithFilter(long j) {
        nativeProcessWithFilter(this.mNativeAddress, j);
    }

    public void setNativeFilter(long j) {
        nativeSetFilterWithAddr(this.mNativeAddress, j);
    }
}
