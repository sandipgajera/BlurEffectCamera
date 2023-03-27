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

public class SvgDiamond extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f791m;
    private static float od;
    private static final Paint f792p;
    private static final Paint ps;
    private static final Path f793t;

    static {
        f792p = new Paint();
        ps = new Paint();
        f793t = new Path();
        f791m = new Matrix();
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
        od = w / 417.0f < h / 417.0f ? w / 417.0f : h / 417.0f;
        m768r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 417.0f)) / 2.0f) + dx, ((h - (od * 417.0f)) / 2.0f) + dy);
        f791m.reset();
        f791m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        c.save();
        f793t.reset();
        f793t.moveTo(333.23f, 39.55f);
        f793t.lineTo(278.31f, 122.56f);
        f793t.lineTo(221.55f, 39.55f);
        f793t.lineTo(333.23f, 39.55f);
        f793t.moveTo(269.59f, 141.54f);
        f793t.lineTo(147.76f, 141.54f);
        f793t.lineTo(208.67f, 373.21f);
        f793t.lineTo(269.59f, 141.54f);
        f793t.moveTo(151.82f, 127.94f);
        f793t.lineTo(265.51f, 127.94f);
        f793t.lineTo(208.66f, 44.79f);
        f793t.lineTo(151.82f, 127.94f);
        f793t.moveTo(139.03f, 122.56f);
        f793t.lineTo(195.79f, 39.55f);
        f793t.lineTo(84.12f, 39.55f);
        f793t.lineTo(139.03f, 122.56f);
        f793t.moveTo(195.76f, 377.58f);
        f793t.lineTo(133.7f, 141.54f);
        f793t.lineTo(0.0f, 141.54f);
        f793t.lineTo(195.76f, 377.58f);
        f793t.moveTo(0.19f, 127.94f);
        f793t.lineTo(126.29f, 127.94f);
        f793t.lineTo(71.36f, 44.91f);
        f793t.lineTo(0.19f, 127.94f);
        f793t.moveTo(345.89f, 45.06f);
        f793t.lineTo(291.05f, 127.95f);
        f793t.lineTo(416.94f, 127.95f);
        f793t.lineTo(345.89f, 45.06f);
        f793t.moveTo(283.64f, 141.54f);
        f793t.lineTo(221.58f, 377.56f);
        f793t.lineTo(417.13f, 141.55f);
        f793t.lineTo(283.64f, 141.55f);
        f793t.transform(f791m);
        if (clearMode) {
            f792p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        c.drawPath(f793t, f792p);
        c.drawPath(f793t, ps);
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m768r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m768r(new Integer[0]);
        c.restore();
    }

    private static void m768r(Integer... o) {
        f792p.reset();
        ps.reset();
        if (cf != null) {
            f792p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f792p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f792p.setStyle(Style.FILL);
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
