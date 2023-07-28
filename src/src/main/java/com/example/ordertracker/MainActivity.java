package com.example.ordertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();

        Button btnSignIn = (Button)findViewById(R.id.buttonsignin);
        Button newacc = (Button)findViewById(R.id.buttonnewaccount);
        RadioButton customer = (RadioButton)findViewById(R.id.radThumbsDown);
        RadioButton staff = (RadioButton)findViewById(R.id.radioButtonstaff);
        EditText etid = (EditText)findViewById(R.id.editTextTextPersonName);
        EditText etpassword = (EditText)findViewById(R.id.editTextTextPassword);

        //final String[] textToSend = new String[1];




        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etid.getText().toString().toUpperCase();
                String password = etpassword.getText().toString().toUpperCase();
                if(customer.isChecked()){
                    if (id.equals("") || password.equals("") ){
                        Toast.makeText(MainActivity.this, "Please enter your id and password",Toast.LENGTH_LONG).show();
                    }
                    else{
                        PHPRequest req3 = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
                        ContentValues cv = new ContentValues();
                        cv.put("CUST_ID", id);
                        cv.put("CUSTOMER_PASSWORD", password);
                        req3.doRequest(MainActivity.this, "check", cv, new RequestHandler() {
                            @Override
                            public void processResponse(String response) {
                                if (response.trim().equals("[]")){
                                    Toast.makeText(MainActivity.this, "Invalid login information",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Intent intent = new Intent(MainActivity.this, CustomerActivity.class);
                                    //textToSend[0] = etid.getText().toString();
                                    intent.putExtra("CustID", id);
                                    startActivity(intent);
                                }
                            }
                        });
                    }

                }
                else if (staff.isChecked()){
                    if (id.equals("") || password.equals("") ){
                        Toast.makeText(MainActivity.this, "Please enter your id and password",Toast.LENGTH_LONG).show();
                    }
                    else{
                        PHPRequest req3 = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
                        ContentValues cv = new ContentValues();
                        cv.put("STAFF_ID", id);
                        cv.put("STAFF_PASSWORD", password);
                        req3.doRequest(MainActivity.this, "checkstaff", cv, new RequestHandler() {
                            @Override
                            public void processResponse(String response) {
                                if (response.trim().equals("[]")){
                                    Toast.makeText(MainActivity.this, "Invalid login information",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Intent intent = new Intent(MainActivity.this, StaffActivity.class);
                                    intent.putExtra("StaffID", id);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Please select your user role",Toast.LENGTH_LONG).show();
                }
            }
        });

        newacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewUserActivity.class);
                startActivity(intent);
            }
        });
    }
}