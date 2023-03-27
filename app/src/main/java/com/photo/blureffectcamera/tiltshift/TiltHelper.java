package com.photo.blureffectcamera.tiltshift;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.View;

import com.photo.blureffectcamera.canvas.TextData;
import com.photo.blureffectcamera.cropimages.SquareActivity;


public class TiltHelper {
    private static final int DRAG = 1;
    private static final int NONE = 0;
    private static final String TAG;
    private static final int ZOOM = 2;
    static float blurBitmapScale;
    int alpha;
    int animAlphaMultiplier;
    int animEpsilon;
    int animHalfTime;
    boolean animate;
    int animationCount;
    int animationDuration;
    int animationDurationLimit;
    int animationLimit;
    private Runnable animator;
    Bitmap bitmapBlur;
    TiltContext currentTiltContext;
    float f537d;
    int frameDuration;
    int f538h;
    float[] lastEvent;
    Matrix matrixBlur;
    private PointF mid;
    private int mode;
    float newRot;
    float oldDist;
    boolean onTouch;
    Paint paintAnimate;
    Paint paintGradient;
    Paint paintGradientTouch;
    Paint paintNormal;
    Paint paintWHite;
    Matrix savedMatrix;
    private PointF start;
    long startTime;
    TiltContext tiltContextLinear;
    TiltContext tiltContextNone;
    TiltContext tiltContextRadial;
    Paint tiltPaint;
    View tiltView;
    int f539w;

    /* renamed from: com.lyrebirdstudio.tiltshift.TiltHelper.1 */
    class C07811 implements Runnable {
        C07811() {
        }

        public void run() {
            boolean scheduleNewFrame = false;
            int iter = ((int) (((float) (System.nanoTime() - TiltHelper.this.startTime)) / 1000000.0f)) / TiltHelper.this.animationDurationLimit;
            if (iter <= 0) {
                iter = TiltHelper.DRAG;
            }
            if (TiltHelper.this.animationCount == 0) {
                TiltHelper tiltHelper = TiltHelper.this;
                tiltHelper.animationCount += TiltHelper.DRAG;
            } else {
                TiltHelper.this.animationCount += iter;
            }
            int animAlphaLocal = TiltHelper.this.animAlpha(TiltHelper.this.animationCount);
            TiltHelper.this.paintAnimate.setAlpha(animAlphaLocal);
            if (TiltHelper.this.animationCount < TiltHelper.this.animationLimit) {
                scheduleNewFrame = true;
            } else {
                TiltHelper.this.animate = false;
            }
            if (scheduleNewFrame) {
                TiltHelper.this.tiltView.postDelayed(this, (long) TiltHelper.this.frameDuration);
            }
            if (animAlphaLocal != TiltHelper.this.alpha) {
                TiltHelper.this.tiltView.invalidate();
            }
            TiltHelper.this.startTime = System.nanoTime();
        }
    }

    static {
        TAG = TiltHelper.class.getSimpleName();
        blurBitmapScale = 2.5f;
    }

