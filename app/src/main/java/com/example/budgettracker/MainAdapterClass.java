package com.example.budgettracker;

import android.hardware.lights.LightState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapterClass extends RecyclerView.Adapter<MainAdapterClass.ViewHolder> {

    List<MainModelClass> modelClassList;
    onItemClick itemClick;

    public void setItemClick(onItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public MainAdapterClass(List<MainModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_view_holder,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainModelClass mainModelClass=modelClassList.get(position);
        holder.tvTitle.setText(mainModelClass.getTitle());
        holder.ivMain.setImageResource(mainModelClass.getImageId());

    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView ivMain;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            ivMain=itemView.findViewById(R.id.ivMain);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.onClick(itemView,getAdapterPosition());
                }
            });
        }
    }

    interface onItemClick{
        void  onClick(View ItemView,int position);
    }
}
