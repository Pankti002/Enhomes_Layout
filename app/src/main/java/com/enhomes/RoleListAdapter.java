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

import java.util.ArrayList;

public class RoleListAdapter extends BaseAdapter {
    Context context;
    ArrayList<RoleLangModel> roleLangModelArrayList;

    public RoleListAdapter(Context context, ArrayList<RoleLangModel> langModelArrayList) {
        this.context = context;
        this.roleLangModelArrayList = langModelArrayList;
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

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.role_table, null);

        TextView tvData = view.findViewById(R.id.tv_data1);
        tvData.setText(roleLangModelArrayList.get(position).getRoleName());

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
                String id = roleLangModelArrayList.get(position).get_id();
                Log.e("id in delete: ", id);

               /*
               ALERT DIALOG
               AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you Sure you want to Delete ?");
                builder.setTitle("Alert !");
                builder.setCancelable(false);
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAPI(id);
                    }
                });
                builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Cancel Button Clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                */

                Intent intent = new Intent(context, RoleDeleteActivity.class);
                intent.putExtra("ROLE_ID",id);
                intent.putExtra("ROLE_NAME",roleLangModelArrayList.get(position).getRoleName());
                context.startActivity(intent);




            }
        });
        return view;
    }
}