package com.photo.blureffectcamera.collagelib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.photo.blureffectcamera.common_libs.MyAsyncTask2;
import com.photo.blureffectcamera.cropimages.FragmentCrop;
import com.photo.blureffectcamera.gallerylib.GalleryUtility;
import com.photo.blureffectcamera.imagesavelib.SelectCamGalleryAnimHelper;
import com.photo.blureffectcamera.lib.RotationGestureDetector;
import com.photo.blureffectcamera.lyrebirdlib.BlurBuilderNormal;
import com.photo.blureffectcamera.lyrebirdlib.FullEffectFragment;
import com.photo.blureffectcamera.lyrebirdlib.LibUtility;
import com.photo.blureffectcamera.lyrebirdlib.Parameter;
import com.photo.blureffectcamera.pattern.PatternAdapter;
import com.photo.blureffectcamera.pattern.PatternHelper;
import com.photo.blureffectcamera.pointlist.Collage;
import com.photo.blureffectcamera.pointlist.CollageLayout;
import com.photo.blureffectcamera.pointlist.MaskPairSvg;
import com.photo.blureffectcamera.sticker.StickerData;
import com.photo.blureffectcamera.sticker.StickerLibHelper;
import com.photo.blureffectcamera.sticker.StickerView;
import com.photo.blureffectcamera.svg.Svg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


@SuppressLint({"NewApi"})
public class ShapeCollageActivity extends FragmentActivity implements View.OnClickListener {
    public static final int INDEX_COLLAGE = 0;
    public static final int INDEX_COLLAGE_BACKGROUND = 1;
    public static final int INDEX_COLLAGE_BLUR = 4;
    public static final int INDEX_COLLAGE_BORDER = 6;
    public static final int INDEX_COLLAGE_CASCADE = 5;
    public static final int INDEX_COLLAGE_INVISIBLE_VIEW = 7;
    public static final int INDEX_COLLAGE_RATIO = 3;
    public static final int INDEX_COLLAGE_SPACE = 2;
    public static final int TAB_SIZE = 8;
    private static final String TAG;
    private static final float UPPER_SIZE_FOR_LOAD = 1500.0f;
    public static boolean isGridLayoutLocked;
    int RATIO_BUTTON_SIZE;
    public static Activity activity;
    FragmentActivity activityFragment;
    boolean bgImageIsWaiting;
    Bitmap[] bitmapList;
    private int blurRadius;
    Bitmap btmDelete;
    Bitmap btmScale;
    MyAdapter collageAdapter;
    RecyclerView collageRecyclerView;
    CollageView collageView;
    LinearLayout colorContainer;
    int colorDefault;
    int colorSelected;
    Context context;
    ViewGroup contextFooter;
    FragmentCrop cropFragment;
    FragmentCrop.CropListener cropListener;
    public final int defaultSizeProgressForBlur;
    OnClickListener dialogClickListener;
    FullEffectFragment fullEffectFragment;
    int height;
    final String isGridLockedKey;
    boolean isScrapBook;
    boolean isShape;
    Matrix f1265m;
    private RotationGestureDetector mRotationDetector;
    OnSeekBarChangeListener mSeekBarListener;
    RelativeLayout mainLayout;
    float mulX;
    float mulY;
    NinePatchDrawable npd;
    Button originalInstagram;
    Parameter[] parameterList;
    PatternHelper patternHelper;
    Button[] ratioButtonArray;
    RecyclerView recyclerViewInnerPattern;
    AlertDialog saveImageAlert;
    float screenDensity;
    SeekBar seekBarBorder;
    SeekBar seekBarPadding;
    SeekBar seekBarRound;
    SeekBar seekbarBlur;
    SeekBar seekbarCascadeBlur;
    SeekBar seekbarCascadeNumber;
    SeekBar seekbarSize;
    View selectFilterTextView;
    boolean selectImageForAdj;
    View selectSwapTextView;
    private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;
    int stickerFragemntContinerId;
    StickerLibHelper stickerLibHelper;
    boolean swapMode;
    View[] tabButtonList;
    FrameLayout textAndStickerViewContainer;
    int textFragemntContinerId;
    TextLibHelper textLibHelper;
    float topOffset;
    float totalOffset;
    ViewFlipper viewFlipper;
    int width;
    Collage coll = new Collage();
    public ImageView imgAdd;
    private ImageView addImg;
    public static int[][] collageIconArray;
    SelectCamGalleryAnimHelper selectCamGalleryAnimHelper;
    public int countshape;
    public Boolean boolValForShape;
    public boolean shapeVal;
    public boolean collage_shape_flag;
    public static boolean isColor = false;
    private Uri imageUri;
    private ImageView ivPreImg;
    private Bundle[] bundleArr;
    public int shapeValCount;
    public PatternAdapter.CurrentCollageIndexChangedListener currentIndexlistener;
    public Bitmap tileBG;
    public BitmapDrawable bitmapDrawable;
    protected Bitmap bitmapRepeat;
    private Button button_collage_layout;
    private Button button_mirror_sticker;
    private Button button_collage_border;
    private Button button_collage_blur;
    private Button button_collage_cascade;
    private Button button_collage_background;
    private Button button_collage_space;
    private Button button_collage_ratio;
    private Button button_mirror_text;
    private Button button_collage_adj;
    private Typeface type;
    private Button button_collage_context_swap;
    private Button button_collage_context_delete;
    private Button button_collage_context_add;
    private Button button_collage_context_center;
    private Button button_collage_context_fit;
    private Button button_collage_context_filter;
    private Button button_collage_context_crop;
    private Button button_collage_context_rotate_left;
    private Button button_collage_context_rotate_right;
    private Button button_collage_context_flip_horizontal;
    private Button button_collage_context_flip_vertical;
    private Button button_collage_context_rotate_negative;
    private Button button_collage_context_rotate_positive;
    private Button button_collage_context_zoom_in;
    private Button button_collage_context_zoom_out;
    private Button button_collage_context_move_left;
    private Button button_collage_context_move_right;
    private Button button_collage_context_move_up;
    private Button button_collage_context_move_down;
    private TextView tvselect_image_swap_text;
    private TextView select_image_filter_text;
    RecyclerView recyclerViewPattern;
    HorizontalScrollView horizontalScrollView;


    class PostDelayLess implements Runnable {
        final HorizontalScrollView val$horizontalScrollView;

        PostDelayLess(HorizontalScrollView horizontalScrollView) {
            this.val$horizontalScrollView = horizontalScrollView;
        }

        public void run() {
            this.val$horizontalScrollView.scrollTo(
                    this.val$horizontalScrollView.getChildAt(
                            ShapeCollageActivity.INDEX_COLLAGE)
                            .getMeasuredWidth(),
                    ShapeCollageActivity.INDEX_COLLAGE);
        }
    }

    class PostDelayMore implements Runnable {
        final HorizontalScrollView val$horizontalScrollView;

        PostDelayMore(HorizontalScrollView horizontalScrollView) {
            this.val$horizontalScrollView = horizontalScrollView;
        }

        public void run() {
            this.val$horizontalScrollView.fullScroll(17);
        }
    }

    class SeekbarChangeManager implements OnSeekBarChangeListener {
        SeekbarChangeManager() {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            int id = seekBar.getId();
            if (id == R.id.seekbar_collage_blur
                    || id == R.id.seekbar_collage_blur_cascade) {
                int progress = seekBar.getProgress();
                float radius = ((float) progress) / 4.0f;
                if (radius > 25.0f) {
                    radius = 25.0f;
                }
                if (radius < 0.0f) {
                    radius = 0.0f;
                }
                boolean isCascade = id == R.id.seekbar_collage_blur_cascade;
                if (isCascade) {
                    if (seekbarBlur != null) {
                        seekbarBlur.setProgress(progress);
                    }
                } else if (seekbarCascadeBlur != null) {
                    seekbarCascadeBlur
                            .setProgress(progress);
                }
                if (collageView != null) {
                    collageView.setBlurBitmap((int) radius, isCascade);
                }
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            int id = seekBar.getId();
            if (id == R.id.seekbar_round) {
                if (collageView != null) {
                    collageView
                            .setCornerRadius((float) progress);
                }
            } else if (id == R.id.seekbar_padding) {
                if (collageView != null) {
                    collageView
                            .setPathPadding(
                                    collageView.currentCollageIndex,
                                    (float) progress, false);
                }
            } else if (id == R.id.seekbar_size) {
                if (collageView != null) {
                    collageView
                            .setCollageSize(
                                    collageView.currentCollageIndex,
                                    collageView.sizeMatrix,
                                    progress, false);
                }
            } else if (id == R.id.seekbar_collage_blur) {
            } else {
                if (id == R.id.seekbar_cascade_number) {
                    if (collageView != null) {
                        collageView.cascadeNumber = progress
                                + INDEX_COLLAGE_BACKGROUND;
                        collageView.invalidate();
                    }
                } else if (id == R.id.seekbar_collage_border) {
                    if (screenDensity <= 0.0f) {
                        screenDensity = 1.0f;
                    }
                    Svg.strokeSize = ((float) (progress - 1))
                            / screenDensity;
                    if (collageView != null) {
                        collageView.invalidate();
                    }
                }
            }
        }
    }

    class HierarchyChanger implements OnHierarchyChangeListener {

        class DecorateViewTouch implements DecorateView.OnDecorateViewTouchUp {
            DecorateViewTouch() {
            }

            public void onTouchUp(BaseData mData) {
                Matrix m = getCurrentMatrixForSticker();
                if (m != null) {
                    mData.setImageSaveMatrix(m);
                }
            }
        }

        HierarchyChanger() {
        }

        public void onChildViewAdded(View parent, View child) {
            ((DecorateView) child)
                    .setOnDecorateViewTouchUp(new DecorateViewTouch());
        }

        public void onChildViewRemoved(View parent, View child) {
        }
    }

