package com.habit.weatherforecasts_01.data.repository;

import com.habit.weatherforecasts_01.data.source.CurrentWeatherDataSource;
import com.habit.weatherforecasts_01.data.source.remote.CurrentRemoteDataSource;

public class CurrentWeatherRepository {
    private static CurrentWeatherRepository sInstance;
    private CurrentRemoteDataSource mCurrentRemoteDataSource;

    private CurrentWeatherRepository(CurrentRemoteDataSource currentRemoteDataSource) {
        mCurrentRemoteDataSource = currentRemoteDataSource;
    }

    public static CurrentWeatherRepository getInstance() {
        if (sInstance == null) {
            sInstance = new CurrentWeatherRepository(CurrentRemoteDataSource.getInstance());
        }
        return sInstance;
    }

    public void getCurrentWeather(CurrentWeatherDataSource.OnFetchDataListener listener,
                                  String lat, String lon) {
        mCurrentRemoteDataSource.getCurrentWeather(listener, lat, lon);
    }
}
