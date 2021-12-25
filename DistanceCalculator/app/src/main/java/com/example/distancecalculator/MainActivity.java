package com.example.distancecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * main class
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-12-05
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<TextView> accelerationText;
    private ArrayList<TextView> speedText;
    private TextView distanceText;

    private Tracker tracker = new Tracker();

    private boolean active = false;

    SensorEventListener sensorEventListener = new SensorEventListener() {
        /** processes occurred events (accelerator and gyroscope value changes)
         *
         * @param event accelerator or gyroscope values
         */
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                if(event.values[0] < 0.5 && event.values[1] < 0.5 && event.values[2] < 0.5) {
                    if(!active) {
                        findViewById(R.id.attention).setVisibility(View.INVISIBLE);
                        active = true;
                    }
                }
                else if(active) {
                    findViewById(R.id.attention).setVisibility(View.VISIBLE);
                    active = false;
                }
            }
            else if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                if(!active) {
                    return;
                }

                tracker.updateTrackedValues(event.values, 0.015);

                updateTextFields(event.values);
            }
        }

        /** this method is here only to have ability to implement SensorEventListener
         */
        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            //unused
        }
    };

    /** class initialization method
     * adds sensors, initializes ui fields
     *
     * @param savedInstanceState saved state of instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        findViewById(R.id.finishButton).setEnabled(false);
        findViewById(R.id.runLayout).setVisibility(View.INVISIBLE);

        initSensors();
        initTextFields();
    }

    /** handler of clicking on start button
     * launch or restart of calculations
     */
    public void onStartClick(View view) {
        findViewById(R.id.startButton).setEnabled(false);
        findViewById(R.id.finishButton).setEnabled(true);
        findViewById(R.id.runLayout).setVisibility(View.VISIBLE);
        tracker.restart();
        active = true;
    }

    /** handler of clicking on start button
     * stops calculations
     */
    public void onFinishClick(View view) {
        findViewById(R.id.startButton).setEnabled(true);
        findViewById(R.id.finishButton).setEnabled(false);
        findViewById(R.id.runLayout).setVisibility(View.INVISIBLE);
        active = false;
    }

    private void initSensors() {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(
                sensorEventListener,
                accelerometerSensor,
                SensorManager.SENSOR_DELAY_FASTEST
        );

        Sensor gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(
                sensorEventListener,
                gyroscopeSensor,
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    private void initTextFields() {
        accelerationText = new ArrayList<>();
        accelerationText.add(findViewById(R.id.accelerationX));
        accelerationText.add(findViewById(R.id.accelerationY));
        accelerationText.add(findViewById(R.id.accelerationZ));
        speedText = new ArrayList<>();
        speedText.add(findViewById(R.id.speedX));
        speedText.add(findViewById(R.id.speedY));
        speedText.add(findViewById(R.id.speedZ));
        distanceText = findViewById(R.id.distance);
    }

    private void updateTextFields(float[] acceleration) {
        accelerationText.get(0).setText("Acceleration X: " + Utils.round(acceleration[0], 1));
        accelerationText.get(1).setText("Acceleration Y: " + Utils.round(acceleration[1], 1));
        accelerationText.get(2).setText("Acceleration Z: " + Utils.round(acceleration[2], 1));
        double[] speed = tracker.getSpeed();
        speedText.get(0).setText("Speed X: " + Utils.round(speed[0], 1));
        speedText.get(1).setText("Speed Y: " + Utils.round(speed[1], 1));
        speedText.get(2).setText("Speed Z: " + Utils.round(speed[2], 1));
        distanceText.setText("Distance: " + Utils.round(tracker.getDistance(), 2));
    }
}