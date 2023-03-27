package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgStone4 extends Svg {
    private static final Matrix f1019m;
    private static float od;
    private static final Paint f1020p;
    private static final Paint ps;
    private static final Path f1021t;

    static {
        f1020p = new Paint();
        ps = new Paint();
        f1021t = new Path();
        f1019m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 489.0f < h / 512.0f ? w / 489.0f : h / 512.0f;
        m844r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 489.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f1019m.reset();
        f1019m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f1020p.setColor(Color.parseColor("#010101"));
        f1021t.reset();
        f1021t.moveTo(146.96f, 50.38f);
        f1021t.cubicTo(334.19f, -22.7f, 382.91f, -6.0f, 439.99f, 35.76f);
        f1021t.cubicTo(497.06f, 77.52f, 510.8f, 204.27f, 448.34f, 321.13f);
        f1021t.cubicTo(383.61f, 442.23f, 314.7f, 551.5f, 172.71f, 498.61f);
        f1021t.cubicTo(30.73f, 445.71f, -6.16f, 307.21f, 0.8f, 246.65f);
        f1021t.cubicTo(5.74f, 203.72f, 27.25f, 113.72f, 146.96f, 50.38f);
        f1021t.transform(f1019m);
        c.drawPath(f1021t, f1020p);
        c.drawPath(f1021t, ps);
        c.restore();
        m844r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f1020p.setColor(Color.parseColor("#010101"));
        c.restore();
        m844r(new Integer[0]);
        c.restore();
    }

    private static void m844r(Integer... o) {
        f1020p.reset();
        ps.reset();
        if (cf != null) {
            f1020p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1020p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1020p.setStyle(Style.FILL);
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
