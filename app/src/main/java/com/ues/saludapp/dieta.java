package com.ues.saludapp;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import control.ControladorDetalleDietaPorTiempo;
import control.ControladorDietaAlimenticia;
import control.ControladorRegistroDietaDiaria;
import control.ControladorTipoComida;
import entidades.DetalleDietaPorTiempo;
import entidades.DietaAlimenticia;
import entidades.RegistroDietaDiaria;
import entidades.TipoComida;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class dieta extends Fragment {

    Button btnLunes;
    Button btnMartes;
    Button btnMiercoles;
    Button btnJueves;
    Button btnViernes;
    Button btnSabado;
    Button btnDomingo;
    TextView txtDia;
    TextView txtDesayuno;
    TextView txtAlmuerzo;
    TextView txtCena;
    ControladorDietaAlimenticia controladorDietaAlimenticia;
    DietaAlimenticia dietaAlimenticia;
    List<DietaAlimenticia> dietaAlimenticiaList = new ArrayList<>();
    ControladorRegistroDietaDiaria controladorRegistroDietaDiaria;
    RegistroDietaDiaria registroDietaDiaria;
    List<RegistroDietaDiaria> registroDietaDiariaList = new ArrayList<>();
    ControladorDetalleDietaPorTiempo controladorDetalleDietaPorTiempo;
    DetalleDietaPorTiempo detalleDietaPorTiempo;
    List<DetalleDietaPorTiempo> detalleDietaPorTiempoList = new ArrayList<>();
    ControladorTipoComida controladorTipoComida;
    TipoComida tipoComida;
    List<TipoComida> tipoComidaList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dieta, container, false);
    }


    public void obtenerDatos(int diaSemana) {
        String desayuno = "Desayuno:\n";
        String almuerzo = "Almuerzo:\n";
        String cena = "Cena:\n";

        dietaAlimenticiaList = controladorDietaAlimenticia.obtenerDietaAlimenticia(0);

        for (int i = 0; i < dietaAlimenticiaList.size() - 1; i++) {
            registroDietaDiaria = controladorRegistroDietaDiaria.obtenerRegistroDietaDia(dietaAlimenticiaList.get(i).getId(), diaSemana);
            detalleDietaPorTiempoList = controladorDetalleDietaPorTiempo.obtenerDetallesDietaPorTiempos(registroDietaDiaria.getId());
        }

        for (int i = 0; i < detalleDietaPorTiempoList.size() - 1; i++) {
            tipoComida = controladorTipoComida.obtenerTipoComidaId(detalleDietaPorTiempoList.get(i).getTipoComidaId());

            if(detalleDietaPorTiempoList.get(i).getTiempoComida() == "desayuno")
            {
                desayuno += "Comida: "+tipoComida.getNombreTipoComida()
                         +"\nCantidad calorifica:" + tipoComida.getCantidadCalorifica()
                         +"\nTamaño de porción: " + tipoComida.getTamanioPorcion();
            }
            else if(detalleDietaPorTiempoList.get(i).getTiempoComida() == "almuerzo"){
                almuerzo += "Comida: "+tipoComida.getNombreTipoComida()
                        +"\nCantidad calorifica:" + tipoComida.getCantidadCalorifica()
                        +"\nTamaño de porción: " + tipoComida.getTamanioPorcion();
            }
            else if(detalleDietaPorTiempoList.get(i).getTiempoComida() == "cena"){
                cena += "Comida: "+tipoComida.getNombreTipoComida()
                        +"\nCantidad calorifica:" + tipoComida.getCantidadCalorifica()
                        +"\nTamaño de porción: " + tipoComida.getTamanioPorcion();
            }
        }

        txtDesayuno.setText(desayuno);
        txtAlmuerzo.setText(almuerzo);
        txtCena.setText(cena);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtDia = view.findViewById(R.id.txtDiaSemana);
        btnLunes = view.findViewById(R.id.btnLunes);
        btnMartes = view.findViewById(R.id.btnMartes);
        btnMiercoles = view.findViewById(R.id.btnMiercoles);
        btnJueves = view.findViewById(R.id.btnJueves);
        btnViernes = view.findViewById(R.id.btnViernes);
        btnSabado = view.findViewById(R.id.btnSabado);
        btnDomingo = view.findViewById(R.id.btnDomingo);
        txtDesayuno = view.findViewById(R.id.txtDesayuno);
        txtAlmuerzo = view.findViewById(R.id.txtAlmuerzo);
        txtCena = view.findViewById(R.id.txtCena);
        controladorDietaAlimenticia = new ControladorDietaAlimenticia(getContext());
        controladorRegistroDietaDiaria = new ControladorRegistroDietaDiaria(getContext());
        controladorDetalleDietaPorTiempo = new ControladorDetalleDietaPorTiempo(getContext());
        controladorTipoComida = new ControladorTipoComida(getContext());

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);

        switch (dayOfTheWeek){
            case "Monday":
                txtDia.setText("Lunes");
                obtenerDatos(1);
                break;
            case "Tuesday":
                txtDia.setText("Martes");
                obtenerDatos(2);
                break;
            case "Wednesday":
                txtDia.setText("Miércoles");
                obtenerDatos(3);
                break;
            case "Thursday":
                txtDia.setText("Jueves");
                obtenerDatos(4);
                break;
            case "Friday":
                txtDia.setText("Viernes");
                obtenerDatos(5);
                break;
            case "Saturday":
                txtDia.setText("Sábado");
                obtenerDatos(6);
                break;
            case "Sunday":
                txtDia.setText("Domingo");
                obtenerDatos(7);
                break;
        }

        btnLunes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                txtDia.setText("Lunes");
                obtenerDatos(1);
            }
        });

        btnMartes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDia.setText("Martes");
                obtenerDatos(2);
            }
        });

        btnMiercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDia.setText("Miércoles");
                obtenerDatos(3);
            }
        });

        btnJueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDia.setText("Jueves");
                obtenerDatos(4);
            }
        });

        btnViernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDia.setText("Viernes");
                obtenerDatos(5);
            }
        });

        btnSabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDia.setText("Sábado");
                obtenerDatos(6);
            }
        });

        btnDomingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDia.setText("Domingo");
                obtenerDatos(7);
            }
        });
    }

}