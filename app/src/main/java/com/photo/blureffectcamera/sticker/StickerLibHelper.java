package com.photo.blureffectcamera.sticker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.canvas.BaseData;
import com.photo.blureffectcamera.canvas.CanvasTextView;
import com.photo.blureffectcamera.canvas.DecorateView;

import java.util.ArrayList;


public class StickerLibHelper {
    private static final String TAG;
    public static final String fragmentTag = "myStickerFragmentTag";
    StickerGalleryFragment stickerGalleryFragment;
    StickerGalleryFragment.StickerGalleryListener stickerGalleryListener;
    Bitmap stickerremoveBitmap;
    Bitmap stickerscaleBitmap;

    /* renamed from: com.lyrebirdstudio.sticker.StickerLibHelper.1 */
    class C13571 implements StickerGalleryFragment.StickerGalleryListener {
        final /* synthetic */ FragmentActivity val$activity;
        final /* synthetic */ ViewGroup val$stickerViewContainer;

        C13571(FragmentActivity fragmentActivity, ViewGroup viewGroup) {
            this.val$activity = fragmentActivity;
            this.val$stickerViewContainer = viewGroup;
        }

        public void onGalleryOkImageArray(StickerGridItem[] ImageIdList) {
            if (StickerLibHelper.this.stickerremoveBitmap == null) {
                StickerLibHelper.this.stickerremoveBitmap = BitmapFactory.decodeResource(this.val$activity.getResources(), R.drawable.sticker_remove_text);
            }
            if (StickerLibHelper.this.stickerscaleBitmap == null) {
                StickerLibHelper.this.stickerscaleBitmap = BitmapFactory.decodeResource(this.val$activity.getResources(), R.drawable.sticker_scale_text);
            }
            for (int i = 0; i < ImageIdList.length; i++) {
                StickerView stickerView;
                if (!ImageIdList[i].isOnline) {
                    stickerView = new StickerView(this.val$activity, BitmapFactory.decodeResource(this.val$activity.getResources(), ImageIdList[i].resId), null, StickerLibHelper.this.stickerremoveBitmap, StickerLibHelper.this.stickerscaleBitmap, ImageIdList[i].resId, null);
                    stickerView.setTextAndStickerSelectedListner(StickerLibHelper.createTextAndStickerViewSelectedListener(this.val$stickerViewContainer));
                    this.val$stickerViewContainer.addView(stickerView);
                } else if (ImageIdList[i].path != null) {
                    Bitmap bitmap = BitmapFactory.decodeFile(ImageIdList[i].path);
                    if (bitmap != null) {
                        stickerView = new StickerView(this.val$activity, bitmap, null, StickerLibHelper.this.stickerremoveBitmap, StickerLibHelper.this.stickerscaleBitmap, ImageIdList[i].resId, ImageIdList[i].path);
                        stickerView.setTextAndStickerSelectedListner(StickerLibHelper.createTextAndStickerViewSelectedListener(this.val$stickerViewContainer));
                        this.val$stickerViewContainer.addView(stickerView);
                    }
                }
            }
            FragmentManager fm = this.val$activity.getSupportFragmentManager();
            if (StickerLibHelper.this.stickerGalleryFragment == null) {
                StickerLibHelper.this.stickerGalleryFragment = (StickerGalleryFragment) fm.findFragmentByTag(StickerLibHelper.fragmentTag);
            }
            fm.beginTransaction().hide(StickerLibHelper.this.stickerGalleryFragment).commitAllowingStateLoss();
        }

        public void onGalleryCancel() {
            this.val$activity.getSupportFragmentManager().beginTransaction().hide(StickerLibHelper.this.stickerGalleryFragment).commitAllowingStateLoss();
        }
    }

    /* renamed from: com.lyrebirdstudio.sticker.StickerLibHelper.2 */
    static class C13582 implements StickerView.StickerViewSelectedListener {
        final /* synthetic */ ViewGroup val$stickerViewContainer;

        C13582(ViewGroup viewGroup) {
            this.val$stickerViewContainer = viewGroup;
        }

        public void setSelectedView(StickerView stickerView) {
            stickerView.bringToFront();
            stickerView.bringToFront();
            if (VERSION.SDK_INT < 19) {
                this.val$stickerViewContainer.requestLayout();
            }
        }

        public void onTouchUp(StickerData data) {
        }
    }

    /* renamed from: com.lyrebirdstudio.sticker.StickerLibHelper.3 */
    static class C13593 implements CanvasTextView.TextAndStickerViewSelectedListener {
        final /* synthetic */ ViewGroup val$textViewContainer;

        C13593(ViewGroup viewGroup) {
            this.val$textViewContainer = viewGroup;
        }

        public void setSelectedView(DecorateView decorateView) {
            decorateView.bringToFront();
            if (VERSION.SDK_INT < 19) {
                this.val$textViewContainer.requestLayout();
            }
        }

        public void onTouchUp(BaseData data) {
        }
    }

    static {
        TAG = StickerLibHelper.class.getSimpleName();
    }

