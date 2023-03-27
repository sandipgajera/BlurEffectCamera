package org.wysaid.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.wysaid.common.Common;
import org.wysaid.texUtils.TextureRenderer;
import org.wysaid.texUtils.TextureRendererDrawOrigin;
import org.wysaid.texUtils.TextureRendererMask;

public class SimplePlayerGLSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener {
    static final  boolean $assertionsDisabled = false;
    public static final String LOG_TAG = "libCGE_java";

    public TextureRenderer mDrawer;
    private float mDrawerFlipScaleX = 1.0f;
    private float mDrawerFlipScaleY = 1.0f;
    private boolean mFitFullView = false;
    private long mFramesCount2 = 0;

    public boolean mIsUsingMask = false;
    private long mLastTimestamp2 = 0;
    private float mMaskAspectRatio = 1.0f;
    private OnCreateCallback mOnCreateCallback;
    PlayCompletionCallback mPlayCompletionCallback;

    public MediaPlayer mPlayer;
    PlayerInitializeCallback mPlayerInitCallback;
    PlayPreparedCallback mPreparedCallback;

    public TextureRenderer.Viewport mRenderViewport = new TextureRenderer.Viewport();

    public SurfaceTexture mSurfaceTexture;
    private long mTimeCount2 = 0;
    private float[] mTransformMatrix = new float[16];

    public int mVideoHeight = 1000;

    public int mVideoTextureID;
    private Uri mVideoUri;

    public int mVideoWidth = 1000;
    private int mViewHeight = 1000;
    private int mViewWidth = 1000;

    public interface OnCreateCallback {
        void createOK();
    }

    public interface PlayCompletionCallback {
        void playComplete(MediaPlayer mediaPlayer);

        boolean playFailed(MediaPlayer mediaPlayer, int i, int i2);
    }

    public interface PlayPreparedCallback {
        void playPrepared(MediaPlayer mediaPlayer);
    }

    public interface PlayerInitializeCallback {
        void initPlayer(MediaPlayer mediaPlayer);
    }

    public interface SetMaskBitmapCallback {
        void setMaskOK(TextureRendererMask textureRendererMask);

        void unsetMaskOK(TextureRenderer textureRenderer);
    }

    public interface TakeShotCallback {
        void takeShotOK(Bitmap bitmap);
    }

    public void setTextureRenderer(TextureRenderer textureRenderer) {
        TextureRenderer textureRenderer2 = this.mDrawer;
        if (textureRenderer2 == null) {
            Log.e("libCGE_java", "Invalid Drawer!");
        } else if (textureRenderer2 != textureRenderer) {
            textureRenderer2.release();
            this.mDrawer = textureRenderer;
            calcViewport();
        }
    }

    public boolean isUsingMask() {
        return this.mIsUsingMask;
    }

    public int getViewWidth() {
        return this.mViewWidth;
    }

    public int getViewheight() {
        return this.mViewHeight;
    }

    public void setFitFullView(boolean z) {
        this.mFitFullView = z;
        if (this.mDrawer != null) {
            calcViewport();
        }
    }

    public void setPlayerInitializeCallback(PlayerInitializeCallback playerInitializeCallback) {
        this.mPlayerInitCallback = playerInitializeCallback;
    }

    public synchronized void setVideoUri(Uri uri, PlayPreparedCallback playPreparedCallback, PlayCompletionCallback playCompletionCallback) {
        this.mVideoUri = uri;
        this.mPreparedCallback = playPreparedCallback;
        this.mPlayCompletionCallback = playCompletionCallback;
        if (this.mDrawer != null) {
            queueEvent(new Runnable() {
                public void run() {
                    Log.i("libCGE_java", "setVideoUri...");
                    if (SimplePlayerGLSurfaceView.this.mSurfaceTexture == null || SimplePlayerGLSurfaceView.this.mVideoTextureID == 0) {
                        int unused = SimplePlayerGLSurfaceView.this.mVideoTextureID = Common.genSurfaceTextureID();
                        SurfaceTexture unused2 = SimplePlayerGLSurfaceView.this.mSurfaceTexture = new SurfaceTexture(SimplePlayerGLSurfaceView.this.mVideoTextureID);
                        SimplePlayerGLSurfaceView.this.mSurfaceTexture.setOnFrameAvailableListener(SimplePlayerGLSurfaceView.this);
                    }
                    SimplePlayerGLSurfaceView.this._useUri();
                }
            });
        }
    }

