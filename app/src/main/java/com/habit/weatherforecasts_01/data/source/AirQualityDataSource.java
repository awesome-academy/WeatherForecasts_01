package com.habit.weatherforecasts_01.data.source;

public interface AirQualityDataSource {

    interface OnFetchDataListener<T> {
        void onFetchDataSuccess(T data);

        void onFetchDataFailure(Exception e);
    }

    interface RemoteDataSource {
        void getAirQuality(OnFetchDataListener listener, String lat, String lon);
    }
}
