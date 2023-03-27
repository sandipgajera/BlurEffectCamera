package com.photo.blureffectcamera.pattern;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.photo.blureffectcamera.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class PatternDetailGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] data;
    private LayoutInflater inflater;

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.pattern_detail_grid_image_view);
        }
    }

    public PatternDetailGridAdapter(Context context, String[] data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setData(String[] data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(this.inflater.inflate(R.layout.pattern_detail_grid_cell, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder myViewHolder, int i) {
        Picasso.with(myViewHolder.itemView.getContext()).load(Uri.parse(this.data[i])).into((Target) myViewHolder.itemView);
    }

    public int getItemCount() {
        return this.data.length;
    }
}
