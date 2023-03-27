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

public class SvgBird extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f881m;
    private static float od;
    private static final Paint f882p;
    private static final Paint ps;
    private static final Path f883t;

    static {
        f882p = new Paint();
        ps = new Paint();
        f883t = new Path();
        f881m = new Matrix();
        cf = null;
    }

    public static void setColorTint(int color) {
        cf = new PorterDuffColorFilter(color, Mode.SRC_IN);
    }

    public static void clearColorTint(int color) {
        cf = null;
    }

    public void drawStroke(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        isStroke = true;
        draw(c, w, h, dx, dy, clearMode);
        isStroke = false;
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 512.0f ? w / 512.0f : h / 512.0f;
        m798r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f881m.reset();
        f881m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        c.save();
        f883t.reset();
        f883t.moveTo(512.0f, 97.21f);
        f883t.cubicTo(493.16f, 105.56f, 472.92f, 111.21f, 451.67f, 113.75f);
        f883t.cubicTo(473.36f, 100.75f, 490.01f, 80.17f, 497.86f, 55.64f);
        f883t.cubicTo(477.56f, 67.67f, 455.08f, 76.42f, 431.15f, 81.13f);
        f883t.cubicTo(411.99f, 60.71f, 384.69f, 47.96f, 354.48f, 47.96f);
        f883t.cubicTo(296.47f, 47.96f, 249.44f, 94.99f, 249.44f, 153.0f);
        f883t.cubicTo(249.44f, 161.23f, 250.37f, 169.25f, 252.16f, 176.93f);
        f883t.cubicTo(164.86f, 172.55f, 87.46f, 130.73f, 35.65f, 67.18f);
        f883t.cubicTo(26.61f, 82.69f, 21.42f, 100.74f, 21.42f, 119.99f);
        f883t.cubicTo(21.42f, 156.43f, 39.97f, 188.59f, 68.15f, 207.42f);
        f883t.cubicTo(50.94f, 206.88f, 34.74f, 202.15f, 20.58f, 194.28f);
        f883t.cubicTo(20.57f, 194.72f, 20.57f, 195.16f, 20.57f, 195.6f);
        f883t.cubicTo(20.57f, 246.5f, 56.78f, 288.95f, 104.83f, 298.61f);
        f883t.cubicTo(96.02f, 301.0f, 86.73f, 302.29f, 77.15f, 302.29f);
        f883t.cubicTo(70.39f, 302.29f, 63.81f, 301.63f, 57.39f, 300.4f);
        f883t.cubicTo(70.76f, 342.13f, 109.55f, 372.51f, 155.52f, 373.35f);
        f883t.cubicTo(119.57f, 401.53f, 74.27f, 418.32f, 25.06f, 418.32f);
        f883t.cubicTo(16.58f, 418.32f, 8.22f, 417.82f, 0.0f, 416.85f);
        f883t.cubicTo(46.49f, 446.66f, 101.7f, 464.05f, 161.02f, 464.05f);
        f883t.cubicTo(354.23f, 464.05f, 459.89f, 303.98f, 459.89f, 165.17f);
        f883t.cubicTo(459.89f, 160.62f, 459.79f, 156.09f, 459.58f, 151.58f);
        f883t.cubicTo(480.11f, 136.78f, 497.92f, 118.28f, 512.0f, 97.21f);
        f883t.transform(f881m);
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f883t, ps);
        } else {
            c.drawPath(f883t, f882p);
            c.drawPath(f883t, ps);
        }
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m798r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m798r(new Integer[0]);
        c.restore();
    }

    private static void m798r(Integer... o) {
        f882p.reset();
        ps.reset();
        if (cf != null) {
            f882p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f882p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f882p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    ps.setStrokeJoin(Join.MITER);
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_BACKGROUND /*1*/:
//                    ps.setStrokeMiter(4.0f * od);
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_BLUR /*2*/:
//                    ps.setStrokeCap(Cap.BUTT);
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_FRAME /*3*/:
//                    ps.setColor(Color.argb(0, 0, 0, 0));
//                    break;
//                default:
//                    break;
            }
        }
    }
}
