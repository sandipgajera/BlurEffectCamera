package com.photo.blureffectcamera.lyrebirdlib;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.photo.blureffectcamera.R;


public class FullEffectFragment extends Fragment {
    private static final String TAG;
    Activity activity;
    int bitmapHeight;
    FullBitmapReady bitmapReadyListener;
    int bitmapWidth;
    Context context;
    Bitmap currentBitmap;
    EffectFragment effectFragment;
    View header;
    ImageView imageView;
    Bitmap sourceBitmap;

    public interface FullBitmapReady {
        void onBitmapReady(Bitmap bitmap, Parameter parameter);

        void onCancel();
    }

    public interface HideHeaderListener {
        void hide(boolean z);
    }

    class C13311 implements EffectFragment.BitmapReady {
        C13311() {
        }

        public void onBitmapReady(Bitmap bitmap) {
            FullEffectFragment.this.imageView.setImageBitmap(bitmap);
            FullEffectFragment.this.currentBitmap = bitmap;
        }
    }

    class C13322 implements HideHeaderListener {
        C13322() {
        }

        public void hide(boolean show) {
            if (show) {
                FullEffectFragment.this.header.setVisibility(View.VISIBLE);
            } else {
                FullEffectFragment.this.header.setVisibility(View.INVISIBLE);
            }
        }
    }

    static {
        TAG = FullEffectFragment.class.getName();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.full_fragment_effect, container, false);
        this.imageView = (ImageView) fragmentView.findViewById(R.id.imageView1);
        this.header = fragmentView.findViewById(R.id.full_fragment_apply_filter_header);
        addFragment(true);
        return fragmentView;
    }

    public void onAttach(Activity act) {
        super.onAttach(act);
        this.context = getActivity();
        this.activity = getActivity();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.imageView.setImageBitmap(this.sourceBitmap);
    }

    public void setBitmapWithParameter(Bitmap btm, Parameter parameter) {
        this.sourceBitmap = btm;
        if (this.imageView != null) {
            this.imageView.setImageBitmap(btm);
        }
        if (this.effectFragment != null) {
            if (parameter == null || this.effectFragment.parameterGlobal == null || parameter.getId() != this.effectFragment.parameterGlobal.id) {
                this.effectFragment.setBitmapAndResetBlur(this.sourceBitmap);
            } else {
                this.effectFragment.setBitmap(this.sourceBitmap);
            }
            if (parameter != null) {
                this.effectFragment.setParameters(parameter);
            }
        }
    }

    void addFragment(boolean isPro) {
        if (this.effectFragment == null) {
            this.effectFragment = (EffectFragment) getChildFragmentManager().findFragmentByTag("MY_FRAGMENT");
            if (this.effectFragment == null) {
                this.effectFragment = new EffectFragment();
                this.effectFragment.isAppPro(isPro);
                this.effectFragment.setArguments(getArguments());
                getChildFragmentManager().beginTransaction().add(R.id.fragment_container, this.effectFragment, "MY_FRAGMENT").commit();
            } else {
                this.effectFragment.isAppPro(isPro);
            }
            getChildFragmentManager().beginTransaction().show(this.effectFragment).commit();
            this.effectFragment.setBitmapReadyListener(new C13311());
            this.effectFragment.setHideHeaderListener(new C13322());
        }
    }

    public void setFullBitmapReadyListener(FullBitmapReady listener) {
        this.bitmapReadyListener = listener;
    }

    public void myClickHandler(View view) {
        if (view.getId() == R.id.button_apply_filter) {


            if (this.currentBitmap == null) {
                this.effectFragment.resetParametersWithoutChange();
                this.bitmapReadyListener.onCancel();
                return;
            }
            Parameter parameter = new Parameter(this.effectFragment.parameterGlobal);
            this.effectFragment.resetParametersWithoutChange();

            this.bitmapReadyListener.onBitmapReady(this.currentBitmap, parameter);
        } else if (view.getId() == R.id.button_cancel_filter) {
            this.effectFragment.resetParametersWithoutChange();
            this.bitmapReadyListener.onCancel();
        } else {
            if (this.header == null) {
                this.header = getView().findViewById(R.id.full_fragment_apply_filter_header);
            }
            int id = view.getId();
            if (id == R.id.button_lib_cancel || id == R.id.button_lib_ok || id == R.id.tilt_ok || id == R.id.tilt_cancel || id == R.id.button_auto_set_parameters || id == R.id.button_filter_reset) {
                this.header.setVisibility(View.VISIBLE);
            } else {
                this.header.setVisibility(View.INVISIBLE);
            }
            this.effectFragment.myClickHandler(id);
        }
    }

    public boolean onBackPressed() {
        boolean handled = false;
        if (this.effectFragment != null && this.effectFragment.isVisible()) {
            handled = this.effectFragment.backPressed();
        }
        if (handled) {
            this.header.setVisibility(View.VISIBLE);
        }
        return handled;
    }

    public void onSaveInstanceState(Bundle outState) {
    }

//    public void onDetach() {
//        super.onDetach();
//        try {
//            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
//            childFragmentManager.setAccessible(true);
//            childFragmentManager.set(this, null);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e2) {
//            throw new RuntimeException(e2);
//        }
//    }
}