package com.photo.blureffectcamera.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.sticker.RotationGestureDetector;
import com.photo.blureffectcamera.sticker.StickerView;


public class CanvasTextView extends DecorateView {
    private static final int INVALID_POINTER_ID = -1;
    static final float MIN_ZOOM = 0.8f;
    static int SNAP_CIRCLE_0 = 0;
    static int SNAP_CIRCLE_1 = 0;
    static int SNAP_CIRCLE_2 = 0;
    static int SNAP_CIRCLE_NONE = 0;
    private static final String TAG = "CanvasTextView";
    public static Paint paintSnap;
    float actualRadius;
    Paint bitmapPaint;
    float bitmapWidth;
    Bitmap blackBarBitmap;
    float circlePadding;
    Paint dashPaint;
    Path dashPathHorizontal;
    Path dashPathVertical;
    boolean doubleTouchCall;
    boolean downModeSnap;
    Bitmap editBitmap;
    Matrix editBtmScaleMatrix;
    final float epsilon;
    GestureDetector gestureDetector;
    Matrix inverse;
    boolean isInCircle;
    boolean isOnRect;
    boolean isOnTouch;
    Rect lineBound;
    private int mActivePointerId;
    private RotationGestureDetector mRotationDetector;
    private ScaleGestureDetector mScaleDetector;
    float mScaleFactor;
    boolean onRectSnap;
    boolean orthogonal;
    float paddingHeight;
    float paddingWidth;
    Paint paintBg;
    Paint paintGreen;
    PointF previosPos;
    float previousY;
    float previousYSnap;
    float[] pts;
    float radiusx;
    RectF rectBg;
    Paint rectPaint;
    Paint rectPaintOnTouch;
    RectF rectSnap;
    private RectF rectText;
    Paint redPaint;
    Bitmap removeBitmap;
    Matrix removeBitmapMatrix;
    Matrix removeBtmSnapMatrix;
    CustomRelativeLayout.RemoveTextListener removeTextListener;
    PointF res;
    RotationGestureDetector.OnRotationGestureListener rotateListener;
    boolean savedViewSelected;
    float scale;
    Bitmap scaleBitmap;
    Matrix scaleBitmapMatrix;
    Matrix scaleBitmapSwitch;
    float screenHeight;
    float screenWidth;
    SingleTap singleTapListener;
    private float startAngle;
    float startAngleGesture;
    Bitmap switchBitmap;
    Matrix switchBtmScaleMatrix;
    TextAndStickerViewSelectedListener textAndStickerViewSelectedListener;
    Rect textBoundrect;
    public TextData textData;
    float[] f768v;
    float[] values;
    private boolean viewSelected;
    ViewSelectedListener viewSelectedListenerEx;
    Paint whitePaint;
    PointF zoomStart;

    /* renamed from: com.lyrebirdstudio.canvastext.CanvasTextView.1 */
    class C06831 implements Runnable {
        C06831() {
        }

        public void run() {
            CanvasTextView.this.doubleTouchCall = false;
        }
    }

    private class GestureListener extends SimpleOnGestureListener {
        private GestureListener() {
        }

        public boolean onDown(MotionEvent e) {
            if (CanvasTextView.this.textData.getSnapMode() && CanvasTextView.this.onRectSnap) {
                return true;
            }
            if (CanvasTextView.this.textData.getSnapMode() && !CanvasTextView.this.onRectSnap) {
                CanvasTextView.this.viewSelected = false;
                return false;
            } else if (CanvasTextView.this.isInCircle || CanvasTextView.this.isOnRect) {
                return true;
            } else {
                CanvasTextView.this.viewSelected = false;
                return false;
            }
        }

        public boolean onSingleTapUp(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            if (!CanvasTextView.this.textData.getSnapMode()) {
                CanvasTextView.this.pts[0] = event.getX();
                CanvasTextView.this.pts[1] = event.getY();
                CanvasTextView.this.textData.canvasMatrix.invert(CanvasTextView.this.inverse);
                CanvasTextView.this.inverse.mapPoints(CanvasTextView.this.pts, CanvasTextView.this.pts);
                CanvasTextView.this.isOnRect = CanvasTextView.this.isOnRectCheck(CanvasTextView.this.pts[0], CanvasTextView.this.pts[1]);
                if (CanvasTextView.this.isOnRect) {
                    if (CanvasTextView.this.doubleTouchCall) {
                        CanvasTextView.this.viewSelected = true;
                    } else {
                        CanvasTextView.this.viewSelected = !CanvasTextView.this.savedViewSelected;
                    }
                    CanvasTextView.this.doubleTouchCall = false;
                } else {
                    CanvasTextView.this.viewSelected = false;
                }
                if (CanvasTextView.this.isInCircle || CanvasTextView.this.isOnRect) {
                    return true;
                }
                return false;
            } else if (x <= CanvasTextView.this.rectSnap.left || x >= CanvasTextView.this.rectSnap.right || y <= CanvasTextView.this.rectSnap.top || y >= CanvasTextView.this.rectSnap.bottom) {
                CanvasTextView.this.viewSelected = false;
                CanvasTextView.this.onRectSnap = false;
                return false;
            } else {
                CanvasTextView.this.onRectSnap = true;
                CanvasTextView.this.viewSelected = true;
                return true;
            }
        }

        public boolean onDoubleTap(MotionEvent e) {
            float x = e.getX();
            float y = e.getY();
            if (!CanvasTextView.this.textData.getSnapMode()) {
                CanvasTextView.this.pts[0] = e.getX();
                CanvasTextView.this.pts[1] = e.getY();
                CanvasTextView.this.textData.canvasMatrix.invert(CanvasTextView.this.inverse);
                CanvasTextView.this.inverse.mapPoints(CanvasTextView.this.pts, CanvasTextView.this.pts);
                CanvasTextView.this.isOnRect = CanvasTextView.this.isOnRectCheck(CanvasTextView.this.pts[0], CanvasTextView.this.pts[1]);
                if (CanvasTextView.this.isOnRect) {
                    CanvasTextView.this.viewSelected = true;
                    CanvasTextView.this.singleTapped();
                } else {
                    CanvasTextView.this.viewSelected = false;
                }
                return true;
            } else if (x <= CanvasTextView.this.rectSnap.left || x >= CanvasTextView.this.rectSnap.right || y <= CanvasTextView.this.rectSnap.top || y >= CanvasTextView.this.rectSnap.bottom) {
                CanvasTextView.this.viewSelected = false;
                CanvasTextView.this.onRectSnap = false;
                return false;
            } else {
                CanvasTextView.this.singleTapped();
                CanvasTextView.this.onRectSnap = true;
                CanvasTextView.this.viewSelected = true;
                return true;
            }
        }
    }

