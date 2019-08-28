package com.mandywebdesign.kkspinners;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mandywebdesign.kkspinners.RetroModel.OrderApi;

public class DetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtOrderName, txtInvNumber, txtOrderStatus, txtQuantity, txtPrice, txtEDT, txtAWB, txtnumberOfContainer, txtContationNo, txtUpdate;
    TextView txtinvNumber;
    int i;
    RelativeLayout viewDocument, viewSpecial, viewInvCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        init();


        toolbar = findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            getSupportActionBar().setTitle("Orders Details");
        }

        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        String a = sharedPreferences.getString("position", "");

        i = Integer.parseInt(a);

        final OrderApi.Datum datum = OngoingFragment.apiArrayList.get(i);
        final OrderApi.ContractDetail contractDetail = OngoingFragment.apiContract.get(i);

        txtOrderName.setText(datum.getProduct());
        txtInvNumber.setText("Contract Number : " + contractDetail.getContractNo());
        txtOrderStatus.setText("Status : " + datum.getCurrentStatus());
        txtinvNumber.setText(datum.getInvNo());
        txtQuantity.setText(datum.getQuantity());
        txtPrice.setText(datum.getPrice());
        txtEDT.setText(datum.getEtd());
        txtAWB.setText(datum.getAwb());
        txtContationNo.setText(datum.getContainerNo());
        txtnumberOfContainer.setText(datum.getNoOfContainer());

        String update = datum.getUpdatedAt();
        int spacePos = update.indexOf(" ");
        if (spacePos > 0) {
            String youString = update.substring(0, spacePos);
            txtUpdate.setText("Last update( " + youString + " )");
        }


        //view the specila remarks
        viewSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(datum.getSpecialRemarks())) {
                    Intent intent = new Intent(getApplicationContext(), SpecialRemarksActivity.class);
                    intent.putExtra("Special", " " + datum.getSpecialRemarks().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(DetailsActivity.this, "Special Remarks is Empty", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //view the invCopy
        viewInvCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://kkspinners.in/adminportal/public/Invoices/" + datum.getInvCopy();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        //view the document
        viewDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Order", datum.getOrderId().toString());
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), DocumentActivity.class);
                startActivity(intent);

            }
        });


    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.tooolbar);
        txtOrderName = (TextView) findViewById(R.id.orderName);
        txtInvNumber = (TextView) findViewById(R.id.orderNumber);
        txtOrderStatus = (TextView) findViewById(R.id.orderStatus);
        txtinvNumber = (TextView) findViewById(R.id.invNumber);
        txtQuantity = (TextView) findViewById(R.id.orderQuantity);
        txtPrice = (TextView) findViewById(R.id.orderPrice);
        txtEDT = (TextView) findViewById(R.id.orderEDT);
        txtAWB = (TextView) findViewById(R.id.orderAWB);
        txtnumberOfContainer = (TextView) findViewById(R.id.orderNoContainer);
        txtContationNo = (TextView) findViewById(R.id.orderContainerNo);
        txtUpdate = (TextView) findViewById(R.id.orderUpdate);
        viewDocument = (RelativeLayout) findViewById(R.id.viewDocument);
        viewInvCopy = (RelativeLayout) findViewById(R.id.viewInv);
        viewSpecial = (RelativeLayout) findViewById(R.id.viewSpecial);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
