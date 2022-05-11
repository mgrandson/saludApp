package com.ues.saludapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ues.saludapp.databinding.FragmentChequeoMedicoAgregarBinding;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

import control.ControladorChequeoSalud;
import control.ControladorDeporte;
import control.ControladorDietaAlimenticia;
import control.ControladorRangoImc;
import control.ControladorRegistroHidratacion;
import control.ControladorRegistroPresionArterial;
import control.ControladorRegistroRitmoCardiaco;
import entidades.ChequeoSalud;
import entidades.Deporte;
import entidades.DetalleDeportePorFactor;
import entidades.DetalleDietaPorTiempo;
import entidades.DiaSemana;
import entidades.DietaAlimenticia;
import entidades.FactorActividad;
import entidades.RangoAltura;
import entidades.RangoImc;
import entidades.RangoPeso;
import entidades.RegistroActividadFisicaDiaria;
import entidades.RegistroDietaDiaria;
import entidades.RegistroHidratacion;
import entidades.RegistroPresionArterial;
import entidades.RegistroRitmoCardiaco;
import entidades.TipoComida;

public class AgregarChequeoMedicoFragment extends Fragment {
    private FragmentChequeoMedicoAgregarBinding binding;

    //FECHA ACTUAL
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date(System.currentTimeMillis());
    String strFechaActual = sdf.format(date);

    //PARA DETERMINAR EL RANGO DE IMC
    private RangoPeso eRangoPeso;
    private RangoAltura eRangoAltura;
    private RangoImc eRangoImc;

    //PARA COMPLETAR EL CHEQUEO DE SALUD
    private ChequeoSalud eChequeoSalud;
    private RegistroRitmoCardiaco eRitmoCardiaco;
    private RegistroPresionArterial ePresionArterial;
    private RegistroHidratacion eHidratacion;

    //PARA GENERAR DIETA ALIMENTICIA
    private DiaSemana eDiaSemana;
    private TipoComida eTipoComida;
    private DietaAlimenticia eDietaAlimenticia;
    private RegistroDietaDiaria eDietaDiaria;
    private DetalleDietaPorTiempo eDietaPorTiempo;

    //PARA GENERAR RUTINA DE EJERCICIO
    private Deporte eDeporte;
    private FactorActividad eFactorActividad;
    private DetalleDeportePorFactor eDeportePorFactor;
    private RegistroActividadFisicaDiaria eActividadFisicaDiaria;

