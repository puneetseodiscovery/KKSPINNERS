package com.example.kkspinners;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplaceScreen extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String Token;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace_screen);

        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        Token = sharedPreferences.getString("Token", "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!Token.equals("")) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        }, 2000);

    }
}
