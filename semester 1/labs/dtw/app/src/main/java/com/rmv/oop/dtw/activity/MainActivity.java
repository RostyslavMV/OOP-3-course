package com.rmv.oop.dtw.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.rmv.oop.dtw.model.AppMode;
import com.rmv.oop.dtw.model.Charts;
import com.rmv.oop.dtw.model.Colorer;
import com.rmv.oop.dtw.model.LineChartManager;
import com.rmv.oop.dtw.R;
import com.rmv.oop.dtw.model.Util;
import com.rmv.oop.dtw.algotithm.DTW;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Setter
    private AppMode appMode;
    @Setter
    private boolean responsive;
    private SensorManager sensorManager;

    // UI
    private Button feedbackButton;
    private ImageView feedbackView;
    private TextView modeTitle;
    private TextView modeDescription;
    private SwitchCompat modeSwitch;
    private TextView resultView;

    private final Charts charts = new Charts();

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appMode = AppMode.TRAINING;
        // not processing data on app start
        responsive = false;

        setContentView(R.layout.activity_main);
        // Initialize UI
        feedbackButton = findViewById(R.id.btn_feedback);
        feedbackView = findViewById(R.id.iv_feedback);
        resultView = findViewById(R.id.tv_result);
        modeTitle = findViewById(R.id.tv_mode);
        modeDescription = findViewById(R.id.tv_mode_desc);
        modeSwitch = findViewById(R.id.sw_mode);
        feedbackView.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent));
        setSwitchListener();

        // Initialize Charts
        charts.setLineAcc(findViewById(R.id.lc_acc));
        charts.setLineTrain(findViewById(R.id.lc_train));
        charts.setLineRecognition(findViewById(R.id.lc_recognize));
        charts.Initialize();

        onInitializeData(charts.getAcceleration());
        onInitializeData(charts.getTraining());
        onInitializeData(charts.getRecognition());
        charts.getAccData().notifyDataChanged();
        charts.getTrainData().notifyDataChanged();
        charts.getRecognitionData().notifyDataChanged();

        Colorer.color(charts.getAcceleration(), new int[]{Color.RED, Color.GREEN, Color.BLUE});
        Colorer.color(charts.getTraining(), new int[]{Color.RED, Color.GREEN, Color.BLUE});
        Colorer.color(charts.getRecognition(), new int[]{Color.RED, Color.GREEN, Color.BLUE});

        charts.setAccChartManager(new LineChartManager(charts.getLineAcc(), Charts.AVERAGE_WINDOW_LENGTH, charts.getAcceleration()));
        charts.setTrainChartManager(new LineChartManager(charts.getLineTrain(), Charts.AVERAGE_WINDOW_LENGTH, charts.getTraining()) {
            @Override
            public final void onAggregateUpdate(float[] aggregate) {
                super.onAggregateUpdate(aggregate);

                for (int i = 0; i < aggregate.length; i++) {
                    charts.getTrainingHistory().get(i).add(aggregate[i]);
                }
            }
        });
        charts.setRecognitionChartManager(new LineChartManager(charts.getLineRecognition(), Charts.AVERAGE_WINDOW_LENGTH, charts.getRecognition()) {
            @Override
            public final void onAggregateUpdate(float[] aggregate) {
                super.onAggregateUpdate(aggregate);

                for (int i = 0; i < aggregate.length; i++) {
                    charts.getRecognitionHistory().get(i).add(aggregate[i]);
                }
            }
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        setFeedbackButtonListener();
        onHideFeedback();
    }

    private void onInitializeData(LineDataSet[] dataSet) {
        for (LineDataSet lineDataSet : dataSet) {
            lineDataSet.clear();
        }
        for (int i = 0; i < Charts.LENGTH_CHART_HISTORY; i++) {
            Entry entry = new Entry(i, 0);
            for (LineDataSet lineDataSet : dataSet) {
                lineDataSet.addEntry(entry);
            }
        }
    }

    private void onHideFeedback() {
        this.getFeedbackView().setVisibility(View.GONE);
        feedbackButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTransparent));
    }

    private void onFeedbackRecording() {
        feedbackView.setVisibility(View.VISIBLE);
        feedbackView.setImageResource(R.drawable.ic_voicemail_black_24dp);
        feedbackButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorMature));
    }

    private void onFeedbackRecognition() {
        feedbackView.setVisibility(View.VISIBLE);
        feedbackView.setImageResource(R.drawable.ic_gesture_black_24dp);
        feedbackButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorMature));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setFeedbackButtonListener() {
        feedbackButton.setOnTouchListener((view, motionEvent) -> {

            if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
                modeSwitch.setEnabled(false);
                if (appMode == AppMode.TRAINING) {
                    resetTraining();
                    onFeedbackRecording();
                } else if (appMode == AppMode.RECOGNITION) {
                    resetRecognition();
                    onFeedbackRecognition();
                }
                responsive = true;
            } else if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                responsive = false;
                onHideFeedback();
                if (appMode == AppMode.RECOGNITION) {
                    new Thread(() -> {
                        double[] averages = new double[3];
                        DTW dtw = new DTW();
                        for (int i = 0; i < 3; i++) {
                            float[] training = Util.getFloatPrimitiveArrayFromList(charts.getTrainingHistory().get(i));
                            float[] recognition = Util.getFloatPrimitiveArrayFromList(charts.getRecognitionHistory().get(i));
                            averages[i] = dtw.compute(recognition, training).getDistance();
                        }
                        runOnUiThread(() -> {
                            String result = "D(X:" + averages[0] + ", Y:" + averages[1] + ", Z:" + averages[2] + ")";
                            resultView.setText(result);
                        });
                    }).start();
                }
                modeSwitch.setEnabled(true);
            }
            return true;
        });
    }

    private void resetTraining() {
        for (List<Float> training : charts.getTrainingHistory()) {
            training.clear();
        }
        charts.getTrainChartManager().setOffset(0);
        onInitializeData(charts.getTraining());
    }

    private void resetRecognition() {
        for (List<Float> recognition : charts.getRecognitionHistory()) {
            recognition.clear();
        }
        charts.getRecognitionChartManager().setOffset(0);
        onInitializeData(charts.getRecognition());
    }

    private void setSwitchListener() {
        modeSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            setAppMode(isChecked ? AppMode.RECOGNITION : AppMode.TRAINING);
            getModeTitle().setText(isChecked ? R.string.mode_recognition : R.string.mode_training);
            getModeDescription().setText(isChecked ? R.string.mode_recognition_desc
                    : R.string.mode_training_desc);
        });
    }

    @Override
    public void onSensorChanged(final SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            charts.getAccChartManager().onUpdateChart(sensorEvent.values);
            if (isResponsive()) {
                if (appMode == AppMode.TRAINING) {
                    charts.getTrainChartManager().onUpdateChart(sensorEvent.values);
                } else if (appMode == AppMode.RECOGNITION) {
                    charts.getRecognitionChartManager().onUpdateChart(sensorEvent.values);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getSensorManager().registerListener(
                this, this.getSensorManager().getDefaultSensor(Sensor.TYPE_ACCELEROMETER), Charts.DELAY_SENSOR);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.getSensorManager().unregisterListener(this);
    }
}
