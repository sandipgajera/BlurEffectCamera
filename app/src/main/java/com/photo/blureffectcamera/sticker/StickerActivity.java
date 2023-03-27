package com.photo.blureffectcamera.sticker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.FragmentActivity;


import com.photo.blureffectcamera.R;

import java.util.ArrayList;


public class StickerActivity extends FragmentActivity {
    protected static final String TAG;
    Context context;
    int currentStickerIndex;
    StickerGalleryFragment galleryFragment;
    ArrayList<StickerView> stickerList;
    FrameLayout stickerViewContainer;
    StickerView.StickerViewSelectedListener stickerViewSelectedListner;

    /* renamed from: com.lyrebirdstudio.sticker.StickerActivity.1 */
    class C13541 implements StickerView.StickerViewSelectedListener {
        C13541() {
        }

        public void setSelectedView(StickerView stickerView) {
            stickerView.bringToFront();
            StickerActivity.this.currentStickerIndex = StickerActivity.this.stickerList.indexOf(stickerView);
            for (int i = 0; i < StickerActivity.this.stickerList.size(); i++) {
                if (StickerActivity.this.currentStickerIndex != i) {
                    ((StickerView) StickerActivity.this.stickerList.get(i)).setViewSelected(false);
                } else {
                    ((StickerView) StickerActivity.this.stickerList.get(i)).setViewSelected(true);
                }
            }
            stickerView.bringToFront();
            if (VERSION.SDK_INT < 19) {
                StickerActivity.this.stickerViewContainer.requestLayout();
            }
        }

        public void onTouchUp(StickerData data) {
        }
    }

    public StickerActivity() {
        this.context = this;
        this.currentStickerIndex = 0;
        this.stickerViewSelectedListner = new C13541();
    }

    static {
        TAG = StickerActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (this.stickerViewContainer != null && this.stickerViewContainer.getChildCount() > 0) {
            int size = this.stickerViewContainer.getChildCount();
            StickerData[] stickerDataArray = new StickerData[size];
            for (int i = 0; i < size; i++) {
                stickerDataArray[i] = ((StickerView) this.stickerViewContainer.getChildAt(i)).getStickerData();
                Log.e(TAG, "stickerDataArray[i].resId " + stickerDataArray[i].resId);
            }
            savedInstanceState.putParcelableArray("sticker_data_array", stickerDataArray);
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Bitmap removeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sticker_remove_text);
        Bitmap scaleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sticker_scale_text);
        StickerData[] stickerDataArray = (StickerData[]) savedInstanceState.getParcelableArray("sticker_data_array");
        for (int i = 0; i < stickerDataArray.length; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), stickerDataArray[i].resId);
            Log.e(TAG, "stickerDataArray[i].resId " + stickerDataArray[i].resId);
            StickerView stickerView = new StickerView(this.context, bitmap, stickerDataArray[i], removeBitmap, scaleBitmap, stickerDataArray[i].resId, null);
            stickerView.setStickerViewSelectedListener(this.stickerViewSelectedListner);
            if (this.stickerViewContainer == null) {
                this.stickerViewContainer = (FrameLayout) findViewById(R.id.sticker_view_container);
            }
            this.stickerViewContainer.addView(stickerView);
        }
    }

    public void onBackPressed() {
        this.galleryFragment = (StickerGalleryFragment) getSupportFragmentManager().findFragmentByTag(StickerLibHelper.fragmentTag);
        if (this.galleryFragment == null || !this.galleryFragment.isVisible()) {
            finish();
        } else {
//            this.galleryFragment.backtrace();
        }
    }
}
