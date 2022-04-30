package com.ues.saludapp;

import android.annotation.SuppressLint;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import control.ControladorRegistroPresionArterial;
import control.ControladorRegistroRitmoCardiaco;
import entidades.ChequeoSalud;
import entidades.RegistroPresionArterial;
import entidades.RegistroRitmoCardiaco;

public class adaptadorListaChequedosMedicos extends BaseAdapter {

    Context context;
    List<ChequeoSalud> chequeos;
    ControladorRegistroRitmoCardiaco controladorRegistroRitmoCardiaco;
    ControladorRegistroPresionArterial controladorRegistroPresionArterial;
    RegistroRitmoCardiaco registroRitmoCardiaco;
    RegistroPresionArterial registroPresionArterial;
    LayoutInflater inflater;


    public adaptadorListaChequedosMedicos(Context context, List<ChequeoSalud> chequeos1) {
        this.context = context;
        this.chequeos = chequeos1;
        controladorRegistroRitmoCardiaco = new ControladorRegistroRitmoCardiaco(context);
        controladorRegistroPresionArterial = new ControladorRegistroPresionArterial(context);


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
        TextView textViewPresionArterial = itemView.findViewById(R.id.textViewPresionArterial);
        TextView textViewFechaChequeo = itemView.findViewById(R.id.txtFechaChequeo);
        textViewFechaChequeo.setText(chequeos.get(i).getFechaChequeo());
        textViewPeso.setText(  String.valueOf(chequeos.get(i).getPesoActual()) + " kg.");
        textViewAltura.setText(String.valueOf(chequeos.get(i).getAlturaActual()) + " cm.");
        textViewImc.setText(String.valueOf(chequeos.get(i).getValorImcActual()) + " imc.");
        registroRitmoCardiaco = controladorRegistroRitmoCardiaco.buscarPorIdChequeoSalud(chequeos.get(i).getId());
        registroPresionArterial = controladorRegistroPresionArterial.buscarPorIdChequeoSalud(chequeos.get(i).getId());

        if(registroPresionArterial != null){
            textViewPresionArterial.setText(String.valueOf(registroPresionArterial.getSistolica())+"/"+String.valueOf(registroPresionArterial.getDiastolica()));
        }
        else{
            textViewRitmoCardiado.setText(String.valueOf("0/0 "));
        }

        if(registroRitmoCardiaco != null){
            textViewRitmoCardiado.setText(String.valueOf(registroRitmoCardiaco.getBpm())+" bpm.");
        }
        else{
            textViewRitmoCardiado.setText(String.valueOf("0  bpm"));
        }

        ImageView imageViewEdit = itemView.findViewById(R.id.imgEdit);
        Long dias = diasDiferencia(chequeos.get(i).getFechaChequeo().toString());

        if(dias == 0) {
            imageViewEdit.setImageResource(R.drawable.edit);
        }
        else
        {
            imageViewEdit.setImageResource(R.drawable.edit_disable);
        }


        imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //falta validar que solo se pueda editar si el chequeo es menor a 2 dias de haberse creado
                if(dias == 0) {
                    //enviar el id del chequeo
                    Bundle bundle = new Bundle();
                    bundle.putInt("idChequeo",chequeos.get(i).getId());
                    Navigation.findNavController(view).navigate(R.id.action_listaChekeoMedico_to_editarChequeoMedico,bundle);

                }
            }
        });

        return itemView;
    }



    /**
     * obtiene la fecha del sistema
     * @param formato
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String obtenerFechaConFormato(String formato) {
        Calendar calendario = Calendar.getInstance();
        Date date = calendario.getTime();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(formato);
        //sdf.setTimeZone(TimeZone.getTimeZone(zonaHoraria));
        return sdf.format(date);
    }



    /**
     * obtiene la cantidad de dias que han pasado desde la fecha de registro del chequeo
     * @param fechaChequeo
     * @return
     */
    public Long diasDiferencia(String fechaChequeo)
    {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date dateChequeo = formato.parse(fechaChequeo);
            Date dateSistema = formato.parse(obtenerFechaConFormato("yyyy-MM-dd"));
            Long dias = getTimeDistance(dateSistema,dateChequeo);

            return  dias;
        }
        catch (ParseException e) {
            return  null;
        }


    }



    /**
     * ¿Cuántos días entre dos fechas?
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long getTimeDistance(Date beginDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(beginDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, fromCalendar.getMinimum(Calendar.HOUR_OF_DAY));
        fromCalendar.set(Calendar.MINUTE, fromCalendar.getMinimum(Calendar.MINUTE));
        fromCalendar.set(Calendar.SECOND, fromCalendar.getMinimum(Calendar.SECOND));
        fromCalendar.set(Calendar.MILLISECOND, fromCalendar.getMinimum(Calendar.MILLISECOND));

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, fromCalendar.getMinimum(Calendar.HOUR_OF_DAY));
        toCalendar.set(Calendar.MINUTE, fromCalendar.getMinimum(Calendar.MINUTE));
        toCalendar.set(Calendar.SECOND, fromCalendar.getMinimum(Calendar.SECOND));
        toCalendar.set(Calendar.MILLISECOND, fromCalendar.getMinimum(Calendar.MILLISECOND));

        long dayDistance = (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / 86400000;
        dayDistance = Math.abs(dayDistance);

        return dayDistance;
    }



}
