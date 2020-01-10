package com.habit.weatherforecasts_01.screen.airquality;

import com.habit.weatherforecasts_01.data.model.AirQuality;
import com.habit.weatherforecasts_01.data.repository.AirQualityRepository;
import com.habit.weatherforecasts_01.data.source.AirQualityDataSource;

public class AirqualityPresenter implements AirqualityContract.Presenter,
        AirQualityDataSource.OnFetchDataListener<AirQuality> {
    private AirqualityContract.View mView;
    private AirQualityRepository mAirQualityRepository;

    public AirqualityPresenter(AirqualityContract.View view) {
        mView = view;
        mAirQualityRepository = AirQualityRepository.getInstance();
    }

    @Override
    public void onFetchDataSuccess(AirQuality data) {
        mView.onGetAirQualitySuccess(data);
    }

    @Override
    public void onFetchDataFailure(Exception e) {
        mView.onGetDataFailure(e.getMessage());
    }

    @Override
    public void getAirQuality(String lat, String lon) {
        mAirQualityRepository.getAirQuality(this, lat, lon);
    }
}
