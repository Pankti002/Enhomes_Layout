package com.enhomes;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PlaceDisplayActivity extends AppCompatActivity {
    ListView listview;
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_display);
        listview = findViewById(R.id.ls_maintenance_listview);

        //Update button
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceDisplayActivity.this, PlaceActivity.class);
                startActivity(intent);
            }
        });

        PlaceApi();
    }

    private void PlaceApi() {

    }
}