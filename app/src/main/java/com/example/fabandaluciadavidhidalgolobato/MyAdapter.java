package com.example.fabandaluciadavidhidalgolobato;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Item> listaItems;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemLongClick(int position);
    }

    public MyAdapter(ArrayList<Item> listaItems, OnItemClickListener onItemClickListener, Context context) {
        this.listaItems = listaItems;
        this.onItemClickListener = onItemClickListener;
        this.context = context;  // Guardamos el contexto
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarjeta_equipos, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item item = listaItems.get(position);
        holder.textView.setText(item.getTexto());
        holder.imageView.setImageResource(item.getImagenResId());
    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Item item = listaItems.get(getAdapterPosition());

                    // Crear un Intent para abrir DetalleActivity
                    Intent intent = new Intent(context, DetalleActivity.class);
                    intent.putExtra("TITULO", item.getTexto());
                    intent.putExtra("IMAGEN", item.getImagenResId());
                    intent.putExtra("RESUMEN", item.getResumen());

                    // Iniciar la actividad DetalleActivity
                    context.startActivity(intent);


                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    Activity activity = (Activity) context;
                    activity.startActionMode(new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                            // Inflar el menú de acción
                            MenuInflater inflater = activity.getMenuInflater();
                            inflater.inflate(R.menu.menu_opciones, menu);
                            mode.setTitle("Acción de ítem");  // Título del ActionMode
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                            return false;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                            if (item.getItemId() == R.id.opcion_1) {  // Eliminar ítem
                                new android.app.AlertDialog.Builder(context)
                                        .setTitle("Confirmación de eliminación")
                                        .setMessage("¿Estás seguro de que quieres eliminar este ítem?")
                                        .setPositiveButton("Sí", (dialog, which) -> {
                                            // Eliminar el ítem de la lista
                                            int position = getAdapterPosition();
                                            if (position != RecyclerView.NO_POSITION) {
                                                listaItems.remove(position);
                                                notifyItemRemoved(position); // Notificar al RecyclerView para actualizar la vista
                                                Toast.makeText(context, "Ítem eliminado", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .setNegativeButton("No", (dialog, which) -> {
                                            Toast.makeText(context, "Acción cancelada", Toast.LENGTH_SHORT).show();
                                        })
                                        .show();
                                mode.finish();
                                return true;
                            }
                            return false;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode mode) {

                        }
                    });
                    return true;
                }
            });
        }
    }
}
