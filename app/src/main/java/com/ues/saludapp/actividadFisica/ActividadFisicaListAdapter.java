package com.ues.saludapp.actividadFisica;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ues.saludapp.R;

import java.util.ArrayList;
import java.util.HashMap;

import control.ControladorRegistroActividadFisicaDiaria;


public class ActividadFisicaListAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> infoActividad;
    private ControladorRegistroActividadFisicaDiaria cActividadFisicaDiaria;

    public ActividadFisicaListAdapter(Activity a, ArrayList<HashMap<String, String>> d, ControladorRegistroActividadFisicaDiaria afd) {
        activity = a;
        infoActividad = d;
        cActividadFisicaDiaria = afd;
    }

    public int getCount() {
        return infoActividad.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ActividadFisicaListViewHolder holder = null;
        if (convertView == null) {
            holder = new ActividadFisicaListViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.fragment_actividad_fisica_item, parent, false);
            holder.txtActividadFisica = convertView.findViewById(R.id.txtDescripcionActividad);
            holder.chMarcarActividad = convertView.findViewById(R.id.chMarcarActividad);
            convertView.setTag(holder);
        } else {
            holder = (ActividadFisicaListViewHolder) convertView.getTag();
        }


        final HashMap<String, String> actividadFisica = infoActividad.get(position);
        final ActividadFisicaListViewHolder tmpHolder = holder;

        holder.txtActividadFisica.setId(position);
        holder.chMarcarActividad.setId(position);

        try {


            holder.chMarcarActividad.setOnCheckedChangeListener(null);
            if (actividadFisica.get("estado").contentEquals("F")) {
                holder.txtActividadFisica.setText(Html.fromHtml("<strike>" + actividadFisica.get("actividad") + "</strike>"));
                holder.chMarcarActividad.setChecked(true);
            } else {
                holder.txtActividadFisica.setText(actividadFisica.get("actividad"));
                holder.chMarcarActividad.setChecked(false);
            }

            holder.chMarcarActividad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    cActividadFisicaDiaria = new ControladorRegistroActividadFisicaDiaria(parent.getContext());
                    if (isChecked) {
                        cActividadFisicaDiaria.actualizarEstado(actividadFisica.get("id"), "F");
                        //database.updateTaskStatus(singleTask.get("id"), 1);
                        tmpHolder.txtActividadFisica.setText(Html.fromHtml("<strike>" + actividadFisica.get("actividad") + "</strike>"));
                    } else {
                        //database.updateTaskStatus(singleTask.get("id"), 0);
                        cActividadFisicaDiaria.actualizarEstado(actividadFisica.get("id"), "P");
                        tmpHolder.txtActividadFisica.setText(actividadFisica.get("actividad"));
                    }

                }
            });


        } catch (Exception e) {
        }
        return convertView;
    }
}

class ActividadFisicaListViewHolder {
    TextView txtActividadFisica;
    CheckBox chMarcarActividad;
}