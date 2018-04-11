package com.example.jacegummersall.newfoodfinder;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.*;

public class HomeScreen extends AppCompatActivity {

    Button addButton;
    EditText input;
    ListView foodList;
    List<String> foodPreferences = new ArrayList<String>();
    private Context context;
    Button mapView;
    Button listView;
    Button historyView;
    Button profileView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        context = this;

        input = (EditText) findViewById(R.id.foodPreferenceField);
        addButton = (Button) findViewById(R.id.addButton);
        foodList = (ListView) findViewById(R.id.foodsList);
        mapView = (Button) findViewById(R.id.mapButton);
        historyView = (Button) findViewById(R.id.historyButton);
        listView = (Button) findViewById(R.id.listButton);
        profileView = (Button) findViewById(R.id.profileButton);

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.getText().clear();
            }
        });

        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, MapsActivity.class));
            }
        });

        historyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, HistoryView.class));
            }
        });

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, ViewList.class));
            }
        });

        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, ProfilePage.class));
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String food = input.getText().toString();
                foodPreferences.add(food);

                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<String>(
                                context,
                                android.R.layout.simple_list_item_1,
                                foodPreferences );

                foodList.setAdapter(arrayAdapter);
                input.setText("Which foods do you like?", TextView.BufferType.EDITABLE);
            }
        });

    }

    public static void writeFoods(){

    }

}
