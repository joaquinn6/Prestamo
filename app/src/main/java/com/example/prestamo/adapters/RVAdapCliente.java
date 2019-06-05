package com.example.prestamo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.prestamo.R;
import com.example.prestamo.obj.Cliente;

import java.util.List;


public class RVAdapCliente extends RecyclerView.Adapter<RVAdapCliente.ClienteHolder> {

    List<Cliente> clienteList;

    private final OnItemClickListener onItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(int posicion, long id);
    }

    public RVAdapCliente(List<Cliente> clienteList, OnItemClickListener onItemClickListener){
        this.clienteList = clienteList;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public ClienteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_cliente, viewGroup, false);
        ClienteHolder clienteHolder = new ClienteHolder(view);
        return clienteHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteHolder clienteholder, int position) {
        clienteholder.tvNombre.setText(clienteList.get(position).getNombre());
        clienteholder.tvApellido.setText(clienteList.get(position).getApelldio());
    }

    @Override
    public int getItemCount() {
        return clienteList.size();
    }


    public class ClienteHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private TextView tvNombre;
        private TextView tvApellido;
        private ImageButton ibEditar;
        private ImageButton ibEliminar;
        private  ImageButton ibMostrarPrestamos;

        public ClienteHolder(@NonNull View itemView){
            super(itemView);

            tvNombre=itemView.findViewById(R.id.tvNombre);
            tvApellido=itemView.findViewById(R.id.tvApellido);
            ibEditar=itemView.findViewById(R.id.ibEditar);
            ibEliminar=itemView.findViewById(R.id.ibEliminar);
            ibMostrarPrestamos=itemView.findViewById(R.id.ibVerPRestamos);

            itemView.setOnClickListener(this);

            ibEliminar.setOnClickListener(this);
            ibEditar.setOnClickListener(this);
            ibMostrarPrestamos.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.OnItemClick(getAdapterPosition(), view.getId());
        }
    }
}
