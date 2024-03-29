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

public class PlaceListAdapter extends BaseAdapter {
    Context context;
    ArrayList<PlaceLangModel> placeLangModelArrayList;
    public PlaceListAdapter(Context context, ArrayList<PlaceLangModel> langModelArrayList){
        this.context=context;
        this.placeLangModelArrayList=langModelArrayList;

}
    @Override
    public int getCount() {
        return placeLangModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return placeLangModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.raw_list, null);

        TextView tvData = view.findViewById(R.id.tv_data);

        tvData.setText(placeLangModelArrayList.get(position).get_id() + " " + placeLangModelArrayList.get(position).getPlaceName() );

        ImageView imgEdit = view.findViewById(R.id.img_edit);
        ImageView imgDelete = view.findViewById(R.id.img_delete);


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = placeLangModelArrayList.get(position).get_id();
                Log.e("id in edit: ", id);

                Intent intent = new Intent(context, PlaceUpdateActivity.class);
                intent.putExtra("PLACE_ID", id);
                intent.putExtra("PLACE_NAME", placeLangModelArrayList.get(position).getPlaceName());
                context.startActivity(intent);

            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String id1 = placeLangModelArrayList.get(position).get_id();
//                Log.e("id in edit: ", id1);
//
//
//                Intent intent = new Intent(context, PlaceUpdateActivity.class);
//                intent.putExtra("PLACE_ID", id1);
//                intent.putExtra("MAINTENANCE_HOUSE", placeLangModelArrayList.get(position).getPlaceName());
//                context.startActivity(intent);
            }
        });
        return view;
    }
}
