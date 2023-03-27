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

public class SvgBearHeart extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f878m;
    private static float od;
    private static final Paint f879p;
    private static final Paint ps;
    private static final Path f880t;

    static {
        f879p = new Paint();
        ps = new Paint();
        f880t = new Path();
        f878m = new Matrix();
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
        m797r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f878m.reset();
        f878m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(0.99f, 0.99f);
        c.save();
        c.save();
        c.save();
        f879p.setColor(Color.parseColor("#000000"));
        f880t.reset();
        f880t.moveTo(458.8f, 297.04f);
        f880t.cubicTo(458.8f, 263.05f, 431.24f, 235.48f, 397.24f, 235.48f);
        f880t.cubicTo(383.38f, 235.48f, 370.64f, 240.12f, 360.35f, 247.84f);
        f880t.cubicTo(352.83f, 240.35f, 344.43f, 233.8f, 335.41f, 228.11f);
        f880t.cubicTo(355.84f, 208.0f, 368.54f, 180.05f, 368.54f, 149.12f);
        f880t.cubicTo(368.54f, 138.83f, 367.03f, 128.91f, 364.41f, 119.45f);
        f880t.cubicTo(392.87f, 114.67f, 414.57f, 89.99f, 414.57f, 60.18f);
        f880t.cubicTo(414.57f, 26.95f, 387.63f, 0.0f, 354.39f, 0.0f);
        f880t.cubicTo(326.31f, 0.0f, 302.8f, 19.25f, 296.15f, 45.25f);
        f880t.cubicTo(284.15f, 40.8f, 271.22f, 38.25f, 257.68f, 38.25f);
        f880t.cubicTo(244.87f, 38.25f, 232.62f, 40.54f, 221.19f, 44.52f);
        f880t.cubicTo(214.29f, 18.9f, 190.95f, 0.0f, 163.14f, 0.0f);
        f880t.cubicTo(129.91f, 0.0f, 102.96f, 26.94f, 102.96f, 60.18f);
        f880t.cubicTo(102.96f, 89.27f, 123.6f, 113.53f, 151.03f, 119.13f);
        f880t.cubicTo(148.36f, 128.68f, 146.82f, 138.7f, 146.82f, 149.11f);
        f880t.cubicTo(146.82f, 180.51f, 159.93f, 208.81f, 180.92f, 228.97f);
        f880t.cubicTo(172.34f, 234.53f, 164.33f, 240.88f, 157.15f, 248.08f);
        f880t.cubicTo(146.81f, 240.2f, 133.94f, 235.47f, 119.93f, 235.47f);
        f880t.cubicTo(85.93f, 235.47f, 58.36f, 263.03f, 58.36f, 297.03f);
        f880t.cubicTo(58.36f, 329.56f, 83.62f, 356.13f, 115.59f, 358.37f);
        f880t.cubicTo(115.97f, 364.78f, 116.66f, 371.11f, 117.86f, 377.27f);
        f880t.cubicTo(89.29f, 397.86f, 85.11f, 442.5f, 108.88f, 478.2f);
        f880t.cubicTo(133.11f, 514.6f, 177.14f, 527.86f, 207.21f, 507.84f);
        f880t.cubicTo(214.55f, 502.95f, 220.35f, 496.51f, 224.62f, 489.08f);
        f880t.cubicTo(235.61f, 491.78f, 247.04f, 493.37f, 258.87f, 493.37f);
        f880t.cubicTo(270.76f, 493.37f, 282.23f, 491.77f, 293.28f, 489.05f);
        f880t.cubicTo(297.55f, 496.5f, 303.35f, 502.94f, 310.71f, 507.84f);
        f880t.cubicTo(340.78f, 527.86f, 384.81f, 514.6f, 409.04f, 478.2f);
        f880t.cubicTo(432.86f, 442.43f, 428.62f, 397.68f, 399.91f, 377.15f);
        f880t.cubicTo(401.09f, 371.02f, 401.78f, 364.72f, 402.16f, 358.35f);
        f880t.cubicTo(433.86f, 355.82f, 458.8f, 329.36f, 458.8f, 297.04f);
        f880t.moveTo(268.15f, 414.98f);
        f880t.lineTo(266.7f, 414.75f);
        f880t.cubicTo(208.03f, 390.65f, 185.41f, 333.27f, 211.25f, 309.18f);
        f880t.cubicTo(237.1f, 285.08f, 266.71f, 314.37f, 266.71f, 314.37f);
        f880t.lineTo(266.92f, 314.37f);
        f880t.cubicTo(266.92f, 314.37f, 296.53f, 285.08f, 322.38f, 309.18f);
        f880t.cubicTo(348.21f, 333.27f, 326.83f, 390.88f, 268.15f, 414.98f);
        f880t.transform(f878m);
        if (clearMode) {
            f879p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f880t, ps);
        } else {
            c.drawPath(f880t, f879p);
            c.drawPath(f880t, ps);
        }
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f879p.setColor(Color.parseColor("#000000"));
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f879p.setColor(Color.parseColor("#000000"));
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f879p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m797r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m797r(new Integer[0]);
        c.restore();
    }

    private static void m797r(Integer... o) {
        f879p.reset();
        ps.reset();
        if (cf != null) {
            f879p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f879p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f879p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f879p.setColor(Color.parseColor("#000000"));
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
