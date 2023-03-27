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

public class SvgCross extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f1088m;
    private static float od;
    private static final Paint f1089p;
    private static final Paint ps;
    private static final Path f1090t;

    static {
        f1089p = new Paint();
        ps = new Paint();
        f1090t = new Path();
        f1088m = new Matrix();
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
        m867r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f1088m.reset();
        f1088m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.09f, 1.09f);
        c.save();
        c.save();
        f1089p.setColor(Color.parseColor("#000000"));
        f1090t.reset();
        f1090t.moveTo(290.52f, 0.0f);
        f1090t.lineTo(260.52f, 0.0f);
        f1090t.lineTo(207.94f, 0.0f);
        f1090t.lineTo(177.94f, 0.0f);
        f1090t.lineTo(177.94f, 30.0f);
        f1090t.lineTo(177.94f, 99.02f);
        f1090t.lineTo(77.14f, 99.02f);
        f1090t.lineTo(47.14f, 99.02f);
        f1090t.lineTo(47.14f, 129.02f);
        f1090t.lineTo(47.14f, 181.6f);
        f1090t.lineTo(47.14f, 211.6f);
        f1090t.lineTo(77.14f, 211.6f);
        f1090t.lineTo(177.94f, 211.6f);
        f1090t.lineTo(177.94f, 438.45f);
        f1090t.lineTo(177.94f, 468.45f);
        f1090t.lineTo(207.94f, 468.45f);
        f1090t.lineTo(260.52f, 468.45f);
        f1090t.lineTo(290.52f, 468.45f);
        f1090t.lineTo(290.52f, 438.45f);
        f1090t.lineTo(290.52f, 211.6f);
        f1090t.lineTo(391.31f, 211.6f);
        f1090t.lineTo(421.31f, 211.6f);
        f1090t.lineTo(421.31f, 181.6f);
        f1090t.lineTo(421.31f, 129.02f);
        f1090t.lineTo(421.31f, 99.02f);
        f1090t.lineTo(391.31f, 99.02f);
        f1090t.lineTo(290.52f, 99.02f);
        f1090t.lineTo(290.52f, 30.0f);
        f1090t.lineTo(290.52f, 0.0f);
        f1090t.lineTo(290.52f, 0.0f);
        f1090t.transform(f1088m);
        if (clearMode) {
            f1089p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1090t, ps);
        } else {
            c.drawPath(f1090t, f1089p);
            c.drawPath(f1090t, ps);
        }
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1089p.setColor(Color.parseColor("#000000"));
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1089p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m867r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m867r(new Integer[0]);
        c.restore();
    }

    private static void m867r(Integer... o) {
        f1089p.reset();
        ps.reset();
        if (cf != null) {
            f1089p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1089p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1089p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f1089p.setColor(Color.parseColor("#000000"));
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
