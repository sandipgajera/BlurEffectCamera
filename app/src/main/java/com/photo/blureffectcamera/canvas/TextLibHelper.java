package com.photo.blureffectcamera.canvas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.sticker.StickerData;
import com.photo.blureffectcamera.sticker.StickerLibHelper;
import com.photo.blureffectcamera.sticker.StickerView;


import java.util.ArrayList;


public class TextLibHelper {
    private static final String TAG;
    public static final String textFragmentTag = "myTextLibFragmentTag";
    Bitmap textBlackBar;
    Bitmap textEditBitmap;
    TextLibFragment textLibFragment;
    Bitmap textRemoveBitmap;
    Bitmap textScaleBitmap;
    TextLibFragment.TextStyledListener textStyledListener;
    Bitmap textSwitchBitmap;

    /* renamed from: com.lyrebirdstudio.canvastext.TextLibHelper.1 */
    class C13001 implements SingleTap {
        final /* synthetic */ FragmentActivity val$activity;
        final /* synthetic */ int val$parentId;
        final /* synthetic */ ViewGroup val$textViewContainer;

        C13001(FragmentActivity fragmentActivity, int i, ViewGroup viewGroup) {
            this.val$activity = fragmentActivity;
            this.val$parentId = i;
            this.val$textViewContainer = viewGroup;
        }

        public void onSingleTap(TextData textData) {
            TextLibHelper.this.textLibFragment = new TextLibFragment();
            Bundle arguments = new Bundle();
            arguments.putSerializable("text_data", textData);
            TextLibHelper.this.textLibFragment.setArguments(arguments);
            this.val$activity.getSupportFragmentManager().beginTransaction().replace(this.val$parentId, TextLibHelper.this.textLibFragment, TextLibHelper.textFragmentTag).commitAllowingStateLoss();
            TextLibHelper.this.textLibFragment.setTextStyledListener(TextLibHelper.this.createFragmentListener(this.val$activity, this.val$textViewContainer, this.val$parentId));
        }
    }

    /* renamed from: com.lyrebirdstudio.canvastext.TextLibHelper.2 */
    class C13012 implements TextLibFragment.TextStyledListener {
        final /* synthetic */ FragmentActivity val$activity;
        final /* synthetic */ FragmentManager val$fm;
        final /* synthetic */ int val$parentId;
        final /* synthetic */ ViewGroup val$textViewContainer;

        C13012(FragmentActivity fragmentActivity, ViewGroup viewGroup, int i, FragmentManager fragmentManager) {
            this.val$activity = fragmentActivity;
            this.val$textViewContainer = viewGroup;
            this.val$parentId = i;
            this.val$fm = fragmentManager;
        }

