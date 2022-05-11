package com.ues.saludapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
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
    ImageView imgView;

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

        imgView = view.findViewById(R.id.imageView8);
        //DIRIGIR HACIA AGREGAR CHEQUEO MEDICO
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!existeChequeoActivo()){
                    Navigation.findNavController(view).navigate(R.id.action_listaChekeoMedico_to_agregarChequeoMedicoFragment);
                    System.out.println("DIRIGIENDO A AGREGAR CHEQUEO MEDICO...");
                }
                else {
                    Navigation.findNavController(view).navigate(R.id.action_agregarChequeoMedicoFragment_to_crearRutinaEjercicioFragment);
                    //MOSTRAS MENSAJE
                    CharSequence text = "Primero termine la dieta generada...";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(getContext(), text, duration);
                    toast.show();
                }
            }
        });
    }

    public boolean existeChequeoActivo(){
        ControladorChequeoSalud cChequeoSalud = new ControladorChequeoSalud(this.getContext());
        ChequeoSalud rcs;
        rcs = cChequeoSalud.consultarPorEstado("P");

        if(rcs != null){
            System.out.println("ID CHEQUEO: "+rcs.getId()+ " ESTADO: "+rcs.getEstado()+ " FECHA: "+rcs.getFechaChequeo());
            return true;
        }
        else{
            return false;
        }
    }
}