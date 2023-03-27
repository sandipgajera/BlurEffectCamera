package com.photo.blureffectcamera.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;

import com.photo.blureffectcamera.lyrebirdlib.BlurBuilderNormal;

public class SvgFaceMiddle extends Svg {
    private static final Matrix f920m;
    private static float od;
    private static final Paint f921p;
    private static final Paint ps;
    private static final Path f922t;

    static {
        f921p = new Paint();
        ps = new Paint();
        f922t = new Path();
        f920m = new Matrix();
    }

    public void draw(Canvas c, int w, int h) {
        draw(c, (float) w, (float) h, 0.0f, 0.0f, false);
    }

    public void draw(Canvas c, float w, float h, float dx, float dy, boolean clearMode) {
        od = w / 415.0f < h / 512.0f ? w / 415.0f : h / 512.0f;
        m811r(new Integer[0]);
        c.save();
        c.translate(((w - (od * 415.0f)) / 2.0f) + dx, ((h - (od * 512.0f)) / 2.0f) + dy);
        f920m.reset();
        f920m.setScale(od, od);
        c.save();
        if (clearMode) {
            f921p.setXfermode(this.xferModeClear);
            ps.setXfermode(this.xferModeClear);
        }
        ps.setColor(Color.argb(0, 0, 0, 0));
        ps.setStrokeCap(Cap.BUTT);
        ps.setStrokeJoin(Join.MITER);
        ps.setStrokeMiter(4.0f * od);
        c.translate(0.2f * od, 0.0f);
        c.scale(1.0f, 1.0f);
        c.save();
        c.save();
        f922t.reset();
        f922t.moveTo(7.73f, 512.72f);
        f922t.cubicTo(7.73f, 512.72f, 52.28f, 505.55f, 59.96f, 453.06f);
        f922t.cubicTo(60.47f, 431.56f, 55.35f, 407.75f, 69.44f, 385.98f);
        f922t.cubicTo(111.17f, 383.42f, 107.07f, 355.0f, 99.65f, 344.51f);
        f922t.cubicTo(88.89f, 328.38f, 84.54f, 325.05f, 84.54f, 325.05f);
        f922t.cubicTo(84.54f, 325.05f, 136.26f, 339.9f, 129.35f, 277.17f);
        f922t.cubicTo(118.85f, 248.49f, 159.81f, 246.7f, 177.23f, 247.21f);
        f922t.cubicTo(227.15f, 243.12f, 213.33f, 191.4f, 212.56f, 188.07f);
        f922t.cubicTo(217.17f, 177.06f, 172.36f, 75.93f, 164.94f, 57.24f);
        f922t.cubicTo(157.0f, 43.41f, 159.3f, 5.52f, 167.24f, BlurBuilderNormal.BITMAP_SCALE);
        f922t.cubicTo(175.18f, BlurBuilderNormal.BITMAP_SCALE, 415.59f, BlurBuilderNormal.BITMAP_SCALE, 415.59f, BlurBuilderNormal.BITMAP_SCALE);
        f922t.cubicTo(415.59f, BlurBuilderNormal.BITMAP_SCALE, 394.86f, 7.4f, 378.98f, 111.52f);
        f922t.cubicTo(384.61f, 136.44f, 386.41f, 130.72f, 383.08f, 150.43f);
        f922t.cubicTo(382.82f, 171.94f, 357.9f, 192.87f, 349.79f, 201.13f);
        f922t.cubicTo(340.58f, 210.51f, 281.94f, 250.54f, 273.5f, 263.86f);
        f922t.cubicTo(261.46f, 285.87f, 268.12f, 303.8f, 292.19f, 324.02f);
        f922t.cubicTo(299.35f, 330.42f, 305.5f, 335.03f, 305.5f, 335.03f);
        f922t.cubicTo(305.5f, 335.03f, 311.64f, 346.04f, 304.22f, 357.56f);
        f922t.cubicTo(296.79f, 369.09f, 289.37f, 378.05f, 287.83f, 385.73f);
        f922t.cubicTo(289.88f, 392.64f, 306.52f, 403.91f, 303.96f, 410.05f);
        f922t.cubicTo(279.13f, 426.18f, 278.87f, 440.52f, 288.86f, 456.39f);
        f922t.cubicTo(302.68f, 477.13f, 294.75f, 504.53f, 284.25f, 512.72f);
        f922t.cubicTo(268.25f, 512.72f, 7.73f, 512.72f, 7.73f, 512.72f);
        f922t.transform(f920m);
        c.drawPath(f922t, f921p);
        c.drawPath(f922t, ps);
        c.restore();
        m811r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m811r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.save();
        c.restore();
        m811r(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(1));
        c.restore();
        m811r(new Integer[0]);
        c.restore();
    }

    private static void m811r(Integer... o) {
        f921p.reset();
        ps.reset();
        if (cf != null) {
            f921p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        f921p.setAntiAlias(true);
        ps.setAntiAlias(true);
        f921p.setStyle(Style.FILL);
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
