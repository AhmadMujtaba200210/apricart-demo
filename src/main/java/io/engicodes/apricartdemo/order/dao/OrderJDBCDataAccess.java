package io.engicodes.apricartdemo.order.dao;

import io.engicodes.apricartdemo.cart.model.Cart;
import io.engicodes.apricartdemo.order.model.Order;
import io.engicodes.apricartdemo.order.model.OrderStatus;
import org.springframework.stereotype.Repository;




@Repository("jdbc-order")
public class OrderJDBCDataAccess implements OrderDao{
    @Override
    public void placeOrder(Cart cart, Double totalPrice) {

    }

    @Override
    public void cancelOrder(Integer orderId) {

    }

    @Override
    public void updateOrderStatus(Integer orderId, OrderStatus status) {

    }

    @Override
    public Order getOrderByUserId(Integer userId) {
        return null;
    }

    @Override
    public Order getOrderByOrderId(Integer orderId) {
        return null;
    }

    @Override
    public Double getOrderTotal(Integer orderId) {
        return null;
    }
}
