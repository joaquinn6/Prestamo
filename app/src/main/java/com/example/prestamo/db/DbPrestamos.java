package com.example.prestamo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.prestamo.dao.PagoDao;
import com.example.prestamo.obj.Cliente;
import com.example.prestamo.dao.ClienteDao;
import com.example.prestamo.dao.PrestamosDao;
import com.example.prestamo.obj.Pago;
import com.example.prestamo.obj.Prestamos;

@Database(version = 1, entities = {Prestamos.class, Cliente.class, Pago.class})
public abstract class DbPrestamos extends RoomDatabase {
    public static DbPrestamos INSTANCE;
    public abstract ClienteDao clienteDao();
    public abstract PrestamosDao prestamosDao();
    public abstract PagoDao pagoDao();

    public static DbPrestamos getAppDatabase(Context context){
        if(INSTANCE == null)
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), DbPrestamos.class, "db").allowMainThreadQueries().build();
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE=null;
    }
}
