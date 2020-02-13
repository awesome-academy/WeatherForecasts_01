package com.habit.weatherforecasts_01.screen.daily;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.data.model.Daily;
import com.habit.weatherforecasts_01.screen.main.MainActivity;
import com.habit.weatherforecasts_01.utils.StringUtil;

import java.util.List;

public class DailyFragment extends Fragment implements DailyContract.View {

    private DailyPresenter mDailyPresenter;
    private DailyAdapter mDailyAdapter;
    private RecyclerView mRecyclerDaily;

    private ConstraintLayout mLayoutFragmentDaily;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        mLayoutFragmentDaily = view.findViewById(R.id.layout_fragment_daily);

        mLayoutFragmentDaily.setBackgroundResource(StringUtil.getResIdOfBackgroundFromDevice());
        mDailyPresenter = new DailyPresenter(this);
        MainActivity mainActivity = (MainActivity) getActivity();
        String lat = mainActivity.getNowLatitude();
        String lon = mainActivity.getNowLongitude();
        mDailyPresenter.getDailyList(lat, lon);
        mRecyclerDaily = view.findViewById(R.id.recycler_daily);
        return view;
    }

    @Override
    public void onGetDailySuccess(List<Daily> dailyList) {
        mDailyAdapter = new DailyAdapter(dailyList);
        mRecyclerDaily.setAdapter(mDailyAdapter);
        mDailyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetDailyFailure(String message) {

    }
}
