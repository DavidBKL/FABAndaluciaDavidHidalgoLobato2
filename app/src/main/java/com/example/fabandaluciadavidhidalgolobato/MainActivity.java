package com.example.fabandaluciadavidhidalgolobato;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnRegistro;
    private Button btnLista;
    private ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de elementos de la interfaz
        imgLogo = findViewById(R.id.imgLogo);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnLista = findViewById(R.id.btnLista);

        // Configurar listener para el botón de registro
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegistro = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intentRegistro);
            }
        });

        // Configurar listener para el botón de lista
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLista = new Intent(MainActivity.this, ListaActivity.class);
                startActivity(intentLista);
            }
        });
    }
}
