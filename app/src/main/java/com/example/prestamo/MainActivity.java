package com.example.prestamo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int indice=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setSubtitle("Ingresar Cliente");

        Bundle extras =getIntent().getExtras();

        if(extras!=null){
            indice=Integer.parseInt(extras.getString("indice"));
            LlenarCliente();
        }
    }

    public void LlenarCliente(){
        EditText tvNombre = findViewById(R.id.etNombre);
        EditText tvApellido= findViewById(R.id.etApellido);
        Spinner tvSexo= findViewById(R.id.spinner);
        EditText tvTelefono = findViewById(R.id.etTelefono);
        EditText tvCedula= findViewById(R.id.etCedula);
        EditText tvOcupacion= findViewById(R.id.etOcupacion);
        EditText tvDireccion= findViewById(R.id.etDireccion);

        tvNombre.setText(Datos.clientes.get(indice).getNombre());
        tvApellido.setText(Datos.clientes.get(indice).getApelldio());
        if(Datos.clientes.get(indice).getSexo().equals("Femenino"))
            tvSexo.setSelection(0);
        else
            tvSexo.setSelection(1);
        tvTelefono.setText(Datos.clientes.get(indice).getNumero());
        tvCedula.setText(Datos.clientes.get(indice).getCedula());
        tvOcupacion.setText(Datos.clientes.get(indice).getOcupacion());
        tvDireccion.setText(Datos.clientes.get(indice).getDireccion());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_yes_no, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnAceptar:

                EditText txtNombre= findViewById(R.id.etNombre);
                EditText txtTelefono= findViewById(R.id.etTelefono);
                EditText txtCedula= findViewById(R.id.etCedula);
                EditText txtDireccion= findViewById(R.id.etDireccion);
                EditText txtApellido= findViewById(R.id.etApellido);
                EditText txtOcupacion= findViewById(R.id.etOcupacion);
                Spinner spSexo = findViewById(R.id.spinner);
                if (txtNombre.getText().toString().length()==0 || txtCedula.getText().toString().length()==0 || txtDireccion.getText().toString().length()==0 || txtTelefono.getText().toString().length()==0){
                    if(txtNombre.getText().toString().length()==0)
                        txtNombre.setError("Campo Obligatorio");
                    if(txtTelefono.getText().toString().length()==0)
                        txtTelefono.setError("Campo Obligatorio");
                    if(txtCedula.getText().toString().length()==0)
                        txtCedula.setError("Campo Obligatorio");
                    if(txtDireccion.getText().toString().length()==0)
                        txtDireccion.setError("Campo Obligatorio");
                }else{
                    if(indice==-1){
                        Cliente nuevo = new Cliente();
                        nuevo.setNombre(txtNombre.getText().toString());
                        nuevo.setApelldio(txtApellido.getText().toString());
                        nuevo.setSexo(spSexo.getSelectedItem().toString());
                        nuevo.setNumero(txtTelefono.getText().toString());
                        nuevo.setCedula(txtCedula.getText().toString());
                        nuevo.setDireccion(txtDireccion.getText().toString());
                        nuevo.setOcupacion(txtOcupacion.getText().toString());

                        Datos.clientes.add(nuevo);
                    }else{
                        Datos.clientes.get(indice).setNombre(txtNombre.getText().toString());
                        Datos.clientes.get(indice).setApelldio(txtApellido.getText().toString());
                        Datos.clientes.get(indice).setSexo(spSexo.getSelectedItem().toString());
                        Datos.clientes.get(indice).setNumero(txtTelefono.getText().toString());
                        Datos.clientes.get(indice).setCedula(txtCedula.getText().toString());
                        Datos.clientes.get(indice).setDireccion(txtDireccion.getText().toString());
                        Datos.clientes.get(indice).setOcupacion(txtOcupacion.getText().toString());

                    }

                    Intent intent = new Intent();
                    intent.putExtra("nombre", txtNombre.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.mnCancelar:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
