package com.photo.blureffectcamera.collagelib;

import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;

public class LineHelper {
    private static final String TAG = "LineHelper";
    float epsilon;
    public ArrayList<GridLine> gridLines;
    float maxHeight;
    float maxWidth;
    public float minDistance;
    public int selectedLine;
    int sentinel;
    ArrayList<ArrayList<PointF>> shapeList;
    public boolean useLine;

    public LineHelper(ArrayList<ArrayList<PointF>> s, float width, float height, boolean use) {
        this.sentinel = 0;
        this.epsilon = 0.001f;
        this.shapeList = new ArrayList<ArrayList<PointF>>();
        this.gridLines = new ArrayList<GridLine>();
        this.minDistance = 175.0f;
        this.selectedLine = -1;
        this.shapeList = s;
        this.maxWidth = width;
        this.maxHeight = height;
        this.useLine = use;
    }

    
    public void findGridLines() {
        if (this.gridLines == null) {
            this.gridLines = new ArrayList<GridLine>();
        }
        else {
            this.gridLines.clear();
        }
        for (int i = 0; i < this.shapeList.size(); ++i) {
            for (int j = 0; j < this.shapeList.get(i).size(); ++j) {
                final int n = j - 1;
                final int n2 = j + 2;
                int n3;
                if ((n3 = n) < 0) {
                    n3 = this.shapeList.get(i).size() - 1;
                }
                int n4;
                if ((n4 = n2) == this.shapeList.get(i).size()) {
                    n4 = 0;
                }
                int n5;
                if ((n5 = n4) == this.shapeList.get(i).size() + 1) {
                    n5 = 1;
                }
                final PointF pointF = this.shapeList.get(i).get(n3);
                final PointF pointF2 = this.shapeList.get(i).get(n5);
                int n6;
                if ((n6 = j + 1) > this.shapeList.get(i).size() - 1) {
                    n6 = 0;
                }
                final PointF pointF3 = this.shapeList.get(i).get(j);
                final PointF pointF4 = this.shapeList.get(i).get(n6);
                final Point point = new Point(i, j);
                final Point point2 = new Point(i, n6);
                final Point point3 = new Point(i, n3);
                final Point point4 = new Point(i, n5);
                if (i == 0) {
                    final GridLine gridLine = new GridLine(pointF3, pointF4, point, point2);
                    gridLine.distanceList.add(new PointF(-pointF3.x + pointF.x, -pointF3.y + pointF.y));
                    gridLine.distanceList.add(new PointF(-pointF4.x + pointF2.x, -pointF4.y + pointF2.y));
                    gridLine.distanceIndexList.add(point3);
                    gridLine.distanceIndexList.add(point4);
                    this.gridLines.add(gridLine);
                }
                else {
                    final boolean b = false;
                    int n7 = 0;
                    boolean b2;
                    while (true) {
                        b2 = b;
                        if (n7 >= this.gridLines.size()) {
                            break;
                        }
                        if (((GridLine) this.gridLines.get(n7)).shouldAddLineSegment(pointF3, pointF4)) {
                            ((GridLine) this.gridLines.get(n7)).addPoints(pointF3, pointF4, point, point2);
                           ((GridLine)this.gridLines.get(n7)).distanceList.add(new PointF(-pointF3.x + pointF.x, -pointF3.y + pointF.y));
                           ((GridLine)this.gridLines.get(n7)).distanceList.add(new PointF(-pointF4.x + pointF2.x, -pointF4.y + pointF2.y));
                           ((GridLine) this.gridLines.get(n7)).distanceIndexList.add(point3);
                           ((GridLine)this.gridLines.get(n7)).distanceIndexList.add(point4);
                            b2 = true;
                            break;
                        }
                        ++n7;
                    }
                    if (!b2) {
                        final GridLine gridLine2 = new GridLine(pointF3, pointF4, point, point2);
                        gridLine2.distanceList.add(new PointF(-pointF3.x + pointF.x, -pointF3.y + pointF.y));
                        gridLine2.distanceList.add(new PointF(-pointF4.x + pointF2.x, -pointF4.y + pointF2.y));
                        gridLine2.distanceIndexList.add(point3);
                        gridLine2.distanceIndexList.add(point4);
                        this.gridLines.add(gridLine2);
                    }
                }
            }
        }
        this.calulateSlopesForPoints();
        for (int k = 0; k < this.gridLines.size(); ++k) {
            ((GridLine) this.gridLines.get(k)).isSide(this.maxWidth, this.maxHeight);
            ((GridLine) this.gridLines.get(k)).findMinMaxDistance();
            ((GridLine) this.gridLines.get(k)).setPointHandle(k % 2 == 1);
        }
    }
    void mergeLines() {
        Log.e(TAG, "merge lines size " + this.gridLines.size());
        mergeLoop();
    }

