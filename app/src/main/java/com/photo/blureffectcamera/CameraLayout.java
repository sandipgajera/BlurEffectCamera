package com.photo.blureffectcamera;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.photo.blureffectcamera.Utils.ImageUtilities;
import com.photo.blureffectcamera.Utils.PermissionsUtils;

import org.wysaid.camera.CameraInstance;
import org.wysaid.nativePort.CGENativeLibrary;
import org.wysaid.view.CameraRecordGLSurfaceView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class CameraLayout extends AppCompatActivity implements View.OnClickListener {

    ImageView takePhoto, flip_camera, flashOff, flashOn;
    private int widthScreen;
    private LinkedHashMap<Integer, String> configs;
    private List<COUNT_DOWN> countDowns = new ArrayList<>();
    private int CAAcurrentCountDown = 0;
    public List<RATIO> ratios = new ArrayList<>();
    public TextView CAcountDown;
    public int currentMode;
    CircleImageView gallery;
    private boolean isFlashModel;

    enum COUNT_DOWN {
        NONE(0, R.drawable.ic_clock),
        THREE(3, R.drawable.ic_circle_three),
        FIVE(5, R.drawable.ic_circle_five),
        NINE(9, R.drawable.ic_circle_nine);

        private int resource;
        private int time;

        COUNT_DOWN(int i, int i2) {
            this.time = i;
            this.resource = i2;
        }

        public int getResource() {
            return this.resource;
        }

        public int getTime() {
            return this.time;
        }
    }

    enum RATIO {
        R_1_1(1, 1),
        R_3_4(3, 4),
        R_4_5(4, 5);

        private int height;
        private int width;

        RATIO(int i, int i2) {
            this.width = i;
            this.height = i2;
        }

        public String getName() {
            return this.width + ":" + this.height;
        }


    }

    public CameraRecordGLSurfaceView mCameraView;
    public CGENativeLibrary.LoadImageCallback mLoadImageCallback = new CGENativeLibrary.LoadImageCallback() {
        public Bitmap loadImage(String str, Object obj) {
            try {
                return BitmapFactory.decodeStream(CameraLayout.this.getAssets().open(str));
            } catch (IOException unused) {
                return null;
            }
        }

        public void loadImageOK(Bitmap bitmap, Object obj) {
            bitmap.recycle();
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_layout);
        Window window = getWindow();
        window.clearFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(ViewCompat.MEASURED_STATE_MASK);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.widthScreen = point.x;

        bindView();
        adListener();
        initConfigs();

        ConstraintLayout cameraLayout = findViewById(R.id.cameraView);
        this.mCameraView = findViewById(R.id.myGLSurfaceView);
        this.mCameraView.presetCameraForward(true);
        this.mCameraView.setFitFullView(false);
        this.mCameraView.setZOrderOnTop(false);
        this.mCameraView.setZOrderMediaOverlay(true);
        this.mCameraView.presetRecordingSize(480, 640);
        this.mCameraView.setPictureSize(1944, 2592, true);
        ((ViewGroup.MarginLayoutParams) cameraLayout.getLayoutParams()).height = (this.widthScreen * 4) / 3;
        this.ratios.add(RATIO.R_4_5);
        this.ratios.add(RATIO.R_3_4);
        this.ratios.add(RATIO.R_1_1);
        this.countDowns.add(COUNT_DOWN.NONE);
        this.countDowns.add(COUNT_DOWN.THREE);
        this.countDowns.add(COUNT_DOWN.FIVE);
        this.countDowns.add(COUNT_DOWN.NINE);


    }

    private void adListener() {
        takePhoto.setOnClickListener(this);
        flip_camera.setOnClickListener(this);
        flashOn.setOnClickListener(this);
        flashOff.setOnClickListener(this);
        gallery.setOnClickListener(this);
    }

    private void bindView() {
        takePhoto = (ImageView) findViewById(R.id.takePhoto);
        flip_camera = (ImageView) findViewById(R.id.flip_camera);
        flashOn = (ImageView) findViewById(R.id.flashOn);
        flashOff = (ImageView) findViewById(R.id.flashOff);
        CAcountDown = findViewById(R.id.countDown);
        gallery = findViewById(R.id.gallery);
        CAcountDown.setVisibility(View.GONE);
    }

    public void initConfigs() {
        this.configs = new LinkedHashMap<>();
        this.configs.put(0, "");
        this.configs.put(2, "");
        this.configs.put(1, "");
        this.configs.put(3, "");
        this.configs.put(4, "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.takePhoto:
                new CountDownTimer((Math.max(this.countDowns.get(this.CAAcurrentCountDown % 4).getTime(), 0) * 1000), 1000) {
                    @SuppressLint("SetTextI18n")
                    public void onTick(long j) {
                        TextView tvCountDown = CAcountDown;
                        tvCountDown.setText(((j / 1000) + 1) + "");
                    }

                    public void onFinish() {
                        CAcountDown.setVisibility(View.GONE);

                        mCameraView.takePicture(this::takePicture, null, "", 1.0f, true);
                    }

                    public void takePicture(Bitmap bitmap) {
                        Bitmap bitmap2;
                        if (bitmap != null) {

                            RATIO ratio = ratios.get(currentMode % 3);
                            if (ratio == RATIO.R_1_1) {
                                bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
                            } else if (ratio == RATIO.R_3_4) {
                                bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), (bitmap.getWidth() * 4) / 3, Bitmap.Config.ARGB_8888);
                            } else if (ratio != RATIO.R_4_5) {
                                bitmap2 = null;
                            } else {
                                bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), (bitmap.getWidth() * 5) / 4, Bitmap.Config.ARGB_8888);
                            }
                            Canvas canvas = new Canvas(Objects.requireNonNull(bitmap2));
                            new Paint(1).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                            canvas.drawBitmap(bitmap, new Rect(0, (bitmap.getHeight() - bitmap2.getHeight()) / 2, bitmap.getWidth(), ((bitmap.getHeight() - bitmap2.getHeight()) / 2) + bitmap2.getHeight()), new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), null);
                            String saveBitmapInCache = ImageUtilities.saveBitmapInCache(CameraLayout.this, bitmap2);
                            bitmap.recycle();
                            bitmap2.recycle();
                            flashOn.setVisibility(View.GONE);
                            flashOff.setVisibility(View.VISIBLE);
                            mCameraView.setFlashLightMode("off");
                            Intent intent = new Intent(CameraLayout.this, PhotoEditorActivity.class);
                            intent.putExtra("path", saveBitmapInCache);
                            startActivity(intent);
                        }
                    }
                }.start();

                break;

            case R.id.gallery:
                startActivity(new Intent(CameraLayout.this, MyCreationActivity.class));
                break;

            case R.id.flip_camera:
                this.mCameraView.switchCamera();
                break;

            case R.id.flashOn:
                flashOn.setVisibility(View.GONE);
                flashOff.setVisibility(View.VISIBLE);
                this.mCameraView.setFlashLightMode("off");

                break;

            case R.id.flashOff:
                flashOff.setVisibility(View.GONE);
                flashOn.setVisibility(View.VISIBLE);
                this.mCameraView.setFlashLightMode("on");

                break;

        }
    }

    @Override
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr.length == 0 || iArr[0] != 0) {
            View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.permission_camera, null, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            inflate.findViewById(R.id.oki).setOnClickListener(view -> {
                create.dismiss();
                PermissionsUtils.checkCameraPermission(CameraLayout.this);
            });
            Objects.requireNonNull(create.getWindow()).setBackgroundDrawable(new ColorDrawable(0));
            create.show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mCameraView.onResume();
        // setupFilter();
       /* if (isFlashModel){
            flashOn.setVisibility(View.VISIBLE);
            flashOff.setVisibility(View.GONE);
            this.mCameraView.setFlashLightMode("off");
        }else {
            flashOn.setVisibility(View.GONE);
            flashOff.setVisibility(View.VISIBLE);
            this.mCameraView.setFlashLightMode("on");
        }*/
    }

    @Override
    public void onPause() {
        super.onPause();
        CameraInstance.getInstance().stopCamera();
        this.mCameraView.release(null);
        this.mCameraView.onPause();
    }

}