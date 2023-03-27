package com.photo.blureffectcamera.collagelib;


public class ShapeLayout {
    boolean isScalable;
    int porterDuffClearBorderIntex;
    public Shape[] shapeArr;
    public boolean useLine;


    public ShapeLayout(Shape[] arr) {
        this.isScalable = false;
        this.porterDuffClearBorderIntex = -1;
        this.useLine = true;
        this.shapeArr = arr;
    }

    public void setClearIndex(int index) {
        if (index >= 0 && index < this.shapeArr.length) {
            this.porterDuffClearBorderIntex = index;
        }
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
