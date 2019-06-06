package com.example.prestamo.obj;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Prestamos {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Double monto;
    private String interes;
    private int plazo;
    private String fechaInicio;
    private String fechaFinal;
    private Double montoPagar;
    private Double montoCuota;
    @ForeignKey(entity = Cliente.class, childColumns = "idCliente", parentColumns = "id", onDelete = CASCADE, onUpdate = CASCADE)
    private String cedulaCliente;

    public Prestamos() {
    }

    public Prestamos(int id, String cedulaCliente, Double monto, String interes, int plazo, String fechaInicio, String fechaFinal, Double montoPagar, Double montoCuota) {
        this.id = id;
        this.monto = monto;
        this.interes = interes;
        this.plazo = plazo;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.montoPagar = montoPagar;
        this.montoCuota = montoCuota;
        this.cedulaCliente=cedulaCliente;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Double getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(Double montoPagar) {
        this.montoPagar = montoPagar;
    }

    public Double getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(Double montoCuota) {
        this.montoCuota = montoCuota;
    }
}
