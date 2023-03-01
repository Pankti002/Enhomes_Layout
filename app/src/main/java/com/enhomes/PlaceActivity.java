package com.enhomes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import utils.VolleySingleton;
import utils.util;

public class PlaceActivity extends AppCompatActivity {
     EditText edtPlaceDetails;
     Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        edtPlaceDetails = findViewById(R.id.et_placeDetails);
        btnAdd = findViewById(R.id.btn_place);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPlaceDetails = edtPlaceDetails.getText().toString();
                if(strPlaceDetails.length()==0)
                {
                    edtPlaceDetails.requestFocus();
                    edtPlaceDetails.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!strPlaceDetails.matches("[a-zA-Z ]+"))
                {
                    edtPlaceDetails.requestFocus();
                    edtPlaceDetails.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else {
                    placeApi(strPlaceDetails);
                }
            }
        });
    }

    private void placeApi(String strPlaceDetails) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, util.PLACE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Place Response ===", "onResponse: " + response);
                Intent i = new Intent(PlaceActivity.this,PlaceDisplayActivity.class);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error: ", String.valueOf(error));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                map.put("placeName", strPlaceDetails);

                return map;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}