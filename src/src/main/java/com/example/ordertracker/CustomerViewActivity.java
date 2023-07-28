package com.example.ordertracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomerViewActivity extends AppCompatActivity {

    LinearLayout container;
    String ordersRef;
    String CustID;

    @SuppressLint({"ResourceAsColor", "Range"})
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



        CustID = getIntent().getStringExtra("CustID");

        Toast.makeText(CustomerViewActivity.this, "To rate an order, click on an order",
                Toast.LENGTH_LONG).show();

        Button backBtn = new Button(this);
        backBtn.setText("Back");
        backBtn.setTextColor(Color.parseColor("#F57C00"));
        backBtn.getBackground().setColorFilter(Color.parseColor("#FF6200EE"), PorterDuff.Mode.MULTIPLY);
        l.addView(backBtn);

        Button helpBtn = new Button(this);
        helpBtn.setText("Help");
        helpBtn.setTextColor(Color.parseColor("#F57C00"));
        helpBtn.getBackground().setColorFilter(Color.parseColor("#FF6200EE"), PorterDuff.Mode.MULTIPLY);
        l.addView(helpBtn);


        TextView title = new TextView(this);
        title.setText("Your Orders");
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        title.setTextColor(Color.parseColor("#F57C00"));
        l.addView(title);


        PHPRequest req = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
        ContentValues cv = new ContentValues();
        cv.put("CUST_ID", CustID);

        req.doRequest(CustomerViewActivity.this, "custview", cv, new RequestHandler() {
            @Override
            public void processResponse(String response) {
                try {
                    processJSON(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        l.addView(container);


        sv.addView(l);
        setContentView(RelLay);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerViewActivity.this, CustomerActivity.class);
                intent.putExtra("CustID", CustID);
                startActivity(intent);
            }
        });

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CustomerViewActivity.this, "To rate an order, click on an order",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @SuppressLint("Range")
    public void processJSON(String json) throws JSONException {
        JSONArray ja = new JSONArray(json);
        for(int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            CustOrdersLayout col = new CustOrdersLayout(this);
            col.populate(jo);
            col.setBackgroundColor(Color.parseColor("#F57C00"));
            if(i % 2 == 0) {
                col.setBackgroundColor(Color.parseColor("#EEEEFF"));
            }
            container.addView(col);

            col.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        ordersRef = jo.getString("ORDER_NO");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(CustomerViewActivity.this, CustomerRateActivity.class);
                    intent.putExtra("ordersRef", ordersRef);
                    intent.putExtra("CustID", CustID);
                    startActivity(intent);
                }
            });
        }

    }
}
