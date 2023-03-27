package com.photo.blureffectcamera.collagelib;

import android.graphics.Point;
import android.graphics.PointF;

import java.util.ArrayList;

public class GridLine {
    float f524a;
    float f525b;
    float f526c;
    ArrayList<Point> distanceIndexList;
    ArrayList<PointF> distanceList;
    private float dx;
    private float dy;
    float eps;
    float eps2;
    float epsilon;
    ArrayList<Point> indexArrayList;
    public boolean isSide;
    int minXIndex;
    int minXIndexNegative;
    int minYIndex;
    int minYIndexNegative;
    PointF negativeMinDistance;
    ArrayList<PointF> pointArrayList;
    public PointF pointHandle;
    PointF positiveMinDistance;
    float primeA;
    float primeB;
    float primeC;
    public float slope;
    ArrayList<Float> slopeArrayList;
    PointF vector;

    public GridLine(PointF p1, PointF p2, Point index1, Point index2) {
        this.pointArrayList = new ArrayList();
        this.indexArrayList = new ArrayList();
        this.slopeArrayList = new ArrayList();
        this.distanceList = new ArrayList();
        this.distanceIndexList = new ArrayList();
        this.dx = 0.0f;
        this.dy = 0.0f;
        this.eps = 1.0E-6f;
        this.pointHandle = new PointF();
        this.vector = new PointF();
        this.eps2 = 0.001f;
        this.epsilon = 1.5f;
        this.positiveMinDistance = new PointF();
        this.negativeMinDistance = new PointF();
        setAbc(p1, p2);
        this.pointArrayList.add(p1);
        this.pointArrayList.add(p2);
        this.indexArrayList.add(index1);
        this.indexArrayList.add(index2);
        this.vector.set(p2.x - p1.x, p2.y - p1.y);
    }

    void addToDx(float x) {
        this.dx += x;
    }

    void addToDy(float y) {
        this.dy += y;
    }

    void setPointHandle(boolean last) {
        int index1 = 0;
        int index2 = 1;
        if (last) {
            index1 = this.pointArrayList.size() - 2;
            index2 = this.pointArrayList.size() - 1;
        }
        PointF p1 = (PointF) this.pointArrayList.get(index1);
        PointF p2 = (PointF) this.pointArrayList.get(index2);
        this.pointHandle.set((p1.x + p2.x) / 2.0f, (p1.y + p2.y) / 2.0f);
    }

    void merge(GridLine gridLine) {
        for (int i = 0; i < gridLine.pointArrayList.size(); i++) {
            this.pointArrayList.add(gridLine.pointArrayList.get(i));
            this.indexArrayList.add(gridLine.indexArrayList.get(i));
            this.distanceList.add(gridLine.distanceList.get(i));
            this.distanceIndexList.add(gridLine.distanceIndexList.get(i));
            this.slopeArrayList.add(gridLine.slopeArrayList.get(i));
        }
    }

    void setAbc(PointF p1, PointF p2) {
        float x1 = p1.x;
        float y1 = p1.y;
        float x2 = p2.x;
        float y2 = p2.y;
        this.f524a = y1 - y2;
        this.f525b = x2 - x1;
        this.f526c = ((x1 - x2) * y1) + ((y2 - y1) * x1);
        this.slope = (y2 - y1) / (x2 - x1);
        if (Math.abs(this.f524a) > this.eps) {
            this.primeA = 1.0f;
            this.primeB = this.f525b / this.f524a;
            this.primeC = this.f526c / this.f524a;
        } else if (Math.abs(this.f525b) > this.eps) {
            this.primeA = this.f524a / this.f525b;
            this.primeB = 1.0f;
            this.primeC = this.f526c / this.f525b;
        } else {
            this.primeA = this.f524a;
            this.primeB = this.f525b;
            this.primeC = this.f526c;
        }
    }

    boolean compareSloples(float s) {
        if ((!Float.isInfinite(s) || !Float.isInfinite(this.slope)) && Math.abs(this.slope - s) >= this.eps2) {
            return false;
        }
        return true;
    }

