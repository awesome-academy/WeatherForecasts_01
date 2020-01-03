package com.habit.weatherforecasts_01.data.source;

import com.habit.weatherforecasts_01.data.model.Daily;

import java.util.List;

public interface DailyDataSource {

    interface OnFetchDataListener {
        void onFetchDataSuccess(List<Daily> data);

        void onFetchDataFailure(Exception e);
    }

    interface RemoteDataSource {
        void getDailyList(OnFetchDataListener listener, String lat, String lon);
    }
}
