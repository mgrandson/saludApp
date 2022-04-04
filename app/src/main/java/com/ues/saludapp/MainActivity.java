package com.ues.saludapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import control.SaludDB;
import control.SaludSqliteHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SaludSqliteHelper salud = new SaludSqliteHelper(this, SaludDB.NOMBRE_BD,null,SaludDB.VERSION_BD);
        SQLiteDatabase db = salud.getWritableDatabase();

    }
}