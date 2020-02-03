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
import com.habit.weatherforecasts_01.data.model.Hourly;
import com.habit.weatherforecasts_01.utils.StringUtil;

import java.util.List;

public class WeatherHourAdapter extends RecyclerView.Adapter<WeatherHourAdapter.ViewHolder> {
    private List<Hourly> mHourlyList;

    public WeatherHourAdapter(List<Hourly> hourlyList) {
        mHourlyList = hourlyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather_hour,
                        parent,
                        false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hourly hourly = mHourlyList.get(position);
        holder.mTextHourWh.setText(StringUtil.getStringHourFromDate(hourly.getDate()));
        holder.mTextTempWh.setText(StringUtil.getCelsius(hourly.getTemp()));
        holder.mIconWeatherWh.setImageResource(
                StringUtil.getResIdOfIconFromName(hourly.getIcon()));
    }

    @Override
    public int getItemCount() {
        return mHourlyList == null ? 0 : mHourlyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextHourWh;
        private TextView mTextTempWh;
        private ImageView mIconWeatherWh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextHourWh = itemView.findViewById(R.id.text_hour_wh);
            mTextTempWh = itemView.findViewById(R.id.text_temp_wh);
            mIconWeatherWh = itemView.findViewById(R.id.icon_weather_wh);
        }
    }

}
