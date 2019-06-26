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
import android.support.v4.app.Fragment;
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

        getOrder();

        return view;
    }


    private void getOrder() {
        final ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<OrderApi> call = apiInterface.orderApi(token, OrderActivity.Id);
        final ProgressDialog dialog = ProgressBarClass.showProgressDialog(getContext(), "Please wait...");
        dialog.show();

        call.enqueue(new Callback<OrderApi>() {
            @Override
            public void onResponse(Call<OrderApi> call, Response<OrderApi> response) {
                dialog.dismiss();
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
                dialog.dismiss();
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