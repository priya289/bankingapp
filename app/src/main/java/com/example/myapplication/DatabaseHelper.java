package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1234567890,'Sita',9472.00,'sita01@gmail.com','9951234567891234','ABC09876543')");
        db.execSQL("insert into user_table values(2345678901,'Sneha',582.67,'sneha02@gmail.com','9951234567892341','BCA98765432')");
        db.execSQL("insert into user_table values(3456789012,'Gaurav',1359.56,'gaurav03@gmail.com','9951234567893412','CAB87654321')");
        db.execSQL("insert into user_table values(4567890123,'Amit',1500.01,'amit04@gmail.com','9951234567894123','ABC76543210')");
        db.execSQL("insert into user_table values(5678901234,'Sona',2603.48,'sona05@gmail.com','9951234567892345','BCA65432109')");
        db.execSQL("insert into user_table values(6789012345,'Punit',945.16,'punit06@gmail.com','9951234567893452','CAB54321098')");
        db.execSQL("insert into user_table values(7890123456,'Dhruv',5936.00,'dhruv07@gmail.com','9951234567894523','ABC43210987')");
        db.execSQL("insert into user_table values(8901234567,'Vinay',857.22,'vinay08@gmail.com','9951234567895234','BCA32109876')");
        db.execSQL("insert into user_table values(9012345678,'Mohini',4398.46,'mohini09@gmail.com','9951234567893456','CAB21098765')");
        db.execSQL("insert into user_table values(1234567809,'Tanvi',273.90,'tanvi10@gmail.com','9951234567894563','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from user_table", null);
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from transfers_table", null);
    }

    public void insertTransferData(String date, String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        long result = db.insert(TABLE_NAME1, null, contentValues);
    }
}
