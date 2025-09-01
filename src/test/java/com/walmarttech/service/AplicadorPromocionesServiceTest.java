package com.walmarttech.service;

import com.walmarttech.entity.Producto;
import com.walmarttech.entity.Promocion;
import com.walmarttech.repository.PromocionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AplicadorPromocionesServiceTest {
    @Autowired
    private AplicadorPromocionesService service;
    @Autowired
    private PromocionRepository promocionRepository;

    @BeforeEach
    void setUp() {
        promocionRepository.deleteAll();
        Promocion promo = new Promocion();
        promo.setCategoria("Electronica");
        promo.setDescuento(0.10);
        promo.setFechaInicio(new Date(System.currentTimeMillis() - 100000));
        promo.setFechaFin(new Date(System.currentTimeMillis() + 100000));
        promocionRepository.save(promo);
    }

    @Test
    void testAplicarPromociones() {
        Producto producto = new Producto();
        producto.setCategoria("Electronica");
        producto.setPrecio(1000.0);
        double precioFinal = service.aplicarPromociones(producto, new Date());
        assertEquals(900.0, precioFinal, 0.01);
    }
}