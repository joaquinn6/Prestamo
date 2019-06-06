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
import com.example.prestamo.obj.Pago;

import java.util.List;

public class AdapPago extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Pago> pagoList;


    public AdapPago(@NonNull Context context, int resource, @NonNull List<Pago> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.pagoList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v =inflater.inflate(this.resource, parent, false);
        TextView tvNumero=v.findViewById(R.id.tvNumero);
        TextView tvPago = v.findViewById(R.id.tvPago);


        tvNumero.setText(String.valueOf(position+1)+".");
        tvPago.setText(String.valueOf(pagoList.get(position).getMonto()));

        return v;
    }
}
