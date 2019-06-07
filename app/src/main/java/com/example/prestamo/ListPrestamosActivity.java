package com.example.prestamo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.prestamo.adapters.AdapPrestamos;
import com.example.prestamo.db.DbPrestamos;
import com.example.prestamo.pojo.PrestamoConCliente;

import java.util.ArrayList;
import java.util.List;

public class ListPrestamosActivity extends AppCompatActivity {
    private String indice;
    private AdapPrestamos adapPrestamo;
    private List<PrestamoConCliente> prestamosList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prestamos);

        ListView listPrestamos =  findViewById(R.id.listPrestamos);
        Bundle extras =getIntent().getExtras();

        if(extras!=null){
            indice=extras.getString("indice");
            prestamosList.addAll(DbPrestamos.getAppDatabase(this).prestamosDao().MostrarPojoCedula(indice));
            adapPrestamo= new AdapPrestamos(this, R.layout.item_prestamos, prestamosList);
        }else{
            prestamosList.addAll(DbPrestamos.getAppDatabase(this).prestamosDao().MostrarPojo());
            adapPrestamo = new AdapPrestamos(this, R.layout.item_prestamos, prestamosList);
        }

        listPrestamos.setAdapter(adapPrestamo);

        listPrestamos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(ListPrestamosActivity.this,VerPrestamosActivity.class);
                intent.putExtra("id", prestamosList.get(position).getPrestamos().getId());
                startActivity(intent);
            }
        });
    }
}
