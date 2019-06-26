package com.example.kkspinners;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BottomDialog {

    public static BottomSheetDialog dialog(final Context context, String msg) {

        final BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(R.layout.bottomdilog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button cancel;
        TextView textView;
        cancel = dialog.findViewById(R.id.dilog_cancel);
        textView = dialog.findViewById(R.id.txtSpecial);

        textView.setText(msg);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }
}
