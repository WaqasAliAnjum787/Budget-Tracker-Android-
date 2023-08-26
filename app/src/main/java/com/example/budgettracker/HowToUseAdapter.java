package com.example.budgettracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HowToUseAdapter extends RecyclerView.Adapter<HowToUseAdapter.ViewHolder> {

    List<HowToUseModelClass>  howToUseModelClasses;


    public HowToUseAdapter(List<HowToUseModelClass> howToUseModelClasses) {
        this.howToUseModelClasses = howToUseModelClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_holder_how_to_use,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HowToUseModelClass howToUseModelClass=howToUseModelClasses.get(position);
        holder.title_how_to_use.setText(howToUseModelClass.getTitle());
        holder.iv_how_to_use.setImageResource(howToUseModelClass.getImage());
    }

    @Override
    public int getItemCount() {
        return howToUseModelClasses.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title_how_to_use;
        ImageView iv_how_to_use;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_how_to_use=itemView.findViewById(R.id.title_how_to_use);
            iv_how_to_use=itemView.findViewById(R.id.iv_how_to_use);
        }
    }
}
