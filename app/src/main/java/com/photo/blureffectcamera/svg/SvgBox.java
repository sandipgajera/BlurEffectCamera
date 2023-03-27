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

public class SvgBox extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f884m;
    private static float od;
    private static final Paint f885p;
    private static final Paint ps;
    private static final Path f886t;

    static {
        f885p = new Paint();
        ps = new Paint();
        f886t = new Path();
        f884m = new Matrix();
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
        m799r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f884m.reset();
        f884m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(0.84f, 0.84f);
        c.save();
        c.save();
        f885p.setColor(Color.parseColor("#000000"));
        f886t.reset();
        f886t.moveTo(530.53f, 32.08f);
        f886t.cubicTo(557.77f, 32.08f, 579.92f, 54.23f, 579.92f, 81.47f);
        f886t.lineTo(579.92f, 530.63f);
        f886t.cubicTo(579.92f, 557.81f, 557.76f, 579.92f, 530.53f, 579.92f);
        f886t.lineTo(81.36f, 579.92f);
        f886t.cubicTo(54.19f, 579.92f, 32.07f, 557.81f, 32.07f, 530.63f);
        f886t.lineTo(32.07f, 81.47f);
        f886t.cubicTo(32.07f, 54.23f, 54.19f, 32.08f, 81.36f, 32.08f);
        f886t.lineTo(530.53f, 32.08f);
        f886t.moveTo(530.53f, 0.0f);
        f886t.lineTo(81.36f, 0.0f);
        f886t.cubicTo(36.57f, 0.0f, 0.0f, 36.57f, 0.0f, 81.47f);
        f886t.lineTo(0.0f, 530.63f);
        f886t.cubicTo(0.0f, 575.54f, 36.57f, 612.0f, 81.36f, 612.0f);
        f886t.lineTo(530.53f, 612.0f);
        f886t.cubicTo(575.43f, 612.0f, 612.0f, 575.54f, 612.0f, 530.64f);
        f886t.lineTo(612.0f, 81.47f);
        f886t.cubicTo(612.0f, 36.57f, 575.43f, 0.0f, 530.53f, 0.0f);
        f886t.lineTo(530.53f, 0.0f);
        f886t.moveTo(292.31f, 557.58f);
        f886t.lineTo(292.31f, 487.44f);
        f886t.lineTo(218.06f, 557.58f);
        f886t.lineTo(292.31f, 557.58f);
        f886t.moveTo(431.85f, 557.58f);
        f886t.lineTo(431.85f, 355.48f);
        f886t.lineTo(319.8f, 461.45f);
        f886t.lineTo(319.8f, 557.58f);
        f886t.lineTo(431.85f, 557.58f);
        f886t.moveTo(530.53f, 557.58f);
        f886t.cubicTo(545.45f, 557.58f, 557.58f, 545.5f, 557.58f, 530.64f);
        f886t.lineTo(557.58f, 236.72f);
        f886t.lineTo(459.32f, 329.52f);
        f886t.lineTo(459.32f, 557.58f);
        f886t.lineTo(530.53f, 557.58f);
        f886t.lineTo(530.53f, 557.58f);
        f886t.moveTo(178.1f, 557.58f);
        f886t.lineTo(557.58f, 198.88f);
        f886t.lineTo(557.58f, 81.48f);
        f886t.cubicTo(557.58f, 66.56f, 545.44f, 54.42f, 530.53f, 54.42f);
        f886t.lineTo(469.18f, 54.42f);
        f886t.lineTo(54.42f, 446.48f);
        f886t.lineTo(54.42f, 530.64f);
        f886t.cubicTo(54.42f, 545.49f, 66.5f, 557.57f, 81.36f, 557.57f);
        f886t.lineTo(178.1f, 557.57f);
        f886t.moveTo(319.8f, 157.95f);
        f886t.lineTo(429.19f, 54.42f);
        f886t.lineTo(319.8f, 54.42f);
        f886t.lineTo(319.8f, 157.95f);
        f886t.moveTo(180.16f, 289.99f);
        f886t.lineTo(292.31f, 183.91f);
        f886t.lineTo(292.31f, 54.42f);
        f886t.lineTo(180.16f, 54.42f);
        f886t.lineTo(180.16f, 289.99f);
        f886t.moveTo(54.42f, 408.78f);
        f886t.lineTo(152.68f, 315.84f);
        f886t.lineTo(152.68f, 54.42f);
        f886t.lineTo(81.36f, 54.42f);
        f886t.cubicTo(66.51f, 54.42f, 54.42f, 66.56f, 54.42f, 81.48f);
        f886t.lineTo(54.42f, 408.78f);
        f886t.lineTo(54.42f, 408.78f);
        f886t.transform(f884m);
        if (clearMode) {
            f885p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f886t, ps);
        } else {
            c.drawPath(f886t, f885p);
            c.drawPath(f886t, ps);
        }
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f885p.setColor(Color.parseColor("#000000"));
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f885p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m799r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m799r(new Integer[0]);
        c.restore();
    }

    private static void m799r(Integer... o) {
        f885p.reset();
        ps.reset();
        if (cf != null) {
            f885p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f885p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f885p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f885p.setColor(Color.parseColor("#000000"));
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
