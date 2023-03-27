package com.photo.blureffectcamera.collagelib;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.photo.blureffectcamera.R;


@SuppressLint({"InflateParams"})
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG;
    int colorDefault;
    int colorSelected;
    CurrentCollageIndexChangedListener currentIndexlistener;
    public static int[] iconList;
    boolean isPattern;
    PatternResIdChangedListener patternResIdListener;
    RecyclerView recylceView;
    View selectedListItem;
    public int selectedPosition;
    boolean setSelectedView;
    
    static {
        TAG = MyAdapter.class.getSimpleName();
    }
    
    public MyAdapter(final int[] iconList, final int colorDefault, final int colorSelected, final boolean isPattern, final boolean setSelectedView) {
        this.isPattern = false;
        this.setSelectedView = true;
        this.iconList = iconList;
        this.colorDefault = colorDefault;
        this.colorSelected = colorSelected;
        this.isPattern = isPattern;
        this.setSelectedView = setSelectedView;
        
    }
    
    public MyAdapter(final int[] iconList, final CurrentCollageIndexChangedListener currentIndexlistener, final int colorDefault, final int colorSelected, final boolean isPattern, final boolean setSelectedView) {
        this.isPattern = false;
        this.setSelectedView = true;
        this.iconList = iconList;
        this.currentIndexlistener = currentIndexlistener;
        this.colorDefault = colorDefault;
        this.colorSelected = colorSelected;
        this.isPattern = isPattern;
        this.setSelectedView = setSelectedView;
    }
    
    @Override
    public int getItemCount() {
    	return this.iconList.length;
        
    }
    
    public void onAttachedToRecyclerView(final RecyclerView recylceView) {
        this.recylceView = recylceView;
    }
    
   
    
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        final View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, (ViewGroup)null);
        final ViewHolder viewHolder = new ViewHolder(inflate, this.isPattern);
        inflate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View selectedListItem) {
				// TODO Auto-generated method stub
				
			     final int childPosition = recylceView.getChildPosition(selectedListItem);
			        final RecyclerView.ViewHolder viewHolderForPosition = recylceView.findViewHolderForPosition(selectedPosition);
			        if (viewHolderForPosition != null) {
			            final View itemView = viewHolderForPosition.itemView;
			            if (itemView != null) {
			                itemView.setBackgroundColor(colorDefault);
			            }
			        }
			        while (true) {
			            if (selectedListItem != null) {
			                if (isPattern) {
			               
			                    currentIndexlistener.onIndexChanged(iconList[childPosition]);
			                }
			                else {
			                    currentIndexlistener.onIndexChanged(childPosition);
			                }
			                if (setSelectedView) {
			                    selectedPosition = childPosition;
			                    selectedListItem.setBackgroundColor(colorSelected);
			                    selectedListItem = selectedListItem;
			                }
			                return;
			            }
			            continue;
			        }
			}
			
		});
        return viewHolder;
    }
    
    public void setData(final int[] iconList) {
        this.iconList = iconList;
    }
    
    public interface CurrentCollageIndexChangedListener
    {
        void onIndexChanged(int p0);
    }
    
    public interface PatternResIdChangedListener
    {
        void onPatternResIdChanged(int p0);
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private static final String TAG;
        public ImageView imageView;
        private int item;
        
        static {
            TAG = ViewHolder.class.getSimpleName();
        }
        
        public ViewHolder(final View view, final boolean b) {
            super(view);
            this.imageView = (ImageView)view.findViewById(R.id.image_view_collage_icon);
            if (b) {
                this.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        
        public void setItem(final int item) {
            this.item = item;
            this.imageView.setImageResource(this.item);
        }
    }

	@Override
	public void onBindViewHolder(
			RecyclerView.ViewHolder paramVH,
			int n) {
		((ViewHolder) paramVH).setItem(this.iconList[n]);
        if (this.selectedPosition == n) {
        	paramVH.itemView.setBackgroundColor(this.colorSelected);
            return;
        }
        paramVH.itemView.setBackgroundColor(this.colorDefault);
	}
}
