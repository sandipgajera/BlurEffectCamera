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

public class SvgDiamond extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f911m;
    private static float od;
    private static final Paint f912p;
    private static final Paint ps;
    private static final Path f913t;

    static {
        f912p = new Paint();
        ps = new Paint();
        f913t = new Path();
        f911m = new Matrix();
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
        od = w / 417.0f < h / 417.0f ? w / 417.0f : h / 417.0f;
        m808r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 417.0f)) / 2.0f) + dx, ((h - (od * 417.0f)) / 2.0f) + dy);
        f911m.reset();
        f911m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        c.save();
        f913t.reset();
        f913t.moveTo(333.23f, 39.55f);
        f913t.lineTo(278.31f, 122.56f);
        f913t.lineTo(221.55f, 39.55f);
        f913t.lineTo(333.23f, 39.55f);
        f913t.moveTo(269.59f, 141.54f);
        f913t.lineTo(147.76f, 141.54f);
        f913t.lineTo(208.67f, 373.21f);
        f913t.lineTo(269.59f, 141.54f);
        f913t.moveTo(151.82f, 127.94f);
        f913t.lineTo(265.51f, 127.94f);
        f913t.lineTo(208.66f, 44.79f);
        f913t.lineTo(151.82f, 127.94f);
        f913t.moveTo(139.03f, 122.56f);
        f913t.lineTo(195.79f, 39.55f);
        f913t.lineTo(84.12f, 39.55f);
        f913t.lineTo(139.03f, 122.56f);
        f913t.moveTo(195.76f, 377.58f);
        f913t.lineTo(133.7f, 141.54f);
        f913t.lineTo(0.0f, 141.54f);
        f913t.lineTo(195.76f, 377.58f);
        f913t.moveTo(0.19f, 127.94f);
        f913t.lineTo(126.29f, 127.94f);
        f913t.lineTo(71.36f, 44.91f);
        f913t.lineTo(0.19f, 127.94f);
        f913t.moveTo(345.89f, 45.06f);
        f913t.lineTo(291.05f, 127.95f);
        f913t.lineTo(416.94f, 127.95f);
        f913t.lineTo(345.89f, 45.06f);
        f913t.moveTo(283.64f, 141.54f);
        f913t.lineTo(221.58f, 377.56f);
        f913t.lineTo(417.13f, 141.55f);
        f913t.lineTo(283.64f, 141.55f);
        f913t.transform(f911m);
        if (clearMode) {
            f912p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f913t, ps);
        } else {
            c.drawPath(f913t, f912p);
            c.drawPath(f913t, ps);
        }
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m808r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m808r(new Integer[0]);
        c.restore();
    }

    private static void m808r(Integer... o) {
        f912p.reset();
        ps.reset();
        if (cf != null) {
            f912p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f912p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f912p.setStyle(Style.FILL);
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
