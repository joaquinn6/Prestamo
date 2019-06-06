package com.example.prestamo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.prestamo.obj.Pago;

import java.util.List;

@Dao
public interface PagoDao {
    @Insert
    void Insertar(Pago pago);

    @Delete
    void Eliminar(Pago pago);

    @Update
    void Actualizar(Pago pago);

    @Query("select * from pago")
    List<Pago> MostrarPagos();
}
