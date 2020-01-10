package com.habit.weatherforecasts_01.data.repository;

import com.habit.weatherforecasts_01.data.source.AirQualityDataSource;
import com.habit.weatherforecasts_01.data.source.remote.airquality.AirQualityRemoteDataSource;

public class AirQualityRepository {
    private static AirQualityRepository sInstance;
    private AirQualityRemoteDataSource mAirQualityRemoteDataSource;

    private AirQualityRepository(AirQualityRemoteDataSource airQualityRemoteDataSource) {
        mAirQualityRemoteDataSource = airQualityRemoteDataSource;
    }

    public static AirQualityRepository getInstance() {
        if (sInstance == null) {
            sInstance = new AirQualityRepository(AirQualityRemoteDataSource.getInstance());
        }
        return sInstance;
    }

    public void getAirQuality(AirQualityDataSource.OnFetchDataListener listener,
                              String lat, String lon) {
        mAirQualityRemoteDataSource.getAirQuality(listener, lat, lon);
    }
}
