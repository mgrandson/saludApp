package com.ues.saludapp.actividadFisica;


import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ues.saludapp.R;
import com.ues.saludapp.databinding.FragmentActividadFisicaCrearActualizarBinding;


import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import control.ControladorChequeoSalud;
import control.ControladorRegistroActividadFisicaDiaria;
import entidades.ChequeoSalud;
import entidades.Deporte;
import entidades.RegistroActividadFisicaDiaria;

public class ActividadFisicaCrearActualizarFragment extends Fragment {
    FragmentActividadFisicaCrearActualizarBinding binding;
    ControladorRegistroActividadFisicaDiaria cActividadFisica;
    ControladorChequeoSalud cChequeoSalud;
    Calendar calendar;

    Boolean esActualizacion = false;
    String actividadFisicaDiariaId;
    int detallePorFactorId;
    int chequesoSaludId;
    ArrayAdapter<Deporte> arrayAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentActividadFisicaCrearActualizarBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);

        //INICIAR CONTRALADORES PARA LA CONECTARSE A LA BASE DE DATOS
        cActividadFisica = new ControladorRegistroActividadFisicaDiaria(getContext());
        cChequeoSalud = new ControladorChequeoSalud(getContext());

        //OBTENER EL CHEQUEO DE SALUD PENDIENTE
        ChequeoSalud cs = cChequeoSalud.consultarPorEstado("P");
        chequesoSaludId = cs.getId();

        //INICIAR CALENDARIO QUE USAREMOS PARA EL DATAPICKER
        calendar = new GregorianCalendar();

        //SE UTILIZA LA CLASE DEPORTE AUNQUE EN REALIDAD SE GUARDA UN REGISTRO DE ACTIVIDAD FISICA
        arrayAdapter = new ArrayAdapter<>(getContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                actividadesFisicas());

        //ASIGNAMOS LA FECHA DEL CALENDARIO
        binding.txtFechaSeleccionada.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy").format(calendar.getTime()));

        //INICIAMOS LA LISTA DE ACTIVIDADES (DEPORTES)
        binding.spActividadFisicas.setAdapter(arrayAdapter);
        binding.spActividadFisicas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //OBTENEMOS LOS DATOS DE LA ACTIVIDAD SELECCIONADA
                detallePorFactorId = ((Deporte) adapterView.getSelectedItem()).getId();
                String descripcion = ((Deporte) adapterView.getSelectedItem()).getDeporte();

                //SE AGREGA COMO CONFIRMACION
                binding.txtDescripcionNuevaActividad.setText(descripcion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //RECUPERANDO DATOS DEL FRAGMENT ANTERIOR
        if(getArguments().getBoolean("esActualizacion")){
            esActualizacion = true;
            actividadFisicaDiariaId = String.valueOf(getArguments().getInt("actividadFisicaDiariaId"));

            //SI SE TRATA DE UNA EDICION ENTONCES CARGAMOS LOS DATOS
            iniciarActualizacion();

            //TEST
            System.out.println(" ARGUMENTO "+getArguments().getInt("actividadFisicaDiariaId"));
            System.out.println("ARGUMENTO: VERDADERO");
            System.out.println("ID ACTIVIDAD RECUPERADO: "+ actividadFisicaDiariaId);
        }
        //FIN RECUPERANDO DATOS DEL FRAGMENT ANTERIOR

        binding.btnGuardarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarActividadFisica();
                Navigation.findNavController(view).navigate(R.id.action_actividadFisicaCrearActualizarFragment_to_crearRutinaEjercicioFragment);
                //action_menuInicio_to_crearRutinaEjercicioFragment
                //action_crearRutinaEjercicioFragment_to_menuInicio
            }
        });

        binding.btnCancelarCreacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_actividadFisicaCrearActualizarFragment_to_crearRutinaEjercicioFragment);
            }
        });

        binding.layoutEliminarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarActividadFisica();
                Navigation.findNavController(view).navigate(R.id.action_actividadFisicaCrearActualizarFragment_to_crearRutinaEjercicioFragment);
            }
        });

        binding.layoutVerFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialogoFecha = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                                calendar = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                                binding.txtFechaSeleccionada.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy").format(calendar.getTime()));
                                //TEST
                                System.out.println("FECHA: ");
                                System.out.println(new SimpleDateFormat("EEEE, dd MMMM yyyy").format(calendar.getTime()));
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialogoFecha.show();
            }
        });

    }

    public void iniciarActualizacion() {

        binding.toolbarTituloNuevaActividad.setText("Actualizar Actividad");
        binding.btnGuardarActividad.setText("Actualizar");

        LinearLayout elimininarActividad = binding.layoutEliminarActividad;
        elimininarActividad.setVisibility(View.VISIBLE);
        System.out.println("ACTIVIDAD ACTUALIZAR ID: "+actividadFisicaDiariaId);
        Cursor cursorActividad = cActividadFisica.getObtenerActividad(actividadFisicaDiariaId);
        if (cursorActividad != null) {
            cursorActividad.moveToFirst();
            binding.txtDescripcionNuevaActividad.setText(cursorActividad.getString(1));

            //OBTENIENDO LA POSICION DE LA ACTIVIDAD A MODIFICAR
            for (int i = 0; i< arrayAdapter.getCount(); i++){
                if(Integer.valueOf(cursorActividad.getString(4)) == arrayAdapter.getItem(i).getId()){
                    int posicionSpinner = arrayAdapter.getPosition(arrayAdapter.getItem(i));
                    //INDICAMOS LA POSICION DE LA ACTIVIDAD A EDITAR
                    binding.spActividadFisicas.setSelection(posicionSpinner);
                    //TEST
                    System.out.println("ACTIVIDAD BUSCADA ID: "+cursorActividad.getString(4)+" ID:::::::: "+arrayAdapter.getItem(i).getId()+" ACTIVIDAD::::: "+arrayAdapter.getItem(i).getDeporte());
                    break;
                }
            }
            SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                calendar.setTime(iso8601Format.parse(cursorActividad.getString(2)));
            } catch (ParseException e) {
                System.out.println("iniciarActualizacion(): "+e.toString());
            }
            //ESTABLECEMOS LA FECHA DE LA ACTIVIDAD A EDITAR
            binding.txtFechaSeleccionada.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy").format(calendar.getTime()));
        }
    }

    public void guardarActividadFisica(){

        //VERIFICAR QUE EXISTA ACTIVIDAD A GUARDAR
        if (binding.txtDescripcionNuevaActividad.getText().toString().trim().length() > 0) {
            if (esActualizacion) {
                //ENTRA AQUI SI SE TRATA DE UNA ACTUALIZACION DE ACTIVIDAD
                RegistroActividadFisicaDiaria af = new RegistroActividadFisicaDiaria();
                af.setId(Integer.valueOf(actividadFisicaDiariaId));
                af.setFechaActividad(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
                af.setEstado("P");
                af.setDiaSemanaId(calendar.get(Calendar.DAY_OF_WEEK));
                af.setDetalleDeportePorFactorId(detallePorFactorId);
                af.setChequeoSaludId(chequesoSaludId);

                //ACTUALIZAR ACTIVIDAD FISICA
                cActividadFisica.actualizar(af);
                Toast.makeText(getContext(), "Actividad actualizada...", Toast.LENGTH_SHORT).show();
                esActualizacion = false;

            } else {
                //ENTRA AQUI SI SE TRATA DE UNA ACTIVIDAD NUEVA
                RegistroActividadFisicaDiaria af = new RegistroActividadFisicaDiaria();

                af.setFechaActividad(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
                af.setEstado("P");
                af.setDiaSemanaId(calendar.get(Calendar.DAY_OF_WEEK)); //CONSIDERANDO QUE EL ID DEL DIA ES IGUAL AL NUMERO ASIGNADO POR JAVA
                af.setDetalleDeportePorFactorId(detallePorFactorId);
                af.setChequeoSaludId(chequesoSaludId);

                //AGREGAR NUEVA ACTIVIDAD FISICA
                cActividadFisica.crear(af);
                Toast.makeText(getContext(), "Actividad agregada...", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Elija una actividad...", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Deporte> actividadesFisicas(){
        Cursor cursor = cActividadFisica.getActividadesFisicas(1);
        List<Deporte> actividades = new ArrayList<>();
        if(cursor!= null){
            while(cursor.moveToNext()){
                Deporte deporte = new Deporte(cursor.getInt(0), cursor.getString(1));
                actividades.add(deporte);
                //TEST
                System.out.println(cursor.getString(1));
                System.out.println(deporte.toString());

            }
        }
        return actividades;
    }

    public void borrarActividadFisica() {
        cActividadFisica.eliminar(new RegistroActividadFisicaDiaria(Integer.valueOf(actividadFisicaDiariaId)));
        Toast.makeText(getContext(), "Actividad eliminada...", Toast.LENGTH_SHORT).show();
    }
}