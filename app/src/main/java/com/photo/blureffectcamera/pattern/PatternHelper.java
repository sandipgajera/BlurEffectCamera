package com.photo.blureffectcamera.pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Debug;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;


import com.photo.blureffectcamera.collagelib.ShapeCollageActivity;
import com.photo.blureffectcamera.collagelib.Utility;
import com.photo.blureffectcamera.imageloader.ImageLoader;
import com.photo.blureffectcamera.imageloader.ImageUtility;

import java.util.ArrayList;


public class PatternHelper {
    public static final int MAX_SIZE_DEFAULT = 800; 
    public static final int PATTERN_SENTINEL = -1;
    public static final int SELCT_IMAGE_BG = 244;
    public static final String TAG = "PatternHelper";
    public static ArrayList<String> sdIdList;
    Activity activity;
    public Bitmap bitmapBlur;
    LinearLayout colorContainer;
    ImageLoader.ImageLoaded imageLoadedListener;
    ImageLoader imageLoader;
    public PatternAdapter patternAdapter;
    ArrayList<RecyclerView.Adapter<RecyclerView.ViewHolder>> patternAdapterList;
    PatternBitmapColorListener patternBitmapColorListener;
    RecyclerView recyclerView;

    public interface PatternBitmapColorListener 
    {
        void patternBitmapReady(int i, Bitmap bitmap);

        void patternColorReady(int i);

        void patternImageReady(Bitmap bitmap);

        void setBacgkroundMode();
    }

    /* renamed from: com.lyrebirdstudio.pattern.PatternHelper.1 */
    class C13411 implements PatternAdapter.CurrentCollageIndexChangedListener
    {
        C13411() {
        }

