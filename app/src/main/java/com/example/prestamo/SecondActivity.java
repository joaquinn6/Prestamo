package com.example.prestamo;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prestamo.db.DbPrestamos;
import com.example.prestamo.obj.Prestamos;
import com.example.prestamo.obj.Cliente;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private String indice="";
    private DbPrestamos db;
    public Spinner spClientes;
    public TextView tvMontoPagar;
    public TextView tvMontoCuota;
    public EditText dtpFecha;
    public EditText dtpFechaFinal;
    public EditText txtMontoCredito;
    public EditText txtPlazos;
    public Spinner spInteres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar = getSupportActionBar();
        bar.setSubtitle("Ingresar Credito");

        db= Room.databaseBuilder(getApplicationContext(), DbPrestamos.class, "prestamos").allowMainThreadQueries().build();

        setContentView(R.layout.activity_second);
        spClientes = findViewById(R.id.spUsuarios);
        Bundle extras =getIntent().getExtras();
        List<String> adaptador= new ArrayList<>();

        if(extras!=null) {
            String cli;
            indice = extras.getString("indice");
            cli = db.clienteDao().MostrarClientePorId(indice).getNombre() + " " + db.clienteDao().MostrarClientePorId(indice).getApelldio();
            adaptador.add(cli);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, adaptador);
            spClientes.setAdapter(adapter);
        }
            //        else{
//            if(Datos.clientes.size()>0){
//                for (Cliente x: Datos.clientes){
//                    adaptador.add(x.getNombre());
//                }
//                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, adaptador);
//                spClientes.setAdapter(adapter);
//            }
//        }
        tvMontoPagar = findViewById(R.id.tvMontoPagar);
        tvMontoCuota = findViewById(R.id.tvMontoCuota);
        dtpFecha = findViewById(R.id.etFecha);
        dtpFechaFinal = findViewById(R.id.etFechaFinal);
        Calendar now= Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        dtpFecha.setText(formato.format(now.getTime()));
        now.add(Calendar.MONTH,+1);
        dtpFechaFinal.setText(formato.format(now.getTime()));

        txtMontoCredito = findViewById(R.id.etMontoCredito);
        txtMontoCredito.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcularMontos();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtPlazos = findViewById(R.id.etPlazo);
        txtPlazos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcularMontos();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        spInteres = findViewById(R.id.spInteres);
        spInteres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calcularMontos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void calcularMontos(){
        EditText txtMonto = findViewById(R.id.etMontoCredito);
        EditText txtPlazo = findViewById(R.id.etPlazo);
        Spinner spInteres = findViewById(R.id.spInteres);

        TextView tvMonto = findViewById(R.id.tvMontoPagar);
        TextView tvPlazo = findViewById(R.id.tvMontoCuota);

        int plazo=1;
        int interes = Integer.valueOf(spInteres.getSelectedItem().toString());
        double monto=0;
        double montoPagar;
        double montoPorPlazo;
        if(txtPlazo.getText().toString().length()>0){
            plazo=Integer.valueOf(txtPlazo.getText().toString());
        }
        else{
            plazo=1;
        }
        if(txtMonto.getText().toString().length()>0){
            monto=Double.valueOf(txtMonto.getText().toString());
        }else{
            monto=0;
        }

        montoPagar=(plazo* Double.valueOf("0."+String.valueOf(interes))*monto)+monto;
        montoPorPlazo=montoPagar/plazo;

        tvMonto.setText(String.valueOf(montoPagar));
        tvPlazo.setText(String.valueOf(montoPorPlazo));

        Calendar now= Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        now.add(Calendar.MONTH,+plazo);
        EditText dtpFechaFinal = findViewById(R.id.etFechaFinal);
        dtpFechaFinal.setText(formato.format(now.getTime()));
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
                if(spClientes.getSelectedItemPosition()!=-1 && txtMontoCredito.getText().toString().length()!=0 && Integer.parseInt(txtMontoCredito.getText().toString())>0){
                    Prestamos nuevo = new Prestamos();

                    nuevo.setMonto(Double.parseDouble(txtMontoCredito.getText().toString()));
                    nuevo.setInteres(spInteres.getSelectedItem().toString());
                    nuevo.setPlazo(Integer.parseInt(txtPlazos.getText().toString()));
                    nuevo.setFechaInicio(dtpFecha.getText().toString());
                    nuevo.setFechaFinal(dtpFechaFinal.getText().toString());
                    nuevo.setMontoPagar(Double.parseDouble(tvMontoPagar.getText().toString()));
                    nuevo.setMontoCuota(Double.parseDouble(tvMontoCuota.getText().toString()));
                    nuevo.setCedulaCliente(indice);
                    try{
                        db.prestamosDao().Insertar(nuevo);
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }catch (SQLiteConstraintException e){
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(txtMontoCredito.getText().toString().length()==0 || Integer.parseInt(txtMontoCredito.getText().toString())==0)
                        txtMontoCredito.setError("Monto mayor que 0");
                }
                break;
            case R.id.mnCancelar:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
