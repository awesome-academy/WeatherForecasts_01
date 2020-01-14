package com.habit.weatherforecasts_01.data.source;

import com.habit.weatherforecasts_01.data.model.AirQuality;

public interface AirQualityDataSource {

    interface OnFetchDataListener {
        void onFetchAirQualitySuccess(AirQuality data);

        void onFetchDataFailure(Exception e);
    }

    interface RemoteDataSource {
        void getAirQuality(OnFetchDataListener listener, String lat, String lon);
    }
}
