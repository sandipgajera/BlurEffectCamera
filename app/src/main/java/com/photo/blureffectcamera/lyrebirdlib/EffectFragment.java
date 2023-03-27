package com.photo.blureffectcamera.lyrebirdlib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.common_libs.MyAsyncTask;
import com.photo.blureffectcamera.hdrlightlib.HdrLightHelper;
import com.photo.blureffectcamera.tiltshift.TiltContext;
import com.photo.blureffectcamera.tiltshift.TiltFragment;
import com.photo.blureffectcamera.tiltshift.TiltHelper;

public class EffectFragment extends Fragment {
    public static final int INDEX_ADJUSTMENT = 4;
    public static final int INDEX_BLUR = 10;
    public static final int INDEX_BRIGHTNESS = 4;
    public static final int INDEX_CONTRAST = 6;
    public static final int INDEX_FRAME = 1;
    public static final int INDEX_FX = 0;
    public static final int INDEX_HIGHLIGHT = 11;
    public static final int INDEX_LIGHT = 2;
    public static final int INDEX_SATURATION = 7;
    public static final int INDEX_SHADOW = 12;
    public static final int INDEX_SHARPEN = 9;
    public static final int INDEX_TEXTURE = 3;
    public static final int INDEX_TILT = 13;
    public static final int INDEX_TINT = 8;
    public static final int INDEX_VOID = 5;
    public static final int INDEX_WARMTH = 5;
    public static final int TAB_SIZE = 14;
    public static EffectFragment effectFragment = new EffectFragment();
    static final String TAG = "EffectFragment";
    private static String[] filterBitmapTitle;
    public static Paint grayPaint;
    static boolean libLoadIsFailed;
    public static Paint sepiaPaint;
    Activity activity;
    Button adjustmentLabel;
    float[] autoParameters;
    int bitmapHeight;
    BitmapReady bitmapReadyListener;
    Bitmap bitmapTiltBlur;
    int bitmapWidth, selectIndex;
    BlurBuilderNormal blurBuilder;
    MyRecyclerViewAdapter borderAdapter;
    HorizontalScrollView borderHS;
    MyRecyclerViewAdapter.RecyclerAdapterIndexChangedListener borderIndexChangedListener;
    View buttonAuto;
    LibUtility.BuyProVersion buyProVersionListener;
    Context context;
    int count;
    LibUtility.ExcludeTabListener excludeTabListener;
    //    MyRecyclerViewAdapter filterAdapter;
    Bitmap filterBitmap;
    HorizontalScrollView filterHS;
    LibUtility.FooterVisibilityListener footerListener;
    FilterAndAdjustmentTask ft;
    HdrLightHelper hdrLightHelper;
    FullEffectFragment.HideHeaderListener hideHeaderListener;
    boolean inFilterAndAdjustment;
    OnSeekBarChangeListener mySeekBarListener;
    MyRecyclerViewAdapter overlayAdapter;
    Parameter parameterBackUp;
    public Parameter parameterGlobal;
    int parameterSize;
    SeekBar seekBarAdjustment;
    Rect seekbarHintTextBounds;
    LayoutParams seekbarHintTextLayoutParams;
    int selectedTab;
    private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;
    private Bitmap sourceBitmap;
    Button[] tabButtonList;
    TextView textHint;
    MyRecyclerViewAdapter textureAdapter;
    HorizontalScrollView textureHS;
    int thumbSize;
    TiltFragment titlFragment;
    ViewFlipper viewFlipper;
    private ViewSwitcher viewSwitcher;

    class C07489 implements OnSeekBarChangeListener {
        C07489() {
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (EffectFragment.this.textHint == null) {
                EffectFragment.this.textHint = (TextView) EffectFragment.this.getView().findViewById(R.id.seekbar_hint);
            }
            if (EffectFragment.this.seekbarHintTextLayoutParams == null) {
                EffectFragment.this.seekbarHintTextLayoutParams = (LayoutParams) EffectFragment.this.textHint.getLayoutParams();
            }
            Rect thumbRect = ((SeekBarHint) seekBar).getSeekBarThumb().getBounds();
            EffectFragment.this.textHint.setText(String.valueOf(progress));
            EffectFragment.this.textHint.getPaint().getTextBounds(EffectFragment.this.textHint.getText().toString(), EffectFragment.INDEX_FX, EffectFragment.this.textHint.getText().length(), EffectFragment.this.seekbarHintTextBounds);
            EffectFragment.this.seekbarHintTextLayoutParams.setMargins(thumbRect.centerX() - (EffectFragment.this.seekbarHintTextBounds.width() / EffectFragment.INDEX_LIGHT), EffectFragment.INDEX_FX, EffectFragment.INDEX_FX, EffectFragment.INDEX_FX);
            EffectFragment.this.textHint.setLayoutParams(EffectFragment.this.seekbarHintTextLayoutParams);
            if (EffectFragment.this.parameterGlobal.seekBarMode == 0) {
                EffectFragment.this.parameterGlobal.setBrightness(progress);
            } else if (EffectFragment.this.parameterGlobal.seekBarMode == EffectFragment.INDEX_FRAME) {
                EffectFragment.this.parameterGlobal.setContrast(progress);
            } else if (EffectFragment.this.parameterGlobal.seekBarMode == EffectFragment.INDEX_LIGHT) {
                EffectFragment.this.parameterGlobal.setTemperature(progress);
            } else if (EffectFragment.this.parameterGlobal.seekBarMode == EffectFragment.INDEX_TEXTURE) {
                EffectFragment.this.parameterGlobal.setSaturation(progress);
            } else if (EffectFragment.this.parameterGlobal.seekBarMode == EffectFragment.INDEX_BRIGHTNESS) {
                EffectFragment.this.parameterGlobal.setTint(progress);
            } else if (EffectFragment.this.parameterGlobal.seekBarMode == EffectFragment.INDEX_WARMTH) {
                EffectFragment.this.parameterGlobal.setSharpen(progress);
            } else if (EffectFragment.this.parameterGlobal.seekBarMode == EffectFragment.INDEX_CONTRAST) {
                EffectFragment.this.parameterGlobal.setBlur(progress);
            } else if (EffectFragment.this.parameterGlobal.seekBarMode == EffectFragment.INDEX_SATURATION) {
                EffectFragment.this.parameterGlobal.setHighlight(progress);
            } else if (EffectFragment.this.parameterGlobal.seekBarMode == EffectFragment.INDEX_TINT) {
                EffectFragment.this.parameterGlobal.setShadow(progress);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            if (EffectFragment.this.textHint == null) {
                EffectFragment.this.textHint = (TextView) EffectFragment.this.getView().findViewById(R.id.seekbar_hint);
            }
            EffectFragment.this.textHint.setVisibility(EffectFragment.INDEX_FX);
        }

        @SuppressLint("WrongConstant")
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (EffectFragment.this.textHint == null) {
                EffectFragment.this.textHint = (TextView) EffectFragment.this.getView().findViewById(R.id.seekbar_hint);
            }
            EffectFragment.this.textHint.setVisibility(EffectFragment.INDEX_BRIGHTNESS);
            EffectFragment.this.callBack();
        }
    }

    public interface BitmapReady {
        void onBitmapReady(Bitmap bitmap);
    }

    class AnonymousClass10 implements TiltFragment.TiltListener {
        final FragmentManager val$fm;

        AnonymousClass10(FragmentManager fragmentManager) {
            this.val$fm = fragmentManager;
        }

        public void onTiltOk(TiltContext tc) {
            if (EffectFragment.this.hideHeaderListener != null) {
                EffectFragment.this.hideHeaderListener.hide(true);
            }
            EffectFragment.this.parameterGlobal.tiltContext = tc;
            FragmentTransaction ft = this.val$fm.beginTransaction();
            ft.remove(EffectFragment.this.titlFragment);
            ft.commit();
            EffectFragment.this.execQueue();
        }

        public void onTiltCancel() {
            if (EffectFragment.this.hideHeaderListener != null) {
                EffectFragment.this.hideHeaderListener.hide(true);
            }
            EffectFragment.this.removeTiltFragment();
            EffectFragment.this.execQueue();
        }
    }

    class C13231 implements MyRecyclerViewAdapter.RecyclerAdapterIndexChangedListener {
        C13231() {
        }

        public void onIndexChanged(int position) {
            if (LibUtility.isAppPro || position <= EffectFragment.this.borderAdapter.proIndex) {
                EffectFragment.this.applyChangesOnBitmap();
            } else {
                EffectFragment.this.showAlertForPro();
            }
        }
    }

    class C13242 implements MyRecyclerViewAdapter.SelectedIndexChangedListener {
        C13242() {
        }

        public void selectedIndexChanged(int index) {
            EffectFragment.this.parameterGlobal.selectedBorderIndex = index;
        }
    }

