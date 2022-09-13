package com.enhomes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

        EditText edtStaffName, edtContact, edtAddress, edtEmail, edtPassword,
                edtAgencyName, edtAgencyContact;

        EditText edtEntryTime, edtExitTime;

        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.raw_list,null);

        TextView tvData=view.findViewById(R.id.tv_data);

        tvData.setText(staffLangModelArrayList.get(position).getStaffMemberName()+" "+ staffLangModelArrayList.get(position).getType()+
                " "+ staffLangModelArrayList.get(position).getEntryTime()+" "+ staffLangModelArrayList.get(position).getExitTime()+" "+
                staffLangModelArrayList.get(position).getContactNo()+" "+ staffLangModelArrayList.get(position).getAddress()+" "+
                staffLangModelArrayList.get(position).getIsAllowed()+" "+ staffLangModelArrayList.get(position).getAgencyName()+" "
        + staffLangModelArrayList.get(position).getAgencyContactNumber());

        edtStaffName=view.findViewById(R.id.et_name);
        edtContact=view.findViewById(R.id.et_contact);
        edtAddress=view.findViewById(R.id.et_add);
        edtEmail=view.findViewById(R.id.et_email);
        edtPassword=view.findViewById(R.id.et_password);
        edtAgencyName=view.findViewById(R.id.et_agencyName);
        edtAgencyContact=view.findViewById(R.id.et_agencyContact);
        edtEntryTime=view.findViewById(R.id.tv_entry);
        edtExitTime=view.findViewById(R.id.tv_exit);

        ImageView imgEdit = view.findViewById(R.id.img_edit);
        ImageView imgDelete = view.findViewById(R.id.img_delete);


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StaffUpdateActivity.class);
                intent.putExtra("STAFF_ID", staffLangModelArrayList.get(position).get_id());
                intent.putExtra("STAFF_NAME",staffLangModelArrayList.get(position).getStaffMemberName());
                intent.putExtra("CONTACT", staffLangModelArrayList.get(position).getContactNo());
                intent.putExtra("ADDRESS",staffLangModelArrayList.get(position).getAddress());
                intent.putExtra("EMAIL",staffLangModelArrayList.get(position).getEmail());
                intent.putExtra("PASSWORD",staffLangModelArrayList.get(position).getPassword());
                intent.putExtra("AGENCY_NAME", staffLangModelArrayList.get(position).getAgencyName());


                context.startActivity(intent);

            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        tvData.setText(maintenanceLangModelArrayList.get(position).getMaintenanceAmount()+" "+maintenanceLangModelArrayList.get(position).getPenalty()+" "
//                +maintenanceLangModelArrayList.get(position).getCreationDate()+" "+maintenanceLangModelArrayList.get(position).getPaymentDate()+
//                " "+maintenanceLangModelArrayList.get(position).getLastDate());


        return view;
    }
}