        public void onOk(TextData textData) {
            if (TextLibHelper.this.textRemoveBitmap == null) {
                TextLibHelper.this.textRemoveBitmap = BitmapFactory.decodeResource(this.val$activity.getResources(), R.drawable.remove_text);
            }
            if (TextLibHelper.this.textScaleBitmap == null) {
                TextLibHelper.this.textScaleBitmap = BitmapFactory.decodeResource(this.val$activity.getResources(), R.drawable.scale_text);
            }
            if (TextLibHelper.this.textEditBitmap == null) {
                TextLibHelper.this.textEditBitmap = BitmapFactory.decodeResource(this.val$activity.getResources(), R.drawable.ic_text_snap_edit2);
            }
            if (TextLibHelper.this.textSwitchBitmap == null) {
                TextLibHelper.this.textSwitchBitmap = BitmapFactory.decodeResource(this.val$activity.getResources(), R.drawable.ic_text_snap_switch);
            }
            if (TextLibHelper.this.textBlackBar == null) {
                TextLibHelper.this.textBlackBar = BitmapFactory.decodeResource(this.val$activity.getResources(), R.drawable.ic_text_black_bar);
            }
            CanvasTextView canvasTextView = null;
            for (int i = 0; i < this.val$textViewContainer.getChildCount(); i++) {
                View view = this.val$textViewContainer.getChildAt(i);
                if (view instanceof CanvasTextView) {
                    CanvasTextView ctv = (CanvasTextView) view;
                    if (ctv.textData.ID.compareTo(textData.ID) == 0) {
                        canvasTextView = ctv;
                    }
                }
            }
            if (canvasTextView == null) {
                Rect lineBound = new Rect();
                int maxLength = 0;
                int height = 0;
                for (String line : textData.message.split("\n")) {
                    height = (int) (((float) height) - ((-textData.textPaint.ascent()) + textData.textPaint.descent()));
                    textData.textPaint.getTextBounds(line, 0, line.length(), lineBound);
                    if (lineBound.width() > maxLength) {
                        maxLength = lineBound.width();
                    }
                }
                height = (int) (((float) height) + ((-textData.textPaint.ascent()) + textData.textPaint.descent()));
                float screenHeight = (float) this.val$activity.getResources().getDisplayMetrics().heightPixels;
                textData.xPos = (((float) this.val$activity.getResources().getDisplayMetrics().widthPixels) / 2.0f) - ((float) (maxLength / 2));
                textData.yPos = (screenHeight / 3.5f) - ((float) height);
                canvasTextView = new CanvasTextView(this.val$activity, textData, TextLibHelper.this.textRemoveBitmap, TextLibHelper.this.textScaleBitmap, TextLibHelper.this.textEditBitmap, TextLibHelper.this.textSwitchBitmap, TextLibHelper.this.textBlackBar);
                canvasTextView.setTextAndStickerViewSelectedListener(TextLibHelper.this.createTextAndStickerViewSelectedListener(this.val$textViewContainer));
                canvasTextView.setSingleTapListener(TextLibHelper.this.createSingleTapListener(this.val$activity, this.val$textViewContainer, this.val$parentId));
                this.val$textViewContainer.addView(canvasTextView);
            } else {
                canvasTextView.textData.set(textData);
                if (textData.getFontPath() != null) {
                    canvasTextView.textData.setTextFont(textData.getFontPath(), this.val$activity);
                }
            }
            canvasTextView.invalidate();
            this.val$fm.beginTransaction().hide(TextLibHelper.this.textLibFragment).commitAllowingStateLoss();
        }

        public void onCancel() {
            this.val$activity.getSupportFragmentManager().beginTransaction().hide(TextLibHelper.this.textLibFragment).commitAllowingStateLoss();
        }
    }

    /* renamed from: com.lyrebirdstudio.canvastext.TextLibHelper.3 */
    class C13023 implements ViewSelectedListener {
        final /* synthetic */ ViewGroup val$textViewContainer;

        C13023(ViewGroup viewGroup) {
            this.val$textViewContainer = viewGroup;
        }

        public void setSelectedView(CanvasTextView canvasTextView) {
            canvasTextView.bringToFront();
            canvasTextView.bringToFront();
            if (VERSION.SDK_INT < 19) {
                this.val$textViewContainer.requestLayout();
            }
        }
    }

    /* renamed from: com.lyrebirdstudio.canvastext.TextLibHelper.4 */
    class C13034 implements CanvasTextView.TextAndStickerViewSelectedListener {
        final /* synthetic */ ViewGroup val$textViewContainer;

        C13034(ViewGroup viewGroup) {
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
        TAG = TextLibHelper.class.getSimpleName();
    }

    public void addCanvasTextFragment(FragmentActivity fragmentActivity, ViewGroup textViewContainer, int parentId) {
        FragmentManager fm = fragmentActivity.getSupportFragmentManager();
        this.textLibFragment = (TextLibFragment) fm.findFragmentByTag(textFragmentTag);
        if (this.textLibFragment == null) {
            this.textLibFragment = new TextLibFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(parentId, this.textLibFragment, textFragmentTag);
            ft.commitAllowingStateLoss();
            this.textLibFragment.setTextStyledListener(createFragmentListener(fragmentActivity, textViewContainer, parentId));
            return;
        }
        fragmentActivity.getSupportFragmentManager().beginTransaction().show(this.textLibFragment).commitAllowingStateLoss();
    }

