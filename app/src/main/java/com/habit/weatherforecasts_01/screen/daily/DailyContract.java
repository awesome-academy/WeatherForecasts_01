package com.habit.weatherforecasts_01.screen.daily;

import com.habit.weatherforecasts_01.data.model.Daily;

import java.util.List;

public interface DailyContract {

    interface View {
        void onGetDailySuccess(List<Daily> dailyList);

        void onGetDailyFailure(String message);
    }

    interface Presenter {
        void getDailyList(String lat, String lon);
    }
}
