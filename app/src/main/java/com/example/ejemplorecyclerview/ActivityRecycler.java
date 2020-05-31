package com.example.ejemplorecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity principal de la aplicación. Contiene el RecyclerView con los datos de los hospitales
 * e implementa la interfaz personalizada del adaptador
 *
 * @see Hospital
 * @see Adaptdor
 * @see Adaptdor.MyHolder
 * @see Adaptdor.OnClickCustom
 * @see Adaptdor.OnClickCustom#click(int)
 */
public class ActivityRecycler extends AppCompatActivity implements Adaptdor.OnClickCustom {

    /**
     * RecyclerView con los datos de los hospitales
     *
     * @see Hospital
     * @see Adaptdor
     */
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lm;
    private RecyclerView.Adapter adapter;

    /**
     * TextView con la palabra Recomendados
     */
    private TextView lblRecomendados;

    List<Hospital> listaHospitales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarVista();
    }

    /**
     * Carga los elementod de la vista
     */
    private void cargarVista() {
        //Inicializo el RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        //Indico que el número de hospitales puede variar
        recyclerView.setHasFixedSize(false);

        //Inicializo y hago un set del LayoutManager del RecyclerView
        lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);

        //Creo los hospitales
        Hospital h1 = new Hospital("Puerta de Hierro", 10, 200, 50, 3);
        Hospital h2 = new Hospital("Hospital Univeritario de León", 1200, 30000, 700, 2);
        Hospital h3 = new Hospital("Gregorio Marañón", 120, 523, 420, 1000);
        Hospital h4 = new Hospital("Gran Hospital de Boadilla", 2, 5, 3, -1);
        Hospital h5 = new Hospital("12 de Octubre", 12, 12, 12, 12);

        //Creo la lista de hospitales
        listaHospitales = new ArrayList<>();
        //Añado los hospitales a la lista
        listaHospitales.add(h1);
        listaHospitales.add(h2);
        listaHospitales.add(h3);
        listaHospitales.add(h4);
        listaHospitales.add(h5);
        listaHospitales.add(h5);
        listaHospitales.add(h5);
        listaHospitales.add(h5);
        listaHospitales.add(h5);

        //Creo el adaptador y hago un set del adaptador en el RecyclerView
        adapter = new Adaptdor(listaHospitales, this);
        recyclerView.setAdapter(adapter);

        //Inicializo el TextView
        lblRecomendados = findViewById(R.id.lblRecomendados);
        //Cambio la tipografía a negrita
        lblRecomendados.setTypeface(lblRecomendados.getTypeface(), Typeface.BOLD);
    }

    /**
     * Selecciono el hospital y le paso su nombre al ActivityHospital
     *
     * @param position posición en del elemento seleccionado
     * @see ActivityHospital
     * @see Hospital
     */
    @Override
    public void click(int position) {
        Hospital h = listaHospitales.get(position);
        Intent inte = new Intent(this, ActivityHospital.class);
        inte.putExtra("nombre", h.getNombre());
        startActivity(inte);
    }
}
