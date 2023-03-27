package com.photo.blureffectcamera.sticker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

import com.photo.blureffectcamera.canvas.BaseData;
import com.photo.blureffectcamera.canvas.CanvasTextView;
import com.photo.blureffectcamera.canvas.DecorateView;
import com.photo.blureffectcamera.canvas.MyMatrix;


public class StickerView extends DecorateView {
    private static final int INVALID_POINTER_ID = -1;
    static float MIN_ZOOM;
    private static final String TAG;
    float actualRadius;
    Paint bitmapPaint;
    float btmH;
    float btmW;
    PointF center;
    float circlePadding;
    Paint dashPaint;
    Path dashPathHorizontal;
    Path dashPathHorizontalTemp;
    Path dashPathVertical;
    Path dashPathVerticalTemp;
    boolean doubleTouchCall;
    final float epsilon;
    GestureDetector gestureDetector;
    Matrix inverse;
    boolean isInCircle;
    boolean isOnRect;
    boolean isOnTouch;
    private int mActivePointerId;
    private RotationGestureDetector mRotationDetector;
    private ScaleGestureDetector mScaleDetector;
    float mScaleFactor;
    float minDimen;
    boolean orthogonal;
    float paddingHeight;
    float paddingWidth;
    public Paint paint;
    PointF previosPos;
    float[] pts;
    Paint rectPaint;
    Paint rectPaintOnTouch;
    Paint redPaint;
    Bitmap removeBitmap;
    Matrix removeBitmapMatrix;
    float removeBitmapWidth;
    RotationGestureDetector.OnRotationGestureListener rotateListener;
    boolean savedViewSelected;
    float scale;
    Bitmap scaleBitmap;
    Matrix scaleBitmapMatrix;
    SingleTap singleTapListener;
    private float startAngle;
    float startAngleGesture;
    public Bitmap stickerBitmap;
    StickerData stickerData;
    CanvasTextView.TextAndStickerViewSelectedListener textAndStickerViewSelectedListener;
    Rect textBoundrect;
    RectF touchRect;
    float[] f769v;
    float[] values;
    private boolean viewSelected;
    StickerViewSelectedListener viewSelectedListenerEx;
    Paint whitePaint;
    PointF zoomStart;

    /* renamed from: com.lyrebirdstudio.sticker.StickerView.1 */
    class C07761 implements Runnable {
        C07761() {
        }

        public void run() {
            StickerView.this.doubleTouchCall = false;
        }
    }

    private class GestureListener extends SimpleOnGestureListener {
        private GestureListener() {
        }

        public boolean onDown(MotionEvent e) {
            Log.e(StickerView.TAG, "onDown");
            if (StickerView.this.isInCircle || StickerView.this.isOnRect) {
                return true;
            }
            StickerView.this.viewSelected = false;
            return false;
        }

        public boolean onSingleTapUp(MotionEvent event) {
            Log.e(StickerView.TAG, "onSingleTapUp");
            StickerView.this.pts[0] = event.getX();
            StickerView.this.pts[1] = event.getY();
            StickerView.this.stickerData.canvasMatrix.invert(StickerView.this.inverse);
            StickerView.this.inverse.mapPoints(StickerView.this.pts, StickerView.this.pts);
            StickerView.this.isOnRect = StickerView.this.isOnRectCheck(StickerView.this.pts[0], StickerView.this.pts[1]);
            Log.e(StickerView.TAG, "onSingleTapUp viewSelected " + StickerView.this.viewSelected);
            if (StickerView.this.isOnRect) {
                Log.e(StickerView.TAG, "onSingleTapUp doubleSavedViewSelected " + StickerView.this.doubleTouchCall);
                if (StickerView.this.doubleTouchCall) {
                    StickerView.this.viewSelected = true;
                } else {
                    StickerView.this.viewSelected = !StickerView.this.savedViewSelected;
                }
                StickerView.this.doubleTouchCall = false;
            } else {
                StickerView.this.viewSelected = false;
            }
            if (StickerView.this.isInCircle || StickerView.this.isOnRect) {
                return true;
            }
            return false;
        }

        public boolean onDoubleTap(MotionEvent e) {
            StickerView.this.pts[0] = e.getX();
            StickerView.this.pts[1] = e.getY();
            StickerView.this.stickerData.canvasMatrix.invert(StickerView.this.inverse);
            StickerView.this.inverse.mapPoints(StickerView.this.pts, StickerView.this.pts);
            StickerView.this.isOnRect = StickerView.this.isOnRectCheck(StickerView.this.pts[0], StickerView.this.pts[1]);
            if (StickerView.this.isOnRect) {
                StickerView.this.viewSelected = true;
            } else {
                StickerView.this.viewSelected = false;
            }
            return true;
        }
    }

