package com.ues.saludapp;

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

        txtChequeoMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_menuInicio_to_chekeoMedico);
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
                Navigation.findNavController(view).navigate(R.id.action_menuInicio_to_ejercicio);
            }
        });

    }
}