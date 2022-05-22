package com.ues.saludapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import control.ControladorUsuario;
import entidades.Usuario;

public class MainActivity extends AppCompatActivity {

    TextView txtCrearCuenta;
    EditText editUsuario;
    EditText editContrasenia;
    Button btnLogin;
    CheckBox checkRecordarUsuario;
    private long tiempoEspera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCrearCuenta = findViewById(R.id.txtCrearCuenta);
        editUsuario = findViewById(R.id.editUsuario);
        editContrasenia = findViewById(R.id.editContrasenia);
        btnLogin = findViewById(R.id.btnCrearUsuario);
        checkRecordarUsuario = findViewById(R.id.checkRecordarUsuario);


        SharedPreferences datosLogin = getSharedPreferences("datosLogin", Context.MODE_PRIVATE);
        boolean recordarUsuario = datosLogin.getBoolean("recordar",false);
        if(recordarUsuario){
            editUsuario.setText(datosLogin.getString("nombreUsuario",""));
            editContrasenia.setText(datosLogin.getString("contrasenia",""));
            checkRecordarUsuario.setChecked(true);
        }
        else{
            checkRecordarUsuario.setChecked(false);
        }

        txtCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),crearUsuarioActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        System.out.println("IS LOGIN: "+datosLogin.getBoolean("isLogin", false));
        if(datosLogin.getBoolean("isLogin", false)){
            Intent intent = new Intent(getApplicationContext(),navegacion.class);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        if (tiempoEspera + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Presione de nuevo para salir.", Toast.LENGTH_SHORT).show();
        }
        tiempoEspera = System.currentTimeMillis();
    }

    /**
     * hace login en la bd
     */
    public void login(){
        //mostrando dialogo con la informacion de los campos vacios
        if(!validarCamposVacios()){

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // Set the message show for the Alert time
            builder.setMessage(camposVacios);
            builder.setIcon(android.R.drawable.ic_dialog_alert) ;

            // Set Alert Title
            builder.setTitle("Completar información");
            builder.setCancelable(true);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                }
            });
            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();
        }
        else{
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(editUsuario.getText().toString());
            usuario.setContrasenia(editContrasenia.getText().toString());
            ControladorUsuario controladorUsuario = new ControladorUsuario(this);
            boolean login = controladorUsuario.validarLogin(usuario.getNombreUsuario(),usuario.getContrasenia());
            if(login) {
                //guardando datos de usuario en  SharedPreferences para mostrarlos cuando abra la app de nuevo
                SharedPreferences datosLogin = getSharedPreferences("datosLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = datosLogin.edit();
                //El nombre de usuario siempre se guarda para mostrarlo en la app, se debe eliminar cuando el usuario cierre sesion.
                editor.putString("nombreUsuario", usuario.getNombreUsuario());
                editor.putBoolean("isLogin", true);
                if(checkRecordarUsuario.isChecked()) {
                    editor.putString("contrasenia", usuario.getContrasenia());
                    editor.putBoolean("recordar",true);
                    editor.commit();
                }
                else {
                    editor.putString("contrasenia", "");
                    editor.putBoolean("recordar",false);
                    editor.commit();
                }

                Intent intent = new Intent(getApplicationContext(),navegacion.class);
                startActivity(intent);
                limpiarCampos();
            }
            else{
                Toast.makeText(this,controladorUsuario.getInformacion(), Toast.LENGTH_LONG).show();
            }
        }

    }



    /**
     * valida que los campos de texto no esten vacios
     * @return
     */
    String camposVacios;
    public boolean validarCamposVacios() {
        int contador = 0;
        camposVacios = "";
        if (editUsuario.getText().toString().isEmpty()) {
            camposVacios = "Usuario\n";
            contador++;
        }

        if (editContrasenia.getText().toString().isEmpty()) {
            camposVacios = camposVacios + "Contraseña \n";
            contador++;
        }

        if(contador ==0) {return  true;}

        return false;
    }


    /**
     * limpia los campos luego de hacer  loggin
     */
    public void limpiarCampos(){
        if(!checkRecordarUsuario.isChecked()) {
            editUsuario.requestFocus();
            editUsuario.setText(null);
            editContrasenia.setText(null);
        }

    }



}