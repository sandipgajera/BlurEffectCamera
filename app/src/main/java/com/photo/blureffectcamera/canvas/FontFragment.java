package com.photo.blureffectcamera.canvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.Fragment;

import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.colorPicker.OnItemSelected;
import com.photo.blureffectcamera.colorPicker.RainbowPickerDialog;


public class FontFragment extends Fragment {
    private static final String TAG = "FontFragment";
    public static final String[] fontPathList;
    Activity activity;
    GridViewAdapter customGridAdapter;
    EditText editText;
    FontChoosedListener fontChoosedListener;
    OnClickListener onClickListener;
    TextData textData;
    TextView textView;

    public final static int FLAG_KEY_MEDIA_NEXT = 1 << 7;

    /* renamed from: com.lyrebirdstudio.canvastext.FontFragment.2 */
    class C06882 implements OnItemClickListener {
        C06882() {
        }

        public void onItemClick(AdapterView<?> adapterView, View v, int position, long arg3) {
            Typeface typeFace = FontCache.get(FontFragment.this.activity, FontFragment.fontPathList[position]);
            if (typeFace != null) {
                FontFragment.this.textView.setTypeface(typeFace);
            }
            FontFragment.this.textData.setTextFont(FontFragment.fontPathList[position], FontFragment.this.activity);
        }
    }

    /* renamed from: com.lyrebirdstudio.canvastext.FontFragment.3 */
    class C06903 implements OnClickListener {

        /* renamed from: com.lyrebirdstudio.canvastext.FontFragment.3.1 */
        class C06891 implements Runnable {
            C06891() {
            }

            public void run() {
                FontFragment.this.editText.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0f, 0.0f, 0));
                FontFragment.this.editText.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, 0.0f, 0.0f, 0));
                FontFragment.this.editText.setSelection(FontFragment.this.editText.getText().length());
            }
        }

        /* renamed from: com.lyrebirdstudio.canvastext.FontFragment.3.2 */
        class C12972 implements OnItemSelected {
            C12972() {
            }

            public void itemSelected(int color) {
                FontFragment.this.textView.setTextColor(color);
                FontFragment.this.textData.textPaint.setColor(color);
            }
        }

        C06903() {
        }

        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.textview_font) {
                FontFragment.this.editText.requestFocusFromTouch();
                ((InputMethodManager) FontFragment.this.activity.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(FontFragment.this.editText, 0);
                String message = FontFragment.this.textView.getText().toString();
                new Handler().postDelayed(new C06891(), 200);
            } else if (id == R.id.button_font_ok) {
                String newMessage = FontFragment.this.textView.getText().toString();
                if (newMessage.compareToIgnoreCase(TextData.defaultMessage) == 0 || newMessage.length() == 0) {
                    if (FontFragment.this.activity == null) {
                        FontFragment.this.activity = FontFragment.this.getActivity();
                    }
                    Toast msg = Toast.makeText(FontFragment.this.activity, FontFragment.this.getString(R.string.canvas_text_enter_text), Toast.LENGTH_LONG);
                    msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
                    msg.show();
                    return;
                }
                if (newMessage.length() == 0) {
                    FontFragment.this.textData.message = TextData.defaultMessage;
                } else {
                    FontFragment.this.textData.message = newMessage;
                }
//                FontFragment.this.editText.setText(BuildConfig.FLAVOR);
//                FontFragment.this.textView.setText(BuildConfig.FLAVOR);
                ((InputMethodManager) FontFragment.this.activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(FontFragment.this.editText.getWindowToken(), 0);
                FontFragment.this.fontChoosedListener.onOk(FontFragment.this.textData);
            } else if (id == R.id.button_text_color) {
                try {
                    RainbowPickerDialog dialog = new RainbowPickerDialog(FontFragment.this.activity);
                    dialog.setListener((OnItemSelected) new C12972());
                    dialog.show();
                } catch (Exception e) {
                }
            }
        }
    }

    public interface FontChoosedListener {
        void onOk(TextData textData);
    }

    public FontFragment() {
        this.onClickListener = new C06903();
    }

    public void setFontChoosedListener(FontChoosedListener l) {
        this.fontChoosedListener = l;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.activity = getActivity();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_font, container, false);
        this.activity = getActivity();
        Bundle extras = getArguments();
        if (extras != null) {
            this.textData = (TextData) extras.getSerializable("text_data");
        }
        this.textView = (TextView) fragmentView.findViewById(R.id.textview_font);
        this.textView.setPaintFlags(this.textView.getPaintFlags() | FLAG_KEY_MEDIA_NEXT);
        this.textView.setOnClickListener(this.onClickListener);
        this.editText = (EditText) fragmentView.findViewById(R.id.edittext_font);
        this.editText.setInputType((this.editText.getInputType() | AccessibilityNodeInfoCompat.ACTION_CLICK) | 176);
