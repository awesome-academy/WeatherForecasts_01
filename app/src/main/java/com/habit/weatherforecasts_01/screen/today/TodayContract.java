package com.habit.weatherforecasts_01.screen.today;

import com.habit.weatherforecasts_01.data.model.AirQuality;
import com.habit.weatherforecasts_01.data.model.CurrentWeather;
import com.habit.weatherforecasts_01.data.model.Daily;
import com.habit.weatherforecasts_01.data.model.Hourly;

import java.util.List;

public interface TodayContract {
    interface View {
        void onGetAirQualitySuccess(AirQuality airQuality);

        void onGetCurrentSuccess(CurrentWeather currentWeather);

        void onGetDataFailure(String message);

        void onGetHourlySuccess(List<Hourly> hourlyList);

        void onGetDailySuccess(List<Daily> dailyList);
    }

    interface Presenter {
        void getCurrentWeather(String lat, String lon);

        void getHourlyList(String lat, String lon);

        void getDailyList(String lat, String lon);

        void getAirQuality(String lat, String lon);
    }
}
