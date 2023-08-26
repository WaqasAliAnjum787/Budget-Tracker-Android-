package com.example.budgettracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgettracker.db.DBHelper;
import com.example.budgettracker.db.DBModelMain;
import com.example.budgettracker.db.DBRecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import java.util.Calendar;

public class firstFrgment extends Fragment {
    TextView tvMain;
    RecyclerView rvMain;
    ImageView btnAddBudget;
    DBHelper dbHelper;
    ImageView ivSeeMore;

    double capital;
    double grandTotal;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Bottom Sheet
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.bottom_shee_dialog);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setDismissWithAnimation(true);
        EditText budget=bottomSheetDialog.findViewById(R.id.etAdBudget);
        EditText discription=bottomSheetDialog.findViewById(R.id.budget_discription);
        Button buttonAtBottomSheetToAdMoney=bottomSheetDialog.findViewById(R.id.btn_added);
        TextView tvViewMore=bottomSheetDialog.findViewById(R.id.tv_view_more);
        ImageView bottomSheetImage=bottomSheetDialog.findViewById(R.id.iv_bottom_sheet);
        TextView bottomSheetHeader=bottomSheetDialog.findViewById(R.id.tv_title_of_bottom_sheet);

        dbHelper=DBHelper.getDbHelper(getContext());



        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_first_frgment, container, false);
        tvMain=view.findViewById(R.id.tvBlank);
        rvMain=view.findViewById(R.id.rvMain);
        ivSeeMore=view.findViewById(R.id.see_more);
        btnAddBudget=view.findViewById(R.id.btnNextActivity);

        ivSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.see_more);
                TextView tvCapital = dialog.findViewById(R.id.tv_capital);
                TextView tvSpend= dialog.findViewById(R.id.tv_spend);
                TextView tvRemaing= dialog.findViewById(R.id.tv_available);
                tvRemaing.setText(String.valueOf(capital));
                tvSpend.setText(String.valueOf(grandTotal));
                tvCapital.setText(String.valueOf(new DBHelper(getContext()).sum()));
                dialog.show();
            }
        });


        btnAddBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetImage.setImageResource(R.drawable.company);
                bottomSheetHeader.setText("Add Capital");
                bottomSheetDialog.show();
                buttonAtBottomSheetToAdMoney.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String myDate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                        String price=budget.getText().toString();
                        String discp=discription.getText().toString();
                        double prices=Double.parseDouble(price);

                        if (price.isEmpty() || prices<0 || discp.isEmpty()){
                            Toast.makeText(getContext(), "You are doing wrong Something", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            dbHelper.addDataInMainTable(new DBModelMain(prices, discp, myDate));
                            onResume();
                            budget.setText("");
                            discription.setText("");
                            bottomSheetDialog.dismiss();
                            Snackbar snackbar=Snackbar.make(getContext(),getView(),"Capital Added Successfully"+"\uD83D\uDE42",Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                });

                tvViewMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getContext(), DBRecyclerView.class);
                        intent.setData(Uri.parse("main"));
                        startActivity(intent);

                    }
                });
            }
        });


        MainAdapterClass adapterClass=new MainAdapterClass(new MainActivity().gatData());
        rvMain.setAdapter(adapterClass);
        rvMain.setHasFixedSize(true);
        rvMain.setLayoutManager(new LinearLayoutManager(getContext()));



        adapterClass.setItemClick(new MainAdapterClass.onItemClick() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View itemView, int position) {
               switch (position){
                   case 0:
                       bottomSheetImage.setImageResource(R.drawable.food_icon);
                       bottomSheetHeader.setText("Food Expanse");
                       bottomSheetDialog.show();

                       tvViewMore.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Intent intent=new Intent(getContext(),DBRecyclerView.class);
                               intent.setData(Uri.parse("one"));
                               startActivity(intent);
                           }
                       });

                       buttonAtBottomSheetToAdMoney.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               String price=budget.getText().toString();
                               double displayedMoney=Double.parseDouble(tvMain.getText().toString());
                               double  prices=0;
                               if (!price.isEmpty() && price.charAt(0)>=49 && price.charAt(0)<=57){
                                 prices=Double.parseDouble(price);
                               }
                               String discp=discription.getText().toString();
                               String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                               if (price.isEmpty()){
                                   Toast.makeText(getContext(), "Not possible", Toast.LENGTH_SHORT).show();
                               }
                               else {
                                   if (prices<0 || prices==0){
                                       Toast.makeText(getContext(), "Not possible", Toast.LENGTH_SHORT).show();
                                   }
                                   else {
                                       if (prices>displayedMoney){
                                           Toast.makeText(getContext(), "Expence must be less then available Balance", Toast.LENGTH_SHORT).show();
                                       }
                                       else {
                                           dbHelper.addDataInFoodTable(new DBModelMain(prices,discp,mydate));
                                           onResume();
                                           discription.setText("");
                                           budget.setText("");
                                           bottomSheetDialog.dismiss();

                                           Snackbar snackbar=Snackbar.make(getContext(),getView(),"Expence Added "+"\uD83D\uDE07",Snackbar.LENGTH_LONG);
                                           snackbar.show();
                                       }
                                   }
                               }

                           }
                       });

                       return;
                   case 1:
                       bottomSheetImage.setImageResource(R.drawable.hotel);
                       bottomSheetHeader.setText("Hostel Expense");
                       bottomSheetDialog.show();

                       tvViewMore.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Intent intent=new Intent(getContext(),DBRecyclerView.class);
                               intent.setData(Uri.parse("two"));
                               startActivity(intent);
                           }
                       });

                      buttonAtBottomSheetToAdMoney.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              double displayedMoney=Double.parseDouble(tvMain.getText().toString());

                              String price=budget.getText().toString();
                              double prices=0;
                              if (!price.isEmpty() && price.charAt(0)>=49 && price.charAt(0)<=57 ){
                                  prices=Double.parseDouble(price);
                              }

                              String discp=discription.getText().toString();
                              String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());


                              if (price.isEmpty() || prices<0 || prices==0 || discp.isEmpty() || prices>displayedMoney){
                                  Toast.makeText(getContext(), "Impossible to do", Toast.LENGTH_SHORT).show();
                              }
                              else {
                                  dbHelper.addDataInHostelTable(new DBModelMain(prices,discp,mydate));
                                  onResume();
                                  discription.setText("");
                                  budget.setText("");
                                  bottomSheetDialog.dismiss();
                                  Snackbar snackbar=Snackbar.make(getContext(),getView(),"Expence Added "+"\uD83D\uDE07",Snackbar.LENGTH_LONG);
                                  snackbar.show();
                              }

                          }
                      });
                       return;
                   case 2:
                       bottomSheetImage.setImageResource(R.drawable.student);
                       bottomSheetHeader.setText("Educational Expense");
                       bottomSheetDialog.show();

                       tvViewMore.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Intent intent=new Intent(getContext(),DBRecyclerView.class);
                               intent.setData(Uri.parse("three"));
                               startActivity(intent);
                           }
                       });
                       buttonAtBottomSheetToAdMoney.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               double displayedMoney=Double.parseDouble(tvMain.getText().toString());
                               String myDate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                               String description=discription.getText().toString();

                               String price=budget.getText().toString();
                               double prices=0;
                               if (!price.isEmpty() && price.charAt(0)>=49 && price.charAt(0)<=57){
                                   prices=Double.parseDouble(price);
                               }

                               if (price.isEmpty() || prices<0 || prices==0 || prices>displayedMoney || description.isEmpty()){
                                   Toast.makeText(getContext(), "It is not possible", Toast.LENGTH_SHORT).show();
                               }
                               else {
                                   dbHelper.addDataInEducationTable(new DBModelMain(prices,description,myDate));
                                   onResume();
                                   discription.setText("");
                                   budget.setText("");
                                   bottomSheetDialog.dismiss();
                                   Snackbar snackbar=Snackbar.make(getContext(),getView(),"Expence Added "+"\uD83D\uDE07",Snackbar.LENGTH_LONG);
                                   snackbar.show();
                               }

                           }
                       });
                       return;
                   case 3:
                       bottomSheetImage.setImageResource(R.drawable.invoice);
                       bottomSheetHeader.setText("Medical Expense");
                       bottomSheetDialog.show();

                       tvViewMore.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Intent intent=new Intent(getContext(),DBRecyclerView.class);
                               intent.setData(Uri.parse("four"));
                               startActivity(intent);
                           }
                       });

                       buttonAtBottomSheetToAdMoney.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               String discp=discription.getText().toString();
                               String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                               String price=budget.getText().toString();
                               double prices=0;
                               if (!price.isEmpty() && price.charAt(0)>=49 && price.charAt(0)<=57){
                               prices=Double.parseDouble(price);
                               }
                               double displayedMoney=Double.parseDouble(tvMain.getText().toString());

                               if (prices<0 || prices>displayedMoney || prices==0 || price.isEmpty() || discp.isEmpty()){
                                   Toast.makeText(getContext(), "It is not possible", Toast.LENGTH_SHORT).show();
                               }
                               else {
                                   dbHelper.addDataInMedicalHealthTable(new DBModelMain(prices, discp, mydate));
                                   onResume();
                                   discription.setText("");
                                   budget.setText("");
                                   bottomSheetDialog.dismiss();
                                   Snackbar snackbar=Snackbar.make(getContext(),getView(),"Expence Added "+"\uD83D\uDE07",Snackbar.LENGTH_LONG);
                                   snackbar.show();
                               }

                           }
                       });

                       return;
                   case 4:
                       bottomSheetImage.setImageResource(R.drawable.businessman);
                       bottomSheetHeader.setText("Personal Expense");
                       bottomSheetDialog.show();
                       tvViewMore.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Intent intent=new Intent(getContext(),DBRecyclerView.class);
                               intent.setData(Uri.parse("five"));
                               startActivity(intent);
                           }
                       });
                       buttonAtBottomSheetToAdMoney.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               String discp=discription.getText().toString();
                               double displayedMoney=Double.parseDouble(tvMain.getText().toString());
                               String price=budget.getText().toString();
                               double prices=0;
                               if (!price.isEmpty() && price.charAt(0)>=47 && price.charAt(0)<=57){
                                   prices=Double.parseDouble(price);
                               }

                               String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());



                               if (prices <0 || prices>displayedMoney || prices==0 || price.isEmpty() || discp.isEmpty()){
                                   Toast.makeText(getContext(), "It is not possible", Toast.LENGTH_SHORT).show();
                               }
                               else {
                                   dbHelper.addDataInPersonalTable(new DBModelMain(prices, discp, mydate));
                                   onResume();
                                   discription.setText("");
                                   budget.setText("");
                                   bottomSheetDialog.dismiss();
                                   Snackbar snackbar=Snackbar.make(getContext(),getView(),"Expence Added "+"\uD83D\uDE07",Snackbar.LENGTH_LONG);
                                   snackbar.show();
                               }

                           }
                       });
                       return;
                   case 5:
                       bottomSheetImage.setImageResource(R.drawable.contract);
                       bottomSheetHeader.setText("Bills Expense");
                       bottomSheetDialog.show();

                       tvViewMore.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Intent intent=new Intent(getContext(),DBRecyclerView.class);
                               intent.setData(Uri.parse("six"));
                               startActivity(intent);
                           }
                       });
                       buttonAtBottomSheetToAdMoney.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               assert budget != null;
                               String discp=discription.getText().toString();
                               String myDate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                               double displayedMoney=Double.parseDouble(tvMain.getText().toString());
                               String price=budget.getText().toString();
                               double prices=0;
                               if (!price.isEmpty() && price.charAt(0) >= 49 && price.charAt(0) <=57){
                                   prices=Double.parseDouble(price);
                               }

                               if (prices<0 || prices>displayedMoney  || discp.isEmpty() || prices==0){
                                   Toast.makeText(getContext(), "It is not possible", Toast.LENGTH_SHORT).show();
                               }
                               else {
                                   dbHelper.addDataInBillsTable(new DBModelMain(prices, discp, myDate));
                                   onResume();
                                   discription.setText("");
                                   budget.setText("");
                                   bottomSheetDialog.dismiss();
                                   Snackbar snackbar=Snackbar.make(getContext(),getView(),"Expence Added "+"\uD83D\uDE07",Snackbar.LENGTH_LONG);
                                   snackbar.show();
                               }

                           }
                       });
                       return;

                   case 6:
                       bottomSheetImage.setImageResource(R.drawable.calendar);
                       bottomSheetHeader.setText("Events Expense");
                       bottomSheetDialog.show();
                       tvViewMore.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Intent intent=new Intent(getContext(),DBRecyclerView.class);
                               intent.setData(Uri.parse("seven"));
                               startActivity(intent);
                           }
                       });
                       buttonAtBottomSheetToAdMoney.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               String discp=discription.getText().toString();
                               String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                               double displayedMoney=Double.parseDouble(tvMain.getText().toString());
                               String price=budget.getText().toString();
                               double prices=0;

                               if (!price.isEmpty() && price.charAt(0) >= 49 && price.charAt(0) <=57){
                                   prices=Double.parseDouble(price);
                               }

                               if (prices<0 || prices==0 || prices>displayedMoney  || discp.isEmpty()){
                                   Toast.makeText(getContext(), "It is not possible", Toast.LENGTH_SHORT).show();
                               }
                               else {
                                   dbHelper.addDataInEventTable(new DBModelMain(prices, discp, mydate));
                                   onResume();
                                   discription.setText("");
                                   budget.setText("");
                                   bottomSheetDialog.dismiss();
                                   Snackbar snackbar=Snackbar.make(getContext(),getView(),"Expence Added "+"\uD83D\uDE07",Snackbar.LENGTH_LONG);
                                   snackbar.show();
                               }

                           }
                       });
                       return;

                   case 7:
                       bottomSheetImage.setImageResource(R.drawable.expensive);
                       bottomSheetHeader.setText("Others Expense");
                       bottomSheetDialog.show();

                       tvViewMore.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Intent intent=new Intent(getContext(),DBRecyclerView.class);
                               intent.setData(Uri.parse("eight"));
                               startActivity(intent);
                           }
                       });

                       buttonAtBottomSheetToAdMoney.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               String discp=discription.getText().toString();
                               String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                               double displayedMoney=Double.parseDouble(tvMain.getText().toString());
                               String price=budget.getText().toString();
                               double prices=0;
                               if (!price.isEmpty() && price.charAt(0) >= 49 && price.charAt(0) <=57){
                                   prices=Double.parseDouble(price);
                               }

                               if (prices<0  || prices==0|| prices>displayedMoney || discp.isEmpty()){
                                   Toast.makeText(getContext(), "It is not possible", Toast.LENGTH_SHORT).show();
                               }
                               else {
                                   dbHelper.addDataInOtherTable(new DBModelMain(prices, discp, mydate));
                                   onResume();
                                   discription.setText("");
                                   budget.setText("");
                                   bottomSheetDialog.dismiss();
                                   Snackbar snackbar=Snackbar.make(getContext(),getView(),"Expence Added "+"\uD83D\uDE07",Snackbar.LENGTH_LONG);
                                   snackbar.show();
                               }
                           }
                       });
                       return;
               }
            }
        });
        return view;

    }
    @Override
    public void onResume() {
        //Taking all the expances from DB.
        double food=(new DBHelper(getContext()).sumOfFoodTable());
        double hostel=new DBHelper(getContext()).sumOfHostelTable();
        double educational=new DBHelper(getContext()).sumOfEducationalTable();
        double medicalHealth=new DBHelper(getContext()).sumOfMedicalHealthTable();
        double personal=new DBHelper(getContext()).sumOfPersonalTable();
        double bills=new DBHelper(getContext()).sumOfBillsTable();
        double events=new DBHelper(getContext()).sumOfEventTable();
        double others=new DBHelper(getContext()).sumOfOthersTable();

        grandTotal=food+hostel+educational+medicalHealth+personal+bills+events+others;
        capital=new DBHelper(getContext()).sum();
        capital=capital-grandTotal;
        String total=String.valueOf(capital);
        tvMain.setText(total);
             super.onResume();
    }

    public boolean isDigit(String num){
        for (int i=0; i<num.length(); i++){
            if (num.charAt(i) >=49 && num.charAt(i)<=57){
                return true;
            }

        }
        return false;
    }
}