    public TiltHelper(View view, Bitmap btmBlur, TiltContext tc, int w, int h) {
        this.onTouch = false;
        this.paintNormal = new Paint(DRAG);
        this.paintGradient = new Paint(DRAG);
        this.paintGradientTouch = new Paint(DRAG);
        this.paintAnimate = new Paint(DRAG);
        this.paintWHite = new Paint(DRAG);
        this.matrixBlur = new Matrix();
        this.animate = false;
        this.alpha = 230;
        this.animationCount = NONE;
        this.animationLimit = 51;
        this.animHalfTime = (this.animationLimit / ZOOM) + DRAG;
        this.frameDuration = 12;
        this.animEpsilon = 8;
        // this.animationDuration = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        this.animationDurationLimit = 50;
        this.startTime = System.nanoTime();
        this.animator = new C07811();
        this.mode = NONE;
        this.start = new PointF();
        this.mid = new PointF();
        this.oldDist = 1.0f;
        this.savedMatrix = new Matrix();
        this.lastEvent = new float[4];
        this.tiltView = view;
        this.bitmapBlur = btmBlur;
        this.f539w = w;
        this.f538h = h;
        this.paintGradient.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        this.paintGradientTouch.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        this.paintNormal.setColor(-1);
        this.paintWHite.setColor(-536870913);
        this.paintAnimate.setColor(-536870913);
        this.paintNormal.setFilterBitmap(true);
        this.tiltContextLinear = new TiltContext(TiltContext.TiltMode.LINEAR, w, h);
        this.tiltContextRadial = new TiltContext(TiltContext.TiltMode.RADIAL, w, h);
        this.tiltContextNone = new TiltContext(TiltContext.TiltMode.NONE, w, h);
        if (tc != null) {
            this.currentTiltContext = tc;
            if (tc.mode == TiltContext.TiltMode.LINEAR) {
                this.tiltContextLinear = tc;
            } else if (tc.mode == TiltContext.TiltMode.RADIAL) {
                this.tiltContextRadial = tc;
            }
        } else {
            this.currentTiltContext = this.tiltContextLinear;
        }
        this.paintGradient.setShader(this.currentTiltContext.gradient);
        this.paintGradientTouch.setShader(this.currentTiltContext.gradientTouch);
        this.matrixBlur.reset();
        this.matrixBlur.postScale(blurBitmapScale, blurBitmapScale);
    }

    public TiltContext getCurrentTiltContext() {
        return this.currentTiltContext;
    }

    public void setTiltMode(TiltContext.TiltMode mode) {
        if (mode == TiltContext.TiltMode.LINEAR) {
            this.currentTiltContext = this.tiltContextLinear;
            startAnimator();
        } else if (mode == TiltContext.TiltMode.RADIAL) {
            this.currentTiltContext = this.tiltContextRadial;
            startAnimator();
        } else if (mode == TiltContext.TiltMode.NONE) {
            this.currentTiltContext = this.tiltContextNone;
        }
        this.paintGradient.setShader(this.currentTiltContext.gradient);
        this.paintGradientTouch.setShader(this.currentTiltContext.gradientTouch);
        this.tiltView.postInvalidate();
    }

    public void drawTiltShift(Canvas canvas, Bitmap blurBtm, int btmW, int btmH) {
        if (this.currentTiltContext.mode != TiltContext.TiltMode.NONE) {
            @SuppressLint("WrongConstant") int i = canvas.saveLayer(0.0f, 0.0f, (float) btmW, (float) btmH, this.paintNormal, 31);
            if (!(this.onTouch || blurBtm == null || blurBtm.isRecycled())) {
                canvas.drawBitmap(blurBtm, this.matrixBlur, this.paintNormal);
            }
            if (this.onTouch) {
                canvas.drawRect(0.0f, 0.0f, (float) btmW, (float) btmH, this.paintWHite);
            } else if (this.animate) {
                canvas.drawRect(0.0f, 0.0f, (float) btmW, (float) btmH, this.paintAnimate);
            }
            if (this.onTouch || this.animate) {
                canvas.drawRect(0.0f, 0.0f, (float) btmW, (float) btmH, this.paintGradientTouch);
            } else {
                canvas.drawRect(0.0f, 0.0f, (float) btmW, (float) btmH, this.paintGradient);
            }
            canvas.restoreToCount(i);
        }
    }

    public static void drawTiltShift2(Canvas canvas, Bitmap blurBtm, int btmW, int btmH, TiltContext tiltContext) {
        if (tiltContext.mode != TiltContext.TiltMode.NONE) {
            Paint p = new Paint(DRAG);
            p.setFilterBitmap(true);
            @SuppressLint("WrongConstant") int i = canvas.saveLayer(0.0f, 0.0f, (float) btmW, (float) btmH, p, 31);
            Matrix m = new Matrix();
            m.postScale(blurBitmapScale, blurBitmapScale);
            canvas.drawBitmap(blurBtm, m, p);
            Paint pG = new Paint(DRAG);
            pG.setShader(pG.setShader(tiltContext.gradient));
            pG.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
            canvas.drawRect(0.0f, 0.0f, (float) btmW, (float) btmH, pG);
            canvas.restoreToCount(i);
        }
    }

