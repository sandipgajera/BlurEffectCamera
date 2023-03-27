package com.photo.blureffectcamera.sticker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.photo.blureffectcamera.R;

import java.util.ArrayList;
import java.util.List;


public class StickerGalleryFragment extends Fragment implements
        OnItemClickListener {
    private static final String TAG = "StickerGalleryFragment";
    public static int initialNavSelection;
    final int STICKER_LIMIT;
    Activity activity;
    Context context;
    int count;
    public static int currentListIndex;
    //    DrawerLayout drawerLayout;
    StickerGalleryListener galleryListener;
    public static StickerGridAdapter gridAdapter;
    public static GridView gridView;
    TextView headerText;
    int initialTogglePos;
    StickerGalleryFragment mFragment;
    float moveFactor;
    //    ListView navList;
    NavigationDrawerItem[] navigationDrawerItemList;
    OnClickListener onClickListener;
    View progressBar;
    List<StickerGridItem> selectedImageIdList;
    Animation slideIn;
    Animation slideOut;
    public static StickerGridItem[][] stickerItemList;
    ImageView toggleButton;
    int totalImage;
    private View fragmentView;
    RelativeLayout rlViewDialog;
    RecyclerView horizontal_recycler_view;
    private ProgressDialog progressDialog_ads;

    String checkAdLoad;
    TextView tvStickerCategoryName;
    ImageView ivStickerCategory;
    String com;
    Dialog dialog;
    ProgressDialog progressDialog;

    class C07671 implements OnTouchListener {
        C07671() {
        }

        public boolean onTouch(View v, MotionEvent event) {
//            StickerGalleryFragment.this.drawerLayout
//                    .openDrawer(StickerGalleryFragment.this.navList);
            return true;
        }
    }

    class C07682 implements Runnable {
        C07682() {
        }

        public void run() {
            StickerGalleryFragment.this.calculateTogglePos();
        }
    }

//    class C07693 implements OnItemClickListener {
//        C07693() {
//        }
//
//        public void onItemClick(AdapterView<?> adapterView, View view, int pos,
//                                long id) {
//            StickerGalleryFragment.this.drawerLayout
//                    .closeDrawer(StickerGalleryFragment.this.navList);
//            Toast.makeText(activity, "Sticker " + pos + "", Toast.LENGTH_SHORT).show();
//            if (StickerGalleryFragment.this.currentListIndex != pos) {
//                if (StickerGalleryFragment.this.stickerItemList == null) {
//                    StickerGalleryFragment.this.createItemList();
//                }
//                StickerGalleryFragment.this.gridAdapter
//                        .setItems(StickerGalleryFragment.this.stickerItemList[pos]);
//                StickerGalleryFragment.this.gridView.smoothScrollToPosition(0);
//                StickerGalleryFragment.this.gridAdapter.notifyDataSetChanged();
//            }
//            StickerGalleryFragment.this.currentListIndex = pos;
//        }
//    }

    class C07716 implements DialogInterface.OnClickListener {
        C07716() {
        }

        public void onClick(DialogInterface arg0, int arg1) {
        }
    }

    class C07737 implements OnClickListener {

        class C07721 implements Runnable {
            C07721() {
            }

            public void run() {
                if (StickerGalleryFragment.this.count > 0) {
                    if (StickerGalleryFragment.this.progressBar != null) {
                        StickerGalleryFragment.this.progressBar
                                .setVisibility(View.INVISIBLE);
                    }
                    if (StickerGalleryFragment.this.getActivity() != null) {
                        StickerGalleryFragment.this.getActivity()
                                .getSupportFragmentManager().beginTransaction()
                                .hide(StickerGalleryFragment.this.mFragment)
                                .commitAllowingStateLoss();
                    }
                }
            }
        }


        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.textView_header) {
                StickerGalleryFragment.this.backtrace();
            }
            if (id == R.id.sticker_gallery_ok) {
                int i;
                new Handler().postDelayed(new C07721(), 30000);
                int outerSize = StickerGalleryFragment.this.stickerItemList.length;
                for (int j = 0; j < outerSize; j++) {
                    for (StickerGridItem stickerGridItem : StickerGalleryFragment.this.stickerItemList[j]) {
                        stickerGridItem.selectedItemCount = 0;
                    }
                }
                int size = StickerGalleryFragment.this.selectedImageIdList
                        .size();
                StickerGridItem[] arrr = new StickerGridItem[size];
                for (i = 0; i < size; i++) {
                    arrr[i] = (StickerGridItem) StickerGalleryFragment.this.selectedImageIdList
                            .get(i);
                }
                StickerGalleryFragment.this.selectedImageIdList.clear();
                if (StickerGalleryFragment.this.galleryListener == null) {
                    StickerGalleryFragment.this.getActivity()
                            .getSupportFragmentManager().beginTransaction()
                            .hide(StickerGalleryFragment.this.mFragment)
                            .commitAllowingStateLoss();
                    return;
                }
                for (i = 0; i < arrr.length; i++) {
                    if (arrr[i].isOnline) {
                        if (StickerGalleryFragment.this.progressBar
                                .getVisibility() != View.VISIBLE) {
                            StickerGalleryFragment.this.progressBar
                                    .setVisibility(View.VISIBLE);
                        }
                        StickerGalleryFragment stickerGalleryFragment = StickerGalleryFragment.this;
                        stickerGalleryFragment.count++;
                        StickerGridItem aa = arrr[i];

                    }
                }
                if (StickerGalleryFragment.this.count <= 0) {
                    if (StickerGalleryFragment.this.progressBar != null) {
                        StickerGalleryFragment.this.progressBar
                                .setVisibility(View.INVISIBLE);
                    }
                    if (StickerGalleryFragment.this.galleryListener != null) {
                        StickerGalleryFragment.this.galleryListener
                                .onGalleryOkImageArray(arrr);
                    } else {
                        StickerGalleryFragment.this.getActivity()
                                .getSupportFragmentManager().beginTransaction()
                                .hide(StickerGalleryFragment.this.mFragment)
                                .commitAllowingStateLoss();
                    }
                }
            }
        }
    }

    public interface StickerGalleryListener {
        void onGalleryCancel();

        void onGalleryOkImageArray(StickerGridItem[] stickerGridItemArr);
    }

    class C14184 extends DrawerLayout.SimpleDrawerListener {
        C14184() {
        }

        @SuppressLint({"NewApi"})
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
        }

        @SuppressLint({"NewApi"})
        public void onDrawerOpened(View drawerView) {
        }

        @SuppressLint({"NewApi"})
        public void onDrawerSlide(View drawerView, float slideOffset) {
            if (StickerGalleryFragment.this.initialTogglePos <= 0) {
                StickerGalleryFragment.this.calculateTogglePos();
            }
            StickerGalleryFragment.this.moveFactor = (-slideOffset)
                    * ((float) StickerGalleryFragment.this.initialTogglePos);
            if (VERSION.SDK_INT >= 11) {
                StickerGalleryFragment.this.toggleButton
                        .setX(StickerGalleryFragment.this.moveFactor);
            }
        }

        @SuppressLint("WrongConstant")
        public void onDrawerStateChanged(int newState) {
//            if (newState == 2
//                    && !StickerGalleryFragment.this.drawerLayout
//                    .isDrawerOpen(3)) {
//            }
        }
    }


    public StickerGalleryFragment() {
        this.currentListIndex = initialNavSelection;
        this.moveFactor = 0.0f;
        this.initialTogglePos = 0;
        this.totalImage = 0;
        this.selectedImageIdList = new ArrayList<StickerGridItem>();
        this.STICKER_LIMIT = 12;
        this.mFragment = this;
        this.onClickListener = new C07737();
        this.count = 0;
    }

    static {
        initialNavSelection = 2;
    }

    public void setGalleryListener(StickerGalleryListener l) {
        this.galleryListener = l;
    }

    @SuppressLint("WrongConstant")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.sticker_fragment_gallery,
                container, false);

        rlViewDialog = fragmentView.findViewById(R.id.rlViewDialog);
    //    prefHandler = new PrefHandler(getActivity());
        horizontal_recycler_view = fragmentView.findViewById(R.id.horizontal_recycler_view);
