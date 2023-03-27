package com.photo.blureffectcamera.svg;

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

public class SvgHeartSquare extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f953m;
    private static float od;
    private static final Paint f954p;
    private static final Paint ps;
    private static final Path f955t;

    static {
        f954p = new Paint();
        ps = new Paint();
        f955t = new Path();
        f953m = new Matrix();
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
        m822r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f953m.reset();
        f953m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.72f, 1.72f);
        c.save();
        c.save();
        c.save();
        f954p.setColor(Color.parseColor("#000000"));
        f955t.reset();
        f955t.moveTo(263.54f, 0.0f);
        f955t.lineTo(33.47f, 0.0f);
        f955t.cubicTo(15.01f, 0.0f, 0.0f, 15.01f, 0.0f, 33.47f);
        f955t.lineTo(0.0f, 263.53f);
        f955t.cubicTo(0.0f, 281.99f, 15.01f, 297.0f, 33.47f, 297.0f);
        f955t.lineTo(263.53f, 297.0f);
        f955t.cubicTo(281.99f, 297.0f, 297.0f, 281.99f, 297.0f, 263.54f);
        f955t.lineTo(297.0f, 33.47f);
        f955t.cubicTo(297.0f, 15.01f, 281.99f, 0.0f, 263.54f, 0.0f);
        f955t.moveTo(258.38f, 173.36f);
        f955t.cubicTo(248.82f, 188.37f, 235.58f, 203.16f, 219.03f, 217.33f);
        f955t.cubicTo(191.09f, 241.26f, 162.85f, 255.66f, 159.73f, 257.22f);
        f955t.cubicTo(156.26f, 258.96f, 152.38f, 259.87f, 148.5f, 259.87f);
        f955t.cubicTo(144.62f, 259.87f, 140.74f, 258.96f, 137.28f, 257.23f);
        f955t.cubicTo(134.15f, 255.66f, 105.91f, 241.26f, 77.97f, 217.33f);
        f955t.cubicTo(61.42f, 203.16f, 48.18f, 188.37f, 38.62f, 173.36f);
        f955t.cubicTo(26.18f, 153.85f, 19.87f, 133.88f, 19.87f, 113.99f);
        f955t.cubicTo(19.87f, 71.61f, 54.35f, 37.13f, 96.73f, 37.13f);
        f955t.cubicTo(116.13f, 37.13f, 134.49f, 44.41f, 148.5f, 57.2f);
        f955t.cubicTo(162.5f, 44.41f, 180.87f, 37.13f, 200.26f, 37.13f);
        f955t.cubicTo(242.65f, 37.13f, 277.13f, 71.61f, 277.13f, 113.99f);
        f955t.cubicTo(277.13f, 133.88f, 270.82f, 153.85f, 258.38f, 173.36f);
        f955t.transform(f953m);
        if (clearMode) {
            f954p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f955t, ps);
        } else {
            c.drawPath(f955t, f954p);
            c.drawPath(f955t, ps);
        }
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f954p.setColor(Color.parseColor("#000000"));
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f954p.setColor(Color.parseColor("#000000"));
        c.save();
        c.save();
        f955t.reset();
        f955t.moveTo(200.27f, 51.77f);
        f955t.cubicTo(178.7f, 51.77f, 159.67f, 62.79f, 148.5f, 79.5f);
        f955t.cubicTo(137.33f, 62.79f, 118.3f, 51.77f, 96.73f, 51.77f);
        f955t.cubicTo(62.42f, 51.77f, 34.51f, 79.68f, 34.51f, 113.99f);
        f955t.cubicTo(34.51f, 188.62f, 139.36f, 241.9f, 143.82f, 244.13f);
        f955t.cubicTo(145.3f, 244.87f, 146.9f, 245.24f, 148.5f, 245.24f);
        f955t.cubicTo(150.1f, 245.24f, 151.71f, 244.87f, 153.18f, 244.13f);
        f955t.cubicTo(157.64f, 241.9f, 262.49f, 188.62f, 262.49f, 113.99f);
        f955t.cubicTo(262.49f, 79.68f, 234.58f, 51.77f, 200.27f, 51.77f);
        f955t.transform(f953m);
        if (clearMode) {
            f954p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f955t, ps);
        } else {
            c.drawPath(f955t, f954p);
            c.drawPath(f955t, ps);
        }
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f954p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m822r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m822r(new Integer[0]);
        c.restore();
    }

    private static void m822r(Integer... o) {
        f954p.reset();
        ps.reset();
        if (cf != null) {
            f954p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f954p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f954p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f954p.setColor(Color.parseColor("#000000"));
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