    class C13253 implements MyRecyclerViewAdapter.RecyclerAdapterIndexChangedListener {
        C13253() {
        }

        public void onIndexChanged(int position) {
            if (LibUtility.isAppPro || position <= EffectFragment.this.textureAdapter.proIndex) {
                EffectFragment.this.applyChangesOnBitmap();
            } else {
                EffectFragment.this.showAlertForPro();
            }
        }
    }

    class C13264 implements MyRecyclerViewAdapter.SelectedIndexChangedListener {
        C13264() {
        }

        public void selectedIndexChanged(int index) {
            EffectFragment.this.parameterGlobal.selectedTextureIndex = index;
        }
    }

    class C13275 implements MyRecyclerViewAdapter.RecyclerAdapterIndexChangedListener {
        C13275() {
        }

        public void onIndexChanged(int position) {
            if (LibUtility.isAppPro || position <= EffectFragment.this.overlayAdapter.proIndex) {
                EffectFragment.this.applyChangesOnBitmap();
            } else {
                EffectFragment.this.showAlertForPro();
            }
        }
    }

    class C13286 implements MyRecyclerViewAdapter.SelectedIndexChangedListener {
        C13286() {
        }

        public void selectedIndexChanged(int index) {
            EffectFragment.this.parameterGlobal.selectedOverlayIndex = index;
        }
    }

//    class C13297 implements MyRecyclerViewAdapter.RecyclerAdapterIndexChangedListener {
//        C13297() {
//        }
//
//        public void onIndexChanged(int position) {
//            if (LibUtility.isAppPro || position <= EffectFragment.this.filterAdapter.proIndex) {
//                EffectFragment.this.applyChangesOnBitmap();
//            } else {
//                EffectFragment.this.showAlertForPro();
//            }
//        }
//    }

    class C13308 implements MyRecyclerViewAdapter.SelectedIndexChangedListener {
        C13308() {
        }

        public void selectedIndexChanged(int index) {
            EffectFragment.this.parameterGlobal.selectedFilterIndex = index;
        }
    }

    class FilterAndAdjustmentTask extends MyAsyncTask<Void, Void, Void> {
        Bitmap bitmapBlur;
        int lastBlurRadius;
        Matrix matrixBlur;
        Paint paintBlur;
        ProgressDialog progressDialog;
        Bitmap smallBitmap;

        FilterAndAdjustmentTask() {
            this.matrixBlur = new Matrix();
            this.paintBlur = new Paint(EffectFragment.INDEX_LIGHT);
            this.lastBlurRadius = -1;
        }

        protected Void doInBackground(Void... arg0) {

            if (EffectFragment.this.isAdded()) {
                if (EffectFragment.this.filterBitmap == null) {
                    EffectFragment.this.filterBitmap = EffectFragment.this.sourceBitmap.copy(Config.ARGB_8888, true);
                } else {
                    new Canvas(EffectFragment.this.filterBitmap).drawBitmap(EffectFragment.this.sourceBitmap, 0.0f, 0.0f, new Paint());
                }
                Canvas cvs = new Canvas(EffectFragment.this.filterBitmap);
                cvs.drawBitmap(EffectFragment.this.sourceBitmap, 0.0f, 0.0f, new Paint());
                if (EffectFragment.this.parameterGlobal.blur > 0) {
                    Bitmap btmBlur = createBlurBitmapNDK(EffectFragment.this.parameterGlobal.blur);
                    Canvas canvas = new Canvas(EffectFragment.this.filterBitmap);
                    this.matrixBlur.reset();
                    this.matrixBlur.postScale(2.5f, 2.5f);
                    canvas.drawBitmap(btmBlur, this.matrixBlur, this.paintBlur);
                }
                if (!(EffectFragment.this.parameterGlobal.tiltContext == null || EffectFragment.this.parameterGlobal.tiltContext.mode == TiltContext.TiltMode.NONE || EffectFragment.this.bitmapTiltBlur != null)) {
                    EffectFragment.this.createTiltBlurBitmap();
                }
                if (!(EffectFragment.this.parameterGlobal.tiltContext == null || EffectFragment.this.bitmapTiltBlur == null || EffectFragment.this.parameterGlobal.tiltContext.mode == TiltContext.TiltMode.NONE)) {
                    TiltHelper.drawTiltShift2(cvs, EffectFragment.this.bitmapTiltBlur, EffectFragment.this.filterBitmap.getWidth(), EffectFragment.this.filterBitmap.getHeight(), EffectFragment.this.parameterGlobal.tiltContext);
                    EffectFragment.this.tiltSharpen(EffectFragment.this.filterBitmap);
                }
                if (EffectFragment.this.isAdded()) {
                    pipeline(EffectFragment.this.filterBitmap);
                } else {
                    cancel(true);
                    EffectFragment.this.inFilterAndAdjustment = false;
                }
            } else {
                EffectFragment.this.inFilterAndAdjustment = false;
            }
            return null;
        }

        void pipeline(Bitmap btmPipe) {
            if (EffectFragment.this.parameterGlobal.selectedFilterIndex <= 33) {
                EffectFragment.setFilter(EffectFragment.this.parameterGlobal.selectedFilterIndex, btmPipe);
            } else {
                if (EffectFragment.this.hdrLightHelper == null) {
                    EffectFragment.this.hdrLightHelper = new HdrLightHelper(EffectFragment.this.context);
                }
                EffectFragment.this.hdrLightHelper.applyHdr(btmPipe, EffectFragment.this.parameterGlobal.selectedFilterIndex - 34);
            }
            if (!(EffectFragment.this.parameterGlobal.contrast == 0 && EffectFragment.this.parameterGlobal.brightness == 0 && EffectFragment.this.parameterGlobal.getTemperature() == 0 && EffectFragment.this.parameterGlobal.getSaturation() == 1.0f && EffectFragment.this.parameterGlobal.getTint() == 0 && EffectFragment.this.parameterGlobal.highlight == 0.0f && EffectFragment.this.parameterGlobal.shadow == 0.0f)) {
                setAdjustment(btmPipe);
            }
            if (EffectFragment.this.parameterGlobal.sharpen > 0.0f) {
                long nanoTime = System.nanoTime();
                nanoTime = System.nanoTime();
                EffectFragment.sharpen6(btmPipe, 18, EffectFragment.this.parameterGlobal.sharpen);
            }
            Bitmap overlayBitmap = EffectFragment.this.getOverlayBitmap(EffectFragment.this.parameterGlobal.selectedOverlayIndex);
            if (!(overlayBitmap == null || overlayBitmap.isRecycled())) {
                if (VERSION.SDK_INT > EffectFragment.INDEX_BLUR) {
                    EffectFragment.this.applyOverlay11(overlayBitmap, btmPipe, EffectFragment.isOverlayScreenMode(EffectFragment.this.parameterGlobal.selectedOverlayIndex));
                } else if (EffectFragment.isOverlayScreenMode(EffectFragment.this.overlayAdapter.getSelectedIndex()) == 0) {
                    EffectFragment.applyOverlay(overlayBitmap, btmPipe, EffectFragment.isOverlayScreenMode(EffectFragment.this.parameterGlobal.selectedOverlayIndex));
                } else {
                    EffectFragment.this.applyOverlay11(overlayBitmap, btmPipe, EffectFragment.isOverlayScreenMode(EffectFragment.this.parameterGlobal.selectedOverlayIndex));
                }
            }
            EffectFragment.this.filterMultiply(btmPipe, EffectFragment.this.parameterGlobal.selectedTextureIndex, false);
            if (EffectFragment.this.borderIndexChangedListener == null) {
                EffectFragment.this.setBorder(btmPipe, EffectFragment.this.parameterGlobal.selectedBorderIndex, false);
            }
            Canvas cvs = new Canvas(btmPipe);
            if (EffectFragment.this.parameterGlobal.selectedFilterIndex < EffectFragment.INDEX_LIGHT) {
                cvs.drawBitmap(btmPipe, 0.0f, 0.0f, new Paint());
            }
        }

        public Bitmap createBlurBitmapRS(int radius) {
            if (this.lastBlurRadius == radius && this.bitmapBlur != null) {
                return this.bitmapBlur;
            }
            if (EffectFragment.this.blurBuilder == null) {
                EffectFragment.this.blurBuilder = new BlurBuilderNormal();
            }
            this.bitmapBlur = EffectFragment.this.blurBuilder.createBlurBitmapNDK(EffectFragment.this.sourceBitmap, radius);
            this.lastBlurRadius = radius;
            return this.bitmapBlur;
        }

