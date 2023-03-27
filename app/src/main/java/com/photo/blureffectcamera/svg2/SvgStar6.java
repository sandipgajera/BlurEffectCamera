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

public class SvgStar6 extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f1130m;
    private static float od;
    private static final Paint f1131p;
    private static final Paint ps;
    private static final Path f1132t;

    static {
        f1131p = new Paint();
        ps = new Paint();
        f1132t = new Path();
        f1130m = new Matrix();
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
        m881r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f1130m.reset();
        f1130m.setScale(od * 2.0f, od * 2.0f);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.save();
        f1131p.setColor(Color.parseColor("#000000"));
        f1132t.reset();
        f1132t.moveTo(164.02f, 62.98f);
        f1132t.lineTo(127.71f, 0.0f);
        f1132t.lineTo(91.39f, 62.98f);
        f1132t.lineTo(16.73f, 62.98f);
        f1132t.lineTo(54.06f, 127.71f);
        f1132t.lineTo(16.73f, 192.44f);
        f1132t.lineTo(91.39f, 192.44f);
        f1132t.lineTo(127.71f, 255.41f);
        f1132t.lineTo(164.02f, 192.44f);
        f1132t.lineTo(238.68f, 192.44f);
        f1132t.lineTo(201.35f, 127.71f);
        f1132t.lineTo(238.68f, 62.98f);
        f1132t.lineTo(164.02f, 62.98f);
        f1132t.moveTo(68.52f, 162.35f);
        f1132t.lineTo(79.37f, 143.52f);
        f1132t.lineTo(90.23f, 162.35f);
        f1132t.lineTo(68.52f, 162.35f);
        f1132t.moveTo(79.37f, 111.89f);
        f1132t.lineTo(68.52f, 93.07f);
        f1132t.lineTo(90.23f, 93.07f);
        f1132t.lineTo(79.37f, 111.89f);
        f1132t.moveTo(127.71f, 59.71f);
        f1132t.lineTo(137.83f, 77.27f);
        f1132t.lineTo(117.58f, 77.27f);
        f1132t.lineTo(127.71f, 59.71f);
        f1132t.moveTo(127.71f, 195.7f);
        f1132t.lineTo(117.58f, 178.15f);
        f1132t.lineTo(137.83f, 178.15f);
        f1132t.lineTo(127.71f, 195.7f);
        f1132t.moveTo(139.37f, 149.77f);
        f1132t.lineTo(116.04f, 149.77f);
        f1132t.lineTo(103.32f, 127.71f);
        f1132t.lineTo(116.04f, 105.65f);
        f1132t.lineTo(139.37f, 105.65f);
        f1132t.lineTo(152.1f, 127.71f);
        f1132t.lineTo(139.37f, 149.77f);
        f1132t.moveTo(165.19f, 162.35f);
        f1132t.lineTo(176.04f, 143.52f);
        f1132t.lineTo(186.9f, 162.35f);
        f1132t.lineTo(165.19f, 162.35f);
        f1132t.moveTo(176.04f, 111.89f);
        f1132t.lineTo(165.19f, 93.07f);
        f1132t.lineTo(186.9f, 93.07f);
        f1132t.lineTo(176.04f, 111.89f);
        f1132t.transform(f1130m);
        if (clearMode) {
            f1131p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1132t, ps);
        } else {
            c.drawPath(f1132t, f1131p);
            c.drawPath(f1132t, ps);
        }
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1131p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m881r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m881r(new Integer[0]);
        c.restore();
    }

    private static void m881r(Integer... o) {
        f1131p.reset();
        ps.reset();
        if (cf != null) {
            f1131p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1131p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1131p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f1131p.setColor(Color.parseColor("#000000"));
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
