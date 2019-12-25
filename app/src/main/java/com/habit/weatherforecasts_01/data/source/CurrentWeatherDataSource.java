package com.habit.weatherforecasts_01.data.source;

public interface CurrentWeatherDataSource {

    interface OnFetchDataListener<T> {
        void onFetchDataSuccess(T data);

        void onFetchDataFailure(Exception e);
    }

    interface RemoteDataSource {
        void getCurrentWeather(OnFetchDataListener listener, String lat, String lon);
    }
}
