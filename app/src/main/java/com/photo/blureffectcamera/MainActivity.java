package com.photo.blureffectcamera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.photo.blureffectcamera.collagelib.CollageHelper;
import com.photo.blureffectcamera.gallerylib.GalleryFragment;
import com.photo.blureffectcamera.photoactivity.PhotoActivity;

import java.io.File;

public  class MainActivity extends SlideMenuActivity {

    ImageView btn_cam, btn_collage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check_Permission_for_read_external();

        btn_cam = (ImageView) findViewById(R.id.btn_cam);
        btn_collage = (ImageView) findViewById(R.id.btn_collage);

        btn_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                                Manifest.permission.CAMERA)) {
                            showAlert();
                        } else {
                            // No explanation needed, we can request the permission.
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    501);
                        }
                    }
                } else {
                    startActivity(new Intent(MainActivity.this, CameraLayout.class));
                }
            }
        });

        btn_collage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                                Manifest.permission.CAMERA)) {
                            showAlert();
                        } else {
                            // No explanation needed, we can request the permission.
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    500);
                        }
                    }
                } else {
                    callPhotoCollage();
                }
            }
        });

    }


    private void callPhotoCollage() {
        openCollage(false, false, false);
    }

    protected int galleryFragmentContainerId() {
        return R.id.nocrop_gallery_fragment_container;
    }


    @Override
    public void onBackPressed() {
        confirmExit();
    }

    private void check_Permission_for_read_external() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 11);
            return;
        }
        check_Permission_for_write_external();
    }

    private void check_Permission_for_write_external() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == -1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 22);
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory() + getResources().getString(R.string.directory));
        if (!file.exists()) {
            Log.d("RESULT", String.valueOf(file.mkdir()));
        }
    }
    private void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                500);

                    }
                });
        alertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 11) {
            if (i == 22) {
                if (iArr.length <= 0 || iArr[0] != 0) {
                    Toast.makeText(this, getResources().getString(R.string.app_name) + " needs WRITE_EXTERNAL_STORAGE permission", Toast.LENGTH_LONG).show();
                    return;
                }
                Log.d("PERMISSION", "GRANTED");
            }
        } else if (iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this, getResources().getString(R.string.app_name) + " needs READ_EXTERNAL_STORAGE permission", Toast.LENGTH_LONG).show();
        }  else {
            check_Permission_for_write_external();
        }

        if (i == 500) {
            if (strArr.length > 0 && iArr[0] == PackageManager.PERMISSION_GRANTED) {
                callPhotoCollage();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale
                        (this, Manifest.permission.CAMERA)) {
                    showAlert();
                } else {
                    callPhotoCollage();
                }
            }
        }else if (i == 501) {
            if (strArr.length > 0 && iArr[0] == PackageManager.PERMISSION_GRANTED) {
//                openCamera();
                startActivity(new Intent(MainActivity.this, CameraLayout.class));
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale
                        (this, Manifest.permission.CAMERA)) {
                    showAlert();
                } else {
                    startActivity(new Intent(MainActivity.this, CameraLayout.class));
                }

            }
        }

    }



    private void confirmExit() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        alertDialog.setTitle("Exit");
        alertDialog.setMessage("Are you sure you want to Exit this App?");
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intentExit = new Intent(Intent.ACTION_MAIN);
                intentExit.addCategory(Intent.CATEGORY_HOME);
                intentExit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentExit);
                finish();
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

}