package com.example.budgettracker.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="Expensify";
    public static final int VERSION=3;

    private static DBHelper dbHelper;

    public static DBHelper getDbHelper(Context context) {
        if (dbHelper==null){
            dbHelper=new DBHelper(context);
        }
        return dbHelper;
    }
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null,  VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBModelMain.CREATE_TABLE);
        sqLiteDatabase.execSQL(DBModelMain.CREATE_TABLE_FOR_FOOD);
        sqLiteDatabase.execSQL(DBModelMain.CREATE_TABLE_FOR_EDUCATION);
        sqLiteDatabase.execSQL(DBModelMain.CREATE_TABLE_FOR_BILLS);
        sqLiteDatabase.execSQL(DBModelMain.CREATE_TABLE_FOR_HOSTEL);
        sqLiteDatabase.execSQL(DBModelMain.CREATE_TABLE_FOR_PERSONAL);
        sqLiteDatabase.execSQL(DBModelMain.CREATE_TABLE_FOR_MEDICAL_HEALTH);
        sqLiteDatabase.execSQL(DBModelMain.CREATE_TABLE_FOR_EVENTS);
        sqLiteDatabase.execSQL(DBModelMain.CREATE_TABLE_FOR_OTHERS);
        sqLiteDatabase.execSQL(DBModelMain.CREATE_TABLE_FOR_PROFILE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i!=i1){

            sqLiteDatabase.execSQL(DBModelMain.DROP_TABLE);
            sqLiteDatabase.execSQL(DBModelMain.DROP_TABLE_OF_BILLS);
            sqLiteDatabase.execSQL(DBModelMain.DROP_TABLE_OF_EDUCATION);
            sqLiteDatabase.execSQL(DBModelMain.DROP_TABLE_OF_EVENTS);
            sqLiteDatabase.execSQL(DBModelMain.DROP_TABLE_OF_FOOD);
            sqLiteDatabase.execSQL(DBModelMain.DROP_TABLE_OF_HOSTEL);
            sqLiteDatabase.execSQL(DBModelMain.DROP_TABLE_OF_MEDICAL_HEALTH);
            sqLiteDatabase.execSQL(DBModelMain.DROP_TABLE_OTHERS);
            sqLiteDatabase.execSQL(DBModelMain.DROP_TABLE_OF_PERSONAL);
            sqLiteDatabase.execSQL(DBModelMain.DROP_TABLE_PROFILE);
        }
    }

    public boolean addDataInProfileTable(DBModelMain dbModelMain){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBModelMain.COL_NAME, dbModelMain.getName());
        contentValues.put(DBModelMain.COL_DESCRIPTION,dbModelMain.getDescription());
        contentValues.put(DBModelMain.COL_PROFILE_IMAGE,dbModelMain.getProfileArray());

        long affected;
        try {
            affected=sqLiteDatabase.insert(DBModelMain.TABLE_NAME_PROFILE,null,contentValues);
        }
        catch (Exception exception){
            return false;
        }
        return affected>=0;
    }

    @SuppressLint("Range")
    public List<DBModelMain> getDataFromProfileTable(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(DBModelMain.SELECT_FROM_PROFILE,null);
        List<DBModelMain> dbModelMains=new ArrayList<>(cursor.getCount());

        long affected;

            if (cursor.moveToFirst()){
                do {
                    DBModelMain dbModelMain=new DBModelMain();
                    dbModelMain.setName(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_NAME)));
                    dbModelMain.setProfileArray(cursor.getBlob(cursor.getColumnIndex(DBModelMain.COL_PROFILE_IMAGE)));
                    dbModelMain.setDescription(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DESCRIPTION)));
                    dbModelMains.add(dbModelMain);
                }while (cursor.moveToNext());
            }
        return dbModelMains;
    }
    public boolean addDataInMainTable(DBModelMain dbModelMain){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(DBModelMain.COL_ENTERED_MONEY, dbModelMain.getEnteredMoney());
        contentValues.put(DBModelMain.COL_DATE_AND_TIME,dbModelMain.getDateAndTime());
        contentValues.put(DBModelMain.COL_DESCRIPTION,dbModelMain.getDescription());


        long affected;
        try {
            affected=sqLiteDatabase.insert(DBModelMain.TABLE_NAME,null,contentValues);
        }
        catch (Exception exception){
            return false;
        }
        return affected >= 0;
    }

    //Taking Sum of All the money which is available

    @SuppressLint("Range")
    public double sum()
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(" +DBModelMain.COL_ENTERED_MONEY + ") as Total FROM "+DBModelMain.TABLE_NAME,null);
        double total=0;
        if (cursor.moveToFirst()){
            total =cursor.getDouble(cursor.getColumnIndex("Total"));
        }
        return total;
    }

    //Taking All the records that are available

    @SuppressLint("Range")
    public List<DBModelMain> getAllData(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(DBModelMain.SELECT,null);

        List<DBModelMain> dbModelMains=new ArrayList<>(cursor.getCount());

       if (cursor.moveToFirst()){
           do  {
               DBModelMain dbModelMain=new DBModelMain();
               dbModelMain.setEnteredMoney(Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_ENTERED_MONEY))));
               dbModelMain.setDateAndTime(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DATE_AND_TIME)));
               dbModelMain.setDescription(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DESCRIPTION)));
               dbModelMains.add(dbModelMain);

           } while (cursor.moveToNext());
       }
       return dbModelMains;
    }

    //For Food Table

    public boolean addDataInFoodTable(DBModelMain dbModelMain){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(DBModelMain.COL_ENTERED_MONEY, dbModelMain.getEnteredMoney());
        contentValues.put(DBModelMain.COL_DATE_AND_TIME,dbModelMain.getDateAndTime());
        contentValues.put(DBModelMain.COL_DESCRIPTION,dbModelMain.getDescription());


        long affected;
        try {
            affected=sqLiteDatabase.insert(DBModelMain.TABLE_NAME_FOR_FOOD,null,contentValues);
        }
        catch (Exception exception){
            return false;
        }
        return affected >= 0;
    }

    //Fetch Data from Food Table

    @SuppressLint("Range")
    public List<DBModelMain> getAllDataFromFoodTable(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(DBModelMain.SELECT_FORM_FOOD,null);

        List<DBModelMain> dbModelMains=new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()){
            do  {
                DBModelMain dbModelMain=new DBModelMain();
                dbModelMain.setEnteredMoney(Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_ENTERED_MONEY))));
                dbModelMain.setDateAndTime(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DATE_AND_TIME)));
                dbModelMain.setDescription(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DESCRIPTION)));
                dbModelMains.add(dbModelMain);

            } while (cursor.moveToNext());
        }
        return dbModelMains;
    }

    @SuppressLint("Range")
    public double sumOfFoodTable()
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(" +DBModelMain.COL_ENTERED_MONEY + ") as Total FROM "+DBModelMain.TABLE_NAME_FOR_FOOD,null);
        double total=0;
        if (cursor.moveToFirst()){
            total =cursor.getDouble(cursor.getColumnIndex("Total"));
        }
        return total;
    }


    //For Hostel Table

    public boolean addDataInHostelTable(DBModelMain dbModelMain){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(DBModelMain.COL_ENTERED_MONEY, dbModelMain.getEnteredMoney());
        contentValues.put(DBModelMain.COL_DATE_AND_TIME,dbModelMain.getDateAndTime());
        contentValues.put(DBModelMain.COL_DESCRIPTION,dbModelMain.getDescription());


        long affected;
        try {
            affected=sqLiteDatabase.insert(DBModelMain.TABLE_NAME_HOSTEL,null,contentValues);
        }
        catch (Exception exception){
            return false;
        }
        return affected >= 0;
    }

    @SuppressLint("Range")
    public List<DBModelMain> getAllDataFromHostelTable(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(DBModelMain.SELECT_FROM_HOSTEL,null);

        List<DBModelMain> dbModelMains=new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()){
            do  {
                DBModelMain dbModelMain=new DBModelMain();
                dbModelMain.setEnteredMoney(Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_ENTERED_MONEY))));
                dbModelMain.setDateAndTime(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DATE_AND_TIME)));
                dbModelMain.setDescription(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DESCRIPTION)));
                dbModelMains.add(dbModelMain);

            } while (cursor.moveToNext());
        }
        return dbModelMains;
    }
    @SuppressLint("Range")
    public double sumOfHostelTable()
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(" +DBModelMain.COL_ENTERED_MONEY + ") as Total FROM "+DBModelMain.TABLE_NAME_HOSTEL,null);
        double total=0;
        if (cursor.moveToFirst()){
            total =cursor.getDouble(cursor.getColumnIndex("Total"));
        }
        return total;
    }

    //Now in Educational Table

    public boolean addDataInEducationTable(DBModelMain dbModelMain){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(DBModelMain.COL_ENTERED_MONEY, dbModelMain.getEnteredMoney());
        contentValues.put(DBModelMain.COL_DATE_AND_TIME,dbModelMain.getDateAndTime());
        contentValues.put(DBModelMain.COL_DESCRIPTION,dbModelMain.getDescription());


        long affected;
        try {
            affected=sqLiteDatabase.insert(DBModelMain.TABLE_NAME_EDUCATION,null,contentValues);
        }
        catch (Exception exception){
            return false;
        }
        return affected >= 0;
    }

    @SuppressLint("Range")
    public List<DBModelMain> getAllDataFromEducationTable(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(DBModelMain.SELECT_FROM_EDUCATION,null);

        List<DBModelMain> dbModelMains=new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()){
            do  {
                DBModelMain dbModelMain=new DBModelMain();
                dbModelMain.setEnteredMoney(Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_ENTERED_MONEY))));
                dbModelMain.setDateAndTime(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DATE_AND_TIME)));
                dbModelMain.setDescription(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DESCRIPTION)));
                dbModelMains.add(dbModelMain);

            } while (cursor.moveToNext());
        }
        return dbModelMains;
    }

    @SuppressLint("Range")
    public double sumOfEducationalTable()
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(" +DBModelMain.COL_ENTERED_MONEY + ") as Total FROM "+DBModelMain.TABLE_NAME_EDUCATION,null);
        double total=0;
        if (cursor.moveToFirst()){
            total =cursor.getDouble(cursor.getColumnIndex("Total"));
        }
        return total;
    }


    //Now for Personal

    public boolean addDataInPersonalTable(DBModelMain dbModelMain){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(DBModelMain.COL_ENTERED_MONEY, dbModelMain.getEnteredMoney());
        contentValues.put(DBModelMain.COL_DATE_AND_TIME,dbModelMain.getDateAndTime());
        contentValues.put(DBModelMain.COL_DESCRIPTION,dbModelMain.getDescription());


        long affected;
        try {
            affected=sqLiteDatabase.insert(DBModelMain.TABLE_NAME_PERSONAL,null,contentValues);
        }
        catch (Exception exception){
            return false;
        }
        return affected >= 0;
    }

    @SuppressLint("Range")
    public List<DBModelMain> getAllDataFromPersonalTable(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(DBModelMain.SELECT_FORM_PERSONAL,null);

        List<DBModelMain> dbModelMains=new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()){
            do  {
                DBModelMain dbModelMain=new DBModelMain();
                dbModelMain.setEnteredMoney(Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_ENTERED_MONEY))));
                dbModelMain.setDateAndTime(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DATE_AND_TIME)));
                dbModelMain.setDescription(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DESCRIPTION)));
                dbModelMains.add(dbModelMain);

            } while (cursor.moveToNext());
        }
        return dbModelMains;
    }

    @SuppressLint("Range")
    public double sumOfPersonalTable()
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(" +DBModelMain.COL_ENTERED_MONEY + ") as Total FROM "+DBModelMain.TABLE_NAME_PERSONAL,null);
        double total=0;
        if (cursor.moveToFirst()){
            total =cursor.getDouble(cursor.getColumnIndex("Total"));
        }
        return total;
    }

    //Now for Medical Health

    public boolean addDataInMedicalHealthTable(DBModelMain dbModelMain){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(DBModelMain.COL_ENTERED_MONEY, dbModelMain.getEnteredMoney());
        contentValues.put(DBModelMain.COL_DATE_AND_TIME,dbModelMain.getDateAndTime());
        contentValues.put(DBModelMain.COL_DESCRIPTION,dbModelMain.getDescription());


        long affected;
        try {
            affected=sqLiteDatabase.insert(DBModelMain.TABLE_NAME_MEDICAL_HEALTH,null,contentValues);
        }
        catch (Exception exception){
            return false;
        }
        return affected >= 0;
    }

    @SuppressLint("Range")
    public List<DBModelMain> getAllDataFromMedicalHealthTable(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(DBModelMain.SELECT_FORM_MEDICAL_HEALTH,null);

        List<DBModelMain> dbModelMains=new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()){
            do  {
                DBModelMain dbModelMain=new DBModelMain();
                dbModelMain.setEnteredMoney(Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_ENTERED_MONEY))));
                dbModelMain.setDateAndTime(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DATE_AND_TIME)));
                dbModelMain.setDescription(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DESCRIPTION)));
                dbModelMains.add(dbModelMain);

            } while (cursor.moveToNext());
        }
        return dbModelMains;
    }

    @SuppressLint("Range")
    public double sumOfMedicalHealthTable()
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(" +DBModelMain.COL_ENTERED_MONEY + ") as Total FROM "+DBModelMain.TABLE_NAME_MEDICAL_HEALTH,null);
        double total=0;
        if (cursor.moveToFirst()){
            total =cursor.getDouble(cursor.getColumnIndex("Total"));
        }
        return total;
    }

    //Now for Bills

    public boolean addDataInBillsTable(DBModelMain dbModelMain){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(DBModelMain.COL_ENTERED_MONEY, dbModelMain.getEnteredMoney());
        contentValues.put(DBModelMain.COL_DATE_AND_TIME,dbModelMain.getDateAndTime());
        contentValues.put(DBModelMain.COL_DESCRIPTION,dbModelMain.getDescription());


        long affected;
        try {
            affected=sqLiteDatabase.insert(DBModelMain.TABLE_NAME_BILLS,null,contentValues);
        }
        catch (Exception exception){
            return false;
        }
        return affected >= 0;
    }

    @SuppressLint("Range")
    public List<DBModelMain> getAllDataFromBillsTable(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(DBModelMain.SELECT_FROM_BILLS,null);

        List<DBModelMain> dbModelMains=new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()){
            do  {
                DBModelMain dbModelMain=new DBModelMain();
                dbModelMain.setEnteredMoney(Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_ENTERED_MONEY))));
                dbModelMain.setDateAndTime(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DATE_AND_TIME)));
                dbModelMain.setDescription(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DESCRIPTION)));
                dbModelMains.add(dbModelMain);

            } while (cursor.moveToNext());
        }
        return dbModelMains;
    }

    @SuppressLint("Range")
    public double sumOfBillsTable()
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(" +DBModelMain.COL_ENTERED_MONEY + ") as Total FROM "+DBModelMain.TABLE_NAME_BILLS,null);
        double total=0;
        if (cursor.moveToFirst()){
            total =cursor.getDouble(cursor.getColumnIndex("Total"));
        }
        return total;
    }

    //Now for Others

    public boolean addDataInOtherTable(DBModelMain dbModelMain){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(DBModelMain.COL_ENTERED_MONEY, dbModelMain.getEnteredMoney());
        contentValues.put(DBModelMain.COL_DATE_AND_TIME,dbModelMain.getDateAndTime());
        contentValues.put(DBModelMain.COL_DESCRIPTION,dbModelMain.getDescription());


        long affected;
        try {
            affected=sqLiteDatabase.insert(DBModelMain.TABLE_NAME_OTHERS,null,contentValues);
        }
        catch (Exception exception){
            return false;
        }
        return affected >= 0;
    }


    @SuppressLint("Range")
    public List<DBModelMain> getAllDataFromOtherTable(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(DBModelMain.SELECT_FROM_OTHERS,null);

        List<DBModelMain> dbModelMains=new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()){
            do  {
                DBModelMain dbModelMain=new DBModelMain();
                dbModelMain.setEnteredMoney(Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_ENTERED_MONEY))));
                dbModelMain.setDateAndTime(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DATE_AND_TIME)));
                dbModelMain.setDescription(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DESCRIPTION)));
                dbModelMains.add(dbModelMain);

            } while (cursor.moveToNext());
        }
        return dbModelMains;
    }


    @SuppressLint("Range")
    public double sumOfOthersTable()
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(" +DBModelMain.COL_ENTERED_MONEY + ") as Total FROM "+DBModelMain.TABLE_NAME_OTHERS,null);
        double total=0;
        if (cursor.moveToFirst()){
            total =cursor.getDouble(cursor.getColumnIndex("Total"));
        }
        return total;
    }

    //Event

    public boolean addDataInEventTable(DBModelMain dbModelMain){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(DBModelMain.COL_ENTERED_MONEY, dbModelMain.getEnteredMoney());
        contentValues.put(DBModelMain.COL_DATE_AND_TIME,dbModelMain.getDateAndTime());
        contentValues.put(DBModelMain.COL_DESCRIPTION,dbModelMain.getDescription());


        long affected;
        try {
            affected=sqLiteDatabase.insert(DBModelMain.TABLE_NAME_EVENTS,null,contentValues);
        }
        catch (Exception exception){
            return false;
        }
        return affected >= 0;
    }

    @SuppressLint("Range")
    public List<DBModelMain> getAllDataFromEventTable(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(DBModelMain.SELECT_FROM_EVENTS,null);

        List<DBModelMain> dbModelMains=new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()){
            do  {
                DBModelMain dbModelMain=new DBModelMain();
                dbModelMain.setEnteredMoney(Double.parseDouble(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_ENTERED_MONEY))));
                dbModelMain.setDateAndTime(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DATE_AND_TIME)));
                dbModelMain.setDescription(cursor.getString(cursor.getColumnIndex(DBModelMain.COL_DESCRIPTION)));
                dbModelMains.add(dbModelMain);

            } while (cursor.moveToNext());
        }
        return dbModelMains;
    }

    @SuppressLint("Range")
    public double sumOfEventTable()
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(" +DBModelMain.COL_ENTERED_MONEY + ") as Total FROM "+DBModelMain.TABLE_NAME_EVENTS,null);
        double total=0;
        if (cursor.moveToFirst()){
            total =cursor.getDouble(cursor.getColumnIndex("Total"));
        }
        return total;
    }


}
