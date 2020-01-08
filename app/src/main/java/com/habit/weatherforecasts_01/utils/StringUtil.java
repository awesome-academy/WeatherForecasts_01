package com.habit.weatherforecasts_01.utils;

import com.habit.weatherforecasts_01.BuildConfig;
import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.constant.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

    public static String formatWeatherAPI(String lat, String lon) {
        return String.format("%s%s/%s,%s", Constant.BASE_URL, BuildConfig.API_KEY, lat, lon);
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

    public static String getStringHourFromDate(Date date) {
        SimpleDateFormat hourFormat = new SimpleDateFormat(Constant.FORMAT_HOUR);
        String textHour = hourFormat.format(date);
        return textHour;
    }

    public static int getResIdOfIconFromName(String icon) {
        int resId;
        switch (icon) {
            case Constant.ICON_CLEAR_DAY:
                resId = R.drawable.icon_clear_day;
                break;
            case Constant.ICON_CLEAR_NIGHT:
                resId = R.drawable.icon_clear_night;
                break;
            case Constant.ICON_RAIN:
                resId = R.drawable.icon_rain;
                break;
            case Constant.ICON_SNOW:
                resId = R.drawable.icon_snow;
                break;
            case Constant.ICON_SLEET:
                resId = R.drawable.icon_sleet;
                break;
            case Constant.ICON_WIND:
                resId = R.drawable.icon_wind;
                break;
            case Constant.ICON_FOG:
                resId = R.drawable.icon_fog;
                break;
            case Constant.ICON_CLOUDY:
                resId = R.drawable.icon_cloudy;
                break;
            case Constant.ICON_PARTLY_CLOUDY_DAY:
                resId = R.drawable.icon_partly_cloudy_day;
                break;
            case Constant.ICON_PARTLY_CLOUDY_NIGHT:
                resId = R.drawable.icon_partly_cloudy_night;
                break;
            default:
                resId = R.drawable.icon_cloudy;
                break;
        }
        return resId;
    }
}
