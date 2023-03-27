package com.photo.blureffectcamera.tiltshift;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.photo.blureffectcamera.R;


@SuppressLint("NewApi")
public class TiltActivity extends FragmentActivity {
	TiltFragment titlFragment;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		getWindow().addFlags(
				AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
		Log.d("TILT", "Tilts shift call :::::");
		setContentView(R.layout.activity_tilt);


		FragmentManager fm = getSupportFragmentManager();
		this.titlFragment = (TiltFragment) fm
				.findFragmentByTag("my_tilt_fragment");
		if (this.titlFragment == null) {
			this.titlFragment = new TiltFragment();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.tilt_fragment_container, this.titlFragment,
					"my_tilt_fragment");
			ft.commit();
			return;
		}
		getSupportFragmentManager().beginTransaction().show(this.titlFragment)
				.commit();
	}

}
