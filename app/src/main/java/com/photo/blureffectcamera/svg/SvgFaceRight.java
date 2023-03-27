package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

import com.photo.blureffectcamera.lyrebirdlib.BlurBuilderNormal;

public class SvgFaceRight extends Svg {
    private static final Matrix f923m;
    private static float od;
    private static final Paint f924p;
    private static final Paint ps;
    private static final Path f925t;

    static {
        f924p = new Paint();
        ps = new Paint();
        f925t = new Path();
        f923m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 229.0f < h / 512.0f ? w / 229.0f : h / 512.0f;
        m812r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 229.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f923m.reset();
        f923m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.02f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        c.restore();
        m812r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.save();
        f924p.setColor(Color.parseColor("#010101"));
        f925t.reset();
        f925t.moveTo(15.96f, 512.3f);
        f925t.cubicTo(15.96f, 512.3f, 39.5f, 489.79f, 20.06f, 455.69f);
        f925t.cubicTo(3.69f, 429.09f, 27.56f, 414.76f, 34.04f, 410.67f);
        f925t.cubicTo(40.52f, 405.9f, 23.47f, 393.28f, 20.06f, 386.46f);
        f925t.cubicTo(15.28f, 376.91f, 49.05f, 354.06f, 36.77f, 334.62f);
        f925t.cubicTo(15.96f, 316.54f, 4.31f, 309.11f, 0.96f, 293.69f);
        f925t.cubicTo(-0.75f, 285.85f, -7.23f, 266.07f, 28.58f, 240.15f);
        f925t.cubicTo(58.59f, 218.33f, 108.04f, 184.9f, 112.82f, 158.3f);
        f925t.cubicTo(115.55f, 141.25f, 118.11f, 138.0f, 111.11f, 114.65f);
        f925t.cubicTo(107.02f, 101.01f, 130.89f, 3.81f, 146.92f, BlurBuilderNormal.BITMAP_SCALE);
        f925t.cubicTo(148.63f, -0.28f, 229.79f, BlurBuilderNormal.BITMAP_SCALE, 229.79f, BlurBuilderNormal.BITMAP_SCALE);
        f925t.lineTo(229.79f, 512.38f);
        f925t.lineTo(15.96f, 512.3f);
        f925t.transform(f923m);
        c.drawPath(f925t, f924p);
        c.drawPath(f925t, ps);
        c.restore();
        m812r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f924p.setColor(Color.parseColor("#010101"));
        c.restore();
        m812r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f924p.setColor(Color.parseColor("#010101"));
        c.restore();
        m812r(new Integer[0]);
        c.restore();
    }

    private static void m812r(Integer... o) {
        f924p.reset();
        ps.reset();
        if (cf != null) {
            f924p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f924p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f924p.setStyle(Style.FILL);
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
