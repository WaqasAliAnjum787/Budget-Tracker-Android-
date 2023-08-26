package com.example.budgettracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class AddBudget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        String name=getIntent().getStringExtra("Name");
        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();
    }
}