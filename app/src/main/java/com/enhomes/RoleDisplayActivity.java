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

public class RoleDisplayActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        listView=findViewById(R.id.ls_listview);

        ArrayList<RoleLangModel> arrayList=new ArrayList<RoleLangModel>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, util.ROLE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String strRoleName = jsonObject1.getString("roleName");

                        RoleLangModel roleLangModel = new RoleLangModel();
                        roleLangModel.setRoleName(strRoleName);

                        arrayList.add(roleLangModel);
                    }
                    RoleListAdapter myListAdapter = new RoleListAdapter(RoleDisplayActivity.this, arrayList);
                    listView.setAdapter(myListAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(RoleDisplayActivity.this).addToRequestQueue(stringRequest);


    }
}