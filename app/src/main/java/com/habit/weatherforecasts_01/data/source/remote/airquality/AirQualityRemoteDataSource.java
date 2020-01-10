package com.habit.weatherforecasts_01.data.source.remote.airquality;

import com.habit.weatherforecasts_01.data.source.AirQualityDataSource;
import com.habit.weatherforecasts_01.utils.StringUtil;

public class AirQualityRemoteDataSource implements AirQualityDataSource.RemoteDataSource {
    private static AirQualityRemoteDataSource sInstance;

    private AirQualityRemoteDataSource() {
    }

    public static AirQualityRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new AirQualityRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getAirQuality(AirQualityDataSource.OnFetchDataListener listener,
                              String lat, String lon) {
        FetchAirQualityFromUrl fetchAirQualityFromUrl
                = new FetchAirQualityFromUrl(listener);
        fetchAirQualityFromUrl.execute(StringUtil.formatAirQualityAPI(lat, lon));
    }
}
