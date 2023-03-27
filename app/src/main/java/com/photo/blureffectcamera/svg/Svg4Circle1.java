package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg4Circle1 extends Svg {
    private static final Matrix f836m;
    private static float od;
    private static final Paint f837p;
    private static final Paint ps;
    private static final Path f838t;

    static {
        f837p = new Paint();
        ps = new Paint();
        f838t = new Path();
        f836m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 505.0f ? w / 512.0f : h / 505.0f;
        m783r(new Integer[0]);
        c.save();
        c.translate((((w - (od * 512.0f)) / 2.0f) + dx) - 4.0f, (((h - (od * 505.0f)) / 2.0f) + dy) - 3.0f);
        f836m.reset();
        f836m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.14f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        f838t.reset();
        f838t.moveTo(0.0f, 498.31f);
        f838t.cubicTo(0.0f, 467.67f, 0.32f, 327.96f, 0.32f, 205.28f);
        f838t.cubicTo(0.32f, 95.63f, 0.33f, 0.0f, 0.33f, 0.0f);
        f838t.cubicTo(0.33f, 0.0f, 359.13f, 0.0f, 432.71f, 0.0f);
        f838t.cubicTo(505.1f, 54.89f, 581.21f, 330.75f, 399.36f, 502.26f);
        f838t.cubicTo(399.36f, 502.26f, 345.23f, 468.93f, 280.11f, 454.61f);
        f838t.cubicTo(211.49f, 439.98f, 113.07f, 429.96f, 0.0f, 504.94f);
        f838t.cubicTo(0.16f, 504.94f, 0.0f, 508.14f, 0.0f, 498.31f);
        f838t.transform(f836m);
        c.drawPath(f838t, f837p);
        c.drawPath(f838t, ps);
        c.restore();
        m783r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m783r(new Integer[0]);
        c.restore();
    }

    private static void m783r(Integer... o) {
        f837p.reset();
        ps.reset();
        if (cf != null) {
            f837p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f837p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f837p.setStyle(Style.FILL);
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
