package com.habit.weatherforecasts_01.screen.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.constant.Constant;
import com.habit.weatherforecasts_01.data.model.AirQuality;
import com.habit.weatherforecasts_01.data.model.CurrentWeather;
import com.habit.weatherforecasts_01.data.model.Daily;
import com.habit.weatherforecasts_01.data.model.Hourly;
import com.habit.weatherforecasts_01.screen.main.MainActivity;
import com.habit.weatherforecasts_01.utils.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodayFragment extends Fragment implements TodayContract.View {

    private TodayPresenter mTodayPresenter;
    private WeatherDayAdapter mWeatherDayAdapter;
    private RecyclerView mRecyclerDayBar;
    private WeatherHourAdapter mWeatherHourAdapter;
    private RecyclerView mRecyclerHourBar;

    private ConstraintLayout mLayoutFragmentToday;
    private ImageView mImageWeatherNow;
    private TextView mTextTempNow;
    private TextView mTextWeatherNow;
    private TextView mTextDayNow;
    private TextView mTextAddressNow;
    private TextView mTextWeatherDay;
    private TextView mTextTempMax;
    private TextView mTextTempMin;

    private TextView mTextNumberAqToday;
    private TextView mTextStatusAqToday;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        mLayoutFragmentToday = view.findViewById(R.id.layout_fragment_today);
        mImageWeatherNow = view.findViewById(R.id.image_weather_now);
        mTextTempNow = view.findViewById(R.id.text_temp_now);
        mTextWeatherNow = view.findViewById(R.id.text_weather_now);
        mTextWeatherDay = view.findViewById(R.id.text_weather_day);

        mTextAddressNow = view.findViewById(R.id.text_address_now);
        mTextDayNow = view.findViewById(R.id.text_day_now);
        mTextTempMax = view.findViewById(R.id.text_temp_max);
        mTextTempMin = view.findViewById(R.id.text_temp_min);
        mTextNumberAqToday = view.findViewById(R.id.text_number_aq_today);
        mTextStatusAqToday = view.findViewById(R.id.text_status_aq_today);

        mRecyclerHourBar = view.findViewById(R.id.recycler_hourly_bar);
        mRecyclerDayBar = view.findViewById(R.id.recycler_daily_bar);

        mLayoutFragmentToday.setBackgroundResource(StringUtil.getResIdOfBackgroundFromDevice());
        mTodayPresenter = new TodayPresenter(this);
        MainActivity mainActivity = (MainActivity) getActivity();
        String lat = mainActivity.getNowLatitude();
        String lon = mainActivity.getNowLongitude();
        String address = mainActivity.getNowAddress();
        mTextAddressNow.setText(address);
        mTodayPresenter.getCurrentWeather(lat, lon);
        mTodayPresenter.getDailyList(lat, lon);
        mTodayPresenter.getHourlyList(lat, lon);
        mTodayPresenter.getAirQuality(lat, lon);
        return view;
    }

    @Override
    public void onGetAirQualitySuccess(AirQuality airQuality) {
        int aqi = airQuality.getAqi();
        mTextNumberAqToday.setText(String.valueOf(aqi));
        mTextStatusAqToday.setText(StringUtil.getStatusFromAqi(aqi, getContext()));
    }

    @Override
    public void onGetCurrentSuccess(CurrentWeather currentWeather) {
        String icon = currentWeather.getIcon();
        mImageWeatherNow.setImageResource(StringUtil.getResIdOfIconFromName(icon));

        int degreeF = currentWeather.getTemp();
        mTextTempNow.setText(StringUtil.getCelsius(degreeF));

        mTextWeatherNow.setText(currentWeather.getWeather());

        Date date = currentWeather.getDate();
        mTextDayNow.setText(StringUtil.getStringYearMonthDayFromDate(date));
    }

    @Override
    public void onGetDataFailure(String message) {
    }

    @Override
    public void onGetHourlySuccess(List<Hourly> hourlyList) {
        List<Hourly> weatherHourInTodayFrag = new ArrayList<>();
        weatherHourInTodayFrag.add(hourlyList.get(Constant.INDEX_0));
        weatherHourInTodayFrag.add(hourlyList.get(Constant.INDEX_1));
        weatherHourInTodayFrag.add(hourlyList.get(Constant.INDEX_2));
        weatherHourInTodayFrag.add(hourlyList.get(Constant.INDEX_3));
        mWeatherHourAdapter = new WeatherHourAdapter(weatherHourInTodayFrag);
        mRecyclerHourBar.setAdapter(mWeatherHourAdapter);
        mWeatherHourAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetDailySuccess(List<Daily> dailyList) {
        int tempMaxInTodayF = dailyList.get(Constant.INDEX_0).getTempMax();
        mTextTempMax.setText(StringUtil.getCelsius(tempMaxInTodayF));

        int tempMinInTodayF = dailyList.get(Constant.INDEX_0).getTempMin();
        mTextTempMin.setText(StringUtil.getCelsius(tempMinInTodayF));

        mTextWeatherDay.setText(dailyList.get(Constant.INDEX_0).getWeather());

        List<Daily> weatherDayInTodayFrag = new ArrayList<>();
        weatherDayInTodayFrag.add(dailyList.get(Constant.INDEX_0));
        weatherDayInTodayFrag.add(dailyList.get(Constant.INDEX_1));
        weatherDayInTodayFrag.add(dailyList.get(Constant.INDEX_2));
        weatherDayInTodayFrag.add(dailyList.get(Constant.INDEX_3));
        mWeatherDayAdapter = new WeatherDayAdapter(weatherDayInTodayFrag);
        mRecyclerDayBar.setAdapter(mWeatherDayAdapter);
        mWeatherDayAdapter.notifyDataSetChanged();
    }
}
