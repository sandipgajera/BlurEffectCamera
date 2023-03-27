package com.photo.blureffectcamera.gallerylib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.photo.blureffectcamera.MainActivity;
import com.photo.blureffectcamera.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment implements OnItemClickListener {
    public static int MAX_COLLAGE;
    public static int MAX_SCRAPBOOK;
    int COLLAGE_IMAGE_LIMIT_MAX;
    int COLLAGE_IMAGE_LIMIT_MIN;
    Activity activity;
    MyGridAdapter adapter;
    List<Album> albumList;
    Button buttonFooterCounter;
    View buttonHeaderBack;
    boolean collageSingleMode;
    Context context;
    TextView deleteAllTv;
    DialogInterface.OnClickListener dialogClickListener;
    LinearLayout footer;
    GalleryListener galleryListener;
    Parcelable gridState;
    GridView gridView;
    TextView headerText;
    boolean isOnBucket;
    boolean isScrapBook;
    boolean isShape;
    TextView maxTv;
    TextView nextTv;
    View.OnClickListener onClickListener;
    TextView removeAllTv;
    int selectedBucketId;
    List<Long> selectedImageIdList;
    List<Integer> selectedImageOrientationList;
    Animation slideInLeft;
    private AlertDialog deleteDialog;
    private Fragment fragment;
    private View fragmentView;

    class GridViewRunnable implements Runnable {
        GridViewRunnable() {
        }

        public void run() {
            if (GalleryFragment.this.gridState != null) {
                GalleryFragment.this.gridView
                        .onRestoreInstanceState(GalleryFragment.this.gridState);
            }
        }
    }

    class ClickListener implements View.OnClickListener {
        ClickListener() {
        }

        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.gallery_header_back_button) {
                GalleryFragment.this.backtrace();
            }

            if (id == R.id.imageView_delete) {
                View parent = (View) view.getParent();
                if (parent != null && parent.getParent() != null) {
                    int location = ((ViewGroup) parent.getParent())
                            .indexOfChild(parent);
                    GalleryFragment.this.footer.removeView(parent);
                    GalleryFragment.this.buttonFooterCounter.setText(""
                            + GalleryFragment.this.footer.getChildCount());
                    GalleryFragment.this.deleteAllTv
                            .setText("("
                                    + GalleryFragment.this.footer
                                    .getChildCount() + ")");
                    long imageid = ((Long) GalleryFragment.this.selectedImageIdList
                            .remove(location)).longValue();
                    GalleryFragment.this.selectedImageOrientationList
                            .remove(location);
                    Point index = GalleryFragment.this.findItemById(imageid);
                    if (index != null) {
                        GridViewItem gridViewItem = (GridViewItem) ((Album) GalleryFragment.this.albumList
                                .get(index.x)).gridItems.get(index.y);
                        gridViewItem.selectedItemCount--;
                        int value = ((GridViewItem) ((Album) GalleryFragment.this.albumList
                                .get(index.x)).gridItems.get(index.y)).selectedItemCount;
                        if (((Album) GalleryFragment.this.albumList
                                .get(index.x)).gridItems == GalleryFragment.this.adapter.items
                                && GalleryFragment.this.gridView
                                .getFirstVisiblePosition() <= index.y
                                && index.y <= GalleryFragment.this.gridView
                                .getLastVisiblePosition()
                                && GalleryFragment.this.gridView
                                .getChildAt(index.y) != null) {
                            TextView text = (TextView) GalleryFragment.this.gridView
                                    .getChildAt(index.y).findViewById(
                                            R.id.textViewSelectedItemCount);
                            text.setText("" + value);
                            if (value <= 0 && text.getVisibility() == View.VISIBLE) {
                                text.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                } else {
                    return;
                }
            }
            if (id == R.id.gallery_delete_all) {
                if (GalleryFragment.this.footer != null
                        && GalleryFragment.this.footer.getChildCount() != 0) {
                    GalleryFragment.this.removeAllTv.setVisibility(View.VISIBLE);
                    GalleryFragment.this.maxTv.setVisibility(View.INVISIBLE);
                    GalleryFragment.this.deleteAllTv.setVisibility(View.INVISIBLE);
                    GalleryFragment.this.removeAllTv
                            .startAnimation(GalleryFragment.this.slideInLeft);
                } else {
                    return;
                }
            }

            if (id == R.id.gallery_remove_all) {
                GalleryFragment.this.remoAll();
            }
            if (id == R.id.button_footer_count || id == R.id.gallery_next) {
                GalleryFragment.this.photosSelectFinished();
            }
        }
    }

    class DialogClickListener implements DialogInterface.OnClickListener {
        DialogClickListener() {
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case GridLayoutManager.DEFAULT_SPAN_COUNT:
                    if (GalleryFragment.this.footer != null) {
                        GalleryFragment.this.footer.removeAllViews();
                        if (GalleryFragment.this.selectedImageIdList != null
                                && GalleryFragment.this.selectedImageIdList.size() > 0) {
                            for (int i = 0; i < GalleryFragment.this.selectedImageIdList
                                    .size(); i++) {
                                Point index = GalleryFragment.this
                                        .findItemById(((Long) GalleryFragment.this.selectedImageIdList
                                                .get(i)).longValue());
                                if (index != null) {
                                    GridViewItem gridViewItem = (GridViewItem) ((Album) GalleryFragment.this.albumList
                                            .get(index.x)).gridItems.get(index.y);
                                    gridViewItem.selectedItemCount--;
                                    int value = ((GridViewItem) ((Album) GalleryFragment.this.albumList
                                            .get(index.x)).gridItems.get(index.y)).selectedItemCount;
                                    if (((Album) GalleryFragment.this.albumList
                                            .get(index.x)).gridItems == GalleryFragment.this.adapter.items
                                            && GalleryFragment.this.gridView
                                            .getFirstVisiblePosition() <= index.y
                                            && index.y <= GalleryFragment.this.gridView
                                            .getLastVisiblePosition()
                                            && GalleryFragment.this.gridView
                                            .getChildAt(index.y) != null) {
                                        TextView text = (TextView) GalleryFragment.this.gridView
                                                .getChildAt(index.y)
                                                .findViewById(
                                                        R.id.textViewSelectedItemCount);
                                        text.setText("" + value);
                                        if (value <= 0 && text.getVisibility() == View.VISIBLE) {
                                            text.setVisibility(View.INVISIBLE);
                                        }
                                    }
                                }
                            }
                        }
                        GalleryFragment.this.selectedImageIdList.clear();
                        GalleryFragment.this.selectedImageOrientationList.clear();
                        GalleryFragment.this.buttonFooterCounter.setText(""
                                + GalleryFragment.this.footer.getChildCount());
                        GalleryFragment.this.deleteAllTv
                                .setText("("
                                        + GalleryFragment.this.footer
                                        .getChildCount() + ")");
                    }
                default:
            }
        }
    }

    public interface GalleryListener {
        void onGalleryCancel();

        void onGalleryOkImageArray(long[] jArr, int[] iArr, boolean z,
                                   boolean z2);

        void onGalleryOkImageArrayRemoveFragment(long[] jArr, int[] iArr,
                                                 boolean z, boolean z2);

        void onGalleryOkSingleImage(long j, int i, boolean z, boolean z2);
    }

    public GalleryFragment() {
        this.isOnBucket = true;
        this.isScrapBook = false;
        this.collageSingleMode = false;
        this.isShape = false;
        this.COLLAGE_IMAGE_LIMIT_MIN = 0;
        this.COLLAGE_IMAGE_LIMIT_MAX = 15;
        this.selectedImageIdList = new ArrayList<Long>();
        this.selectedImageOrientationList = new ArrayList<Integer>();
        this.onClickListener = new ClickListener();
        this.dialogClickListener = new DialogClickListener();
    }

    static {
        MAX_COLLAGE = 15;
        MAX_SCRAPBOOK = 9;
    }

    public void setGalleryListener(GalleryListener l) {
        this.galleryListener = l;
    }

    public GalleryListener getGalleryListener() {
        return this.galleryListener;
    }

    public boolean getIsScrapbook() {
        return this.isScrapBook;
    }

    public void setIsShape(boolean isShape) {
        this.isShape = isShape;
    }

    public void setIsScrapbook(boolean isScrapbook) {
        this.isScrapBook = isScrapbook;
        setLimitMax(MAX_SCRAPBOOK);
        if (this.selectedImageIdList != null
                && this.selectedImageIdList.size() > this.COLLAGE_IMAGE_LIMIT_MAX) {
            remoAll();
        } else if (this.footer != null
                && this.footer.getChildCount() > this.COLLAGE_IMAGE_LIMIT_MAX) {
            remoAll();
        }
    }

    public void setCollageSingleMode(boolean mode) {
        this.collageSingleMode = mode;
        if (mode) {
            if (this.selectedImageIdList != null) {
                for (int i = this.selectedImageIdList.size() - 1; i >= 0; i--) {
                    Point index = findItemById(((Long) this.selectedImageIdList
                            .remove(i)).longValue());
                    if (index != null) {
                        GridViewItem gridViewItem = (GridViewItem) ((Album) this.albumList
                                .get(index.x)).gridItems.get(index.y);
                        gridViewItem.selectedItemCount--;
                        int value = ((GridViewItem) ((Album) this.albumList
                                .get(index.x)).gridItems.get(index.y)).selectedItemCount;
                        if (((Album) this.albumList.get(index.x)).gridItems == this.adapter.items
                                && this.gridView.getFirstVisiblePosition() <= index.y
                                && index.y <= this.gridView
                                .getLastVisiblePosition()
                                && this.gridView.getChildAt(index.y) != null) {
                            TextView text = (TextView) this.gridView
                                    .getChildAt(index.y).findViewById(
                                            R.id.textViewSelectedItemCount);
                            text.setText("" + value);
                            if (value <= 0 && text.getVisibility() == View.VISIBLE) {
                                text.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                }
            }
            if (this.selectedImageOrientationList != null) {
                this.selectedImageOrientationList.clear();
            }
            if (this.footer != null) {
                this.footer.removeAllViews();
            }
            if (this.deleteAllTv != null) {
                this.deleteAllTv.setText("(0)");
            }
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_gallery, container,
                false);


        this.buttonHeaderBack = fragmentView
                .findViewById(R.id.gallery_header_back_button);
        this.buttonHeaderBack.setOnClickListener(this.onClickListener);
        this.footer = (LinearLayout) fragmentView
                .findViewById(R.id.selected_image_linear);
        this.headerText = (TextView) fragmentView
                .findViewById(R.id.textView_header);
        this.buttonFooterCounter = (Button) fragmentView
                .findViewById(R.id.button_footer_count);
        this.deleteAllTv = (TextView) fragmentView
                .findViewById(R.id.gallery_delete_all);
        this.removeAllTv = (TextView) fragmentView
                .findViewById(R.id.gallery_remove_all);
        this.maxTv = (TextView) fragmentView.findViewById(R.id.gallery_max);
        this.nextTv = (TextView) fragmentView.findViewById(R.id.gallery_next);
        this.buttonFooterCounter.setOnClickListener(this.onClickListener);
        this.deleteAllTv.setOnClickListener(this.onClickListener);
        this.nextTv.setOnClickListener(this.onClickListener);
        this.removeAllTv.setOnClickListener(this.onClickListener);
        this.slideInLeft = AnimationUtils.loadAnimation(this.context,
                R.anim.slide_in_left);
        this.maxTv.setText(String.format(getString(R.string.gallery_lib_max),
                new Object[]{Integer.valueOf(getLimitMax())}));
        setFont();
        fragmentView.setFocusableInTouchMode(true);
        fragmentView.requestFocus();
        fragmentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Intent intent = new Intent(getActivity(),
                            MainActivity.class);

//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    getActivity().startActivity(intent);
                    getActivity().overridePendingTransition(
                            R.anim.slide_in_left, R.anim.slide_out_right);
                    getActivity().finish();

                    Log.i("viewBack1001", "Back Press Click");
                    return true;
                } else {
                    return false;
                }
            }
        });
        // Utility.BACKMAIN = true;
        return fragmentView;
    }


    private void setFont() {
        // TODO Auto-generated method stub
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "roboto.regular.ttf");
        this.nextTv.setTypeface(type);
        this.maxTv.setTypeface(type);
        this.deleteAllTv.setTypeface(type);
        this.removeAllTv.setTypeface(type);

    }

    public void onAttach(Activity act) {
        super.onAttach(act);
        this.context = getActivity();
        this.activity = getActivity();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        if (this.gridView != null) {
            try {
                this.gridState = this.gridView.onSaveInstanceState();
            } catch (Exception e) {
            }
        }
        logGalleryFolders();
        updateListForSelection();
        setGridAdapter();
        if (!this.isOnBucket && this.albumList != null
                && this.selectedBucketId >= 0
                && this.selectedBucketId < this.albumList.size()) {
            this.adapter.items = ((Album) this.albumList
                    .get(this.selectedBucketId)).gridItems;
            if (this.gridView != null) {
                this.gridView.post(new GridViewRunnable());
            }
        }
        this.adapter.notifyDataSetChanged();
    }

    private void setGridAdapter() {
        this.gridView = (GridView) getView().findViewById(R.id.gridView);
        this.adapter = new MyGridAdapter(
                this.context,
                ((Album) this.albumList.get(this.albumList.size() - 1)).gridItems,
                this.gridView);
        this.gridView.setAdapter(this.adapter);
        this.gridView.setOnItemClickListener(this);
    }

    private List<GridViewItem> createGridItemsOnClick(int position) {
        List<GridViewItem> items = new ArrayList();
        Album album = (Album) this.albumList.get(position);
        List<Long> imageIdList = album.imageIdList;
        List<Integer> orientList = album.orientationList;
        for (int i = 0; i < imageIdList.size(); i++) {
            items.add(new GridViewItem(this.activity, "", 0, false,
                    ((Long) imageIdList.get(i)).longValue(),
                    ((Integer) orientList.get(i)).intValue()));
        }
        return items;
    }

    private boolean logGalleryFolders() {
        this.albumList = new ArrayList();
        List<Integer> bucketIdList = new ArrayList();
        ContentResolver contentResolver = this.context.getContentResolver();

        Cursor cur = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{"_id", "bucket_display_name", "bucket_id",
                        "_id", "orientation"}, null, null,
                "date_modified DESC");



