package io.engicodes.apricartdemo.cart.controller;

import io.engicodes.apricartdemo.product.model.Product;

public record RequestCartAddItem(
        Product product
) {
}
