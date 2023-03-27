package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgPaperCut5 extends Svg {
    private static final Matrix f992m;
    private static float od;
    private static final Paint f993p;
    private static final Paint ps;
    private static final Path f994t;

    static {
        f993p = new Paint();
        ps = new Paint();
        f994t = new Path();
        f992m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 509.0f < h / 512.0f ? w / 509.0f : h / 512.0f;
        m835r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 509.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f992m.reset();
        f992m.setScale(od, od);
        c.save();
        if (clearMode) {
            f993p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.0f, 0.2f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f994t.reset();
        f994t.moveTo(72.73f, 74.72f);
        f994t.lineTo(434.39f, 74.72f);
        f994t.quadTo(434.39f, 74.72f, 434.39f, 74.72f);
        f994t.lineTo(434.39f, 437.59f);
        f994t.quadTo(434.39f, 437.59f, 434.39f, 437.59f);
        f994t.lineTo(72.73f, 437.59f);
        f994t.quadTo(72.73f, 437.59f, 72.73f, 437.59f);
        f994t.lineTo(72.73f, 74.72f);
        f994t.quadTo(72.73f, 74.72f, 72.73f, 74.72f);
        f994t.transform(f992m);
        c.drawPath(f994t, f993p);
        c.drawPath(f994t, ps);
        c.restore();
        m835r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m835r(new Integer[0]);
        c.restore();
    }

    private static void m835r(Integer... o) {
        f993p.reset();
        ps.reset();
        if (cf != null) {
            f993p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f993p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f993p.setStyle(Style.FILL);
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
