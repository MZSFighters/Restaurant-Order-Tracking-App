package com.example.ordertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewUserSuccessStaff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_new_user_success);

        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setBackgroundColor(Color.parseColor("#FF3700B3"));
        setContentView(l);

        TextView t = new TextView(this);
        TextView t2 = new TextView(this);


        String success = getIntent().getStringExtra("SuccessKey");

        t.setText(success);
        t.setTextColor(Color.parseColor("#F57C00"));
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);

        t2.setTextColor(Color.parseColor("#F57C00"));
        t2.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);


        l.addView(t);
        l.addView(t2);

        Button backBtn = new Button(this);
        backBtn.setText("Back");
        backBtn.setTextColor(Color.parseColor("#F57C00"));
        backBtn.getBackground().setColorFilter(Color.parseColor("#FF6200EE"), PorterDuff.Mode.MULTIPLY);
        l.addView(backBtn);



        PHPRequest req = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
        ContentValues cv = new ContentValues();

        req.doRequest(NewUserSuccessStaff.this, "staffid", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) throws JSONException {

                JSONArray ja = new JSONArray(response);
                JSONObject jo = ja.getJSONObject(ja.length() -1);
                String str = jo.getString("STAFF_ID");
                t2.setText("Staff ID : " + str);


            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewUserSuccessStaff.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}