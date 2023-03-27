package com.photo.blureffectcamera.imagesavelib;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.photo.blureffectcamera.R;


public class SelectCamGalleryAnimHelper {
    public static int MODE_CLICK;
    public static int MODE_OPEN;
    View buttonSelectCamera;
    View buttonSelectGallery;
    private Animation slideLeftIn;
    private Animation slideRightIn;


    static {
        MODE_CLICK = 0;
        MODE_OPEN = 1;
    }

    public SelectCamGalleryAnimHelper(Activity activity) {
        this.slideLeftIn = AnimationUtils.loadAnimation(activity, R.anim.slide_in_left);
        this.slideRightIn = AnimationUtils.loadAnimation(activity, R.anim.slide_in_right);
//        this.buttonSelectCamera = activity.findViewById(R.id.select_dialog_button_camera);
//        this.buttonSelectGallery = activity.findViewById(R.id.select_dialog_button_gallery);
    }

    public void onClickListener(View selectContainer, View view, int id) {
//        if (id == R.id.select_image_container) {
//            view.setVisibility(View.INVISIBLE);
//        } else if (id == R.id.select_dialog_button_gallery) {
//            selectContainer.setVisibility(View.INVISIBLE);
//        } else if (id == R.id.select_dialog_button_camera) {
//            selectContainer.setVisibility(View.INVISIBLE);
//        }
    }

    public void openCamGal(View selectContainer) {
        selectContainer.setVisibility(View.VISIBLE);
        selectContainer.bringToFront();
        this.buttonSelectGallery.startAnimation(this.slideLeftIn);
        this.buttonSelectCamera.startAnimation(this.slideRightIn);
    }

    public boolean onBackButton(View selectContainer) {
        if (selectContainer.getVisibility() != View.VISIBLE) {
            return false;
        }
        selectContainer.setVisibility(View.INVISIBLE);
        return true;
    }
}
