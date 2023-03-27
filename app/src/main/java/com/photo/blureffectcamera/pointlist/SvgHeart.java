package com.photo.blureffectcamera.pointlist;

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
    private static final Matrix f797m;
    private static float od;
    private static final Paint f798p;
    private static final Paint ps;
    private static final Path f799t;

    static {
        f798p = new Paint();
        ps = new Paint();
        f799t = new Path();
        f797m = new Matrix();
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
        m770r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f797m.reset();
        f797m.setScale(od, od);
        c.save();
        if (clearMode) {
            f798p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f798p.setColor(Color.parseColor("#FFFFFF"));
        c.scale(1.0f, -1.0f);
        c.translate(0.0f, -448.0f * od);
        f799t.reset();
        f799t.moveTo(256.0f, -7.47f);
        f799t.lineTo(225.07f, 20.69f);
        f799t.cubicTo(115.2f, 120.32f, 42.67f, 186.24f, 42.67f, 266.67f);
        f799t.cubicTo(42.67f, 332.59f, 94.29f, 384.0f, 160.0f, 384.0f);
        f799t.cubicTo(197.12f, 384.0f, 232.75f, 366.72f, 256.0f, 339.63f);
        f799t.cubicTo(279.25f, 366.72f, 314.88f, 384.0f, 352.0f, 384.0f);
        f799t.cubicTo(417.71f, 384.0f, 469.33f, 332.59f, 469.33f, 266.67f);
        f799t.cubicTo(469.33f, 186.24f, 396.8f, 120.32f, 286.93f, 20.69f);
        f799t.lineTo(256.0f, -7.47f);
        f799t.transform(f797m);
        c.drawPath(f799t, f798p);
        c.drawPath(f799t, ps);
        c.restore();
        m770r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f798p.setColor(Color.parseColor("#FFFFFF"));
        c.restore();
        m770r(new Integer[0]);
        c.restore();
    }

    private static void m770r(Integer... o) {
        f798p.reset();
        ps.reset();
        if (cf != null) {
            f798p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f798p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f798p.setStyle(Style.FILL);
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