    private class ScaleListener extends SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        public boolean onScale(ScaleGestureDetector detector) {
            CanvasTextView.this.mScaleFactor = detector.getScaleFactor();
            if (detector.isInProgress()) {
                CanvasTextView.this.pts[0] = CanvasTextView.this.rectText.centerX();
                CanvasTextView.this.pts[1] = CanvasTextView.this.rectText.centerY();
                CanvasTextView.this.textData.canvasMatrix.mapPoints(CanvasTextView.this.pts, CanvasTextView.this.pts);
                CanvasTextView.this.mScaleFactor = detector.getScaleFactor();
                CanvasTextView.this.mScaleFactor = Math.max(CanvasTextView.MIN_ZOOM, CanvasTextView.this.mScaleFactor);
                CanvasTextView.this.textData.canvasMatrix.postScale(CanvasTextView.this.mScaleFactor, CanvasTextView.this.mScaleFactor, CanvasTextView.this.pts[0], CanvasTextView.this.pts[1]);
                CanvasTextView.this.scale = CanvasTextView.this.getScale();
                CanvasTextView.this.invalidate();
            } else {
                CanvasTextView.this.pts[0] = CanvasTextView.this.rectText.centerX();
                CanvasTextView.this.pts[1] = CanvasTextView.this.rectText.centerY();
                CanvasTextView.this.textData.canvasMatrix.mapPoints(CanvasTextView.this.pts, CanvasTextView.this.pts);
                CanvasTextView.this.mScaleFactor = detector.getScaleFactor();
                CanvasTextView.this.mScaleFactor = Math.max(CanvasTextView.MIN_ZOOM, CanvasTextView.this.mScaleFactor);
                CanvasTextView.this.textData.canvasMatrix.postScale(CanvasTextView.this.mScaleFactor, CanvasTextView.this.mScaleFactor, CanvasTextView.this.pts[0], CanvasTextView.this.pts[1]);
                CanvasTextView.this.scale = CanvasTextView.this.getScale();
                CanvasTextView.this.invalidate();
            }
            return true;
        }
    }

    public interface TextAndStickerViewSelectedListener {
        void onTouchUp(BaseData baseData);

        void setSelectedView(DecorateView decorateView);
    }

    /* renamed from: com.lyrebirdstudio.canvastext.CanvasTextView.2 */
    class C12932 implements RotationGestureDetector.OnRotationGestureListener {
        C12932() {
        }

        public void OnRotation(RotationGestureDetector rotationDetector) {
            float angle = rotationDetector.getAngle();
            float rotation = CanvasTextView.this.getMatrixRotation(CanvasTextView.this.textData.canvasMatrix);
            if ((rotation == 0.0f || rotation == 90.0f || rotation == 180.0f || rotation == -180.0f || rotation == -90.0f) && Math.abs(CanvasTextView.this.startAngleGesture - angle) < 4.0f) {
                CanvasTextView.this.orthogonal = true;
                return;
            }
            if (Math.abs((rotation - CanvasTextView.this.startAngleGesture) + angle) < 4.0f) {
                angle = CanvasTextView.this.startAngleGesture - rotation;
                CanvasTextView.this.orthogonal = true;
            } else if (Math.abs(90.0f - ((rotation - CanvasTextView.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (CanvasTextView.this.startAngleGesture + 90.0f) - rotation;
                CanvasTextView.this.orthogonal = true;
            } else if (Math.abs(180.0f - ((rotation - CanvasTextView.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (180.0f + CanvasTextView.this.startAngleGesture) - rotation;
                CanvasTextView.this.orthogonal = true;
            } else if (Math.abs(-180.0f - ((rotation - CanvasTextView.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (CanvasTextView.this.startAngleGesture - 0.024902344f) - rotation;
                CanvasTextView.this.orthogonal = true;
            } else if (Math.abs(-90.0f - ((rotation - CanvasTextView.this.startAngleGesture) + angle)) < 4.0f) {
                angle = (CanvasTextView.this.startAngleGesture - 0.049804688f) - rotation;
                CanvasTextView.this.orthogonal = true;
            } else {
                CanvasTextView.this.orthogonal = false;
            }
            CanvasTextView.this.pts[0] = CanvasTextView.this.rectText.centerX();
            CanvasTextView.this.pts[1] = CanvasTextView.this.rectText.centerY();
            CanvasTextView.this.textData.canvasMatrix.mapPoints(CanvasTextView.this.pts, CanvasTextView.this.pts);
            CanvasTextView.this.textData.canvasMatrix.postRotate(CanvasTextView.this.startAngleGesture - angle, CanvasTextView.this.pts[0], CanvasTextView.this.pts[1]);
            CanvasTextView.this.startAngleGesture = angle;
            CanvasTextView.this.invalidate();
        }
    }

    public void setSingleTapListener(SingleTap l) {
        this.singleTapListener = l;
    }

    public void setViewSelectedListener(ViewSelectedListener l) {
        this.viewSelectedListenerEx = l;
    }

    public void setTextAndStickerViewSelectedListener(TextAndStickerViewSelectedListener l) {
        this.textAndStickerViewSelectedListener = l;
    }

    public void setTextSelected(boolean selection) {
        this.viewSelected = selection;
        postInvalidate();
    }

    public void setRemoveTextListener(CustomRelativeLayout.RemoveTextListener listener) {
        this.removeTextListener = listener;
    }

    public BaseData getData() {
        return this.textData;
    }

    public CanvasTextView(Context context, TextData td, Bitmap removeBtm, Bitmap scaleBtm, Bitmap editBtm, Bitmap switchBtm, Bitmap blackBar) {
        super(context);
        this.paddingHeight = 30.0f;
        this.paddingWidth = 10.0f;
        this.radiusx = 40.0f;
        this.actualRadius = this.paddingHeight;
        this.circlePadding = 5.0f;
        this.paintGreen = new Paint(1);
        this.redPaint = new Paint(1);
        this.whitePaint = new Paint(1);
        this.bitmapPaint = new Paint(1);
        this.values = new float[9];
        this.scale = 1.0f;
        this.viewSelected = false;
        this.isOnTouch = false;
        this.removeBitmapMatrix = new Matrix();
        this.scaleBitmapMatrix = new Matrix();
        this.scaleBitmapSwitch = new Matrix();
        this.dashPaint = new Paint();
        this.rectSnap = new RectF();
        this.removeBtmSnapMatrix = new Matrix();
        this.editBtmScaleMatrix = new Matrix();
        this.switchBtmScaleMatrix = new Matrix();
        this.paintBg = new Paint(1);
        this.rectBg = new RectF();
        this.res = new PointF();
        this.lineBound = new Rect();
        this.isOnRect = false;
        this.isInCircle = false;
        this.epsilon = 4.0f;
        this.orthogonal = false;
        this.previosPos = new PointF();
        this.zoomStart = new PointF();
        this.startAngle = 0.0f;
        this.inverse = new Matrix();
        this.pts = new float[2];
        this.onRectSnap = false;
        this.downModeSnap = false;
        this.savedViewSelected = false;
        this.doubleTouchCall = false;
        this.mActivePointerId = INVALID_POINTER_ID;
        this.f768v = new float[9];
        this.mScaleFactor = 1.0f;
        this.startAngleGesture = 0.0f;
        this.rotateListener = new C12932();
        this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        this.mRotationDetector = new RotationGestureDetector(this.rotateListener);
        float textSize = context.getResources().getDimension(R.dimen.myFontSize);
        this.screenWidth = (float) getResources().getDisplayMetrics().widthPixels;
        this.screenHeight = (float) getResources().getDisplayMetrics().heightPixels;
        this.rectPaint = new Paint(1);
        this.rectPaint.setColor(2006555033);
        this.redPaint.setColor(-2140327);
        this.paintGreen.setColor(-1722294439);
        this.whitePaint.setColor(-1460137);
        this.bitmapPaint.setFilterBitmap(true);
        this.rectPaintOnTouch = new Paint(1);
        this.rectPaintOnTouch.setColor(2011028957);
        this.textBoundrect = new Rect();
        if (td == null) {
            this.textData = new TextData(textSize);
            this.textData.textPaint.getTextBounds(TextData.defaultMessage, 0, TextData.defaultMessage.length(), this.textBoundrect);
            this.textData.xPos = (this.screenWidth / 2.0f) - ((float) (this.textBoundrect.width() / 2));
            this.textData.yPos = this.screenHeight / 3.0f;
        } else {
            this.textData = td;
            if (this.textData.getFontPath() != null) {
                Typeface typeFace = FontCache.get(context, this.textData.getFontPath());
                if (typeFace != null) {
                    this.textData.textPaint.setTypeface(typeFace);
                }
            }
            this.textData.textPaint.getTextBounds(this.textData.message, 0, this.textData.message.length(), this.textBoundrect);
        }
        this.paddingWidth = this.screenWidth / 15.0f;
        this.paddingHeight = this.screenWidth / 14.0f;
        this.rectText = new RectF(this.textData.xPos - this.paddingWidth, (this.textData.yPos - ((float) this.textBoundrect.height())) - this.paddingHeight, (this.textData.xPos + ((float) this.textBoundrect.width())) + (2.0f * this.paddingWidth), this.textData.yPos + this.paddingHeight);
        this.gestureDetector = new GestureDetector(context, new GestureListener());
        float minDimen = Math.min(this.screenWidth, this.screenHeight);
        this.actualRadius = minDimen / 20.0f;
        this.circlePadding = this.actualRadius / 2.0f;
        if (this.actualRadius <= 5.0f) {
            this.actualRadius = this.paddingHeight;
        }
        this.removeBitmap = removeBtm;
        this.scaleBitmap = scaleBtm;
        this.editBitmap = editBtm;
        this.switchBitmap = switchBtm;
        this.blackBarBitmap = blackBar;
        this.bitmapWidth = (float) this.removeBitmap.getWidth();
        this.removeBitmapMatrix.reset();
        this.scaleBitmapMatrix.reset();
        this.scaleBitmapSwitch.reset();
        float bitmapScale = (2.0f * this.actualRadius) / this.bitmapWidth;
        this.removeBitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.removeBitmapMatrix.postTranslate(this.rectText.left - ((this.bitmapWidth * bitmapScale) / 2.0f), this.rectText.top - ((this.bitmapWidth * bitmapScale) / 2.0f));
        this.scaleBitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.scaleBitmapMatrix.postTranslate(this.rectText.right - ((this.bitmapWidth * bitmapScale) / 2.0f), this.rectText.bottom - ((this.bitmapWidth * bitmapScale) / 2.0f));
        this.scaleBitmapSwitch.postScale(bitmapScale, bitmapScale);
        this.scaleBitmapSwitch.postTranslate(this.rectText.right - ((this.bitmapWidth * bitmapScale) / 2.0f), this.rectText.top - ((this.bitmapWidth * bitmapScale) / 2.0f));
        this.scale = getScale();
        this.scaleBitmapMatrix.postScale(1.0f / this.scale, 1.0f / this.scale, this.rectText.right, this.rectText.bottom);
        this.removeBitmapMatrix.postScale(1.0f / this.scale, 1.0f / this.scale, this.rectText.left, this.rectText.top);
        this.scaleBitmapSwitch.postScale(1.0f / this.scale, 1.0f / this.scale, this.rectText.right, this.rectText.top);
        float rW = this.rectText.width();
        float rH = this.rectText.height();
        this.dashPaint.setColor(-7829368);
        this.dashPaint.setStyle(Style.STROKE);
        float strokeW = minDimen / 120.0f;
        if (strokeW <= 0.0f) {
            strokeW = 5.0f;
        }
        this.dashPaint.setStrokeWidth(strokeW);
        this.dashPaint.setPathEffect(new DashPathEffect(new float[]{strokeW, strokeW}, 0.0f));
        this.dashPathVertical = new Path();
        dashPathReset(rW, rH);
        initSnapText();
    }

    public TextData getTextData() {
        return this.textData;
    }

    void dashPathReset(float w, float h) {
        this.dashPathVertical.reset();
        this.dashPathVertical.moveTo(this.rectText.left + (w / 2.0f), this.rectText.top - (h / 5.0f));
        this.dashPathVertical.lineTo(this.rectText.left + (w / 2.0f), this.rectText.top + ((6.0f * h) / 5.0f));
        this.dashPathHorizontal = new Path();
        this.dashPathHorizontal.moveTo(this.rectText.left + ((-w) / 5.0f), this.rectText.top + (h / 2.0f));
        this.dashPathHorizontal.lineTo(this.rectText.left + ((6.0f * w) / 5.0f), this.rectText.top + (h / 2.0f));
    }

    public void setAlignment(int mode) {
        Align align = Align.LEFT;
        if (mode == 1) {
            align = Align.CENTER;
        }
        if (mode == 2) {
            align = Align.RIGHT;
        }
        this.textData.textPaint.setTextAlign(align);
        invalidate();
    }

    public boolean isDecorateViewSelected() {
        return this.viewSelected;
    }

    public void setDecorateViewSelected(boolean selection) {
        this.viewSelected = selection;
        invalidate();
    }

    public void setMatrix(MyMatrix matrix) {
        this.textData.canvasMatrix = matrix;
        this.scale = getScale();
    }

    public void onDraw(Canvas canvas) {
        resetRectPosition();
        if (this.textData.getSnapMode()) {
            drawSnap(canvas);
            return;
        }
        float bitmapScale = (this.actualRadius * 2.0f) / this.bitmapWidth;
        this.removeBitmapMatrix.reset();
        this.scaleBitmapMatrix.reset();
        this.scaleBitmapSwitch.reset();
        this.removeBitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.removeBitmapMatrix.postTranslate(this.rectText.left - ((this.bitmapWidth * bitmapScale) / 2.0f), this.rectText.top - ((this.bitmapWidth * bitmapScale) / 2.0f));
        this.scaleBitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.scaleBitmapMatrix.postTranslate(this.rectText.right - ((this.bitmapWidth * bitmapScale) / 2.0f), this.rectText.bottom - ((this.bitmapWidth * bitmapScale) / 2.0f));
        this.scaleBitmapSwitch.postScale(bitmapScale, bitmapScale);
        this.scaleBitmapSwitch.postTranslate(this.rectText.right - ((this.bitmapWidth * bitmapScale) / 2.0f), this.rectText.top - ((this.bitmapWidth * bitmapScale) / 2.0f));
        this.scale = getScale();
        this.scaleBitmapMatrix.postScale(1.0f / this.scale, 1.0f / this.scale, this.rectText.right, this.rectText.bottom);
        this.removeBitmapMatrix.postScale(1.0f / this.scale, 1.0f / this.scale, this.rectText.left, this.rectText.top);
        this.scaleBitmapSwitch.postScale(1.0f / this.scale, 1.0f / this.scale, this.rectText.right, this.rectText.top);
        canvas.setMatrix(this.textData.canvasMatrix);
        if (this.viewSelected) {
            if (this.isOnTouch) {
                canvas.drawRect(this.rectText, this.rectPaintOnTouch);
            } else {
                canvas.drawRect(this.rectText, this.rectPaint);
            }
            float rad = this.actualRadius / this.scale;
            canvas.drawCircle(this.rectText.right, this.rectText.bottom, rad, this.whitePaint);
            canvas.drawCircle(this.rectText.left, this.rectText.top, rad, this.redPaint);
            canvas.drawCircle(this.rectText.right, this.rectText.top, rad, this.paintGreen);
            canvas.drawBitmap(this.scaleBitmap, this.scaleBitmapMatrix, this.bitmapPaint);
            canvas.drawBitmap(this.removeBitmap, this.removeBitmapMatrix, this.bitmapPaint);
            canvas.drawBitmap(this.blackBarBitmap, this.scaleBitmapSwitch, this.bitmapPaint);
        }
        drawMultiline(canvas, this.textData.message, this.textData.xPos, this.textData.yPos, this.textData.textPaint, this.textData, this.lineBound, this.rectBg, this.paintBg);
        if (this.orthogonal) {
            canvas.drawPath(this.dashPathVertical, this.dashPaint);
            canvas.drawPath(this.dashPathHorizontal, this.dashPaint);
        }
    }

    static {
        paintSnap = new Paint(1);
        SNAP_CIRCLE_NONE = INVALID_POINTER_ID;
        SNAP_CIRCLE_0 = 0;
        SNAP_CIRCLE_1 = 1;
        SNAP_CIRCLE_2 = 2;
    }

    void initSnapText() {
        setRectSnap(this.textData, this.rectSnap, this.screenWidth);
        paintSnap.setColor(-2030043136);
        if (this.textData.yPosSnap == 0.0f) {
            this.textData.yPosSnap = this.screenHeight / 2.0f;
        }
    }

    public static void setRectSnap(TextData textData, RectF rectSnap, float screenWidth) {
        float width = screenWidth;
        int height = getTextHeight(textData);
        float padding = getSnapRectPadding(textData);
        float yPos = textData.yPosSnap + ((float) (height / 2));
        rectSnap.set(0.0f, (yPos - ((float) height)) - padding, width, yPos + padding);
    }

    void drawSnap(Canvas canvas) {
        setRectSnap(this.textData, this.rectSnap, this.screenWidth);
        Canvas canvas2 = canvas;
        drawSnap(canvas2, this.textData, (this.screenWidth - ((float) getMaxLength(this.textData, this.lineBound, this.textData.message))) / 2.0f, ((getSnapRectPadding(this.textData) + this.rectSnap.top) + ((float) getTextHeight(this.textData))) - this.textData.textPaint.descent(), this.rectSnap, paintSnap, this.lineBound);
        if (this.viewSelected) {
            this.scale = getScale();
            float rad = this.actualRadius;
            float midX = this.rectSnap.left + (this.rectSnap.width() / 2.0f);
            float circleY = this.rectSnap.bottom + rad;
            float d0 = 3.0f * rad;
            float d2 = -3.0f * rad;
            float bitmapScale = (2.0f * this.actualRadius) / this.bitmapWidth;
            this.removeBtmSnapMatrix.reset();
            this.removeBtmSnapMatrix.postScale(bitmapScale, bitmapScale);
            this.removeBtmSnapMatrix.postTranslate((midX - d0) - ((this.bitmapWidth * bitmapScale) / 2.0f), circleY - ((this.bitmapWidth * bitmapScale) / 2.0f));
            this.editBtmScaleMatrix.reset();
            this.editBtmScaleMatrix.postScale(bitmapScale, bitmapScale);
            this.editBtmScaleMatrix.postTranslate((midX - 0.0f) - ((this.bitmapWidth * bitmapScale) / 2.0f), circleY - ((this.bitmapWidth * bitmapScale) / 2.0f));
            this.switchBtmScaleMatrix.reset();
            this.switchBtmScaleMatrix.postScale(bitmapScale, bitmapScale);
            this.switchBtmScaleMatrix.postTranslate((midX - d2) - ((this.bitmapWidth * bitmapScale) / 2.0f), circleY - ((this.bitmapWidth * bitmapScale) / 2.0f));
            canvas.drawCircle(midX - d0, circleY, rad, this.whitePaint);
            canvas.drawCircle(midX - 0.0f, circleY, rad, this.redPaint);
            canvas.drawCircle(midX - d2, circleY, rad, this.paintGreen);
            canvas.drawBitmap(this.removeBitmap, this.removeBtmSnapMatrix, this.bitmapPaint);
            canvas.drawBitmap(this.editBitmap, this.editBtmScaleMatrix, this.bitmapPaint);
            canvas.drawBitmap(this.switchBitmap, this.switchBtmScaleMatrix, this.bitmapPaint);
        }
    }

    int isInCircleSnap(float x, float y) {
        float rad = this.actualRadius;
        float touchrad = rad + this.circlePadding;
        float midX = this.rectSnap.left + (this.rectSnap.width() / 2.0f);
        float circleY = this.rectSnap.bottom + rad;
        float d0 = 3.0f * rad;
        float d2 = -3.0f * rad;
        if (((x - (midX - d0)) * (x - (midX - d0))) + ((y - circleY) * (y - circleY)) < touchrad * touchrad) {
            this.viewSelected = true;
            return SNAP_CIRCLE_0;
        } else if (((x - (midX - 0.0f)) * (x - (midX - 0.0f))) + ((y - circleY) * (y - circleY)) < touchrad * touchrad) {
            this.viewSelected = true;
            return SNAP_CIRCLE_1;
        } else if (((x - (midX - d2)) * (x - (midX - d2))) + ((y - circleY) * (y - circleY)) >= touchrad * touchrad) {
            return SNAP_CIRCLE_NONE;
        } else {
            this.viewSelected = true;
            return SNAP_CIRCLE_2;
        }
    }

    public static float getSnapRectPadding(TextData textData) {
        return ((-textData.textPaint.ascent()) + textData.textPaint.descent()) / 4.7f;
    }

    public static void drawSnap(Canvas canvas, TextData textData, float xPos, float yPos, RectF rectSnap, Paint paintSnap, Rect lineBound) {
        canvas.drawRect(rectSnap, paintSnap);
        drawMultiline(canvas, textData.message, xPos, yPos, textData.textPaint, textData, lineBound, null, null);
    }

    public static int getTextHeight(TextData textData) {
        int height = 0;
        for (String line : textData.message.split("\n")) {
            height = (int) (((float) height) + ((-textData.textPaint.ascent()) + textData.textPaint.descent()));
        }
        return height;
    }

    int getTextHeight23() {
        int height = 0;
        Rect bounds = new Rect();
        for (String line : this.textData.message.split("\n")) {
            this.textData.textPaint.getTextBounds(line, 0, line.length(), bounds);
            height += bounds.height();
        }
        return height;
    }

    float resetRectPosition() {
        this.paintBg.setColor(this.textData.getBackgroundColorFinal());
        getHeightAndMaxLength(this.textData, this.lineBound, this.res);
        float maxLength = this.res.x;
        float height = this.res.y;
        this.paddingWidth = this.screenWidth / 15.0f;
        this.paddingHeight = this.screenWidth / 14.0f;
        this.rectText.set(this.textData.xPos - this.paddingWidth, ((this.textData.yPos - ((float) this.textBoundrect.height())) - this.paddingHeight) + height, (this.textData.xPos + maxLength) + this.paddingWidth, this.textData.yPos + this.paddingHeight);
        this.paddingWidth = this.screenWidth / 30.0f;
        this.paddingHeight = this.screenWidth / 30.0f;
        this.rectBg.set(this.textData.xPos - this.paddingWidth, ((this.textData.yPos - ((float) this.textBoundrect.height())) - this.paddingHeight) + height, (this.textData.xPos + maxLength) + this.paddingWidth, (this.textData.yPos + this.paddingHeight) + this.textData.textPaint.descent());
        dashPathReset(this.rectText.width(), this.rectText.height());
        return -height;
    }

    static void getHeightAndMaxLength(TextData textData, Rect lineBound, PointF res) {
        float maxLength = 0.0f;
        float height = 0.0f;
        for (String line : textData.message.split("\n")) {
            height -= (-textData.textPaint.ascent()) + textData.textPaint.descent();
            textData.textPaint.getTextBounds(line, 0, line.length(), lineBound);
            if (((float) lineBound.width()) > maxLength) {
                maxLength = (float) (lineBound.width() + (lineBound.left * 2));
            }
        }
        res.set(maxLength, height + ((-textData.textPaint.ascent()) + textData.textPaint.descent()));
    }

    public static void setBgRect(TextData textData, RectF rrr, Rect lineBound, Rect textBoundrect, float screenWidth) {
        PointF res = new PointF();
        getHeightAndMaxLength(textData, lineBound, res);
        float maxLength = res.x;
        float height = res.y;
        float paddingWidth = screenWidth / 30.0f;
        float paddingHeight = screenWidth / 30.0f;
        float bottomPaddingExtra = textData.textPaint.descent();
        textData.textPaint.getTextBounds(textData.message, 0, textData.message.length(), textBoundrect);
        rrr.set(textData.xPos - paddingWidth, ((textData.yPos - ((float) textBoundrect.height())) - paddingHeight) + height, (textData.xPos + maxLength) + paddingWidth, (textData.yPos + paddingHeight) + bottomPaddingExtra);
    }

    public static void drawMultiline(Canvas canvas, String str, float x, float y, Paint paint, TextData textData, Rect lineBound, RectF rectBg, Paint paintBg) {
        float myX = x;
        float myY = y;
        int maxLength = 0;
        for (String line : str.split("\n")) {
            myY -= (-paint.ascent()) + paint.descent();
            textData.textPaint.getTextBounds(line, 0, line.length(), lineBound);
            if (lineBound.width() > maxLength) {
                maxLength = lineBound.width();
            }
        }
        myY += (-paint.ascent()) + paint.descent();
        Align align = textData.textPaint.getTextAlign();
        if (align == Align.RIGHT) {
            myX += (float) maxLength;
        }
        if (align == Align.CENTER) {
            myX += (float) (maxLength / 2);
        }
        if (!(rectBg == null || paintBg == null)) {
            canvas.drawRect(rectBg, paintBg);
        }
        for (String line2 : str.split("\n")) {
            canvas.drawText(line2, myX, myY, paint);
            myY += (-paint.ascent()) + paint.descent();
        }
    }

    public static int getMaxLength(TextData textData, Rect lineBound, String str) {
        int maxLength = 0;
        for (String line : str.split("\n")) {
            textData.textPaint.getTextBounds(line, 0, line.length(), lineBound);
            if (lineBound.width() > maxLength) {
                maxLength = lineBound.width();
            }
        }
        return maxLength;
    }

    void checkMatrix() {
        this.textData.canvasMatrix.getValues(this.values);
        if (getScale() < 0.5f) {
            this.textData.canvasMatrix.postScale(0.5f, 0.5f, this.pts[0], this.pts[1]);
        }
    }

    public void singleTapped() {
        if (this.singleTapListener != null) {
            this.singleTapListener.onSingleTap(this.textData);
        }
    }

    public void setTextColor(int color) {
        this.textData.textPaint.setColor(color);
        postInvalidate();
    }

    public void setMessage(CharSequence newMessage) {
        if (newMessage.length() == 0) {
            this.textData.message = TextData.defaultMessage;
        } else {
            this.textData.message = newMessage.toString();
        }
        float exRight = this.rectText.right;
        this.rectText.right = (this.rectText.left + this.textData.textPaint.measureText(this.textData.message)) + (2.0f * this.paddingWidth);
        this.scaleBitmapMatrix.postTranslate(this.rectText.right - exRight, 0.0f);
        this.scaleBitmapSwitch.postTranslate(this.rectText.right - exRight, 0.0f);
        postInvalidate();
    }

    public void setNewTextData(TextData textData) {
        this.textData = textData;
        float exRight = this.rectText.right;
        this.rectText.right = (this.rectText.left + textData.textPaint.measureText(textData.message)) + (2.0f * this.paddingWidth);
        this.scaleBitmapMatrix.postTranslate(this.rectText.right - exRight, 0.0f);
        this.scaleBitmapSwitch.postTranslate(this.rectText.right - exRight, 0.0f);
        postInvalidate();
    }

    public boolean isOnRectCheck(float x, float y) {
        if (x <= this.rectText.left || x >= this.rectText.right || y <= this.rectText.top || y >= this.rectText.bottom) {
            return false;
        }
        this.viewSelected = true;
        return true;
    }

    boolean isInCircle(float x, float y) {
        if (((x - this.rectText.right) * (x - this.rectText.right)) + ((y - this.rectText.bottom) * (y - this.rectText.bottom)) >= ((this.actualRadius + this.circlePadding) * (this.actualRadius + this.circlePadding)) / (this.scale * this.scale)) {
            return false;
        }
        this.viewSelected = true;
        return true;
    }

    boolean isInSecondCircle(float x, float y) {
        if (((x - this.rectText.right) * (x - this.rectText.right)) + ((y - this.rectText.top) * (y - this.rectText.top)) >= ((this.actualRadius + this.circlePadding) * (this.actualRadius + this.circlePadding)) / (this.scale * this.scale)) {
            return false;
        }
        this.viewSelected = true;
        return true;
    }

    boolean isOnCross(float x, float y) {
        if (((x - this.rectText.left) * (x - this.rectText.left)) + ((y - this.rectText.top) * (y - this.rectText.top)) >= ((this.actualRadius + this.circlePadding) * (this.actualRadius + this.circlePadding)) / (this.scale * this.scale)) {
            return false;
        }
        this.viewSelected = true;
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
 //   public boolean onTouchEvent(android.view.MotionEvent r28) {
        /*
        r27 = this;
        r20 = r28.getX();
        r21 = r28.getY();
        r0 = r27;
        r0 = r0.textData;
        r22 = r0;
        r22 = r22.getSnapMode();
        if (r22 == 0) goto L_0x0160;
    L_0x0014:
        r22 = r28.getAction();
        r0 = r22;
        r0 = r0 & 255;
        r22 = r0;
        switch(r22) {
            case 0: goto L_0x0033;
            case 1: goto L_0x013f;
            case 2: goto L_0x0111;
            default: goto L_0x0021;
        };
    L_0x0021:
        r27.invalidate();
        r0 = r27;
        r0 = r0.gestureDetector;
        r22 = r0;
        r0 = r22;
        r1 = r28;
        r15 = r0.onTouchEvent(r1);
    L_0x0032:
        return r15;
    L_0x0033:
        r22 = 1;
        r0 = r22;
        r1 = r27;
        r1.downModeSnap = r0;
        r6 = SNAP_CIRCLE_NONE;
        r0 = r27;
        r0 = r0.viewSelected;
        r22 = r0;
        if (r22 == 0) goto L_0x0085;
    L_0x0045:
        r0 = r27;
        r1 = r20;
        r2 = r21;
        r6 = r0.isInCircleSnap(r1, r2);
        r22 = SNAP_CIRCLE_0;
        r0 = r22;
        if (r6 != r0) goto L_0x0064;
    L_0x0055:
        r22 = r27.getContext();
        r0 = r27;
        r1 = r22;
        r2 = r27;
        r0.createDeleteDialog(r1, r2);
        r15 = 1;
        goto L_0x0032;
    L_0x0064:
        r22 = SNAP_CIRCLE_1;
        r0 = r22;
        if (r6 != r0) goto L_0x006f;
    L_0x006a:
        r27.singleTapped();
        r15 = 1;
        goto L_0x0032;
    L_0x006f:
        r22 = SNAP_CIRCLE_2;
        r0 = r22;
        if (r6 != r0) goto L_0x0085;
    L_0x0075:
        r0 = r27;
        r0 = r0.textData;
        r22 = r0;
        r23 = 0;
        r22.setSnapMode(r23);
        r27.invalidate();
        r15 = 1;
        goto L_0x0032;
    L_0x0085:
        r0 = r27;
        r0 = r0.rectSnap;
        r22 = r0;
        r0 = r22;
        r0 = r0.left;
        r22 = r0;
        r22 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1));
        if (r22 <= 0) goto L_0x010f;
    L_0x0095:
        r0 = r27;
        r0 = r0.rectSnap;
        r22 = r0;
        r0 = r22;
        r0 = r0.right;
        r22 = r0;
        r22 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1));
        if (r22 >= 0) goto L_0x010f;
    L_0x00a5:
        r0 = r27;
        r0 = r0.rectSnap;
        r22 = r0;
        r0 = r22;
        r0 = r0.top;
        r22 = r0;
        r22 = (r21 > r22 ? 1 : (r21 == r22 ? 0 : -1));
        if (r22 <= 0) goto L_0x010f;
    L_0x00b5:
        r0 = r27;
        r0 = r0.rectSnap;
        r22 = r0;
        r0 = r22;
        r0 = r0.bottom;
        r22 = r0;
        r22 = (r21 > r22 ? 1 : (r21 == r22 ? 0 : -1));
        if (r22 >= 0) goto L_0x010f;
    L_0x00c5:
        r9 = 1;
    L_0x00c6:
        if (r9 == 0) goto L_0x00d8;
    L_0x00c8:
        r22 = 1;
        r0 = r22;
        r1 = r27;
        r1.onRectSnap = r0;
        r22 = 1;
        r0 = r22;
        r1 = r27;
        r1.viewSelected = r0;
    L_0x00d8:
        r0 = r21;
        r1 = r27;
        r1.previousY = r0;
        r0 = r27;
        r0 = r0.textData;
        r22 = r0;
        r0 = r22;
        r0 = r0.yPosSnap;
        r22 = r0;
        r0 = r22;
        r1 = r27;
        r1.previousYSnap = r0;
        if (r9 != 0) goto L_0x00f8;
    L_0x00f2:
        r22 = SNAP_CIRCLE_NONE;
        r0 = r22;
        if (r6 == r0) goto L_0x0021;
    L_0x00f8:
        r0 = r27;
        r0 = r0.textAndStickerViewSelectedListener;
        r22 = r0;
        if (r22 == 0) goto L_0x0021;
    L_0x0100:
        r0 = r27;
        r0 = r0.textAndStickerViewSelectedListener;
        r22 = r0;
        r0 = r22;
        r1 = r27;
        r0.setSelectedView(r1);
        goto L_0x0021;
    L_0x010f:
        r9 = 0;
        goto L_0x00c6;
    L_0x0111:
        r0 = r27;
        r0 = r0.downModeSnap;
        r22 = r0;
        if (r22 == 0) goto L_0x0021;
    L_0x0119:
        r0 = r27;
        r0 = r0.onRectSnap;
        r22 = r0;
        if (r22 == 0) goto L_0x0021;
    L_0x0121:
        r0 = r27;
        r0 = r0.textData;
        r22 = r0;
        r0 = r27;
        r0 = r0.previousYSnap;
        r23 = r0;
        r23 = r23 + r21;
        r0 = r27;
        r0 = r0.previousY;
        r24 = r0;
        r23 = r23 - r24;
        r0 = r23;
        r1 = r22;
        r1.yPosSnap = r0;
        goto L_0x0021;
    L_0x013f:
        r22 = 0;
        r0 = r22;
        r1 = r27;
        r1.onRectSnap = r0;
        r0 = r27;
        r0 = r0.onDecorateViewTouchUpListener;
        r22 = r0;
        if (r22 == 0) goto L_0x0021;
    L_0x014f:
        r0 = r27;
        r0 = r0.onDecorateViewTouchUpListener;
        r22 = r0;
        r0 = r27;
        r0 = r0.textData;
        r23 = r0;
        r22.onTouchUp(r23);
        goto L_0x0021;
    L_0x0160:
        r0 = r27;
        r0 = r0.mScaleDetector;
        r22 = r0;
        r0 = r22;
        r1 = r28;
        r0.onTouchEvent(r1);
        r0 = r27;
        r0 = r0.mRotationDetector;
        r22 = r0;
        r0 = r22;
        r1 = r28;
        r0.onTouchEvent(r1);
        r22 = r28.getAction();
        r0 = r22;
        r0 = r0 & 255;
        r22 = r0;
        switch(r22) {
            case 0: goto L_0x019d;
            case 1: goto L_0x0799;
            case 2: goto L_0x038c;
            case 3: goto L_0x0187;
            case 4: goto L_0x0187;
            case 5: goto L_0x0187;
            case 6: goto L_0x07f1;
            default: goto L_0x0187;
        };
    L_0x0187:
        r27.postInvalidate();
        r0 = r27;
        r0 = r0.gestureDetector;
        r22 = r0;
        r0 = r22;
        r1 = r28;
        r15 = r0.onTouchEvent(r1);
        r27.postInvalidate();
        goto L_0x0032;
    L_0x019d:
        r22 = 0;
        r0 = r22;
        r1 = r27;
        r1.isOnRect = r0;
        r22 = 0;
        r0 = r22;
        r1 = r27;
        r1.isInCircle = r0;
        r22 = 0;
        r0 = r22;
        r1 = r27;
        r1.downModeSnap = r0;
        r22 = 1;
        r0 = r22;
        r1 = r27;
        r1.isOnTouch = r0;
        r0 = r27;
        r0 = r0.viewSelected;
        r22 = r0;
        r0 = r22;
        r1 = r27;
        r1.savedViewSelected = r0;
        r0 = r27;
        r0 = r0.pts;
        r22 = r0;
        r23 = 0;
        r22[r23] = r20;
        r0 = r27;
        r0 = r0.pts;
        r22 = r0;
        r23 = 1;
        r22[r23] = r21;
        r0 = r27;
        r0 = r0.textData;
        r22 = r0;
        r0 = r22;
        r0 = r0.canvasMatrix;
        r22 = r0;
        r0 = r27;
        r0 = r0.inverse;
        r23 = r0;
        r22.invert(r23);
        r0 = r27;
        r0 = r0.inverse;
        r22 = r0;
        r0 = r27;
        r0 = r0.pts;
        r23 = r0;
        r0 = r27;
        r0 = r0.pts;
        r24 = r0;
        r22.mapPoints(r23, r24);
        r0 = r27;
        r0 = r0.viewSelected;
        r22 = r0;
        if (r22 == 0) goto L_0x0270;
    L_0x020f:
        r0 = r27;
        r0 = r0.pts;
        r22 = r0;
        r23 = 0;
        r22 = r22[r23];
        r0 = r27;
        r0 = r0.pts;
        r23 = r0;
        r24 = 1;
        r23 = r23[r24];
        r0 = r27;
        r1 = r22;
        r2 = r23;
        r22 = r0.isOnCross(r1, r2);
        if (r22 == 0) goto L_0x023f;
    L_0x022f:
        r22 = r27.getContext();
        r0 = r27;
        r1 = r22;
        r2 = r27;
        r0.createDeleteDialog(r1, r2);
        r15 = 1;
        goto L_0x0032;
    L_0x023f:
        r0 = r27;
        r0 = r0.pts;
        r22 = r0;
        r23 = 0;
        r22 = r22[r23];
        r0 = r27;
        r0 = r0.pts;
        r23 = r0;
        r24 = 1;
        r23 = r23[r24];
        r0 = r27;
        r1 = r22;
        r2 = r23;
        r22 = r0.isInSecondCircle(r1, r2);
        if (r22 == 0) goto L_0x0270;
    L_0x025f:
        r0 = r27;
        r0 = r0.textData;
        r22 = r0;
        r23 = 1;
        r22.setSnapMode(r23);
        r27.invalidate();
        r15 = 1;
        goto L_0x0032;
    L_0x0270:
        r0 = r27;
        r0 = r0.pts;
        r22 = r0;
        r23 = 0;
        r22 = r22[r23];
        r0 = r27;
        r0 = r0.pts;
        r23 = r0;
        r24 = 1;
        r23 = r23[r24];
        r0 = r27;
        r1 = r22;
        r2 = r23;
        r22 = r0.isOnRectCheck(r1, r2);
        r0 = r22;
        r1 = r27;
        r1.isOnRect = r0;
        r0 = r27;
        r0 = r0.pts;
        r22 = r0;
        r23 = 0;
        r22 = r22[r23];
        r0 = r27;
        r0 = r0.pts;
        r23 = r0;
        r24 = 1;
        r23 = r23[r24];
        r0 = r27;
        r1 = r22;
        r2 = r23;
        r22 = r0.isInCircle(r1, r2);
        r0 = r22;
        r1 = r27;
        r1.isInCircle = r0;
        r0 = r27;
        r0 = r0.previosPos;
        r22 = r0;
        r0 = r22;
        r1 = r20;
        r2 = r21;
        r0.set(r1, r2);
        r0 = r27;
        r0 = r0.zoomStart;
        r22 = r0;
        r0 = r22;
        r1 = r20;
        r2 = r21;
        r0.set(r1, r2);
        r0 = r27;
        r0 = r0.pts;
        r22 = r0;
        r23 = 0;
        r0 = r27;
        r0 = r0.rectText;
        r24 = r0;
        r24 = r24.centerX();
        r22[r23] = r24;
        r0 = r27;
        r0 = r0.pts;
        r22 = r0;
        r23 = 1;
        r0 = r27;
        r0 = r0.rectText;
        r24 = r0;
        r24 = r24.centerY();
        r22[r23] = r24;
        r0 = r27;
        r0 = r0.textData;
        r22 = r0;
        r0 = r22;
        r0 = r0.canvasMatrix;
        r22 = r0;
        r0 = r27;
        r0 = r0.pts;
        r23 = r0;
        r0 = r27;
        r0 = r0.pts;
        r24 = r0;
        r22.mapPoints(r23, r24);
        r0 = r27;
        r0 = r0.pts;
        r22 = r0;
        r23 = 0;
        r22 = r22[r23];
        r0 = r27;
        r0 = r0.pts;
        r23 = r0;
        r24 = 1;
        r23 = r23[r24];
        r22 = com.lyrebirdstudio.sticker.StickerView.pointToAngle(r20, r21, r22, r23);
        r0 = r22;
        r0 = -r0;
        r22 = r0;
        r0 = r22;
        r0 = (float) r0;
        r22 = r0;
        r0 = r22;
        r1 = r27;
        r1.startAngle = r0;
        r0 = r27;
        r0 = r0.isInCircle;
        r22 = r0;
        if (r22 != 0) goto L_0x0351;
    L_0x0349:
        r0 = r27;
        r0 = r0.isOnRect;
        r22 = r0;
        if (r22 == 0) goto L_0x0366;
    L_0x0351:
        r0 = r27;
        r0 = r0.textAndStickerViewSelectedListener;
        r22 = r0;
        if (r22 == 0) goto L_0x0366;
    L_0x0359:
        r0 = r27;
        r0 = r0.textAndStickerViewSelectedListener;
        r22 = r0;
        r0 = r22;
        r1 = r27;
        r0.setSelectedView(r1);
    L_0x0366:
        r22 = 0;
        r0 = r28;
        r1 = r22;
        r22 = r0.getPointerId(r1);
        r0 = r22;
        r1 = r27;
        r1.mActivePointerId = r0;
        r0 = r27;
        r0 = r0.savedViewSelected;
        r22 = r0;
        if (r22 != 0) goto L_0x0187;
    L_0x037e:
        r22 = 1;
        r0 = r22;
        r1 = r27;
        r1.doubleTouchCall = r0;
        r0 = r27;
        r15 = r0.savedViewSelected;
        goto L_0x0032;
    L_0x038c:
        r0 = r27;
        r0 = r0.downModeSnap;
        r22 = r0;
        if (r22 != 0) goto L_0x0187;
    L_0x0394:
        r0 = r27;
        r0 = r0.isInCircle;
        r22 = r0;
        if (r22 == 0) goto L_0x0735;
    L_0x039c:
        r0 = r27;
        r0 = r0.pts;
        r22 = r0;
        r23 = 0;
        r22 = r22[r23];
        r0 = r27;
        r0 = r0.pts;
        r23 = r0;
        r24 = 1;
        r23 = r23[r24];
        r22 = com.lyrebirdstudio.sticker.StickerView.pointToAngle(r20, r21, r22, r23);
        r0 = r22;
        r0 = -r0;
        r22 = r0;
        r0 = r22;
        r7 = (float) r0;
        r0 = r27;
        r0 = r0.textData;
        r22 = r0;
        r0 = r22;
        r0 = r0.canvasMatrix;
        r22 = r0;
        r0 = r27;
        r1 = r22;
        r16 = r0.getMatrixRotation(r1);
        r22 = 0;
        r22 = (r16 > r22 ? 1 : (r16 == r22 ? 0 : -1));
        if (r22 == 0) goto L_0x03ee;
    L_0x03d6:
        r22 = 1119092736; // 0x42b40000 float:90.0 double:5.529052754E-315;
        r22 = (r16 > r22 ? 1 : (r16 == r22 ? 0 : -1));
        if (r22 == 0) goto L_0x03ee;
    L_0x03dc:
        r22 = 1127481344; // 0x43340000 float:180.0 double:5.570497984E-315;
        r22 = (r16 > r22 ? 1 : (r16 == r22 ? 0 : -1));
        if (r22 == 0) goto L_0x03ee;
    L_0x03e2:
        r22 = -1020002304; // 0xffffffffc3340000 float:-180.0 double:NaN;
        r22 = (r16 > r22 ? 1 : (r16 == r22 ? 0 : -1));
        if (r22 == 0) goto L_0x03ee;
    L_0x03e8:
        r22 = -1028390912; // 0xffffffffc2b40000 float:-90.0 double:NaN;
        r22 = (r16 > r22 ? 1 : (r16 == r22 ? 0 : -1));
        if (r22 != 0) goto L_0x05af;
    L_0x03ee:
        r0 = r27;
        r0 = r0.startAngle;
        r22 = r0;
        r22 = r22 - r7;
        r22 = java.lang.Math.abs(r22);
        r23 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r22 = (r22 > r23 ? 1 : (r22 == r23 ? 0 : -1));
        if (r22 >= 0) goto L_0x05af;
    L_0x0400:
        r22 = 1;
        r0 = r22;
        r1 = r27;
        r1.orthogonal = r0;
    L_0x0408:
        r0 = r27;
        r0 = r0.pts;
        r22 = r0;
        r23 = 0;
        r22 = r22[r23];
        r22 = r20 - r22;
        r0 = r27;
        r0 = r0.pts;
        r23 = r0;
        r24 = 0;
        r23 = r23[r24];
        r23 = r20 - r23;
        r22 = r22 * r23;
        r0 = r27;
        r0 = r0.pts;
        r23 = r0;
        r24 = 1;
        r23 = r23[r24];
        r23 = r21 - r23;
        r0 = r27;
        r0 = r0.pts;
        r24 = r0;
        r25 = 1;
        r24 = r24[r25];
        r24 = r21 - r24;
        r23 = r23 * r24;
        r22 = r22 + r23;
        r0 = r22;
        r0 = (double) r0;
        r22 = r0;
        r22 = java.lang.Math.sqrt(r22);
        r0 = r22;
        r0 = (float) r0;
        r19 = r0;
        r0 = r27;
        r0 = r0.zoomStart;
        r22 = r0;
        r0 = r22;
        r0 = r0.x;
        r22 = r0;
        r0 = r27;
        r0 = r0.pts;
        r23 = r0;
        r24 = 0;
        r23 = r23[r24];
        r22 = r22 - r23;
        r0 = r27;
        r0 = r0.zoomStart;
        r23 = r0;
        r0 = r23;
        r0 = r0.x;
        r23 = r0;
        r0 = r27;
        r0 = r0.pts;
        r24 = r0;
        r25 = 0;
        r24 = r24[r25];
        r23 = r23 - r24;
        r22 = r22 * r23;
        r0 = r27;
        r0 = r0.zoomStart;
        r23 = r0;
        r0 = r23;
        r0 = r0.y;
        r23 = r0;
        r0 = r27;
        r0 = r0.pts;
        r24 = r0;
        r25 = 1;
        r24 = r24[r25];
        r23 = r23 - r24;
        r0 = r27;
        r0 = r0.zoomStart;
        r24 = r0;
        r0 = r24;
        r0 = r0.y;
        r24 = r0;
        r0 = r27;
        r0 = r0.pts;
        r25 = r0;
        r26 = 1;
        r25 = r25[r26];
        r24 = r24 - r25;
        r23 = r23 * r24;
        r22 = r22 + r23;
        r0 = r22;
        r0 = (double) r0;
        r22 = r0;
        r22 = java.lang.Math.sqrt(r22);
        r0 = r22;
        r0 = (float) r0;
        r17 = r0;
        r18 = r19 / r17;
        r22 = r27.getScale();
        r0 = r22;
        r1 = r27;
        r1.scale = r0;
        r0 = r27;
        r0 = r0.scale;
        r22 = r0;
        r23 = 1061997773; // 0x3f4ccccd float:0.8 double:5.246966156E-315;
        r22 = (r22 > r23 ? 1 : (r22 == r23 ? 0 : -1));
        if (r22 >= 0) goto L_0x04ec;
    L_0x04d9:
        r0 = r27;
        r0 = r0.scale;
        r22 = r0;
        r23 = 1061997773; // 0x3f4ccccd float:0.8 double:5.246966156E-315;
        r22 = (r22 > r23 ? 1 : (r22 == r23 ? 0 : -1));
        if (r22 >= 0) goto L_0x0187;
    L_0x04e6:
        r22 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r22 = (r18 > r22 ? 1 : (r18 == r22 ? 0 : -1));
        if (r22 <= 0) goto L_0x0187;
    L_0x04ec:
        r0 = r27;
        r0 = r0.textData;
        r22 = r0;
        r0 = r22;
        r0 = r0.canvasMatrix;
        r22 = r0;
        r0 = r27;
        r0 = r0.pts;
        r23 = r0;
        r24 = 0;
        r23 = r23[r24];
        r0 = r27;
        r0 = r0.pts;
        r24 = r0;
        r25 = 1;
        r24 = r24[r25];
        r0 = r22;
        r1 = r18;
        r2 = r18;
        r3 = r23;
        r4 = r24;
        r0.postScale(r1, r2, r3, r4);
        r0 = r27;
        r0 = r0.zoomStart;
        r22 = r0;
        r0 = r22;
        r1 = r20;
        r2 = r21;
        r0.set(r1, r2);
        r22 = r27.getScale();
        r0 = r22;
        r1 = r27;
        r1.scale = r0;
        r0 = r27;
        r0 = r0.scaleBitmapMatrix;
        r22 = r0;
        r23 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r23 = r23 / r18;
        r24 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r24 = r24 / r18;
        r0 = r27;
        r0 = r0.rectText;
        r25 = r0;
        r0 = r25;
        r0 = r0.right;
        r25 = r0;
        r0 = r27;
        r0 = r0.rectText;
        r26 = r0;
        r0 = r26;
        r0 = r0.bottom;
        r26 = r0;
        r22.postScale(r23, r24, r25, r26);
        r0 = r27;
        r0 = r0.removeBitmapMatrix;
        r22 = r0;
        r23 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r23 = r23 / r18;
        r24 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r24 = r24 / r18;
        r0 = r27;
        r0 = r0.rectText;
        r25 = r0;
        r0 = r25;
        r0 = r0.left;
        r25 = r0;
        r0 = r27;
        r0 = r0.rectText;
        r26 = r0;
        r0 = r26;
        r0 = r0.top;
        r26 = r0;
        r22.postScale(r23, r24, r25, r26);
        r0 = r27;
        r0 = r0.scaleBitmapSwitch;
        r22 = r0;
        r23 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r23 = r23 / r18;
        r24 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r24 = r24 / r18;
        r0 = r27;
        r0 = r0.rectText;
        r25 = r0;
        r0 = r25;
        r0 = r0.right;
        r25 = r0;
        r0 = r27;
        r0 = r0.rectText;
        r26 = r0;
        r0 = r26;
        r0 = r0.top;
        r26 = r0;
        r22.postScale(r23, r24, r25, r26);
        goto L_0x0187;
    L_0x05af:
        r0 = r27;
        r0 = r0.startAngle;
        r22 = r0;
        r22 = r16 - r22;
        r22 = r22 + r7;
        r22 = java.lang.Math.abs(r22);
        r23 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r22 = (r22 > r23 ? 1 : (r22 == r23 ? 0 : -1));
        if (r22 >= 0) goto L_0x0620;
    L_0x05c3:
        r0 = r27;
        r0 = r0.startAngle;
        r22 = r0;
        r7 = r22 - r16;
        r22 = 1;
        r0 = r22;
        r1 = r27;
        r1.orthogonal = r0;
        r22 = "CanvasTextView";
        r23 = new java.lang.StringBuilder;
        r23.<init>();
        r24 = "aaaaa ";
        r23 = r23.append(r24);
        r24 = java.lang.Float.toString(r16);
        r23 = r23.append(r24);
        r23 = r23.toString();
        android.util.Log.d(r22, r23);
    L_0x05ef:
        r0 = r27;
        r0 = r0.textData;
        r22 = r0;
        r0 = r22;
        r0 = r0.canvasMatrix;
        r22 = r0;
        r0 = r27;
        r0 = r0.startAngle;
        r23 = r0;
        r23 = r23 - r7;
        r0 = r27;
        r0 = r0.pts;
        r24 = r0;
        r25 = 0;
        r24 = r24[r25];
        r0 = r27;
        r0 = r0.pts;
        r25 = r0;
        r26 = 1;
        r25 = r25[r26];
        r22.postRotate(r23, r24, r25);
        r0 = r27;
        r0.startAngle = r7;
        goto L_0x0408;
    L_0x0620:
        r22 = 1119092736; // 0x42b40000 float:90.0 double:5.529052754E-315;
        r0 = r27;
        r0 = r0.startAngle;
        r23 = r0;
        r23 = r16 - r23;
        r23 = r23 + r7;
        r22 = r22 - r23;
        r22 = java.lang.Math.abs(r22);
        r23 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r22 = (r22 > r23 ? 1 : (r22 == r23 ? 0 : -1));
        if (r22 >= 0) goto L_0x0669;
    L_0x0638:
        r22 = 1119092736; // 0x42b40000 float:90.0 double:5.529052754E-315;
        r0 = r27;
        r0 = r0.startAngle;
        r23 = r0;
        r22 = r22 + r23;
        r7 = r22 - r16;
        r22 = 1;
        r0 = r22;
        r1 = r27;
        r1.orthogonal = r0;
        r22 = "CanvasTextView";
        r23 = new java.lang.StringBuilder;
        r23.<init>();
        r24 = "bbbbb ";
        r23 = r23.append(r24);
        r24 = java.lang.Float.toString(r16);
        r23 = r23.append(r24);
        r23 = r23.toString();
        android.util.Log.d(r22, r23);
        goto L_0x05ef;
    L_0x0669:
        r22 = 1127481344; // 0x43340000 float:180.0 double:5.570497984E-315;
        r0 = r27;
        r0 = r0.startAngle;
        r23 = r0;
        r23 = r16 - r23;
        r23 = r23 + r7;
        r22 = r22 - r23;
        r22 = java.lang.Math.abs(r22);
        r23 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r22 = (r22 > r23 ? 1 : (r22 == r23 ? 0 : -1));
        if (r22 >= 0) goto L_0x06b3;
    L_0x0681:
        r22 = 1127481344; // 0x43340000 float:180.0 double:5.570497984E-315;
        r0 = r27;
        r0 = r0.startAngle;
        r23 = r0;
        r22 = r22 + r23;
        r7 = r22 - r16;
        r22 = 1;
        r0 = r22;
        r1 = r27;
        r1.orthogonal = r0;
        r22 = "CanvasTextView";
        r23 = new java.lang.StringBuilder;
        r23.<init>();
        r24 = "cccc ";
        r23 = r23.append(r24);
        r24 = java.lang.Float.toString(r16);
        r23 = r23.append(r24);
        r23 = r23.toString();
        android.util.Log.d(r22, r23);
        goto L_0x05ef;
    L_0x06b3:
        r22 = -1020002304; // 0xffffffffc3340000 float:-180.0 double:NaN;
        r0 = r27;
        r0 = r0.startAngle;
        r23 = r0;
        r23 = r16 - r23;
        r23 = r23 + r7;
        r22 = r22 - r23;
        r22 = java.lang.Math.abs(r22);
        r23 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r22 = (r22 > r23 ? 1 : (r22 == r23 ? 0 : -1));
        if (r22 >= 0) goto L_0x06e1;
    L_0x06cb:
        r22 = -1020002304; // 0xffffffffc3340000 float:-180.0 double:NaN;
        r0 = r27;
        r0 = r0.startAngle;
        r23 = r0;
        r22 = r22 + r23;
        r7 = r22 - r16;
        r22 = 1;
        r0 = r22;
        r1 = r27;
        r1.orthogonal = r0;
        goto L_0x05ef;
    L_0x06e1:
        r22 = -1028390912; // 0xffffffffc2b40000 float:-90.0 double:NaN;
        r0 = r27;
        r0 = r0.startAngle;
        r23 = r0;
        r23 = r16 - r23;
        r23 = r23 + r7;
        r22 = r22 - r23;
        r22 = java.lang.Math.abs(r22);
        r23 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r22 = (r22 > r23 ? 1 : (r22 == r23 ? 0 : -1));
        if (r22 >= 0) goto L_0x072b;
    L_0x06f9:
        r22 = -1028390912; // 0xffffffffc2b40000 float:-90.0 double:NaN;
        r0 = r27;
        r0 = r0.startAngle;
        r23 = r0;
        r22 = r22 + r23;
        r7 = r22 - r16;
        r22 = 1;
        r0 = r22;
        r1 = r27;
        r1.orthogonal = r0;
        r22 = "CanvasTextView";
        r23 = new java.lang.StringBuilder;
        r23.<init>();
        r24 = "dddd ";
        r23 = r23.append(r24);
        r24 = java.lang.Float.toString(r16);
        r23 = r23.append(r24);
        r23 = r23.toString();
        android.util.Log.d(r22, r23);
        goto L_0x05ef;
    L_0x072b:
        r22 = 0;
        r0 = r22;
        r1 = r27;
        r1.orthogonal = r0;
        goto L_0x05ef;
    L_0x0735:
        r0 = r27;
        r0 = r0.isOnRect;
        r22 = r0;
        if (r22 == 0) goto L_0x0187;
    L_0x073d:
        r0 = r27;
        r0 = r0.mActivePointerId;
        r22 = r0;
        r0 = r28;
        r1 = r22;
        r14 = r0.findPointerIndex(r1);
        if (r14 < 0) goto L_0x0187;
    L_0x074d:
        r22 = r28.getPointerCount();
        r0 = r22;
        if (r14 >= r0) goto L_0x0187;
    L_0x0755:
        r0 = r28;
        r10 = r0.getX(r14);
        r0 = r28;
        r11 = r0.getY(r14);
        r0 = r27;
        r0 = r0.textData;
        r22 = r0;
        r0 = r22;
        r0 = r0.canvasMatrix;
        r22 = r0;
        r0 = r27;
        r0 = r0.previosPos;
        r23 = r0;
        r0 = r23;
        r0 = r0.x;
        r23 = r0;
        r23 = r10 - r23;
        r0 = r27;
        r0 = r0.previosPos;
        r24 = r0;
        r0 = r24;
        r0 = r0.y;
        r24 = r0;
        r24 = r11 - r24;
        r22.postTranslate(r23, r24);
        r0 = r27;
        r0 = r0.previosPos;
        r22 = r0;
        r0 = r22;
        r0.set(r10, r11);
        goto L_0x0187;
    L_0x0799:
        r8 = new android.os.Handler;
        r8.<init>();
        r22 = new com.lyrebirdstudio.canvastext.CanvasTextView$1;
        r0 = r22;
        r1 = r27;
        r0.<init>();
        r24 = 100;
        r0 = r22;
        r1 = r24;
        r8.postDelayed(r0, r1);
        r22 = 0;
        r0 = r22;
        r1 = r27;
        r1.startAngleGesture = r0;
        r22 = 0;
        r0 = r22;
        r1 = r27;
        r1.orthogonal = r0;
        r22 = 0;
        r0 = r22;
        r1 = r27;
        r1.isOnTouch = r0;
        r22 = 0;
        r0 = r22;
        r1 = r27;
        r1.isOnRect = r0;
        r0 = r27;
        r0 = r0.onDecorateViewTouchUpListener;
        r22 = r0;
        if (r22 == 0) goto L_0x07e7;
    L_0x07d8:
        r0 = r27;
        r0 = r0.onDecorateViewTouchUpListener;
        r22 = r0;
        r0 = r27;
        r0 = r0.textData;
        r23 = r0;
        r22.onTouchUp(r23);
    L_0x07e7:
        r22 = -1;
        r0 = r22;
        r1 = r27;
        r1.mActivePointerId = r0;
        goto L_0x0187;
    L_0x07f1:
        r22 = 0;
        r0 = r22;
        r1 = r27;
        r1.startAngleGesture = r0;
        r22 = r28.getAction();
        r23 = 65280; // 0xff00 float:9.1477E-41 double:3.22526E-319;
        r22 = r22 & r23;
        r14 = r22 >> 8;
        r0 = r28;
        r13 = r0.getPointerId(r14);
        r0 = r27;
        r0 = r0.mActivePointerId;
        r22 = r0;
        r0 = r22;
        if (r13 != r0) goto L_0x0187;
    L_0x0814:
        if (r14 != 0) goto L_0x0844;
    L_0x0816:
        r12 = 1;
    L_0x0817:
        if (r12 < 0) goto L_0x0187;
    L_0x0819:
        r22 = r28.getPointerCount();
        r0 = r22;
        if (r12 >= r0) goto L_0x0187;
    L_0x0821:
        r0 = r27;
        r0 = r0.previosPos;
        r22 = r0;
        r0 = r28;
        r23 = r0.getX(r12);
        r0 = r28;
        r24 = r0.getY(r12);
        r22.set(r23, r24);
        r0 = r28;
        r22 = r0.getPointerId(r12);
        r0 = r22;
        r1 = r27;
        r1.mActivePointerId = r0;
        goto L_0x0187;
    L_0x0844:
        r12 = 0;
        goto L_0x0817;
        */
       // throw new UnsupportedOperationException("Method not decompiled: com.lyrebirdstudio.canvastext.CanvasTextView.onTouchEvent(android.view.MotionEvent):boolean");
   // }


    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final float x = motionEvent.getX();
        final float y = motionEvent.getY();
        if (this.textData.getSnapMode()) {
            switch (motionEvent.getAction() & 0xFF) {
                case 0: {
                    this.downModeSnap = true;
                    int snap_CIRCLE_NONE = CanvasTextView.SNAP_CIRCLE_NONE;
                    if (this.viewSelected) {
                        final int inCircleSnap = this.isInCircleSnap(x, y);
                        if (inCircleSnap == CanvasTextView.SNAP_CIRCLE_0) {
                            this.createDeleteDialog(this.getContext(), this);
                            return true;
                        }
                        if (inCircleSnap == CanvasTextView.SNAP_CIRCLE_1) {
                            this.singleTapped();
                            return true;
                        }
                        if ((snap_CIRCLE_NONE = inCircleSnap) == CanvasTextView.SNAP_CIRCLE_2) {
                            this.textData.setSnapMode(false);
                            this.invalidate();
                            return true;
                        }
                    }
                    boolean b;
                    if (x > this.rectSnap.left && x < this.rectSnap.right && y > this.rectSnap.top && y < this.rectSnap.bottom) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    if (b) {
                        this.onRectSnap = true;
                        this.viewSelected = true;
                    }
                    this.previousY = y;
                    this.previousYSnap = this.textData.yPosSnap;
                    if ((b || snap_CIRCLE_NONE != CanvasTextView.SNAP_CIRCLE_NONE) && this.textAndStickerViewSelectedListener != null) {
                        this.textAndStickerViewSelectedListener.setSelectedView(this);
                        break;
                    }
                    break;
                }
                case 2: {
                    if (this.downModeSnap && this.onRectSnap) {
                        this.textData.yPosSnap = this.previousYSnap + y - this.previousY;
                        break;
                    }
                    break;
                }
                case 1: {
                    this.onRectSnap = false;
                    if (this.onDecorateViewTouchUpListener != null) {
                        this.onDecorateViewTouchUpListener.onTouchUp(this.textData);
                        break;
                    }
                    break;
                }
            }
            this.invalidate();
            return this.gestureDetector.onTouchEvent(motionEvent);
        }
        this.mScaleDetector.onTouchEvent(motionEvent);
        this.mRotationDetector.onTouchEvent(motionEvent);
        switch (motionEvent.getAction() & 0xFF) {
            case 0: {
                this.isOnRect = false;
                this.isInCircle = false;
                this.downModeSnap = false;
                this.isOnTouch = true;
                this.savedViewSelected = this.viewSelected;
                this.pts[0] = x;
                this.pts[1] = y;
                this.textData.canvasMatrix.invert(this.inverse);
                this.inverse.mapPoints(this.pts, this.pts);
                if (this.viewSelected) {
                    if (this.isOnCross(this.pts[0], this.pts[1])) {
                        this.createDeleteDialog(this.getContext(), this);
                        return true;
                    }
                    if (this.isInSecondCircle(this.pts[0], this.pts[1])) {
                        this.textData.setSnapMode(true);
                        this.invalidate();
                        return true;
                    }
                }
                this.isOnRect = this.isOnRectCheck(this.pts[0], this.pts[1]);
                this.isInCircle = this.isInCircle(this.pts[0], this.pts[1]);
                this.previosPos.set(x, y);
                this.zoomStart.set(x, y);
                this.pts[0] = this.rectText.centerX();
                this.pts[1] = this.rectText.centerY();
                this.textData.canvasMatrix.mapPoints(this.pts, this.pts);
                this.startAngle = -StickerView.pointToAngle(x, y, this.pts[0], this.pts[1]);
                if ((this.isInCircle || this.isOnRect) && this.textAndStickerViewSelectedListener != null) {
                    this.textAndStickerViewSelectedListener.setSelectedView(this);
                }
                this.mActivePointerId = motionEvent.getPointerId(0);
                if (!this.savedViewSelected) {
                    this.doubleTouchCall = true;
                    return this.savedViewSelected;
                }
                break;
            }
            case 2: {
                if (this.downModeSnap) {
                    break;
                }
                if (this.isInCircle) {
                    float startAngle = -StickerView.pointToAngle(x, y, this.pts[0], this.pts[1]);
                    final float matrixRotation = this.getMatrixRotation(this.textData.canvasMatrix);
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
                        this.textData.canvasMatrix.postRotate(this.startAngle - startAngle, this.pts[0], this.pts[1]);
                        this.startAngle = startAngle;
                    }
                    final float n = (float) Math.sqrt((x - this.pts[0]) * (x - this.pts[0]) + (y - this.pts[1]) * (y - this.pts[1])) / (float) Math.sqrt((this.zoomStart.x - this.pts[0]) * (this.zoomStart.x - this.pts[0]) + (this.zoomStart.y - this.pts[1]) * (this.zoomStart.y - this.pts[1]));
                    this.scale = this.getScale();
                    if (this.scale >= 0.8f || (this.scale < 0.8f && n > 1.0f)) {
                        this.textData.canvasMatrix.postScale(n, n, this.pts[0], this.pts[1]);
                        this.zoomStart.set(x, y);
                        this.scale = this.getScale();
                        this.scaleBitmapMatrix.postScale(1.0f / n, 1.0f / n, this.rectText.right, this.rectText.bottom);
                        this.removeBitmapMatrix.postScale(1.0f / n, 1.0f / n, this.rectText.left, this.rectText.top);
                        this.scaleBitmapSwitch.postScale(1.0f / n, 1.0f / n, this.rectText.right, this.rectText.top);
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
                        this.textData.canvasMatrix.postTranslate(x2 - this.previosPos.x, y2 - this.previosPos.y);
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
                        CanvasTextView.this.doubleTouchCall = false;
                    }
                }, 100L);
                this.startAngleGesture = 0.0f;
                this.orthogonal = false;
                this.isOnTouch = false;
                this.isOnRect = false;
                if (this.onDecorateViewTouchUpListener != null) {
                    this.onDecorateViewTouchUpListener.onTouchUp(this.textData);
                }
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
        this.postInvalidate();
        final boolean onTouchEvent = this.gestureDetector.onTouchEvent(motionEvent);
        this.postInvalidate();
        return onTouchEvent;
    }
    
    
    
    float getScale() {
        this.textData.canvasMatrix.getValues(this.values);
        float scalex = this.values[0];
        float skewy = this.values[3];
        return (float) Math.sqrt((double) ((scalex * scalex) + (skewy * skewy)));
    }

    float getMatrixRotation(Matrix matrix) {
        matrix.getValues(this.f768v);
        return (float) Math.round(Math.atan2((double) this.f768v[1], (double) this.f768v[0]) * 57.29577951308232d);
    }

    public float containsScore(float xx, float yy) {
        if (this.textData.getSnapMode()) {
            return (float) -2;
        }
        this.pts[0] = xx;
        this.pts[1] = yy;
        this.textData.canvasMatrix.invert(this.inverse);
        this.inverse.mapPoints(this.pts, this.pts);
        float x = this.pts[0];
        float y = this.pts[1];
        RectF maskRect = this.rectText;
        if (x >= maskRect.left && x <= maskRect.right && y >= maskRect.top && y <= maskRect.bottom) {
            float contain = ((x - maskRect.centerX()) * (x - maskRect.centerX())) + ((y - maskRect.centerY()) * (y - maskRect.centerY()));
            float hypotenus = (maskRect.width() * maskRect.width()) + (maskRect.height() * maskRect.height());
            if (contain > 0.0f) {
                return hypotenus / contain;
            }
        }
        return (float) -2;
    }
}
