package com.example.ordertracker;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerRateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_rate);

        String ordersRef = getIntent().getStringExtra("ordersRef");
        String CustID = getIntent().getStringExtra("CustID");

        TextView t = (TextView)findViewById(R.id.textViewfeedback);
        RadioButton RadioThumbsUp = findViewById(R.id.radThumbsUp);
        RadioButton RadioThumbsDown = findViewById(R.id.radThumbsDown);
        Button saveBtn = (Button)findViewById(R.id.buttonSave);
        Button backBtn = (Button)findViewById(R.id.buttonBack);
        backBtn.setText("Cancel");



        PHPRequest req = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
        ContentValues cv = new ContentValues();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rating = "NA";
                if(RadioThumbsUp.isChecked()) {
                    rating = "THUMBSUP";
                    cv.put("ORDER_NO", ordersRef);
                    cv.put("RATING", rating);
                    req.doRequest(CustomerRateActivity.this, "rateorder", cv, new RequestHandler() {
                        @Override
                        public void processResponse(String response) {

                            t.setText(response);
                        }
                    });
                    backBtn.setText("Back");
                }
                if(RadioThumbsDown.isChecked()) {
                    rating = "THUMBSDOWN";
                    cv.put("ORDER_NO", ordersRef);
                    cv.put("RATING", rating);
                    req.doRequest(CustomerRateActivity.this, "rateorder", cv, new RequestHandler() {
                        @Override
                        public void processResponse(String response) {

                            t.setText(response);
                        }
                    });
                    backBtn.setText("Back");
                }

                //Intent intent = new Intent(StaffEditActivity.this, StaffViewActivity3.class);
                //intent.putExtra("StaffID", Staff_ID);
                //startActivity(intent);
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerRateActivity.this, CustomerViewActivity.class);
                intent.putExtra("ordersRef", ordersRef);
                intent.putExtra("CustID", CustID);
                startActivity(intent);
            }
        });

    }
}
