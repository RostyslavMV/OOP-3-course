package com.rmv.oop.dtw.model;

import com.github.mikephil.charting.data.LineDataSet;

public class Colorer {

    public static void color(final LineDataSet[] lineDataSets, final int[] color) {
        for (int i = 0; i < lineDataSets.length; i++) {
            // Update the color of the LineDataSet.
            Colorer.color(lineDataSets[i], color[i]);
        }
    }

    public static void color(final LineDataSet lineDataSet, final int color) {
        // Update the Colors.
        lineDataSet.setColor(color);
        lineDataSet.setCircleColorHole(color);
        lineDataSet.setCircleColor(color);
    }
}
