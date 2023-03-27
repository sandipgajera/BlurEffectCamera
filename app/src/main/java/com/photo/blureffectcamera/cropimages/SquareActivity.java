package com.photo.blureffectcamera.cropimages;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.photo.blureffectcamera.MainActivity;
import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.canvas.BaseData;
import com.photo.blureffectcamera.canvas.CanvasTextView;
import com.photo.blureffectcamera.canvas.DecorateView;
import com.photo.blureffectcamera.canvas.MyMatrix;
import com.photo.blureffectcamera.canvas.TextData;
import com.photo.blureffectcamera.canvas.TextLibHelper;

import com.photo.blureffectcamera.collagelib.Utility;
import com.photo.blureffectcamera.common_libs.MyAsyncTask2;
import com.photo.blureffectcamera.imageloader.ImageUtility;
import com.photo.blureffectcamera.lib.MyAdapter;
import com.photo.blureffectcamera.lib.MyRecylceAdapterBase;
import com.photo.blureffectcamera.lyrebirdlib.BlurBuilderNormal;
import com.photo.blureffectcamera.pattern.PatternHelper;
import com.photo.blureffectcamera.sticker.RotationGestureDetector;
import com.photo.blureffectcamera.sticker.StickerData;
import com.photo.blureffectcamera.sticker.StickerLibHelper;
import com.photo.blureffectcamera.sticker.StickerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

@TargetApi(11)
public class SquareActivity extends FragmentActivity {
    public static final int INDEX_SQUARE = 0;
    public static final int INDEX_SQUARE_ADJ = 3;
    public static final int INDEX_SQUARE_BACKGROUND = 1;
    public static final int INDEX_SQUARE_BLUR = 2;
    public static final int INDEX_SQUARE_CASCADE = 4;
    public static final int INDEX_SQUARE_FRAME = 7;
    public static final int INDEX_SQUARE_FX = 6;
    public static final int INDEX_SQUARE_INVISIBLE_VIEW = 5;
    static final int INSTAGRAM_ID = 470;
    public static final int MATRIX_MODE_CENTER = 1;
    public static final int MATRIX_MODE_FIT = 0;
    public static final int MATRIX_MODE_FLIP_HORIZONTAL = 4;
    public static final int MATRIX_MODE_FLIP_VERTICAL = 5;
    public static final int MATRIX_MODE_ROTATE_LEFT = 3;
    public static final int MATRIX_MODE_ROTATE_RIGHT = 2;
    static final int SAVE_IMAGE_ID = 1346;
    public static final int TAB_INDEX_SQUARE = 0;
    public static final int TAB_INDEX_SQUARE_ADJ = 5;
    public static final int TAB_INDEX_SQUARE_BACKGROUND = 1;
    public static final int TAB_INDEX_SQUARE_BLUR = 2;
    public static final int TAB_INDEX_SQUARE_CASCADE = 6;
    public static final int TAB_INDEX_SQUARE_FRAME = 3;
    public static final int TAB_INDEX_SQUARE_FX = 4;
    public static final int TAB_SIZE = 7;
    private static final String TAG;
    int SAVE_FINISH;
    int SAVE_INSTAGRAM;
    int SAVE_NORMAL;
    Activity activity;
    FragmentActivity activityFragment;
    TextView blurText;
    LinearLayout colorContainer;
    int colorDefault;
    int colorSelected;
    Context context;
    FragmentCrop cropFragment;
    FragmentCrop.CropListener cropListener;
    int currentSelectedTabIndex;
    private ScaleGestureDetector mScaleDetector;
    OnSeekBarChangeListener mSeekBarListener;
    SquareView mSqView;
    RelativeLayout mainLayout;
    Button originalInstagram;
    PatternHelper patternHelper;
    RecyclerView recyclerViewInnerPattern;
    AlertDialog saveImageAlert;
    SeekBar seekBarBlur;
    SeekBar seekbarCascadeBlur;
    SeekBar seekbarCascadeNumber;
    View selectFilterTextView;
    boolean selectImageForAdj;
    private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;
    Bitmap sourceBitmap;
    int stickerFragemntContinerId;
    StickerLibHelper stickerLibHelper;
    View[] tabButtonList;
    FrameLayout textAndStickerViewContainer;
    int textFragemntContinerId;
    TextLibHelper textLibHelper;
    float topOffset;
    float totalOffset;
    ViewFlipper viewFlipper;
    ArrayList<MyRecylceAdapterBase> patternAdapterList;

    /* renamed from: com.lyrebirdstudio.instasquare.lib.SquareActivity.2 */
    class C07422 implements OnHierarchyChangeListener {

        class C13171 implements DecorateView.OnDecorateViewTouchUp {
            C13171() {
            }

            public void onTouchUp(BaseData mData) {
                mData.setImageSaveMatrix(SquareActivity.this.mSqView.bitmapMatrix);
            }
        }

        C07422() {
        }

        public void onChildViewAdded(View parent, View child) {
            ((DecorateView) child).setOnDecorateViewTouchUp(new C13171());
        }

        public void onChildViewRemoved(View parent, View child) {
        }
    }

    /* renamed from: com.lyrebirdstudio.instasquare.lib.SquareActivity.3 */
    class C07433 implements Runnable {
        final /* synthetic */ HorizontalScrollView val$horizontalScrollView;

        C07433(HorizontalScrollView horizontalScrollView) {
            this.val$horizontalScrollView = horizontalScrollView;
        }

        public void run() {
            this.val$horizontalScrollView.scrollTo(
                    this.val$horizontalScrollView.getChildAt(SquareActivity.TAB_INDEX_SQUARE).getMeasuredWidth(),
                    SquareActivity.TAB_INDEX_SQUARE);
        }
    }

    /* renamed from: com.lyrebirdstudio.instasquare.lib.SquareActivity.4 */
    class C07444 implements Runnable {
        final /* synthetic */ HorizontalScrollView val$horizontalScrollView;

        C07444(HorizontalScrollView horizontalScrollView) {
            this.val$horizontalScrollView = horizontalScrollView;
        }

        public void run() {
            this.val$horizontalScrollView.fullScroll(17);
        }
    }

    /* renamed from: com.lyrebirdstudio.instasquare.lib.SquareActivity.5 */
    class C07455 implements OnSeekBarChangeListener {
        C07455() {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            int id = seekBar.getId();
            if (id == R.id.nocrop_blur_seek_bar || id == R.id.seekbar_square_cascade_blur) {
                int progress = seekBar.getProgress();
                float radius = ((float) progress) / 4.0f;
                if (radius > 25.0f) {
                    radius = 25.0f;
                }
                if (radius < 0.0f) {
                    radius = 0.0f;
                }
                boolean isCascade = id == R.id.seekbar_square_cascade_blur;
                if (isCascade) {
                    SquareActivity.this.seekBarBlur.setProgress(progress);
                } else {
                    SquareActivity.this.seekbarCascadeBlur.setProgress(progress);
                    SquareActivity.this.blurText.setText("" + ((int) radius));
                }
                SquareActivity.this.mSqView.setBlurBitmap((int) radius, isCascade);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int id = seekBar.getId();
            if (id == R.id.nocrop_blur_seek_bar || id == R.id.seekbar_square_cascade_blur) {
                SquareActivity.this.blurText.setText("" + ((int) (((float) progress) / 4.0f)));
            } else if (id == R.id.seekbar_square_cascade_number) {
                SquareActivity.this.mSqView.cascadeNumber = progress + SquareActivity.TAB_INDEX_SQUARE_BACKGROUND;
                SquareActivity.this.mSqView.invalidate();
            }
        }
    }

    /* renamed from: com.lyrebirdstudio.instasquare.lib.SquareActivity.9 */
    class C07469 implements OnClickListener {
        C07469() {
        }

        public void onClick(DialogInterface dialog, int which) {
            SquareActivity.this.activity.finish();
        }
    }

    class MyGestureListener extends SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        MyGestureListener() {
        }

        public boolean onDown(MotionEvent e) {
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent event) {
            return true;
        }

        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }

    final class MyMediaScannerConnectionClient implements MediaScannerConnectionClient {
        private MediaScannerConnection mConn;
        private String mFilename;
        private String mMimetype;

        public MyMediaScannerConnectionClient(Context ctx, File file, String mimetype) {
            this.mFilename = file.getAbsolutePath();
            this.mConn = new MediaScannerConnection(ctx, this);
            this.mConn.connect();
        }

        public void onMediaScannerConnected() {
            this.mConn.scanFile(this.mFilename, this.mMimetype);
        }

        public void onScanCompleted(String path, Uri uri) {
            this.mConn.disconnect();
        }
    }

