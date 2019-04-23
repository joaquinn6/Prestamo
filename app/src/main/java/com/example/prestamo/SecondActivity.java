package com.example.prestamo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar = getSupportActionBar();
        bar.setSubtitle("Ingresar Credito");


        setContentView(R.layout.activity_second);
        final Spinner spClientes = findViewById(R.id.spUsuarios);
        Bundle extras =getIntent().getExtras();
        List<String> adaptador= new ArrayList<>();

        if(extras!=null){
            String cli;
            int indice = Integer.parseInt(extras.getString("indice"));
            cli= Datos.clientes.get(indice).getNombre();
            adaptador.add(cli);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, adaptador);
            spClientes.setAdapter(adapter);
        }else{
            if(Datos.clientes.size()>0){
                for (Cliente x: Datos.clientes){
                    adaptador.add(x.getNombre());
                }
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, adaptador);
                spClientes.setAdapter(adapter);
            }
        }
        final TextView tvMontoPagar = findViewById(R.id.tvMontoPagar);
        final TextView tvMontoCuota = findViewById(R.id.tvMontoCuota);
        final EditText dtpFecha = findViewById(R.id.etFecha);
        final EditText dtpFechaFinal = findViewById(R.id.etFechaFinal);
        Calendar now= Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        dtpFecha.setText(formato.format(now.getTime()));
        now.add(Calendar.MONTH,+1);
        dtpFechaFinal.setText(formato.format(now.getTime()));

        final EditText txtMontoCredito = findViewById(R.id.etMontoCredito);
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
        final EditText txtPlazos = findViewById(R.id.etPlazo);
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
        final Spinner spInteres = findViewById(R.id.spInteres);
        spInteres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calcularMontos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Button btnFinalizar=findViewById(R.id.btnSiguiente);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spClientes.getSelectedItemPosition()!=-1 && txtMontoCredito.getText().toString().length()!=0 && Integer.parseInt(txtMontoCredito.getText().toString())>0){
                    Prestamos nuevo = new Prestamos();

                    nuevo.setNombreCliente(spClientes.getSelectedItem().toString());
                    nuevo.setMonto(Double.parseDouble(txtMontoCredito.getText().toString()));
                    nuevo.setInteres(spInteres.getSelectedItem().toString());
                    nuevo.setPlazo(Integer.parseInt(txtPlazos.getText().toString()));
                    nuevo.setFechaInicio(dtpFecha.getText().toString());
                    nuevo.setFechaFinal(dtpFechaFinal.getText().toString());
                    nuevo.setMontoPagar(Double.parseDouble(tvMontoPagar.getText().toString()));
                    nuevo.setMontoCuota(Double.parseDouble(tvMontoCuota.getText().toString()));

                    Datos.prestamos.add(nuevo);

                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    if(txtMontoCredito.getText().toString().length()==0 || Integer.parseInt(txtMontoCredito.getText().toString())==0)
                        txtMontoCredito.setError("Monto mayos que 0");
                }
            }
        });

        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
}
