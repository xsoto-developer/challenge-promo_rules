package com.walmarttech.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PromocionDTO {
    private String nombre;
    private String categoria;
    private double descuento;
    private Date fechaInicio;
    private Date fechaFin;
}