    void mergeLoop() {
        int i = 0;
        while (i < this.gridLines.size()) {
            int j = 0;
            while (j < this.gridLines.size()) {
                if (i != j && ((GridLine) this.gridLines.get(i)).compareSloples(((GridLine) this.gridLines.get(j)).slope)) {
                    for (int q = 0; q < ((GridLine) this.gridLines.get(i)).pointArrayList.size(); q++) {
                        int r = 0;
                        while (r < ((GridLine) this.gridLines.get(j)).pointArrayList.size()) {
                            PointF p1 = (PointF) ((GridLine) this.gridLines.get(i)).pointArrayList.get(q);
                            PointF p2 = (PointF) ((GridLine) this.gridLines.get(j)).pointArrayList.get(r);
                            if (Math.abs(p1.x - p2.x) >= this.epsilon || Math.abs(p1.y - p2.y) >= this.epsilon) {
                                r++;
                            } else {
                                actualMerge(i, j);
                                mergeLines();
                                return;
                            }
                        }
                    }
                    continue;
                }
                j++;
            }
            i++;
        }
    }

    void actualMerge(int i, int j) {
        ((GridLine) this.gridLines.get(i)).merge((GridLine) this.gridLines.get(j));
        this.gridLines.remove(j);
    }

    public void updateGridLines() {
        for (int i = 0; i < this.gridLines.size(); i++) {
            GridLine gridLine = (GridLine) this.gridLines.get(i);
            for (int j = 0; j < gridLine.indexArrayList.size(); j++) {
                Point index = (Point) gridLine.indexArrayList.get(j);
                PointF q = (PointF) ((ArrayList<?>) this.shapeList.get(index.x)).get(index.y);
                ((PointF) gridLine.pointArrayList.get(j)).set(q.x, q.y);
            }
            gridLine.setAbc((PointF) gridLine.pointArrayList.get(0), (PointF) gridLine.pointArrayList.get(1));
        }
    }

    void calulateSlopesForPoints() {
        for (int i = 0; i < this.gridLines.size(); i++) {
            GridLine gridLine = (GridLine) this.gridLines.get(i);
            for (int j = 0; j < gridLine.indexArrayList.size(); j++) {
                gridLine.slopeArrayList.add(Float.valueOf(findPointInLine(i, (Point) gridLine.indexArrayList.get(j))));
            }
        }
    }

    float findPointInLine(int except, Point index) {
        for (int i = 0; i < this.gridLines.size(); i++) {
            if (i != except) {
                GridLine gridLine = (GridLine) this.gridLines.get(i);
                int j = 0;
                while (j < gridLine.indexArrayList.size()) {
                    if (((Point) gridLine.indexArrayList.get(j)).x == index.x && ((Point) gridLine.indexArrayList.get(j)).y == index.y) {
                        return gridLine.slope;
                    }
                    j++;
                }
                continue;
            }
        }
        return (float) this.sentinel;
    }

    float checkMoveX(float dx) {
        GridLine gridLine = (GridLine) this.gridLines.get(this.selectedLine);
        Point indPos = (Point) gridLine.distanceIndexList.get(gridLine.minXIndex);
        Point indNeg = (Point) gridLine.distanceIndexList.get(gridLine.minXIndexNegative);
        float positiveMinDistance = gridLine.distance((PointF) ((ArrayList<?>) this.shapeList.get(indPos.x)).get(indPos.y));
        float negativeMinDistance = gridLine.distance((PointF) ((ArrayList<?>) this.shapeList.get(indNeg.x)).get(indNeg.y));
        if (dx >= 0.0f && dx < positiveMinDistance - this.minDistance) {
            return dx;
        }
        if (dx < 0.0f && (-dx) < negativeMinDistance - this.minDistance) {
            return dx;
        }
        if (dx >= 0.0f) {
            return positiveMinDistance - this.minDistance;
        }
        return dx < 0.0f ? (-negativeMinDistance) + this.minDistance : 0.0f;
    }

