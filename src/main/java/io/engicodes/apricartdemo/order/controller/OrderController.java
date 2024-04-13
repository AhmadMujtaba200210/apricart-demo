package io.engicodes.apricartdemo.order.controller;

import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import io.engicodes.apricartdemo.order.model.OrderStatus;
import io.engicodes.apricartdemo.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling order-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    /**
     * Constructor injection of OrderService.
     * @param orderService OrderService instance.
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Endpoint for placing an order.
     * @param cartId The ID of the cart to place the order from.
     * @param totalPrice The total price of the order.
     * @return ResponseEntity indicating success or failure of the operation.
     * @throws Exception if placing the order fails.
     */
    @PostMapping("/addItem/{cartId}")
    public ResponseEntity<?> placeOrder(
            @PathVariable("cartId") Integer cartId,
            @RequestParam("totalPrice") Double totalPrice
    ) throws Exception {
        orderService.placeOrder(cartId, totalPrice);
        return ResponseEntity.ok("Order has been placed!");
    }

    /**
     * Endpoint for updating the status of an order.
     * @param orderId The ID of the order to update.
     * @param status The new status of the order.
     * @return ResponseEntity indicating success or failure of the operation.
     * @throws Exception if updating the order status fails.
     */
    @PutMapping("/updateItem/{orderId}")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable("orderId") Integer orderId,
            @RequestParam("status") OrderStatus status
    ) throws Exception {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("Order status has been updated!");
    }

    /**
     * Endpoint for retrieving an order by its ID.
     * @param orderId The ID of the order to retrieve.
     * @return ResponseEntity containing the order details.
     * @throws ResourceNotFoundException if the order does not exist.
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(
            @PathVariable("orderId") Integer orderId
    ) throws ResourceNotFoundException {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    /**
     * Endpoint for retrieving orders by user ID.
     * @param userId The ID of the user to retrieve orders for.
     * @return ResponseEntity containing the orders associated with the user ID.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrderByUserId(
            @PathVariable("userId") Integer userId
    ){
        return ResponseEntity.ok(orderService.getOrderByUserId(userId));
    }

    /**
     * Endpoint for cancelling an order.
     * @param orderId The ID of the order to cancel.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<?> cancelOrder(
            @PathVariable("orderId") Integer orderId
    ){
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order has been cancelled!");
    }

    /**
     * Endpoint for retrieving the total price of an order.
     * @param orderId The ID of the order to retrieve the total price for.
     * @return ResponseEntity containing the total price of the order.
     */
    @GetMapping("/getOrderTotal/{orderId}")
    public ResponseEntity<?> orderTotal(
            @PathVariable("orderId") Integer orderId
    ){
        return ResponseEntity.ok("Total: "+orderService.getOrderTotal(orderId));
    }
}
