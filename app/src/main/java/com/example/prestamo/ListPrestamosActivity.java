package com.example.prestamo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListPrestamosActivity extends AppCompatActivity {

    AdapPrestamos adapPrestamo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prestamos);

        ListView listPrestamos =  findViewById(R.id.listPrestamos);

        adapPrestamo = new AdapPrestamos(this, R.layout.item_prestamos, Datos.prestamos);
        listPrestamos.setAdapter(adapPrestamo);
    }
}
