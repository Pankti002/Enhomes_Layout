package com.enhomes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import utils.VolleySingleton;
import utils.util;

public class StaffDisplayActivity extends AppCompatActivity {
    ListView listView;
    FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_display);
        listView=findViewById(R.id.ls_staff_listview);

        btnAdd=findViewById(R.id.btn_addStaff);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StaffDisplayActivity.this, StaffActivity.class);
                startActivity(intent);
            }
        });

        StaffApi();
    }

    private void StaffApi() {
        ArrayList<StaffLangModel> arrayList=new ArrayList<StaffLangModel>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, util.STAFF_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray =jsonObject.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String strStaffMemberName= jsonObject1.getString("staffMemberName");
                        String strType=jsonObject1.getString("type");
                        String strEntryTime=jsonObject1.getString("entryTime");
                        String strExitTime=jsonObject1.getString("exitTime");
                        String strContactNo=jsonObject1.getString("contactNo");
                        String strAddress = jsonObject1.getString("address");
                        String strEmail = jsonObject1.getString("email");
                        String strPassword=jsonObject1.getString("password");
                        String strIsAllowed=jsonObject1.getString("isAllowed");
                        String strAgencyName=jsonObject1.getString("agencyName");
                        String strAgencyContactNo=jsonObject1.getString("agencyContactNumber");


                        StaffLangModel staffLangModel = new StaffLangModel();
                        staffLangModel.setStaffMemberName(strStaffMemberName);
                        staffLangModel.setType(strType);
                        staffLangModel.setEntryTime(strEntryTime);
                        staffLangModel.setExitTime(strExitTime);
                        staffLangModel.setContactNo(strContactNo);
                        staffLangModel.setAddress(strAddress);
                        staffLangModel.setEmail(strEmail);
                        staffLangModel.setPassword(strPassword);
                        staffLangModel.setIsAllowed(strIsAllowed);
                        staffLangModel.setAgencyName(strAgencyName);
                        staffLangModel.setAgencyContactNumber(strAgencyContactNo);

                        arrayList.add(staffLangModel);
                    }
                    StaffListAdapter staffListAdapter = new StaffListAdapter(StaffDisplayActivity.this,arrayList);
                    listView.setAdapter(staffListAdapter);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(StaffDisplayActivity.this).addToRequestQueue(stringRequest);
    }
}