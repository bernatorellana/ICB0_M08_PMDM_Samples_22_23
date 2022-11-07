package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.appsqlite.bd.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper helper = new DbHelper(this, "principal",null,1 );
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from persona",null);
        while(cursor.moveToNext()) {
            Log.d("XXX", ""+cursor.getInt(cursor.getColumnIndex("id")));
            Log.d("XXX", cursor.getString(cursor.getColumnIndex("nom")));
            Log.d("XXX", ""+cursor.getFloat(cursor.getColumnIndex("pes")));
        }


    }
}