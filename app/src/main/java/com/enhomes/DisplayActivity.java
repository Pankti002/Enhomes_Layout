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
import java.util.List;

import utils.VolleySingleton;
import utils.util;

public class DisplayActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        listView=findViewById(R.id.ls_listview);

        ArrayList<LangModel> arrayList=new ArrayList<LangModel>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, util.ROLE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String strRoleId = jsonObject.getString("roleId");
                        String strRoleName = jsonObject.getString("roleName");

                        LangModel langModel = new LangModel();
                        langModel.setRoleId(strRoleId);
                        langModel.setRoleName(strRoleName);

                        arrayList.add(langModel);
                    }
                    MyListAdapter myListAdapter = new MyListAdapter(DisplayActivity.this, arrayList);
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

        VolleySingleton.getInstance(DisplayActivity.this).addToRequestQueue(stringRequest);


    }
}