package com.example.kkspinners;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kkspinners.Adapter.OngoingAdapter;
import com.example.kkspinners.Adapter.SpacesItemDecoration;
import com.example.kkspinners.RetroModel.OrderApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class OngoingFragment extends Fragment {

    RecyclerView recyclerView;
    OngoingAdapter adapter;
    View view;
    SharedPreferences sharedPreferences;
    String token;
    public static ArrayList<OrderApi.Datum> apiArrayList = new ArrayList<OrderApi.Datum>();
    SwipeRefreshLayout swipeRefreshLayout;


    public OngoingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ongoing, container, false);
        recyclerView = view.findViewById(R.id.recyclerView1);

        sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        token = "Bearer " + sharedPreferences.getString("Token", "");


        swipeRefreshLayout = view.findViewById(R.id.swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        getOrder();
                    }
                }, 1000);
            }
        });


        getOrder();

        return view;
    }


    private void getOrder() {
        final ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<OrderApi> call = apiInterface.orderApi(token, OrderActivity.Id);
        swipeRefreshLayout.setRefreshing(true);
        call.enqueue(new Callback<OrderApi>() {
            @Override
            public void onResponse(Call<OrderApi> call, Response<OrderApi> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    apiArrayList.clear();

                    if (response.body().getStatus().equals(200)) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            apiArrayList.add(response.body().getData().get(i));
                            setData(apiArrayList);
                        }

                    } else {
                        Toast.makeText(getContext(), "Unauthorized", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderApi> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData(ArrayList<OrderApi.Datum> apiArrayList) {
        adapter = new OngoingAdapter(getContext(), apiArrayList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}
