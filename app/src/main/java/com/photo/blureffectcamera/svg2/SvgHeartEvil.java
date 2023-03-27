package com.photo.blureffectcamera.svg2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;

import com.photo.blureffectcamera.svg.Svg;

public class SvgHeartEvil extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f1100m;
    private static float od;
    private static final Paint f1101p;
    private static final Paint ps;
    private static final Path f1102t;

    static {
        f1101p = new Paint();
        ps = new Paint();
        f1102t = new Path();
        f1100m = new Matrix();
        cf = null;
    }

    public void drawStroke(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        isStroke = true;
        draw(c, w, h, dx, dy, clearMode);
        isStroke = false;
    }

    public static void setColorTint(int color) {
        cf = new PorterDuffColorFilter(color, Mode.SRC_IN);
    }

    public static void clearColorTint(int color) {
        cf = null;
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 512.0f ? w / 512.0f : h / 512.0f;
        m871r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f1100m.reset();
        f1100m.setScale(od * 11.28f, od * 11.28f);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.0f, 0.01f * od);
        c.save();
        c.save();
        c.save();
        f1101p.setColor(Color.parseColor("#000000"));
        f1102t.reset();
        f1102t.moveTo(39.69f, 26.02f);
        f1102t.cubicTo(39.55f, 25.74f, 39.26f, 25.56f, 38.94f, 25.55f);
        f1102t.cubicTo(38.63f, 25.55f, 38.34f, 25.73f, 38.2f, 26.02f);
        f1102t.cubicTo(36.39f, 29.74f, 33.1f, 31.36f, 30.76f, 32.05f);
        f1102t.cubicTo(30.42f, 32.15f, 30.18f, 32.46f, 30.17f, 32.82f);
        f1102t.cubicTo(30.17f, 33.18f, 30.39f, 33.51f, 30.73f, 33.62f);
        f1102t.cubicTo(32.38f, 34.18f, 33.76f, 34.43f, 34.85f, 34.54f);
        f1102t.cubicTo(34.47f, 35.15f, 33.91f, 35.88f, 33.08f, 36.7f);
        f1102t.cubicTo(30.92f, 38.84f, 23.62f, 41.78f, 18.7f, 41.37f);
        f1102t.cubicTo(17.63f, 41.28f, 16.23f, 40.98f, 15.58f, 40.08f);
        f1102t.cubicTo(14.9f, 39.13f, 14.69f, 38.2f, 14.92f, 37.15f);
        f1102t.cubicTo(15.37f, 35.07f, 17.4f, 32.97f, 19.33f, 31.41f);
        f1102t.cubicTo(20.1f, 32.17f, 20.78f, 32.85f, 21.32f, 33.39f);
        f1102t.cubicTo(22.06f, 34.13f, 23.26f, 34.13f, 24.0f, 33.39f);
        f1102t.cubicTo(27.68f, 29.73f, 37.66f, 19.8f, 37.86f, 19.6f);
        f1102t.cubicTo(40.13f, 17.32f, 41.17f, 14.28f, 40.98f, 11.3f);
        f1102t.cubicTo(40.82f, 11.42f, 40.65f, 11.51f, 40.46f, 11.57f);
        f1102t.cubicTo(39.76f, 11.8f, 39.07f, 11.92f, 38.4f, 11.92f);
        f1102t.cubicTo(34.52f, 11.92f, 32.46f, 8.25f, 31.86f, 6.08f);
        f1102t.cubicTo(31.56f, 5.02f, 32.18f, 3.92f, 33.24f, 3.62f);
        f1102t.cubicTo(34.07f, 3.38f, 34.81f, 3.0f, 35.46f, 2.57f);
        f1102t.cubicTo(31.38f, 0.3f, 26.14f, 0.89f, 22.66f, 4.34f);
        f1102t.cubicTo(19.18f, 0.89f, 13.94f, 0.3f, 9.86f, 2.57f);
        f1102t.cubicTo(10.52f, 3.0f, 11.25f, 3.38f, 12.09f, 3.62f);
        f1102t.cubicTo(13.14f, 3.92f, 13.76f, 5.02f, 13.46f, 6.08f);
        f1102t.cubicTo(12.86f, 8.25f, 10.8f, 11.92f, 6.93f, 11.92f);
        f1102t.cubicTo(6.25f, 11.92f, 5.56f, 11.8f, 4.86f, 11.57f);
        f1102t.cubicTo(4.67f, 11.51f, 4.5f, 11.42f, 4.34f, 11.3f);
        f1102t.cubicTo(4.15f, 14.28f, 5.19f, 17.32f, 7.46f, 19.6f);
        f1102t.cubicTo(7.6f, 19.74f, 12.33f, 24.44f, 16.48f, 28.57f);
        f1102t.cubicTo(14.21f, 30.46f, 11.7f, 33.16f, 11.02f, 36.28f);
        f1102t.cubicTo(10.54f, 38.43f, 11.0f, 40.55f, 12.33f, 42.41f);
        f1102t.cubicTo(13.56f, 44.13f, 15.59f, 45.12f, 18.36f, 45.35f);
        f1102t.cubicTo(18.77f, 45.39f, 19.2f, 45.4f, 19.63f, 45.4f);
        f1102t.cubicTo(25.47f, 45.4f, 33.04f, 42.38f, 35.9f, 39.54f);
        f1102t.cubicTo(37.13f, 38.32f, 37.96f, 37.19f, 38.52f, 36.19f);
        f1102t.cubicTo(39.28f, 37.55f, 39.96f, 38.99f, 40.46f, 40.13f);
        f1102t.cubicTo(40.6f, 40.46f, 40.95f, 40.66f, 41.3f, 40.62f);
        f1102t.cubicTo(41.66f, 40.58f, 41.95f, 40.31f, 42.02f, 39.96f);
        f1102t.cubicTo(43.13f, 34.27f, 40.97f, 28.68f, 39.69f, 26.02f);
        f1102t.transform(f1100m);
        if (clearMode) {
            f1101p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1102t, ps);
        } else {
            c.drawPath(f1102t, f1101p);
            c.drawPath(f1102t, ps);
        }
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1101p.setColor(Color.parseColor("#000000"));
        c.save();
        f1102t.reset();
        f1102t.moveTo(5.48f, 9.67f);
        f1102t.cubicTo(6.0f, 9.84f, 6.48f, 9.92f, 6.93f, 9.92f);
        f1102t.cubicTo(10.32f, 9.92f, 11.54f, 5.54f, 11.54f, 5.54f);
        f1102t.cubicTo(8.19f, 4.59f, 6.09f, 2.04f, 5.03f, 0.38f);
        f1102t.cubicTo(4.88f, 0.14f, 4.61f, 0.0f, 4.33f, 0.0f);
        f1102t.cubicTo(4.3f, 0.0f, 4.26f, 0.0f, 4.22f, 0.01f);
        f1102t.cubicTo(3.9f, 0.05f, 3.63f, 0.28f, 3.54f, 0.59f);
        f1102t.cubicTo(1.81f, 6.41f, 5.48f, 9.67f, 5.48f, 9.67f);
        f1102t.transform(f1100m);
        if (clearMode) {
            f1101p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1102t, ps);
        } else {
            c.drawPath(f1102t, f1101p);
            c.drawPath(f1102t, ps);
        }
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        f1102t.reset();
        f1102t.moveTo(38.4f, 9.92f);
        f1102t.cubicTo(38.84f, 9.92f, 39.32f, 9.84f, 39.84f, 9.67f);
        f1102t.cubicTo(39.84f, 9.67f, 43.52f, 6.41f, 41.78f, 0.59f);
        f1102t.cubicTo(41.69f, 0.28f, 41.43f, 0.05f, 41.1f, 0.01f);
        f1102t.cubicTo(41.06f, 0.0f, 41.03f, 0.0f, 40.99f, 0.0f);
        f1102t.cubicTo(40.71f, 0.0f, 40.45f, 0.14f, 40.3f, 0.38f);
        f1102t.cubicTo(39.24f, 2.04f, 37.14f, 4.59f, 33.79f, 5.54f);
        f1102t.cubicTo(33.79f, 5.54f, 35.01f, 9.92f, 38.4f, 9.92f);
        f1102t.transform(f1100m);
        if (clearMode) {
            f1101p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1102t, ps);
        } else {
            c.drawPath(f1102t, f1101p);
            c.drawPath(f1102t, ps);
        }
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1101p.setColor(Color.parseColor("#000000"));
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1101p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m871r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m871r(new Integer[0]);
        c.restore();
    }

    private static void m871r(Integer... o) {
        f1101p.reset();
        ps.reset();
        if (cf != null) {
            f1101p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1101p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1101p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f1101p.setColor(Color.parseColor("#000000"));
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_BACKGROUND /*1*/:
//                    ps.setStrokeJoin(Join.MITER);
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_BLUR /*2*/:
//                    ps.setStrokeMiter(4.0f * od);
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_FRAME /*3*/:
//                    ps.setStrokeCap(Cap.BUTT);
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_FX /*4*/:
//                    ps.setColor(Color.argb(0, 0, 0, 0));
//                    break;
//                default:
//                    break;
            }
        }
    }
}
