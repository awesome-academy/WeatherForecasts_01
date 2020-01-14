package com.habit.weatherforecasts_01.data.source;

import com.habit.weatherforecasts_01.data.model.CurrentWeather;

public interface CurrentWeatherDataSource {

    interface OnFetchDataListener {
        void onFetchDataSuccess(CurrentWeather data);

        void onFetchDataFailure(Exception e);
    }

    interface RemoteDataSource {
        void getCurrentWeather(OnFetchDataListener listener, String lat, String lon);
    }
}
