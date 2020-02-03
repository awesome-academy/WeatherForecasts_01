package com.habit.weatherforecasts_01.utils;

import android.content.Context;

import com.habit.weatherforecasts_01.BuildConfig;
import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.constant.Constant;
import com.habit.weatherforecasts_01.constant.IconWeather;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    public static String formatWeatherAPI(String lat, String lon) {
        return String.format("%s%s/%s,%s", Constant.BASE_URL, BuildConfig.API_KEY, lat, lon);
    }

    public static String formatAirQualityAPI(String lat, String lon) {
        return String.format("%s%s;%s%s%s",
                Constant.BASE_URL_AQI, lat, lon, Constant.GET_TOKEN_AQI, BuildConfig.AQI_TOKEN);
    }

    public static int getCelsiusFromFahrenheit(int degreeF) {
        int degreeC = Math.round((degreeF - 32) / 1.8f);
        return degreeC;
    }

    public static String getFahrenheit(int degreeF) {
        return degreeF + Constant.DEGREE;
    }

    public static String getCelsius(int degreeF) {
        int degreeC = getCelsiusFromFahrenheit(degreeF);
        return degreeC + Constant.DEGREE;
    }

    public static String getTempDayF(int tempMaxF, int tempMinF) {
        return getFahrenheit(tempMaxF) + Constant.SPACE
                + Constant.SLASH + Constant.SPACE + getFahrenheit(tempMinF);
    }

    public static String getTempDayC(int tempMaxF, int tempMinF) {
        return getCelsius(tempMaxF) + Constant.SPACE
                + Constant.SLASH + Constant.SPACE + getCelsius(tempMinF);
    }

    public static String getDayInWeekFromDate(Date date) {
        SimpleDateFormat EEE = new SimpleDateFormat(Constant.FORMAT_EEE);
        String DayInWeek = EEE.format(date);
        return DayInWeek;
    }

    public static String getStringYearMonthDayFromDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_YEAR_MONTH_DAY);
        String textYearMonthDay = dateFormat.format(date);
        return textYearMonthDay;
    }

    public static String getStringDayFromDate(Date date) {
        SimpleDateFormat dayFormat = new SimpleDateFormat(Constant.FORMAT_DAY);
        String textDay = dayFormat.format(date);
        return textDay;
    }

    public static String getSecondStringDayFromDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DAY_2);
        String textDayEEEEE = dateFormat.format(date);
        return textDayEEEEE;
    }

    public static String getStringHourFromDate(Date date) {
        SimpleDateFormat hourFormat = new SimpleDateFormat(Constant.FORMAT_HOUR);
        String textHour = hourFormat.format(date);
        return textHour;
    }

    public static int getResIdOfIconFromName(String icon) {
        int resId = 0;
        switch (icon) {
            case IconWeather.ICON_CLEAR_DAY:
                resId = R.drawable.icon_clear_day;
                break;
            case IconWeather.ICON_CLEAR_NIGHT:
                resId = R.drawable.icon_clear_night;
                break;
            case IconWeather.ICON_RAIN:
                resId = R.drawable.icon_rain;
                break;
            case IconWeather.ICON_SNOW:
                resId = R.drawable.icon_snow;
                break;
            case IconWeather.ICON_SLEET:
                resId = R.drawable.icon_sleet;
                break;
            case IconWeather.ICON_WIND:
                resId = R.drawable.icon_wind;
                break;
            case IconWeather.ICON_FOG:
                resId = R.drawable.icon_fog;
                break;
            case IconWeather.ICON_CLOUDY:
                resId = R.drawable.icon_cloudy;
                break;
            case IconWeather.ICON_PARTLY_CLOUDY_DAY:
                resId = R.drawable.icon_partly_cloudy_day;
                break;
            case IconWeather.ICON_PARTLY_CLOUDY_NIGHT:
                resId = R.drawable.icon_partly_cloudy_night;
                break;
            default:
                resId = R.drawable.icon_cloudy;
                break;
        }
        return resId;
    }

    private static final int MAX_AIR_QUALITY = 500;
    private static final int MIN_AIR_QUALITY = 0;
    private static final int AIR_QUALITY_MODERATE = 50;
    private static final int AIR_QUALITY_UNHEALTHY_FOR_SENSITIVE = 100;
    private static final int AIR_QUALITY_UNHEALTHY = 150;
    private static final int AIR_QUALITY_VERY_UNHEALTHY = 200;
    private static final int AIR_QUALITY_HAZARDOUS = 300;

    public static int getProgressFromAqi(int aqi) {
        float ratio = (aqi * 1.0f) / MAX_AIR_QUALITY;
        int percent = Math.round(ratio * 100);
        return percent;
    }

    public static String getStatusFromAqi(int aqi, Context context) {
        if (aqi < MIN_AIR_QUALITY || aqi > MAX_AIR_QUALITY) {
            return context.getString(R.string.error_server);
        } else if (aqi >= MIN_AIR_QUALITY && aqi <= AIR_QUALITY_MODERATE) {
            return context.getString(R.string.status_good);
        } else if (aqi > AIR_QUALITY_MODERATE
                && aqi <= AIR_QUALITY_UNHEALTHY_FOR_SENSITIVE) {
            return context.getString(R.string.status_moderate);
        } else if (aqi > AIR_QUALITY_UNHEALTHY_FOR_SENSITIVE
                && aqi <= AIR_QUALITY_UNHEALTHY) {
            return context.getString(R.string.status_unhealthy_for_sensitive);
        } else if (aqi > AIR_QUALITY_UNHEALTHY
                && aqi <= AIR_QUALITY_VERY_UNHEALTHY) {
            return context.getString(R.string.status_unhealthy);
        } else if (aqi > AIR_QUALITY_VERY_UNHEALTHY
                && aqi <= AIR_QUALITY_HAZARDOUS) {
            return context.getString(R.string.status_very_unhealthy);
        } else if (aqi > AIR_QUALITY_HAZARDOUS
                && aqi <= MAX_AIR_QUALITY) {
            return context.getString(R.string.status_hazardous);
        }
        return context.getString(R.string.error_server);
    }

    public static String getContentFromAqi(int aqi, Context context) {
        if (aqi < MIN_AIR_QUALITY || aqi > MAX_AIR_QUALITY) {
            return context.getString(R.string.error_server);
        } else if (aqi >= MIN_AIR_QUALITY && aqi <= AIR_QUALITY_MODERATE) {
            return context.getString(R.string.content_good);
        } else if (aqi > AIR_QUALITY_MODERATE
                && aqi <= AIR_QUALITY_UNHEALTHY_FOR_SENSITIVE) {
            return context.getString(R.string.content_moderate);
        } else if (aqi > AIR_QUALITY_UNHEALTHY_FOR_SENSITIVE
                && aqi <= AIR_QUALITY_UNHEALTHY) {
            return context.getString(R.string.content_unhealthy_for_sensitive);
        } else if (aqi > AIR_QUALITY_UNHEALTHY
                && aqi <= AIR_QUALITY_VERY_UNHEALTHY) {
            return context.getString(R.string.content_unhealthy);
        } else if (aqi > AIR_QUALITY_VERY_UNHEALTHY
                && aqi <= AIR_QUALITY_HAZARDOUS) {
            return context.getString(R.string.content_very_unhealthy);
        } else if (aqi > AIR_QUALITY_HAZARDOUS
                && aqi <= MAX_AIR_QUALITY) {
            return context.getString(R.string.content_hazardous);
        }
        return context.getString(R.string.error_server);
    }
}
