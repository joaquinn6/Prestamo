package com.example.prestamo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerClienteActivity extends AppCompatActivity {
    public int indice=0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActionBar bar = getSupportActionBar();
        bar.setSubtitle("Ver Cliente");

        setContentView(R.layout.activity_ver_cliente);
        if(Datos.clientes.size()!=0){

            LlenarCliente();

        Button btnSiguiente= findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indice==Datos.clientes.size()-1){
                    Toast.makeText(VerClienteActivity.this, "LLego al fin", Toast.LENGTH_SHORT).show();
                }else {
                    indice=1+indice;
                    LlenarCliente();
                }
            }
        });
        
        Button btnAnterior= findViewById(R.id.btnAnterior);
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indice==0){
                    Toast.makeText(VerClienteActivity.this, "Llego al inicio", Toast.LENGTH_SHORT).show();
                }else{
                    indice=indice-1;
                    LlenarCliente();
                }
            }
        });

        Button btnNuevoPrestamo = findViewById(R.id.btnNuevoPrestamo);
        btnNuevoPrestamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerClienteActivity.this, SecondActivity.class);
                intent.putExtra("indice", String.valueOf(indice));

                startActivity(intent);
            }
        });
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
}
