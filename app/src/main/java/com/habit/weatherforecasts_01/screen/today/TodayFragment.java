package com.habit.weatherforecasts_01.screen.today;

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
import com.habit.weatherforecasts_01.data.model.CurrentWeather;
import com.habit.weatherforecasts_01.data.model.Daily;
import com.habit.weatherforecasts_01.data.model.Hourly;
import com.habit.weatherforecasts_01.utils.StringUtil;

import java.util.Date;
import java.util.List;

public class TodayFragment extends Fragment implements TodayContract.View {

    private TodayPresenter mTodayPresenter;
    private TextView mTextTempNow;
    private TextView mTextWeatherNow;
    private TextView mTextDayNow;
    private TextView mTextHourNow;
    private TextView mTextTempDay;
    private TextView mTextTempMax;
    private TextView mTextTempMin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        mTextTempNow = view.findViewById(R.id.text_temp_now);
        mTextWeatherNow = view.findViewById(R.id.text_weather_now);
        mTextDayNow = view.findViewById(R.id.text_day_now);
        mTextHourNow = view.findViewById(R.id.text_hour_now);
        mTextTempDay = view.findViewById(R.id.text_temp_day);
        mTextTempMax = view.findViewById(R.id.text_temp_max);
        mTextTempMin = view.findViewById(R.id.text_temp_min);

        mTodayPresenter = new TodayPresenter(this);
        mTodayPresenter.getCurrentWeather(Constant.LATITUDE_HANOI, Constant.LONGITUDE_HANOI);
        mTodayPresenter.getDailyList(Constant.LATITUDE_HANOI, Constant.LONGITUDE_HANOI);
        mTodayPresenter.getHourlyList(Constant.LATITUDE_HANOI, Constant.LONGITUDE_HANOI);
        return view;
    }

    @Override
    public void onGetCurrentSuccess(CurrentWeather currentWeather) {
        int degreeF = currentWeather.getTemp();
        mTextTempNow.setText(StringUtil.getCelsius(degreeF));

        mTextWeatherNow.setText(currentWeather.getWeather());

        Date date = currentWeather.getDate();
        mTextDayNow.setText(StringUtil.getStringDayFromDate(date));
        mTextHourNow.setText(StringUtil.getStringHourFromDate(date));
    }

    @Override
    public void onGetDataFailure(String message) {

    }

    @Override
    public void onGetHourlySuccess(List<Hourly> hourlyList) {

    }

    @Override
    public void onGetDailySuccess(List<Daily> dailyList) {
        int tempMaxF = dailyList.get(0).getTempMax();
        mTextTempMax.setText(StringUtil.getCelsius(tempMaxF));

        int tempMinF = dailyList.get(0).getTempMin();
        mTextTempMin.setText(StringUtil.getCelsius(tempMinF));

        mTextTempDay.setText(StringUtil.getTempDayC(tempMaxF, tempMinF));
    }
}
