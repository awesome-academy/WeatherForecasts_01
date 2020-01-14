package com.habit.weatherforecasts_01.constant;

import androidx.annotation.StringDef;

@StringDef({
        IconWeather.ICON_CLEAR_DAY,
        IconWeather.ICON_CLEAR_NIGHT,
        IconWeather.ICON_RAIN,
        IconWeather.ICON_SNOW,
        IconWeather.ICON_SLEET,
        IconWeather.ICON_WIND,
        IconWeather.ICON_FOG,
        IconWeather.ICON_CLOUDY,
        IconWeather.ICON_PARTLY_CLOUDY_DAY,
        IconWeather.ICON_PARTLY_CLOUDY_NIGHT
})
public @interface IconWeather {
    String ICON_CLEAR_DAY = "clear-day";
    String ICON_CLEAR_NIGHT = "clear-night";
    String ICON_RAIN = "rain";
    String ICON_SNOW = "snow";
    String ICON_SLEET = "sleet";
    String ICON_WIND = "wind";
    String ICON_FOG = "fog";
    String ICON_CLOUDY = "cloudy";
    String ICON_PARTLY_CLOUDY_DAY = "partly-cloudy-day";
    String ICON_PARTLY_CLOUDY_NIGHT = "partly-cloudy-night";
}
