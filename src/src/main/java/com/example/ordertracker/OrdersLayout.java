package com.example.ordertracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import org.json.JSONException;
import org.json.JSONObject;

public class OrdersLayout extends LinearLayout {
    /*shows information for staff id(for now)

     */
    TextView orderNo;
    TextView staffId;
    TextView staffName;
    TextView restaurant;
    TextView custName;
    TextView orderStatus;
    TextView custId;
    TextView rating;
    int thumbsUpUnicode = 0x1F44D;
    int thumbsDownUnicode = 0x1F44E;


    public OrdersLayout(Context context) {
        super(context);
        setOrientation(LinearLayout.HORIZONTAL);

        staffName = new TextView(context);
        staffId = new TextView(context);
        orderNo = new TextView(context);
        restaurant = new TextView(context);
        custName = new TextView(context);
        orderStatus = new TextView(context);
        custId = new TextView(context);
        rating = new TextView(context);

        LinearLayout leftLayout = new LinearLayout(context);
        leftLayout.setOrientation(VERTICAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.weight = 0;
        orderNo.setPadding(10, 10, 180, 10);
        leftLayout.addView(orderNo);
        //addView(orderStatus, lp);


        //LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
        //      LinearLayout.LayoutParams.WRAP_CONTENT);
        //lp3.weight = 0;
        orderStatus.setPadding(10, 0, 0, 0);
        leftLayout.addView(orderStatus);
        //addView(leftLayout, lp);


        rating.setPadding(10, 60, 0, 0);
        leftLayout.addView(rating);
        addView(leftLayout, lp);


        LinearLayout rightLayout = new LinearLayout(context);
        rightLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1;
        //orderNo.setPadding(20, 10, 10, 10);
        //orderNo.setPadding(20, 10, 10, 10);
        //orderNo.setPadding(20, 10, 10, 10);
        //orderNo.setPadding(20, 10, 10, 10);
        rightLayout.addView(staffName);
        rightLayout.addView(staffId);
        rightLayout.addView(restaurant);
        rightLayout.addView(custName);
        rightLayout.addView(custId);

        addView(rightLayout, lp2);
    }

    @SuppressLint("SetTextI18n")
    public void populate(JSONObject jo) throws JSONException {
        staffName.setText("Staff Name: " + jo.getString("STAFF_NAME"));
        staffId.setText("Staff ID: " + jo.getString("STAFF_ID"));
        orderNo.setText("Order number: " + jo.getString("ORDER_NO"));
        restaurant.setText("Restaurant: " + jo.getString("RESTAURANT"));
        custName.setText("Customer Name: " + jo.getString("CUSTOMER_NAME"));
        orderStatus.setText("Order Status: " + jo.getString("ORDERSTATUS"));
        custId.setText("Customer ID: " + jo.getString("CUST_ID"));
        //rating.setText("Rating: " + jo.getString("RATING"));
        if (jo.getString("RATING").equals("THUMBSUP")) {
            rating.setText("Rating: " +getEmojiByUnicode(thumbsUpUnicode));
        }
        else if (jo.getString("RATING").equals("THUMBSDOWN")) {
            rating.setText("Rating: " +getEmojiByUnicode(thumbsDownUnicode));
        }
        else {
            rating.setText("Rating: " + "...");
        }

    }
    public String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }
}
