package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

//import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.Home.USER;

//import static com.getmotivation.getmotivation.OrlandoAntonioVeneziano.Home.application;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    //public static final String TABLE_NAME = " corse2";
    private static final String COL0 = "ID";
    private static final String COL1 = "data";
    private static final String COL2 = "passi";
    private static final String COL3 = "km";
    private static final String COL4 = "kcal";
    private static final String COL5 = "email";
    public static final String USER = "corse";
    String user;
            //FirebaseAuth.getInstance().getCurrentUser().getEmail();


    public DataBaseHelper(Context context,String user) {
        super(context, user, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        SharedPreferences sharedPreferences = application.getSharedPreferences("save", MODE_PRIVATE);
//        user = sharedPreferences.getString("email",USER);
        user ="corse";
//        String email = String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail());
//        user = email;

        onCreateModified(db,user);
    }

    public void onCreateModified(SQLiteDatabase db,String user){
        String createTable = "CREATE TABLE " + user + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 +" TEXT,"+ COL2 + " TEXT,"+
                COL3+" TEXT,"+COL4+" TEXT,"+COL5+" TEXT)";
        db.execSQL(createTable);
        Log.d(TAG,"Tabella creata correttamente");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
//        onCreate(db);
    }

    public boolean doInsert(String data,String passi,String km,String kcal,String email,String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,data);
        contentValues.put(COL2,passi);
        contentValues.put(COL3,km);
        contentValues.put(COL4,kcal);
        contentValues.put(COL5,email);

        Log.d(TAG, " Dati aggiunti " + data + " "+ passi +" "+km +" "+kcal+ " "+" aggiunti alla tabella " + user);

        long result = db.insert(user, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getTable(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + user;
        Cursor dati_tabella = db.rawQuery(query, null);
        return dati_tabella;
    }

    public Cursor getDatiUtente(String email,String user){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " +user +
                " WHERE " + COL5 + " = '" + email + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getDay(String email,String user,String day){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " +user +
                " WHERE " + COL5 + " = '" + email + "' AND "+
                COL1 + " = '"+ day + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPassiInDay(String email,String user,String day){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ COL2 + " FROM " +user +
                " WHERE " + COL5 + " = '" + email + "' AND "+
                COL1 + " = '"+ day + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getKmInDay(String email,String user,String day){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ COL3 + " FROM " +user +
                " WHERE " + COL5 + " = '" + email + "' AND "+
                COL1 + " = '"+ day + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getKcalInDay(String email,String user,String day){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ COL4 + " FROM " +user +
                " WHERE " + COL5 + " = '" + email + "' AND "+
                COL1 + " = '"+ day + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }  //dopo aver usato questi 3 metodi otengo dei cursori che puntano non ad un solo elemento, ma ad una colonna di Stringhe, scorrendo queste colonne utilizzando i metodi di parse e sommando i valori ottenuti posso ottenere il numero di passi svolti in un giorno


}
