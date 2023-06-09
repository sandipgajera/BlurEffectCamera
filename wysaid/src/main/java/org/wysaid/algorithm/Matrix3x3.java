package org.wysaid.algorithm;

public class Matrix3x3 {
    public float[] data;

    Matrix3x3() {
        this.data = new float[9];
    }

    public static Matrix3x3 makeZRotation(float f) {
        double d = (double) f;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        return new Matrix3x3(new float[]{cos, sin, 0.0f, -sin, cos, 0.0f, 0.0f, 0.0f, 1.0f});
    }

    public static Matrix3x3 makeIdentity() {
        return new Matrix3x3(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f});
    }

    public static Matrix3x3 makeRotation(float f, float f2, float f3, float f4) {
        float normalizeScaling = AlgorithmUtil.getNormalizeScaling(f2, f3, f4);
        float f5 = f2 * normalizeScaling;
        float f6 = f3 * normalizeScaling;
        float f7 = f4 * normalizeScaling;
        double d = (double) f;
        float cos = (float) Math.cos(d);
        float f8 = 1.0f - cos;
        float sin = (float) Math.sin(d);
        float f9 = f8 * f5;
        float f10 = f9 * f6;
        float f11 = f7 * sin;
        float f12 = f9 * f7;
        float f13 = f6 * sin;
        float f14 = f8 * f6;
        float f15 = f14 * f7;
        float f16 = f5 * sin;
        return new Matrix3x3(new float[]{(f9 * f5) + cos, f10 + f11, f12 - f13, f10 - f11, (f6 * f14) + cos, f15 + f16, f12 + f13, f15 - f16, cos + (f8 * f7 * f7)});
    }

    Matrix3x3(float[] fArr) {
        this.data = fArr;
    }

    public static Matrix3x3 makeYRotation(float f) {
        double d = (double) f;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        return new Matrix3x3(new float[]{cos, 0.0f, -sin, 0.0f, 1.0f, 0.0f, sin, 0.0f, cos});
    }

    protected static float[] _mul(float[] fArr, float[] fArr2) {
        return new float[]{(fArr[0] * fArr2[0]) + (fArr[3] * fArr2[1]) + (fArr[6] * fArr2[2]), (fArr[1] * fArr2[0]) + (fArr[4] * fArr2[1]) + (fArr[7] * fArr2[2]), (fArr[2] * fArr2[0]) + (fArr[5] * fArr2[1]) + (fArr[8] * fArr2[2]), (fArr[0] * fArr2[3]) + (fArr[3] * fArr2[4]) + (fArr[6] * fArr2[5]), (fArr[1] * fArr2[3]) + (fArr[4] * fArr2[4]) + (fArr[7] * fArr2[5]), (fArr[2] * fArr2[3]) + (fArr[5] * fArr2[4]) + (fArr[8] * fArr2[5]), (fArr[0] * fArr2[6]) + (fArr[3] * fArr2[7]) + (fArr[6] * fArr2[8]), (fArr[1] * fArr2[6]) + (fArr[4] * fArr2[7]) + (fArr[7] * fArr2[8]), (fArr[2] * fArr2[6]) + (fArr[5] * fArr2[7]) + (fArr[8] * fArr2[8])};
    }

    public static Matrix3x3 makeXRotation(float f) {
        double d = (double) f;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        return new Matrix3x3(new float[]{1.0f, 0.0f, 0.0f, 0.0f, cos, sin, 0.0f, -sin, cos});
    }

    public Matrix3x3 multiplyBy(Matrix3x3 matrix3x3) {
        this.data = _mul(this.data, matrix3x3.data);
        return this;
    }

    public Matrix3x3 clone() {
        return new Matrix3x3((float[]) this.data.clone());
    }

    public Matrix3x3 multiply(Matrix3x3 matrix3x3) {
        return new Matrix3x3(_mul(this.data, matrix3x3.data));
    }
}
