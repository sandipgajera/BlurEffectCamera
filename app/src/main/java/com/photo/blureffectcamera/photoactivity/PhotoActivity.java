package com.photo.blureffectcamera.photoactivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.photo.blureffectcamera.MainActivity;
import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.collagelib.CollageHelper;
import com.photo.blureffectcamera.collagelib.ShapeCollageActivity;
import com.photo.blureffectcamera.collagelib.Utility;
import com.photo.blureffectcamera.gallerylib.GalleryFragment;
import com.photo.blureffectcamera.imageloader.ImageLoader;
import com.photo.blureffectcamera.svg.Svg;

import java.io.File;

@SuppressLint({"NewApi"})
public abstract class PhotoActivity extends AppCompatActivity {
    public static final int REQUEST_WRITE_STORAGE_BLUR = 114;
    public static final int REQUEST_WRITE_STORAGE_COLLAGE = 112;
    public static final int REQUEST_WRITE_STORAGE_FACE = 113;
    public static final int REQUEST_WRITE_STORAGE_SCRAPBOOK = 115;
    public static final int REQUEST_WRITE_STORAGE_SCRAPBOOK_BLUR = 116;
    public final int CROP_PICTURE_COLOR_SPLASH;
    public final int CROP_RESULT_COLOR_SPLASH;
    public final int SELECT_IMAGE;
    public final int SELECT_IMAGE_COLOR_EFFECT;
    public final int SELECT_IMAGE_COLOR_SPLASH;
    public final int SELECT_IMAGE_EYE_COLOR;
    public final int SELECT_IMAGE_HDR_FX;
    public final int SELECT_IMAGE_MIRROR;
    public final int SELECT_IMAGE_PIP;
    public final int SELECT_IMAGE_SQUARE;
    public final int SHOW_ADS_AFTER;
    public final int TAKE_PICTURE;
    public final int TAKE_PICTURE_COLLAGE;
    public final int TAKE_PICTURE_COLOR_EFFECT;
    public final int TAKE_PICTURE_COLOR_SPLASH;
    public final int TAKE_PICTURE_EYE_COLOR;
    public final int TAKE_PICTURE_HDR_FX;
    public final int TAKE_PICTURE_MIRROR;
    public final int TAKE_PICTURE_PIP;
    public final int TAKE_PICTURE_SQUARE;
    public Activity activity;
    public Context context;
    boolean doubleBackToExitPressedOnce;
    int durationLimit;
    public GalleryFragment galleryFragment;
    public ImageLoader imageLoader;
    boolean isOnActivityResult;
    private Handler mHandler;
    private final Runnable mRunnable;
    public int selectImageMode;
    boolean showInterstitial;
    Activity thisActivity;

    class RunnableManager implements Runnable {
        RunnableManager() {
        }

        public void run() {
            PhotoActivity.this.doubleBackToExitPressedOnce = false;
        }
    }

    class ImageLoaderManager implements ImageLoader.ImageLoaded {
        ImageLoaderManager() {
        }

        public void callFileSizeAlertDialogBuilder() {
            PhotoActivity.this.fileSizeAlertDialogBuilder();
        }
    }

    protected abstract int galleryFragmentContainerId();


    protected abstract int getToolbarId();


    public abstract void myClickHandler(View view);

    protected abstract void startShaderActivity();

    public PhotoActivity() {
        this.TAKE_PICTURE = 51;
        this.TAKE_PICTURE_COLOR_SPLASH = 52;
        this.TAKE_PICTURE_COLOR_EFFECT = 53;
        this.TAKE_PICTURE_SQUARE = 54;
        this.TAKE_PICTURE_COLLAGE = 55;
        this.TAKE_PICTURE_MIRROR = 56;
        this.TAKE_PICTURE_PIP = 57;
        this.TAKE_PICTURE_HDR_FX = 58;
        this.TAKE_PICTURE_EYE_COLOR = 59;
        this.SELECT_IMAGE = 101;
        this.SELECT_IMAGE_SQUARE = Svg.SVG_STONE_HEART;
        this.SELECT_IMAGE_MIRROR = Svg.SVG_STONE_HEART_STONE_1;
        this.SELECT_IMAGE_PIP = Svg.SVG_STONE_HEART_STONE_2;
        this.SELECT_IMAGE_COLOR_EFFECT = Svg.SVG_3_WAVE_1;
        this.SELECT_IMAGE_COLOR_SPLASH = Svg.SVG_3_WAVE_2;
        this.SELECT_IMAGE_EYE_COLOR = Svg.SVG_3_WAVE_3;
        this.SELECT_IMAGE_HDR_FX = R.styleable.AppCompatTheme_ratingBarStyle;
        this.CROP_PICTURE_COLOR_SPLASH = R.styleable.AppCompatTheme_ratingBarStyleIndicator;
        this.CROP_RESULT_COLOR_SPLASH = R.styleable.AppCompatTheme_ratingBarStyleSmall;
        this.SHOW_ADS_AFTER = 45;
        this.context = this;
        this.activity = this;
        this.selectImageMode = Svg.SVG_STONE_HEART;
        this.isOnActivityResult = false;
        this.showInterstitial = false;
        this.doubleBackToExitPressedOnce = false;
        this.mRunnable = new RunnableManager();
        this.thisActivity = this;
        this.durationLimit = 14400000;
    }

