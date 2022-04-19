package com.ues.saludapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;

import control.ControladorUsuario;
import control.SaludDB;
import control.SaludSqliteHelper;
import entidades.Usuario;

public class MainActivity extends AppCompatActivity {


    TextView txtCrearCuenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCrearCuenta = findViewById(R.id.txtCrearCuenta);

        txtCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),crearUsuarioActivity.class);
                startActivity(intent);
            }
        });

    }
}