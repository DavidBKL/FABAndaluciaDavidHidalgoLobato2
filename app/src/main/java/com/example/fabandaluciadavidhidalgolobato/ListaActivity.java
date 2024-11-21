package com.example.fabandaluciadavidhidalgolobato;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Item> listaItems;
    private MyAdapter adapter;
    private int selectedItemPosition;
    private boolean isItemSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear la lista de elementos con texto e imágenes
        listaItems = new ArrayList<>();
        listaItems.add(new Item("Fundacion CB Granada", R.drawable.fundacioncbgranada,"El mejor equipo de Granada en la actualidad, cuenta con una amplia cantera, y un equipo en la máxima categoría a nivel de clubs en España, la ACB. Si bien no consigue grandes resultados en esta liga, lleva 4 temporadas consecutivas en la élite con un presupuesto bastante modesto, lo que le convierte en un hueso duro de roer. En los últimos cuatro años, la Fundación CB Granada ha seguido consolidándose como un referente en el baloncesto español, especialmente tras su meteórico ascenso desde categorías inferiores hasta la Liga ACB. Durante este período, destacan los siguientes hitos:\n" +
                "\n" +
                "Liga ACB (2022-2023): La Fundación CB Granada logró ascender a la máxima categoría del baloncesto español en 2022, tras una excelente temporada en la LEB Oro. Su debut en la ACB marcó un hito en su historia, enfrentándose a algunos de los mejores equipos de Europa y demostrando su crecimiento tanto deportivo como institucional\u200B\n" +
                "\n" +
                "Compromiso social y educativo: La Fundación ha mantenido su enfoque en el desarrollo de programas sociales y educativos, fomentando valores como el trabajo en equipo y la superación personal, alineados con su visión más allá del ámbito deportivo. Estos programas han beneficiado a miles de jóvenes en Granada y sus alrededores\u200B\n" +
                "\n" +
                "Alianzas estratégicas: La renovación de su patrocinio con empresas como Cuerva refleja la fortaleza de su modelo de colaboración, basado en valores compartidos y la promoción del baloncesto como motor de impacto positivo en la comunidad\u200B\n" +
                "\n" +
                "Desafíos y oportunidades: En el ámbito deportivo, el equipo ha afrontado retos significativos en la ACB, trabajando para consolidar su posición en una de las ligas más competitivas del mundo. Su progreso refleja una gestión sólida y un compromiso continuo con el crecimiento\u200B"));
        listaItems.add(new Item("Molina Olea CB Costa Motril", R.drawable.cbcostamotril,"El novato de 3ªFEB en Granada, de una ciudad con grandísima tradición de baloncesto, se estrena en Liga EBA tras dar la campanada en Nacional, quedando finalista de la F4 tras una liga casi perfecta." +
                "Su mayor virtud reside en su juego interior, siendo su punto débil la defensa en tiros de 3, donde encajan demasiados puntos. \n\n" + "En este inicio de liga han tenido mala suerte, cosechando derrotas en los últimos minutos de forma ajustada, pero su juego vistoso seguro que pronto trae resultados prometedores." ));

        listaItems.add(new Item("Tu Super CB La Zubia", R.drawable.cblazubia,"Un clásico de 3ªFEB, consolidado en la categoría, llegando incluso a Play-Off en 2 temporadas consecutivas, donde no tuvo oportunidad contra sus rivales de la zona oeste, donde el nivel es claramente superior. \n\n" +
        "Capitaneados por Devin Wright, hijo del exjugador Jimmy Wright, tienen un excelente tiro exterior, además de un juego interior muy versátil, que los convierte en un equipo impredecible."));
        listaItems.add(new Item("CFI Reina Isabel GMASB", R.drawable.gmasb,"Equipo femenino que ha conseguido por primera vez en su historia el ascenso a Liga Femenina 2, tras 3 Play-Off consecutivos.\n\n" +
                "Un equipo fuerte en todas sus líneas, que lleva años demostrando que la categoría Nacional se le queda pequeña, por fin ha llegado donde le corresponde.\n\n" +
                "Comandadas por Antonio Gómez Nieto, ex-entrenador del desaparecido CB Granada, tienen al mejor entrenador posible, con más experiencia que ningún otro en su posición en Andalucía"));

        // Configuración del adaptador con el contexto
        adapter = new MyAdapter(listaItems, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Acción al hacer clic en un item
                Item item = listaItems.get(position);

                // Crear un Intent para abrir DetalleActivity
                Intent intent = new Intent(ListaActivity.this, DetalleActivity.class);
                // Pasar los datos del ítem a DetalleActivity
                intent.putExtra("titulo", item.getTexto());
                intent.putExtra("imagenResId", item.getImagenResId());
                intent.putExtra("resumen", item.getResumen());
                startActivity(intent);  // Iniciar la nueva actividad
            }

            @Override
            public void onItemLongClick(int position) {
                // Acción al hacer clic largo en un item
                selectedItemPosition = position;
                isItemSelected = true;
                invalidateOptionsMenu(); // Invalidamos el menú para que se muestre la barra de acción
            }
        }, this);  // Pasamos el contexto

        recyclerView.setAdapter(adapter);  // Establecer el adaptador al RecyclerView
    }

    // Inflar el menú con la papelera al hacer long-click
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isItemSelected) {
            getMenuInflater().inflate(R.menu.menu_lista, menu);  // Inflar el menú con la papelera si hay un item seleccionado
        }
        return true;
    }

    // Manejar la selección de opciones del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            showDeleteConfirmationDialog(); // Confirmación de eliminación
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Método para mostrar el diálogo de confirmación antes de eliminar un item
    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(ListaActivity.this)
                .setMessage("¿Estás seguro de que deseas eliminar este elemento?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Eliminar el item de la lista
                        listaItems.remove(selectedItemPosition);
                        adapter.notifyItemRemoved(selectedItemPosition); // Notificar al adaptador que se eliminó un ítem
                        isItemSelected = false; // Reseteamos la selección
                        invalidateOptionsMenu(); // Actualizamos el menú
                        Toast.makeText(ListaActivity.this, "Elemento eliminado", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Si se cancela la acción, mostrar un mensaje
                        Toast.makeText(ListaActivity.this, "Acción cancelada", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
