package com.photo.blureffectcamera.lyrebirdlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

@SuppressLint("AppCompatCustomView")
public class SeekBarHint extends SeekBar {
    Drawable mThumb;

    public SeekBarHint(Context context) {
        super(context);
    }

    public SeekBarHint(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SeekBarHint(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setThumb(Drawable thumb) {
        super.setThumb(thumb);
        this.mThumb = thumb;
    }

    public Drawable getSeekBarThumb() {
        return this.mThumb;
    }
}