    float checkMoveY(float dy) {
        GridLine gridLine = (GridLine) this.gridLines.get(this.selectedLine);
        Point indPos = (Point) gridLine.distanceIndexList.get(gridLine.minYIndex);
        Point indNeg = (Point) gridLine.distanceIndexList.get(gridLine.minYIndexNegative);
        float positiveMinDistance = gridLine.distance((PointF) ((ArrayList<?>) this.shapeList.get(indPos.x)).get(indPos.y));
        float negativeMinDistance = gridLine.distance((PointF) ((ArrayList<?>) this.shapeList.get(indNeg.x)).get(indNeg.y));
        if (dy >= 0.0f && dy < positiveMinDistance - this.minDistance) {
            return dy;
        }
        if (dy < 0.0f && (-dy) < negativeMinDistance - this.minDistance) {
            return dy;
        }
        if (dy >= 0.0f) {
            return positiveMinDistance - this.minDistance;
        }
        return dy < 0.0f ? (-negativeMinDistance) + this.minDistance : 0.0f;
    }

    public void moveGridLines(float dxx, float dyy) {
        if (this.gridLines != null && this.gridLines.get(this.selectedLine) != null && ((GridLine) this.gridLines.get(this.selectedLine)).indexArrayList != null) {
            int i;
            for (i = 0; i < ((GridLine) this.gridLines.get(this.selectedLine)).indexArrayList.size(); i++) {
                Point index = (Point) ((GridLine) this.gridLines.get(this.selectedLine)).indexArrayList.get(i);
                boolean moveX = false;
                if (((GridLine) this.gridLines.get(this.selectedLine)).slope > 1.0f || ((GridLine) this.gridLines.get(this.selectedLine)).slope < -1.0f) {
                    moveX = true;
                }
                float dx = checkMoveX(dxx);
                float dy = checkMoveY(dyy);
                float orthoSlope;
                PointF pointF;
                if (moveX && Math.abs(dx) > this.epsilon) {
                    orthoSlope = ((Float) ((GridLine) this.gridLines.get(this.selectedLine)).slopeArrayList.get(i)).floatValue();
                    pointF = (PointF) ((ArrayList<?>) this.shapeList.get(index.x)).get(index.y);
                    pointF.x += dx;
                    if (!Float.isInfinite(orthoSlope)) {
                        pointF = (PointF) ((ArrayList<?>) this.shapeList.get(index.x)).get(index.y);
                        pointF.y += dx * orthoSlope;
                    }
                    if (i == 0) {
                        ((GridLine) this.gridLines.get(this.selectedLine)).addToDx(dx);
                        if (!Float.isInfinite(orthoSlope)) {
                            ((GridLine) this.gridLines.get(this.selectedLine)).addToDy(dx * orthoSlope);
                        }
                    }
                } else if (!moveX && Math.abs(dy) > this.epsilon) {
                    orthoSlope = ((Float) ((GridLine) this.gridLines.get(this.selectedLine)).slopeArrayList.get(i)).floatValue();
                    pointF = (PointF) ((ArrayList<?>) this.shapeList.get(index.x)).get(index.y);
                    pointF.y += dy;
                    if (Math.abs(orthoSlope - this.epsilon) > 0.0f) {
                        pointF = (PointF) ((ArrayList<?>) this.shapeList.get(index.x)).get(index.y);
                        pointF.x += dy / orthoSlope;
                    }
                    if (i == 0) {
                        ((GridLine) this.gridLines.get(this.selectedLine)).addToDy(dy);
                        if (Math.abs(orthoSlope - this.epsilon) > 0.0f) {
                            ((GridLine) this.gridLines.get(this.selectedLine)).addToDx(dy / orthoSlope);
                        }
                    }
                }
            }
            for (i = 0; i < this.gridLines.size(); i++) {
                boolean z;
                GridLine gridLine = (GridLine) this.gridLines.get(i);
                gridLine.distanceList.clear();
                for (int g = 0; g < gridLine.distanceIndexList.size(); g += 2) {
                    PointF before = (PointF) ((ArrayList<?>) this.shapeList.get(((Point) gridLine.distanceIndexList.get(g)).x)).get(((Point) gridLine.distanceIndexList.get(g)).y);
                    PointF after = (PointF) ((ArrayList<?>) this.shapeList.get(((Point) gridLine.distanceIndexList.get(g + 1)).x)).get(((Point) gridLine.distanceIndexList.get(g + 1)).y);
                    PointF p1 = (PointF) gridLine.pointArrayList.get(g);
                    PointF p2 = (PointF) gridLine.pointArrayList.get(g + 1);
                    gridLine.distanceList.add(new PointF((-p1.x) + before.x, (-p1.y) + before.y));
                    gridLine.distanceList.add(new PointF((-p2.x) + after.x, (-p2.y) + after.y));
                }
                ((GridLine) this.gridLines.get(i)).findMinMaxDistance();
                GridLine gridLine2 = (GridLine) this.gridLines.get(i);
                if (i % 2 == 1) {
                    z = true;
                } else {
                    z = false;
                }
                gridLine2.setPointHandle(z);
            }
        }
    }
}
