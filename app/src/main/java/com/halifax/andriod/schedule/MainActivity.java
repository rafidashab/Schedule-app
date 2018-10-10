package com.halifax.andriod.schedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int NUMBER_OF_TIME_BLOCKS = 9;
    Button[] mTimeBlockButtons = new Button[NUMBER_OF_TIME_BLOCKS];
    TimeBlock[] timeBlocks = new TimeBlock[NUMBER_OF_TIME_BLOCKS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //start activity_main.xml
        for(int i = 0; i < NUMBER_OF_TIME_BLOCKS; i++) {
            mTimeBlockButtons[i] = (Button) findViewById(getTimeBlockButtonId(i));
            mTimeBlockButtons[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v)
    {
        for(int i = 0; i < NUMBER_OF_TIME_BLOCKS; i++) {
            if(v == mTimeBlockButtons[i]) {
                onTimeBlockClicked(i);
            }
        }
    }

    private void onTimeBlockClicked(int id) {
        /**
        mTimeBlockButtons[i].setBackgroundResource(R.color.holo_green_dark);
        This is how you set it to green, for instance
        */
    }

    private int getTimeBlockButtonId(int index) {
        String button = "" + (index);
        return getResources().getIdentifier(button, "id", getPackageName());
    }
}
