package io.engicodes.apricartdemo.order.dao;

import io.engicodes.apricartdemo.cart.model.Cart;
import io.engicodes.apricartdemo.order.model.Order;
import io.engicodes.apricartdemo.order.model.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;




@Repository("jpa-order")
@Transactional
public class OrderJPADataAccess implements OrderDao{
    private final OrderRepository orderRepository;

    public OrderJPADataAccess(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void placeOrder(Cart cart, Double totalPrice) {
        Order order = new Order();
        order.setStatus(OrderStatus.INPROCESS);
        order.setProductId(
                cart.getProductId()
        );
        order.setQuantity(cart.getQuantity());
        //dummy values as specifications are not completed
        order.setTotalPrice(totalPrice);
    }

    @Override
    public void cancelOrder(Integer orderId) {
        Order order = getOrderByOrderId(orderId);
        order.setStatus(OrderStatus.CANCELLED);
    }

    @Override
    public void updateOrderStatus(Integer orderId, OrderStatus status) {
        Order order = getOrderByOrderId(orderId);
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public Order getOrderByUserId(Integer userId) {
        return orderRepository.getOrderByUserId(userId);
    }

    @Override
    public Order getOrderByOrderId(Integer orderId) {
        return orderRepository.getOrderByOrderId(orderId);
    }

    @Override
    public Double getOrderTotal(Integer orderId) {
        Order order = getOrderByOrderId(orderId);
        return order.getTotalPrice();
    }
}
