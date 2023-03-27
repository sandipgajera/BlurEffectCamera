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
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.wysaid.common.Common;
import org.wysaid.nativePort.CGEFrameRenderer;
import org.wysaid.texUtils.TextureRenderer;

public class VideoPlayerGLSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener {
    static final  boolean $assertionsDisabled = false;
    public static final String LOG_TAG = "libCGE_java";
    private boolean mFitFullView = false;

    public CGEFrameRenderer mFrameRenderer;
    private long mFramesCount2 = 0;

    public boolean mIsUsingMask = false;
    private long mLastTimestamp2 = 0;

    public float mMaskAspectRatio = 1.0f;
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
        void setMaskOK(CGEFrameRenderer cGEFrameRenderer);
    }

    public interface TakeShotCallback {
        void takeShotOK(Bitmap bitmap);
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
        if (this.mFrameRenderer != null) {
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
        if (this.mFrameRenderer != null) {
            queueEvent(new Runnable() {
                public void run() {
                    Log.i("libCGE_java", "setVideoUri...");
                    if (VideoPlayerGLSurfaceView.this.mSurfaceTexture == null || VideoPlayerGLSurfaceView.this.mVideoTextureID == 0) {
                        int unused = VideoPlayerGLSurfaceView.this.mVideoTextureID = Common.genSurfaceTextureID();
                        SurfaceTexture unused2 = VideoPlayerGLSurfaceView.this.mSurfaceTexture = new SurfaceTexture(VideoPlayerGLSurfaceView.this.mVideoTextureID);
                        VideoPlayerGLSurfaceView.this.mSurfaceTexture.setOnFrameAvailableListener(VideoPlayerGLSurfaceView.this);
                    }
                    VideoPlayerGLSurfaceView.this._useUri();
                }
            });
        }
    }

    public synchronized void setFilterWithConfig(final String str) {
        queueEvent(new Runnable() {
            public void run() {
                if (VideoPlayerGLSurfaceView.this.mFrameRenderer != null) {
                    VideoPlayerGLSurfaceView.this.mFrameRenderer.setFilterWidthConfig(str);
                } else {
                    Log.e("libCGE_java", "setFilterWithConfig after release!!");
                }
            }
        });
    }

    public void setFilterIntensity(final float f) {
        queueEvent(new Runnable() {
            public void run() {
                if (VideoPlayerGLSurfaceView.this.mFrameRenderer != null) {
                    VideoPlayerGLSurfaceView.this.mFrameRenderer.setFilterIntensity(f);
                } else {
                    Log.e("libCGE_java", "setFilterIntensity after release!!");
                }
            }
        });
    }

    public void setMaskBitmap(Bitmap bitmap, boolean z) {
        setMaskBitmap(bitmap, z, (SetMaskBitmapCallback) null);
    }


    public void setMaskBitmap(final Bitmap bmp, final boolean shouldRecycle, final SetMaskBitmapCallback callback) {

        queueEvent(new Runnable() {
            @Override
            public void run() {

                if (mFrameRenderer == null) {
                    Log.e(LOG_TAG, "setMaskBitmap after release!!");
                    return;
                }

                if (bmp == null) {
                    mFrameRenderer.setMaskTexture(0, 1.0f);
                    mIsUsingMask = false;
                    calcViewport();
                    return;
                }

                int texID = Common.genNormalTextureID(bmp, GLES20.GL_NEAREST, GLES20.GL_CLAMP_TO_EDGE);

                mFrameRenderer.setMaskTexture(texID, bmp.getWidth() / (float) bmp.getHeight());
                mIsUsingMask = true;
                mMaskAspectRatio = bmp.getWidth() / (float) bmp.getHeight();

                if (callback != null) {
                    callback.setMaskOK(mFrameRenderer);
                }

                if (shouldRecycle)
                    bmp.recycle();

                calcViewport();
            }
        });
    }


    public synchronized MediaPlayer getPlayer() {
        if (this.mPlayer == null) {
            Log.e("libCGE_java", "Player is not initialized!");
        }
        return this.mPlayer;
    }

    public void setOnCreateCallback(final OnCreateCallback onCreateCallback) {
        if (this.mFrameRenderer == null) {
            this.mOnCreateCallback = onCreateCallback;
        } else {
            queueEvent(new Runnable() {
                public void run() {
                    onCreateCallback.createOK();
                }
            });
        }
    }

    public VideoPlayerGLSurfaceView(Context context, AttributeSet attributeSet) {
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
                    if (VideoPlayerGLSurfaceView.this.mPlayer != null) {
                        VideoPlayerGLSurfaceView.this.mPlayer.setSurface((Surface) null);
                        if (VideoPlayerGLSurfaceView.this.mPlayer.isPlaying()) {
                            VideoPlayerGLSurfaceView.this.mPlayer.stop();
                        }
                        VideoPlayerGLSurfaceView.this.mPlayer.release();
                        MediaPlayer unused = VideoPlayerGLSurfaceView.this.mPlayer = null;
                    }
                    if (VideoPlayerGLSurfaceView.this.mFrameRenderer != null) {
                        VideoPlayerGLSurfaceView.this.mFrameRenderer.release();
                        CGEFrameRenderer unused2 = VideoPlayerGLSurfaceView.this.mFrameRenderer = null;
                    }
                    if (VideoPlayerGLSurfaceView.this.mSurfaceTexture != null) {
                        VideoPlayerGLSurfaceView.this.mSurfaceTexture.release();
                        SurfaceTexture unused3 = VideoPlayerGLSurfaceView.this.mSurfaceTexture = null;
                    }
                    if (VideoPlayerGLSurfaceView.this.mVideoTextureID != 0) {
                        GLES20.glDeleteTextures(1, new int[]{VideoPlayerGLSurfaceView.this.mVideoTextureID}, 0);
                        int unused4 = VideoPlayerGLSurfaceView.this.mVideoTextureID = 0;
                    }
                    boolean unused5 = VideoPlayerGLSurfaceView.this.mIsUsingMask = false;
                    VideoPlayerGLSurfaceView.this.mPreparedCallback = null;
                    VideoPlayerGLSurfaceView.this.mPlayCompletionCallback = null;
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
        if (surfaceTexture != null && this.mFrameRenderer != null) {
            surfaceTexture.updateTexImage();
            if (this.mPlayer.isPlaying()) {
                this.mSurfaceTexture.getTransformMatrix(this.mTransformMatrix);
                this.mFrameRenderer.update(this.mVideoTextureID, this.mTransformMatrix);
                this.mFrameRenderer.runProc();
                GLES20.glBindFramebuffer(36160, 0);
                GLES20.glClear(16384);
                GLES20.glEnable(3042);
                this.mFrameRenderer.render(this.mRenderViewport.x, this.mRenderViewport.y, this.mRenderViewport.width, this.mRenderViewport.height);
                GLES20.glDisable(3042);
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
            f = this.mMaskAspectRatio;
        } else {
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
                    if (VideoPlayerGLSurfaceView.this.mPlayCompletionCallback != null) {
                        VideoPlayerGLSurfaceView.this.mPlayCompletionCallback.playComplete(VideoPlayerGLSurfaceView.this.mPlayer);
                    }
                    Log.i("libCGE_java", "Video Play Over");
                }
            });
            this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    int unused = VideoPlayerGLSurfaceView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                    int unused2 = VideoPlayerGLSurfaceView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                    VideoPlayerGLSurfaceView.this.queueEvent(new Runnable() {
                        public void run() {
                            if (VideoPlayerGLSurfaceView.this.mFrameRenderer == null) {
                                CGEFrameRenderer unused = VideoPlayerGLSurfaceView.this.mFrameRenderer = new CGEFrameRenderer();
                            }
                            if (VideoPlayerGLSurfaceView.this.mFrameRenderer.init(VideoPlayerGLSurfaceView.this.mVideoWidth, VideoPlayerGLSurfaceView.this.mVideoHeight, VideoPlayerGLSurfaceView.this.mVideoWidth, VideoPlayerGLSurfaceView.this.mVideoHeight)) {
                                VideoPlayerGLSurfaceView.this.mFrameRenderer.setSrcFlipScale(1.0f, -1.0f);
                                VideoPlayerGLSurfaceView.this.mFrameRenderer.setRenderFlipScale(1.0f, -1.0f);
                            } else {
                                Log.e("libCGE_java", "Frame Recorder init failed!");
                            }
                            VideoPlayerGLSurfaceView.this.calcViewport();
                        }
                    });
                    if (VideoPlayerGLSurfaceView.this.mPreparedCallback != null) {
                        VideoPlayerGLSurfaceView.this.mPreparedCallback.playPrepared(VideoPlayerGLSurfaceView.this.mPlayer);
                    } else {
                        mediaPlayer.start();
                    }
                    Log.i("libCGE_java", String.format("Video resolution 1: %d x %d", new Object[]{Integer.valueOf(VideoPlayerGLSurfaceView.this.mVideoWidth), Integer.valueOf(VideoPlayerGLSurfaceView.this.mVideoHeight)}));
                }
            });
            this.mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    if (VideoPlayerGLSurfaceView.this.mPlayCompletionCallback != null) {
                        return VideoPlayerGLSurfaceView.this.mPlayCompletionCallback.playFailed(mediaPlayer, i, i2);
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
                            if (VideoPlayerGLSurfaceView.this.mPlayCompletionCallback != null && !VideoPlayerGLSurfaceView.this.mPlayCompletionCallback.playFailed(VideoPlayerGLSurfaceView.this.mPlayer, 1, -1010)) {
                                VideoPlayerGLSurfaceView.this.mPlayCompletionCallback.playComplete(VideoPlayerGLSurfaceView.this.mPlayer);
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
                        if (VideoPlayerGLSurfaceView.this.mPlayCompletionCallback != null && !VideoPlayerGLSurfaceView.this.mPlayCompletionCallback.playFailed(VideoPlayerGLSurfaceView.this.mPlayer, 1, -1010)) {
                            VideoPlayerGLSurfaceView.this.mPlayCompletionCallback.playComplete(VideoPlayerGLSurfaceView.this.mPlayer);
                        }
                    }
                });
            }
        }
    }

    public synchronized void takeShot(final TakeShotCallback takeShotCallback) {
        if (this.mFrameRenderer == null) {
            Log.e("libCGE_java", "Drawer not initialized!");
            takeShotCallback.takeShotOK((Bitmap) null);
            return;
        }
        queueEvent(new Runnable() {
            public void run() {
                IntBuffer allocate = IntBuffer.allocate(VideoPlayerGLSurfaceView.this.mRenderViewport.width * VideoPlayerGLSurfaceView.this.mRenderViewport.height);
                GLES20.glReadPixels(VideoPlayerGLSurfaceView.this.mRenderViewport.x, VideoPlayerGLSurfaceView.this.mRenderViewport.y, VideoPlayerGLSurfaceView.this.mRenderViewport.width, VideoPlayerGLSurfaceView.this.mRenderViewport.height, 6408, 5121, allocate);
                Bitmap createBitmap = Bitmap.createBitmap(VideoPlayerGLSurfaceView.this.mRenderViewport.width, VideoPlayerGLSurfaceView.this.mRenderViewport.height, Bitmap.Config.ARGB_8888);
                createBitmap.copyPixelsFromBuffer(allocate);
                Bitmap createBitmap2 = Bitmap.createBitmap(VideoPlayerGLSurfaceView.this.mRenderViewport.width, VideoPlayerGLSurfaceView.this.mRenderViewport.height, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap2);
                Matrix matrix = new Matrix();
                matrix.setTranslate(0.0f, ((float) (-VideoPlayerGLSurfaceView.this.mRenderViewport.height)) / 2.0f);
                matrix.postScale(1.0f, -1.0f);
                matrix.postTranslate(0.0f, ((float) VideoPlayerGLSurfaceView.this.mRenderViewport.height) / 2.0f);
                canvas.drawBitmap(createBitmap, matrix, (Paint) null);
                createBitmap.recycle();
                takeShotCallback.takeShotOK(createBitmap2);
            }
        });
    }
}
