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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StaffAddActivity2 extends AppCompatActivity {

    LinearLayout container;

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
        l.setOrientation(LinearLayout.VERTICAL);
        l.setBackgroundColor(Color.parseColor("#FF3700B3"));
        //setContentView(l);

        TextView title = new TextView(this);
        title.setText("Fill in the required fields:");
        title.setTextColor(Color.parseColor("#F57C00"));
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);


        container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams paramsTitle = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        paramsTitle.setMargins(0, 0, 0, 35);

        l.addView(title, paramsTitle);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params2.setMargins(0, 0, 0, 50);

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params1.setMargins(0, 0, 0, 50);


        TextView t = new TextView(this);
        t.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        Button backBtnAdd = new Button(this);
        backBtnAdd.setText("Back");
        backBtnAdd.setTextColor(Color.parseColor("#F57C00"));
        backBtnAdd.getBackground().setColorFilter(Color.parseColor("#FF6200EE"), PorterDuff.Mode.MULTIPLY);


        Button saveBtnAdd = new Button(this);
        saveBtnAdd.setText("Save");
        saveBtnAdd.setTextColor(Color.parseColor("#F57C00"));
        saveBtnAdd.getBackground().setColorFilter(Color.parseColor("#FF6200EE"), PorterDuff.Mode.MULTIPLY);


        String StaffIDintent = getIntent().getStringExtra("StaffID");

        EditText StaffNameET = new EditText(this);
        StaffNameET.setHint("Staff Name: ");
        StaffNameET.setHintTextColor(Color.parseColor("#FF000000"));
        StaffNameET.setBackgroundColor(Color.parseColor("#F57C00"));


        EditText StaffIDET = new EditText(this);
        StaffIDET.setHint("Staff ID:");
        StaffIDET.setHintTextColor(Color.parseColor("#FF000000"));
        StaffIDET.setBackgroundColor(Color.parseColor("#F57C00"));

        EditText CustNameET = new EditText(this);
        CustNameET.setHint("Customer Name: ");
        CustNameET.setHintTextColor(Color.parseColor("#FF000000"));
        CustNameET.setBackgroundColor(Color.parseColor("#F57C00"));

        EditText CustIDET = new EditText(this);
        CustIDET.setHint("Customer ID: ");
        CustIDET.setHintTextColor(Color.parseColor("#FF000000"));
        CustIDET.setBackgroundColor(Color.parseColor("#F57C00"));

        EditText RestaurantET = new EditText(this);
        RestaurantET.setHint("Restaurant: ");
        RestaurantET.setHintTextColor(Color.parseColor("#FF000000"));
        RestaurantET.setBackgroundColor(Color.parseColor("#F57C00"));

        //Time and Date:
        EditText DateET = new EditText(this);
        DateET.setBackgroundColor(Color.parseColor("#F57C00"));

        EditText TimeET = new EditText(this);

        TimeET.setBackgroundColor(Color.parseColor("#F57C00"));

        l.addView(StaffNameET,params1);
        l.addView(StaffIDET, params2);
        l.addView(CustNameET,params2);
        l.addView(CustIDET,params2);
        l.addView(RestaurantET,params2);
        l.addView(DateET,params2);
        l.addView(TimeET,params2);

        l.addView(saveBtnAdd);
        l.addView(container);
        //l.addView(t);
        l.addView(backBtnAdd);

        sv.addView(l);
        setContentView(RelLay);


        Calendar c = Calendar.getInstance();
        DateFormat dfD = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dfT = new SimpleDateFormat("hh:mm:ss");

        String date = dfD.format(c.getTime());
        String time = dfT.format(c.getTime());

        DateET.setText("Date : " + dfD.format(c.getTime()));
        TimeET.setText("Time : " + dfT.format(c.getTime()));

        boolean success = false;

        saveBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String staffName = StaffNameET.getText().toString().toUpperCase();
                String staffID = StaffIDET.getText().toString().toUpperCase();
                String CustName = CustNameET.getText().toString().toUpperCase();
                String CustID = CustIDET.getText().toString().toUpperCase();
                String Restaurant = RestaurantET.getText().toString().toUpperCase();
                //String date = DateET.getText().toString();
                //String time = TimeET.getText().toString();

                // l.addView(container);


                if (staffName.equals("") || staffID.equals("") || CustName.equals("") || CustID.equals("") ||
                        Restaurant.equals("") || date.equals("") || time.equals("")) {
                    Toast.makeText(StaffAddActivity2.this, "Please fill in all the fields",
                            Toast.LENGTH_LONG).show();
                } else {
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
                    //success == true;
                    //success = !success;

                    req.doRequest(StaffAddActivity2.this, "insertorder", cv, new RequestHandler() {
                        @Override
                        public void processResponse(String response) {
                        }
                    });
                    /*
                    req2.doRequest(StaffAddActivity2.this, "orderno", cv, new RequestHandler() {
                        @Override
                        public void processResponse(String response) throws JSONException {
                            processJSON(response);
                        }
                    });

                     */

                }
            }
        });

        //if()

        backBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffAddActivity2.this, StaffActivity.class);
                intent.putExtra("StaffID", StaffIDintent);
                startActivity(intent);

            }
        });
    }


    public void processJSON(String json) throws JSONException {
        JSONArray ja = new JSONArray(json);
        for(int  i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            String OrderNumber = jo.getString("ORDER_NO");
            TextView t = new TextView(this);
            t.setText(OrderNumber);
            container.addView(t);
        }

    }
}