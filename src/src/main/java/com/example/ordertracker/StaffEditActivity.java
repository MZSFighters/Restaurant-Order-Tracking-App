package com.example.ordertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class StaffEditActivity extends AppCompatActivity {

    Button backBtn, saveBtn;
    RadioButton  pendingRadBtn, readyRadBtn, collectedRadBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_edit);

        saveBtn = findViewById(R.id.saveBtn);
        backBtn = findViewById(R.id.editBackBtn);
        backBtn.setText("Cancel");

        pendingRadBtn = findViewById(R.id.pendingBtn);
        readyRadBtn = findViewById(R.id.readyBtn);
        collectedRadBtn = findViewById(R.id.collectedBtn);

        String ordersRef = getIntent().getStringExtra("ordersRef");
        String Staff_ID = getIntent().getStringExtra("StaffID");
        TextView t = findViewById(R.id.editTest);
        TextView t2 = findViewById(R.id.TextViewOrderNo);
        t2.setText("Order Number : " + ordersRef);

        //RadioButton rb = (RadioButton)findViewById(R.id.pendingBtn);
        //rb.setChecked(true);

        PHPRequest req = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
        ContentValues cv = new ContentValues();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = "NA";
                if(pendingRadBtn.isChecked()) {
                    status = "PENDING";
                    cv.put("ORDER_NO", ordersRef);
                    cv.put("ORDERSTATUS", status);
                    req.doRequest(StaffEditActivity.this, "editorder", cv, new RequestHandler() {
                        @Override
                        public void processResponse(String response) {

                            t.setText(response);
                        }
                    });
                    backBtn.setText("Back");
                }
                if(readyRadBtn.isChecked()) {
                    status = "READY";
                    cv.put("ORDER_NO", ordersRef);
                    cv.put("ORDERSTATUS", status);
                    req.doRequest(StaffEditActivity.this, "editorder", cv, new RequestHandler() {
                        @Override
                        public void processResponse(String response) {

                            t.setText(response);
                        }
                    });
                    backBtn.setText("Back");
                }
                if(collectedRadBtn.isChecked()) {
                    status = "COLLECTED";
                    cv.put("ORDER_NO", ordersRef);
                    cv.put("ORDERSTATUS", status);
                    req.doRequest(StaffEditActivity.this, "editorder", cv, new RequestHandler() {
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
            public void onClick(View view) {
                Intent intent = new Intent(StaffEditActivity.this, StaffViewActivity3.class);
                intent.putExtra("StaffID", Staff_ID);
                startActivity(intent);
            }
        });
    }
}