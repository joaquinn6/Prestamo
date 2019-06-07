package com.example.prestamo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.prestamo.adapters.RVAdapCliente;
import com.example.prestamo.db.DbPrestamos;
import com.example.prestamo.obj.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ListClienteActivity extends AppCompatActivity {

    private RVAdapCliente adapter;
    private List<Cliente> clienteList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cliente);
        final Intent[] intent = new Intent[1];
        RecyclerView rvCliente = findViewById(R.id.rvClientes);

        clienteList.addAll(DbPrestamos.getAppDatabase(this).clienteDao().MostrarClientes());

        RVAdapCliente.OnItemClickListener onItemClickListener = new RVAdapCliente.OnItemClickListener() {
            @Override
            public void OnItemClick(final int posicion, long id) {
                if(id==R.id.ibEliminar){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListClienteActivity.this);
                    builder.setTitle("Eliminando Cliente");
                    builder.setMessage("Esta seguro que desea eliminar al usuario "+ clienteList.get(posicion).getNombre());
                    builder.setNegativeButton("NO", null);
                    builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try{
                                DbPrestamos.getAppDatabase(ListClienteActivity.this).clienteDao().Borrar(clienteList.get(posicion));
                                clienteList.remove(posicion);
                                adapter.notifyDataSetChanged();
                            }catch (SQLiteConstraintException e){
                                Toast.makeText(ListClienteActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }else if(id==R.id.ibEditar){
                    intent[0] =new Intent(ListClienteActivity.this, MainActivity.class);
                    intent[0].putExtra("indice", clienteList.get(posicion).getCedula());
                    intent[0].putExtra("lugar", posicion);
                    startActivityForResult(intent[0], 1111);
                }else if(id==R.id.ibVerPRestamos){
                    intent[0] =new Intent(ListClienteActivity.this, ListPrestamosActivity.class);
                    intent[0].putExtra("indice", clienteList.get(posicion).getCedula());
                    startActivity(intent[0]);
                }else{
                    intent[0] =new Intent(ListClienteActivity.this, VerClienteActivity.class);
                    intent[0].putExtra("indice", clienteList.get(posicion).getCedula());
                    startActivity(intent[0]);
                }
            }
        };
        adapter=new RVAdapCliente(clienteList, onItemClickListener);
        GridLayoutManager manager = new GridLayoutManager(this,1);
        rvCliente.setLayoutManager(manager);
        rvCliente.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1111){
            if(resultCode==RESULT_OK){
                Bundle extras =data.getExtras();

                if(extras!=null){
                    clienteList.set(extras.getInt("lugar"), DbPrestamos.getAppDatabase(this).clienteDao().MostrarClientePorId(extras.getString("cedula")));
                    adapter.notifyDataSetChanged();
                }

            }
        }
    }
}
