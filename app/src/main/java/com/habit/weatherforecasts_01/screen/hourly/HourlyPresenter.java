package com.habit.weatherforecasts_01.screen.hourly;

import com.habit.weatherforecasts_01.data.model.Hourly;
import com.habit.weatherforecasts_01.data.repository.HourlyRepository;
import com.habit.weatherforecasts_01.data.source.HourlyDataSource;

import java.util.List;

public class HourlyPresenter implements HourlyContract.Presenter,
        HourlyDataSource.OnFetchDataListener<Hourly> {

    private HourlyContract.View mView;
    private HourlyRepository mHourlyRepository;

    public HourlyPresenter(HourlyContract.View view) {
        mView = view;
        mHourlyRepository = HourlyRepository.getInstance();
    }

    @Override
    public void onFetchDataSuccess(List<Hourly> data) {
        mView.onGetHourlySuccess(data);
    }

    @Override
    public void onFetchDataFailure(Exception e) {
        mView.onGetHourlyFailure(e.getMessage());
    }

    @Override
    public void getHourlyList(String lat, String lon) {
        mHourlyRepository.getHourlyList(this, lat, lon);
    }
}
