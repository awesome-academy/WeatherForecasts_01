package com.habit.weatherforecasts_01.screen.airquality;

import com.habit.weatherforecasts_01.data.model.AirQuality;

public interface AirqualityContract {
    interface View {
        void onGetAirQualitySuccess(AirQuality airQuality);

        void onGetDataFailure(String message);
    }

    interface Presenter {
        void getAirQuality(String lat, String lon);
    }
}
