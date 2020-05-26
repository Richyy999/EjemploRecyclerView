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

    private OnClickCustom onClickCustom;

    /**
     * ViewHolder del RecylerView. Se encarga de cargar el layout de cada tarjeta el RecyclerView. Contiene todos los elementos de cada tarjeta.
     *
     * @see Adaptdor
     */
    public static class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * TextView de cada tarjeta
         */
        private TextView lblHospital, lblUCI, lblPlanta, lblUrgencias, lblDisponible;

        private OnClickCustom onClickCustom;

        public MyHolder(@NonNull View v, OnClickCustom onClickCustom) {
            super(v);

            lblHospital = v.findViewById(R.id.lblHospital);

            lblUCI = v.findViewById(R.id.lblUCI);
            lblUrgencias = v.findViewById(R.id.lblurgencias);
            lblPlanta = v.findViewById(R.id.lblPlanta);
            lblDisponible = v.findViewById(R.id.lblDisponible);

            v.setOnClickListener(this);

            this.onClickCustom = onClickCustom;
        }

        @Override
        public void onClick(View v) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler, parent, false);
        return new MyHolder(v, onClickCustom);
    }

    /**
     * Carga los datos en los elementos de cada ventana
     *
     * @param holder   objeto de la clase MyHolder en el que se inicializan los elementos de cada tarjeta
     * @param position posición del List qe recorre en ese momento
     * @see Adaptdor#hospitales
     * @see MyHolder
     */
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Hospital h = hospitales.get(position);

        holder.lblHospital.setText(h.getNombre());
        holder.lblHospital.setTypeface(holder.lblHospital.getTypeface(), Typeface.BOLD);

        holder.lblUCI.setText("Camas UCI: " + String.valueOf(h.getCamasUCI()));
        holder.lblUrgencias.setText("Camas Urgencias: " + String.valueOf(h.getCamasUrgencias()));
        holder.lblPlanta.setText("Camas Planta: " + String.valueOf(h.getCamasPlanta()));
        holder.lblDisponible.setText("Disponibles: " + String.valueOf(h.getCamasDisponibles()));

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

    public interface OnClickCustom {
        void click(int position);
    }
}
