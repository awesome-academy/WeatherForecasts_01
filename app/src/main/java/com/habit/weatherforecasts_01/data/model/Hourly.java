package com.habit.weatherforecasts_01.data.model;

import java.util.Date;

public class Hourly {
    private Date mDate;
    private int mTemp;
    private String mWeather;
    private String mIcon;

    public Hourly() {
    }

    public Hourly(Date date, int temp, String weather, String icon) {
        mDate = date;
        mTemp = temp;
        mWeather = weather;
        mIcon = icon;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getTemp() {
        return mTemp;
    }

    public void setTemp(int temp) {
        mTemp = temp;
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
}
