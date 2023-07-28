package com.example.ordertracker;

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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StaffViewActivity2 extends AppCompatActivity {
    LinearLayout container;

    public void processJSON(String json) throws JSONException {
        JSONArray ja = new JSONArray(json);
        for(int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            OrdersLayout ol = new OrdersLayout(this);
            ol.populate(jo);
            container.addView(ol);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        setContentView(l);
        TextView t = new TextView(this);
        l.addView(t);
        t.setText("This is where i am");


        Button backbtn = new Button(this);
        backbtn.setText("Back");
        l.addView(backbtn);

        Button testbtn = new Button(this);
        testbtn.setText("Test");
        l.addView(testbtn);

        container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        l.addView(container);

        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PHPRequest req = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
                ContentValues cv = new ContentValues();

                req.doRequest(StaffViewActivity2.this, "ordersview", cv, new RequestHandler() {
                    @Override
                    public void processResponse(String response) {
                        try {
                            processJSON(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });



                backbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(StaffViewActivity2.this, StaffActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
/*
        PHPRequest req = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
        ContentValues cv = new ContentValues();

        req.doRequest(StaffViewActivity2.this, "ordersview", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                try {
                    processJSON(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffViewActivity2.this, StaffActivity.class);
                startActivity(intent);
            }
        });

 */

    }
}