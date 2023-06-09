package com.photo.blureffectcamera.pointlist;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class Collage7 extends Collage {
    public static int shapeCount;

    static {
        shapeCount = 7;
    }

    public Collage7(int width, int height) {
        this.collageLayoutList = new ArrayList();
        PointF[] points = new PointF[4];
        List<PointF[]> shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(0.3085f * ((float) width), ((float) height) * 0.0f), new PointF(0.6915f * ((float) width), ((float) height) * 0.0f), new PointF(0.3085f * ((float) width), 0.333f * ((float) height)), new PointF(0.6915f * ((float) width), 0.333f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.3085f * ((float) width), 0.333f * ((float) height)), new PointF(0.6915f * ((float) width), 0.333f * ((float) height)), new PointF(0.3085f * ((float) width), 0.666f * ((float) height)), new PointF(0.6915f * ((float) width), 0.666f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.3085f * ((float) width), 0.666f * ((float) height)), new PointF(0.6915f * ((float) width), 0.666f * ((float) height)), new PointF(0.3085f * ((float) width), ((float) height) * 1.0f), new PointF(0.6915f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.02125f * ((float) width), 0.1665f * ((float) height)), new PointF(0.40425f * ((float) width), 0.1665f * ((float) height)), new PointF(0.02125f * ((float) width), 0.4995f * ((float) height)), new PointF(0.40425f * ((float) width), 0.4995f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.02125f * ((float) width), 0.4995f * ((float) height)), new PointF(0.40425f * ((float) width), 0.4995f * ((float) height)), new PointF(0.02125f * ((float) width), 0.8325f * ((float) height)), new PointF(0.40425f * ((float) width), 0.8325f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.59575f * ((float) width), 0.1665f * ((float) height)), new PointF(0.97875f * ((float) width), 0.1665f * ((float) height)), new PointF(0.59575f * ((float) width), 0.4995f * ((float) height)), new PointF(0.97875f * ((float) width), 0.4995f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.59575f * ((float) width), 0.4995f * ((float) height)), new PointF(0.97875f * ((float) width), 0.4995f * ((float) height)), new PointF(0.59575f * ((float) width), 0.8325f * ((float) height)), new PointF(0.97875f * ((float) width), 0.8325f * ((float) height))});
        CollageLayout collageLayout = new CollageLayout(shapeList);
        collageLayout.useLine = false;
        collageLayout.maskPairListSvg.add(new MaskPairSvg(0, 70));
        collageLayout.maskPairListSvg.add(new MaskPairSvg(1, 70));
        collageLayout.maskPairListSvg.add(new MaskPairSvg(2, 70));
        collageLayout.maskPairListSvg.add(new MaskPairSvg(3, 70));
        collageLayout.maskPairListSvg.add(new MaskPairSvg(4, 70));
        collageLayout.maskPairListSvg.add(new MaskPairSvg(5, 70));
        collageLayout.maskPairListSvg.add(new MaskPairSvg(6, 70));
        this.collageLayoutList.add(collageLayout);
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(0.25f * ((float) width), 0.35f * ((float) height)), new PointF(0.5f * ((float) width), 0.2f * ((float) height)), new PointF(0.75f * ((float) width), 0.35f * ((float) height)), new PointF(0.75f * ((float) width), 0.65f * ((float) height)), new PointF(0.5f * ((float) width), 0.8f * ((float) height)), new PointF(0.25f * ((float) width), 0.65f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.25f * ((float) width), 0.65f * ((float) height)), new PointF(((float) width) * 0.0f, 0.5f * ((float) height)), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(0.25f * ((float) width), ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(0.25f * ((float) width), 0.35f * ((float) height)), new PointF(0.25f * ((float) width), ((float) height) * 0.0f), new PointF(0.85f * ((float) width), ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(0.85f * ((float) width), ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, 0.5f * ((float) height)), new PointF(0.5f * ((float) width), 0.2f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.75f * ((float) width), 0.35f * ((float) height)), new PointF(((float) width) * 1.0f, 0.5f * ((float) height)), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(0.75f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.75f * ((float) width), 0.65f * ((float) height)), new PointF(0.75f * ((float) width), ((float) height) * 1.0f), new PointF(0.15f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, 0.5f * ((float) height)), new PointF(0.5f * ((float) width), 0.8f * ((float) height)), new PointF(0.15f * ((float) width), ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, ((float) height) * 1.0f)});
        collageLayout = new CollageLayout(shapeList);
        collageLayout.useLine = false;
        this.collageLayoutList.add(collageLayout);
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.33333334f, ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.33333334f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.33333334f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(0.59999996f * ((float) width), 0.41666666f * ((float) height)), new PointF(0.59999996f * ((float) width), ((float) height) * 0.0f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.0f), new PointF(((float) width) * 0.33333334f, 0.41666666f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.59999996f * ((float) width), 0.41666666f * ((float) height)), new PointF(((float) width) * 1.0f, 0.41666666f * ((float) height)), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(0.59999996f * ((float) width), ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.33333334f, ((float) height) * 1.0f), new PointF(0.73333335f * ((float) width), ((float) height) * 1.0f), new PointF(0.73333335f * ((float) width), 0.41666666f * ((float) height)), new PointF(((float) width) * 0.33333334f, 0.41666666f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.73333335f * ((float) width), ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, 0.41666666f * ((float) height)), new PointF(0.73333335f * ((float) width), 0.41666666f * ((float) height))});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(0.25f * ((float) width), ((float) height) * 1.0f), new PointF(0.25f * ((float) width), ((float) height) * 0.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(0.5f * ((float) width), ((float) height) * 0.0f), new PointF(0.25f * ((float) width), ((float) height) * 0.0f), new PointF(0.25f * ((float) width), 0.5f * ((float) height)), new PointF(0.5f * ((float) width), 0.5f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.75f * ((float) width), ((float) height) * 0.0f), new PointF(0.5f * ((float) width), ((float) height) * 0.0f), new PointF(0.5f * ((float) width), 0.5f * ((float) height)), new PointF(0.75f * ((float) width), 0.5f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.75f * ((float) width), ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, 0.5f * ((float) height)), new PointF(0.75f * ((float) width), 0.5f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.5f * ((float) width), 0.5f * ((float) height)), new PointF(0.25f * ((float) width), 0.5f * ((float) height)), new PointF(0.25f * ((float) width), ((float) height) * 1.0f), new PointF(0.5f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.5f * ((float) width), 0.5f * ((float) height)), new PointF(0.75f * ((float) width), 0.5f * ((float) height)), new PointF(0.75f * ((float) width), ((float) height) * 1.0f), new PointF(0.5f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.75f * ((float) width), 0.5f * ((float) height)), new PointF(((float) width) * 1.0f, 0.5f * ((float) height)), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(0.75f * ((float) width), ((float) height) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.33333334f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.33333334f, ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.33333334f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.6666667f, ((float) height) * 1.0f), new PointF(((float) width) * 0.33333334f, ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.6666667f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.33333334f, ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.33333334f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.6666667f, ((float) height) * 1.0f), new PointF(((float) width) * 0.33333334f, ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.6666667f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.0f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.0f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.0f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.6666667f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.6666667f, ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.33333334f, ((float) height) * 0.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.33333334f, ((float) height) * 0.0f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.0f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.6666667f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.6666667f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(0.5f * ((float) width), ((float) height) * 0.33333334f), new PointF(0.5f * ((float) width), ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.33333334f, ((float) height) * 0.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.33333334f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.0f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.0f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f), new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(0.5f * ((float) width), ((float) height) * 0.6666667f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f), new PointF(0.5f * ((float) width), ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f), new PointF(0.5f * ((float) width), ((float) height) * 0.6666667f), new PointF(0.5f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.5f * ((float) width), ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f), new PointF(0.5f * ((float) width), ((float) height) * 0.6666667f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.0f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.6666667f, ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.0f), new PointF(((float) width) * 0.6666667f, 0.25f * ((float) height)), new PointF(((float) width) * 1.0f, 0.25f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 1.0f, 0.25f * ((float) height)), new PointF(((float) width) * 1.0f, 0.5f * ((float) height)), new PointF(((float) width) * 0.6666667f, 0.5f * ((float) height)), new PointF(((float) width) * 0.6666667f, 0.25f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 1.0f, 0.5f * ((float) height)), new PointF(((float) width) * 0.6666667f, 0.5f * ((float) height)), new PointF(((float) width) * 0.6666667f, 0.75f * ((float) height)), new PointF(((float) width) * 1.0f, 0.75f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 1.0f, 0.75f * ((float) height)), new PointF(((float) width) * 0.6666667f, 0.75f * ((float) height)), new PointF(((float) width) * 0.6666667f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.0f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.33333334f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.6666667f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.6666667f, ((float) height) * 0.33333334f), new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.33333334f, ((float) height) * 1.0f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.0f, ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.33333334f, ((float) height) * 0.6666667f), new PointF(((float) width) * 0.33333334f, ((float) height) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, 0.75f * ((float) height)), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(0.29166666f * ((float) width), ((float) height) * 0.0f), new PointF(0.29166666f * ((float) width), 0.75f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.29166666f * ((float) width), 0.75f * ((float) height)), new PointF(0.29166666f * ((float) width), ((float) height) * 0.0f), new PointF(0.5833333f * ((float) width), ((float) height) * 0.0f), new PointF(0.5833333f * ((float) width), 0.75f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, 0.75f * ((float) height)), new PointF(0.29166666f * ((float) width), 0.75f * ((float) height)), new PointF(0.29166666f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.29166666f * ((float) width), ((float) height) * 1.0f), new PointF(0.29166666f * ((float) width), 0.75f * ((float) height)), new PointF(0.5833333f * ((float) width), 0.75f * ((float) height)), new PointF(0.5833333f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.5833333f * ((float) width), ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f), new PointF(0.5833333f * ((float) width), ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f), new PointF(0.5833333f * ((float) width), ((float) height) * 0.33333334f), new PointF(0.5833333f * ((float) width), ((float) height) * 0.6666667f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(0.5833333f * ((float) width), ((float) height) * 1.0f), new PointF(0.5833333f * ((float) width), ((float) height) * 0.6666667f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, 0.25f * ((float) height)), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(0.29166666f * ((float) width), ((float) height) * 0.0f), new PointF(0.29166666f * ((float) width), 0.25f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.29166666f * ((float) width), 0.25f * ((float) height)), new PointF(0.29166666f * ((float) width), ((float) height) * 0.0f), new PointF(0.5833333f * ((float) width), ((float) height) * 0.0f), new PointF(0.5833333f * ((float) width), 0.25f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, 0.25f * ((float) height)), new PointF(0.29166666f * ((float) width), 0.25f * ((float) height)), new PointF(0.29166666f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.29166666f * ((float) width), ((float) height) * 1.0f), new PointF(0.29166666f * ((float) width), 0.25f * ((float) height)), new PointF(0.5833333f * ((float) width), 0.25f * ((float) height)), new PointF(0.5833333f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.5833333f * ((float) width), ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f), new PointF(0.5833333f * ((float) width), ((float) height) * 0.33333334f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 1.0f, ((float) height) * 0.33333334f), new PointF(0.5833333f * ((float) width), ((float) height) * 0.33333334f), new PointF(0.5833333f * ((float) width), ((float) height) * 0.6666667f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f)});
        shapeList.add(new PointF[]{new PointF(0.5833333f * ((float) width), ((float) height) * 1.0f), new PointF(0.5833333f * ((float) width), ((float) height) * 0.6666667f), new PointF(((float) width) * 1.0f, ((float) height) * 0.6666667f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(0.3085f * ((float) width), ((float) height) * 0.0f), new PointF(0.6915f * ((float) width), ((float) height) * 0.0f), new PointF(0.3085f * ((float) width), 0.333f * ((float) height)), new PointF(0.6915f * ((float) width), 0.333f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.3085f * ((float) width), 0.333f * ((float) height)), new PointF(0.6915f * ((float) width), 0.333f * ((float) height)), new PointF(0.3085f * ((float) width), 0.666f * ((float) height)), new PointF(0.6915f * ((float) width), 0.666f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.3085f * ((float) width), 0.666f * ((float) height)), new PointF(0.6915f * ((float) width), 0.666f * ((float) height)), new PointF(0.3085f * ((float) width), ((float) height) * 1.0f), new PointF(0.6915f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.02125f * ((float) width), 0.1665f * ((float) height)), new PointF(0.40425f * ((float) width), 0.1665f * ((float) height)), new PointF(0.02125f * ((float) width), 0.4995f * ((float) height)), new PointF(0.40425f * ((float) width), 0.4995f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.02125f * ((float) width), 0.4995f * ((float) height)), new PointF(0.40425f * ((float) width), 0.4995f * ((float) height)), new PointF(0.02125f * ((float) width), 0.8325f * ((float) height)), new PointF(0.40425f * ((float) width), 0.8325f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.59575f * ((float) width), 0.1665f * ((float) height)), new PointF(0.97875f * ((float) width), 0.1665f * ((float) height)), new PointF(0.59575f * ((float) width), 0.4995f * ((float) height)), new PointF(0.97875f * ((float) width), 0.4995f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.59575f * ((float) width), 0.4995f * ((float) height)), new PointF(0.97875f * ((float) width), 0.4995f * ((float) height)), new PointF(0.59575f * ((float) width), 0.8325f * ((float) height)), new PointF(0.97875f * ((float) width), 0.8325f * ((float) height))});
        collageLayout = new CollageLayout(shapeList);
        collageLayout.useLine = false;
        collageLayout.maskPairListSvg.add(new MaskPairSvg(0, 70));
        collageLayout.maskPairListSvg.add(new MaskPairSvg(1, 70));
        collageLayout.maskPairListSvg.add(new MaskPairSvg(2, 70));
        collageLayout.maskPairListSvg.add(new MaskPairSvg(3, 70));
        collageLayout.maskPairListSvg.add(new MaskPairSvg(4, 70));
        collageLayout.maskPairListSvg.add(new MaskPairSvg(5, 70));
        collageLayout.maskPairListSvg.add(new MaskPairSvg(6, 70));
        this.collageLayoutList.add(collageLayout);
    }
}
