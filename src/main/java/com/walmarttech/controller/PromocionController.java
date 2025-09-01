package com.walmarttech.controller;

import com.walmarttech.dto.ProductoDTO;
import com.walmarttech.dto.PromocionDTO;
import com.walmarttech.entity.Producto;
import com.walmarttech.entity.Promocion;
import com.walmarttech.repository.ProductoRepository;
import com.walmarttech.repository.PromocionRepository;
import com.walmarttech.service.AplicadorPromocionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class PromocionController {
    private final AplicadorPromocionesService service;
    private final ProductoRepository productoRepository;
    private final PromocionRepository promocionRepository;

    @Autowired
    public PromocionController(AplicadorPromocionesService service, ProductoRepository productoRepository, PromocionRepository promocionRepository) {
        this.service = service;
        this.productoRepository = productoRepository;
        this.promocionRepository = promocionRepository;
    }

    @PostMapping("/productos")
    public Producto createProducto(@RequestBody ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setCategoria(dto.getCategoria());
        producto.setPrecio(dto.getPrecio());
        return productoRepository.save(producto);
    }

    @PostMapping("/promociones")
    public Promocion createPromocion(@RequestBody PromocionDTO dto) {
        Promocion promocion = new Promocion();
        promocion.setNombre(dto.getNombre());
        promocion.setCategoria(dto.getCategoria());
        promocion.setDescuento(dto.getDescuento());
        promocion.setFechaInicio(dto.getFechaInicio());
        promocion.setFechaFin(dto.getFechaFin());
        return promocionRepository.save(promocion);
    }

    @GetMapping("/aplicar/{productoId}")
    public double aplicarPromociones(@PathVariable Long productoId, @RequestParam long fechaMillis) {
        Producto producto = productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return service.aplicarPromociones(producto, new Date(fechaMillis));
    }
}