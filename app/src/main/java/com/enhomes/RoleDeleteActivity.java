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

public class RoleDeleteActivity extends AppCompatActivity {

    EditText edtRoleName;
    Button btnRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        Intent i = getIntent();

        edtRoleName=findViewById(R.id.et_roleName);
        btnRole=findViewById(R.id.btn_role);

        String strRoleId=i.getStringExtra("ROLE_ID");
        String strRoleName = i.getStringExtra("ROLE_NAME");

        Log.e("data: ",strRoleId+" "+strRoleName);

        RoleLangModel roleLangModel = new RoleLangModel();
        edtRoleName.setText(strRoleName);
        btnRole.setText("Delete Role");
        btnRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strRoleName=edtRoleName.getText().toString();
                deleteApi(strRoleId,strRoleName);
            }
        });

    }

    private void deleteApi(String strRoleId, String strRoleName) {
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, util.ROLE_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);
                Intent intent = new Intent(RoleDeleteActivity.this, RoleDisplayActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error: ", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                Log.e("id in map",strRoleId);
                hashMap.put("roleId", strRoleId);
                hashMap.put("roleName",strRoleName);
                return hashMap;


            }
        };
        VolleySingleton.getInstance(RoleDeleteActivity.this).addToRequestQueue(stringRequest);

    }

//    private void RoleApi(String strRoleId, String strRoleName) {
//        StringRequest stringRequest = new StringRequest(Request.Method.PUT, util.ROLE_URL, new Response.Listener<String>() {
//            @Override
//
//            public void onResponse(String response) {
//                Log.e("api calling done", response);
//                Intent intent = new Intent(RoleDeleteActivity.this, RoleDisplayActivity.class);
//                startActivity(intent);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> hashMap = new HashMap<>();
//                Log.e("id in update Map : ",strRoleId);
//                hashMap.put("roleId", strRoleId);
//                hashMap.put("roleName", strRoleName);
//                return hashMap;
//
//
//            }
//        };
//        VolleySingleton.getInstance(RoleDeleteActivity.this).addToRequestQueue(stringRequest);
//
//    }


}
