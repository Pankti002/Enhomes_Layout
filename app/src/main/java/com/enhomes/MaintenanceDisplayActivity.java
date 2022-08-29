package com.enhomes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import utils.VolleySingleton;
import utils.util;

public class MaintenanceDisplayActivity extends AppCompatActivity {
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_display);

        listview=findViewById(R.id.ls_maintenance_listview);

        ArrayList<MaintenanceLangModel> arrayList=new ArrayList<MaintenanceLangModel>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, util.MAINTENANCE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String strHouseId = jsonObject1.getString("houseId");
                        String strMaintenanceAmount=jsonObject1.getString("maintenanceAmount");
                        String strCreationDate=jsonObject1.getString("creationDate");
                        String strPayDate=jsonObject1.getString("paymentDate");
                        String strLastDate=jsonObject1.getString("lastDate");
                        String strPenalty=jsonObject1.getString("penalty");

                        MaintenanceLangModel maintenanceLangModel = new MaintenanceLangModel();
                        maintenanceLangModel.setHouseId(strHouseId);
                        maintenanceLangModel.setMaintenanceAmount(strMaintenanceAmount);
                        maintenanceLangModel.setCreationDate(strCreationDate);
                        maintenanceLangModel.setPaymentDate(strPayDate);
                        maintenanceLangModel.setLastDate(strLastDate);
                        maintenanceLangModel.setPenalty(strPenalty);

                        arrayList.add(maintenanceLangModel);
                    }
                    MaintenanceListAdapter myListAdapter = new MaintenanceListAdapter(MaintenanceDisplayActivity.this, arrayList);
                    listview.setAdapter(myListAdapter);

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