    public void setMaskBitmap(Bitmap bitmap, boolean z) {
        setMaskBitmap(bitmap, z, (SetMaskBitmapCallback) null);
    }

    public synchronized void setMaskBitmap(final Bitmap bitmap, final boolean z, final SetMaskBitmapCallback setMaskBitmapCallback) {
        if (this.mDrawer == null) {
            Log.e("libCGE_java", "setMaskBitmap after release!");
        } else {
            queueEvent(new Runnable() {
                public void run() {
                    if (bitmap == null) {
                        Log.i("libCGE_java", "Cancel Mask Bitmap!");
                        SimplePlayerGLSurfaceView.this.setMaskTexture(0, 1.0f);

                        if (setMaskBitmapCallback != null) {
                            setMaskBitmapCallback.unsetMaskOK(SimplePlayerGLSurfaceView.this.mDrawer);
                            return;
                        }
                        return;
                    }
                    Log.i("libCGE_java", "Use Mask Bitmap!");
                    int[] iArr = {0};
                    GLES20.glGenTextures(1, iArr, 0);
                    GLES20.glBindTexture(3553, iArr[0]);
                    GLUtils.texImage2D(3553, 0, bitmap, 0);
                    GLES20.glTexParameteri(3553, 10241, 9728);
                    GLES20.glTexParameteri(3553, 10240, 9728);
                    GLES20.glTexParameteri(3553, 10242, 33071);
                    GLES20.glTexParameteri(3553, 10243, 33071);
                    SimplePlayerGLSurfaceView.this.setMaskTexture(iArr[0], ((float) bitmap.getWidth()) / ((float) bitmap.getHeight()));
                    if (setMaskBitmapCallback != null && (SimplePlayerGLSurfaceView.this.mDrawer instanceof TextureRendererMask)) {
                        setMaskBitmapCallback.setMaskOK((TextureRendererMask) SimplePlayerGLSurfaceView.this.mDrawer);
                    }
                    if (z) {
                        bitmap.recycle();
                    }
                }
            });
        }
    }

    public synchronized void setMaskTexture(int i, float f) {
        Log.i("libCGE_java", "setMaskTexture... ");
        if (i == 0) {
            if (this.mDrawer instanceof TextureRendererMask) {
                this.mDrawer.release();
                this.mDrawer = TextureRendererDrawOrigin.create(true);
            }
            this.mIsUsingMask = false;
        } else {
            if (!(this.mDrawer instanceof TextureRendererMask)) {
                this.mDrawer.release();
                TextureRendererMask create = TextureRendererMask.create(true);
                create.setMaskTexture(i);
                this.mDrawer = create;
            }
            this.mIsUsingMask = true;
        }
        this.mMaskAspectRatio = f;
        calcViewport();
    }

    public synchronized MediaPlayer getPlayer() {
        if (this.mPlayer == null) {
            Log.e("libCGE_java", "Player is not initialized!");
        }
        return this.mPlayer;
    }

    public void setOnCreateCallback(final OnCreateCallback onCreateCallback) {
        if (this.mDrawer == null) {
            this.mOnCreateCallback = onCreateCallback;
        } else {
            queueEvent(new Runnable() {
                public void run() {
                    onCreateCallback.createOK();
                }
            });
        }
    }

