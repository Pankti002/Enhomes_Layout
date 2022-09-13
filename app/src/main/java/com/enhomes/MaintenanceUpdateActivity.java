package com.enhomes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MaintenanceUpdateActivity extends AppCompatActivity {

    EditText edtMaintenanceAmount,edtPenalty;
    TextView tvCreationDate, tvPaymentDate,tvLastDate, tvMonth;

    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);

        edtMaintenanceAmount=findViewById(R.id.et_amt);
        edtPenalty=findViewById(R.id.et_penalty);

        tvCreationDate=findViewById(R.id.tv_create);
        tvPaymentDate=findViewById(R.id.tv_payDate);
        tvLastDate=findViewById(R.id.tv_lastDate);

        btnClick=findViewById(R.id.btn_maintenance);
        btnClick.setText("Update Maintenance");

        Intent i = getIntent();
        int maintenanceId=i.getIntExtra("MAINTENANCE_ID",0);
        Log.e("MAINTENANCE_ID", String.valueOf(maintenanceId));
        String strMaintenanceAmount=i.getStringExtra("MAINTENANCE_AMOUNT");
        String strPenalty=i.getStringExtra("PENALTY");
        String strCreationDate=i.getStringExtra("CREATION_DATE");
        String strPaymentDate=i.getStringExtra("PAYMENT_DATE");
        String strLastDate=i.getStringExtra("LAST_DATE");

        edtMaintenanceAmount.setText(strMaintenanceAmount);
        edtPenalty.setText(strPenalty);
        tvCreationDate.setText(strCreationDate);
        tvPaymentDate.setText(strPaymentDate);
        tvLastDate.setText(strLastDate);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintenanceUpdateActivity.this,MaintenanceDisplayActivity.class);
                startActivity(intent);
            }
        });

    }
}