//        loadAd();
        progressDialog = new ProgressDialog(activity);
      //  ePreferences = EPreferences.getInstance(getActivity());
//        navList = fragmentView.findViewById(R.id.drawer);

        this.progressBar = fragmentView.findViewById(R.id.progress_download);
        if (this.progressBar != null) {
            this.progressBar.setVisibility(4);
        }

        progressDialog_ads = new ProgressDialog(getActivity());
        progressDialog_ads.setTitle("Please Wait");
        progressDialog_ads.setMessage("Loading Ad");


        this.headerText = (TextView) fragmentView
                .findViewById(R.id.textView_header);
        this.headerText.setText(String.format(
                getString(R.string.sticker_items_selected_zero),
                new Object[]{Integer.valueOf(12)}));
        this.headerText.setOnClickListener(this.onClickListener);
        ((ImageButton) fragmentView.findViewById(R.id.sticker_gallery_ok))
                .setOnClickListener(this.onClickListener);
        this.toggleButton = (ImageView) fragmentView
                .findViewById(R.id.toggle_button);
        this.slideIn = AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_in_left_galler_toggle);
        this.slideOut = AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_out_left_gallery_toggle);
        this.slideIn.setFillAfter(true);
        this.slideOut.setFillAfter(true);
        this.toggleButton.setOnTouchListener(new C07671());
        this.toggleButton.post(new C07682());
        createNavItemList();


        HorizontalStickerDataAdapter horizontalStickerDataAdapter = new HorizontalStickerDataAdapter(getActivity(), this.navigationDrawerItemList);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalStickerDataAdapter);

