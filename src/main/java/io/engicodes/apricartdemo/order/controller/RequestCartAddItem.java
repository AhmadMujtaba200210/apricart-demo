package io.engicodes.apricartdemo.order.controller;

import io.engicodes.apricartdemo.product.model.Product;

public record RequestCartAddItem(
        Product product
) {
}