    class DialogClickListener implements OnClickListener {
        DialogClickListener() {
        }

        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case GridLayoutManager.DEFAULT_SPAN_COUNT /*-1*/:
                    handleLock();
                default:
            }
        }
    }

    class NegativeButtonClick implements OnClickListener {
        NegativeButtonClick() {
        }

        public void onClick(DialogInterface dialog, int id) {
        }
    }

    class PositiveButtonClick implements OnClickListener {
        PositiveButtonClick() {
        }

        public void onClick(DialogInterface dialog, int id) {
            collageView.deleteBitmap(
                    collageView.shapeIndex,
                    width,
                    width);
        }
    }

    public class CollageView extends View {
        public static final int BACKGROUND_BLUR = 1;
        public static final int BACKGROUND_CASCADE = 2;
        public static final int BACKGROUND_PATTERN = 0;
        public static final int PATTERN_SENTINEL = -1;
        static final float RATIO_CONSTANT = 1.25f;
        public static final int VIEW_ORIGINAL = 1;
        public static final int VIEW_SQUARE = 0;
        static final float smallestDistanceDivider = 500.0f;
        float MIN_ZOOM;
        RectF above;
        int animEpsilon;
        int animHalfTime;
        int animSizeSeekbarProgress;
        boolean animate;
        int animationCount;
        int animationDurationLimit;
        int animationLimit;
        private Runnable animator;
        int backgroundMode;
        Bitmap blurBitmap;
        BlurBuilderNormal blurBuilderNormal;
        RectF blurRectDst;
        Rect blurRectSrc;
        Paint borderPaint;
        RectF bottom;
        RectF bottomLeft;
        RectF bottomRight;
        Bitmap btmDoubleArrow;
        int cascadeNumber;
        float cascadeOffsetX;
        float cascadeOffsetY;
        RectF[] cascadeRectArray;
        Paint circlePaint;
        float cornerRadius;
        int currentCollageIndex;
        RectF drawingAreaRect;
        final float epsilon;
        float finalAngle;
        Bitmap frameBitmap;
        int frameDuration;
        RectF frameRect;
        Matrix identityMatrix;
        boolean isInCircle;
        boolean isOnCross;
        int lastSize;
        RectF left;
        Paint lineCircleStroke;
        ArrayList<LineHelper> lineHelpers;
        PointF linePointPointer;
        private int mActivePointerId;
        float mLastTouchX;
        float mLastTouchXLine;
        float mLastTouchY;
        float mLastTouchYLine;
        private ScaleGestureDetector mScaleDetector;
        float mScaleFactor;
        private GestureDetectorCompat mTouchDetector;
        Bitmap[] maskBitmapArray;
        Matrix matrixDoubleArrow;
        float[] matrixValues;
        boolean move;
        int offsetX;
        int offsetY;
        int oldSizeProgress;
        float oldX;
        float oldY;
        boolean orthogonal;
        float paddingDistance;
        Paint paint;
        Paint paintGray;
        Paint paintLine;
        Bitmap patternBitmap;
        Bitmap patternBitmapBlur;
        Paint patternPaint;
        int previousIndex;
        float[] pts;
        float radiusLineHandle;
        Rect rectAnim;
        RectF right;
        RotationGestureDetector.OnRotationGestureListener rotateListener;
        Shape scaleShape;
        int screenHeight;
        int screenWidth;
        int shapeIndex;
        List<ShapeLayout> shapeLayoutList;
        Matrix sizeMatrix;
        Matrix sizeMatrixSaved;
        float sizeScale;
        ArrayList<Float> smallestDistanceList;
        private float startAngle;
        Matrix startMatrix;
        long startTime;
        Matrix textMatrix;
        RectF topLeft;
        RectF topRight;
        float[] vSize;
        float[] values;
        int viewSizeMode;
        float xscale;
        float yscale;
        PointF zoomStart;
        private CurvedAndTiled drawable;

        class AnimatorRunnable implements Runnable {
            AnimatorRunnable() {
            }

            public void run() {
                boolean scheduleNewFrame = false;
                int iter = ((int) (((float) (System.nanoTime() - CollageView.this.startTime)) / 1000000.0f))
                        / CollageView.this.animationDurationLimit;
                if (iter <= 0) {
                    iter = CollageView.VIEW_ORIGINAL;
                }
                if (CollageView.this.animationCount == 0) {
                    CollageView collageView = CollageView.this;
                    collageView.animationCount += CollageView.VIEW_ORIGINAL;
                } else {
                    CollageView.this.animationCount += iter;
                }
                CollageView.this.setCollageSize(CollageView.VIEW_SQUARE,
                        CollageView.this.sizeMatrix, CollageView.this
                                .animSize(CollageView.this.animationCount),
                        false);
                if (CollageView.this.animationCount < CollageView.this.animationLimit) {
                    scheduleNewFrame = true;
                } else {
                    CollageView.this.animate = false;
                }
                if (scheduleNewFrame) {
                    CollageView.this.postDelayed(this,
                            (long) CollageView.this.frameDuration);
                } else {
                    CollageView.this.sizeMatrix
                            .set(CollageView.this.sizeMatrixSaved);
                }
                ((ShapeLayout) CollageView.this.shapeLayoutList
                        .get(CollageView.this.currentCollageIndex)).shapeArr[CollageView.VIEW_SQUARE].f529r
                        .roundOut(CollageView.this.rectAnim);
                CollageView.this.invalidate(CollageView.this.rectAnim);
                CollageView.this.startTime = System.nanoTime();
            }
        }

        class MyGestureListener extends SimpleOnGestureListener {
            private static final String DEBUG_TAG = "Gestures";

            MyGestureListener() {
            }

            public boolean onSingleTapConfirmed(MotionEvent event) {
                return true;
            }

            public boolean onSingleTapUp(MotionEvent event) {
                if (!CollageView.this.isOnCross) {
                    collageView.selectCurrentShape(
                            event.getX(), event.getY(), true);
                }
                return true;
            }

            public boolean onDoubleTap(MotionEvent event) {
                if (!CollageView.this.isOnCross) {
                    collageView.selectCurrentShape(
                            event.getX(), event.getY(), false);
                }
                if (collageView.currentCollageIndex < 0) {
                    return false;
                }
                collageView
                        .setShapeScaleMatrix(CollageView.VIEW_ORIGINAL);
                return true;
            }
        }

        private class ScaleListener extends SimpleOnScaleGestureListener {
            private ScaleListener() {
            }

            public boolean onScale(ScaleGestureDetector detector) {
                if (CollageView.this.shapeIndex >= 0) {
                    CollageView.this.mScaleFactor = detector.getScaleFactor();
                    if (detector.isInProgress()) {
                        CollageView.this.mScaleFactor = Math.max(0.1f,
                                Math.min(CollageView.this.mScaleFactor, 5.0f));
                        CollageView.this.scaleShape = ((ShapeLayout) CollageView.this.shapeLayoutList.get(CollageView.this.currentCollageIndex)).shapeArr[CollageView.this.shapeIndex];
                    } else {
                        CollageView.this.mScaleFactor = Math.max(0.1f,
                                Math.min(CollageView.this.mScaleFactor, 5.0f));
                        CollageView.this.scaleShape = ((ShapeLayout) CollageView.this.shapeLayoutList
                                .get(CollageView.this.currentCollageIndex)).shapeArr[CollageView.this.shapeIndex];
                    }
                    if (isScrapBook) {
                        CollageView.this.scaleShape.bitmapMatrixScaleScrapBook(
                                CollageView.this.mScaleFactor,
                                CollageView.this.mScaleFactor);
                    } else {
                        CollageView.this.scaleShape.bitmapMatrixScale(
                                CollageView.this.mScaleFactor,
                                CollageView.this.mScaleFactor,
                                CollageView.this.scaleShape.bounds.centerX(),
                                CollageView.this.scaleShape.bounds.centerY());
                    }
                    CollageView.this.invalidate();
                    CollageView.this.requestLayout();
                }
                return true;
            }
        }

        class RotateManager implements RotationGestureDetector.OnRotationGestureListener {
            RotateManager() {
            }

            public void OnRotation(RotationGestureDetector rotationDetector) {
                if (CollageView.this.shapeIndex >= 0) {
                    float angle = rotationDetector.getAngle();
                    CollageView.this.scaleShape = ((ShapeLayout) CollageView.this.shapeLayoutList
                            .get(CollageView.this.currentCollageIndex)).shapeArr[CollageView.this.shapeIndex];
                    float rotation = CollageView.this
                            .getMatrixRotation(CollageView.this.scaleShape.bitmapMatrix);
                    if ((rotation == 0.0f || rotation == 90.0f
                            || rotation == 180.0f || rotation == -180.0f || rotation == -90.0f)
                            && Math.abs(CollageView.this.finalAngle - angle) < 4.0f) {
                        CollageView.this.orthogonal = true;
                        return;
                    }
                    if (Math.abs((rotation - CollageView.this.finalAngle)
                            + angle) < 4.0f) {
                        angle = CollageView.this.finalAngle - rotation;
                        CollageView.this.orthogonal = true;
                    }
                    if (Math.abs(90.0f - ((rotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                        angle = (CollageView.this.finalAngle + 90.0f)
                                - rotation;
                        CollageView.this.orthogonal = true;
                    }
                    if (Math.abs(180.0f - ((rotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                        angle = (180.0f + CollageView.this.finalAngle)
                                - rotation;
                        CollageView.this.orthogonal = true;
                    }
                    if (Math.abs(-180.0f
                            - ((rotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                        angle = (CollageView.this.finalAngle - 0.024902344f)
                                - rotation;
                        CollageView.this.orthogonal = true;
                    }
                    if (Math.abs(-90.0f
                            - ((rotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                        angle = (CollageView.this.finalAngle - 0.049804688f)
                                - rotation;
                        CollageView.this.orthogonal = true;
                    } else {
                        CollageView.this.orthogonal = false;
                    }
                    CollageView.this.scaleShape
                            .bitmapMatrixRotate(CollageView.this.finalAngle
                                    - angle);
                    CollageView.this.finalAngle = angle;
                    CollageView.this.invalidate();
                    CollageView.this.requestLayout();
                }
            }
        }

        @SuppressLint({"NewApi"})
        public CollageView(Context context, int width, int height) {
            super(context);
            this.paint = new Paint();
            this.paddingDistance = 0.0f;
            this.cornerRadius = 0.0f;
            this.currentCollageIndex = countshape;
            this.shapeIndex = PATTERN_SENTINEL;
            this.patternPaint = new Paint(VIEW_ORIGINAL);
            this.shapeLayoutList = new ArrayList();
            this.identityMatrix = new Matrix();
            this.cascadeOffsetX = 120.0f;
            this.cascadeOffsetY = 120.0f;
            this.paintLine = new Paint(VIEW_ORIGINAL);
            this.radiusLineHandle = 45.0f;
            this.lineCircleStroke = new Paint(VIEW_ORIGINAL);
            this.viewSizeMode = VIEW_SQUARE;
            this.oldSizeProgress = VIEW_SQUARE;
            this.smallestDistanceList = new ArrayList();
            this.lineHelpers = new ArrayList();
            this.yscale = 1.0f;
            this.xscale = 1.0f;
            this.sizeScale = 1.0f;
            this.sizeMatrix = new Matrix();
            this.animSizeSeekbarProgress = VIEW_SQUARE;
            this.animate = false;
            this.animationCount = VIEW_SQUARE;
            this.animationLimit = 31;
            this.animHalfTime = (this.animationLimit / BACKGROUND_CASCADE)
                    + VIEW_ORIGINAL;
            this.frameDuration = 10;
            this.animEpsilon = 20;
            this.animationDurationLimit = 50;
            this.startTime = System.nanoTime();
            this.animator = new AnimatorRunnable();
            this.rectAnim = new Rect();
            this.textMatrix = new Matrix();
            this.blurRectDst = new RectF();
            this.drawingAreaRect = new RectF();
            RectF[] rectFArr = new RectF[ShapeCollageActivity.INDEX_COLLAGE_BLUR];
            rectFArr[VIEW_SQUARE] = new RectF();
            rectFArr[VIEW_ORIGINAL] = new RectF();
            rectFArr[BACKGROUND_CASCADE] = new RectF();
            rectFArr[ShapeCollageActivity.INDEX_COLLAGE_RATIO] = new RectF();
            this.cascadeRectArray = rectFArr;
            this.cascadeNumber = ShapeCollageActivity.INDEX_COLLAGE_BLUR;
            this.matrixDoubleArrow = new Matrix();
            this.above = new RectF();
            this.left = new RectF();
            this.right = new RectF();
            this.bottom = new RectF();
            this.move = false;
            this.paintGray = new Paint(VIEW_ORIGINAL);
            this.mActivePointerId = PATTERN_SENTINEL;
            this.zoomStart = new PointF();
            this.startMatrix = new Matrix();
            this.startAngle = 0.0f;
            this.MIN_ZOOM = 0.1f;
            this.isInCircle = false;
            this.isOnCross = false;
            this.orthogonal = false;
            this.vSize = new float[9];
            this.linePointPointer = new PointF();
            this.mScaleFactor = 1.0f;
            this.matrixValues = new float[9];
            this.finalAngle = 0.0f;
            this.epsilon = 4.0f;
            this.rotateListener = new RotateManager();
            this.values = new float[9];
            this.backgroundMode = VIEW_SQUARE;
            this.blurRectSrc = new Rect();
            this.borderPaint = new Paint(VIEW_ORIGINAL);
            this.borderPaint.setColor(getResources().getColor(R.color.black));
            this.borderPaint.setStyle(Style.STROKE);
            this.screenWidth = width;
            this.screenHeight = height;
            this.borderPaint
                    .setStrokeWidth(((float) this.screenWidth) / 144.0f);
            this.radiusLineHandle = ((float) this.screenWidth) / 29.0f;
            this.circlePaint = new Paint();
            this.circlePaint.setColor(SupportMenu.CATEGORY_MASK);
            this.identityMatrix.reset();
            this.lineCircleStroke.setStyle(Style.STROKE);
            this.lineCircleStroke.setColor(-15591619);
            this.lineCircleStroke
                    .setStrokeWidth(((float) this.screenWidth) / 240.0f);
            this.topLeft = new RectF((float) (width * VIEW_SQUARE),
                    (float) (height * VIEW_SQUARE), 0.5f * ((float) width),
                    0.5f * ((float) height));
            this.topRight = new RectF(0.5f * ((float) width),
                    0.0f * ((float) height), 1.0f * ((float) width),
                    0.5f * ((float) height));
            this.bottomLeft = new RectF((float) (width * VIEW_SQUARE),
                    0.5f * ((float) height), 0.5f * ((float) width),
                    1.0f * ((float) height));
            this.bottomRight = new RectF(0.5f * ((float) width),
                    0.5f * ((float) height), 1.0f * ((float) width),
                    1.0f * ((float) height));
            Path pathTopLeft = new Path();
            Path pathTopRight = new Path();
            Path pathBottomLeft = new Path();
            Path pathBottomRight = new Path();
            pathTopLeft.addRect(this.topLeft, Direction.CCW);
            pathTopRight.addRect(this.topRight, Direction.CCW);
            pathBottomLeft.addRect(this.bottomLeft, Direction.CCW);
            pathBottomRight.addRect(this.bottomRight, Direction.CCW);
            this.mTouchDetector = new GestureDetectorCompat(context,
                    new MyGestureListener());
            this.mScaleDetector = new ScaleGestureDetector(context,
                    new ScaleListener());
            mRotationDetector = new RotationGestureDetector(
                    this.rotateListener);
            calculateOffset();
            this.patternPaint = new Paint(VIEW_ORIGINAL);
            this.patternPaint.setColor(PATTERN_SENTINEL);

            createShapeList(bitmapList.length, width,
                    height);
            this.paintGray.setColor(getResources().getColor(R.color.purple_700));
            if (!(!bgImageIsWaiting
                    || patternHelper == null
                    || patternHelper.bitmapBlur == null || patternHelper.bitmapBlur
                    .isRecycled())) {
                setBlurBitmap(blurRadius, false);
                this.backgroundMode = VIEW_ORIGINAL;
                invalidate();
                bgImageIsWaiting = false;
            }
            this.btmDoubleArrow = BitmapFactory.decodeResource(getResources(),
                    R.drawable.double_arrow);
        }

        public void changeViewSizeMode() {
            int sizeMode = VIEW_SQUARE;
            if (this.viewSizeMode == 0) {
                sizeMode = VIEW_ORIGINAL;
            }
            this.viewSizeMode = sizeMode;
            setViewSize();
            ShapeCollageActivity.this
                    .checkDecoareteViewPositions(ShapeCollageActivity.this
                            .getCurrentMatrixForSticker());
        }

        public void setViewSize() {
            if (this.viewSizeMode == 0) {
                mulX = 1.0f;
                mulY = 1.0f;
                updateShapeListForRatio(width,
                        height);
                if (seekbarSize.getProgress() == 0) {
                    setCollageSize(VIEW_SQUARE, this.sizeMatrix,
                            this.oldSizeProgress, true);
                    seekbarSize
                            .setProgress(this.oldSizeProgress);
                }
            } else if (this.viewSizeMode == VIEW_ORIGINAL) {
                mulX = ((float) bitmapList[VIEW_SQUARE]
                        .getWidth())
                        / ((float) bitmapList[VIEW_SQUARE]
                        .getHeight());
                mulY = 1.0f;
                updateShapeListForRatio(width,
                        height);
                this.oldSizeProgress = this.lastSize;
                setCollageSize(VIEW_SQUARE, this.sizeMatrix, getResources()
                        .getInteger(R.integer.default_ssize_value), true);
                seekbarSize.setProgress(VIEW_SQUARE);
            }
            invalidate();
        }

        private void calculateOffset() {
            PointF scale = getRatio();
            this.offsetX = (int) ((((float) width) - (scale.x * ((float) width))) / 2.0f);
            this.offsetY = (int) (topOffset + (((((float) height) - totalOffset) - (scale.y * ((float) width))) / 2.0f));
        }

        @SuppressLint("WrongThread")
        private String saveBitmap(int width, int height) {
            int i;
            int btmWidth = (int) (((float) width) * collageView.xscale);
            int btmHeight = (int) (((float) width) * collageView.yscale);
            float btmScale = ((float) Utility.maxSizeForSave(
                    context,
                    ShapeCollageActivity.UPPER_SIZE_FOR_LOAD))
                    / ((float) Math.max(btmWidth, btmHeight));
            int newBtmWidth = (int) (((float) btmWidth) * btmScale);
            int newBtmHeight = (int) (((float) btmHeight) * btmScale);
            if (newBtmWidth <= 0) {
                newBtmWidth = btmWidth;
            }
            if (newBtmHeight <= 0) {
                newBtmHeight = btmHeight;
            }
            Bitmap savedBitmap = Bitmap.createBitmap(newBtmWidth, newBtmHeight,
                    Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(savedBitmap);
            ShapeLayout arr = (ShapeLayout) this.shapeLayoutList
                    .get(this.currentCollageIndex);
            Matrix sizeMat = new Matrix();
            sizeMat.reset();
            sizeMat.preScale(btmScale, btmScale);
            bitmapCanvas.setMatrix(sizeMat);
            if (this.backgroundMode == 0) {
                bitmapCanvas.drawRect(0.0f, 0.0f, (float) btmWidth,
                        (float) btmHeight, this.patternPaint);
            }
            if (!(this.blurBitmap == null || this.blurBitmap.isRecycled() || (this.backgroundMode != VIEW_ORIGINAL && this.backgroundMode != BACKGROUND_CASCADE))) {
                if (this.backgroundMode == BACKGROUND_CASCADE) {
                    float localCascadeOffsetX = (((float) btmWidth) / 4.0f)
                            / ((float) (this.cascadeNumber + VIEW_ORIGINAL));
                    float localCascadeOffsetY = (((float) btmHeight) / 4.0f)
                            / ((float) (this.cascadeNumber + VIEW_ORIGINAL));
                    for (int j = VIEW_SQUARE; j < this.cascadeNumber; j += VIEW_ORIGINAL) {
                        int offsetIndex = j + VIEW_ORIGINAL;
                        this.cascadeRectArray[j]
                                .set(((float) offsetIndex)
                                                * localCascadeOffsetX,
                                        ((float) offsetIndex)
                                                * localCascadeOffsetY,
                                        ((float) btmWidth)
                                                - (((float) offsetIndex) * localCascadeOffsetX),
                                        ((float) btmHeight)
                                                - (((float) offsetIndex) * localCascadeOffsetY));
                    }

                }
                RectF rectF = new RectF(0.0f, 0.0f, (float) btmWidth,
                        (float) btmHeight);
                if (!(this.blurBitmap == null || this.blurBitmap.isRecycled())) {
                    bitmapCanvas.drawBitmap(this.blurBitmap, this.blurRectSrc,
                            rectF, this.paint);
                }
                if (this.backgroundMode == BACKGROUND_CASCADE) {
                    for (i = VIEW_SQUARE; i < this.cascadeNumber; i += VIEW_ORIGINAL) {
                        if (!(this.blurBitmap == null || this.blurBitmap
                                .isRecycled())) {
                            bitmapCanvas.drawBitmap(this.blurBitmap,
                                    this.blurRectSrc, this.cascadeRectArray[i],
                                    this.paint);
                        }
                    }
                }
            }
            sizeMat.postScale(this.sizeScale, this.sizeScale,
                    ((float) newBtmWidth) / 2.0f, ((float) newBtmHeight) / 2.0f);
            sizeMat.preTranslate((float) (-this.offsetX),
                    (float) (-this.offsetY));
            bitmapCanvas.setMatrix(sizeMat);
            @SuppressLint("WrongConstant") int q = bitmapCanvas
                    .saveLayer(((float) (-width)) / this.sizeScale,
                            ((float) (-height)) / this.sizeScale,
                            ((float) this.offsetX)
                                    + (((float) width) / this.sizeScale),
                            ((float) this.offsetY)
                                    + (((float) height) / this.sizeScale),
                            null, 31);
            for (i = VIEW_SQUARE; i < arr.shapeArr.length; i += VIEW_ORIGINAL) {
                boolean drawPorterClear = false;
                if (i == arr.getClearIndex()) {
                    drawPorterClear = true;
                }
                if (isScrapBook) {
                    arr.shapeArr[i].drawShapeForScrapBook(bitmapCanvas,
                            newBtmWidth, newBtmHeight, false, false);
                } else {
                    arr.shapeArr[i].drawShape(bitmapCanvas, newBtmWidth,
                            newBtmHeight, q, drawPorterClear, true,
                            arr.shapeArr.length == VIEW_ORIGINAL);
                }
            }
            if (textAndStickerViewContainer != null) {
                for (i = VIEW_SQUARE; i < textAndStickerViewContainer
                        .getChildCount(); i += VIEW_ORIGINAL) {
                    Matrix mat = new Matrix();
                    View v = textAndStickerViewContainer
                            .getChildAt(i);
                    if (v instanceof StickerView) {
                        StickerView view = (StickerView) v;
                        StickerData data = view.getStickerData();
                        mat.set(data.getCanvasMatrix());
                        mat.postTranslate((float) (-this.offsetX),
                                (float) (-this.offsetY));
                        mat.postScale(btmScale, btmScale);
                        bitmapCanvas.setMatrix(mat);
                        if (!(view.stickerBitmap == null || view.stickerBitmap
                                .isRecycled())) {
                            bitmapCanvas.drawBitmap(view.stickerBitmap,
                                    data.xPos, data.yPos, view.paint);
                        }
                    } else if (v instanceof CanvasTextView) {
                        TextData textData = ((CanvasTextView) v).getTextData();
                        if (!textData.getSnapMode()) {
                            mat.set(textData.getCanvasMatrix());
                        }
                        mat.postTranslate((float) (-this.offsetX),
                                (float) (-this.offsetY));
                        mat.postScale(btmScale, btmScale);
                        bitmapCanvas.setMatrix(mat);
                        TextLibHelper.saveTextOnBitmap(bitmapCanvas, textData,
                                this.screenWidth);
                    }
                }
            }
            bitmapCanvas.restoreToCount(q);
            String resultPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    .toString()
                    + getString(R.string.directory)
                    + String.valueOf(System.currentTimeMillis()) + ".jpg";
            new File(resultPath).getParentFile().mkdirs();
            try {
                OutputStream fileOutputStream = new FileOutputStream(resultPath);
                savedBitmap.compress(CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            savedBitmap.recycle();
            return resultPath;
        }

        private void createShapeList(int shapeCount, int width, int height) {
            this.shapeLayoutList.clear();
            this.smallestDistanceList.clear();

            coll = coll.CreateCollage(shapeCount, width, width,
                    isScrapBook);
            int size = ((CollageLayout) coll.collageLayoutList.get(VIEW_SQUARE)).shapeList
                    .size();
            int i = VIEW_SQUARE;

            while (i < coll.collageLayoutList.size()) {
                Shape[] shapeArray = new Shape[size];
                for (int j = VIEW_SQUARE; j < shapeCount; j += VIEW_ORIGINAL) {
                    boolean maskedSvg = false;
                    int svgIndex = PATTERN_SENTINEL;
                    if (!(((CollageLayout) coll.collageLayoutList.get(i)).maskPairListSvg == null || ((CollageLayout) coll.collageLayoutList
                            .get(i)).maskPairListSvg.isEmpty())) {
                        for (MaskPairSvg maskPairSvg : ((CollageLayout) coll.collageLayoutList
                                .get(i)).maskPairListSvg) {
                            if (j == maskPairSvg.index) {
                                maskedSvg = true;
                                svgIndex = maskPairSvg.svgIndex;
                            }
                        }
                    }
                    if (maskedSvg) {
                        shapeArray[j] = new Shape(
                                (PointF[]) ((CollageLayout) coll.collageLayoutList
                                        .get(i)).shapeList.get(j),
                                bitmapList[j], null,
                                this.offsetX, this.offsetY,
                                isScrapBook, j,
                                false, btmDelete,
                                btmScale,
                                this.screenWidth,
                                ((CollageLayout) coll.collageLayoutList.get(i))
                                        .getConcavite(), svgIndex, width, width);
                        // }
                        if (isScrapBook) {
                            shapeArray[j]
                                    .initScrapBook(npd);
                        }
                    } else {
                        int shapeMode = VIEW_ORIGINAL;

                        if (j == ((CollageLayout) coll.collageLayoutList.get(i))
                                .getClearIndex()) {
                            shapeMode = ShapeCollageActivity.INDEX_COLLAGE_BLUR;
                        }
                        int i2 = j;
                        shapeArray[j] = new Shape(
                                (PointF[]) ((CollageLayout) coll.collageLayoutList
                                        .get(i)).shapeList.get(j),
                                bitmapList[j],
                                ((CollageLayout) coll.collageLayoutList.get(i))
                                        .getexceptionIndex(j), this.offsetX,
                                this.offsetY,
                                isScrapBook, i2,
                                false, btmDelete,
                                btmScale,
                                this.screenWidth, shapeMode,
                                ((CollageLayout) coll.collageLayoutList.get(i))
                                        .getConcavite(), width, width);
                        if (isScrapBook) {
                            shapeArray[j]
                                    .initScrapBook(npd);
                        }
                    }
                }
                this.smallestDistanceList.add(Float
                        .valueOf(smallestDistance(shapeArray)));
                ShapeLayout shapeLayout = new ShapeLayout(shapeArray);
                shapeLayout.useLine = ((CollageLayout) coll.collageLayoutList
                        .get(i)).useLine;
                shapeLayout
                        .setClearIndex(((CollageLayout) coll.collageLayoutList
                                .get(i)).getClearIndex());
                this.shapeLayoutList.add(shapeLayout);
                i += VIEW_ORIGINAL;
            }
            if (!isScrapBook) {
                if (shapeCount != VIEW_ORIGINAL) {
                    for (int index = VIEW_SQUARE; index < this.shapeLayoutList
                            .size(); index += VIEW_ORIGINAL) {
                        setPathPadding(index, (float) getResources()
                                        .getInteger(R.integer.default_space_value),
                                false);
                    }
                    setCollageSize(VIEW_SQUARE, this.sizeMatrix, getResources()
                            .getInteger(R.integer.default_ssize_value), true);
                } else if (bitmapList.length == VIEW_ORIGINAL) {
                    setCollageSize(VIEW_SQUARE, this.sizeMatrix, getResources()
                            .getInteger(R.integer.default_ssize_value), false);
                }
            }
            if (!isScrapBook) {
                createLineList((int) (((float) width) * this.xscale),
                        (int) (((float) width) * this.yscale));
            }
        }

        void createLineList(int w, int h) {
            if (this.lineHelpers == null) {
                this.lineHelpers = new ArrayList();
            }
            this.lineHelpers.clear();
            for (int position = VIEW_SQUARE; position < this.shapeLayoutList
                    .size(); position += VIEW_ORIGINAL) {
                ShapeLayout sl = (ShapeLayout) this.shapeLayoutList
                        .get(position);
                ArrayList<ArrayList<PointF>> shapeList = new ArrayList();
                for (int i = VIEW_SQUARE; i < sl.shapeArr.length; i += VIEW_ORIGINAL) {
                    shapeList.add(new ArrayList());
                    for (int j = VIEW_SQUARE; j < sl.shapeArr[i].points.length; j += VIEW_ORIGINAL) {
                        ((ArrayList) shapeList.get(i))
                                .add(sl.shapeArr[i].points[j]);
                    }
                }
                LineHelper lineHelper = new LineHelper(
                        shapeList,
                        (float) w,
                        (float) h,
                        ((ShapeLayout) this.shapeLayoutList.get(position)).useLine);
                lineHelper.findGridLines();
                lineHelper.minDistance = ((Float) this.smallestDistanceList
                        .get(position)).floatValue() / 2.0f;
                this.lineHelpers.add(lineHelper);
            }
        }

        private int setShapeScaleMatrix(int mode) {
            if (this.shapeIndex < 0) {
                return PATTERN_SENTINEL;
            }
            int message = ((ShapeLayout) this.shapeLayoutList
                    .get(this.currentCollageIndex)).shapeArr[this.shapeIndex]
                    .setScaleMatrix(mode);
            invalidate();
            ShapeCollageActivity.this
                    .checkDecoareteViewPositions(ShapeCollageActivity.this
                            .getCurrentMatrixForSticker());
            return message;
        }

        private float defaultSizeValue(int index) {
            return ((Float) this.smallestDistanceList.get(index)).floatValue();
        }

        private void setLockScales() {
            for (int i = VIEW_SQUARE; i < this.shapeLayoutList.size(); i += VIEW_ORIGINAL) {
                for (int j = VIEW_SQUARE; j < ((ShapeLayout) this.shapeLayoutList
                        .get(i)).shapeArr.length; j += VIEW_ORIGINAL) {
                    ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[j]
                            .setMaxMinScalesForLock();
                }
            }
        }

        private void update(int index, int width, int height) {
            Shape[] scrapBookTemp = ((ShapeLayout) this.shapeLayoutList
                    .get(VIEW_SQUARE)).shapeArr;
            if (index >= 0
                    && index < ((ShapeLayout) this.shapeLayoutList
                    .get(VIEW_SQUARE)).shapeArr.length) {
                int i;
                int newSize = ((ShapeLayout) this.shapeLayoutList
                        .get(VIEW_SQUARE)).shapeArr.length;
                Bitmap[] currentBitmapListTemp = new Bitmap[newSize];
                Bitmap[] bitmapListTemp = new Bitmap[newSize];
                int j = VIEW_SQUARE;
                for (i = VIEW_SQUARE; i < currentBitmapListTemp.length
                        + VIEW_ORIGINAL; i += VIEW_ORIGINAL) {
                    if (i != index) {

                        currentBitmapListTemp[j] = ((ShapeLayout) this.shapeLayoutList
                                .get(VIEW_SQUARE)).shapeArr[i].getBitmap();
                        bitmapListTemp[j] = bitmapList[i];
                        j += VIEW_ORIGINAL;
                    }
                }
                bitmapList[index].recycle();
                ((ShapeLayout) this.shapeLayoutList.get(VIEW_SQUARE)).shapeArr[index].getBitmap().recycle();
                this.shapeLayoutList.clear();
                this.smallestDistanceList.clear();
                coll = coll.CreateCollage(newSize, width, width,
                        isScrapBook);
                int size = ((CollageLayout) coll.collageLayoutList
                        .get(VIEW_SQUARE)).shapeList.size();
                bitmapList = bitmapListTemp;
                i = VIEW_SQUARE;
                while (i < coll.collageLayoutList.size()) {
                    Shape[] shapeArray = new Shape[size];
                    for (j = VIEW_SQUARE; j < currentBitmapListTemp.length; j += VIEW_ORIGINAL) {
                        boolean maskedSvg = false;
                        int svgIndex = PATTERN_SENTINEL;
                        if (!(((CollageLayout) coll.collageLayoutList.get(i)).maskPairListSvg == null || ((CollageLayout) coll.collageLayoutList
                                .get(i)).maskPairListSvg.isEmpty())) {
                            for (MaskPairSvg maskPairSvg : ((CollageLayout) coll.collageLayoutList
                                    .get(i)).maskPairListSvg) {
                                if (j == maskPairSvg.index) {
                                    maskedSvg = true;
                                    svgIndex = maskPairSvg.svgIndex;
                                }
                            }
                        }
                        if (maskedSvg) {
                            shapeArray[j] = new Shape(
                                    (PointF[]) ((CollageLayout) coll.collageLayoutList
                                            .get(i)).shapeList.get(j),
                                    bitmapList[j],
                                    null, this.offsetX, this.offsetY,
                                    isScrapBook, j,
                                    false, btmDelete,
                                    btmScale,
                                    this.screenWidth,
                                    ((CollageLayout) coll.collageLayoutList
                                            .get(i)).getConcavite(), svgIndex,
                                    width, width);
                            if (isScrapBook) {
                                shapeArray[j]
                                        .initScrapBook(npd);
                            }
                        } else {
                            int shapeMode = VIEW_ORIGINAL;
                            if (j == ((CollageLayout) coll.collageLayoutList
                                    .get(i)).getClearIndex()) {
                                shapeMode = INDEX_COLLAGE_BLUR;
                            }
                            int i2 = j;
                            shapeArray[j] = new Shape(
                                    (PointF[]) ((CollageLayout) coll.collageLayoutList
                                            .get(i)).shapeList.get(j),
                                    currentBitmapListTemp[j],
                                    ((CollageLayout) coll.collageLayoutList
                                            .get(i)).getexceptionIndex(j),
                                    this.offsetX, this.offsetY,
                                    isScrapBook, i2,
                                    true, btmDelete,
                                    btmScale,
                                    this.screenWidth, shapeMode,
                                    ((CollageLayout) coll.collageLayoutList
                                            .get(i)).getConcavite(), width,
                                    width);
                            if (isScrapBook) {
                                shapeArray[j]
                                        .initScrapBook(npd);
                            }
                        }
                    }
                    if (isScrapBook) {
                        for (int k = VIEW_SQUARE; k < scrapBookTemp.length; k += VIEW_ORIGINAL) {
                            if (k < index) {
                                shapeArray[k].bitmapMatrix
                                        .set(scrapBookTemp[k].bitmapMatrix);
                            }
                            if (k > index) {
                                shapeArray[k + PATTERN_SENTINEL].bitmapMatrix
                                        .set(scrapBookTemp[k].bitmapMatrix);
                            }
                        }
                    }
                    ShapeLayout shapeLayout = new ShapeLayout(shapeArray);
                    shapeLayout.useLine = ((CollageLayout) coll.collageLayoutList
                            .get(i)).useLine;
                    shapeLayout
                            .setClearIndex(((CollageLayout) coll.collageLayoutList
                                    .get(i)).getClearIndex());
                    this.shapeLayoutList.add(shapeLayout);
                    this.smallestDistanceList.add(Float
                            .valueOf(smallestDistance(shapeArray)));
                    i += VIEW_ORIGINAL;
                }
                this.currentCollageIndex = VIEW_SQUARE;
                collageAdapter.selectedPosition = VIEW_SQUARE;
                collageAdapter
                        .setData(coll.collageIconArray[newSize
                                + PATTERN_SENTINEL]);
                collageAdapter.notifyDataSetChanged();
                if (!isScrapBook) {
                    updateShapeListForRatio(width, height);
                }
                unselectShapes();
                invalidate();
                if (currentBitmapListTemp.length == VIEW_ORIGINAL) {
                    setVisibilityForSingleImage();
                }
                if (newSize == VIEW_ORIGINAL) {
                    setPathPadding(VIEW_SQUARE, 0.0f, false);
                    if (this.sizeScale == 1.0f
                            && !isScrapBook) {
                        setCollageSize(
                                VIEW_SQUARE,
                                this.sizeMatrix,
                                getResources().getInteger(
                                        R.integer.default_ssize_value), true);
                    }
                }
                if (!isScrapBook) {
                    createLineList((int) (((float) width) * this.xscale),
                            (int) (((float) width) * this.yscale));
                }
            }
        }

        private void deleteBitmap(int index, int width, int height) {
            Shape[] scrapBookTemp = ((ShapeLayout) this.shapeLayoutList
                    .get(VIEW_SQUARE)).shapeArr;
            if (index >= 0
                    && index < ((ShapeLayout) this.shapeLayoutList
                    .get(VIEW_SQUARE)).shapeArr.length) {
                int i;
                int newSize = ((ShapeLayout) this.shapeLayoutList
                        .get(VIEW_SQUARE)).shapeArr.length + PATTERN_SENTINEL;
                Bitmap[] currentBitmapListTemp = new Bitmap[newSize];
                Bitmap[] bitmapListTemp = new Bitmap[newSize];
                int j = VIEW_SQUARE;
                for (i = VIEW_SQUARE; i < currentBitmapListTemp.length
                        + VIEW_ORIGINAL; i += VIEW_ORIGINAL) {
                    if (i != index) {

                        currentBitmapListTemp[j] = ((ShapeLayout) this.shapeLayoutList
                                .get(VIEW_SQUARE)).shapeArr[i].getBitmap();
                        bitmapListTemp[j] = bitmapList[i];
                        j += VIEW_ORIGINAL;
                    }
                }
                bitmapList[index].recycle();
                ((ShapeLayout) this.shapeLayoutList.get(VIEW_SQUARE)).shapeArr[index]
                        .getBitmap().recycle();
                this.shapeLayoutList.clear();
                this.smallestDistanceList.clear();
                coll = coll.CreateCollage(newSize, width, width,
                        isScrapBook);
                int size = ((CollageLayout) coll.collageLayoutList
                        .get(VIEW_SQUARE)).shapeList.size();
                bitmapList = bitmapListTemp;
                i = VIEW_SQUARE;
                while (i < coll.collageLayoutList.size()) {
                    Shape[] shapeArray = new Shape[size];
                    for (j = VIEW_SQUARE; j < currentBitmapListTemp.length; j += VIEW_ORIGINAL) {
                        boolean maskedSvg = false;
                        int svgIndex = PATTERN_SENTINEL;
                        if (!(((CollageLayout) coll.collageLayoutList.get(i)).maskPairListSvg == null || ((CollageLayout) coll.collageLayoutList
                                .get(i)).maskPairListSvg.isEmpty())) {
                            for (MaskPairSvg maskPairSvg : ((CollageLayout) coll.collageLayoutList
                                    .get(i)).maskPairListSvg) {
                                if (j == maskPairSvg.index) {
                                    maskedSvg = true;
                                    svgIndex = maskPairSvg.svgIndex;
                                }
                            }
                        }
                        if (maskedSvg) {
                            shapeArray[j] = new Shape(
                                    (PointF[]) ((CollageLayout) coll.collageLayoutList
                                            .get(i)).shapeList.get(j),
                                    bitmapList[j],
                                    null, this.offsetX, this.offsetY,
                                    isScrapBook, j,
                                    false, btmDelete,
                                    btmScale,
                                    this.screenWidth,
                                    ((CollageLayout) coll.collageLayoutList
                                            .get(i)).getConcavite(), svgIndex,
                                    width, width);
                            if (isScrapBook) {
                                shapeArray[j]
                                        .initScrapBook(npd);
                            }
                        } else {
                            int shapeMode = VIEW_ORIGINAL;
                            if (j == ((CollageLayout) coll.collageLayoutList
                                    .get(i)).getClearIndex()) {
                                shapeMode = ShapeCollageActivity.INDEX_COLLAGE_BLUR;
                            }
                            int i2 = j;
                            shapeArray[j] = new Shape(
                                    (PointF[]) ((CollageLayout) coll.collageLayoutList
                                            .get(i)).shapeList.get(j),
                                    currentBitmapListTemp[j],
                                    ((CollageLayout) coll.collageLayoutList
                                            .get(i)).getexceptionIndex(j),
                                    this.offsetX, this.offsetY,
                                    isScrapBook, i2,
                                    true, btmDelete,
                                    btmScale,
                                    this.screenWidth, shapeMode,
                                    ((CollageLayout) coll.collageLayoutList
                                            .get(i)).getConcavite(), width,
                                    width);
                            if (isScrapBook) {
                                shapeArray[j]
                                        .initScrapBook(npd);
                            }
                        }
                    }
                    if (isScrapBook) {
                        for (int k = VIEW_SQUARE; k < scrapBookTemp.length; k += VIEW_ORIGINAL) {
                            if (k < index) {
                                shapeArray[k].bitmapMatrix
                                        .set(scrapBookTemp[k].bitmapMatrix);
                            }
                            if (k > index) {
                                shapeArray[k + PATTERN_SENTINEL].bitmapMatrix
                                        .set(scrapBookTemp[k].bitmapMatrix);
                            }
                        }
                    }
                    ShapeLayout shapeLayout = new ShapeLayout(shapeArray);
                    shapeLayout.useLine = ((CollageLayout) coll.collageLayoutList
                            .get(i)).useLine;
                    shapeLayout
                            .setClearIndex(((CollageLayout) coll.collageLayoutList
                                    .get(i)).getClearIndex());
                    this.shapeLayoutList.add(shapeLayout);
                    this.smallestDistanceList.add(Float
                            .valueOf(smallestDistance(shapeArray)));
                    i += VIEW_ORIGINAL;
                }
                this.currentCollageIndex = VIEW_SQUARE;
                collageAdapter.selectedPosition = VIEW_SQUARE;
                collageAdapter
                        .setData(coll.collageIconArray[newSize
                                + PATTERN_SENTINEL]);
                collageAdapter.notifyDataSetChanged();
                if (!isScrapBook) {
                    updateShapeListForRatio(width, height);
                }
                unselectShapes();
                invalidate();
                if (currentBitmapListTemp.length == VIEW_ORIGINAL) {
                    setVisibilityForSingleImage();
                }
                if (newSize == VIEW_ORIGINAL) {
                    setPathPadding(VIEW_SQUARE, 0.0f, false);
                    if (this.sizeScale == 1.0f
                            && !isScrapBook) {
                        setCollageSize(
                                VIEW_SQUARE,
                                this.sizeMatrix,
                                getResources().getInteger(
                                        R.integer.default_ssize_value), true);
                    }
                }
                if (!isScrapBook) {
                    createLineList((int) (((float) width) * this.xscale),
                            (int) (((float) width) * this.yscale));
                }
            }
        }

        private void updateBitmap(int index, int width, int height) {
            onGalleryClickedForUpdate();

        }

        public float smallestDistance(Shape[] shapeArray) {
            float smallestDistance = shapeArray[VIEW_SQUARE].smallestDistance();
            for (int i = VIEW_SQUARE; i < shapeArray.length; i += VIEW_ORIGINAL) {
                float distance = shapeArray[i].smallestDistance();
                if (distance < smallestDistance) {
                    smallestDistance = distance;
                }
            }
            return smallestDistance;
        }

        public void updateShapeListForRatio(int width, int height) {
            int shapeCount = ((ShapeLayout) this.shapeLayoutList
                    .get(VIEW_SQUARE)).shapeArr.length;
            PointF scale = getRatio();
            calculateOffset();
            coll = Collage.CreateCollage(shapeCount,
                    (int) (scale.x * ((float) width)),
                    (int) (((float) width) * scale.y),
                    isScrapBook);
            this.smallestDistanceList.clear();
            for (int index = VIEW_SQUARE; index < this.shapeLayoutList.size(); index += VIEW_ORIGINAL) {
                if (shapeCount == VIEW_ORIGINAL) {
                    ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr[VIEW_SQUARE]
                            .changeRatio(
                                    (PointF[]) ((CollageLayout) coll.collageLayoutList
                                            .get(index)).shapeList
                                            .get(VIEW_SQUARE), null,
                                    this.offsetX, this.offsetY,
                                    isScrapBook,
                                    VIEW_SQUARE,
                                    (int) (scale.x * ((float) width)),
                                    (int) (((float) width) * scale.y));
                } else {
                    for (int j = VIEW_SQUARE; j < shapeCount; j += VIEW_ORIGINAL) {
                        ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr[j]
                                .changeRatio(
                                        (PointF[]) ((CollageLayout) coll.collageLayoutList
                                                .get(index)).shapeList.get(j),
                                        null, this.offsetX, this.offsetY,
                                        isScrapBook,
                                        j, (int) (scale.x * ((float) width)),
                                        (int) (((float) width) * scale.y));
                    }
                }
                this.smallestDistanceList.add(Float
                        .valueOf(smallestDistance(((ShapeLayout) this.shapeLayoutList
                                .get(index)).shapeArr)));
                setPathPadding(index, this.paddingDistance, false);
                if (!isScrapBook) {
                    for (int k = VIEW_SQUARE; k < ((ShapeLayout) this.shapeLayoutList
                            .get(index)).shapeArr.length; k += VIEW_ORIGINAL) {
                        ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr[k]
                                .setScaleMatrix(VIEW_ORIGINAL);
                    }
                }
            }
            setCornerRadius(this.cornerRadius);
            if (this.blurBitmap != null) {
                setBlurRect2((float) this.blurBitmap.getWidth(),
                        (float) this.blurBitmap.getHeight());
            }
            if (!isScrapBook) {
                createLineList((int) (scale.x * ((float) width)),
                        (int) (((float) width) * scale.y));
            }
            postInvalidate();
            checkDecoareteViewPositions(getCurrentMatrixForSticker());
        }

        PointF getRatio() {
            this.yscale = 1.0f;
            this.xscale = 1.0f;
            this.yscale = mulY
                    / mulX;
            if (!isScrapBook
                    && this.yscale > RATIO_CONSTANT) {
                this.xscale = RATIO_CONSTANT / this.yscale;
                this.yscale = RATIO_CONSTANT;
            }
            return new PointF(this.xscale, this.yscale);
        }

        private void updateShapeListForFilterBitmap(Bitmap bitmap) {
            if (this.shapeIndex >= 0) {
                for (int i = VIEW_SQUARE; i < this.shapeLayoutList.size(); i += VIEW_ORIGINAL) {
                    ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[this.shapeIndex]
                            .setBitmap(bitmap, true);
                }
            }
        }

        void updateParamList(Parameter p) {
            if (this.shapeIndex >= 0) {
                parameterList[this.shapeIndex] = new Parameter(
                        p);
            }
        }

        @SuppressLint("WrongConstant")
        private void swapBitmaps(int index1, int index2) {
            Bitmap bitmap1 = ((ShapeLayout) this.shapeLayoutList
                    .get(VIEW_SQUARE)).shapeArr[index1].getBitmap();
            Bitmap bitmap2 = ((ShapeLayout) this.shapeLayoutList
                    .get(VIEW_SQUARE)).shapeArr[index2].getBitmap();
            for (int i = VIEW_SQUARE; i < this.shapeLayoutList.size(); i += VIEW_ORIGINAL) {
                ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[index1]
                        .setBitmap(bitmap2, false);
                ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[index1]
                        .setScaleMatrix(VIEW_ORIGINAL);
                ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[index2]
                        .setBitmap(bitmap1, false);
                ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[index2]
                        .setScaleMatrix(VIEW_ORIGINAL);
            }
            Bitmap temp = bitmapList[index1];
            bitmapList[index1] = bitmapList[index2];
            bitmapList[index2] = temp;
            Parameter tempParam = parameterList[index1];
            parameterList[index1] = parameterList[index2];
            parameterList[index2] = tempParam;
            selectSwapTextView
                    .setVisibility(INDEX_COLLAGE_BLUR);
            unselectShapes();
        }

        void setCurrentCollageIndex(int index) {
            boolean z = false;
            this.currentCollageIndex = index;
            if (this.currentCollageIndex >= this.shapeLayoutList.size()) {
                this.currentCollageIndex = VIEW_SQUARE;
            }
            if (this.currentCollageIndex < 0) {
                this.currentCollageIndex = this.shapeLayoutList.size()
                        + PATTERN_SENTINEL;
            }
            setCornerRadius(this.cornerRadius);
            setPathPadding(this.currentCollageIndex, this.paddingDistance,
                    false);
            int i = this.currentCollageIndex;
            Matrix matrix = this.sizeMatrix;
            int i2 = this.lastSize;
            if (this.lastSize == getResources().getInteger(
                    R.integer.default_ssize_value)) {
                z = true;
            }
            setCollageSize(i, matrix, i2, z);
            checkDecoareteViewPositions(getCurrentMatrixForSticker());
        }

        private void setCornerRadius(float radius) {
            this.cornerRadius = radius;
            CornerPathEffect corEffect = new CornerPathEffect(radius);
            for (int i = VIEW_SQUARE; i < ((ShapeLayout) this.shapeLayoutList
                    .get(this.currentCollageIndex)).shapeArr.length; i += VIEW_ORIGINAL) {
                ((ShapeLayout) this.shapeLayoutList
                        .get(this.currentCollageIndex)).shapeArr[i]
                        .setRadius(corEffect);
            }
            postInvalidate();
        }

        private void setPathPadding(int index, float distance, boolean override) {
            this.paddingDistance = distance;
            for (int i = VIEW_SQUARE; i < ((ShapeLayout) this.shapeLayoutList
                    .get(index)).shapeArr.length; i += VIEW_ORIGINAL) {
                ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr[i]
                        .scalePath(
                                (((Float) this.smallestDistanceList.get(index))
                                        .floatValue() / smallestDistanceDivider)
                                        * distance, (float) this.screenWidth,
                                (float) this.screenWidth);
                if (!isScrapBook) {
                    ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr[i]
                            .checkScaleBounds(override);
                    ((ShapeLayout) this.shapeLayoutList.get(index)).shapeArr[i]
                            .checkBoundries(override);
                }
            }
            postInvalidate();
        }

        private void setCollageSize(int index, Matrix matrix, int progress,
                                    boolean equalSize) {
            this.lastSize = progress;
            matrix.reset();
            this.sizeScale = calculateSize(index, (float) progress, equalSize);
            matrix.postScale(
                    this.sizeScale,
                    this.sizeScale,
                    (((float) (this.offsetX + this.offsetX)) + (((float) width) * this.xscale)) / 2.0f,
                    (((float) (this.offsetY + this.offsetY)) + (((float) width) * this.yscale)) / 2.0f);
            invalidate();
            ShapeCollageActivity.this
                    .checkDecoareteViewPositions(ShapeCollageActivity.this
                            .getCurrentMatrixForSticker());
        }

        float calculateSize(int index, float progress, boolean equalSize) {

            if (!equalSize) {

                return 100f / (200.0f - progress);
            }
            float xx = (2.0f * progress)
                    * (((Float) this.smallestDistanceList.get(index))
                    .floatValue() / smallestDistanceDivider);
            return (((float) width) - ((((float) width) * xx) / (((float) width) + xx)))
                    / ((float) width);
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
                patternBitmapBlur = bitmap;
                this.patternPaint.setShader(new BitmapShader(patternBitmap,
                        TileMode.REPEAT, TileMode.REPEAT));

                drawable = new CurvedAndTiled(patternBitmap, resId);
                Bitmap bitmap1 = drawable.getBitmap(mainLayout.getWidth(),
                        mainLayout.getHeight());
                patternHelper.bitmapBlur = bitmap1;

                postInvalidate();

            }
        }

        public Bitmap convertToBitmap(Drawable drawable, int widthPixels,
                                      int heightPixels) {
            Bitmap mutableBitmap = Bitmap.createBitmap(widthPixels,
                    heightPixels, Config.ARGB_8888);
            Canvas canvas = new Canvas(mutableBitmap);
            drawable.setBounds(0, 0, widthPixels, heightPixels);
            drawable.draw(canvas);
            return mutableBitmap;
        }

        void setPatternPaintColor(int color) {
            if (this.patternPaint == null) {
                this.patternPaint = new Paint(VIEW_ORIGINAL);
            }
            this.patternPaint.setShader(null);
            this.patternPaint.setColor(color);
            isColor = true;
            patternHelper.bitmapBlur = Bitmap
                    .createBitmap(width, height, Config.ARGB_8888);
            patternHelper.bitmapBlur
                    .eraseColor(color);
            postInvalidate();
        }

        public void setFrame(int index) {
            if (this.frameRect == null) {
                this.frameRect = new RectF(0.0f, 0.0f,
                        (float) this.screenWidth, (float) this.screenWidth);
            }
            if (!(this.frameBitmap == null || this.frameBitmap.isRecycled())) {
                this.frameBitmap.recycle();
                this.frameBitmap = null;
            }
            if (index != 0) {
                this.frameBitmap = BitmapFactory.decodeResource(getResources(),
                        LibUtility.borderRes[index]);
                postInvalidate();
            }
        }

        public void startAnimator() {
            if (seekbarSize != null) {
                this.animSizeSeekbarProgress = seekbarSize
                        .getProgress();
            } else {
                this.animSizeSeekbarProgress = VIEW_SQUARE;
            }
            this.sizeMatrixSaved = new Matrix(this.sizeMatrix);
            this.animationCount = VIEW_SQUARE;
            this.animate = true;
            removeCallbacks(this.animator);
            postDelayed(this.animator, 150);
        }

        int animSize(int value) {
            int res;
            if (value < this.animHalfTime) {
                res = value;
            } else {
                res = this.animationLimit - value;
            }
            return this.animSizeSeekbarProgress
                    + Math.round((float) (res * BACKGROUND_CASCADE));
        }

        @SuppressLint("WrongConstant")
        public void onDraw(Canvas canvas) {
            int j;
            int i;
            int width = getWidth();
            int height = getHeight();
            this.cascadeOffsetX = (((float) width) * this.xscale) / 4.0f;
            this.cascadeOffsetY = (((float) width) * this.yscale) / 4.0f;
            canvas.save();
            if (this.backgroundMode == BACKGROUND_CASCADE) {
                float localCascadeOffsetX = this.cascadeOffsetX
                        / ((float) (this.cascadeNumber + VIEW_ORIGINAL));
                float loxalCascadeOffsetY = this.cascadeOffsetY
                        / ((float) (this.cascadeNumber + VIEW_ORIGINAL));
                for (j = VIEW_SQUARE; j < this.cascadeNumber; j += VIEW_ORIGINAL) {
                    int offsetIndex = j + VIEW_ORIGINAL;
                    this.cascadeRectArray[j]
                            .set(((float) this.offsetX)
                                            + (((float) offsetIndex) * localCascadeOffsetX),
                                    ((float) this.offsetY)
                                            + (((float) offsetIndex) * loxalCascadeOffsetY),
                                    (((float) this.offsetX) + (((float) width) * this.xscale))
                                            - (((float) offsetIndex) * localCascadeOffsetX),
                                    (((float) this.offsetY) + (((float) width) * this.yscale))
                                            - (((float) offsetIndex) * loxalCascadeOffsetY));
                }
            }
            this.drawingAreaRect.set((float) this.offsetX,
                    (float) this.offsetY, ((float) this.offsetX)
                            + (((float) width) * this.xscale),
                    ((float) this.offsetY) + (((float) width) * this.yscale));
            canvas.drawPaint(this.paintGray);
            if (this.backgroundMode == 0) {

                canvas.drawRect(this.drawingAreaRect, this.patternPaint);

            }
            if (!(this.blurBitmap == null || this.blurBitmap.isRecycled() || (this.backgroundMode != VIEW_ORIGINAL && this.backgroundMode != BACKGROUND_CASCADE))) {
                this.blurRectDst.set(this.drawingAreaRect);
                canvas.drawBitmap(this.blurBitmap, this.blurRectSrc,
                        this.blurRectDst, this.paint);

                if (this.backgroundMode == BACKGROUND_CASCADE) {
                    for (i = VIEW_SQUARE; i < this.cascadeNumber; i += VIEW_ORIGINAL) {
                        canvas.drawBitmap(this.blurBitmap, this.blurRectSrc,
                                this.cascadeRectArray[i], this.paint);
                    }
                }
            }
            if (!isScrapBook) {
                canvas.setMatrix(this.sizeMatrix);
            }
            j = VIEW_SQUARE;
            if (!isScrapBook) {
                j = canvas.saveLayer(-Svg.strokeSize, 0.0f, Svg.strokeSize
                        + ((float) width), (float) height, null, 31);
            }
            i = VIEW_SQUARE;
            while (i < ((ShapeLayout) this.shapeLayoutList
                    .get(this.currentCollageIndex)).shapeArr.length) {
                boolean drawPorterClear = false;
                if (i == ((ShapeLayout) this.shapeLayoutList
                        .get(this.currentCollageIndex)).getClearIndex()) {
                    drawPorterClear = true;
                }
                if (isScrapBook) {
                    ((ShapeLayout) this.shapeLayoutList
                            .get(this.currentCollageIndex)).shapeArr[i]
                            .drawShapeForScrapBook(canvas, width, height,
                                    i == this.shapeIndex, this.orthogonal);
                } else {
                    ((ShapeLayout) this.shapeLayoutList
                            .get(this.currentCollageIndex)).shapeArr[i]
                            .drawShape(
                                    canvas,
                                    width,
                                    height,
                                    j,
                                    drawPorterClear,
                                    false,
                                    ((ShapeLayout) this.shapeLayoutList
                                            .get(this.currentCollageIndex)).shapeArr.length == VIEW_ORIGINAL);
                }
                i += VIEW_ORIGINAL;
            }
            if (!isScrapBook
                    && this.shapeIndex >= 0
                    && ((ShapeLayout) this.shapeLayoutList.get(VIEW_SQUARE)).shapeArr.length > VIEW_ORIGINAL) {
                canvas.drawRect(
                        ((ShapeLayout) this.shapeLayoutList
                                .get(this.currentCollageIndex)).shapeArr[this.shapeIndex].bounds,
                        this.borderPaint);
            }
            if (!(this.frameBitmap == null || this.frameBitmap.isRecycled())) {
                canvas.drawBitmap(this.frameBitmap, null, this.frameRect,
                        this.paint);
            }
            if (isScrapBook) {
                canvas.restore();
                this.above.set(0.0f, 0.0f, (float) canvas.getWidth(),
                        this.drawingAreaRect.top);
                this.left.set(0.0f, this.drawingAreaRect.top,
                        this.drawingAreaRect.left, this.drawingAreaRect.bottom);
                this.right.set(this.drawingAreaRect.right,
                        this.drawingAreaRect.top, (float) canvas.getWidth(),
                        this.drawingAreaRect.bottom);
                this.bottom.set(0.0f, this.drawingAreaRect.bottom,
                        (float) canvas.getWidth(), (float) canvas.getHeight());
                canvas.drawRect(this.above, this.paintGray);
                canvas.drawRect(this.left, this.paintGray);
                canvas.drawRect(this.right, this.paintGray);
                canvas.drawRect(this.bottom, this.paintGray);
            } else if (this.offsetX == 0) {
                canvas.restore();
                canvas.setMatrix(this.identityMatrix);
                canvas.drawRect(0.0f, 0.0f, (float) this.screenWidth,
                        (float) this.offsetY, this.paintGray);
                canvas.drawRect((float) this.offsetX, ((float) this.offsetY)
                                + (((float) width) * this.yscale),
                        (float) this.screenWidth, (float) this.screenHeight,
                        this.paintGray);
            } else if (this.offsetY == 0) {
                canvas.restore();
                canvas.setMatrix(this.identityMatrix);
                canvas.drawRect(0.0f, 0.0f, (float) this.offsetX,
                        (float) this.screenHeight, this.paintGray);
                canvas.drawRect(((float) this.offsetX)
                                + (((float) width) * this.xscale),
                        (float) this.offsetY, (float) this.screenWidth,
                        (float) this.screenHeight, this.paintGray);
            } else {
                canvas.restore();
            }
            if (!isScrapBook
                    && this.lineHelpers != null
                    && ((LineHelper) this.lineHelpers
                    .get(this.currentCollageIndex)).useLine) {
                canvas.setMatrix(this.sizeMatrix);
                canvas.translate((float) this.offsetX, (float) this.offsetY);
                ArrayList<GridLine> gridLines = ((LineHelper) this.lineHelpers
                        .get(this.currentCollageIndex)).gridLines;
                if (gridLines != null && !gridLines.isEmpty()) {
                    int q = VIEW_SQUARE;
                    while (q < gridLines.size()) {
                        if (!((GridLine) gridLines.get(q)).isSide) {
                            this.paintLine.setColor(PATTERN_SENTINEL);
                            if (((LineHelper) this.lineHelpers
                                    .get(this.currentCollageIndex)).selectedLine == q) {
                                this.paintLine.setColor(-567676);
                            }
                            canvas.drawCircle(
                                    ((GridLine) gridLines.get(q)).pointHandle.x,
                                    ((GridLine) gridLines.get(q)).pointHandle.y,
                                    this.radiusLineHandle, this.paintLine);
                            canvas.drawCircle(
                                    ((GridLine) gridLines.get(q)).pointHandle.x,
                                    ((GridLine) gridLines.get(q)).pointHandle.y,
                                    this.radiusLineHandle,
                                    this.lineCircleStroke);
                            this.matrixDoubleArrow.reset();
                            this.matrixDoubleArrow
                                    .postTranslate(
                                            ((GridLine) gridLines.get(q)).pointHandle.x
                                                    - ((float) (this.btmDoubleArrow
                                                    .getWidth() / BACKGROUND_CASCADE)),
                                            ((GridLine) gridLines.get(q)).pointHandle.y
                                                    - ((float) (this.btmDoubleArrow
                                                    .getHeight() / BACKGROUND_CASCADE)));
                            this.matrixDoubleArrow
                                    .postRotate(
                                            -((float) (Math
                                                    .atan((double) (1.0f / ((GridLine) gridLines
                                                            .get(q)).slope)) * 57.29577951308232d)),
                                            ((GridLine) gridLines.get(q)).pointHandle.x,
                                            ((GridLine) gridLines.get(q)).pointHandle.y);
                            canvas.drawBitmap(this.btmDoubleArrow,
                                    this.matrixDoubleArrow, this.paintLine);
                        }
                        q += VIEW_ORIGINAL;
                    }
                }
            }
        }

        public boolean onTouchEvent(final MotionEvent motionEvent) {
            this.mScaleDetector.onTouchEvent(motionEvent);
            this.mTouchDetector.onTouchEvent(motionEvent);
            if (isScrapBook) {
                mRotationDetector
                        .onTouchEvent(motionEvent);
            }
            final int action = motionEvent.getAction();
            switch (action & 0xFF) {
                case 0: {
                    this.previousIndex = this.shapeIndex;
                    final float x = motionEvent.getX();
                    final float y = motionEvent.getY();
                    this.mLastTouchX = x;
                    this.mLastTouchY = y;
                    this.orthogonal = false;
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    if (isScrapBook
                            && this.shapeIndex >= 0) {
                        this.zoomStart.set(x, y);
                        this.pts = this.shapeLayoutList
                                .get(this.currentCollageIndex).shapeArr[this.shapeIndex]
                                .getMappedCenter();
                        if (this.pts != null) {
                            this.startAngle = -Utility
                                    .pointToAngle(x, y, this.pts[0], this.pts[1]);
                        }
                        this.isInCircle = this.shapeLayoutList
                                .get(this.currentCollageIndex).shapeArr[this.shapeIndex]
                                .isInCircle(x, y);
                        this.isOnCross = this.shapeLayoutList
                                .get(this.currentCollageIndex).shapeArr[this.shapeIndex]
                                .isOnCross(x, y);
                    } else {
                        this.selectCurrentShape(x, y, false);
                    }
                    if (!isScrapBook
                            && this.lineHelpers != null
                            && !this.lineHelpers.isEmpty()
                            && this.lineHelpers.get(this.currentCollageIndex).useLine) {
                        this.sizeMatrix.getValues(this.vSize);
                        final float n = this.vSize[0];
                        final float n2 = this.vSize[2];
                        final float n3 = this.vSize[5];
                        this.mLastTouchXLine = x - this.offsetX;
                        this.mLastTouchYLine = y - this.offsetY;
                        final float n4 = (x - n2) / n;
                        final float n5 = this.offsetX;
                        final float n6 = (y - n3) / n;
                        final float n7 = this.offsetY;
                        this.lineHelpers.get(this.currentCollageIndex).selectedLine = -1;
                        for (int i = 0; i < this.lineHelpers
                                .get(this.currentCollageIndex).gridLines.size(); ++i) {
                            final GridLine gridLine = (GridLine) this.lineHelpers
                                    .get(this.currentCollageIndex).gridLines.get(i);
                            if (gridLine.isTouchOnHandle(n4 - n5, n6 - n7, 2.0f
                                    * this.radiusLineHandle / n)
                                    && !gridLine.isSide) {
                                this.lineHelpers.get(this.currentCollageIndex).selectedLine = i;
                            }
                        }
                        if (this.lineHelpers.get(this.currentCollageIndex).selectedLine >= 0) {
                            this.unselectShapes();
                        }
                        return true;
                    }
                    break;
                }
                case 2: {
                    if (this.isOnCross) {
                        break;
                    }
                    final int pointerIndex = motionEvent
                            .findPointerIndex(this.mActivePointerId);
                    final float x2 = motionEvent.getX(pointerIndex);
                    final float y2 = motionEvent.getY(pointerIndex);
                    if (!isScrapBook
                            && this.lineHelpers != null
                            && !this.lineHelpers.isEmpty()) {
                        boolean b;
                        if (this.lineHelpers.get(this.currentCollageIndex).useLine
                                && this.lineHelpers.get(this.currentCollageIndex).selectedLine >= 0) {
                            b = true;
                        } else {
                            b = false;
                        }
                        if (b && this.lineHelpers != null
                                && !this.lineHelpers.isEmpty()) {
                            if (this.lineHelpers.get(this.currentCollageIndex).selectedLine >= 0) {
                                this.sizeMatrix.getValues(this.vSize);
                                this.lineHelpers.get(this.currentCollageIndex)
                                        .moveGridLines(
                                                x2 - this.mLastTouchXLine
                                                        - this.offsetX,
                                                y2 - this.mLastTouchYLine
                                                        - this.offsetY);
                                this.mLastTouchXLine = x2 - this.offsetX;
                                this.mLastTouchYLine = y2 - this.offsetY;
                                this.lineHelpers.get(this.currentCollageIndex)
                                        .updateGridLines();
                                for (int j = 0; j < this.shapeLayoutList
                                        .get(this.currentCollageIndex).shapeArr.length; ++j) {
                                    this.shapeLayoutList
                                            .get(this.currentCollageIndex).shapeArr[j]
                                            .updateFromLine(this.shapeLayoutList
                                                    .get(this.currentCollageIndex).shapeArr[j].points);
                                    this.setPathPadding(this.currentCollageIndex,
                                            this.paddingDistance, true);
                                }
                                this.invalidate();
                            }
                            return true;
                        }
                    }
                    if (this.shapeIndex < 0) {
                        this.selectCurrentShape(x2, y2, false);
                    }
                    if (this.shapeIndex < 0) {
                        break;
                    }
                    if (isScrapBook && this.isInCircle) {
                        this.pts = this.shapeLayoutList
                                .get(this.currentCollageIndex).shapeArr[this.shapeIndex]
                                .getMappedCenter();
                        float startAngle = -Utility
                                .pointToAngle(x2, y2, this.pts[0], this.pts[1]);
                        final float matrixRotation = this
                                .getMatrixRotation(this.shapeLayoutList
                                        .get(this.currentCollageIndex).shapeArr[this.shapeIndex].bitmapMatrix);
                        if ((matrixRotation == 0.0f || matrixRotation == 90.0f
                                || matrixRotation == 180.0f
                                || matrixRotation == -180.0f || matrixRotation == -90.0f)
                                && Math.abs(this.startAngle - startAngle) < 4.0f) {
                            this.orthogonal = true;
                        } else {
                            if (Math.abs(matrixRotation - this.startAngle
                                    + startAngle) < 4.0f) {
                                startAngle = this.startAngle - matrixRotation;
                                this.orthogonal = true;
                            } else if (Math.abs(90.0f - (matrixRotation
                                    - this.startAngle + startAngle)) < 4.0f) {
                                startAngle = 90.0f + this.startAngle
                                        - matrixRotation;
                                this.orthogonal = true;
                            } else if (Math.abs(180.0f - (matrixRotation
                                    - this.startAngle + startAngle)) < 4.0f) {
                                startAngle = 180.0f + this.startAngle
                                        - matrixRotation;
                                this.orthogonal = true;
                            } else if (Math
                                    .abs(-180.0f
                                            - (matrixRotation - this.startAngle + startAngle)) < 4.0f) {
                                startAngle = -180.0f + this.startAngle
                                        - matrixRotation;
                                this.orthogonal = true;
                            } else if (Math
                                    .abs(-90.0f
                                            - (matrixRotation - this.startAngle + startAngle)) < 4.0f) {
                                startAngle = -90.0f + this.startAngle
                                        - matrixRotation;
                                this.orthogonal = true;
                            } else {
                                this.orthogonal = false;
                            }
                            this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex]
                                    .bitmapMatrixRotate(this.startAngle
                                            - startAngle);
                            this.startAngle = startAngle;
                        }
                        final float n8 = (float) Math.sqrt((x2 - this.pts[0])
                                * (x2 - this.pts[0]) + (y2 - this.pts[1])
                                * (y2 - this.pts[1]))
                                / (float) Math
                                .sqrt((this.zoomStart.x - this.pts[0])
                                        * (this.zoomStart.x - this.pts[0])
                                        + (this.zoomStart.y - this.pts[1])
                                        * (this.zoomStart.y - this.pts[1]));
                        final float scale = this.shapeLayoutList
                                .get(this.currentCollageIndex).shapeArr[this.shapeIndex]
                                .getScale();
                        if (scale >= this.MIN_ZOOM
                                || (scale < this.MIN_ZOOM && n8 > 1.0f)) {
                            this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex]
                                    .bitmapMatrixScaleScrapBook(n8, n8);
                            this.zoomStart.set(x2, y2);
                        }
                        this.invalidate();
                        ShapeCollageActivity.this
                                .checkDecoareteViewPositions(ShapeCollageActivity.this
                                        .getCurrentMatrixForSticker());
                        return true;
                    }
                    this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex]
                            .bitmapMatrixTranslate(x2 - this.mLastTouchX, y2
                                    - this.mLastTouchY);
                    this.mLastTouchX = x2;
                    this.mLastTouchY = y2;
                    this.invalidate();
                    break;
                }
                case 1: {
                    if (!isScrapBook
                            && this.lineHelpers != null
                            && !this.lineHelpers.isEmpty()
                            && this.lineHelpers.get(this.currentCollageIndex).useLine
                            && this.lineHelpers.get(this.currentCollageIndex).selectedLine >= 0) {
                        this.lineHelpers.get(this.currentCollageIndex)
                                .updateGridLines();
                        for (int k = 0; k < this.shapeLayoutList
                                .get(this.currentCollageIndex).shapeArr.length; ++k) {
                            this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[k]
                                    .updateFromLine(this.shapeLayoutList
                                            .get(this.currentCollageIndex).shapeArr[k].points);
                            this.setPathPadding(this.currentCollageIndex,
                                    this.paddingDistance, true);
                        }
                        this.lineHelpers.get(this.currentCollageIndex).selectedLine = -1;
                    }
                    this.orthogonal = false;
                    this.mActivePointerId = -1;
                    if (this.isOnCross) {
                        createDeleteDialog();
                    }
                    this.isInCircle = false;
                    this.isOnCross = false;
                    this.invalidate();
                    break;
                }
                case 3: {
                    this.mActivePointerId = -1;
                    this.isInCircle = false;
                    this.isOnCross = false;
                    break;
                }
                case 6: {
                    this.finalAngle = 0.0f;
                    final int n9 = (0xFF00 & action) >> 8;
                    if (motionEvent.getPointerId(n9) == this.mActivePointerId) {
                        int n10;
                        if (n9 == 0) {
                            n10 = 1;
                        } else {
                            n10 = 0;
                        }
                        this.mLastTouchX = motionEvent.getX(n10);
                        this.mLastTouchY = motionEvent.getY(n10);
                        this.mActivePointerId = motionEvent.getPointerId(n10);
                        break;
                    }
                    break;
                }
            }

            checkDecoareteViewPositions(ShapeCollageActivity.this
                    .getCurrentMatrixForSticker());
            return true;
        }

        private void selectCurrentShapeScrapBook(float x, float y,
                                                 boolean isSingleTap) {
            int i;
            int length = ((ShapeLayout) this.shapeLayoutList
                    .get(this.currentCollageIndex)).shapeArr.length;
            boolean isSelected = false;
            for (i = length + PATTERN_SENTINEL; i >= 0; i += PATTERN_SENTINEL) {
                if (((ShapeLayout) this.shapeLayoutList
                        .get(this.currentCollageIndex)).shapeArr[i]
                        .isScrapBookSelected(x, y)) {
                    this.shapeIndex = i;
                    isSelected = true;
                    break;
                }
            }
            if (this.previousIndex == this.shapeIndex && isSingleTap) {
                unselectShapes();
            } else if (!isSelected) {
                unselectShapes();
            } else if (selectImageForAdj) {
                openFilterFragment();
            } else if (this.shapeIndex >= 0 && this.shapeIndex < length) {
                Shape shapeTemp = ((ShapeLayout) this.shapeLayoutList
                        .get(this.currentCollageIndex)).shapeArr[this.shapeIndex];
                Bitmap btmTemp = bitmapList[this.shapeIndex];
                Parameter prmTemp = parameterList[this.shapeIndex];
                for (i = VIEW_SQUARE; i < length; i += VIEW_ORIGINAL) {
                    if (i >= this.shapeIndex) {
                        if (i < length + PATTERN_SENTINEL) {
                            ((ShapeLayout) this.shapeLayoutList
                                    .get(this.currentCollageIndex)).shapeArr[i] = ((ShapeLayout) this.shapeLayoutList
                                    .get(this.currentCollageIndex)).shapeArr[i
                                    + VIEW_ORIGINAL];
                            bitmapList[i] = bitmapList[i
                                    + VIEW_ORIGINAL];
                            parameterList[i] = parameterList[i
                                    + VIEW_ORIGINAL];
                        } else {
                            ((ShapeLayout) this.shapeLayoutList
                                    .get(this.currentCollageIndex)).shapeArr[i] = shapeTemp;
                            bitmapList[i] = btmTemp;
                            parameterList[i] = prmTemp;
                        }
                    }
                }
                if (this.previousIndex == this.shapeIndex) {
                    this.previousIndex = length + PATTERN_SENTINEL;
                } else if (this.previousIndex > this.shapeIndex) {
                    this.previousIndex += PATTERN_SENTINEL;
                }
                this.shapeIndex = length + PATTERN_SENTINEL;
                if (this.shapeIndex >= 0
                        && ((ShapeLayout) this.shapeLayoutList.get(VIEW_SQUARE)).shapeArr.length > 0) {
                    contextFooter
                            .setVisibility(VIEW_SQUARE);
                    ShapeCollageActivity.this
                            .setSelectedTab(ShapeCollageActivity.INDEX_COLLAGE_INVISIBLE_VIEW);
                }
            }
            if (this.shapeIndex >= 0) {
                ((ShapeLayout) this.shapeLayoutList
                        .get(this.currentCollageIndex)).shapeArr[this.shapeIndex]
                        .bitmapMatrixgGetValues(this.matrixValues);
                this.mScaleFactor = this.matrixValues[VIEW_SQUARE];
            }
            postInvalidate();
        }

        private void selectCurrentShape(float x, float y, boolean isSingleTap) {
           /* if (isScrapBook) {
                selectCurrentShapeScrapBook(x, y, isSingleTap);
            } else {
                selectCurrentShapeCollage(x, y, isSingleTap);
            }*/
        }

        private void selectCurrentShapeCollage(float x, float y,
                                               boolean isSingleTap) {
            int swapIndex = this.shapeIndex;
            for (int i = VIEW_SQUARE; i < ((ShapeLayout) this.shapeLayoutList
                    .get(this.currentCollageIndex)).shapeArr.length; i += VIEW_ORIGINAL) {
                if (((ShapeLayout) this.shapeLayoutList
                        .get(this.currentCollageIndex)).shapeArr[i].region
                        .contains((int) x, (int) y)) {
                    this.shapeIndex = i;
                }
            }
            if (selectImageForAdj) {
                openFilterFragment();
            } else if (swapMode) {
                if (swapIndex != this.shapeIndex
                        && swapIndex > PATTERN_SENTINEL
                        && this.shapeIndex > PATTERN_SENTINEL) {
                    swapBitmaps(this.shapeIndex, swapIndex);
                    swapMode = false;
                }
            } else if (this.previousIndex == this.shapeIndex && isSingleTap) {
                unselectShapes();
            } else if (this.shapeIndex >= 0
                    && ((ShapeLayout) this.shapeLayoutList.get(VIEW_SQUARE)).shapeArr.length > 0) {
                contextFooter
                        .setVisibility(VIEW_SQUARE);
                ShapeCollageActivity.this
                        .setSelectedTab(ShapeCollageActivity.INDEX_COLLAGE_INVISIBLE_VIEW);
            }
            if (this.shapeIndex >= 0) {
                ((ShapeLayout) this.shapeLayoutList
                        .get(this.currentCollageIndex)).shapeArr[this.shapeIndex]
                        .bitmapMatrixgGetValues(this.matrixValues);
                this.mScaleFactor = this.matrixValues[VIEW_SQUARE];
            }
            postInvalidate();
        }

        @SuppressLint("WrongConstant")
        void unselectShapes() {
            contextFooter
                    .setVisibility(ShapeCollageActivity.INDEX_COLLAGE_BLUR);
            this.shapeIndex = PATTERN_SENTINEL;
            postInvalidate();
        }

        @SuppressLint("WrongConstant")
        public void openFilterFragment() {
            selectFilterTextView
                    .setVisibility(ShapeCollageActivity.INDEX_COLLAGE_BLUR);
            selectImageForAdj = false;
            if (this.shapeIndex >= 0) {
                fullEffectFragment
                        .setBitmapWithParameter(
                                bitmapList[this.shapeIndex],
                                parameterList[this.shapeIndex]);
                ShapeCollageActivity.this
                        .setVisibilityOfFilterHorizontalListview(true);
            }
        }

        float getMatrixRotation(Matrix matrix) {
            matrix.getValues(this.values);
            return (float) Math.round(Math.atan2(
                    (double) this.values[VIEW_ORIGINAL],
                    (double) this.values[VIEW_SQUARE]) * 57.29577951308232d);
        }

        public void setBlurBitmap(int radius, boolean cascade) {
            if (this.blurBuilderNormal == null) {

                this.blurBuilderNormal = new BlurBuilderNormal();
            }
            if (cascade) {
                this.backgroundMode = BACKGROUND_CASCADE;
                if (!isScrapBook) {
                    seekbarSize.setProgress(45);
                }

            } else {
                this.backgroundMode = VIEW_ORIGINAL;
                seekbarSize.setProgress(45);
            }

            if (patternHelper == null
                    || patternHelper.bitmapBlur == null
                    || patternHelper.bitmapBlur
                    .isRecycled() || radius != 0) {

                if (patternHelper == null
                        || patternHelper.bitmapBlur == null
                        || patternHelper.bitmapBlur
                        .isRecycled()) {
                    if (VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1
                            && Utility.backImg) {

                        this.blurBitmap = this.blurBuilderNormal
                                .createBlurBitmapNDK(
                                        patternHelper.bitmapBlur,
                                        radius);

                    } else {

                        this.blurBitmap = this.blurBuilderNormal
                                .createBlurBitmapNDK(
                                        bitmapList[VIEW_SQUARE],
                                        radius);

                    }
                } else {
                    if (Utility.SET_PREV_IMG) {
                        this.blurBitmap = this.blurBuilderNormal
                                .createBlurBitmapNDK(
                                        bitmapList[0],
                                        radius);
                    } else {
                        this.blurBitmap = this.blurBuilderNormal
                                .createBlurBitmapNDK(
                                        patternHelper.bitmapBlur,
                                        radius);
                    }
                }
                blurRadius = this.blurBuilderNormal
                        .getBlur();
            } else {
                this.blurBitmap = patternHelper.bitmapBlur;
                blurRadius = VIEW_SQUARE;

            }
            if (this.blurBitmap != null) {
                setBlurRect2((float) this.blurBitmap.getWidth(),
                        (float) this.blurBitmap.getHeight());
            }
            postInvalidate();
        }

        void setBlurRect2(float btmwidth, float btmheight) {
            float w;
            float h;
            if ((mulY * btmwidth)
                    / mulX < btmheight) {
                w = btmwidth;
                h = (mulY * btmwidth)
                        / mulX;
            } else {
                w = (mulX * btmheight)
                        / mulY;
                h = btmheight;
            }
            int l = (int) ((btmwidth - w) / 2.0f);
            int t = (int) ((btmheight - h) / 2.0f);
            this.blurRectSrc.set(l, t, (int) (((float) l) + w),
                    (int) (((float) t) + h));
        }

        public void setCropBitmap(int left, int top, int right, int bottom) {
            if (this.shapeIndex >= 0) {
                Bitmap sourceBitmap = bitmapList[this.shapeIndex];
                boolean isFilter = sourceBitmap != ((ShapeLayout) this.shapeLayoutList
                        .get(VIEW_SQUARE)).shapeArr[this.shapeIndex]
                        .getBitmap();
                if (isFilter) {
                    doCrop(left, top, right, bottom, sourceBitmap, false, false);
                    doCrop(left,
                            top,
                            right,
                            bottom,
                            ((ShapeLayout) this.shapeLayoutList
                                    .get(VIEW_SQUARE)).shapeArr[this.shapeIndex]
                                    .getBitmap(), true, true);
                } else {
                    doCrop(left, top, right, bottom, sourceBitmap, false, true);
                }
                if (!(!isFilter
                        || parameterList == null || parameterList[this.shapeIndex] == null)) {
                    parameterList[this.shapeIndex]
                            .setId(Parameter.uniqueId.getAndIncrement());
                }
                invalidate();
            }
        }

        public void doCrop(int left, int top, int right, int bottom,
                           Bitmap sourceBitmap, boolean isFilter, boolean last) {
            Bitmap localCropBtm = sourceBitmap;
            int bitmapWidth = sourceBitmap.getWidth();
            int bitmapHeight = sourceBitmap.getHeight();
            if (right > bitmapWidth) {
                right = bitmapWidth;
            }
            if (bottom > bitmapHeight) {
                bottom = bitmapHeight;
            }
            if (right - left > 0 && bottom - top > 0) {
                if (VERSION.SDK_INT < 12) {
                    sourceBitmap = BlurBuilderNormal.createCroppedBitmap(
                            localCropBtm, left, top, right - left,
                            bottom - top, false);
                } else {
                    sourceBitmap = Bitmap.createBitmap(localCropBtm, left, top,
                            right - left, bottom - top);
                }
                if (localCropBtm != sourceBitmap) {
                    localCropBtm.recycle();
                }
                if (!isFilter) {
                    bitmapList[this.shapeIndex] = sourceBitmap;
                }
                if (last) {
                    for (int i = VIEW_SQUARE; i < this.shapeLayoutList.size(); i += VIEW_ORIGINAL) {
                        ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[this.shapeIndex]
                                .setBitmap(sourceBitmap, false);
                        if (isScrapBook) {
                            ((ShapeLayout) this.shapeLayoutList.get(i)).shapeArr[this.shapeIndex]
                                    .resetDashPaths();
                        }
                    }
                }
            }
        }
    }

    final class MyMediaScannerConnectionClient implements
            MediaScannerConnectionClient {
        private MediaScannerConnection mConn;
        private String mFilename;
        private String mMimetype;

        public MyMediaScannerConnectionClient(Context ctx, File file,
                                              String mimetype) {
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

    class CurrentIndexChanger implements MyAdapter.CurrentCollageIndexChangedListener {
        CurrentIndexChanger() {
        }

        public void onIndexChanged(int position) {
            collageView
                    .setCurrentCollageIndex(position);
        }
    }

    class BitmapColorChanger implements PatternHelper.PatternBitmapColorListener {
        BitmapColorChanger() {
        }

        public void patternBitmapReady(int index, Bitmap bitmap) {
            collageView
                    .setPatternPaint(index, bitmap);

        }

        public void patternColorReady(int color) {
            collageView.setPatternPaintColor(color);
        }

        public void setBacgkroundMode() {
            collageView.backgroundMode = ShapeCollageActivity.INDEX_COLLAGE;
        }

        public void patternImageReady(Bitmap bitmap) {
            if (bitmap != null) {
                if (collageView == null) {
                    bgImageIsWaiting = true;
                } else {
                    bgImageIsWaiting = false;
                }
                if (!(collageView == null || collageView.blurBitmap == null)) {
                    collageView.blurBitmap.recycle();
                }
                if (collageView != null) {

                    collageView.setBlurBitmap(
                            blurRadius, false);
                    collageView.backgroundMode = ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND;
                    collageView.invalidate();
                }
            }
        }
    }

    class BitmapWorkerTask extends MyAsyncTask2<Bundle, Void, Void> {
        int arraySize;
        Bundle data;
        ProgressDialog progressDialog;
        Bundle savedInstanceState;
        private Bitmap tmpBitmap;

        class HierarchyChangerListener implements OnHierarchyChangeListener {

            class DecorateTouchUp implements DecorateView.OnDecorateViewTouchUp {
                DecorateTouchUp() {
                }

                public void onTouchUp(BaseData mData) {
                    Matrix m = ShapeCollageActivity.this
                            .getCurrentMatrixForSticker();
                    if (m != null) {
                        mData.setImageSaveMatrix(m);
                    }
                }
            }

            HierarchyChangerListener() {
            }

            public void onChildViewAdded(View parent, View child) {
                ((DecorateView) child)
                        .setOnDecorateViewTouchUp(new DecorateTouchUp());
            }

            public void onChildViewRemoved(View parent, View child) {
            }
        }

        BitmapWorkerTask() {
        }

        protected void onPreExecute() {
            this.progressDialog = new ProgressDialog(
                    context);
            this.progressDialog.setCancelable(false);
            this.progressDialog.setMessage(ShapeCollageActivity.this
                    .getString(R.string.collage_lib_loading_message));
            this.progressDialog.show();
        }

        protected Void doInBackground(Bundle... params) {

            int i;
            this.data = params[ShapeCollageActivity.INDEX_COLLAGE];
            this.savedInstanceState = params[ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND];
            isScrapBook = this.data.getBoolean(
                    "is_scrap_book", false);
            isShape = this.data.getBoolean(
                    "is_shape", false);
            long[] selectedImageList = this.data.getLongArray("photo_id_list");
            int[] selectedImageOrientationList = this.data
                    .getIntArray("photo_orientation_list");
            this.arraySize = ShapeCollageActivity.INDEX_COLLAGE;
            collage_shape_flag = this.data.getBoolean(
                    Utility.COLLAGE_SHAPE, false);
            countshape = this.data.getInt("grid_item_no");
            shapeValCount = this.data.getInt("two_shape");

            boolValForShape = this.data.getBoolean(
                    Utility.FRO_SHAPE_NAME, false);
            shapeVal = this.data.getBoolean(
                    Utility.FOR_GET_GALLERY_IMG,
                    false);

            int maxDivider;
            if (selectedImageList == null) {

                this.arraySize = 1;
                bitmapList = new Bitmap[1];
                maxDivider = this.arraySize;
                if (maxDivider < ShapeCollageActivity.INDEX_COLLAGE_RATIO) {
                    maxDivider = ShapeCollageActivity.INDEX_COLLAGE_RATIO;
                }
                int loadingImageError1 = ShapeCollageActivity.INDEX_COLLAGE;
                for (i = ShapeCollageActivity.INDEX_COLLAGE; i < this.arraySize; i += ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND) {

                    Bitmap bitmap = BitmapFactory.decodeResource(
                            getResources(), R.drawable.bg1);
                    if (bitmap != null) {
                        if (Utility.ShapeSelectbitmap == null) {
                            tmpBitmap = bitmap;
                        } else {
                            tmpBitmap = Utility.ShapeSelectbitmap;
                        }

                        bitmapList[i] = tmpBitmap;
                        if (boolValForShape) {
                            ivPreImg.setVisibility(View.VISIBLE);
                            ivPreImg.setImageBitmap(bitmapList[i]);
                        } else {
                            ivPreImg.setVisibility(View.GONE);
                        }
                    } else {
                        loadingImageError1 += ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND;
                    }
                }
                if (loadingImageError1 > 0) {
                    int newSize = this.arraySize - loadingImageError1;
                    Bitmap[] arr = new Bitmap[newSize];
                    int j = ShapeCollageActivity.INDEX_COLLAGE;
                    for (i = ShapeCollageActivity.INDEX_COLLAGE; i < this.arraySize; i += ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND) {
                        if (bitmapList[i] != null) {
                            arr[j] = bitmapList[i];
                            j += ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND;
                        }
                    }
                    this.arraySize = newSize;
                    bitmapList = arr;
                }

            } else {
                addImg.setVisibility(View.GONE);
                this.arraySize = selectedImageList.length;
                bitmapList = new Bitmap[this.arraySize];
                maxDivider = this.arraySize;
                if (maxDivider < ShapeCollageActivity.INDEX_COLLAGE_RATIO) {
                    maxDivider = ShapeCollageActivity.INDEX_COLLAGE_RATIO;
                }
                int requiredSize = Utility.maxSizeForDimension(
                        context, maxDivider,
                        ShapeCollageActivity.UPPER_SIZE_FOR_LOAD);
                int loadingImageError = ShapeCollageActivity.INDEX_COLLAGE;
                for (i = ShapeCollageActivity.INDEX_COLLAGE; i < this.arraySize; i += ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND) {

                    Log.i(TAG, "doInBackground: " + selectedImageList[i]);
                    Log.i(TAG, "doInBackground:--> " + selectedImageOrientationList[i]);


                    Bitmap bitmap = Utility
                            .getScaledBitmapFromId(
                                    context,
                                    selectedImageList[i],
                                    selectedImageOrientationList[i],
                                    requiredSize,
                                    isScrapBook);
                    if (bitmap != null) {
                        bitmapList[i] = bitmap;
                        Log.i(TAG, "doInBackground: " + bitmap.toString());
                        Log.i(TAG, "doInBackground:<---> " + bitmapList.length);
                        if (boolValForShape) {
                            ivPreImg.setVisibility(View.VISIBLE);
                            ivPreImg.setImageBitmap(bitmapList[i]);
                        } else {
                            ivPreImg.setVisibility(View.GONE);
                        }
                    } else {
                        loadingImageError += ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND;
                    }
                }

                if (loadingImageError > 0) {
                    int newSize = this.arraySize - loadingImageError;
                    Bitmap[] arr = new Bitmap[newSize];
                    int j = ShapeCollageActivity.INDEX_COLLAGE;
                    for (i = ShapeCollageActivity.INDEX_COLLAGE; i < this.arraySize; i += ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND) {
                        if (bitmapList[i] != null) {
                            arr[j] = bitmapList[i];
                            j += ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND;
                        }
                    }
                    this.arraySize = newSize;
                    bitmapList = arr;
                }
            }
            parameterList = new Parameter[this.arraySize];
            for (i = ShapeCollageActivity.INDEX_COLLAGE; i < parameterList.length; i += ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND) {
                parameterList[i] = new Parameter();
            }
            return null;
        }

        @SuppressLint("WrongConstant")
        protected void onPostExecute(Void bitmap) {
            try {
                this.progressDialog.dismiss();
            } catch (Exception e) {
            }
            if (this.arraySize <= 0) {

                @SuppressLint("WrongConstant") Toast msg = Toast.makeText(context,
                        R.string.collage_lib_loading_error_message,
                        ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND);
                msg.setGravity(17, msg.getXOffset()
                                / ShapeCollageActivity.INDEX_COLLAGE_SPACE,
                        msg.getYOffset()
                                / ShapeCollageActivity.INDEX_COLLAGE_SPACE);
                msg.show();
                finish();
                return;
            }
            if (!isScrapBook) {
                ShapeCollageActivity.isGridLayoutLocked = PreferenceManager
                        .getDefaultSharedPreferences(
                                context).getBoolean(
                                "is_grid_locked2",
                                ShapeCollageActivity.isGridLayoutLocked);
            }

            if (coll.collageIconArray[bitmapList.length - 1] != collageAdapter.iconList) {
                collageAdapter
                        .setData(coll.collageIconArray[bitmapList.length - 1]);
                collageAdapter.notifyDataSetChanged();
            }
            if (isScrapBook) {
                btmDelete = BitmapFactory
                        .decodeResource(
                                getResources(),
                                R.drawable.scrapbook_remove);
                btmScale = BitmapFactory
                        .decodeResource(
                                getResources(),
                                R.drawable.scrapbook_scale);
            }
            if (isScrapBook) {
                npd = (NinePatchDrawable) ContextCompat
                        .getDrawable(context,
                                R.drawable.shadow_7);
            }
            collageView = new CollageView(
                    context,
                    width,
                    height);
            mainLayout = (RelativeLayout) ShapeCollageActivity.this
                    .findViewById(R.id.collage_main_layout_for_setall);
            mainLayout
                    .addView(collageView);
            slideLeftIn = AnimationUtils
                    .loadAnimation(activity,
                            R.anim.slide_in_left);
            slideLeftOut = AnimationUtils
                    .loadAnimation(activity,
                            R.anim.slide_out_left);
            slideRightIn = AnimationUtils
                    .loadAnimation(activity,
                            R.anim.slide_in_right);
            slideRightOut = AnimationUtils
                    .loadAnimation(activity,
                            R.anim.slide_out_right);
            addEffectFragment();
            if (this.arraySize == ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND
                    && !isScrapBook) {
                setVisibilityForSingleImage();
            } else {
                blurRadius = ShapeCollageActivity.INDEX_COLLAGE;
            }
            if (isScrapBook) {
                setVisibilityForScrapbook();
            }
            textAndStickerViewContainer = (FrameLayout) ShapeCollageActivity.this
                    .findViewById(R.id.sticker_view_container);
            textAndStickerViewContainer
                    .bringToFront();
            ((HorizontalScrollView) ShapeCollageActivity.this
                    .findViewById(R.id.collage_footer)).bringToFront();
            if (viewFlipper == null) {
                viewFlipper = (ViewFlipper) ShapeCollageActivity.this
                        .findViewById(R.id.collage_view_flipper);
            }
            viewFlipper.bringToFront();
            findViewById(R.id.rv_tool)
                    .bringToFront();
            contextFooter = (ViewGroup) ShapeCollageActivity.this
                    .findViewById(R.id.collage_context_menu);
            contextFooter.bringToFront();
            selectSwapTextView = ShapeCollageActivity.this
                    .findViewById(R.id.select_image_swap);
            selectSwapTextView.bringToFront();
            selectSwapTextView
                    .setVisibility(ShapeCollageActivity.INDEX_COLLAGE_BLUR);
            selectFilterTextView = ShapeCollageActivity.this
                    .findViewById(R.id.select_image_filter);
            selectFilterTextView.bringToFront();
            selectFilterTextView
                    .setVisibility(ShapeCollageActivity.INDEX_COLLAGE_BLUR);
            textAndStickerViewContainer
                    .setOnHierarchyChangeListener(new HierarchyChangerListener());
            findViewById(
                    textFragemntContinerId)
                    .bringToFront();
            textLibHelper = new TextLibHelper();
            findViewById(
                    stickerFragemntContinerId)
                    .bringToFront();
            stickerLibHelper = new StickerLibHelper();
            if (this.savedInstanceState != null) {
                textLibHelper.hideForOncreate(
                        activityFragment,
                        textAndStickerViewContainer,
                        textFragemntContinerId);
                stickerLibHelper.hideForOncreate(
                        activityFragment,
                        textAndStickerViewContainer);
            }
        }
    }

    String mResultPath;

    private class SaveImageTask extends MyAsyncTask2<Object, Object, Object> {
        //        ProgressDialog progressDialog;
        String resultPath;
        int saveMode;

        private SaveImageTask() {
            this.saveMode = ShapeCollageActivity.INDEX_COLLAGE;
            this.resultPath = null;
        }

        protected Object doInBackground(Object... arg0) {
            if (arg0 != null) {
                this.saveMode = ((Integer) arg0[ShapeCollageActivity.INDEX_COLLAGE])
                        .intValue();
            }
            this.resultPath = collageView.saveBitmap(
                    width,
                    height);
            return null;
        }

        protected void onPreExecute() {
//            this.progressDialog = new ProgressDialog(
//                    ShapeCollageActivity.this.context);
//            this.progressDialog.setMessage(ShapeCollageActivity.this
//                    .getString(R.string.collage_lib_saving_message));
//            this.progressDialog.show();
        }

        @SuppressLint("WrongConstant")
        protected void onPostExecute(Object result) {
            Toast msg;

            String string;
            if (this.saveMode == 0
                    || this.saveMode == ShapeCollageActivity.INDEX_COLLAGE_BLUR) {
                super.onPostExecute(result);
                Context context;
                string = ShapeCollageActivity.this
                        .getString(R.string.save_image_lib_image_saved_message);
                Object[] objArr = new Object[ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND];
                objArr[ShapeCollageActivity.INDEX_COLLAGE] = ShapeCollageActivity.this
                        .getString(R.string.directory);
                msg = Toast.makeText(getApplicationContext(), String.format(string, objArr),
                        ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND);
                msg.setGravity(17, msg.getXOffset()
                                / ShapeCollageActivity.INDEX_COLLAGE_SPACE,
                        msg.getYOffset()
                                / ShapeCollageActivity.INDEX_COLLAGE_SPACE);
                msg.show();
                if (this.saveMode == ShapeCollageActivity.INDEX_COLLAGE_BLUR) {
                    finish();
                }
            } else if (this.saveMode == ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND) {
                super.onPostExecute(result);
                try {
                    Intent picMessageIntent = new Intent(
                            "android.intent.action.SEND");
                    picMessageIntent.setFlags(268435456);
                    picMessageIntent.setType("image/jpeg");
                    if (this.resultPath != null) {
                        picMessageIntent.putExtra(
                                "android.intent.extra.STREAM",
                                Uri.fromFile(new File(this.resultPath)));
                        ShapeCollageActivity.this
                                .startActivity(picMessageIntent);
                    }
                } catch (Exception e2) {
                    msg = Toast.makeText(context,
                            R.string.collage_lib_no_email_message,
                            ShapeCollageActivity.INDEX_COLLAGE_BACKGROUND);
                    msg.setGravity(17, msg.getXOffset()
                                    / ShapeCollageActivity.INDEX_COLLAGE_SPACE,
                            msg.getYOffset()
                                    / ShapeCollageActivity.INDEX_COLLAGE_SPACE);
                    msg.show();
                }
            } else if (this.saveMode == ShapeCollageActivity.INDEX_COLLAGE_RATIO) {
                if (this.resultPath != null) {
                    mResultPath = resultPath;
                    Toast.makeText(activityFragment, "Image Saved !", Toast.LENGTH_SHORT).show();

                    if (VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        final Uri contentUri = Uri.fromFile(new File(mResultPath));
                        scanIntent.setData(contentUri);
                        sendBroadcast(scanIntent);
                    } else {
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"
                                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath() + "/Photo Poster Maker")));

                    }
                    //  loadShareActivity();

                }
            }
        }
    }

    public ShapeCollageActivity() {
        this.activity = this;
        this.activityFragment = this;
        this.selectImageForAdj = false;
        this.isScrapBook = false;
        this.blurRadius = 14;
        this.isShape = false;
        this.defaultSizeProgressForBlur = 45;
        this.btmDelete = null;
        this.btmScale = null;
        this.textFragemntContinerId = R.id.collage_text_view_fragment_container;
        this.stickerFragemntContinerId = R.id.sticker_grid_fragment_container;
        this.screenDensity = 1.0f;
        this.bgImageIsWaiting = false;
        this.mSeekBarListener = new SeekbarChangeManager();
        this.context = this;
        this.isGridLockedKey = "is_grid_locked2";
        this.swapMode = false;
        this.mulX = 1.0f;
        this.mulY = 1.0f;
        this.dialogClickListener = new DialogClickListener();
        this.RATIO_BUTTON_SIZE = 11;
        this.f1265m = new Matrix();
        this.cropListener = new FragmentCrop.CropListener() {
            public void cropCancelled() {
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(cropFragment)
                        .commitAllowingStateLoss();
            }

            public void cropApply(int leftPos, int topPos, int rightPos,
                                  int bottomPos) {
                collageView.setCropBitmap(leftPos,
                        topPos, rightPos, bottomPos);
                cropFragment.setBitmap(null);
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(cropFragment)
                        .commitAllowingStateLoss();
            }
        };
    }

    static {
        TAG = ShapeCollageActivity.class.getSimpleName();
        isGridLayoutLocked = false;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(INDEX_COLLAGE_BACKGROUND);
        getWindow().addFlags(
                AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        Display display = getWindowManager().getDefaultDisplay();
        this.width = display.getWidth();
        this.height = display.getHeight();
        Utility.logFreeMemory(this.context);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        this.topOffset = metrics.density * 92.0f;
        this.totalOffset = metrics.density * 140.0f;
        this.screenDensity = metrics.density;
        setContentView(R.layout.activity_collage);


        bindView();
        init(savedInstanceState);
        addListener();

        if (Utility.flag_from_create_img) {
            Utility.ShapeSelectbitmap = null;
        }

        setSelectedTab(INDEX_COLLAGE);

    }


    private void bindView() {

        this.seekBarRound = (SeekBar) findViewById(R.id.seekbar_round);
        this.seekBarPadding = (SeekBar) findViewById(R.id.seekbar_padding);
        this.seekbarSize = (SeekBar) findViewById(R.id.seekbar_size);
        this.seekbarBlur = (SeekBar) findViewById(R.id.seekbar_collage_blur);
        this.seekbarCascadeBlur = (SeekBar) findViewById(R.id.seekbar_collage_blur_cascade);
        this.seekbarCascadeNumber = (SeekBar) findViewById(R.id.seekbar_cascade_number);
        this.seekBarBorder = (SeekBar) findViewById(R.id.seekbar_collage_border);
        this.collageRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_grid);
        this.addImg = (ImageView) findViewById(R.id.addimgView);
        this.recyclerViewPattern = (RecyclerView) findViewById(R.id.recyclerView_pattern);
        this.viewFlipper = (ViewFlipper) findViewById(R.id.collage_view_flipper);
        this.colorContainer = (LinearLayout) findViewById(R.id.color_container);
        this.recyclerViewInnerPattern = (RecyclerView) findViewById(R.id.recyclerView_color);
        this.originalInstagram = (Button) findViewById(R.id.button_collage_screen_mode);
        button_collage_layout = (Button) findViewById(R.id.button_collage_layout);
        button_mirror_sticker = (Button) findViewById(R.id.button_mirror_sticker);
        button_collage_border = (Button) findViewById(R.id.button_collage_border);
        button_collage_blur = (Button) findViewById(R.id.button_collage_blur);
        button_collage_cascade = (Button) findViewById(R.id.button_collage_cascade);
        button_collage_background = (Button) findViewById(R.id.button_collage_background);
        button_collage_space = (Button) findViewById(R.id.button_collage_space);
        button_collage_ratio = (Button) findViewById(R.id.button_collage_ratio);
        button_mirror_text = (Button) findViewById(R.id.button_mirror_text);
        button_collage_adj = (Button) findViewById(R.id.button_collage_adj);
        button_collage_context_swap = (Button) findViewById(R.id.button_collage_context_swap);
        button_collage_context_delete = (Button) findViewById(R.id.button_collage_context_delete);
        button_collage_context_add = (Button) findViewById(R.id.button_collage_context_add);
        button_collage_context_center = (Button) findViewById(R.id.button_collage_context_center);
        button_collage_context_fit = (Button) findViewById(R.id.button_collage_context_fit);
        button_collage_context_filter = (Button) findViewById(R.id.button_collage_context_filter);
        button_collage_context_crop = (Button) findViewById(R.id.button_collage_context_crop);
        button_collage_context_rotate_left = (Button) findViewById(R.id.button_collage_context_rotate_left);
        button_collage_context_rotate_right = (Button) findViewById(R.id.button_collage_context_rotate_right);
        button_collage_context_flip_horizontal = (Button) findViewById(R.id.button_collage_context_flip_horizontal);
        button_collage_context_flip_vertical = (Button) findViewById(R.id.button_collage_context_flip_vertical);
        button_collage_context_rotate_negative = (Button) findViewById(R.id.button_collage_context_rotate_negative);
        button_collage_context_rotate_positive = (Button) findViewById(R.id.button_collage_context_rotate_positive);
        button_collage_context_zoom_in = (Button) findViewById(R.id.button_collage_context_zoom_in);
        button_collage_context_zoom_out = (Button) findViewById(R.id.button_collage_context_zoom_out);
        button_collage_context_move_left = (Button) findViewById(R.id.button_collage_context_move_left);
        button_collage_context_move_right = (Button) findViewById(R.id.button_collage_context_move_right);
        button_collage_context_move_up = (Button) findViewById(R.id.button_collage_context_move_up);
        button_collage_context_move_down = (Button) findViewById(R.id.button_collage_context_move_down);
        tvselect_image_swap_text = (TextView) findViewById(R.id.select_image_swap_text);
        select_image_filter_text = (TextView) findViewById(R.id.select_image_filter_text);
        ivPreImg = (ImageView) findViewById(R.id.btn_pre_img);
        this.horizontalScrollView = (HorizontalScrollView) findViewById(R.id.collage_footer);

    }

    @SuppressWarnings("deprecation")
    private void init(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        int arraySize = getCollageSize(extras);

        this.colorDefault = getResources().getColor(R.color.view_flipper_bg_color);
        this.colorSelected = getResources().getColor(R.color.footer_button_color_pressed);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);

        layoutManager.setOrientation(INDEX_COLLAGE);
        this.collageRecyclerView.setLayoutManager(layoutManager);

        this.collageAdapter = new MyAdapter(
                coll.collageIconArray[arraySize - 1],
                (MyAdapter.CurrentCollageIndexChangedListener) new MyAdapter.CurrentCollageIndexChangedListener() {
                    @Override
                    public void onIndexChanged(final int currentCollageIndex) {
                        collageView
                                .setCurrentCollageIndex(currentCollageIndex);
                        countshape = currentCollageIndex;

                    }
                }, this.colorDefault, this.colorSelected, false, true);
        this.collageRecyclerView
                .setAdapter((RecyclerView.Adapter) this.collageAdapter);
        this.collageAdapter = new MyAdapter(
                coll.collageIconArray[arraySize - 1],
                new CurrentIndexChanger(), this.colorDefault,
                this.colorSelected, false, true);
        this.collageRecyclerView.setAdapter(this.collageAdapter);
        this.collageRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.viewFlipper.setDisplayedChild(INDEX_COLLAGE_INVISIBLE_VIEW);

        LinearLayoutManager layoutManagerPattern = new LinearLayoutManager(
                this.context);
        layoutManagerPattern.setOrientation(INDEX_COLLAGE);

        recyclerViewPattern.setLayoutManager(layoutManagerPattern);
        patternHelperInit();
        recyclerViewPattern.setAdapter(this.patternHelper.patternAdapter);
        recyclerViewPattern.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager layoutManagerColor = new LinearLayoutManager(
                this.context);
        layoutManagerColor.setOrientation(INDEX_COLLAGE);
        this.recyclerViewInnerPattern.setLayoutManager(layoutManagerColor);

        horizontalScrollView.bringToFront();
        horizontalScrollView.postDelayed(
                new PostDelayLess(horizontalScrollView), 350);
        horizontalScrollView.postDelayed(
                new PostDelayMore(horizontalScrollView), 1500);

        BitmapWorkerTask bitmapWorkerTask = new BitmapWorkerTask();
        bundleArr = new Bundle[INDEX_COLLAGE_SPACE];
        bundleArr[INDEX_COLLAGE] = extras;
        bundleArr[INDEX_COLLAGE_BACKGROUND] = savedInstanceState;
        bitmapWorkerTask.execute(bundleArr);
        boolValForShape = getFromShape(extras);
        setFont();
    }

    private void addListener() {
        this.seekBarRound.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekBarPadding.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekbarSize.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekbarBlur.setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekbarCascadeBlur
                .setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekbarCascadeNumber
                .setOnSeekBarChangeListener(this.mSeekBarListener);
        this.seekBarBorder.setOnSeekBarChangeListener(this.mSeekBarListener);
        addImg.setOnClickListener(this);
        ivPreImg.setOnClickListener(this);
    }

    private void setFont() {
        type = Typeface.createFromAsset(getAssets(), "roboto.regular.ttf");
        originalInstagram.setTypeface(type);
        button_collage_layout.setTypeface(type);
        button_mirror_sticker.setTypeface(type);
        button_collage_border.setTypeface(type);
        button_collage_blur.setTypeface(type);
        button_collage_cascade.setTypeface(type);
        button_collage_background.setTypeface(type);
        button_collage_space.setTypeface(type);
        button_collage_ratio.setTypeface(type);
        button_mirror_text.setTypeface(type);
        button_collage_adj.setTypeface(type);
        tvselect_image_swap_text.setTypeface(type);
        select_image_filter_text.setTypeface(type);
        button_collage_context_filter.setTypeface(type);
        button_collage_context_crop.setTypeface(type);
        button_collage_context_rotate_left.setTypeface(type);
        button_collage_context_rotate_right.setTypeface(type);
        button_collage_context_flip_horizontal.setTypeface(type);
        button_collage_context_flip_vertical.setTypeface(type);
        button_collage_context_rotate_negative.setTypeface(type);
        button_collage_context_rotate_positive.setTypeface(type);
        button_collage_context_zoom_in.setTypeface(type);
        button_collage_context_zoom_out.setTypeface(type);
        button_collage_context_move_left.setTypeface(type);
        button_collage_context_move_right.setTypeface(type);
        button_collage_context_move_up.setTypeface(type);
    }

    void patternHelperInit() {
        if (this.recyclerViewInnerPattern == null) {
            this.recyclerViewInnerPattern = (RecyclerView) findViewById(R.id.recyclerView_color);
            LinearLayoutManager layoutManagerColor = new LinearLayoutManager(
                    this.context);
            layoutManagerColor.setOrientation(INDEX_COLLAGE);
            this.recyclerViewInnerPattern.setLayoutManager(layoutManagerColor);
        }
        this.patternHelper = new PatternHelper(this, new BitmapColorChanger(),
                this.colorContainer, this.recyclerViewInnerPattern,
                this.colorDefault, this.colorSelected);
        this.patternHelper.createPatternAdapter(this, this.colorDefault,
                this.colorSelected);
    }

    @SuppressLint("WrongConstant")
    private void setVisibilityForSingleImage() {
        this.findViewById(R.id.seekbar_corner_container).setVisibility(8);
        this.findViewById(R.id.seekbar_space_container).setVisibility(8);
        button_collage_blur.setVisibility(0);
        button_collage_context_delete.setVisibility(8);
        button_collage_context_add.setVisibility(8);
        button_collage_context_move_down.setVisibility(8);
        button_collage_cascade.setVisibility(0);
        originalInstagram.setVisibility(0);
        button_collage_border.setVisibility(0);
        if (!this.isScrapBook) {
            this.collageView.setCollageSize(0, this.collageView.sizeMatrix, 45,
                    false);
            if (this.seekbarSize != null) {
                this.seekbarSize.setProgress(45);
            }
        }
        this.collageView.setBlurBitmap(this.blurRadius, false);
        if (this.isShape) {
            this.setSelectedTab(0);
        } else if (!this.isScrapBook) {
            this.setSelectedTab(2);
        }
        if (this.screenDensity <= 0.0f) {
            this.screenDensity = 1.0f;
        }
        Svg.strokeSize = 9.0f / this.screenDensity;
    }

    @SuppressLint("WrongConstant")
    private void setVisibilityForScrapbook() {
        button_collage_layout.setVisibility(TAB_SIZE);
        button_collage_space.setVisibility(TAB_SIZE);
        button_collage_context_swap.setVisibility(TAB_SIZE);
        button_collage_context_fit.setVisibility(TAB_SIZE);
        button_collage_context_center.setVisibility(TAB_SIZE);
        button_collage_context_delete.setVisibility(TAB_SIZE);
        button_collage_context_add.setVisibility(TAB_SIZE);
    }

    public static boolean hasHardwareAcceleration(Activity activity) {
        Window window = activity.getWindow();
        if (window != null
                && (window.getAttributes().flags & ViewCompat.MEASURED_STATE_TOO_SMALL) != 0) {
            return true;
        }
        try {
            if ((activity.getPackageManager().getActivityInfo(
                    activity.getComponentName(), INDEX_COLLAGE).flags) != 0) {
                return true;
            }
        } catch (NameNotFoundException e) {
        }
        return false;
    }

    int getCollageSize(Bundle extras) {
        long[] selectedImageList = extras.getLongArray("photo_id_list");
        if (selectedImageList == null) {
            return INDEX_COLLAGE_BACKGROUND;
        }
        return selectedImageList.length;
    }

    Boolean getFromShape(Bundle extras) {
        boolValForShape = extras.getBoolean(
                Utility.FRO_SHAPE_NAME, false);
        return boolValForShape;
    }

    @SuppressLint("WrongConstant")
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);

        if (n == 99 && n2 == -1) {

            imageUri = intent.getData();
            Utility.ShapeSelectbitmap = null;
            try {
                Intent in = new Intent(ShapeCollageActivity.this,
                        ShapeCollageActivity.class);

                Bitmap b = MediaStore.Images.Media.getBitmap(
                        this.getContentResolver(), imageUri);

                Utility.ShapeSelectbitmap = BlurBuilderNormal
                        .createScaledBitmap(b, width, height, false);

                in.putExtra(
                        Utility.FOR_GET_GALLERY_IMG,
                        true);
                in.putExtra("grid_item_no", countshape);
                in.putExtra(Utility.FRO_SHAPE_NAME,
                        true);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                finish();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (n == 68 && n2 == -1) {

            imageUri = intent.getData();

            Utility.updateBitmap = null;
            try {
                Utility.updateBitmap = MediaStore.Images.Media
                        .getBitmap(this.getContentResolver(), imageUri);
                bitmapList[collageView.shapeIndex] = Utility.updateBitmap;

                collageView
                        .updateShapeListForFilterBitmap(Utility.updateBitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (n == 244 && n2 == -1) {
            if (this.patternHelper == null) {
                this.patternHelperInit();
            }
            if (this.patternHelper != null) {
                this.patternHelper.activityResult(n2, intent);
                final View viewById = this
                        .findViewById(R.id.button_collage_blur);
                if (viewById != null && viewById.getVisibility() != 0) {
                    this.seekbarBlur.setProgress(this.blurRadius * 4);
                    viewById.setVisibility(0);
                }
                this.setSelectedTab(4);
            }
        }
    }

    public void onPause() {
        super.onPause();
        Editor editor = PreferenceManager.getDefaultSharedPreferences(this)
                .edit();
        editor.putBoolean("is_grid_locked2", isGridLayoutLocked);
        editor.commit();
    }

//    public void onDestroy() {
//        int i;
//        super.onDestroy();
//        if (this.bitmapList != null) {
//            for (i = INDEX_COLLAGE; i < this.bitmapList.length; i += INDEX_COLLAGE_BACKGROUND) {
//                if (this.bitmapList[i] != null) {
//                    this.bitmapList[i].recycle();
//                }
//            }
//        }
//        if (this.collageView != null) {
//            if (this.collageView.shapeLayoutList != null) {
//                for (i = INDEX_COLLAGE; i < this.collageView.shapeLayoutList
//                        .size(); i += INDEX_COLLAGE_BACKGROUND) {
//                    for (int j = INDEX_COLLAGE; j < ((ShapeLayout) this.collageView.shapeLayoutList
//                            .get(i)).shapeArr.length; j += INDEX_COLLAGE_BACKGROUND) {
//                        if (((ShapeLayout) this.collageView.shapeLayoutList
//                                .get(i)).shapeArr[j] != null) {
//                            ((ShapeLayout) this.collageView.shapeLayoutList
//                                    .get(i)).shapeArr[j].freeBitmaps();
//                        }
//                    }
//                }
//            }
//            if (this.collageView.maskBitmapArray != null) {
//                i = INDEX_COLLAGE;
//                while (i < this.collageView.maskBitmapArray.length) {
//                    if (this.collageView.maskBitmapArray[i] != null) {
//                        if (!(this.collageView.maskBitmapArray[i] == null || this.collageView.maskBitmapArray[i]
//                                .isRecycled())) {
//                            this.collageView.maskBitmapArray[i].recycle();
//                        }
//                        this.collageView.maskBitmapArray[i] = null;
//                    }
//                    i += INDEX_COLLAGE_BACKGROUND;
//                }
//            }
//        }
//        if (!(this.patternHelper == null || this.patternHelper.bitmapBlur == null)) {
//            this.patternHelper.bitmapBlur.recycle();
//        }
//        if (!(this.collageView == null || this.collageView.blurBuilderNormal == null)) {
//            this.collageView.blurBuilderNormal.destroy();
//        }
//        if (Utility.ShapeSelectbitmap != null
//                && boolValForShape) {
//            Utility.ShapeSelectbitmap = null;
//        }
//
//
//    }


    void setSelectedTab(final int n) {
        if (this.viewFlipper != null) {

            this.setTabBg(0);
            final int displayedChild = this.viewFlipper.getDisplayedChild();
            if (displayedChild != 1) {
                this.hideColorContainer();
            }
            if (n == 0) {

                if (displayedChild != 0) {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                    this.viewFlipper.setDisplayedChild(0);
                } else {
                    this.viewFlipper.setDisplayedChild(-1);
                    setTabBg(-1);
                    return;
                }
            }

            if (n == 1) {
                this.setTabBg(1);
                if (displayedChild != 1) {
                    this.viewFlipper.setDisplayedChild(1);
                } else {
                    this.viewFlipper.setDisplayedChild(-1);
                    setTabBg(-1);
                    return;
                }
            }
            if (displayedChild == 0) {
                this.viewFlipper.setInAnimation(this.slideRightIn);
                this.viewFlipper.setOutAnimation(this.slideLeftOut);
            } else {
                this.viewFlipper.setInAnimation(this.slideLeftIn);
                this.viewFlipper.setOutAnimation(this.slideRightOut);
            }
            if (n == 4) {
                this.setTabBg(4);
                if (displayedChild != 4) {
                    this.viewFlipper.setDisplayedChild(4);
                } else {
                    this.viewFlipper.setDisplayedChild(-1);
                    setTabBg(-1);
                    return;
                }
                if (displayedChild == 0) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                }
            }
            if (n == 5) {
                this.setTabBg(5);
                if (displayedChild != 5) {
                    this.viewFlipper.setDisplayedChild(5);
                } else {
                    this.viewFlipper.setDisplayedChild(-1);
                    setTabBg(-1);
                    return;
                }
                if (displayedChild < 5) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                }
            }
            if (n == 6) {
                this.setTabBg(6);
                if (displayedChild != 6) {
                    this.viewFlipper.setDisplayedChild(6);
                } else {
                    this.viewFlipper.setDisplayedChild(-1);
                    setTabBg(-1);
                    return;
                }
                if (displayedChild < 6) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                }
            }
            if (n == 2) {
                this.setTabBg(2);
                if (displayedChild != 2) {
                    this.viewFlipper.setDisplayedChild(2);
                } else {
                    this.viewFlipper.setDisplayedChild(-1);
                    setTabBg(-1);
                    return;
                }
                if (displayedChild == 0 || displayedChild == 1) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                }
            }
            if (n == 3) {
                this.setTabBg(3);
                if (displayedChild != 3) {
                    this.viewFlipper.setDisplayedChild(3);
                } else {
                    this.viewFlipper.setDisplayedChild(-1);
                    setTabBg(-1);
                    return;
                }
                if (displayedChild == 7) {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                }
            }
            if (n == 7) {
                this.setTabBg(-1);
                if (displayedChild != 7) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    this.viewFlipper.setDisplayedChild(7);
                }
            }
        }
    }

    private void setTabBg(int index) {
        if (this.tabButtonList == null) {
            this.tabButtonList = new View[TAB_SIZE];
            this.tabButtonList[INDEX_COLLAGE] = button_collage_layout;
            this.tabButtonList[INDEX_COLLAGE_SPACE] = button_collage_space;
            this.tabButtonList[INDEX_COLLAGE_BLUR] = button_collage_blur;
            this.tabButtonList[INDEX_COLLAGE_BACKGROUND] = button_collage_background;
            this.tabButtonList[INDEX_COLLAGE_RATIO] = button_collage_ratio;
            this.tabButtonList[INDEX_COLLAGE_CASCADE] = button_collage_cascade;
            this.tabButtonList[INDEX_COLLAGE_BORDER] = button_collage_border;
            this.tabButtonList[INDEX_COLLAGE_INVISIBLE_VIEW] = button_collage_adj;
        }
        for (int i = INDEX_COLLAGE; i < this.tabButtonList.length; i += INDEX_COLLAGE_BACKGROUND) {

            this.tabButtonList[i].setBackgroundResource(R.drawable.collage_footer_button);

        }
        if (index >= 0) {
            this.tabButtonList[index]
                    .setBackgroundResource(R.color.footer_button_color_pressed);
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (this.textLibHelper != null) {
            this.textLibHelper.saveTextAndStickerDataInstance(
                    savedInstanceState, this.textAndStickerViewContainer, null);
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    @SuppressLint("WrongConstant")
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
            this.textAndStickerViewContainer
                    .setOnHierarchyChangeListener(new HierarchyChanger());
            ((HorizontalScrollView) findViewById(R.id.collage_footer))
                    .bringToFront();
            if (this.viewFlipper == null) {
                this.viewFlipper = (ViewFlipper) findViewById(R.id.collage_view_flipper);
            }
            if (this.viewFlipper != null) {
                this.viewFlipper.bringToFront();
            }
            View header = findViewById(R.id.rv_tool);
            if (header != null) {
                header.bringToFront();
            }
            if (this.contextFooter == null) {
                this.contextFooter = (ViewGroup) findViewById(R.id.collage_context_menu);
            }
            if (this.contextFooter != null) {
                this.contextFooter.bringToFront();
            }
            this.selectSwapTextView = findViewById(R.id.select_image_swap);
            if (this.selectSwapTextView != null) {
                this.selectSwapTextView.bringToFront();
                this.selectSwapTextView.setVisibility(INDEX_COLLAGE_BLUR);
            }
            this.selectFilterTextView = findViewById(R.id.select_image_filter);
            if (this.selectFilterTextView != null) {
                this.selectFilterTextView.bringToFront();
                this.selectFilterTextView.setVisibility(INDEX_COLLAGE_BLUR);
            }
        }
        if (this.textLibHelper != null) {
            this.textLibHelper.loadTextAndStickerDataFromSavedInstance(
                    this.activityFragment, savedInstanceState,
                    this.textAndStickerViewContainer,
                    this.textFragemntContinerId, null);
        }
    }

    @SuppressLint("ResourceType")
    public void myClickHandler(View view) {
        int id = view.getId();
        if (id == R.id.button_collage_layout) {
            //     setSelectedTab(INDEX_COLLAGE);
        } else if (id == R.id.button_collage_ratio) {
            setSelectedTab(INDEX_COLLAGE_RATIO);
        } else if (id == R.id.button_collage_blur) {
            if (!isColor) {
                setSelectedTab(INDEX_COLLAGE_BLUR);
                this.collageView.setBlurBitmap(this.blurRadius, false);
            } else {
                Toast.makeText(getApplicationContext(),
                        "Blur not set in color background", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (id == R.id.button_collage_cascade) {
            if (!isColor) {
                this.collageView.setBlurBitmap(this.blurRadius, true);
                setSelectedTab(INDEX_COLLAGE_CASCADE);
            } else {
                Toast.makeText(getApplicationContext(),
                        "Casacade not set in color background",
                        Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.button_collage_background) {
            setSelectedTab(INDEX_COLLAGE_BACKGROUND);
        } else if (id == R.id.button_collage_space) {
            setSelectedTab(INDEX_COLLAGE_SPACE);
        } else if (id == R.id.button_collage_border) {
            setSelectedTab(INDEX_COLLAGE_BORDER);
        } else if (id == R.id.button_collage_adj) {
            if (((ShapeLayout) this.collageView.shapeLayoutList
                    .get(INDEX_COLLAGE)).shapeArr.length == INDEX_COLLAGE_BACKGROUND) {
                this.collageView.shapeIndex = INDEX_COLLAGE;
                this.collageView.openFilterFragment();
            } else if (this.collageView.shapeIndex >= 0) {
                this.collageView.openFilterFragment();
            } else {
                setSelectedTab(INDEX_COLLAGE_INVISIBLE_VIEW);
                this.selectFilterTextView.setVisibility(INDEX_COLLAGE);
                this.selectImageForAdj = true;
            }
        } else if (id == R.id.button_collage_context_swap) {
            if (((ShapeLayout) this.collageView.shapeLayoutList
                    .get(this.collageView.currentCollageIndex)).shapeArr.length == INDEX_COLLAGE_SPACE) {
                this.collageView.swapBitmaps(INDEX_COLLAGE,
                        INDEX_COLLAGE_BACKGROUND);
            } else {
                this.selectSwapTextView.setVisibility(INDEX_COLLAGE);
                this.swapMode = true;
            }
        } else if (id == R.id.button_collage_context_delete) {
            createDeleteDialog();
        } else if (id == R.id.button_collage_context_add) {
            collageView.updateBitmap(
                    collageView.shapeIndex,
                    width,
                    width);
        } else if (id == R.id.button_collage_context_crop) {
            if (this.collageView != null && this.collageView.shapeIndex >= 0) {
                addCropFragment(((ShapeLayout) this.collageView.shapeLayoutList
                        .get(INDEX_COLLAGE)).shapeArr[this.collageView.shapeIndex]
                        .getBitmap());
            }
        } else if (id == R.id.button_collage_context_filter) {
            this.collageView.openFilterFragment();
        } else if (id == R.id.tv_save) {

            setSelectedTab(INDEX_COLLAGE_INVISIBLE_VIEW);
            if (collage_shape_flag) {
                SaveImageTask saveImageTask = new SaveImageTask();
                Object[] objArr = new Object[INDEX_COLLAGE_BACKGROUND];
                objArr[INDEX_COLLAGE] = Integer.valueOf(INDEX_COLLAGE_RATIO);
                saveImageTask.execute(objArr);
            } else {
                if (shapeVal) {
                    SaveImageTask saveImageTask = new SaveImageTask();
                    Object[] objArr = new Object[INDEX_COLLAGE_BACKGROUND];
                    objArr[INDEX_COLLAGE] = Integer
                            .valueOf(INDEX_COLLAGE_RATIO);
                    saveImageTask.execute(objArr);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Plz select Image then save", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        } else if (id == R.id.tv_back) {
            backButtonAlertBuilder();
        } else if (id == R.id.button11) {
            this.mulX = 1.0f;
            this.mulY = 1.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(INDEX_COLLAGE);
        } else if (id == R.id.button21) {
            this.mulX = 2.0f;
            this.mulY = 1.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(INDEX_COLLAGE_BACKGROUND);
        } else if (id == R.id.button12) {
            this.mulX = 1.0f;
            this.mulY = 2.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(INDEX_COLLAGE_SPACE);
        } else if (id == R.id.button32) {
            this.mulX = 3.0f;
            this.mulY = 2.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(INDEX_COLLAGE_RATIO);
        } else if (id == R.id.button23) {
            this.mulX = 2.0f;
            this.mulY = 3.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(INDEX_COLLAGE_BLUR);
        } else if (id == R.id.button43) {
            this.mulX = 4.0f;
            this.mulY = 3.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(INDEX_COLLAGE_CASCADE);
        } else if (id == R.id.button34) {
            this.mulX = 3.0f;
            this.mulY = 4.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(INDEX_COLLAGE_BORDER);
        } else if (id == R.id.button45) {
            this.mulX = 4.0f;
            this.mulY = 5.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(INDEX_COLLAGE_INVISIBLE_VIEW);
        } else if (id == R.id.button57) {
            this.mulX = 5.0f;
            this.mulY = 7.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(TAB_SIZE);
        } else if (id == R.id.button169) {
            this.mulX = 16.0f;
            this.mulY = 9.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(9);
        } else if (id == R.id.button916) {
            this.mulX = 9.0f;
            this.mulY = 16.0f;
            this.collageView.updateShapeListForRatio(this.width, this.height);
            setRatioButtonBg(10);
        } else if (id == R.id.hide_select_image_warning) {
            this.selectSwapTextView.setVisibility(INDEX_COLLAGE_BLUR);
            this.swapMode = false;
        } else if (id == R.id.hide_select_image_warning_filter) {
            this.selectFilterTextView.setVisibility(INDEX_COLLAGE_BLUR);
            this.selectImageForAdj = false;
        } else if (id == R.id.hide_color_container) {
            hideColorContainer();
        } else if (id == R.id.button_mirror_text) {
            if (this.textLibHelper == null) {
                this.textLibHelper = new TextLibHelper();
            }
            this.textLibHelper.addCanvasText2(this.activityFragment,
                    this.textAndStickerViewContainer,
                    this.textFragemntContinerId);
            clearViewFlipper();
        } else if (id == R.id.button_mirror_sticker) {
            if (this.stickerLibHelper == null) {
                this.stickerLibHelper = new StickerLibHelper();
            }
            this.stickerLibHelper.addStickerGalleryFragment(
                    this.activityFragment, this.textAndStickerViewContainer,
                    this.stickerFragemntContinerId);
        } else if (id == R.id.addimgView) {
        }
        if (id == R.id.button_collage_context_fit) {
            this.collageView.setShapeScaleMatrix(INDEX_COLLAGE);
        } else if (id == R.id.button_collage_context_center) {
            this.collageView.setShapeScaleMatrix(INDEX_COLLAGE_BACKGROUND);
        } else if (id == R.id.button_collage_context_rotate_left) {
            this.collageView.setShapeScaleMatrix(INDEX_COLLAGE_RATIO);
        } else if (id == R.id.button_collage_context_rotate_right) {
            this.collageView.setShapeScaleMatrix(INDEX_COLLAGE_SPACE);
        } else if (id == R.id.button_collage_context_flip_horizontal) {
            this.collageView.setShapeScaleMatrix(INDEX_COLLAGE_BLUR);
        } else if (id == R.id.button_collage_context_flip_vertical) {
            this.collageView.setShapeScaleMatrix(INDEX_COLLAGE_CASCADE);
        } else if (id == R.id.button_collage_context_rotate_negative) {
            this.collageView.setShapeScaleMatrix(INDEX_COLLAGE_BORDER);
        } else if (id == R.id.button_collage_context_rotate_positive) {
            this.collageView.setShapeScaleMatrix(INDEX_COLLAGE_INVISIBLE_VIEW);
        } else if (id == R.id.button_collage_context_zoom_in) {
            toastMatrixMessage(this.collageView.setShapeScaleMatrix(TAB_SIZE));
        } else if (id == R.id.button_collage_context_zoom_out) {
            toastMatrixMessage(this.collageView.setShapeScaleMatrix(9));
        } else if (id == R.id.button_collage_context_move_left) {
            toastMatrixMessage(this.collageView.setShapeScaleMatrix(10));
        } else if (id == R.id.button_collage_context_move_right) {
            toastMatrixMessage(this.collageView.setShapeScaleMatrix(11));
        } else if (id == R.id.button_collage_context_move_up) {
            toastMatrixMessage(this.collageView.setShapeScaleMatrix(12));
        } else if (id == R.id.button_collage_context_move_down) {
            toastMatrixMessage(this.collageView.setShapeScaleMatrix(13));
        } else if (id == R.id.button_collage_pattern_image) {
            final Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction("android.intent.action.PICK");
            this.patternHelper.selectImage();
            if (VERSION.SDK_INT >= 11) {
                intent.putExtra("android.intent.extra.LOCAL_ONLY", true);
            }
            startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"),
                    PatternHelper.SELCT_IMAGE_BG);
        } else if (id == R.id.button_collage_screen_mode) {
            String msg;
            this.collageView.changeViewSizeMode();
            if (this.originalInstagram == null) {
                this.originalInstagram = (Button) findViewById(R.id.button_collage_screen_mode);
            }
            int i = this.collageView.viewSizeMode;
            if (i == 0) {
                this.originalInstagram.setCompoundDrawablesWithIntrinsicBounds(
                        INDEX_COLLAGE, R.drawable.collage_mode_original,
                        INDEX_COLLAGE, INDEX_COLLAGE);
                msg = getString(R.string.instagram);
                this.originalInstagram.setText(R.string.hdr_fx_original);
                setRatioButtonBg(INDEX_COLLAGE);
                msg = msg + " 1:1";
            } else {
                this.originalInstagram.setCompoundDrawablesWithIntrinsicBounds(
                        INDEX_COLLAGE, R.drawable.collage_mode_instagram,
                        INDEX_COLLAGE, INDEX_COLLAGE);
                this.originalInstagram.setText(R.string.instagram);
            }

        } else if (this.fullEffectFragment != null
                && this.fullEffectFragment.isVisible()) {
            this.fullEffectFragment.myClickHandler(view);
        }

        GalleryUtility.logHeap();
        Utility.logFreeMemory(this.context);

    }

    void handleLock() {
        isGridLayoutLocked = !isGridLayoutLocked;
        this.collageView.setLockScales();
        checkDecoareteViewPositions(getCurrentMatrixForSticker());
        this.collageView.invalidate();
    }


    void toastMatrixMessage(int message) {
        String str = null;
        if (message == INDEX_COLLAGE_BACKGROUND) {
            str = getString(R.string.collage_lib_maximum_zoom);
        } else if (message == INDEX_COLLAGE_SPACE) {
            str = getString(R.string.collage_lib_minimum_zoom);
        } else if (message == INDEX_COLLAGE_BORDER) {
            str = getString(R.string.collage_lib_max_bottom);
        } else if (message == INDEX_COLLAGE_CASCADE) {
            str = getString(R.string.collage_lib_max_top);
        } else if (message == INDEX_COLLAGE_BLUR) {
            str = getString(R.string.collage_lib_max_right);
        } else if (message == INDEX_COLLAGE_RATIO) {
            str = getString(R.string.collage_lib_max_left);
        }
        if (str != null) {
        }
    }

    @SuppressLint("ResourceType")
    void createDeleteDialog() {

        if (((ShapeLayout) this.collageView.shapeLayoutList.get(INDEX_COLLAGE)).shapeArr.length == INDEX_COLLAGE_BACKGROUND) {

            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setMessage(R.string.collage_lib_delete_message)
                .setCancelable(true)
                .setPositiveButton(getString(17039379),
                        new PositiveButtonClick())
                .setNegativeButton(getString(17039369),
                        new NegativeButtonClick());
        this.saveImageAlert = builder.create();
        this.saveImageAlert.show();
    }

    void clearViewFlipper() {
        this.viewFlipper.setDisplayedChild(INDEX_COLLAGE_INVISIBLE_VIEW);
        setTabBg(-1);
    }

    @SuppressLint("WrongConstant")
    private void hideColorContainer() {
        if (this.colorContainer == null) {
            this.colorContainer = (LinearLayout) findViewById(R.id.color_container);
        }
        this.colorContainer.setVisibility(INDEX_COLLAGE_BLUR);
    }

    private void setRatioButtonBg(int index) {
        if (this.ratioButtonArray == null) {
            this.ratioButtonArray = new Button[this.RATIO_BUTTON_SIZE];
            this.ratioButtonArray[INDEX_COLLAGE] = (Button) findViewById(R.id.button11);
            this.ratioButtonArray[INDEX_COLLAGE_BACKGROUND] = (Button) findViewById(R.id.button21);
            this.ratioButtonArray[INDEX_COLLAGE_SPACE] = (Button) findViewById(R.id.button12);
            this.ratioButtonArray[INDEX_COLLAGE_RATIO] = (Button) findViewById(R.id.button32);
            this.ratioButtonArray[INDEX_COLLAGE_BLUR] = (Button) findViewById(R.id.button23);
            this.ratioButtonArray[INDEX_COLLAGE_CASCADE] = (Button) findViewById(R.id.button43);
            this.ratioButtonArray[INDEX_COLLAGE_BORDER] = (Button) findViewById(R.id.button34);
            this.ratioButtonArray[INDEX_COLLAGE_INVISIBLE_VIEW] = (Button) findViewById(R.id.button45);
            this.ratioButtonArray[TAB_SIZE] = (Button) findViewById(R.id.button57);
            this.ratioButtonArray[9] = (Button) findViewById(R.id.button169);
            this.ratioButtonArray[10] = (Button) findViewById(R.id.button916);

        }
        for (int i = INDEX_COLLAGE; i < this.RATIO_BUTTON_SIZE; i += INDEX_COLLAGE_BACKGROUND) {
            this.ratioButtonArray[i]
                    .setBackgroundResource(R.drawable.selector_collage_ratio_button);
            this.ratioButtonArray[i].setTypeface(type);
        }
        this.ratioButtonArray[index]
                .setBackgroundResource(R.drawable.collage_ratio_bg_pressed);
    }

    void addEffectFragment() {
        if (this.fullEffectFragment == null) {
            this.fullEffectFragment = (FullEffectFragment) getSupportFragmentManager()
                    .findFragmentByTag("FULL_FRAGMENT");
            if (this.fullEffectFragment == null) {
                this.fullEffectFragment = new FullEffectFragment();
                this.fullEffectFragment.setArguments(getIntent().getExtras());
                try {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.collage_effect_fragment_container,
                                    this.fullEffectFragment, "FULL_FRAGMENT")
                            .commitAllowingStateLoss();
                } catch (Exception e) {
                }
            } else if (this.collageView.shapeIndex >= 0) {
                this.fullEffectFragment.setBitmapWithParameter(
                        this.bitmapList[this.collageView.shapeIndex],
                        this.parameterList[this.collageView.shapeIndex]);
            }
            try {
                getSupportFragmentManager().beginTransaction()
                        .hide(this.fullEffectFragment)
                        .commitAllowingStateLoss();
            } catch (Exception e2) {
            }
            this.fullEffectFragment
                    .setFullBitmapReadyListener(new FullEffectFragment.FullBitmapReady() {
                        public void onBitmapReady(Bitmap bitmap,
                                                  Parameter parameter) {
                            collageView
                                    .updateShapeListForFilterBitmap(bitmap);
                            collageView
                                    .updateParamList(parameter);
                            collageView
                                    .postInvalidate();
                            ShapeCollageActivity.this
                                    .getSupportFragmentManager()
                                    .beginTransaction()
                                    .hide(fullEffectFragment)
                                    .commitAllowingStateLoss();
                            collageView
                                    .postInvalidate();
                        }

                        public void onCancel() {
                            ShapeCollageActivity.this
                                    .setVisibilityOfFilterHorizontalListview(false);
                            collageView
                                    .postInvalidate();
                        }
                    });
            findViewById(R.id.collage_effect_fragment_container).bringToFront();
        }
    }

    void setVisibilityOfFilterHorizontalListview(boolean show) {
        if (show && this.fullEffectFragment.isHidden()) {
            getSupportFragmentManager().beginTransaction()
                    .show(this.fullEffectFragment).commitAllowingStateLoss();
        }
        if (!show && this.fullEffectFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(this.fullEffectFragment).commitAllowingStateLoss();
        }
        findViewById(R.id.collage_effect_fragment_container).bringToFront();
    }

    public void onBackPressed() {

        if (this.stickerLibHelper == null) {
            this.stickerLibHelper = new StickerLibHelper();
        }
        if (this.textLibHelper == null) {
            this.textLibHelper = new TextLibHelper();
        }
        if (!this.textLibHelper.removeOnBackPressed(this.activityFragment)
                && PatternHelper.onBackPressed(this)) {
        }
        if (this.cropFragment != null && this.cropFragment.isVisible()) {
            this.cropFragment.onBackPressed();
        } else if (this.textLibHelper != null
                && this.textLibHelper.hideOnBackPressed(this.activityFragment)) {
        } else {
            if (this.stickerLibHelper != null
                    && this.stickerLibHelper.hideOnBackPressed()) {
                return;
            }
            if (this.textAndStickerViewContainer != null
                    && TextLibHelper
                    .onBackPressedForDecorateViewSelection(this.textAndStickerViewContainer)) {
                return;
            }
            if (this.fullEffectFragment == null
                    || !this.fullEffectFragment.isVisible()) {
                if (this.colorContainer.getVisibility() == View.VISIBLE) {
                    hideColorContainer();
                } else if (this.swapMode) {
                    this.selectSwapTextView.setVisibility(View.INVISIBLE);
                    this.swapMode = false;
                } else if (this.collageView != null
                        && this.collageView.shapeIndex >= 0) {
                    this.collageView.unselectShapes();
                } else if (this.selectImageForAdj) {
                    this.selectFilterTextView.setVisibility(View.INVISIBLE);
                    this.selectImageForAdj = false;
                }/* else if (this.viewFlipper != null
                        && this.viewFlipper.getDisplayedChild() != INDEX_COLLAGE_INVISIBLE_VIEW) {
                    setSelectedTab(INDEX_COLLAGE_INVISIBLE_VIEW);
                }*/ else if (this.contextFooter == null
                        || this.contextFooter.getVisibility() != View.VISIBLE) {
                    backButtonAlertBuilder();
                } else {
                    this.contextFooter.setVisibility(View.INVISIBLE);
                }
            } else if (!this.fullEffectFragment.onBackPressed()) {
                setVisibilityOfFilterHorizontalListview(false);
            }
        }
    }

    @SuppressLint("ResourceType")
    private void backButtonAlertBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setMessage("Would you like to save image ?")
                .setCancelable(true)
                .setPositiveButton("Yes", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (collage_shape_flag) {
                            SaveImageTask saveImageTask = new SaveImageTask();
                            Object[] objArr = new Object[INDEX_COLLAGE_BACKGROUND];
                            objArr[INDEX_COLLAGE] = Integer.valueOf(INDEX_COLLAGE_RATIO);
                            saveImageTask.execute(objArr);
                        } else {
                            if (shapeVal) {
                                SaveImageTask saveImageTask = new SaveImageTask();
                                Object[] objArr = new Object[INDEX_COLLAGE_BACKGROUND];
                                objArr[INDEX_COLLAGE] = Integer.valueOf(INDEX_COLLAGE_RATIO);
                                saveImageTask.execute(objArr);
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Plz select Image then save",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .setNegativeButton("No",
                        new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (collage_shape_flag) {
                                    activity.finish();
                                }
                                if (shapeVal == true || boolValForShape) {
                                    Intent intentGridShape = new Intent(ShapeCollageActivity.this,
                                            MainActivity.class);
                                    intentGridShape.putExtra(Utility.CHOOSE_FRAME_SHAPE, Utility.FOR_SHAPE_CODE);
                                    startActivity(intentGridShape);
                                    finish();
                                }
                            }
                        });
        this.saveImageAlert = builder.create();
        this.saveImageAlert.show();
    }

    void checkDecoareteViewPositions(Matrix cvsMtrx) {
        if (cvsMtrx != null && this.textAndStickerViewContainer != null
                && this.textAndStickerViewContainer.getChildCount() > 0) {
            int size = this.textAndStickerViewContainer.getChildCount();
            for (int i = INDEX_COLLAGE; i < size; i += INDEX_COLLAGE_BACKGROUND) {
                DecorateView decorateView = (DecorateView) this.textAndStickerViewContainer
                        .getChildAt(i);
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

    Matrix getCurrentMatrixForSticker() {
        if (this.collageView == null
                || this.collageView.shapeLayoutList == null
                || ((ShapeLayout) this.collageView.shapeLayoutList
                .get(this.collageView.currentCollageIndex)).shapeArr == null
                || ((ShapeLayout) this.collageView.shapeLayoutList
                .get(this.collageView.currentCollageIndex)).shapeArr[INDEX_COLLAGE] == null) {
            return null;
        }
        if (((ShapeLayout) this.collageView.shapeLayoutList
                .get(this.collageView.currentCollageIndex)).shapeArr.length > INDEX_COLLAGE_BACKGROUND) {
            return null;
        }
        this.f1265m
                .set(((ShapeLayout) this.collageView.shapeLayoutList
                        .get(this.collageView.currentCollageIndex)).shapeArr[INDEX_COLLAGE].bitmapMatrix);
        this.f1265m.postConcat(this.collageView.sizeMatrix);
        return this.f1265m;
    }

    void addCropFragment(Bitmap sourceBitmap) {
        ((FrameLayout) findViewById(R.id.crop_fragment_container))
                .bringToFront();
        this.cropFragment = (FragmentCrop) getSupportFragmentManager()
                .findFragmentByTag("crop_fragment");
        if (this.cropFragment == null) {
            this.cropFragment = new FragmentCrop();
            this.cropFragment.setCropListener(this.cropListener);
            this.cropFragment.setBitmap(sourceBitmap);
            this.cropFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.crop_fragment_container, this.cropFragment,
                            "crop_fragment").commitAllowingStateLoss();
            return;
        }
        this.cropFragment.setCropListener(this.cropListener);
        this.cropFragment.setBitmap(sourceBitmap);
    }

    @Override
    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case R.id.addimgView:
                onGalleryClicked();
                Utility.flag_from_create_img = false;
                break;

            case R.id.btn_pre_img:
                Utility.SET_PREV_IMG = true;
                isColor = false;
                this.collageView.setBlurBitmap(this.blurRadius, false);
                break;

            default:
                break;
        }
    }

    private void onGalleryClicked() {

        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.PICK");
        if (VERSION.SDK_INT >= 11) {
            intent.putExtra("android.intent.extra.LOCAL_ONLY", true);
        }
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 99);
    }

    private void onGalleryClickedForUpdate() {
        Intent var1 = new Intent("android.intent.action.GET_CONTENT");
        var1.setType("image/*");
        this.startActivityForResult(
                Intent.createChooser(var1, "Choose a Picture"), 68);
    }

//    void callCamGalleryHelper(View selectContainer, View view, int id, int mode) {
//        if (selectContainer == null) {
//            selectContainer = findViewById(R.id.select_image_container);
//        }
//        if (this.selectCamGalleryAnimHelper == null) {
//            initSelectCamGal();
//        }
//        if (this.selectCamGalleryAnimHelper != null) {
//            if (mode == SelectCamGalleryAnimHelper.MODE_CLICK) {
//                this.selectCamGalleryAnimHelper.onClickListener(
//                        selectContainer, view, id);
//            }
//            if (mode == SelectCamGalleryAnimHelper.MODE_OPEN) {
//                this.selectCamGalleryAnimHelper.openCamGal(selectContainer);
//            }
//        }
//    }

    void initSelectCamGal() {
        this.selectCamGalleryAnimHelper = new SelectCamGalleryAnimHelper(this);
    }

    /*private void loadShareActivity() {
        Intent fbIntent = new Intent(ShapeCollageActivity.this.context,
                ShareActivity.class);
        Utility.opengalleryfrommainpage = false;
        fbIntent.putExtra("imagePath", mResultPath);
        startActivity(fbIntent);
        overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);
        fbIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		finish();
    }*/


}