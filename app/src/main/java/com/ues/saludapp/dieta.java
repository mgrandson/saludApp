package com.ues.saludapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

import control.ControladorChequeoSalud;
import control.ControladorDetalleDietaPorTiempo;
import control.ControladorDietaAlimenticia;
import control.ControladorRegistroDietaDiaria;
import control.ControladorTipoComida;
import control.ControladorUsuario;
import entidades.ChequeoSalud;
import entidades.DetalleDietaPorTiempo;
import entidades.DietaAlimenticia;
import entidades.RegistroDietaDiaria;
import entidades.TipoComida;
import entidades.Usuario;

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

    //Controladores en caso de que sea por datos guardados
    ControladorDietaAlimenticia controladorDietaAlimenticia;
    DietaAlimenticia dietaAlimenticia;
    List<DietaAlimenticia> dietaAlimenticiaList = new ArrayList<>();
    ControladorRegistroDietaDiaria controladorRegistroDietaDiaria;
    List<RegistroDietaDiaria> registroDietaDiariaList = new ArrayList<>();
    ControladorDetalleDietaPorTiempo controladorDetalleDietaPorTiempo;
    List<DetalleDietaPorTiempo> detalleDietaPorTiempoList = new ArrayList<>();
    ControladorTipoComida controladorTipoComida;
    TipoComida tipoComida;
    ControladorChequeoSalud controladorChequeoSalud;
    ChequeoSalud chequeoSalud;
    List<ChequeoSalud> chequeoSaludList = new ArrayList<>();

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
        int idChequeo=1;
        int idDieta,idRegistroDia, idRegistro,idTipoComida,porcionTipo;
        String idTiempoComida, nombreTipoComida,comida1,comida2,comida3;
        double cantidadTipo;
        String estado = "P";

        //chequeoSaludList = controladorChequeoSalud.obtenerRegistros();
       /* if(chequeoSalud != null){
           idChequeo = chequeoSalud.getId();
        }*/

        dietaAlimenticia = controladorDietaAlimenticia.obtenerDietaAlimenticiaId(idChequeo);
        idDieta = dietaAlimenticia.getId();
        registroDietaDiariaList = controladorRegistroDietaDiaria.obtenerRegistroDieta(idDieta);

        for (int i = 0; i <= dietaAlimenticiaList.size() ; i++) {
            idRegistroDia = registroDietaDiariaList.get(i).getDiaSemanaId();
            idRegistro = registroDietaDiariaList.get(i).getId();
            if(idRegistroDia == diaSemana){
                detalleDietaPorTiempoList = controladorDetalleDietaPorTiempo.obtenerDetallesDietaPorTiempos(idRegistro);
            }
        }

        for (int i = 0; i <= detalleDietaPorTiempoList.size()-1 ; i++) {
            idTipoComida = detalleDietaPorTiempoList.get(i).getTipoComidaId();
            idTiempoComida = detalleDietaPorTiempoList.get(i).getTiempoComida();
            tipoComida = controladorTipoComida.obtenerTipoComidaId(idTipoComida);

            nombreTipoComida = tipoComida.getNombreTipoComida();
            cantidadTipo = tipoComida.getCantidadCalorifica();
            porcionTipo = tipoComida.getTamanioPorcion();
            if(idTiempoComida.equals("Desayuno"))
            {
                comida1="Comida: "+nombreTipoComida+"\nCantidad calorifica: " + cantidadTipo +"\nTamaño de porción: " + porcionTipo+"\n\n";
                desayuno = desayuno + comida1;
            }
            else if(idTiempoComida.equals("Almuerzo")){
                comida2 = "Comida: "+ nombreTipoComida  +"\nCantidad calorifica: " + cantidadTipo +"\nTamaño de porción: " + porcionTipo+"\n\n";
                almuerzo = almuerzo+comida2;
            }
            else if(idTiempoComida.equals("Cena")){
                comida3 = "Comida: "+ nombreTipoComida +"\nCantidad calorifica: " + cantidadTipo +"\nTamaño de porción: " + porcionTipo+"\n\n";
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
        SharedPreferences datosLogin = getContext().getSharedPreferences("datosLogin", Context.MODE_PRIVATE);
        datosLogin.getString("nombreUsuari","");
        switch (dayOfTheWeek){
            case "Monday":
                txtDia.setText("LUNES");
                obtenerDatos(2);
                break;
            case "Tuesday":
                txtDia.setText("MARTES");
                obtenerDatos(3);
                break;
            case "Wednesday":
                txtDia.setText("MIERCOLES");
                obtenerDatos(4);
                break;
            case "Thursday":
                txtDia.setText("JUEVES");
                obtenerDatos(5);
                break;
            case "Friday":
                txtDia.setText("VIERNES");
                obtenerDatos(6);
                break;
            case "Saturday":
                txtDia.setText("SABADO");
                obtenerDatos(7);
                break;
            case "Sunday":
                txtDia.setText("DOMINGO");
                obtenerDatos(1);
                break;
        }

        btnLunes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                txtDia.setText("LUNES");
                obtenerDatos(2);
            }
        });

        btnMartes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDia.setText("MARTES");
                obtenerDatos(3);
            }
        });

        btnMiercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDia.setText("MIERCOLES");
                obtenerDatos(4);
            }
        });

        btnJueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDia.setText("JUEVES");
                obtenerDatos(5);
            }
        });

        btnViernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDia.setText("VIERNES");
                obtenerDatos(6);
            }
        });

        btnSabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDia.setText("SABADO");
                obtenerDatos(7);
            }
        });

        btnDomingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDia.setText("DOMINGO");
                obtenerDatos(1);
            }
        });
    }

}