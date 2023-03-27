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

import com.photo.blureffectcamera.canvas.TextData;


public class SvgCircle2 extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f779m;
    private static float od;
    private static final Paint f780p;
    private static final Paint ps;
    private static final Path f781t;

    static {
        f780p = new Paint();
        ps = new Paint();
        f781t = new Path();
        f779m = new Matrix();
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
        od = w / 377.0f < h / 377.0f ? w / 377.0f : h / 377.0f;
        m764r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 377.0f)) / 2.0f) + dx, ((h - (od * 377.0f)) / 2.0f) + dy);
        f779m.reset();
        f779m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.save();
        f780p.setColor(Color.parseColor("#FFFFFF"));
        c.restore();
        m764r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m764r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.translate(-197.14f * od, -338.08f * od);
        c.save();
        f780p.setColor(Color.argb(TextData.defBgAlpha, 0, 0, 0));
        c.translate(31.43f * od, 2.86f * od);
        f781t.reset();
        f781t.moveTo(542.86f, 523.79f);
        f781t.cubicTo(542.86f, 627.94f, 458.43f, 712.36f, 354.29f, 712.36f);
        f781t.cubicTo(250.14f, 712.36f, 165.71f, 627.94f, 165.71f, 523.79f);
        f781t.cubicTo(165.71f, 419.65f, 250.14f, 335.22f, 354.29f, 335.22f);
        f781t.cubicTo(458.43f, 335.22f, 542.86f, 419.65f, 542.86f, 523.79f);
        f781t.transform(f779m);
        if (clearMode) {
            f780p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        c.drawPath(f781t, f780p);
        c.drawPath(f781t, ps);
        c.restore();
        m764r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f780p.setColor(Color.argb(TextData.defBgAlpha, 0, 0, 0));
        c.restore();
        m764r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f780p.setColor(Color.argb(TextData.defBgAlpha, 0, 0, 0));
        c.restore();
        m764r(new Integer[0]);
        c.restore();
    }

    private static void m764r(Integer... o) {
        f780p.reset();
        ps.reset();
        if (cf != null) {
            f780p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f780p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f780p.setStyle(Style.FILL);
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
