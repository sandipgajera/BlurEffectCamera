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

public class SvgSheriff extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f1001m;
    private static float od;
    private static final Paint f1002p;
    private static final Paint ps;
    private static final Path f1003t;

    static {
        f1002p = new Paint();
        ps = new Paint();
        f1003t = new Path();
        f1001m = new Matrix();
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
        m838r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f1001m.reset();
        f1001m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.72f, 1.72f);
        c.save();
        f1002p.setColor(Color.parseColor("#000000"));
        f1003t.reset();
        f1003t.moveTo(275.43f, 100.78f);
        f1003t.cubicTo(269.88f, 100.78f, 264.84f, 103.04f, 261.18f, 106.7f);
        f1003t.lineTo(196.62f, 93.63f);
        f1003t.lineTo(162.95f, 33.67f);
        f1003t.cubicTo(165.97f, 30.1f, 167.81f, 25.48f, 167.81f, 20.44f);
        f1003t.cubicTo(167.81f, 9.17f, 158.71f, 0.0f, 147.54f, 0.0f);
        f1003t.cubicTo(136.36f, 0.0f, 127.27f, 9.17f, 127.27f, 20.44f);
        f1003t.cubicTo(127.27f, 25.48f, 129.1f, 30.1f, 132.12f, 33.67f);
        f1003t.lineTo(98.46f, 93.63f);
        f1003t.lineTo(33.46f, 106.79f);
        f1003t.cubicTo(30.12f, 104.34f, 26.02f, 102.87f, 21.57f, 102.87f);
        f1003t.cubicTo(10.4f, 102.87f, 1.3f, 112.04f, 1.3f, 123.3f);
        f1003t.cubicTo(1.3f, 134.57f, 10.4f, 143.74f, 21.57f, 143.74f);
        f1003t.cubicTo(23.63f, 143.74f, 25.61f, 143.43f, 27.49f, 142.85f);
        f1003t.lineTo(68.15f, 187.25f);
        f1003t.lineTo(60.17f, 256.52f);
        f1003t.cubicTo(50.86f, 258.36f, 43.81f, 266.64f, 43.81f, 276.56f);
        f1003t.cubicTo(43.81f, 287.83f, 52.9f, 297.0f, 64.08f, 297.0f);
        f1003t.cubicTo(75.25f, 297.0f, 84.34f, 287.83f, 84.34f, 276.56f);
        f1003t.cubicTo(84.34f, 275.78f, 84.28f, 275.0f, 84.2f, 274.24f);
        f1003t.lineTo(147.54f, 245.14f);
        f1003t.lineTo(210.88f, 274.24f);
        f1003t.cubicTo(210.8f, 275.01f, 210.74f, 275.78f, 210.74f, 276.56f);
        f1003t.cubicTo(210.74f, 287.83f, 219.83f, 297.0f, 231.01f, 297.0f);
        f1003t.cubicTo(242.19f, 297.0f, 251.28f, 287.83f, 251.28f, 276.56f);
        f1003t.cubicTo(251.28f, 266.64f, 244.22f, 258.36f, 234.91f, 256.52f);
        f1003t.lineTo(226.93f, 187.25f);
        f1003t.lineTo(269.51f, 140.75f);
        f1003t.cubicTo(271.39f, 141.33f, 273.37f, 141.65f, 275.43f, 141.65f);
        f1003t.cubicTo(286.6f, 141.65f, 295.7f, 132.48f, 295.7f, 121.21f);
        f1003t.cubicTo(295.69f, 109.94f, 286.6f, 100.78f, 275.43f, 100.78f);
        f1003t.transform(f1001m);
        if (clearMode) {
            f1002p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1003t, ps);
        } else {
            c.drawPath(f1003t, f1002p);
            c.drawPath(f1003t, ps);
        }
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1002p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m838r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m838r(new Integer[0]);
        c.restore();
    }

    private static void m838r(Integer... o) {
        f1002p.reset();
        ps.reset();
        if (cf != null) {
            f1002p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1002p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1002p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f1002p.setColor(Color.parseColor("#000000"));
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
