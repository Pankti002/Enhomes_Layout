package com.enhomes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class MaintenanceListAdapter extends BaseAdapter {
    Context context;
    ArrayList<MaintenanceLangModel> langModelArrayList;

    public MaintenanceListAdapter(Context context, ArrayList<MaintenanceLangModel> langModelArrayList) {
        this.context = context;
        this.langModelArrayList = langModelArrayList;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