    void addPoints(PointF p1, PointF p2, Point index1, Point index2) {
        this.pointArrayList.add(p1);
        this.pointArrayList.add(p2);
        this.indexArrayList.add(index1);
        this.indexArrayList.add(index2);
    }

    boolean isPointOnLine(PointF p) {
        if (Math.abs(((this.f524a * p.x) + (this.f525b * p.y)) + this.f526c) <= this.epsilon) {
            return true;
        }
        return false;
    }

    boolean shouldAddLineSegment(PointF p1, PointF p2) {
        if (isLineSegmentOnLinex(p1, p2)) {
            for (int i = 0; i < this.pointArrayList.size(); i += 2) {
                PointF pp = (PointF) this.pointArrayList.get(i);
                PointF pq = (PointF) this.pointArrayList.get(i + 1);
                if (isBetween(pp, pq, p1, 0.1f) || isBetween(pp, pq, p2, 0.1f)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isTouchOnHandle(float x, float y, float tolerance) {
        if (((x - this.pointHandle.x) * (x - this.pointHandle.x)) + ((y - this.pointHandle.y) * (y - this.pointHandle.y)) < tolerance * tolerance) {
            return true;
        }
        return false;
    }

    boolean isBetween(PointF a, PointF b, PointF c, float tolerance) {
        if (((float) (((double) Math.abs(((((c.y - b.y) * a.x) - ((c.x - b.x) * a.y)) + (c.x * b.y)) - (c.y * b.x))) / Math.sqrt(Math.pow((double) (c.y - b.y), 2.0d) + Math.pow((double) (c.x - b.x), 2.0d)))) > tolerance) {
            return false;
        }
        float dotproduct = ((c.x - a.x) * (b.x - a.x)) + ((c.y - a.y) * (b.y - a.y));
        if (dotproduct < 0.0f) {
            return false;
        }
        if (dotproduct > ((b.x - a.x) * (b.x - a.x)) + ((b.y - a.y) * (b.y - a.y))) {
            return false;
        }
        return true;
    }

    boolean isLineSegmentOnLinex(PointF p1, PointF p2) {
        return isPointOnLine(p1) && isPointOnLine(p2);
    }

    float distance(PointF p) {
        return distance(p.x, p.y);
    }

    float distance(float x, float y) {
        return (float) (((double) Math.abs(((this.f524a * x) + (this.f525b * y)) + this.f526c)) / Math.sqrt((double) ((this.f524a * this.f524a) + (this.f525b * this.f525b))));
    }

    void isSide(float width, float height) {
        for (int i = 0; i < this.pointArrayList.size(); i++) {
            PointF p = (PointF) this.pointArrayList.get(i);
            if ((p.x == 0.0f && p.y == 0.0f) || ((p.x == 0.0f && p.y == height) || ((p.x == width && p.y == 0.0f) || (p.x == width && p.y == height)))) {
                this.isSide = true;
            }
        }
    }

    void findMinMaxDistance() {
        float minX = 1000000.0f;
        float minXNegative = -1000000.0f;
        float minY = 1000000.0f;
        float minYNegative = -1000000.0f;
        for (int i = 0; i < this.distanceList.size(); i++) {
            PointF distance = (PointF) this.distanceList.get(i);
            if (distance.x > 0.0f && distance.x < minX) {
                minX = distance.x;
                this.minXIndex = i;
            }
            if (distance.x < 0.0f && distance.x > minXNegative) {
                minXNegative = distance.x;
                this.minXIndexNegative = i;
            }
            if (distance.y > 0.0f && distance.y < minY) {
                minY = distance.y;
                this.minYIndex = i;
            }
            if (distance.y < 0.0f && distance.y > minYNegative) {
                minYNegative = distance.y;
                this.minYIndexNegative = i;
            }
        }
        this.positiveMinDistance.set(minX, minY);
        this.negativeMinDistance.set(minXNegative, minYNegative);
    }
}
