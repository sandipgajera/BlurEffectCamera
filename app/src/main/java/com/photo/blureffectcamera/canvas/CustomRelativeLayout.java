package com.photo.blureffectcamera.canvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.colorPicker.OnItemSelected;
import com.photo.blureffectcamera.colorPicker.RainbowPickerDialog;

import java.util.ArrayList;

public class CustomRelativeLayout extends RelativeLayout {
    ApplyTextInterface applyListener;
    ArrayList<CanvasTextView> canvasTextViewList;
    Context context;
    int currentCanvasTextIndex, i;
    LayoutParams levelParams;
    RelativeLayout mainLayout;
    public OnClickListener onClickListener;
    Bitmap removeBitmap;
    RemoveTextListener removeTextListener;
    Bitmap scaleBitmap;
    SingleTap singleTapListener;
    ArrayList<TextData> textDataList;
    ArrayList<TextData> textDataListOriginal;
    ViewSelectedListener viewSelectedListner;

    /* renamed from: com.lyrebirdstudio.canvastext.CustomRelativeLayout.3 */
    class C06843 implements OnClickListener {

        /* renamed from: com.lyrebirdstudio.canvastext.CustomRelativeLayout.3.1 */
        class C12961 implements OnItemSelected {
            C12961() {
            }

            public void itemSelected(int color) {
                if (!CustomRelativeLayout.this.canvasTextViewList.isEmpty()) {
                    ((CanvasTextView) CustomRelativeLayout.this.canvasTextViewList.get(CustomRelativeLayout.this.currentCanvasTextIndex)).setTextColor(color);
                }
            }
        }

        C06843() {
        }

        public void onClick(View v) {
            CustomRelativeLayout.this.hideSoftKeyboard((Activity) CustomRelativeLayout.this.context);
            int id = v.getId();
            if (id == R.id.button_text_color) {
                RainbowPickerDialog dialog = new RainbowPickerDialog(CustomRelativeLayout.this.context);
                dialog.setListener((OnItemSelected) new C12961());
                dialog.show();
            } else if (id == R.id.button_apply_action) {
                i = 0;
                while (i < CustomRelativeLayout.this.textDataList.size()) {
                    if (((TextData) CustomRelativeLayout.this.textDataList.get(i)).message.compareTo(TextData.defaultMessage) == 0) {
                        CustomRelativeLayout.this.textDataList.remove(i);
                        i--;
                    }
                    i++;
                }
                CustomRelativeLayout.this.applyListener.onOk(CustomRelativeLayout.this.textDataList);
            } else if (id == R.id.button_cancel_action) {
                CustomRelativeLayout.this.textDataList.clear();
                for (i = 0; i < CustomRelativeLayout.this.textDataListOriginal.size(); i++) {
                    CustomRelativeLayout.this.textDataList.add(CustomRelativeLayout.this.textDataListOriginal.get(i));
                }
                CustomRelativeLayout.this.applyListener.onCancel();
            } else if (id == R.id.button_add_text_action) {
                CustomRelativeLayout.this.addTextView(null);
            }
        }
    }

    public interface RemoveTextListener {
        void onRemove();
    }

    /* renamed from: com.lyrebirdstudio.canvastext.CustomRelativeLayout.1 */
    class C12941 implements RemoveTextListener {
        C12941() {
        }

        public void onRemove() {
            if (!CustomRelativeLayout.this.canvasTextViewList.isEmpty()) {
                CanvasTextView canvasTextView = (CanvasTextView) CustomRelativeLayout.this.canvasTextViewList.get(CustomRelativeLayout.this.currentCanvasTextIndex);
                CustomRelativeLayout.this.mainLayout.removeView(canvasTextView);
                CustomRelativeLayout.this.canvasTextViewList.remove(canvasTextView);
                CustomRelativeLayout.this.textDataList.remove(canvasTextView.textData);
                CustomRelativeLayout.this.currentCanvasTextIndex = CustomRelativeLayout.this.canvasTextViewList.size() - 1;
                if (!CustomRelativeLayout.this.canvasTextViewList.isEmpty()) {
                    ((CanvasTextView) CustomRelativeLayout.this.canvasTextViewList.get(CustomRelativeLayout.this.currentCanvasTextIndex)).setTextSelected(true);
                }
            }
        }
    }

    /* renamed from: com.lyrebirdstudio.canvastext.CustomRelativeLayout.2 */
    class C12952 implements ViewSelectedListener {
        C12952() {
        }

        public void setSelectedView(CanvasTextView cvt) {
            CustomRelativeLayout.this.currentCanvasTextIndex = CustomRelativeLayout.this.canvasTextViewList.indexOf(cvt);
            for (int i = 0; i < CustomRelativeLayout.this.canvasTextViewList.size(); i++) {
                if (CustomRelativeLayout.this.currentCanvasTextIndex != i) {
                    ((CanvasTextView) CustomRelativeLayout.this.canvasTextViewList.get(i)).setTextSelected(false);
                }
            }
        }
    }

