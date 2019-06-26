package com.example.kkspinners.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kkspinners.BottomDialog;
import com.example.kkspinners.DetailsActivity;
import com.example.kkspinners.DocumentActivity;
import com.example.kkspinners.R;
import com.example.kkspinners.RetroModel.OrderApi;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.ViewHolder> {

    Context context;
    ArrayList<OrderApi.Datum> apiArrayList = new ArrayList<OrderApi.Datum>();


    public OngoingAdapter(Context context, ArrayList<OrderApi.Datum> apiArrayList) {
        this.context = context;
        this.apiArrayList = apiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.customorder, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final OrderApi.Datum datum = apiArrayList.get(i);

        viewHolder.txtOrder.setText(datum.getProduct());
        viewHolder.txtPrice.setText(datum.getPrice());
        viewHolder.txtOrderNumber.setText(datum.getInvNo());
        viewHolder.txtWeight.setText(datum.getQuantity() + " KG");


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                String abc = String.valueOf(i);
                SharedPreferences sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("position", abc);
                editor.apply();
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return apiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtOrder, txtPrice, txtOrderNumber, txtWeight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrder = itemView.findViewById(R.id.txtOrdername);
            txtPrice = itemView.findViewById(R.id.txtOrderPrice);
            txtOrderNumber = itemView.findViewById(R.id.txtOrderNumber);
            txtWeight = itemView.findViewById(R.id.txtOrderWeight);
        }
    }
}
