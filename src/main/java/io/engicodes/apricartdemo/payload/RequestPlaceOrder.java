package io.engicodes.apricartdemo.payload;

import io.engicodes.apricartdemo.order.model.OrderStatus;
import io.engicodes.apricartdemo.product.model.Product;
import io.engicodes.apricartdemo.user.User;
import io.engicodes.apricartdemo.warehouse.model.Warehouse;


import java.util.List;

public record RequestPlaceOrder(
        List<Product> productId,
        User userId,
        Warehouse warehouseId,
        Integer quantity,
        OrderStatus status,
        Double totalPrice
) {
}
