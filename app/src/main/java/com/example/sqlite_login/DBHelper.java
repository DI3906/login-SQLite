package com.example.sqlite_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "login.db";

    //Constructor
    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username VARCHAR primary key, password VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }

    //metodo para insetar datos
    public Boolean insertarDatos(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);

        long resultado = db.insert("users", null, values);
        if (resultado == -1) return false;
        else
            return true;
    }

    //metodo para verificar usuario
    public Boolean checkUserName(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ?", new String[] {username});
        if (cursor.getCount()>0) return true;
        else
            return false;
    }

    //verificar usuario y constraseña
    public Boolean checkUserPassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ? and password = ?", new String[] {username, password});
        if (cursor.getCount()>0) return true;
        else
            return false;
    }

}
