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

public class SvgAppleSteve extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f866m;
    private static float od;
    private static final Paint f867p;
    private static final Paint ps;
    private static final Path f868t;

    static {
        f867p = new Paint();
        ps = new Paint();
        f868t = new Path();
        f866m = new Matrix();
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
        m793r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f866m.reset();
        f866m.setScale(od * 5.69f, od * 5.69f);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.save();
        c.save();
        f867p.setColor(Color.parseColor("#000000"));
        f868t.reset();
        f868t.moveTo(49.65f, 6.82f);
        f868t.cubicTo(52.88f, 3.02f, 58.34f, 0.19f, 62.85f, 0.0f);
        f868t.cubicTo(63.42f, 5.28f, 61.31f, 10.56f, 58.18f, 14.37f);
        f868t.cubicTo(55.05f, 18.18f, 49.92f, 21.14f, 44.89f, 20.74f);
        f868t.cubicTo(44.2f, 15.58f, 46.74f, 10.19f, 49.65f, 6.82f);
        f868t.moveTo(75.29f, 78.83f);
        f868t.cubicTo(71.55f, 84.31f, 67.68f, 89.77f, 61.57f, 89.88f);
        f868t.cubicTo(55.56f, 89.99f, 53.63f, 86.31f, 46.77f, 86.31f);
        f868t.cubicTo(39.91f, 86.31f, 37.76f, 89.77f, 32.08f, 90.0f);
        f868t.cubicTo(26.19f, 90.22f, 21.7f, 84.07f, 17.93f, 78.61f);
        f868t.cubicTo(10.23f, 67.44f, 4.35f, 47.03f, 12.25f, 33.26f);
        f868t.cubicTo(16.17f, 26.42f, 23.19f, 22.09f, 30.79f, 21.98f);
        f868t.cubicTo(36.58f, 21.87f, 42.05f, 25.89f, 45.59f, 25.89f);
        f868t.cubicTo(49.13f, 25.89f, 55.78f, 21.06f, 62.76f, 21.77f);
        f868t.cubicTo(65.68f, 21.89f, 73.88f, 22.95f, 79.15f, 30.68f);
        f868t.cubicTo(78.73f, 30.96f, 69.36f, 36.43f, 69.47f, 47.82f);
        f868t.cubicTo(69.58f, 61.44f, 81.36f, 65.96f, 81.5f, 66.02f);
        f868t.cubicTo(81.39f, 66.34f, 79.62f, 72.48f, 75.29f, 78.83f);
        f868t.transform(f866m);
        if (clearMode) {
            f867p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f868t, ps);
        } else {
            c.drawPath(f868t, f867p);
            c.drawPath(f868t, ps);
        }
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f867p.setColor(Color.parseColor("#000000"));
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f867p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m793r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m793r(new Integer[0]);
        c.restore();
    }

    private static void m793r(Integer... o) {
        f867p.reset();
        ps.reset();
        if (cf != null) {
            f867p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f867p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f867p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f867p.setColor(Color.parseColor("#000000"));
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
