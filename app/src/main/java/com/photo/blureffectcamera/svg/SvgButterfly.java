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

public class SvgButterfly extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f893m;
    private static float od;
    private static final Paint f894p;
    private static final Paint ps;
    private static final Path f895t;

    static {
        f894p = new Paint();
        ps = new Paint();
        f895t = new Path();
        f893m = new Matrix();
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
        m802r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f893m.reset();
        f893m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.04f, 1.04f);
        c.save();
        f895t.reset();
        f895t.moveTo(481.45f, 72.88f);
        f895t.cubicTo(467.9f, 58.95f, 446.79f, 55.62f, 429.62f, 64.73f);
        f895t.cubicTo(371.1f, 95.71f, 303.93f, 150.16f, 262.71f, 244.29f);
        f895t.cubicTo(266.77f, 154.39f, 266.55f, 160.12f, 266.49f, 158.77f);
        f895t.cubicTo(266.27f, 153.93f, 264.28f, 149.61f, 261.21f, 146.33f);
        f895t.cubicTo(269.07f, 139.24f, 294.21f, 115.29f, 314.16f, 82.04f);
        f895t.cubicTo(329.78f, 56.01f, 337.9f, 37.59f, 341.66f, 27.91f);
        f895t.cubicTo(342.41f, 25.98f, 342.27f, 23.82f, 341.27f, 22.01f);
        f895t.cubicTo(340.27f, 20.2f, 338.52f, 18.91f, 336.49f, 18.52f);
        f895t.lineTo(330.75f, 17.4f);
        f895t.cubicTo(327.41f, 16.74f, 324.1f, 18.59f, 322.9f, 21.77f);
        f895t.cubicTo(318.31f, 33.85f, 307.15f, 62.56f, 300.31f, 74.99f);
        f895t.cubicTo(284.59f, 103.51f, 265.92f, 127.2f, 256.28f, 138.69f);
        f895t.cubicTo(253.95f, 141.47f, 250.51f, 143.08f, 246.88f, 143.08f);
        f895t.lineTo(246.86f, 143.08f);
        f895t.cubicTo(243.28f, 143.08f, 239.88f, 141.49f, 237.58f, 138.75f);
        f895t.cubicTo(227.95f, 127.28f, 209.25f, 103.56f, 193.51f, 74.99f);
        f895t.cubicTo(186.66f, 62.56f, 175.51f, 33.85f, 170.92f, 21.77f);
        f895t.cubicTo(169.71f, 18.59f, 166.4f, 16.74f, 163.06f, 17.4f);
        f895t.lineTo(157.32f, 18.52f);
        f895t.cubicTo(155.29f, 18.91f, 153.54f, 20.2f, 152.54f, 22.01f);
        f895t.cubicTo(151.55f, 23.82f, 151.4f, 25.98f, 152.15f, 27.91f);
        f895t.cubicTo(155.91f, 37.59f, 164.03f, 56.01f, 179.65f, 82.04f);
        f895t.cubicTo(199.74f, 115.53f, 225.12f, 139.59f, 232.78f, 146.49f);
        f895t.cubicTo(229.27f, 150.17f, 227.07f, 155.06f, 227.33f, 160.54f);
        f895t.lineTo(231.11f, 244.33f);
        f895t.cubicTo(189.88f, 150.16f, 122.7f, 95.71f, 64.19f, 64.71f);
        f895t.cubicTo(47.01f, 55.61f, 25.9f, 58.92f, 12.36f, 72.86f);
        f895t.cubicTo(-1.18f, 86.79f, -3.89f, 107.99f, 5.7f, 124.89f);
        f895t.lineTo(10.86f, 133.97f);
        f895t.cubicTo(25.84f, 160.35f, 32.67f, 190.57f, 30.49f, 220.81f);
        f895t.cubicTo(29.53f, 234.17f, 34.16f, 247.32f, 43.28f, 257.12f);
        f895t.cubicTo(52.4f, 266.92f, 65.18f, 272.49f, 78.57f, 272.49f);
        f895t.lineTo(131.08f, 272.49f);
        f895t.cubicTo(111.36f, 277.07f, 90.9f, 283.62f, 71.64f, 293.14f);
        f895t.cubicTo(63.74f, 297.05f, 57.54f, 303.73f, 54.24f, 311.91f);
        f895t.lineTo(23.29f, 388.68f);
        f895t.cubicTo(17.36f, 403.41f, 21.68f, 420.28f, 33.95f, 430.34f);
        f895t.lineTo(80.38f, 468.4f);
        f895t.cubicTo(91.0f, 477.1f, 105.67f, 478.99f, 118.16f, 473.26f);
        f895t.cubicTo(148.12f, 459.53f, 200.82f, 427.34f, 220.3f, 365.18f);
        f895t.lineTo(232.68f, 279.12f);
        f895t.lineTo(238.67f, 411.65f);
        f895t.cubicTo(238.87f, 415.85f, 242.25f, 419.33f, 246.54f, 419.52f);
        f895t.cubicTo(251.09f, 419.72f, 254.95f, 416.2f, 255.16f, 411.65f);
        f895t.lineTo(261.14f, 279.16f);
        f895t.lineTo(273.52f, 365.18f);
        f895t.cubicTo(292.98f, 427.33f, 345.69f, 459.52f, 375.66f, 473.28f);
        f895t.cubicTo(388.13f, 479.0f, 402.81f, 477.11f, 413.43f, 468.41f);
        f895t.lineTo(459.86f, 430.34f);
        f895t.cubicTo(472.13f, 420.3f, 476.45f, 403.41f, 470.53f, 388.69f);
        f895t.lineTo(439.58f, 311.92f);
        f895t.cubicTo(436.28f, 303.73f, 430.08f, 297.05f, 422.19f, 293.15f);
        f895t.cubicTo(402.92f, 283.62f, 382.45f, 277.07f, 362.72f, 272.49f);
        f895t.lineTo(415.25f, 272.49f);
        f895t.cubicTo(428.64f, 272.49f, 441.42f, 266.92f, 450.54f, 257.12f);
        f895t.cubicTo(459.66f, 247.33f, 464.28f, 234.18f, 463.31f, 220.83f);
        f895t.cubicTo(461.14f, 190.57f, 467.98f, 160.35f, 482.95f, 133.97f);
        f895t.lineTo(488.12f, 124.91f);
        f895t.cubicTo(497.71f, 108.0f, 494.99f, 86.81f, 481.45f, 72.88f);
        f895t.transform(f893m);
        if (clearMode) {
            f894p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f895t, ps);
        } else {
            c.drawPath(f895t, f894p);
            c.drawPath(f895t, ps);
        }
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m802r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m802r(new Integer[0]);
        c.restore();
    }

    private static void m802r(Integer... o) {
        f894p.reset();
        ps.reset();
        if (cf != null) {
            f894p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f894p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f894p.setStyle(Style.FILL);
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
