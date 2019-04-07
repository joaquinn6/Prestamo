package com.example.prestamo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EditText dtpFecha = findViewById(R.id.etFecha);
        EditText dtpFechaFinal = findViewById(R.id.etFechaFinal);
        Calendar now= Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        dtpFecha.setText(formato.format(now.getTime()));
        now.add(Calendar.MONTH,+1);
        dtpFechaFinal.setText(formato.format(now.getTime()));

        EditText txtMontoCredito = findViewById(R.id.etMontoCredito);
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
        EditText txtPlazos = findViewById(R.id.etPlazo);
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
        Spinner spInteres = findViewById(R.id.spInteres);
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
}
