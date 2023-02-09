package com.enhomes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.enhomes.StaffUpdateActivity;
import com.enhomes.R;
import com.enhomes.StaffDisplayActivity;
import com.enhomes.StaffLangModel;
import utils.util;
import utils.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

public class StaffListAdapter extends BaseAdapter {
    Context context;
    ArrayList<StaffLangModel> staffLangModelArrayList;

    public StaffListAdapter(Context context, ArrayList<StaffLangModel> langModelArrayList) {
        this.context = context;
        this.staffLangModelArrayList = langModelArrayList;
    }

    @Override
    public int getCount() {
        return staffLangModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return staffLangModelArrayList.get(position);
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

        tvData.setText(staffLangModelArrayList.get(position).getStaffMemberName()+" "+ staffLangModelArrayList.get(position).getType()+
                " "+ staffLangModelArrayList.get(position).getEntryTime()+" "+ staffLangModelArrayList.get(position).getExitTime()+" "+
                staffLangModelArrayList.get(position).getContactNo()+" "+ staffLangModelArrayList.get(position).getAddress()+" "+
             staffLangModelArrayList.get(position).getAgencyName()+" "
        + staffLangModelArrayList.get(position).getAgencyContactNumber());

        ImageView imgEdit = view.findViewById(R.id.img_edit);
        ImageView imgDelete = view.findViewById(R.id.img_delete);


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = staffLangModelArrayList.get(position).get_id();
               Log.e("id in edit: ","id "+id);
               Log.e("position: ", "id: "+staffLangModelArrayList.get(position).get_id());

                Intent intent = new Intent(context, StaffUpdateActivity.class);
                intent.putExtra("STAFF_ID", id);
                intent.putExtra("STAFF_NAME",staffLangModelArrayList.get(position).getStaffMemberName());
                intent.putExtra("ENTRY_TIME",staffLangModelArrayList.get(position).getEntryTime());
                intent.putExtra("EXIT_TIME",staffLangModelArrayList.get(position).getExitTime());
                intent.putExtra("CONTACT", staffLangModelArrayList.get(position).getContactNo());
                intent.putExtra("ADDRESS",staffLangModelArrayList.get(position).getAddress());
                intent.putExtra("AGENCY_NAME", staffLangModelArrayList.get(position).getAgencyName());
                intent.putExtra("AGENCY_CONTACT",staffLangModelArrayList.get(position).getAgencyContactNumber());
                intent.putExtra("TYPE",staffLangModelArrayList.get(position).getType());

                context.startActivity(intent);

            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
