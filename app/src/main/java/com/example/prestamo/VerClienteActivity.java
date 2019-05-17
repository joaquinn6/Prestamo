package com.example.prestamo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VerClienteActivity extends AppCompatActivity {
    public int indice=0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActionBar bar = getSupportActionBar();
        bar.setSubtitle("Ver Cliente");

        setContentView(R.layout.activity_ver_cliente);


        Bundle extras =getIntent().getExtras();

        if(extras!=null){
            indice=Integer.parseInt(extras.getString("indice"));
            LlenarCliente();
        }
    }

    public void LlenarCliente(){
        TextView tvNombre = findViewById(R.id.etNombre);
        TextView tvApellido= findViewById(R.id.etApellido);
        TextView tvSexo= findViewById(R.id.spinner);
        TextView tvTelefono = findViewById(R.id.etTelefono);
        TextView tvCedula= findViewById(R.id.etCedula);
        TextView tvOcupacion= findViewById(R.id.etOcupacion);
        TextView tvDireccion= findViewById(R.id.etDireccion);
        
        tvNombre.setText(Datos.clientes.get(indice).getNombre());
        tvApellido.setText(Datos.clientes.get(indice).getApelldio());
        tvSexo.setText(Datos.clientes.get(indice).getSexo());
        tvTelefono.setText(Datos.clientes.get(indice).getNumero());
        tvCedula.setText(Datos.clientes.get(indice).getCedula());
        tvOcupacion.setText(Datos.clientes.get(indice).getOcupacion());
        tvDireccion.setText(Datos.clientes.get(indice).getDireccion());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnAgregar:
                Intent intent = new Intent(VerClienteActivity.this, SecondActivity.class);
                intent.putExtra("indice", String.valueOf(indice));

                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