    class SquareView extends View {
        public static final int BACKGROUND_BLUR = 1;
        public static final int BACKGROUND_CASCADE = 2;
        public static final int BACKGROUND_PATTERN = 0;
        private static final int INVALID_POINTER_ID = -1;
        public static final int PATTERN_SENTINEL = -1;
        private static final int UPPER_SIZE_LIMIT = 2048;
        public static final int VIEW_ORIGINAL = 1;
        public static final int VIEW_SQUARE = 0;
        int actualScreenHeight;
        int backgroundMode;
        float bitmapHeight;
        Matrix bitmapMatrix;
        float bitmapWidth;
        Bitmap blurBitmap;
        Matrix blurBitmapMatrix;
        BlurBuilderNormal blurBuilderNormal;
        private int blurRadius;
        Rect blurRectSrc;
        int cascadeNumber;
        float cascadeOffsetX;
        float cascadeOffsetY;
        RectF[] cascadeRectArray;
        PointF centerOriginal;
        Paint dashPaint;
        Path dashPathHorizontal;
        Path dashPathHorizontalTemp;
        Path dashPathVertical;
        Path dashPathVerticalTemp;
        int drawableScreenHeight;
        int drawableScreenWidth;
        float epsilon;
        float[] f531f;
        Bitmap filterBitmap;
        float finalAngle;
        Paint grayPaint;
        Matrix inverse;
        boolean isOrthogonal;
        private int mActivePointerId;
        float mLastTouchX;
        float mLastTouchY;
        private RotationGestureDetector mRotationDetector;
        float mScaleFactor;
        int offsetX;
        int offsetY;
        Paint paint;
        Bitmap patternBitmap;
        Paint patternPaint;
        Paint pointPaint;
        float[] pts;
        private RectF rectBitmap;
        RotationGestureDetector.OnRotationGestureListener rotateListener;
        float[] values;
        int viewHeight;
        int viewSizeMode;
        int viewWidth;

        private class ScaleListener extends SimpleOnScaleGestureListener {
            private ScaleListener() {
            }

            public boolean onScale(ScaleGestureDetector detector) {
                SquareView.this.mScaleFactor = detector.getScaleFactor();
                PointF center;
                if (detector.isInProgress()) {
                    SquareView.this.mScaleFactor = Math.max(0.1f, Math.min(SquareView.this.mScaleFactor, 5.0f));
                    center = SquareView.this.getCenterOfImage();
                    SquareView.this.bitmapMatrix.postScale(SquareView.this.mScaleFactor, SquareView.this.mScaleFactor,
                            center.x, center.y);
                    SquareView.this.invalidate();
                } else {
                    SquareView.this.mScaleFactor = Math.max(0.1f, Math.min(SquareView.this.mScaleFactor, 5.0f));
                    center = SquareView.this.getCenterOfImage();
                    SquareView.this.bitmapMatrix.postScale(SquareView.this.mScaleFactor, SquareView.this.mScaleFactor,
                            center.x, center.y);
                    SquareView.this.invalidate();
                }
                return true;
            }
        }

        /*
         * renamed from:
         * com.lyrebirdstudio.instasquare.lib.SquareActivity.SquareView.1
         */
        class C13211 implements RotationGestureDetector.OnRotationGestureListener {
            C13211() {
            }

            public void OnRotation(RotationGestureDetector rotationDetector) {
                float angle = rotationDetector.getAngle();
                float rotation = SquareView.this.getMatrixRotation(SquareView.this.bitmapMatrix);
                if ((rotation == 0.0f || rotation == 90.0f || rotation == 180.0f || rotation == -180.0f
                        || rotation == -90.0f)
                        && Math.abs(SquareView.this.finalAngle - angle) < SquareView.this.epsilon) {
                    SquareView.this.isOrthogonal = true;
                    return;
                }
                if (Math.abs((rotation - SquareView.this.finalAngle) + angle) < SquareView.this.epsilon) {
                    angle = SquareView.this.finalAngle - rotation;
                    SquareView.this.isOrthogonal = true;
                } else if (Math
                        .abs(90.0f - ((rotation - SquareView.this.finalAngle) + angle)) < SquareView.this.epsilon) {
                    angle = (SquareView.this.finalAngle + 90.0f) - rotation;
                    SquareView.this.isOrthogonal = true;
                } else if (Math
                        .abs(180.0f - ((rotation - SquareView.this.finalAngle) + angle)) < SquareView.this.epsilon) {
                    angle = (SquareView.this.finalAngle + 180.0f) - rotation;
                    SquareView.this.isOrthogonal = true;
                } else if (Math
                        .abs(-180.0f - ((rotation - SquareView.this.finalAngle) + angle)) < SquareView.this.epsilon) {
                    angle = (SquareView.this.finalAngle - 0.024902344f) - rotation;
                    SquareView.this.isOrthogonal = true;
                } else if (Math
                        .abs(-90.0f - ((rotation - SquareView.this.finalAngle) + angle)) < SquareView.this.epsilon) {
                    angle = (SquareView.this.finalAngle - 0.049804688f) - rotation;
                    SquareView.this.isOrthogonal = true;
                } else {
                    SquareView.this.isOrthogonal = false;
                }
                PointF center = SquareView.this.getCenterOfImage();
                SquareView.this.bitmapMatrix.postRotate(SquareView.this.finalAngle - angle, center.x, center.y);
                SquareView.this.finalAngle = angle;
                SquareView.this.invalidate();
            }
        }

        public SquareView(Context context, int w, int h) {
            super(context);
            this.backgroundMode = BACKGROUND_PATTERN;
            this.dashPaint = new Paint();
            this.dashPathVerticalTemp = new Path();
            this.dashPathHorizontalTemp = new Path();
            this.isOrthogonal = false;
            this.cascadeOffsetX = 120.0f;
            this.cascadeOffsetY = 120.0f;
            RectF[] rectFArr = new RectF[SquareActivity.TAB_INDEX_SQUARE_FX];
            rectFArr[BACKGROUND_PATTERN] = new RectF();
            rectFArr[VIEW_ORIGINAL] = new RectF();
            rectFArr[BACKGROUND_CASCADE] = new RectF();
            rectFArr[SquareActivity.TAB_INDEX_SQUARE_FRAME] = new RectF();
            this.cascadeRectArray = rectFArr;
            this.cascadeNumber = SquareActivity.TAB_INDEX_SQUARE_FX;
            this.viewSizeMode = BACKGROUND_PATTERN;
            this.blurRectSrc = new Rect();
            this.blurRadius = 14;
            this.f531f = new float[BACKGROUND_CASCADE];
            this.centerOriginal = new PointF();
            this.mActivePointerId = PATTERN_SENTINEL;
            this.rectBitmap = new RectF();
            this.pts = new float[BACKGROUND_CASCADE];
            this.inverse = new Matrix();
            this.values = new float[9];
            this.mScaleFactor = 1.0f;
            this.finalAngle = 0.0f;
            this.epsilon = 4.0f;
            this.rotateListener = new C13211();
            this.drawableScreenWidth = w;
            this.drawableScreenHeight = (int) (((float) h) - SquareActivity.this.totalOffset);
            this.actualScreenHeight = h;
            this.paint = new Paint();
            this.bitmapMatrix = new Matrix();
            this.paint.setColor(-3355444);
            int min = Math.min(w, h);
            this.viewWidth = min;
            this.viewHeight = min;
            new Options().inPreferredConfig = Config.ARGB_8888;
            this.bitmapWidth = (float) SquareActivity.this.sourceBitmap.getWidth();
            this.bitmapHeight = (float) SquareActivity.this.sourceBitmap.getHeight();
            this.rectBitmap.set(0.0f, 0.0f, this.bitmapWidth, this.bitmapHeight);
            float strokeW = ((float) this.drawableScreenWidth) / 120.0f;
            if (strokeW <= 0.0f) {
                strokeW = 5.0f;
            }
            this.dashPaint.setStrokeWidth(strokeW);
            Paint paint = this.dashPaint;
            float[] fArr = new float[BACKGROUND_CASCADE];
            fArr[BACKGROUND_PATTERN] = strokeW;
            fArr[VIEW_ORIGINAL] = strokeW;
            paint.setPathEffect(new DashPathEffect(fArr, 0.0f));
            this.dashPathVertical = new Path();
            this.dashPathHorizontal = new Path();
            setViewSize();
            SquareActivity.this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
            this.mRotationDetector = new RotationGestureDetector(this.rotateListener);
            this.grayPaint = new Paint();
            this.grayPaint.setColor(-12303292);
            this.pointPaint = new Paint();
            this.pointPaint.setColor(InputDeviceCompat.SOURCE_ANY);
            this.pointPaint.setStrokeWidth(20.0f);
            this.blurBitmapMatrix = new Matrix();
            this.patternPaint = new Paint(VIEW_ORIGINAL);
            this.patternPaint.setColor(PATTERN_SENTINEL);
            this.dashPaint.setColor(-7829368);
            this.dashPaint.setStyle(Style.STROKE);
            setPathPositions();
        }

        public void changeViewSizeMode() {
            int sizeMode = BACKGROUND_PATTERN;
            if (this.viewSizeMode == 0) {
                sizeMode = VIEW_ORIGINAL;
            }
            this.viewSizeMode = sizeMode;
            setViewSize();
            SquareActivity.this.checkDecoareteViewPositions(this.bitmapMatrix);
        }

