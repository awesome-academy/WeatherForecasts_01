package com.habit.weatherforecasts_01.screen.airquality;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.data.model.AirQuality;
import com.habit.weatherforecasts_01.screen.main.MainActivity;
import com.habit.weatherforecasts_01.utils.StringUtil;

public class AirqualityFragment extends Fragment implements AirqualityContract.View {

    private AirqualityPresenter mAirqualityPresenter;

    private ConstraintLayout mLayoutFragmentAirQuality;
    private TextView mTextNumberAq;
    private ProgressBar mProgressBarAq;
    private TextView mTextStatusAq;
    private TextView mTextContentAq;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_airquality, container, false);
        mLayoutFragmentAirQuality = view.findViewById(R.id.layout_fragment_air_quality);
        mTextNumberAq = view.findViewById(R.id.text_number_aq);
        mProgressBarAq = view.findViewById(R.id.progressbar_air_quality);
        mTextStatusAq = view.findViewById(R.id.text_status_aq);
        mTextContentAq = view.findViewById(R.id.text_content_aq);

        mLayoutFragmentAirQuality
                .setBackgroundResource(StringUtil.getResIdOfBackgroundFromDevice());
        mAirqualityPresenter = new AirqualityPresenter(this);
        MainActivity mainActivity = (MainActivity) getActivity();
        String lat = mainActivity.getNowLatitude();
        String lon = mainActivity.getNowLongitude();
        mAirqualityPresenter.getAirQuality(lat, lon);
        return view;
    }

    @Override
    public void onGetAirQualitySuccess(AirQuality airQuality) {
        int aqi = airQuality.getAqi();
        mTextNumberAq.setText(String.valueOf(aqi));
        mProgressBarAq.setProgress(StringUtil.getProgressFromAqi(aqi));
        mTextStatusAq.setText(StringUtil.getStatusFromAqi(aqi, getContext()));
        mTextContentAq.setText(StringUtil.getContentFromAqi(aqi, getContext()));
    }

    @Override
    public void onGetDataFailure(String message) {
    }
}
