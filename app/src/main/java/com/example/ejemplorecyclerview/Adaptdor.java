package com.example.ejemplorecyclerview;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adaptador del RecyclerView. Se encarga de llenar el RecyclerView.
 */
public class Adaptdor extends RecyclerView.Adapter<Adaptdor.MyHolder> {

    /**
     * Lista de elementos para cargar en el RecyclerView
     *
     * @see Hospital
     */
    private List<Hospital> hospitales;

    /**
     * Interfaz personalizada para detectar el click sobre un elemento de la lista.
     * La funcionalidad se implementa en el activity que contiene el RecyclerView.
     *
     * @see Adaptdor.OnClickCustom
     */
    private OnClickCustom onClickCustom;

    /**
     * ViewHolder del RecylerView. Se encarga de cargar el layout de cada tarjeta el RecyclerView. Contiene todos los elementos de cada tarjeta.
     * Implementa la interfaz View.OcClickListener para detectar la tarjeta escogida.
     * Tiene un atributo del mismo tipo de la interfaz creada para detectar el click en cada tarjeta.
     *
     * @see Adaptdor.OnClickCustom
     * @see Adaptdor
     */
    public static class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * TextView de cada tarjeta
         */
        private TextView lblHospital, lblUCI, lblPlanta, lblUrgencias, lblDisponible;

        /**
         * Interfaz personalizada para detectar el click
         */
        private OnClickCustom onClickCustom;

        public MyHolder(@NonNull View v, OnClickCustom onClickCustom) {
            //Llamo al constructor del padre
            super(v);

            //inicializo los elementos de la vista
            lblHospital = v.findViewById(R.id.lblHospital);

            lblUCI = v.findViewById(R.id.lblUCI);
            lblUrgencias = v.findViewById(R.id.lblurgencias);
            lblPlanta = v.findViewById(R.id.lblPlanta);
            lblDisponible = v.findViewById(R.id.lblDisponible);

            //Añado el OnClickListener, NO el listener personalizado
            v.setOnClickListener(this);

            //Inicializo la interfaz personalizada
            this.onClickCustom = onClickCustom;
        }


        @Override
        public void onClick(View v) {
            //Le paso a la interfaz la posición actual de cada tarjeta. No se implementa el método aquí.
            onClickCustom.click(getAdapterPosition());
        }
    }

    /**
     * Constructor del adaptador en él le pasamos los objetos con los que se van a construir las tarjetas
     *
     * @param hospitales lista de hospitales
     * @see Hospital
     */
    public Adaptdor(List<Hospital> hospitales, OnClickCustom onClickCustom) {
        this.hospitales = hospitales;
        this.onClickCustom = onClickCustom;
    }


    @NonNull
    @Override
    public Adaptdor.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creo un View con el layout personalizado para cada tarjeta
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler, parent, false);

        /**
         * Devuelvo una instancia de la clase personalizada MyHolder con la vista y el atributo de
         * la interfaz personalzada
         *
         * @see Adaptdor.OnClickCustom
         * @see Adaptdor#onClickCustom
         */
        return new MyHolder(v, onClickCustom);
    }

    /**
     * Carga los datos en los elementos de cada tarjeta
     *
     * @param holder   objeto de la clase MyHolder en el que se inicializan los elementos de cada tarjeta
     * @param position posición del List que recorre en ese momento
     * @see Adaptdor#hospitales
     * @see MyHolder
     */
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //Cojo el objeto de la lista en la posición en la que se encuentre el adaptador
        Hospital h = hospitales.get(position);

        //Le doy valores a los elementos de la tarjeta
        holder.lblHospital.setText(h.getNombre());
        holder.lblHospital.setTypeface(holder.lblHospital.getTypeface(), Typeface.BOLD);

        holder.lblUCI.setText("Camas UCI: " + String.valueOf(h.getCamasUCI()));
        holder.lblUrgencias.setText("Camas Urgencias: " + String.valueOf(h.getCamasUrgencias()));
        holder.lblPlanta.setText("Camas Planta: " + String.valueOf(h.getCamasPlanta()));
        holder.lblDisponible.setText("Disponibles: " + String.valueOf(h.getCamasDisponibles()));

        //Pongo el texto en negrita
        holder.lblDisponible.setTypeface(holder.lblDisponible.getTypeface(), Typeface.BOLD);
    }

    /**
     * Cantidad de items que se le pasan al adaptador
     *
     * @return size del List de hospitales
     * @see Adaptdor#hospitales
     */
    @Override
    public int getItemCount() {
        return hospitales.size();
    }

    /**
     * Interfaz personalizada que contiene los métodos a implementar en el
     * activity que contiene el RecyclerView
     *
     * @see ActivityRecycler
     */
    public interface OnClickCustom {

        /**
         * Método que se implementa en el activity que contiene el RecyclerView
         *
         * @param position posición en del elemento seleccionado
         * @see Adaptdor#onClickCustom
         * @see ActivityRecycler#click(int)
         */
        void click(int position);
    }
}
