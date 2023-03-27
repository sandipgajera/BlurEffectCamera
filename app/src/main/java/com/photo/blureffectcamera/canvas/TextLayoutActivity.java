package com.photo.blureffectcamera.canvas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.FragmentActivity;


import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.pattern.PatternHelper;

import java.util.ArrayList;

@SuppressLint("NewApi")
public class TextLayoutActivity extends FragmentActivity {
    int MODE_COLOR;
    int MODE_FONT;
    int MODE_NONE;
    String TAG;
    Activity activity;
    int alignCount;
    ApplyTextInterface applyListener;
    CanvasTextView canvasTextView;
    ArrayList<CanvasTextView> canvasTextViewList;
    Context context;
    int currentCanvasTextIndex;
    GridViewAdapter customGridAdapter;
    EditText editText;
    GridView gridView;
    GridView gridViewColors;
    int keyboardHeight;
    LayoutParams levelParams;
    RelativeLayout mainLayout;
    OnClickListener myOnClickListener;
    Bitmap removeBitmap;
    CustomRelativeLayout.RemoveTextListener removeTextListener;
    Bitmap scaleBitmap;
    SingleTap singleTapListener;
    TextData textData;
    ArrayList<TextData> textDataList;
    ArrayList<TextData> textDataListOriginal;
    TextView textView;
    ViewSelectedListener viewSelectedListner;
    public final static int FLAG_KEY_MEDIA_NEXT = 1 << 7;

    class C06941 implements OnItemClickListener {
        C06941() {
        }

        public void onItemClick(AdapterView<?> adapterView, View v, int position, long arg3) {
            Typeface typeFace = FontCache.get(TextLayoutActivity.this.activity, FontFragment.fontPathList[position]);
            if (typeFace != null) {
                TextLayoutActivity.this.textView.setTypeface(typeFace);
            }
            TextLayoutActivity.this.editText.setTypeface(typeFace);
            TextLayoutActivity.this.textData.setTextFont(FontFragment.fontPathList[position], TextLayoutActivity.this.activity);
            TextLayoutActivity.this.canvasTextView.setTextSelected(true);
            TextLayoutActivity.this.canvasTextView.invalidate();
        }
    }

    /* renamed from: com.lyrebirdstudio.canvastext.TextLayoutActivity.2 */
    class C06952 implements OnGlobalLayoutListener {
        final /* synthetic */ RelativeLayout val$container;
        final /* synthetic */ LinearLayout val$mainLayout;
        final /* synthetic */ View val$toolbar;

        C06952(LinearLayout linearLayout, View view, RelativeLayout relativeLayout) {
            this.val$mainLayout = linearLayout;
            this.val$toolbar = view;
            this.val$container = relativeLayout;
        }

        public void onGlobalLayout() {
            int height = this.val$mainLayout.getHeight();
            Rect r = new Rect();
            this.val$mainLayout.getWindowVisibleDisplayFrame(r);
            int visible = r.bottom - r.top;
            int heightDiff = height - visible;
            if (heightDiff > 150 && this.val$container.getLayoutParams().height != heightDiff) {
                this.val$container.getLayoutParams().height = heightDiff;
                this.val$container.requestLayout();
            }
        }
    }

    /* renamed from: com.lyrebirdstudio.canvastext.TextLayoutActivity.3 */
    class C06963 implements TextWatcher {
        C06963() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence message, int start, int before, int count) {
            if (message.toString().compareToIgnoreCase("") != 0) {
                TextLayoutActivity.this.textView.setText(message.toString());
            } else {
                TextLayoutActivity.this.textView.setText(TextData.defaultMessage);
            }
            TextLayoutActivity.this.textData.message = TextLayoutActivity.this.textView.getText().toString();
            TextLayoutActivity.this.canvasTextView.invalidate();
            TextLayoutActivity.this.editText.setSelection(TextLayoutActivity.this.editText.getText().length());
        }

