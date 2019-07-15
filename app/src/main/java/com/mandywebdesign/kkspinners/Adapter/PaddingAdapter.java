package com.mandywebdesign.kkspinners.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mandywebdesign.kkspinners.PendingDetailsActivity;
import com.mandywebdesign.kkspinners.R;
import com.mandywebdesign.kkspinners.RetroModel.PendingOrderApi;

import java.util.ArrayList;

public class PaddingAdapter extends RecyclerView.Adapter<PaddingAdapter.ViewHolder> {

    Context context;
    ArrayList<PendingOrderApi.Datum> arrayList = new ArrayList<>();

    public PaddingAdapter(Context context, ArrayList<PendingOrderApi.Datum> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.customorder, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final PendingOrderApi.Datum datum = arrayList.get(i);

        viewHolder.txtOrder.setText(datum.getPenProduct());
        viewHolder.txtPrice.setText(datum.getPenPrice());
        viewHolder.txtWeight.setText(datum.getPenQuantity());
        viewHolder.txtordera.setText(datum.getPSaleContNo());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PendingDetailsActivity.class);
                String abc = String.valueOf(i);
                intent.putExtra("Position", abc);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtOrder, txtPrice, txtWeight, txtordera;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtOrder = itemView.findViewById(R.id.txtOrdername);
            txtPrice = itemView.findViewById(R.id.txtOrderPrice);
            txtWeight = itemView.findViewById(R.id.txtOrderWeight);
            txtordera = itemView.findViewById(R.id.txtOrderNumber);
        }
    }
}
