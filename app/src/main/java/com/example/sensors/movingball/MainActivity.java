package com.example.sensors.movingball;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Animatable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ImageView tv;
    SensorManager sMgr;
    long prevTime;
    int h,w;
    float x,y,orX,orY;
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
        x=0;
        y=0;
        sMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tv = (ImageView) findViewById(R.id.ball);
        sMgr.registerListener(this, sensor, 1000000);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //Log.d(TAG, "onSensorChanged: ");
        if(prevTime==0)
        {
            prevTime=sensorEvent.timestamp;
        }
        if(sensorEvent.timestamp>(prevTime+(1000*1000*10)) && ((orX-sensorEvent.values[0]>1 || orX-sensorEvent.values[0]<-1) || (orY-sensorEvent.values[1]>1 || orY-sensorEvent.values[1]<-1)))
        {
            prevTime=sensorEvent.timestamp;
            ScaleAnimation a=new ScaleAnimation(x,((10-sensorEvent.values[0])/20)*w,y,((sensorEvent.values[1]+10)/20)*h);
            //a.setDuration();
            tv.setAnimation(a);
            x=((10-sensorEvent.values[0])/20)*w;
            y=((sensorEvent.values[1]+10)/20)*h;
            orX=sensorEvent.values[0];
            orY=sensorEvent.values[1];
            tv.setX(x);
            tv.setY(y);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
