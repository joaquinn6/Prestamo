package com.example.prestamo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.prestamo.obj.Cliente;

import java.util.List;

@Dao
public interface ClienteDao {
    @Insert
    void Insertar(Cliente cliente);

    @Delete
    void Borrar(Cliente cliente);

    @Update
    void Actualizar(Cliente cliente);

    @Query("select * from cliente")
    List<Cliente> MostrarClientes();

    @Query("select * from cliente where cedula=:id")
    Cliente MostrarClientePorId(String id);
}
