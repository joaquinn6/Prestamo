package com.example.prestamo.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.prestamo.obj.Pago;

import java.util.ArrayList;
import java.util.List;

public class PrestamoConClienteConPagos {
    @Embedded
    PrestamoConCliente prestamoConCliente;

    @Relation(entity = Pago.class, parentColumn = "id", entityColumn = "idPrestamo")
    List<Pago> pagoList;

    public PrestamoConClienteConPagos() {
        prestamoConCliente = new PrestamoConCliente();
        pagoList= new ArrayList<>();
    }

    public PrestamoConCliente getPrestamoConCliente() {
        return prestamoConCliente;
    }

    public void setPrestamoConCliente(PrestamoConCliente prestamoConCliente) {
        this.prestamoConCliente = prestamoConCliente;
    }

    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }
}
