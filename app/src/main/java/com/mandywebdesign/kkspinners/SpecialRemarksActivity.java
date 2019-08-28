package com.mandywebdesign.kkspinners;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

public class SpecialRemarksActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_remarks);

        toolbar = findViewById(R.id.tooolbar);
        textView = findViewById(R.id.text);

        toolbar = findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            getSupportActionBar().setTitle("Special Remarks");
        }
        if (TextUtils.isEmpty(getIntent().getStringExtra("Special"))) {

            Toast.makeText(this, "Special Remarks is Empty", Toast.LENGTH_SHORT).show();
        } else {
            textView.setText(getIntent().getStringExtra("Special"));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