    void loadBitmaps() {
        if (this.removeBitmap == null || this.removeBitmap.isRecycled()) {
            this.removeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.remove_text);
        }
        if (this.scaleBitmap == null || this.scaleBitmap.isRecycled()) {
            this.scaleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scale_text);
        }
    }

    public CustomRelativeLayout(Context c, ArrayList<TextData> textDataListParam, Matrix canvasMatrix, SingleTap l) {
        super(c);
        this.currentCanvasTextIndex = 0;
        this.removeTextListener = new C12941();
        this.viewSelectedListner = new C12952();
        this.onClickListener = new C06843();
        this.context = c;
        this.singleTapListener = l;
        loadBitmaps();
        ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.activity_canvas, this);
        this.mainLayout = (RelativeLayout) findViewById(R.id.canvas_relative_layout);
        this.levelParams = new LayoutParams(-1, -1);
        this.levelParams.addRule(15, -1);
        this.levelParams.addRule(14, -1);
        this.canvasTextViewList = new ArrayList();
        this.textDataList = new ArrayList();
        this.textDataListOriginal = new ArrayList();
        for (i = 0; i < textDataListParam.size(); i++) {
            this.textDataListOriginal.add(new TextData((TextData) textDataListParam.get(i)));
            this.textDataList.add(new TextData((TextData) textDataListParam.get(i)));
        }
        for (i = 0; i < this.textDataList.size(); i++) {
            CanvasTextView canvasTextView = new CanvasTextView(this.context, (TextData) this.textDataList.get(i), this.removeBitmap, this.scaleBitmap, null, null, null);
            canvasTextView.setSingleTapListener(this.singleTapListener);
            canvasTextView.setViewSelectedListener(this.viewSelectedListner);
            canvasTextView.setRemoveTextListener(this.removeTextListener);
            this.mainLayout.addView(canvasTextView, this.levelParams);
            this.canvasTextViewList.add(canvasTextView);
            MyMatrix textMatrix = new MyMatrix(canvasMatrix);
            textMatrix.set(((TextData) this.textDataList.get(i)).getImageSaveMatrix());
            textMatrix.postConcat(canvasMatrix);
            canvasTextView.setMatrix(textMatrix);
        }
        if (0 == 0 && !this.canvasTextViewList.isEmpty()) {
            ((CanvasTextView) this.canvasTextViewList.get(this.canvasTextViewList.size() - 1)).setTextSelected(true);
            this.currentCanvasTextIndex = this.canvasTextViewList.size() - 1;
        }
        ((LinearLayout) findViewById(R.id.text_header)).bringToFront();
        ((LinearLayout) findViewById(R.id.text_footer)).bringToFront();
        View okButton = findViewById(R.id.button_apply_action);
        View cancelButton = findViewById(R.id.button_cancel_action);
        ImageButton addTextButton = (ImageButton) findViewById(R.id.button_add_text_action);
        okButton.setOnClickListener(this.onClickListener);
        cancelButton.setOnClickListener(this.onClickListener);
        addTextButton.setOnClickListener(this.onClickListener);
    }

    public void setSingleTapListener(SingleTap l) {
        this.singleTapListener = l;
    }

    public void setApplyTextListener(ApplyTextInterface l) {
        this.applyListener = l;
    }

    public void addTextView(TextData textData) {
        if (this.textDataList.contains(textData)) {
            ((CanvasTextView) this.canvasTextViewList.get(this.currentCanvasTextIndex)).setNewTextData(textData);
            return;
        }
        for (int i = 0; i < this.canvasTextViewList.size(); i++) {
            ((CanvasTextView) this.canvasTextViewList.get(i)).setTextSelected(false);
        }
        this.currentCanvasTextIndex = this.canvasTextViewList.size();
        loadBitmaps();
        CanvasTextView canvasTextView = new CanvasTextView(this.context, textData, this.removeBitmap, this.scaleBitmap, null, null, null);
        canvasTextView.setSingleTapListener(this.singleTapListener);
        canvasTextView.setViewSelectedListener(this.viewSelectedListner);

        canvasTextView.setRemoveTextListener(this.removeTextListener);
        this.mainLayout.addView(canvasTextView, this.levelParams);
        this.canvasTextViewList.add(canvasTextView);
        this.textDataList.add(canvasTextView.textData);
        canvasTextView.setTextSelected(true);
        canvasTextView.singleTapped();
    }

    public void addTextDataEx(TextData textData) {
        if (this.textDataList.contains(textData)) {
            ((CanvasTextView) this.canvasTextViewList.get(this.currentCanvasTextIndex)).setNewTextData(textData);
            return;
        }
        for (int i = 0; i < this.canvasTextViewList.size(); i++) {
            ((CanvasTextView) this.canvasTextViewList.get(i)).setTextSelected(false);
        }
        this.currentCanvasTextIndex = this.canvasTextViewList.size();
        CanvasTextView canvasTextView = new CanvasTextView(this.context, textData, this.removeBitmap, this.scaleBitmap, null, null, null);
        canvasTextView.setSingleTapListener(this.singleTapListener);
        canvasTextView.setViewSelectedListener(this.viewSelectedListner);
        canvasTextView.setRemoveTextListener(this.removeTextListener);
    }

    public boolean onTouchEvent(MotionEvent event) {
        hideSoftKeyboard((Activity) this.context);
        return true;
    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