//        this.drawerLayout = (DrawerLayout) fragmentView
//                .findViewById(R.id.layout_gallery_fragment_drawer);

//        this.drawerLayout.setDrawerListener(new C14184());
        setFont();
        return fragmentView;
    }


    private void setFont() {
        // TODO Auto-generated method stub
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),
                "roboto.regular.ttf");
        this.headerText.setTypeface(type);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    void calculateTogglePos() {
        int[] loc = new int[2];
        this.toggleButton.getLocationOnScreen(loc);
        this.initialTogglePos = this.toggleButton.getWidth() + loc[0];
    }

    public void onAttach(Activity act) {
        super.onAttach(act);
        this.context = getActivity();
        this.activity = getActivity();
    }

    public void onStart() {
        super.onStart();
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (this.gridView != null && !hidden) {
            this.gridAdapter.notifyDataSetChanged();
            this.gridView.invalidateViews();
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setGridAdapter();
//        if (this.drawerLayout != null) {
//            this.drawerLayout.postDelayed(new C07705(), 600);
//        }
    }

    private void setGridAdapter() {
        this.gridView = (GridView) getView().findViewById(R.id.gridView);
        if (this.stickerItemList == null) {
            createItemList();
        }
        this.gridAdapter = new StickerGridAdapter(this.context,
                this.stickerItemList[0], this.gridView);
        this.gridView.setAdapter(this.gridAdapter);
        this.gridView.setOnItemClickListener(this);
    }

    public static void createItemList() {
        int j, i;
        int outerSizeRes = Utility.stickerResIdList.length;
        stickerItemList = new StickerGridItem[(outerSizeRes)][];
        for (j = 0; j < outerSizeRes; j++) {
            int size = Utility.stickerResIdList[j].length;
            stickerItemList[j] = new StickerGridItem[size];
            for (i = 0; i < size; i++) {
                stickerItemList[j][i] = new StickerGridItem(
                        Utility.stickerResIdList[j][i]);
            }
        }
    }

 /*   public void onBackPressed() {
        Toast.makeText(activity, "Click Back", Toast.LENGTH_SHORT).show();
        backtrace();
    }*/

    @SuppressLint("WrongConstant")
    public void backtrace() {

//        if (this.drawerLayout.isDrawerOpen(3)) {
        int outerSize = this.stickerItemList.length;
        for (int j = 0; j < outerSize; j++) {
            for (StickerGridItem stickerGridItem : this.stickerItemList[j]) {
                stickerGridItem.selectedItemCount = 0;
            }
        }
        this.selectedImageIdList.clear();
        this.galleryListener.onGalleryCancel();
        return;
    }
//        this.drawerLayout.openDrawer(this.navList);


    public void setTotalImage(int size) {
        this.totalImage = size;
        if (this.headerText != null) {
            this.headerText.setText((this.totalImage + this.selectedImageIdList
                    .size())
                    + String.format(getString(R.string.sticker_items_selected),
                    new Object[]{Integer.valueOf(12)}));
        }
    }


    @SuppressLint("ResourceType")
    public void onItemClick(AdapterView<?> adapterView, View arg1,
                            int location, long arg3) {
        if (this.totalImage + this.selectedImageIdList.size() >= 12) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this.context);
            alertDialogBuilder.setMessage(String.format(
                    getString(R.string.sticker_choose_limit),
                    new Object[]{Integer.valueOf(12)}));
            alertDialogBuilder.setPositiveButton(getString(17039370),
                    new C07716());
            alertDialogBuilder.create().show();
            return;
        }
        if (this.gridAdapter.itemList[location].selectedItemCount == 0) {
            StickerGridItem stickerGridItem = this.gridAdapter.itemList[location];
            stickerGridItem.selectedItemCount++;
        } else {
            this.gridAdapter.itemList[location].selectedItemCount = 0;
        }
        ImageView imageItemSelected = (ImageView) arg1
                .findViewById(R.id.image_view_item_selected);
        if (imageItemSelected.getVisibility() == View.INVISIBLE
                && this.gridAdapter.itemList[location].selectedItemCount == 1) {
            imageItemSelected.setVisibility(View.VISIBLE);
        }
        if (imageItemSelected.getVisibility() == View.VISIBLE
                && this.gridAdapter.itemList[location].selectedItemCount == 0) {
            imageItemSelected.setVisibility(View.INVISIBLE);
        }
        StickerGridItem item = this.gridAdapter.itemList[location];
        if (this.gridAdapter.itemList[location].selectedItemCount == 1) {
            this.selectedImageIdList.add(this.gridAdapter.itemList[location]);
        } else {
            for (int i = 0; i < this.selectedImageIdList.size(); i++) {
                if (this.selectedImageIdList.get(i) == item) {
                    this.selectedImageIdList.remove(i);
                    break;
                }
            }
        }
        this.headerText.setText((this.totalImage + this.selectedImageIdList
                .size())
                + String.format(getString(R.string.sticker_items_selected),
                new Object[]{Integer.valueOf(12)}));
    }

    void createNavItemList() {
        if (this.navigationDrawerItemList == null) {

            this.navigationDrawerItemList = new NavigationDrawerItem[]{
                    new NavigationDrawerItem("Smile", R.drawable.emoji_001,
                            101),

                    new NavigationDrawerItem(
                            "Christmas",
                            R.drawable.list_icon_love, 102),

                    new NavigationDrawerItem("Crown", R.drawable.list_icon_flower_crown,
                            103),

                    new NavigationDrawerItem(
                            this.context
                                    .getString(R.string.sticker_navigation_name_list_love),
                            R.drawable.list_icon_love, 104),

                    new NavigationDrawerItem("Cat",
                            R.drawable.list_icon_snap_4, 105),

                    new NavigationDrawerItem("Goggles",
                            R.drawable.list_icon_glasses, 106),

                    new NavigationDrawerItem(
                            this.context
                                    .getString(R.string.sticker_navigation_name_list_birds),
                            R.drawable.list_icon_love_bird, 107),
                    new NavigationDrawerItem(
                            this.context
                                    .getString(R.string.sticker_navigation_name_list_beard),
                            R.drawable.list_icon_beard, 108),
                    new NavigationDrawerItem(
                            this.context
                                    .getString(R.string.sticker_navigation_name_list_candy),
                            R.drawable.list_icon_candy, 109),
                    new NavigationDrawerItem("Dog",
                            R.drawable.list_icon_cat, 110),
                    new NavigationDrawerItem("Monsters",
                            R.drawable.list_icon_monster, 111),

                    new NavigationDrawerItem(
                            this.context
                                    .getString(R.string.sticker_navigation_name_list_comic),
                            R.drawable.list_icon_comic, 112),

                    new NavigationDrawerItem(
                            this.context
                                    .getString(R.string.sticker_navigation_name_list_hat),
                            R.drawable.list_icon_hat, 113),
                    new NavigationDrawerItem(
                            this.context
                                    .getString(R.string.sticker_navigation_name_list_wig),
                            R.drawable.list_icon_wig, 114),
                    new NavigationDrawerItem(
                            this.context
                                    .getString(R.string.sticker_navigation_name_list_accesories),
                            R.drawable.list_icon_accesory, 115)};
        }
    }


    int findIndexOfStickerItem(int id) {
        for (int i = 0; i < this.navigationDrawerItemList.length; i++) {
            if (id == this.navigationDrawerItemList[i].id) {
                return i;
            }
        }
        return -1;
    }

    public class HorizontalStickerDataAdapter extends RecyclerView.Adapter<HorizontalStickerDataAdapter.MyViewHolder> {

        Context context;
        NavigationDrawerItem[] navigationDrawerItemList;
        private Typeface type;

        public HorizontalStickerDataAdapter(Context context, NavigationDrawerItem[] data) {
            this.context = context;
            this.navigationDrawerItemList = data;
            type = Typeface.createFromAsset(context.getAssets(), "roboto.regular.ttf");
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView imageViewNewBadge;
            ImageView imageView;
            TextView txtview;

            public MyViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.imageview);
                txtview = (TextView) view.findViewById(R.id.txtview);
            }
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_menu, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.txtview.setText(this.navigationDrawerItemList[position].name);
            holder.imageView.setImageResource(this.navigationDrawerItemList[position].resId);
            holder.txtview.setTypeface(type);

            holder.imageView.setOnClickListener(new OnClickListener() {
                @Override

                public void onClick(View v) {
                    if (position == 0) {
                        loadSelectedSticker(position);
                    } else if (position == 1) {
                   //     checkAdLoad = ePreferences.getPreferencesStr(EPreferences.LOAD_AD_CHRISTMAS, "");
                        if (checkAdLoad.contains("complete")) {
                            loadSelectedSticker(position);
                        } else {
                    //        confirmExit(position);
                        }
                    } else if (position == 2) {
                        loadSelectedSticker(position);
                    } else if (position == 3) {
             //           checkAdLoad = ePreferences.getPreferencesStr(EPreferences.LOAD_AD_LOVE, "");
                        if (checkAdLoad.contains("complete")) {
                            loadSelectedSticker(position);
                        } else {
                    //        confirmExit(position);
                        }
                    } else if (position == 4) {
                        loadSelectedSticker(position);
                    } else if (position == 5) {
                      //  checkAdLoad = ePreferences.getPreferencesStr(EPreferences.LOAD_AD_GOGGLES, "");
                        if (checkAdLoad.contains("complete")) {
                            loadSelectedSticker(position);
                        } else {
                  //          confirmExit(position);
                        }
                    } else if (position == 6) {
                        loadSelectedSticker(position);
                    } else if (position == 7) {
             //           checkAdLoad = ePreferences.getPreferencesStr(EPreferences.LOAD_AD_MUCHA, "");
                        if (checkAdLoad.contains("complete")) {
                            loadSelectedSticker(position);
                        } else {
                      //      confirmExit(position);
                        }
                    } else if (position == 8) {
                        loadSelectedSticker(position);
                    } else if (position == 9) {
                        loadSelectedSticker(position);
                    } else if (position == 10) {
                        loadSelectedSticker(position);
                    } else if (position == 11) {
                        loadSelectedSticker(position);
                    } else if (position == 12) {
                        loadSelectedSticker(position);
                    } else if (position == 13) {
                        loadSelectedSticker(position);
                    } else if (position == 14) {
                        loadSelectedSticker(position);
                    } else if (position == 15) {
                        loadSelectedSticker(position);
                    } else if (position == 16) {
                        loadSelectedSticker(position);
                    } else if (position == 17) {
                        loadSelectedSticker(position);
                    } else if (position == 18) {
                        loadSelectedSticker(position);
                    } else if (position == 19) {
                        loadSelectedSticker(position);
                    } else if (position == 20) {
                        loadSelectedSticker(position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return navigationDrawerItemList.length;
        }


    }

    public void loadSelectedSticker(int position) {
        if (StickerGalleryFragment.currentListIndex != position) {
            if (StickerGalleryFragment.stickerItemList == null) {
                StickerGalleryFragment.createItemList();
            }
            StickerGalleryFragment.gridAdapter
                    .setItems(StickerGalleryFragment.stickerItemList[position]);
            StickerGalleryFragment.gridView.smoothScrollToPosition(0);
            StickerGalleryFragment.gridAdapter.notifyDataSetChanged();
        }
        StickerGalleryFragment.currentListIndex = position;
        rlViewDialog.setVisibility(View.GONE);
    }


  /*  private void confirmExit(final int position) {
        dialog = new Dialog(getActivity());
      //  dialog.setCancelable(false);
        View view = getActivity().getLayoutInflater().inflate(R.layout.load_ads_dialog, null);
        dialog.setContentView(view);


        TextView tvViewAds = (TextView) view.findViewById(R.id.tvViewAds);
        tvStickerCategoryName = (TextView) view.findViewById(R.id.tvStickerCategoryName);
        ivStickerCategory = (ImageView) view.findViewById(R.id.ivStickerCategory);

        if (position == 1) {
            ivStickerCategory.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.christmas_ads));
            tvStickerCategoryName.setText("Christmas Sticker");

        } else if (position == 3) {
            ivStickerCategory.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.lovy_you_ads));
            tvStickerCategoryName.setText("Love Sticker");

        } else if (position == 5) {
            ivStickerCategory.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.goggles_ads));
            tvStickerCategoryName.setText("Goggles Sticker");

        } else if (position == 7) {
            ivStickerCategory.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.mustach_ads));
            tvStickerCategoryName.setText("Mustach Sticker");
        }

        tvViewAds.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //  videoAd();
                progressDialog_ads.show();

                dialog.dismiss();
                rlViewDialog.setVisibility(View.GONE);

                            loadSelectedSticker(position);


                  *//*  if (position == 1) {}
                if (position == 3) {


                } else if (position == 5) {


                } else if (position == 7) {

                }*//*


            }
        });

        dialog.show();
      //  rlViewDialog.setVisibility(View.VISIBLE);


    }*/

}
