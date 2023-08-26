package com.example.budgettracker.db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgettracker.R;

import java.util.List;

public class DBAdapter  extends RecyclerView.Adapter<DBAdapter.Holder> {

    List<DBModelMain> dbModelMainList;

    public DBAdapter(List<DBModelMain> dbModelMainList) {
        this.dbModelMainList = dbModelMainList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.db_recycler_view,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DBModelMain dbModelMain=dbModelMainList.get(position);
        holder.tvMoney.setText(String.valueOf(dbModelMain.getEnteredMoney()));
        holder.tvDescription.setText(dbModelMain.getDescription());
        holder.tvDateAndTime.setText(dbModelMain.getDateAndTime());
    }

    @Override
    public int getItemCount() {
        return dbModelMainList.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView tvMoney,tvDescription,tvDateAndTime;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvDateAndTime=itemView.findViewById(R.id.tv_db_date_and_time);
            tvMoney=itemView.findViewById(R.id.tv_db_money);
            tvDescription=itemView.findViewById(R.id.tv_db_discription);

        }
    }
}