        public void setViewSize() {
            if (this.viewSizeMode == 0) {
                int min = Math.min(this.drawableScreenWidth, this.drawableScreenHeight);
                this.viewWidth = min;
                this.viewHeight = min;
            } else if (this.viewSizeMode == VIEW_ORIGINAL) {
                float scale = Math.min(((float) this.drawableScreenWidth) / this.bitmapWidth,
                        ((float) this.drawableScreenHeight) / this.bitmapHeight);
                this.viewWidth = (int) (this.bitmapWidth * scale);
                this.viewHeight = (int) (this.bitmapHeight * scale);
            }
            this.offsetX = Math.abs(this.drawableScreenWidth - this.viewWidth) / BACKGROUND_CASCADE;
            this.offsetY = (int) (SquareActivity.this.topOffset
                    + ((float) (Math.abs(this.drawableScreenHeight - this.viewHeight) / BACKGROUND_CASCADE)));
            float bitmapScale = Math.min(((float) this.viewWidth) / this.bitmapWidth,
                    ((float) this.viewHeight) / this.bitmapHeight);
            float bitmapOffsetX = ((float) this.offsetX)
                    + ((((float) this.viewWidth) - (this.bitmapWidth * bitmapScale)) / 2.0f);
            float bitmapOffsetY = ((float) this.offsetY)
                    + ((((float) this.viewHeight) - (this.bitmapHeight * bitmapScale)) / 2.0f);
            this.bitmapMatrix.reset();
            this.bitmapMatrix.postScale(bitmapScale, bitmapScale);
            this.bitmapMatrix.postTranslate(bitmapOffsetX, bitmapOffsetY);
            setPathPositions();
            if (!(this.blurBitmap == null || this.blurBitmap.isRecycled())) {
                setMatrixCenter(this.blurBitmapMatrix, (float) this.blurBitmap.getWidth(),
                        (float) this.blurBitmap.getHeight());
                setBlurRect2((float) this.blurBitmap.getWidth(), (float) this.blurBitmap.getHeight());
            }
            invalidate();
        }

        private void setPathPositions() {
            this.dashPathVertical.reset();
            this.dashPathHorizontal.reset();
            this.dashPathVertical.moveTo(this.bitmapWidth / 2.0f, (-this.bitmapHeight) / 5.0f);
            this.dashPathVertical.lineTo(this.bitmapWidth / 2.0f, (this.bitmapHeight * 6.0f) / 5.0f);
            this.dashPathHorizontal.moveTo((-this.bitmapWidth) / 5.0f, this.bitmapHeight / 2.0f);
            this.dashPathHorizontal.lineTo((this.bitmapWidth * 6.0f) / 5.0f, this.bitmapHeight / 2.0f);
        }

        public void onDraw(Canvas canvas) {
            this.cascadeOffsetX = (float) (this.viewWidth / SquareActivity.TAB_INDEX_SQUARE_FX);
            this.cascadeOffsetY = (float) (this.viewHeight / SquareActivity.TAB_INDEX_SQUARE_FX);
            if (this.backgroundMode == 0) {
                canvas.drawRect((float) this.offsetX, (float) this.offsetY, (float) (this.offsetX + this.viewWidth),
                        (float) (this.offsetY + this.viewHeight), this.patternPaint);
            }
            if (this.backgroundMode == BACKGROUND_CASCADE) {
                float localCascadeOffsetX = this.cascadeOffsetX / ((float) (this.cascadeNumber + VIEW_ORIGINAL));
                float loxalCascadeOffsetY = this.cascadeOffsetY / ((float) (this.cascadeNumber + VIEW_ORIGINAL));
                for (int j = BACKGROUND_PATTERN; j < this.cascadeNumber; j += VIEW_ORIGINAL) {
                    int offsetIndex = j + VIEW_ORIGINAL;
                    this.cascadeRectArray[j].set(((float) this.offsetX) + (((float) offsetIndex) * localCascadeOffsetX),
                            ((float) this.offsetY) + (((float) offsetIndex) * loxalCascadeOffsetY),
                            ((float) (this.offsetX + this.viewWidth)) - (((float) offsetIndex) * localCascadeOffsetX),
                            ((float) (this.offsetY + this.viewHeight)) - (((float) offsetIndex) * loxalCascadeOffsetY));
                }
            }
            if (!(this.blurBitmap == null || this.blurBitmap.isRecycled()
                    || (this.backgroundMode != VIEW_ORIGINAL && this.backgroundMode != BACKGROUND_CASCADE))) {
                canvas.drawBitmap(this.blurBitmap, this.blurBitmapMatrix, this.paint);
                if (this.backgroundMode == BACKGROUND_CASCADE) {
                    for (int i = BACKGROUND_PATTERN; i < this.cascadeNumber; i += VIEW_ORIGINAL) {
                        canvas.drawBitmap(this.blurBitmap, this.blurRectSrc, this.cascadeRectArray[i], this.paint);
                    }
                }
            }
            canvas.drawBitmap(SquareActivity.this.sourceBitmap, this.bitmapMatrix, this.paint);
            if (!(this.filterBitmap == null || this.filterBitmap.isRecycled())) {
                canvas.drawBitmap(this.filterBitmap, this.bitmapMatrix, this.paint);
            }
            if (this.isOrthogonal) {
                this.dashPathVertical.transform(this.bitmapMatrix, this.dashPathVerticalTemp);
                this.dashPathHorizontal.transform(this.bitmapMatrix, this.dashPathHorizontalTemp);
                canvas.drawPath(this.dashPathVerticalTemp, this.dashPaint);
                canvas.drawPath(this.dashPathHorizontalTemp, this.dashPaint);
            }
            canvas.drawRect(0.0f, 0.0f, (float) this.drawableScreenWidth, (float) this.offsetY, this.grayPaint);
            canvas.drawRect((float) this.offsetX, (float) (this.offsetY + this.viewHeight),
                    (float) this.drawableScreenWidth, (float) this.actualScreenHeight, this.grayPaint);
            canvas.drawRect(0.0f, 0.0f, (float) this.offsetX, (float) this.actualScreenHeight, this.grayPaint);
            canvas.drawRect((float) (this.offsetX + this.viewWidth), (float) this.offsetY,
                    (float) this.drawableScreenWidth, (float) this.actualScreenHeight, this.grayPaint);
        }

