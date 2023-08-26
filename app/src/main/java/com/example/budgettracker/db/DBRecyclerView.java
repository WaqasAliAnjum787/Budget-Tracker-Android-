package com.example.budgettracker.db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.budgettracker.R;

import java.util.ArrayList;
import java.util.List;

public class DBRecyclerView extends AppCompatActivity {
    RecyclerView dbRecyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbrecycler_view);

        dbRecyclerView=findViewById(R.id.db_recycler_view);
        dbRecyclerView.setHasFixedSize(true);
        dbRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String getData=getIntent().getData().toString();

        if (getData.equals("main")){
            DBAdapter dbAdapter=new DBAdapter(new DBHelper(this).getAllData());
            dbRecyclerView.setAdapter(dbAdapter);
        }
        else {
           if (getData.equals("one")){
               DBAdapter dbAdapter=new DBAdapter(new DBHelper(this).getAllDataFromFoodTable());
               dbRecyclerView.setAdapter(dbAdapter);
           }
           else {
               if (getData.equals("two")){
                   DBAdapter dbAdapter=new DBAdapter(new DBHelper(this).getAllDataFromHostelTable());
                   dbRecyclerView.setAdapter(dbAdapter);
               }
               else {
                   if (getData.equals("three")){
                       DBAdapter dbAdapter=new DBAdapter(new DBHelper(this).getAllDataFromEducationTable());
                       dbRecyclerView.setAdapter(dbAdapter);
                   }
                   else {
                       if (getData.equals("four")){
                           DBAdapter dbAdapter=new DBAdapter(new DBHelper(this).getAllDataFromMedicalHealthTable());
                           dbRecyclerView.setAdapter(dbAdapter);
                       }
                       else {
                           if (getData.equals("five")){
                               DBAdapter dbAdapter=new DBAdapter(new DBHelper(this).getAllDataFromPersonalTable());
                               dbRecyclerView.setAdapter(dbAdapter);
                           }
                           else {
                               if (getData.equals("six")){
                                   DBAdapter dbAdapter=new DBAdapter(new DBHelper(this).getAllDataFromBillsTable());
                                   dbRecyclerView.setAdapter(dbAdapter);
                               }
                               else {
                                   if (getData.equals("seven")){
                                       DBAdapter dbAdapter=new DBAdapter(new DBHelper(this).getAllDataFromEventTable());
                                       dbRecyclerView.setAdapter(dbAdapter);
                                   }
                                   else {
                                       if (getData.equals("eight")){
                                           DBAdapter dbAdapter=new DBAdapter(new DBHelper(this).getAllDataFromOtherTable());
                                           dbRecyclerView.setAdapter(dbAdapter);
                                       }
                                   }
                               }
                           }
                       }
                   }
               }
           }
        }
    }

}