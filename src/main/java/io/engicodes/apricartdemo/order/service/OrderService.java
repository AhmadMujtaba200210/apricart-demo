package io.engicodes.apricartdemo.order.service;

import io.engicodes.apricartdemo.cart.dao.CartDao;
import io.engicodes.apricartdemo.cart.model.Cart;
import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import io.engicodes.apricartdemo.order.dao.OrderDao;
import io.engicodes.apricartdemo.order.model.Order;
import io.engicodes.apricartdemo.order.model.OrderStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Service layer for handling order-related operations.
 */
@Service
public class OrderService {
    private final OrderDao orderDao;
    private final CartDao cartDao;

    /**
     * Constructor injection of OrderDao and CartDao.
     * @param orderDao OrderDao instance.
     * @param cartDao CartDao instance.
     */
    public OrderService(@Qualifier("jpa-order") OrderDao orderDao, @Qualifier("jpa-cart") CartDao cartDao) {
        this.orderDao = orderDao;
        this.cartDao = cartDao;
    }

    /**
     * Places an order with the given cart ID and total price.
     * @param cartId The ID of the cart to place the order from.
     * @param totalPrice The total price of the order.
     * @throws Exception if placing the order fails.
     */
    public void placeOrder(Integer cartId, Double totalPrice) throws Exception {
        Cart cart = cartDao.getCartByCartId(cartId);
        if (cart == null) {
            throw new ResourceNotFoundException("Cart does not exist!");
        }
        if (cart.getQuantity() == 0) {
            throw new Exception("Cart is Empty!");
        }
        orderDao.placeOrder(cart, totalPrice);
    }

    /**
     * Updates the status of an order with the given order ID.
     * @param orderId The ID of the order to update.
     * @param updatedStatus The new status of the order.
     * @throws Exception if updating the order status fails.
     */
    public void updateOrderStatus(Integer orderId, OrderStatus updatedStatus) throws Exception {
        Order order = orderDao.getOrderByOrderId(orderId);
        if (order == null) {
            throw new ResourceNotFoundException("Order has not been placed!");
        } else if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new Exception("Order has been cancelled!");
        }
        orderDao.updateOrderStatus(orderId, updatedStatus);
    }

    /**
     * Retrieves an order by its order ID.
     * @param orderId The ID of the order to retrieve.
     * @return The order with the specified ID.
     * @throws ResourceNotFoundException if the order does not exist.
     */
    public Object getOrderById(Integer orderId) throws ResourceNotFoundException {
        Order order = orderDao.getOrderByOrderId(orderId);
        if (order == null) {
            throw new ResourceNotFoundException("Order has not been placed!");
        }
        return order;
    }

    /**
     * Retrieves orders by user ID.
     * @param userId The ID of the user to retrieve orders for.
     * @return The orders associated with the user ID.
     */
    public Object getOrderByUserId(Integer userId) {
        return orderDao.getOrderByUserId(userId);
    }

    /**
     * Cancels an order with the given order ID.
     * @param orderId The ID of the order to cancel.
     */
    public void cancelOrder(Integer orderId) {
        orderDao.cancelOrder(orderId);
    }

    /**
     * Retrieves the total price of an order.
     * @param orderId The ID of the order to retrieve the total price for.
     * @return The total price of the order.
     */
    public Double getOrderTotal(Integer orderId) {
        return orderDao.getOrderTotal(orderId);
    }
}
