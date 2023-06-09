package com.photo.blureffectcamera.pointlist;

import android.graphics.PointF;

import com.photo.blureffectcamera.lyrebirdlib.BlurBuilderNormal;

import java.util.ArrayList;
import java.util.List;

public class CollageScrapBook extends Collage {
    public CollageScrapBook(int width, int height) {
        this.collageLayoutList = new ArrayList();
        List<PointF[]> shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.5f, ((float) height) * 1.0f), new PointF(((float) width) * 0.5f, ((float) height) * 0.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.5f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.5f, ((float) height) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.5f), new PointF(((float) width) * 1.0f, ((float) height) * 0.5f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.5f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(0.2f * ((float) width), 0.6f * ((float) height)), new PointF(0.8f * ((float) width), 0.6f * ((float) height)), new PointF(0.8f * ((float) width), ((float) height) * 0.0f), new PointF(0.2f * ((float) width), ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.5f, ((float) height) * 1.0f), new PointF(((float) width) * 0.5f, BlurBuilderNormal.BITMAP_SCALE * ((float) height)), new PointF(((float) width) * 0.0f, BlurBuilderNormal.BITMAP_SCALE * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.5f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, BlurBuilderNormal.BITMAP_SCALE * ((float) height)), new PointF(((float) width) * 0.5f, BlurBuilderNormal.BITMAP_SCALE * ((float) height))});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.5f), new PointF(((float) width) * 0.5f, ((float) height) * 0.5f), new PointF(((float) width) * 0.5f, ((float) height) * 0.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.5f, ((float) height) * 1.0f), new PointF(((float) width) * 0.5f, ((float) height) * 0.5f), new PointF(((float) width) * 0.0f, ((float) height) * 0.5f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.5f, ((float) height) * 0.5f), new PointF(((float) width) * 1.0f, ((float) height) * 0.5f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.5f, ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.5f, ((float) height) * 1.0f), new PointF(((float) width) * 0.5f, ((float) height) * 0.5f), new PointF(((float) width) * 1.0f, ((float) height) * 0.5f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.5f), new PointF(((float) width) * 0.5f, ((float) height) * 0.5f), new PointF(((float) width) * 0.5f, ((float) height) * 0.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.5f, ((float) height) * 1.0f), new PointF(((float) width) * 0.5f, ((float) height) * 0.5f), new PointF(((float) width) * 0.0f, ((float) height) * 0.5f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.5f, ((float) height) * 0.5f), new PointF(((float) width) * 1.0f, ((float) height) * 0.5f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.5f, ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.5f, ((float) height) * 1.0f), new PointF(((float) width) * 0.5f, ((float) height) * 0.5f), new PointF(((float) width) * 1.0f, ((float) height) * 0.5f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.25f * ((float) width), 0.25f * ((float) height)), new PointF(0.25f * ((float) width), 0.75f * ((float) height)), new PointF(0.75f * ((float) width), 0.75f * ((float) height)), new PointF(0.75f * ((float) width), 0.25f * ((float) height))});
        CollageLayout collageLayout = new CollageLayout(shapeList);
        collageLayout.setClearIndex(4);
        this.collageLayoutList.add(collageLayout);
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, 0.6666667f * ((float) height)), new PointF(((float) width) * 0.5f, 0.6666667f * ((float) height)), new PointF(((float) width) * 0.5f, ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.5f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, 0.6666667f * ((float) height)), new PointF(((float) width) * 0.5f, 0.6666667f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, 0.6666667f * ((float) height)), new PointF(((float) width) * 0.0f, 0.33333334f * ((float) height)), new PointF(((float) width) * 0.5f, 0.33333334f * ((float) height)), new PointF(((float) width) * 0.5f, 0.6666667f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.5f, 0.33333334f * ((float) height)), new PointF(((float) width) * 1.0f, 0.33333334f * ((float) height)), new PointF(((float) width) * 1.0f, 0.6666667f * ((float) height)), new PointF(((float) width) * 0.5f, 0.6666667f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, 0.33333334f * ((float) height)), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.5f, ((float) height) * 0.0f), new PointF(((float) width) * 0.5f, 0.33333334f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 1.0f, 0.33333334f * ((float) height)), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.5f, ((float) height) * 0.0f), new PointF(((float) width) * 0.5f, 0.33333334f * ((float) height))});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.5f), new PointF(0.33333334f * ((float) width), ((float) height) * 0.5f), new PointF(0.33333334f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.5f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(0.33333334f * ((float) width), ((float) height) * 0.0f), new PointF(0.33333334f * ((float) width), ((float) height) * 0.5f)});
        shapeList.add(new PointF[]{new PointF(0.33333334f * ((float) width), ((float) height) * 0.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 0.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 0.5f), new PointF(0.33333334f * ((float) width), ((float) height) * 0.5f)});
        shapeList.add(new PointF[]{new PointF(0.33333334f * ((float) width), ((float) height) * 1.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 1.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 0.5f), new PointF(0.33333334f * ((float) width), ((float) height) * 0.5f)});
        shapeList.add(new PointF[]{new PointF(0.6666667f * ((float) width), ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.5f), new PointF(0.6666667f * ((float) width), ((float) height) * 0.5f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 1.0f, ((float) height) * 0.5f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 1.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, 0.6666667f * ((float) height)), new PointF(((float) width) * 0.5f, 0.6666667f * ((float) height)), new PointF(((float) width) * 0.5f, ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.5f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(((float) width) * 1.0f, 0.6666667f * ((float) height)), new PointF(((float) width) * 0.5f, 0.6666667f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, 0.6666667f * ((float) height)), new PointF(((float) width) * 0.0f, 0.33333334f * ((float) height)), new PointF(((float) width) * 0.5f, 0.33333334f * ((float) height)), new PointF(((float) width) * 0.5f, 0.6666667f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.5f, 0.33333334f * ((float) height)), new PointF(((float) width) * 1.0f, 0.33333334f * ((float) height)), new PointF(((float) width) * 1.0f, 0.6666667f * ((float) height)), new PointF(((float) width) * 0.5f, 0.6666667f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, 0.33333334f * ((float) height)), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.5f, ((float) height) * 0.0f), new PointF(((float) width) * 0.5f, 0.33333334f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 1.0f, 0.33333334f * ((float) height)), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 0.5f, ((float) height) * 0.0f), new PointF(((float) width) * 0.5f, 0.33333334f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.25f * ((float) width), 0.25f * ((float) height)), new PointF(0.25f * ((float) width), 0.75f * ((float) height)), new PointF(0.75f * ((float) width), 0.75f * ((float) height)), new PointF(0.75f * ((float) width), 0.25f * ((float) height))});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, ((float) height) * 0.5f), new PointF(0.33333334f * ((float) width), ((float) height) * 0.5f), new PointF(0.33333334f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 0.5f), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(0.33333334f * ((float) width), ((float) height) * 0.0f), new PointF(0.33333334f * ((float) width), ((float) height) * 0.5f)});
        shapeList.add(new PointF[]{new PointF(0.33333334f * ((float) width), ((float) height) * 0.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 0.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 0.5f), new PointF(0.33333334f * ((float) width), ((float) height) * 0.5f)});
        shapeList.add(new PointF[]{new PointF(0.33333334f * ((float) width), ((float) height) * 1.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 1.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 0.5f), new PointF(0.33333334f * ((float) width), ((float) height) * 0.5f)});
        shapeList.add(new PointF[]{new PointF(0.6666667f * ((float) width), ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.5f), new PointF(0.6666667f * ((float) width), ((float) height) * 0.5f)});
        shapeList.add(new PointF[]{new PointF(((float) width) * 1.0f, ((float) height) * 0.5f), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 1.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 0.5f)});
        shapeList.add(new PointF[]{new PointF(0.25f * ((float) width), 0.25f * ((float) height)), new PointF(0.25f * ((float) width), 0.75f * ((float) height)), new PointF(0.75f * ((float) width), 0.75f * ((float) height)), new PointF(0.75f * ((float) width), 0.25f * ((float) height))});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, BlurBuilderNormal.BITMAP_SCALE * ((float) height)), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(BlurBuilderNormal.BITMAP_SCALE * ((float) width), ((float) height) * 0.0f), new PointF(BlurBuilderNormal.BITMAP_SCALE * ((float) width), BlurBuilderNormal.BITMAP_SCALE * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.3f * ((float) width), 0.45f * ((float) height)), new PointF(0.7f * ((float) width), 0.45f * ((float) height)), new PointF(0.7f * ((float) width), ((float) height) * 0.0f), new PointF(0.3f * ((float) width), ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(0.6f * ((float) width), ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, BlurBuilderNormal.BITMAP_SCALE * ((float) height)), new PointF(0.6f * ((float) width), BlurBuilderNormal.BITMAP_SCALE * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, 0.7f * ((float) height)), new PointF(((float) width) * 0.0f, 0.3f * ((float) height)), new PointF(0.45f * ((float) width), 0.3f * ((float) height)), new PointF(0.45f * ((float) width), 0.7f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, 0.6f * ((float) height)), new PointF(BlurBuilderNormal.BITMAP_SCALE * ((float) width), 0.6f * ((float) height)), new PointF(BlurBuilderNormal.BITMAP_SCALE * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.3f * ((float) width), 0.55f * ((float) height)), new PointF(0.7f * ((float) width), 0.55f * ((float) height)), new PointF(0.7f * ((float) width), ((float) height) * 1.0f), new PointF(0.3f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.6f * ((float) width), 0.6f * ((float) height)), new PointF(((float) width) * 1.0f, 0.6f * ((float) height)), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(0.6f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.55f * ((float) width), 0.7f * ((float) height)), new PointF(0.55f * ((float) width), 0.3f * ((float) height)), new PointF(((float) width) * 1.0f, 0.3f * ((float) height)), new PointF(((float) width) * 1.0f, 0.7f * ((float) height))});
        this.collageLayoutList.add(new CollageLayout(shapeList));
        shapeList = new ArrayList();
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, 0.33333334f * ((float) height)), new PointF(((float) width) * 0.0f, ((float) height) * 0.0f), new PointF(0.33333334f * ((float) width), ((float) height) * 0.0f), new PointF(0.33333334f * ((float) width), 0.33333334f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.33333334f * ((float) width), 0.33333334f * ((float) height)), new PointF(0.6666667f * ((float) width), 0.33333334f * ((float) height)), new PointF(0.6666667f * ((float) width), ((float) height) * 0.0f), new PointF(0.33333334f * ((float) width), ((float) height) * 0.0f)});
        shapeList.add(new PointF[]{new PointF(0.6666667f * ((float) width), ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, ((float) height) * 0.0f), new PointF(((float) width) * 1.0f, 0.33333334f * ((float) height)), new PointF(0.6666667f * ((float) width), 0.33333334f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, 0.6666667f * ((float) height)), new PointF(((float) width) * 0.0f, 0.33333334f * ((float) height)), new PointF(0.33333334f * ((float) width), 0.33333334f * ((float) height)), new PointF(0.33333334f * ((float) width), 0.6666667f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(((float) width) * 0.0f, ((float) height) * 1.0f), new PointF(((float) width) * 0.0f, 0.6666667f * ((float) height)), new PointF(0.33333334f * ((float) width), 0.6666667f * ((float) height)), new PointF(0.33333334f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.33333334f * ((float) width), 0.6666667f * ((float) height)), new PointF(0.6666667f * ((float) width), 0.6666667f * ((float) height)), new PointF(0.6666667f * ((float) width), ((float) height) * 1.0f), new PointF(0.33333334f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.6666667f * ((float) width), 0.6666667f * ((float) height)), new PointF(((float) width) * 1.0f, 0.6666667f * ((float) height)), new PointF(((float) width) * 1.0f, ((float) height) * 1.0f), new PointF(0.6666667f * ((float) width), ((float) height) * 1.0f)});
        shapeList.add(new PointF[]{new PointF(0.6666667f * ((float) width), 0.6666667f * ((float) height)), new PointF(0.6666667f * ((float) width), 0.33333334f * ((float) height)), new PointF(((float) width) * 1.0f, 0.33333334f * ((float) height)), new PointF(((float) width) * 1.0f, 0.6666667f * ((float) height))});
        shapeList.add(new PointF[]{new PointF(0.6666667f * ((float) width), 0.6666667f * ((float) height)), new PointF(0.6666667f * ((float) width), 0.33333334f * ((float) height)), new PointF(0.33333334f * ((float) width), 0.33333334f * ((float) height)), new PointF(0.33333334f * ((float) width), 0.6666667f * ((float) height))});
        this.collageLayoutList.add(new CollageLayout(shapeList));
    }
}
