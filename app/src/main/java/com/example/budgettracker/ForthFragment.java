package com.example.budgettracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ForthFragment extends Fragment {

    RecyclerView rvHowToUse;

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_forth,null);
        recyclerView=view.findViewById(R.id.rv_how_to_use);

        HowToUseAdapter howToUseAdapter=new HowToUseAdapter(howToUseData());
        recyclerView.setAdapter(howToUseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        return view;
    }

    public List<HowToUseModelClass> howToUseData(){
        List<HowToUseModelClass> howToUseModelClasses=new ArrayList<>();
        howToUseModelClasses.add(new HowToUseModelClass(R.drawable.hostel1,"Welcome to our finance app! We're excited to announce some new features that we've added and some upcoming updates that we're working on. Here's what's new: Welcome to our finance app! We're excited to announce some new features that we've added and some upcoming updates that we're working on. Here's what's new:Welcome to our finance app! We're excited to announce some new features that we've added and some upcoming updates that we're working on. Here's what's new:"));
        howToUseModelClasses.add(new HowToUseModelClass(R.drawable.food1,"How to use"));
        howToUseModelClasses.add(new HowToUseModelClass(R.drawable.bills1,"How to use"));
        howToUseModelClasses.add(new HowToUseModelClass(R.drawable.educational1,"How to use"));
        howToUseModelClasses.add(new HowToUseModelClass(R.drawable.medical1,"How to use"));
        howToUseModelClasses.add(new HowToUseModelClass(R.drawable.other1,"How to use"));
        return  howToUseModelClasses;
    }
}