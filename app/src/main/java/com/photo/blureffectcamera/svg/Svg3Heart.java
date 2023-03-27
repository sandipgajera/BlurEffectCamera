package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg3Heart extends Svg {
    private static final Matrix f818m;
    private static float od;
    private static final Paint f819p;
    private static final Paint ps;
    private static final Path f820t;

    static {
        f819p = new Paint();
        ps = new Paint();
        f820t = new Path();
        f818m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 487.0f ? w / 512.0f : h / 487.0f;
        m777r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 487.0f)) / 2.0f) + dy);
        f818m.reset();
        f818m.setScale(od, od);
        c.save();
        if (clearMode) {
            f819p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.0f, 0.19f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f819p.setColor(Color.parseColor("#030303"));
        f820t.reset();
        f820t.moveTo(417.68f, 16.27f);
        f820t.cubicTo(368.92f, -1.32f, 309.6f, 16.0f, 266.85f, 89.28f);
        f820t.cubicTo(244.57f, 21.31f, 180.9f, -17.31f, 107.53f, 8.63f);
        f820t.cubicTo(56.81f, 26.09f, 32.75f, 47.89f, 7.36f, 104.92f);
        f820t.cubicTo(-19.62f, 174.5f, 36.35f, 242.91f, 96.86f, 313.95f);
        f820t.cubicTo(159.1f, 374.84f, 245.35f, 444.43f, 258.34f, 485.93f);
        f820t.cubicTo(264.86f, 452.11f, 313.98f, 409.83f, 367.87f, 359.52f);
        f820t.cubicTo(423.52f, 311.6f, 487.02f, 264.52f, 500.72f, 215.06f);
        f820t.cubicTo(534.37f, 119.91f, 491.8f, 38.07f, 417.68f, 16.27f);
        f820t.transform(f818m);
        c.drawPath(f820t, f819p);
        c.drawPath(f820t, ps);
        c.restore();
        m777r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f819p.setColor(Color.parseColor("#030303"));
        c.restore();
        m777r(new Integer[0]);
        c.restore();
    }

    private static void m777r(Integer... o) {
        f819p.reset();
        ps.reset();
        if (cf != null) {
            f819p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f819p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f819p.setStyle(Style.FILL);
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