        @SuppressLint("WrongThread")
        private String saveBitmap() {
            int i;
            float max = (float) Math.max(this.viewHeight, this.viewWidth);
            float btmScale = ((float) Utility.maxSizeForSave(SquareActivity.this.context, 2048.0f)) / max;
            int newBtmWidth = (int) (((float) this.viewWidth) * btmScale);
            int newBtmHeight = (int) (((float) this.viewHeight) * btmScale);
            if (newBtmWidth <= 0) {
                newBtmWidth = this.viewWidth;
            }
            if (newBtmHeight <= 0) {
                newBtmHeight = this.viewHeight;
            }
            Bitmap savedBitmap = Bitmap.createBitmap(newBtmWidth, newBtmHeight, Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(savedBitmap);
            Matrix sizeMat = new Matrix();
            sizeMat.reset();
            sizeMat.postTranslate((float) (-this.offsetX), (float) (-this.offsetY));
            sizeMat.postScale(btmScale, btmScale);
            bitmapCanvas.setMatrix(sizeMat);
            if (this.backgroundMode == 0) {
                bitmapCanvas.drawRect((float) this.offsetX, (float) this.offsetY,
                        (float) (this.offsetX + this.viewWidth), (float) (this.offsetY + this.viewHeight),
                        this.patternPaint);
            }
            if (!(this.blurBitmap == null || this.blurBitmap.isRecycled()
                    || (this.backgroundMode != VIEW_ORIGINAL && this.backgroundMode != BACKGROUND_CASCADE))) {
                bitmapCanvas.drawBitmap(this.blurBitmap, this.blurBitmapMatrix, this.paint);
                if (this.backgroundMode == BACKGROUND_CASCADE) {
                    for (i = BACKGROUND_PATTERN; i < this.cascadeNumber; i += VIEW_ORIGINAL) {
                        bitmapCanvas.drawBitmap(this.blurBitmap, this.blurRectSrc, this.cascadeRectArray[i],
                                this.paint);
                    }
                }
            }
            bitmapCanvas.drawBitmap(SquareActivity.this.sourceBitmap, this.bitmapMatrix, this.paint);
            if (!(this.filterBitmap == null || this.filterBitmap.isRecycled())) {
                bitmapCanvas.drawBitmap(this.filterBitmap, this.bitmapMatrix, this.paint);
            }
            for (i = BACKGROUND_PATTERN; i < SquareActivity.this.textAndStickerViewContainer
                    .getChildCount(); i += VIEW_ORIGINAL) {
                Matrix mat = new Matrix();
                View v = SquareActivity.this.textAndStickerViewContainer.getChildAt(i);
                if (v instanceof StickerView) {
                    StickerView view = (StickerView) v;
                    StickerData data = view.getStickerData();
                    mat.set(data.getCanvasMatrix());
                    mat.postTranslate((float) (-this.offsetX), (float) (-this.offsetY));
                    mat.postScale(btmScale, btmScale);
                    bitmapCanvas.setMatrix(mat);
                    if (!(view.stickerBitmap == null || view.stickerBitmap.isRecycled())) {
                        bitmapCanvas.drawBitmap(view.stickerBitmap, data.xPos, data.yPos, view.paint);
                    }
                } else if (v instanceof CanvasTextView) {
                    TextData textData = ((CanvasTextView) v).getTextData();
                    if (!textData.getSnapMode()) {
                        mat.set(textData.getCanvasMatrix());
                    }
                    mat.postTranslate((float) (-this.offsetX), (float) (-this.offsetY));
                    mat.postScale(btmScale, btmScale);
                    bitmapCanvas.setMatrix(mat);
                    TextLibHelper.saveTextOnBitmap(bitmapCanvas, textData, this.drawableScreenWidth);
                }
            }
            String resultPath = Environment.getExternalStorageDirectory().toString()
                    + SquareActivity.this.getString(R.string.directory) + String.valueOf(System.currentTimeMillis())
                    + ".jpg";
            new File(resultPath).getParentFile().mkdirs();
            try {
                OutputStream out = new FileOutputStream(resultPath);
                savedBitmap.compress(CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            savedBitmap.recycle();
            return resultPath;
        }

        void setPatternPaint(int resId) {
            if (this.patternPaint == null) {
                this.patternPaint = new Paint(VIEW_ORIGINAL);
                this.patternPaint.setColor(PATTERN_SENTINEL);
            }
            if (resId == PATTERN_SENTINEL) {
                this.patternPaint.setShader(null);
                this.patternPaint.setColor(PATTERN_SENTINEL);
                postInvalidate();
                return;
            }
            this.patternBitmap = BitmapFactory.decodeResource(getResources(), resId);
            if (this.patternBitmap != null) {
                this.patternPaint.setShader(new BitmapShader(this.patternBitmap, TileMode.REPEAT, TileMode.REPEAT));
            }
            postInvalidate();
        }

        void setPatternPaint(int resId, Bitmap bitmap) {
            if (this.patternPaint == null) {
                this.patternPaint = new Paint(VIEW_ORIGINAL);
                this.patternPaint.setColor(PATTERN_SENTINEL);
            }
            if (resId == PATTERN_SENTINEL) {
                this.patternPaint.setShader(null);
                this.patternPaint.setColor(PATTERN_SENTINEL);
                postInvalidate();
            } else if (bitmap != null && !bitmap.isRecycled()) {
                this.patternBitmap = bitmap;
                this.patternPaint.setShader(new BitmapShader(this.patternBitmap, TileMode.REPEAT, TileMode.REPEAT));
                postInvalidate();
            }
        }

        void setPatternPaintColor(int color) {
            if (this.patternPaint == null) {
                this.patternPaint = new Paint(VIEW_ORIGINAL);
            }
            this.patternPaint.setShader(null);
            this.patternPaint.setColor(color);
            postInvalidate();
        }

        public void setBlurBitmap(int radius, boolean cascade) {
            if (this.blurBuilderNormal == null) {
                this.blurBuilderNormal = new BlurBuilderNormal();
            }
            if (cascade) {
                this.backgroundMode = BACKGROUND_CASCADE;
            } else {
                this.backgroundMode = VIEW_ORIGINAL;
            }
            if (SquareActivity.this.patternHelper == null || SquareActivity.this.patternHelper.bitmapBlur == null
                    || SquareActivity.this.patternHelper.bitmapBlur.isRecycled() || radius != 0) {
                if (SquareActivity.this.patternHelper == null || SquareActivity.this.patternHelper.bitmapBlur == null
                        || SquareActivity.this.patternHelper.bitmapBlur.isRecycled()) {
                    this.blurBitmap = this.blurBuilderNormal.createBlurBitmapNDK(SquareActivity.this.sourceBitmap,
                            radius);
                } else {
                    this.blurBitmap = this.blurBuilderNormal
                            .createBlurBitmapNDK(SquareActivity.this.patternHelper.bitmapBlur, radius);
                }
                this.blurRadius = this.blurBuilderNormal.getBlur();
            } else {
                this.blurBitmap = SquareActivity.this.patternHelper.bitmapBlur;
                this.blurRadius = BACKGROUND_PATTERN;
            }
            setMatrixCenter(this.blurBitmapMatrix, (float) this.blurBitmap.getWidth(),
                    (float) this.blurBitmap.getHeight());
            setBlurRect2((float) this.blurBitmap.getWidth(), (float) this.blurBitmap.getHeight());
            postInvalidate();
        }

        void setBlurRect2(float btmwidth, float btmheight) {
            if (this.viewSizeMode == 0) {
                float min = Math.min(btmwidth, btmheight);
                int l = (int) ((btmwidth - min) / 2.0f);
                int t = (int) ((btmheight - min) / 2.0f);
                this.blurRectSrc.set(l, t, (int) (((float) l) + min), (int) (((float) t) + min));
                return;
            }
            int r;
            int b;
            float s = this.bitmapWidth / this.bitmapHeight;
            int l;
            int t;
            if (s < btmwidth / btmheight) {
                float w = s * btmheight;
                l = (int) ((btmwidth - w) / 2.0f);
                t = BACKGROUND_PATTERN;
                r = (int) (((float) l) + w);
                b = (int) btmheight;
            } else {
                float h = btmwidth / s;
                l = BACKGROUND_PATTERN;
                t = (int) ((btmheight - h) / 2.0f);
                r = (int) btmwidth;
                b = (int) (((float) t) + h);
            }
            this.blurRectSrc.set(l, t, r, b);
        }

        public void setCropBitmap(int left, int top, int right, int bottom) {
            Bitmap localCropBtm = SquareActivity.this.sourceBitmap;
            if (((float) right) > this.bitmapWidth) {
                right = (int) this.bitmapWidth;
            }
            if (((float) bottom) > this.bitmapHeight) {
                bottom = (int) this.bitmapHeight;
            }
            if (right - left > 0 && bottom - top > 0) {
                if (VERSION.SDK_INT < 12) {
                    SquareActivity.this.sourceBitmap = BlurBuilderNormal.createCroppedBitmap(localCropBtm, left, top,
                            right - left, bottom - top, false);
                } else {
                    SquareActivity.this.sourceBitmap = Bitmap.createBitmap(localCropBtm, left, top, right - left,
                            bottom - top);
                }
                if (localCropBtm != SquareActivity.this.sourceBitmap) {
                    localCropBtm.recycle();
                }
                this.bitmapHeight = (float) SquareActivity.this.sourceBitmap.getHeight();
                this.bitmapWidth = (float) SquareActivity.this.sourceBitmap.getWidth();
                this.rectBitmap.set(0.0f, 0.0f, this.bitmapWidth, this.bitmapHeight);
                setViewSize();
                setPathPositions();
                setScaleMatrix(BACKGROUND_PATTERN);
            }
        }

        public void setScaleMatrix(int mode) {
            PointF centerOfImage = getCenterOfImage();
            if (mode == 0) {
                this.bitmapMatrix.reset();
                float bitmapScale = Math.min(((float) this.viewWidth) / this.bitmapWidth,
                        ((float) this.viewHeight) / this.bitmapHeight);
                float bitmapOffsetX = ((float) this.offsetX)
                        + ((((float) this.viewWidth) - (this.bitmapWidth * bitmapScale)) / 2.0f);
                float bitmapOffsetY = ((float) this.offsetY)
                        + ((((float) this.viewHeight) - (this.bitmapHeight * bitmapScale)) / 2.0f);
                this.bitmapMatrix.postScale(bitmapScale, bitmapScale);
                this.bitmapMatrix.postTranslate(bitmapOffsetX, bitmapOffsetY);
            } else if (mode == VIEW_ORIGINAL) {
                setMatrixCenter(this.bitmapMatrix, this.bitmapWidth, this.bitmapHeight);
            } else if (mode == SquareActivity.TAB_INDEX_SQUARE_FRAME) {
                this.bitmapMatrix.postRotate(-90.0f, centerOfImage.x, centerOfImage.y);
            } else if (mode == BACKGROUND_CASCADE) {
                this.bitmapMatrix.postRotate(90.0f, centerOfImage.x, centerOfImage.y);
            } else if (mode == SquareActivity.TAB_INDEX_SQUARE_FX) {
                this.bitmapMatrix.postScale(-1.0f, 1.0f, centerOfImage.x, centerOfImage.y);
            } else if (mode == SquareActivity.TAB_INDEX_SQUARE_ADJ) {
                this.bitmapMatrix.postScale(1.0f, -1.0f, centerOfImage.x, centerOfImage.y);
            }
            SquareActivity.this.checkDecoareteViewPositions(this.bitmapMatrix);
            postInvalidate();
        }

        void setMatrixCenter(Matrix matrix, float btmwidth, float btmheight) {
            matrix.reset();
            float bitmapScale = Math.max(((float) this.viewWidth) / btmwidth, ((float) this.viewHeight) / btmheight);
            float bitmapOffsetX = ((float) this.offsetX)
                    + ((((float) this.viewWidth) - (bitmapScale * btmwidth)) / 2.0f);
            float bitmapOffsetY = ((float) this.offsetY)
                    + ((((float) this.viewHeight) - (bitmapScale * btmheight)) / 2.0f);
            matrix.postScale(bitmapScale, bitmapScale);
            matrix.postTranslate(bitmapOffsetX, bitmapOffsetY);
        }

        PointF getCenterOfImage() {
            if (this.centerOriginal == null) {
                this.centerOriginal = new PointF();
            }
            if (this.f531f == null) {
                this.f531f = new float[BACKGROUND_CASCADE];
            }
            float y = this.bitmapHeight / 2.0f;
            this.f531f[BACKGROUND_PATTERN] = this.bitmapWidth / 2.0f;
            this.f531f[VIEW_ORIGINAL] = y;
            this.bitmapMatrix.mapPoints(this.f531f);
            this.centerOriginal.set(this.f531f[BACKGROUND_PATTERN], this.f531f[VIEW_ORIGINAL]);
            return this.centerOriginal;
        }

        public boolean isOnRect(float x, float y) {
            this.rectBitmap.set(0.0f, 0.0f, this.bitmapWidth, this.bitmapHeight);
            this.pts[BACKGROUND_PATTERN] = x;
            this.pts[VIEW_ORIGINAL] = y;
            this.bitmapMatrix.mapRect(this.rectBitmap);
            if (this.rectBitmap.contains(x, y)) {
                return true;
            }
            return false;
        }

        public boolean onTouchEvent(MotionEvent ev) {
            int newPointerIndex = BACKGROUND_PATTERN;
            SquareActivity.this.mScaleDetector.onTouchEvent(ev);
            this.mRotationDetector.onTouchEvent(ev);
            int action = ev.getAction();
            float x;
            float y;
            int pointerIndex;
            switch (action & TextData.defBgAlpha) {
                case BACKGROUND_PATTERN /* 0 */:
                    x = ev.getX();
                    y = ev.getY();
                    if (isOnRect(x, y)) {
                        this.mLastTouchX = x;
                        this.mLastTouchY = y;
                        this.mActivePointerId = ev.getPointerId(BACKGROUND_PATTERN);
                        break;
                    }
                    return false;
                case VIEW_ORIGINAL /* 1 */:
                    this.isOrthogonal = false;
                    this.mActivePointerId = PATTERN_SENTINEL;
                    break;
                case BACKGROUND_CASCADE /* 2 */:
                    pointerIndex = ev.findPointerIndex(this.mActivePointerId);
                    x = ev.getX(pointerIndex);
                    y = ev.getY(pointerIndex);
                    this.bitmapMatrix.postTranslate(x - this.mLastTouchX, y - this.mLastTouchY);
                    this.mLastTouchX = x;
                    this.mLastTouchY = y;
                    break;
                case SquareActivity.TAB_INDEX_SQUARE_FRAME /* 3 */:
                    this.isOrthogonal = false;
                    this.mActivePointerId = PATTERN_SENTINEL;
                    break;
                case SquareActivity.TAB_INDEX_SQUARE_CASCADE /* 6 */:
                    this.isOrthogonal = false;
                    this.finalAngle = 0.0f;
                    pointerIndex = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & action) >> 8;
                    if (ev.getPointerId(pointerIndex) == this.mActivePointerId) {
                        if (pointerIndex == 0) {
                            newPointerIndex = VIEW_ORIGINAL;
                        }
                        this.mLastTouchX = ev.getX(newPointerIndex);
                        this.mLastTouchY = ev.getY(newPointerIndex);
                        this.mActivePointerId = ev.getPointerId(newPointerIndex);
                        break;
                    }
                    break;
            }
            SquareActivity.this.checkDecoareteViewPositions(this.bitmapMatrix);
            postInvalidate();
            return true;
        }

        float getMatrixRotation(Matrix matrix) {
            matrix.getValues(this.values);
            return (float) Math
                    .round(Math.atan2((double) this.values[VIEW_ORIGINAL], (double) this.values[BACKGROUND_PATTERN])
                            * 57.29577951308232d);
        }
    }

    /* renamed from: com.lyrebirdstudio.instasquare.lib.SquareActivity.1 */
    class C13161 implements PatternHelper.PatternBitmapColorListener {
        C13161() {
        }

        public void patternBitmapReady(int index, Bitmap bitmap) {
            SquareActivity.this.mSqView.setPatternPaint(index, bitmap);
        }

        public void patternColorReady(int color) {
            SquareActivity.this.mSqView.setPatternPaintColor(color);
        }

        public void setBacgkroundMode() {
            SquareView squareView = SquareActivity.this.mSqView;
            squareView.backgroundMode = SquareActivity.TAB_INDEX_SQUARE;
        }

        public void patternImageReady(Bitmap bitmap) {
            if (bitmap != null) {
                if (!(SquareActivity.this.mSqView == null || SquareActivity.this.mSqView.blurBitmap == null)) {
                    SquareActivity.this.mSqView.blurBitmap.recycle();
                }
                if (SquareActivity.this.mSqView != null) {
                    SquareActivity.this.mSqView.setBlurBitmap(SquareActivity.this.mSqView.blurRadius, false);
                    SquareView squareView = SquareActivity.this.mSqView;
                    squareView.backgroundMode = SquareActivity.TAB_INDEX_SQUARE_BACKGROUND;
                    SquareActivity.this.mSqView.invalidate();
                }
            }
        }
    }

    /* renamed from: com.lyrebirdstudio.instasquare.lib.SquareActivity.6 */
    class C13186 implements MyAdapter.CurrentSquareIndexChangedListener {
        C13186() {
        }

        public void onIndexChanged(int color) {
            SquareActivity.this.mSqView.setPatternPaintColor(color);
        }
    }

    /* renamed from: com.lyrebirdstudio.instasquare.lib.SquareActivity.7 */
    class C13197 implements MyAdapter.CurrentSquareIndexChangedListener {
        C13197() {
        }

        public void onIndexChanged(int positionOrColor) {
            SquareActivity.this.mSqView.setPatternPaint(positionOrColor);
        }
    }


    private class SaveImageTask extends MyAsyncTask2<Object, Object, Object> {
        ProgressDialog progressDialog;
        String resultPath;
        int saveMode;

        private SaveImageTask() {
            this.saveMode = SquareActivity.TAB_INDEX_SQUARE;
            this.resultPath = null;
        }

        protected Object doInBackground(Object... arg0) {
            if (arg0 != null) {
                this.saveMode = ((Integer) arg0[SquareActivity.TAB_INDEX_SQUARE]).intValue();
            }
            this.resultPath = SquareActivity.this.mSqView.saveBitmap();
            return null;
        }

        protected void onPreExecute() {
            this.progressDialog = new ProgressDialog(SquareActivity.this.context);
            this.progressDialog.setMessage(SquareActivity.this.getString(R.string.save_image_lib_saving_message));
            this.progressDialog.show();
        }

        protected void onPostExecute(Object result) {
            try {
                if (this.progressDialog != null && this.progressDialog.isShowing()) {
                    this.progressDialog.cancel();
                }
            } catch (Exception e) {
            }
            Utility.logFreeMemory(SquareActivity.this.context);
            Toast msg;
            if (this.saveMode == SquareActivity.this.SAVE_FINISH) {
                super.onPostExecute(result);
                Context context = SquareActivity.this.context;
                String string = SquareActivity.this.getString(R.string.save_image_lib_image_saved_message);
                Object[] objArr = new Object[SquareActivity.TAB_INDEX_SQUARE_BACKGROUND];
                objArr[SquareActivity.TAB_INDEX_SQUARE] = SquareActivity.this.getString(R.string.directory);
                msg = Toast.makeText(context, String.format(string, objArr),
                        Toast.LENGTH_LONG);
                msg.setGravity(17, msg.getXOffset() / SquareActivity.TAB_INDEX_SQUARE_BLUR,
                        msg.getYOffset() / SquareActivity.TAB_INDEX_SQUARE_BLUR);
                msg.show();
                SquareActivity.this.finish();
            } else if (this.saveMode == SquareActivity.this.SAVE_INSTAGRAM) {
                try {
                    Intent instagram = new Intent("android.intent.action.SEND");
                    instagram.setType("image/*");
                    instagram.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(this.resultPath)));
                    instagram.putExtra("android.intent.extra.TEXT",
                            SquareActivity.this.getString(R.string.hashtag_twitter) + " ");
                    instagram.setPackage("com.instagram.android");
                    SquareActivity.this.startActivityForResult(instagram, SquareActivity.INSTAGRAM_ID);
                } catch (Exception e2) {
                    msg = Toast.makeText(SquareActivity.this.context,
                            SquareActivity.this.getString(R.string.no_instagram_app),
                            Toast.LENGTH_LONG);
                    msg.setGravity(17, msg.getXOffset() / SquareActivity.TAB_INDEX_SQUARE_BLUR,
                            msg.getYOffset() / SquareActivity.TAB_INDEX_SQUARE_BLUR);
                    msg.show();
                    SquareActivity.this.saveNormal(this.resultPath);
                }
            } else if (this.saveMode == SquareActivity.this.SAVE_NORMAL) {
                SquareActivity.this.saveNormal(this.resultPath);
            }
        }
    }

