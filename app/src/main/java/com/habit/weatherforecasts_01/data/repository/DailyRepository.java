package com.habit.weatherforecasts_01.data.repository;

import com.habit.weatherforecasts_01.data.source.DailyDataSource;
import com.habit.weatherforecasts_01.data.source.remote.daily.DailyRemoteDataSource;

public class DailyRepository {
    private static DailyRepository sInstance;
    private DailyRemoteDataSource mDailyRemoteDataSource;

    private DailyRepository(DailyRemoteDataSource dailyRemoteDataSource) {
        mDailyRemoteDataSource = dailyRemoteDataSource;
    }

    public static DailyRepository getInstance() {
        if (sInstance == null) {
            sInstance = new DailyRepository(DailyRemoteDataSource.getInstance());
        }
        return sInstance;
    }

    public void getDailyList(DailyDataSource.OnFetchDataListener listener,
                             String lat, String lon) {
        mDailyRemoteDataSource.getDailyList(listener, lat, lon);
    }
}
