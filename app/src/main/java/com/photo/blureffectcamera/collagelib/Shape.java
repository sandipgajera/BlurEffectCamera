package com.photo.blureffectcamera.collagelib;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Xfermode;
import android.graphics.drawable.NinePatchDrawable;
import android.util.Log;

import com.photo.blureffectcamera.pointlist.Collage;
import com.photo.blureffectcamera.svg.Svg;


public class Shape {
	public static final int MATRIX_MODE_CENTER = 1;
	public static final int MATRIX_MODE_FIT = 0;
	public static final int MATRIX_MODE_FLIP_HORIZONTAL = 4;
	public static final int MATRIX_MODE_FLIP_VERTICAL = 5;
	public static final int MATRIX_MODE_MOVE_DOWN = 13;
	public static final int MATRIX_MODE_MOVE_LEFT = 10;
	public static final int MATRIX_MODE_MOVE_RIGHT = 11;
	public static final int MATRIX_MODE_MOVE_UP = 12;
	public static final int MATRIX_MODE_ROTATE_LEFT = 3;
	public static final int MATRIX_MODE_ROTATE_NEGATIVE = 6;
	public static final int MATRIX_MODE_ROTATE_POSITIVE = 7;
	public static final int MATRIX_MODE_ROTATE_RIGHT = 2;
	public static final int MATRIX_MODE_ZOOM_IN = 8;
	public static final int MATRIX_MODE_ZOOM_OUT = 9;
	public static final int MESSAGE_DEFAULT = 0;
	public static final int MESSAGE_MAX_BOTTOM = 6;
	public static final int MESSAGE_MAX_LEFT = 3;
	public static final int MESSAGE_MAX_RIGHT = 4;
	public static final int MESSAGE_MAX_TOP = 5;
	public static final int MESSAGE_MAX_ZOOM = 1;
	public static final int MESSAGE_MIN_ZOOM = 2;
	public static final int SHAPE_MODE_INNER_POINT = 4;
	public static final int SHAPE_MODE_MASK = 3;
	public static final int SHAPE_MODE_POINT = 1;
	public static final int SHAPE_MODE_RECT = 2;
	public static final int SHAPE_MODE_SVG = 5;
	private static final String TAG;
	static final int[] scrapBookRotation;
	private Bitmap bitmap;
	int bitmapHeight;
	public Matrix bitmapMatrix;
	RectF bitmapRect;
	int bitmapWidth;
	Paint borderPaint;
	int borderStrokeWidth;
	public RectF bounds;
	Bitmap btmDelete;
	Bitmap btmScale;
	PointF centerOriginal;
	boolean checkForConcavite;
	Paint dashPaint;
	Path dashPathHorizontal;
	Path dashPathVertical;
	int delW;
	float deleteWidthHalf;
	float dx;
	float dy;
	int[] exceptionIndex;
	float[] f527f;
	Paint iconMaskPaint;
	Paint iconPaint;
	Xfermode iconXferMode;
	Matrix inverse;
	boolean isScrapBook;
	private Bitmap maskBitmap;
	private Matrix maskMatrix;
	Paint maskPaint;
	float maxScale;
	float minScale;
	NinePatchDrawable npd;
	int npdPadding;
	int offsetX;
	int offsetY;
	RectF originalBounds;
	Path originalPath;
	float[] f528p;
	private Paint paintLine;
	private Paint paintPath;
	Paint paintScrap;
	Paint paintSoloBorder;
	private Paint paintTransparent;
	Paint paintXferMode;
	Path path;
	Path[] pathList;
	int pathListLength;
	Matrix pathMatrix;
	public PointF[] points;
	float[] pts;
	public RectF f529r;
	public Region region;
	Matrix removeBitmapMatrix;
	Matrix scaleBitmapMatrix;
	float scaleDown;
	float scaleUp;
	float scrapBookPadding;
	int screenWidth;
	int shapeMode;
	RectF sourceRect;
	int svgClearFixPadding;
	int svgIndex;
	final float tempRadius;
	RectF tempRect;
	final float tempScrapBookPadding;
	float tempTouchStrokeWidth;
	Paint touchPaint;
	RectF touchRect;
	float touchStrokeWidth;
	Matrix transparentMaskMatrix;
	float[] f530v;
	float[] values;
	int viewH;
	int viewW;

	static {
		TAG =Shape.class.getSimpleName();
		scrapBookRotation = new int[] { MATRIX_MODE_MOVE_DOWN, -13, -7, -12,
				MATRIX_MODE_MOVE_RIGHT, MATRIX_MODE_ZOOM_IN, -9,
				MATRIX_MODE_MOVE_LEFT, MATRIX_MODE_ZOOM_OUT };
	}

	public Shape(PointF[] points, Bitmap b, int[] exceptionIndex, int offsetX,
                 int offsetY, boolean isScrapBook, int index, boolean isDelete,
                 Bitmap del, Bitmap scl, int screenWidth, int shapeModeParam,
                 boolean checkForConcavite, int w, int h) {
		this.offsetY = MESSAGE_DEFAULT;
		this.offsetX = MESSAGE_DEFAULT;
		this.maskBitmap = null;
		this.maskMatrix = new Matrix();
		this.transparentMaskMatrix = new Matrix();
		this.checkForConcavite = false;
		this.svgIndex = -1;
		this.f530v = new float[MATRIX_MODE_ZOOM_OUT];
		this.tempRect = new RectF();
		this.svgClearFixPadding = SHAPE_MODE_RECT;
		this.f529r = new RectF();
		this.minScale = 1.0f;
		this.maxScale = 1.0f;
		this.bitmapRect = new RectF();
		this.f528p = new float[SHAPE_MODE_RECT];
		this.dx = 0.0f;
		this.dy = 0.0f;
		this.scaleDown = 0.95f;
		this.scaleUp = 1.05f;
		this.f527f = new float[SHAPE_MODE_RECT];
		this.centerOriginal = new PointF();
		this.touchPaint = new Paint(SHAPE_MODE_POINT);
		this.borderPaint = new Paint(SHAPE_MODE_POINT);
		this.paintScrap = new Paint(SHAPE_MODE_RECT);
		this.pts = new float[SHAPE_MODE_RECT];
		this.inverse = new Matrix();
		this.tempScrapBookPadding = 25.0f;
		this.scrapBookPadding = 25.0f;
		this.tempTouchStrokeWidth = 8.0f;
		this.touchStrokeWidth = this.tempTouchStrokeWidth;
		this.values = new float[MATRIX_MODE_ZOOM_OUT];
		this.tempRadius = 60.0f;
		this.borderStrokeWidth = MESSAGE_MAX_BOTTOM;
		this.dashPaint = new Paint();
		this.delW = MESSAGE_DEFAULT;
		this.deleteWidthHalf = 0.0f;
		this.npdPadding = 16;
		this.points = points;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.btmDelete = del;
		this.btmScale = scl;
		this.screenWidth = screenWidth;
		this.isScrapBook = isScrapBook;
		this.checkForConcavite = checkForConcavite;
		createPathFromPoints(false);
		this.path.offset((float) offsetX, (float) offsetY);
		this.exceptionIndex = exceptionIndex;
		this.bitmap = b;
		this.bitmapWidth = this.bitmap.getWidth();
		this.bitmapHeight = this.bitmap.getHeight();
		this.shapeMode = shapeModeParam;
		this.viewW = w;
		this.viewH = h;
		init(isScrapBook, index, false, MESSAGE_DEFAULT, MESSAGE_DEFAULT);
	}

	public Shape(PointF[] points, Bitmap b, int[] exceptionIndex, int offsetX,
                 int offsetY, Bitmap mask, boolean isScrapBook, int index,
                 boolean isDelete, Bitmap del, Bitmap scl, int screenWidth,
                 boolean checkForConcavite, int w, int h) {
		this.offsetY = MESSAGE_DEFAULT;
		this.offsetX = MESSAGE_DEFAULT;
		this.maskBitmap = null;
		this.maskMatrix = new Matrix();
		this.transparentMaskMatrix = new Matrix();
		this.checkForConcavite = false;
		this.svgIndex = -1;
		this.f530v = new float[MATRIX_MODE_ZOOM_OUT];
		this.tempRect = new RectF();
		this.svgClearFixPadding = SHAPE_MODE_RECT;
		this.f529r = new RectF();
		this.minScale = 1.0f;
		this.maxScale = 1.0f;
		this.bitmapRect = new RectF();
		this.f528p = new float[SHAPE_MODE_RECT];
		this.dx = 0.0f;
		this.dy = 0.0f;
		this.scaleDown = 0.95f;
		this.scaleUp = 1.05f;
		this.f527f = new float[SHAPE_MODE_RECT];
		this.centerOriginal = new PointF();
		this.touchPaint = new Paint(SHAPE_MODE_POINT);
		this.borderPaint = new Paint(SHAPE_MODE_POINT);
		this.paintScrap = new Paint(SHAPE_MODE_RECT);
		this.pts = new float[SHAPE_MODE_RECT];
		this.inverse = new Matrix();
		this.tempScrapBookPadding = 25.0f;
		this.scrapBookPadding = 25.0f;
		this.tempTouchStrokeWidth = 8.0f;
		this.touchStrokeWidth = this.tempTouchStrokeWidth;
		this.values = new float[MATRIX_MODE_ZOOM_OUT];
		this.tempRadius = 60.0f;
		this.borderStrokeWidth = MESSAGE_MAX_BOTTOM;
		this.dashPaint = new Paint();
		this.delW = MESSAGE_DEFAULT;
		this.deleteWidthHalf = 0.0f;
		this.npdPadding = 16;
		this.maskBitmap = mask;
		this.points = points;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.btmDelete = del;
		this.btmScale = scl;
		this.screenWidth = screenWidth;
		this.isScrapBook = isScrapBook;
		this.checkForConcavite = checkForConcavite;
		createPathFromPoints(false);
		this.path.offset((float) offsetX, (float) offsetY);
		this.exceptionIndex = exceptionIndex;
		this.bitmap = b;
		this.bitmapWidth = this.bitmap.getWidth();
		this.bitmapHeight = this.bitmap.getHeight();
		this.shapeMode = SHAPE_MODE_MASK;
		this.viewW = w;
		this.viewH = h;
		init(isScrapBook, index, false, MESSAGE_DEFAULT, MESSAGE_DEFAULT);
	}

