package com.photo.blureffectcamera.lyrebirdlib;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.photo.blureffectcamera.R;


@SuppressLint({"InflateParams"})
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> implements OnClickListener {
    
    int colorDefault;
    int colorSelected;
    public int[] iconList;
    RecyclerAdapterIndexChangedListener listener;
    int proIndex;
    RecyclerView recylceView;
    private int selectedIndex;
    SelectedIndexChangedListener selectedIndexChangedListener;
    View selectedListItem;
    boolean showAds;

    public interface RecyclerAdapterIndexChangedListener {
        void onIndexChanged(int i);
    }

    public interface SelectedIndexChangedListener {
        void selectedIndexChanged(int i);
    }
    public MyRecyclerViewAdapter(final int[] iconList, final RecyclerAdapterIndexChangedListener listener, final int colorDefault, final int colorSelected, final int proIndex, final boolean showAds) {
        this.proIndex = 100;
        this.showAds = false;
        this.iconList = iconList;
        this.listener = listener;
        this.colorDefault = colorDefault;
        this.colorSelected = colorSelected;
        this.proIndex = proIndex;
        this.showAds = showAds;
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ImageView proImageView;
        RecyclerAdapterIndexChangedListener viewHolderListener;

        static {
        }

        public void setRecyclerAdapterIndexChangedListener(RecyclerAdapterIndexChangedListener l) {
            this.viewHolderListener = l;
        }

        public void setItem(int item, boolean setPro) {
            this.imageView.setImageResource(item);
            if (setPro) {
                this.proImageView.setImageResource(R.drawable.pro);
            }
        }

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            this.imageView = (ImageView) itemLayoutView.findViewById(R.id.filter_image);
            this.proImageView = (ImageView) itemLayoutView.findViewById(R.id.pro_imageview);
        }
    }

    public void setSelectedIndexChangedListener(SelectedIndexChangedListener l) {
        this.selectedIndexChangedListener = l;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public void setSelectedIndex(int index) {
        this.selectedIndex = index; 
        this.selectedIndexChangedListener.selectedIndexChanged(this.selectedIndex);
    }
    
    public void setData(int[] iconList) {
        this.iconList = iconList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lyrebird_library_viewitem, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        viewHolder.setRecyclerAdapterIndexChangedListener(this.listener);
        itemLayoutView.setOnClickListener(this);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        boolean setPro = this.showAds && position > this.proIndex;
        viewHolder.setItem(this.iconList[position], setPro);
        if (this.selectedIndex == position) {
            viewHolder.itemView.setBackgroundResource(this.colorSelected);
        } else {
            viewHolder.itemView.setBackgroundResource(this.colorDefault);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recylceView) {
        this.recylceView = recylceView;
    }

    static {
    }

    public void onClick(View view) {
        int position = this.recylceView.getChildPosition(view);
        RecyclerView.ViewHolder oldViewHolder = this.recylceView.findViewHolderForPosition(this.selectedIndex);
        if (oldViewHolder != null) {
            View oldView = oldViewHolder.itemView;
            if (oldView != null) {
                oldView.setBackgroundResource(this.colorDefault);
            }
        }
        if (this.selectedListItem != null) {
            this.selectedIndex = position;
            this.selectedIndexChangedListener.selectedIndexChanged(this.selectedIndex);
            view.setBackgroundResource(this.colorSelected);
            this.selectedListItem = view;
            this.listener.onIndexChanged(position);
        } else {
            this.selectedIndex = position;
            this.selectedIndexChangedListener.selectedIndexChanged(this.selectedIndex);
            view.setBackgroundResource(this.colorSelected);
            this.selectedListItem = view;
            this.listener.onIndexChanged(position);
        }
    }

    public void setSelectedView(int index) {
        if (this.selectedListItem != null) {
            this.selectedListItem.setBackgroundResource(this.colorDefault);
        }
        this.selectedListItem = this.recylceView.getChildAt(index);
        if (this.selectedListItem != null) {
            this.selectedListItem.setBackgroundResource(this.colorSelected);
        }
        setSelectedIndex(index);
    }

    public int getItemCount() {
        return this.iconList.length;
    }
}
