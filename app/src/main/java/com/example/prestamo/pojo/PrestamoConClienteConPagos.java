package com.example.prestamo.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.prestamo.obj.Cliente;
import com.example.prestamo.obj.Pago;
import com.example.prestamo.obj.Prestamos;

import java.util.ArrayList;
import java.util.List;

public class PrestamoConClienteConPagos {
    @Embedded
    Prestamos prestamos;

    @Embedded
    Cliente cliente;

    @Relation(entity = Pago.class, parentColumn = "id", entityColumn = "idPrestamo")
    List<Pago> pagoList;

    public PrestamoConClienteConPagos() {
        prestamos = new Prestamos();
        cliente = new Cliente();
        pagoList= new ArrayList<>();
    }

    public Prestamos getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Prestamos prestamos) {
        this.prestamos = prestamos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }
}
