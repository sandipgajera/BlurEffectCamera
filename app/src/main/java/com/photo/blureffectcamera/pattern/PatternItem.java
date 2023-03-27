package com.photo.blureffectcamera.pattern;

import android.util.Log;

public class PatternItem {
    public boolean isFromOnline;
    public String path;
    public int resId;

    public PatternItem(int resId) {
    	Log.d("PatternItem","PatternItem res id ::::::::::::::" + resId);
        this.isFromOnline = false;
        this.resId = resId;
        this.isFromOnline = false;
    }

    public PatternItem(String path) {
    	Log.d("PatternItem","PatternItem path::::::::::::::" + path);
        this.isFromOnline = false;
        this.path = path;
        this.isFromOnline = true;
    }
}