	public Shape(PointF[] points, Bitmap b, int[] exceptionIndex, int offsetX,
                 int offsetY, boolean isScrapBook, int index, boolean isDelete,
                 Bitmap del, Bitmap scl, int screenWidth, boolean checkForConcavite,
                 int svgIndex, int w, int h) {

		Log.i("BITMAP", "BITMAP value ::::::::::::::" + b);

		this.offsetY = MESSAGE_DEFAULT;
		this.offsetX = MESSAGE_DEFAULT;
		this.maskBitmap = null;
		this.maskMatrix = new Matrix();
		this.transparentMaskMatrix = new Matrix();
		this.checkForConcavite = false;
		this.svgIndex = -1;
		this.f530v = new float[MATRIX_MODE_ZOOM_OUT];
		this.tempRect = new RectF();
		this.svgClearFixPadding = SHAPE_MODE_RECT;
		this.f529r = new RectF();
		this.minScale = 1.0f;
		this.maxScale = 1.0f;
		this.bitmapRect = new RectF();
		this.f528p = new float[SHAPE_MODE_RECT];
		this.dx = 0.0f;
		this.dy = 0.0f;
		this.scaleDown = 0.95f;
		this.scaleUp = 1.05f;
		this.f527f = new float[SHAPE_MODE_RECT];
		this.centerOriginal = new PointF();
		this.touchPaint = new Paint(SHAPE_MODE_POINT);
		this.borderPaint = new Paint(SHAPE_MODE_POINT);
		this.paintScrap = new Paint(SHAPE_MODE_RECT);
		this.pts = new float[SHAPE_MODE_RECT];
		this.inverse = new Matrix();
		this.tempScrapBookPadding = 25.0f;
		this.scrapBookPadding = 25.0f;
		this.tempTouchStrokeWidth = 8.0f;
		this.touchStrokeWidth = this.tempTouchStrokeWidth;
		this.values = new float[MATRIX_MODE_ZOOM_OUT];
		this.tempRadius = 60.0f;
		this.borderStrokeWidth = MESSAGE_MAX_BOTTOM;
		this.dashPaint = new Paint();
		this.delW = MESSAGE_DEFAULT;
		this.deleteWidthHalf = 0.0f;
		this.npdPadding = 16;
		this.points = points;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.btmDelete = del;
		this.btmScale = scl;
		this.screenWidth = screenWidth;
		this.isScrapBook = isScrapBook;
		this.checkForConcavite = checkForConcavite;
		createPathFromPoints(false);
		this.path.offset((float) offsetX, (float) offsetY);
		this.exceptionIndex = exceptionIndex;
		this.bitmap = b;
		this.bitmapWidth = this.bitmap.getWidth();
		this.bitmapHeight = this.bitmap.getHeight();
		this.shapeMode = SHAPE_MODE_SVG;
		this.svgIndex = svgIndex;
		this.viewW = w;
		this.viewH = h;
		init(isScrapBook, index, false, MESSAGE_DEFAULT, MESSAGE_DEFAULT);
	}

	public void changeRatio(PointF[] points, int[] exceptionIndex, int offsetX,
                            int offsetY, boolean isScrapBook, int index, int w, int h) {
		this.points = points;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		createPathFromPoints(false);
		this.path.offset((float) offsetX, (float) offsetY);
		this.exceptionIndex = exceptionIndex;
		this.viewW = w;
		this.viewH = h;
		init(isScrapBook, index, true, w, h);
	}

	public void updateFromLine(PointF[] points) {
		this.points = points;
		createPathFromPoints(true);
		this.path.offset((float) this.offsetX, (float) this.offsetY);
		this.path.computeBounds(this.bounds, true);
		if (ShapeCollageActivity.isGridLayoutLocked) {
			this.region = new Region();
			this.region.setPath(this.path, new Region((int) this.bounds.left,
					(int) this.bounds.top, (int) this.bounds.right,
					(int) this.bounds.bottom));
		} else {
			this.region = new Region();
			this.region.setPath(this.path, new Region((int) this.bounds.left,
					(int) this.bounds.top, (int) this.bounds.right,
					(int) this.bounds.bottom));
		}
	}

	public void freeBitmaps() {
		if (!(this.bitmap == null || this.bitmap.isRecycled())) {
			this.bitmap.recycle();
		}
		if (this.maskBitmap != null && !this.maskBitmap.isRecycled()) {
			this.maskBitmap = null;
		}
	}

	public void setRadius(CornerPathEffect corEffect) {
		this.paintPath.setPathEffect(corEffect);
		this.paintTransparent.setPathEffect(corEffect);
	}

	public float smallestDistance() {
		float smallestDistance = 1500.0f;
		for (int i = MESSAGE_DEFAULT; i < this.points.length; i += SHAPE_MODE_POINT) {
			for (int j = MESSAGE_DEFAULT; j < this.points.length; j += SHAPE_MODE_POINT) {
				if (i != j) {
					float distance = Math.abs(this.points[i].x
							- this.points[j].x)
							+ Math.abs(this.points[i].y - this.points[j].y);
					if (distance < smallestDistance) {
						smallestDistance = distance;
					}
				}
			}
		}
		if (this.shapeMode == SHAPE_MODE_MASK
				|| this.shapeMode == SHAPE_MODE_INNER_POINT
				|| this.shapeMode == SHAPE_MODE_SVG) {
			return smallestDistance / 2.0f;
		}
		return smallestDistance;
	}

	public void init(boolean isScrapBook, int index, boolean isChangeRatio,
			int w, int h) {
		this.bounds = new RectF();
		this.originalPath = new Path(this.path);
		this.path.computeBounds(this.bounds, true);
		this.originalBounds = new RectF(this.bounds);
		this.paintSoloBorder = new Paint(SHAPE_MODE_POINT);
		this.paintSoloBorder.setStyle(Style.STROKE);
		this.paintSoloBorder.setStrokeWidth(Svg.strokeSize);
		this.paintSoloBorder.setColor(Svg.colorStroke);
		this.paintXferMode = new Paint(SHAPE_MODE_POINT);
		this.paintXferMode.setFilterBitmap(true);
		this.paintXferMode.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		this.paintPath = new Paint(SHAPE_MODE_POINT);
		this.paintPath.setFilterBitmap(true);
		this.maskPaint = new Paint(SHAPE_MODE_POINT);
		this.maskPaint.setFilterBitmap(true);
		this.paintLine = new Paint(SHAPE_MODE_POINT);
		this.paintLine.setColor(-16711936);
		this.paintLine.setStyle(Style.STROKE);
		this.paintLine.setStrokeWidth(10.0f);
		this.paintTransparent = new Paint(SHAPE_MODE_POINT);
		this.paintTransparent.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
		this.paintTransparent.setFilterBitmap(true);
		if (isScrapBook) {
			setScrapBookBitmapPosition(index, isChangeRatio, w, h);
		} else {
			setBitmapPosition(!ShapeCollageActivity.isGridLayoutLocked);
		}
		this.paintPath.setPathEffect(new CornerPathEffect(3.0f));
		this.pathMatrix = new Matrix();
		this.region = new Region();
		this.region.setPath(this.path, new Region((int) this.bounds.left,
				(int) this.bounds.top, (int) this.bounds.right,
				(int) this.bounds.bottom));
		if (isScrapBook) {
			this.dashPaint.setColor(-7829368);
			this.dashPaint.setStyle(Style.STROKE);
			float strokeW = ((float) this.screenWidth) / 120.0f;
			if (strokeW <= 0.0f) {
				strokeW = 5.0f;
			}
			this.dashPaint.setStrokeWidth(strokeW);
			Paint paint = this.dashPaint;
			float[] fArr = new float[SHAPE_MODE_RECT];
			fArr[MESSAGE_DEFAULT] = strokeW;
			fArr[SHAPE_MODE_POINT] = strokeW;
			paint.setPathEffect(new DashPathEffect(fArr, 0.0f));
			this.dashPathVertical = new Path();
			this.dashPathHorizontal = new Path();
			resetDashPaths();
		}
	}

