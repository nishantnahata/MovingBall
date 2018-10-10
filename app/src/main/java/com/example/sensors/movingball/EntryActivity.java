package com.example.sensors.movingball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class EntryActivity extends AppCompatActivity {

    String ball="ball";
    Button btPlay;
    public static final String BALL_TYPE="BallType";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        btPlay= (Button) findViewById(R.id.bt_play);

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EntryActivity.this,MainActivity.class);
                i.putExtra(BALL_TYPE,ball);
                startActivity(i);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbt1:
                if (checked) {
                    ball = "ball";
                    break;
                }
            case R.id.rbt2:
                if (checked){
                    ball="ball2";
                    break;
                }
            case R.id.rbt3:
                if (checked){
                    ball="ball3";
                    break;
                }
        }
    }
}
