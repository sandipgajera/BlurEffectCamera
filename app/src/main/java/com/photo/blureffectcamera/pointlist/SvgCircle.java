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

public class SvgCircle extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f782m;
    private static float od;
    private static final Paint f783p;
    private static final Paint ps;
    private static final Path f784t;

    static {
        f783p = new Paint();
        ps = new Paint();
        f784t = new Path();
        f782m = new Matrix();
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
        m765r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f782m.reset();
        f782m.setScale(od, od);
        c.save();
        if (clearMode) {
            f783p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f783p.setColor(Color.parseColor("#FFFFFF"));
        c.scale(1.0f, -1.0f);
        c.translate(0.0f, -448.0f * od);
        f784t.reset();
        f784t.moveTo(256.0f, 405.33f);
        f784t.cubicTo(138.24f, 405.33f, 42.67f, 309.76f, 42.67f, 192.0f);
        f784t.cubicTo(42.67f, 74.24f, 138.24f, -21.33f, 256.0f, -21.33f);
        f784t.cubicTo(373.76f, -21.33f, 469.33f, 74.24f, 469.33f, 192.0f);
        f784t.cubicTo(469.33f, 309.76f, 373.76f, 405.33f, 256.0f, 405.33f);
        f784t.transform(f782m);
        c.drawPath(f784t, f783p);
        c.drawPath(f784t, ps);
        c.restore();
        m765r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f783p.setColor(Color.parseColor("#FFFFFF"));
        c.restore();
        m765r(new Integer[0]);
        c.restore();
    }

    private static void m765r(Integer... o) {
        f783p.reset();
        ps.reset();
        if (cf != null) {
            f783p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f783p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f783p.setStyle(Style.FILL);
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
