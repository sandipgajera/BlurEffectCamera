package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg3Block3 extends Svg {
    private static final Matrix f815m;
    private static float od;
    private static final Paint f816p;
    private static final Paint ps;
    private static final Path f817t;

    static {
        f816p = new Paint();
        ps = new Paint();
        f817t = new Path();
        f815m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 483.0f ? w / 512.0f : h / 483.0f;
        m776r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 483.0f)) / 2.0f) + dy);
        f815m.reset();
        f815m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.05f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        f816p.setColor(Color.parseColor("#010101"));
        f817t.reset();
        f817t.moveTo(0.68f, 483.06f);
        f817t.cubicTo(0.68f, 483.06f, 346.1f, 483.06f, 360.41f, 483.06f);
        f817t.cubicTo(391.75f, 484.08f, 499.4f, 456.83f, 512.0f, 347.82f);
        f817t.cubicTo(512.0f, 326.5f, 512.0f, 0.01f, 512.0f, 0.01f);
        f817t.cubicTo(512.0f, 0.01f, 189.06f, 0.01f, 144.1f, 0.01f);
        f817t.cubicTo(93.34f, -1.01f, 2.73f, 53.5f, 0.0f, 142.58f);
        f817t.cubicTo(0.0f, 182.6f, 0.68f, 483.06f, 0.68f, 483.06f);
        f817t.transform(f815m);
        c.drawPath(f817t, f816p);
        c.drawPath(f817t, ps);
        c.restore();
        m776r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f816p.setColor(Color.parseColor("#010101"));
        c.restore();
        m776r(new Integer[0]);
        c.restore();
    }

    private static void m776r(Integer... o) {
        f816p.reset();
        ps.reset();
        if (cf != null) {
            f816p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f816p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f816p.setStyle(Style.FILL);
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
