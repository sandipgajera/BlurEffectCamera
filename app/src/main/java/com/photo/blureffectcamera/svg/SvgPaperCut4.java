package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgPaperCut4 extends Svg {
    private static final Matrix f989m;
    private static float od;
    private static final Paint f990p;
    private static final Paint ps;
    private static final Path f991t;

    static {
        f990p = new Paint();
        ps = new Paint();
        f991t = new Path();
        f989m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 510.0f < h / 504.0f ? w / 510.0f : h / 504.0f;
        m834r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 510.0f)) / 2.0f) + dx, ((h - (od * 504.0f)) / 2.0f) + dy);
        f989m.reset();
        f989m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.0f, 1.0f);
        c.translate(0.5f * od, -6.67f * od);
        c.save();
        f990p.setColor(Color.parseColor("#010101"));
        f991t.reset();
        f991t.moveTo(246.01f, 6.88f);
        f991t.cubicTo(246.01f, 6.88f, 506.66f, 237.64f, 510.25f, 253.19f);
        f991t.cubicTo(513.83f, 268.73f, 371.55f, 531.77f, 236.44f, 510.25f);
        f991t.cubicTo(102.1f, 488.85f, 2.1f, 271.12f, -0.29f, 251.99f);
        f991t.cubicTo(35.58f, 218.51f, 246.01f, 6.88f, 246.01f, 6.88f);
        f991t.transform(f989m);
        c.drawPath(f991t, f990p);
        c.drawPath(f991t, ps);
        c.restore();
        m834r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f990p.setColor(Color.parseColor("#010101"));
        c.restore();
        m834r(new Integer[0]);
        c.restore();
    }

    private static void m834r(Integer... o) {
        f990p.reset();
        ps.reset();
        if (cf != null) {
            f990p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f990p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f990p.setStyle(Style.FILL);
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
