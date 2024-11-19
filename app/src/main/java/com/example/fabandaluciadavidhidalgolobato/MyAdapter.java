package com.example.fabandaluciadavidhidalgolobato;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<String> items;  // Lista de elementos a mostrar
    private OnItemClickListener listener;  // Listener para manejar los clics

    // Constructor del adaptador
    public MyAdapter(ArrayList<String> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    // Este método infla el layout de cada item
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar el layout de un item (puedes personalizar este layout si quieres)
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder(view);
    }

    // Este método configura el contenido de cada item en el RecyclerView
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // Asignar el texto de cada item a su respectiva vista
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        // Retorna el número de elementos en la lista
        return items.size();
    }

    // ViewHolder para manejar las vistas de cada item
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            // Obtener el TextView del item
            textView = itemView.findViewById(android.R.id.text1);
        }

        public void bind(final String item, final OnItemClickListener listener) {
            // Configurar el texto para este item
            textView.setText(item);

            // Manejar clic corto
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

            // Manejar clic largo
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClick(getAdapterPosition());
                    return true; // Indicar que se manejó el clic largo
                }
            });
        }
    }

    // Interfaz para manejar los clics
    public interface OnItemClickListener {
        void onItemClick(int position);       // Para manejar la pulsación corta
        void onItemLongClick(int position);   // Para manejar la pulsación larga
    }
}
