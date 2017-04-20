package com.example.lanovo.weatheryahoo.Data;

import org.json.JSONObject;

/**
 * Created by lanovo on 15/02/2017.
 */

public class Condition implements JSONPopulator {
    private int code;
    private int tempareture;
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getTempareture() {
        return tempareture;
    }

    @Override
    public void populate(JSONObject data) {
code = data.optInt("code");
        tempareture = data.optInt("temp");
        description = data.optString("text");
    }
}
