package com.habit.weatherforecasts_01.screen.hourly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.constant.Constant;
import com.habit.weatherforecasts_01.data.model.Hourly;

import java.util.List;

public class HourlyFragment extends Fragment implements HourlyContract.View {

    private HourlyPresenter mHourlyPresenter;
    private HourlyAdapter mHourlyAdapter;
    private RecyclerView mRecyclerHourly;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hourly, container, false);
        mHourlyPresenter = new HourlyPresenter(this);
        mHourlyPresenter.getHourlyList(Constant.LATITUDE_HANOI, Constant.LONGITUDE_HANOI);
        mRecyclerHourly = view.findViewById(R.id.recycler_hourly);
        return view;
    }

    @Override
    public void onGetHourlySuccess(List<Hourly> hourlyList) {
        mHourlyAdapter = new HourlyAdapter(hourlyList);
        mRecyclerHourly.setAdapter(mHourlyAdapter);
        mHourlyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetHourlyFailure(String message) {

    }
}
