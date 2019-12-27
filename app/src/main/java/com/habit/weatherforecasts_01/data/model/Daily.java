package com.habit.weatherforecasts_01.data.model;

import java.util.Date;

public class Daily {
    private Date mDate;
    private String mWeather;
    private String mIcon;
    private int mTempMax;
    private int mTempMin;

    public Daily() {
    }

    public Daily(Date date, String weather, String icon, int tempMax, int tempMin) {
        mDate = date;
        mWeather = weather;
        mIcon = icon;
        mTempMax = tempMax;
        mTempMin = tempMin;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getWeather() {
        return mWeather;
    }

    public void setWeather(String weather) {
        mWeather = weather;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getTempMax() {
        return mTempMax;
    }

    public void setTempMax(int tempMax) {
        mTempMax = tempMax;
    }

    public int getTempMin() {
        return mTempMin;
    }

    public void setTempMin(int tempMin) {
        mTempMin = tempMin;
    }
}
