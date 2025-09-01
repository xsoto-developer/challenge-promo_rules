package com.walmarttech.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Promocion {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String categoria;
    private double descuento;
    private Date fechaInicio;
    private Date fechaFin;

    public boolean esValidaEnFecha(Date fecha) {
        return !fecha.before(fechaInicio) && !fecha.after(fechaFin);
    }
}