        public void onIndexChanged(int position) {
            if (PatternHelper.this.patternBitmapColorListener != null) {
                PatternHelper.this.patternBitmapColorListener.setBacgkroundMode();
                if (position == 0) {
                    PatternHelper.this.patternBitmapColorListener.patternBitmapReady(PatternHelper.PATTERN_SENTINEL, null);
                } else if (position != 0 && PatternHelper.this.patternAdapterList != null && !PatternHelper.this.patternAdapterList.isEmpty()) {
                    int newPos = position + PatternHelper.PATTERN_SENTINEL;
                    if (PatternHelper.this.patternAdapterList.get(newPos) != PatternHelper.this.recyclerView.getAdapter()) {
                        PatternHelper.this.recyclerView.setAdapter((RecyclerView.Adapter) PatternHelper.this.patternAdapterList.get(newPos));
                    } else {
                    }
                    if (PatternHelper.this.colorContainer != null) {
                        PatternHelper.this.colorContainer.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

        public void onPatternChanged(PatternItem item) {
        }
    }

    class C13422 implements PatternAdapter.CurrentCollageIndexChangedListener {
    	C13422() {
       }

        public void onIndexChanged(int color) {
            if (PatternHelper.this.patternBitmapColorListener != null) {
                PatternHelper.this.patternBitmapColorListener.patternColorReady(color);
            }
        }

        public void onPatternChanged(PatternItem item) 
        {
        }
    }

    /* renamed from: com.lyrebirdstudio.pattern.PatternHelper.3 */
    class C13433 implements PatternAdapter.CurrentCollageIndexChangedListener {
    	   C13433() {
          }

        public void onIndexChanged(int color) {
            if (PatternHelper.this.patternBitmapColorListener != null) {
                PatternHelper.this.patternBitmapColorListener.patternColorReady(color);
            }
        }

        public void onPatternChanged(PatternItem item) {
        }
    }

    class C13444 implements PatternAdapter.CurrentCollageIndexChangedListener {
        final /* synthetic */ Activity val$activity;

        C13444(Activity activity) {
            this.val$activity = activity;
        }

        public void onIndexChanged(int positionOrColor) {
        }

        public void onPatternChanged(PatternItem item) {
            if (PatternHelper.this.patternBitmapColorListener != null) {
                Bitmap patternBitmap;
                if (item.isFromOnline) {
                    patternBitmap = BitmapFactory.decodeFile(item.path);
                } else {
                    patternBitmap = BitmapFactory.decodeResource(this.val$activity.getResources(),item.resId);
                }
                bitmapBlur = patternBitmap;
                Utility.backBitmapBlur = patternBitmap;
                ShapeCollageActivity.isColor = false;
                Utility.SET_PREV_IMG = false;
                PatternHelper.this.patternBitmapColorListener.patternBitmapReady(0, bitmapBlur);
            } 
        }
    }

    /* renamed from: com.lyrebirdstudio.pattern.PatternHelper.5 */
    class C13455 implements PatternAdapter.CurrentCollageIndexChangedListener {
        final /* synthetic */ Activity val$activity;

        C13455(Activity activity) {
            this.val$activity = activity;
        }

        public void onIndexChanged(int positionOrColor) {
        }

        public void onPatternChanged(PatternItem item) {
            Bitmap patternBitmap;
            if (item.isFromOnline)
            {
                patternBitmap = BitmapFactory.decodeFile(item.path);
            } 
            else 
            {
                patternBitmap =  BitmapFactory.decodeResource(this.val$activity.getResources(), item.resId);
            }
            
            bitmapBlur = patternBitmap;
            ShapeCollageActivity.isColor = false;
            Utility.SET_PREV_IMG = false;
            PatternHelper.this.patternBitmapColorListener.patternBitmapReady(0, bitmapBlur);
        }
    }

    class C13477 implements ImageLoader.ImageLoaded {
        C13477() {
        }

        public void callFileSizeAlertDialogBuilder() {
            int maxSize = PatternHelper.MAX_SIZE_DEFAULT;
            if (PatternHelper.this.activity != null) {
                maxSize = maxSizeForDimension(PatternHelper.this.activity, 1, 800.0f);
            }
            Bitmap btm = PatternHelper.this.readBitapBlur(PatternHelper.this.imageLoader.selectedImagePath, maxSize);
            if (btm != null) {
            	
                PatternHelper.this.patternBitmapColorListener.patternImageReady(btm);
            }
        }
        
        public int maxSizeForDimension(final Context context, final int n, final float n2) {
            float n3 = 30.0f;
            if (Build.VERSION.SDK_INT <= 11) {
                n3 = 160.0f;
            }
            int defaultLimit;
            if ((defaultLimit = (int) Math.sqrt(getLeftSizeOfMemory() / (n * n3))) <= 0)
            {
                defaultLimit = getDefaultLimit(n, n2);
            }
            return Math.min(defaultLimit, getDefaultLimit(n, n2));
        }
        private int getDefaultLimit(int n, final float n2) {
            n = (int)(n2 / Math.sqrt(n));
            return n;
        }
        public  double getLeftSizeOfMemory() {
            return Double.valueOf(Runtime.getRuntime().maxMemory()) - (Double.valueOf(Runtime.getRuntime().totalMemory()) - Double.valueOf(Runtime.getRuntime().freeMemory())) - Double.valueOf(Debug.getNativeHeapAllocatedSize());
        }
    }

    static {
        sdIdList = new ArrayList();
    }

    public PatternHelper(FragmentActivity activity, PatternBitmapColorListener patternBitmapColorListener, LinearLayout colorContainer, RecyclerView recyclerView, int colorDefault, int colorSelected) {
        this.patternAdapterList = new ArrayList();
        this.imageLoadedListener = new C13477();
        this.patternBitmapColorListener = patternBitmapColorListener;
        this.colorContainer = colorContainer;
        this.recyclerView = recyclerView;
        sdIdList = new ArrayList();
        this.activity = activity;
    }

    public void createPatternAdapter(FragmentActivity activity, int colorDefault, int colorSelected) {
    	
        this.patternAdapter = new PatternAdapter(UtilityPattern.getPatternIconList(activity), new C13411(), colorDefault, colorSelected, false, false);
        for (int i = 0; i < this.patternAdapter.patternItemArrayList.size(); i++) 
        {
            PatternItem p = (PatternItem) this.patternAdapter.patternItemArrayList.get(i);
        }
        this.patternAdapter.setSelectedPositinVoid();
        this.recyclerView.setAdapter(new PatternColorPickerAdapter(new C13422(), colorDefault, colorSelected));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        createAdapterList(activity, colorDefault, colorSelected);
    }

    private void createAdapterList(Activity activity, int colorDefault, int colorSelected) {
        ArrayList<PatternItem> patternItems;
        int size = UtilityPattern.patternResIdList2.length;
        this.patternAdapterList.clear();
        this.patternAdapterList.add(new PatternColorPickerAdapter(new C13433(), colorDefault, colorSelected));
        int s = this.patternAdapter.patternItemArrayList.size();
        for (int x = 0; x < s; x++) {
            if (((PatternItem) this.patternAdapter.patternItemArrayList.get(x)).isFromOnline) {
                patternItems = UtilityPattern.getPatternIconsFromSdCard(((PatternItem) this.patternAdapter.patternItemArrayList.get(x)).path);
                if (!(patternItems == null || patternItems.isEmpty())) {
                    this.patternAdapterList.add(new PatternAdapter(patternItems, new C13444(activity), colorDefault, colorSelected, true, true));
                }
            }
        }
        for (int i = 0; i < size; i++) {
            patternItems = new ArrayList();
            for (int patternItem : UtilityPattern.patternResIdList2[i]) {
                patternItems.add(new PatternItem(patternItem));
            }
            this.patternAdapterList.add(new PatternAdapter(patternItems, new C13455(activity), colorDefault, colorSelected, true, true));
        }
        if (this.patternAdapter.patternItemArrayList.size() != this.patternAdapterList.size() + 1) {
            this.patternAdapter.setData(UtilityPattern.getPatternIconList(activity));
        }
    }

    public static boolean onBackPressed(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        PatternDetailFragment patternDetailFragment = (PatternDetailFragment) fm.findFragmentByTag(PatternDetailFragment.FRAGMENT_TAG);
        PatternDeleteFragment patternDeleteFragment = (PatternDeleteFragment) fm.findFragmentByTag(PatternDeleteFragment.FRAGMENT_TAG);
        FragmentTransaction ft;
        if (patternDeleteFragment != null && patternDeleteFragment.isVisible()) {
            ft = fm.beginTransaction();
            ft.remove(patternDeleteFragment);
            ft.commitAllowingStateLoss();
            return true;
        } else if (patternDetailFragment != null && patternDetailFragment.isVisible()) {
            ft = fm.beginTransaction();
            ft.remove(patternDetailFragment);
            ft.commitAllowingStateLoss();
            return true;
        }  else {
            ft = fm.beginTransaction();
            ft.commitAllowingStateLoss();
            return true;
        }
    }


    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void selectImage() {
        if (this.activity != null) {
            this.imageLoader = new ImageLoader(this.activity);
            this.imageLoader.setListener(this.imageLoadedListener);
        }
    }

    private Bitmap readBitapBlur(String selectedImagePath, int maxSize) {
        if (this.bitmapBlur != null) {
            this.bitmapBlur.recycle();
            ShapeCollageActivity.isColor = false;
            Utility.SET_PREV_IMG = false;
        }
        Bitmap readTemp = ImageUtility.decodeBitmapFromFile(selectedImagePath, maxSize/2 );
        if (readTemp == null) {
            return null;
        }
        this.bitmapBlur = readTemp;
        ShapeCollageActivity.isColor = false;
        Utility.SET_PREV_IMG = false;
        return this.bitmapBlur;
    }

    public static Bitmap createCroppedBitmap(Bitmap src, int left, int top, int width, int height, boolean filter) {
        Bitmap result = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setFilterBitmap(filter);
        canvas.drawBitmap(src, (float) (-left), (float) (-top), paint);
        return result;
    }

    public void activityResult(int resultCode, Intent data) {
        if (resultCode == PATTERN_SENTINEL) {
            if (this.imageLoader == null) {
                selectImage();
            }
            this.imageLoader.getImageFromIntent(data);
        }
    }
}
