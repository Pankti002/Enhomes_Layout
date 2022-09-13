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

public class MaintenanceListAdapter extends BaseAdapter {
    Context context;
    ArrayList<MaintenanceLangModel> maintenanceLangModelArrayList;

    public MaintenanceListAdapter(Context context, ArrayList<MaintenanceLangModel> langModelArrayList) {
        this.context = context;
        this.maintenanceLangModelArrayList = langModelArrayList;
    }

    @Override
    public int getCount() {
        return maintenanceLangModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return maintenanceLangModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        EditText edtMaintenanceAmount,edtPenalty;
        TextView tvCreationDate, tvPaymentDate,tvLastDate, tvMonth;

        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.raw_list,null);

        TextView tvData=view.findViewById(R.id.tv_data);

        tvData.setText(maintenanceLangModelArrayList.get(position).getHouseId()+" "+ maintenanceLangModelArrayList.get(position).getCreationDate()
        +" "+ maintenanceLangModelArrayList.get(position).getMonth()+" "+ maintenanceLangModelArrayList.get(position).getMaintenanceAmount()+" "
                + maintenanceLangModelArrayList.get(position).getMaintenancePaid()+" "+ maintenanceLangModelArrayList.get(position).getPaymentDate()+
                maintenanceLangModelArrayList.get(position).getLastDate()+ maintenanceLangModelArrayList.get(position).getPenalty());

        edtMaintenanceAmount=view.findViewById(R.id.et_amt);
        edtPenalty=view.findViewById(R.id.et_penalty);

        tvCreationDate=view.findViewById(R.id.tv_create);
        tvPaymentDate=view.findViewById(R.id.tv_payDate);
        tvLastDate=view.findViewById(R.id.tv_lastDate);

        ImageView imgEdit = view.findViewById(R.id.img_edit);
        ImageView imgDelete = view.findViewById(R.id.img_delete);


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MaintenanceUpdateActivity.class);
                intent.putExtra("MAINTENANCE_ID", maintenanceLangModelArrayList.get(position).getMaintenanceId());
                intent.putExtra("MAINTENANCE_AMOUNT",maintenanceLangModelArrayList.get(position).getMaintenanceAmount());
                intent.putExtra("PENALTY", maintenanceLangModelArrayList.get(position).getPenalty());
                intent.putExtra("CREATION_DATE",maintenanceLangModelArrayList.get(position).getCreationDate());
                intent.putExtra("PAYMENT_DATE",maintenanceLangModelArrayList.get(position).getPaymentDate());
                intent.putExtra("LAST_DATE",maintenanceLangModelArrayList.get(position).getLastDate());

                context.startActivity(intent);

            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvData.setText(maintenanceLangModelArrayList.get(position).getMaintenanceAmount()+" "+maintenanceLangModelArrayList.get(position).getPenalty()+" "
                +maintenanceLangModelArrayList.get(position).getCreationDate()+" "+maintenanceLangModelArrayList.get(position).getPaymentDate()+
                " "+maintenanceLangModelArrayList.get(position).getLastDate());

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String id=maintenanceLangModelArrayList.get(position).maintenanceId;
//                Intent i = new Intent()
//            }
//        });
        return view;
    }
}

