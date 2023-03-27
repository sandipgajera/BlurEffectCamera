package com.photo.blureffectcamera.colorPicker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.photo.blureffectcamera.R;


public class RainbowPickerDialog extends Dialog {
    OnItemSelected listener;

    class ItemClickListener implements OnItemClickListener {
        final GridView val$gridViewColors;

        ItemClickListener(GridView gridView) {
            this.val$gridViewColors = gridView;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            RainbowPickerDialog.this.dismiss();
            RainbowPickerDialog.this.listener.itemSelected(((Integer) this.val$gridViewColors.getItemAtPosition(position)).intValue());
        }
    }

    public RainbowPickerDialog(Context context) {
        super(context);
        setTitle("Color Picker");
    }

    public void setListener(OnItemSelected l) {
        this.listener = l;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_picker);
        GridView gridViewColors = (GridView) findViewById(R.id.gridViewColors);
        gridViewColors.setAdapter(new RainbowPickerAdapter(getContext()));
        gridViewColors.setOnItemClickListener(new ItemClickListener(gridViewColors));
    }
}
