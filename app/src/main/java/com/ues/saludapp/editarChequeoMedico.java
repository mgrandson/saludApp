package com.ues.saludapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import control.ControladorChequeoSalud;
import entidades.ChequeoSalud;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class editarChequeoMedico extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_chequeo_medico, container, false);
    }

    TextView txtFecha;
    ControladorChequeoSalud controladorChequeoSalud;
    ChequeoSalud chequeoSalud;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controladorChequeoSalud = new ControladorChequeoSalud(getContext());
        chequeoSalud = controladorChequeoSalud.consultarPorId( getArguments().getInt("idChequeo"));
        txtFecha = view.findViewById(R.id.textViewFechaChequeo);



        if(chequeoSalud!=null){
            txtFecha.setText(chequeoSalud.getFechaChequeo().toString());
        }
        else
        {
            txtFecha.setText("No se encontro");
        }




    }
}