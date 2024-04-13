package io.engicodes.apricartdemo.order.dao;

import io.engicodes.apricartdemo.cart.model.Cart;
import io.engicodes.apricartdemo.order.model.Order;
import io.engicodes.apricartdemo.order.model.OrderStatus;




public interface OrderDao {
    void placeOrder(Cart cart,Double totalPrice);
    void cancelOrder(Integer orderId);
    void updateOrderStatus(Integer orderId,OrderStatus status);
    Order getOrderByUserId(Integer userId);
    Order getOrderByOrderId(Integer orderId);
    Double getOrderTotal(Integer orderId);
}
