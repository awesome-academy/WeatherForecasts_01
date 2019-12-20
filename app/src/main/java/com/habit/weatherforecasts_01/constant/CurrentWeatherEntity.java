package com.habit.weatherforecasts_01.constant;

import androidx.annotation.StringDef;

@StringDef({
        CurrentWeatherEntity.ADDRESS,
        CurrentWeatherEntity.TIMEZONE,
        CurrentWeatherEntity.MAIN_WEATHER,
        CurrentWeatherEntity.WEATHER_TEMP,
        CurrentWeatherEntity.NUMBER_KELVIN,
        CurrentWeatherEntity.WEATHER,
        CurrentWeatherEntity.MAIN_STATUS,
        CurrentWeatherEntity.DESCRIPTION
})
public @interface CurrentWeatherEntity {
    String ADDRESS = "name";
    String TIMEZONE = "timezone";
    String MAIN_WEATHER = "main";
    String WEATHER_TEMP = "temp";
    String NUMBER_KELVIN = "273.15f";
    String WEATHER = "weather";
    String MAIN_STATUS = "main";
    String DESCRIPTION = "description";
}