    public void addCanvasText2(FragmentActivity fragmentActivity, ViewGroup textViewContainer, int parentId) {
        if (fragmentActivity != null && textViewContainer != null) {
            this.textLibFragment = new TextLibFragment();
            this.textLibFragment.setArguments(new Bundle());
            fragmentActivity.getSupportFragmentManager().beginTransaction().replace(parentId, this.textLibFragment, textFragmentTag).commitAllowingStateLoss();
            this.textLibFragment.setTextStyledListener(createFragmentListener(fragmentActivity, textViewContainer, parentId));
        }
    }

    SingleTap createSingleTapListener(FragmentActivity activity, ViewGroup textViewContainer, int parentId) {
        return (activity == null || textViewContainer == null) ? null : new C13001(activity, parentId, textViewContainer);
    }

    TextLibFragment.TextStyledListener createFragmentListener(FragmentActivity activity, ViewGroup textViewContainer, int parentId) {
        if (activity == null || textViewContainer == null) {
            return null;
        }
        FragmentManager fm = activity.getSupportFragmentManager();
        if (this.textLibFragment == null) {
            this.textLibFragment = (TextLibFragment) fm.findFragmentByTag(textFragmentTag);
        }
        if (this.textStyledListener == null) {
            this.textStyledListener = new C13012(activity, textViewContainer, parentId, fm);
        }
        return this.textStyledListener;
    }

    ViewSelectedListener excreateCanvasTextViewSelectedListner(ViewGroup textViewContainer) {
        return new C13023(textViewContainer);
    }

    CanvasTextView.TextAndStickerViewSelectedListener createTextAndStickerViewSelectedListener(ViewGroup textViewContainer) {
        return textViewContainer == null ? null : new C13034(textViewContainer);
    }

