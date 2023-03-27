package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgWave3 extends Svg {
    private static final Matrix f1058m;
    private static float od;
    private static final Paint f1059p;
    private static final Paint ps;
    private static final Path f1060t;

    static {
        f1059p = new Paint();
        ps = new Paint();
        f1060t = new Path();
        f1058m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 503.0f < h / 512.0f ? w / 503.0f : h / 512.0f;
        m857r(new Integer[0]);
        c.save();
        c.translate((((w - (od * 503.0f)) / 2.0f) + dx) + 9.0f, (((h - (od * 512.0f)) / 2.0f) + dy) - 6.0f);
        f1058m.reset();
        f1058m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.0f, 0.13f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f1060t.reset();
        f1060t.moveTo(51.3f, 455.93f);
        f1060t.cubicTo(51.3f, 455.93f, -31.91f, 393.42f, 14.33f, 350.49f);
        f1060t.cubicTo(33.18f, 337.53f, 50.6f, 333.5f, 57.57f, 331.38f);
        f1060t.cubicTo(109.17f, 307.93f, 88.85f, 243.43f, 88.56f, 202.05f);
        f1060t.cubicTo(87.6f, 162.65f, 62.64f, 17.67f, 50.27f, 0.0f);
        f1060t.cubicTo(50.86f, 0.0f, 503.25f, 0.0f, 503.25f, 0.0f);
        f1060t.lineTo(503.25f, 506.0f);
        f1060t.cubicTo(503.25f, 506.0f, 329.18f, 516.01f, 262.91f, 510.12f);
        f1060t.cubicTo(235.23f, 502.47f, 183.83f, 498.34f, 172.79f, 457.69f);
        f1060t.cubicTo(154.23f, 388.33f, 111.23f, 404.68f, 92.68f, 417.05f);
        f1060t.cubicTo(69.85f, 431.19f, 51.3f, 455.93f, 51.3f, 455.93f);
        f1060t.transform(f1058m);
        c.drawPath(f1060t, f1059p);
        c.drawPath(f1060t, ps);
        c.restore();
        m857r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m857r(new Integer[0]);
        c.restore();
    }

    private static void m857r(Integer... o) {
        f1059p.reset();
        ps.reset();
        if (cf != null) {
            f1059p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1059p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1059p.setStyle(Style.FILL);
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
