package org.wysaid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import java.nio.ByteBuffer;
import javax.microedition.khronos.opengles.GL10;

public class TrackingCameraGLSurfaceView extends CameraGLSurfaceViewWithBuffer {
    protected TrackingProc mTrackingProc;

    public interface TrackingProc {
        void processTracking(ByteBuffer byteBuffer);

        void release();

        void render(TrackingCameraGLSurfaceView trackingCameraGLSurfaceView);

        void resize(int i, int i2);

        boolean setup(int i, int i2);
    }

    public TrackingCameraGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TrackingProc getTrackingProc() {
        return this.mTrackingProc;
    }

    public boolean setTrackingProc(TrackingProc trackingProc) {
        TrackingProc trackingProc2 = this.mTrackingProc;
        if (trackingProc2 != null) {
            trackingProc2.release();
            this.mTrackingProc = null;
        }
        if (trackingProc == null) {
            return true;
        }
        if (!trackingProc.setup(this.mRecordWidth, this.mRecordHeight)) {
            Log.e("libCGE_java", "setup proc failed!");
            trackingProc.release();
            return false;
        }
        this.mTrackingProc = trackingProc;
        return true;
    }


    public void onRelease() {
        super.onRelease();
        TrackingProc trackingProc = this.mTrackingProc;
        if (trackingProc != null) {
            trackingProc.release();
            this.mTrackingProc = null;
        }
    }

    public void onDrawFrame(GL10 gl10) {
        if (this.mSurfaceTexture != null && cameraInstance().isPreviewing()) {
            if (this.mBufferUpdated && this.mTrackingProc != null) {
                synchronized (this.mBufferUpdateLock) {
                    this.mTrackingProc.processTracking(this.mBufferY);
                }
            }
            TrackingProc trackingProc = this.mTrackingProc;
            if (trackingProc == null) {
                super.onDrawFrame(gl10);
            } else {
                trackingProc.render(this);
            }
        }
    }
}
