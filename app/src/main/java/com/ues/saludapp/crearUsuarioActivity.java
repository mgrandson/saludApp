package com.ues.saludapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

import control.ControladorUsuario;
import entidades.Usuario;

public class crearUsuarioActivity extends AppCompatActivity {

    Spinner spinnerGenero;
    EditText newNombreUsuario;
    EditText newContrasenia;
    EditText newRepetirContrasenia;
    EditText newNombre;
    EditText newApellido;
    EditText newFecha;
    Button btnCrearUsuario;
    Usuario usuario;
    ControladorUsuario controladorUsuario;

    // Guardar el último año, mes y día del mes
    private int ultimoAnio, ultimoMes, ultimoDiaDelMes;

    // Crear un listener del datepicker;
    private DatePickerDialog.OnDateSetListener listenerDeDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int anio, int mes, int diaDelMes) {
            // Esto se llama cuando seleccionan una fecha. Nos pasa la vista, pero más importante, nos pasa:
            // El año, el mes y el día del mes. Es lo que necesitamos para saber la fecha completa
            // Refrescamos las globales
            ultimoAnio = anio;
            ultimoMes = mes;
            ultimoDiaDelMes = diaDelMes;
            // Y refrescamos la fecha
            refrescarFechaEnEditText();

        }
    };

    public void refrescarFechaEnEditText() {
        // Formateamos la fecha pero podríamos hacer cualquier otra cosa ;)
        String fecha = String.format(Locale.getDefault(), "%02d-%02d-%02d", ultimoAnio, ultimoMes+1, ultimoDiaDelMes);

        // La ponemos en el editText
        newFecha.setText(fecha);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        newNombreUsuario = findViewById(R.id.editNewUsuario);
        newContrasenia = findViewById(R.id.editNewContrasenia);
        newRepetirContrasenia = findViewById(R.id.editNewRepetirContrasenia);
        newNombre = findViewById(R.id.editNewNombre);
        newApellido = findViewById(R.id.editNewApellido);
        newFecha = findViewById(R.id.editNewFecha);
        spinnerGenero = findViewById(R.id.spinner);
        btnCrearUsuario = findViewById(R.id.btnCrearUsuario);
        String[] generos = {"SELECCION GENERO","FEMENINO","MASCULINO"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.item_genero,generos);
        spinnerGenero.setAdapter(arrayAdapter);
        spinnerGenero.setSelection(0);
        // Poner último año, mes y día a la fecha de hoy
        final Calendar calendario = Calendar.getInstance();
        ultimoAnio = calendario.get(Calendar.YEAR);
        ultimoMes = calendario.get(Calendar.MONTH);
        ultimoDiaDelMes = calendario.get(Calendar.DAY_OF_MONTH);
        // Refrescar la fecha en el EditText
        //refrescarFechaEnEditText();

        newFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Le pasamos lo que haya en las globales
                DatePickerDialog dialogoFecha = new DatePickerDialog(crearUsuarioActivity.this, listenerDeDatePicker, ultimoAnio, ultimoMes, ultimoDiaDelMes);
                //Mostrar
                dialogoFecha.show();
            }
        });
        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearUsuario();
            }
        });

    }


    /**
     * registra un nuevo usuario
     */
    public void crearUsuario()
    {
        //mostrando dialogo con la informacion de los campos vacios
        if(!validarCamposVacios()){

            AlertDialog.Builder builder = new AlertDialog.Builder(crearUsuarioActivity.this);
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

        //registrando usuario
        else{
            usuario = new Usuario();
            usuario.setNombreUsuario(newNombreUsuario.getText().toString());
            usuario.setNombre(newNombre.getText().toString());
            usuario.setApellido(newApellido.getText().toString());
            usuario.setGenero(spinnerGenero.getSelectedItem().toString());
            usuario.setFechaNacimiento(newFecha.getText().toString());
            controladorUsuario = new ControladorUsuario(this);
            Usuario bUsuario = controladorUsuario.buscarUsuario(usuario.getNombreUsuario());

            if(bUsuario==null){
                long respuesta = controladorUsuario.crearUsuario(usuario);
                if(respuesta ==-1 || respuesta == 0){
                    Toast.makeText(this,"Error al registrar el usuario.", Toast.LENGTH_LONG).show();
                }
                else {
                    //aca deberiamos llevar al usaurio al menu principal
                    Toast.makeText(this,"Usuario registrado.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),Menu.class);
                    startActivity(intent);
                }

            }
            else
            {
                Toast.makeText(this,"El usuario: " + bUsuario.getNombreUsuario() + " ya existe, favor intentar con otro nombre de usuario.", Toast.LENGTH_LONG).show();
            }

            long msg = controladorUsuario.crearUsuario(usuario);
            /**if(msg !=-1 || msg !=0  )
            {
                Toast.makeText(this,"Usuario registrado.", Toast.LENGTH_LONG).show();
            }
            Toast.makeText(this,"Error al registrar el usuario.", Toast.LENGTH_LONG).show();
            **/
        }
    }



    /**
     * valida que los campos de texto no esten vacios
     * @return
     */
    String camposVacios;
    public boolean validarCamposVacios(){
        int contador =0;
        camposVacios = "";
        if(newNombreUsuario.getText().toString().isEmpty())
        {
            camposVacios = "Usuario\n";
            contador++;
        }

        if(newContrasenia.getText().toString().isEmpty()){
            camposVacios = camposVacios + "Contraseña \n";
            contador++;
        }

        if(newRepetirContrasenia.getText().toString().isEmpty()){
            camposVacios = camposVacios + "Repetir contraseña \n";
            contador++;
        }

       if(newNombre.getText().toString().isEmpty()){
            camposVacios = camposVacios + "Nombre \n";
            contador++;
        }

        if (newApellido.getText().toString().isEmpty()){
            camposVacios = camposVacios + "Apellido \n";
            contador++;
        }

        if(spinnerGenero.getSelectedItemPosition() == 0){
            camposVacios = camposVacios + "Genero \n";
            contador++;
        }

        if(newFecha.getText().toString().isEmpty()) {
            camposVacios = camposVacios + "Fecha de nacimiento \n";
            contador++;
        }

        if(!newContrasenia.getText().toString() .equals(newRepetirContrasenia.getText().toString())){
           camposVacios = camposVacios + "Contraseñas no coindicen" ;
            contador++;
        }

        if(contador ==0) {return  true;}

        return false;
    }







}