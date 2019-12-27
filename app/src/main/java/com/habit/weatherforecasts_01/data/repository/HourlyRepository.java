package com.habit.weatherforecasts_01.data.repository;

import com.habit.weatherforecasts_01.data.source.HourlyDataSource;
import com.habit.weatherforecasts_01.data.source.remote.hourly.HourlyRemoteDataSource;

public class HourlyRepository {
    private static HourlyRepository sInstance;
    private HourlyRemoteDataSource mHourlyRemoteDataSource;

    private HourlyRepository(HourlyRemoteDataSource hourlyRemoteDataSource) {
        mHourlyRemoteDataSource = hourlyRemoteDataSource;
    }

    public static HourlyRepository getInstance() {
        if (sInstance == null) {
            sInstance = new HourlyRepository(HourlyRemoteDataSource.getInstance());
        }
        return sInstance;
    }

    public void getHourlyList(HourlyDataSource.OnFetchDataListener listener,
                              String lat, String lon) {
        mHourlyRemoteDataSource.getHourlyList(listener, lat, lon);
    }
}
