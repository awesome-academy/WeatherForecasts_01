package com.habit.weatherforecasts_01.data.source;

import java.util.List;

public interface HourlyDataSource {

    interface OnFetchDataListener<T> {
        void onFetchDataSuccess(List<T> data);

        void onFetchDataFailure(Exception e);
    }

    interface RemoteDataSource {
        void getHourlyList(OnFetchDataListener listener, String lat, String lon);
    }
}
