package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgStone3 extends Svg {
    private static final Matrix f1016m;
    private static float od;
    private static final Paint f1017p;
    private static final Paint ps;
    private static final Path f1018t;

    static {
        f1017p = new Paint();
        ps = new Paint();
        f1018t = new Path();
        f1016m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 483.0f < h / 512.0f ? w / 483.0f : h / 512.0f;
        m843r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 483.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f1016m.reset();
        f1016m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.0f, 0.18f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f1017p.setColor(Color.parseColor("#010101"));
        f1018t.reset();
        f1018t.moveTo(147.94f, 1.5f);
        f1018t.cubicTo(273.85f, -13.19f, 423.01f, 82.98f, 465.86f, 176.72f);
        f1018t.cubicTo(516.22f, 286.89f, 444.88f, 390.76f, 407.1f, 427.49f);
        f1018t.cubicTo(369.33f, 464.21f, 288.54f, 549.2f, 163.68f, 493.59f);
        f1018t.cubicTo(71.35f, 442.17f, 3.67f, 272.2f, 0.0f, 201.9f);
        f1018t.cubicTo(4.2f, 157.83f, -7.34f, 37.17f, 147.94f, 1.5f);
        f1018t.transform(f1016m);
        c.drawPath(f1018t, f1017p);
        c.drawPath(f1018t, ps);
        c.restore();
        m843r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f1017p.setColor(Color.parseColor("#010101"));
        c.restore();
        m843r(new Integer[0]);
        c.restore();
    }

    private static void m843r(Integer... o) {
        f1017p.reset();
        ps.reset();
        if (cf != null) {
            f1017p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1017p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1017p.setStyle(Style.FILL);
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
