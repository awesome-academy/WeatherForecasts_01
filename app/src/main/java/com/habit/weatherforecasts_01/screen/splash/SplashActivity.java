package com.habit.weatherforecasts_01.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.screen.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    public static final long SPLASH_TIME_DELAY = 1500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = MainActivity.getIntent(SplashActivity.this);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_DELAY);
    }
}
