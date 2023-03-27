package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg4Circle4 extends Svg {
    private static final Matrix f845m;
    private static float od;
    private static final Paint f846p;
    private static final Paint ps;
    private static final Path f847t;

    static {
        f846p = new Paint();
        ps = new Paint();
        f847t = new Path();
        f845m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 505.0f < h / 509.0f ? w / 505.0f : h / 509.0f;
        m786r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 505.0f)) / 2.0f) + dx, (((h - (od * 509.0f)) / 2.0f) + dy) + 8.0f);
        f845m.reset();
        f845m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.31f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        f847t.reset();
        f847t.moveTo(505.13f, 510.13f);
        f847t.lineTo(174.9f, 510.13f);
        f847t.cubicTo(174.9f, 510.13f, 181.3f, 466.75f, 180.05f, 419.08f);
        f847t.cubicTo(178.65f, 372.13f, 167.75f, 324.94f, 160.63f, 306.14f);
        f847t.cubicTo(149.72f, 270.56f, 122.11f, 219.27f, 80.34f, 175.14f);
        f847t.cubicTo(45.06f, 137.08f, 18.26f, 120.72f, 0.16f, 108.66f);
        f847t.cubicTo(18.93f, 91.16f, 36.43f, 70.39f, 49.47f, 51.34f);
        f847t.cubicTo(64.46f, 29.45f, 74.41f, 9.7f, 79.06f, 0.05f);
        f847t.cubicTo(94.33f, 9.55f, 132.21f, 34.01f, 188.48f, 49.6f);
        f847t.cubicTo(216.29f, 57.3f, 249.52f, 61.44f, 287.33f, 63.41f);
        f847t.cubicTo(314.14f, 64.8f, 353.01f, 62.54f, 398.03f, 48.32f);
        f847t.cubicTo(448.48f, 32.63f, 492.25f, 6.38f, 504.43f, -2.5f);
        f847t.lineTo(505.13f, 510.13f);
        f847t.transform(f845m);
        c.drawPath(f847t, f846p);
        c.drawPath(f847t, ps);
        c.restore();
        m786r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m786r(new Integer[0]);
        c.restore();
    }

    private static void m786r(Integer... o) {
        f846p.reset();
        ps.reset();
        if (cf != null) {
            f846p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f846p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f846p.setStyle(Style.FILL);
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
