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

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class listaChekeoMedico extends Fragment {


    /**
     * esta es la entidad de chekeo medio, la crre aqui solo para pruebas con los valores que es necesitaran para listar los chequeos
     */
    public class chequeo{
        private String fecha;
        private float peso;


        public chequeo(String fecha, float peso) {
            this.fecha = fecha;
            this.peso = peso;

        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public float getPeso() {

            return Math.round(peso);
        }

        public void setPeso(float peso) {
            this.peso = peso;
        }


    }


    ArrayList<chequeo> chequeos;
    Integer i =0;
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
        chequeos = new ArrayList<>();
        for(i=0;i<20;i++){
            chequeos.add(new chequeo(i +"/04/2022", i*1.1f));

        }
        adaptadorListaChequedosMedicos adaptadorListaChequedosMedicos = new adaptadorListaChequedosMedicos(getContext(),chequeos);
        listaChequeosMedicos.setAdapter(adaptadorListaChequedosMedicos);



    }
}