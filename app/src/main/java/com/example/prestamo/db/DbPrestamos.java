package com.example.prestamo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.prestamo.obj.Cliente;
import com.example.prestamo.dao.ClienteDao;
import com.example.prestamo.dao.PrestamosDao;
import com.example.prestamo.obj.Prestamos;

@Database(version = 1, entities = {Prestamos.class, Cliente.class})
public abstract class DbPrestamos extends RoomDatabase {
    public abstract ClienteDao clienteDao();
    public abstract PrestamosDao prestamosDao();
}
