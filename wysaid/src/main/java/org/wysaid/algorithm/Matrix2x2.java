package org.wysaid.algorithm;

public class Matrix2x2 {
    public float[] data;

    protected Matrix2x2(float[] fArr) {
        this.data = fArr;
    }

    public static Matrix2x2 makeRotation(float f) {
        double d = (double) f;
        float cos = (float) Math.cos(d);
        float sin = (float) Math.sin(d);
        return new Matrix2x2(new float[]{cos, sin, -sin, cos});
    }

    public Matrix2x2 multiplyBy(Matrix2x2 matrix2x2) {
        this.data = _mul(this.data, matrix2x2.data);
        return this;
    }

    protected static float[] _mul(float[] fArr, float[] fArr2) {
        return new float[]{(fArr[0] * fArr2[0]) + (fArr[2] * fArr2[1]), (fArr[1] * fArr2[0]) + (fArr[3] * fArr2[1]), (fArr[0] * fArr2[2]) + (fArr[2] * fArr2[3]), (fArr[1] * fArr2[2]) + (fArr[3] * fArr2[3])};
    }

    public Matrix2x2 multiply(Matrix2x2 matrix2x2) {
        return new Matrix2x2(_mul(this.data, matrix2x2.data));
    }

    public static Matrix2x2 makeIdentity() {
        return new Matrix2x2(new float[]{1.0f, 0.0f, 0.0f, 1.0f});
    }

    public Matrix2x2 clone() {
        return new Matrix2x2((float[]) this.data.clone());
    }

    protected Matrix2x2() {
        this.data = new float[4];
    }
}