    public SquareActivity() {
        this.activity = this;
        this.context = this;
        this.selectImageForAdj = false;
        this.patternAdapterList = new ArrayList();
        this.activityFragment = this;
        this.textFragemntContinerId = R.id.sticker_grid_fragment_container;
        this.stickerFragemntContinerId = R.id.sticker_grid_fragment_container;
        this.mSeekBarListener = new C07455();
        this.currentSelectedTabIndex = TAB_INDEX_SQUARE;
        this.SAVE_NORMAL = TAB_INDEX_SQUARE_FRAME;
        this.SAVE_FINISH = TAB_INDEX_SQUARE_FX;
        this.SAVE_INSTAGRAM = TAB_INDEX_SQUARE_ADJ;
        this.cropListener = new FragmentCrop.CropListener() {
            public void cropCancelled() {
                SquareActivity.this.getSupportFragmentManager().beginTransaction()
                        .remove(SquareActivity.this.cropFragment).commit();
            }

            public void cropApply(int leftPos, int topPos, int rightPos, int bottomPos) {
                SquareActivity.this.mSqView.setCropBitmap(leftPos, topPos, rightPos, bottomPos);
                SquareActivity.this.cropFragment.setBitmap(null);
                SquareActivity.this.getSupportFragmentManager().beginTransaction()
                        .remove(SquareActivity.this.cropFragment).commit();
            }
        };
    }

