package com.photo.blureffectcamera.colorPicker;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;


import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.canvas.TextData;

import java.util.ArrayList;
import java.util.List;


public class RainbowPickerAdapter extends BaseAdapter {
    int colorGridColumnWidth;
    private List<Integer> colorList;
    private Context context;

    public RainbowPickerAdapter(Context context) {
        int green;
        int red;
        int blue;
		this.colorList = new ArrayList<Integer>();
        this.context = context;
        this.colorGridColumnWidth = (int) context.getResources().getDimension(R.dimen.colorGridColumnWidth);
        int step = AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY / 16;
        for (green = 0; green <= TextData.defBgAlpha; green += 16) {
            this.colorList.add(Integer.valueOf(Color.rgb(TextData.defBgAlpha, green, 0)));
        }
        for (red = TextData.defBgAlpha; red >= 0; red -= 16) {
            this.colorList.add(Integer.valueOf(Color.rgb(red, TextData.defBgAlpha, 0)));
        }
        for (blue = 0; blue <= TextData.defBgAlpha; blue += 16) {
            this.colorList.add(Integer.valueOf(Color.rgb(0, TextData.defBgAlpha, blue)));
        }
        for (green = TextData.defBgAlpha; green >= 0; green -= 16) {
            this.colorList.add(Integer.valueOf(Color.rgb(0, green, TextData.defBgAlpha)));
        }
        for (red = 0; red <= TextData.defBgAlpha; red += 16) {
            this.colorList.add(Integer.valueOf(Color.rgb(red, 0, TextData.defBgAlpha)));
        }
        for (blue = TextData.defBgAlpha; blue >= 0; blue -= 16) {
            this.colorList.add(Integer.valueOf(Color.rgb(TextData.defBgAlpha, 0, blue)));
        }
        for (int i = TextData.defBgAlpha; i >= 0; i -= 11) {
            this.colorList.add(Integer.valueOf(Color.rgb(i, i, i)));
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new LayoutParams(this.colorGridColumnWidth, this.colorGridColumnWidth));
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setBackgroundColor(((Integer) this.colorList.get(position)).intValue());
        imageView.setId(position);
        return imageView;
    }

    public int getCount() {
        return this.colorList.size();
    }

    public Integer getItem(int position) {
        return (Integer) this.colorList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }
}
