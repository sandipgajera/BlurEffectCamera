package org.wysaid.algorithm;

public class Matrix4x4 {
    public float[] data;

    public static Matrix4x4 makeTranslation(float f, float f2, float f3) {
        return new Matrix4x4(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, f, f2, f3, 1.0f});
    }

    Matrix4x4() {
        this.data = new float[16];
    }

    public static Matrix4x4 makeRotation(float f, float f2, float f3, float f4) {
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
        return new Matrix4x4(new float[]{(f9 * f5) + cos, f10 + f11, f12 - f13, 0.0f, f10 - f11, (f6 * f14) + cos, f15 + f16, 0.0f, f12 + f13, f15 - f16, cos + (f8 * f7 * f7), 0.0f, 0.0f, 0.0f, 0.0f, 1.0f});
    }

    public static Matrix4x4 makeXRotation(float f) {
        double d = (double) f;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        return new Matrix4x4(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, cos, sin, 0.0f, 0.0f, -sin, cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f});
    }

    public static Matrix4x4 makeZRotation(float f) {
        double d = (double) f;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        return new Matrix4x4(new float[]{cos, sin, 0.0f, 0.0f, -sin, cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f});
    }

    Matrix4x4(float[] fArr) {
        this.data = fArr;
    }

    public static Matrix4x4 makeYRotation(float f) {
        double d = (double) f;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        return new Matrix4x4(new float[]{cos, 0.0f, -sin, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, sin, 0.0f, cos, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f});
    }

    public static Matrix4x4 makeIdentity() {
        return new Matrix4x4(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f});
    }

    public static Matrix4x4 makeScaling(float f, float f2, float f3) {
        return new Matrix4x4(new float[]{f, 0.0f, 0.0f, 0.0f, 0.0f, f2, 0.0f, 0.0f, 0.0f, 0.0f, f3, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f});
    }

    public static Matrix4x4 makePerspective(float f, float f2, float f3, float f4) {
        float tan = 1.0f / ((float) Math.tan((double) (f / 2.0f)));
        float f5 = f3 - f4;
        return new Matrix4x4(new float[]{tan / f2, 0.0f, 0.0f, 0.0f, 0.0f, tan, 0.0f, 0.0f, 0.0f, 0.0f, (f4 + f3) / f5, -1.0f, 0.0f, 0.0f, ((f4 * 2.0f) * f3) / f5, 0.0f});
    }

    protected static float[] _mul(float[] fArr, float[] fArr2) {
        return new float[]{(fArr[0] * fArr2[0]) + (fArr[4] * fArr2[1]) + (fArr[8] * fArr2[2]) + (fArr[12] * fArr2[3]), (fArr[1] * fArr2[0]) + (fArr[5] * fArr2[1]) + (fArr[9] * fArr2[2]) + (fArr[13] * fArr2[3]), (fArr[2] * fArr2[0]) + (fArr[6] * fArr2[1]) + (fArr[10] * fArr2[2]) + (fArr[14] * fArr2[3]), (fArr[3] * fArr2[0]) + (fArr[7] * fArr2[1]) + (fArr[11] * fArr2[2]) + (fArr[15] * fArr2[3]), (fArr[0] * fArr2[4]) + (fArr[4] * fArr2[5]) + (fArr[8] * fArr2[6]) + (fArr[12] * fArr2[7]), (fArr[1] * fArr2[4]) + (fArr[5] * fArr2[5]) + (fArr[9] * fArr2[6]) + (fArr[13] * fArr2[7]), (fArr[2] * fArr2[4]) + (fArr[6] * fArr2[5]) + (fArr[10] * fArr2[6]) + (fArr[14] * fArr2[7]), (fArr[3] * fArr2[4]) + (fArr[7] * fArr2[5]) + (fArr[11] * fArr2[6]) + (fArr[15] * fArr2[7]), (fArr[0] * fArr2[8]) + (fArr[4] * fArr2[9]) + (fArr[8] * fArr2[10]) + (fArr[12] * fArr2[11]), (fArr[1] * fArr2[8]) + (fArr[5] * fArr2[9]) + (fArr[9] * fArr2[10]) + (fArr[13] * fArr2[11]), (fArr[2] * fArr2[8]) + (fArr[6] * fArr2[9]) + (fArr[10] * fArr2[10]) + (fArr[14] * fArr2[11]), (fArr[3] * fArr2[8]) + (fArr[7] * fArr2[9]) + (fArr[11] * fArr2[10]) + (fArr[15] * fArr2[11]), (fArr[0] * fArr2[12]) + (fArr[4] * fArr2[13]) + (fArr[8] * fArr2[14]) + (fArr[12] * fArr2[15]), (fArr[1] * fArr2[12]) + (fArr[5] * fArr2[13]) + (fArr[9] * fArr2[14]) + (fArr[13] * fArr2[15]), (fArr[2] * fArr2[12]) + (fArr[6] * fArr2[13]) + (fArr[10] * fArr2[14]) + (fArr[14] * fArr2[15]), (fArr[3] * fArr2[12]) + (fArr[7] * fArr2[13]) + (fArr[11] * fArr2[14]) + (fArr[15] * fArr2[15])};
    }

    public static Matrix4x4 makeOrtho(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f2 + f;
        float f8 = f2 - f;
        float f9 = f4 - f3;
        float f10 = f4 + f3;
        float f11 = f6 + f5;
        float f12 = f6 - f5;
        return new Matrix4x4(new float[]{2.0f / f8, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f / f9, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f / f12, 0.0f, (-f7) / f8, (-f10) / f9, (-f11) / f12, 1.0f});
    }

    public Matrix4x4 multiply(Matrix4x4 matrix4x4) {
        return new Matrix4x4(_mul(this.data, matrix4x4.data));
    }

    public Matrix4x4 multiplyBy(Matrix4x4 matrix4x4) {
        this.data = _mul(this.data, matrix4x4.data);
        return this;
    }

    public static Matrix4x4 makeFrustum(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f2 + f;
        float f8 = f2 - f;
        float f9 = f4 - f3;
        float f10 = f6 - f5;
        float f11 = 2.0f * f5;
        return new Matrix4x4(new float[]{f11 / f8, 0.0f, 0.0f, 0.0f, 0.0f, f11 / f9, 0.0f, 0.0f, f7 / f8, (f4 + f3) / f9, (-(f6 + f5)) / f10, -1.0f, 0.0f, 0.0f, ((f6 * -2.0f) * f5) / f10, 0.0f});
    }

    public Matrix4x4 clone() {
        return new Matrix4x4((float[]) this.data.clone());
    }
}
