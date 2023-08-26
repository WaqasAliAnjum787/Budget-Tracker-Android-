package com.example.budgettracker.db;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class DBModelMain {
    private double enteredMoney;
    private String description;
    private String name;
    private String dateAndTime;

    private byte[] profileArray;

    public DBModelMain() {
    }

    public DBModelMain(String description, String name,  byte[]  profileArray) {
        this.description = description;
        this.name = name;
        this.profileArray = profileArray;
    }

    public DBModelMain(double enteredMoney, String description, String dateAndTime) {
        this.enteredMoney = enteredMoney;
        this.description = description;
        this.dateAndTime = dateAndTime;
    }

//Here

    public static final String TABLE_NAME="TotalAmount";
    public static final String TABLE_NAME_FOR_FOOD="Food";
    public static final String TABLE_NAME_HOSTEL="Hostel";
    public static final String TABLE_NAME_EDUCATION="Educational";
    public static final String TABLE_NAME_MEDICAL_HEALTH="MedicalHealth";
    public static final String TABLE_NAME_PERSONAL="Personal";
    public static final String TABLE_NAME_BILLS="Bills";
    public static final String TABLE_NAME_EVENTS="Events";
    public static final String TABLE_NAME_OTHERS="Other";
    public  static final String TABLE_NAME_PROFILE="Profile";


    //Coloums of our Table
    public static final String COL_ENTERED_MONEY="EnteredMoney";
    public static final String COL_DATE_AND_TIME="DateAndTime";

    public static final String COL_PROFILE_IMAGE="DateAndTime";
    public static final String COL_DESCRIPTION="Description";
    public static final String COL_NAME="Name";

    //Create Table
    public static final String CREATE_TABLE=String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT,%s TEXT,%s TEXT)",TABLE_NAME,COL_ENTERED_MONEY,COL_DESCRIPTION,COL_DATE_AND_TIME);
    public static final String CREATE_TABLE_FOR_FOOD=String.format("CREATE TABLE IF NOT EXISTS %s " +
                    "(%s TEXT,%s TEXT,%s TEXT)",
            TABLE_NAME_FOR_FOOD,COL_ENTERED_MONEY,COL_DESCRIPTION,COL_DATE_AND_TIME);
    public static final String CREATE_TABLE_FOR_HOSTEL=String.format("CREATE TABLE IF NOT EXISTS %s " +
                    "(%s TEXT,%s TEXT,%s TEXT)",
            TABLE_NAME_HOSTEL,COL_ENTERED_MONEY,COL_DESCRIPTION,COL_DATE_AND_TIME);

    public static final String CREATE_TABLE_FOR_EDUCATION=String.format("CREATE TABLE IF NOT EXISTS %s " +
                    "(%s TEXT,%s TEXT,%s TEXT)",
            TABLE_NAME_EDUCATION,COL_ENTERED_MONEY,COL_DESCRIPTION,COL_DATE_AND_TIME);

    public static final String CREATE_TABLE_FOR_MEDICAL_HEALTH=String.format("CREATE TABLE IF NOT EXISTS %s " +
                    "(%s TEXT,%s TEXT,%s TEXT)",
            TABLE_NAME_MEDICAL_HEALTH,COL_ENTERED_MONEY,COL_DESCRIPTION,COL_DATE_AND_TIME);

    public static final String CREATE_TABLE_FOR_PERSONAL=String.format("CREATE TABLE IF NOT EXISTS %s " +
                    "(%s TEXT,%s TEXT,%s TEXT)",
            TABLE_NAME_PERSONAL,COL_ENTERED_MONEY,COL_DESCRIPTION,COL_DATE_AND_TIME);

    public static final String CREATE_TABLE_FOR_BILLS=String.format("CREATE TABLE IF NOT EXISTS %s " +
                    "(%s TEXT,%s TEXT,%s TEXT)",
            TABLE_NAME_BILLS,COL_ENTERED_MONEY,COL_DESCRIPTION,COL_DATE_AND_TIME);

    public static final String CREATE_TABLE_FOR_EVENTS=String.format("CREATE TABLE IF NOT EXISTS %s " +
                    "(%s TEXT,%s TEXT,%s TEXT)",
            TABLE_NAME_EVENTS,COL_ENTERED_MONEY,COL_DESCRIPTION,COL_DATE_AND_TIME);

    public static final String CREATE_TABLE_FOR_OTHERS=String.format("CREATE TABLE IF NOT EXISTS %s " +
                    "(%s TEXT,%s TEXT,%s TEXT)",
            TABLE_NAME_OTHERS,COL_ENTERED_MONEY,COL_DESCRIPTION,COL_DATE_AND_TIME);

    public static final String CREATE_TABLE_FOR_PROFILE=String.format("CREATE TABLE IF NOT EXISTS %s (%s TEXT, %s TEST, %s BLOB)",
            TABLE_NAME_PROFILE,COL_NAME,COL_DESCRIPTION,COL_PROFILE_IMAGE);

    //Select Data from Tables
    public static final String SELECT=String.format("SELECT * FROM %S ",TABLE_NAME);
    public static final String SELECT_FORM_FOOD=String.format("SELECT * FROM %s ",TABLE_NAME_FOR_FOOD);
    public static final String SELECT_FROM_HOSTEL=String.format("SELECT * FROM %s ",TABLE_NAME_HOSTEL);
    public static final String SELECT_FROM_EDUCATION=String.format("SELECT * FROM %s ",TABLE_NAME_EDUCATION);
    public static final String SELECT_FORM_PERSONAL=String.format("SELECT * FROM %s ",TABLE_NAME_PERSONAL);
    public static final String SELECT_FORM_MEDICAL_HEALTH=String.format("SELECT * FROM %s ",TABLE_NAME_MEDICAL_HEALTH);
    public static final String SELECT_FROM_BILLS=String.format("SELECT * FROM %s ",TABLE_NAME_BILLS);
    public static final String SELECT_FROM_OTHERS=String.format("SELECT * FROM %s ",TABLE_NAME_OTHERS);
    public static final String SELECT_FROM_EVENTS=String.format("SELECT * FROM %s ",TABLE_NAME_EVENTS);
    public static final String SELECT_FROM_PROFILE=String.format("SELECT * FROM %s ",TABLE_NAME_PROFILE);

    //Drop Table
    public static final String DROP_TABLE=String.format("DROP TABLE IF EXISTS %s ",TABLE_NAME);
    public static final String DROP_TABLE_OF_FOOD=String.format("DROP TABLE IF EXISTS %s ",TABLE_NAME_FOR_FOOD);
    public static final String DROP_TABLE_OF_HOSTEL=String.format("DROP TABLE IF EXISTS %s ",TABLE_NAME_HOSTEL);
    public static final String DROP_TABLE_OF_EDUCATION=String.format("DROP TABLE IF EXISTS %s ",TABLE_NAME_EDUCATION);
    public static final String DROP_TABLE_OF_MEDICAL_HEALTH=String.format("DROP TABLE IF EXISTS %s ",TABLE_NAME_MEDICAL_HEALTH);
    public static final String DROP_TABLE_OF_PERSONAL=String.format("DROP TABLE IF EXISTS %s ",TABLE_NAME_PERSONAL);
    public static final String DROP_TABLE_OF_BILLS=String.format("DROP TABLE IF EXISTS %s ",TABLE_NAME_BILLS);
    public static final String DROP_TABLE_OF_EVENTS=String.format("DROP TABLE IF EXISTS %s ",TABLE_NAME_EVENTS);
    public static final String DROP_TABLE_OTHERS=String.format("DROP TABLE IF EXISTS %s ",TABLE_NAME_OTHERS);
    public static final String DROP_TABLE_PROFILE=String.format("DROPE TABLE IF EXISTS %s ",TABLE_NAME_PROFILE);


    public double getEnteredMoney() {
        return enteredMoney;
    }

    public void setEnteredMoney(double enteredMoney) {
        this.enteredMoney = enteredMoney;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getProfileArray() {
        return profileArray;
    }

    public void setProfileArray(byte[] profileArray) {
        this.profileArray = profileArray;
    }

    @Override
    public String toString() {
        return "DBModelMain{" +
                "enteredMoney=" + enteredMoney +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", profileArray=" + Arrays.toString(profileArray) +
                '}';
    }
}
