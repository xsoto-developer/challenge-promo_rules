package com.walmarttech.service;

import com.walmarttech.entity.Producto;
import com.walmarttech.entity.Promocion;
import com.walmarttech.repository.PromocionRepository;
import com.walmarttech.service.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AplicadorPromocionesService {
    private final PromocionRepository promocionRepository;
    private final DiscountStrategy discountStrategy;

    @Autowired
    public AplicadorPromocionesService(PromocionRepository promocionRepository, DiscountStrategy discountStrategy) {
        this.promocionRepository = promocionRepository;
        this.discountStrategy = discountStrategy;
    }

    public double aplicarPromociones(Producto producto, Date fecha) {
        List<Promocion> promociones = promocionRepository.findAll();
        double descuentoMaximo = 0.0;

        for (Promocion promocion : promociones) {
            if (producto.getCategoria().equals(promocion.getCategoria()) && promocion.esValidaEnFecha(fecha)) {
                descuentoMaximo = Math.max(descuentoMaximo, promocion.getDescuento());
            }
        }

        double precioFinal = producto.getPrecio();
        if (descuentoMaximo > 0) {
            precioFinal = discountStrategy.apply(precioFinal, descuentoMaximo);
        }
        return precioFinal;
    }
}