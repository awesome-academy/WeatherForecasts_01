package com.habit.weatherforecasts_01.data.source;

import com.habit.weatherforecasts_01.data.model.CurrentWeather;

public interface CurrentWeatherDataSource {

    interface OnFetchDataListener<CurrentWeather>{
        void onFetchDataSuccess(CurrentWeather currentWeather);

        void onFetchDataFailure(Exception e);
    }
}
