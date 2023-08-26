package com.example.budgettracker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.budgettracker.db.DBHelper;
import com.example.budgettracker.db.DBModelMain;
import com.google.android.material.snackbar.Snackbar;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.xml.transform.Result;

public class ThirdFragment extends Fragment {


    EditText etName;
    EditText etOtherDetails;
    AppCompatButton btnCreateProfile;
    CircularImageView circularImageView;
    Bitmap bitmap;
    String names;
    String others;
    byte[] myProfileImage;
    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_third,null);
        etName=view.findViewById(R.id.tv_user_name);
        etOtherDetails=view.findViewById(R.id.tv_other_details);
        btnCreateProfile=view.findViewById(R.id.btn_create_a_profile);
        circularImageView=view.findViewById(R.id.avatarView);

        ActivityResultLauncher<Intent> intentActivityResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
               try {
                   if (result.getData()!=null && result.getResultCode()== Activity.RESULT_OK){
                       bitmap=result.getData().getParcelableExtra("data");
                       circularImageView.setImageBitmap(bitmap);
                   }
               }
               catch (Exception exception){
                   Toast.makeText(getContext(), ""+exception, Toast.LENGTH_SHORT).show();
               }
            }
        });


        ActivityResultLauncher<String> permisionOfCamera=registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                intentActivityResultLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            }
        });



        circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (ContextCompat.checkSelfPermission(getContext(), MediaStore.ACTION_IMAGE_CAPTURE) == PackageManager.PERMISSION_GRANTED){
            intentActivityResultLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        } else {
                  permisionOfCamera.launch(Manifest.permission.CAMERA);
               }
            }
        });


        List<DBModelMain> dbModelMainList=new DBHelper(getContext()).getDataFromProfileTable();
        try {
            int size= dbModelMainList.size();
             names=dbModelMainList.get(size-1).getName();
             others=dbModelMainList.get(size-1).getDescription();
            etName.setText(names);
            etOtherDetails.setText(others);
          myProfileImage = dbModelMainList.get(size-1).getProfileArray();
          Bitmap  myBitmapImage = BitmapFactory.decodeStream(new ByteArrayInputStream(myProfileImage));
          circularImageView.setImageBitmap(myBitmapImage);
        }
        catch (Exception ignored){

        }


        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String name=etName.getText().toString();
               String other=etOtherDetails.getText().toString();
                byte[] myByteArray=myProfileImage;

               try {
                   myByteArray= convertBitmapToByteArray(bitmap);
               }
               catch (Exception ignored){

               }

               if (name.isEmpty() || other.isEmpty()){
                   Toast.makeText(getContext(), "Please fill all the fields  \uD83D\uDE0C ", Toast.LENGTH_SHORT).show();
               }

               else {
                   if (!name.equals(names) || !other.equals(others) || !myByteArray.equals(myProfileImage) ){
                           DBHelper dbHelper=DBHelper.getDbHelper(getContext());
                           dbHelper.addDataInProfileTable(new DBModelMain(other,name,myByteArray));
                       Snackbar.make(getView(),"Profile Updated ☺️",Snackbar.LENGTH_LONG).show();
                   }
               }

            }
        });

        return view;
    }

    public byte[] convertBitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }
}