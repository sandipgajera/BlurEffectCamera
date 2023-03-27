package com.photo.blureffectcamera.svg2;

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

import com.photo.blureffectcamera.svg.Svg;

public class SvgClover2 extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f1082m;
    private static float od;
    private static final Paint f1083p;
    private static final Paint ps;
    private static final Path f1084t;

    static {
        f1083p = new Paint();
        ps = new Paint();
        f1084t = new Path();
        f1082m = new Matrix();
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
        m865r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f1082m.reset();
        f1082m.setScale(od * 11.59f, od * 11.59f);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.01f * od, 0.0f);
        c.save();
        c.save();
        c.save();
        f1083p.setColor(Color.parseColor("#000000"));
        f1084t.reset();
        f1084t.moveTo(21.23f, 20.38f);
        f1084t.cubicTo(21.7f, 20.84f, 22.46f, 20.84f, 22.93f, 20.38f);
        f1084t.cubicTo(25.26f, 18.06f, 31.58f, 11.77f, 31.71f, 11.64f);
        f1084t.cubicTo(34.37f, 8.98f, 34.37f, 4.66f, 31.71f, 2.0f);
        f1084t.cubicTo(29.05f, -0.66f, 24.75f, -0.67f, 22.08f, 1.98f);
        f1084t.cubicTo(19.42f, -0.67f, 15.12f, -0.66f, 12.46f, 2.0f);
        f1084t.cubicTo(9.8f, 4.66f, 9.8f, 8.98f, 12.46f, 11.64f);
        f1084t.cubicTo(12.59f, 11.77f, 18.91f, 18.06f, 21.23f, 20.38f);
        f1084t.transform(f1082m);
        if (clearMode) {
            f1083p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1084t, ps);
        } else {
            c.drawPath(f1084t, f1083p);
            c.drawPath(f1084t, ps);
        }
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1083p.setColor(Color.parseColor("#000000"));
        c.save();
        f1084t.reset();
        f1084t.moveTo(22.93f, 23.79f);
        f1084t.cubicTo(22.46f, 23.32f, 21.7f, 23.32f, 21.23f, 23.79f);
        f1084t.cubicTo(18.91f, 26.11f, 12.59f, 32.4f, 12.46f, 32.52f);
        f1084t.cubicTo(9.8f, 35.19f, 9.8f, 39.5f, 12.46f, 42.17f);
        f1084t.cubicTo(15.12f, 44.82f, 19.42f, 44.83f, 22.08f, 42.19f);
        f1084t.cubicTo(24.75f, 44.83f, 29.05f, 44.83f, 31.71f, 42.17f);
        f1084t.cubicTo(34.37f, 39.51f, 34.37f, 35.19f, 31.71f, 32.52f);
        f1084t.cubicTo(31.58f, 32.4f, 25.26f, 26.11f, 22.93f, 23.79f);
        f1084t.transform(f1082m);
        if (clearMode) {
            f1083p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1084t, ps);
        } else {
            c.drawPath(f1084t, f1083p);
            c.drawPath(f1084t, ps);
        }
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        f1084t.reset();
        f1084t.moveTo(42.17f, 12.46f);
        f1084t.cubicTo(39.51f, 9.8f, 35.19f, 9.8f, 32.52f, 12.46f);
        f1084t.cubicTo(32.39f, 12.59f, 26.1f, 18.91f, 23.79f, 21.23f);
        f1084t.cubicTo(23.32f, 21.7f, 23.32f, 22.46f, 23.79f, 22.93f);
        f1084t.cubicTo(26.1f, 25.26f, 32.39f, 31.58f, 32.52f, 31.71f);
        f1084t.cubicTo(35.18f, 34.37f, 39.5f, 34.37f, 42.17f, 31.71f);
        f1084t.cubicTo(44.82f, 29.05f, 44.83f, 24.75f, 42.19f, 22.08f);
        f1084t.cubicTo(44.83f, 19.42f, 44.83f, 15.12f, 42.17f, 12.46f);
        f1084t.transform(f1082m);
        if (clearMode) {
            f1083p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1084t, ps);
        } else {
            c.drawPath(f1084t, f1083p);
            c.drawPath(f1084t, ps);
        }
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        f1084t.reset();
        f1084t.moveTo(20.38f, 22.93f);
        f1084t.cubicTo(20.84f, 22.46f, 20.84f, 21.7f, 20.38f, 21.23f);
        f1084t.cubicTo(18.06f, 18.91f, 11.77f, 12.59f, 11.64f, 12.46f);
        f1084t.cubicTo(8.98f, 9.8f, 4.66f, 9.8f, 2.0f, 12.46f);
        f1084t.cubicTo(-0.66f, 15.12f, -0.66f, 19.42f, 1.98f, 22.08f);
        f1084t.cubicTo(-0.66f, 24.75f, -0.66f, 29.05f, 2.0f, 31.71f);
        f1084t.cubicTo(4.66f, 34.37f, 8.98f, 34.37f, 11.64f, 31.71f);
        f1084t.cubicTo(11.77f, 31.58f, 18.06f, 25.26f, 20.38f, 22.93f);
        f1084t.transform(f1082m);
        if (clearMode) {
            f1083p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1084t, ps);
        } else {
            c.drawPath(f1084t, f1083p);
            c.drawPath(f1084t, ps);
        }
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1083p.setColor(Color.parseColor("#000000"));
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1083p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m865r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m865r(new Integer[0]);
        c.restore();
    }

    private static void m865r(Integer... o) {
        f1083p.reset();
        ps.reset();
        if (cf != null) {
            f1083p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1083p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1083p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f1083p.setColor(Color.parseColor("#000000"));
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
