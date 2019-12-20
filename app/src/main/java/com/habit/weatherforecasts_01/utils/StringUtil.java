package com.habit.weatherforecasts_01.utils;

import com.habit.weatherforecasts_01.data.model.CurrentWeather;

public class StringUtil {

    public static String toStringCurrentWeather(CurrentWeather currentWeather){
        return currentWeather.getAddress().concat(String.valueOf(currentWeather.getDate()))
                .concat(String.valueOf(currentWeather.getTemp())).concat(currentWeather.getStatus())
                .concat(currentWeather.getDescription());
    }

}
