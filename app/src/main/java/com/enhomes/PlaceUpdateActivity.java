package com.enhomes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import utils.VolleySingleton;
import utils.util;

public class PlaceUpdateActivity extends AppCompatActivity {

    EditText edtPlaceName;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        Intent i = getIntent();

        edtPlaceName = findViewById(R.id.et_placeDetails);
        btnUpdate = findViewById(R.id.btn_place);

        String strPlaceName = i.getStringExtra("PLACE_NAME");
        String placeId = i.getStringExtra("PLACE_ID");



        //set text
        PlaceLangModel placeLangModel = new PlaceLangModel();
        edtPlaceName.setText(strPlaceName);

        //normal code
        btnUpdate.setText("Update Place");
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPlaceName = edtPlaceName.getText().toString();
                apiCall(placeId, strPlaceName);

            }
        });

}

    private void apiCall(String placeId, String strPlaceName) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, util.PLACE_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);
                Intent intent = new Intent(PlaceUpdateActivity.this, PlaceDisplayActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("placeID", placeId);
                hashMap.put("placeName", strPlaceName);
                return hashMap;


            }
        };
        VolleySingleton.getInstance(PlaceUpdateActivity.this).addToRequestQueue(stringRequest);
    }
    }
