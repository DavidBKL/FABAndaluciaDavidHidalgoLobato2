package com.example.fabandaluciadavidhidalgolobato;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleActivity extends AppCompatActivity {

    private TextView tituloDetalle;
    private ImageView imagenDetalle;
    private TextView resumenDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        // Inicializar vistas
        tituloDetalle = findViewById(R.id.tituloDetalle);
        imagenDetalle = findViewById(R.id.imagenDetalle);
        resumenDetalle = findViewById(R.id.resumenDetalle);

        // Obtener los datos del Intent
        String titulo = getIntent().getStringExtra("titulo");
        int imagenResId = getIntent().getIntExtra("imagenResId", 0);
        String resumen = getIntent().getStringExtra("resumen");

        // Verificar los valores recibidos
        Log.d("DetalleActivity", "Título: " + titulo);
        Log.d("DetalleActivity", "Imagen Res ID: " + imagenResId);
        Log.d("DetalleActivity", "Resumen: " + resumen);

        // Establecer los datos en las vistas
        if (titulo != null && !titulo.isEmpty()) {
            tituloDetalle.setText(titulo);
        } else {
            tituloDetalle.setText("Título no disponible");
        }

        if (imagenResId != 0) {
            imagenDetalle.setImageResource(imagenResId);
        }
        if (resumen != null && !resumen.isEmpty()) {
            resumenDetalle.setText(resumen);
        } else {
            resumenDetalle.setText("Resumen no disponible");
        }
    }
}