	public void resetDashPaths() {
		if (this.dashPathVertical == null) {
			this.dashPathVertical = new Path();
		}
		this.dashPathVertical.reset();
		this.dashPathVertical.moveTo(
				(float) (this.bitmapWidth / SHAPE_MODE_RECT),
				(float) ((-this.bitmapHeight) / SHAPE_MODE_SVG));
		this.dashPathVertical
				.lineTo((float) (this.bitmapWidth / SHAPE_MODE_RECT),
						(float) ((this.bitmapHeight * MESSAGE_MAX_BOTTOM) / SHAPE_MODE_SVG));
		if (this.dashPathHorizontal == null) {
			this.dashPathHorizontal = new Path();
		}
		this.dashPathHorizontal.reset();
		this.dashPathHorizontal.moveTo(
				(float) ((-this.bitmapWidth) / SHAPE_MODE_SVG),
				(float) (this.bitmapHeight / SHAPE_MODE_RECT));
		this.dashPathHorizontal
				.lineTo((float) ((this.bitmapWidth * MESSAGE_MAX_BOTTOM) / SHAPE_MODE_SVG),
						(float) (this.bitmapHeight / SHAPE_MODE_RECT));
	}

	public void setBitmap(Bitmap bitmap, boolean isFilter) {
		this.bitmap = bitmap;
		this.bitmapWidth = bitmap.getWidth();
		this.bitmapHeight = bitmap.getHeight();
		if (!isFilter) {
			setBitmapPosition(!ShapeCollageActivity.isGridLayoutLocked);
		}
	}

	public Bitmap getBitmap() {
		return this.bitmap;
	}

	public Bitmap getMaskBitmap() {
		return this.maskBitmap;
	}

	private void setBitmapPosition(boolean init) {
		RectF rrr;
		if (init) {
			rrr = this.originalBounds;
		} else {
			rrr = this.bounds;
		}
		float scaleBitmap = getBitmapScale(rrr);
		float bitmapY = rrr.top
				- (((((float) this.bitmapHeight) * scaleBitmap) - rrr.height()) / 2.0f);
		float bitmapX = rrr.left
				- (((((float) this.bitmapWidth) * scaleBitmap) - rrr.width()) / 2.0f);
		this.bitmapMatrix = new Matrix();
		this.bitmapMatrix.reset();
		this.bitmapMatrix.postScale(scaleBitmap, scaleBitmap);
		this.bitmapMatrix.postTranslate(bitmapX, bitmapY);
		if (this.shapeMode == SHAPE_MODE_MASK) {
			setMaskBitmapPositions();
		}
		setMaxMinScales(scaleBitmap);
	}

	private float getBitmapScale(RectF rrr) {
		float scaleBitmapX = rrr.width() / ((float) this.bitmapWidth);
		float scaleBitmapY = rrr.height() / ((float) this.bitmapHeight);
		if (scaleBitmapX < scaleBitmapY) {
			return scaleBitmapY;
		}
		return scaleBitmapX;
	}

	private float getBitmapScaleRotated() {
		float scaleBitmapX;
		float scaleBitmapY;
		float rotation = getMatrixRotation(this.bitmapMatrix);
		if (rotation == 90.0f || rotation == 270.0f || rotation == -90.0f
				|| rotation == -270.0f) {
			scaleBitmapX = this.bounds.width() / ((float) this.bitmapHeight);
			scaleBitmapY = this.bounds.height() / ((float) this.bitmapWidth);
		} else {
			scaleBitmapX = this.bounds.width() / ((float) this.bitmapWidth);
			scaleBitmapY = this.bounds.height() / ((float) this.bitmapHeight);
		}
		if (scaleBitmapX < scaleBitmapY) {
			return scaleBitmapY;
		}
		return scaleBitmapX;
	}

	float getMatrixRotation(Matrix matrix) {
		matrix.getValues(this.f530v);
		return (float) Math.round(Math.atan2(
				(double) this.f530v[SHAPE_MODE_POINT],
				(double) this.f530v[MESSAGE_DEFAULT]) * 57.29577951308232d);
	}

	public void setMaxMinScalesForLock() {
		if (ShapeCollageActivity.isGridLayoutLocked) {
			setMaxMinScalesRotated();
			checkScaleBoundries();
			checkBoundries(false);
			return;
		}
		setMaxMinScales(getBitmapScale(this.bounds));
	}

	void setMaxMinScales(float scaleBitmap) {
		if (this.isScrapBook || !ShapeCollageActivity.isGridLayoutLocked) {
			this.minScale = scaleBitmap / 2.0f;
		} else {
			this.minScale = scaleBitmap;
		}
		if (this.isScrapBook) {
			this.maxScale = scaleBitmap * 2.0f;
		} else {
			this.maxScale = 4.0f * scaleBitmap;
		}
	}

	void setMaxMinScalesRotated() {
		float scaleBitmap = getBitmapScaleRotated();
		this.minScale = scaleBitmap;
		this.maxScale = 4.0f * scaleBitmap;
	}

	void setMinScales(float scaleBitmap, boolean override) {
		if (this.isScrapBook
				|| !(ShapeCollageActivity.isGridLayoutLocked || override)) {
			this.minScale = scaleBitmap / 2.0f;
		} else {
			this.minScale = scaleBitmap;
		}
	}

	private void setMaskBitmapPositions() {
		if (this.maskBitmap != null) {
			float scaleMaskBitmap;
			float scaleMaskBitmapTr;
			int maskBitmapWidth = this.maskBitmap.getWidth();
			int maskBitmapHeight = this.maskBitmap.getHeight();
			float scaleMaskBitmapX = this.bounds.width()
					/ ((float) maskBitmapWidth);
			float scaleMaskBitmapY = this.bounds.height()
					/ ((float) maskBitmapHeight);
			if (scaleMaskBitmapX > scaleMaskBitmapY) {
				scaleMaskBitmap = scaleMaskBitmapY;
			} else {
				scaleMaskBitmap = scaleMaskBitmapX;
			}
			float maskBitmapY = this.bounds.top
					- (((((float) maskBitmapHeight) * scaleMaskBitmap) - this.bounds
							.height()) / 2.0f);
			float maskBitmapX = this.bounds.left
					- (((((float) maskBitmapWidth) * scaleMaskBitmap) - this.bounds
							.width()) / 2.0f);
			this.maskMatrix = new Matrix();
			this.maskMatrix.reset();
			this.maskMatrix.postScale(scaleMaskBitmap, scaleMaskBitmap);
			this.maskMatrix.postTranslate(maskBitmapX, maskBitmapY);
			float scaleMaskBitmapXTr = this.originalBounds.width()
					/ ((float) maskBitmapWidth);
			float scaleMaskBitmapYTr = this.originalBounds.height()
					/ ((float) maskBitmapHeight);
			if (scaleMaskBitmapXTr > scaleMaskBitmapYTr) {
				scaleMaskBitmapTr = scaleMaskBitmapYTr;
			} else {
				scaleMaskBitmapTr = scaleMaskBitmapXTr;
			}
			float maskBitmapYTr = this.originalBounds.top
					- (((((float) maskBitmapHeight) * scaleMaskBitmapTr) - this.originalBounds
							.height()) / 2.0f);
			float maskBitmapXTr = this.originalBounds.left
					- (((((float) maskBitmapWidth) * scaleMaskBitmapTr) - this.originalBounds
							.width()) / 2.0f);
			this.transparentMaskMatrix = new Matrix();
			this.transparentMaskMatrix.reset();
			this.transparentMaskMatrix.postScale(scaleMaskBitmapTr,
					scaleMaskBitmapTr);
			this.transparentMaskMatrix.postTranslate(maskBitmapXTr,
					maskBitmapYTr);
		}
	}

	private void setSvgPositions() {
	}

	public void scalePath(float distance, float width, float height) {
		if (this.shapeMode == SHAPE_MODE_POINT
				|| this.shapeMode == SHAPE_MODE_INNER_POINT) {
			if (this.shapeMode == SHAPE_MODE_INNER_POINT) {
				distance *= 2.0f;
			}
			pathTransform(this.points, this.path, distance);
		} else if (this.shapeMode == SHAPE_MODE_RECT) {
			pathTransformFromRect(distance);
		} else {
			float scaleX = (this.originalBounds.width() - (4.0f * distance))
					/ this.originalBounds.width();
			float scaleY = (this.originalBounds.height() - (4.0f * distance))
					/ this.originalBounds.height();
			this.pathMatrix.reset();
			this.pathMatrix.setScale(scaleX, scaleY,
					this.originalBounds.centerX(),
					this.originalBounds.centerY());
			this.originalPath.transform(this.pathMatrix, this.path);
		}
		this.path.computeBounds(this.bounds, true);
		if (this.shapeMode == SHAPE_MODE_MASK) {
			setMaskBitmapPositions();
		}
	}

