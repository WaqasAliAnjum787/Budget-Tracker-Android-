package com.example.budgettracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgettracker.db.DBHelper;
import com.example.budgettracker.db.DBRecyclerView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class secondFragment extends Fragment {
    List<PieEntry> pieEntries;
    PieChart pieChart;
    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        onResume();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e.equalTo(pieEntries.get(0))) {
                    Intent intent = new Intent(getContext(), DBRecyclerView.class);
                    intent.setData(Uri.parse("two"));
                    startActivity(intent);
                } else {
                    if (e.equalTo(pieEntries.get(1))) {
                        Intent intent = new Intent(getContext(), DBRecyclerView.class);
                        intent.setData(Uri.parse("three"));
                        startActivity(intent);
                    } else {
                        if (e.equalTo(pieEntries.get(2))) {
                            Intent intent = new Intent(getContext(), DBRecyclerView.class);
                            intent.setData(Uri.parse("five"));
                            startActivity(intent);
                        } else {
                            if (e.equalTo(pieEntries.get(3))) {
                                Intent intent = new Intent(getContext(), DBRecyclerView.class);
                                intent.setData(Uri.parse("seven"));
                                startActivity(intent);
                            } else {
                                if (e.equalTo(pieEntries.get(4))) {
                                    Intent intent = new Intent(getContext(), DBRecyclerView.class);
                                    intent.setData(Uri.parse("four"));
                                    startActivity(intent);
                                } else {
                                    if (e.equalTo(pieEntries.get(5))) {
                                        Intent intent = new Intent(getContext(), DBRecyclerView.class);
                                        intent.setData(Uri.parse("one"));
                                        startActivity(intent);
                                    } else {
                                        if (e.equalTo(pieEntries.get(6))) {
                                            Intent intent = new Intent(getContext(), DBRecyclerView.class);
                                            intent.setData(Uri.parse("six"));
                                            startActivity(intent);
                                        } else {
                                            if (e.equalTo(pieEntries.get(7))) {
                                                Intent intent = new Intent(getContext(), DBRecyclerView.class);
                                                intent.setData(Uri.parse("eight"));
                                                startActivity(intent);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            @Override
            public void onNothingSelected() {
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_second, null);
        pieChart = view.findViewById(R.id.pie_chart);
        String label = "";
        //String label = "type";
        pieEntries = new Stack<>();



        Map<String, Double> typeAmountMap = new HashMap<>();
        typeAmountMap.put("Study", new DBHelper(getContext()).sumOfEducationalTable());
        typeAmountMap.put("Food", new DBHelper(getContext()).sumOfFoodTable());
        typeAmountMap.put("Personal", new DBHelper(getContext()).sumOfPersonalTable());
        typeAmountMap.put("Hostel", new DBHelper(getContext()).sumOfHostelTable());
        typeAmountMap.put("Bills", new DBHelper(getContext()).sumOfBillsTable());
        typeAmountMap.put("Event", new DBHelper(getContext()).sumOfEventTable());
        typeAmountMap.put("Medical", new DBHelper(getContext()).sumOfMedicalHealthTable());
        typeAmountMap.put("Other", new DBHelper(getContext()).sumOfOthersTable());


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#304567"));
        colors.add(Color.parseColor("#309967"));
        colors.add(Color.parseColor("#476567"));
        colors.add(Color.parseColor("#890567"));
        colors.add(Color.parseColor("#a35567"));
        colors.add(Color.parseColor("#ff5f67"));
        colors.add(Color.parseColor("#3ca567"));
        colors.add(Color.parseColor("#598567"));
        colors.add(Color.parseColor("#a65967"));

        for (String type : typeAmountMap.keySet()) {
            pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        pieDataSet.setValueTextSize(12f);
        //providing color list for coloring different entries
        pieDataSet.setColors(colors);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.GRAY);
        pieChart.setCenterText("Your Expenses");
        pieChart.setHoleRadius(30f);
        pieChart.setData(pieData);
       // pieChart.setDragDecelerationFrictionCoef(0.81f);
        pieChart.invalidate();
        super.onResume();
    }
}
