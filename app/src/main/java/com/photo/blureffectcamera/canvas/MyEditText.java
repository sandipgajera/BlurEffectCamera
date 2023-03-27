package com.photo.blureffectcamera.canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

@SuppressLint("AppCompatCustomView")
public class MyEditText extends EditText {
    Context context;

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            ((InputMethodManager) this.context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindowToken(), 0);
        }
        return false;
    }
}
