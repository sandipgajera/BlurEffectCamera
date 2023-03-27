package com.photo.blureffectcamera.pointlist;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class CollageLayout {
    boolean checkForConcavite;
    public int[][] exceptionIndexForShapes;
    boolean isScalable;
    public List<MaskPair> maskPairList;
    public List<MaskPairSvg> maskPairListSvg;
    int porterDuffClearBorderIntex;
    public List<PointF[]> shapeList;
    public boolean useLine;

    public CollageLayout() {
        this.isScalable = false;
        this.porterDuffClearBorderIntex = -1;
        this.checkForConcavite = false;
        this.useLine = true;
        this.exceptionIndexForShapes = (int[][]) null;
        this.maskPairList = new ArrayList<MaskPair>();
        this.maskPairListSvg = new ArrayList<MaskPairSvg>();
    }

    public CollageLayout(List<PointF[]> shapeList) {
        this.isScalable = false;
        this.porterDuffClearBorderIntex = -1;
        this.checkForConcavite = false;
        this.useLine = true;
        this.exceptionIndexForShapes = (int[][]) null;
        this.maskPairList = new ArrayList<MaskPair>();
        this.maskPairListSvg = new ArrayList<MaskPairSvg>();
        this.shapeList = shapeList;
    }

    public int[] getexceptionIndex(int index) {
        if (this.exceptionIndexForShapes == null || index >= this.exceptionIndexForShapes.length || index < 0) {
            return null;
        }
        return this.exceptionIndexForShapes[index];
    }

    public void setClearIndex(int index) {
        if (index >= 0 && index < this.shapeList.size()) {
            this.porterDuffClearBorderIntex = index;
        }
    }

    public boolean getConcavite() {
        return this.checkForConcavite;
    }

    public void setScalibility(boolean scalebility) {
        this.isScalable = scalebility;
    }

    public int getClearIndex() {
        return this.porterDuffClearBorderIntex;
    }

    public boolean getScalibility() {
        return this.isScalable;
    }
}
