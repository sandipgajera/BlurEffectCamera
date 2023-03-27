package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class SvgStoneHeart extends Svg {
    private static final Matrix f1028m;
    private static float od;
    private static final Paint f1029p;
    private static final Paint ps;
    private static final Path f1030t;

    static {
        f1029p = new Paint();
        ps = new Paint();
        f1030t = new Path();
        f1028m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 512.0f < h / 424.0f ? w / 512.0f : h / 424.0f;
        m847r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 512.0f)) / 2.0f) + dx, ((h - (od * 424.0f)) / 2.0f) + dy);
        f1028m.reset();
        f1028m.setScale(od, od);
        c.save();
        if (clearMode) {
            f1029p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.scale(1.0f, 1.0f);
        c.save();
        f1029p.setColor(Color.parseColor("#020202"));
        f1030t.reset();
        f1030t.moveTo(257.06f, 58.4f);
        f1030t.cubicTo(186.36f, -28.51f, -11.65f, -28.14f, 0.54f, 126.22f);
        f1030t.cubicTo(6.14f, 189.84f, 32.1f, 229.04f, 65.18f, 270.78f);
        f1030t.cubicTo(110.62f, 328.09f, 254.03f, 416.35f, 256.06f, 424.5f);
        f1030t.cubicTo(258.1f, 416.35f, 401.98f, 328.09f, 447.41f, 270.78f);
        f1030t.cubicTo(480.49f, 229.04f, 506.45f, 189.84f, 512.05f, 126.22f);
        f1030t.cubicTo(524.24f, -28.14f, 327.75f, -28.51f, 257.06f, 58.4f);
        f1030t.transform(f1028m);
        c.drawPath(f1030t, f1029p);
        c.drawPath(f1030t, ps);
        c.restore();
        m847r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        f1029p.setColor(Color.parseColor("#020202"));
        c.restore();
        m847r(new Integer[0]);
        c.restore();
    }

    private static void m847r(Integer... o) {
        f1029p.reset();
        ps.reset();
        if (cf != null) {
            f1029p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f1029p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f1029p.setStyle(Style.FILL);
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
