package com.walmarttech.service.strategy;

import org.springframework.stereotype.Component;

@Component
public class PercentageDiscountStrategy implements DiscountStrategy {
    @Override
    public double apply(double precio, double descuento) {
        return precio * (1 - descuento);
    }
}