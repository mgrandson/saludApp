package com.ues.saludapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import control.ControladorRegistroRitmoCardiaco;
import entidades.ChequeoSalud;
import entidades.RegistroRitmoCardiaco;

public class adaptadorListaChequedosMedicos extends BaseAdapter {

    Context context;
    List<ChequeoSalud> chequeos;
    ControladorRegistroRitmoCardiaco controladorRegistroRitmoCardiaco;
    RegistroRitmoCardiaco registroRitmoCardiaco;
    LayoutInflater inflater;


    public adaptadorListaChequedosMedicos(Context context, List<ChequeoSalud> chequeos1) {
        this.context = context;
        this.chequeos = chequeos1;
        controladorRegistroRitmoCardiaco = new ControladorRegistroRitmoCardiaco(context);

    }

    @Override
    public int getCount() {
        return chequeos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.chequeo_medico_items, null);
        TextView textViewPeso = itemView.findViewById(R.id.textViewPeso);
        TextView textViewAltura = itemView.findViewById(R.id.textViewAltura);
        TextView textViewImc = itemView.findViewById(R.id.textViewImc);
        TextView textViewRitmoCardiado = itemView.findViewById(R.id.textViewRitmoCardiaco);



        TextView textViewFechaChequeo = itemView.findViewById(R.id.txtFechaChequeo);
        textViewFechaChequeo.setText(chequeos.get(i).getFechaChequeo());
        textViewPeso.setText(  String.valueOf(chequeos.get(i).getPesoActual()) + " KG.");
        textViewAltura.setText(String.valueOf(chequeos.get(i).getAlturaActual()) + " CM.");
        textViewImc.setText(String.valueOf(chequeos.get(i).getValorImcActual()) + " IMC.");
        registroRitmoCardiaco = controladorRegistroRitmoCardiaco.buscarPorIdChequeoSalud(chequeos.get(i).getId());
        if(registroRitmoCardiaco != null){
            textViewRitmoCardiado.setText(String.valueOf(registroRitmoCardiaco.getBpm()));
        }

        ImageView imageViewEdit = itemView.findViewById(R.id.imgEdit);

        imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //falta validar que solo se pueda editar si el chequeo es menor a 2 dias de haberse creado
                Bundle bundle = new Bundle();
                bundle.putString("idChequeo",chequeos.get(i).getFechaChequeo().toString());
                Navigation.findNavController(view).navigate(R.id.action_listaChekeoMedico_to_editarChequeoMedico,bundle);
            }
        });

        return itemView;
    }
}
