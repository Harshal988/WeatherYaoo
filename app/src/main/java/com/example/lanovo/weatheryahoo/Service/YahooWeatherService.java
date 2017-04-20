package com.example.lanovo.weatheryahoo.Service;

import android.net.Uri;
import android.os.AsyncTask;
import com.example.lanovo.weatheryahoo.Data.Channel;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class YahooWeatherService {
    private WeatherServiceCallBack callBack;
    private String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallBack callBack) {
        this.callBack = callBack;
    }


    public String getLocation() {
        return location;
    }

    public void refreshWeather(String l){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"nome, ak\"))",params[0]);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys", Uri.encode(YQL));
                try
                {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = reader.readLine())!= null)
                    {
                        result.append(line);
                    }
                    return result.toString();
                }catch(Exception e){
                    error = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null && error != null){
                    callBack.serviceFailure(error);
                    return;
                }
                try{
                    JSONObject data = new JSONObject(s);
                    JSONObject queryresult = data.optJSONObject("query");
                    int count = queryresult.getInt("count");

                    if(count == 0){
                        callBack.serviceFailure(new LocationWeatherException("No Weather information found for "+ location));
                        return;
                    }
                    Channel channel = new Channel();
                    channel.populate(queryresult.optJSONObject("result").optJSONObject("channel "));
                }catch (JSONException e){
                    e.printStackTrace();
                }
                super.onPostExecute(s);
            }
        }.execute(location);
    }
    public class LocationWeatherException extends Exception{
        public LocationWeatherException(String message) {
            super(message);
        }
    }
}
