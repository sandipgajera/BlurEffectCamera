package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg5Circle1 extends Svg {
    private static final Matrix f848m;
    private static float od;
    private static final Paint f849p;
    private static final Paint ps;
    private static final Path f850t;

    static {
        f849p = new Paint();
        ps = new Paint();
        f850t = new Path();
        f848m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 510.0f ? w / 512.0f : h / 510.0f;
        m787r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 510.0f)) / 2.0f) + dy);
        f848m.reset();
        f848m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.05f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        f849p.setColor(Color.parseColor("#020202"));
        f850t.reset();
        f850t.moveTo(0.72f, 65.42f);
        f850t.cubicTo(0.72f, 65.42f, 11.17f, 52.9f, 30.97f, 40.1f);
        f850t.cubicTo(61.48f, 23.03f, 122.36f, -3.78f, 171.62f, 0.44f);
        f850t.cubicTo(199.72f, 29.53f, 270.31f, 117.86f, 270.31f, 117.86f);
        f850t.lineTo(332.42f, 209.56f);
        f850t.lineTo(407.36f, 274.63f);
        f850t.lineTo(511.86f, 336.74f);
        f850t.cubicTo(511.86f, 336.74f, 514.5f, 363.69f, 504.14f, 401.15f);
        f850t.cubicTo(493.59f, 441.72f, 471.42f, 489.33f, 446.15f, 509.5f);
        f850t.lineTo(0.0f, 510.27f);
        f850t.lineTo(0.72f, 65.42f);
        f850t.transform(f848m);
        c.drawPath(f850t, f849p);
        c.drawPath(f850t, ps);
        c.restore();
        m787r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f849p.setColor(Color.parseColor("#020202"));
        c.restore();
        m787r(new Integer[0]);
        c.restore();
    }

    private static void m787r(Integer... o) {
        f849p.reset();
        ps.reset();
        if (cf != null) {
            f849p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f849p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f849p.setStyle(Style.FILL);
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
