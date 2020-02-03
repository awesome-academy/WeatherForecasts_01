package com.habit.weatherforecasts_01.screen.today;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.data.model.Daily;
import com.habit.weatherforecasts_01.utils.StringUtil;

import java.util.List;

public class WeatherDayAdapter extends RecyclerView.Adapter<WeatherDayAdapter.ViewHolder> {
    private List<Daily> mDailyList;
    public WeatherDayAdapter(List<Daily> dailyList) {
        mDailyList = dailyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather_day,
                        parent,
                        false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Daily daily = mDailyList.get(position);
        holder.mTextDayInWeekWd.setText(StringUtil.getDayInWeekFromDate(daily.getDate()));
        holder.mIconWeatherWd.setImageResource(
                StringUtil.getResIdOfIconFromName(daily.getIcon()));
        holder.mTextTempDayWd.setText(
                StringUtil.getTempDayC(daily.getTempMax(), daily.getTempMin()));
    }

    @Override
    public int getItemCount() {
        return mDailyList == null ? 0 : mDailyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextDayInWeekWd;
        private ImageView mIconWeatherWd;
        private TextView mTextTempDayWd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextDayInWeekWd = itemView.findViewById(R.id.text_day_in_week_wd);
            mIconWeatherWd = itemView.findViewById(R.id.icon_weather_wd);
            mTextTempDayWd = itemView.findViewById(R.id.text_temp_day_wd);
        }
    }

}
