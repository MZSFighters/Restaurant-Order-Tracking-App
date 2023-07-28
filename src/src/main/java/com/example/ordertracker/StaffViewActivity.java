package com.example.ordertracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StaffViewActivity extends AppCompatActivity {

    LinearLayout container;

    public void processJson(String json) throws JSONException {
        JSONArray ja = new JSONArray(json);
        for(int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            String staffID = jo.getString("RESTAURANT");
            TextView t = new TextView(this);
            t.setText(staffID);
            t.setTextColor(Color.WHITE);
            container.addView(t);
        }

    }

    Button btn, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_view);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout1);
        container = new LinearLayout(this);
        layout.addView(container);

        TextView t = (TextView) findViewById(R.id.viewOrders);

        PHPRequest req = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
        ContentValues cv = new ContentValues();

        req.doRequest(StaffViewActivity.this, "ordersview", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                try {
                    processJson(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        btn = findViewById(R.id.viewBack);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffViewActivity.this, StaffActivity.class);
                startActivity(intent);
            }
        });
    }
}



/*
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://lamp.ms.wits.ac.za/~s2433079/ins.php")
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    t.setText(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }); */




