package com.hotel;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SpalshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        CountDownTimer mCountDownTimer = new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                Intent i = new Intent(SpalshActivity.this, NavigationActivity.class);
                startActivity(i);
                finish();
            }
        };
        mCountDownTimer.start();
    }
}
