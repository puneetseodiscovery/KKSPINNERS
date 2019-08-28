package com.mandywebdesign.kkspinners.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mandywebdesign.kkspinners.MainActivity;
import com.mandywebdesign.kkspinners.OrderActivity;
import com.mandywebdesign.kkspinners.R;


public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ViewHolder> {
    Context context;

    public ContractAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.customcontract, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {


        viewHolder.txtName.setText(MainActivity.contractName.get(i));
        viewHolder.txtDate.setText(MainActivity.contractUpdate.get(i));
        viewHolder.txtKk.setText(MainActivity.contractNo.get(i));


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Order_No", MainActivity.contractId.get(i));
                editor.apply();

                Intent intent = new Intent(context, OrderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return MainActivity.contractId.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtDate, txtKk, txtView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.textName);
            txtDate = itemView.findViewById(R.id.textdate);
            txtKk = itemView.findViewById(R.id.textKk);

            txtView = itemView.findViewById(R.id.textOrder);

        }
    }
}