    static {
        TAG = SquareActivity.class.getSimpleName();
    }

    void patternHelperInit() {
        if (this.recyclerViewInnerPattern == null) {
            this.recyclerViewInnerPattern = (RecyclerView) findViewById(R.id.recyclerView_color);
            LinearLayoutManager layoutManagerColor = new LinearLayoutManager(this.context);
            layoutManagerColor.setOrientation(TAB_INDEX_SQUARE);
            this.recyclerViewInnerPattern.setLayoutManager(layoutManagerColor);
        }
        this.patternHelper = new PatternHelper(this, new C13161(), this.colorContainer, this.recyclerViewInnerPattern,
                this.colorDefault, this.colorSelected);
        this.patternHelper.createPatternAdapter(this, this.colorDefault, this.colorSelected);
    }

    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(TAB_INDEX_SQUARE_BACKGROUND);
        getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        Bundle extras = getIntent().getExtras();
        this.sourceBitmap = ImageUtility.decodeBitmapFromFile(extras.getString("selectedImagePath"),
                extras.getInt("MAX_SIZE"));
        if (this.sourceBitmap == null) {
            Toast msg = Toast.makeText(this.context, R.string.square_lib_load_error, Toast.LENGTH_LONG);
            msg.setGravity(17, msg.getXOffset() / TAB_INDEX_SQUARE_BLUR, msg.getYOffset() / TAB_INDEX_SQUARE_BLUR);
            msg.show();
            finish();
            return;
        }
        this.colorDefault = getResources().getColor(R.color.view_flipper_bg_color);
        this.colorSelected = getResources().getColor(R.color.footer_button_color_pressed);
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        this.topOffset = metrics.density * 92.0f;
        this.totalOffset = metrics.density * 140.0f;
        setContentView(R.layout.activity_square);

        this.mainLayout = (RelativeLayout) findViewById(R.id.nocrop_main_layout);
        this.mSqView = new SquareView(this, width, height);
        this.mainLayout.addView(this.mSqView);
        @SuppressWarnings("unused")
        RecyclerView recyclerViewColor = (RecyclerView) findViewById(R.id.recyclerView_color);
        int colorDefault = getResources().getColor(R.color.view_flipper_bg_color);
        int colorSelected = getResources().getColor(R.color.footer_button_color_pressed);
        new LinearLayoutManager(this.context).setOrientation(TAB_INDEX_SQUARE);
        this.viewFlipper = (ViewFlipper) findViewById(R.id.square_view_flipper);
        this.viewFlipper.setDisplayedChild(TAB_INDEX_SQUARE_ADJ);
        //createAdapterList(colorDefault, colorSelected);
        this.textAndStickerViewContainer = (FrameLayout) findViewById(R.id.sticker_view_container);
        this.textAndStickerViewContainer.bringToFront();
        this.textAndStickerViewContainer.setOnHierarchyChangeListener(new C07422());
        RecyclerView recyclerViewPattern = (RecyclerView) findViewById(R.id.recyclerView_pattern);
        LinearLayoutManager layoutManagerPattern = new LinearLayoutManager(this.context);
        layoutManagerPattern.setOrientation(TAB_INDEX_SQUARE);
        this.colorContainer = (LinearLayout) findViewById(R.id.color_container);
        recyclerViewPattern.setLayoutManager(layoutManagerPattern);
        patternHelperInit();
        recyclerViewPattern.setAdapter(this.patternHelper.patternAdapter);
        recyclerViewPattern.setItemAnimator(new DefaultItemAnimator());
        this.recyclerViewInnerPattern = (RecyclerView) findViewById(R.id.recyclerView_color);
        LinearLayoutManager layoutManagerColor = new LinearLayoutManager(this.context);
        layoutManagerColor.setOrientation(TAB_INDEX_SQUARE);
        this.recyclerViewInnerPattern.setLayoutManager(layoutManagerColor);
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(R.id.square_footer);
        horizontalScrollView.bringToFront();
        horizontalScrollView.postDelayed(new C07433(horizontalScrollView), 350);
        horizontalScrollView.postDelayed(new C07444(horizontalScrollView), 1350);
        this.slideLeftIn = AnimationUtils.loadAnimation(this.activity, R.anim.slide_in_left);
        this.slideLeftOut = AnimationUtils.loadAnimation(this.activity, R.anim.slide_out_left);
        this.slideRightIn = AnimationUtils.loadAnimation(this.activity, R.anim.slide_in_right);
        this.slideRightOut = AnimationUtils.loadAnimation(this.activity, R.anim.slide_out_right);
        this.viewFlipper = (ViewFlipper) findViewById(R.id.square_view_flipper);
        this.viewFlipper.bringToFront();
        this.blurText = (TextView) findViewById(R.id.square_blur_text_view);
        this.seekBarBlur = (SeekBar) findViewById(R.id.nocrop_blur_seek_bar);
        this.seekBarBlur.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekbarCascadeBlur = (SeekBar) findViewById(R.id.seekbar_square_cascade_blur);
        this.seekbarCascadeBlur.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekbarCascadeNumber = (SeekBar) findViewById(R.id.seekbar_square_cascade_number);
        this.seekbarCascadeNumber.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.textAndStickerViewContainer.bringToFront();
        findViewById(R.id.square_header).bringToFront();
        horizontalScrollView.bringToFront();
        this.viewFlipper.bringToFront();
        findViewById(this.textFragemntContinerId).bringToFront();
        this.textLibHelper = new TextLibHelper();
        findViewById(this.stickerFragemntContinerId).bringToFront();
        this.stickerLibHelper = new StickerLibHelper();
        if (savedInstanceState != null) {
            if (this.textLibHelper != null) {
                this.textLibHelper.hideForOncreate(this.activityFragment, this.textAndStickerViewContainer,
                        this.textFragemntContinerId);
            }
            if (this.stickerLibHelper != null) {
                this.stickerLibHelper.hideForOncreate(this.activityFragment, this.textAndStickerViewContainer);
            }
        }
        if (this.mSqView != null) {
            this.mSqView.setBlurBitmap(this.mSqView.blurRadius, false);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PatternHelper.SELCT_IMAGE_BG && resultCode == -1) {
            if (this.patternHelper == null) {
                patternHelperInit();
            }
            if (this.patternHelper != null) {
                this.patternHelper.activityResult(resultCode, data);
                setSelectedTab(TAB_INDEX_SQUARE_BLUR);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.sourceBitmap != null) {
            this.sourceBitmap.recycle();
        }
        if (this.mSqView != null) {
            if (this.mSqView.filterBitmap != null) {
                this.mSqView.filterBitmap.recycle();
            }
            if (this.mSqView.blurBitmap != null) {
                this.mSqView.blurBitmap.recycle();
            }
        }
        if (!(this.patternHelper == null || this.patternHelper.bitmapBlur == null)) {
            this.patternHelper.bitmapBlur.recycle();
        }
        if (this.mSqView != null && this.mSqView.blurBuilderNormal != null) {
            this.mSqView.blurBuilderNormal.destroy();
        }
    }

//	private void createAdapterList(final int n, final int n2) {
//		final int length = Utility.patternResIdList2.length;
//		this.patternAdapterList.clear();
//		this.patternAdapterList.add(new ColorPickerAdapter(new MyAdapter.CurrentSquareIndexChangedListener() {
//			@Override
//			public void onIndexChanged(final int patternPaintColor) {
//				SquareActivity.this.mSqView.setPatternPaintColor(patternPaintColor);
//			}
//		}, n, n2));
//		for (int i = 0; i < length; ++i) {
//			this.patternAdapterList.add(new MyAdapter(Utility.patternResIdList2[i],
//					(MyAdapter.CurrentSquareIndexChangedListener) new MyAdapter.CurrentSquareIndexChangedListener() {
//						@Override
//						public void onIndexChanged(final int patternPaint) {
//							SquareActivity.this.mSqView.setPatternPaint(patternPaint);
//						}
//					}, n, n2, true, true));
//		}
//	}

