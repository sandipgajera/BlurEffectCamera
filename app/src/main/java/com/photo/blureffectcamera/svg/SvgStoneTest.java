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

public class SvgStoneTest extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f1031m;
    private static float od;
    private static final Paint f1032p;
    private static final Paint ps;
    private static final Path f1033t;

    static {
        f1032p = new Paint();
        ps = new Paint();
        f1033t = new Path();
        f1031m = new Matrix();
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
        od = w / 511.0f < h / 511.0f ? w / 511.0f : h / 511.0f;
        m848r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 511.0f)) / 2.0f) + dx, ((h - (od * 511.0f)) / 2.0f) + dy);
        f1031m.reset();
        f1031m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f1033t.reset();
        f1033t.moveTo(212.62f, 45.88f);
        f1033t.cubicTo(256.63f, 28.27f, 334.75f, -10.24f, 429.36f, 43.67f);
        f1033t.cubicTo(523.98f, 97.58f, 529.91f, 235.63f, 449.17f, 355.04f);
        f1033t.cubicTo(371.05f, 470.56f, 286.34f, 602.59f, 75.09f, 411.15f);
        f1033t.cubicTo(1.38f, 325.33f, 1.38f, 237.31f, 12.38f, 205.41f);
        f1033t.cubicTo(27.78f, 141.59f, 212.62f, 45.88f, 212.62f, 45.88f);
        f1033t.transform(f1031m);
        c.drawPath(f1033t, f1032p);
        c.drawPath(f1033t, ps);
        c.restore();
        m848r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m848r(new Integer[0]);
        c.restore();
    }

    private static void m848r(Integer... o) {
        f1032p.reset();
        ps.reset();
        if (cf != null) {
            f1032p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1032p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1032p.setStyle(Style.FILL);
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
