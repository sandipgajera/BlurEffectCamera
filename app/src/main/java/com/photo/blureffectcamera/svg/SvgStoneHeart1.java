package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgStoneHeart1 extends Svg {
    private static final Matrix f1022m;
    private static float od;
    private static final Paint f1023p;
    private static final Paint ps;
    private static final Path f1024t;

    static {
        f1023p = new Paint();
        ps = new Paint();
        f1024t = new Path();
        f1022m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 513.0f < h / 441.0f ? w / 513.0f : h / 441.0f;
        m845r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 513.0f)) / 2.0f) + dx, ((h - (od * 441.0f)) / 2.0f) + dy);
        f1022m.reset();
        f1022m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.15f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        f1023p.setColor(Color.parseColor("#010101"));
        f1024t.reset();
        f1024t.moveTo(117.8f, 4.41f);
        f1024t.cubicTo(14.56f, 22.84f, -51.8f, 289.53f, 52.66f, 384.16f);
        f1024t.cubicTo(157.12f, 478.79f, 514.75f, 454.21f, 513.52f, 328.85f);
        f1024t.cubicTo(512.29f, 203.5f, 375.39f, 102.91f, 334.09f, 74.46f);
        f1024t.cubicTo(187.85f, -26.32f, 117.8f, 4.41f, 117.8f, 4.41f);
        f1024t.transform(f1022m);
        c.drawPath(f1024t, f1023p);
        c.drawPath(f1024t, ps);
        c.restore();
        m845r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f1023p.setColor(Color.parseColor("#010101"));
        c.restore();
        m845r(new Integer[0]);
        c.restore();
    }

    private static void m845r(Integer... o) {
        f1023p.reset();
        ps.reset();
        if (cf != null) {
            f1023p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1023p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1023p.setStyle(Style.FILL);
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
