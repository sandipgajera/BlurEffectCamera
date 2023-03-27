package org.wysaid.common;

import android.opengl.EGL14;
import android.util.Log;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

public class SharedContext {
    public static final int EGL_RECORDABLE_ANDROID = 12610;
    public static final String LOG_TAG = "libCGE_java";
    private static int mBitsA = 8;
    private static int mBitsB = 8;
    private static int mBitsG = 8;
    private static int mBitsR = 8;
    private EGLConfig mConfig;
    private EGLContext mContext;
    private EGLDisplay mDisplay;
    private EGL10 mEgl;
    private GL10 mGl;
    private EGLSurface mSurface;

    public static void setContextColorBits(int i, int i2, int i3, int i4) {
        mBitsR = i;
        mBitsG = i2;
        mBitsB = i3;
        mBitsA = i4;
    }

    public static SharedContext create(EGLContext eGLContext, int i, int i2) {
        return create(eGLContext, i, i2, 1, (Object) null);
    }

    public static SharedContext create(EGLContext eGLContext, int i, int i2, int i3, Object obj) {
        SharedContext sharedContext = new SharedContext();
        if (sharedContext.initEGL(eGLContext, i, i2, i3, obj)) {
            return sharedContext;
        }
        sharedContext.release();
        return null;
    }

    public static SharedContext create() {
        return create(EGL10.EGL_NO_CONTEXT, 64, 64, 1, (Object) null);
    }

    public static SharedContext create(int i, int i2) {
        return create(EGL10.EGL_NO_CONTEXT, i, i2, 1, (Object) null);
    }

    public EGLContext getContext() {
        return this.mContext;
    }

    public EGLDisplay getDisplay() {
        return this.mDisplay;
    }

    public EGLSurface getSurface() {
        return this.mSurface;
    }

    public EGL10 getEGL() {
        return this.mEgl;
    }

    public GL10 getGL() {
        return this.mGl;
    }

    SharedContext() {
    }

    public boolean swapBuffers() {
        return this.mEgl.eglSwapBuffers(this.mDisplay, this.mSurface);
    }

    private boolean initEGL(EGLContext context, int width, int height, int contextType, Object obj) {

        int[] contextAttribList = {
                EGL14.EGL_CONTEXT_CLIENT_VERSION, 2,
                EGL10.EGL_NONE
        };

        int[] configSpec = {
                EGL10.EGL_SURFACE_TYPE, contextType,
                EGL10.EGL_RENDERABLE_TYPE, EGL14.EGL_OPENGL_ES2_BIT,
                EGL10.EGL_RED_SIZE, 8, EGL10.EGL_GREEN_SIZE, 8,
                EGL10.EGL_BLUE_SIZE, 8, EGL10.EGL_ALPHA_SIZE, 8,
                EGL10.EGL_NONE
        };

        EGLConfig[] configs = new EGLConfig[1];
        int[] numConfig = new int[1];
        int[] version = new int[2];

        int surfaceAttribList[] = {
                EGL10.EGL_WIDTH, width,
                EGL10.EGL_HEIGHT, height,
                EGL10.EGL_NONE
        };

        mEgl = (EGL10) EGLContext.getEGL();

        if((mDisplay = mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY)) == EGL10.EGL_NO_DISPLAY) {
            Log.e(LOG_TAG, String.format("eglGetDisplay() returned error 0x%x", mEgl.eglGetError()));
            return false;
        }

        if(!mEgl.eglInitialize(mDisplay, version)) {
            Log.e(LOG_TAG, String.format("eglInitialize() returned error 0x%x", mEgl.eglGetError()));
            return false;
        }

        Log.i(LOG_TAG, String.format("eglInitialize - major: %d, minor: %d", version[0], version[1]));

        if(!mEgl.eglChooseConfig(mDisplay, configSpec, configs, 1, numConfig)) {
            Log.e(LOG_TAG, String.format("eglChooseConfig() returned error 0x%x", mEgl.eglGetError()));
            return false;
        }

        Log.i(LOG_TAG, String.format("Config num: %d, has sharedContext: %s", numConfig[0], context == EGL10.EGL_NO_CONTEXT ? "NO" : "YES"));

        mConfig = configs[0];

        mContext = mEgl.eglCreateContext(mDisplay, mConfig,
                context, contextAttribList);
        if (mContext == EGL10.EGL_NO_CONTEXT) {
            Log.e(LOG_TAG, "eglCreateContext Failed!");
            return false;
        }

        switch (contextType) {
            case EGL10.EGL_PIXMAP_BIT:
                mSurface = mEgl.eglCreatePixmapSurface(mDisplay, mConfig, obj, surfaceAttribList);
                break;
            case EGL10.EGL_WINDOW_BIT:
                mSurface = mEgl.eglCreateWindowSurface(mDisplay, mConfig, obj, surfaceAttribList);
                break;
            case EGL10.EGL_PBUFFER_BIT:
            case EGL_RECORDABLE_ANDROID:
                mSurface = mEgl.eglCreatePbufferSurface(mDisplay, mConfig,
                        surfaceAttribList);
        }

        if (mSurface == EGL10.EGL_NO_SURFACE) {
            Log.e(LOG_TAG, "eglCreatePbufferSurface Failed!");
            return false;
        }

        if (!mEgl.eglMakeCurrent(mDisplay, mSurface, mSurface, mContext)) {
            Log.e(LOG_TAG, "eglMakeCurrent failed:" + mEgl.eglGetError());
            return false;
        }

        int[] clientVersion = new int[1];
        mEgl.eglQueryContext(mDisplay, mContext, EGL14.EGL_CONTEXT_CLIENT_VERSION, clientVersion);
        Log.i(LOG_TAG, "EGLContext created, client version " + clientVersion[0]);

        mGl = (GL10) mContext.getGL();

        return true;
    }

    public void release() {
        Log.i("libCGE_java", "#### CGESharedGLContext Destroying context... ####");
        if (this.mDisplay != EGL10.EGL_NO_DISPLAY) {
            this.mEgl.eglMakeCurrent(this.mDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            this.mEgl.eglDestroyContext(this.mDisplay, this.mContext);
            this.mEgl.eglDestroySurface(this.mDisplay, this.mSurface);
            this.mEgl.eglTerminate(this.mDisplay);
        }
        this.mDisplay = EGL10.EGL_NO_DISPLAY;
        this.mSurface = EGL10.EGL_NO_SURFACE;
        this.mContext = EGL10.EGL_NO_CONTEXT;
    }

    public void makeCurrent() {
        EGL10 egl10 = this.mEgl;
        EGLDisplay eGLDisplay = this.mDisplay;
        EGLSurface eGLSurface = this.mSurface;
        if (!egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.mContext)) {
            Log.e("libCGE_java", "eglMakeCurrent failed:" + this.mEgl.eglGetError());
        }
    }

}
