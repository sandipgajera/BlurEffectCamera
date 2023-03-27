package com.photo.blureffectcamera.hdrlightlib;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;

public class MyPoint extends Point implements Parcelable {
    public static final Creator<MyPoint> CREATOR;

    static class CreatorManager implements Creator<MyPoint> {
        CreatorManager() {
        }

        public MyPoint createFromParcel(Parcel in) {
            return new MyPoint(in);
        }

        public MyPoint[] newArray(int size) {
            return new MyPoint[size];
        }
    }

    public MyPoint(int _x, int _y) {
        super(_x, _y);
    }

    public MyPoint(Parcel in) {
        this.x = in.readInt();
        this.y = in.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.x);
        dest.writeInt(this.y);
    }

    static {
        CREATOR = new CreatorManager();
    }
}
