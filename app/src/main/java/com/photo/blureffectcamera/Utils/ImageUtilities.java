package com.photo.blureffectcamera.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.FaceDetector;
import android.util.Log;

import org.wysaid.myUtils.FileUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageUtilities extends FileUtil {

    public static class FaceRects {
        public FaceDetector.Face[] faces;
        public int numOfFaces;
    }

    public static String saveBitmapInCache(Context context, Bitmap bitmap) {
        try {
            File file = new File(context.getExternalCacheDir(), new Date().getTime() + ".jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file.getPath();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String saveBitmap(Bitmap bitmap) {
        try{
            //Edited by dipesh
            String path = getPath();
            String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
            return saveBitmap(bitmap, path + "/" + format + ".jpg");
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }

    public Bitmap getResizedBitmap(Bitmap bitmap, float f, float f2) {
        Bitmap createBitmap = Bitmap.createBitmap((int) f, (int) f2, Bitmap.Config.ARGB_8888);
        float width = f / ((float) bitmap.getWidth());
        float height = f2 / ((float) bitmap.getHeight());
        Matrix matrix = new Matrix();
        matrix.postScale(width, height);
        Canvas canvas = new Canvas(createBitmap);
        canvas.setMatrix(matrix);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    public static String saveBitmap(Bitmap bitmap, Context context) {
        try{
            //Edited by dipesh
            String path = getPath(context);
            String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
            return saveBitmap(bitmap, path + "/" + format + ".jpg");
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }

    public static FaceRects findFaceByBitmap(Bitmap bitmap, int i) {
        if (bitmap == null) {
            Log.e("libCGE_java", "Invalid Bitmap for Face Detection!");
            return null;
        }
        Bitmap copy = bitmap.getConfig() != Bitmap.Config.RGB_565 ? bitmap.copy(Bitmap.Config.RGB_565, false) : bitmap;
        FaceRects faceRects = new FaceRects();
        faceRects.faces = new FaceDetector.Face[i];
        try {
            faceRects.numOfFaces = new FaceDetector(copy.getWidth(), copy.getHeight(), i).findFaces(copy, faceRects.faces);
            if (copy != bitmap) {
                copy.recycle();
            }
            return faceRects;
        } catch (Exception e) {
            Log.e("libCGE_java", "findFaceByBitmap error: " + e.getMessage());
            return null;
        }
    }

    public static String saveBitmap(Bitmap bitmap, String str) {
        Log.i("libCGE_java", "saving Bitmap : " + str);
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            Log.i("libCGE_java", "Bitmap " + str + " saved!");
            return str;
        } catch (IOException e) {
            Log.e("libCGE_java", "Err when saving bitmap...");
            e.printStackTrace();
            return null;
        }
    }

    public static FaceRects findFaceByBitmap(Bitmap bitmap) {
        return findFaceByBitmap(bitmap, 1);
    }

}
