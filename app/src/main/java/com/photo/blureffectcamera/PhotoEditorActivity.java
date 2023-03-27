package com.photo.blureffectcamera;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.contentarcade.adnan.shapedblurlibrary.GaussianBlur;
import com.contentarcade.adnan.shapedblurlibrary.view.ShapeLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoEditorActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Data-->";
    ImageView iv_img;
 /*   RelativeLayout rl;*/

    LinearLayout ll_blur, ll_crop, ll_dark, ll_delete, ll_back;
    LinearLayout ll_BlurControl, ll_CropControl, ll_DarkControl;
    private ImageView blurredImage;
    ShapeLayout shapeLayout;
    int progressofCircle = 250;
    Bitmap bitmap;
    SeekBar seek_range, seek_effect, seek_bright, seek_dark;
    TextView tv_11, tv_34, tv_169, tv_plus, tv_save;
    ConstraintLayout cons;

    float cont = 1f;
    float bright = 0f;
    float sat = 1f;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_editor);

        progressDialog = new ProgressDialog(PhotoEditorActivity.this);
        progressDialog.setMessage("Image Saving");
        progressDialog.setCancelable(false);

        try {
            bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), Uri.fromFile(new File(getIntent().getStringExtra("path"))));
        } catch (Exception e) {
            //handle exception
            e.printStackTrace();
        }

        ll_blur = (LinearLayout) findViewById(R.id.ll_blur);
        ll_crop = (LinearLayout) findViewById(R.id.ll_crop);
        ll_dark = (LinearLayout) findViewById(R.id.ll_bright);
        ll_delete = (LinearLayout) findViewById(R.id.ll_delete);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);

        ll_BlurControl = (LinearLayout) findViewById(R.id.constraintLayout);
        ll_CropControl = (LinearLayout) findViewById(R.id.crop_layout);
        ll_DarkControl = (LinearLayout) findViewById(R.id.constraintDark);

        cons = (ConstraintLayout) findViewById(R.id.cons);

        seek_range = (SeekBar) findViewById(R.id.seek_range);
        seek_effect = (SeekBar) findViewById(R.id.seek_effect);
        seek_bright = (SeekBar) findViewById(R.id.seek_bright);
        seek_dark = (SeekBar) findViewById(R.id.seek_dark);

        blurredImage = (ImageView) findViewById(R.id.blurred_image);
        shapeLayout = (ShapeLayout) findViewById(R.id.shape_layout_overlay);

        iv_img = (ImageView) findViewById(R.id.img);
        iv_img.setImageURI(Uri.parse(String.valueOf(getIntent().getStringExtra("path"))));

        tv_11 = (TextView) findViewById(R.id.tv_11);
        tv_34 = (TextView) findViewById(R.id.tv_34);
        tv_169 = (TextView) findViewById(R.id.tv_169);
        tv_plus = (TextView) findViewById(R.id.tv_plus);

        tv_save = (TextView) findViewById(R.id.tv_save);

    /*    rl = (RelativeLayout) findViewById(R.id.rl);*/

        ll_blur.setOnClickListener(this);
        ll_crop.setOnClickListener(this);
        ll_dark.setOnClickListener(this);
        ll_delete.setOnClickListener(this);
        ll_back.setOnClickListener(this);

        tv_11.setOnClickListener(this);
        tv_34.setOnClickListener(this);
        tv_169.setOnClickListener(this);
        tv_plus.setOnClickListener(this);

        tv_save.setOnClickListener(this);

        blur_area_seekbar_method();
        radius_seekbar_method();

        seek_bright.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                bright = ((255f / 50f) * progress) - 255f;
                iv_img.setImageBitmap(changeBitmapContrastBrightness(cont, bright, sat));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek_dark.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                bright = (progress) - 100f;
                iv_img.setImageBitmap(changeBitmapContrastBrightness(cont, bright, sat));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private Bitmap changeBitmapContrastBrightness(float contrast, float brightness, float saturation) {
        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, brightness,
                        0, contrast, 0, 0, brightness,
                        0, 0, contrast, 0, brightness,
                        0, 0, 0, 1, 0
                });

        Bitmap ret = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        Canvas canvas = new Canvas(ret);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        cm.setSaturation(saturation);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(ret, 0, 0, paint);
        return ret;
    }

    private void applyBlurView(int r, int size) {
        GaussianBlur.with(this)
                .size(size)
                .radius(r)
                .put(bitmap, blurredImage);
        // .put()  also accepts bitmap and drawable

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_blur:
                ll_BlurControl.setVisibility(View.VISIBLE);
                ll_DarkControl.setVisibility(View.GONE);
                ll_CropControl.setVisibility(View.GONE);

                applyBlurView(GaussianBlur.MAX_RADIUS, GaussianBlur.MAX_SIZE);
                shapeLayout.setTypeOfShape(ShapeLayout.ShapeType.CIRCLE);
                seek_range.setProgress(progressofCircle);
                break;
            case R.id.ll_crop:
                ll_BlurControl.setVisibility(View.GONE);
                ll_DarkControl.setVisibility(View.GONE);
                ll_CropControl.setVisibility(View.VISIBLE);

                break;
            case R.id.ll_bright:
                ll_BlurControl.setVisibility(View.GONE);
                ll_DarkControl.setVisibility(View.VISIBLE);
                ll_CropControl.setVisibility(View.GONE);

                break;
            case R.id.ll_delete:
                BottomDialog("delete");
                break;
            case R.id.ll_back:
                onBackPressed();
                break;
            case R.id.tv_11:
                changeLayout("1:1");
                break;
            case R.id.tv_34:
                changeLayout("3:4");
                break;
            case R.id.tv_169:
                changeLayout("16:9");
                break;
            case R.id.tv_plus:
                changeLayout("5:7");
                break;
            case R.id.tv_save:
                try {
                    progressDialog.show();

                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + getString(R.string.directory));
                    if (!file.exists()) {
                        file.mkdir();
                    }

                    String resultPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                            .toString()
                            + getString(R.string.directory)
                            + String.valueOf(System.currentTimeMillis()) + ".jpg";

                    new File(file, System.currentTimeMillis() + ".jpg").getParentFile().mkdirs();

                    FileOutputStream output = new FileOutputStream(resultPath);
                    viewToBitmap(cons).compress(Bitmap.CompressFormat.PNG, 100, output);
                    output.close();
                    progressDialog.dismiss();
                    Toast.makeText(this, "Sucessfully saved..", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        final Uri contentUri = Uri.fromFile(new File(resultPath));
                        scanIntent.setData(contentUri);
                        sendBroadcast(scanIntent);
                    } else {
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"
                                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath() + "/Comic Camera")));

                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();

                } catch (IOException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();

                }
                break;
        }
    }

    public Bitmap viewToBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public void changeLayout(String ratio) {
        ConstraintSet set = new ConstraintSet();
        set.clone(cons);
        set.setDimensionRatio(iv_img.getId(), ratio);
        set.applyTo(cons);
    }

    public void blur_area_seekbar_method() {
        //   Toast.makeText(this, "radius "+viewWeight, Toast.LENGTH_SHORT).show();
        seek_range.setMax(720);
        seek_range.setProgress(progressofCircle);

        seek_range.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (shapeLayout.getType().equals(ShapeLayout.ShapeType.CIRCLE)) {
                    progressofCircle = progress;
                } else if (shapeLayout.getType().equals(ShapeLayout.ShapeType.SQUARE)) {
                    progressofCircle = progress;
                } else if (shapeLayout.getType().equals(ShapeLayout.ShapeType.RECTANGLE)) {
                    progressofCircle = progress;
                }
                progressChangedValue = progress;
                shapeLayout.resetShapeSize(progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

                //  applyBlur(radius_seekbar.getProgress(), progressChangedValue );
            }
        });
    }

    public void radius_seekbar_method() {


        seek_effect.setProgress(GaussianBlur.MAX_RADIUS);

        seek_effect.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                Log.i(TAG, "onProgressChanged: " + progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (progressChangedValue > 0) {
                    shapeLayout.setVisibility(View.VISIBLE);
                    applyBlurView(progressChangedValue, seek_effect.getProgress());
                } else {
                    shapeLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        BottomDialog("back");

    }

    public void BottomDialog(String type) {
        BottomSheetDialog dialog = new BottomSheetDialog(PhotoEditorActivity.this);
        dialog.setContentView(R.layout.bottom_dialog_layout);

        LinearLayout ll_yes = dialog.findViewById(R.id.ll_yes);
        LinearLayout ll_no = dialog.findViewById(R.id.ll_no);
        TextView tv_text = dialog.findViewById(R.id.tv_title);

        if (type.equals("back")) {
            tv_text.setText(getResources().getString(R.string.go_back_text));
        } else {
            tv_text.setText(getResources().getString(R.string.delete_msg));

        }


        ll_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (type.equals("back")) {
                    finish();
                } else {
                    File image = new File(getIntent().getStringExtra("path"));
                    if (image.exists()) {
                        if (image.delete()) {
                            onBackPressed();
                            finish();
                        } else {
                            Toast.makeText(PhotoEditorActivity.this, "Nothing delete", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                dialog.dismiss();
            }
        });
        ll_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


        dialog.show();
    }

}