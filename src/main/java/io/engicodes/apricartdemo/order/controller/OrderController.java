package io.engicodes.apricartdemo.order.controller;

import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import io.engicodes.apricartdemo.order.model.OrderStatus;
import io.engicodes.apricartdemo.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/addItem/{cartId}")
    public ResponseEntity<?> placeOrder(
            @PathVariable("cartId") Integer cartId,
            @RequestParam("totalPrice") Double totalPrice
    ) throws Exception {
        orderService.placeOrder(cartId,totalPrice);
        return ResponseEntity.ok("Order has been place!");
    }

    @PutMapping("/updateItem/{orderId}")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable("orderId") Integer orderId,
            @RequestParam("status") OrderStatus status
    ) throws Exception {
        orderService.updateOrderStatus(orderId,status);
        return ResponseEntity.ok("Product quantity has been increased!");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(
            @PathVariable("orderId") Integer orderId
    ) throws ResourceNotFoundException {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrderByUserId(
            @PathVariable("userId") Integer orderId
    ){
        return ResponseEntity.ok(orderService.getOrderByUserId(orderId));
    }

    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<?> cancelOrder(
            @PathVariable("orderId") Integer orderId
    ){
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order has been cancelled!");
    }

    @GetMapping("/getOrderTotal/{orderId}")
    public ResponseEntity<?> orderTotal(
            @PathVariable("orderId") Integer orderId
    ){
        return ResponseEntity.ok("Total: "+orderService.getOrderTotal(orderId));
    }
}