    //CONTROLADORES
    private ControladorChequeoSalud cChequeoSalud;
    private ControladorDeporte cDeporte;
    private ControladorRegistroRitmoCardiaco cRitmoCardiaco;
    private ControladorRegistroHidratacion cRegistroHidratacion;
    private ControladorRegistroPresionArterial cPresionArterial;
    private ControladorRangoImc cRangoImc;
    private ControladorDietaAlimenticia cDietaAlimenticia;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChequeoMedicoAgregarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);

        binding.btnEvaluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generarChequeoMedico();
                Navigation.findNavController(view).navigate(R.id.action_agregarChequeoMedicoFragment_to_crearRutinaEjercicioFragment);
            }
        });
    }

    //FUNCIONES PERSONALIZADAS

    public void generarChequeoMedico(){

        //OBTENIENDO VALORES INGRESADOS POR EL USUARIO
        DecimalFormat df = new DecimalFormat("#.##");
        double pesoActual = Double.parseDouble(binding.txtPesoActual.getText().toString());
        double alturaActual = (Double.parseDouble(binding.txtAlturaActual.getText().toString()))/100;
        //double presionArterial =Double.parseDouble(binding.txtPresionArterial.getText().toString());
        int consumoAgua = Integer.parseInt(binding.txtConsumoAgua.getText().toString());
        int ritmoCardiaco = Integer.parseInt(binding.txtRitmoCardiaco.getText().toString());

        double imcActualCalc = pesoActual / Math.pow(alturaActual, 2);
        double imcActual = Double.valueOf(df.format(imcActualCalc));

        int sistolica = Integer.parseInt(binding.txtPresionArterialSistolica.getText().toString());
        int diastolica = Integer.parseInt(binding.txtPresionArterialDiastolica.getText().toString());

        int rangoImcId = 1;

        //TEST
        System.out.println("PRESION ARTERIAL: "+sistolica+"/"+diastolica);
        System.out.println("SISTOLICA: "+sistolica);
        System.out.println("DIASTOLICA: "+diastolica);
        System.out.println("PESO ACTUAL: "+pesoActual);
        System.out.println("ALTURA ACTUAL: "+alturaActual);
        System.out.println("CONSUMO AGUA: "+consumoAgua);
        System.out.println("RITMO CARDIACO: "+ritmoCardiaco);
        System.out.println("IMC ACTUAL: "+imcActual);

        //GUARDANDO REGISTRO DE CONTROL DE SALUD
        eChequeoSalud = new ChequeoSalud();
        eChequeoSalud.setPesoActual(pesoActual);
        eChequeoSalud.setAlturaActual(alturaActual);
        eChequeoSalud.setValorImcActual(imcActual);
        eChequeoSalud.setFechaChequeo(strFechaActual);

        //ASIGNANDO RANGO IMC
        cRangoImc = new ControladorRangoImc(this.getContext());
        if(imcActual < 18.5){
            eChequeoSalud.setMensajeImcActual("Bajopeso");
        }
        else if(imcActual >= 18.5 && imcActual < 25){
            eChequeoSalud.setMensajeImcActual("Peso Normal");
            rangoImcId = cRangoImc.obtenerRangosIMCId(18.5, 25);
        }
        else if(imcActual >=25.0 && imcActual <30.0){
            rangoImcId = cRangoImc.obtenerRangosIMCId(25.0, 30.0);
            eChequeoSalud.setMensajeImcActual("Sobrepeso");
        }
        else if(imcActual >=30.0 && imcActual <35.0){
            rangoImcId = cRangoImc.obtenerRangosIMCId(30.0, 35.0);
            eChequeoSalud.setMensajeImcActual("Obesidad Clase I");
        }
        else if(imcActual >=35.0 && imcActual < 40.0){
            rangoImcId = cRangoImc.obtenerRangosIMCId(35.0, 40.0);
            eChequeoSalud.setMensajeImcActual("Obesidad Clase II");
        }
        else if(imcActual >=40.0){
            rangoImcId = cRangoImc.obtenerRangosIMCId(40.0, 50.0);
            eChequeoSalud.setMensajeImcActual("Obesidad Clase III");
        }

        eChequeoSalud.setRangoImcId(String.valueOf(rangoImcId));

        //GUARDANDO CHEQUEO DE SALUD
        cChequeoSalud = new ControladorChequeoSalud(this.getContext());
        int chequeoSaludId = (int) cChequeoSalud.crear(eChequeoSalud);

        //GUARDANDO REGISTRO DE RITMO CARDIACO
        eRitmoCardiaco = new RegistroRitmoCardiaco();
        eRitmoCardiaco.setBpm(ritmoCardiaco);
        eRitmoCardiaco.setChequeoSaludId(chequeoSaludId);
        cRitmoCardiaco = new ControladorRegistroRitmoCardiaco(this.getContext());
        cRitmoCardiaco.crear(eRitmoCardiaco);

        //GUARDANDO REGISTRO HIDRATACION
        eHidratacion = new RegistroHidratacion();
        eHidratacion.setConsumoAgua(consumoAgua);
        eHidratacion.setChequeoSaludId(chequeoSaludId);
        cRegistroHidratacion = new ControladorRegistroHidratacion(this.getContext());
        cRegistroHidratacion.crear(eHidratacion);

        //GUARDANDO REGISTRO PRESION ARTERIAL
        ePresionArterial = new RegistroPresionArterial();
        ePresionArterial.setSistolica(sistolica);
        ePresionArterial.setDiastolica(diastolica);
        ePresionArterial.setChequeoSaludId(chequeoSaludId);
        cPresionArterial = new ControladorRegistroPresionArterial(this.getContext());
        cPresionArterial.crear(ePresionArterial);

        //GENERANDO DIETA ALIMENTICIA
        DietaAlimenticia dietaAlimenticia = new DietaAlimenticia(5, 1000, chequeoSaludId);
        cDietaAlimenticia = new ControladorDietaAlimenticia(this.getContext());
        cDietaAlimenticia.asignarDietaAlimenticia(dietaAlimenticia);

        //MOSTRAS MENSAJE
        CharSequence text = "Su indice de masa corporal es "+imcActual+ ". \n\nUsted tiene "+eChequeoSalud.getMensajeImcActual();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(this.getContext(), text, duration);
        toast.show();
    }
}