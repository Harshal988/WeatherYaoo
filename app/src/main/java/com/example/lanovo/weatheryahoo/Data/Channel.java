package com.example.lanovo.weatheryahoo.Data;

import org.json.JSONObject;

/**
 * Created by lanovo on 15/02/2017.
 */

public class Channel implements JSONPopulator {
    private Units units;
    private Item item;

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    @Override
    public void populate(JSONObject data) {

        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));
    }
}
