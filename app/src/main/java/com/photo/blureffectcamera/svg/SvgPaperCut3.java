package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgPaperCut3 extends Svg {
    private static final Matrix f986m;
    private static float od;
    private static final Paint f987p;
    private static final Paint ps;
    private static final Path f988t;

    static {
        f987p = new Paint();
        ps = new Paint();
        f988t = new Path();
        f986m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 501.0f ? w / 512.0f : h / 501.0f;
        m833r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 501.0f)) / 2.0f) + dy);
        f986m.reset();
        f986m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.38f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.translate(1.8f * od, -9.75f * od);
        c.save();
        f988t.reset();
        f988t.moveTo(-1.77f, 269.73f);
        f988t.cubicTo(-1.77f, 269.73f, 211.7f, 38.57f, 244.72f, 12.62f);
        f988t.cubicTo(277.74f, -13.32f, 499.47f, 144.71f, 510.08f, 252.04f);
        f988t.cubicTo(517.88f, 330.89f, 348.51f, 481.43f, 240.4f, 511.5f);
        f988t.cubicTo(196.76f, 467.86f, -1.77f, 269.73f, -1.77f, 269.73f);
        f988t.transform(f986m);
        c.drawPath(f988t, f987p);
        c.drawPath(f988t, ps);
        c.restore();
        m833r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m833r(new Integer[0]);
        c.restore();
    }

    private static void m833r(Integer... o) {
        f987p.reset();
        ps.reset();
        if (cf != null) {
            f987p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f987p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f987p.setStyle(Style.FILL);
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
