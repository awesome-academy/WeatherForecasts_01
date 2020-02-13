package com.habit.weatherforecasts_01.screen.splash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.screen.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int PERMISSON_REQUEST_ACCESS = 1;
    private static final long SPLASH_TIME_DELAY = 2000;
    private static final String NOTIFY_PERMISSON_NOT_ACCEPT = "Permisson not accept";
    private static final String NOTIFY_ENABLE_GPS = "Please enable GPS";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            Toast.makeText(this, NOTIFY_ENABLE_GPS, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestPermissonRuntime();
                Intent intent = MainActivity.getIntent(SplashActivity.this);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_DELAY);
    }

    private void requestPermissonRuntime() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions
                    (this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION},
                            PERMISSON_REQUEST_ACCESS);
        } else {
            Intent intent = MainActivity.getIntent(SplashActivity.this);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSON_REQUEST_ACCESS:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = MainActivity.getIntent(SplashActivity.this);
                    startActivity(intent);
                } else {
                    Toast.makeText(this,
                            NOTIFY_PERMISSON_NOT_ACCEPT, Toast.LENGTH_SHORT).show();
                }
                return;
            default:
                return;
        }
    }
}
