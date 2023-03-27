package com.photo.blureffectcamera.collagelib;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

public class CurvedAndTiled  extends Drawable {

    private final float mCornerRadius;
    private final RectF mRect = new RectF();
    private final BitmapShader mBitmapShader;
    private final Paint mTilePaint;

    public CurvedAndTiled(
            Bitmap bitmap,
            float cornerRadius) {
        mCornerRadius = cornerRadius;

        mBitmapShader = new BitmapShader(bitmap,
                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
      

        mTilePaint = new Paint();
        mTilePaint.setAntiAlias(true);
        mTilePaint.setShader(mBitmapShader);             
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mRect.set(0, 0, bounds.width(), bounds.height());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRoundRect(mRect, mCornerRadius, mCornerRadius, mTilePaint);           
    }
    
  public Bitmap getBitmap(int width, int height) {
    	 mRect.set(0, 0, width, height);
    	Bitmap bitmap = Bitmap.createBitmap((int)mRect.width()		, (int)mRect.height(), Bitmap.Config.ARGB_8888);
    	Canvas canvas = new Canvas(bitmap);
      	canvas.drawRoundRect(mRect, mCornerRadius, mCornerRadius, mTilePaint); 
    	return  bitmap;
	}
    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setAlpha(int alpha) {
        mTilePaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mTilePaint.setColorFilter(cf);
    }       
}