package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;

public class SvgPuzzle extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f998m;
    private static float od;
    private static final Paint f999p;
    private static final Paint ps;
    private static final Path f1000t;

    static {
        f999p = new Paint();
        ps = new Paint();
        f1000t = new Path();
        f998m = new Matrix();
        cf = null;
    }

    public void drawStroke(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        isStroke = true;
        draw(c, w, h, dx, dy, clearMode);
        isStroke = false;
    }

    public static void setColorTint(int color) {
        cf = new PorterDuffColorFilter(color, Mode.SRC_IN);
    }

    public static void clearColorTint(int color) {
        cf = null;
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 512.0f ? w / 512.0f : h / 512.0f;
        m837r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f998m.reset();
        f998m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.05f, 1.05f);
        c.save();
        c.save();
        c.save();
        f999p.setColor(Color.parseColor("#000000"));
        f1000t.reset();
        f1000t.moveTo(423.66f, 234.36f);
        f1000t.cubicTo(411.22f, 234.35f, 399.65f, 237.81f, 389.79f, 243.82f);
        f1000t.cubicTo(386.08f, 246.09f, 381.44f, 246.17f, 377.65f, 244.04f);
        f1000t.cubicTo(373.86f, 241.91f, 371.53f, 237.9f, 371.53f, 233.56f);
        f1000t.lineTo(371.53f, 155.4f);
        f1000t.cubicTo(371.53f, 143.37f, 361.42f, 133.7f, 349.39f, 133.7f);
        f1000t.lineTo(253.64f, 133.7f);
        f1000t.cubicTo(249.39f, 133.7f, 245.45f, 131.45f, 243.29f, 127.79f);
        f1000t.cubicTo(241.13f, 124.13f, 241.06f, 119.6f, 243.12f, 115.87f);
        f1000t.cubicTo(248.34f, 106.45f, 251.31f, 95.58f, 251.31f, 84.05f);
        f1000t.cubicTo(251.31f, 47.82f, 221.94f, 18.43f, 185.69f, 18.43f);
        f1000t.cubicTo(149.46f, 18.43f, 120.09f, 47.83f, 120.09f, 84.06f);
        f1000t.cubicTo(120.09f, 95.59f, 123.06f, 106.45f, 128.28f, 115.88f);
        f1000t.cubicTo(130.34f, 119.6f, 130.28f, 124.13f, 128.12f, 127.8f);
        f1000t.cubicTo(125.95f, 131.46f, 122.01f, 133.7f, 117.76f, 133.7f);
        f1000t.lineTo(22.01f, 133.7f);
        f1000t.cubicTo(9.98f, 133.7f, 0.0f, 143.37f, 0.0f, 155.4f);
        f1000t.lineTo(0.0f, 231.64f);
        f1000t.cubicTo(0.0f, 235.87f, 2.23f, 239.79f, 5.86f, 241.96f);
        f1000t.cubicTo(9.49f, 244.12f, 14.0f, 244.23f, 17.72f, 242.21f);
        f1000t.cubicTo(26.99f, 237.2f, 37.6f, 234.35f, 48.88f, 234.36f);
        f1000t.cubicTo(85.11f, 234.35f, 114.49f, 263.73f, 114.49f, 299.97f);
        f1000t.cubicTo(114.49f, 336.21f, 85.11f, 365.59f, 48.88f, 365.58f);
        f1000t.cubicTo(37.6f, 365.58f, 26.99f, 362.73f, 17.72f, 357.72f);
        f1000t.cubicTo(14.0f, 355.7f, 9.49f, 355.8f, 5.85f, 357.97f);
        f1000t.cubicTo(2.22f, 360.14f, 0.0f, 364.06f, 0.0f, 368.29f);
        f1000t.lineTo(0.0f, 449.05f);
        f1000t.cubicTo(0.0f, 461.09f, 9.98f, 470.84f, 22.01f, 470.84f);
        f1000t.lineTo(349.39f, 470.84f);
        f1000t.cubicTo(361.41f, 470.84f, 371.53f, 461.09f, 371.53f, 449.05f);
        f1000t.lineTo(371.53f, 366.37f);
        f1000t.cubicTo(371.53f, 362.03f, 373.87f, 358.03f, 377.65f, 355.9f);
        f1000t.cubicTo(381.44f, 353.77f, 386.08f, 353.84f, 389.79f, 356.1f);
        f1000t.cubicTo(399.65f, 362.12f, 411.22f, 365.57f, 423.66f, 365.57f);
        f1000t.cubicTo(459.9f, 365.58f, 489.26f, 336.21f, 489.26f, 299.97f);
        f1000t.cubicTo(489.26f, 263.73f, 459.9f, 234.35f, 423.66f, 234.36f);
        f1000t.transform(f998m);
        if (clearMode) {
            f999p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1000t, ps);
        } else {
            c.drawPath(f1000t, f999p);
            c.drawPath(f1000t, ps);
        }
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f999p.setColor(Color.parseColor("#000000"));
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f999p.setColor(Color.parseColor("#000000"));
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f999p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m837r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m837r(new Integer[0]);
        c.restore();
    }

    private static void m837r(Integer... o) {
        f999p.reset();
        ps.reset();
        if (cf != null) {
            f999p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f999p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f999p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f999p.setColor(Color.parseColor("#000000"));
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
