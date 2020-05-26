package com.example.ejemplorecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
