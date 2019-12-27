package com.habit.weatherforecasts_01.data.source.remote.daily;

import com.habit.weatherforecasts_01.data.source.DailyDataSource;
import com.habit.weatherforecasts_01.utils.StringUtil;

public class DailyRemoteDataSource implements DailyDataSource.RemoteDataSource {
    private static DailyRemoteDataSource sInstance;

    private DailyRemoteDataSource() {
    }

    public static DailyRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new DailyRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getDailyList(DailyDataSource.OnFetchDataListener listener,
                             String lat, String lon) {
        FetchDailyFromUrl fetchDailyFromUrl = new FetchDailyFromUrl(listener);
        fetchDailyFromUrl.execute(StringUtil.formatWeatherAPI(lat, lon));
    }
}
