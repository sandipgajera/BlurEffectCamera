package com.photo.blureffectcamera.collagelib;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.photo.blureffectcamera.MainActivity;
import com.photo.blureffectcamera.gallerylib.GalleryFragment;
import com.photo.blureffectcamera.photoactivity.PhotoActivity;


public class CollageHelper {

    static class GallaryManager implements GalleryFragment.GalleryListener {
        final FragmentActivity fragmentActivity;
        final GalleryFragment galleryFragment;

        GallaryManager(FragmentActivity fragmentActivity, GalleryFragment galleryFragment) {
            this.fragmentActivity = fragmentActivity;
            this.galleryFragment = galleryFragment;
        }

        public void onGalleryOkSingleImage(long ImageIdList, int orientationList, boolean isScrapBook,
                                           boolean isShape) {
        }

        public void onGalleryOkImageArrayRemoveFragment(long[] ImageIdList, int[] orientationList, boolean isScrapBook,
                                                        boolean isShape) {
        }

        public void onGalleryOkImageArray(final long[] ImageIdList, final int[] orientationList, final boolean isScrapBook,
                                          final boolean isShape) {


                Intent intent = new Intent(this.fragmentActivity, ShapeCollageActivity.class);
                intent.putExtra(Utility.COLLAGE_SHAPE, true);
                intent.putExtra("photo_id_list", ImageIdList);
                intent.putExtra("photo_orientation_list", orientationList);
                intent.putExtra("is_scrap_book", isScrapBook);
                intent.putExtra("is_shape", isShape);
                this.fragmentActivity.startActivity(intent);

        }

        public void onGalleryCancel() {
            this.fragmentActivity.getSupportFragmentManager().beginTransaction().hide(this.galleryFragment)
                    .commitAllowingStateLoss();
        }
    }

    public static GalleryFragment getGalleryFragment(PhotoActivity activity) {
        return (GalleryFragment) activity.getSupportFragmentManager().findFragmentByTag("myFragmentTag");
    }

    public static GalleryFragment addGalleryFragment(PhotoActivity activity, int gallery_fragment_container) {
        FragmentManager fm = activity.getSupportFragmentManager();
        GalleryFragment galleryFragment = (GalleryFragment) fm.findFragmentByTag("myFragmentTag");
        if (galleryFragment == null) {
            galleryFragment = new GalleryFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(gallery_fragment_container, galleryFragment, "myFragmentTag");
            ft.commitAllowingStateLoss();
            galleryFragment.setGalleryListener(createGalleryListener(activity, galleryFragment));
            activity.findViewById(gallery_fragment_container).bringToFront();
            return galleryFragment;
        }
        activity.getSupportFragmentManager().beginTransaction().show(galleryFragment).commitAllowingStateLoss();
        return galleryFragment;
    }

    public static GalleryFragment.GalleryListener createGalleryListener(FragmentActivity activity, GalleryFragment galleryFragment) {
        return new GallaryManager(activity, galleryFragment);
    }
}
