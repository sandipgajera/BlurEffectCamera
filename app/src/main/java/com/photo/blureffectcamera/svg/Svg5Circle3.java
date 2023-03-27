package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg5Circle3 extends Svg {
    private static final Matrix f854m;
    private static float od;
    private static final Paint f855p;
    private static final Paint ps;
    private static final Path f856t;

    static {
        f855p = new Paint();
        ps = new Paint();
        f856t = new Path();
        f854m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 512.0f ? w / 512.0f : h / 512.0f;
        m789r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f854m.reset();
        f854m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.13f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        f856t.reset();
        f856t.moveTo(512.33f, 448.7f);
        f856t.cubicTo(512.33f, 448.7f, 499.13f, 456.93f, 479.36f, 469.7f);
        f856t.cubicTo(448.92f, 486.73f, 387.33f, 516.39f, 338.16f, 512.17f);
        f856t.cubicTo(310.11f, 483.15f, 241.38f, 395.1f, 241.38f, 395.1f);
        f856t.lineTo(179.39f, 303.59f);
        f856t.lineTo(104.6f, 238.64f);
        f856t.lineTo(0.3f, 176.65f);
        f856t.cubicTo(0.3f, 176.65f, -2.33f, 149.75f, 8.0f, 112.36f);
        f856t.cubicTo(18.54f, 71.88f, 39.38f, 29.88f, 64.44f, 1.15f);
        f856t.lineTo(511.51f, 0.0f);
        f856t.lineTo(512.33f, 448.7f);
        f856t.transform(f854m);
        c.drawPath(f856t, f855p);
        c.drawPath(f856t, ps);
        c.restore();
        m789r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m789r(new Integer[0]);
        c.restore();
    }

    private static void m789r(Integer... o) {
        f855p.reset();
        ps.reset();
        if (cf != null) {
            f855p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f855p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f855p.setStyle(Style.FILL);
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
