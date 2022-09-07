package com.enhomes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import utils.VolleySingleton;
import utils.util;

public class StaffActivity extends AppCompatActivity {

    EditText edtStaffName, edtContact, edtAddress, edtEamil, edtPassword,
    edtAgencyName, edtAgencyContact;

    Button btnStaff;

    Spinner spinnerType;
    String strTypes[]={"Select a Type","SecurityGuard","Sweeper","PumpOperator","Gardener"};
    String strStaff;

    RadioGroup radioGroup;

    ImageButton btnEntry, btnExit;
    TextView tvEntry, tvExit;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        spinnerType = findViewById(R.id.spinner_type);

        //spinner
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
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strStaff=strTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //time
        tvEntry=findViewById(R.id.tv_entry);
        tvExit=findViewById(R.id.tv_exit);
        btnEntry=findViewById(R.id.btn_entry);
        btnExit=findViewById(R.id.btn_exit);


        Calendar calendar =Calendar.getInstance();
        hour=calendar.get(Calendar.HOUR_OF_DAY);
        minute=calendar.get(Calendar.MINUTE);

        //entry time
        btnEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(StaffActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hour=String.valueOf(hourOfDay);
                        String min=String.valueOf(minute);
                        tvEntry.setText(hour+":"+min+":00");
                    }
                },hour, minute, true);

                timePickerDialog.show();
            }
        });

        //exit time
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


        //radio Button
        radioGroup=findViewById(R.id.radio_staff);




        //normal code
        edtStaffName=findViewById(R.id.et_name);
        edtContact=findViewById(R.id.et_contact);
        edtAddress=findViewById(R.id.et_add);
        edtEamil=findViewById(R.id.et_email);
        edtPassword=findViewById(R.id.et_password);
        edtAgencyName=findViewById(R.id.et_agencyName);
        edtAgencyContact=findViewById(R.id.et_agencyContact);

        btnStaff=findViewById(R.id.btn_staff);

        btnStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strStaffName=edtStaffName.getText().toString();
                String strContact=edtContact.getText().toString();
                String strAddress=edtAddress.getText().toString();
                String strEmail=edtEamil.getText().toString();
                String strPassword=edtPassword.getText().toString();
                String strAgencyName=edtAgencyName.getText().toString();
                String strAgencyContact=edtAgencyContact.getText().toString();
                String strEntryTime=tvEntry.getText().toString();
                String strExitTime=tvExit.getText().toString();

                int id= radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(id);
                String strRadioStaff=radioButton.getText().toString();


                Log.e("Entry Time: ", strEntryTime);


                apicall(strStaffName,strContact,strAddress,strEmail,strPassword,strAgencyName,strAgencyContact,strEntryTime,strExitTime,strRadioStaff, strStaff);

            }
        });

        }

    private void apicall(String strStaffName, String strContact, String strAddress, String strEmail, String strPassword, String strAgencyName, String strAgencyContact, String strEntryTime, String strExitTime, String strRadioStaff, String strStaff) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, util.STAFF_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response: ",response);
                Intent intent = new Intent(StaffActivity.this, StaffDisplayActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("staffMemberName", strStaffName);
                hashMap.put("type", strStaff);
                hashMap.put("entryTime", strEntryTime);
                hashMap.put("exitTime", strExitTime);
                hashMap.put("contactNo", strContact);
                hashMap.put("address", strAddress);
                hashMap.put("email", strEmail);
                hashMap.put("password", strPassword);
                hashMap.put("isAllowed",strRadioStaff);
                hashMap.put("agencyName",strAgencyName);
                hashMap.put("agencyContactNumber",strAgencyContact);
                return hashMap;
            }
        };
        VolleySingleton.getInstance(StaffActivity.this).addToRequestQueue(stringRequest);

    }
}