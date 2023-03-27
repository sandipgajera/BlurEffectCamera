package com.photo.blureffectcamera.sticker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.photo.blureffectcamera.R;
import com.photo.blureffectcamera.common_libs.MyAsyncTask2;

import java.lang.ref.WeakReference;

public class StickerGridAdapter extends BaseAdapter {
	private static final String TAG = "StickerGridAdapter";
	Context context;
	GridView gridView;
	LayoutInflater inflater;
	StickerGridItem[] itemList;
	Options option;
	Bitmap placeHolder;
	ViewHolder viewHolder;

	static class AsyncDrawable extends BitmapDrawable {
		private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

		public AsyncDrawable(Resources res, Bitmap bitmap, BitmapWorkerTask bitmapWorkerTask) {
			super(res, bitmap);
			this.bitmapWorkerTaskReference = new WeakReference(bitmapWorkerTask);
		}

		public BitmapWorkerTask getBitmapWorkerTask() {
			return (BitmapWorkerTask) this.bitmapWorkerTaskReference.get();
		}
	}

	static class ViewHolder {
		ImageView imageView;
		ImageView itemSelected;

		ViewHolder() {
		}
	}

	
	class BitmapWorkerTask extends MyAsyncTask2<Long, Void, Bitmap> {
		private long data;
		private final WeakReference<ImageView> imageViewReference;
		private int item;
		private Resources res;

		public BitmapWorkerTask(ImageView imageView, Resources res, Integer item) {
			this.data = 0;
			this.imageViewReference = new WeakReference(imageView);
			this.item = item.intValue();
			this.res = res;
		}

		protected Bitmap doInBackground(Long... params) {
			this.data = params[0].longValue();
			return BitmapFactory.decodeResource(this.res, this.item, StickerGridAdapter.this.option);
		}

		protected void onPostExecute(Bitmap bitmap) {
			if (isCancelled()) {
				bitmap = null;
			}
			if (this.imageViewReference != null && bitmap != null) {
				ImageView imageView = (ImageView) this.imageViewReference.get();
				if (this == StickerGridAdapter.getBitmapWorkerTask(imageView) && imageView != null) {
					imageView.setImageBitmap(bitmap);
				}
			}
		}
	}

	public StickerGridAdapter(Context context, StickerGridItem[] resList, GridView gridView) {
		this.option = new Options();
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.gridView = gridView;
		this.placeHolder = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_photo);
		this.context = context;
		this.option.inSampleSize = 2;
		this.itemList = resList;
	}

	public int getCount() {
		return this.itemList.length;
	}

	public StickerGridItem getItem(int position) {
		return this.itemList[position];
	}

	public void setItems(StickerGridItem[] itemList) {
		this.itemList = itemList;
	}

	public long getItemId(int position) {
		return (long) position;
	}

	@SuppressLint({ "NewApi" })
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = this.inflater.inflate(R.layout.sticker_grid_item, null);
			this.viewHolder = new ViewHolder();
			this.viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
			this.viewHolder.itemSelected = (ImageView) convertView.findViewById(R.id.image_view_item_selected);
			convertView.setTag(this.viewHolder);
		} else {
			this.viewHolder = (ViewHolder) convertView.getTag();
		}
		

		// Picasso.with(this.context).cancelRequest(this.viewHolder.imageView);
		loadBitmap((long) position, this.viewHolder.imageView, this.itemList[position].resId);
		// }
		if (this.itemList[position].selectedItemCount > 0) {
			if (this.viewHolder.itemSelected.getVisibility() == View.INVISIBLE) {
				this.viewHolder.itemSelected.setVisibility(View.VISIBLE);
			}
		} else if (this.viewHolder.itemSelected.getVisibility() == View.VISIBLE) {
			this.viewHolder.itemSelected.setVisibility(View.INVISIBLE);
		}
		return convertView;
	}

	public void loadBitmap(long resId, ImageView imageView, int item) {
		if (cancelPotentialWork(resId, imageView)) {
			BitmapWorkerTask task = new BitmapWorkerTask(imageView, this.context.getResources(), Integer.valueOf(item));
			imageView.setImageDrawable(new AsyncDrawable(this.context.getResources(), this.placeHolder, task));
			task.execute(new Long[] { Long.valueOf(resId) });
		}
	}

	public static boolean cancelPotentialWork(long data, ImageView imageView) {
		BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
		if (bitmapWorkerTask == null) {
			return true;
		}
		long bitmapData = bitmapWorkerTask.data;
		if (bitmapData != 0 && bitmapData == data) {
			return false;
		}
		bitmapWorkerTask.cancel(true);
		return true;
	}

	private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
		if (imageView != null) {
			Drawable drawable = imageView.getDrawable();
			if (drawable instanceof AsyncDrawable) {
				return ((AsyncDrawable) drawable).getBitmapWorkerTask();
			}
		}
		return null;
	}
}
