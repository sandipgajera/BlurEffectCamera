package org.wysaid.algorithm;

public class AlgorithmUtil {

    public static float getNormalizeScaling(float f, float f2, float f3, float f4) {
        return (float) (1.0d / Math.sqrt((double) ((((f * f) + (f2 * f2)) + (f3 * f3)) + (f4 * f4))));
    }

    public static float getNormalizeScaling(float f, float f2, float f3) {
        return (float) (1.0d / Math.sqrt((double) (((f * f) + (f2 * f2)) + (f3 * f3))));
    }

}
