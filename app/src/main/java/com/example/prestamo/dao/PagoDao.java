package com.example.prestamo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.prestamo.obj.Pago;

@Dao
public interface PagoDao {
    @Insert
    void Insertar(Pago pago);
}
