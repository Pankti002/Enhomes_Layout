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

public class UserActivity extends AppCompatActivity {

    EditText edtHouseId,edtRoleId,edtFirstName,edtLastName,edtDateOfBirth,edtAge,edtGender,edtContactNo,edtEmail,edtPassword;
    Button btnUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btnUser=findViewById(R.id.btn_user);
        edtHouseId=findViewById(R.id.et_houseId);
        edtRoleId=findViewById(R.id.et_roleId);
        edtFirstName=findViewById(R.id.et_firstName);
        edtLastName=findViewById(R.id.et_lastName);
        edtDateOfBirth=findViewById(R.id.et_dob);
        edtAge=findViewById(R.id.et_age);
        edtGender=findViewById(R.id.et_gender);
        edtContactNo=findViewById(R.id.et_contactNo);
        edtEmail=findViewById(R.id.et_email);
        edtPassword=findViewById(R.id.et_password);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strHouseId = edtHouseId.getText().toString();
                String strRoleId = edtRoleId.getText().toString();
                String strFirstName = edtFirstName.getText().toString();
                String strLastName = edtLastName.getText().toString();
                String strDateOfBirth = edtDateOfBirth.getText().toString();
                String strAge = edtAge.getText().toString();
                String strGender = edtGender.getText().toString();
                String strContactNo = edtContactNo.getText().toString();
                String strEmail = edtEmail.getText().toString();
                String strPassword = edtPassword.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, util.USER_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent i = new Intent(UserActivity.this, RoleDisplayActivity.class);
                        startActivity(i);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> hashMap = new HashMap<>();
                        hashMap.put("houseId", strHouseId);
                        hashMap.put("roleId", strRoleId);
                        hashMap.put("firstName", strFirstName);
                        hashMap.put("lastName", strLastName);
                        hashMap.put("dateOfBirth", strDateOfBirth);
                        hashMap.put("age", strAge);
                        hashMap.put("gender", strGender);
                        hashMap.put("contactNo", strContactNo);
                        hashMap.put("email", strEmail);
                        hashMap.put("password", strPassword);

                        return hashMap;
                    }
                };
                VolleySingleton.getInstance(UserActivity.this).addToRequestQueue(stringRequest);
            }
        });
    }
}