package com.photo.blureffectcamera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;


public class MyCreationActivity extends AppCompatActivity {
    private static final String TAG = "data-->";
    public static ArrayList<String> f11f;
    private RecyclerView recyclerView;
    private OpenMyCreationAdapter1 openDraftImgAdapter;
    public static ArrayList<ImageModel> allImageList;
    File[] listFile;
    Toolbar toolbar_myCreation;
    RelativeLayout rlNoimage;
    ImageView iv_delete;
    TextView tv_select, tv_selected;
    LinearLayout ll_select;
    public static ArrayList<String> selected_pos_list = new ArrayList<String>();


    int ITEMS_PER_AD = 8;
    int NATIVE_EXPRESS_AD_HEIGHT = 150;

    FrameLayout adContainerView;
    String[] projection = {MediaStore.MediaColumns.DATA};
    boolean isSelected = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_creation);

        bindview();
        init();
        getAllImages();
        addlistener();

    }


    private void bindview() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        toolbar_myCreation = (Toolbar) findViewById(R.id.toolbar_myCreation);
        rlNoimage = (RelativeLayout) findViewById(R.id.rlNoImage);
        ll_select = (LinearLayout) findViewById(R.id.ll_select);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        tv_select = (TextView) findViewById(R.id.tv_select);
        tv_selected = (TextView) findViewById(R.id.tv_selected);
    }

    private void init() {
        toolbar_myCreation.setTitle("My Creation");
        toolbar_myCreation.setTitleTextColor(getResources().getColor(
                R.color.white));
        setSupportActionBar(toolbar_myCreation);
        allImageList = new ArrayList<>();
        allImageList.clear();
    }

    private void addlistener() {
        toolbar_myCreation.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onBackPressed();
            }
        });


        openDraftImgAdapter = new OpenMyCreationAdapter1(MyCreationActivity.this, allImageList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(
                MyCreationActivity.this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        //   recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setVisibility(View.VISIBLE);

        if (allImageList.size() == 0) {
            if (rlNoimage.getVisibility() == View.GONE) {
                rlNoimage.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        }

        if (allImageList.size() > 0) {
            if (rlNoimage.getVisibility() == View.VISIBLE) {
                rlNoimage.setVisibility(View.GONE);

            }
            recyclerView.setAdapter(openDraftImgAdapter);
//            addNativeExpressAds();
//            setUpAndLoadNativeExpressAds();
        }

        ll_select.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected) {
                    isSelected = false;
                    tv_selected.setVisibility(View.GONE);
                    tv_select.setVisibility(View.VISIBLE);
                    iv_delete.setImageDrawable(getResources().getDrawable(R.drawable.delete));
                    selected_pos_list.clear();
                } else {
                    isSelected = true;
                    tv_select.setVisibility(View.GONE);
                    tv_selected.setVisibility(View.VISIBLE);
                    iv_delete.setImageDrawable(getResources().getDrawable(R.drawable.delete_pressed));

                }
            }
        });


        iv_delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (selected_pos_list.size()>0){
                    for (int i = 0; i < selected_pos_list.size(); i++) {
                        File newFile = new File(selected_pos_list.get(i));
                        boolean delete = newFile.delete();
                        if (delete) {
                            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(selected_pos_list.get(i)))));
                            openDraftImgAdapter.notifyDataSetChanged();
                        }

                    }}else{
                        Toast.makeText(MyCreationActivity.this, "Please Select atleast one image!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i(TAG, "onClick: " + e.getMessage());
                }
            }
        });
    }

    /*private void getAllImage() {
        File[] files = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + getString(R.string.directory)).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()
                        && (file.getName().endsWith(".jpg")
                        || file.getName().endsWith(".jpeg") || file
                        .getName().endsWith(".png"))) {
                    allImageList.add(file.getAbsolutePath());
                }
            }
            Collections.reverse(allImageList);
        }
    }*/

    public void getAllImages() {
        String orderByDate = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null,   orderByDate);
        while (cursor.moveToNext()) {
            String absolutePathOfImage = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            ImageModel ImageModel = new ImageModel();
            ImageModel.setImage(absolutePathOfImage);
            allImageList.add(ImageModel);
        }
        cursor.close();
    }

    public void onBackPressed() {
        Intent intent = new Intent(MyCreationActivity.this, CameraLayout.class);
        startActivity(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    protected void onStop() {
        super.onStop();
    }

    public class OpenMyCreationAdapter1 extends
            RecyclerView.Adapter<OpenMyCreationAdapter1.MyViewHolder> {
        private static final String TAG = "data-->";
        private Context context;
        private LayoutInflater inflater;
        int pos = 0;
        private ArrayList<ImageModel> allImageList;


        public OpenMyCreationAdapter1(Context context, ArrayList<ImageModel> allImageList) {
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
            ImageModel imageModel = allImageList.get(position);

            Glide.with(context).load(imageModel.getImage()).into(holder.roundedImageView);

            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isSelected) {

                        if (holder.iv_tick.getVisibility() == View.VISIBLE) {
                            holder.iv_tick.setVisibility(View.GONE);
                        } else {
                            holder.iv_tick.setVisibility(View.VISIBLE);
                        }
                        deleteSelected(imageModel.getImage());
                    } else {
                        startActivity(new Intent(MyCreationActivity.this, PhotoEditorActivity.class)
                                .putExtra("path", imageModel.getImage()));
                        finish();
                    }
                }
            });

        }

        public void deleteSelected(String path) {
            if (selected_pos_list != null) {
                if (selected_pos_list.contains(path)) {
                    selected_pos_list.remove(path);
                } else {
                    selected_pos_list.add(path);
                }
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
            public ImageView iv_tick;

            public MyViewHolder(View view) {
                super(view);
                v = view;
                roundedImageView = (ImageView) view.findViewById(R.id.gallery_image_preview_item);
                iv_tick = (ImageView) view.findViewById(R.id.iv_tick);

            }
        }
    }

}