package com.habit.weatherforecasts_01.screen.airquality;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.constant.Constant;
import com.habit.weatherforecasts_01.data.model.AirQuality;

public class AirqualityFragment extends Fragment implements AirqualityContract.View {

    private AirqualityPresenter mAirqualityPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_airquality, container, false);

        mAirqualityPresenter = new AirqualityPresenter(this);
        mAirqualityPresenter.getAirQuality(Constant.LATITUDE_HANOI, Constant.LONGITUDE_HANOI);
        return view;
    }

    @Override
    public void onGetAirQualitySuccess(AirQuality airQuality) {
    }

    @Override
    public void onGetDataFailure(String message) {
    }
}
