package com.halifax.andriod.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int NUMBER_OF_TIME_BLOCKS = 9;
    Button[] mTimeBlockButtons = new Button[NUMBER_OF_TIME_BLOCKS];
    TimeBlock[] mTimeBlocks = new TimeBlock[NUMBER_OF_TIME_BLOCKS];


    private Button mGreenButton;
    private Button mRedButton;
    private String mColor;

    private Button mSingIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //start activity_main.xml
        for(int i = 0; i < NUMBER_OF_TIME_BLOCKS; i++) {
            mTimeBlockButtons[i] = (Button) findViewById(getTimeBlockButtonId(i));
            mTimeBlockButtons[i].setOnClickListener(this);
        }

        mColor = "default";

        for (int i=0; i< mTimeBlocks.length; i++ ) {
            mTimeBlocks[i] = new TimeBlock();
        }
      
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

        mSingIn = (Button) findViewById(R.id.sign_in);
        mSingIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });
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
        if(mColor == "green") {
            mTimeBlockButtons[id].setBackgroundResource(R.color.holo_green_dark);
        }
        else if(mColor == "red") {
            mTimeBlockButtons[id].setBackgroundResource(R.color.holo_red_dark);
        }
        mTimeBlocks[id].setColor(mColor);
    }

    private int getTimeBlockButtonId(int index) {
        String button = "button" + (index);
        return getResources().getIdentifier(button, "id", getPackageName());
    }
}
