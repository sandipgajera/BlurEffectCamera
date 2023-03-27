package com.photo.blureffectcamera.lib;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import androidx.recyclerview.widget.RecyclerView;

import com.photo.blureffectcamera.R;


@SuppressWarnings("unused")
@SuppressLint({"InflateParams"})
public class MyAdapter extends MyRecylceAdapterBase<RecyclerView.ViewHolder> implements OnClickListener {
    private static final String TAG;
    int colorDefault;
    int colorSelected;
    CurrentSquareIndexChangedListener currentIndexlistener;
    public int[] iconList;
    boolean isPattern;
    PatternResIdChangedListener patternResIdListener;
    RecyclerView recylceView;
    View selectedListItem;
    int selectedPosition;
    boolean setSelectedView;

    public interface CurrentSquareIndexChangedListener {
        void onIndexChanged(int i);
    }

    public interface PatternResIdChangedListener {
        void onPatternResIdChanged(int i);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG;
        public ImageView imageView;
        private int item;

        static {
            TAG = ViewHolder.class.getSimpleName();
        }

        public void setItem(int items) {
            this.item = items;
            this.imageView.setImageResource(this.item);
        }

        public ViewHolder(View itemLayoutView, boolean isPattern) {
            super(itemLayoutView);
            this.imageView = (ImageView) itemLayoutView.findViewById(R.id.image_view_square_icon);
            if (isPattern) {
                this.imageView.setScaleType(ScaleType.CENTER_CROP);
            }
        }
    }

    public MyAdapter(int[] fruitsData, CurrentSquareIndexChangedListener l , int cDefault, int cSelected, boolean isPattern, boolean setSelectedView) {
        this.isPattern = false;
        this.setSelectedView = true;
        this.iconList = fruitsData;
        this.currentIndexlistener = l;
        this.colorDefault = cDefault;
        this.colorSelected = cSelected;
        this.isPattern = isPattern;
        this.setSelectedView = setSelectedView;
    }

    public MyAdapter(int[] fruitsData, int cDefault, int cSelected, boolean isPattern, boolean setSelectedView) {
        this.isPattern = false;
        this.setSelectedView = true;
        this.iconList = fruitsData;
        this.colorDefault = cDefault;
        this.colorSelected = cSelected;
        this.isPattern = isPattern;
        this.setSelectedView = setSelectedView;
    }

    public void setData(int[] fruitsData) {
        this.iconList = fruitsData;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.square_recycler_view_item, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, this.isPattern);
        itemLayoutView.setOnClickListener(this);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setItem(this.iconList[position]);
        if (this.selectedPosition == position) {
            viewHolder.itemView.setBackgroundColor(this.colorSelected);
        } else {
            viewHolder.itemView.setBackgroundColor(this.colorDefault);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recylceView) {
        this.recylceView = recylceView;
    }

    public void setSelectedPositinVoid() {
        this.selectedListItem = null;
        this.selectedPosition = -1;
    }

    static {
        TAG = MyAdapter.class.getSimpleName();
    }

    public void onClick(View view) {
        int position = this.recylceView.getChildPosition(view);
        if (position >= 0) {
        	
           RecyclerView.ViewHolder oldViewHolder = this.recylceView.findViewHolderForPosition(this.selectedPosition);
            if (oldViewHolder != null) {
                View oldView = oldViewHolder.itemView;
                if (oldView != null) {
                    oldView.setBackgroundColor(this.colorDefault);
                }
            }
            if (this.selectedListItem != null) {
            }
            if (this.isPattern) {
                this.currentIndexlistener.onIndexChanged(this.iconList[position]);
            } else {
                this.currentIndexlistener.onIndexChanged(position);
            }
            if (this.setSelectedView) {
                this.selectedPosition = position;
                view.setBackgroundColor(this.colorSelected);
                this.selectedListItem = view;
            }
        }
    }
  
    public int getItemCount() {
        return this.iconList.length;
    }

	
}