    void setSelectedTab(int index) {
        if (this.viewFlipper != null) {
            setTabBg(TAB_INDEX_SQUARE);
            int displayedChild = this.viewFlipper.getDisplayedChild();
            if (displayedChild != TAB_INDEX_SQUARE_BACKGROUND) {
                hideColorContainer();
            }
            if (index == 0) {
                if (displayedChild != 0) {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                    this.viewFlipper.setDisplayedChild(TAB_INDEX_SQUARE);
                } else {
                    return;
                }
            }
            if (index == TAB_INDEX_SQUARE_BACKGROUND) {
                setTabBg(TAB_INDEX_SQUARE_BACKGROUND);
                if (displayedChild != TAB_INDEX_SQUARE_BACKGROUND) {
                    if (displayedChild == 0) {
                        this.viewFlipper.setInAnimation(this.slideRightIn);
                        this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    } else {
                        this.viewFlipper.setInAnimation(this.slideLeftIn);
                        this.viewFlipper.setOutAnimation(this.slideRightOut);
                    }
                    this.viewFlipper.setDisplayedChild(TAB_INDEX_SQUARE_BACKGROUND);
                } else {
                    return;
                }
            }
            if (index == TAB_INDEX_SQUARE_BLUR) {
                setTabBg(TAB_INDEX_SQUARE_BLUR);
                if (displayedChild != TAB_INDEX_SQUARE_BLUR) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    this.viewFlipper.setDisplayedChild(TAB_INDEX_SQUARE_BLUR);
                } else {
                    return;
                }
            }
            if (index == TAB_INDEX_SQUARE_CASCADE) {
                setTabBg(TAB_INDEX_SQUARE_FX);
                if (displayedChild != TAB_INDEX_SQUARE_FRAME) {
                    if (displayedChild == TAB_INDEX_SQUARE_BLUR || displayedChild == TAB_INDEX_SQUARE_BACKGROUND) {
                        this.viewFlipper.setInAnimation(this.slideRightIn);
                        this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    } else {
                        this.viewFlipper.setInAnimation(this.slideLeftIn);
                        this.viewFlipper.setOutAnimation(this.slideRightOut);
                    }
                    this.viewFlipper.setDisplayedChild(TAB_INDEX_SQUARE_FRAME);
                } else {
                    return;
                }
            }
            if (index == TAB_SIZE) {
                setTabBg(TAB_INDEX_SQUARE_FRAME);
                if (displayedChild != TAB_INDEX_SQUARE_FRAME) {
                    if (displayedChild == TAB_INDEX_SQUARE_BLUR) {
                        this.viewFlipper.setInAnimation(this.slideLeftIn);
                        this.viewFlipper.setOutAnimation(this.slideRightOut);
                    } else {
                        this.viewFlipper.setInAnimation(this.slideRightIn);
                        this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    }
                    this.viewFlipper.setDisplayedChild(TAB_INDEX_SQUARE_FRAME);
                } else {
                    return;
                }
            }
            if (index == TAB_INDEX_SQUARE_FRAME) {
                setTabBg(TAB_INDEX_SQUARE_ADJ);
                if (displayedChild != TAB_INDEX_SQUARE_FRAME) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    this.viewFlipper.setDisplayedChild(TAB_INDEX_SQUARE_FRAME);
                } else {
                    return;
                }
            }
            if (index == TAB_INDEX_SQUARE_FX) {
                setTabBg(TAB_INDEX_SQUARE_CASCADE);
                if (displayedChild != TAB_INDEX_SQUARE_FX) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    this.viewFlipper.setDisplayedChild(TAB_INDEX_SQUARE_FX);
                } else {
                    return;
                }
            }
            if (index == TAB_INDEX_SQUARE_ADJ) {
                setTabBg(-1);
                if (displayedChild != TAB_INDEX_SQUARE_ADJ) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    this.viewFlipper.setDisplayedChild(TAB_INDEX_SQUARE_ADJ);
                }
            }
        }
    }

    private void setTabBg(int index) {
        this.currentSelectedTabIndex = index;
        if (this.tabButtonList == null) {
            this.tabButtonList = new View[TAB_SIZE];
            this.tabButtonList[TAB_INDEX_SQUARE] = findViewById(R.id.button_square_layout);
            this.tabButtonList[TAB_INDEX_SQUARE_BACKGROUND] = findViewById(R.id.button_square_background);
            this.tabButtonList[TAB_INDEX_SQUARE_BLUR] = findViewById(R.id.button_square_blur);
            this.tabButtonList[TAB_INDEX_SQUARE_FRAME] = findViewById(R.id.button_square_frame);
            this.tabButtonList[TAB_INDEX_SQUARE_FX] = findViewById(R.id.button_square_fx);
            this.tabButtonList[TAB_INDEX_SQUARE_ADJ] = findViewById(R.id.button_square_adj);
            this.tabButtonList[TAB_INDEX_SQUARE_CASCADE] = findViewById(R.id.button_square_cascade);
        }
        for (int i = TAB_INDEX_SQUARE; i < this.tabButtonList.length; i += TAB_INDEX_SQUARE_BACKGROUND) {
            this.tabButtonList[i].setBackgroundResource(R.drawable.square_footer_button);
        }
        if (index >= 0) {
            this.tabButtonList[index].setBackgroundResource(R.color.footer_button_color_pressed);
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (this.textLibHelper == null) {
            this.textLibHelper = new TextLibHelper();
        }
        if (this.textLibHelper != null) {
            this.textLibHelper.saveTextAndStickerDataInstance(savedInstanceState, this.textAndStickerViewContainer,
                    null);
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (this.textLibHelper == null) {
            this.textLibHelper = new TextLibHelper();
        }
        if (this.stickerLibHelper == null) {
            this.stickerLibHelper = new StickerLibHelper();
        }
        if (this.textAndStickerViewContainer == null) {
            this.textAndStickerViewContainer = (FrameLayout) findViewById(R.id.sticker_view_container);
            this.textAndStickerViewContainer.bringToFront();
            View header = findViewById(R.id.square_header);
            if (header != null) {
                header.bringToFront();
            }
            HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(R.id.square_footer);
            if (horizontalScrollView != null) {
                horizontalScrollView.bringToFront();
            }
            if (this.viewFlipper == null) {
                this.viewFlipper = (ViewFlipper) findViewById(R.id.square_view_flipper);
            }
            if (this.viewFlipper != null) {
                this.viewFlipper.bringToFront();
            }
        }
        if (this.textLibHelper != null) {
            this.textLibHelper.loadTextAndStickerDataFromSavedInstance(this.activityFragment, savedInstanceState,
                    this.textAndStickerViewContainer, this.textFragemntContinerId, null);
        }
    }

    void clearViewFlipper() {
        this.viewFlipper.setDisplayedChild(TAB_INDEX_SQUARE_ADJ);
        setTabBg(-1);
    }

    private void hideColorContainer() {
        if (this.colorContainer == null) {
            this.colorContainer = (LinearLayout) findViewById(R.id.color_container);
        }
        this.colorContainer.setVisibility(View.INVISIBLE);
    }


    public void onBackPressed() {
        if (this.stickerLibHelper == null) {
            this.stickerLibHelper = new StickerLibHelper();
        }
        if (this.textLibHelper == null) {
            this.textLibHelper = new TextLibHelper();
        }
        if (this.cropFragment != null && this.cropFragment.isVisible()) {
            this.cropFragment.onBackPressed();
        } else if (!PatternHelper.onBackPressed(this)
                && !this.textLibHelper.removeOnBackPressed(this.activityFragment)) {
            if (this.textLibHelper != null && this.textLibHelper.hideOnBackPressed(this.activityFragment)) {
                return;
            }
            if (this.stickerLibHelper != null ) {
                return;
            }
            if (this.textAndStickerViewContainer != null
                    && TextLibHelper.onBackPressedForDecorateViewSelection(this.textAndStickerViewContainer)) {
                return;
            }
            if (this.viewFlipper.getDisplayedChild() == TAB_INDEX_SQUARE_FRAME) {
                clearFxAndFrame();

            } else if (this.colorContainer.getVisibility() == View.VISIBLE) {
                hideColorContainer();
            } else if (this.selectImageForAdj) {
                this.selectFilterTextView.setVisibility(View.INVISIBLE);
                this.selectImageForAdj = false;
            } else if (this.viewFlipper == null || this.viewFlipper.getDisplayedChild() == TAB_INDEX_SQUARE_ADJ) {
                backButtonAlertBuilder();
            } else {
                setSelectedTab(TAB_INDEX_SQUARE_ADJ);
            }
        }
    }

    private void backButtonAlertBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setMessage(R.string.square_lib_save_message).setCancelable(true)
                .setPositiveButton("Yes", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SaveImageTask saveImageTask = new SaveImageTask();
                        Object[] objArr = new Object[SquareActivity.TAB_INDEX_SQUARE_BACKGROUND];
                        objArr[SquareActivity.TAB_INDEX_SQUARE] = Integer.valueOf(SquareActivity.this.SAVE_FINISH);
                        saveImageTask.execute(objArr);
                    }
                }).setNegativeButton("Cancle", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        }).setNeutralButton(R.string.square_lib_save_no, new C07469());
        this.saveImageAlert = builder.create();
        this.saveImageAlert.show();
    }

    void saveNormal(String resultPath) {
        boolean z = true;
        Intent fbIntent = new Intent(this.context, MainActivity.class);
        Utility.opengalleryfrommainpage = false;
        if (resultPath != null) {
            fbIntent.putExtra("imagePath", resultPath);
//			fbIntent.putExtra("urlFacebookLike", getString(R.string.facebook_like_url));
//			fbIntent.putExtra("proVersionUrl", getString(R.string.pro_package));
//			fbIntent.putExtra("folder", getString(R.string.directory));
//			fbIntent.putExtra("twitter_message", getString(R.string.hashtag_twitter) + " ");
            startActivityForResult(fbIntent, SAVE_IMAGE_ID);
        }
    }

