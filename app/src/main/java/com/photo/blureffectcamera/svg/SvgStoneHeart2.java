package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgStoneHeart2 extends Svg {
    private static final Matrix f1025m;
    private static float od;
    private static final Paint f1026p;
    private static final Paint ps;
    private static final Path f1027t;

    static {
        f1026p = new Paint();
        ps = new Paint();
        f1027t = new Path();
        f1025m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 513.0f < h / 441.0f ? w / 513.0f : h / 441.0f;
        m846r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 513.0f)) / 2.0f) + dx, ((h - (od * 441.0f)) / 2.0f) + dy);
        f1025m.reset();
        f1025m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.15f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        f1026p.setColor(Color.parseColor("#010101"));
        f1027t.reset();
        f1027t.moveTo(395.73f, 4.41f);
        f1027t.cubicTo(498.96f, 22.84f, 565.33f, 289.53f, 460.87f, 384.16f);
        f1027t.cubicTo(356.4f, 478.79f, -1.23f, 454.21f, 0.0f, 328.85f);
        f1027t.cubicTo(1.23f, 203.5f, 138.14f, 102.91f, 179.43f, 74.46f);
        f1027t.cubicTo(325.68f, -26.32f, 395.73f, 4.41f, 395.73f, 4.41f);
        f1027t.transform(f1025m);
        c.drawPath(f1027t, f1026p);
        c.drawPath(f1027t, ps);
        c.restore();
        m846r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f1026p.setColor(Color.parseColor("#010101"));
        c.restore();
        m846r(new Integer[0]);
        c.restore();
    }

    private static void m846r(Integer... o) {
        f1026p.reset();
        ps.reset();
        if (cf != null) {
            f1026p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1026p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1026p.setStyle(Style.FILL);
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
