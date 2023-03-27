package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg3Wave3 extends Svg {
    private static final Matrix f833m;
    private static float od;
    private static final Paint f834p;
    private static final Paint ps;
    private static final Path f835t;

    static {
        f834p = new Paint();
        ps = new Paint();
        f835t = new Path();
        f833m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 511.0f < h / 204.0f ? w / 511.0f : h / 204.0f;
        m782r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 511.0f)) / 2.0f) + dx, ((h - (od * 204.0f)) / 2.0f) + dy);
        f833m.reset();
        f833m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f835t.reset();
        f835t.moveTo(0.0f, 82.12f);
        f835t.cubicTo(0.0f, 82.12f, 48.44f, 95.78f, 123.99f, 76.72f);
        f835t.cubicTo(184.68f, 60.35f, 230.81f, 34.88f, 263.54f, 18.61f);
        f835t.cubicTo(304.62f, -2.06f, 332.08f, -0.02f, 358.03f, 0.11f);
        f835t.cubicTo(391.48f, 1.67f, 427.95f, 12.91f, 449.81f, 21.64f);
        f835t.cubicTo(461.92f, 25.54f, 474.94f, 31.4f, 484.93f, 35.7f);
        f835t.cubicTo(500.69f, 42.47f, 511.51f, 48.26f, 511.51f, 48.26f);
        f835t.lineTo(511.51f, 204.2f);
        f835t.cubicTo(511.51f, 204.2f, 87.47f, 204.2f, 0.17f, 204.2f);
        f835t.cubicTo(0.52f, 204.55f, 0.0f, 82.12f, 0.0f, 82.12f);
        f835t.transform(f833m);
        c.drawPath(f835t, f834p);
        c.drawPath(f835t, ps);
        c.restore();
        m782r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m782r(new Integer[0]);
        c.restore();
    }

    private static void m782r(Integer... o) {
        f834p.reset();
        ps.reset();
        if (cf != null) {
            f834p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f834p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f834p.setStyle(Style.FILL);
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
