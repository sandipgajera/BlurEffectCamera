package com.photo.blureffectcamera.sticker;

public class NavigationDrawerItem {
    String icenListUrl;
    int id;
    boolean isDonwloadable;
    boolean isIconListDownloaded;
    public boolean isNew;
    public String name;
    public int resId;

    NavigationDrawerItem(String name, int resId, int id) {
        this.isDonwloadable = false;
        this.isIconListDownloaded = false;
        this.isNew = false;
        this.name = name;
        this.resId = resId;
        this.id = id;
    }
}
