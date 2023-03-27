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

public class SvgLeafAutumn extends Svg {
    protected static ColorFilter cf;
    private static final Matrix f1112m;
    private static float od;
    private static final Paint f1113p;
    private static final Paint ps;
    private static final Path f1114t;

    static {
        f1113p = new Paint();
        ps = new Paint();
        f1114t = new Path();
        f1112m = new Matrix();
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
        m875r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f1112m.reset();
        f1112m.setScale(od * 20.27f, od * 20.27f);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.save();
        c.save();
        f1113p.setColor(Color.parseColor("#000000"));
        f1114t.reset();
        f1114t.moveTo(15.36f, 9.69f);
        f1114t.cubicTo(15.36f, 9.69f, 14.61f, 10.8f, 14.29f, 9.69f);
        f1114t.cubicTo(13.98f, 8.58f, 12.95f, 4.74f, 12.53f, 4.46f);
        f1114t.cubicTo(12.12f, 4.19f, 12.12f, 4.19f, 12.12f, 4.19f);
        f1114t.cubicTo(12.12f, 4.19f, 12.04f, 5.69f, 11.52f, 5.49f);
        f1114t.cubicTo(11.01f, 5.29f, 7.21f, 3.28f, 6.89f, 2.84f);
        f1114t.lineTo(6.57f, 2.44f);
        f1114t.cubicTo(6.57f, 2.44f, 6.93f, 5.77f, 6.06f, 5.06f);
        f1114t.cubicTo(6.06f, 5.06f, 7.17f, 7.83f, 3.49f, 8.11f);
        f1114t.cubicTo(3.49f, 8.11f, 4.67f, 11.19f, 8.04f, 12.86f);
        f1114t.cubicTo(8.04f, 12.86f, 9.19f, 14.64f, 7.09f, 14.36f);
        f1114t.cubicTo(7.09f, 14.36f, 4.16f, 13.96f, 3.76f, 13.61f);
        f1114t.cubicTo(3.76f, 13.61f, 2.22f, 16.62f, 0.0f, 16.74f);
        f1114t.cubicTo(0.0f, 16.74f, 2.66f, 18.24f, 2.34f, 20.89f);
        f1114t.cubicTo(2.34f, 20.89f, 7.44f, 20.93f, 7.25f, 21.72f);
        f1114t.cubicTo(7.05f, 22.52f, 6.69f, 23.32f, 6.69f, 23.32f);
        f1114t.cubicTo(6.69f, 23.32f, 11.4f, 22.4f, 12.12f, 21.13f);
        f1114t.cubicTo(12.12f, 21.13f, 13.1f, 22.12f, 13.19f, 23.07f);
        f1114t.cubicTo(13.19f, 23.07f, 15.88f, 22.48f, 15.64f, 20.77f);
        f1114t.cubicTo(15.64f, 20.77f, 16.91f, 21.72f, 17.07f, 23.32f);
        f1114t.lineTo(17.62f, 22.95f);
        f1114t.cubicTo(17.62f, 22.95f, 17.3f, 21.29f, 16.16f, 19.98f);
        f1114t.cubicTo(16.16f, 19.98f, 15.64f, 18.67f, 16.94f, 19.35f);
        f1114t.cubicTo(16.94f, 19.35f, 20.03f, 20.38f, 22.64f, 18.52f);
        f1114t.cubicTo(22.64f, 18.52f, 19.76f, 17.57f, 19.64f, 16.54f);
        f1114t.cubicTo(19.64f, 16.54f, 25.03f, 13.21f, 25.26f, 11.0f);
        f1114t.cubicTo(25.26f, 11.0f, 23.32f, 11.19f, 22.29f, 10.36f);
        f1114t.cubicTo(22.29f, 10.36f, 20.98f, 9.97f, 22.68f, 5.57f);
        f1114t.cubicTo(22.68f, 5.57f, 21.46f, 6.3f, 21.06f, 1.94f);
        f1114t.cubicTo(21.06f, 1.94f, 18.01f, 6.6f, 16.55f, 5.02f);
        f1114t.cubicTo(16.55f, 5.02f, 15.16f, 8.07f, 15.36f, 9.69f);
        f1114t.transform(f1112m);
        if (clearMode) {
            f1113p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f1114t, ps);
        } else {
            c.drawPath(f1114t, f1113p);
            c.drawPath(f1114t, ps);
        }
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1113p.setColor(Color.parseColor("#000000"));
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2));
        f1113p.setColor(Color.parseColor("#000000"));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.save();
        c.restore();
        m875r(Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(0));
        c.restore();
        m875r(new Integer[0]);
        c.restore();
    }

    private static void m875r(Integer... o) {
        f1113p.reset();
        ps.reset();
        if (cf != null) {
            f1113p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1113p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1113p.setStyle(Style.FILL);
        ps.setStyle(Style.STROKE);
        for (Integer i : o) {
            switch (i.intValue()) {
//                case SquareActivity.TAB_INDEX_SQUARE /*0*/:
//                    f1113p.setColor(Color.parseColor("#000000"));
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
