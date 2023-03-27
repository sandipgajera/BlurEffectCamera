package com.photo.blureffectcamera.tiltshift;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import androidx.fragment.app.Fragment;

import com.photo.blureffectcamera.R;

public class TiltFragment extends Fragment {
    public Bitmap bitmap;
    public Bitmap bitmapBlur;
    OnClickListener onClickListener;
    TiltContext tiltContext;
    TiltListener tiltListener;
    Button[] tiltModeButtonArray;
    TiltView tiltView;

    /* renamed from: com.lyrebirdstudio.tiltshift.TiltFragment.1 */
    class C07801 implements OnClickListener {
        C07801() {
        }

        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.tilt_button_none) {
                TiltFragment.this.tiltView.tiltHelper.setTiltMode(TiltContext.TiltMode.NONE);
                TiltFragment.this.setTiltButtonBg(TiltContext.TiltMode.NONE);
            } else if (id == R.id.tilt_button_radial) {
                TiltFragment.this.tiltView.tiltHelper.setTiltMode(TiltContext.TiltMode.RADIAL);
                TiltFragment.this.setTiltButtonBg(TiltContext.TiltMode.RADIAL);
            } else if (id == R.id.tilt_button_linear) {
                TiltFragment.this.tiltView.tiltHelper.setTiltMode(TiltContext.TiltMode.LINEAR);
                TiltFragment.this.setTiltButtonBg(TiltContext.TiltMode.LINEAR);
            } else if (id == R.id.tilt_ok) {
                TiltFragment.this.tiltListener.onTiltOk(TiltFragment.this.tiltView.tiltHelper.currentTiltContext);
            } else if (id == R.id.tilt_cancel && TiltFragment.this.tiltListener != null) {
                TiltFragment.this.tiltListener.onTiltCancel();
            }
        }
    }

    public interface TiltListener {
        void onTiltCancel();

        void onTiltOk(TiltContext tiltContext);
    }

    public TiltFragment() {
        this.tiltModeButtonArray = new Button[3];
        this.onClickListener = new C07801();
    }

    public void setTiltListener(TiltListener l) {
        this.tiltListener = l;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup mainLayout = (ViewGroup) inflater.inflate(R.layout.fragment_tilt, container, false);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        this.tiltView = new TiltView(getActivity(), this.bitmap, this.bitmapBlur, display.getWidth(), display.getHeight(), this.tiltContext);
        LayoutParams lp = new LayoutParams(-2, -2);
        lp.gravity = 17;
        ((FrameLayout) mainLayout.findViewById(R.id.tilt_view_container)).addView(this.tiltView, lp);
        Button buttonNone = (Button) mainLayout.findViewById(R.id.tilt_button_none);
        buttonNone.setOnClickListener(this.onClickListener);
        Button buttonRadial = (Button) mainLayout.findViewById(R.id.tilt_button_radial);
        buttonRadial.setOnClickListener(this.onClickListener);
        Button buttonLinear = (Button) mainLayout.findViewById(R.id.tilt_button_linear);
        buttonLinear.setOnClickListener(this.onClickListener);
        this.tiltModeButtonArray[0] = buttonNone;
        this.tiltModeButtonArray[1] = buttonRadial;
        this.tiltModeButtonArray[2] = buttonLinear;
        if (this.tiltContext != null) {
            setTiltButtonBg(this.tiltContext.mode);
        } else if (!(this.tiltView == null || this.tiltView.tiltHelper == null || this.tiltView.tiltHelper.currentTiltContext == null)) {
            setTiltButtonBg(this.tiltView.tiltHelper.currentTiltContext.mode);
        }
        mainLayout.findViewById(R.id.tilt_ok).setOnClickListener(this.onClickListener);
        mainLayout.findViewById(R.id.tilt_cancel).setOnClickListener(this.onClickListener);
        return mainLayout;
    }

    private void setTiltButtonBg(TiltContext.TiltMode tiltMode) {
        for (Button backgroundResource : this.tiltModeButtonArray) {
            backgroundResource.setBackgroundResource(R.drawable.selector_btn_tilt_mode);
        }
        this.tiltModeButtonArray[tiltMode.getValue()].setBackgroundResource(R.color.lib_footer_button_color_pressed);
    }

    public void setBitmaps(Bitmap btm, Bitmap blur) {
        this.bitmap = btm;
        this.bitmapBlur = blur;
    }

    public void setTiltContext(TiltContext tiltContext) {
        this.tiltContext = tiltContext;
    }

    public void backPressed() {
        if (this.tiltListener != null) {
            this.tiltListener.onTiltCancel();
        }
    }
}
