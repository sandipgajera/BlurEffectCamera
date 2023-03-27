package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg5Circle2 extends Svg {
    private static final Matrix f851m;
    private static float od;
    private static final Paint f852p;
    private static final Paint ps;
    private static final Path f853t;

    static {
        f852p = new Paint();
        ps = new Paint();
        f853t = new Path();
        f851m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 511.0f < h / 509.0f ? w / 511.0f : h / 509.0f;
        m788r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 511.0f)) / 2.0f) + dx, ((h - (od * 509.0f)) / 2.0f) + dy);
        f851m.reset();
        f851m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.28f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        f852p.setColor(Color.parseColor("#020202"));
        f853t.reset();
        f853t.moveTo(511.45f, 509.03f);
        f853t.lineTo(511.45f, 0.0f);
        f853t.cubicTo(511.45f, 0.0f, 410.81f, 75.48f, 328.06f, 61.94f);
        f853t.cubicTo(337.74f, 105.0f, 207.1f, 124.36f, 180.48f, 186.77f);
        f853t.cubicTo(134.03f, 253.55f, 64.36f, 342.58f, 63.87f, 340.64f);
        f853t.cubicTo(70.16f, 379.35f, 39.28f, 473.3f, 0.0f, 510.0f);
        f853t.lineTo(511.45f, 509.03f);
        f853t.transform(f851m);
        c.drawPath(f853t, f852p);
        c.drawPath(f853t, ps);
        c.restore();
        m788r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f852p.setColor(Color.parseColor("#020202"));
        c.restore();
        m788r(new Integer[0]);
        c.restore();
    }

    private static void m788r(Integer... o) {
        f852p.reset();
        ps.reset();
        if (cf != null) {
            f852p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f852p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f852p.setStyle(Style.FILL);
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