/*
        Cursor cur = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "SELECT _id,bucket_display_name,bucket_id,_id,orientation FROM images WHERE ((is_pending=0) AND (is_trashed=0) AND (volume_name IN ( 'external_primary' ))) AND ((1)) GROUP BY 1,(2) ORDER BY date_modified DESC");
*/



        /*SELECT
                _id,
                bucket_display_name,
                bucket_id,
                _id,
                orientation
        FROM
                images
        WHERE
                (
                        (is_pending=0)
                        AND (is_trashed=0)
                        AND (volume_name IN ( 'external_primary' ))
                )
        AND ((1)) GROUP BY 1,(2)
        ORDER BY date_modified DESC*/

        List<GridViewItem> items;
        int i;
        if (cur == null || !cur.moveToFirst()) {
            items = new ArrayList();
            for (i = 0; i < this.albumList.size(); i++) {
                items.add(new GridViewItem(
                        this.activity,
                        ((Album) this.albumList.get(i)).name,
                        ((Album) this.albumList.get(i)).imageIdList.size(),
                        true,
                        ((Album) this.albumList.get(i)).imageIdForThumb,
                        ((Integer) ((Album) this.albumList.get(i)).orientationList.get(0)).intValue()));
            }
            this.albumList.add(new Album());
            ((Album) this.albumList.get(this.albumList.size() - 1)).gridItems = items;
            for (i = 0; i < this.albumList.size() - 1; i++) {
                ((Album) this.albumList.get(i)).gridItems = createGridItemsOnClick(i);
            }
            return true;
        }
        int bucketColumn = cur.getColumnIndex("bucket_display_name");
        int bucketId = cur.getColumnIndex("bucket_id");
        int imageId = cur.getColumnIndex("_id");
        int orientationColumnIndex = cur.getColumnIndex("orientation");
        do {
            Album album = new Album();
            int id = cur.getInt(bucketId);
            album.ID = id;
            if (bucketIdList.contains(Integer.valueOf(id))) {
                Album albumFromList = (Album) this.albumList.get(bucketIdList.indexOf(Integer.valueOf(album.ID)));
                albumFromList.imageIdList.add(Long.valueOf(cur.getLong(imageId)));
                albumFromList.orientationList.add(Integer.valueOf(cur.getInt(orientationColumnIndex)));
            } else {
                String bucket = cur.getString(bucketColumn);
                bucketIdList.add(Integer.valueOf(id));
                album.name = bucket;
                album.imageIdForThumb = cur.getLong(imageId);
                album.imageIdList.add(Long.valueOf(album.imageIdForThumb));
                this.albumList.add(album);
                album.orientationList.add(Integer.valueOf(cur.getInt(orientationColumnIndex)));
            }
        } while (cur.moveToNext());
        items = new ArrayList();
        for (i = 0; i < this.albumList.size(); i++) {
            items.add(new GridViewItem(this.activity, ((Album) this.albumList
                    .get(i)).name, ((Album) this.albumList.get(i)).imageIdList
                    .size(), true,
                    ((Album) this.albumList.get(i)).imageIdForThumb,
                    ((Integer) ((Album) this.albumList.get(i)).orientationList.get(0)).intValue()));
        }
        this.albumList.add(new Album());
        ((Album) this.albumList.get(this.albumList.size() - 1)).gridItems = items;
        for (i = 0; i < this.albumList.size() - 1; i++) {
            ((Album) this.albumList.get(i)).gridItems = createGridItemsOnClick(i);
        }
        return true;
    }

    public Boolean onBackPressed() {
        Log.d("BACK",
                "onBackPressed gallery fragment call :::::::::::::::::::::");
        return backtrace();
    }

    boolean backtrace() {
        if (this.isOnBucket) {
            if (this.galleryListener != null) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(this).commit();
                galleryListener.onGalleryCancel();
            }
            return true;
        }
        this.gridView.setNumColumns(2);
        this.adapter.items = ((Album) this.albumList
                .get(this.albumList.size() - 1)).gridItems;
        this.adapter.notifyDataSetChanged();
        this.gridView.smoothScrollToPosition(0);
        this.isOnBucket = true;
        this.headerText.setText(getString(R.string.gallery_select_an_album));
        return false;
    }

    @SuppressLint("StringFormatInvalid")
    public void setLimitMax(int max) {
        this.COLLAGE_IMAGE_LIMIT_MAX = max;
        if (this.maxTv != null) {
            this.maxTv.setText(String.format(getString(R.string.gallery_lib_max), new Object[]{Integer
                            .valueOf(this.COLLAGE_IMAGE_LIMIT_MAX)}));
        }
    }

    public void setLimitMin(int min) {
        this.COLLAGE_IMAGE_LIMIT_MIN = min;
    }

    public int getLimitMax() {
        return this.COLLAGE_IMAGE_LIMIT_MAX;
    }

    public int getLimitMin() {
        return this.COLLAGE_IMAGE_LIMIT_MIN;
    }

    public void onItemClick(AdapterView<?> adapterView, View arg1,
                            int location, long arg3) {
        if (this.isOnBucket) {
            this.gridView.setNumColumns(3);
            this.adapter.items = ((Album) this.albumList.get(location)).gridItems;
            this.adapter.notifyDataSetChanged();
            this.gridView.smoothScrollToPosition(0);
            this.isOnBucket = false;
            this.selectedBucketId = location;
            this.headerText
                    .setText(((Album) this.albumList.get(location)).name);
        } else if (this.footer.getChildCount() >= this.COLLAGE_IMAGE_LIMIT_MAX) {
            Toast msg = Toast.makeText(this.context, String.format(
                    getString(R.string.gallery_no_more), new Object[]{Integer
                            .valueOf(this.COLLAGE_IMAGE_LIMIT_MAX)}),
                    Toast.LENGTH_LONG);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();
        } else {

            View retval = LayoutInflater.from(this.context).inflate(
                    R.layout.footer_item, null);
            ((ImageView) retval.findViewById(R.id.imageView_delete)).setOnClickListener(this.onClickListener);
            ImageView im = (ImageView) retval.findViewById(R.id.imageView);
            if (this.selectedBucketId >= 0
                    && this.selectedBucketId < this.albumList.size()
                    && location >= 0
                    && location < ((Album) this.albumList
                    .get(this.selectedBucketId)).imageIdList.size()) {
                long id = ((Long) ((Album) this.albumList
                        .get(this.selectedBucketId)).imageIdList.get(location))
                        .longValue();
                this.selectedImageIdList.add(Long.valueOf(id));
                this.selectedImageOrientationList.add(((Album) this.albumList
                        .get(this.selectedBucketId)).orientationList
                        .get(location));
                Bitmap bm = GalleryUtility.getThumbnailBitmap(this.context, id,
                        ((Integer) ((Album) this.albumList
                                .get(this.selectedBucketId)).orientationList
                                .get(location)).intValue());
                if (bm != null) {
                    im.setImageBitmap(bm);
                }
                this.footer.addView(retval);
                this.buttonFooterCounter.setText(""
                        + this.footer.getChildCount());
                this.deleteAllTv.setText("(" + this.footer.getChildCount() + ")");
                GridViewItem gridViewItem = (GridViewItem) this.adapter.items
                        .get(location);
                gridViewItem.selectedItemCount++;
                TextView text = (TextView) arg1
                        .findViewById(R.id.textViewSelectedItemCount);
                text.setText(""
                        + ((GridViewItem) this.adapter.items.get(location)).selectedItemCount);
                if (text.getVisibility() == View.INVISIBLE) {
                    text.setVisibility(View.VISIBLE);
                }
                if (this.collageSingleMode) {
                    photosSelectFinished();
                    this.collageSingleMode = false;
                }
            }
        }
    }

    Point findItemById(long id) {
        for (int i = 0; i < this.albumList.size() - 1; i++) {
            List<GridViewItem> list = ((Album) this.albumList.get(i)).gridItems;
            for (int j = 0; j < list.size(); j++) {
                if (((GridViewItem) list.get(j)).imageIdForThumb == id) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    void updateListForSelection() {
        if (this.selectedImageIdList != null
                && !this.selectedImageIdList.isEmpty()) {
            for (int i = 0; i < this.selectedImageIdList.size(); i++) {
                Point index = findItemById(((Long) this.selectedImageIdList
                        .get(i)).longValue());
                if (index != null) {
                    GridViewItem gridViewItem = (GridViewItem) ((Album) this.albumList
                            .get(index.x)).gridItems.get(index.y);
                    gridViewItem.selectedItemCount++;
                }
            }
        }
    }

    void remoAll() {
        if (this.footer != null) {
            this.footer.removeAllViews();
            if (this.selectedImageIdList != null
                    && this.selectedImageIdList.size() > 0) {
                for (int i = 0; i < this.selectedImageIdList.size(); i++) {
                    Point index = findItemById(((Long) this.selectedImageIdList
                            .get(i)).longValue());
                    if (index != null) {
                        GridViewItem gridViewItem = (GridViewItem) ((Album) this.albumList
                                .get(index.x)).gridItems.get(index.y);
                        gridViewItem.selectedItemCount--;
                        int value = ((GridViewItem) ((Album) this.albumList
                                .get(index.x)).gridItems.get(index.y)).selectedItemCount;
                        if (((Album) this.albumList.get(index.x)).gridItems == this.adapter.items
                                && this.gridView.getFirstVisiblePosition() <= index.y
                                && index.y <= this.gridView
                                .getLastVisiblePosition()
                                && this.gridView.getChildAt(index.y) != null) {
                            TextView text = (TextView) this.gridView
                                    .getChildAt(index.y).findViewById(
                                            R.id.textViewSelectedItemCount);
                            text.setText("" + value);
                            if (value <= 0 && text.getVisibility() == View.VISIBLE) {
                                text.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                }
            }
            this.selectedImageIdList.clear();
            this.selectedImageOrientationList.clear();
            this.buttonFooterCounter.setText("" + this.footer.getChildCount());
            this.deleteAllTv.setText("(" + this.footer.getChildCount() + ")");
            getView().findViewById(R.id.gallery_remove_all).setVisibility(View.INVISIBLE);
            getView().findViewById(R.id.gallery_max).setVisibility(View.VISIBLE);
            this.deleteAllTv.setVisibility(View.VISIBLE);
        }
    }

    void photosSelectFinished() {
        int size = this.selectedImageIdList.size();
        if (size <= this.COLLAGE_IMAGE_LIMIT_MIN) {
            Toast msg = Toast.makeText(this.context, String.format(
                    getString(R.string.gallery_message_select_one),
                    new Object[]{Integer.valueOf(getLimitMin() + 1)}),
                    Toast.LENGTH_LONG);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();
            return;
        }

        int i;
        long[] arrr = new long[size];
        for (i = 0; i < size; i++) {
            arrr[i] = ((Long) this.selectedImageIdList.get(i)).longValue();
        }
        int[] orientationArr = new int[size];
        for (i = 0; i < size; i++) {
            orientationArr[i] = ((Integer) this.selectedImageOrientationList.get(i)).intValue();
        }
        if (this.galleryListener != null) {
            this.galleryListener.onGalleryOkImageArray(arrr, orientationArr,
                    this.isScrapBook, this.isShape);
            return;
        }
        try {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(this).commitAllowingStateLoss();
        } catch (Exception e) {
        }
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//
//        try {
//            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
//            childFragmentManager.setAccessible(true);
//            childFragmentManager.set(this, null);
//
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

