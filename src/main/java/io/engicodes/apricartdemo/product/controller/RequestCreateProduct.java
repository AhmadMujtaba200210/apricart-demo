package io.engicodes.apricartdemo.product.controller;

import io.engicodes.apricartdemo.warehouse.model.Warehouse;

public record RequestCreateProduct(
        String name,
        String description,
        String brand,
        Double price,
        Boolean status,
        Integer warehouseId
) {

}
