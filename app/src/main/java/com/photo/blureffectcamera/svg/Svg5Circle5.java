package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg5Circle5 extends Svg {
    private static final Matrix f860m;
    private static float od;
    private static final Paint f861p;
    private static final Paint ps;
    private static final Path f862t;

    static {
        f861p = new Paint();
        ps = new Paint();
        f862t = new Path();
        f860m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 511.0f < h / 511.0f ? w / 511.0f : h / 511.0f;
        m791r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 511.0f)) / 2.0f) + dx, ((h - (od * 511.0f)) / 2.0f) + dy);
        f860m.reset();
        f860m.setScale(od, od);
        c.save();
        if (clearMode) {
            f861p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f862t.reset();
        f862t.moveTo(511.99f, 256.0f);
        f862t.cubicTo(511.99f, 133.64f, 426.15f, 31.36f, 311.41f, 6.03f);
        f862t.cubicTo(293.56f, 2.09f, 275.03f, 0.0f, 256.0f, 0.0f);
        f862t.cubicTo(116.25f, 0.0f, 2.67f, 111.98f, 0.06f, 251.11f);
        f862t.cubicTo(0.03f, 252.74f, 0.0f, 254.36f, 0.0f, 256.0f);
        f862t.cubicTo(0.0f, 397.38f, 114.61f, 511.99f, 256.0f, 511.99f);
        f862t.cubicTo(292.7f, 511.99f, 327.6f, 504.25f, 359.16f, 490.33f);
        f862t.cubicTo(447.54f, 451.37f, 509.71f, 363.88f, 511.92f, 261.62f);
        f862t.cubicTo(511.96f, 259.75f, 511.99f, 257.88f, 511.99f, 256.0f);
        f862t.transform(f860m);
        c.drawPath(f862t, f861p);
        c.drawPath(f862t, ps);
        c.restore();
        m791r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m791r(new Integer[0]);
        c.restore();
    }

    private static void m791r(Integer... o) {
        f861p.reset();
        ps.reset();
        if (cf != null) {
            f861p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f861p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f861p.setStyle(Style.FILL);
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
