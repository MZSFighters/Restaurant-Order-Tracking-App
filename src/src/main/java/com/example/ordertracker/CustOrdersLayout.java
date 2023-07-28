package com.example.ordertracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import org.json.JSONException;
import org.json.JSONObject;

public class CustOrdersLayout extends LinearLayout {
    /*shows information for staff id(for now)

     */
    TextView orderNo;
    TextView staffId;
    TextView staffName;
    TextView restaurant;
    TextView orderStatus;
    TextView rating;

    int thumbsUpUnicode = 0x1F44D;
    int thumbsDownUnicode = 0x1F44E;

    public CustOrdersLayout(Context context) {
        super(context);
        setOrientation(LinearLayout.HORIZONTAL);

        staffName = new TextView(context);
        staffId = new TextView(context);
        orderNo = new TextView(context);
        restaurant = new TextView(context);
        orderStatus = new TextView(context);
        rating = new TextView(context);
        //custId = new TextView(context);

        LinearLayout leftLayout = new LinearLayout(context);
        leftLayout.setOrientation(VERTICAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        //addView(staffName);

        lp.weight = 0;
        orderNo.setPadding(10, 10, 180, 10);
        rating.setPadding(10, 60, 0, 0);

        leftLayout.addView(orderNo);
        leftLayout.addView(orderStatus);
        leftLayout.addView(rating);

        addView(leftLayout, lp);

        LinearLayout rightLayout = new LinearLayout(context);
        rightLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1;
        rightLayout.addView(staffName);
        rightLayout.addView(staffId);
        //rightLayout.addView(orderNo);
        rightLayout.addView(restaurant);
        /*
        LinearLayout rightLayout = new LinearLayout(context);
        //rightLayout.setOrientation(LinearLayout.VERTICAL);
        rightLayout.addView(orderNo);
        rightLayout.addView(restaurant);

        addView(rightLayout);

         */
        addView(rightLayout, lp2);
    }

    @SuppressLint("SetTextI18n")
    public void populate(JSONObject jo) throws JSONException {
        staffName.setText("Staff Name: " + jo.getString("STAFF_NAME"));
        staffId.setText("Staff ID: " + jo.getString("STAFF_ID"));
        orderNo.setText("Order number: " + jo.getString("ORDER_NO"));
        restaurant.setText("Restaurant: " + jo.getString("RESTAURANT"));
        orderStatus.setText("Order Status: " + jo.getString("ORDERSTATUS"));
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
