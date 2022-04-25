package com.ues.saludapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class chekeoMedico extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_chekeo_medico, container, false);
    }


    Button bnt;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ////bnt = view.findViewById(R.id.btnTest);
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        ////        Navigation.findNavController(view).navigate(R.id.action_chekeoMedico_to_listaChekeoMedico);
            }
        });
    }
}