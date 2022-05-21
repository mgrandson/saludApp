package com.ues.saludapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class menuInicio extends Fragment {

    TextView txtChequeoMedico;
    TextView txtDieta;
    TextView txtRutinaEjercicio;
    TextView txtNombreUsuario;
    TextView txtSalir;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_inicio, container, false);
    }

    /**
     * En este metodo se coloca la funcioalidad de acceso a las diferentes pantallas
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtChequeoMedico = view.findViewById(R.id.txtChequeoMedico);
        txtDieta = view.findViewById(R.id.txtDieta);
        txtRutinaEjercicio = view.findViewById(R.id.txtRutinaEjercicio);
        txtNombreUsuario = view.findViewById(R.id.txtUsuario);
        SharedPreferences datosLogin = getContext().getSharedPreferences("datosLogin", Context.MODE_PRIVATE);
        txtNombreUsuario.setText("¡Bienvenido " + datosLogin.getString("nombreUsuario","")+"!");
        txtSalir = view.findViewById(R.id.txtSalir);
        txtChequeoMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_menuInicio_to_listaChekeoMedico);
            }
        });


        txtDieta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_menuInicio_to_dieta);
            }
        });


        txtRutinaEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_menuInicio_to_crearRutinaEjercicioFragment);
            }
        });
        txtSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Cerrar sesión");
                builder.setMessage("¿Está seguro?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor = datosLogin.edit();
                        if(!datosLogin.getBoolean("recordar",false)){
                            editor.putString("nombreUsuario", "");
                            editor.putString("contrasenia", "");
                        }
                        editor.putBoolean("isLogin", false);
                        editor.commit();
                        startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }
}