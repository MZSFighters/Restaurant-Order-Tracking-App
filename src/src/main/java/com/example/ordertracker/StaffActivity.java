package com.example.ordertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import okhttp3.OkHttpClient;

public class StaffActivity extends AppCompatActivity {

    Button btnAdd, btnEdit, btnView, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        btnAdd = findViewById(R.id.addBtn);
        btnView = findViewById(R.id.viewBtn);
        btnBack = findViewById(R.id.BackToHome);

        String StaffID = getIntent().getStringExtra("StaffID");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffActivity.this, StaffAddActivity2.class);
                intent.putExtra("StaffID", StaffID);
                startActivity(intent);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffActivity.this, StaffViewActivity3.class);
                intent.putExtra("StaffID", StaffID);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}