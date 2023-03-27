package com.photo.blureffectcamera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class OpenMyCreationAdapter extends
        RecyclerView.Adapter<OpenMyCreationAdapter.MyViewHolder> {
    private static final String TAG = "data-->";
    private Context context;
    private LayoutInflater inflater;
    int pos = 0;
    private ArrayList<String> allImageList = new ArrayList<String>();
    public ArrayList<String> selectImageList = new ArrayList<String>();


    public OpenMyCreationAdapter(Context context, ArrayList<String> allImageList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.allImageList = allImageList;
      //  selectImageList = allImageList;
        setHasStableIds(true);
    }

    @Override
    public int getItemCount() {
        return allImageList.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        String path = allImageList.get(position);

        Glide.with(context).load(path).into(holder.roundedImageView);

        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.iv_tick.getVisibility()==View.VISIBLE){
                    holder.iv_tick.setVisibility(View.GONE);
                }else{
                    holder.iv_tick.setVisibility(View.VISIBLE);
                }
                deleteSelected(allImageList.get(position));
            }
        });

    }

    public void deleteSelected(String path){
        if (selectImageList.size()>0){
         if (selectImageList.contains(path)){
             selectImageList.remove(path);
         }  else{
             selectImageList.add(path);
         }
            Log.i(TAG, "deleteSelected: "+selectImageList.size());
        }
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.open_gallery_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView roundedImageView;
        public ImageButton imgbtn_select;
        public View v;
        public  ImageView iv_tick;

        public MyViewHolder(View view) {
            super(view);
            v = view;
            roundedImageView = (ImageView) view.findViewById(R.id.gallery_image_preview_item);
            iv_tick = (ImageView) view.findViewById(R.id.iv_tick);

        }
    }
}