        public Bitmap createBlurBitmapNDK(int radius) {
            if (this.lastBlurRadius == radius && this.smallBitmap != null) {
                return this.smallBitmap;
            }
            if (this.smallBitmap == null) {
                int width = Math.round(((float) EffectFragment.this.sourceBitmap.getWidth()) * BlurBuilderNormal.BITMAP_SCALE);
                int height = Math.round(((float) EffectFragment.this.sourceBitmap.getHeight()) * BlurBuilderNormal.BITMAP_SCALE);
                if (width % EffectFragment.INDEX_LIGHT == EffectFragment.INDEX_FRAME) {
                    width += EffectFragment.INDEX_FRAME;
                }
                if (height % EffectFragment.INDEX_LIGHT == EffectFragment.INDEX_FRAME) {
                    height += EffectFragment.INDEX_FRAME;
                }
                if (VERSION.SDK_INT < EffectFragment.INDEX_SHADOW) {
                    Options myOptions = new Options();
                    myOptions.inDither = true;
                    myOptions.inScaled = false;
                    myOptions.inPreferredConfig = Config.ARGB_8888;
                    myOptions.inPurgeable = true;
                    this.smallBitmap = BlurBuilderNormal.createScaledBitmap(EffectFragment.this.sourceBitmap, width, height, false);
                } else {
                    this.smallBitmap = Bitmap.createScaledBitmap(EffectFragment.this.sourceBitmap, width, height, false);
                }
            } else {
                Canvas canvasSmall = new Canvas(this.smallBitmap);
                this.matrixBlur.reset();
                this.matrixBlur.postScale(BlurBuilderNormal.BITMAP_SCALE, BlurBuilderNormal.BITMAP_SCALE);
                canvasSmall.drawBitmap(EffectFragment.this.sourceBitmap, this.matrixBlur, this.paintBlur);
            }

            EffectFragment.functionToBlur(this.smallBitmap, radius);
            this.lastBlurRadius = radius;
            return this.smallBitmap;
        }

        public Bitmap getBlurBitmap(Bitmap bitmap, float radius) {
            BlurBuilderNormal bb = new BlurBuilderNormal();
            Bitmap btm = bb.createBlurBitmapNDK(bitmap, (int) radius);
            bb.destroy();
            return btm;
        }

        void setAdjustment(Bitmap btmAdj) {
            EffectFragment.applyAdjustment(btmAdj, EffectFragment.this.parameterGlobal.contrast, EffectFragment.this.parameterGlobal.brightness, EffectFragment.this.parameterGlobal.getTemperature(), EffectFragment.this.parameterGlobal.getSaturation(), EffectFragment.this.parameterGlobal.getTint(), EffectFragment.this.parameterGlobal.highlight, EffectFragment.this.parameterGlobal.shadow);
        }

        protected void onPreExecute() {
            EffectFragment.this.inFilterAndAdjustment = true;
            try {
                this.progressDialog = new ProgressDialog(EffectFragment.this.context);
                this.progressDialog.show();
            } catch (Exception e) {
            }
        }

        protected void onPostExecute(Void result) {
            EffectFragment.this.inFilterAndAdjustment = false;
            try {
                this.progressDialog.dismiss();
            } catch (Exception e) {
            }
            if (EffectFragment.this.isAdded()) {
                EffectFragment.this.bitmapReadyListener.onBitmapReady(EffectFragment.this.filterBitmap);
            }
        }
    }

    private class InitializeEffectThumbTask extends MyAsyncTask<Void, Void, Void> {
        private Bitmap[] filterBitmapArray;

        private InitializeEffectThumbTask() {
        }

        protected Void doInBackground(Void... arg0) {
            int width = EffectFragment.this.bitmapWidth;
            int height = EffectFragment.this.bitmapWidth;
            int side = Math.max(width, height);
            float f = (float) side;
            float r0 = 0;
            float scaler = r0 / EffectFragment.this.getResources().getDimension(R.dimen.lib_filter_horizontal_view_image_size);
            width = (int) (((float) width) / scaler);
            height = (int) (((float) height) / scaler);
            Paint paint = new Paint();
            int i = EffectFragment.INDEX_FX;
            while (true) {
                int length = this.filterBitmapArray.length;
                if (i >= r0) {
                    return null;
                }
                int x0;
                int y0;
                int x1;
                int y1;
                Bitmap thumbBtm = Bitmap.createBitmap(EffectFragment.this.thumbSize, EffectFragment.this.thumbSize, Config.ARGB_8888);
                Canvas cvs = new Canvas(thumbBtm);
                int x = EffectFragment.this.bitmapWidth;
                int y = EffectFragment.this.bitmapHeight;
                if (x >= y) {
                    x0 = (x - y) / EffectFragment.INDEX_LIGHT;
                    y0 = EffectFragment.INDEX_FX;
                    x1 = (x + y) / EffectFragment.INDEX_LIGHT;
                    y1 = y;
                } else {
                    x0 = EffectFragment.INDEX_FX;
                    y0 = (y - x) / EffectFragment.INDEX_LIGHT;
                    x1 = x;
                    y1 = (x + y) / EffectFragment.INDEX_LIGHT;
                }
                Rect dst = new Rect(EffectFragment.INDEX_FX, EffectFragment.INDEX_FX, EffectFragment.this.thumbSize, EffectFragment.this.thumbSize);
                Rect src = new Rect(x0, y0, x1, y1);
                cvs.drawBitmap(EffectFragment.this.sourceBitmap, src, dst, paint);
                EffectFragment.setFilter(i, thumbBtm);
                this.filterBitmapArray[i] = thumbBtm;
                i += EffectFragment.INDEX_FRAME;
            }
        }

        protected void onPostExecute(Void result) {
        }

        protected void onPreExecute() {
            if (this.filterBitmapArray == null) {
                this.filterBitmapArray = new Bitmap[EffectFragment.filterBitmapTitle.length];
            }
        }
    }

//    private class SaveThumbTask extends MyAsyncTask<Void, Void, Void> {
//        private SaveThumbTask() {
//        }
//
//        protected Void doInBackground(Void... arg0) {
//            int x0;
//            int y0;
//            int x1;
//            int y1;
//            int width = EffectFragment.this.bitmapWidth;
//            int height = EffectFragment.this.bitmapWidth;
//            int side = Math.max(width, height);
//            float f = (float) side;
//            float r0 = 0;
//            float scaler = r0 / EffectFragment.this.getResources().getDimension(R.dimen.lib_thumb_save_size);
//            width = (int) (((float) width) / scaler);
//            height = (int) (((float) height) / scaler);
//            Paint paint = new Paint();
//            int x = EffectFragment.this.bitmapWidth;
//            int y = EffectFragment.this.bitmapHeight;
//            if (x >= y) {
//                x0 = (x - y) / EffectFragment.INDEX_LIGHT;
//                y0 = EffectFragment.INDEX_FX;
//                x1 = (x + y) / EffectFragment.INDEX_LIGHT;
//                y1 = y;
//            } else {
//                x0 = EffectFragment.INDEX_FX;
//                y0 = (y - x) / EffectFragment.INDEX_LIGHT;
//                x1 = x;
//                y1 = (x + y) / EffectFragment.INDEX_LIGHT;
//            }
//            Rect dst = new Rect(EffectFragment.INDEX_FX, EffectFragment.INDEX_FX, EffectFragment.this.thumbSize, EffectFragment.this.thumbSize);
//            Rect src = new Rect(x0, y0, x1, y1);
//            int i = EffectFragment.INDEX_FRAME;
//            while (true) {
//                int length = LibUtility.borderRes.length;
//                if (i >= r0) {
//                    break;
//                }
//                Bitmap thumbBtm = Bitmap.createBitmap(EffectFragment.this.thumbSize, EffectFragment.this.thumbSize, Config.ARGB_8888);
//                new Canvas(thumbBtm).drawBitmap(EffectFragment.this.sourceBitmap, src, dst, paint);
//                EffectFragment.this.setBorder(thumbBtm, i, false);
//                saveBitmap(thumbBtm, "border_" + (i - 1));
//                thumbBtm.recycle();
//                i += EffectFragment.INDEX_FRAME;
//            }
//            i = EffectFragment.INDEX_FRAME;
//            int length;
//            Bitmap thumbBtm;
//            while (true) {
//                length = LibUtility.textureResThumb.length;
//                if (i >= r0) {
//                    break;
//                }
//                thumbBtm = Bitmap.createBitmap(EffectFragment.this.thumbSize, EffectFragment.this.thumbSize, Config.ARGB_8888);
//                new Canvas(thumbBtm).drawBitmap(EffectFragment.this.sourceBitmap, src, dst, paint);
//                EffectFragment.this.filterMultiply(thumbBtm, i, false);
//                saveBitmap(thumbBtm, "texture_" + (i - 1));
//                thumbBtm.recycle();
//                i += EffectFragment.INDEX_FRAME;
//            }
//            i = EffectFragment.INDEX_FRAME;
//            while (true) {
//                length = LibUtility.overlayResThumb.length;
//                if (i >= r0) {
//                    break;
//                }
//                thumbBtm = Bitmap.createBitmap(EffectFragment.this.thumbSize, EffectFragment.this.thumbSize, Config.ARGB_8888);
//                new Canvas(thumbBtm).drawBitmap(EffectFragment.this.sourceBitmap, src, dst, paint);
//                Bitmap overlayBitmap = EffectFragment.this.getOverlayBitmap(i);
//                EffectFragment.this.applyOverlay11(overlayBitmap, thumbBtm, EffectFragment.isOverlayScreenMode(i));
//                overlayBitmap.recycle();
//                saveBitmap(thumbBtm, "overlay_" + (i - 1));
//                thumbBtm.recycle();
//                i += EffectFragment.INDEX_FRAME;
//            }
//            i = EffectFragment.INDEX_FX;
//            while (true) {
//                length = LibUtility.filterResThumb.length;
//                if (i >= r0) {
//                    return null;
//                }
//                dst.set(EffectFragment.INDEX_FX, EffectFragment.INDEX_FX, EffectFragment.this.thumbSize, EffectFragment.this.thumbSize);
//                thumbBtm = Bitmap.createBitmap(EffectFragment.this.thumbSize, EffectFragment.this.thumbSize, Config.ARGB_8888);
//                new Canvas(thumbBtm).drawBitmap(EffectFragment.this.sourceBitmap, src, dst, paint);
//                if (i <= 33) {
//                    EffectFragment.setFilter(i, thumbBtm);
//                } else {
//                    if (EffectFragment.this.hdrLightHelper == null) {
//                        EffectFragment effectFragment = EffectFragment.this;
//                        effectFragment.hdrLightHelper = new HdrLightHelper(EffectFragment.this.context);
//                    }
//                    EffectFragment.this.hdrLightHelper.applyHdr(thumbBtm, i - 34);
//                }
//                saveBitmap(thumbBtm, "effect_" + i);
//                thumbBtm.recycle();
//                i += EffectFragment.INDEX_FRAME;
//            }
//        }
//
//        protected void onPreExecute() {
//        }
//
//        void saveBitmap(Bitmap bitmap, String name) {
//            try {
//                bitmap.compress(CompressFormat.JPEG, 100, new FileOutputStream(Environment.getExternalStorageDirectory() + "/coloreffect/" + name + "_thumb.jpg"));
//            } catch (FileNotFoundException e) {
//            }
//        }
//
//        protected void onPostExecute(Void result) {
//        }
//    }

