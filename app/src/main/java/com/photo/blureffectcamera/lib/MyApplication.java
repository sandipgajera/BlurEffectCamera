package com.photo.blureffectcamera.lib;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;


public class MyApplication extends Application {
	public void onCreate() {
		super.onCreate();
	}
    public static String ORIENTATION = "ORIENTATION";
    public static String HORIZONTAL = "HORIZONTAL";
    public static String VERTICAL = "VERTICAL";

    public static int p_width, p_height;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
	public void onTerminate() {
		super.onTerminate();
	}
}
