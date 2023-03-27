package org.wysaid.nativePort;

public class NativeLibraryLoader {
    public static void load() {
        try {

            System.loadLibrary("ffmpeg");
            System.loadLibrary("CGE");
            System.loadLibrary("CGEExt");
            CGEFFmpegNativeLibrary.avRegisterAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
