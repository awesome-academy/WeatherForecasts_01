package com.habit.weatherforecasts_01.screen.hourly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.habit.weatherforecasts_01.R;
import com.habit.weatherforecasts_01.data.model.Hourly;
import com.habit.weatherforecasts_01.utils.StringUtil;

import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder>{
    private List<Hourly> mHourlyList;

    public HourlyAdapter(List<Hourly> hourlyList) {
        mHourlyList = hourlyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hourly,
                        parent,
                        false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hourly hourly = mHourlyList.get(position);
        holder.mIconWeatherHourly.setImageResource(
                StringUtil.getResIdOfIconFromName(hourly.getIcon()));
        holder.mTextHourOfHourly.setText(StringUtil.getStringHourFromDate(hourly.getDate()));
        holder.mTextDayOfHourly.setText(StringUtil.getStringDayFromDate(hourly.getDate()));
        holder.mTextWeatherHourly.setText(hourly.getWeather());
        holder.mTextTempHourly.setText(StringUtil.getCelsius(hourly.getTemp()));
    }

    @Override
    public int getItemCount() {
        return mHourlyList == null ? 0 : mHourlyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIconWeatherHourly;
        private TextView mTextHourOfHourly;
        private TextView mTextDayOfHourly;
        private TextView mTextWeatherHourly;
        private TextView mTextTempHourly;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIconWeatherHourly = itemView.findViewById(R.id.icon_weather_hourly);
            mTextHourOfHourly = itemView.findViewById(R.id.text_hour_hourly);
            mTextDayOfHourly = itemView.findViewById(R.id.text_day_hourly);
            mTextWeatherHourly = itemView.findViewById(R.id.text_weather_hourly);
            mTextTempHourly = itemView.findViewById(R.id.text_temp_hourly);
        }
    }

}
