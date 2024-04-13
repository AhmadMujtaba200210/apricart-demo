package io.engicodes.apricartdemo.order.service;

import io.engicodes.apricartdemo.cart.dao.CartDao;
import io.engicodes.apricartdemo.cart.model.Cart;
import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import io.engicodes.apricartdemo.order.dao.OrderDao;
import io.engicodes.apricartdemo.order.model.Order;
import io.engicodes.apricartdemo.order.model.OrderStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderDao orderDao;
    private final CartDao cartDao;
    public OrderService(@Qualifier("jpa-order") OrderDao orderDao,@Qualifier("jpa-cart") CartDao cartDao) {
        this.orderDao = orderDao;
        this.cartDao = cartDao;
    }

    public void placeOrder( Integer cartId, Double totalPrice) throws Exception {
        Cart cart = cartDao.getCartByCartId(cartId);
        if (cart==null){
            throw new ResourceNotFoundException("cart does not exists!");
        }
        if (cart.getQuantity()==0) throw new Exception("Cart is Empty!");
        orderDao.placeOrder(cart,totalPrice);
    }


    public void updateOrderStatus(Integer orderId, OrderStatus updatedStatus) throws Exception {
        Order order = orderDao.getOrderByOrderId(orderId);
        if (order == null) throw new ResourceNotFoundException("Order has not been placed!");
        else if (order.getStatus()==OrderStatus.CANCELLED) {
            throw new Exception("Order has been cancelled!");
        }
        orderDao.updateOrderStatus(orderId,updatedStatus);
    }

    public Object getOrderById(Integer orderId) throws ResourceNotFoundException {
        Order order = orderDao.getOrderByOrderId(orderId);
        if (order == null) throw new ResourceNotFoundException("Order has not been placed!");
        return order;
    }

    public Object getOrderByUserId(Integer userId) {
        return orderDao.getOrderByUserId(userId);
    }

    public void cancelOrder(Integer orderId) {
        orderDao.cancelOrder(orderId);
    }

    public Double getOrderTotal(Integer orderId) {
        return orderDao.getOrderTotal(orderId);
    }
}
