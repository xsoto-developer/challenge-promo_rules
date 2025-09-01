package com.walmarttech.service.factory;

import com.walmarttech.entity.Promocion;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PromocionFactory {
    public Promocion create(String nombre, String categoria, double descuento, Date inicio, Date fin) {
        Promocion promo = new Promocion();
        promo.setNombre(nombre);
        promo.setCategoria(categoria);
        promo.setDescuento(descuento);
        promo.setFechaInicio(inicio);
        promo.setFechaFin(fin);
        return promo;
    }
}