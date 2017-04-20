package com.example.lanovo.weatheryahoo.Data;

import org.json.JSONObject;

/**
 * Created by lanovo on 15/02/2017.
 */

public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

    }
}
