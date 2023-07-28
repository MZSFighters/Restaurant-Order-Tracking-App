package com.example.ordertracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_activity);

        Button b = (Button)findViewById(R.id.buttonViewC);
        //Button rate = (Button)findViewById(R.id.buttonRate);
        Button back = (Button)findViewById(R.id.buttonBackC1);

        String CustID = getIntent().getStringExtra("CustID");

        TextView test = findViewById(R.id.testTextView);
        test.setText(CustID);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this, CustomerViewActivity.class);
                intent.putExtra("CustID", CustID);
                startActivity(intent);
            }
        });

        /*
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this, CustomerRateActivity.class);

                startActivity(intent);
            }
        });

         */

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

    }
}
