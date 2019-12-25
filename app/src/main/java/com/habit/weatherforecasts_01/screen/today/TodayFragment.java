package com.habit.weatherforecasts_01.screen.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.constant.Constant;
import com.habit.weatherforecasts_01.data.model.CurrentWeather;

public class TodayFragment extends Fragment implements TodayContract.View {

    private TodayPresenter mTodayPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        mTodayPresenter = new TodayPresenter(this);
        mTodayPresenter.getCurrentWeather(Constant.LATITUDE_HANOI, Constant.LONGITUDE_HANOI);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onGetCurrentSuccess(CurrentWeather currentWeather) {

    }

    @Override
    public void onGetCurrentFailure(String message) {

    }
}
