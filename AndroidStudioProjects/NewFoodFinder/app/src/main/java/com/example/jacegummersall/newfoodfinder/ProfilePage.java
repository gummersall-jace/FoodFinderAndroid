package com.example.jacegummersall.newfoodfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {

    EditText firstName;
    EditText lastName;
    EditText email;
    EditText city;
    Button submit;
    JSONObject userData;
    String returnMessage;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        city = (EditText) findViewById(R.id.city);
        submit = (Button) findViewById(R.id.submit);
        home = (Button) findViewById(R.id.home);

        firstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName.getText().clear();
            }
        });

        lastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastName.getText().clear();
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.getText().clear();
            }
        });

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city.getText().clear();
            }
        });

        submit.setOnClickListener(this);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this, HomeScreen.class));
            }
        });
    }

    @Override
    public void onClick(View v) {

        new Thread(new Runnable() {
            public void run() {

                try{
                    URL url = new URL("http://10.0.2.2:8080/FoodFinderServlet/PassData");
                    URLConnection connection = url.openConnection();

                    String fname = firstName.getText().toString();
                    String lname = lastName.getText().toString();
                    String uemail = email.getText().toString();
                    String ucity = city.getText().toString();

                    userData = new JSONObject();
                        try{
                            userData.put("FirstName",fname);
                            userData.put("LastName",lname);
                            userData.put("Email",uemail);
                            userData.put("City",ucity);
                        }catch(JSONException jsonE){

                        }

                    Log.d("passedObject", String.valueOf(userData));

                    connection.setDoOutput(true);
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(String.valueOf(userData));
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    returnMessage = in.readLine();

                    in.close();


                    runOnUiThread(new Runnable() {
                        public void run() {

                            submit.setText(returnMessage);
                        }
                    });

                }catch(Exception e)
                {
                    Log.d("Exception",e.toString());
                }

            }
        }).start();

    }
}
