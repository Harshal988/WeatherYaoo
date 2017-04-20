package com.example.lanovo.weatheryahoo.Service;

import com.example.lanovo.weatheryahoo.Data.Channel;

/**
 * Created by lanovo on 15/02/2017.
 */

public interface WeatherServiceCallBack {
    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);


}
