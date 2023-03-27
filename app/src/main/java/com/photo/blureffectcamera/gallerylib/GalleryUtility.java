package com.photo.blureffectcamera.gallerylib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Debug;
import android.provider.MediaStore.Images.Thumbnails;

import java.text.DecimalFormat;

public class GalleryUtility {
    private static final String TAG;

    static {
        TAG = GalleryUtility.class.getSimpleName();
    }

    public static Bitmap getThumbnailBitmap(Context context, long imageId, int orientation) {
        if (context == null) {
            return null;
        }
        Bitmap bitmap = null;
        try {
            bitmap = Thumbnails.getThumbnail(context.getContentResolver(), imageId, 1, (Options) null);
        } catch (Exception e) {
        }
        if (bitmap == null) {
            return null;
        }
        Bitmap btm = rotateImage(bitmap, orientation);
        if (btm == null) {
            return bitmap;
        }
        if (btm == bitmap) {
            return btm;
        }
        bitmap.recycle();
        return btm;
    }

    public static void logHeap() {
        Double allocated = Double.valueOf(Double.valueOf((double) Debug.getNativeHeapAllocatedSize()).doubleValue() / Double.valueOf(1048576.0d).doubleValue());
        Double available = Double.valueOf(Double.valueOf((double) Debug.getNativeHeapSize()).doubleValue() / 1048576.0d);
        Double free = Double.valueOf(Double.valueOf((double) Debug.getNativeHeapFreeSize()).doubleValue() / 1048576.0d);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
    }

    private static Bitmap rotateImage(Bitmap bitmap, int orientation) {
        Matrix matrix = new Matrix();
        if (orientation == 90) {
            matrix.postRotate(90.0f);
        } else if (orientation == 180) {
            matrix.postRotate(180.0f);
        } else if (orientation == 270) {
            matrix.postRotate(270.0f);
        }
        if (orientation == 0) {
            return null;
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
