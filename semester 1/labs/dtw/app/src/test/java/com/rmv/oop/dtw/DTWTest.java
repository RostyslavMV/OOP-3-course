package com.rmv.oop.dtw;

import com.rmv.oop.dtw.algotithm.DTW;
import com.rmv.oop.dtw.model.Util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DTWTest {

    private final DTW dtw = new DTW();

    @Test
    public void dtw_isCorrect(){
        List<Float> first = Arrays.asList(1f, 3f, 4f, 9f, 8f, 2f, 1f, 5f, 7f, 3f);
        List<Float> second = Arrays.asList(1f, 3f, 4f, 9f, 8f, 2f, 1f, 5f, 7f, 3f);
        double[] averages = new double[3];
        for (int i = 0; i < 3; i++) {
            float[] training = Util.getFloatPrimitiveArrayFromList(first);
            float[] recognition = Util.getFloatPrimitiveArrayFromList(second);
            averages[i] = dtw.compute(recognition, training).getDistance();
            assertTrue(averages[i] < 0.00001);
        }
    }
}