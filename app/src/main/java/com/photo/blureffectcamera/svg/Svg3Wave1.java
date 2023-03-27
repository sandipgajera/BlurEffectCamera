package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg3Wave1 extends Svg {
    private static final Matrix f827m;
    private static float od;
    private static final Paint f828p;
    private static final Paint ps;
    private static final Path f829t;

    static {
        f828p = new Paint();
        ps = new Paint();
        f829t = new Path();
        f827m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 213.0f ? w / 512.0f : h / 213.0f;
        m780r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 213.0f)) / 2.0f) + dy);
        f827m.reset();
        f827m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.0f, 0.05f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f829t.reset();
        f829t.moveTo(0.0f, 0.0f);
        f829t.lineTo(512.4f, 0.0f);
        f829t.lineTo(512.4f, 174.44f);
        f829t.cubicTo(512.4f, 174.44f, 385.86f, 108.31f, 297.86f, 133.83f);
        f829t.cubicTo(229.47f, 153.79f, 198.57f, 191.28f, 87.48f, 209.85f);
        f829t.cubicTo(34.89f, 217.66f, 0.0f, 208.81f, 0.0f, 208.81f);
        f829t.lineTo(0.0f, 0.0f);
        f829t.transform(f827m);
        c.drawPath(f829t, f828p);
        c.drawPath(f829t, ps);
        c.restore();
        m780r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m780r(new Integer[0]);
        c.restore();
    }

    private static void m780r(Integer... o) {
        f828p.reset();
        ps.reset();
        if (cf != null) {
            f828p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f828p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f828p.setStyle(Style.FILL);
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
