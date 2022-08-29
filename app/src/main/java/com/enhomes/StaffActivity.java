package com.enhomes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class StaffActivity extends AppCompatActivity {

    Spinner spinnerType;
    String strTypes[]={"Select a Type","Security Guard","Sweeper","Pump Operator","Gardened"};

    ImageButton btnEntry, btnExit;
    TextView tvEntry, tvExit;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        spinnerType = findViewById(R.id.spinner_type);

        ArrayAdapter<String> arrayAdapter = new
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strTypes) {
                    @Override
                    public View getDropDownView(int position, @Nullable View convertView,
                                                @NonNull ViewGroup parent) {

                        TextView tvData1 = (TextView) super.getDropDownView(position, convertView, parent);
                        tvData1.setTextColor(Color.WHITE);
                        tvData1.setTextSize(20);
                        return tvData1;
                    }

                };
        spinnerType.setAdapter(arrayAdapter);


        //time
        tvEntry=findViewById(R.id.tv_entry);
        tvExit=findViewById(R.id.tv_exit);
        btnEntry=findViewById(R.id.btn_entry);
        btnExit=findViewById(R.id.btn_exit);


        Calendar calendar =Calendar.getInstance();
        hour=calendar.get(Calendar.HOUR_OF_DAY);
        minute=calendar.get(Calendar.MINUTE);

        btnEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(StaffActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvEntry.setText(hourOfDay+":"+minute);
                    }
                },hour, minute, true);

                timePickerDialog.show();
            }
        });


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(StaffActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvExit.setText(hourOfDay+":"+minute);
                    }
                },hour, minute, true);

                timePickerDialog.show();
            }
        });

        }
}