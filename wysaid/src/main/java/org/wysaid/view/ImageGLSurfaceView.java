package org.wysaid.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.wysaid.nativePort.CGEImageHandler;
import org.wysaid.texUtils.TextureRenderer;

public class ImageGLSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer {
    public static final String LOG_TAG = "libCGE_java";
    protected DisplayMode mDisplayMode = DisplayMode.DISPLAY_SCALE_TO_FILL;
    protected float mFilterIntensity = 1.0f;
    protected CGEImageHandler mImageHandler;
    protected int mImageHeight;
    protected int mImageWidth;
    protected TextureRenderer.Viewport mRenderViewport = new TextureRenderer.Viewport();
    protected int mSettingIntensityCount = 1;
    protected final Object mSettingIntensityLock = new Object();
    protected OnSurfaceCreatedCallback mSurfaceCreatedCallback;
    protected int mViewHeight;
    protected int mViewWidth;

    public enum DisplayMode {
        DISPLAY_SCALE_TO_FILL,
        DISPLAY_ASPECT_FILL,
        DISPLAY_ASPECT_FIT
    }

    public interface OnSurfaceCreatedCallback {
        void surfaceCreated();
    }

    public interface QueryResultBitmapCallback {
        void get(Bitmap bitmap);
    }

    public CGEImageHandler getImageHandler() {
        return this.mImageHandler;
    }

    public TextureRenderer.Viewport getRenderViewport() {
        return this.mRenderViewport;
    }

    public int getImageWidth() {
        return this.mImageWidth;
    }

    public int getImageheight() {
        return this.mImageHeight;
    }

    public DisplayMode getDisplayMode() {
        return this.mDisplayMode;
    }

    public void setDisplayMode(DisplayMode displayMode) {
        this.mDisplayMode = displayMode;
        calcViewport();
        requestRender();
    }

    public void setFilterWithConfig(final String str) {
        if (this.mImageHandler != null) {
            queueEvent(new Runnable() {
                public void run() {
                    if (ImageGLSurfaceView.this.mImageHandler == null) {
                        Log.e("libCGE_java", "set config after release!!");
                        return;
                    }
                    ImageGLSurfaceView.this.mImageHandler.setFilterWithConfig(str);
                    ImageGLSurfaceView.this.requestRender();
                }
            });
        }
    }

    public void setFilterIntensityForIndex(float f, int i) {
        setFilterIntensityForIndex(f, i, true);
    }

    public void setFilterIntensityForIndex(float f, final int i, final boolean z) {
        if (this.mImageHandler != null) {
            this.mFilterIntensity = f;
            synchronized (this.mSettingIntensityLock) {
                if (this.mSettingIntensityCount <= 0) {
                    Log.i("libCGE_java", "Too fast, skipping...");
                    return;
                }
                this.mSettingIntensityCount--;
                queueEvent(new Runnable() {
                    public void run() {
                        if (ImageGLSurfaceView.this.mImageHandler == null) {
                            Log.e("libCGE_java", "set intensity after release!!");
                        } else {
                            ImageGLSurfaceView.this.mImageHandler.setFilterIntensityAtIndex(ImageGLSurfaceView.this.mFilterIntensity, i, z);
                            if (z) {
                                ImageGLSurfaceView.this.requestRender();
                            }
                        }
                        synchronized (ImageGLSurfaceView.this.mSettingIntensityLock) {
                            ImageGLSurfaceView.this.mSettingIntensityCount++;
                        }
                    }
                });
            }
        }
    }

    public void setFilterIntensity(float f) {
        if (this.mImageHandler != null) {
            this.mFilterIntensity = f;
            synchronized (this.mSettingIntensityLock) {
                if (this.mSettingIntensityCount <= 0) {
                    Log.i("libCGE_java", "Too fast, skipping...");
                    return;
                }
                this.mSettingIntensityCount--;
                queueEvent(new Runnable() {
                    public void run() {
                        if (ImageGLSurfaceView.this.mImageHandler == null) {
                            Log.e("libCGE_java", "set intensity after release!!");
                        } else {
                            ImageGLSurfaceView.this.mImageHandler.setFilterIntensity(ImageGLSurfaceView.this.mFilterIntensity, true);
                            ImageGLSurfaceView.this.requestRender();
                        }
                        synchronized (ImageGLSurfaceView.this.mSettingIntensityLock) {
                            ImageGLSurfaceView.this.mSettingIntensityCount++;
                        }
                    }
                });
            }
        }
    }

