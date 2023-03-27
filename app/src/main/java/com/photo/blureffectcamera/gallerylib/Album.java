package com.photo.blureffectcamera.gallerylib;

import java.util.ArrayList;
import java.util.List;

public class Album {
    int ID;
    long coverID;
    public List<GridViewItem> gridItems;
    String id;
    long imageIdForThumb;
    public List<Long> imageIdList;
    String name;
    public List<Integer> orientationList;

    public Album() {
        this.imageIdList = new ArrayList<Long>();
		this.orientationList = new ArrayList<Integer>();
    }
}
