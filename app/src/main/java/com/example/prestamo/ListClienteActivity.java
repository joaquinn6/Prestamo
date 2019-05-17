package com.example.prestamo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
            public void OnItemClick(final int posicion, long id) {
                if(id==R.id.ibEliminar){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListClienteActivity.this);
                    builder.setTitle("Eliminando Cliente");
                    builder.setMessage("Esta seguro que desea eliminar al usuario "+ Datos.clientes.get(posicion).getNombre());
                    builder.setNegativeButton("NO", null);
                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Datos.clientes.remove(posicion);
                            adapter.notifyDataSetChanged();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }else if(id==R.id.ibEditar){
                    intent[0] =new Intent(ListClienteActivity.this, MainActivity.class);
                    intent[0].putExtra("indice", String.valueOf(posicion));
                    startActivityForResult(intent[0], 1111);
                }else{
                    intent[0] =new Intent(ListClienteActivity.this, VerClienteActivity.class);
                    intent[0].putExtra("indice", String.valueOf(posicion));
                    startActivity(intent[0]);
                }
            }
        };
        adapter=new RVAdapCliente(Datos.clientes, onItemClickListener);
        GridLayoutManager manager = new GridLayoutManager(this,1);
        rvCliente.setLayoutManager(manager);
        rvCliente.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1111){
            if(resultCode==RESULT_OK){

                adapter.notifyDataSetChanged();
            }
        }
    }
}
