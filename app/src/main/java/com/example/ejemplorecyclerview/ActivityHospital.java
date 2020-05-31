package com.example.ejemplorecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Muestra el nombre del hospital seleccionado en el ActivityRecycler
 *
 * @author Ricardo Bordería Pi
 * @see Hospital
 * @see ActivityRecycler
 */
public class ActivityHospital extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        TextView lblNombre = findViewById(R.id.lblNombreHospital);
        String nombre = getIntent().getStringExtra("nombre");
        lblNombre.setText(nombre);
    }
}
