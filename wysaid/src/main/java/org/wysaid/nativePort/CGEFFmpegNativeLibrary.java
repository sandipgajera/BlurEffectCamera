package org.wysaid.nativePort;

import android.graphics.Bitmap;

public class CGEFFmpegNativeLibrary {
    public static native void avRegisterAll();

    private static native boolean nativeGenerateVideoWithFilter(String str, String str2, String str3, float f, Bitmap bitmap, int i, float f2, boolean z);

    static {
        NativeLibraryLoader.load();
    }

    public static boolean generateVideoWithFilter(String str, String str2, String str3, float f, Bitmap bitmap, CGENativeLibrary.TextureBlendMode textureBlendMode, float f2, boolean z) {
        return nativeGenerateVideoWithFilter(str, str2, str3, f, bitmap, textureBlendMode == null ? 0 : textureBlendMode.ordinal(), f2, z);
    }
}
