package com.habit.weatherforecasts_01.data.model;

public class AirQuality {
    private String mAqi;

    public AirQuality() {
    }

    public AirQuality(String aqi) {
        mAqi = aqi;
    }

    public String getAqi() {
        return mAqi;
    }

    public void setAqi(String aqi) {
        mAqi = aqi;
    }
}