//	public void myClickHandler(View view) {
//		int id = view.getId();
//		if (id == R.id.nocrop_fit) {
//			this.mSqView.setScaleMatrix(TAB_INDEX_SQUARE);
//		} else if (id == R.id.nocrop_center) {
//			this.mSqView.setScaleMatrix(TAB_INDEX_SQUARE_BACKGROUND);
//		} else if (id == R.id.button_straighten_rotate_left) {
//			this.mSqView.setScaleMatrix(TAB_INDEX_SQUARE_FRAME);
//		} else if (id == R.id.button_straighten_rotate_right) {
//			this.mSqView.setScaleMatrix(TAB_INDEX_SQUARE_BLUR);
//		} else if (id == R.id.button_straighten_flip_horizontal) {
//			this.mSqView.setScaleMatrix(TAB_INDEX_SQUARE_FX);
//		} else if (id == R.id.button_straighten_flip_vertical) {
//			this.mSqView.setScaleMatrix(TAB_INDEX_SQUARE_ADJ);
//		} else if (id == R.id.button_crop) {
//			addCropFragment();
//		} else if (id == R.id.button_square_blur) {
//			this.mSqView.setBlurBitmap(this.mSqView.blurRadius, false);
//			setSelectedTab(TAB_INDEX_SQUARE_BLUR);
//		} else if (id == R.id.button_square_cascade) {
//			this.mSqView.setBlurBitmap(this.mSqView.blurRadius, true);
//			setSelectedTab(TAB_INDEX_SQUARE_FX);
//		} else if (id == R.id.button_save_square_image) {
//			SaveImageTask saveImageTask = new SaveImageTask();
//			Object[] objArr = new Object[TAB_INDEX_SQUARE_BACKGROUND];
//			objArr[TAB_INDEX_SQUARE] = Integer.valueOf(this.SAVE_NORMAL);
//			saveImageTask.execute(objArr);
//		} else if (id == R.id.button_cancel_square_image) {
//			backButtonAlertBuilder();
//		} else if (id == R.id.hide_color_container) {
//			hideColorContainer();
//		} else if (id == R.id.button_mirror_text) {
//			setSelectedTab(TAB_INDEX_SQUARE_ADJ);
//			if (this.textLibHelper != null) {
//				this.textLibHelper.addCanvasText2(this.activityFragment, this.textAndStickerViewContainer,
//						this.textFragemntContinerId);
//			}
//			clearViewFlipper();
//		} else if (id == R.id.button_mirror_sticker) {
//			setSelectedTab(TAB_INDEX_SQUARE_ADJ);
//			this.stickerLibHelper.addStickerGalleryFragment(this.activityFragment, this.textAndStickerViewContainer,
//					this.stickerFragemntContinerId);
//		} else if (id == R.id.button_square_background) {
//			setSelectedTab(TAB_INDEX_SQUARE_BACKGROUND);
//		}
//		if (id == R.id.button_square_layout) {
//			setSelectedTab(TAB_INDEX_SQUARE);
//		} else if (id == R.id.button_square_background) {
//			setSelectedTab(TAB_INDEX_SQUARE_BACKGROUND);
//		} else if (id == R.id.button_square_adj) {
//			setSelectedTab(TAB_INDEX_SQUARE_FRAME);
//		} else if (id == R.id.button_square_fx) {
//			setSelectedTab(TAB_INDEX_SQUARE_CASCADE);
//		} else if (id == R.id.button_square_frame) {
//			setSelectedTab(TAB_SIZE);
//		} else if (id == R.id.button_square_screen_mode) {
//			String msg;
//			this.mSqView.changeViewSizeMode();
//			if (this.originalInstagram == null) {
//				this.originalInstagram = (Button) findViewById(R.id.button_square_screen_mode);
//			}
//			if (this.mSqView.viewSizeMode == 0) {
//				this.originalInstagram.setCompoundDrawablesWithIntrinsicBounds(TAB_INDEX_SQUARE,
//						R.drawable.square_mode_original, TAB_INDEX_SQUARE, TAB_INDEX_SQUARE);
//				msg = getString(R.string.instagram);
//				this.originalInstagram.setText(R.string.hdr_fx_original);
//				msg = msg + " 1:1";
//			} else {
//				this.originalInstagram.setCompoundDrawablesWithIntrinsicBounds(TAB_INDEX_SQUARE,
//						R.drawable.square_mode_instagram, TAB_INDEX_SQUARE, TAB_INDEX_SQUARE);
//				msg = getString(R.string.hdr_fx_original);
//				this.originalInstagram.setText(R.string.instagram);
//			}
//			Toast toast = Toast.makeText(this.context, msg, TAB_INDEX_SQUARE_BACKGROUND);
//			toast.setGravity(17, toast.getXOffset() / TAB_INDEX_SQUARE_BLUR,
//					toast.getYOffset() / TAB_INDEX_SQUARE_BLUR);
//			toast.show();
//		} else if (id == R.id.button_collage_pattern_image) {
//			Intent intent = new Intent();
//			intent.setType("image/*");
//			intent.setAction("android.intent.action.GET_CONTENT");
//			this.patternHelper.selectImage();
//			startActivityForResult(Intent.createChooser(intent, "Select Picture"), PatternHelper.SELCT_IMAGE_BG);
//		}
//		if (this.effectFragment != null) {
//			this.effectFragment.myClickHandler(id);
//		}
//		if (id == R.id.button_lib_cancel || id == R.id.button_lib_ok) {
//			clearFxAndFrame();
//		}
//	}

    private void clearFxAndFrame() {
        if (this.currentSelectedTabIndex != TAB_INDEX_SQUARE_FX
                && this.currentSelectedTabIndex != TAB_INDEX_SQUARE_FRAME) {
            return;
        }
    }

    void addCropFragment() {
        ((FrameLayout) findViewById(R.id.crop_fragment_container)).bringToFront();
        this.cropFragment = (FragmentCrop) getSupportFragmentManager().findFragmentByTag("crop_fragment");
        if (this.cropFragment == null) {
            this.cropFragment = new FragmentCrop();
            this.cropFragment.setCropListener(this.cropListener);
            this.cropFragment.setBitmap(this.sourceBitmap);
            this.cropFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.crop_fragment_container, this.cropFragment, "crop_fragment").commitAllowingStateLoss();
            return;
        }
        this.cropFragment.setCropListener(this.cropListener);
        this.cropFragment.setBitmap(this.sourceBitmap);
    }

    void checkDecoareteViewPositions(Matrix cvsMtrx) {
        if (cvsMtrx != null && this.textAndStickerViewContainer != null
                && this.textAndStickerViewContainer.getChildCount() > 0) {
            int size = this.textAndStickerViewContainer.getChildCount();
            for (int i = TAB_INDEX_SQUARE; i < size; i += TAB_INDEX_SQUARE_BACKGROUND) {
                DecorateView decorateView = (DecorateView) this.textAndStickerViewContainer.getChildAt(i);
                BaseData data = decorateView.getData();
                MyMatrix imageSaveMatrix = data.getImageSaveMatrix();
                if (imageSaveMatrix != null) {
                    decorateView.setMatrix(imageSaveMatrix);
                    MyMatrix mat = new MyMatrix(data.getCanvasMatrix());
                    mat.postConcat(cvsMtrx);
                    decorateView.setMatrix(mat);
                    decorateView.postInvalidate();
                }
            }
        }
    }
}
