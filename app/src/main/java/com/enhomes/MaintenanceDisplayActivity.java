package com.enhomes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

public class MaintenanceDisplayActivity extends AppCompatActivity {
    ListView listview;
    FloatingActionButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_display);

        listview = findViewById(R.id.ls_maintenance_listview);

        //Update button
        btnAdd=findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintenanceDisplayActivity.this, MaintenanceActivity.class);
                startActivity(intent);
            }
        });

        MaintenanceApi();

    }

    private void MaintenanceApi() {
        ArrayList<MaintenanceLangModel> arrayList=new ArrayList<MaintenanceLangModel>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, util.MAINTENANCE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        //JSONObject jsonObject2 = jsonObject1.getJSONObject("house");
                        String strMaintenanceId = jsonObject1.getString("_id");
                        String strCreationDate=jsonObject1.getString("creationDate");
                        String strMonth=jsonObject1.getString("month");
                        String strMaintenanceAmount=jsonObject1.getString("maintenanceAmount");
                        String strPaid=jsonObject1.getString("maintenancePaid");
                        String strPayDate=jsonObject1.getString("paymentDate");
                        String strLastDate=jsonObject1.getString("lastDate");
                        String strPenalty=jsonObject1.getString("penalty");

                         MaintenanceLangModel maintenanceLangModel = new MaintenanceLangModel();
                        maintenanceLangModel.set_id(strMaintenanceId);
                        maintenanceLangModel.setCreationDate(strCreationDate);
                        maintenanceLangModel.setMonth(strMonth);
                        maintenanceLangModel.setMaintenanceAmount(strMaintenanceAmount);
                        maintenanceLangModel.setMaintenancePaid(strPaid);
                        maintenanceLangModel.setPaymentDate(strPayDate);
                        maintenanceLangModel.setLastDate(strLastDate);
                        maintenanceLangModel.setPenalty(strPenalty);


                        arrayList.add(maintenanceLangModel);
                    }
                    MaintenanceListAdapter maintenanceListAdapter = new MaintenanceListAdapter(MaintenanceDisplayActivity.this, arrayList);
                    listview.setAdapter(maintenanceListAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(MaintenanceDisplayActivity.this).addToRequestQueue(stringRequest);
    }
}