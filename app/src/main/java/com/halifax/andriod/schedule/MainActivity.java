package com.halifax.andriod.schedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mGreenButton;
    private Button mRedButton;
    private String mColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //start activity_main.xml

        //Start coding here

        //Make the UI first

        //Create TimeBlock class
            //has color attribute , getColor(), setColor(string color)

        mGreenButton = (Button) findViewById(R.id.button_green);
        mRedButton = (Button) findViewById(R.id.button_red);

        mGreenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mColor = "green";
            }
        });

        mRedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mColor = "red";
            }
        });


        //Initialize an array length n=9 of TimeBlocks[] week;

        //Wire the array with the UI widget timeBlocks

        //If a timeBlock is touched change the color

    }
}
