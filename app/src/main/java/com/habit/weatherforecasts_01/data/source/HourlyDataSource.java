package com.habit.weatherforecasts_01.data.source;

import com.habit.weatherforecasts_01.data.model.Hourly;

import java.util.List;

public interface HourlyDataSource {

    interface OnFetchDataListener {
        void onFetchDataHourlySuccess(List<Hourly> dataHourly);

        void onFetchDataFailure(Exception e);
    }

    interface RemoteDataSource {
        void getHourlyList(OnFetchDataListener listener, String lat, String lon);
    }
}