	void createPathFromPoints(boolean update) {
		if (!update || this.path == null) {
			this.path = new Path();
		} else {
			this.path.reset();
		}
		this.path.setFillType(FillType.EVEN_ODD);
		this.path.moveTo(this.points[MESSAGE_DEFAULT].x,
				this.points[MESSAGE_DEFAULT].y);
		for (int i = SHAPE_MODE_POINT; i < this.points.length; i += SHAPE_MODE_POINT) {
			Log.e(TAG, "i = " + i);
			this.path.lineTo(this.points[i].x, this.points[i].y);
		}
		this.path.lineTo(this.points[MESSAGE_DEFAULT].x,
				this.points[MESSAGE_DEFAULT].y);
		this.path.close();
	}

	void createPathFromRect() {
		this.path = new Path();
		this.path.addRect(this.sourceRect, Direction.CCW);
	}

	void pathTransformExEx(PointF[] points, Path path, float distance,
                           float centerX, float centerY) {
		int i;
		centerX -= (float) this.offsetX;
		centerY -= (float) this.offsetY;
		path.rewind();
		path.setFillType(FillType.EVEN_ODD);
		int size = points.length;
		float[] distanceArray = new float[size];
		for (i = MESSAGE_DEFAULT; i < size; i += SHAPE_MODE_POINT) {
			distanceArray[i] = distance;
		}
		if (this.exceptionIndex != null) {
			for (i = MESSAGE_DEFAULT; i < this.exceptionIndex.length; i += SHAPE_MODE_POINT) {
				distanceArray[this.exceptionIndex[i]] = 2.0f * distance;
			}
		}
		path.moveTo(
				checkRange(points[MESSAGE_DEFAULT].x,
						distanceArray[MESSAGE_DEFAULT], centerX),
				checkRange(points[MESSAGE_DEFAULT].y, distance, centerY));
		for (i = SHAPE_MODE_POINT; i < size; i += SHAPE_MODE_POINT) {
			path.lineTo(checkRange(points[i].x, distanceArray[i], centerX),
					checkRange(points[i].y, distance, centerY));
		}
		path.lineTo(
				checkRange(points[MESSAGE_DEFAULT].x,
						distanceArray[MESSAGE_DEFAULT], centerX),
				checkRange(points[MESSAGE_DEFAULT].y, distance, centerY));
		path.close();
		path.offset((float) this.offsetX, (float) this.offsetY);
	}

	void pathTransformEx(PointF[] points, Path path, float distance) {
		path.rewind();
		path.setFillType(FillType.EVEN_ODD);
		int size = points.length;
		PointF newPosition = findAngle(points[MESSAGE_DEFAULT],
				points[size - 1], points[SHAPE_MODE_POINT], distance);
		path.moveTo(newPosition.x, newPosition.y);
		for (int i = SHAPE_MODE_POINT; i < size; i += SHAPE_MODE_POINT) {
			int nextIndex = i + SHAPE_MODE_POINT;
			if (nextIndex == size) {
				nextIndex = MESSAGE_DEFAULT;
			}
			newPosition = findAngle(points[i], points[i - 1],
					points[nextIndex], distance);
			path.lineTo(newPosition.x, newPosition.y);
		}
		newPosition = findAngle(points[MESSAGE_DEFAULT], points[size - 1],
				points[SHAPE_MODE_POINT], distance);
		path.lineTo(newPosition.x, newPosition.y);
		path.close();
		path.offset((float) this.offsetX, (float) this.offsetY);
	}

	void pathTransform(final PointF[] array, final Path path, final float n) {
		path.rewind();
		path.setFillType(FillType.EVEN_ODD);
		final int length = array.length;
		PointF angle2 = this.findAngle2(array[length - 1], array[0], array[1],
				n);
		path.moveTo(angle2.x, angle2.y);
		for (int i = 1; i < length; ++i) {
			int n2;
			if ((n2 = i + 1) == length) {
				n2 = 0;
			}
			angle2 = this.findAngle2(array[i - 1], array[i], array[n2], n);
			path.lineTo(angle2.x, angle2.y);
		}
		final PointF angle3 = this.findAngle2(array[length - 1], array[0],
				array[1], n);
		path.lineTo(angle3.x, angle3.y);
		path.close();
		path.offset((float) this.offsetX, (float) this.offsetY);
	}

	PointF findAngle(PointF p1, PointF p2, PointF p3, float distance) {
		float P12 = (float) Math
				.sqrt((double) (((p1.x - p2.x) * (p1.x - p2.x)) + ((p1.y - p2.y) * (p1.y - p2.y))));
		float P13 = (float) Math
				.sqrt((double) (((p1.x - p3.x) * (p1.x - p3.x)) + ((p1.y - p3.y) * (p1.y - p3.y))));
		float P23 = (float) Math
				.sqrt((double) (((p2.x - p3.x) * (p2.x - p3.x)) + ((p2.y - p3.y) * (p2.y - p3.y))));
		float movement = (float) (((double) distance) / Math
				.sin((double) (((float) Math
						.acos((double) ((((P12 * P12) + (P13 * P13)) - (P23 * P23)) / ((2.0f * P12) * P13)))) / 2.0f)));
		PointF pp1 = p2;
		PointF pp2 = p1;
		PointF pp3 = p3;
		float concavite = ((pp2.x - pp1.x) * (pp3.y - pp2.y))
				- ((pp3.x - pp2.x) * (pp2.y - pp1.y));
		int concMultiplier = SHAPE_MODE_POINT;
		if (this.checkForConcavite) {
			concMultiplier = (int) Math.signum(concavite);
		}
		float a = (float) Math
				.sqrt((double) (((p2.x - p3.x) * (p2.x - p3.x)) + ((p2.y - p3.y) * (p2.y - p3.y))));
		float b = (float) Math
				.sqrt((double) (((p1.x - p3.x) * (p1.x - p3.x)) + ((p1.y - p3.y) * (p1.y - p3.y))));
		float c = (float) Math
				.sqrt((double) (((p1.x - p2.x) * (p1.x - p2.x)) + ((p1.y - p2.y) * (p1.y - p2.y))));
		float xxx = (((p1.x * a) + (p2.x * b)) + (p3.x * c)) / ((a + b) + c);
		float yyy = (((p1.y * a) + (p2.y * b)) + (p3.y * c)) / ((a + b) + c);
		float vx = xxx - p1.x;
		float vy = yyy - p1.y;
		float vd = (float) Math.sqrt((double) ((vx * vx) + (vy * vy)));
		vx = (vx / vd) * movement;
		vy = (vy / vd) * movement;
		return new PointF(p1.x + (((float) concMultiplier) * vx), p1.y
				+ (((float) concMultiplier) * vy));
	}

