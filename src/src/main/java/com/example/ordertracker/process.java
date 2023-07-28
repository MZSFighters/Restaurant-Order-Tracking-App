package com.example.ordertracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class process {
    public void processActivity(String json){
        try {
            JSONArray ja = new JSONArray(json);
            for(int i = 0; i < ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                String order = jo.getString("RestaurantName");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
