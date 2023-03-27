package com.photo.blureffectcamera.photoactivity;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.media.ExifInterface;
import android.os.Build.VERSION;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BitmapResizer {
    public static Bitmap decodeFile(File f, int requiredSize) {
        try {
            Options o = new Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
            int REQUIRED_SIZE = requiredSize;
            int width_tmp = o.outWidth;
            int height_tmp = o.outHeight;
            int scale = 1;
            while (width_tmp / 2 >= REQUIRED_SIZE && height_tmp / 2 >= REQUIRED_SIZE) {
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            Options o2 = new Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Point decodeFileSize(File f, int requiredSize) {
        try {
            Options o = new Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
            int REQUIRED_SIZE = requiredSize;
            int width_tmp = o.outWidth;
            int height_tmp = o.outHeight;
            int scale = 1;
            while (Math.max(width_tmp, height_tmp) / 2 > REQUIRED_SIZE) {
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            if (scale == 1) {
                return new Point(-1, -1);
            }
            return new Point(width_tmp, height_tmp);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Point getFileSize(File f, int requiredSize) {
        try {
            Options o = new Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
            return new Point(o.outWidth, o.outHeight);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Bitmap decodeBitmapFromFile(String selectedImagePath, int MAX_SIZE) {
        int orientation = 0;
        try {
            orientation = new ExifInterface(selectedImagePath).getAttributeInt("Orientation", 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap localBitmap = decodeFile(selectedImagePath, MAX_SIZE);
        if (localBitmap == null) {
            return null;
        }
        Bitmap graySourceBtm = rotateBitmap(localBitmap, orientation);
        if (graySourceBtm == null || VERSION.SDK_INT >= 13) {
            return graySourceBtm;
        }
        Bitmap temp = graySourceBtm.copy(Config.ARGB_8888, true);
        if (graySourceBtm != temp) {
            graySourceBtm.recycle();
        }
        return temp;
    }

    public static Bitmap rotateBitmap(Bitmap r9, int r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.lyrebirdstudio.photoactivity.BitmapResizer.rotateBitmap(android.graphics.Bitmap, int):android.graphics.Bitmap");
    }

    private static Bitmap decodeFile(String selectedImagePath, int MAX_SIZE) {
        Options o = new Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, o);
        int scale = 1;
        int width_tmp = o.outWidth;
        int height_tmp = o.outHeight;
        while (Math.max(width_tmp, height_tmp) / 2 > MAX_SIZE) {
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        Options o2 = new Options();
        o2.inSampleSize = scale;
        Bitmap b = BitmapFactory.decodeFile(selectedImagePath, o2);
        if (b != null) {
        }
        return b;
    }
}