	PointF findAngle2(PointF lineIntersection, final PointF pointF,
                      final PointF pointF2, float n) {
		final float x = pointF.x;
		final float x2 = lineIntersection.x;
		final float y = pointF2.y;
		final float y2 = pointF.y;
		final float x3 = pointF2.x;
		final float x4 = pointF.x;
		final float y3 = pointF.y;
		final float y4 = lineIntersection.y;
		int n2 = 1;
		if (this.checkForConcavite) {
			n2 = (int) Math.signum((x - x2) * (y - y2) - (x3 - x4) * (y3 - y4));
		}
		final float n3 = n;
		final float n4 = n;
		float n5 = n3;
		if (pointF2.x == 0.0f) {
			n5 = n3;
			if (pointF.x == 0.0f) {
				n5 = n * 2.0f;
			}
		}
		float n6 = n4;
		if (pointF2.y == 0.0f) {
			n6 = n4;
			if (pointF.y == 0.0f) {
				n6 = n * 2.0f;
			}
		}
		final float n7 = this.viewW;
		final float n8 = this.viewH;
		float n9 = n5;
		if (pointF2.x == n7) {
			n9 = n5;
			if (pointF.x == n7) {
				n9 = n * 2.0f;
			}
		}
		float n10 = n6;
		if (pointF2.y == n8) {
			n10 = n6;
			if (pointF.y == n8) {
				n10 = n * 2.0f;
			}
		}
		final PointF pointF3 = new PointF(-(pointF2.y - pointF.y), pointF2.x
				- pointF.x);
		final PointF pointF4 = new PointF(pointF2.y - pointF.y,
				-(pointF2.x - pointF.x));
		final float length = pointF3.length();
		pointF3.set(pointF3.x / length, pointF3.y / length);
		final PointF pointF5 = new PointF(pointF2.x + pointF3.x * n9, pointF2.y
				+ pointF3.y * n10);
		final PointF pointF6 = new PointF(pointF.x + pointF3.x * n9, pointF.y
				+ pointF3.y * n10);
		final float length2 = pointF4.length();
		pointF4.set(pointF4.x / length2, pointF4.y / length2);
		final PointF pointF7 = new PointF(pointF2.x + pointF4.x * n9, pointF2.y
				+ pointF4.y * n10);
		PointF pointF8 = new PointF(pointF.x + pointF4.x * n9, pointF.y
				+ pointF4.y * n10);
		PointF pointF9 = pointF7;
		if ((lineIntersection.x - pointF5.x) * (lineIntersection.x - pointF5.x)
				+ (lineIntersection.y - pointF5.y)
				* (lineIntersection.y - pointF5.y) < (lineIntersection.x - pointF7.x)
				* (lineIntersection.x - pointF7.x)
				+ (lineIntersection.y - pointF7.y)
				* (lineIntersection.y - pointF7.y)) {
			pointF9 = pointF5;
			pointF8 = pointF6;
		}
		final float n11 = n;
		final float n12 = n;
		float n13 = n11;
		if (lineIntersection.x == 0.0f) {
			n13 = n11;
			if (pointF.x == 0.0f) {
				n13 = n * 2.0f;
			}
		}
		float n14 = n12;
		if (lineIntersection.y == 0.0f) {
			n14 = n12;
			if (pointF.y == 0.0f) {
				n14 = n * 2.0f;
			}
		}
		float n15 = n13;
		if (lineIntersection.x == n7) {
			n15 = n13;
			if (pointF.x == n7) {
				n15 = n * 2.0f;
			}
		}
		float n16 = n14;
		if (lineIntersection.y == n8) {
			n16 = n14;
			if (pointF.y == n8) {
				n16 = n * 2.0f;
			}
		}
		final PointF pointF10 = new PointF(-(pointF.y - lineIntersection.y),
				pointF.x - lineIntersection.x);
		final PointF pointF11 = new PointF(pointF.y - lineIntersection.y,
				-(pointF.x - lineIntersection.x));
		n = pointF10.length();
		pointF10.set(pointF10.x / n, pointF10.y / n);
		final PointF pointF12 = new PointF(pointF.x + pointF10.x * n15,
				pointF.y + pointF10.y * n16);
		final PointF pointF13 = new PointF(lineIntersection.x + pointF10.x
				* n15, lineIntersection.y + pointF10.y * n16);
		n = pointF11.length();
		pointF11.set(pointF11.x / n, pointF11.y / n);
		final PointF pointF14 = new PointF(pointF.x + pointF11.x * n15,
				pointF.y + pointF11.y * n16);
		PointF pointF15 = new PointF(lineIntersection.x + pointF11.x * n15,
				lineIntersection.y + pointF11.y * n16);
		PointF pointF16 = pointF14;
		if ((pointF2.x - pointF12.x) * (pointF2.x - pointF12.x)
				+ (pointF2.y - pointF12.y) * (pointF2.y - pointF12.y) < (pointF2.x - pointF14.x)
				* (pointF2.x - pointF14.x)
				+ (pointF2.y - pointF14.y)
				* (pointF2.y - pointF14.y)) {
			pointF16 = pointF12;
			pointF15 = pointF13;
		}
		lineIntersection = this.findLineIntersection(pointF9, pointF8,
				pointF16, pointF15);
		final PointF pointF17 = new PointF();
		pointF17.set(lineIntersection);
		if (n2 < 0 && pointF17 != null) {
			pointF17.set(2.0f * pointF.x - pointF17.x, 2.0f * pointF.y
					- pointF17.y);
		}
		return pointF17;
	}

	PointF findLineIntersection(final PointF pointF, final PointF pointF2,
                                final PointF pointF3, final PointF pointF4) {
		final float x = pointF.x;
		final float y = pointF.y;
		final float x2 = pointF2.x;
		final float y2 = pointF2.y;
		final float x3 = pointF3.x;
		final float y3 = pointF3.y;
		final float x4 = pointF4.x;
		final float y4 = pointF4.y;
		final float n = y - y2;
		final float n2 = x2 - x;
		final float n3 = (x - x2) * y + (y2 - y) * x;
		final float n4 = y3 - y4;
		final float n5 = x4 - x3;
		final float n6 = (x3 - x4) * y3 + (y4 - y3) * x3;
		final float n7 = (n6 * n2 - n3 * n5) / (n5 * n - n2 * n4);
		float n8;
		if (n2 != 0.0f) {
			n8 = (-n * n7 - n3) / n2;
		} else {
			n8 = (-n4 * n7 - n6) / n5;
		}
		return new PointF(n7, n8);
	}

	void pathTransformFromRect(float distance) {
		float top = this.sourceRect.top;
		float left = this.sourceRect.left;
		float bottom = this.sourceRect.bottom;
		this.tempRect.set(left + distance, top + distance,
				this.sourceRect.right - distance, bottom - distance);
		this.path.rewind();
		this.path.addRect(this.tempRect, Direction.CCW);
	}

	float checkRange(float pointA, float distance, float centerA) {
		if (pointA > centerA) {
			return pointA - distance;
		}
		if (pointA < centerA) {
			return pointA + distance;
		}
		return pointA;
	}

	public void drawShape(Canvas canvas, int width, int height, int j,
                          boolean drawPorterClear, boolean saving, boolean isSolo) {
		if (drawPorterClear) {
			if (this.shapeMode == SHAPE_MODE_SVG) {
				Svg.svgList[this.svgIndex]
						.draw(canvas,
								this.originalBounds.width()
										- ((float) (this.svgClearFixPadding * SHAPE_MODE_RECT)),
								this.originalBounds.height()
										- ((float) (this.svgClearFixPadding * SHAPE_MODE_RECT)),
								((float) this.svgClearFixPadding)
										+ this.originalBounds.left,
								((float) this.svgClearFixPadding)
										+ this.originalBounds.top, true);
			} else if (this.shapeMode != SHAPE_MODE_MASK) {
				canvas.drawPath(this.originalPath, this.paintTransparent);
			} else if (!(this.maskBitmap == null || this.maskBitmap
					.isRecycled())) {
				canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix,
						this.paintTransparent);
			}
			canvas.restoreToCount(j);
		}
		this.f529r.set(0.0f, 0.0f, (float) this.bitmapWidth,
				(float) this.bitmapHeight);
		this.bitmapMatrix.mapRect(this.f529r);
		if (!saving) {
			this.f529r.intersect(0.0f, 0.0f, (float) width, (float) height);
		}
		@SuppressLint("WrongConstant") int k = canvas.saveLayer(this.f529r, null, 31);
		if (this.shapeMode == SHAPE_MODE_SVG) {
			Svg.svgList[svgIndex].draw(canvas, this.bounds.width(),
					this.bounds.height(), this.bounds.left, this.bounds.top,
					false);
		} else if (this.shapeMode != SHAPE_MODE_MASK) {
			canvas.drawPath(this.path, this.paintPath);
		} else if (!(this.maskBitmap == null || this.maskBitmap.isRecycled())) {
			canvas.drawBitmap(this.maskBitmap, this.maskMatrix, this.maskPaint);
		}
		canvas.drawBitmap(this.bitmap, this.bitmapMatrix, this.paintXferMode);
		canvas.restoreToCount(k);
		if (isSolo && Svg.strokeSize > 0.0f) {
			if (this.shapeMode == SHAPE_MODE_SVG) {
				Svg.svgList[this.svgIndex].drawStroke(canvas,
						this.bounds.width(), this.bounds.height(),
						this.bounds.left, this.bounds.top, false);
				return;
			}
			this.paintSoloBorder.setStrokeWidth(Svg.strokeSize);
			this.paintSoloBorder.setColor(Svg.colorStroke);
			canvas.drawPath(this.path, this.paintSoloBorder);
		}
	}

	public void initIcon(int width, int height) {
		this.iconPaint = new Paint(SHAPE_MODE_POINT);
		this.iconPaint.setFilterBitmap(true);
		this.iconPaint.setColor(-7829368);
		this.paintXferMode.setColor(-7829368);
		scalePath(5.0f, (float) width, (float) height);
		this.iconMaskPaint = new Paint(SHAPE_MODE_POINT);
		this.iconMaskPaint.setFilterBitmap(true);
		this.iconMaskPaint.setColor(-7829368);
		this.iconXferMode = new PorterDuffXfermode(Mode.SRC_IN);
		this.iconMaskPaint.setXfermode(this.iconXferMode);
		Svg.setColorTint(-7829368);
	}

	@SuppressLint("WrongConstant")
	void drawShapeIcon(Canvas canvas, int width, int height, int j,
                       boolean drawPorterClear) {
		setMaskBitmapPositions();
		this.path.offset((float) (-this.offsetX), (float) (-this.offsetY));
		this.originalPath.offset((float) (-this.offsetX),
				(float) (-this.offsetY));
		this.maskMatrix.postTranslate((float) (-this.offsetX),
				(float) (-this.offsetY));
		this.transparentMaskMatrix.postTranslate((float) (-this.offsetX),
				(float) (-this.offsetY));
		if (drawPorterClear) {
			if (this.shapeMode == SHAPE_MODE_SVG) {
				Svg.svgList[this.svgIndex]
						.draw(canvas,
								this.originalBounds.width()
										- ((float) (this.svgClearFixPadding * SHAPE_MODE_RECT)),
								this.originalBounds.height()
										- ((float) (this.svgClearFixPadding * SHAPE_MODE_RECT)),
								(this.originalBounds.left + ((float) this.svgClearFixPadding))
										- ((float) this.offsetX),
								(this.originalBounds.top + ((float) this.svgClearFixPadding))
										- ((float) this.offsetY), true);
			} else if (this.shapeMode == SHAPE_MODE_MASK) {
				canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix,
						this.paintTransparent);
			} else {
				canvas.drawPath(this.originalPath, this.paintTransparent);
			}
			canvas.restoreToCount(j);
		}
		int i;
		if (this.shapeMode == SHAPE_MODE_SVG) {
			i = canvas.saveLayer(0.0f, 0.0f, (float) width, (float) height,
					null, 31);
			Svg.svgList[this.svgIndex].draw(canvas, this.bounds.width(),
					this.bounds.height(), this.bounds.left
							- ((float) this.offsetX), this.bounds.top
							- ((float) this.offsetY), false);
			canvas.drawRect(this.bounds, this.iconMaskPaint);
			canvas.restoreToCount(i);
		} else if (this.shapeMode == SHAPE_MODE_MASK) {
			i = canvas.saveLayer(0.0f, 0.0f, (float) width, (float) height,
					null, 31);
			canvas.drawBitmap(this.maskBitmap, this.maskMatrix, this.iconPaint);
			canvas.drawBitmap(this.maskBitmap, this.maskMatrix,
					this.iconMaskPaint);
			canvas.restoreToCount(i);
		} else {
			canvas.drawPath(this.path, this.iconPaint);
		}
	}

