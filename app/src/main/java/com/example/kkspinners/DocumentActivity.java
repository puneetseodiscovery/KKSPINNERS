package com.example.kkspinners;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kkspinners.Adapter.DocumentAdapter;
import com.example.kkspinners.RetroModel.GetDocumentApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    DocumentAdapter adapter;
    String id, token;
    ArrayList<GetDocumentApi.Datum> arrayList = new ArrayList<>();
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);

        toolbar = findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            getSupportActionBar().setTitle("Documents");

        }

        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);

        token = "Bearer " + sharedPreferences.getString("Token", "");

        id = sharedPreferences.getString("Order", "");

        recyclerView = findViewById(R.id.recyclerView);

        getData();

    }


    private void getData() {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<GetDocumentApi> call = apiInterface.getDocumentApi(token, id);
        final ProgressDialog dialog = ProgressBarClass.showProgressDialog(this, "Please wait...");
        dialog.show();

        call.enqueue(new Callback<GetDocumentApi>() {
            @Override
            public void onResponse(Call<GetDocumentApi> call, Response<GetDocumentApi> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    arrayList.clear();
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        arrayList.add(response.body().getData().get(i));
                        setData(arrayList);

                    }
                } else {
                    Toast.makeText(DocumentActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetDocumentApi> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(DocumentActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }


    private void setData(ArrayList<GetDocumentApi.Datum> arrayList) {
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new DocumentAdapter(DocumentActivity.this, arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return true;

            case R.id.logout2:
                editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
