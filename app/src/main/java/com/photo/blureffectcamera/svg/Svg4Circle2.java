package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg4Circle2 extends Svg {
    private static final Matrix f839m;
    private static float od;
    private static final Paint f840p;
    private static final Paint ps;
    private static final Path f841t;

    static {
        f840p = new Paint();
        ps = new Paint();
        f841t = new Path();
        f839m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 405.0f ? w / 512.0f : h / 405.0f;
        m784r(new Integer[0]);
        c.save();
        c.translate((((w - (od * 512.0f)) / 2.0f) + dx) - 4.0f, ((h - (od * 405.0f)) / 2.0f) + dy);
        f839m.reset();
        f839m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.21f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        f841t.reset();
        f841t.moveTo(507.32f, 404.93f);
        f841t.lineTo(-0.09f, 405.0f);
        f841t.lineTo(-0.46f, 54.45f);
        f841t.cubicTo(-0.46f, 54.45f, 45.01f, 20.27f, 106.13f, 7.68f);
        f841t.cubicTo(149.87f, -3.4f, 199.39f, -0.41f, 244.69f, 8.91f);
        f841t.cubicTo(308.86f, 22.11f, 375.9f, 57.2f, 420.8f, 106.59f);
        f841t.cubicTo(456.44f, 145.8f, 531.4f, 226.17f, 507.32f, 404.93f);
        f841t.transform(f839m);
        c.drawPath(f841t, f840p);
        c.drawPath(f841t, ps);
        c.restore();
        m784r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m784r(new Integer[0]);
        c.restore();
    }

    private static void m784r(Integer... o) {
        f840p.reset();
        ps.reset();
        if (cf != null) {
            f840p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f840p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f840p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
//            switch (i.intValue()) {
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
//            }
        }
    }
}
