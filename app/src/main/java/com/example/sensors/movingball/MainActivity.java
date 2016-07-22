package com.example.sensors.movingball;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tv;
    SensorManager sMgr;
    int h,w;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Display display =    getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        w = size.x;
        h = size.y;
        sMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tv = (TextView) findViewById(R.id.ball);
        sMgr.registerListener(this, sensor, 1000000);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //Log.d(TAG, "onSensorChanged: ");
        tv.setX(((10-sensorEvent.values[0])/20)*w);
        tv.setY(((sensorEvent.values[1]+10)/20)*h);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
