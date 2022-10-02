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

public class DeleteRoleActivity extends AppCompatActivity {

    EditText edtRoleName;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        Intent i = getIntent();

        edtRoleName=findViewById(R.id.et_roleName);
        btnDelete=findViewById(R.id.btn_role);

        String strRoleId=i.getStringExtra("ROLE_ID");
        String strRoleName = i.getStringExtra("ROLE_NAME");

        RoleLangModel roleLangModel = new RoleLangModel();
        edtRoleName.setText(strRoleName);

        btnDelete.setText("Delete Role");
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strRoleName=edtRoleName.getText().toString();

                RoleApi(strRoleId, strRoleName);
            }
        });

    }

    private void RoleApi(String strRoleId, String strRoleName) {
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, util.ROLE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(DeleteRoleActivity.this, RoleDisplayActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.e("api calling done", strRoleId);
                Map<String, String> hashMap = new HashMap<>();
                Log.e("id in Delete Map : ",strRoleId);
                hashMap.put("roleId", strRoleId);
                return hashMap;


            }
        };
        VolleySingleton.getInstance(DeleteRoleActivity.this).addToRequestQueue(stringRequest);

    }
}