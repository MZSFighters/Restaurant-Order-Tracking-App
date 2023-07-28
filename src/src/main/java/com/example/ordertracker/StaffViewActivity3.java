package com.example.ordertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StaffViewActivity3 extends AppCompatActivity {

    LinearLayout container;
    String ordersRef;
    String StaffID;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout RelLay = new RelativeLayout(this);

        ScrollView sv = new ScrollView(this);
        sv.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.MATCH_PARENT));
        sv.setBackgroundColor(Color.parseColor("#FF3700B3"));

        RelLay.addView(sv);

        LinearLayout l = new LinearLayout(this);
        l.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        l.setOrientation(LinearLayout.VERTICAL);
        l.setBackgroundColor(Color.parseColor("#FF3700B3"));

        StaffID = getIntent().getStringExtra("StaffID");


        Toast.makeText(StaffViewActivity3.this, "To edit the status of an order, click on the order",
                Toast.LENGTH_LONG).show();

        PHPRequest req = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
        ContentValues cv = new ContentValues();
        cv.put("STAFF_ID", StaffID);

        req.doRequest(StaffViewActivity3.this, "staffview", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                try {
                    processJSON(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Button backBtn = new Button(this);
        backBtn.setText("Back");
        backBtn.setTextColor(Color.parseColor("#F57C00"));
        //backBtn.setBackgroundColor(Color.parseColor("#FF6200EE"));
        //backBtn.setPadding(0,30,0,40);
        backBtn.getBackground().setColorFilter(Color.parseColor("#FF6200EE"), PorterDuff.Mode.MULTIPLY);
        backBtn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        l.addView(backBtn);

        Button helpBtn = new Button(this);
        helpBtn.setText("Help");
        helpBtn.setTextColor(Color.parseColor("#F57C00"));
        helpBtn.getBackground().setColorFilter(Color.parseColor("#FF6200EE"), PorterDuff.Mode.MULTIPLY);
        //helpBtn.setPadding(0,30,0,40);
        backBtn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        l.addView(helpBtn);

        container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        l.addView(container);

        sv.addView(l);
        setContentView(RelLay);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffViewActivity3.this, StaffActivity.class);
                intent.putExtra("StaffID", StaffID);
                startActivity(intent);
            }
        });

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StaffViewActivity3.this, "To edit the status of an order, click on the order",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("Range")
    public void processJSON(String json) throws JSONException {
        JSONArray ja = new JSONArray(json);
        for(int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            OrdersLayout ol = new OrdersLayout(this);
            ol.populate(jo);
            ol.setBackgroundColor(Color.parseColor("#F57C00"));
            if(i % 2 == 0) {
                ol.setBackgroundColor(Color.parseColor("#EEEEFF"));
            }
            container.addView(ol);

            ol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        ordersRef = jo.getString("ORDER_NO");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(StaffViewActivity3.this, StaffEditActivity.class);
                    intent.putExtra("ordersRef", ordersRef);
                    intent.putExtra("StaffID", StaffID);
                    startActivity(intent);

                }
            });
        }
    }
}