    private static native void applyAdjustment(Bitmap bitmap, int i, int i2, int i3, float f, int i4, float f2, float f3);

    private static native Bitmap applyOverlay(Bitmap bitmap, Bitmap bitmap2, int i);

    public static native void calculateAutoParameters(Bitmap bitmap, float[] fArr);

    private static native void filterAmber(Bitmap bitmap);

    private static native void filterAnne(Bitmap bitmap);

    private static native void filterAntonio(Bitmap bitmap);

    private static native void filterCameron(Bitmap bitmap);

    private static native void filterCross(Bitmap bitmap);

    private static native void filterCuddy(Bitmap bitmap);

    private static native void filterIns1(Bitmap bitmap);

    private static native void filterIns10(Bitmap bitmap);

    private static native void filterIns11(Bitmap bitmap);

    private static native void filterIns12(Bitmap bitmap);

    private static native void filterIns13(Bitmap bitmap);

    private static native void filterIns14(Bitmap bitmap);

    private static native void filterIns15(Bitmap bitmap);

    private static native void filterIns1Reverse(Bitmap bitmap);

    private static native void filterIns2(Bitmap bitmap);

    private static native void filterIns3(Bitmap bitmap);

    private static native void filterIns4(Bitmap bitmap);

    private static native void filterIns5(Bitmap bitmap);

    private static native void filterIns6(Bitmap bitmap);

    private static native void filterIns7(Bitmap bitmap);

    private static native void filterIns8(Bitmap bitmap);

    private static native void filterIns9(Bitmap bitmap);

    private static native void filterKaren(Bitmap bitmap);

    private static native void filterMain(Bitmap bitmap);

    private static native void filterNew1(Bitmap bitmap);

    private static native void filterNew2(Bitmap bitmap);

    private static native void filterNew3(Bitmap bitmap);

    private static native void filterNew4(Bitmap bitmap);

    private static native void filterNew5(Bitmap bitmap);

    private static native void filterPeter(Bitmap bitmap);

    private static native void filterSalomon(Bitmap bitmap);

    public static native void functionToBlur(Bitmap bitmap, int i);

    private static native void highlight(Bitmap bitmap, float f);

    public static native void saveFullImage(String str, String str2, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, int i, int i2, int i3, int i4, int i5, int i6, float f, int i7, int i8);

    private static native void shadows(Bitmap bitmap, float f);

    private static native void sharpen(Bitmap bitmap, float f);

    private static native void sharpen2(Bitmap bitmap, float f);

    private static native void sharpen3(Bitmap bitmap, int i, float f);

    private static native void sharpen4(Bitmap bitmap, int i, float f);

    private static native void sharpen5(Bitmap bitmap, int i, float f);

    private static native void sharpen6(Bitmap bitmap, int i, float f);

    private static native void sharpen7(Bitmap bitmap, Bitmap bitmap2, float f);

    public EffectFragment() {
        this.borderIndexChangedListener = null;
        this.seekbarHintTextBounds = new Rect();
        this.selectedTab = INDEX_FX;
        this.mySeekBarListener = new C07489();
        this.count = INDEX_FX;
        this.inFilterAndAdjustment = false;
        this.parameterBackUp = new Parameter();
        this.parameterSize = INDEX_BRIGHTNESS;
    }

    @SuppressLint("WrongConstant")
    public void excludeTabs(int[] tabList) {
        for (int i = INDEX_FX; i < tabList.length; i += INDEX_FRAME) {
            this.tabButtonList[tabList[i]].setVisibility(INDEX_TINT);
        }
    }

    public void setFooterVisibilityListener(LibUtility.FooterVisibilityListener listener) {
        this.footerListener = listener;
    }

    public void setExcludeTabListener(LibUtility.ExcludeTabListener listener) {
        this.excludeTabListener = listener;
    }

    public void setBitmapReadyListener(BitmapReady listener) {
        this.bitmapReadyListener = listener;
    }

    public MyRecyclerViewAdapter getBorderAdapter() {
        return this.borderAdapter;
    }

    public void setHideHeaderListener(FullEffectFragment.HideHeaderListener l) {
        this.hideHeaderListener = l;
    }

    public void setVisibilityOfEffectFooter(int visibility) {
        getView().findViewById(R.id.fx_footer).setVisibility(visibility);
    }

    public boolean backPressed() {
        if (this.titlFragment == null || !this.titlFragment.isVisible()) {
            return showToolBar();
        }
        this.titlFragment.backPressed();
        removeTiltFragment();
        return true;
    }

