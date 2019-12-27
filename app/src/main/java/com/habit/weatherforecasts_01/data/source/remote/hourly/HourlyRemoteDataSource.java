package com.habit.weatherforecasts_01.data.source.remote.hourly;

import com.habit.weatherforecasts_01.data.source.HourlyDataSource;
import com.habit.weatherforecasts_01.utils.StringUtil;

public class HourlyRemoteDataSource implements HourlyDataSource.RemoteDataSource {
    private static HourlyRemoteDataSource sInstance;

    private HourlyRemoteDataSource() {
    }

    public static HourlyRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new HourlyRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getHourlyList(HourlyDataSource.OnFetchDataListener listener,
                              String lat, String lon) {
        FetchHourlyFromUrl fetchHourlyFromUrl = new FetchHourlyFromUrl(listener);
        fetchHourlyFromUrl.execute(StringUtil.formatWeatherAPI(lat, lon));
    }
}
