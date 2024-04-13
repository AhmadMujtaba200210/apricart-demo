package io.engicodes.apricartdemo.product.controller;

import io.engicodes.apricartdemo.warehouse.model.Warehouse;



public record RequestUpdateProduct(
        String name,
        String description,
        String brand,
        Double price,
        Boolean status,
        Warehouse warehouseId
) {
}
