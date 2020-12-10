package com.rmv.oop.dtw.model;

import java.util.List;

public class Util {
    public static float[] getFloatPrimitiveArrayFromList(final List<Float> list) {
        float[] array = new float[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
