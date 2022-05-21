package com.ues.saludapp.actividadFisica;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.ues.saludapp.R;
import com.ues.saludapp.databinding.FragmentActividadFisicaBinding;

import java.util.ArrayList;
import java.util.HashMap;

import control.ControladorRegistroActividadFisicaDiaria;

public class ActividadFisicaFragment extends Fragment {
    private FragmentActividadFisicaBinding  binding;


    ControladorRegistroActividadFisicaDiaria cActividadFisica; //DBHelper mydb;
    ArrayList<HashMap<String, String>> listaActual = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> listaSiguiente = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> listaFutura = new ArrayList<HashMap<String, String>>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentActividadFisicaBinding.inflate(inflater, container, false);
        //ACCION CUANDO PRESIONAN EL BOTON ATRAS
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavHostFragment.findNavController(ActividadFisicaFragment.this).navigate(R.id.action_crearRutinaEjercicioFragment_to_menuInicio);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(callback);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cActividadFisica = new ControladorRegistroActividadFisicaDiaria(getContext());

        binding.btnAgregarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_crearRutinaEjercicioFragment_to_actividadFisicaCrearActualizarFragment);
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_crearRutinaEjercicioFragment_to_actividadFisicaCrearActualizarFragment);
            }
        });
    }

    /*****/

    /*public void openAddModifyTask(View view) {
        startActivity(new Intent(getActivity(), ActividadFisicaCrearActualizarFragment.class));
    }*/

    @Override
    public void onResume() {
        super.onResume();
        cargarDatos();
    }


    public void cargarDatos() {
        //mydb = new DBHelper(this);

        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                obtenerDatosDesdeBD();
            }
        });
    }

    public void obtenerDatosDesdeBD() {
        listaActual.clear();
        listaSiguiente.clear();
        listaFutura.clear();

        Cursor actividadesActuales = cActividadFisica.getObtenerActvidadActual();
        Cursor actividadesSiguientes = cActividadFisica.getObtenerActvidadSiguiente() ;
        Cursor actividadesProximas = cActividadFisica.getObtenerActividadFisicaFutura();

        cargarDatosEnLista(actividadesActuales, listaActual);
        cargarDatosEnLista(actividadesSiguientes, listaSiguiente);
        cargarDatosEnLista(actividadesProximas, listaFutura);


        if (listaActual.isEmpty() && listaSiguiente.isEmpty() && listaFutura.isEmpty()) {
            binding.contenedorSinActividades.setVisibility(View.VISIBLE);
            binding.scrollView.setVisibility(View.GONE);
        } else {
            binding.contenedorSinActividades.setVisibility(View.GONE);
            binding.scrollView.setVisibility(View.VISIBLE);

            if (listaActual.isEmpty()) {
                binding.contenedorActividadActual.setVisibility(View.GONE);
            } else {
                binding.contenedorActividadActual.setVisibility(View.VISIBLE);
                cargarListView(binding.listaActividadActual, listaActual);
            }

            if (listaSiguiente.isEmpty()) {
                binding.contenedorActividadSiguiente.setVisibility(View.GONE);
            } else {
                binding.contenedorActividadSiguiente.setVisibility(View.VISIBLE);
                cargarListView(binding.listaActividadSiguiente, listaSiguiente);
            }

            if (listaFutura.isEmpty()) {
                binding.contenedorActividadFutura.setVisibility(View.GONE);
            } else {
                binding.contenedorActividadFutura.setVisibility(View.VISIBLE);
                cargarListView(binding.listaActividadFutura, listaFutura);
            }
        }
    }


    public void cargarDatosEnLista(Cursor cursor, ArrayList<HashMap<String, String>> dataList) {
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {

                HashMap<String, String> mapActividadActual = new HashMap<String, String>();
                mapActividadActual.put("id", cursor.getString(0).toString());
                mapActividadActual.put("actividad", cursor.getString(1).toString());
                mapActividadActual.put("fecha", cursor.getString(2).toString());
                mapActividadActual.put("estado", cursor.getString(3).toString());
                dataList.add(mapActividadActual);
                cursor.moveToNext();
            }
        }
    }

    public void cargarListView(NoScrollListView listView, final ArrayList<HashMap<String, String>> dataList) {
       ActividadFisicaListAdapter adapter = new ActividadFisicaListAdapter(getActivity(), dataList, cActividadFisica);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //PASANDO DATOS AL SIGUIENTE FRAGMENTO
                Bundle bundle = new Bundle();
                bundle.putBoolean("esActualizacion", true);
                bundle.putInt("actividadFisicaDiariaId", Integer.parseInt(dataList.get(+position).get("id")));
                Navigation.findNavController(view).navigate(R.id.action_crearRutinaEjercicioFragment_to_actividadFisicaCrearActualizarFragment, bundle);

                //
                System.out.println("ITEM CLIC: "+dataList.get(+position).get("id"));
            }
        });

    }
}