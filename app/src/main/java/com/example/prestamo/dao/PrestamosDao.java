package com.example.prestamo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.prestamo.obj.Prestamos;
import com.example.prestamo.pojo.PrestamoConCliente;
import com.example.prestamo.pojo.PrestamoConClienteConPagos;

import java.util.List;

@Dao
public interface PrestamosDao {
    @Insert
    void Insertar(Prestamos prestamos);

    @Query("select * from prestamos inner join cliente on cliente.cedula = prestamos.cedulaCliente")
    List<PrestamoConCliente> MostrarPojo();

    @Query("select * from prestamos inner join cliente on cliente.cedula = prestamos.cedulaCliente where cedulaCliente=:cedula")
    List<PrestamoConCliente> MostrarPojoCedula(String cedula);

    @Query("select * from cliente inner join prestamos on cliente.cedula = prestamos.cedulaCliente where prestamos.id=:id")
    PrestamoConClienteConPagos MostrarPojoTodo(int id);
}