    public void flush(final boolean z, final Runnable runnable) {
        if (this.mImageHandler != null && runnable != null) {
            queueEvent(new Runnable() {
                public void run() {
                    if (ImageGLSurfaceView.this.mImageHandler == null) {
                        Log.e("libCGE_java", "flush after release!!");
                        return;
                    }
                    runnable.run();
                    if (z) {
                        ImageGLSurfaceView.this.mImageHandler.revertImage();
                        ImageGLSurfaceView.this.mImageHandler.processFilters();
                    }
                    ImageGLSurfaceView.this.requestRender();
                }
            });
        }
    }

    public void lazyFlush(final boolean z, final Runnable runnable) {
        if (this.mImageHandler != null && runnable != null) {
            synchronized (this.mSettingIntensityLock) {
                if (this.mSettingIntensityCount <= 0) {
                    Log.i("libCGE_java", "Too fast, skipping...");
                    return;
                }
                this.mSettingIntensityCount--;
                queueEvent(new Runnable() {
                    public void run() {
                        if (ImageGLSurfaceView.this.mImageHandler == null) {
                            Log.e("libCGE_java", "flush after release!!");
                        } else {
                            if (z) {
                                ImageGLSurfaceView.this.mImageHandler.revertImage();
                                ImageGLSurfaceView.this.mImageHandler.processFilters();
                            }
                            runnable.run();
                            ImageGLSurfaceView.this.requestRender();
                        }
                        synchronized (ImageGLSurfaceView.this.mSettingIntensityLock) {
                            ImageGLSurfaceView.this.mSettingIntensityCount++;
                        }
                    }
                });
            }
        }
    }

    public void setImageBitmap(final Bitmap bitmap) {
        if (bitmap != null) {
            if (this.mImageHandler == null) {
                Log.e("libCGE_java", "Handler not initialized!");
                return;
            }
            this.mImageWidth = bitmap.getWidth();
            this.mImageHeight = bitmap.getHeight();
            queueEvent(new Runnable() {
                public void run() {
                    if (ImageGLSurfaceView.this.mImageHandler == null) {
                        Log.e("libCGE_java", "set image after release!!");
                    } else if (ImageGLSurfaceView.this.mImageHandler.initWithBitmap(bitmap)) {
                        ImageGLSurfaceView.this.calcViewport();
                        ImageGLSurfaceView.this.requestRender();
                    } else {
                        Log.e("libCGE_java", "setImageBitmap: init handler failed!");
                    }
                }
            });
        }
    }

    public void getResultBitmap(final QueryResultBitmapCallback queryResultBitmapCallback) {
        if (queryResultBitmapCallback != null) {
            queueEvent(new Runnable() {
                public void run() {
                    queryResultBitmapCallback.get(ImageGLSurfaceView.this.mImageHandler.getResultBitmap());
                }
            });
        }
    }

