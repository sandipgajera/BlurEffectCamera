package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;


public class Svg3HeartLeft extends Svg {
    private static final Matrix f821m;
    private static float od;
    private static final Paint f822p;
    private static final Paint ps;
    private static final Path f823t;

    static {
        f822p = new Paint();
        ps = new Paint();
        f823t = new Path();
        f821m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 357.0f < h / 512.0f ? w / 357.0f : h / 512.0f;
        m778r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 357.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f821m.reset();
        f821m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.0f, 0.02f * od);
        c.scale(1.0f, 1.0f);
        c.translate(7.1f * od, 7.1f * od);
        c.save();
        f823t.reset();
        f823t.moveTo(349.91f, -7.1f);
        f823t.cubicTo(314.76f, 2.19f, 241.97f, 33.07f, 250.55f, 144.4f);
        f823t.cubicTo(213.12f, 47.49f, 112.11f, 95.48f, 94.74f, 130.23f);
        f823t.cubicTo(59.32f, 178.35f, 79.05f, 221.4f, 136.17f, 287.96f);
        f823t.cubicTo(176.55f, 328.65f, 251.8f, 388.87f, 244.78f, 410.59f);
        f823t.cubicTo(240.8f, 449.57f, 221.47f, 482.73f, 152.88f, 505.15f);
        f823t.cubicTo(75.75f, 505.15f, -7.2f, 505.2f, -7.2f, 505.2f);
        f823t.lineTo(-7.17f, -7.1f);
        f823t.cubicTo(-7.17f, -7.1f, 356.47f, -7.07f, 349.91f, -7.1f);
        f823t.transform(f821m);
        c.drawPath(f823t, f822p);
        c.drawPath(f823t, ps);
        c.restore();
        m778r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m778r(new Integer[0]);
        c.restore();
    }

    private static void m778r(Integer... o) {
        f822p.reset();
        ps.reset();
        if (cf != null) {
            f822p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f822p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f822p.setStyle(Style.FILL);
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
