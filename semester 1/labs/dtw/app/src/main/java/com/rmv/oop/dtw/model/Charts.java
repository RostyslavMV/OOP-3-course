package com.rmv.oop.dtw.model;

import android.hardware.SensorManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Charts {
    // Chart Constants
    public static final int LENGTH_CHART_HISTORY = 64;
    public static final int AVERAGE_WINDOW_LENGTH = 1;
    public static final int DELAY_SENSOR = SensorManager.SENSOR_DELAY_FASTEST;

    // Graphs
    private LineChart lineAcc;
    private LineChart lineTrain;
    private LineChart lineRecognition;

    // Data
    private LineData accData;
    private LineData trainData;
    private LineData recognitionData;

    // Datasets
    private LineDataSet[] acceleration;
    private LineDataSet[] training;
    private LineDataSet[] recognition;

    // Chart Managers
    private LineChartManager accChartManager;
    private LineChartManager trainChartManager;
    private LineChartManager recognitionChartManager;

    // History Lists
    private List<List<Float>> trainingHistory;
    private List<List<Float>> recognitionHistory;

    public void Initialize() {
        accData = new LineData();
        trainData = new LineData();
        recognitionData = new LineData();
        trainingHistory = new ArrayList<>(Arrays.asList(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        recognitionHistory = new ArrayList<>(Arrays.asList(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        lineAcc.setData(accData);
        lineTrain.setData(trainData);
        lineRecognition.setData(recognitionData);
        lineAcc.setAutoScaleMinMaxEnabled(true);
        lineTrain.setAutoScaleMinMaxEnabled(true);
        lineRecognition.setAutoScaleMinMaxEnabled(true);
        lineTrain.getAxisLeft().setDrawLabels(false);
        lineRecognition.getAxisRight().setDrawLabels(false);

        Description emptyDescription = new Description();
        emptyDescription.setText("");
        lineAcc.setDescription(emptyDescription);
        lineTrain.setDescription(emptyDescription);
        lineRecognition.setDescription(emptyDescription);

        acceleration = new LineDataSet[]{new LineDataSet(null, "X"), new LineDataSet(null, "Y"), new LineDataSet(null, "Z")};
        training = new LineDataSet[]{new LineDataSet(null, "X"), new LineDataSet(null, "Y"), new LineDataSet(null, "Z")};
        recognition = new LineDataSet[]{new LineDataSet(null, "X"), new LineDataSet(null, "Y"), new LineDataSet(null, "Z")};

        for (LineDataSet lineDataSet : acceleration) {
            accData.addDataSet(lineDataSet);
        }
        for (LineDataSet lineDataSet : training) {
            trainData.addDataSet(lineDataSet);
        }
        for (LineDataSet lineDataSet : recognition) {
            recognitionData.addDataSet(lineDataSet);
        }

    }
}
