package com.photo.blureffectcamera.sticker;

public class StickerGridItem {
    String id;
    boolean isOnline;
    public String path;
    public int resId;
    int selectedItemCount;
    public String url;

    public StickerGridItem(int resId, int count) {
        this.selectedItemCount = 0;
        this.isOnline = false;
        this.resId = resId;
        this.selectedItemCount = count;
    }

    public StickerGridItem(int resId) {
        this.selectedItemCount = 0;
        this.isOnline = false;
        this.resId = resId;
        this.selectedItemCount = 0;
        this.isOnline = false;
    }
}
