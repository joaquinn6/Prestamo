package com.example.prestamo;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prestamo.adapters.AdapPago;
import com.example.prestamo.db.DbPrestamos;
import com.example.prestamo.obj.Pago;
import com.example.prestamo.pojo.PrestamoConClienteConPagos;

import java.util.ArrayList;
import java.util.List;


public class VerPrestamosActivity extends AppCompatActivity {
    private int id=0;
    private PrestamoConClienteConPagos prestamoConClienteConPagos=new PrestamoConClienteConPagos();
    private DbPrestamos db;
    private AdapPago adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getSupportActionBar();
        bar.setSubtitle("Ver Prestamo");
        setContentView(R.layout.activity_ver_prestamos);

        db= Room.databaseBuilder(getApplicationContext(), DbPrestamos.class, "prestamos").allowMainThreadQueries().build();

        Bundle extras =getIntent().getExtras();
        ListView lvPagos = findViewById(R.id.lvPagos);
        if(extras!=null){
            id=extras.getInt("id");
            prestamoConClienteConPagos=db.prestamosDao().MostrarPojoTodo(id);
            llenarPrestamo();
            adapter= new AdapPago(this, R.layout.item_pago, prestamoConClienteConPagos.getPagoList());
            lvPagos.setAdapter(adapter);

        }
    }

    public void llenarPrestamo(){
        TextView tvNombreCliente = findViewById(R.id.spUsuarios);
        TextView tvMontoCredito= findViewById(R.id.etMontoCredito);
        TextView tvInteres= findViewById(R.id.spInteres);
        TextView tvPlazo= findViewById(R.id.etPlazo);
        TextView tvFecha= findViewById(R.id.etFecha);
        TextView tvFechaFinal= findViewById(R.id.etFechaFinal);
        TextView tvMontoPagar= findViewById(R.id.tvMontoPagar);
        TextView tvMontoCuota= findViewById(R.id.tvMontoPagado);

        if(prestamoConClienteConPagos.getPagoList().size()==0)
            tvMontoCuota.setText("0.0");
        else{
            float total=0;
            for (int i=0; i<prestamoConClienteConPagos.getPagoList().size(); i++){
                total+=prestamoConClienteConPagos.getPagoList().get(i).getMonto();
            }
            tvMontoCuota.setText(String.valueOf(total));
        }
        tvMontoPagar.setText(prestamoConClienteConPagos.getPrestamos().getMontoPagar().toString());
        tvFechaFinal.setText(prestamoConClienteConPagos.getPrestamos().getFechaFinal());
        tvFecha.setText(prestamoConClienteConPagos.getPrestamos().getFechaInicio());
        tvPlazo.setText(String.valueOf(prestamoConClienteConPagos.getPrestamos().getPlazo()));
        tvInteres.setText(prestamoConClienteConPagos.getPrestamos().getInteres());
        tvMontoCredito.setText(prestamoConClienteConPagos.getPrestamos().getMonto().toString());
        tvNombreCliente.setText(prestamoConClienteConPagos.getCliente().getNombre() + " "+ prestamoConClienteConPagos.getCliente().getApelldio());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(VerPrestamosActivity.this);
                builder.setTitle("Pago");
                final View view = LayoutInflater.from(VerPrestamosActivity.this).inflate(R.layout.dialog_pago, null, false);
                builder.setView(view);
                builder.setNegativeButton("Cancelar", null);
                builder.setPositiveButton("guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText etCantidad= view.findViewById(R.id.etCantidad);

                        Pago pago= new Pago();
                        pago.setMonto(Float.parseFloat(etCantidad.getText().toString()));
                        pago.setIdPrestamo(prestamoConClienteConPagos.getPrestamos().getId());
                        try {
                            db.pagoDao().Insertar(pago);
                            prestamoConClienteConPagos.getPagoList().add(pago);
                            adapter.notifyDataSetChanged();
                            calcularPago();
                        }catch (SQLiteConstraintException e){
                            Toast.makeText(VerPrestamosActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void calcularPago(){
        TextView tvMontoCuota = findViewById(R.id.tvMontoPagado);
        if(prestamoConClienteConPagos.getPagoList().size()==0)
            tvMontoCuota.setText("0.0");
        else{
            float total=0;
            for (int i=0; i<prestamoConClienteConPagos.getPagoList().size(); i++){
                total+=prestamoConClienteConPagos.getPagoList().get(i).getMonto();
            }
            tvMontoCuota.setText(String.valueOf(total));
        }
    }
}
