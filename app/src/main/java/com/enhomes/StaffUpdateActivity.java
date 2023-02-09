package com.enhomes;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import utils.VolleySingleton;
import utils.util;
import java.util.HashMap;
import java.util.Map;


public class StaffUpdateActivity extends AppCompatActivity {
    EditText edtStaffName, edtContact, edtAddress, edtAgencyName, edtAgencyContact;
    EditText edtEntryTime, edtExitTime;

    Button btnStaff;

    Spinner spinnerType;
    String strTypes[] = {"Select a Type", "SecurityGuard", "Sweeper", "PumpOperator", "Gardener"};
    String strStaff;

    ImageButton btnEntry, btnExit;
    TextView tvEntry, tvExit;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        Intent i = getIntent();

        edtStaffName = findViewById(R.id.et_name);
        edtContact = findViewById(R.id.et_contact);
        edtAddress = findViewById(R.id.et_add);
        edtAgencyName = findViewById(R.id.et_agencyName);
        edtAgencyContact = findViewById(R.id.et_agencyContact);
        edtEntryTime = findViewById(R.id.tv_entry);
        edtExitTime = findViewById(R.id.tv_exit);
        btnStaff = findViewById(R.id.btn_staff);

        String staffId = i.getStringExtra("STAFF_ID");
        String strStaffName = i.getStringExtra("STAFF_MEMBER_NAME");
        String strContact = i.getStringExtra("CONTACT");
        String strAddress = i.getStringExtra("ADDRESS");
        String strAgencyName = i.getStringExtra("AGENCY_NAME");
        String strAgencyContact = i.getStringExtra("AGENCY_CONTACT_NUMBER");
        String strEntryTime = i.getStringExtra("ENTRY_TIME");
        String strExitTime = i.getStringExtra("EXIT_TIME");

        Log.e("Data: ",staffId+" "+strStaffName+" "+strContact+" "+strAddress+" "+strEntryTime+" "+strExitTime+" "+strAgencyName+" "+strAgencyContact);
        StaffLangModel staffLangModel = new StaffLangModel();
        edtStaffName.setText(strStaffName);
        edtContact.setText(strContact);
        edtAddress.setText(strAddress);
        edtAgencyName.setText(strAgencyName);
        edtAgencyContact.setText(strAgencyContact);
        edtEntryTime.setText(strEntryTime);
        edtExitTime.setText(strExitTime);

        btnStaff.setText("Update Staff");
        btnStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strStaffName = edtStaffName.getText().toString();
                String strContact = edtContact.getText().toString();
                String strAddress = edtAddress.getText().toString();
                String strAgencyName = edtAgencyName.getText().toString();
                String strAgencyContact = edtAgencyContact.getText().toString();
                String strEntryTime = edtEntryTime.getText().toString();
                String strExitTime = edtExitTime.getText().toString();

                Log.e("Data",staffId+" "+strStaffName+" "+strContact+" "+strAddress+" "+strAgencyName+" "+strAgencyContact+" "+strEntryTime+" "+strExitTime);
                apicall(staffId, strStaffName, strContact, strAddress, strAgencyName, strAgencyContact, strEntryTime, strExitTime);

            }
        });


    }

    private void apicall(String staffId, String strStaffName, String strContact, String strAddress, String strAgencyName, String strAgencyContact, String strEntryTime, String strExitTime) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, util.STAFF_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("api calling done", response);
                Intent intent = new Intent(StaffUpdateActivity.this, StaffDisplayActivity.class);
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
                hashMap.put("staffId", staffId);
                hashMap.put("staffMemberName", strStaffName);
                hashMap.put("contactNo", strContact);
                hashMap.put("address", strAddress);
                hashMap.put("entryTime", strEntryTime);
                hashMap.put("exitTime", strExitTime);
                hashMap.put("agencyName", strAgencyName);
                hashMap.put("agencyContactNumber", strAgencyContact);
                return hashMap;


            }
    };
        VolleySingleton.getInstance(StaffUpdateActivity.this).addToRequestQueue(stringRequest);
}
}
