package com.example.kkspinners;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kkspinners.RetroModel.PendingOrderApi;

public class PendingDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtOrderName, txtInvNumber, txtOrderStatus, txtQuantity, txtPrice, txtCurrent, txtCreate, txtUpdate;
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

        txtOrderName.setText(datum.getPenProduct());
        txtInvNumber.setText("Inv Number : " + datum.getPSaleContNo());
        txtOrderStatus.setText("Status : " + datum.getPenLcStatus());
        txtQuantity.setText(datum.getPenQuantity());
        txtPrice.setText(datum.getPenPrice());
        txtCurrent.setText(datum.getPenCurrentStatus());
        txtCreate.setText(datum.getCreatedAt());
        txtUpdate.setText(datum.getUpdatedAt());


        //view the specila remarks
        viewSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SpecialRemarksActivity.class);
                intent.putExtra("Special", datum.getPenSpecialRemarks().toString());
                startActivity(intent);
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
        txtCreate = (TextView) findViewById(R.id.orderCreate);
        txtUpdate = (TextView) findViewById(R.id.orderUpdate);
        viewSpecial = (RelativeLayout) findViewById(R.id.viewSpecial);
    }
}
