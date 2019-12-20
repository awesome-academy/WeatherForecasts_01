package com.habit.weatherforecasts_01.data.source.remote;

import android.os.AsyncTask;

import com.habit.weatherforecasts_01.constant.Constant;
import com.habit.weatherforecasts_01.constant.CurrentWeatherEntity;
import com.habit.weatherforecasts_01.data.model.CurrentWeather;
import com.habit.weatherforecasts_01.data.source.CurrentWeatherDataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FetchCurrentWeatherFromUrl extends AsyncTask<String, Void, CurrentWeather> {

    private CurrentWeatherDataSource.OnFetchDataListener<CurrentWeather> mListener;
    private Exception mException;

    public void setListener(CurrentWeatherDataSource.OnFetchDataListener<CurrentWeather> listener) {
        mListener = listener;
    }

    @Override
    protected CurrentWeather doInBackground(String... strings) {
        String url = strings[0];

        try {
            String data = getStringDataFromUrl(url);
            return getCurrentFromStringData(data);
        } catch (IOException e) {
            mException = e;
        } catch (JSONException e) {
            mException = e;
        }

        return null;
    }

    private CurrentWeather getCurrentFromStringData(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        String address = jsonObject.getString(CurrentWeatherEntity.ADDRESS);

        String timezone = jsonObject.getString(CurrentWeatherEntity.TIMEZONE);
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone(timezone));
        Date date = cal.getTime();

        JSONObject mainObject = jsonObject.getJSONObject(CurrentWeatherEntity.MAIN_WEATHER);
        float f = Float.parseFloat(mainObject.getString(CurrentWeatherEntity.WEATHER_TEMP));
        float f1 = f - Float.parseFloat(CurrentWeatherEntity.NUMBER_KELVIN);
        int temp = Math.round(f1);

        JSONArray weatherArray = jsonObject.getJSONArray(CurrentWeatherEntity.WEATHER);
        JSONObject weatherObject = (JSONObject) weatherArray.get(0);
        String status = weatherObject.getString(CurrentWeatherEntity.MAIN_STATUS);
        String description = weatherObject.getString(CurrentWeatherEntity.DESCRIPTION);

        CurrentWeather currentWeather = new CurrentWeather(address, date, temp, status, description);

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
        while ((line = br.readLine())!= null){
            sb.append(line).append(Constant.BREAK_LINE);
        }

        return sb.toString();
    }

    @Override
    protected void onPostExecute(CurrentWeather currentWeather) {
        if(mListener == null){
            return;
        }
        if(mException == null){
            mListener.onFetchDataSuccess(currentWeather);
        }else {
            mListener.onFetchDataFailure(mException);
        }
    }
}