//	void drawShapeIcon2(Canvas canvas, int width, int height) {
//		this.path.offset((float) (-this.offsetX), (float) (-this.offsetY));
//		this.originalPath.offset((float) (-this.offsetX),
//				(float) (-this.offsetY));
//		this.maskMatrix.postTranslate((float) (-this.offsetX),
//				(float) (-this.offsetY));
//		this.transparentMaskMatrix.postTranslate((float) (-this.offsetX),
//				(float) (-this.offsetY));
//		Paint p2 = new Paint();
//		if (this.shapeMode == SHAPE_MODE_MASK) {
//			@SuppressLint("WrongConstant") int i = canvas.saveLayer(0.0f, 0.0f, (float) width, (float) height,
//					null, 31);
//			canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix, p2);
//			p2.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//			canvas.drawRect(0.0f, 0.0f, (float) width, (float) height, p2);
//			p2.setXfermode(null);
//			canvas.restoreToCount(i);
//			return;
//		}
//		canvas.drawPath(this.path, this.iconPaint);
//	}

	public void bitmapMatrixScale(float scaleX, float scaleY, float centerX,
								  float centerY) {
		this.bitmapMatrix.postScale(scaleX, scaleY, centerX, centerY);
		checkScaleBoundries();
	}

	public void bitmapMatrixScaleScrapBook(float scaleX, float scaleY) {
		this.f528p[MESSAGE_DEFAULT] = (float) (this.bitmapWidth / SHAPE_MODE_RECT);
		this.f528p[SHAPE_MODE_POINT] = (float) (this.bitmapHeight / SHAPE_MODE_RECT);
		this.bitmapMatrix.mapPoints(this.f528p);
		this.bitmapMatrix.postScale(scaleX, scaleY,
				this.f528p[MESSAGE_DEFAULT], this.f528p[SHAPE_MODE_POINT]);
		checkScaleBoundries();
	}

	void checkScaleBoundries() {
		float scale = getScale();
		PointF centerOfImage = getCenterOfImage();
		if (scale < this.minScale) {
			this.bitmapMatrix.postScale(this.minScale / scale, this.minScale
					/ scale, centerOfImage.x, centerOfImage.y);
		}
		if (scale > this.maxScale) {
			this.bitmapMatrix.postScale(this.maxScale / scale, this.maxScale
					/ scale, centerOfImage.x, centerOfImage.y);
		}
	}

	public void bitmapMatrixTranslate(float dx, float dy) {
		this.bitmapMatrix.postTranslate(dx, dy);
		if (!this.isScrapBook) {
			checkBoundries(false);
		}
	}

	public void checkBoundries(boolean override) {
		if (ShapeCollageActivity.isGridLayoutLocked || override) {
			this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth,
					(float) this.bitmapHeight);
			this.bitmapMatrix.mapRect(this.bitmapRect);
			float dx = 0.0f;
			float dy = 0.0f;
			if (this.bitmapRect.left > this.bounds.left) {
				dx = this.bounds.left - this.bitmapRect.left;
			}
			if (this.bitmapRect.top > this.bounds.top) {
				dy = this.bounds.top - this.bitmapRect.top;
			}
			if (this.bitmapRect.right < this.bounds.right) {
				dx = this.bounds.right - this.bitmapRect.right;
			}
			if (this.bitmapRect.bottom < this.bounds.bottom) {
				dy = this.bounds.bottom - this.bitmapRect.bottom;
			}
			this.bitmapMatrix.postTranslate(dx, dy);
		}
	}

	public void checkScaleBounds(boolean override) {
		setMinScales(getBitmapScale(this.bounds), override);
		checkScaleBoundries();
	}

	public void bitmapMatrixgGetValues(float[] values) {
		this.bitmapMatrix.getValues(values);
	}

	public void bitmapMatrixRotate(float angle) {
		this.f528p[MESSAGE_DEFAULT] = (float) (this.bitmapWidth / SHAPE_MODE_RECT);
		this.f528p[SHAPE_MODE_POINT] = (float) (this.bitmapHeight / SHAPE_MODE_RECT);
		this.bitmapMatrix.mapPoints(this.f528p);
		this.bitmapMatrix.postRotate(angle, this.f528p[MESSAGE_DEFAULT],
				this.f528p[SHAPE_MODE_POINT]);
	}

	public int setScaleMatrix(int mode) {
		if (this.dx <= 0.5f) {
			this.dx = ((float) this.bitmapWidth) / 100.0f;
		}
		if (this.dy <= 0.5f) {
			this.dy = ((float) this.bitmapHeight) / 100.0f;
		}
		PointF centerOfImage = getCenterOfImage();
		float scale;
		if (mode == 0) {
			setMatrixFit();
		} else if (mode == SHAPE_MODE_POINT) {
			setBitmapPosition(false);
		} else if (mode == SHAPE_MODE_MASK) {
			this.bitmapMatrix.postRotate(-90.0f, centerOfImage.x,
					centerOfImage.y);
			if (!this.isScrapBook && ShapeCollageActivity.isGridLayoutLocked) {
				setMaxMinScalesRotated();
				scale = getScale();
				this.bitmapMatrix
						.postScale(this.minScale / scale,
								this.minScale / scale, centerOfImage.x,
								centerOfImage.y);
			}
		} else if (mode == SHAPE_MODE_RECT) {
			this.bitmapMatrix.postRotate(90.0f, centerOfImage.x,
					centerOfImage.y);
			if (!this.isScrapBook && ShapeCollageActivity.isGridLayoutLocked) {
				setMaxMinScalesRotated();
				scale = getScale();
				this.bitmapMatrix
						.postScale(this.minScale / scale,
								this.minScale / scale, centerOfImage.x,
								centerOfImage.y);
			}
		} else if (mode == SHAPE_MODE_INNER_POINT) {
			this.bitmapMatrix.postScale(-1.0f, 1.0f, centerOfImage.x,
					centerOfImage.y);
		} else if (mode == SHAPE_MODE_SVG) {
			this.bitmapMatrix.postScale(1.0f, -1.0f, centerOfImage.x,
					centerOfImage.y);
		} else if (mode == MESSAGE_MAX_BOTTOM) {
			this.bitmapMatrix.postRotate(-10.0f, centerOfImage.x,
					centerOfImage.y);
		} else if (mode == MATRIX_MODE_ROTATE_POSITIVE) {
			this.bitmapMatrix.postRotate(10.0f, centerOfImage.x,
					centerOfImage.y);
		} else if (mode == MATRIX_MODE_ZOOM_IN) {
			if (getScale() >= this.maxScale) {
				return SHAPE_MODE_POINT;
			}
			this.bitmapMatrix.postScale(this.scaleUp, this.scaleUp,
					centerOfImage.x, centerOfImage.y);
		} else if (mode == MATRIX_MODE_ZOOM_OUT) {
			if (getScale() <= this.minScale) {
				return SHAPE_MODE_RECT;
			}
			this.bitmapMatrix.postScale(this.scaleDown, this.scaleDown,
					centerOfImage.x, centerOfImage.y);
		} else if (mode == MATRIX_MODE_MOVE_LEFT) {
			this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth,
					(float) this.bitmapHeight);
			this.bitmapMatrix.mapRect(this.bitmapRect);
			if (this.bitmapRect.right <= this.bounds.right && !this.isScrapBook
					&& ShapeCollageActivity.isGridLayoutLocked) {
				return SHAPE_MODE_MASK;
			}
			this.bitmapMatrix.postTranslate(-this.dx, 0.0f);
		} else if (mode == MATRIX_MODE_MOVE_RIGHT) {
			this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth,
					(float) this.bitmapHeight);
			this.bitmapMatrix.mapRect(this.bitmapRect);
			if (this.bitmapRect.left >= this.bounds.left && !this.isScrapBook
					&& ShapeCollageActivity.isGridLayoutLocked) {
				return SHAPE_MODE_INNER_POINT;
			}
			this.bitmapMatrix.postTranslate(this.dx, 0.0f);
		} else if (mode == MATRIX_MODE_MOVE_UP) {
			this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth,
					(float) this.bitmapHeight);
			this.bitmapMatrix.mapRect(this.bitmapRect);
			if (this.bitmapRect.bottom <= this.bounds.bottom
					&& !this.isScrapBook
					&& ShapeCollageActivity.isGridLayoutLocked) {
				return SHAPE_MODE_SVG;
			}
			this.bitmapMatrix.postTranslate(0.0f, -this.dy);
		} else if (mode == MATRIX_MODE_MOVE_DOWN) {
			this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth,
					(float) this.bitmapHeight);
			this.bitmapMatrix.mapRect(this.bitmapRect);
			if (this.bitmapRect.top >= this.bounds.top && !this.isScrapBook
					&& ShapeCollageActivity.isGridLayoutLocked) {
				return MESSAGE_MAX_BOTTOM;
			}
			this.bitmapMatrix.postTranslate(0.0f, this.dy);
		}
		checkScaleBoundries();
		if (this.isScrapBook) {
			return MESSAGE_DEFAULT;
		}
		checkBoundries(false);
		return MESSAGE_DEFAULT;
	}

	PointF getCenterOfImage() {
		if (this.centerOriginal == null) {
			this.centerOriginal = new PointF();
		}
		if (this.f527f == null) {
			this.f527f = new float[SHAPE_MODE_RECT];
		}
		float y = ((float) this.bitmapHeight) / 2.0f;
		this.f527f[MESSAGE_DEFAULT] = ((float) this.bitmapWidth) / 2.0f;
		this.f527f[SHAPE_MODE_POINT] = y;
		this.bitmapMatrix.mapPoints(this.f527f);
		this.centerOriginal.set(this.f527f[MESSAGE_DEFAULT],
				this.f527f[SHAPE_MODE_POINT]);
		return this.centerOriginal;
	}

	void setMatrixFit() {
		float scaleBitmap = Math.min(this.bounds.width()
				/ ((float) this.bitmapWidth), this.bounds.height()
				/ ((float) this.bitmapHeight));
		if (this.isScrapBook) {
			scaleBitmap *= Collage.scrapBookShapeScale;
		}
		float bitmapY = this.bounds.top
				+ ((this.bounds.height() - (((float) this.bitmapHeight) * scaleBitmap)) / 2.0f);
		float bitmapX = this.bounds.left
				+ ((this.bounds.width() - (((float) this.bitmapWidth) * scaleBitmap)) / 2.0f);
		this.bitmapMatrix.reset();
		this.bitmapMatrix.postScale(scaleBitmap, scaleBitmap);
		this.bitmapMatrix.postTranslate(bitmapX, bitmapY);
	}

	private void setScrapBookBitmapPosition(int index, boolean isChangeRatio,
			int width, int height) {
		if (isChangeRatio) {
			int w = this.bitmapWidth;
			int h = this.bitmapHeight;
			float[] points = new float[MATRIX_MODE_ZOOM_IN];
			points[MESSAGE_DEFAULT] = 0.0f;
			points[SHAPE_MODE_POINT] = 0.0f;
			points[SHAPE_MODE_RECT] = (float) w;
			points[SHAPE_MODE_MASK] = 0.0f;
			points[SHAPE_MODE_INNER_POINT] = (float) w;
			points[SHAPE_MODE_SVG] = (float) h;
			points[MESSAGE_MAX_BOTTOM] = 0.0f;
			points[MATRIX_MODE_ROTATE_POSITIVE] = (float) h;
			this.bitmapMatrix.mapPoints(points);
			RectF drawArea = new RectF((float) this.offsetX,
					(float) this.offsetY, (float) (this.offsetX + width),
					(float) (this.offsetY + height));
			if (!drawArea.contains(points[MESSAGE_DEFAULT],
					points[SHAPE_MODE_POINT])) {
				if (!drawArea.contains(points[SHAPE_MODE_RECT],
						points[SHAPE_MODE_MASK])) {
					if (!drawArea.contains(points[SHAPE_MODE_INNER_POINT],
							points[SHAPE_MODE_SVG])) {
						if (!drawArea.contains(points[MESSAGE_MAX_BOTTOM],
								points[MATRIX_MODE_ROTATE_POSITIVE])) {
							PointF A = new PointF((float) this.offsetX,
									(float) this.offsetY);
							PointF B = new PointF(
									(float) (this.offsetX + width),
									(float) this.offsetY);
							PointF P = new PointF();
							float[] f;
							float min;
							int minIndex;
							int i;
							if (points[SHAPE_MODE_POINT] < ((float) this.offsetY)) {
								P.set(points[MESSAGE_DEFAULT],
										points[SHAPE_MODE_POINT]);
								f = new float[SHAPE_MODE_INNER_POINT];
								f[MESSAGE_DEFAULT] = pointToLineDistance(A, B,
										P);
								P.set(points[SHAPE_MODE_RECT],
										points[SHAPE_MODE_MASK]);
								f[SHAPE_MODE_POINT] = pointToLineDistance(A, B,
										P);
								P.set(points[SHAPE_MODE_INNER_POINT],
										points[SHAPE_MODE_SVG]);
								f[SHAPE_MODE_RECT] = pointToLineDistance(A, B,
										P);
								P.set(points[MESSAGE_MAX_BOTTOM],
										points[MATRIX_MODE_ROTATE_POSITIVE]);
								f[SHAPE_MODE_MASK] = pointToLineDistance(A, B,
										P);
								min = f[MESSAGE_DEFAULT];
								minIndex = MESSAGE_DEFAULT;
								for (i = SHAPE_MODE_POINT; i < SHAPE_MODE_INNER_POINT; i += SHAPE_MODE_POINT) {
									if (f[i] < min) {
										min = f[i];
										minIndex = i;
									}
								}
								this.bitmapMatrix
										.postTranslate(
												0.0f,
												((float) (this.offsetY + 120))
														- points[(minIndex * SHAPE_MODE_RECT)
																+ SHAPE_MODE_POINT]);
								return;
							}
							A = new PointF((float) this.offsetX,
									(float) (this.offsetY + height));
							B = new PointF((float) (this.offsetX + width),
									(float) (this.offsetY + height));
							P.set(points[MESSAGE_DEFAULT],
									points[SHAPE_MODE_POINT]);
							f = new float[SHAPE_MODE_INNER_POINT];
							f[MESSAGE_DEFAULT] = pointToLineDistance(A, B, P);
							P.set(points[SHAPE_MODE_RECT],
									points[SHAPE_MODE_MASK]);
							f[SHAPE_MODE_POINT] = pointToLineDistance(A, B, P);
							P.set(points[SHAPE_MODE_INNER_POINT],
									points[SHAPE_MODE_SVG]);
							f[SHAPE_MODE_RECT] = pointToLineDistance(A, B, P);
							P.set(points[MESSAGE_MAX_BOTTOM],
									points[MATRIX_MODE_ROTATE_POSITIVE]);
							f[SHAPE_MODE_MASK] = pointToLineDistance(A, B, P);
							min = f[MESSAGE_DEFAULT];
							minIndex = MESSAGE_DEFAULT;
							for (i = SHAPE_MODE_POINT; i < SHAPE_MODE_INNER_POINT; i += SHAPE_MODE_POINT) {
								if (f[i] < min) {
									min = f[i];
									minIndex = i;
								}
							}
							this.bitmapMatrix
									.postTranslate(
											0.0f,
											((float) ((this.offsetY + height) - 120))
													- points[(minIndex * SHAPE_MODE_RECT)
															+ SHAPE_MODE_POINT]);
							return;
						}
						return;
					}
					return;
				}
				return;
			}
			return;
		}
		this.bitmapMatrix = new Matrix();
		setMatrixFit();
		float actualScale = getScale();
		setMaxMinScales(actualScale);
		float scale = 1.0f / actualScale;
		this.touchStrokeWidth = this.tempTouchStrokeWidth * scale;
		this.scrapBookPadding = 25.0f * scale;
		this.bitmapMatrix.postRotate((float) scrapBookRotation[index],
				this.bounds.left + (this.bounds.width() / 2.0f),
				this.bounds.top + (this.bounds.height() / 2.0f));
		this.touchRect = new RectF(-this.scrapBookPadding,
				-this.scrapBookPadding, ((float) this.bitmapWidth)
						+ this.scrapBookPadding, ((float) this.bitmapHeight)
						+ this.scrapBookPadding);
		this.touchPaint.setColor(-1290417);
		this.touchPaint.setFilterBitmap(true);
		this.touchPaint.setStyle(Style.STROKE);
		this.touchPaint.setStrokeWidth(this.touchStrokeWidth);
		this.borderPaint.setColor(-1);
		this.borderPaint.setStyle(Style.STROKE);
		this.borderPaint.setStrokeWidth((float) this.borderStrokeWidth);
		this.borderPaint.setAntiAlias(true);
	}

	public float pointToLineDistance(PointF A, PointF B, PointF P) {
		return Math.abs(((P.x - A.x) * (B.y - A.y))
				- ((P.y - A.y) * (B.x - A.x)))
				/ ((float) Math
						.sqrt((double) (((B.x - A.x) * (B.x - A.x)) + ((B.y - A.y) * (B.y - A.y)))));
	}

	float sqr(float x) {
		return x * x;
	}

	float dist2(PointF v, PointF w) {
		return sqr(v.x - w.x) + sqr(v.y - w.y);
	}

	float distToSegmentSquared(PointF p, PointF v, PointF w) {
		float l2 = dist2(v, w);
		if (l2 == 0.0f) {
			return dist2(p, v);
		}
		float t = (((p.x - v.x) * (w.x - v.x)) + ((p.y - v.y) * (w.y - v.y)))
				/ l2;
		if (t < 0.0f) {
			return dist2(p, v);
		}
		if (t > 1.0f) {
			return dist2(p, w);
		}
		return dist2(p, new PointF(v.x + ((w.x - v.x) * t), v.y
				+ ((w.y - v.y) * t)));
	}

	float distToSegment(PointF p, PointF v, PointF w) {
		return (float) Math.sqrt((double) distToSegmentSquared(p, v, w));
	}

	public float[] getMappedCenter() {
		this.pts[MESSAGE_DEFAULT] = (float) (this.bitmapWidth / SHAPE_MODE_RECT);
		this.pts[SHAPE_MODE_POINT] = (float) (this.bitmapHeight / SHAPE_MODE_RECT);
		this.bitmapMatrix.mapPoints(this.pts, this.pts);
		return this.pts;
	}

	public boolean isScrapBookSelected(float x1, float y1) {
		this.pts[MESSAGE_DEFAULT] = x1;
		this.pts[SHAPE_MODE_POINT] = y1;
		this.inverse.reset();
		boolean isInversable = this.bitmapMatrix.invert(this.inverse);
		this.inverse.mapPoints(this.pts, this.pts);
		float x = this.pts[MESSAGE_DEFAULT];
		float y = this.pts[SHAPE_MODE_POINT];
		if (x < 0.0f || x > ((float) this.bitmapWidth) || y < 0.0f
				|| y > ((float) this.bitmapHeight)) {
			return false;
		}
		return true;
	}

	public void drawShapeForScrapBook(Canvas canvas, int width, int height,
                                      boolean isSelected, boolean isOrthogonal) {
		this.touchRect.set(-this.scrapBookPadding, -this.scrapBookPadding,
				((float) this.bitmapWidth) + this.scrapBookPadding,
				((float) this.bitmapHeight) + this.scrapBookPadding);
		canvas.save();
		canvas.concat(this.bitmapMatrix);
		this.npd.setBounds((-this.npdPadding) - this.borderStrokeWidth,
				(-this.npdPadding) - this.borderStrokeWidth,
				(this.bitmapWidth + this.npdPadding) + this.borderStrokeWidth,
				(this.bitmapHeight + this.npdPadding) + this.borderStrokeWidth);
		this.npd.draw(canvas);
		if (!(this.bitmap == null || this.bitmap.isRecycled())) {
			canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, this.paintScrap);
		}
		if (isSelected) {
			this.touchStrokeWidth = this.tempTouchStrokeWidth
					* (1.0f / getScale());
			this.touchPaint.setStrokeWidth(this.touchStrokeWidth);
			canvas.drawRect(this.touchRect, this.touchPaint);
			setDelAndScaleBitmapMatrix();
			if (!(this.btmDelete == null || this.btmDelete.isRecycled())) {
				canvas.drawBitmap(this.btmDelete, this.removeBitmapMatrix,
						this.touchPaint);
			}
			if (!(this.btmScale == null || this.btmScale.isRecycled())) {
				canvas.drawBitmap(this.btmScale, this.scaleBitmapMatrix,
						this.touchPaint);
			}
			if (isOrthogonal) {
				canvas.drawPath(this.dashPathVertical, this.dashPaint);
				canvas.drawPath(this.dashPathHorizontal, this.dashPaint);
			}
		}
		canvas.drawRect(
				(float) ((-this.borderStrokeWidth) / SHAPE_MODE_RECT),
				(float) ((-this.borderStrokeWidth) / SHAPE_MODE_RECT),
				(float) (this.bitmapWidth + (this.borderStrokeWidth / SHAPE_MODE_RECT)),
				(float) (this.bitmapHeight + (this.borderStrokeWidth / SHAPE_MODE_RECT)),
				this.borderPaint);
		canvas.restore();
	}

	void setDelAndScaleBitmapMatrix() {
		if (this.removeBitmapMatrix == null) {
			this.removeBitmapMatrix = new Matrix();
		}
		if (this.scaleBitmapMatrix == null) {
			this.scaleBitmapMatrix = new Matrix();
		}
		this.removeBitmapMatrix.reset();
		this.scaleBitmapMatrix.reset();
		if (this.delW == 0) {
			this.delW = this.btmDelete.getWidth();
		}
		if (this.screenWidth <= 0) {
			this.screenWidth = 720;
		}
		float bitmapScale = (2.0f * (((float) this.screenWidth) / 20.0f))
				/ ((float) this.delW);
		this.deleteWidthHalf = (((float) this.delW) * bitmapScale) / 1.4f;
		this.removeBitmapMatrix.postScale(bitmapScale, bitmapScale);
		this.removeBitmapMatrix.postTranslate((-this.scrapBookPadding)
				- ((((float) this.delW) * bitmapScale) / 2.0f),
				(-this.scrapBookPadding)
						- ((((float) this.delW) * bitmapScale) / 2.0f));
		this.scaleBitmapMatrix.postScale(bitmapScale, bitmapScale);
		this.scaleBitmapMatrix.postTranslate(
				(((float) this.bitmapWidth) + this.scrapBookPadding)
						- ((((float) this.delW) * bitmapScale) / 2.0f),
				(((float) this.bitmapHeight) + this.scrapBookPadding)
						- ((((float) this.delW) * bitmapScale) / 2.0f));
		float scale = getScale();
		this.scaleBitmapMatrix.postScale(1.0f / scale, 1.0f / scale,
				this.touchRect.right, this.touchRect.bottom);
		this.removeBitmapMatrix.postScale(1.0f / scale, 1.0f / scale,
				this.touchRect.left, this.touchRect.top);
		if (this.screenWidth > 0) {
			this.tempTouchStrokeWidth = ((float) this.screenWidth) / 120.0f;
		}
	}

	public void initScrapBook(NinePatchDrawable npd) {
		setNinePatch(npd);
	}

	public void setNinePatch(NinePatchDrawable npd) {
		this.npd = npd;
		this.touchRect.round(new Rect());
	}

	public float getScale() {
		this.bitmapMatrix.getValues(this.values);
		float scalex = this.values[MESSAGE_DEFAULT];
		float skewy = this.values[SHAPE_MODE_MASK];
		float scale = (float) Math
				.sqrt((double) ((scalex * scalex) + (skewy * skewy)));
		if (scale <= 0.0f) {
			return 1.0f;
		}
		return scale;
	}

	public boolean isInCircle(float x1, float y1) {
		this.pts[MESSAGE_DEFAULT] = x1;
		this.pts[SHAPE_MODE_POINT] = y1;
		this.bitmapMatrix.invert(this.inverse);
		this.inverse.mapPoints(this.pts, this.pts);
		float x = this.pts[MESSAGE_DEFAULT];
		float y = this.pts[SHAPE_MODE_POINT];
		float scale = getScale();
		if (((x - this.touchRect.right) * (x - this.touchRect.right))
				+ ((y - this.touchRect.bottom) * (y - this.touchRect.bottom)) < (this.deleteWidthHalf * this.deleteWidthHalf)
				/ (scale * scale)) {
			return true;
		}
		return false;
	}

	public boolean isOnCross(float x1, float y1) {
		this.pts[MESSAGE_DEFAULT] = x1;
		this.pts[SHAPE_MODE_POINT] = y1;
		this.bitmapMatrix.invert(this.inverse);
		this.inverse.mapPoints(this.pts, this.pts);
		float x = this.pts[MESSAGE_DEFAULT];
		float y = this.pts[SHAPE_MODE_POINT];
		float scale = getScale();
		if (((x - this.touchRect.left) * (x - this.touchRect.left))
				+ ((y - this.touchRect.top) * (y - this.touchRect.top)) < (this.deleteWidthHalf * this.deleteWidthHalf)
				/ (scale * scale)) {
			return true;
		}
		return false;
	}
}
