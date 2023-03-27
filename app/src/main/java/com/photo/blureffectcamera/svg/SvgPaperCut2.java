package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgPaperCut2 extends Svg {
    private static final Matrix f983m;
    private static float od;
    private static final Paint f984p;
    private static final Paint ps;
    private static final Path f985t;

    static {
        f984p = new Paint();
        ps = new Paint();
        f985t = new Path();
        f983m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 505.0f < h / 512.0f ? w / 505.0f : h / 512.0f;
        m832r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 505.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f983m.reset();
        f983m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.25f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.translate(-4.0f * od, 0.5f * od);
        c.save();
        f984p.setColor(Color.parseColor("#020202"));
        f985t.reset();
        f985t.moveTo(259.89f, -0.48f);
        f985t.cubicTo(259.89f, -0.48f, 5.69f, 240.05f, 4.51f, 253.71f);
        f985t.cubicTo(3.32f, 267.37f, 113.78f, 474.65f, 229.0f, 507.91f);
        f985t.cubicTo(343.45f, 540.94f, 477.26f, 337.46f, 509.33f, 248.96f);
        f985t.cubicTo(505.77f, 244.21f, 259.89f, -0.48f, 259.89f, -0.48f);
        f985t.transform(f983m);
        c.drawPath(f985t, f984p);
        c.drawPath(f985t, ps);
        c.restore();
        m832r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f984p.setColor(Color.parseColor("#020202"));
        c.restore();
        m832r(new Integer[0]);
        c.restore();
    }

    private static void m832r(Integer... o) {
        f984p.reset();
        ps.reset();
        if (cf != null) {
            f984p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f984p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f984p.setStyle(Style.FILL);
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
