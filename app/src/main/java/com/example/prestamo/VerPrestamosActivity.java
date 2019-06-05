//package com.example.prestamo;
//
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.sql.DriverPropertyInfo;
//
//public class VerPrestamosActivity extends AppCompatActivity {
//    public int indice=0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        ActionBar bar = getSupportActionBar();
//        bar.setSubtitle("Ver Prestamo");
//
//        setContentView(R.layout.activity_ver_prestamos);
//        if(Datos.prestamos.size()!=0){
//            llenarPrestamo();
//            Button btnSiguiente= findViewById(R.id.btnSiguiente);
//            btnSiguiente.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(indice==Datos.prestamos.size()-1){
//                        Toast.makeText(VerPrestamosActivity.this, "LLego al fin", Toast.LENGTH_SHORT).show();
//                    }else {
//                        indice=1+indice;
//                        llenarPrestamo();
//                    }
//                }
//            });
//
//            Button btnAnterior = findViewById(R.id.btnAnterior);
//            btnAnterior.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(indice==0){
//                        Toast.makeText(VerPrestamosActivity.this, "Llego al inicio", Toast.LENGTH_SHORT).show();
//                    }else{
//                        indice=indice-1;
//                        llenarPrestamo();
//                    }
//                }
//            });
//        }
//    }
//
//    public void llenarPrestamo(){
//        TextView tvNombreCliente = findViewById(R.id.spUsuarios);
//        TextView tvMontoCredito= findViewById(R.id.etMontoCredito);
//        TextView tvInteres= findViewById(R.id.spInteres);
//        TextView tvPlazo= findViewById(R.id.etPlazo);
//        TextView tvFecha= findViewById(R.id.etFecha);
//        TextView tvFechaFinal= findViewById(R.id.etFechaFinal);
//        TextView tvMontoPagar= findViewById(R.id.tvMontoPagar);
//        TextView tvMontoCuota= findViewById(R.id.tvMontoCuota);
//
//        tvMontoCuota.setText(Datos.prestamos.get(indice).getMontoCuota().toString());
//        tvMontoPagar.setText(Datos.prestamos.get(indice).getMontoPagar().toString());
//        tvFechaFinal.setText(Datos.prestamos.get(indice).getFechaFinal());
//        tvFecha.setText(Datos.prestamos.get(indice).getFechaInicio());
//        tvPlazo.setText(String.valueOf(Datos.prestamos.get(indice).getPlazo()));
//        tvInteres.setText(Datos.prestamos.get(indice).getInteres());
//        tvMontoCredito.setText(Datos.prestamos.get(indice).getMonto().toString());
//        tvNombreCliente.setText(Datos.prestamos.get(indice).getNombreCliente());
//    }
//}
