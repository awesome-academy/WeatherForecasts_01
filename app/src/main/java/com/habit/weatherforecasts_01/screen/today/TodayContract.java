package com.habit.weatherforecasts_01.screen.today;

import com.habit.weatherforecasts_01.data.model.CurrentWeather;

public interface TodayContract {
    interface View {
        void onGetCurrentSuccess(CurrentWeather currentWeather);

        void onGetCurrentFailure(String message);
    }

    interface Presenter {
        void getCurrentWeather(String lat, String lon);
    }
}
