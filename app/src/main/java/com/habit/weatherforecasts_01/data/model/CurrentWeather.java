package com.habit.weatherforecasts_01.data.model;

import java.util.Date;

public class CurrentWeather {
    private String mAddress;
    private Date mDate;
    private int mTemp;
    private String mStatus;
    private String mDescription;

    public CurrentWeather() {
    }

    public CurrentWeather(String address, Date date, int temp, String status, String description) {
        mAddress = address;
        mDate = date;
        mTemp = temp;
        mStatus = status;
        mDescription = description;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
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

    public String getStatus() {
        return mStatus;
    }

    public void setMain(String status) {
        mStatus = status;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

}
