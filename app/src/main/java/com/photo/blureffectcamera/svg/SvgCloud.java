package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgCloud extends Svg {
    private static final Matrix f905m;
    private static float od;
    private static final Paint f906p;
    private static final Paint ps;
    private static final Path f907t;

    static {
        f906p = new Paint();
        ps = new Paint();
        f907t = new Path();
        f905m = new Matrix();
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
        m806r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f905m.reset();
        f905m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.64f, 1.64f);
        c.save();
        c.save();
        f906p.setColor(Color.parseColor("#010002"));
        f907t.reset();
        f907t.moveTo(169.13f, 285.97f);
        f907t.cubicTo(143.05f, 285.97f, 117.84f, 275.52f, 99.27f, 257.16f);
        f907t.cubicTo(93.07f, 259.82f, 86.35f, 261.23f, 79.57f, 261.23f);
        f907t.cubicTo(52.15f, 261.23f, 29.85f, 238.92f, 29.85f, 211.5f);
        f907t.cubicTo(29.85f, 208.57f, 30.13f, 205.6f, 30.67f, 202.63f);
        f907t.cubicTo(11.39f, 188.36f, 0.0f, 165.94f, 0.0f, 141.83f);
        f907t.cubicTo(0.0f, 103.92f, 28.62f, 71.73f, 65.85f, 66.89f);
        f907t.cubicTo(75.18f, 42.49f, 98.41f, 26.36f, 124.84f, 26.36f);
        f907t.cubicTo(147.92f, 26.36f, 169.02f, 38.97f, 180.08f, 58.93f);
        f907t.cubicTo(188.06f, 56.17f, 196.37f, 54.78f, 204.88f, 54.78f);
        f907t.cubicTo(246.83f, 54.78f, 280.95f, 88.9f, 280.95f, 130.84f);
        f907t.cubicTo(280.95f, 132.6f, 280.88f, 134.39f, 280.72f, 136.29f);
        f907t.cubicTo(299.56f, 143.66f, 312.33f, 162.02f, 312.33f, 182.55f);
        f907t.cubicTo(312.33f, 211.67f, 286.99f, 235.0f, 257.48f, 232.01f);
        f907t.cubicTo(240.5f, 264.93f, 206.3f, 285.97f, 169.13f, 285.97f);
        f907t.transform(f905m);
        if (clearMode) {
            f906p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f907t, ps);
        } else {
            c.drawPath(f907t, f906p);
            c.drawPath(f907t, ps);
        }
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1));
        f906p.setColor(Color.parseColor("#010002"));
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1));
        f906p.setColor(Color.parseColor("#010002"));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.save();
        c.restore();
        m806r(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(4));
        c.restore();
        m806r(new Integer[0]);
        c.restore();
    }

    private static void m806r(Integer... o) {
        f906p.reset();
        ps.reset();
        if (cf != null) {
            f906p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f906p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f906p.setStyle(Style.FILL);
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
//                    ps.setColor(Color.argb(0, 0, 0, 0));
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_FRAME /*3*/:
//                    ps.setStrokeCap(Cap.BUTT);
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_FX /*4*/:
//                    f906p.setColor(Color.parseColor("#010002"));
//                    break;
//                default:
//                    break;
            }
        }
    }
}