    public SimplePlayerGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.i("libCGE_java", "MyGLSurfaceView Construct...");
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 8, 0);
        getHolder().setFormat(1);
        setRenderer(this);
        setRenderMode(0);
        setZOrderOnTop(true);
        Log.i("libCGE_java", "MyGLSurfaceView Construct OK...");
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        Log.i("libCGE_java", "video player onSurfaceCreated...");
        GLES20.glDisable(2929);
        GLES20.glDisable(2960);
        TextureRendererDrawOrigin create = TextureRendererDrawOrigin.create(true);
        this.mDrawer = create;
        if (create == null) {
            Log.e("libCGE_java", "Create Drawer Failed!");
            return;
        }
        OnCreateCallback onCreateCallback = this.mOnCreateCallback;
        if (onCreateCallback != null) {
            onCreateCallback.createOK();
        }
        if (this.mVideoUri == null) {
            return;
        }
        if (this.mSurfaceTexture == null || this.mVideoTextureID == 0) {
            this.mVideoTextureID = Common.genSurfaceTextureID();
            SurfaceTexture surfaceTexture = new SurfaceTexture(this.mVideoTextureID);
            this.mSurfaceTexture = surfaceTexture;
            surfaceTexture.setOnFrameAvailableListener(this);
            _useUri();
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        this.mViewWidth = i;
        this.mViewHeight = i2;
        calcViewport();
    }

    public void release() {
        Log.i("libCGE_java", "Video player view release...");
        if (this.mPlayer != null) {
            queueEvent(new Runnable() {
                public void run() {
                    Log.i("libCGE_java", "Video player view release run...");
                    if (SimplePlayerGLSurfaceView.this.mPlayer != null) {
                        SimplePlayerGLSurfaceView.this.mPlayer.setSurface((Surface) null);
                        if (SimplePlayerGLSurfaceView.this.mPlayer.isPlaying()) {
                            SimplePlayerGLSurfaceView.this.mPlayer.stop();
                        }
                        SimplePlayerGLSurfaceView.this.mPlayer.release();
                        MediaPlayer unused = SimplePlayerGLSurfaceView.this.mPlayer = null;
                    }
                    if (SimplePlayerGLSurfaceView.this.mDrawer != null) {
                        SimplePlayerGLSurfaceView.this.mDrawer.release();
                        TextureRenderer unused2 = SimplePlayerGLSurfaceView.this.mDrawer = null;
                    }
                    if (SimplePlayerGLSurfaceView.this.mSurfaceTexture != null) {
                        SimplePlayerGLSurfaceView.this.mSurfaceTexture.release();
                        SurfaceTexture unused3 = SimplePlayerGLSurfaceView.this.mSurfaceTexture = null;
                    }
                    if (SimplePlayerGLSurfaceView.this.mVideoTextureID != 0) {
                        GLES20.glDeleteTextures(1, new int[]{SimplePlayerGLSurfaceView.this.mVideoTextureID}, 0);
                        int unused4 = SimplePlayerGLSurfaceView.this.mVideoTextureID = 0;
                    }
                    boolean unused5 = SimplePlayerGLSurfaceView.this.mIsUsingMask = false;
                    SimplePlayerGLSurfaceView.this.mPreparedCallback = null;
                    SimplePlayerGLSurfaceView.this.mPlayCompletionCallback = null;
                    Log.i("libCGE_java", "Video player view release OK");
                }
            });
        }
    }

    public void onPause() {
        Log.i("libCGE_java", "surfaceview onPause ...");
        super.onPause();
    }

    public void onDrawFrame(GL10 gl10) {
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.updateTexImage();
            if (this.mPlayer.isPlaying()) {
                GLES20.glBindFramebuffer(36160, 0);
                GLES20.glClear(16384);
                GLES20.glViewport(0, 0, this.mViewWidth, this.mViewHeight);
                this.mSurfaceTexture.getTransformMatrix(this.mTransformMatrix);
                this.mDrawer.setTransform(this.mTransformMatrix);
                this.mDrawer.renderTexture(this.mVideoTextureID, this.mRenderViewport);
            }
        }
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        requestRender();
        if (this.mLastTimestamp2 == 0) {
            this.mLastTimestamp2 = System.currentTimeMillis();
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.mFramesCount2 + 1;
        this.mFramesCount2 = j;
        long j2 = this.mTimeCount2 + (currentTimeMillis - this.mLastTimestamp2);
        this.mTimeCount2 = j2;
        this.mLastTimestamp2 = currentTimeMillis;
        if (((double) j2) >= 1000.0d) {
            Log.i("libCGE_java", String.format("播放帧率: %d", new Object[]{Long.valueOf(j)}));
            this.mTimeCount2 = (long) (((double) this.mTimeCount2) - 1000.0d);
            this.mFramesCount2 = 0;
        }
    }


    public void calcViewport() {
        float f;
        if (this.mIsUsingMask) {
            flushMaskAspectRatio();
            f = this.mMaskAspectRatio;
        } else {
            this.mDrawer.setFlipscale(this.mDrawerFlipScaleX, this.mDrawerFlipScaleY);
            f = ((float) this.mVideoWidth) / ((float) this.mVideoHeight);
        }
        int i = this.mViewWidth;
        int i2 = this.mViewHeight;
        float f2 = f / (((float) i) / ((float) i2));
        if (!this.mFitFullView ? ((double) f2) <= 1.0d : ((double) f2) > 1.0d) {
            i = (int) (((float) i2) * f);
        } else {
            i2 = (int) (((float) i) / f);
        }
        this.mRenderViewport.width = i;
        this.mRenderViewport.height = i2;
        TextureRenderer.Viewport viewport = this.mRenderViewport;
        viewport.x = (this.mViewWidth - viewport.width) / 2;
        TextureRenderer.Viewport viewport2 = this.mRenderViewport;
        viewport2.y = (this.mViewHeight - viewport2.height) / 2;
        Log.i("libCGE_java", String.format("View port: %d, %d, %d, %d", new Object[]{Integer.valueOf(this.mRenderViewport.x), Integer.valueOf(this.mRenderViewport.y), Integer.valueOf(this.mRenderViewport.width), Integer.valueOf(this.mRenderViewport.height)}));
    }


    public void _useUri() {
        MediaPlayer mediaPlayer = this.mPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mPlayer.reset();
        } else {
            this.mPlayer = new MediaPlayer();
        }
        try {
            this.mPlayer.setDataSource(getContext(), this.mVideoUri);
            this.mPlayer.setSurface(new Surface(this.mSurfaceTexture));
            PlayerInitializeCallback playerInitializeCallback = this.mPlayerInitCallback;
            if (playerInitializeCallback != null) {
                playerInitializeCallback.initPlayer(this.mPlayer);
            }
            this.mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if (SimplePlayerGLSurfaceView.this.mPlayCompletionCallback != null) {
                        SimplePlayerGLSurfaceView.this.mPlayCompletionCallback.playComplete(SimplePlayerGLSurfaceView.this.mPlayer);
                    }
                    Log.i("libCGE_java", "Video Play Over");
                }
            });
            this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    int unused = SimplePlayerGLSurfaceView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                    int unused2 = SimplePlayerGLSurfaceView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                    SimplePlayerGLSurfaceView.this.queueEvent(new Runnable() {
                        public void run() {
                            SimplePlayerGLSurfaceView.this.calcViewport();
                        }
                    });
                    if (SimplePlayerGLSurfaceView.this.mPreparedCallback != null) {
                        SimplePlayerGLSurfaceView.this.mPreparedCallback.playPrepared(SimplePlayerGLSurfaceView.this.mPlayer);
                    } else {
                        mediaPlayer.start();
                    }
                    Log.i("libCGE_java", String.format("Video resolution 1: %d x %d", new Object[]{Integer.valueOf(SimplePlayerGLSurfaceView.this.mVideoWidth), Integer.valueOf(SimplePlayerGLSurfaceView.this.mVideoHeight)}));
                }
            });
            this.mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    if (SimplePlayerGLSurfaceView.this.mPlayCompletionCallback != null) {
                        return SimplePlayerGLSurfaceView.this.mPlayCompletionCallback.playFailed(mediaPlayer, i, i2);
                    }
                    return false;
                }
            });
            try {
                this.mPlayer.prepareAsync();
            } catch (Exception e) {
                Log.i("libCGE_java", String.format("Error handled: %s, play failure handler would be called!", new Object[]{e.toString()}));
                if (this.mPlayCompletionCallback != null) {
                    post(new Runnable() {
                        public void run() {
                            if (SimplePlayerGLSurfaceView.this.mPlayCompletionCallback != null && !SimplePlayerGLSurfaceView.this.mPlayCompletionCallback.playFailed(SimplePlayerGLSurfaceView.this.mPlayer, 1, -1010)) {
                                SimplePlayerGLSurfaceView.this.mPlayCompletionCallback.playComplete(SimplePlayerGLSurfaceView.this.mPlayer);
                            }
                        }
                    });
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e("libCGE_java", "useUri failed");
            if (this.mPlayCompletionCallback != null) {
                post(new Runnable() {
                    public void run() {
                        if (SimplePlayerGLSurfaceView.this.mPlayCompletionCallback != null && !SimplePlayerGLSurfaceView.this.mPlayCompletionCallback.playFailed(SimplePlayerGLSurfaceView.this.mPlayer, 1, -1010)) {
                            SimplePlayerGLSurfaceView.this.mPlayCompletionCallback.playComplete(SimplePlayerGLSurfaceView.this.mPlayer);
                        }
                    }
                });
            }
        }
    }

    private void flushMaskAspectRatio() {
        float f = (((float) this.mVideoWidth) / ((float) this.mVideoHeight)) / this.mMaskAspectRatio;
        if (f > 1.0f) {
            this.mDrawer.setFlipscale(this.mDrawerFlipScaleX / f, this.mDrawerFlipScaleY);
        } else {
            this.mDrawer.setFlipscale(this.mDrawerFlipScaleX, f * this.mDrawerFlipScaleY);
        }
    }

    public synchronized void takeShot(final TakeShotCallback takeShotCallback) {
        if (this.mDrawer == null) {
            Log.e("libCGE_java", "Drawer not initialized!");
            takeShotCallback.takeShotOK((Bitmap) null);
            return;
        }
        queueEvent(new Runnable() {
            public void run() {
                IntBuffer allocate = IntBuffer.allocate(SimplePlayerGLSurfaceView.this.mRenderViewport.width * SimplePlayerGLSurfaceView.this.mRenderViewport.height);
                GLES20.glReadPixels(SimplePlayerGLSurfaceView.this.mRenderViewport.x, SimplePlayerGLSurfaceView.this.mRenderViewport.y, SimplePlayerGLSurfaceView.this.mRenderViewport.width, SimplePlayerGLSurfaceView.this.mRenderViewport.height, 6408, 5121, allocate);
                Bitmap createBitmap = Bitmap.createBitmap(SimplePlayerGLSurfaceView.this.mRenderViewport.width, SimplePlayerGLSurfaceView.this.mRenderViewport.height, Bitmap.Config.ARGB_8888);
                createBitmap.copyPixelsFromBuffer(allocate);
                Bitmap createBitmap2 = Bitmap.createBitmap(SimplePlayerGLSurfaceView.this.mRenderViewport.width, SimplePlayerGLSurfaceView.this.mRenderViewport.height, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap2);
                Matrix matrix = new Matrix();
                matrix.setTranslate(0.0f, ((float) (-SimplePlayerGLSurfaceView.this.mRenderViewport.height)) / 2.0f);
                matrix.postScale(1.0f, -1.0f);
                matrix.postTranslate(0.0f, ((float) SimplePlayerGLSurfaceView.this.mRenderViewport.height) / 2.0f);
                canvas.drawBitmap(createBitmap, matrix, (Paint) null);
                createBitmap.recycle();
                takeShotCallback.takeShotOK(createBitmap2);
            }
        });
    }
}
