package com.example.prestamo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.prestamo.obj.Prestamos;

import java.util.List;

@Dao
public interface PrestamosDao {
    @Insert
    void Insertar(Prestamos prestamos);

    @Delete
    void Eliminar(Prestamos prestamos);

    @Update
    void Actualizar(Prestamos prestamos);

    @Query("select * from prestamos")
    List<Prestamos> MostrarPrestamos();

    @Query("select * from prestamos where idCliente=:id")
    List<Prestamos> MostrarPrestamosPorId(int id);
}