    public void addStickerGalleryFragment(FragmentActivity activity, ViewGroup stickerViewContainer, int parentId) {
        if (activity != null && stickerViewContainer != null) {
            FragmentManager fm = activity.getSupportFragmentManager();
            this.stickerGalleryFragment = (StickerGalleryFragment) fm.findFragmentByTag(fragmentTag);
            if (this.stickerGalleryFragment == null) {
                this.stickerGalleryFragment = new StickerGalleryFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(parentId, this.stickerGalleryFragment, fragmentTag);
                ft.commitAllowingStateLoss();
                this.stickerGalleryFragment.setGalleryListener(createGalleryListener(activity, stickerViewContainer));
            } else {
                activity.getSupportFragmentManager().beginTransaction().show(this.stickerGalleryFragment).commitAllowingStateLoss();
            }
            this.stickerGalleryFragment.setTotalImage(0);
        }
    }

    int getStickerChildCount(ViewGroup parent) {
        int count = 0;
        int totalCount = parent.getChildCount();
        if (totalCount <= 0) {
            return 0;
        }
        for (int i = 0; i < totalCount; i++) {
            if (parent.getChildAt(i) instanceof StickerView) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<StickerView> getStickerViewList(ViewGroup parent) {
        ArrayList<StickerView> stickerViewList = new ArrayList();
        int totalCount = parent.getChildCount();
        if (totalCount > 0) {
            for (int i = 0; i < totalCount; i++) {
                View child = parent.getChildAt(i);
                if (child instanceof StickerView) {
                    stickerViewList.add((StickerView) child);
                }
            }
        }
        return stickerViewList;
    }

    StickerGalleryFragment.StickerGalleryListener createGalleryListener(FragmentActivity activity, ViewGroup stickerViewContainer) {
        if (activity == null || stickerViewContainer == null) {
            return null;
        }
        if (this.stickerGalleryListener == null) {
            this.stickerGalleryListener = new C13571(activity, stickerViewContainer);
        }
        return this.stickerGalleryListener;
    }

    public static StickerView.StickerViewSelectedListener excreateStickerViewSelectedListner(ViewGroup stickerViewContainer) {
        return new C13582(stickerViewContainer);
    }

    public static CanvasTextView.TextAndStickerViewSelectedListener createTextAndStickerViewSelectedListener(ViewGroup textViewContainer) {
        return new C13593(textViewContainer);
    }

    public void loadStixckerDataFromSavedInstance(FragmentActivity activity, Bundle savedInstanceState, ViewGroup stickerViewContainer) {
        if (activity != null && stickerViewContainer != null) {
            StickerData[] stickerDataArray = StickerData.toStickerData(savedInstanceState.getParcelableArray("sticker_data_array"));
            if (stickerDataArray != null) {
                Bitmap stickerremoveBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.sticker_remove_text);
                Bitmap stickerscaleBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.sticker_scale_text);
                for (int i = 0; i < stickerDataArray.length; i++) {
                    StickerView stickerView = new StickerView(activity, BitmapFactory.decodeResource(activity.getResources(), stickerDataArray[i].getResId()), stickerDataArray[i], stickerremoveBitmap, stickerscaleBitmap, stickerDataArray[i].getResId(), stickerDataArray[i].path);
                    stickerView.setTextAndStickerSelectedListner(createTextAndStickerViewSelectedListener(stickerViewContainer));
                    stickerViewContainer.addView(stickerView);
                }
            }
        }
    }

    public void saveStickerDataInstance(Bundle savedInstanceState, ViewGroup stickerViewContainer) {
        if (savedInstanceState != null && stickerViewContainer != null) {
            int size = getStickerChildCount(stickerViewContainer);
            if (stickerViewContainer != null && size > 0) {
                StickerData[] stickerDataArray = new StickerData[size];
                int index = 0;
                for (int i = 0; i < stickerViewContainer.getChildCount(); i++) {
                    View view = stickerViewContainer.getChildAt(i);
                    if (view instanceof StickerView) {
                        stickerDataArray[index] = ((StickerView) view).getStickerData();
                        index++;
                    }
                }
                savedInstanceState.putParcelableArray("sticker_data_array", stickerDataArray);
            }
        }
    }

    public void hideForOncreate(FragmentActivity activity, ViewGroup stickerViewContainer) {
        if (activity != null && stickerViewContainer != null) {
            FragmentManager fm = activity.getSupportFragmentManager();
            this.stickerGalleryFragment = (StickerGalleryFragment) fm.findFragmentByTag(fragmentTag);
            if (this.stickerGalleryFragment != null) {
                fm.beginTransaction().hide(this.stickerGalleryFragment).commitAllowingStateLoss();
                this.stickerGalleryFragment.setGalleryListener(createGalleryListener(activity, stickerViewContainer));
            }
        }
    }

    public boolean hideOnBackPressed() {
        if (this.stickerGalleryFragment == null || !this.stickerGalleryFragment.isVisible()) {
            return false;
        }
        this.stickerGalleryFragment.backtrace();
        return true;
    }

    public boolean removeOnBackPressed(FragmentActivity activity) {
        if (this.stickerGalleryFragment == null) {
            this.stickerGalleryFragment = (StickerGalleryFragment) activity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
        }
        if (this.stickerGalleryFragment == null || !this.stickerGalleryFragment.isVisible()) {
            return false;
        }
        activity.getSupportFragmentManager().beginTransaction().remove(this.stickerGalleryFragment).commitAllowingStateLoss();
        return true;
    }
}