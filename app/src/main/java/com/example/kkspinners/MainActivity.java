package com.example.kkspinners;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.example.kkspinners.Adapter.ContractAdapter;
import com.example.kkspinners.RetroModel.ContractApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ContractAdapter adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String Token;
    public static ArrayList<String> contractId = new ArrayList<>();
    public static ArrayList<String> contractName = new ArrayList<>();
    public static ArrayList<String> contractUpdate = new ArrayList<>();
    public static ArrayList<String> contractNo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        Token = "Bearer " + sharedPreferences.getString("Token", "");
        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu);

        getContract();

    }

    private void getContract() {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<ContractApi> call = apiInterface.contractApi(Token, "application/json");
        final ProgressDialog dialog = ProgressBarClass.showProgressDialog(this, "Please wait");
        dialog.show();

        call.enqueue(new Callback<ContractApi>() {
            @Override
            public void onResponse(Call<ContractApi> call, Response<ContractApi> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals(200)) {
                        clear();
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            contractId.add(response.body().getData().get(i).getContractId().toString());
                            contractName.add(response.body().getData().get(i).getUser().toString());
                            contractNo.add(response.body().getData().get(i).getContractNo().toString());
                            contractUpdate.add(response.body().getData().get(i).getContractUpdated().toString());
                            Log.d("123456", "" + contractNo);
                            setData();
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "Unauthorized", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ContractApi> call, Throwable t) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setData() {
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new ContractAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        getContract();
                    }
                }, 1000);
            }
        });
    }

    private void clear() {
        contractId.clear();
        contractName.clear();
        contractNo.clear();
        contractUpdate.clear();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.logout:
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
