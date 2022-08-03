package com.example.newapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "exercise.db";
    public static final String TABLE_NAME = "ex_table";
    public static final int DB_VERSION = 1;
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Title";
    public static final String COL_3 = "Color";

    public DBHelper(@Nullable Context context) {
        super(context , DATABASE_NAME , null , DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , Title TEXT , Color Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addPlan(String title , String Color){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,title);
        contentValues.put(COL_3, Color);

      long res =   sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

      if (res == -1){
          Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
      }else{
          Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
      }
    }

    Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateData(String id , String title , String Color){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2 , title);
        contentValues.put(COL_3 , Color);

        long result =sqLiteDatabase.update(TABLE_NAME , contentValues , "id=?" , new String[] {id});

        if (result == -1) {
            Toast.makeText(context,"Failed to update",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successfully Update",Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_NAME, "id=?" , new String[] {id});

        if(result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Deleted Successfully" , Toast.LENGTH_SHORT ).show();
        }
    }
}
