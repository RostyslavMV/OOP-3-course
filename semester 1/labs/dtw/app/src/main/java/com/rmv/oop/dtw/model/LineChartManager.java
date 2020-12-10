package com.rmv.oop.dtw.model;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

@Getter
public class LineChartManager {

    private final LineChart lineChart;
    private final LineDataSet[] dataSets;
    private final int window;
    private final float[] buffer;
    @Setter
    private int offset;

    public LineChartManager(final LineChart lineChart, final int window, final LineDataSet... dataSets) {
        this.lineChart = lineChart;
        this.dataSets = dataSets;
        this.window = window;
        this.buffer = new float[dataSets.length];
        this.offset = 0;
    }

  // Updates the Chart
    public final void onUpdateChart(final float... vertical) {
        offset++;
        for (int i = 0; i < vertical.length; i++) {
            buffer[i] += vertical[i];
        }
        // Have we reached the window length?
        if (offset % window == 0) {
            // Perform an aggregated update.
            this.onAggregateUpdate(buffer);
            // Clear the Buffer.
            Arrays.fill(buffer, 0.0f);
        }
    }

    // Called when the number of samples displayed on the graph have satisfied the window size.
    public void onAggregateUpdate(final float[] aggregate) {
        // Update the chart.
        for (int i = 0; i < this.getDataSets().length; i++) {
            float average = this.getBuffer()[i] / window;
            LineDataSet lineDataSet = dataSets[i];
            // Write this Value to the Aggregate for subclasses.
            aggregate[i] = average;
            // Remove the oldest element.
            lineDataSet.removeFirst();
            // Buffer the Average.
            lineDataSet.addEntry(new Entry(offset, average));
        }
        // Invalidate the Graph. (Ensure it is redrawn!)
        this.getLineChart().invalidate();
    }
}
