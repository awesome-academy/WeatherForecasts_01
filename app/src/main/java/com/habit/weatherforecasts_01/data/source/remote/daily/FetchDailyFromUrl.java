package com.habit.weatherforecasts_01.data.source.remote.daily;

import android.os.AsyncTask;

import com.habit.weatherforecasts_01.constant.Constant;
import com.habit.weatherforecasts_01.constant.DailyEntity;
import com.habit.weatherforecasts_01.data.model.Daily;
import com.habit.weatherforecasts_01.data.source.DailyDataSource;

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

public class FetchDailyFromUrl extends AsyncTask<String, Void, List<Daily>> {

    private DailyDataSource.OnFetchDataListener mListener;
    private Exception mException;

    public FetchDailyFromUrl(DailyDataSource.OnFetchDataListener listener) {
        mListener = listener;
    }

    @Override
    protected List<Daily> doInBackground(String... strings) {
        String url = strings[0];

        try {
            String data = getStringDataFromUrl(url);

            return getDailysFromStringData(data);
        } catch (IOException e) {
            mException = e;
        } catch (JSONException e) {
            mException = e;
        }
        return null;
    }

    private List<Daily> getDailysFromStringData(String data) throws JSONException {
        List<Daily> dailyList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(data);
        JSONObject daily = jsonObject.getJSONObject(DailyEntity.DAILY_OBJECT);
        JSONArray jsonArray = daily.getJSONArray(DailyEntity.ARRAY_DATA_DAILY);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objectDaily = (JSONObject) jsonArray.get(i);

            String time = objectDaily.getString(DailyEntity.TIME);
            Date date = new Date(Long.parseLong(time) * Constant.S_TO_MILIS);

            String weather = objectDaily.getString(DailyEntity.WEATHER);
            String icon = objectDaily.getString(DailyEntity.ICON);

            String temperatureHigh = objectDaily.getString(DailyEntity.TEMP_MAX);
            int tempMax = Math.round(Float.parseFloat(temperatureHigh));

            String temperatureLow = objectDaily.getString(DailyEntity.TEMP_MIN);
            int tempMin = Math.round(Float.parseFloat(temperatureLow));

            Daily daily1 = new Daily(date, weather, icon, tempMax, tempMin);
            dailyList.add(daily1);
        }

        return dailyList;
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
    protected void onPostExecute(List<Daily> dailies) {
        if (mListener == null) {
            return;
        }
        if (mException == null) {
            mListener.onFetchDataSuccess(dailies);
        } else {
            mListener.onFetchDataFailure(mException);
        }
    }
}
