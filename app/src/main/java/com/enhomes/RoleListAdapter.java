package com.enhomes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import utils.util;
import utils.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoleListAdapter extends BaseAdapter {
    Context context;
    ArrayList<RoleLangModel> roleLangModelArrayList;
    public RoleListAdapter(Context context, ArrayList<RoleLangModel> langModelArrayList) {
        this.context=context;
        this.roleLangModelArrayList=langModelArrayList;
    }

    @Override
    public int getCount() {
        return roleLangModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return roleLangModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.raw_list,null);

        TextView tvData=view.findViewById(R.id.tv_data);

        tvData.setText(roleLangModelArrayList.get(position).get_id()+" "+roleLangModelArrayList.get(position).getRoleName());

        ImageView imgEdit = view.findViewById(R.id.img_edit);
        ImageView imgDelete = view.findViewById(R.id.img_delete);

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = roleLangModelArrayList.get(position).get_id();
                Log.e("id in edit: ", id);

                Intent intent = new Intent(context, RoleUpdateActivity.class);
                intent.putExtra("ROLE_ID", id);
                intent.putExtra("ROLE_NAME", roleLangModelArrayList.get(position).getRoleName());
                context.startActivity(intent);

            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String id = roleLangModelArrayList.get(position).get_id();
//                Log.e("id in delete: ", id);
//                deleteAPI(id);

                String id = roleLangModelArrayList.get(position).get_id();
                Log.e("id in Delete: ", id);

                Intent intent = new Intent(context, DeleteRoleActivity.class);
                intent.putExtra("ROLE_ID", id);
                intent.putExtra("ROLE_NAME", roleLangModelArrayList.get(position).getRoleName());
                context.startActivity(intent);
            }
        });
        return view;
    }

    private void deleteAPI(String id) {
        Log.e("id in deleteAPI: ", id);

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, util.ROLE_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("api calling done", response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                Log.e("id in Map: ",id);
                hashMap.put("roleId", id);
                return hashMap;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }
}
