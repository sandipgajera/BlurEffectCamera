package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgStone2 extends Svg {
    private static final Matrix f1013m;
    private static float od;
    private static final Paint f1014p;
    private static final Paint ps;
    private static final Path f1015t;

    static {
        f1014p = new Paint();
        ps = new Paint();
        f1015t = new Path();
        f1013m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 500.0f ? w / 512.0f : h / 500.0f;
        m842r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 500.0f)) / 2.0f) + dy);
        f1013m.reset();
        f1013m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.26f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        f1014p.setColor(Color.parseColor("#010101"));
        f1015t.reset();
        f1015t.moveTo(357.81f, 1.35f);
        f1015t.cubicTo(266.53f, -10.3f, 134.95f, 55.49f, 56.43f, 115.48f);
        f1015t.cubicTo(7.13f, 153.15f, -22.24f, 231.82f, 20.98f, 334.87f);
        f1015t.cubicTo(64.19f, 437.91f, 169.45f, 493.31f, 217.1f, 497.75f);
        f1015t.cubicTo(265.98f, 502.3f, 310.67f, 508.42f, 374.44f, 456.75f);
        f1015t.cubicTo(438.7f, 404.67f, 517.37f, 238.47f, 511.83f, 174.2f);
        f1015t.cubicTo(512.94f, 114.92f, 461.97f, 14.65f, 357.81f, 1.35f);
        f1015t.transform(f1013m);
        c.drawPath(f1015t, f1014p);
        c.drawPath(f1015t, ps);
        c.restore();
        m842r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f1014p.setColor(Color.parseColor("#010101"));
        c.restore();
        m842r(new Integer[0]);
        c.restore();
    }

    private static void m842r(Integer... o) {
        f1014p.reset();
        ps.reset();
        if (cf != null) {
            f1014p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1014p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1014p.setStyle(Style.FILL);
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
