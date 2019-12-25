package com.habit.weatherforecasts_01.utils;

import com.habit.weatherforecasts_01.BuildConfig;
import com.habit.weatherforecasts_01.constant.Constant;

public class StringUtil {

    public static String formatWeatherAPI(String lat, String lon) {
        return String.format("%s%s/%s,%s", Constant.BASE_URL, BuildConfig.API_KEY, lat, lon);
    }

}
