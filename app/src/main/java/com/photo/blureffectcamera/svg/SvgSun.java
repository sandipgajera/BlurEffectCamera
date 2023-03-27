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

public class SvgSun extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f1040m;
    private static float od;
    private static final Paint f1041p;
    private static final Paint ps;
    private static final Path f1042t;

    static {
        f1041p = new Paint();
        ps = new Paint();
        f1042t = new Path();
        f1040m = new Matrix();
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
        m851r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f1040m.reset();
        f1040m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(0.88f, 0.88f);
        c.save();
        c.save();
        c.save();
        f1041p.setColor(Color.parseColor("#000000"));
        f1042t.reset();
        f1042t.moveTo(565.41f, 199.96f);
        f1042t.cubicTo(564.54f, 196.72f, 562.39f, 194.57f, 558.93f, 193.49f);
        f1042t.lineTo(464.45f, 162.44f);
        f1042t.lineTo(464.45f, 63.42f);
        f1042t.cubicTo(464.45f, 59.97f, 463.05f, 57.18f, 460.24f, 55.01f);
        f1042t.cubicTo(457.01f, 52.86f, 453.88f, 52.43f, 450.86f, 53.71f);
        f1042t.lineTo(356.38f, 84.13f);
        f1042t.lineTo(298.14f, 3.89f);
        f1042t.cubicTo(296.22f, 1.3f, 293.41f, 0.0f, 289.74f, 0.0f);
        f1042t.cubicTo(286.07f, 0.0f, 283.27f, 1.3f, 281.33f, 3.89f);
        f1042t.lineTo(223.09f, 84.13f);
        f1042t.lineTo(128.61f, 53.71f);
        f1042t.cubicTo(125.58f, 52.42f, 122.46f, 52.86f, 119.23f, 55.01f);
        f1042t.cubicTo(116.42f, 57.18f, 115.02f, 59.98f, 115.02f, 63.42f);
        f1042t.lineTo(115.02f, 162.44f);
        f1042t.lineTo(20.55f, 193.49f);
        f1042t.cubicTo(17.09f, 194.58f, 14.93f, 196.73f, 14.07f, 199.96f);
        f1042t.cubicTo(12.99f, 203.42f, 13.43f, 206.54f, 15.36f, 209.35f);
        f1042t.lineTo(73.61f, 289.58f);
        f1042t.lineTo(15.36f, 369.83f);
        f1042t.cubicTo(13.42f, 372.41f, 12.99f, 375.55f, 14.07f, 379.21f);
        f1042t.cubicTo(14.93f, 382.45f, 17.09f, 384.61f, 20.55f, 385.68f);
        f1042t.lineTo(115.03f, 416.74f);
        f1042t.lineTo(115.03f, 515.76f);
        f1042t.cubicTo(115.03f, 519.21f, 116.43f, 522.0f, 119.23f, 524.16f);
        f1042t.cubicTo(122.47f, 526.32f, 125.6f, 526.75f, 128.61f, 525.46f);
        f1042t.lineTo(223.09f, 495.04f);
        f1042t.lineTo(281.33f, 575.29f);
        f1042t.cubicTo(283.49f, 578.09f, 286.29f, 579.5f, 289.75f, 579.5f);
        f1042t.cubicTo(293.19f, 579.5f, 296.0f, 578.1f, 298.16f, 575.29f);
        f1042t.lineTo(356.4f, 495.04f);
        f1042t.lineTo(450.88f, 525.46f);
        f1042t.cubicTo(453.9f, 526.75f, 457.02f, 526.32f, 460.26f, 524.16f);
        f1042t.cubicTo(463.06f, 522.0f, 464.47f, 519.2f, 464.47f, 515.76f);
        f1042t.lineTo(464.47f, 416.74f);
        f1042t.lineTo(558.95f, 385.69f);
        f1042t.cubicTo(562.4f, 384.61f, 564.56f, 382.45f, 565.43f, 379.22f);
        f1042t.cubicTo(566.5f, 375.55f, 566.07f, 372.42f, 564.14f, 369.83f);
        f1042t.lineTo(505.89f, 289.58f);
        f1042t.lineTo(564.14f, 209.35f);
        f1042t.cubicTo(566.06f, 206.54f, 566.49f, 203.42f, 565.41f, 199.96f);
        f1042t.moveTo(461.39f, 361.9f);
        f1042t.cubicTo(451.57f, 384.88f, 438.31f, 404.72f, 421.59f, 421.43f);
        f1042t.cubicTo(404.88f, 438.16f, 385.03f, 451.42f, 362.06f, 461.23f);
        f1042t.cubicTo(339.08f, 471.05f, 314.98f, 475.95f, 289.74f, 475.95f);
        f1042t.cubicTo(264.5f, 475.95f, 240.4f, 471.05f, 217.43f, 461.23f);
        f1042t.cubicTo(194.45f, 451.42f, 174.6f, 438.16f, 157.89f, 421.43f);
        f1042t.cubicTo(141.18f, 404.72f, 127.91f, 384.87f, 118.09f, 361.9f);
        f1042t.cubicTo(108.28f, 338.93f, 103.38f, 314.82f, 103.38f, 289.58f);
        f1042t.cubicTo(103.38f, 264.35f, 108.28f, 240.24f, 118.09f, 217.27f);
        f1042t.cubicTo(127.91f, 194.29f, 141.17f, 174.46f, 157.89f, 157.73f);
        f1042t.cubicTo(174.6f, 141.02f, 194.45f, 127.75f, 217.43f, 117.94f);
        f1042t.cubicTo(240.39f, 108.13f, 264.5f, 103.22f, 289.74f, 103.22f);
        f1042t.cubicTo(314.98f, 103.22f, 339.08f, 108.13f, 362.05f, 117.94f);
        f1042t.cubicTo(385.02f, 127.75f, 404.87f, 141.02f, 421.59f, 157.73f);
        f1042t.cubicTo(438.3f, 174.46f, 451.57f, 194.3f, 461.39f, 217.27f);
        f1042t.cubicTo(471.2f, 240.23f, 476.11f, 264.35f, 476.11f, 289.58f);
        f1042t.cubicTo(476.11f, 314.82f, 471.2f, 338.93f, 461.39f, 361.9f);
        f1042t.transform(f1040m);
        if (clearMode) {
            f1041p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1042t, ps);
        } else {
            c.drawPath(f1042t, f1041p);
            c.drawPath(f1042t, ps);
        }
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1041p.setColor(Color.parseColor("#000000"));
        c.save();
        f1042t.reset();
        f1042t.transform(f1040m);
        if (clearMode) {
            f1041p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        c.drawPath(f1042t, f1041p);
        c.drawPath(f1042t, ps);
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1041p.setColor(Color.parseColor("#000000"));
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1041p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m851r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m851r(new Integer[0]);
        c.restore();
    }

    private static void m851r(Integer... o) {
        f1041p.reset();
        ps.reset();
        if (cf != null) {
            f1041p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1041p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1041p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f1041p.setColor(Color.parseColor("#000000"));
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
