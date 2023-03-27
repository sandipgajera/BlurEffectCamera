package com.photo.blureffectcamera.gallerylib;

import android.app.Activity;
import android.graphics.Bitmap;

public class GridViewItem {
    Activity context;
    int count;
    private String folderName;
    long imageIdForThumb;
    private boolean isDirectory;
    int orientation;
    public int selectedItemCount;

    public GridViewItem(Activity context, String path, int count, boolean isDirectory, long imageId, int orientation) {
        this.selectedItemCount = 0;
        this.folderName = path;
        this.isDirectory = isDirectory;
        this.count = count;
        this.context = context;
        this.imageIdForThumb = imageId;
        this.orientation = orientation;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public boolean isDirectory() {
        return this.isDirectory;
    }

    public Bitmap getImage() {
        return GalleryUtility.getThumbnailBitmap(this.context, this.imageIdForThumb, this.orientation);
    }
}
