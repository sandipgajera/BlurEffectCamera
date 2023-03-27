package com.photo.blureffectcamera.tiltshift;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class TiltView extends View {
    private static final String TAG;
    Bitmap bitmap;
    Bitmap bitmapBlur;
    int f540h;
    Paint paint;
    float scale;
    float screenHeight;
    float screenWidth;
    TiltHelper tiltHelper;
    int viewHeight;
    int viewWidth;
    int f541w;

    /* renamed from: com.lyrebirdstudio.tiltshift.TiltView.1 */
    class C07821 implements Runnable {
        C07821() {
        }

        public void run() {
            TiltView.this.tiltHelper.startAnimator();
        }
    }

    static {
        TAG = TiltView.class.getSimpleName();
    }

    public TiltView(Context context, Bitmap btm, Bitmap blur, int scrW, int scrH, TiltContext tc) {
        super(context);
        this.paint = new Paint(1);
        this.scale = 1.0f;
        if (context != null && btm != null) {
            this.bitmap = btm;
            this.bitmapBlur = blur;
            this.f541w = this.bitmap.getWidth();
            this.f540h = this.bitmap.getHeight();
            this.tiltHelper = new TiltHelper(this, this.bitmapBlur, tc, this.f541w, this.f540h);
            this.screenWidth = (float) scrW;
            this.screenHeight = (float) scrH;
            this.scale = Math.min(this.screenWidth / ((float) this.f541w), this.screenHeight / ((float) this.f540h));
            this.viewWidth = (int) (this.scale * ((float) this.f541w));
            this.viewHeight = (int) (this.scale * ((float) this.f540h));
            this.paint.setFilterBitmap(true);
            post(new C07821());
        }
    }

    public void onDraw(Canvas canvas) {
        canvas.scale(this.scale, this.scale);
        canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, this.paint);
        this.tiltHelper.drawTiltShift(canvas, this.bitmapBlur, this.f541w, this.f540h);
    }

    @SuppressLint("WrongConstant")
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int myWidth = (int) (((double) MeasureSpec.getSize(heightMeasureSpec)) * 0.5d);
        super.onMeasure(MeasureSpec.makeMeasureSpec(this.viewWidth, 1073741824), MeasureSpec.makeMeasureSpec(this.viewHeight, 1073741824));
    }

    public boolean onTouchEvent(MotionEvent event) {
        return this.tiltHelper.onTouchEvent(event);
    }
}
