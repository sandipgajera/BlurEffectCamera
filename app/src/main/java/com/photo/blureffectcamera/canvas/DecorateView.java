package com.photo.blureffectcamera.canvas;


import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class DecorateView extends View {
    public static final String TAG = "DecorateView";
    protected OnDecorateViewTouchUp onDecorateViewTouchUpListener;

    /* renamed from: com.lyrebirdstudio.canvastext.DecorateView.1 */
    class C06851 implements OnClickListener, DialogInterface.OnClickListener {
        C06851() {
        }

        public void onClick(DialogInterface dialog, int id) {
        }

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}
    }

    /* renamed from: com.lyrebirdstudio.canvastext.DecorateView.2 */
    class C06862 implements OnClickListener, DialogInterface.OnClickListener {
        final /* synthetic */ View val$view;

        C06862(View view) {
            this.val$view = view;
        }

        public void onClick(DialogInterface dialog, int id) {
            ViewGroup parent = (ViewGroup) this.val$view.getParent();
            if (parent != null) {
                parent.removeView(this.val$view);
                DecorateView.this.onDestroy();
            }
        }

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}
    }

    public interface OnDecorateViewTouchUp {
        void onTouchUp(BaseData baseData);
    }

    public DecorateView(Context context) {
        super(context);
    }

    public DecorateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DecorateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseData getData() {
        return null;
    }

    public void setMatrix(MyMatrix matrix) {
    }

    public void setOnDecorateViewTouchUp(OnDecorateViewTouchUp l) {
        this.onDecorateViewTouchUpListener = l;
    }

    public boolean isDecorateViewSelected() {
        return false;
    }

    public void setDecorateViewSelected(boolean selection) {
    }

    public void onDestroy() {
    }

    public void createDeleteDialog(Context context, View view) {
        Builder builder = new Builder(context);
        builder.setMessage("Do you want to delete image?").setCancelable(true).setPositiveButton("Yes", new C06862(view)).setNegativeButton("No", new C06851());
        builder.create().show();
    }

    public float containsScore(float x, float y) {
        return -2.0f;
    }

    public boolean isOnRectCheck(float x, float y) {
        return false;
    }
}
