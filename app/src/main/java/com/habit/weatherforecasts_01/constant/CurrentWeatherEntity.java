package com.habit.weatherforecasts_01.constant;

import androidx.annotation.StringDef;

@StringDef({
        CurrentWeatherEntity.CURRENT_OBJECT,
        CurrentWeatherEntity.TIME,
        CurrentWeatherEntity.TEMP,
        CurrentWeatherEntity.WEATHER,
        CurrentWeatherEntity.ICON
})
public @interface CurrentWeatherEntity {
    String CURRENT_OBJECT = "currently";
    String TIME = "time";
    String TEMP = "temperature";
    String WEATHER = "summary";
    String ICON = "icon";
}
