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

public class SvgHeart extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f938m;
    private static float od;
    private static final Paint f939p;
    private static final Paint ps;
    private static final Path f940t;

    static {
        f939p = new Paint();
        ps = new Paint();
        f940t = new Path();
        f938m = new Matrix();
        cf = null;
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
        m817r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f938m.reset();
        f938m.setScale(od, od);
        c.save();
        if (clearMode) {
            f939p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f939p.setColor(Color.parseColor("#FFFFFF"));
        c.scale(1.0f, -1.0f);
        c.translate(0.0f, -448.0f * od);
        f940t.reset();
        f940t.moveTo(256.0f, -7.47f);
        f940t.lineTo(225.07f, 20.69f);
        f940t.cubicTo(115.2f, 120.32f, 42.67f, 186.24f, 42.67f, 266.67f);
        f940t.cubicTo(42.67f, 332.59f, 94.29f, 384.0f, 160.0f, 384.0f);
        f940t.cubicTo(197.12f, 384.0f, 232.75f, 366.72f, 256.0f, 339.63f);
        f940t.cubicTo(279.25f, 366.72f, 314.88f, 384.0f, 352.0f, 384.0f);
        f940t.cubicTo(417.71f, 384.0f, 469.33f, 332.59f, 469.33f, 266.67f);
        f940t.cubicTo(469.33f, 186.24f, 396.8f, 120.32f, 286.93f, 20.69f);
        f940t.lineTo(256.0f, -7.47f);
        f940t.transform(f938m);
        c.drawPath(f940t, f939p);
        c.drawPath(f940t, ps);
        c.restore();
        m817r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f939p.setColor(Color.parseColor("#FFFFFF"));
        c.restore();
        m817r(new Integer[0]);
        c.restore();
    }

    private static void m817r(Integer... o) {
        f939p.reset();
        ps.reset();
        if (cf != null) {
            f939p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f939p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f939p.setStyle(Style.FILL);
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
