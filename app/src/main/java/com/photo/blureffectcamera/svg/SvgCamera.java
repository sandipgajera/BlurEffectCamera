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

public class SvgCamera extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f896m;
    private static float od;
    private static final Paint f897p;
    private static final Paint ps;
    private static final Path f898t;

    static {
        f897p = new Paint();
        ps = new Paint();
        f898t = new Path();
        f896m = new Matrix();
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
        m803r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f896m.reset();
        f896m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(0.93f, 0.93f);
        c.save();
        c.save();
        c.save();
        f897p.setColor(Color.parseColor("#000000"));
        f898t.reset();
        f898t.moveTo(526.76f, 131.04f);
        f898t.cubicTo(512.48f, 116.77f, 495.26f, 109.63f, 475.08f, 109.63f);
        f898t.lineTo(411.13f, 109.63f);
        f898t.lineTo(396.57f, 70.81f);
        f898t.cubicTo(392.96f, 61.48f, 386.34f, 53.44f, 376.73f, 46.68f);
        f898t.cubicTo(367.11f, 39.92f, 357.27f, 36.54f, 347.18f, 36.54f);
        f898t.lineTo(201.0f, 36.54f);
        f898t.cubicTo(190.91f, 36.54f, 181.06f, 39.92f, 171.44f, 46.68f);
        f898t.cubicTo(161.83f, 53.44f, 155.22f, 61.48f, 151.6f, 70.81f);
        f898t.lineTo(137.04f, 109.63f);
        f898t.lineTo(73.09f, 109.63f);
        f898t.cubicTo(52.91f, 109.63f, 35.69f, 116.77f, 21.41f, 131.04f);
        f898t.cubicTo(7.14f, 145.32f, 0.0f, 162.54f, 0.0f, 182.72f);
        f898t.lineTo(0.0f, 438.53f);
        f898t.cubicTo(0.0f, 458.71f, 7.14f, 475.94f, 21.41f, 490.21f);
        f898t.cubicTo(35.69f, 504.49f, 52.91f, 511.63f, 73.09f, 511.63f);
        f898t.lineTo(475.08f, 511.63f);
        f898t.cubicTo(495.25f, 511.63f, 512.48f, 504.49f, 526.75f, 490.21f);
        f898t.cubicTo(541.03f, 475.94f, 548.16f, 458.71f, 548.16f, 438.53f);
        f898t.lineTo(548.16f, 182.72f);
        f898t.cubicTo(548.17f, 162.54f, 541.03f, 145.32f, 526.76f, 131.04f);
        f898t.moveTo(364.45f, 400.99f);
        f898t.cubicTo(339.42f, 426.02f, 309.3f, 438.54f, 274.08f, 438.54f);
        f898t.cubicTo(238.87f, 438.54f, 208.75f, 426.02f, 183.72f, 400.99f);
        f898t.cubicTo(158.69f, 375.97f, 146.18f, 345.84f, 146.18f, 310.63f);
        f898t.cubicTo(146.18f, 275.42f, 158.7f, 245.3f, 183.72f, 220.27f);
        f898t.cubicTo(208.75f, 195.24f, 238.87f, 182.73f, 274.08f, 182.73f);
        f898t.cubicTo(309.3f, 182.73f, 339.42f, 195.24f, 364.45f, 220.27f);
        f898t.cubicTo(389.48f, 245.3f, 401.99f, 275.42f, 401.99f, 310.63f);
        f898t.cubicTo(401.99f, 345.84f, 389.48f, 375.96f, 364.45f, 400.99f);
        f898t.transform(f896m);
        if (clearMode) {
            f897p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f898t, ps);
        } else {
            c.drawPath(f898t, f897p);
            c.drawPath(f898t, ps);
        }
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f897p.setColor(Color.parseColor("#000000"));
        c.save();
        f898t.reset();
        f898t.moveTo(274.08f, 228.4f);
        f898t.cubicTo(251.43f, 228.4f, 232.07f, 236.44f, 215.98f, 252.53f);
        f898t.cubicTo(199.9f, 268.62f, 191.86f, 287.98f, 191.86f, 310.63f);
        f898t.cubicTo(191.86f, 333.28f, 199.9f, 352.65f, 215.98f, 368.73f);
        f898t.cubicTo(232.07f, 384.81f, 251.43f, 392.86f, 274.08f, 392.86f);
        f898t.cubicTo(296.73f, 392.86f, 316.1f, 384.81f, 332.19f, 368.73f);
        f898t.cubicTo(348.27f, 352.65f, 356.31f, 333.28f, 356.31f, 310.63f);
        f898t.cubicTo(356.31f, 287.98f, 348.27f, 268.62f, 332.19f, 252.53f);
        f898t.cubicTo(316.1f, 236.45f, 296.73f, 228.4f, 274.08f, 228.4f);
        f898t.transform(f896m);
        if (clearMode) {
            f897p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f898t, ps);
        } else {
            c.drawPath(f898t, f897p);
            c.drawPath(f898t, ps);
        }
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f897p.setColor(Color.parseColor("#000000"));
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f897p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m803r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m803r(new Integer[0]);
        c.restore();
    }

    private static void m803r(Integer... o) {
        f897p.reset();
        ps.reset();
        if (cf != null) {
            f897p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f897p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f897p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f897p.setColor(Color.parseColor("#000000"));
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
