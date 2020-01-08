package com.habit.weatherforecasts_01.screen.daily;

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

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {
    private List<Daily> mDailyList;

    public DailyAdapter(List<Daily> dailyList) {
        mDailyList = dailyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_daily,
                        parent,
                        false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Daily daily = mDailyList.get(position);
        holder.mIconWeatherDaily.setImageResource(
                StringUtil.getResIdOfIconFromName(daily.getIcon()));
        holder.mTextDayOfDaily.setText(StringUtil.getSecondStringDayFromDate(daily.getDate()));
        holder.mTextTempDayOfDaily.setText(
                StringUtil.getTempDayC(daily.getTempMax(), daily.getTempMin()));
        holder.mTextWeatherDaily.setText(daily.getWeather());
    }

    @Override
    public int getItemCount() {
        return mDailyList == null ? 0 : mDailyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIconWeatherDaily;
        private TextView mTextDayOfDaily;
        private TextView mTextTempDayOfDaily;
        private TextView mTextWeatherDaily;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIconWeatherDaily = itemView.findViewById(R.id.icon_weather_daily);
            mTextDayOfDaily = itemView.findViewById(R.id.text_day_daily);
            mTextTempDayOfDaily = itemView.findViewById(R.id.text_temp_day_daily);
            mTextWeatherDaily = itemView.findViewById(R.id.text_weather_daily);
        }
    }

}
