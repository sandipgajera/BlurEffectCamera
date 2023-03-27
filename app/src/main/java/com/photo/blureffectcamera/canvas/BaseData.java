package com.photo.blureffectcamera.canvas;

import android.graphics.Matrix;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class BaseData implements Serializable, Parcelable {
	public static final Creator<BaseData> CREATOR;

	static class CreatorManager implements Creator<BaseData> {
		CreatorManager() {
		}

		public BaseData createFromParcel(Parcel in) {
			return new BaseData(in);
		}

		public BaseData[] newArray(int size) {
			return new BaseData[size];
		}
	}

	protected BaseData() {

	}

	protected BaseData(Parcel in) {
	}

	public void setMatrix(Matrix matrix) {
	}

	public void setImageSaveMatrix(Matrix matrix) {
	}

	public MyMatrix getCanvasMatrix() {
		return null;
	}

	public MyMatrix getImageSaveMatrix() {
		return null;
	}

	static {
		CREATOR = new CreatorManager();
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
	}

	public static BaseData[] toBaseData(Parcelable[] parcelables) {
		if (parcelables == null) {
			return null;
		}
		BaseData[] objects = new BaseData[parcelables.length];
		System.arraycopy(parcelables, 0, objects, 0, parcelables.length);
		return objects;
	}
}
