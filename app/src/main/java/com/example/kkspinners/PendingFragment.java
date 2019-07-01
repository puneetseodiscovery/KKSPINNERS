package com.example.kkspinners;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kkspinners.Adapter.PaddingAdapter;
import com.example.kkspinners.Adapter.SpacesItemDecoration;
import com.example.kkspinners.RetroModel.OrderApi;
import com.example.kkspinners.RetroModel.PendingOrderApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PendingFragment extends Fragment {

    RecyclerView recyclerView;
    View view;
    PaddingAdapter adapter;
    String token;
    public static ArrayList<PendingOrderApi.Datum> apiArrayList = new ArrayList<PendingOrderApi.Datum>();


    public PendingFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pending, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        token = "Bearer " + sharedPreferences.getString("Token", "");

        recyclerView = view.findViewById(R.id.recyclerView);


        getData();

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        getData();
                    }
                }, 1000);
            }
        });


        return view;
    }


    private void getData() {
        final ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<PendingOrderApi> call = apiInterface.getPendingApi(token, OrderActivity.Id);
        final ProgressDialog dialog = ProgressBarClass.showProgressDialog(getContext(), "Please wait...");
        dialog.show();


        call.enqueue(new Callback<PendingOrderApi>() {
            @Override
            public void onResponse(Call<PendingOrderApi> call, Response<PendingOrderApi> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    apiArrayList.clear();
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        apiArrayList.add(response.body().getData().get(i));
                        if (response.body().getData().get(i).equals(null)) {
                            Toast.makeText(getContext(), "No Pending Data ", Toast.LENGTH_SHORT).show();
                        } else
                            setData(apiArrayList);
                    }

                } else {
                    Toast.makeText(getContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PendingOrderApi> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData(ArrayList<PendingOrderApi.Datum> apiArrayList) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new PaddingAdapter(getContext(), apiArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
