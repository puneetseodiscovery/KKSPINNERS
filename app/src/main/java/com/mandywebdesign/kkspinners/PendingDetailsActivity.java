package com.mandywebdesign.kkspinners;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mandywebdesign.kkspinners.RetroModel.PendingOrderApi;

public class PendingDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtOrderName, txtInvNumber, txtOrderStatus, txtQuantity, txtPrice, txtCurrent;
    RelativeLayout viewSpecial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_details);

        init();
        toolbar = findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            getSupportActionBar().setTitle("Pending Orders Details");
        }

        String no = getIntent().getStringExtra("Position");
        int i = Integer.parseInt(no);

        final PendingOrderApi.Datum datum = PendingFragment.apiArrayList.get(i);
        PendingOrderApi.ContractDetail contractDetail = PendingFragment.apiContract.get(i);

        txtOrderName.setText(datum.getPenProduct());
        txtInvNumber.setText("Contract Number : " + contractDetail.getContractNo());
        txtOrderStatus.setText("Status : " + datum.getPenCurrentStatus());
        txtQuantity.setText(datum.getPenQuantity());
        txtPrice.setText(datum.getPenPrice());
        txtCurrent.setText(datum.getPenLcStatus());


        //view the specila remarks
        viewSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(datum.getPenSpecialRemarks())) {
                    Toast.makeText(PendingDetailsActivity.this, "Special Remarks is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), SpecialRemarksActivity.class);
                    intent.putExtra("Special", " " + datum.getPenSpecialRemarks().toString());
                    startActivity(intent);
                }
            }
        });
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.tooolbar);
        txtOrderName = (TextView) findViewById(R.id.orderName);
        txtInvNumber = (TextView) findViewById(R.id.orderNumber);
        txtOrderStatus = (TextView) findViewById(R.id.orderStatus);
        txtQuantity = (TextView) findViewById(R.id.orderQuantity);
        txtPrice = (TextView) findViewById(R.id.orderPrice);
        txtCurrent = (TextView) findViewById(R.id.orderCstatus);
        viewSpecial = (RelativeLayout) findViewById(R.id.viewSpecial);
    }
}
