package com.example.prestamo;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.prestamo.adapters.AdapPrestamos;
import com.example.prestamo.db.DbPrestamos;

public class ListPrestamosActivity extends AppCompatActivity {
    private String indice;
    private AdapPrestamos adapPrestamo;
    private DbPrestamos db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prestamos);

        ListView listPrestamos =  findViewById(R.id.listPrestamos);
        db= Room.databaseBuilder(getApplicationContext(), DbPrestamos.class, "prestamos").allowMainThreadQueries().build();

        Bundle extras =getIntent().getExtras();

        if(extras!=null){
            indice=extras.getString("indice");
            adapPrestamo= new AdapPrestamos(this, R.layout.item_prestamos, db.prestamosDao().MostrarPojoCedula(indice));
        }else{
            adapPrestamo = new AdapPrestamos(this, R.layout.item_prestamos, db.prestamosDao().MostrarPojo());
        }

        listPrestamos.setAdapter(adapPrestamo);
    }
}
