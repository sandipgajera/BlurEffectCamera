package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Svg5Circle4 extends Svg {
    private static final Matrix f857m;
    private static float od;
    private static final Paint f858p;
    private static final Paint ps;
    private static final Path f859t;

    static {
        f858p = new Paint();
        ps = new Paint();
        f859t = new Path();
        f857m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 508.0f < h / 513.0f ? w / 508.0f : h / 513.0f;
        m790r(new Integer[0]);
        c.save();
        c.translate((((w - (od * 508.0f)) / 2.0f) + dx) - 4.0f, (((h - (od * 513.0f)) / 2.0f) + dy) - 1.0f);
        f857m.reset();
        f857m.setScale(od, od);
        c.save();
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.0f, 0.33f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f859t.reset();
        f859t.moveTo(0.0f, 0.0f);
        f859t.lineTo(508.9f, 0.0f);
        f859t.cubicTo(508.9f, 0.0f, 433.43f, 102.55f, 446.98f, 185.27f);
        f859t.cubicTo(403.92f, 175.6f, 384.57f, 306.21f, 322.17f, 332.81f);
        f859t.cubicTo(255.42f, 379.25f, 166.41f, 448.91f, 168.34f, 449.4f);
        f859t.cubicTo(128.77f, 443.95f, 31.44f, 475.03f, 0.97f, 513.25f);
        f859t.lineTo(0.0f, 0.0f);
        f859t.transform(f857m);
        c.drawPath(f859t, f858p);
        c.drawPath(f859t, ps);
        c.restore();
        m790r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m790r(new Integer[0]);
        c.restore();
    }

    private static void m790r(Integer... o) {
        f858p.reset();
        ps.reset();
        if (cf != null) {
            f858p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f858p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f858p.setStyle(Style.FILL);
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
