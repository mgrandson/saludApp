package com.ues.saludapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import control.ControladorChequeoSalud;
import control.SaludDB;
import entidades.ChequeoSalud;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class listaChekeoMedico extends Fragment {


    ControladorChequeoSalud controladorChequeoSalud;
    List<ChequeoSalud> chequeos;
    ListView listaChequeosMedicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_chekeo_medico, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listaChequeosMedicos = view.findViewById(R.id.listviewChequeoMedicos);
        controladorChequeoSalud = new ControladorChequeoSalud(getContext());
        chequeos = new ArrayList<>();
        chequeos = controladorChequeoSalud.obtenerRegistros();


        adaptadorListaChequedosMedicos adaptadorListaChequedosMedicos = new adaptadorListaChequedosMedicos(getContext(),chequeos);
        listaChequeosMedicos.setAdapter(adaptadorListaChequedosMedicos);



    }
}