package com.example.prestamo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.prestamo.R;
import com.example.prestamo.obj.Prestamos;

import java.util.List;

public class AdapPrestamos extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Prestamos> prestamosList;

    public AdapPrestamos(@NonNull Context context, int resource, @NonNull List<Prestamos> prestamosList) {
        super(context, resource, prestamosList);
        this.context = context;
        this.resource = resource;
        this.prestamosList = prestamosList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v =inflater.inflate(this.resource, parent, false);
        TextView tvNombre = v.findViewById(R.id.tvNombre);
        TextView tvMonto = v.findViewById(R.id.tvMonto);
        TextView tvCuotas = v.findViewById(R.id.tvCuotas);

        tvNombre.setText(prestamosList.get(position).getNombreCliente());
        tvMonto.setText(prestamosList.get(position).getMonto().toString());
        tvCuotas.setText(String.valueOf(prestamosList.get(position).getPlazo()));


        return v;
    }
}
