package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgHex2 extends Svg {
    private static final Matrix f959m;
    private static float od;
    private static final Paint f960p;
    private static final Paint ps;
    private static final Path f961t;

    static {
        f960p = new Paint();
        ps = new Paint();
        f961t = new Path();
        f959m = new Matrix();
    }

    public void drawStroke(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        isStroke = true;
        draw(c, w, h, dx, dy, clearMode);
        isStroke = false;
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 723.0f < h / 625.77f ? w / 723.0f : h / 625.77f;
        m824r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 723.0f)) / 2.0f) + dx, ((h - (od * 625.77f)) / 2.0f) + dy);
        f959m.reset();
        f959m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.save();
        f960p.setColor(Color.parseColor("#FFFFFF"));
        ps.setColor(Color.parseColor("#000000"));
        ps.setStrokeWidth(4.0f * od);
        f961t.reset();
        f961t.moveTo(723.0f, 314.0f);
        f961t.lineTo(543.0f, 625.77f);
        f961t.lineTo(183.0f, 625.77f);
        f961t.lineTo(0.0f, 314.0f);
        f961t.lineTo(183.0f, 0.0f);
        f961t.lineTo(543.0f, 0.0f);
        f961t.lineTo(723.0f, 314.0f);
        f961t.lineTo(723.0f, 314.0f);
        f961t.transform(f959m);
        if (clearMode) {
            f960p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        if (isStroke) {
            ps.setColor(colorStroke);
            ps.setStrokeWidth(strokeSize);
            c.drawPath(f961t, ps);
        } else {
            c.drawPath(f961t, f960p);
            c.drawPath(f961t, ps);
        }
        c.restore();
        m824r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f960p.setColor(Color.parseColor("#FFFFFF"));
        ps.setColor(Color.parseColor("#000000"));
        ps.setStrokeWidth(4.0f * od);
        c.restore();
        m824r(new Integer[0]);
        c.restore();
    }

    private static void m824r(Integer... o) {
        f960p.reset();
        ps.reset();
        if (cf != null) {
            f960p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f960p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f960p.setStyle(Style.FILL);
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
