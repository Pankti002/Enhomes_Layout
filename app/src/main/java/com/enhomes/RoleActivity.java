package com.enhomes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class RoleActivity extends AppCompatActivity {
    EditText edtRoleName;
    Button btnRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        btnRole=findViewById(R.id.btn_role);
        edtRoleName=findViewById(R.id.et_roleName);

        btnRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strRoleName = edtRoleName.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, util.ROLE_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(RoleActivity.this, RoleDisplayActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> hashMap = new HashMap<>();
                        hashMap.put("roleName", strRoleName);

                        return hashMap;
                    }
                };
                VolleySingleton.getInstance(RoleActivity.this).addToRequestQueue(stringRequest);
            }
        });
    }
}