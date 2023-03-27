package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg3Block1 extends Svg {
    private static final Matrix f809m;
    private static float od;
    private static final Paint f810p;
    private static final Paint ps;
    private static final Path f811t;

    static {
        f810p = new Paint();
        ps = new Paint();
        f811t = new Path();
        f809m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 375.0f < h / 511.0f ? w / 375.0f : h / 511.0f;
        m774r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 375.0f)) / 2.0f) + dx, ((h - (od * 511.0f)) / 2.0f) + dy);
        f809m.reset();
        f809m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.0f, 0.54f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f811t.reset();
        f811t.moveTo(375.79f, 511.0f);
        f811t.cubicTo(375.79f, 511.0f, 375.79f, 147.7f, 375.79f, 107.8f);
        f811t.cubicTo(374.83f, 84.34f, 360.7f, 3.84f, 269.09f, 0.0f);
        f811t.lineTo(0.01f, 0.0f);
        f811t.cubicTo(0.01f, 0.0f, 0.43f, 375.23f, 0.01f, 387.16f);
        f811t.cubicTo(-0.4f, 404.85f, 7.42f, 502.36f, 110.69f, 510.18f);
        f811t.cubicTo(111.51f, 510.59f, 375.79f, 511.0f, 375.79f, 511.0f);
        f811t.transform(f809m);
        c.drawPath(f811t, f810p);
        c.drawPath(f811t, ps);
        c.restore();
        m774r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m774r(new Integer[0]);
        c.restore();
    }

    private static void m774r(Integer... o) {
        f810p.reset();
        ps.reset();
        if (cf != null) {
            f810p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f810p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f810p.setStyle(Style.FILL);
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
