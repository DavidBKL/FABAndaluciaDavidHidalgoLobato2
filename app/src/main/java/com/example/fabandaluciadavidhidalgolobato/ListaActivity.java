package com.example.fabandaluciadavidhidalgolobato;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.AdapterView;
import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> listaItems;
    private MyAdapter adapter; // El adaptador que usaremos para mostrar los items

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        // Inicializar el RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear un ArrayList con algunos elementos de ejemplo
        listaItems = new ArrayList<>();
        listaItems.add("Elemento 1");
        listaItems.add("Elemento 2");
        listaItems.add("Elemento 3");
        listaItems.add("Elemento 4");

        // Configurar el adaptador con el ArrayList
        adapter = new MyAdapter(listaItems, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Aquí manejamos la pulsación corta: ir a la ventana detalle
                String item = listaItems.get(position);
                // Aquí puedes usar el Intent para pasar el item a la nueva actividad de detalle
                Toast.makeText(ListaActivity.this, "Elemento seleccionado: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                // Aquí manejamos la pulsación larga: mostrar el menú de opciones
                showDeleteConfirmationDialog(position);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void showDeleteConfirmationDialog(final int position) {
        new AlertDialog.Builder(this)
                .setMessage("¿Estás seguro de que deseas eliminar este elemento?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Eliminar el elemento de la lista y actualizar el RecyclerView
                        listaItems.remove(position);
                        adapter.notifyItemRemoved(position);
                        Toast.makeText(ListaActivity.this, "Elemento eliminado", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