    public boolean showToolBar() {
        if (this.viewSwitcher.getDisplayedChild() != 0) {
            return false;
        }
        cancelViewSwitcher();
        this.viewSwitcher.setDisplayedChild(INDEX_FRAME);
        return true;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (libLoadIsFailed) {
//            LibUtility.initNativeLib(getActivity());
//        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(getString(R.string.effect_parameter_bundle_name), this.parameterGlobal);
        super.onSaveInstanceState(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.thumbSize = (int) getResources().getDimension(R.dimen.lib_thumb_save_size);
        return inflater.inflate(R.layout.horizontal_fragment_effect, container, false);
    }

    public void onAttach(Activity act) {
        super.onAttach(act);
        this.context = getActivity();
        this.activity = getActivity();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            this.parameterGlobal = (Parameter) savedInstanceState.getParcelable(getString(R.string.effect_parameter_bundle_name));
        } else if (getArguments() != null) {
            this.parameterGlobal = (Parameter) getArguments().getParcelable(getString(R.string.effect_parameter_bundle_name));
        }
        if (this.parameterGlobal == null) {
            this.parameterGlobal = new Parameter();
        }
        this.context = getActivity();
        this.activity = getActivity();
        initPaints();
        initAdapters();
        this.viewSwitcher = (ViewSwitcher) getView().findViewById(R.id.viewswitcher);
        this.viewFlipper = (ViewFlipper) getView().findViewById(R.id.control_container);
        this.slideLeftIn = AnimationUtils.loadAnimation(this.activity, R.anim.slide_in_left);
        this.slideLeftOut = AnimationUtils.loadAnimation(this.activity, R.anim.slide_out_left);
        this.slideRightIn = AnimationUtils.loadAnimation(this.activity, R.anim.slide_in_right);
        this.slideRightOut = AnimationUtils.loadAnimation(this.activity, R.anim.slide_out_right);
        this.adjustmentLabel = (Button) getView().findViewById(R.id.lib_current_adjustmen_label);
        setSelectedTab(this.selectedTab);
        this.viewSwitcher.setDisplayedChild(INDEX_FRAME);
        setTabBg(this.selectedTab);
        if (this.excludeTabListener != null) {
            this.excludeTabListener.exclude();
        }
        if (this.footerListener != null) {
            this.footerListener.setVisibility();
        }
        this.seekBarAdjustment = (SeekBar) getView().findViewById(R.id.seek_bar_adjustment);
        this.seekBarAdjustment.setOnSeekBarChangeListener(this.mySeekBarListener);
        this.hdrLightHelper = new HdrLightHelper(this.context);
    }

    public void setBorderIndexChangedListener(MyRecyclerViewAdapter.RecyclerAdapterIndexChangedListener l) {
        this.borderIndexChangedListener = l;
    }

    private void initAdapters() {
        MyRecyclerViewAdapter.RecyclerAdapterIndexChangedListener borderL;
        MyRecyclerViewAdapter.RecyclerAdapterIndexChangedListener c13231 = new C13231();
        if (this.borderIndexChangedListener != null) {
            borderL = this.borderIndexChangedListener;
        } else {
            borderL = c13231;
        }
        this.borderAdapter = new MyRecyclerViewAdapter(LibUtility.borderResThumb, borderL, R.color.lib_footer_second_bg, R.color.lib_footer_button_color_pressed, 100, LibUtility.shouldShowAds(this.activity));
        this.borderAdapter.setSelectedIndexChangedListener(new C13242());
        this.textureAdapter = new MyRecyclerViewAdapter(LibUtility.textureResThumb, new C13253(), R.color.lib_footer_second_bg, R.color.lib_footer_button_color_pressed, 100, LibUtility.shouldShowAds(this.activity));
        this.textureAdapter.setSelectedIndexChangedListener(new C13264());
        this.overlayAdapter = new MyRecyclerViewAdapter(LibUtility.overlayResThumb, new C13275(), R.color.lib_footer_second_bg, R.color.lib_footer_button_color_pressed, 100, LibUtility.shouldShowAds(this.activity));
        this.overlayAdapter.setSelectedIndexChangedListener(new C13286());
//        this.filterAdapter = new MyRecyclerViewAdapter(LibUtility.filterResThumb, new C13297(), R.color.lib_footer_second_bg, R.color.lib_footer_button_color_pressed, 100, LibUtility.shouldShowAds(this.activity));
//        this.filterAdapter.setSelectedIndexChangedListener(new C13308());
        RecyclerView borderRecyclerView = (RecyclerView) getView().findViewById(R.id.border_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        linearLayoutManager.setOrientation(INDEX_FX);
        borderRecyclerView.setLayoutManager(linearLayoutManager);
        borderRecyclerView.setAdapter(this.borderAdapter);
        borderRecyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView textureRecyclerView = (RecyclerView) getView().findViewById(R.id.texture_RecyclerView);
        linearLayoutManager = new LinearLayoutManager(this.context);
        linearLayoutManager.setOrientation(INDEX_FX);
        textureRecyclerView.setLayoutManager(linearLayoutManager);
        textureRecyclerView.setAdapter(this.textureAdapter);
        textureRecyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView overlayRecyclerView = (RecyclerView) getView().findViewById(R.id.overlay_RecyclerView);
        linearLayoutManager = new LinearLayoutManager(this.context);
        linearLayoutManager.setOrientation(INDEX_FX);
        overlayRecyclerView.setLayoutManager(linearLayoutManager);
        overlayRecyclerView.setAdapter(this.overlayAdapter);
        overlayRecyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView filterRecyclerView = (RecyclerView) getView().findViewById(R.id.filter_RecyclerView);
        linearLayoutManager = new LinearLayoutManager(this.context);
        linearLayoutManager.setOrientation(INDEX_FX);
        filterRecyclerView.setLayoutManager(linearLayoutManager);
//        filterRecyclerView.setAdapter(this.filterAdapter);
        filterRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.textureAdapter.setSelectedView(this.parameterGlobal.selectedTextureIndex);
        this.borderAdapter.setSelectedView(this.parameterGlobal.selectedBorderIndex);
        this.overlayAdapter.setSelectedView(this.parameterGlobal.selectedOverlayIndex);
//        if (this.parameterGlobal.selectedFilterIndex >= this.filterAdapter.getItemCount()) {
//            this.parameterGlobal.selectedFilterIndex = INDEX_FX;
//        }
//        this.filterAdapter.setSelectedView(this.parameterGlobal.selectedFilterIndex);
    }

    public void setSelectedTabIndex(int index) {
        if (index >= 0 && index < TAB_SIZE) {
            this.selectedTab = index;
            if (getView() != null) {
                setSelectedTab(index);
            }
        }
    }

    public int getSelectedTabIndex() {
        if (this.viewFlipper != null) {
            return this.viewFlipper.getDisplayedChild();
        }
        return -1;
    }

    void setSeekBarProgress() {
        int progress = 50;
        if (this.parameterGlobal.seekBarMode == 0) {
            progress = this.parameterGlobal.getBrightProgress();
        } else if (this.parameterGlobal.seekBarMode == INDEX_FRAME) {
            progress = this.parameterGlobal.getContrastProgress();
        } else if (this.parameterGlobal.seekBarMode == INDEX_LIGHT) {
            progress = this.parameterGlobal.getTemperatureProgress();
        } else if (this.parameterGlobal.seekBarMode == INDEX_TEXTURE) {
            progress = this.parameterGlobal.saturation;
        } else if (this.parameterGlobal.seekBarMode == INDEX_BRIGHTNESS) {
            progress = this.parameterGlobal.getTintProgressValue();
        } else if (this.parameterGlobal.seekBarMode == INDEX_WARMTH) {
            progress = this.parameterGlobal.getSharpenValue();
        } else if (this.parameterGlobal.seekBarMode == INDEX_CONTRAST) {
            progress = this.parameterGlobal.getBlurValue();
        } else if (this.parameterGlobal.seekBarMode == INDEX_SATURATION) {
            progress = this.parameterGlobal.getHighlightValue();
        } else if (this.parameterGlobal.seekBarMode == INDEX_TINT) {
            progress = this.parameterGlobal.getShadowValue();
        }
        this.seekBarAdjustment.setProgress(progress);
    }

    public void callBack() {
        execQueue();
    }

    public void setBitmap(Bitmap btm) {
        this.sourceBitmap = btm;
        this.bitmapWidth = this.sourceBitmap.getWidth();
        this.bitmapHeight = this.sourceBitmap.getHeight();
        this.filterBitmap = null;
    }

    public void setBitmapAndResetBlur(Bitmap btm) {
        setBitmap(btm);
        if (!(this.bitmapTiltBlur == null || this.bitmapTiltBlur.isRecycled())) {
            this.bitmapTiltBlur.recycle();
        }
        this.bitmapTiltBlur = null;
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    @SuppressLint({"NewApi"})
    public void applyOverlay11(Bitmap overlay, Bitmap btm, int screenMode) {
        Paint paint = new Paint(INDEX_FRAME);
        paint.setFilterBitmap(true);
        Mode mode = Mode.SCREEN;
        if (screenMode == 0) {
            mode = Mode.OVERLAY;
        }
        PorterDuffXfermode porterMode = new PorterDuffXfermode(mode);
        if (screenMode == INDEX_LIGHT) {
            porterMode = null;
        }
        paint.setXfermode(porterMode);
        Matrix borderMatrix = new Matrix();
        float wScale = ((float) btm.getWidth()) / ((float) overlay.getWidth());
        float hScale = ((float) btm.getHeight()) / ((float) overlay.getHeight());
        borderMatrix.reset();
        Canvas cvs = new Canvas(btm);
        borderMatrix.postScale(wScale, hScale);
        cvs.drawBitmap(overlay, borderMatrix, paint);
    }

    static int getBorderMode(int index) {
        return INDEX_FX;
    }

    public synchronized void setBorder(Bitmap btm, int index, boolean isThumb) {
        if (isAdded() && index != 0) {
            if (LibUtility.borderRes.length > index) {
                Bitmap borderBitmap;
                Paint paint = new Paint(INDEX_FRAME);
                if (getBorderMode(index) == INDEX_FRAME) {
                    paint.setXfermode(new PorterDuffXfermode(Mode.MULTIPLY));
                }
                Matrix borderMatrix = new Matrix();
                if (isThumb) {
                    borderBitmap = BitmapFactory.decodeResource(getResources(), LibUtility.borderResThumb[index]);
                } else {
                    borderBitmap = BitmapFactory.decodeResource(getResources(), LibUtility.borderRes[index]);
                }
                float wScale = ((float) btm.getWidth()) / ((float) borderBitmap.getWidth());
                float hScale = ((float) btm.getHeight()) / ((float) borderBitmap.getHeight());
                borderMatrix.reset();
                Canvas cvs = new Canvas(btm);
                borderMatrix.postScale(wScale, hScale);
                cvs.drawBitmap(borderBitmap, borderMatrix, paint);
                if (!(borderBitmap == null || btm == borderBitmap)) {
                    borderBitmap.recycle();
                }
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void filterMultiply(Bitmap btm, int index, boolean isThumb) {
        if (index != 0 && isAdded()) {
            Bitmap textureBitmap;
            Paint paint = new Paint(INDEX_FRAME);
            Mode mode = Mode.SCREEN;
            if (LibUtility.textureModes[index] == LibUtility.MODE_MULTIPLY) {
                mode = Mode.MULTIPLY;
            } else if (LibUtility.textureModes[index] == LibUtility.MODE_OVERLAY && VERSION.SDK_INT > INDEX_BLUR) {
                mode = Mode.OVERLAY;
            } else if (LibUtility.textureModes[index] == LibUtility.MODE_OVERLAY && VERSION.SDK_INT <= INDEX_BLUR) {
                mode = Mode.MULTIPLY;
            }
            paint.setXfermode(new PorterDuffXfermode(mode));
            Matrix borderMatrix = new Matrix();
            if (isThumb) {
                textureBitmap = BitmapFactory.decodeResource(getResources(), LibUtility.textureResThumb[index]);
            } else {
                Options o2 = new Options();
                if (LibUtility.getLeftSizeOfMemory() > 1.024E7d) {
                    o2.inSampleSize = INDEX_FRAME;
                } else {
                    o2.inSampleSize = INDEX_LIGHT;
                }
                textureBitmap = BitmapFactory.decodeResource(getResources(), LibUtility.textureRes[index], o2);
            }
            float wScale = ((float) btm.getWidth()) / ((float) textureBitmap.getWidth());
            float hScale = ((float) btm.getHeight()) / ((float) textureBitmap.getHeight());
            borderMatrix.reset();
            Canvas cvs = new Canvas(btm);
            borderMatrix.postScale(wScale, hScale);
            cvs.drawBitmap(textureBitmap, borderMatrix, paint);
            if (textureBitmap != null && btm != textureBitmap) {
                textureBitmap.recycle();
            }
        }
    }

    Bitmap getOverlayBitmap(int index) {
        Bitmap bitmap = null;
        if (isAdded()) {
            Options opts = new Options();
            opts.inPreferredConfig = Config.ARGB_8888;
            if (LibUtility.getLeftSizeOfMemory() > 1.024E7d) {
                opts.inSampleSize = INDEX_FRAME;
            } else {
                opts.inSampleSize = INDEX_LIGHT;
            }
            if (index > 0 && index < LibUtility.overlayDrawableList.length) {
                Bitmap temp;
                bitmap = BitmapFactory.decodeResource(getResources(), LibUtility.overlayDrawableList[index], opts);
                if (bitmap.getConfig() != Config.ARGB_8888) {
                    temp = bitmap;
                    bitmap = bitmap.copy(Config.ARGB_8888, false);
                    if (bitmap != temp) {
                        temp.recycle();
                    }
                }
                int overlayWidth = bitmap.getWidth();
                int overlayHeight = bitmap.getHeight();
                if ((this.bitmapHeight > this.bitmapWidth && overlayHeight < overlayWidth) || (this.bitmapHeight < this.bitmapWidth && overlayHeight > overlayWidth)) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(90.0f);
                    temp = bitmap;
                    bitmap = Bitmap.createBitmap(temp, INDEX_FX, INDEX_FX, temp.getWidth(), temp.getHeight(), matrix, true);
                    if (bitmap != temp) {
                        temp.recycle();
                    }
                }
            }
        }
        return bitmap;
    }

    static int isOverlayScreenMode(int index) {
        if (index == INDEX_LIGHT) {
            return INDEX_LIGHT;
        }
        return INDEX_FRAME;
    }

    public void isAppPro(boolean isAppPro) {
        LibUtility.isAppPro = isAppPro;
    }

    private void setTabBg(int index) {
        if (this.tabButtonList == null) {
            this.tabButtonList = new Button[TAB_SIZE];
            this.tabButtonList[INDEX_FX] = (Button) getView().findViewById(R.id.button_fx);
            this.tabButtonList[INDEX_FRAME] = (Button) getView().findViewById(R.id.button_frame);
            this.tabButtonList[INDEX_LIGHT] = (Button) getView().findViewById(R.id.button_light);
            this.tabButtonList[INDEX_TEXTURE] = (Button) getView().findViewById(R.id.button_texture);
            this.tabButtonList[INDEX_BRIGHTNESS] = (Button) getView().findViewById(R.id.button_brightness);
            this.tabButtonList[INDEX_CONTRAST] = (Button) getView().findViewById(R.id.button_contrast);
            this.tabButtonList[INDEX_WARMTH] = (Button) getView().findViewById(R.id.button_temperature);
            this.tabButtonList[INDEX_SATURATION] = (Button) getView().findViewById(R.id.button_saturation);
            this.tabButtonList[INDEX_TINT] = (Button) getView().findViewById(R.id.button_tint);
            this.tabButtonList[INDEX_SHARPEN] = (Button) getView().findViewById(R.id.button_sharpen);
            this.tabButtonList[INDEX_BLUR] = (Button) getView().findViewById(R.id.button_blur);
            this.tabButtonList[INDEX_HIGHLIGHT] = (Button) getView().findViewById(R.id.button_highlights);
            this.tabButtonList[INDEX_SHADOW] = (Button) getView().findViewById(R.id.button_shadows);
            this.tabButtonList[INDEX_TILT] = (Button) getView().findViewById(R.id.button_tilt_shift);
        }
        if (index >= 0) {
            this.adjustmentLabel.setText(this.tabButtonList[index].getText());
        }
    }

    private void setTabBgEx(int index) {
        if (this.tabButtonList == null) {
            this.tabButtonList = new Button[TAB_SIZE];
            this.tabButtonList[INDEX_FX] = (Button) getView().findViewById(R.id.button_fx);
            this.tabButtonList[INDEX_FRAME] = (Button) getView().findViewById(R.id.button_frame);
            this.tabButtonList[INDEX_LIGHT] = (Button) getView().findViewById(R.id.button_light);
            this.tabButtonList[INDEX_TEXTURE] = (Button) getView().findViewById(R.id.button_texture);
            this.tabButtonList[INDEX_BRIGHTNESS] = (Button) getView().findViewById(R.id.button_brightness);
            this.tabButtonList[INDEX_CONTRAST] = (Button) getView().findViewById(R.id.button_contrast);
            this.tabButtonList[INDEX_WARMTH] = (Button) getView().findViewById(R.id.button_temperature);
            this.tabButtonList[INDEX_SATURATION] = (Button) getView().findViewById(R.id.button_saturation);
            this.tabButtonList[INDEX_TINT] = (Button) getView().findViewById(R.id.button_tint);
            this.tabButtonList[INDEX_SHARPEN] = (Button) getView().findViewById(R.id.button_sharpen);
            this.tabButtonList[INDEX_BLUR] = (Button) getView().findViewById(R.id.button_blur);
            this.tabButtonList[INDEX_HIGHLIGHT] = (Button) getView().findViewById(R.id.button_highlights);
            this.tabButtonList[INDEX_SHADOW] = (Button) getView().findViewById(R.id.button_shadows);
            this.tabButtonList[INDEX_TILT] = (Button) getView().findViewById(R.id.button_tilt_shift);
        }
        for (int i = INDEX_FX; i < this.tabButtonList.length; i += INDEX_FRAME) {
            this.tabButtonList[i].setBackgroundResource(R.drawable.selector_btn_footer);
        }
        if (index >= 0) {
            this.tabButtonList[index].setBackgroundResource(R.color.lib_footer_button_color_pressed);
        }
    }

    public void myClickHandler(int id) {
        if (id != R.id.button_lib_cancel) {
            this.parameterBackUp.set(this.parameterGlobal);

        }
        if (id == R.id.button_fx) {
            setSelectedTab(INDEX_FX);
        } else if (id == R.id.button_frame) {
            setSelectedTab(INDEX_FRAME);
        } else if (id == R.id.button_light) {
            setSelectedTab(INDEX_LIGHT);
        } else if (id == R.id.button_texture) {
            setSelectedTab(INDEX_TEXTURE);
        } else if (id == R.id.button_filter_reset) {
            resetParameters();
        } else if (id == R.id.button_brightness) {
            setSelectedTab(INDEX_BRIGHTNESS);
            this.parameterGlobal.seekBarMode = INDEX_FX;
            setSeekBarProgress();
        } else if (id == R.id.button_contrast) {
            setSelectedTab(INDEX_CONTRAST);
            this.parameterGlobal.seekBarMode = INDEX_FRAME;
            setSeekBarProgress();
        } else if (id == R.id.button_temperature) {
            setSelectedTab(INDEX_WARMTH);
            this.parameterGlobal.seekBarMode = INDEX_LIGHT;
            setSeekBarProgress();
        } else if (id == R.id.button_saturation) {
            setSelectedTab(INDEX_SATURATION);
            this.parameterGlobal.seekBarMode = INDEX_TEXTURE;
            setSeekBarProgress();
        } else if (id == R.id.button_tint) {
            setSelectedTab(INDEX_TINT);
            this.parameterGlobal.seekBarMode = INDEX_BRIGHTNESS;
            setSeekBarProgress();
        } else if (id == R.id.button_sharpen) {
            setSelectedTab(INDEX_SHARPEN);
            this.parameterGlobal.seekBarMode = INDEX_WARMTH;
            setSeekBarProgress();
        } else if (id == R.id.button_blur) {
            setSelectedTab(INDEX_BLUR);
            this.parameterGlobal.seekBarMode = INDEX_CONTRAST;
            setSeekBarProgress();
        } else if (id == R.id.button_highlights) {
            setSelectedTab(INDEX_HIGHLIGHT);
            this.parameterGlobal.seekBarMode = INDEX_SATURATION;
            setSeekBarProgress();
        } else if (id == R.id.button_shadows) {
            setSelectedTab(INDEX_SHADOW);
            this.parameterGlobal.seekBarMode = INDEX_TINT;
            setSeekBarProgress();
        } else if (id == R.id.button_tilt_shift) {
            this.viewFlipper.setDisplayedChild(INDEX_WARMTH);
            setTabBg(-1);
            FragmentManager fm = getChildFragmentManager();
            this.titlFragment = (TiltFragment) fm.findFragmentByTag("my_tilt_fragment");
            if (this.titlFragment == null) {
                this.titlFragment = new TiltFragment();
                if (this.filterBitmap == null) {
                    this.filterBitmap = this.sourceBitmap.copy(Config.ARGB_8888, true);
                } else {
                    new Canvas(this.filterBitmap).drawBitmap(this.sourceBitmap, 0.0f, 0.0f, new Paint());
                }
                createTiltBlurBitmap();
                tiltSharpen(this.filterBitmap);
                this.titlFragment.setBitmaps(this.filterBitmap, this.bitmapTiltBlur);
                this.titlFragment.setTiltContext(this.parameterGlobal.tiltContext);
                this.titlFragment.setTiltListener(new AnonymousClass10(fm));
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.lyrebird_lib_tilt_fragment_container, this.titlFragment, "my_tilt_fragment");
                ft.commit();
                return;
            }
            if (this.bitmapTiltBlur == null) {
                createTiltBlurBitmap();
            }
            if (this.titlFragment.bitmap == null) {
                this.titlFragment.bitmap = this.filterBitmap;
            }
            if (this.titlFragment.bitmapBlur == null) {
                this.titlFragment.bitmapBlur = this.bitmapTiltBlur;
            }
            getChildFragmentManager().beginTransaction().show(this.titlFragment).commit();
        } else if (id == R.id.button_auto_set_parameters) {
            autoSetParameters();
        } else if (id == R.id.button_lib_cancel) {
            cancelViewSwitcher();
            this.viewSwitcher.setDisplayedChild(INDEX_FRAME);
        } else if (id == R.id.button_lib_ok) {
            this.viewSwitcher.setDisplayedChild(INDEX_FRAME);
        }
    }

    void tiltSharpen(Bitmap bitmap) {
        sharpen6(bitmap, 18, 0.1f);
    }

    private void cancelViewSwitcher() {
        if (this.parameterGlobal.isParameterReallyChanged(this.parameterBackUp)) {
            this.parameterGlobal.set(this.parameterBackUp);
            this.textureAdapter.setSelectedView(this.parameterGlobal.selectedTextureIndex);
            this.textureAdapter.notifyDataSetChanged();
            this.borderAdapter.setSelectedView(this.parameterGlobal.selectedBorderIndex);
            this.borderAdapter.notifyDataSetChanged();
            if (this.borderIndexChangedListener != null) {
                this.borderIndexChangedListener.onIndexChanged(this.parameterGlobal.selectedBorderIndex);
            }
            this.overlayAdapter.setSelectedView(this.parameterGlobal.selectedOverlayIndex);
            this.overlayAdapter.notifyDataSetChanged();
//            if (this.parameterGlobal.selectedFilterIndex >= this.filterAdapter.getItemCount()) {
//                this.parameterGlobal.selectedFilterIndex = INDEX_FX;
//            }
//            this.filterAdapter.setSelectedView(this.parameterGlobal.selectedFilterIndex);
//            this.filterAdapter.notifyDataSetChanged();
            execQueue();
        }
    }

    private void removeTiltFragment() {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.remove(this.titlFragment);
        ft.commit();
    }

    public void createTiltBlurBitmap() {
        if (this.bitmapTiltBlur == null || this.bitmapTiltBlur.isRecycled()) {
            if (this.blurBuilder == null) {
                this.blurBuilder = new BlurBuilderNormal();
            }
            float blurRadius = 15.0f * ((float) Math.sqrt((double) (((float) (this.sourceBitmap.getWidth() * this.sourceBitmap.getWidth())) / 4194304.0f)));
            if (((double) blurRadius) < 1.5d) {
                blurRadius = 1.5f;
            }
            this.bitmapTiltBlur = this.blurBuilder.createBlurBitmapNDK(this.sourceBitmap, (int) blurRadius);
        }
    }

    @SuppressLint("WrongConstant")
    void setSelectedTab(int index) {
        this.viewSwitcher.setDisplayedChild(INDEX_FX);
        if (this.buttonAuto == null) {
            this.buttonAuto = getView().findViewById(R.id.button_auto_set_parameters);
        }
        this.buttonAuto.setVisibility(INDEX_BRIGHTNESS);
        int displayedChild = this.viewFlipper.getDisplayedChild();
        if (index == 0) {
            setTabBg(INDEX_FX);
            if (displayedChild != 0) {
                this.viewFlipper.setInAnimation(this.slideLeftIn);
                this.viewFlipper.setOutAnimation(this.slideRightOut);
                this.viewFlipper.setDisplayedChild(INDEX_FX);
            } else {
                return;
            }
        }
        if (index == INDEX_FRAME) {
            setTabBg(INDEX_FRAME);
            if (displayedChild != INDEX_FRAME) {
                if (displayedChild == 0) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                }
                this.viewFlipper.setDisplayedChild(INDEX_FRAME);
            } else {
                return;
            }
        }
        if (index == INDEX_LIGHT) {
            setTabBg(INDEX_LIGHT);
            if (displayedChild != INDEX_LIGHT) {
                if (displayedChild == INDEX_TEXTURE || displayedChild == INDEX_BRIGHTNESS) {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                }
                this.viewFlipper.setDisplayedChild(INDEX_LIGHT);
            } else {
                return;
            }
        }
        if (index == INDEX_TEXTURE) {
            setTabBg(INDEX_TEXTURE);
            if (displayedChild != INDEX_TEXTURE) {
                if (displayedChild == INDEX_BRIGHTNESS) {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                }
                this.viewFlipper.setDisplayedChild(INDEX_TEXTURE);
            } else {
                return;
            }
        }
        if (index == INDEX_BRIGHTNESS || index == INDEX_CONTRAST || index == INDEX_SATURATION || index == INDEX_WARMTH || index == INDEX_TINT || index == INDEX_SHARPEN || index == INDEX_BLUR || index == INDEX_HIGHLIGHT || index == INDEX_SHADOW) {
            setTabBg(index);
            this.buttonAuto.setVisibility(INDEX_FX);
            if (displayedChild != INDEX_BRIGHTNESS) {
                this.viewFlipper.setInAnimation(this.slideRightIn);
                this.viewFlipper.setOutAnimation(this.slideLeftOut);
                this.viewFlipper.setDisplayedChild(INDEX_BRIGHTNESS);
            }
        }
    }

    void showAlertForPro() {
        if (this.buyProVersionListener != null) {
            this.buyProVersionListener.proVersionCalled();
            return;
        }
        @SuppressLint("WrongConstant") Toast msg = Toast.makeText(this.context, getString(R.string.lyrebirdlib_buy_pro), INDEX_FRAME);
        msg.setGravity(17, msg.getXOffset() / INDEX_LIGHT, msg.getYOffset() / INDEX_LIGHT);
        msg.show();
    }

    void resetParameters() {
        this.parameterGlobal.reset();
        setAdjustmentSeekbarProgress();
    }

    public Parameter getParameter() {
        return this.parameterGlobal;
    }

    public void setParameters(Parameter parameter) {
        this.parameterGlobal.set(parameter);
        setAdjustmentSeekbarProgress();
    }

    void resetParametersWithoutChange() {
        this.parameterGlobal.reset();
        setSelectedIndexes();
        setSeekBarProgress();
    }

    public void setAutoParameters(int brightness, int saturation, int contrast, int warmth) {
        this.parameterGlobal.setBrightness(brightness);
        this.parameterGlobal.setContrast(contrast);
        this.parameterGlobal.setSaturation(saturation);
        this.parameterGlobal.setTemperature(warmth);
        this.parameterGlobal.setTint(50);
        setAdjustmentSeekbarProgress();
    }

    void setAdjustmentSeekbarProgress() {
        setSeekBarProgress();
        setSelectedIndexes();
        execQueue();
    }

    void setSelectedIndexes() {
        this.textureAdapter.setSelectedView(this.parameterGlobal.selectedTextureIndex);
        this.borderAdapter.setSelectedView(this.parameterGlobal.selectedBorderIndex);
        this.overlayAdapter.setSelectedView(this.parameterGlobal.selectedOverlayIndex);
//        this.filterAdapter.setSelectedView(this.parameterGlobal.selectedFilterIndex);
    }

    void applyChangesOnBitmap() {
//        this.parameterGlobal.selectedFilterIndex = this.filterAdapter.getSelectedIndex();
        this.parameterGlobal.selectedBorderIndex = this.borderAdapter.getSelectedIndex();
        this.parameterGlobal.selectedTextureIndex = this.textureAdapter.getSelectedIndex();
        this.parameterGlobal.selectedOverlayIndex = this.overlayAdapter.getSelectedIndex();
        execQueue();
    }

    public void execQueue() {
        if (this.ft == null || this.ft.getStatus() != MyAsyncTask.Status.RUNNING) {
            this.ft = new FilterAndAdjustmentTask();
            try {
                this.ft.execute(new Void[INDEX_FX]);
            } catch (Exception e) {
            }
        }
    }

    static {
        filterBitmapTitle = new String[]{"None", "Gray", "Sepia", "Joey", "Sancia", "Blair", "Sura", "Tara", "Summer", "Penny", "Cuddy", "Cameron", "Lemon", "Tanya", "Lorelai", "Quinn", "Izabella", "Amber", "Cersei", "Debra", "Ellen", "Gabrielle", "Arya", "Lily", "Alexandra", "Nancy", "Daisy", "Brenda", "Sun", "Willow", "Ilina", "Faith", "Jess", "Cordelia"};
        libLoadIsFailed = false;
//        try {
//            System.loadLibrary("filter");
//            libLoadIsFailed = false;
//        } catch (Exception er) {
//            libLoadIsFailed = true;
//        }
    }

    public static void setFilter(int index, Bitmap btm) {
        if (index >= filterBitmapTitle.length) {
            index = INDEX_FX;
        }
        index--;
        if (VERSION.SDK_INT != INDEX_SATURATION && index != -1) {
            if (index == 0) {
                filterGray(btm);
            } else if (index == INDEX_FRAME) {
                filterSepia(btm);
            } else if (index == INDEX_LIGHT) {
                filterMain(btm);
            } else if (index == INDEX_TEXTURE) {
                filterAmber(btm);
            } else if (index == INDEX_BRIGHTNESS) {
                filterAnne(btm);
            } else if (index == INDEX_WARMTH) {
                filterPeter(btm);
            } else if (index == INDEX_CONTRAST) {
                filterSalomon(btm);
            } else if (index == INDEX_SATURATION) {
                filterKaren(btm);
            } else if (index == INDEX_TINT) {
                filterCross(btm);
            } else if (index == INDEX_SHARPEN) {
                filterCuddy(btm);
            } else if (index == INDEX_BLUR) {
                filterCameron(btm);
            } else if (index == INDEX_HIGHLIGHT) {
                filterAntonio(btm);
            } else if (index == INDEX_SHADOW) {
                filterNew1(btm);
            } else if (index == INDEX_TILT) {
                filterNew2(btm);
            } else if (index == TAB_SIZE) {
                filterNew3(btm);
            } else if (index == 15) {
                filterNew4(btm);
            } else if (index == 16) {
                filterIns10(btm);
            } else if (index == 17) {
                filterIns1Reverse(btm);
            } else if (index == 18) {
                filterIns1(btm);
            } else if (index == 19) {
                filterIns2(btm);
            } else if (index == 20) {
                filterIns3(btm);
            } else if (index == 21) {
                filterIns4(btm);
            } else if (index == 22) {
                filterIns5(btm);
            } else if (index == 23) {
                filterIns6(btm);
            } else if (index == 24) {
                filterIns7(btm);
            } else if (index == 25) {
                filterIns8(btm);
            } else if (index == 26) {
                filterIns9(btm);
            } else if (index == 27) {
                filterNew5(btm);
            } else if (index == 28) {
                filterIns11(btm);
            } else if (index == 29) {
                filterIns12(btm);
            } else if (index == 30) {
                filterIns13(btm);
            } else if (index == 31) {
                filterIns14(btm);
            } else if (index == 32) {
                filterIns15(btm);
            }
        }
    }

    private static void filterSepia(Bitmap src) {
        new Canvas(src).drawBitmap(src, 0.0f, 0.0f, sepiaPaint);
    }

    private static void filterGray(Bitmap src) {
        new Canvas(src).drawBitmap(src, 0.0f, 0.0f, grayPaint);
    }

    public static void initPaints() {
        sepiaPaint = new Paint();
        ColorMatrix sepiaMatrix = new ColorMatrix();
        sepiaMatrix.set(new float[]{0.393f, 0.769f, 0.189f, 0.0f, 0.0f, 0.349f, 0.686f, 0.168f, 0.0f, 0.0f, 0.272f, 0.534f, 0.131f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f});
        sepiaPaint.setColorFilter(new ColorMatrixColorFilter(sepiaMatrix));
        grayPaint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0.0f);
        grayPaint.setColorFilter(new ColorMatrixColorFilter(cm));
    }

    public void saveFullImageMember(String selectedImagePath, String resultPath) {
        Bitmap myOverlayBitmap = getOverlayBitmap(this.overlayAdapter.getSelectedIndex());
        Bitmap borderBitmap = BitmapFactory.decodeResource(getResources(), LibUtility.borderRes[this.borderAdapter.getSelectedIndex()]);
        Options o2 = new Options();
        o2.inSampleSize = INDEX_LIGHT;
        Bitmap textureBitmap = BitmapFactory.decodeResource(getResources(), LibUtility.textureRes[this.textureAdapter.getSelectedIndex()], o2);
        //saveFullImage(selectedImagePath, resultPath, myOverlayBitmap, borderBitmap, textureBitmap, INDEX_FX, this.filterAdapter.getSelectedIndex() - 1, isOverlayScreenMode(this.overlayAdapter.getSelectedIndex()), this.parameterGlobal.contrast, this.parameterGlobal.brightness, this.parameterGlobal.getTemperature(), this.parameterGlobal.getSaturation(), this.parameterGlobal.getTint(), LibUtility.textureModes[this.textureAdapter.getSelectedIndex()]);
    }

    public static void saveFullImage2(String selectedImagePath, String resultPath, Parameter parameter, Resources res) {
        Bitmap borderBitmap = BitmapFactory.decodeResource(res, LibUtility.borderRes[parameter.selectedBorderIndex]);
        Options o2 = new Options();
        o2.inSampleSize = INDEX_LIGHT;
        Bitmap myOverlayBitmap = BitmapFactory.decodeResource(res, LibUtility.overlayDrawableList[parameter.selectedOverlayIndex], o2);
        Bitmap textureBitmap = BitmapFactory.decodeResource(res, LibUtility.textureRes[parameter.selectedTextureIndex], o2);
        int i = parameter.selectedFilterIndex - 1;
        saveFullImage(selectedImagePath, resultPath, myOverlayBitmap, borderBitmap, textureBitmap, INDEX_FX, i, isOverlayScreenMode(parameter.selectedOverlayIndex), parameter.contrast, parameter.brightness, parameter.getTemperature(), parameter.getSaturation(), parameter.getTint(), LibUtility.textureModes[parameter.selectedTextureIndex]);
    }

    boolean checkAutoParameters() {
        if (this.autoParameters == null) {
            return false;
        }
        for (int i = INDEX_FX; i < this.parameterSize; i += INDEX_FRAME) {
            if (this.autoParameters[i] <= 0.0f) {
                return false;
            }
        }
        return true;
    }

    void autoSetParameters() {
    }
}