        public void afterTextChanged(Editable s) {
            TextLayoutActivity.this.editText.setSelection(TextLayoutActivity.this.editText.getText().length());
        }
    }

    /* renamed from: com.lyrebirdstudio.canvastext.TextLayoutActivity.4 */
    class C06974 implements OnItemClickListener {
        C06974() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            int color = ((Integer) TextLayoutActivity.this.gridViewColors.getItemAtPosition(position)).intValue();
            TextLayoutActivity.this.editText.setTextColor(color);
            TextLayoutActivity.this.textData.textPaint.setColor(color);
            TextLayoutActivity.this.canvasTextView.invalidate();
        }
    }

    /* renamed from: com.lyrebirdstudio.canvastext.TextLayoutActivity.5 */
    class C06995 implements OnClickListener {

        /* renamed from: com.lyrebirdstudio.canvastext.TextLayoutActivity.5.1 */
        class C06981 implements Runnable {
            C06981() {
            }

            public void run() {
                TextLayoutActivity.this.editText.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0f, 0.0f, 0));
                TextLayoutActivity.this.editText.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, 0.0f, 0.0f, 0));
                TextLayoutActivity.this.editText.setSelection(TextLayoutActivity.this.editText.getText().length());
            }
        }

        C06995() {
        }

        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.text_lib_font) {
                ((InputMethodManager) TextLayoutActivity.this.getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(TextLayoutActivity.this.editText.getWindowToken(), 0);
                TextLayoutActivity.this.setVisibiltyMode(TextLayoutActivity.this.MODE_FONT);
            } else if (id == R.id.text_lib_keyboard) {
                TextLayoutActivity.this.setVisibiltyMode(TextLayoutActivity.this.MODE_NONE);
                ((InputMethodManager) TextLayoutActivity.this.getSystemService(INPUT_METHOD_SERVICE)).toggleSoftInput(2, 1);
            } else if (id == R.id.text_lib_color) {
                TextLayoutActivity.this.setVisibiltyMode(TextLayoutActivity.this.MODE_COLOR);
            } else if (id == R.id.textview_font) {
                TextLayoutActivity.this.editText.requestFocusFromTouch();
                ((InputMethodManager) TextLayoutActivity.this.getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(TextLayoutActivity.this.editText, 0);
                String message = TextLayoutActivity.this.textView.getText().toString();
                if (message.compareToIgnoreCase(TextData.defaultMessage) != 0) {
                    TextLayoutActivity.this.editText.setText(message);
                    TextLayoutActivity.this.editText.setSelection(TextLayoutActivity.this.editText.getText().length());
                } else {
                    TextLayoutActivity.this.editText.setText("");
                }
                new Handler().postDelayed(new C06981(), 200);
            } else if (id == R.id.text_lib_align) {
                TextLayoutActivity textLayoutActivity = TextLayoutActivity.this;
                textLayoutActivity.alignCount++;
                int alignMode = TextLayoutActivity.this.alignCount % 3;
                int gravity = 3;
                Align align = Align.LEFT;
                if (alignMode == 1) {
                    align = Align.CENTER;
                    gravity = 17;
                }
                if (alignMode == 2) {
                    align = Align.RIGHT;
                    gravity = 5;
                }
                TextLayoutActivity.this.editText.setGravity(gravity);
                TextLayoutActivity.this.textData.textPaint.setTextAlign(align);
                TextLayoutActivity.this.canvasTextView.invalidate();
            }
        }
    }

    /* renamed from: com.lyrebirdstudio.canvastext.TextLayoutActivity.6 */
    class C12986 implements ViewSelectedListener {
        C12986() {
        }

        public void setSelectedView(CanvasTextView cvt) {
            TextLayoutActivity.this.currentCanvasTextIndex = TextLayoutActivity.this.canvasTextViewList.indexOf(cvt);
            for (int i = 0; i < TextLayoutActivity.this.canvasTextViewList.size(); i++) {
                if (TextLayoutActivity.this.currentCanvasTextIndex != i) {
                    ((CanvasTextView) TextLayoutActivity.this.canvasTextViewList.get(i)).setTextSelected(false);
                }
            }
        }
    }

    /* renamed from: com.lyrebirdstudio.canvastext.TextLayoutActivity.7 */
    class C12997 implements CustomRelativeLayout.RemoveTextListener {
        C12997() {
        }

        public void onRemove() {
            if (!TextLayoutActivity.this.canvasTextViewList.isEmpty()) {
                CanvasTextView canvasTextView = (CanvasTextView) TextLayoutActivity.this.canvasTextViewList.get(TextLayoutActivity.this.currentCanvasTextIndex);
                TextLayoutActivity.this.mainLayout.removeView(canvasTextView);
                TextLayoutActivity.this.canvasTextViewList.remove(canvasTextView);
                TextLayoutActivity.this.textDataList.remove(canvasTextView.textData);
                TextLayoutActivity.this.currentCanvasTextIndex = TextLayoutActivity.this.canvasTextViewList.size() - 1;
                if (!TextLayoutActivity.this.canvasTextViewList.isEmpty()) {
                    ((CanvasTextView) TextLayoutActivity.this.canvasTextViewList.get(TextLayoutActivity.this.currentCanvasTextIndex)).setTextSelected(true);
                }
            }
        }
    }

    public TextLayoutActivity() {
        this.TAG = TextLayoutActivity.class.getSimpleName();
        this.keyboardHeight = 0;
        this.activity = this;
        this.MODE_NONE = 0;
        this.MODE_FONT = 1;
        this.MODE_COLOR = 2;
        this.myOnClickListener = new C06995();
        this.alignCount = 0;
        this.viewSelectedListner = new C12986();
        this.currentCanvasTextIndex = 0;
        this.removeTextListener = (CustomRelativeLayout.RemoveTextListener) new C12997();
    }

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_canvas);

        this.textView = (TextView) findViewById(R.id.textview_font);
        this.textView.setPaintFlags(this.textView.getPaintFlags() | FLAG_KEY_MEDIA_NEXT);
        this.textView.setOnClickListener(this.myOnClickListener);
        this.editText = (EditText) findViewById(R.id.edittext_font);
        this.editText.setInputType((this.editText.getInputType() | AccessibilityNodeInfoCompat.ACTION_CLICK) | 176);
        if (this.textData == null) {
            this.textData = new TextData(this.activity.getResources().getDimension(R.dimen.myFontSize));
            float screenWidth = (float) getResources().getDisplayMetrics().widthPixels;
            float screenHeight = (float) getResources().getDisplayMetrics().heightPixels;
            Rect r = new Rect();
            this.textData.textPaint.getTextBounds(TextData.defaultMessage, 0, TextData.defaultMessage.length(), r);
            this.textData.xPos = (screenWidth / 2.0f) - ((float) (r.width() / 2));
            this.textData.yPos = 700.0f;
            this.editText.setText("");
            this.textView.setText(getString(R.string.preview_text));
            this.textData.message = "hosdasdasd";
        } else {
            if (!this.textData.message.equals(TextData.defaultMessage)) {
                this.editText.setText(this.textData.message, BufferType.EDITABLE);
            }
            this.editText.setTextColor(this.textData.textPaint.getColor());
            this.editText.setText(this.textData.message);
            if (this.textData.getFontPath() != null) {
                Typeface typeFace = FontCache.get(this.activity, this.textData.getFontPath());
                if (typeFace != null) {
                    this.editText.setTypeface(typeFace);
                }
            }
        }
        this.canvasTextView = new CanvasTextView(this.activity, this.textData, BitmapFactory.decodeResource(getResources(), R.drawable.remove_text), BitmapFactory.decodeResource(getResources(), R.drawable.scale_text), null, null, null);
        this.canvasTextView.setTextSelected(true);
        this.gridView = (GridView) findViewById(R.id.gridview_font);
        this.customGridAdapter = new GridViewAdapter(this, R.layout.row_grid, FontFragment.fontPathList);
        this.gridView.setAdapter(this.customGridAdapter);
        this.gridView.setOnItemClickListener(new C06941());
        (findViewById(R.id.text_lib_keyboard)).setOnClickListener(this.myOnClickListener);
        findViewById(R.id.text_lib_font).setOnClickListener(this.myOnClickListener);
        findViewById(R.id.text_lib_color).setOnClickListener(this.myOnClickListener);
        findViewById(R.id.text_lib_align).setOnClickListener(this.myOnClickListener);
        View toolbar = findViewById(R.id.text_lib_toolbar);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.text_lib_container);
        @SuppressLint("WrongViewCast") LinearLayout mainLayout = (LinearLayout) findViewById(R.id.text_lib_main_layout);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, PatternHelper.MAX_SIZE_DEFAULT);
        mainLayout.addView(this.canvasTextView, 0, layoutParams);
        mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(new C06952(mainLayout, toolbar, container));
        this.editText.requestFocus();
        this.editText.addTextChangedListener(new C06963());
        this.editText.setFocusableInTouchMode(true);
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).toggleSoftInput(2, 1);
        this.gridViewColors = (GridView) findViewById(R.id.gridViewColor);
        //this.gridViewColors.setAdapter(new RainbowPickerAdapter(this.activity));
        this.gridViewColors.setOnItemClickListener(new C06974());
    }

    public void onDestroy() {
        if (this.customGridAdapter != null) {
            if (this.customGridAdapter.typeFaceArray != null) {
                int length = this.customGridAdapter.typeFaceArray.length;
                for (int i = 0; i < length; i++) {
                    this.customGridAdapter.typeFaceArray[i] = null;
                }
            }
            this.customGridAdapter.typeFaceArray = null;
        }
        super.onDestroy();
    }

    void setVisibiltyMode(int mode) {
        if (mode == this.MODE_NONE) {
            this.gridView.setVisibility(View.GONE);
            this.gridViewColors.setVisibility(View.GONE);
        } else if (mode == this.MODE_FONT) {
            this.gridView.setVisibility(View.VISIBLE);
            this.gridViewColors.setVisibility(View.GONE);
        } else if (mode == this.MODE_COLOR) {
            this.gridView.setVisibility(View.GONE);
            this.gridViewColors.setVisibility(View.VISIBLE);
        }
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

    void loadBitmaps() {
        if (this.removeBitmap == null || this.removeBitmap.isRecycled()) {
            this.removeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.remove_text);
        }
        if (this.scaleBitmap == null || this.scaleBitmap.isRecycled()) {
            this.scaleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scale_text);
        }
    }

    public void setApplyTextListener(ApplyTextInterface l) {
        this.applyListener = l;
    }

    public void setSingleTapListener(SingleTap l) {
        this.singleTapListener = l;
    }
}
