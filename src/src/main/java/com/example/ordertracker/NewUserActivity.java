package com.example.ordertracker;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newuser);
        TextView t = (TextView)findViewById(R.id.textView3);
        TextView t2 = (TextView)findViewById(R.id.textView4);
        EditText UserName = (EditText)findViewById(R.id.editTextTextPersonName);
        EditText UserPassword = (EditText)findViewById(R.id.editTextTextPassword);
        Button back = (Button)findViewById(R.id.button3);
        RadioButton Customer = (RadioButton)findViewById(R.id.radioButtonstaff);
        RadioButton Staff = (RadioButton)findViewById(R.id.radioButton4);
        Button b = (Button)findViewById(R.id.buttonCreate);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = UserName.getText().toString().toUpperCase();
                String Password = UserPassword.getText().toString().toUpperCase();
                PHPRequest req = new PHPRequest("https://lamp.ms.wits.ac.za/~s2433079/");
                ContentValues cv = new ContentValues();


                final ContentValues[] cv2 = {new ContentValues()};
                if (Staff.isChecked() == true) {
                    if (Name.equals("") == true || Password.equals("") == true) {
                        Toast.makeText(NewUserActivity.this, "Please enter your name and password", Toast.LENGTH_LONG).show();
                    } else {
                        cv.put("STAFF_NAME", Name);
                        cv.put("STAFF_PASSWORD", Password);
                        final String[] successResponse = {""};

                        req.doRequest(NewUserActivity.this, "insertstaff", cv, new RequestHandler() {
                            @Override
                            public void processResponse(String response) {

                                t.setText(response);
                                successResponse[0] = response;
                                Intent intent = new Intent(NewUserActivity.this, NewUserSuccessStaff.class);
                                intent.putExtra("SuccessKey", successResponse[0]);
                                startActivity(intent);

                            }
                        });
                        /*
                        req.doRequest(NewUserActivity.this, "staffid", cv2[0], new RequestHandler() {
                            @Override
                            public void processResponse(String response) {

                                t2.setText(response);
                            }
                        });

                         */
                        ViewGroup layout = (ViewGroup) b.getParent();
                        if(null != layout) {
                            layout.removeView(b);
                        }
                    }
                }
                else if (Customer.isChecked()==true) {
                    if (Name.equals("") == true || Password.equals("") == true) {
                        Toast.makeText(NewUserActivity.this, "Please enter your name and password", Toast.LENGTH_LONG).show();
                    } else {
                        cv.put("CUSTOMER_NAME", Name);
                        cv.put("CUSTOMER_PASSWORD", Password);
                        final String[] successResponse = {""};
                        req.doRequest(NewUserActivity.this, "insertcust", cv, new RequestHandler() {
                            @Override
                            public void processResponse(String response) {

                                t.setText(response);
                                successResponse[0] = response;
                                Intent intent = new Intent(NewUserActivity.this, NewUserSuccessActivity.class);
                                intent.putExtra("SuccessKey", successResponse[0]);
                                startActivity(intent);
                            }
                        });
                        /*
                        req.doRequest(NewUserActivity.this, "custid", cv2, new RequestHandler() {
                            @Override
                            public void processResponse(String response) throws JSONException {

                                JSONArray ja = new JSONArray(response);
                                for(int i = 0; i < ja.length(); i++) {
                                    JSONObject jo = ja.getJSONObject(i);
                                    String str = jo.getString("CUST_ID");
                                    t2.setText("Customer ID: " + str);
                                }
                                //t2.setText(response);

                            }
                        });

                         */
                        ViewGroup layout = (ViewGroup) b.getParent();
                        if(null != layout) {
                            layout.removeView(b);
                        }
                    }
                }
                else{
                    Toast.makeText(NewUserActivity.this, "Please select your user role", Toast.LENGTH_LONG).show();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
