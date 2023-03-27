package com.photo.blureffectcamera.imageloader;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Files;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;


import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.common_libs.MyAsyncTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@SuppressLint("NewApi") public class ImageLoader {
    static boolean DEBUG;
    String TAG;
    Context context;
    int count;
    Cursor cursorBackup;
    public String filemanagerstring;
    ImageLoaded imageLoadedListener;
    String loadImageMessage;
    public String selectedCropPath;
    public String selectedImagePath;

    /* renamed from: com.lyrebirdstudio.imagesavelib.ImageLoader.1 */
    class C07371 implements OnClickListener {
        C07371() {
        }

        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
        }
    }

    public interface ImageLoaded {
        void callFileSizeAlertDialogBuilder();
    }

    private class LoadImage19Task extends MyAsyncTask<Uri, Void, Void> {
        String path;
        ProgressDialog saveImageDialog;
        Uri uri;

        private LoadImage19Task() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            ImageLoader.this.loadImageMessage = ImageLoader.this.context.getString(R.string.loading_image);
            try {
                this.saveImageDialog = new ProgressDialog(ImageLoader.this.context);
                this.saveImageDialog.setMessage(ImageLoader.this.loadImageMessage);
                this.saveImageDialog.show();
            } catch (Exception e) {
            }
        }

        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            ImageLoader.this.selectedImagePath = this.path;
            ImageLoader.this.startActivityFromUri(this.uri);
            try {
                this.saveImageDialog.dismiss();
            } catch (Exception e) {
            }
        }

        protected Void doInBackground(Uri... arg0) throws IOException {
            if (arg0 != null) {
                this.uri = arg0[0];
            }
            if (this.uri != null) {
                this.path = ImageLoader.this.getRealPathFromURI19(this.uri);
            }
            return null;
        }
    }

    public void setListener(ImageLoaded l) {
        this.imageLoadedListener = l;
    }

    public ImageLoader(Context context) {
        this.TAG = "ImageLoader";
        this.count = 0;
        this.loadImageMessage = "Loading image!";
        this.context = context;
    }

    public void getImageFromIntent(Intent intent) {
        Uri selectedImageUri = intent.getData();
        if (selectedImageUri == null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                selectedImageUri = (Uri) bundle.get("android.intent.extra.STREAM");
            } else {
                return;
            }
        }
        if (VERSION.SDK_INT >= 19) {
            try {
                this.selectedImagePath = getPathForKitKat(selectedImageUri);
            } catch (Exception e) {
            }
            if (this.selectedImagePath == null) {
                new LoadImage19Task().execute(selectedImageUri);
                return;
            }
            startActivityFromUri(selectedImageUri);
            return;
        }
        this.selectedImagePath = getRealPathFromURI(selectedImageUri);
        startActivityFromUri(selectedImageUri);
    }

    void startActivityFromUri(Uri selectedImageUri) {
        if (selectedImageUri != null) {
            this.filemanagerstring = selectedImageUri.getPath();
            if (this.selectedImagePath == null) {
                this.selectedImagePath = this.filemanagerstring;
            }
            if (this.selectedImagePath == null || this.selectedImagePath.length() == 0 || this.selectedImagePath.toLowerCase().contains("http")) {
                new LoadImage19Task().execute(selectedImageUri);
                this.count++;
            } else if (checkFileExtension(this.selectedImagePath)) {
                this.imageLoadedListener.callFileSizeAlertDialogBuilder();
            } else {
                Builder builder = new Builder(this.context);
                builder.setMessage(this.context.getString(R.string.image_format_error_message)).setCancelable(false).setNegativeButton("Ok", new C07371());
                builder.create().show();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        if (this.context == null) {
            return null;
        }
        String res = null;
        String[] proj = new String[]{"_data"};
        Cursor cursor = null;
        if (!(this.context == null || this.context.getContentResolver() == null)) {
            try {
                cursor = this.context.getContentResolver().query(contentUri, proj, null, null, null);
                if (cursor.moveToFirst()) {
                    res = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                }
                cursor.close();
            } catch (Exception e) {
            }
        }
        if (cursor != null && res != null) {
            return res;
        }
        this.cursorBackup = this.context.getContentResolver().query(contentUri, proj, null, null, null);
        try {
            if (this.cursorBackup == null) {
                return res;
            }
            int column_index = this.cursorBackup.getColumnIndexOrThrow("_data");
            this.cursorBackup.moveToFirst();
            return this.cursorBackup.getString(column_index);
        } catch (Exception e2) {
            return res;
        }
    }

    public String getRealPathFromURI19(Uri contentUri) throws IOException {
        Bitmap bitmap = getBitmapFromUri(contentUri);
        if (bitmap == null) {
            return null;
        }
        return saveImageToTemp(bitmap);
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = this.context.getContentResolver().openFileDescriptor(uri, "r");
        if (parcelFileDescriptor == null) {
            return null;
        }
        Bitmap image = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
        parcelFileDescriptor.close();
        return image;
    }

    private String saveImageToTemp(Bitmap bitmap) throws FileNotFoundException {
        if (bitmap == null) {
            return null;
        }
        String resultPath = null;
		try {
			resultPath = ImageUtility.getPrefferredDirectoryPath(this.context, false, true, false) + "temp/dump.dump";
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        new File(resultPath).getParentFile().mkdirs();
        bitmap.compress(CompressFormat.JPEG, 90, new FileOutputStream(resultPath));
        bitmap.recycle();
        return resultPath;
    }

    private String getFileExtension(String str) {
        if (str == null) {
            str = "";
        }
        int dotPos = str.lastIndexOf(".");
        String extension = "";
        if (dotPos > 0) {
            return str.substring(dotPos);
        }
        return extension;
    }

    private boolean checkFileExtension(String str) {
        String extension = getFileExtension(str).toLowerCase();
        if (extension.contains("jpg") || extension.contains("png") || extension.contains("jpeg") || extension.contains("gif") || extension.contains("bmp") || extension.contains("webp") || extension.contains("dump")) {
            return true;
        }
        return false;
    }

    public void closeCursor() {
        if (this.cursorBackup != null) {
            this.cursorBackup.close();
        }
    }

    static {
        DEBUG = true;
    }

    @SuppressLint({"NewApi"})
    public String getPathForKitKat(Uri uri) {
        boolean isKitKat;
        if (DEBUG) {
        }
        if (VERSION.SDK_INT >= 19) {
            isKitKat = true;
        } else {
            isKitKat = false;
        }
        if (isKitKat && DocumentsContract.isDocumentUri(this.context, uri)) {
            String[] split = null;
            if (isExternalStorageDocument(uri)) {
                split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                return null;
            } else if (isDownloadsDocument(uri)) {
                return getDataColumn(this.context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
            } else if (!isMediaDocument(uri)) {
                return null;
            } else {
                String type = DocumentsContract.getDocumentId(uri).split(":")[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getDataColumn(this.context, contentUri, "_id=?", new String[]{split[1]});
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(this.context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        } else {
            return null;
        }
    }



    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = "_data";
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, selection, selectionArgs, null);
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            }
            if (DEBUG) {
                DatabaseUtils.dumpCursor(cursor);
            }
            String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
            if (cursor == null) {
                return string;
            }
            cursor.close();
            return string;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
		return column;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT) @SuppressLint("NewApi") public String getPathFromUri(Context context, Uri uri) {
        boolean isAfterKitKat = VERSION.SDK_INT >= 19;
        if (isAfterKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            String type;
            if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                return "/stroage/" + type + "/" + split[1];
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
            } else if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                type = DocumentsContract.getDocumentId(uri).split(":")[0];
                String selection = "_id=?";
                return getDataColumn(context, Files.getContentUri("external"), "_id=?", new String[]{DocumentsContract.getDocumentId(uri).split(":")[1]});
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        } else {
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }
}
