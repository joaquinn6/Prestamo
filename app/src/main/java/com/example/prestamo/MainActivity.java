package com.example.prestamo;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.prestamo.db.DbPrestamos;
import com.example.prestamo.obj.Cliente;

public class MainActivity extends AppCompatActivity {
    private int indice=-1;
    private DbPrestamos db;
    private Cliente actualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setSubtitle("Ingresar Cliente");

        db= Room.databaseBuilder(getApplicationContext(), DbPrestamos.class, "prestamos").allowMainThreadQueries().build();
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

        actualizar=db.clienteDao().MostrarClientePorId(indice);
        tvNombre.setText(actualizar.getNombre());
        tvApellido.setText(actualizar.getApelldio());
        if(actualizar.getSexo().equals("Femenino"))
            tvSexo.setSelection(0);
        else
            tvSexo.setSelection(1);
        tvTelefono.setText(actualizar.getNumero());
        tvCedula.setText(actualizar.getCedula());
        tvOcupacion.setText(actualizar.getOcupacion());
        tvDireccion.setText(actualizar.getDireccion());

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

                        db.clienteDao().Insertar(nuevo);
                    }else{
                        actualizar.setNombre(txtNombre.getText().toString());
                        actualizar.setApelldio(txtApellido.getText().toString());
                        actualizar.setSexo(spSexo.getSelectedItem().toString());
                        actualizar.setNumero(txtTelefono.getText().toString());
                        actualizar.setCedula(txtCedula.getText().toString());
                        actualizar.setDireccion(txtDireccion.getText().toString());
                        actualizar.setOcupacion(txtOcupacion.getText().toString());

                        db.clienteDao().Actualizar(actualizar);
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
