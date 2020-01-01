package com.habit.weatherforecasts_01.screen.today;

import com.habit.weatherforecasts_01.data.model.CurrentWeather;
import com.habit.weatherforecasts_01.data.model.Daily;
import com.habit.weatherforecasts_01.data.model.Hourly;
import com.habit.weatherforecasts_01.data.repository.CurrentWeatherRepository;
import com.habit.weatherforecasts_01.data.repository.DailyRepository;
import com.habit.weatherforecasts_01.data.repository.HourlyRepository;
import com.habit.weatherforecasts_01.data.source.CurrentWeatherDataSource;
import com.habit.weatherforecasts_01.data.source.DailyDataSource;
import com.habit.weatherforecasts_01.data.source.HourlyDataSource;

import java.util.List;

public class TodayPresenter implements TodayContract.Presenter,
        CurrentWeatherDataSource.OnFetchDataListener<CurrentWeather>,
        DailyDataSource.OnFetchDataListener, HourlyDataSource.OnFetchDataListener {

    private TodayContract.View mView;
    private CurrentWeatherRepository mCurrentWeatherRepository;
    private HourlyRepository mHourlyRepository;
    private DailyRepository mDailyRepository;

    public TodayPresenter(TodayContract.View view) {
        mView = view;
        mCurrentWeatherRepository = CurrentWeatherRepository.getInstance();
        mHourlyRepository = HourlyRepository.getInstance();
        mDailyRepository = DailyRepository.getInstance();
    }

    @Override
    public void onFetchDataSuccess(CurrentWeather data) {
        mView.onGetCurrentSuccess(data);
    }

    @Override
    public void onFetchDataSuccess(List<Daily> data) {
        mView.onGetDailySuccess(data);
    }

    @Override
    public void onFetchDataHourlySuccess(List<Hourly> dataHourly) {
        mView.onGetHourlySuccess(dataHourly);
    }

    @Override
    public void onFetchDataFailure(Exception e) {
        mView.onGetDataFailure(e.getMessage());
    }

    @Override
    public void getCurrentWeather(String lat, String lon) {
        mCurrentWeatherRepository.getCurrentWeather(this, lat, lon);
    }

    @Override
    public void getHourlyList(String lat, String lon) {
        mHourlyRepository.getHourlyList(this, lat, lon);
    }

    @Override
    public void getDailyList(String lat, String lon) {
        mDailyRepository.getDailyList(this, lat, lon);
    }
}
