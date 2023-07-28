package com.example.ordertracker;

import android.app.Activity;
import android.content.ContentValues;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PHPRequest {
    String url;
    public PHPRequest(String prefix){
        url = prefix;
    }

    public void doRequest(Activity a, String method, ContentValues params, RequestHandler rh){
        OkHttpClient client = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();
        for (String key:params.keySet()){
            builder.add(key, params.getAsString(key));
        }


        Request request = new Request.Builder().url(url+method+".php").post(builder.build()).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call arg0, IOException arg1) {

                arg1.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseData = response.body().string();
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            rh.processResponse(responseData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

}