package com.enhomes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RoleListAdapter extends BaseAdapter {
    Context context;
    ArrayList<RoleLangModel> langModelArrayList;
    public RoleListAdapter(Context context, ArrayList<RoleLangModel> langModelArrayList) {
        this.context=context;
        this.langModelArrayList=langModelArrayList;
    }

    @Override
    public int getCount() {
        return langModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return langModelArrayList.get(position);
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

        tvData.setText(langModelArrayList.get(position).getRoleName());

        return view;
    }
}
