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

public class SvgStarSquare extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f1007m;
    private static float od;
    private static final Paint f1008p;
    private static final Paint ps;
    private static final Path f1009t;

    static {
        f1008p = new Paint();
        ps = new Paint();
        f1009t = new Path();
        f1007m = new Matrix();
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
        m840r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f1007m.reset();
        f1007m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.61f, 1.61f);
        c.save();
        c.save();
        f1008p.setColor(Color.parseColor("#000000"));
        f1009t.reset();
        f1009t.moveTo(190.86f, 114.49f);
        f1009t.lineTo(170.32f, 72.87f);
        f1009t.cubicTo(168.17f, 68.52f, 163.73f, 65.76f, 158.88f, 65.76f);
        f1009t.cubicTo(154.02f, 65.76f, 149.58f, 68.52f, 147.43f, 72.87f);
        f1009t.lineTo(126.89f, 114.49f);
        f1009t.lineTo(80.96f, 121.17f);
        f1009t.cubicTo(76.16f, 121.86f, 72.16f, 125.23f, 70.66f, 129.85f);
        f1009t.cubicTo(69.16f, 134.47f, 70.41f, 139.54f, 73.89f, 142.93f);
        f1009t.lineTo(107.13f, 175.33f);
        f1009t.lineTo(99.28f, 221.07f);
        f1009t.cubicTo(98.46f, 225.86f, 100.43f, 230.7f, 104.36f, 233.55f);
        f1009t.cubicTo(108.29f, 236.41f, 113.5f, 236.79f, 117.8f, 234.53f);
        f1009t.lineTo(158.88f, 212.93f);
        f1009t.lineTo(199.96f, 234.53f);
        f1009t.cubicTo(201.82f, 235.51f, 203.86f, 235.99f, 205.89f, 235.99f);
        f1009t.cubicTo(208.54f, 235.99f, 211.17f, 235.17f, 213.4f, 233.55f);
        f1009t.cubicTo(217.33f, 230.7f, 219.29f, 225.86f, 218.47f, 221.07f);
        f1009t.lineTo(210.63f, 175.33f);
        f1009t.lineTo(243.86f, 142.93f);
        f1009t.cubicTo(247.34f, 139.54f, 248.59f, 134.47f, 247.09f, 129.85f);
        f1009t.cubicTo(245.59f, 125.23f, 241.6f, 121.86f, 236.79f, 121.17f);
        f1009t.lineTo(190.86f, 114.49f);
        f1009t.transform(f1007m);
        if (clearMode) {
            f1008p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1009t, ps);
        } else {
            c.drawPath(f1009t, f1008p);
            c.drawPath(f1009t, ps);
        }
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1008p.setColor(Color.parseColor("#000000"));
        c.save();
        f1009t.reset();
        f1009t.moveTo(286.53f, 0.0f);
        f1009t.lineTo(31.23f, 0.0f);
        f1009t.cubicTo(18.68f, 0.0f, 8.52f, 10.17f, 8.52f, 22.71f);
        f1009t.lineTo(8.52f, 295.05f);
        f1009t.cubicTo(8.52f, 307.59f, 18.69f, 317.75f, 31.23f, 317.75f);
        f1009t.lineTo(286.53f, 317.75f);
        f1009t.cubicTo(299.07f, 317.75f, 309.23f, 307.59f, 309.23f, 295.05f);
        f1009t.lineTo(309.23f, 22.71f);
        f1009t.cubicTo(309.23f, 10.17f, 299.07f, 0.0f, 286.53f, 0.0f);
        f1009t.moveTo(158.88f, 274.65f);
        f1009t.cubicTo(94.89f, 274.65f, 43.11f, 222.87f, 43.11f, 158.88f);
        f1009t.cubicTo(43.11f, 94.89f, 94.88f, 43.11f, 158.88f, 43.11f);
        f1009t.cubicTo(222.86f, 43.11f, 274.65f, 94.89f, 274.65f, 158.88f);
        f1009t.cubicTo(274.65f, 222.86f, 222.87f, 274.65f, 158.88f, 274.65f);
        f1009t.transform(f1007m);
        if (clearMode) {
            f1008p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1009t, ps);
        } else {
            c.drawPath(f1009t, f1008p);
            c.drawPath(f1009t, ps);
        }
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1008p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m840r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m840r(new Integer[0]);
        c.restore();
    }

    private static void m840r(Integer... o) {
        f1008p.reset();
        ps.reset();
        if (cf != null) {
            f1008p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1008p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1008p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f1008p.setColor(Color.parseColor("#000000"));
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