    public void startAnimator() {
        this.animationCount = NONE;
        this.animAlphaMultiplier = this.alpha / (this.animHalfTime - this.animEpsilon);
        this.animate = true;
        this.tiltView.removeCallbacks(this.animator);
        this.tiltView.post(this.animator);
    }

    int animAlpha(int value) {
        if (value == (this.animHalfTime - this.animEpsilon) - 1) {
            return this.alpha;
        }
        if (value < this.animHalfTime - this.animEpsilon) {
            return value * this.animAlphaMultiplier;
        }
        if (value > this.animHalfTime + this.animEpsilon) {
            return (this.animationLimit - value) * this.animAlphaMultiplier;
        }
        return this.alpha;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.currentTiltContext.mode == TiltContext.TiltMode.NONE) {
            return false;
        }
        switch (event.getAction() & TextData.defBgAlpha) {
            case NONE /*0*/:
                this.onTouch = true;
                this.savedMatrix.set(this.currentTiltContext.matrix);
                this.start.set(event.getX(), event.getY());
                this.mode = DRAG;
                this.lastEvent = null;
                break;
            case DRAG /*1*/:
            case SquareActivity.TAB_INDEX_SQUARE_CASCADE /*6*/:
                this.mode = NONE;
                this.onTouch = false;
                this.lastEvent = null;
                break;
            case ZOOM /*2*/:
                if (this.mode != DRAG) {
                    if (this.mode == ZOOM && event.getPointerCount() == ZOOM) {
                        float newDist = spacing(event);
                        this.currentTiltContext.matrix.set(this.savedMatrix);
                        if (newDist > 10.0f) {
                            float scale = newDist / this.oldDist;
                            this.currentTiltContext.matrix.postScale(scale, scale, this.mid.x, this.mid.y);
                        }
                        if (this.lastEvent != null) {
                            this.newRot = rotation(event);
                            this.currentTiltContext.matrix.postRotate(this.newRot - this.f537d, (float) (this.f539w / ZOOM), (float) (this.f538h / ZOOM));
                            break;
                        }
                    }
                }
                this.currentTiltContext.matrix.set(this.savedMatrix);
                this.currentTiltContext.matrix.postTranslate(event.getX() - this.start.x, event.getY() - this.start.y);
                break;

            case SquareActivity.TAB_INDEX_SQUARE_ADJ /*5*/:
                this.oldDist = spacing(event);
                if (this.oldDist > 10.0f) {
                    this.savedMatrix.set(this.currentTiltContext.matrix);
                    midPoint(this.mid, event);
                    this.mode = ZOOM;
                }
                this.lastEvent = new float[4];
                this.lastEvent[NONE] = event.getX(NONE);
                this.lastEvent[DRAG] = event.getX(DRAG);
                this.lastEvent[ZOOM] = event.getY(NONE);
                this.lastEvent[3] = event.getY(DRAG);
                this.f537d = rotation(event);
                break;
        }
        this.currentTiltContext.setLocalMatrix();
        this.paintGradient.setShader(this.currentTiltContext.gradient);
        this.paintGradientTouch.setShader(this.currentTiltContext.gradientTouch);
        this.tiltView.postInvalidate();
        return true;
    }

    private float rotation(MotionEvent event) {
        return (float) Math.toDegrees(Math.atan2((double) (event.getY(NONE) - event.getY(DRAG)), (double) (event.getX(NONE) - event.getX(DRAG))));
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(NONE) - event.getX(DRAG);
        float y = event.getY(NONE) - event.getY(DRAG);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    private void midPoint(PointF point, MotionEvent event) {
        point.set((event.getX(NONE) + event.getX(DRAG)) / 2.0f, (event.getY(NONE) + event.getY(DRAG)) / 2.0f);
    }
}
