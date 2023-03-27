package com.photo.blureffectcamera.pattern;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import androidx.recyclerview.widget.RecyclerView;

import com.photo.blureffectcamera.R;

import java.util.ArrayList;


@SuppressLint({"InflateParams"})
public class PatternAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG;
    int colorDefault;
    int colorSelected;
    CurrentCollageIndexChangedListener currentIndexlistener;
    boolean isPattern;
     public ArrayList<PatternItem> patternItemArrayList;
    PatternResIdChangedListener patternResIdListener;
    RecyclerView recylceView;
    View selectedListItem;
    int selectedPosition;
    boolean setSelectedView;
	protected boolean bgsetClick = false;
	protected int oldposition;

    public interface CurrentCollageIndexChangedListener {
        void onIndexChanged(int i);

        void onPatternChanged(PatternItem patternItem);
    }

    public interface PatternResIdChangedListener {
        void onPatternResIdChanged(int i);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        private PatternItem item;

        static {
        }
     
        public void setItem(PatternItem item) {
            this.item = item;
            if (item.isFromOnline) {
                this.imageView.setImageBitmap(BitmapFactory.decodeFile(item.path));
                return;
            }
            this.imageView.setImageResource(item.resId);
        }

        public ViewHolder(View itemLayoutView, boolean isPattern) {
            super(itemLayoutView);
            this.imageView = (ImageView) itemLayoutView.findViewById(R.id.image_view_collage_icon);
            if (isPattern) {
                this.imageView.setScaleType(ScaleType.FIT_XY);
            }
            imageView.setImageResource(R.drawable.color_picker);
        }
    }

    public PatternAdapter(ArrayList<PatternItem> patternItemArrayList, CurrentCollageIndexChangedListener l, int cDefault, int cSelected, boolean isPattern, boolean setSelectedView) {
        this.isPattern = false;
        this.setSelectedView = true;
        this.patternItemArrayList = patternItemArrayList;
        this.currentIndexlistener = l;
        this.colorDefault = cDefault;
        this.colorSelected = cSelected;
        this.isPattern = isPattern;
        this.setSelectedView = setSelectedView;
    }

    public PatternAdapter(ArrayList<PatternItem> patternItemArrayList, int cDefault, int cSelected, boolean isPattern, boolean setSelectedView) {
        this.isPattern = false;
        this.setSelectedView = true;
        this.patternItemArrayList = patternItemArrayList;
        this.colorDefault = cDefault;
        this.colorSelected = cSelected;
        this.isPattern = isPattern;
        this.setSelectedView = setSelectedView;
    }

    public void setData(ArrayList<PatternItem> patternItemArrayList) {
        this.patternItemArrayList = patternItemArrayList;
        notifyDataSetChanged();
    }

    public void addItem(PatternItem item) 
    {
        if (item.isFromOnline)  
        {
            int i = 0;
            while (i < this.patternItemArrayList.size()) 
            {
                if (!((PatternItem) this.patternItemArrayList.get(i)).isFromOnline || item.path.compareTo(((PatternItem) this.patternItemArrayList.get(i)).path) != 0) {
                    i++;
                } else {
                    return;
                }
            }
        }
        this.patternItemArrayList.add(2, item);
        notifyItemInserted(2);
    }

    public void removeItem(PatternItem item) {
        if (item.isFromOnline) {
            for (int i = 0; i < this.patternItemArrayList.size(); i++) {
                if (((PatternItem) this.patternItemArrayList.get(i)).isFromOnline) {
                    if (((PatternItem) this.patternItemArrayList.get(i)).path.contains(item.path)) {
                        this.patternItemArrayList.remove(i);
                        notifyItemRemoved(i);
                        return;
                    }
                }
            }
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, this.isPattern);
        itemLayoutView.setOnClickListener(new OnClickListener() {
			private View oldView;
			@Override
			public void onClick(View view) {
				
				 int position = recylceView.getChildPosition(view);
			        RecyclerView.ViewHolder oldViewHolder = recylceView.findViewHolderForPosition(selectedPosition);
			        if (oldViewHolder != null) {
			            oldView = oldViewHolder.itemView;
			            if (oldView != null) {
			                oldView.setBackgroundColor(colorDefault);
			            }
			            
			        }
			        if (selectedListItem != null) {
			        	  
			        }
			        if (isPattern) 
			        {
			            currentIndexlistener.onPatternChanged((PatternItem) patternItemArrayList.get(position));
			        } else {
			            currentIndexlistener.onIndexChanged(position); 
			        }
			        if (setSelectedView) {
			        	oldposition = position;
			            selectedPosition = position;
			            view.setBackgroundColor(colorSelected);
			            bgsetClick = true;
			        }
			        else
			        { 
			        	view.setBackgroundColor(colorDefault);
			        }
			}
		});
        return viewHolder;
    }

   
    public void onAttachedToRecyclerView(RecyclerView recylceView) {
        this.recylceView = recylceView;
    }

    public void setSelectedPositinVoid() {
        this.selectedListItem = null;
        this.selectedPosition = -1;
    }

    static {
        TAG = PatternAdapter.class.getSimpleName();
    }

    public int getItemCount() {
        return this.patternItemArrayList.size();
    }

	@Override
	public void onBindViewHolder(
			RecyclerView.ViewHolder viewHolder,
			int position) {
        ((ViewHolder) viewHolder).setItem((PatternItem) this.patternItemArrayList.get(position));
        
        
        
     if (this.selectedPosition == position && bgsetClick && setSelectedView) { 
    	
        } else {
            viewHolder.itemView.setBackgroundColor(this.colorDefault);
        } 
	}
}
