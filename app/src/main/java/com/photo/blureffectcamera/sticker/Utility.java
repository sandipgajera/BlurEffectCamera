package com.photo.blureffectcamera.sticker;

import android.util.Log;

import androidx.core.view.ViewCompat;

import com.photo.blureffectcamera.R;

import java.util.concurrent.atomic.AtomicInteger;

public class Utility {
    private static final AtomicInteger sNextGeneratedId;
    public static final int[][] stickerResIdList;
    // public static final String[][] stickerUrlList;

    static {
        sNextGeneratedId = new AtomicInteger(1);
        // stickerUrlList = new String[][]{createEmojiUrlList(),
        // createAnimalUrlList()};
        int[][] r0 = new int[18][];

        r0[0] = new int[]{R.drawable.emotion_01, R.drawable.emotion_02};


        r0[1] = new int[]{R.drawable.cristmas_01, R.drawable.cristmas_02};

        r0[2] = new int[]{R.drawable.snap_flower_crown_0,
                R.drawable.snap_flower_crown_1};

        r0[3] = new int[]{R.drawable.love_01, R.drawable.love_02};

        r0[4] = new int[]{R.drawable.snap_cat_ear_left_01,
                R.drawable.snap_cat_ear_right_01};



        r0[5] = new int[]{R.drawable.glasses_01, R.drawable.glasses_02};

        r0[6] = new int[]{R.drawable.love_bird_01, R.drawable.love_bird_02};

        r0[7] = new int[]{R.drawable.beard_01, R.drawable.beard_02};


        r0[8] = new int[]{R.drawable.candy_01, R.drawable.candy_02};


        r0[9] = new int[]{R.drawable.snap_dog_muzzle,
                R.drawable.snap_dog_tongue};

        r0[10] = new int[]{R.drawable.monster_01, R.drawable.monster_02};

        r0[11] = new int[]{R.drawable.comic_01, R.drawable.comic_02};

        r0[12] = new int[]{R.drawable.hat_01, R.drawable.hat_02};
        r0[13] = new int[]{R.drawable.wig_01, R.drawable.wig_02};
        r0[14] = new int[]{R.drawable.accessory_01, R.drawable.accessory_02};
        r0[15] = new int[]{R.drawable.accessory_11};
        r0[16] = new int[]{R.drawable.accessory_01, R.drawable.accessory_02};
        r0[17] = new int[]{R.drawable.cristmas_01, R.drawable.cristmas_02};

        stickerResIdList = r0;
        Log.i("stickerResIdSize", "" + stickerResIdList.length);
    }

    public static int generateViewId() {
        int result;
        int newValue;
        do {
            result = sNextGeneratedId.get();
            newValue = result + 1;
            if (newValue > ViewCompat.MEASURED_SIZE_MASK) {
                newValue = 1;
            }
        } while (!sNextGeneratedId.compareAndSet(result, newValue));
        return result;
    }

}
