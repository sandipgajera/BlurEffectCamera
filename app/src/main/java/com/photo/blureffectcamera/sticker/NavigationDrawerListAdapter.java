package com.photo.blureffectcamera.sticker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.photo.blureffectcamera.R;

public class NavigationDrawerListAdapter extends BaseAdapter {
    Context context;
    NavigationDrawerItem[] navigationDrawerItemList;
	private Typeface type;

    static class ViewHolderItem {
        ImageView imageViewItem;
        ImageView imageViewNewBadge;
        TextView textViewItem;

        ViewHolderItem() {
        }
    }

    public NavigationDrawerListAdapter(Context context, NavigationDrawerItem[] data) {
        this.context = context;
        this.navigationDrawerItemList = data;
        type = Typeface.createFromAsset(context.getAssets(),"roboto.regular.ttf");
    }

    public int getCount() {
        return this.navigationDrawerItemList.length;
    }

    public Object getItem(int position) {
        return this.navigationDrawerItemList;
    }

    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("WrongConstant")
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;
        if (convertView == null) {
            convertView = ((Activity) this.context).getLayoutInflater().inflate(R.layout.sticker_nav_list_item, parent, false);
            viewHolder = new ViewHolderItem();
            viewHolder.textViewItem = (TextView) convertView.findViewById(R.id.nav_list_text);
            viewHolder.imageViewItem = (ImageView) convertView.findViewById(R.id.nav_list_image);
            viewHolder.imageViewNewBadge = (ImageView) convertView.findViewById(R.id.nav_list_new_badge);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }
        viewHolder.textViewItem.setText(this.navigationDrawerItemList[position].name);
        viewHolder.imageViewItem.setImageResource(this.navigationDrawerItemList[position].resId);
        if (this.navigationDrawerItemList[position].isNew) {
            viewHolder.imageViewNewBadge.setVisibility(0);
        } else {
            viewHolder.imageViewNewBadge.setVisibility(4);
        }
        viewHolder.textViewItem.setTypeface(type);
        return convertView;
    }
}
