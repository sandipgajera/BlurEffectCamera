package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg3Block2 extends Svg {
    private static final Matrix f812m;
    private static float od;
    private static final Paint f813p;
    private static final Paint ps;
    private static final Path f814t;

    static {
        f813p = new Paint();
        ps = new Paint();
        f814t = new Path();
        f812m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 485.0f ? w / 512.0f : h / 485.0f;
        m775r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 485.0f)) / 2.0f) + dy);
        f812m.reset();
        f812m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.23f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        f814t.reset();
        f814t.moveTo(1.02f, 0.0f);
        f814t.cubicTo(1.02f, 0.0f, 293.3f, 0.0f, 371.99f, 0.0f);
        f814t.cubicTo(450.17f, 4.09f, 508.42f, 77.67f, 512.0f, 144.1f);
        f814t.cubicTo(512.0f, 207.46f, 512.0f, 485.43f, 512.0f, 485.43f);
        f814t.cubicTo(512.0f, 485.43f, 241.18f, 485.43f, 143.07f, 485.43f);
        f814t.cubicTo(50.59f, 478.79f, 2.04f, 395.5f, 0.0f, 343.38f);
        f814t.cubicTo(0.0f, 314.76f, 1.02f, 0.0f, 1.02f, 0.0f);
        f814t.transform(f812m);
        c.drawPath(f814t, f813p);
        c.drawPath(f814t, ps);
        c.restore();
        m775r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m775r(new Integer[0]);
        c.restore();
    }

    private static void m775r(Integer... o) {
        f813p.reset();
        ps.reset();
        if (cf != null) {
            f813p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f813p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f813p.setStyle(Style.FILL);
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
