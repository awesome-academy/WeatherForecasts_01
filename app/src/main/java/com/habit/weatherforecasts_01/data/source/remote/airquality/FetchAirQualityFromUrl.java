package com.habit.weatherforecasts_01.data.source.remote.airquality;

import android.os.AsyncTask;

import com.habit.weatherforecasts_01.constant.AirQualityEntity;
import com.habit.weatherforecasts_01.constant.Constant;
import com.habit.weatherforecasts_01.data.model.AirQuality;
import com.habit.weatherforecasts_01.data.source.AirQualityDataSource;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchAirQualityFromUrl extends AsyncTask<String, Void, AirQuality> {
    private AirQualityDataSource.OnFetchDataListener mListener;
    private Exception mException;

    public FetchAirQualityFromUrl(AirQualityDataSource.OnFetchDataListener listener) {
        mListener = listener;
    }

    @Override
    protected AirQuality doInBackground(String... strings) {
        String url = strings[0];
        try {
            String data = getStringDataFromUrl(url);

            return getAirQualityFromStringData(data);
        } catch (IOException e) {
            mException = e;
        } catch (JSONException e) {
            mException = e;
        }
        return null;
    }

    private AirQuality getAirQualityFromStringData(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        JSONObject dataObject = jsonObject.getJSONObject(AirQualityEntity.DATA_OBJECT);
        int aqi = Integer.parseInt(dataObject.getString(AirQualityEntity.AQI));

        AirQuality airQuality = new AirQuality(aqi);
        return airQuality;
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
    protected void onPostExecute(AirQuality airQuality) {
        if (mListener == null) {
            return;
        }
        if (mException == null) {
            mListener.onFetchAirQualitySuccess(airQuality);
        } else {
            mListener.onFetchDataFailure(mException);
        }
    }
}
