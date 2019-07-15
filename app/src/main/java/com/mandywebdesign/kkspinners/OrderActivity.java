package com.mandywebdesign.kkspinners;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class OrderActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnOngoing, btnPending;
    FragmentManager manager;
    public static String Id;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        toolbar = findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            getSupportActionBar().setTitle("Orders List");
        }


        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        Id = sharedPreferences.getString("Order_No", "");

        btnOngoing = findViewById(R.id.btnongoing);
        btnPending = findViewById(R.id.btnpending);


        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame, new OngoingFragment()).commit();

        btnOngoing.setEnabled(false);
        btnPending.setEnabled(true);


        btnOngoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOngoing.setTextColor(getResources().getColor(R.color.colorBlack));
                btnPending.setTextColor(getResources().getColor(R.color.gray));
                btnPending.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnOngoing.setBackgroundColor(getResources().getColor(R.color.colorWhite));

                btnOngoing.setEnabled(false);
                btnPending.setEnabled(true);
                manager.beginTransaction().replace(R.id.frame, new OngoingFragment()).commit();
            }
        });

        btnPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                manager.beginTransaction().replace(R.id.frame, new PendingFragment()).commit();

                btnPending.setTextColor(getResources().getColor(R.color.colorBlack));
                btnOngoing.setTextColor(getResources().getColor(R.color.gray));
                btnOngoing.setBackgroundColor(getResources().getColor(R.color.colorGray));
                btnPending.setBackgroundColor(getResources().getColor(R.color.colorWhite));

                btnOngoing.setEnabled(true);
                btnPending.setEnabled(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }


}
