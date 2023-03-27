package com.photo.blureffectcamera.tiltshift;

import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.core.view.MotionEventCompat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TiltContext implements Parcelable, Serializable {
    public static final Creator<TiltContext> CREATOR;
    private static final String TAG;
    private static final long serialVersionUID = -5455273508176976785L;
    transient Shader gradient;
    transient Shader gradientTouch;
    int f535h;
    MyMatrix matrix;
    public TiltMode mode;
    int f536w;

    /* renamed from: com.lyrebirdstudio.tiltshift.TiltContext.1 */
    static class C07781 implements Creator<TiltContext> {
        C07781() {
        }

        public TiltContext createFromParcel(Parcel in) {
            return new TiltContext(in);
        }

        public TiltContext[] newArray(int size) {
            return new TiltContext[size];
        }
    }

    public enum TiltMode implements Parcelable {
        NONE(0),
        RADIAL(1),
        LINEAR(2);
        
        public static final Creator<TiltMode> CREATOR;
        private int f534v;

        /* renamed from: com.lyrebirdstudio.tiltshift.TiltContext.TiltMode.1 */
        static class C07791 implements Creator<TiltMode> {
            C07791() {
            }

            public TiltMode createFromParcel(Parcel source) {
                TiltMode tiltMode = TiltMode.values()[source.readInt()];
                tiltMode.setValue(source.readInt());
                return tiltMode;
            }

            public TiltMode[] newArray(int size) {
                return new TiltMode[size];
            }
        }

        static {
            CREATOR = new C07791();
        }

        private TiltMode(int value) {
            this.f534v = value;
        }

        public void setValue(int value) {
            this.f534v = value;
        }

        public int getValue() {
            return this.f534v;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ordinal());
            dest.writeInt(this.f534v);
        }
    }

    static {
        TAG = TiltContext.class.getSimpleName();
        CREATOR = new C07781();
    }

    public TiltContext(TiltMode mode, int w, int h) {
        this.mode = mode;
        this.matrix = new MyMatrix(matrix);
        this.matrix.reset();
        this.f536w = w;
        this.f535h = h;
        if (mode == TiltMode.LINEAR) {
            this.gradient = createLinearGradient(h);
            this.gradientTouch = createLinearGradientPaint(h);
        } else if (mode == TiltMode.RADIAL) {
            this.gradient = createRadialGradient(w, h);
            this.gradientTouch = createRadialGradientPaint(w, h);
        } else {
            this.gradient = null;
            this.gradientTouch = null;
        }
    }

    public TiltContext(TiltContext tiltContext) {
        this.gradient = tiltContext.gradient;
        this.gradientTouch = tiltContext.gradientTouch;
        this.mode = tiltContext.mode;
        this.matrix = new MyMatrix(tiltContext.matrix);
        this.f536w = tiltContext.f536w;
        this.f535h = tiltContext.f535h;
    }

    public void setLocalMatrix() {
        if (this.gradient != null) {
            this.gradient.setLocalMatrix(this.matrix);
        }
        if (this.gradientTouch != null) {
            this.gradientTouch.setLocalMatrix(this.matrix);
        }
    }

    public MyMatrix getMatrix() {
        return this.matrix;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.f536w);
        dest.writeInt(this.f535h);
        dest.writeParcelable(this.matrix, flags);
        dest.writeParcelable(this.mode, flags);
    }

    public TiltContext(Parcel in) {
        this.f536w = in.readInt();
        this.f535h = in.readInt();
        this.matrix = (MyMatrix) in.readParcelable(MyMatrix.class.getClassLoader());
        this.mode = (TiltMode) in.readParcelable(TiltMode.class.getClassLoader());
        if (this.mode == TiltMode.LINEAR) {
            this.gradient = createLinearGradient(this.f535h);
            this.gradientTouch = createLinearGradientPaint(this.f535h);
        } else if (this.mode == TiltMode.RADIAL) {
            this.gradient = createRadialGradient(this.f536w, this.f535h);
            this.gradientTouch = createRadialGradientPaint(this.f536w, this.f535h);
        }
        setLocalMatrix();
    }

    public static LinearGradient createLinearGradient(int h) {
        return new LinearGradient(0.0f, 0.0f, 0.0f, (float) h, new int[]{-16711936, -16711936, -1291780352, MotionEventCompat.ACTION_POINTER_INDEX_MASK, MotionEventCompat.ACTION_POINTER_INDEX_MASK, -1291780352, -16711936, -16711936}, new float[]{0.0f, 0.055555556f, 0.3148148f, 0.4351852f, 0.5648148f, 0.6851852f, 0.9444444f, 1.0f}, TileMode.CLAMP);
    }

    public static LinearGradient createLinearGradientPaint(int h) {
        return new LinearGradient(0.0f, 0.0f, 0.0f, (float) h, new int[]{-16711936, -872349952, MotionEventCompat.ACTION_POINTER_INDEX_MASK, MotionEventCompat.ACTION_POINTER_INDEX_MASK, -872349952, -16711936}, new float[]{0.0f, 0.3148148f, 0.4351852f, 0.5648148f, 0.6851852f, 1.0f}, TileMode.CLAMP);
    }

    public static RadialGradient createRadialGradient(int w, int h) {
        if (w > 0 && h > 0) {
            float radius = (float) Math.sqrt((double) (((float) (w * h)) / 16.0f));
        }
        return new RadialGradient((float) (w / 2), (float) (h / 2), ((float) Math.min(w, h)) * 0.555f, new int[]{MotionEventCompat.ACTION_POINTER_INDEX_MASK, MotionEventCompat.ACTION_POINTER_INDEX_MASK, -2147418368, -16711936}, new float[]{0.0f, 0.10309278f, 0.30927834f, 1.0f}, TileMode.CLAMP);
    }

    public static RadialGradient createRadialGradientPaint(int w, int h) {
        float radius = 250.0f;
        if (w > 0 && h > 0) {
            radius = (float) Math.sqrt((double) (((float) (w * h)) * 0.077f));
        }
        return new RadialGradient((float) (w / 2), (float) (h / 2), radius, new int[]{MotionEventCompat.ACTION_POINTER_INDEX_MASK, MotionEventCompat.ACTION_POINTER_INDEX_MASK, -16711936}, new float[]{0.0f, 0.34f, 1.0f}, TileMode.CLAMP);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(this.f536w);
        oos.writeInt(this.f535h);
        oos.writeObject(this.matrix);
        oos.writeObject(this.mode);
    }

    private void readObject(ObjectInputStream ois) throws Exception, ClassNotFoundException {
        ois.defaultReadObject();
        this.f536w = ois.readInt();
        this.f535h = ois.readInt();
        this.matrix = (MyMatrix) ois.readObject();
        this.mode = (TiltMode) ois.readObject();
        if (this.mode == TiltMode.LINEAR) {
            this.gradient = createLinearGradient(this.f535h);
            this.gradientTouch = createLinearGradientPaint(this.f535h);
        } else if (this.mode == TiltMode.RADIAL) {
            this.gradient = createRadialGradient(this.f536w, this.f535h);
            this.gradientTouch = createRadialGradientPaint(this.f536w, this.f535h);
        }
        setLocalMatrix();
    }
}
