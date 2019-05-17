package com.example.prestamo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class ListClienteActivity extends AppCompatActivity {

    private RVAdapCliente adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cliente);
        final Intent[] intent = new Intent[1];
        RecyclerView rvCliente = findViewById(R.id.rvClientes);
        RVAdapCliente.OnItemClickListener onItemClickListener = new RVAdapCliente.OnItemClickListener() {
            @Override
            public void OnItemClick(int posicion) {
                intent[0] =new Intent(ListClienteActivity.this, VerClienteActivity.class);
                intent[0].putExtra("indice", String.valueOf(posicion));
                startActivity(intent[0]);
            }
        };
        adapter=new RVAdapCliente(Datos.clientes, onItemClickListener);
        GridLayoutManager manager = new GridLayoutManager(this,1);
        rvCliente.setLayoutManager(manager);
        rvCliente.setAdapter(adapter);

    }
}
