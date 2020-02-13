package com.habit.weatherforecasts_01.screen.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.screen.airquality.AirqualityFragment;
import com.habit.weatherforecasts_01.screen.daily.DailyFragment;
import com.habit.weatherforecasts_01.screen.hourly.HourlyFragment;
import com.habit.weatherforecasts_01.screen.today.TodayFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private static final int PERMISSON_REQUEST_ACCESS = 10;
    private static final int MIN_TIME_UPDATE_LOCATION = 5000;
    private static final int MIN_DISTANCE_UPDATE_LOCATION = 100;
    private LocationManager mLocationManager;
    private String provider;
    private String lat;
    private String lon;
    private Geocoder mGeocoder;
    private List<Address> mAddresses;
    private String address;

    private ViewPager mViewPager;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = mLocationManager.getBestProvider(criteria, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, PERMISSON_REQUEST_ACCESS);
                return;
            }
        }
        Location location = mLocationManager.getLastKnownLocation(provider);
        if (location != null) {
            onLocationChanged(location);
        }
        mLocationManager.requestLocationUpdates(
                provider,
                MIN_TIME_UPDATE_LOCATION,
                MIN_DISTANCE_UPDATE_LOCATION,
                this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSON_REQUEST_ACCESS:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
                return;
            default:
                return;
        }
    }

    public String getNowLatitude() {
        return lat;
    }

    public String getNowLongitude() {
        return lon;
    }

    public String getNowAddress() {
        return address;
    }

    private void initViews() {
        Toolbar mToolbar = findViewById(R.id.toolbar);

        mViewPager = findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        TabLayout mTabLayout = findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new TodayFragment(), getString(R.string.title_today));
        pagerAdapter.addFragment(new HourlyFragment(), getString(R.string.title_hourly));
        pagerAdapter.addFragment(new DailyFragment(), getString(R.string.title_daily));
        pagerAdapter.addFragment(new AirqualityFragment(), getString(R.string.title_air_quality));
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = String.valueOf(location.getLatitude());
        lon = String.valueOf(location.getLongitude());
        mGeocoder = new Geocoder(this, Locale.getDefault());
        try {
            mAddresses = mGeocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    5);
            if (mAddresses != null && mAddresses.size() > 0) {
                for (Address adr : mAddresses) {
                    if (adr.getLocality() != null && adr.getLocality().length() > 0) {
                        address = adr.getLocality();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mTitles = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mTitles.add(title);
        }
    }

}
