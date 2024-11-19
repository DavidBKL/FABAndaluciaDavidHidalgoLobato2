package com.example.fabandaluciadavidhidalgolobato;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText edtNombre, edtEmail, edtEdad;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar los elementos de la interfaz
        edtNombre = findViewById(R.id.edtNombre);
        edtEmail = findViewById(R.id.edtEmail);
        edtEdad = findViewById(R.id.edtEdad);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        // Configurar el listener para el botón de registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edtNombre.getText().toString();
                String email = edtEmail.getText().toString();
                String edadStr = edtEdad.getText().toString();

                // Validación de campos vacíos
                if (nombre.isEmpty() || email.isEmpty() || edadStr.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "No puedes dejar campos vacíos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validación de la edad
                try {
                    int edad = Integer.parseInt(edadStr); // Intentamos convertir la edad a número
                    if (edad < 18) {
                        // Si la edad es menor a 18
                        Toast.makeText(RegistroActivity.this, "Tienes que ser mayor de edad para poder registrarte", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    // Si no es un número válido (es decir, contiene letras u otros caracteres no numéricos)
                    Toast.makeText(RegistroActivity.this, "Por favor, introduce una edad válida", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Si todo es correcto
                Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
