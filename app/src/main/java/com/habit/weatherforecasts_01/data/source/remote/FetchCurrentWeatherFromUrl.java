package com.habit.weatherforecasts_01.data.source.remote;

import android.os.AsyncTask;

import com.habit.weatherforecasts_01.constant.Constant;
import com.habit.weatherforecasts_01.constant.CurrentWeatherEntity;
import com.habit.weatherforecasts_01.data.model.CurrentWeather;
import com.habit.weatherforecasts_01.data.source.CurrentWeatherDataSource;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class FetchCurrentWeatherFromUrl extends AsyncTask<String, Void, CurrentWeather> {

    private CurrentWeatherDataSource.OnFetchDataListener mListener;
    private Exception mException;

    public FetchCurrentWeatherFromUrl(CurrentWeatherDataSource.OnFetchDataListener listener) {
        mListener = listener;
    }

    @Override
    protected CurrentWeather doInBackground(String... strings) {
        String url = strings[0];
        try {
            String data = getStringDataFromUrl(url);

            return getCurrentWeatherFromStringData(data);
        } catch (IOException e) {
            mException = e;
        } catch (JSONException e) {
            mException = e;
        }
        return null;
    }

    private CurrentWeather getCurrentWeatherFromStringData(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        JSONObject currently = jsonObject.getJSONObject(CurrentWeatherEntity.CURRENT_OBJECT);

        String time = currently.getString(CurrentWeatherEntity.TIME);
        Date date = new Date(Long.parseLong(time) * Constant.S_TO_MILIS);

        String temperature = currently.getString(CurrentWeatherEntity.TEMP);
        int temp = Math.round(Float.parseFloat(temperature));

        String weather = currently.getString(CurrentWeatherEntity.WEATHER);
        String icon = currently.getString(CurrentWeatherEntity.ICON);

        CurrentWeather currentWeather = new CurrentWeather(date, temp, weather, icon);

        return currentWeather;
    }

    private String getStringDataFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(Constant.REQUEST_METHOD_GET);
        connection.setConnectTimeout(Constant.CONNECT_TIME_OUT);
        connection.setReadTimeout(Constant.READ_TIME_OUT);
        connection.setDoOutput(true);

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append(Constant.BREAK_LINE);
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(CurrentWeather currentWeather) {
        if (mListener == null) {
            return;
        }
        if (mException == null) {
            mListener.onFetchDataSuccess(currentWeather);
        } else {
            mListener.onFetchDataFailure(mException);
        }
    }
}