//        this.editText.addTextChangedListener(new C06871());
        this.editText.setFocusableInTouchMode(true);
        if (this.textData == null) {
            this.textData = new TextData(this.activity.getResources().getDimension(R.dimen.myFontSize));
            float screenWidth = (float) getResources().getDisplayMetrics().widthPixels;
            float screenHeight = (float) getResources().getDisplayMetrics().heightPixels;
            Rect r = new Rect();
            this.textData.textPaint.getTextBounds(TextData.defaultMessage, 0, TextData.defaultMessage.length(), r);
            this.textData.xPos = (screenWidth / 2.0f) - ((float) (r.width() / 2));
            this.textData.yPos = (screenHeight / 2.0f) - ((float) (r.height() / 2));
//            this.editText.setText(BuildConfig.FLAVOR);
            this.textView.setText(getString(R.string.preview_text));
        } else {
            if (!this.textData.message.equals(TextData.defaultMessage)) {
                this.editText.setText(this.textData.message, BufferType.EDITABLE);
            }
            this.textView.setTextColor(this.textData.textPaint.getColor());
            this.textView.setText(this.textData.message);
            if (this.textData.getFontPath() != null) {
                Typeface typeFace = FontCache.get(this.activity, this.textData.getFontPath());
                if (typeFace != null) {
                    this.textView.setTypeface(typeFace);
                }
            }
        }
        GridView gridView = (GridView) fragmentView.findViewById(R.id.gridview_font);
        this.customGridAdapter = new GridViewAdapter(this.activity, R.layout.row_grid, fontPathList);
        gridView.setAdapter(this.customGridAdapter);
        gridView.setOnItemClickListener(new C06882());
        ((Button) fragmentView.findViewById(R.id.button_text_color)).setOnClickListener(this.onClickListener);
        ((Button) fragmentView.findViewById(R.id.button_font_ok)).setOnClickListener(this.onClickListener);
        return fragmentView;
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

    static {
        fontPathList = new String[]{"fonts/MfStillKindaRidiculous.ttf", "fonts/ahundredmiles.ttf", "fonts/Binz.ttf", "fonts/Blunt.ttf", "fonts/FreeUniversal-Bold.ttf", "fonts/gtw.ttf", "fonts/HandTest.ttf", "fonts/Jester.ttf", "fonts/Semplicita_Light.otf", "fonts/OldFolksShuffle.ttf", "fonts/vinque.ttf", "fonts/Primal _ream.otf", "fonts/Junction 02.otf", "fonts/Laine.ttf", "fonts/NotCourierSans.otf", "fonts/OSP-DIN.ttf", "fonts/otfpoc.otf", "fonts/Sofia-Regular.ttf", "fonts/Quicksand-Regular.otf", "fonts/Roboto-Thin.ttf", "fonts/RomanAntique.ttf", "fonts/SerreriaSobria.otf", "fonts/Strato-linked.ttf", "fonts/waltographUI.ttf", "fonts/CaviarDreams.ttf", "fonts/GoodDog.otf", "fonts/Pacifico.ttf", "fonts/Windsong.ttf"};
    }
}
