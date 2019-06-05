package com.example.prestamo;

import android.arch.persistence.room.Room;
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

import com.example.prestamo.db.DbPrestamos;
import com.example.prestamo.obj.Cliente;

import java.util.ArrayList;
import java.util.List;

public class VerClienteActivity extends AppCompatActivity {
    private int indice=0;
    private DbPrestamos db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActionBar bar = getSupportActionBar();
        bar.setSubtitle("Ver Cliente");

        setContentView(R.layout.activity_ver_cliente);

        db= Room.databaseBuilder(getApplicationContext(), DbPrestamos.class, "prestamos").allowMainThreadQueries().build();


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

        Cliente cliente = db.clienteDao().MostrarClientePorId(indice);

        tvNombre.setText(cliente.getNombre());
        tvApellido.setText(cliente.getApelldio());
        tvSexo.setText(cliente.getSexo());
        tvTelefono.setText(cliente.getNumero());
        tvCedula.setText(cliente.getCedula());
        tvOcupacion.setText(cliente.getOcupacion());
        tvDireccion.setText(cliente.getDireccion());

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
