package com.example.ordertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StaffAddActivity extends AppCompatActivity {

    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_add);

        //LinearLayout l = findViewById(R.id.llAdd);

        //container = new LinearLayout(this);
        //container.setOrientation(LinearLayout.VERTICAL);

        String StaffIDintent = getIntent().getStringExtra("StaffID");

        EditText StaffNameET = findViewById(R.id.StaffNameET);
        EditText StaffIDET = findViewById(R.id.StaffIdET);
        EditText CustNameET = findViewById(R.id.CustNameET);
        EditText CustIDET = findViewById(R.id.CustIdET);
        EditText RestaurantET = findViewById(R.id.RestaurantET);

        //Time and Date:
        EditText DateET = findViewById(R.id.DateET);
        EditText TimeET = findViewById(R.id.TimeET);
        Calendar c = Calendar.getInstance();
        DateFormat dfD = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dfT = new SimpleDateFormat("hh:mm:ss");

        String date = dfD.format(c.getTime());
        String time = dfT.format(c.getTime());

        DateET.setText("Date : " + dfD.format(c.getTime()));
        TimeET.setText("Time : " + dfT.format(c.getTime()));

        Button backBtn, saveBtn;
        backBtn = findViewById(R.id.addBack);
        saveBtn = findViewById(R.id.saveBtnAdd);

        TextView t = findViewById(R.id.textView7);
        t.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String staffName = StaffNameET.getText().toString();
                String staffID = StaffIDET.getText().toString();
                String CustName = CustNameET.getText().toString();
                String CustID = CustIDET.getText().toString();
                String Restaurant = RestaurantET.getText().toString();
                //String date = DateET.getText().toString();
                //String time = TimeET.getText().toString();

                // l.addView(container);

                if(staffName.equals("") || staffID.equals("") || CustName.equals("") || CustID.equals("") ||
                        Restaurant.equals("") || date.equals("") || time.equals("")) {
                    Toast.makeText(StaffAddActivity.this, "Please fill in all the fields",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    PHPRequest req = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
                    ContentValues cv = new ContentValues();
                    PHPRequest req2 = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
                    ContentValues cv2 = new ContentValues();
                    cv.put("CUST_ID", CustID);
                    cv.put("STAFF_ID", staffID);
                    cv.put("CUSTOMER_NAME", CustName);
                    cv.put("STAFF_NAME", staffName);
                    cv.put("DATE", date);
                    cv.put("TIME", time);
                    cv.put("RESTAURANT", Restaurant);

                    req.doRequest(StaffAddActivity.this, "insertorder", cv, new RequestHandler() {
                        @Override
                        public void processResponse(String response) {
                        }
                    });


                    String testtingg = "";

                    req2.doRequest(StaffAddActivity.this, "orderno", cv, new RequestHandler() {
                        @Override
                        public void processResponse(String response) throws JSONException {
                            JSONArray ja = new JSONArray(response);
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject jo = ja.getJSONObject(i);
                                String orderNumber = jo.getString("MAX(CUST_ID)");
                                t.setText("Order Number WORK : " + orderNumber);
                            }

                        }
                    });
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffAddActivity.this, StaffActivity.class);
                intent.putExtra("StaffID", StaffIDintent);
                startActivity(intent);

            }
        });
    }

    /*
    public String processJSON(String json) throws JSONException {
        String orderNumber = "";
        JSONArray ja = new JSONArray(json);
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            orderNumber = jo.getString("ORDER_NO");
            //TextView te = new TextView(this);
            //te.setText(orderNumber);
        }
        //return orderNumber;
    }

     */

}