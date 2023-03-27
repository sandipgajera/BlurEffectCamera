package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgStar extends Svg {
    private static final Matrix f1004m;
    private static float od;
    private static final Paint f1005p;
    private static final Paint ps;
    private static final Path f1006t;

    static {
        f1005p = new Paint();
        ps = new Paint();
        f1006t = new Path();
        f1004m = new Matrix();
    }

    public void drawStroke(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        isStroke = true;
        draw(c, w, h, dx, dy, clearMode);
        isStroke = false;
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 512.0f ? w / 512.0f : h / 512.0f;
        m839r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f1004m.reset();
        f1004m.setScale(od * 13.73f, od * 13.73f);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.01f * od, 0.0f);
        c.save();
        c.save();
        f1005p.setColor(Color.parseColor("#000000"));
        f1006t.reset();
        f1006t.moveTo(36.68f, 16.34f);
        f1006t.lineTo(29.12f, 23.72f);
        f1006t.lineTo(30.9f, 34.13f);
        f1006t.cubicTo(31.03f, 34.88f, 30.72f, 35.64f, 30.11f, 36.09f);
        f1006t.cubicTo(29.76f, 36.34f, 29.34f, 36.47f, 28.93f, 36.47f);
        f1006t.cubicTo(28.61f, 36.47f, 28.29f, 36.4f, 28.0f, 36.24f);
        f1006t.lineTo(18.64f, 31.32f);
        f1006t.lineTo(9.29f, 36.24f);
        f1006t.cubicTo(8.61f, 36.6f, 7.8f, 36.54f, 7.18f, 36.09f);
        f1006t.cubicTo(6.57f, 35.64f, 6.26f, 34.89f, 6.39f, 34.13f);
        f1006t.lineTo(8.17f, 23.72f);
        f1006t.lineTo(0.6f, 16.34f);
        f1006t.cubicTo(0.06f, 15.81f, -0.14f, 15.01f, 0.1f, 14.29f);
        f1006t.cubicTo(0.33f, 13.57f, 0.96f, 13.04f, 1.71f, 12.93f);
        f1006t.lineTo(12.17f, 11.41f);
        f1006t.lineTo(16.85f, 1.93f);
        f1006t.cubicTo(17.19f, 1.25f, 17.88f, 0.81f, 18.64f, 0.81f);
        f1006t.cubicTo(19.41f, 0.81f, 20.1f, 1.25f, 20.44f, 1.93f);
        f1006t.lineTo(25.12f, 11.41f);
        f1006t.lineTo(35.58f, 12.93f);
        f1006t.cubicTo(36.33f, 13.04f, 36.95f, 13.56f, 37.19f, 14.29f);
        f1006t.cubicTo(37.42f, 15.01f, 37.23f, 15.81f, 36.68f, 16.34f);
        f1006t.transform(f1004m);
        if (clearMode) {
            f1005p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1006t, ps);
        } else {
            c.drawPath(f1006t, f1005p);
            c.drawPath(f1006t, ps);
        }
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1005p.setColor(Color.parseColor("#000000"));
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1005p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m839r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m839r(new Integer[0]);
        c.restore();
    }

    private static void m839r(Integer... o) {
        f1005p.reset();
        ps.reset();
        if (cf != null) {
            f1005p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1005p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1005p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f1005p.setColor(Color.parseColor("#000000"));
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_BACKGROUND /*1*/:
//                    ps.setStrokeJoin(Join.MITER);
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_BLUR /*2*/:
//                    ps.setStrokeMiter(4.0f * od);
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_FRAME /*3*/:
//                    ps.setStrokeCap(Cap.BUTT);
//                    break;
//                case SquareActivity.TAB_INDEX_SQUARE_FX /*4*/:
//                    ps.setColor(Color.argb(0, 0, 0, 0));
//                    break;
//                default:
//                    break;
            }
        }
    }
}