    public void loadTextDataFromSavedInstance(FragmentActivity activity, Bundle savedInstanceState, ViewGroup textViewContainer, int parentId) {
        if (activity != null && textViewContainer != null && savedInstanceState != null) {
            TextData[] textDataArray = TextData.toTextData(savedInstanceState.getParcelableArray("text_data_array"));
            if (textDataArray != null) {
                Bitmap textRemoveBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.remove_text);
                Bitmap textScaleBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.scale_text);
                Bitmap textEditBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_text_snap_edit2);
                Bitmap textSwitchBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_text_snap_switch);
                Bitmap textBlackBar = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_text_black_bar);
                for (TextData canvasTextView : textDataArray) {
                    CanvasTextView canvasTextView2 = new CanvasTextView(activity, canvasTextView, textRemoveBitmap, textScaleBitmap, textEditBitmap, textSwitchBitmap, textBlackBar);
                    canvasTextView2.setTextAndStickerViewSelectedListener(createTextAndStickerViewSelectedListener(textViewContainer));
                    canvasTextView2.setSingleTapListener(createSingleTapListener(activity, textViewContainer, parentId));
                    textViewContainer.addView(canvasTextView2);
                }
            }
        }
    }

    public void saveTextDataInstance(Bundle savedInstanceState, ViewGroup textViewContainer) {
        if (textViewContainer != null && savedInstanceState != null) {
            int size = getCanvasTextChildCount(textViewContainer);
            if (textViewContainer != null && size > 0) {
                TextData[] textDataArray = new TextData[size];
                int index = 0;
                for (int i = 0; i < textViewContainer.getChildCount(); i++) {
                    View view = textViewContainer.getChildAt(i);
                    if (view instanceof CanvasTextView) {
                        textDataArray[index] = ((CanvasTextView) view).textData;
                        index++;
                    }
                }
                savedInstanceState.putParcelableArray("text_data_array", textDataArray);
            }
        }
    }

    public void saveTextAndStickerDataInstance(Bundle savedInstanceState, ViewGroup textViewContainer, Matrix matrix) {
        if (textViewContainer != null && savedInstanceState != null && textViewContainer != null && savedInstanceState != null) {
            int size = textViewContainer.getChildCount();
            if (textViewContainer != null && size > 0) {
                BaseData[] baseDataArray = new BaseData[size];
                for (int i = 0; i < size; i++) {
                    View view = textViewContainer.getChildAt(i);
                    if (view instanceof CanvasTextView) {
                        TextData data = ((CanvasTextView) view).textData;
                        if (matrix != null) {
                            data.setImageSaveMatrix(matrix);
                        }
                        baseDataArray[i] = data;
                    }
                    if (view instanceof StickerView) {
                        BaseData data2 = ((StickerView) view).getStickerData();
                        if (matrix != null) {
                            data2.setImageSaveMatrix(matrix);
                        }
                        baseDataArray[i] = data2;
                    }
                }
                savedInstanceState.putParcelableArray("base_data_array", baseDataArray);
            }
        }
    }

    public void loadTextAndStickerDataFromSavedInstance(FragmentActivity activity, Bundle savedInstanceState, ViewGroup textViewContainer, int parentId, Matrix matrixx) {
        if (textViewContainer != null && savedInstanceState != null) {
            BaseData[] baseDataArray = BaseData.toBaseData(savedInstanceState.getParcelableArray("base_data_array"));
            if (baseDataArray != null) {
                Bitmap textRemoveBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.remove_text);
                Bitmap textScaleBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.scale_text);
                Bitmap textEditBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_text_snap_edit2);
                Bitmap textSwitchBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_text_snap_switch);
                Bitmap textBlackBar = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_text_black_bar);
                Bitmap stickerremoveBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.sticker_remove_text);
                Bitmap stickerscaleBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.sticker_scale_text);
                for (BaseData baseData : baseDataArray) {
                    if (baseData instanceof TextData) {
                        CanvasTextView canvasTextView = new CanvasTextView(activity, (TextData) baseData, textRemoveBitmap, textScaleBitmap, textEditBitmap, textSwitchBitmap, textBlackBar);
                        canvasTextView.setTextAndStickerViewSelectedListener(createTextAndStickerViewSelectedListener(textViewContainer));
                        canvasTextView.setSingleTapListener(createSingleTapListener(activity, textViewContainer, parentId));
                        textViewContainer.addView(canvasTextView);
                    } else if (baseData instanceof StickerData) {
                        Bitmap bitmap;
                        StickerData stickerData = (StickerData) baseData;
                        if (stickerData.getPath() != null) {
                            bitmap = BitmapFactory.decodeFile(stickerData.getPath());
                        } else {
                            bitmap = BitmapFactory.decodeResource(activity.getResources(), stickerData.getResId());
                        }
                        StickerView stickerView = new StickerView(activity, bitmap, stickerData, stickerremoveBitmap, stickerscaleBitmap, stickerData.getResId(), stickerData.getPath());
                        stickerView.setTextAndStickerSelectedListner(StickerLibHelper.createTextAndStickerViewSelectedListener(textViewContainer));
                        textViewContainer.addView(stickerView);
                    }
                }
            }
        }
    }

    int getCanvasTextChildCount(ViewGroup parent) {
        int count = 0;
        int totalCount = parent.getChildCount();
        if (totalCount <= 0) {
            return 0;
        }
        for (int i = 0; i < totalCount; i++) {
            if (parent.getChildAt(i) instanceof CanvasTextView) {
                count++;
            }
        }
        return count;
    }

    public static ArrayList<TextData> getTextDataList(ViewGroup parent) {
        ArrayList<TextData> textDataList = new ArrayList();
        int totalCount = parent.getChildCount();
        for (int i = 0; i < totalCount; i++) {
            View child = parent.getChildAt(i);
            if (child instanceof CanvasTextView) {
                textDataList.add(((CanvasTextView) child).textData);
            }
        }
        return textDataList;
    }

    public void hideForOncreate(FragmentActivity activity, ViewGroup textViewContainer, int parentId) {
        if (activity != null && textViewContainer != null) {
            FragmentManager fm = activity.getSupportFragmentManager();
            this.textLibFragment = (TextLibFragment) fm.findFragmentByTag(textFragmentTag);
            if (this.textLibFragment != null) {
                fm.beginTransaction().hide(this.textLibFragment).commitAllowingStateLoss();
                this.textLibFragment.setTextStyledListener(createFragmentListener(activity, textViewContainer, parentId));
            }
        }
    }

    public boolean removeOnBackPressed(FragmentActivity activity) {
        if (activity == null) {
            return false;
        }
        if (this.textLibFragment == null) {
            this.textLibFragment = (TextLibFragment) activity.getSupportFragmentManager().findFragmentByTag(textFragmentTag);
        }
        if (this.textLibFragment == null || !this.textLibFragment.isVisible()) {
            return false;
        }
        activity.getSupportFragmentManager().beginTransaction().remove(this.textLibFragment).commitAllowingStateLoss();
        return true;
    }

    public boolean hideOnBackPressed(FragmentActivity activity) {
        if (activity == null) {
            return false;
        }
        if (this.textLibFragment == null) {
            this.textLibFragment = (TextLibFragment) activity.getSupportFragmentManager().findFragmentByTag(textFragmentTag);
        }
        if (this.textLibFragment == null || !this.textLibFragment.isVisible()) {
            return false;
        }
        activity.getSupportFragmentManager().beginTransaction().hide(this.textLibFragment).commitAllowingStateLoss();
        return true;
    }

    public static boolean onBackPressedForDecorateViewSelection(ViewGroup textAndStickerViewContainer) {
        if (textAndStickerViewContainer == null) {
            return false;
        }
        boolean result = false;
        for (int i = 0; i < textAndStickerViewContainer.getChildCount(); i++) {
            DecorateView view = (DecorateView) textAndStickerViewContainer.getChildAt(i);
            if (view.isDecorateViewSelected()) {
                view.setDecorateViewSelected(false);
                view.invalidate();
                result = true;
            }
        }
        return result;
    }

    public static void saveTextOnBitmap(Canvas bitmapCanvas, TextData textData, int screenWidth) {
        if (textData.getSnapMode()) {
            RectF rectSnap = new RectF();
            Rect lineBound = new Rect();
            CanvasTextView.setRectSnap(textData, rectSnap, (float) (screenWidth + 1));
            Canvas canvas = bitmapCanvas;
            TextData textData2 = textData;
            CanvasTextView.drawSnap(canvas, textData2, (float) ((screenWidth - CanvasTextView.getMaxLength(textData, lineBound, textData.message)) / 2), ((CanvasTextView.getSnapRectPadding(textData) + rectSnap.top) + ((float) CanvasTextView.getTextHeight(textData))) - textData.textPaint.descent(), rectSnap, CanvasTextView.paintSnap, lineBound);
            return;
        }
       Rect lineBound = new Rect();
        RectF rectBg = new RectF();
        Rect textBound = new Rect();
        Paint paint = new Paint(1);
        paint.setColor(textData.getBackgroundColorFinal());
        CanvasTextView.setBgRect(textData, rectBg, lineBound, textBound, (float) screenWidth);
        CanvasTextView.drawMultiline(bitmapCanvas, textData.message, textData.xPos, textData.yPos, textData.textPaint, textData, lineBound, rectBg, paint);
    }
}
