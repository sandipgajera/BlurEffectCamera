package com.photo.blureffectcamera;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.photo.blureffectcamera.photoactivity.PhotoActivity;


public class SlideMenuActivity extends PhotoActivity {
    public static Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_layout);
        activity =SlideMenuActivity.this;

    }

    @Override
    protected int galleryFragmentContainerId() {
        return 0;
    }


    @Override
    protected int getToolbarId() {
        return 0;
    }


    @Override
    public void myClickHandler(View view) {

    }

    @Override
    protected void startShaderActivity() {

    }
}
