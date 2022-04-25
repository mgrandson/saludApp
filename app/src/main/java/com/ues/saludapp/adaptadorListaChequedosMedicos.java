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

public class adaptadorListaChequedosMedicos extends BaseAdapter {

    Context context;
    ArrayList<listaChekeoMedico.chequeo> chequeos;
    LayoutInflater inflater;


    public adaptadorListaChequedosMedicos(Context context, ArrayList<listaChekeoMedico.chequeo> chequeos1) {
        this.context = context;
        this.chequeos = chequeos1;
        //this.inflater = (LayoutInflater.from(context));;
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
        TextView textViewFechaChequeo = itemView.findViewById(R.id.txtFechaChequeo);
        textViewFechaChequeo.setText(chequeos.get(i).getFecha());
        textViewPeso.setText(  String.valueOf(chequeos.get(i).getPeso()) + " Kg.");

        ImageView imageViewEdit = itemView.findViewById(R.id.imgEdit);

        imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("idChequeo",chequeos.get(i).getFecha().toString());
                Navigation.findNavController(view).navigate(R.id.action_listaChekeoMedico_to_editarChequeoMedico,bundle);
            }
        });

        return itemView;
    }
}
