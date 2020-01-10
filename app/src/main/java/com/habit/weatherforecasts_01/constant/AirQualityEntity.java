package com.habit.weatherforecasts_01.constant;

import androidx.annotation.StringDef;

@StringDef({
        AirQualityEntity.DATA_OBJECT,
        AirQualityEntity.AQI
})
public @interface AirQualityEntity {
    String DATA_OBJECT = "data";
    String AQI = "aqi";
}
