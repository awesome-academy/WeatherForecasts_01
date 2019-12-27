package com.habit.weatherforecasts_01.screen.hourly;

import com.habit.weatherforecasts_01.data.model.Hourly;

import java.util.List;

public interface HourlyContract {

    interface View {
        void onGetHourlySuccess(List<Hourly> hourlyList);

        void onGetHourlyFailure(String message);
    }

    interface Presenter {
        void getHourlyList(String lat, String lon);
    }
}
