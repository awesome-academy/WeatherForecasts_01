package com.habit.weatherforecasts_01.screen.today;

import com.habit.weatherforecasts_01.data.model.CurrentWeather;
import com.habit.weatherforecasts_01.data.repository.CurrentWeatherRepository;
import com.habit.weatherforecasts_01.data.source.CurrentWeatherDataSource;

public class TodayPresenter implements TodayContract.Presenter,
        CurrentWeatherDataSource.OnFetchDataListener<CurrentWeather> {

    private TodayContract.View mView;
    private CurrentWeatherRepository mCurrentWeatherRepository;

    public TodayPresenter(TodayContract.View view) {
        mView = view;
        mCurrentWeatherRepository = CurrentWeatherRepository.getInstance();
    }

    @Override
    public void onFetchDataSuccess(CurrentWeather data) {
        mView.onGetCurrentSuccess(data);
    }

    @Override
    public void onFetchDataFailure(Exception e) {
        mView.onGetCurrentFailure(e.getMessage());
    }

    @Override
    public void getCurrentWeather(String lat, String lon) {
        mCurrentWeatherRepository.getCurrentWeather(this, lat, lon);
    }
}
