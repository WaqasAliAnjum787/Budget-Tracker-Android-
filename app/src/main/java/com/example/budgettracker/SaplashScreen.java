package com.example.budgettracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SaplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saplash_screen);

        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                return false;
            }
        }).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SaplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}