    public ImageGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 8, 0);
        getHolder().setFormat(1);
        setRenderer(this);
        setRenderMode(0);
        Log.i("libCGE_java", "ImageGLSurfaceView Construct...");
    }

    public void setSurfaceCreatedCallback(OnSurfaceCreatedCallback onSurfaceCreatedCallback) {
        this.mSurfaceCreatedCallback = onSurfaceCreatedCallback;
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        Log.i("libCGE_java", "ImageGLSurfaceView onSurfaceCreated...");
        GLES20.glDisable(2929);
        GLES20.glDisable(2960);
        CGEImageHandler cGEImageHandler = new CGEImageHandler();
        this.mImageHandler = cGEImageHandler;
        cGEImageHandler.setDrawerFlipScale(1.0f, -1.0f);
        OnSurfaceCreatedCallback onSurfaceCreatedCallback = this.mSurfaceCreatedCallback;
        if (onSurfaceCreatedCallback != null) {
            onSurfaceCreatedCallback.surfaceCreated();
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        this.mViewWidth = i;
        this.mViewHeight = i2;
        calcViewport();
    }

    public void onDrawFrame(GL10 gl10) {
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glClear(16384);
        if (this.mImageHandler != null) {
            GLES20.glViewport(this.mRenderViewport.x, this.mRenderViewport.y, this.mRenderViewport.width, this.mRenderViewport.height);
            this.mImageHandler.drawResult();
        }
    }

    public void release() {
        if (this.mImageHandler != null) {
            queueEvent(new Runnable() {
                public void run() {
                    Log.i("libCGE_java", "ImageGLSurfaceView release...");
                    if (ImageGLSurfaceView.this.mImageHandler != null) {
                        ImageGLSurfaceView.this.mImageHandler.release();
                        ImageGLSurfaceView.this.mImageHandler = null;
                    }
                }
            });
        }
    }


    public void calcViewport() {
        int i;
        int i2;
        int i3;
        if (this.mDisplayMode == DisplayMode.DISPLAY_SCALE_TO_FILL) {
            this.mRenderViewport.x = 0;
            this.mRenderViewport.y = 0;
            this.mRenderViewport.width = this.mViewWidth;
            this.mRenderViewport.height = this.mViewHeight;
            return;
        }
        float f = ((float) this.mImageWidth) / ((float) this.mImageHeight);
        float f2 = f / (((float) this.mViewWidth) / ((float) this.mViewHeight));
        int i4 = AnonymousClass9.$SwitchMap$org$wysaid$view$ImageGLSurfaceView$DisplayMode[this.mDisplayMode.ordinal()];
        if (i4 != 1) {
            if (i4 != 2) {
                Log.i("libCGE_java", "Error occured, please check the code...");
                return;
            } else if (((double) f2) < 1.0d) {
                i = this.mViewHeight;
            } else {
                i3 = this.mViewWidth;
                int i5 = i3;
                i = (int) (((float) i3) / f);
                i2 = i5;
                this.mRenderViewport.width = i2;
                this.mRenderViewport.height = i;
                this.mRenderViewport.x = (this.mViewWidth - i2) / 2;
                this.mRenderViewport.y = (this.mViewHeight - i) / 2;
                Log.i("libCGE_java", String.format("View port: %d, %d, %d, %d", new Object[]{Integer.valueOf(this.mRenderViewport.x), Integer.valueOf(this.mRenderViewport.y), Integer.valueOf(this.mRenderViewport.width), Integer.valueOf(this.mRenderViewport.height)}));
            }
        } else if (((double) f2) > 1.0d) {
            i = this.mViewHeight;
        } else {
            i3 = this.mViewWidth;
            int i52 = i3;
            i = (int) (((float) i3) / f);
            i2 = i52;
            this.mRenderViewport.width = i2;
            this.mRenderViewport.height = i;
            this.mRenderViewport.x = (this.mViewWidth - i2) / 2;
            this.mRenderViewport.y = (this.mViewHeight - i) / 2;
            Log.i("libCGE_java", String.format("View port: %d, %d, %d, %d", new Object[]{Integer.valueOf(this.mRenderViewport.x), Integer.valueOf(this.mRenderViewport.y), Integer.valueOf(this.mRenderViewport.width), Integer.valueOf(this.mRenderViewport.height)}));
        }
        i2 = (int) (((float) i) * f);
        this.mRenderViewport.width = i2;
        this.mRenderViewport.height = i;
        this.mRenderViewport.x = (this.mViewWidth - i2) / 2;
        this.mRenderViewport.y = (this.mViewHeight - i) / 2;
        Log.i("libCGE_java", String.format("View port: %d, %d, %d, %d", new Object[]{Integer.valueOf(this.mRenderViewport.x), Integer.valueOf(this.mRenderViewport.y), Integer.valueOf(this.mRenderViewport.width), Integer.valueOf(this.mRenderViewport.height)}));
    }


    static  class AnonymousClass9 {
        static final  int[] $SwitchMap$org$wysaid$view$ImageGLSurfaceView$DisplayMode;


        static {
            int[] iArr = new int[DisplayMode.values().length];
            $SwitchMap$org$wysaid$view$ImageGLSurfaceView$DisplayMode = iArr;
            iArr[DisplayMode.DISPLAY_ASPECT_FILL.ordinal()] = 1;
            $SwitchMap$org$wysaid$view$ImageGLSurfaceView$DisplayMode[DisplayMode.DISPLAY_ASPECT_FIT.ordinal()] = 2;
        }
    }
}
