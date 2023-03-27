package com.photo.blureffectcamera.imageloader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore.Images.Media;
import android.widget.Toast;


import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.pattern.PatternHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class ImageUtility {
    static int SPLASH_TIME_OUT_AFTER_AD_LOADED = 0;
    public static int SPLASH_TIME_OUT_DEFAULT = 0;
    public static int SPLASH_TIME_OUT_LONG = 0;
    static int SPLASH_TIME_OUT_MAX = 0;
    public static int SPLASH_TIME_OUT_SHORT = 0;
    private static final String TAG;
    static final long interStitialPeriodMin = 15000;
    public static long lastTimeInterstitialShowed = 0;
    public static final int sizeDivider = 100000;
    public final static int FLAG_KEY_MEDIA_NEXT = 1 << 7;

    /* renamed from: com.lyrebirdstudio.imagesavelib.ImageUtility.1 */
    static class C07381 implements Runnable {
        final /* synthetic */ String val$finalDirectory;
        final /* synthetic */ Context val$mContext;

        C07381(Context context, String str) {
            this.val$mContext = context;
            this.val$finalDirectory = str;
        }

        public void run() {
            @SuppressLint({"StringFormatInvalid", "LocalSuppress"}) Toast msg = Toast.makeText(this.val$mContext, String.format(this.val$mContext.getString(R.string.save_image_directory_error_message), new Object[]{this.val$finalDirectory}), Toast.LENGTH_LONG);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();
        }
    }

    /* renamed from: com.lyrebirdstudio.imagesavelib.ImageUtility.3 */
    static class C07393 implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub

        }
    }


    static {
        TAG = ImageUtility.class.getSimpleName();
        SPLASH_TIME_OUT_SHORT = 150;
        // SPLASH_TIME_OUT_DEFAULT = HttpResponseCode.MULTIPLE_CHOICES;
        SPLASH_TIME_OUT_LONG = PatternHelper.MAX_SIZE_DEFAULT;
        // SPLASH_TIME_OUT_AFTER_AD_LOADED = HttpResponseCode.MULTIPLE_CHOICES;
        SPLASH_TIME_OUT_MAX = 1300;
    }

    @SuppressLint("WrongConstant")
    public static boolean getAmazonMarket(Context context) {
        int AMAZON_MARKET = 0;
        try {
            AMAZON_MARKET = context.getPackageManager().getApplicationInfo(context.getPackageName(), FLAG_KEY_MEDIA_NEXT).metaData.getInt("amazon_market");
            if (AMAZON_MARKET < 0) {
                AMAZON_MARKET = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (AMAZON_MARKET == 1) {
            return true;
        }
        return false;
    }

    public static String getPrefferredDirectoryPathEx(Context mContext) {
        String directory = Environment.getExternalStorageDirectory().getAbsolutePath() + mContext.getResources().getString(R.string.app_name);
        String prefDir = PreferenceManager.getDefaultSharedPreferences(mContext).getString("save_image_directory_custom", null);
        if (prefDir != null) {
            return prefDir + File.separator;
        }
        return directory;
    }

    public static String getPrefferredDirectoryPath(Context mContext, boolean showErrorMessage, boolean getPrefDirectoryNoMatterWhat, boolean isAppCamera) throws Throwable {
        String directory;
        if (isAppCamera) {
            directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + mContext.getResources().getString(R.string.app_name);
        } else {
            directory = Environment.getExternalStorageDirectory().getAbsolutePath() + mContext.getResources().getString(R.string.app_name);
        }
        String prefDir = PreferenceManager.getDefaultSharedPreferences(mContext).getString("save_image_directory_custom", null);
        if (prefDir != null) {
            prefDir = prefDir + File.separator;
            if (getPrefDirectoryNoMatterWhat) {
                return prefDir;
            }
            File dirFile = new File(prefDir);
            String finalDirectory = directory;
            if (dirFile.canRead() && dirFile.canWrite() && checkIfEACCES(prefDir)) {
                directory = prefDir;
            } else if (showErrorMessage) {
                ((Activity) mContext).runOnUiThread(new C07381(mContext, finalDirectory));
            }
        }
        return directory;
    }

    public static boolean checkIfEACCES(String dir) throws Throwable {
        IOException ex;
        Throwable th;
        boolean result = false;
        Writer writer = null;
        try {
            File f = new File(dir);
            String localPath = dir + "mpp";
            f.mkdirs();
            Writer writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(localPath), "utf-8"));
            try {
                writer2.write("Something");
                result = true;
                writer2.close();
                try {
                    writer2.close();
                    writer = writer2;
                } catch (Exception e) {
                    writer = writer2;
                }
            } catch (IOException e2) {
                ex = e2;
                writer = writer2;
                try {
                    try {
                        writer.close();
                    } catch (Exception e3) {
                    }
                    return result;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        writer.close();
                    } catch (Exception e4) {
                    }
                    try {
                        throw th;
                    } catch (Throwable e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                writer = writer2;
                writer.close();
                throw th;
            }
        } catch (IOException e5) {
            ex = e5;
            writer.close();
            return result;
        }
        return result;
    }

    private static long getFreeMemory() {
        return Runtime.getRuntime().maxMemory() - Debug.getNativeHeapAllocatedSize();
    }

    public static int maxSizeForDimension() {
        int maxSize = (int) Math.sqrt(((double) getFreeMemory()) / 30.0d);
        return Math.min(maxSize, 1624);
    }

    public static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        if (height <= reqHeight && width <= reqWidth) {
            return 1;
        }
        if (width < height) {
            return (int) Math.ceil((double) (((float) height) / ((float) reqHeight)));
        }
        return (int) Math.ceil((double) (((float) width) / ((float) reqWidth)));
    }


    public static boolean shouldShowAds(Context mContext) {
        return !mContext.getPackageName().toLowerCase().contains("pro");
    }


    public static double getLeftSizeOfMemory() {
        double totalSize = Double.valueOf((double) Runtime.getRuntime().maxMemory()).doubleValue();
        double heapAllocated = Double.valueOf((double) Runtime.getRuntime().totalMemory()).doubleValue();
        double nativeAllocated = Double.valueOf((double) Debug.getNativeHeapAllocatedSize()).doubleValue();
        double usedMemory = heapAllocated - Double.valueOf((double) Runtime.getRuntime().freeMemory()).doubleValue();
        return (totalSize - usedMemory) - nativeAllocated;
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
        Bitmap temp = graySourceBtm.copy(Config.ARGB_8888, false);
        if (graySourceBtm != temp) {
            graySourceBtm.recycle();
        }
        return temp;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Bitmap rotateBitmap(Bitmap r9, int r10) {
        return r9;
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

    @SuppressLint("WrongConstant")
    public static boolean isPackageInstalled(String packagename, Context context) {
        try {
            context.getPackageManager().getPackageInfo(packagename, 1);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }


    @SuppressLint("WrongConstant")
    public static boolean shareTwit(Context context, String message, String imagePath) {
        try {
            String path = Media.insertImage(context.getContentResolver(), new File(imagePath).getAbsolutePath(), "Title", null);
            if (path == null) {
                return false;
            }
            Uri imageUri = Uri.parse(path);
            Intent shareIntent = new Intent("android.intent.action.SEND");
            shareIntent.setAction("android.intent.action.SEND");
            shareIntent.putExtra("android.intent.extra.TEXT", message);
            shareIntent.putExtra("android.intent.extra.STREAM", imageUri);
            shareIntent.setType("image/jpeg");
            for (ResolveInfo app : context.getPackageManager().queryIntentActivities(shareIntent, 0)) {
                if (app.activityInfo.name.contains("twitter")) {
                    ActivityInfo activity = app.activityInfo;
                    ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                    shareIntent.addCategory("android.intent.category.LAUNCHER");
                    shareIntent.setFlags(270532608);
                    shareIntent.setComponent(name);
                    context.startActivity(shareIntent);
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void openPromoApp(String packageName, Context context) {
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent == null) {
                intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("market://details?id=" + packageName));
            }
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }
}