    private class ScaleListener extends SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        public boolean onScale(ScaleGestureDetector detector) {
            StickerView.this.mScaleFactor = detector.getScaleFactor();
            if (detector.isInProgress()) {
                StickerView.this.pts[0] = StickerView.this.touchRect.centerX();
                StickerView.this.pts[1] = StickerView.this.touchRect.centerY();
                StickerView.this.stickerData.canvasMatrix.mapPoints(StickerView.this.pts, StickerView.this.pts);
                StickerView.this.mScaleFactor = detector.getScaleFactor();
                StickerView.this.mScaleFactor = Math.max(StickerView.MIN_ZOOM, StickerView.this.mScaleFactor);
                StickerView.this.stickerData.canvasMatrix.postScale(StickerView.this.mScaleFactor, StickerView.this.mScaleFactor, StickerView.this.pts[0], StickerView.this.pts[1]);
                StickerView.this.scale = StickerView.this.getScale();
                StickerView.this.invalidate();
            } else {
                StickerView.this.pts[0] = StickerView.this.touchRect.centerX();
                StickerView.this.pts[1] = StickerView.this.touchRect.centerY();
                StickerView.this.stickerData.canvasMatrix.mapPoints(StickerView.this.pts, StickerView.this.pts);
                StickerView.this.mScaleFactor = detector.getScaleFactor();
                StickerView.this.mScaleFactor = Math.max(StickerView.MIN_ZOOM, StickerView.this.mScaleFactor);
                StickerView.this.stickerData.canvasMatrix.postScale(StickerView.this.mScaleFactor, StickerView.this.mScaleFactor, StickerView.this.pts[0], StickerView.this.pts[1]);
                StickerView.this.scale = StickerView.this.getScale();
                StickerView.this.invalidate();
            }
            return true;
        }
    }

    public interface SingleTap {
        void onSingleTap(StickerData stickerData);
    }

    public interface StickerViewSelectedListener {
        void onTouchUp(StickerData stickerData);

        void setSelectedView(StickerView stickerView);
    }

    public interface StickerViewTouchUpListener {
        void onTouchUp();
    }

    /* renamed from: com.lyrebirdstudio.sticker.StickerView.2 */
    class C13602 implements RotationGestureDetector.OnRotationGestureListener {
        C13602() {
        }

        public void OnRotation(RotationGestureDetector rotationDetector) {
            float angle = rotationDetector.getAngle();
            float rotation = StickerView.this.getMatrixRotation(StickerView.this.stickerData.canvasMatrix);
            if ((rotation == 0.0f || rotation == 90.0f || rotation == 180.0f || rotation == -180.0f || rotation == -90.0f) && Math.abs(StickerView.this.startAngleGesture - angle) < 4.0f) {
                StickerView.this.orthogonal = true;
                return;
            }
            if (Math.abs((rotation - StickerView.this.startAngleGesture) + angle) < 4.0f) {
                angle = StickerView.this.startAngleGesture - rotation;
                StickerView.this.orthogonal = true;
            } else if (Math.abs(90.0f - ((rotation - StickerView.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (StickerView.this.startAngleGesture + 90.0f) - rotation;
                StickerView.this.orthogonal = true;
            } else if (Math.abs(180.0f - ((rotation - StickerView.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (180.0f + StickerView.this.startAngleGesture) - rotation;
                StickerView.this.orthogonal = true;
            } else if (Math.abs(-180.0f - ((rotation - StickerView.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (StickerView.this.startAngleGesture - 0.024902344f) - rotation;
                StickerView.this.orthogonal = true;
            } else if (Math.abs(-90.0f - ((rotation - StickerView.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (StickerView.this.startAngleGesture - 0.049804688f) - rotation;
                StickerView.this.orthogonal = true;
            } else {
                StickerView.this.orthogonal = false;
            }
            StickerView.this.pts[0] = StickerView.this.touchRect.centerX();
            StickerView.this.pts[1] = StickerView.this.touchRect.centerY();
            StickerView.this.stickerData.canvasMatrix.mapPoints(StickerView.this.pts, StickerView.this.pts);
            StickerView.this.stickerData.canvasMatrix.postRotate(StickerView.this.startAngleGesture - angle, StickerView.this.pts[0], StickerView.this.pts[1]);
            StickerView.this.startAngleGesture = angle;
            StickerView.this.invalidate();
        }
    }

    public void setStickerViewSelectedListener(StickerViewSelectedListener l) {
    }

    public BaseData getData() {
        return this.stickerData;
    }

    static {
        TAG = StickerView.class.getSimpleName();
        MIN_ZOOM = 0.2f;
    }

    public void setSingleTapListener(SingleTap l) {
        this.singleTapListener = l;
    }

    public void setTextAndStickerSelectedListner(CanvasTextView.TextAndStickerViewSelectedListener l) {
        this.textAndStickerViewSelectedListener = l;
    }

    public void setViewSelected(boolean selection) {
        Log.e(TAG, "setViewSelected " + selection);
        this.viewSelected = selection;
        postInvalidate();
    }

    public StickerData getStickerData() {
        return this.stickerData;
    }

    public void setStickerData(StickerData data) {
        this.stickerData.set(data);
    }

    public boolean getViewSelected() {
        return this.viewSelected;
    }

    @SuppressLint({"NewApi"})
    public StickerView(Context context, Bitmap bitmap, StickerData stData, Bitmap removeBtm, Bitmap scaleBtm, int resourceId, String path) {
        super(context);
        this.paddingHeight = 30.0f;
        this.paddingWidth = 10.0f;
        this.actualRadius = this.paddingHeight;
        this.center = new PointF();
        this.values = new float[9];
        this.scale = 1.0f;
        this.viewSelected = false;
        this.isOnTouch = false;
        this.removeBitmapMatrix = new Matrix();
        this.scaleBitmapMatrix = new Matrix();
        this.circlePadding = 5.0f;
        this.redPaint = new Paint(1);
        this.whitePaint = new Paint(1);
        this.bitmapPaint = new Paint(1);
        this.isOnRect = false;
        this.isInCircle = false;
        this.dashPathVerticalTemp = new Path();
        this.dashPathHorizontalTemp = new Path();
        this.dashPaint = new Paint();
        this.previosPos = new PointF();
        this.zoomStart = new PointF();
        this.startAngle = 0.0f;
        this.inverse = new Matrix();
        this.pts = new float[2];
        this.savedViewSelected = false;
        this.doubleTouchCall = false;
        this.epsilon = 4.0f;
        this.orthogonal = false;
        this.mActivePointerId = INVALID_POINTER_ID;
        this.f769v = new float[9];
        this.mScaleFactor = 1.0f;
        this.startAngleGesture = 0.0f;
        this.rotateListener = new C13602();
        this.stickerBitmap = bitmap;
        this.removeBitmap = removeBtm;
        this.scaleBitmap = scaleBtm;
        this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        this.mRotationDetector = new RotationGestureDetector(this.rotateListener);
        float screenWidth = (float) getResources().getDisplayMetrics().widthPixels;
        float screenHeight = (float) getResources().getDisplayMetrics().heightPixels;
        this.minDimen = Math.min(screenWidth, screenHeight);
        this.rectPaint = new Paint(1);
        this.rectPaint.setColor(2006555033);
        this.rectPaintOnTouch = new Paint(1);
        this.rectPaintOnTouch.setColor(2011028957);
        this.textBoundrect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        if (stData == null) {
            if (path != null) {
                this.stickerData = new StickerData(path);
            } else {
                this.stickerData = new StickerData(resourceId);
            }
            float scale = this.minDimen / 1080.0f;
            this.stickerData.canvasMatrix.postScale(scale, scale);
            this.stickerData.canvasMatrix.postTranslate(0.1f, 0.1f);
            this.stickerData.xPos = ((screenWidth / scale) - ((float) this.textBoundrect.width())) / 2.0f;
            this.stickerData.yPos = screenHeight / (3.0f * scale);
        } else {
            this.stickerData = stData;
        }
        this.paddingWidth = screenWidth / 15.0f;
        this.paddingHeight = screenWidth / 14.0f;
        this.touchRect = new RectF(this.stickerData.xPos - this.paddingWidth, this.stickerData.yPos - this.paddingHeight, (this.stickerData.xPos + ((float) this.textBoundrect.width())) + this.paddingWidth, (this.stickerData.yPos + ((float) this.textBoundrect.height())) + this.paddingHeight);
        this.gestureDetector = new GestureDetector(context, new GestureListener());
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setFilterBitmap(true);
        this.redPaint.setColor(-16485377);
        this.whitePaint.setColor(-1460137);
        this.bitmapPaint.setFilterBitmap(true);
        this.actualRadius = this.minDimen / 20.0f;
        float maxDimForBitmap = (float) Math.max(bitmap.getWidth(), bitmap.getHeight());
        if (maxDimForBitmap > this.actualRadius * 3.0f) {
            MIN_ZOOM = (this.actualRadius * 1.0f) / maxDimForBitmap;
        }
        this.circlePadding = this.actualRadius / 2.0f;
        if (this.actualRadius <= 5.0f) {
            this.actualRadius = this.paddingHeight;
        }
        this.removeBitmapWidth = (float) this.removeBitmap.getWidth();
        this.removeBitmapMatrix.reset();
        this.scaleBitmapMatrix.reset();
        float bitmapScale = (2.0f * this.actualRadius) / this.removeBitmapWidth;
        this.removeBitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.removeBitmapMatrix.postTranslate(this.touchRect.left - ((this.removeBitmapWidth * bitmapScale) / 2.0f), this.touchRect.top - ((this.removeBitmapWidth * bitmapScale) / 2.0f));
        this.scaleBitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.scaleBitmapMatrix.postTranslate(this.touchRect.right - ((this.removeBitmapWidth * bitmapScale) / 2.0f), this.touchRect.bottom - ((this.removeBitmapWidth * bitmapScale) / 2.0f));
        this.scale = getScale();
        this.scaleBitmapMatrix.postScale(1.0f / this.scale, 1.0f / this.scale, this.touchRect.right, this.touchRect.bottom);
        this.removeBitmapMatrix.postScale(1.0f / this.scale, 1.0f / this.scale, this.touchRect.left, this.touchRect.top);
        this.btmW = (float) bitmap.getWidth();
        this.btmH = (float) bitmap.getHeight();
        this.dashPaint.setColor(-7829368);
        this.dashPaint.setStyle(Style.STROKE);
        float strokeW = screenWidth / 120.0f;
        if (strokeW <= 0.0f) {
            strokeW = 5.0f;
        }
        this.dashPaint.setStrokeWidth(strokeW);
        this.dashPaint.setPathEffect(new DashPathEffect(new float[]{strokeW, strokeW}, 0.0f));
        this.dashPathVertical = new Path();
        this.dashPathVertical.moveTo(this.btmW / 2.0f, (-this.btmH) / 5.0f);
        this.dashPathVertical.lineTo(this.btmW / 2.0f, (6.0f * this.btmH) / 5.0f);
        this.dashPathHorizontal = new Path();
        this.dashPathHorizontal.moveTo((-this.btmW) / 5.0f, this.btmH / 2.0f);
        this.dashPathHorizontal.lineTo((6.0f * this.btmW) / 5.0f, this.btmH / 2.0f);
    }

    public boolean isDecorateViewSelected() {
        return this.viewSelected;
    }

    public void setDecorateViewSelected(boolean selection) {
        this.viewSelected = selection;
        invalidate();
    }

    public void setMatrix(MyMatrix matrix) {
        this.stickerData.canvasMatrix.set(matrix);
        this.scale = getScale();
    }

    public void onDestroy() {
        Log.e(TAG, "ondestroy");
        this.stickerBitmap.recycle();
        this.stickerBitmap = null;
    }

    public void onDraw(Canvas canvas) {
        canvas.setMatrix(this.stickerData.canvasMatrix);
        this.removeBitmapMatrix.reset();
        this.scaleBitmapMatrix.reset();
        this.scale = getScale();
        float localPaddingWidth = this.paddingWidth;
        float localPaddingHeight = this.paddingHeight;
        localPaddingWidth = this.minDimen / (this.scale * 18.0f);
        localPaddingHeight = this.minDimen / (this.scale * 18.0f);
        this.touchRect.set(this.stickerData.xPos - localPaddingWidth, this.stickerData.yPos - localPaddingHeight, (this.stickerData.xPos + ((float) this.textBoundrect.width())) + localPaddingWidth, (this.stickerData.yPos + ((float) this.textBoundrect.height())) + localPaddingHeight);
        float bitmapScale = (this.actualRadius * 2.0f) / this.removeBitmapWidth;
        this.removeBitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.removeBitmapMatrix.postTranslate(this.touchRect.left - ((this.removeBitmapWidth * bitmapScale) / 2.0f), this.touchRect.top - ((this.removeBitmapWidth * bitmapScale) / 2.0f));
        this.scaleBitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.scaleBitmapMatrix.postTranslate(this.touchRect.right - ((this.removeBitmapWidth * bitmapScale) / 2.0f), this.touchRect.bottom - ((this.removeBitmapWidth * bitmapScale) / 2.0f));
        this.scaleBitmapMatrix.postScale(1.0f / this.scale, 1.0f / this.scale, this.touchRect.right, this.touchRect.bottom);
        this.removeBitmapMatrix.postScale(1.0f / this.scale, 1.0f / this.scale, this.touchRect.left, this.touchRect.top);
        float rad = this.actualRadius / this.scale;
        if (this.viewSelected) {
            if (this.isOnTouch) {
                canvas.drawRect(this.touchRect, this.rectPaintOnTouch);
            } else {
                canvas.drawRect(this.touchRect, this.rectPaint);
            }
            canvas.drawCircle(this.touchRect.right, this.touchRect.bottom, rad, this.whitePaint);
            canvas.drawCircle(this.touchRect.left, this.touchRect.top, rad, this.redPaint);
            canvas.drawBitmap(this.scaleBitmap, this.scaleBitmapMatrix, this.bitmapPaint);
            canvas.drawBitmap(this.removeBitmap, this.removeBitmapMatrix, this.bitmapPaint);
        }
        if (!(this.stickerBitmap == null || this.stickerBitmap.isRecycled())) {
            canvas.drawBitmap(this.stickerBitmap, this.stickerData.xPos, this.stickerData.yPos, this.paint);
        }
        if (this.orthogonal) {
            this.dashPathVertical.offset(this.stickerData.xPos, this.stickerData.yPos, this.dashPathVerticalTemp);
            this.dashPathHorizontal.offset(this.stickerData.xPos, this.stickerData.yPos, this.dashPathHorizontalTemp);
            canvas.drawPath(this.dashPathVerticalTemp, this.dashPaint);
            canvas.drawPath(this.dashPathHorizontalTemp, this.dashPaint);
        }
    }

    void singleTapped() {
    }

    public boolean isOnRectCheck(float x, float y) {
        float paddingX = this.touchRect.width() / 10.0f;
        float paddingY = this.touchRect.height() / 10.0f;
        if (getScale() < MIN_ZOOM * 2.0f) {
            paddingX = -paddingX;
            paddingY = -paddingY;
        }
        if (x <= this.touchRect.left + paddingX || x >= this.touchRect.right - paddingX || y <= this.touchRect.top + paddingY || y >= this.touchRect.bottom - paddingY) {
            return false;
        }
        this.viewSelected = true;
        return true;
    }

    public float containsScore(float xx, float yy) {
        this.pts[0] = xx;
        this.pts[1] = yy;
        this.stickerData.canvasMatrix.invert(this.inverse);
        this.inverse.mapPoints(this.pts, this.pts);
        float x = this.pts[0];
        float y = this.pts[1];
        RectF maskRect = this.touchRect;
        if (x >= maskRect.left && x <= maskRect.right && y >= maskRect.top && y <= maskRect.bottom) {
            float contain = ((x - maskRect.centerX()) * (x - maskRect.centerX())) + ((y - maskRect.centerY()) * (y - maskRect.centerY()));
            float hypotenus = (maskRect.width() * maskRect.width()) + (maskRect.height() * maskRect.height());
            if (contain > 0.0f) {
                return hypotenus / contain;
            }
        }
        return -2.0f;
    }

    boolean isInCircle(float x, float y) {
        if (((x - this.touchRect.right) * (x - this.touchRect.right)) + ((y - this.touchRect.bottom) * (y - this.touchRect.bottom)) >= ((this.actualRadius + this.circlePadding) * (this.actualRadius + this.circlePadding)) / (this.scale * this.scale)) {
            return false;
        }
        this.viewSelected = true;
        return true;
    }

    boolean isOnCross(float x, float y) {
        if (((x - this.touchRect.left) * (x - this.touchRect.left)) + ((y - this.touchRect.top) * (y - this.touchRect.top)) >= ((this.actualRadius + this.circlePadding) * (this.actualRadius + this.circlePadding)) / (this.scale * this.scale)) {
            return false;
        }
        this.viewSelected = true;
        return true;
    }

    boolean isOnCross2(float x, float y, float left, float top) {
        if (((x - left) * (x - left)) + ((y - top) * (y - top)) >= ((this.actualRadius + this.circlePadding) * (this.actualRadius + this.circlePadding)) / (this.scale * this.scale)) {
            return false;
        }
        this.viewSelected = true;
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
 //   public boolean onTouchEvent(android.view.MotionEvent r24) {
        /*
        r23 = this;
        r0 = r23;
        r0 = r0.mScaleDetector;
        r18 = r0;
        r0 = r18;
        r1 = r24;
        r0.onTouchEvent(r1);
        r0 = r23;
        r0 = r0.mRotationDetector;
        r18 = r0;
        r0 = r18;
        r1 = r24;
        r0.onTouchEvent(r1);
        r16 = r24.getX();
        r17 = r24.getY();
        r18 = r24.getAction();
        r0 = r18;
        r0 = r0 & 255;
        r18 = r0;
        switch(r18) {
            case 0: goto L_0x0041;
            case 1: goto L_0x0575;
            case 2: goto L_0x0265;
            case 3: goto L_0x002f;
            case 4: goto L_0x002f;
            case 5: goto L_0x0247;
            case 6: goto L_0x05db;
            default: goto L_0x002f;
        };
    L_0x002f:
        r0 = r23;
        r0 = r0.gestureDetector;
        r18 = r0;
        r0 = r18;
        r1 = r24;
        r11 = r0.onTouchEvent(r1);
        r23.postInvalidate();
    L_0x0040:
        return r11;
    L_0x0041:
        r18 = 0;
        r0 = r18;
        r1 = r23;
        r1.isOnRect = r0;
        r18 = 0;
        r0 = r18;
        r1 = r23;
        r1.isInCircle = r0;
        r0 = r23;
        r0 = r0.viewSelected;
        r18 = r0;
        r0 = r18;
        r1 = r23;
        r1.savedViewSelected = r0;
        r18 = 1;
        r0 = r18;
        r1 = r23;
        r1.isOnTouch = r0;
        r0 = r23;
        r0 = r0.pts;
        r18 = r0;
        r19 = 0;
        r18[r19] = r16;
        r0 = r23;
        r0 = r0.pts;
        r18 = r0;
        r19 = 1;
        r18[r19] = r17;
        r0 = r23;
        r0 = r0.stickerData;
        r18 = r0;
        r0 = r18;
        r0 = r0.canvasMatrix;
        r18 = r0;
        r0 = r23;
        r0 = r0.inverse;
        r19 = r0;
        r18.invert(r19);
        r0 = r23;
        r0 = r0.inverse;
        r18 = r0;
        r0 = r23;
        r0 = r0.pts;
        r19 = r0;
        r0 = r23;
        r0 = r0.pts;
        r20 = r0;
        r18.mapPoints(r19, r20);
        r18 = TAG;
        r19 = new java.lang.StringBuilder;
        r19.<init>();
        r20 = "ACTION_DOWN savedViewSelected ";
        r19 = r19.append(r20);
        r0 = r23;
        r0 = r0.savedViewSelected;
        r20 = r0;
        r19 = r19.append(r20);
        r19 = r19.toString();
        android.util.Log.e(r18, r19);
        r18 = TAG;
        r19 = new java.lang.StringBuilder;
        r19.<init>();
        r20 = "pointer count = ";
        r19 = r19.append(r20);
        r20 = r24.getPointerCount();
        r19 = r19.append(r20);
        r19 = r19.toString();
        android.util.Log.e(r18, r19);
        r0 = r23;
        r0 = r0.viewSelected;
        r18 = r0;
        if (r18 == 0) goto L_0x0115;
    L_0x00e5:
        r0 = r23;
        r0 = r0.pts;
        r18 = r0;
        r19 = 0;
        r18 = r18[r19];
        r0 = r23;
        r0 = r0.pts;
        r19 = r0;
        r20 = 1;
        r19 = r19[r20];
        r0 = r23;
        r1 = r18;
        r2 = r19;
        r18 = r0.isOnCross(r1, r2);
        if (r18 == 0) goto L_0x0115;
    L_0x0105:
        r18 = r23.getContext();
        r0 = r23;
        r1 = r18;
        r2 = r23;
        r0.createDeleteDialog(r1, r2);
        r11 = 1;
        goto L_0x0040;
    L_0x0115:
        r0 = r23;
        r0 = r0.pts;
        r18 = r0;
        r19 = 0;
        r18 = r18[r19];
        r0 = r23;
        r0 = r0.pts;
        r19 = r0;
        r20 = 1;
        r19 = r19[r20];
        r0 = r23;
        r1 = r18;
        r2 = r19;
        r18 = r0.isOnRectCheck(r1, r2);
        r0 = r18;
        r1 = r23;
        r1.isOnRect = r0;
        r18 = TAG;
        r19 = new java.lang.StringBuilder;
        r19.<init>();
        r20 = "ACTION_DOWN viewSelected ";
        r19 = r19.append(r20);
        r0 = r23;
        r0 = r0.viewSelected;
        r20 = r0;
        r19 = r19.append(r20);
        r19 = r19.toString();
        android.util.Log.e(r18, r19);
        r0 = r23;
        r0 = r0.pts;
        r18 = r0;
        r19 = 0;
        r18 = r18[r19];
        r0 = r23;
        r0 = r0.pts;
        r19 = r0;
        r20 = 1;
        r19 = r19[r20];
        r0 = r23;
        r1 = r18;
        r2 = r19;
        r18 = r0.isInCircle(r1, r2);
        r0 = r18;
        r1 = r23;
        r1.isInCircle = r0;
        r0 = r23;
        r0 = r0.previosPos;
        r18 = r0;
        r0 = r18;
        r1 = r16;
        r2 = r17;
        r0.set(r1, r2);
        r0 = r23;
        r0 = r0.zoomStart;
        r18 = r0;
        r0 = r18;
        r1 = r16;
        r2 = r17;
        r0.set(r1, r2);
        r0 = r23;
        r0 = r0.pts;
        r18 = r0;
        r19 = 0;
        r0 = r23;
        r0 = r0.touchRect;
        r20 = r0;
        r20 = r20.centerX();
        r18[r19] = r20;
        r0 = r23;
        r0 = r0.pts;
        r18 = r0;
        r19 = 1;
        r0 = r23;
        r0 = r0.touchRect;
        r20 = r0;
        r20 = r20.centerY();
        r18[r19] = r20;
        r0 = r23;
        r0 = r0.stickerData;
        r18 = r0;
        r0 = r18;
        r0 = r0.canvasMatrix;
        r18 = r0;
        r0 = r23;
        r0 = r0.pts;
        r19 = r0;
        r0 = r23;
        r0 = r0.pts;
        r20 = r0;
        r18.mapPoints(r19, r20);
        r0 = r23;
        r0 = r0.pts;
        r18 = r0;
        r19 = 0;
        r18 = r18[r19];
        r0 = r23;
        r0 = r0.pts;
        r19 = r0;
        r20 = 1;
        r19 = r19[r20];
        r18 = pointToAngle(r16, r17, r18, r19);
        r0 = r18;
        r0 = -r0;
        r18 = r0;
        r0 = r18;
        r0 = (float) r0;
        r18 = r0;
        r0 = r18;
        r1 = r23;
        r1.startAngle = r0;
        r0 = r23;
        r0 = r0.isInCircle;
        r18 = r0;
        if (r18 != 0) goto L_0x0214;
    L_0x020c:
        r0 = r23;
        r0 = r0.isOnRect;
        r18 = r0;
        if (r18 == 0) goto L_0x0221;
    L_0x0214:
        r0 = r23;
        r0 = r0.textAndStickerViewSelectedListener;
        r18 = r0;
        r0 = r18;
        r1 = r23;
        r0.setSelectedView(r1);
    L_0x0221:
        r18 = 0;
        r0 = r24;
        r1 = r18;
        r18 = r0.getPointerId(r1);
        r0 = r18;
        r1 = r23;
        r1.mActivePointerId = r0;
        r0 = r23;
        r0 = r0.savedViewSelected;
        r18 = r0;
        if (r18 != 0) goto L_0x002f;
    L_0x0239:
        r18 = 1;
        r0 = r18;
        r1 = r23;
        r1.doubleTouchCall = r0;
        r0 = r23;
        r11 = r0.savedViewSelected;
        goto L_0x0040;
    L_0x0247:
        r18 = TAG;
        r19 = new java.lang.StringBuilder;
        r19.<init>();
        r20 = "ACTION_POINTER_DOWN pointer count = ";
        r19 = r19.append(r20);
        r20 = r24.getPointerCount();
        r19 = r19.append(r20);
        r19 = r19.toString();
        android.util.Log.e(r18, r19);
        goto L_0x002f;
    L_0x0265:
        r0 = r23;
        r0 = r0.isInCircle;
        r18 = r0;
        if (r18 == 0) goto L_0x0511;
    L_0x026d:
        r0 = r23;
        r0 = r0.pts;
        r18 = r0;
        r19 = 0;
        r18 = r18[r19];
        r0 = r23;
        r0 = r0.pts;
        r19 = r0;
        r20 = 1;
        r19 = r19[r20];
        r18 = pointToAngle(r16, r17, r18, r19);
        r0 = r18;
        r0 = -r0;
        r18 = r0;
        r0 = r18;
        r4 = (float) r0;
        r0 = r23;
        r0 = r0.stickerData;
        r18 = r0;
        r0 = r18;
        r0 = r0.canvasMatrix;
        r18 = r0;
        r0 = r23;
        r1 = r18;
        r12 = r0.getMatrixRotation(r1);
        r18 = 0;
        r18 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1));
        if (r18 == 0) goto L_0x02bf;
    L_0x02a7:
        r18 = 1119092736; // 0x42b40000 float:90.0 double:5.529052754E-315;
        r18 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1));
        if (r18 == 0) goto L_0x02bf;
    L_0x02ad:
        r18 = 1127481344; // 0x43340000 float:180.0 double:5.570497984E-315;
        r18 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1));
        if (r18 == 0) goto L_0x02bf;
    L_0x02b3:
        r18 = -1020002304; // 0xffffffffc3340000 float:-180.0 double:NaN;
        r18 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1));
        if (r18 == 0) goto L_0x02bf;
    L_0x02b9:
        r18 = -1028390912; // 0xffffffffc2b40000 float:-90.0 double:NaN;
        r18 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1));
        if (r18 != 0) goto L_0x03fb;
    L_0x02bf:
        r0 = r23;
        r0 = r0.startAngle;
        r18 = r0;
        r18 = r18 - r4;
        r18 = java.lang.Math.abs(r18);
        r19 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r18 = (r18 > r19 ? 1 : (r18 == r19 ? 0 : -1));
        if (r18 >= 0) goto L_0x03fb;
    L_0x02d1:
        r18 = 1;
        r0 = r18;
        r1 = r23;
        r1.orthogonal = r0;
    L_0x02d9:
        r0 = r23;
        r0 = r0.pts;
        r18 = r0;
        r19 = 0;
        r18 = r18[r19];
        r18 = r16 - r18;
        r0 = r23;
        r0 = r0.pts;
        r19 = r0;
        r20 = 0;
        r19 = r19[r20];
        r19 = r16 - r19;
        r18 = r18 * r19;
        r0 = r23;
        r0 = r0.pts;
        r19 = r0;
        r20 = 1;
        r19 = r19[r20];
        r19 = r17 - r19;
        r0 = r23;
        r0 = r0.pts;
        r20 = r0;
        r21 = 1;
        r20 = r20[r21];
        r20 = r17 - r20;
        r19 = r19 * r20;
        r18 = r18 + r19;
        r0 = r18;
        r0 = (double) r0;
        r18 = r0;
        r18 = java.lang.Math.sqrt(r18);
        r0 = r18;
        r15 = (float) r0;
        r0 = r23;
        r0 = r0.zoomStart;
        r18 = r0;
        r0 = r18;
        r0 = r0.x;
        r18 = r0;
        r0 = r23;
        r0 = r0.pts;
        r19 = r0;
        r20 = 0;
        r19 = r19[r20];
        r18 = r18 - r19;
        r0 = r23;
        r0 = r0.zoomStart;
        r19 = r0;
        r0 = r19;
        r0 = r0.x;
        r19 = r0;
        r0 = r23;
        r0 = r0.pts;
        r20 = r0;
        r21 = 0;
        r20 = r20[r21];
        r19 = r19 - r20;
        r18 = r18 * r19;
        r0 = r23;
        r0 = r0.zoomStart;
        r19 = r0;
        r0 = r19;
        r0 = r0.y;
        r19 = r0;
        r0 = r23;
        r0 = r0.pts;
        r20 = r0;
        r21 = 1;
        r20 = r20[r21];
        r19 = r19 - r20;
        r0 = r23;
        r0 = r0.zoomStart;
        r20 = r0;
        r0 = r20;
        r0 = r0.y;
        r20 = r0;
        r0 = r23;
        r0 = r0.pts;
        r21 = r0;
        r22 = 1;
        r21 = r21[r22];
        r20 = r20 - r21;
        r19 = r19 * r20;
        r18 = r18 + r19;
        r0 = r18;
        r0 = (double) r0;
        r18 = r0;
        r18 = java.lang.Math.sqrt(r18);
        r0 = r18;
        r13 = (float) r0;
        r14 = r15 / r13;
        r18 = r23.getScale();
        r0 = r18;
        r1 = r23;
        r1.scale = r0;
        r0 = r23;
        r0 = r0.scale;
        r18 = r0;
        r19 = MIN_ZOOM;
        r18 = (r18 > r19 ? 1 : (r18 == r19 ? 0 : -1));
        if (r18 >= 0) goto L_0x03b7;
    L_0x03a5:
        r0 = r23;
        r0 = r0.scale;
        r18 = r0;
        r19 = MIN_ZOOM;
        r18 = (r18 > r19 ? 1 : (r18 == r19 ? 0 : -1));
        if (r18 >= 0) goto L_0x002f;
    L_0x03b1:
        r18 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r18 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1));
        if (r18 <= 0) goto L_0x002f;
    L_0x03b7:
        r0 = r23;
        r0 = r0.stickerData;
        r18 = r0;
        r0 = r18;
        r0 = r0.canvasMatrix;
        r18 = r0;
        r0 = r23;
        r0 = r0.pts;
        r19 = r0;
        r20 = 0;
        r19 = r19[r20];
        r0 = r23;
        r0 = r0.pts;
        r20 = r0;
        r21 = 1;
        r20 = r20[r21];
        r0 = r18;
        r1 = r19;
        r2 = r20;
        r0.postScale(r14, r14, r1, r2);
        r0 = r23;
        r0 = r0.zoomStart;
        r18 = r0;
        r0 = r18;
        r1 = r16;
        r2 = r17;
        r0.set(r1, r2);
        r18 = r23.getScale();
        r0 = r18;
        r1 = r23;
        r1.scale = r0;
        goto L_0x002f;
    L_0x03fb:
        r0 = r23;
        r0 = r0.startAngle;
        r18 = r0;
        r18 = r12 - r18;
        r18 = r18 + r4;
        r18 = java.lang.Math.abs(r18);
        r19 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r18 = (r18 > r19 ? 1 : (r18 == r19 ? 0 : -1));
        if (r18 >= 0) goto L_0x0450;
    L_0x040f:
        r0 = r23;
        r0 = r0.startAngle;
        r18 = r0;
        r4 = r18 - r12;
        r18 = 1;
        r0 = r18;
        r1 = r23;
        r1.orthogonal = r0;
    L_0x041f:
        r0 = r23;
        r0 = r0.stickerData;
        r18 = r0;
        r0 = r18;
        r0 = r0.canvasMatrix;
        r18 = r0;
        r0 = r23;
        r0 = r0.startAngle;
        r19 = r0;
        r19 = r19 - r4;
        r0 = r23;
        r0 = r0.pts;
        r20 = r0;
        r21 = 0;
        r20 = r20[r21];
        r0 = r23;
        r0 = r0.pts;
        r21 = r0;
        r22 = 1;
        r21 = r21[r22];
        r18.postRotate(r19, r20, r21);
        r0 = r23;
        r0.startAngle = r4;
        goto L_0x02d9;
    L_0x0450:
        r18 = 1119092736; // 0x42b40000 float:90.0 double:5.529052754E-315;
        r0 = r23;
        r0 = r0.startAngle;
        r19 = r0;
        r19 = r12 - r19;
        r19 = r19 + r4;
        r18 = r18 - r19;
        r18 = java.lang.Math.abs(r18);
        r19 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r18 = (r18 > r19 ? 1 : (r18 == r19 ? 0 : -1));
        if (r18 >= 0) goto L_0x047d;
    L_0x0468:
        r18 = 1119092736; // 0x42b40000 float:90.0 double:5.529052754E-315;
        r0 = r23;
        r0 = r0.startAngle;
        r19 = r0;
        r18 = r18 + r19;
        r4 = r18 - r12;
        r18 = 1;
        r0 = r18;
        r1 = r23;
        r1.orthogonal = r0;
        goto L_0x041f;
    L_0x047d:
        r18 = 1127481344; // 0x43340000 float:180.0 double:5.570497984E-315;
        r0 = r23;
        r0 = r0.startAngle;
        r19 = r0;
        r19 = r12 - r19;
        r19 = r19 + r4;
        r18 = r18 - r19;
        r18 = java.lang.Math.abs(r18);
        r19 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r18 = (r18 > r19 ? 1 : (r18 == r19 ? 0 : -1));
        if (r18 >= 0) goto L_0x04ab;
    L_0x0495:
        r18 = 1127481344; // 0x43340000 float:180.0 double:5.570497984E-315;
        r0 = r23;
        r0 = r0.startAngle;
        r19 = r0;
        r18 = r18 + r19;
        r4 = r18 - r12;
        r18 = 1;
        r0 = r18;
        r1 = r23;
        r1.orthogonal = r0;
        goto L_0x041f;
    L_0x04ab:
        r18 = -1020002304; // 0xffffffffc3340000 float:-180.0 double:NaN;
        r0 = r23;
        r0 = r0.startAngle;
        r19 = r0;
        r19 = r12 - r19;
        r19 = r19 + r4;
        r18 = r18 - r19;
        r18 = java.lang.Math.abs(r18);
        r19 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r18 = (r18 > r19 ? 1 : (r18 == r19 ? 0 : -1));
        if (r18 >= 0) goto L_0x04d9;
    L_0x04c3:
        r18 = -1020002304; // 0xffffffffc3340000 float:-180.0 double:NaN;
        r0 = r23;
        r0 = r0.startAngle;
        r19 = r0;
        r18 = r18 + r19;
        r4 = r18 - r12;
        r18 = 1;
        r0 = r18;
        r1 = r23;
        r1.orthogonal = r0;
        goto L_0x041f;
    L_0x04d9:
        r18 = -1028390912; // 0xffffffffc2b40000 float:-90.0 double:NaN;
        r0 = r23;
        r0 = r0.startAngle;
        r19 = r0;
        r19 = r12 - r19;
        r19 = r19 + r4;
        r18 = r18 - r19;
        r18 = java.lang.Math.abs(r18);
        r19 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r18 = (r18 > r19 ? 1 : (r18 == r19 ? 0 : -1));
        if (r18 >= 0) goto L_0x0507;
    L_0x04f1:
        r18 = -1028390912; // 0xffffffffc2b40000 float:-90.0 double:NaN;
        r0 = r23;
        r0 = r0.startAngle;
        r19 = r0;
        r18 = r18 + r19;
        r4 = r18 - r12;
        r18 = 1;
        r0 = r18;
        r1 = r23;
        r1.orthogonal = r0;
        goto L_0x041f;
    L_0x0507:
        r18 = 0;
        r0 = r18;
        r1 = r23;
        r1.orthogonal = r0;
        goto L_0x041f;
    L_0x0511:
        r0 = r23;
        r0 = r0.isOnRect;
        r18 = r0;
        if (r18 == 0) goto L_0x002f;
    L_0x0519:
        r0 = r23;
        r0 = r0.mActivePointerId;
        r18 = r0;
        r0 = r24;
        r1 = r18;
        r10 = r0.findPointerIndex(r1);
        if (r10 < 0) goto L_0x002f;
    L_0x0529:
        r18 = r24.getPointerCount();
        r0 = r18;
        if (r10 >= r0) goto L_0x002f;
    L_0x0531:
        r0 = r24;
        r6 = r0.getX(r10);
        r0 = r24;
        r7 = r0.getY(r10);
        r0 = r23;
        r0 = r0.stickerData;
        r18 = r0;
        r0 = r18;
        r0 = r0.canvasMatrix;
        r18 = r0;
        r0 = r23;
        r0 = r0.previosPos;
        r19 = r0;
        r0 = r19;
        r0 = r0.x;
        r19 = r0;
        r19 = r6 - r19;
        r0 = r23;
        r0 = r0.previosPos;
        r20 = r0;
        r0 = r20;
        r0 = r0.y;
        r20 = r0;
        r20 = r7 - r20;
        r18.postTranslate(r19, r20);
        r0 = r23;
        r0 = r0.previosPos;
        r18 = r0;
        r0 = r18;
        r0.set(r6, r7);
        goto L_0x002f;
    L_0x0575:
        r5 = new android.os.Handler;
        r5.<init>();
        r18 = new com.lyrebirdstudio.sticker.StickerView$1;
        r0 = r18;
        r1 = r23;
        r0.<init>();
        r20 = 100;
        r0 = r18;
        r1 = r20;
        r5.postDelayed(r0, r1);
        r18 = TAG;
        r19 = "ACTION_UP";
        android.util.Log.e(r18, r19);
        r18 = 0;
        r0 = r18;
        r1 = r23;
        r1.orthogonal = r0;
        r0 = r23;
        r0 = r0.textAndStickerViewSelectedListener;
        r18 = r0;
        r0 = r23;
        r0 = r0.stickerData;
        r19 = r0;
        r18.onTouchUp(r19);
        r0 = r23;
        r0 = r0.onDecorateViewTouchUpListener;
        r18 = r0;
        if (r18 == 0) goto L_0x05c1;
    L_0x05b2:
        r0 = r23;
        r0 = r0.onDecorateViewTouchUpListener;
        r18 = r0;
        r0 = r23;
        r0 = r0.stickerData;
        r19 = r0;
        r18.onTouchUp(r19);
    L_0x05c1:
        r18 = 0;
        r0 = r18;
        r1 = r23;
        r1.isOnTouch = r0;
        r18 = 0;
        r0 = r18;
        r1 = r23;
        r1.isOnRect = r0;
        r18 = -1;
        r0 = r18;
        r1 = r23;
        r1.mActivePointerId = r0;
        goto L_0x002f;
    L_0x05db:
        r18 = 0;
        r0 = r18;
        r1 = r23;
        r1.startAngleGesture = r0;
        r18 = r24.getAction();
        r19 = 65280; // 0xff00 float:9.1477E-41 double:3.22526E-319;
        r18 = r18 & r19;
        r10 = r18 >> 8;
        r0 = r24;
        r9 = r0.getPointerId(r10);
        r0 = r23;
        r0 = r0.mActivePointerId;
        r18 = r0;
        r0 = r18;
        if (r9 != r0) goto L_0x002f;
    L_0x05fe:
        if (r10 != 0) goto L_0x062e;
    L_0x0600:
        r8 = 1;
    L_0x0601:
        if (r8 < 0) goto L_0x002f;
    L_0x0603:
        r18 = r24.getPointerCount();
        r0 = r18;
        if (r8 >= r0) goto L_0x002f;
    L_0x060b:
        r0 = r23;
        r0 = r0.previosPos;
        r18 = r0;
        r0 = r24;
        r19 = r0.getX(r8);
        r0 = r24;
        r20 = r0.getY(r8);
        r18.set(r19, r20);
        r0 = r24;
        r18 = r0.getPointerId(r8);
        r0 = r18;
        r1 = r23;
        r1.mActivePointerId = r0;
        goto L_0x002f;
    L_0x062e:
        r8 = 0;
        goto L_0x0601;
        */
      //  throw new UnsupportedOperationException("Method not decompiled: com.lyrebirdstudio.sticker.StickerView.onTouchEvent(android.view.MotionEvent):boolean");
    //}

    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.mScaleDetector.onTouchEvent(motionEvent);
        this.mRotationDetector.onTouchEvent(motionEvent);
        final float x = motionEvent.getX();
        final float y = motionEvent.getY();
        switch (motionEvent.getAction() & 0xFF) {
            case 0: {
                this.isOnRect = false;
                this.isInCircle = false;
                this.savedViewSelected = this.viewSelected;
                this.isOnTouch = true;
                this.pts[0] = x;
                this.pts[1] = y;
                this.stickerData.canvasMatrix.invert(this.inverse);
                this.inverse.mapPoints(this.pts, this.pts);
                Log.e(StickerView.TAG, "ACTION_DOWN savedViewSelected " + this.savedViewSelected);
                Log.e(StickerView.TAG, "pointer count = " + motionEvent.getPointerCount());
                if (this.viewSelected && this.isOnCross(this.pts[0], this.pts[1])) {
                    this.createDeleteDialog(this.getContext(), this);
                    return true;
                }
                this.isOnRect = this.isOnRectCheck(this.pts[0], this.pts[1]);
                Log.e(StickerView.TAG, "ACTION_DOWN viewSelected " + this.viewSelected);
                this.isInCircle = this.isInCircle(this.pts[0], this.pts[1]);
                this.previosPos.set(x, y);
                this.zoomStart.set(x, y);
                this.pts[0] = this.touchRect.centerX();
                this.pts[1] = this.touchRect.centerY();
                this.stickerData.canvasMatrix.mapPoints(this.pts, this.pts);
                this.startAngle = -pointToAngle(x, y, this.pts[0], this.pts[1]);
                if (this.isInCircle || this.isOnRect) {
                    this.textAndStickerViewSelectedListener.setSelectedView(this);
                }
                this.mActivePointerId = motionEvent.getPointerId(0);
                if (!this.savedViewSelected) {
                    this.doubleTouchCall = true;
                    return this.savedViewSelected;
                }
                break;
            }
            case 5: {
                Log.e(StickerView.TAG, "ACTION_POINTER_DOWN pointer count = " + motionEvent.getPointerCount());
                break;
            }
            case 2: {
                if (this.isInCircle) {
                    float startAngle = -pointToAngle(x, y, this.pts[0], this.pts[1]);
                    final float matrixRotation = this.getMatrixRotation(this.stickerData.canvasMatrix);
                    if ((matrixRotation == 0.0f || matrixRotation == 90.0f || matrixRotation == 180.0f || matrixRotation == -180.0f || matrixRotation == -90.0f) && Math.abs(this.startAngle - startAngle) < 4.0f) {
                        this.orthogonal = true;
                    }
                    else {
                        if (Math.abs(matrixRotation - this.startAngle + startAngle) < 4.0f) {
                            startAngle = this.startAngle - matrixRotation;
                            this.orthogonal = true;
                        }
                        else if (Math.abs(90.0f - (matrixRotation - this.startAngle + startAngle)) < 4.0f) {
                            startAngle = 90.0f + this.startAngle - matrixRotation;
                            this.orthogonal = true;
                        }
                        else if (Math.abs(180.0f - (matrixRotation - this.startAngle + startAngle)) < 4.0f) {
                            startAngle = 180.0f + this.startAngle - matrixRotation;
                            this.orthogonal = true;
                        }
                        else if (Math.abs(-180.0f - (matrixRotation - this.startAngle + startAngle)) < 4.0f) {
                            startAngle = -180.0f + this.startAngle - matrixRotation;
                            this.orthogonal = true;
                        }
                        else if (Math.abs(-90.0f - (matrixRotation - this.startAngle + startAngle)) < 4.0f) {
                            startAngle = -90.0f + this.startAngle - matrixRotation;
                            this.orthogonal = true;
                        }
                        else {
                            this.orthogonal = false;
                        }
                        this.stickerData.canvasMatrix.postRotate(this.startAngle - startAngle, this.pts[0], this.pts[1]);
                        this.startAngle = startAngle;
                    }
                    final float n = (float) Math.sqrt((x - this.pts[0]) * (x - this.pts[0]) + (y - this.pts[1]) * (y - this.pts[1])) / (float) Math.sqrt((this.zoomStart.x - this.pts[0]) * (this.zoomStart.x - this.pts[0]) + (this.zoomStart.y - this.pts[1]) * (this.zoomStart.y - this.pts[1]));
                    this.scale = this.getScale();
                    if (this.scale >= StickerView.MIN_ZOOM || (this.scale < StickerView.MIN_ZOOM && n > 1.0f)) {
                        this.stickerData.canvasMatrix.postScale(n, n, this.pts[0], this.pts[1]);
                        this.zoomStart.set(x, y);
                        this.scale = this.getScale();
                        break;
                    }
                    break;
                }
                else {
                    if (!this.isOnRect) {
                        break;
                    }
                    final int pointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (pointerIndex >= 0 && pointerIndex < motionEvent.getPointerCount()) {
                        final float x2 = motionEvent.getX(pointerIndex);
                        final float y2 = motionEvent.getY(pointerIndex);
                        this.stickerData.canvasMatrix.postTranslate(x2 - this.previosPos.x, y2 - this.previosPos.y);
                        this.previosPos.set(x2, y2);
                        break;
                    }
                    break;
                }
                
            }
            case 1: {
                new Handler().postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        StickerView.this.doubleTouchCall = false;
                    }
                }, 100L);
                Log.e(StickerView.TAG, "ACTION_UP");
                this.orthogonal = false;
                this.textAndStickerViewSelectedListener.onTouchUp(this.stickerData);
                if (this.onDecorateViewTouchUpListener != null) {
                    this.onDecorateViewTouchUpListener.onTouchUp(this.stickerData);
                }
                this.isOnTouch = false;
                this.isOnRect = false;
                this.mActivePointerId = -1;
                break;
            }
            case 6: {
                this.startAngleGesture = 0.0f;
                final int n2 = (motionEvent.getAction() & 0xFF00) >> 8;
                if (motionEvent.getPointerId(n2) != this.mActivePointerId) {
                    break;
                }
                int n3;
                if (n2 == 0) {
                    n3 = 1;
                }
                else {
                    n3 = 0;
                }
                if (n3 >= 0 && n3 < motionEvent.getPointerCount()) {
                    this.previosPos.set(motionEvent.getX(n3), motionEvent.getY(n3));
                    this.mActivePointerId = motionEvent.getPointerId(n3);
                    break;
                }
                break;
            }
        }
        final boolean onTouchEvent = this.gestureDetector.onTouchEvent(motionEvent);
        this.postInvalidate();
        return onTouchEvent;
    }
    float getScale() {
        this.stickerData.canvasMatrix.getValues(this.values);
        float scalex = this.values[0];
        float skewy = this.values[3];
        return (float) Math.sqrt((double) ((scalex * scalex) + (skewy * skewy)));
    }

    public static int pointToAngle(float x, float y, float centerX, float centerY) {
        if (x >= centerX && y < centerY) {
            return ((int) Math.toDegrees(Math.atan(((double) (x - centerX)) / ((double) (centerY - y))))) + 270;
        }
        if (x > centerX && y >= centerY) {
            return (int) Math.toDegrees(Math.atan(((double) (y - centerY)) / ((double) (x - centerX))));
        }
        if (x <= centerX && y > centerY) {
            return ((int) Math.toDegrees(Math.atan(((double) (centerX - x)) / ((double) (y - centerY))))) + 90;
        }
        if (x >= centerX || y > centerY) {
            return 0;
        }
        return ((int) Math.toDegrees(Math.atan(((double) (centerY - y)) / ((double) (centerX - x))))) + 180;
    }

    float getMatrixRotation(Matrix matrix) {
        matrix.getValues(this.f769v);
        return (float) Math.round(Math.atan2((double) this.f769v[1], (double) this.f769v[0]) * 57.29577951308232d);
    }
}
