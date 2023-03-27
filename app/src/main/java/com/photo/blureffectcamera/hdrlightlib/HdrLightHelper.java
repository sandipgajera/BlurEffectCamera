package com.photo.blureffectcamera.hdrlightlib;

import android.content.Context;
import android.graphics.Bitmap;

import com.photo.blureffectcamera.canvas.TextData;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HdrLightHelper {
    ArrayList<HdrParameter> hdrParameterArrayList;

    static native void applyAdjustment(Bitmap bitmap, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, int i4, float f6, float f7, float f8, int i5, int i6, int i7, int i8, float f9);

    static native void renderPlasma2(Bitmap bitmap, int i, int i2, float f, float f2, int i3, float f3, float f4, int i4);

    public HdrLightHelper(Context context) {
    }

    public void applyHdr(Bitmap bitmap, int index) {
        callHdrLight(bitmap, (HdrParameter) this.hdrParameterArrayList.get(index));
    }


    public static void callHdrLight(Bitmap bitmap, HdrParameter parameterGlobal) {
        renderPlasma2(bitmap, parameterGlobal.lowSaturation, parameterGlobal.highSaturation, parameterGlobal.power, parameterGlobal.blur, parameterGlobal.umEnabled, parameterGlobal.umPower, parameterGlobal.umBlur, parameterGlobal.umThreshold);
        Bitmap bitmap2 = bitmap;
        applyAdjustment(bitmap2, parameterGlobal.rgb, parameterGlobal.red, parameterGlobal.green, parameterGlobal.blue, parameterGlobal.contrast, parameterGlobal.brightness, parameterGlobal.temperature, parameterGlobal.minInput, parameterGlobal.gamma, parameterGlobal.maxInput, parameterGlobal.minOutput, parameterGlobal.maxOutput, parameterGlobal.applyVignette, parameterGlobal.range, parameterGlobal.slope, parameterGlobal.shade, parameterGlobal.offsetLeft, parameterGlobal.offsetTop, bitmap.getWidth() + parameterGlobal.offsetLeft, bitmap.getHeight() + parameterGlobal.offsetTop, parameterGlobal.getSaturation());
    }

    public static void calcululateCurve(MyPoint[] points, int[] result) {
        double[] sd = secondDerivative(points);
        int i = 0;
        while (true) {
            if (i < points.length - 1) {
                int j;
                int i2;
                MyPoint cur = points[i];
                MyPoint next = points[i + 1];
                int r0 = 0;
				if (i == 0 && cur.x > 0) {
                    j = 0;
                    while (true) {
                        i2 = cur.x;
                        if (j >= r0) {
                            break;
                        }
                        if (j < 256) {
                            result[j] = Math.round((float) cur.y);
                            if (result[j] < 0) {
                                result[j] = 0;
                            }
                            if (result[j] > 255) {
                                result[j] = TextData.defBgAlpha;
                            }
                        }
                        j++;
                    }
                }
                int x = cur.x;
                while (true) {
                    i2 = next.x;
                    if (x >= r0) {
                        break;
                    }
                    double t = ((double) (x - cur.x)) / ((double) (next.x - cur.x));
                    double a = 1.0d - t;
                    double b = t;
                    double h = (double) (next.x - cur.x);
                    double y = ((((double) cur.y) * a) + (((double) next.y) * b)) + (((h * h) / 6.0d) * (((((a * a) * a) - a) * sd[i]) + ((((b * b) * b) - b) * sd[i + 1])));
                    if (x < 256) {
                        result[x] = (int) Math.round(y);
                        if (result[x] < 0) {
                            result[x] = 0;
                        }
                        if (result[x] > 255) {
                            result[x] = TextData.defBgAlpha;
                        }
                    }
                    x++;
                }
                if (i == points.length - 2) {
                    i2 = next.x;
                    if (r0 < 255) {
                        for (j = next.x; j < 256; j++) {
                            if (j < 256) {
                                result[j] = Math.round((float) next.y);
                                if (result[j] < 0) {
                                    result[j] = 0;
                                }
                                if (result[j] > 255) {
                                    result[j] = TextData.defBgAlpha;
                                }
                            }
                        }
                    }
                }
                i++;
            } else {
                return;
            }
        }
    }

    public static double[] secondDerivative(MyPoint... P) {
        int i;
        int n = P.length;
        double[][] matrix = (double[][]) Array.newInstance(Double.TYPE, new int[]{n, 3});
        double[] result = new double[n];
        matrix[0][1] = 1.0d;
        for (i = 1; i < n - 1; i++) {
            matrix[i][0] = ((double) (P[i].x - P[i - 1].x)) / 6.0d;
            matrix[i][1] = ((double) (P[i + 1].x - P[i - 1].x)) / 3.0d;
            matrix[i][2] = ((double) (P[i + 1].x - P[i].x)) / 6.0d;
            result[i] = (((double) (P[i + 1].y - P[i].y)) / ((double) (P[i + 1].x - P[i].x))) - (((double) (P[i].y - P[i - 1].y)) / ((double) (P[i].x - P[i - 1].x)));
        }
        matrix[n - 1][1] = 1.0d;
        for (i = 1; i < n; i++) {
            double k = matrix[i][0] / matrix[i - 1][1];
            double[] dArr = matrix[i];
            dArr[1] = dArr[1] - (matrix[i - 1][2] * k);
            matrix[i][0] = 0.0d;
            result[i] = result[i] - (result[i - 1] * k);
        }
        for (i = n - 2; i >= 0; i--) {
            double k = matrix[i][2] / matrix[i + 1][1];
            double[] dArr = matrix[i];
            dArr[1] = dArr[1] - (matrix[i + 1][0] * k);
            matrix[i][2] = 0.0d;
            result[i] = result[i] - (result[i + 1] * k);
        }
        double[] y2 = new double[n];
        for (i = 0; i < n; i++) {
            y2[i] = result[i] / matrix[i][1];
        }
        return y2;
    }
}
