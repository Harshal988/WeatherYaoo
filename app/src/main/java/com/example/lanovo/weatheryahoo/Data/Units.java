package com.example.lanovo.weatheryahoo.Data;

import org.json.JSONObject;

/**
 * Created by lanovo on 15/02/2017.
 */

public class Units implements JSONPopulator {
    private String tempareture;

    public String getTempareture() {
        return tempareture;
    }

    @Override
    public void populate(JSONObject data) {
tempareture = data.optString("tempareture");
    }
}
