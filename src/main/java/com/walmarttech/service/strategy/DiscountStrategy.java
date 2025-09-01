package com.walmarttech.service.strategy;

public interface DiscountStrategy {
    double apply(double precio, double descuento);
}