package com.example.remindme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;


public class DBHelper extends SQLiteOpenHelper {

    //Declared and defined some constant to be used in our project
    public static final int DB_VERSION = 2;
    public static final String DB_NAME = "alarmDB.db";

    private static final String CREATE_TABLE = "CREATE TABLE " + AlarmSchema.Alarm_Colns.TABLE_NAME + " (" + AlarmSchema.Alarm_Colns.COL_Alarm_Id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AlarmSchema.Alarm_Colns.Col_Alarm_Name + " TEXT," + AlarmSchema.Alarm_Colns.Col_Alarm_Hour + " TEXT," + AlarmSchema.Alarm_Colns.Col_Alarm_Minute + " TEXT," + AlarmSchema.Alarm_Colns.Col_meridiem + " TEXT," + AlarmSchema.Alarm_Colns.Col_alarmStatus + " INTEGER DEFAULT 0 );";


    private static final String DROP_TAbLE = "DROP TABLE IF EXISTS " + AlarmSchema.Alarm_Colns.TABLE_NAME;



    String[] Result = {AlarmSchema.Alarm_Colns.Col_Alarm_Name, AlarmSchema.Alarm_Colns.Col_Alarm_Hour, AlarmSchema.Alarm_Colns.Col_Alarm_Minute, AlarmSchema.Alarm_Colns.Col_meridiem};


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    //this method will create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
//        db.execSQL(CREATE_TABLE2);
    }

    //This method will drop the table if exists or create it
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL(DROP_TAbLE);
//        db.execSQL(DROP_TAbLE2);
        onCreate(db);
    }

    //This method will fetch all data of customers which we have used in ViewAllCustomer Activity
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> fetchdata(){
        ArrayList<HashMap<String, String>> userList2 = new ArrayList<>();
        SQLiteDatabase sqldb = this.getReadableDatabase();
        //String selection = AlarmSchema.Alarm_Colns.COL_Acc_No + " = ?";
        //String[] selectionArgs = {"101"};
        Cursor cursor = sqldb.query(
                AlarmSchema.Alarm_Colns.TABLE_NAME,
                Result,
                null,
                null,
                null,
                null,
                null
        );
        ArrayList<HashMap<String,String>> itemId = new ArrayList<>();
        while(cursor.moveToNext()) {
            HashMap<String,String> user = new HashMap<>();
//            user.put("AlarmName", "Alarm Name: " + cursor.getString(cursor.getColumnIndex("AlarmName")));
            //Log.d("Data: ",cursor.getString(cursor.getColumnIndex("Acc_No")));
            user.put("AlarmHour", cursor.getString(cursor.getColumnIndex("AlarmHour")));
            user.put("AlarmMinutes",":"+cursor.getString(cursor.getColumnIndex("AlarmMinutes")));
            user.put("AlarmMeridiem"," "+cursor.getString(cursor.getColumnIndex("AlarmMeridiem")));
//            user.put("AlarmStatus", "status: " + cursor.getInt(cursor.getColumnIndex("AlarmStatus")));
            userList2.add(user);

        }
        return userList2;
    }

//
//    //This method will update  data after transferring money in Customer table(Credited into Account)
//    public boolean update(String ToAno, int amount, int oldbal){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        int newbal = oldbal+amount;
//        sqLiteDatabase.execSQL("UPDATE "+AlarmSchema.Alarm_Colns.TABLE_NAME+ " SET Balance = "+"'"+newbal+"' "+ "WHERE Acc_No = "+"'"+ToAno+"'");
//        return true;
//    }

//    //This method will update  data after transferring money in Customer table(Debited from Account)
//    public boolean update2(String fromAno, int amount, int oldbal2) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        int newbal2 = oldbal2 - amount;
//        sqLiteDatabase.execSQL("UPDATE " + AlarmSchema.Alarm_Colns.TABLE_NAME + " SET Balance = " + "'" + newbal2 + "' " + "WHERE Acc_No = " + "'" + fromAno + "'");
//        return true;
//    }

//    //This method will details of particular Account number
//    @SuppressLint("Range")
//    public ArrayList<String> getDetails(String Acno) {
//        ArrayList<String> details = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.query(AlarmSchema.Alarm_Colns.TABLE_NAME, Result, AlarmSchema.Alarm_Colns.COL_Acc_No + "=?", new String[]{String.valueOf(Acno)}, null, null, null);
//        if (cursor.moveToFirst()) {
//            String s1, s2, s3, s4;
//            s1 = cursor.getString(cursor.getColumnIndex("Balance"));
//            s2 = cursor.getString(cursor.getColumnIndex("Acc_No"));
//            s3 = cursor.getString(cursor.getColumnIndex("Customer_name"));
//            s4 = cursor.getString(cursor.getColumnIndex("Email"));
//            details.add("Account No: " + s2);
//            details.add("Account Holder: " + s3);
//            details.add("Account Balance: " + s1);
//            details.add("Email: " + s4);
//        }
//        return details;
//    }

}
