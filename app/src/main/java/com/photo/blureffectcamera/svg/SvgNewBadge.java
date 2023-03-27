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

public class SvgNewBadge extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f974m;
    private static float od;
    private static final Paint f975p;
    private static final Paint ps;
    private static final Path f976t;

    static {
        f975p = new Paint();
        ps = new Paint();
        f976t = new Path();
        f974m = new Matrix();
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
        m829r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f974m.reset();
        f974m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(0.84f, 0.84f);
        c.save();
        c.save();
        f975p.setColor(Color.parseColor("#000000"));
        f976t.reset();
        f976t.moveTo(608.07f, 333.25f);
        f976t.lineTo(563.09f, 261.89f);
        f976t.cubicTo(559.53f, 256.23f, 558.33f, 249.4f, 559.74f, 242.87f);
        f976t.lineTo(577.6f, 160.43f);
        f976t.cubicTo(580.38f, 147.58f, 572.95f, 134.72f, 560.43f, 130.7f);
        f976t.lineTo(480.11f, 104.94f);
        f976t.cubicTo(473.74f, 102.9f, 468.43f, 98.44f, 465.31f, 92.53f);
        f976t.lineTo(426.0f, 17.9f);
        f976t.cubicTo(419.88f, 6.27f, 405.92f, 1.19f, 393.75f, 6.16f);
        f976t.lineTo(315.65f, 38.06f);
        f976t.cubicTo(309.46f, 40.58f, 302.53f, 40.58f, 296.34f, 38.06f);
        f976t.lineTo(218.26f, 6.17f);
        f976t.cubicTo(206.09f, 1.2f, 192.13f, 6.28f, 186.0f, 17.91f);
        f976t.lineTo(146.69f, 92.53f);
        f976t.cubicTo(143.57f, 98.44f, 138.26f, 102.9f, 131.89f, 104.94f);
        f976t.lineTo(51.57f, 130.7f);
        f976t.cubicTo(39.05f, 134.72f, 31.62f, 147.58f, 34.41f, 160.43f);
        f976t.lineTo(52.25f, 242.87f);
        f976t.cubicTo(53.67f, 249.4f, 52.46f, 256.23f, 48.9f, 261.89f);
        f976t.lineTo(3.93f, 333.25f);
        f976t.cubicTo(-3.08f, 344.38f, -0.49f, 359.0f, 9.9f, 367.06f);
        f976t.lineTo(76.56f, 418.74f);
        f976t.cubicTo(81.85f, 422.84f, 85.32f, 428.84f, 86.22f, 435.47f);
        f976t.lineTo(97.65f, 519.04f);
        f976t.cubicTo(99.42f, 532.07f, 110.81f, 541.61f, 123.94f, 541.1f);
        f976t.lineTo(208.22f, 537.84f);
        f976t.cubicTo(214.9f, 537.58f, 221.42f, 539.95f, 226.38f, 544.44f);
        f976t.lineTo(288.84f, 601.12f);
        f976t.cubicTo(298.58f, 609.95f, 313.44f, 609.95f, 323.17f, 601.12f);
        f976t.lineTo(385.64f, 544.44f);
        f976t.cubicTo(390.59f, 539.95f, 397.11f, 537.58f, 403.79f, 537.84f);
        f976t.lineTo(488.07f, 541.1f);
        f976t.cubicTo(501.21f, 541.61f, 512.59f, 532.06f, 514.37f, 519.04f);
        f976t.lineTo(525.79f, 435.47f);
        f976t.cubicTo(526.7f, 428.84f, 530.16f, 422.84f, 535.45f, 418.74f);
        f976t.lineTo(602.11f, 367.06f);
        f976t.cubicTo(612.5f, 359.0f, 615.08f, 344.38f, 608.07f, 333.25f);
        f976t.moveTo(235.23f, 407.92f);
        f976t.lineTo(160.8f, 355.93f);
        f976t.lineTo(191.39f, 426.83f);
        f976t.lineTo(171.0f, 435.63f);
        f976t.lineTo(124.08f, 326.91f);
        f976t.lineTo(145.44f, 317.69f);
        f976t.lineTo(221.28f, 371.09f);
        f976t.lineTo(189.94f, 298.49f);
        f976t.lineTo(210.32f, 289.69f);
        f976t.lineTo(257.26f, 398.41f);
        f976t.lineTo(235.23f, 407.92f);
        f976t.moveTo(280.47f, 388.39f);
        f976t.lineTo(233.55f, 279.67f);
        f976t.lineTo(314.16f, 244.87f);
        f976t.lineTo(322.09f, 263.27f);
        f976t.lineTo(263.43f, 288.59f);
        f976t.lineTo(273.83f, 312.69f);
        f976t.lineTo(328.42f, 289.12f);
        f976t.lineTo(336.32f, 307.44f);
        f976t.lineTo(281.73f, 331.0f);
        f976t.lineTo(294.5f, 360.59f);
        f976t.lineTo(355.24f, 334.38f);
        f976t.lineTo(363.15f, 352.7f);
        f976t.lineTo(280.47f, 388.39f);
        f976t.moveTo(464.25f, 309.08f);
        f976t.lineTo(407.51f, 237.13f);
        f976t.lineTo(421.0f, 327.73f);
        f976t.lineTo(397.2f, 338.01f);
        f976t.lineTo(324.33f, 240.49f);
        f976t.lineTo(346.79f, 230.79f);
        f976t.lineTo(395.42f, 298.4f);
        f976t.lineTo(383.06f, 215.14f);
        f976t.lineTo(409.18f, 203.87f);
        f976t.lineTo(461.01f, 271.59f);
        f976t.lineTo(444.92f, 188.45f);
        f976t.lineTo(467.02f, 178.91f);
        f976t.lineTo(487.53f, 299.03f);
        f976t.lineTo(464.25f, 309.08f);
        f976t.transform(f974m);
        if (clearMode) {
            f975p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f976t, ps);
        } else {
            c.drawPath(f976t, f975p);
            c.drawPath(f976t, ps);
        }
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f975p.setColor(Color.parseColor("#000000"));
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f975p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m829r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m829r(new Integer[0]);
        c.restore();
    }

    private static void m829r(Integer... o) {
        f975p.reset();
        ps.reset();
        if (cf != null) {
            f975p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f975p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f975p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f975p.setColor(Color.parseColor("#000000"));
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
