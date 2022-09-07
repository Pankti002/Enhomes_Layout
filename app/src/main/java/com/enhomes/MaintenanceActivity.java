package com.enhomes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

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

public class MaintenanceActivity extends AppCompatActivity {
    EditText edtHouseId, edtMaintenanceAmount, edtPenalty;
    Button btnMaintenance;
    String strMaintenanceMonth;

    RadioGroup radioGroup;
    Spinner spinnerMonth;
    String strMonths[]={"Select a Month","January","February","March","April","May","June","July","August","September",
            "October","November","December"};

    TextView tvDisDate, tvPayDate, tvLastDate;
    ImageButton btnDate, btnPayDate, btnLastDate;
    private int date;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        //Spinner for Months
        spinnerMonth=findViewById(R.id.spinner_month);

        ArrayAdapter<String> arrayAdapter = new
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,strMonths){
                    @Override
                    public View getDropDownView(int position, @Nullable View convertView,
                                                @NonNull ViewGroup parent) {

                        TextView tvData = (TextView) super.getDropDownView(position, convertView, parent);
                        tvData.setTextColor(Color.WHITE);
                        tvData.setTextSize(20);
                        return tvData;
                    }

                };
        spinnerMonth.setAdapter(arrayAdapter);

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strMaintenanceMonth=strMonths[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //radio Button
        radioGroup=findViewById(R.id.radio_grp);



        //Date :- creationDate,paymentDate,lastDate
        tvDisDate=findViewById(R.id.tv_create);
        tvPayDate=findViewById(R.id.tv_payDate);
        tvLastDate=findViewById(R.id.tv_lastDate);

        btnDate=findViewById(R.id.btn_date);
        btnPayDate=findViewById(R.id.btn_payDate);
        btnLastDate=findViewById(R.id.btn_lastDate);

        Calendar calendar = Calendar.getInstance();
        date=calendar.get(Calendar.DAY_OF_MONTH);
        month=calendar.get(Calendar.MONTH);
        year=calendar.get(Calendar.YEAR);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MaintenanceActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvDisDate.setText(year+"-"+0+(month+1)+"-"+dayOfMonth);
                    }
                },date,month,year);
                datePickerDialog.show();
            }
        });

        btnPayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MaintenanceActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvPayDate.setText(year+"-"+0+(month+1)+"-"+dayOfMonth);
                    }
                },date,month,year);
                datePickerDialog.show();
            }
        });

        btnLastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MaintenanceActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvLastDate.setText(year+"-"+0+(month+1)+"-"+dayOfMonth);
                    }
                },date,month,year);
                datePickerDialog.show();
            }
        });



        //Normal code
        edtHouseId=findViewById(R.id.et_houseId);
        edtMaintenanceAmount=findViewById(R.id.et_amt);
        edtPenalty=findViewById(R.id.et_penalty);
        btnMaintenance=findViewById(R.id.btn_maintenance);

        btnMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strHouseId=edtHouseId.getText().toString();
                String strMaintenanceAmount=edtMaintenanceAmount.getText().toString();
                String strPenalty=edtPenalty.getText().toString();
                String strCreateDate=tvDisDate.getText().toString();
                String strPaymentDate=tvPayDate.getText().toString();
                String strLastDate=tvLastDate.getText().toString();
                int id= radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(id);

                String strRadioButton=radioButton.getText().toString();
                Log.e("House: ",strHouseId);
                Log.e("CreateDate: ",strCreateDate);
                Log.e("PayDate: ",strPaymentDate);
                Log.e("LastDate: ",strLastDate);

                apiCall(strHouseId,strMaintenanceMonth,strPenalty,strCreateDate,strPaymentDate,strLastDate,strRadioButton,strMaintenanceAmount);

            }
        });

    }

    private void apiCall( String strHouseId, String strMaintenanceMonth, String strPenalty, String strCreateDate, String strPaymentDate, String strLastDate, String strRadioButton, String strMaintenanceAmount) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, util.MAINTENANCE_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done",response);
                Intent intent = new Intent(MaintenanceActivity.this, MaintenanceDisplayActivity.class);
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
                hashMap.put("house", strHouseId);
                hashMap.put("creationDate", strCreateDate);
                hashMap.put("month", strMaintenanceMonth);
                hashMap.put("maintenanceAmount", strMaintenanceAmount);
                hashMap.put("maintenancePaid", strRadioButton);
                hashMap.put("paymentDate", strPaymentDate);
                hashMap.put("lastDate", strLastDate);
                hashMap.put("penalty", strPenalty);
                return hashMap;


            }
        };
        VolleySingleton.getInstance(MaintenanceActivity.this).addToRequestQueue(stringRequest);

    }
}