    public void initImageLoader() {
        this.imageLoader = new ImageLoader(this);
        this.imageLoader.setListener(new ImageLoaderManager());
    }

    public boolean checkDoubleClickWhenExit() {
        return false;
    }

    public void onStart() {
        super.onStart();
        if (!this.isOnActivityResult) {
        }
        this.isOnActivityResult = false;
    }

    public void onStop() {
        super.onStop();
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroy() {

        if (this.imageLoader != null) {
            this.imageLoader.closeCursor();
        }
        super.onDestroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.isOnActivityResult = true;
        if (this.imageLoader == null) {
            initImageLoader();
        }
//        if (requestCode == 101 && resultCode == -1) {
//            this.selectImageMode = 101;
//            this.imageLoader.getImageFromIntent(data);
//        }
        Uri selectedImage;
        String path;

        if (requestCode == Svg.SVG_STONE_HEART) {
            if (resultCode == -1) {
                this.selectImageMode = Svg.SVG_STONE_HEART;
                this.imageLoader.getImageFromIntent(data);
            }
        } else if (requestCode == Svg.SVG_STONE_HEART_STONE_1) {
            if (resultCode == -1) {
                this.selectImageMode = Svg.SVG_STONE_HEART_STONE_1;
                this.imageLoader.getImageFromIntent(data);
            }
        } else if (requestCode == Svg.SVG_STONE_HEART_STONE_2) {
            if (resultCode == -1) {
                this.selectImageMode = Svg.SVG_STONE_HEART_STONE_2;
                this.imageLoader.getImageFromIntent(data);
            }
        } else if (requestCode == Svg.SVG_3_WAVE_1) {
            if (resultCode == -1) {
                this.selectImageMode = Svg.SVG_3_WAVE_1;
                this.imageLoader.getImageFromIntent(data);
            }
        } else if (requestCode == Svg.SVG_3_WAVE_2) {
            if (resultCode == -1) {
                this.selectImageMode = Svg.SVG_3_WAVE_2;
                this.imageLoader.getImageFromIntent(data);
            }
        } else if (requestCode == Svg.SVG_3_WAVE_3) {
            if (resultCode == -1) {
                this.selectImageMode = Svg.SVG_3_WAVE_3;
                this.imageLoader.getImageFromIntent(data);
            }
        } else if (requestCode == R.styleable.AppCompatTheme_ratingBarStyle) {
            if (resultCode == -1) {
                this.selectImageMode = R.styleable.AppCompatTheme_ratingBarStyle;
                this.imageLoader.getImageFromIntent(data);
            }
        } else if (requestCode == R.styleable.AppCompatTheme_ratingBarStyleIndicator) {
            if (resultCode == -1) {
                this.selectImageMode = R.styleable.AppCompatTheme_ratingBarStyleIndicator;
                this.imageLoader.getImageFromIntent(data);
            }
        } else if (requestCode == R.styleable.AppCompatTheme_ratingBarStyleSmall) {
            this.selectImageMode = Svg.SVG_3_WAVE_2;
            if (resultCode == -1) {
                this.selectImageMode = R.styleable.AppCompatTheme_ratingBarStyleSmall;
                String resultPath = null;
                if (data != null) {
                    resultPath = data.getExtras().getString("result_path");
                }
                path = getCropPath();
                if (resultPath != null) {
                    path = resultPath;
                }
                if (!new File(path).exists()) {
                    path = getCropPath();
                    if (!new File(path).exists()) {
                        return;
                    }
                }
                this.imageLoader.selectedCropPath = path;
            }
            startShaderActivity();
        } else if (requestCode == 51) {
            if (resultCode == -1) {
                selectedImage = getImageUri();
                this.imageLoader.selectedImagePath = selectedImage.getPath();
                if (this.imageLoader.selectedImagePath != null
                        && BitmapResizer.decodeFileSize(new File(
                        this.imageLoader.selectedImagePath), Utility
                        .maxSizeForDimension(this.context, 1, 1500.0f)) != null) {
                    startShaderActivity();
                }
            }
        } else if (requestCode == 56) {
            if (resultCode == -1) {
                selectedImage = getImageUri();
                this.imageLoader.selectedImagePath = selectedImage.getPath();
                if (this.imageLoader.selectedImagePath != null
                        && BitmapResizer.decodeFileSize(new File(
                        this.imageLoader.selectedImagePath), Utility
                        .maxSizeForDimension(this.context, 1, 1500.0f)) != null) {
                    startShaderActivity();
                }
            }
        } else if (requestCode == 55) {
            if (resultCode == -1) {
                path = getImageUri().getPath();
                if (path != null) {
                    Intent intent = new Intent(this, ShapeCollageActivity.class);
                    intent.putExtra("selected_image_path", path);
                    startActivity(intent);
                }
            }
        } else if (requestCode == 57) {
            if (resultCode == -1) {
                this.selectImageMode = Svg.SVG_STONE_HEART_STONE_2;
                selectedImage = getImageUri();
                this.imageLoader.selectedImagePath = selectedImage.getPath();
                if (this.imageLoader.selectedImagePath != null
                        && BitmapResizer.decodeFileSize(new File(
                        this.imageLoader.selectedImagePath), Utility
                        .maxSizeForDimension(this.context, 1, 1500.0f)) != null) {
                    startShaderActivity();
                }
            }
        } else if (requestCode == 54) {
            if (resultCode == -1) {
                this.selectImageMode = 54;
                selectedImage = getImageUri();
                this.imageLoader.selectedImagePath = selectedImage.getPath();
                if (this.imageLoader.selectedImagePath != null
                        && BitmapResizer.decodeFileSize(new File(
                        this.imageLoader.selectedImagePath), Utility
                        .maxSizeForDimension(this.context, 1, 1500.0f)) != null) {
                    startShaderActivity();
                }
            }
        } else if (requestCode == 53) {
            if (resultCode == -1) {
                this.selectImageMode = 53;
                selectedImage = getImageUri();
                this.imageLoader.selectedImagePath = selectedImage.getPath();
                if (this.imageLoader.selectedImagePath != null
                        && BitmapResizer.decodeFileSize(new File(
                        this.imageLoader.selectedImagePath), Utility
                        .maxSizeForDimension(this.context, 1, 1500.0f)) != null) {
                    startShaderActivity();
                }
            }
        } else if (requestCode == 52) {
            if (resultCode == -1) {
                this.selectImageMode = 52;
                selectedImage = getImageUri();
                this.imageLoader.selectedImagePath = selectedImage.getPath();
                if (this.imageLoader.selectedImagePath != null
                        && BitmapResizer.decodeFileSize(new File(
                        this.imageLoader.selectedImagePath), Utility
                        .maxSizeForDimension(this.context, 1, 1500.0f)) != null) {
                    startShaderActivity();
                }
            }
        } else if (requestCode == 59) {
            if (resultCode == -1) {
                this.selectImageMode = 59;
                selectedImage = getImageUri();
                this.imageLoader.selectedImagePath = selectedImage.getPath();
                if (this.imageLoader.selectedImagePath != null
                        && BitmapResizer.decodeFileSize(new File(
                        this.imageLoader.selectedImagePath), Utility
                        .maxSizeForDimension(this.context, 1, 1500.0f)) != null) {
                    startShaderActivity();
                }
            }
        } else if (requestCode == 58) {
            if (resultCode == -1) {
                this.selectImageMode = 58;
                selectedImage = getImageUri();
                this.imageLoader.selectedImagePath = selectedImage.getPath();
                if (this.imageLoader.selectedImagePath != null
                        && BitmapResizer.decodeFileSize(new File(
                        this.imageLoader.selectedImagePath), Utility
                        .maxSizeForDimension(this.context, 1, 1500.0f)) != null) {
                    startShaderActivity();
                }
            }
        }

    }

    private void fileSizeAlertDialogBuilder() {
        Point p = BitmapResizer.decodeFileSize(new File(
                this.imageLoader.selectedImagePath), Utility
                .maxSizeForDimension(this.context, 1, 1500.0f));
        if (p == null || p.x != -1) {
            startShaderActivity();
        } else {
            startShaderActivity();
        }
    }

    private Uri getImageUri() {
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                "pic.jpg"));
    }

    public void onBackPressed() {
        GalleryFragment galleryFragment = (GalleryFragment) CollageHelper
                .getGalleryFragment(this);
        if (galleryFragment != null && galleryFragment.isVisible()) {
            galleryFragment.onBackPressed();
            Intent intent = new Intent(PhotoActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (!checkDoubleClickWhenExit()) {
            Intent intent = new Intent(PhotoActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (this.doubleBackToExitPressedOnce) {
            Intent intent = new Intent(PhotoActivity.this, MainActivity.class);
            startActivity(intent);

        } else {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Double press to back",
                    Toast.LENGTH_SHORT).show();
            this.mHandler = new Handler();
            this.mHandler.postDelayed(this.mRunnable, 2000);
        }

    }

    public void takePhoto(int requestId) {
        if (VERSION.SDK_INT >= 23) {
            boolean permission = checkPermission(requestId);
            if (!permission) {
                Toast.makeText(this, R.string.permission_warning,
                        Toast.LENGTH_SHORT).show();
                return;
            }
        }
        try {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", getImageUri());
            startActivityForResult(intent, requestId);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.photo_activity_no_camera),
                    Toast.LENGTH_LONG).show();
        }
    }

    private String getCropPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + this.context.getResources().getString(R.string.app_name)
                + getString(R.string.crop_file_name);
    }

    public void openGallery(int requestId) {
        if (VERSION.SDK_INT >= 23) {
            boolean permission = checkPermission(requestId);
            if (!permission) {
                Toast.makeText(this, R.string.permission_warning,
                        Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"), requestId);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this.context,
                    getString(R.string.save_image_lib_no_gallery),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void openCollage(boolean isblur, boolean isScrapBook, boolean isShape) {
        int reqId = REQUEST_WRITE_STORAGE_COLLAGE;
        if (isblur && isScrapBook) {
            reqId = REQUEST_WRITE_STORAGE_SCRAPBOOK_BLUR;
        }
        if (!isblur && isScrapBook) {
            reqId = REQUEST_WRITE_STORAGE_SCRAPBOOK;
        }
        if (isblur && !isScrapBook) {
            reqId = REQUEST_WRITE_STORAGE_BLUR;
        }
        if (VERSION.SDK_INT >= 23) {
            boolean permission = checkPermission(reqId);
            if (!permission) {
                Toast.makeText(this, R.string.permission_warning,
                        Toast.LENGTH_SHORT).show();
                return;
            }
        }
        int galleryFragmentContainerId = galleryFragmentContainerId();
        galleryFragment = CollageHelper.addGalleryFragment(this, galleryFragmentContainerId);
        this.galleryFragment.setCollageSingleMode(isblur);
        this.galleryFragment.setIsScrapbook(isScrapBook);
        this.galleryFragment.setIsShape(isShape);
        if (!isScrapBook) {
            this.galleryFragment.setLimitMax(GalleryFragment.MAX_COLLAGE);
        } else {

        }
    }

    public boolean checkPermission(int requestId) {
        if (ContextCompat.checkSelfPermission(this.thisActivity,
                "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this.thisActivity, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            ActivityCompat
                    .requestPermissions(
                            this.thisActivity,
                            new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},
                            requestId);
            return false;
        }

        ActivityCompat.requestPermissions(this.thisActivity,
                new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},
                requestId);
        return false;
    }

//    public void onRequestPermissionsResult(int requestCode,
//                                           String[] permissions, int[] grantResults) {
//        boolean isScrapBook = true;
//
//        if (requestCode == REQUEST_WRITE_STORAGE_COLLAGE
//                || requestCode == REQUEST_WRITE_STORAGE_BLUR
//                || requestCode == REQUEST_WRITE_STORAGE_SCRAPBOOK
//                || requestCode == REQUEST_WRITE_STORAGE_SCRAPBOOK_BLUR) {
//            boolean isBlur = requestCode == REQUEST_WRITE_STORAGE_BLUR
//                    || requestCode == REQUEST_WRITE_STORAGE_SCRAPBOOK_BLUR;
//            if (!(requestCode == REQUEST_WRITE_STORAGE_SCRAPBOOK || requestCode == REQUEST_WRITE_STORAGE_SCRAPBOOK_BLUR)) {
//                isScrapBook = false;
//            }
//            if (grantResults.length > 0 && grantResults[0] == 0) {
//                openCollage(isBlur, isScrapBook, false);
//            }
//        } else if (isRequestSelectPicture(requestCode)) {
//            if (grantResults.length > 0 && grantResults[0] == 0) {
//                openGallery(requestCode);
//            }
//        } else if (isRequestTakePicture(requestCode) && grantResults.length > 0
//                && grantResults[0] == 0) {
//            takePhoto(requestCode);
//        }
//    }

    boolean isRequestSelectPicture(int requestCode) {
        if (requestCode == 68) {
            return false;
        }
        return true;
    }

    boolean isRequestTakePicture(int requestCode) {
        if (requestCode >= 100 || requestCode < 50) {
            return false;
        }
        return true;
    }

}
