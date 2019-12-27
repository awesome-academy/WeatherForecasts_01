package com.habit.weatherforecasts_01.data.source.remote.hourly;

import android.os.AsyncTask;

import com.habit.weatherforecasts_01.constant.Constant;
import com.habit.weatherforecasts_01.constant.HourlyEntity;
import com.habit.weatherforecasts_01.data.model.Hourly;
import com.habit.weatherforecasts_01.data.source.HourlyDataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FetchHourlyFromUrl extends AsyncTask<String, Void, List<Hourly>> {

    private HourlyDataSource.OnFetchDataListener mListener;
    private Exception mException;

    public FetchHourlyFromUrl(HourlyDataSource.OnFetchDataListener listener) {
        mListener = listener;
    }

    @Override
    protected List<Hourly> doInBackground(String... strings) {
        String url = strings[0];

        try {
            String data = getStringDataFromUrl(url);
            return getHourlysFromStringData(data);
        } catch (IOException e) {
            mException = e;
        } catch (JSONException e) {
            mException = e;
        }
        return null;
    }

    private List<Hourly> getHourlysFromStringData(String data) throws JSONException {
        List<Hourly> hourlyList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(data);
        JSONObject hourly = jsonObject.getJSONObject(HourlyEntity.HOURLY_OBJECT);
        JSONArray jsonArray = hourly.getJSONArray(HourlyEntity.ARRAY_DATA_HOURLY);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objectHourly = (JSONObject) jsonArray.get(i);

            String time = objectHourly.getString(HourlyEntity.TIME);
            Date date = new Date(Long.parseLong(time) * Constant.S_TO_MILIS);

            String temperature = objectHourly.getString(HourlyEntity.TEMP);
            int temp = Math.round(Float.parseFloat(temperature));

            String weather = objectHourly.getString(HourlyEntity.WEATHER);
            String icon = objectHourly.getString(HourlyEntity.ICON);

            Hourly hourly1 = new Hourly(date, temp, weather, icon);
            hourlyList.add(hourly1);
        }
        return hourlyList;
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
        br.close();
        connection.disconnect();
        return sb.toString();
    }

    @Override
    protected void onPostExecute(List<Hourly> hourlies) {
        if (mListener == null) {
            return;
        }
        if (mException == null) {
            mListener.onFetchDataSuccess(hourlies);
        } else {
            mListener.onFetchDataFailure(mException);
        }
    }
}
