package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg4Circle3 extends Svg {
    private static final Matrix f842m;
    private static float od;
    private static final Paint f843p;
    private static final Paint ps;
    private static final Path f844t;

    static {
        f843p = new Paint();
        ps = new Paint();
        f844t = new Path();
        f842m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 501.0f ? w / 512.0f : h / 501.0f;
        m785r(new Integer[0]);
        c.save();
        c.translate((((w - (od * 512.0f)) / 2.0f) + dx) + 5.0f, (((h - (od * 501.0f)) / 2.0f) + dy) - 13.0f);
        f842m.reset();
        f842m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.06f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        f844t.reset();
        f844t.moveTo(86.33f, 209.26f);
        f844t.cubicTo(77.27f, 135.35f, 56.38f, 48.53f, 1.93f, 0.13f);
        f844t.lineTo(511.98f, 0.13f);
        f844t.lineTo(511.98f, 428.96f);
        f844t.cubicTo(524.52f, 419.53f, 420.03f, 499.26f, 298.57f, 500.78f);
        f844t.cubicTo(197.56f, 501.28f, 123.99f, 479.05f, 50.34f, 431.56f);
        f844t.cubicTo(74.07f, 384.91f, 94.13f, 300.95f, 86.33f, 209.26f);
        f844t.transform(f842m);
        c.drawPath(f844t, f843p);
        c.drawPath(f844t, ps);
        c.restore();
        m785r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m785r(new Integer[0]);
        c.restore();
    }

    private static void m785r(Integer... o) {
        f843p.reset();
        ps.reset();
        if (cf != null) {
            f843p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f843p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f843p.setStyle(Style.FILL);
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
