package com.habit.weatherforecasts_01.screen.daily;

import com.habit.weatherforecasts_01.data.model.Daily;
import com.habit.weatherforecasts_01.data.repository.DailyRepository;
import com.habit.weatherforecasts_01.data.source.DailyDataSource;

import java.util.List;

public class DailyPresenter implements DailyContract.Presenter, DailyDataSource.OnFetchDataListener {

    private DailyContract.View mView;
    private DailyRepository mDailyRepository;

    public DailyPresenter(DailyContract.View view) {
        mView = view;
        mDailyRepository = DailyRepository.getInstance();
    }

    @Override
    public void onFetchDataSuccess(List<Daily> data) {
        mView.onGetDailySuccess(data);
    }

    @Override
    public void onFetchDataFailure(Exception e) {
        mView.onGetDailyFailure(e.getMessage());
    }

    @Override
    public void getDailyList(String lat, String lon) {
        mDailyRepository.getDailyList(this, lat, lon);
    }
}
