package io.engicodes.apricartdemo.cart.controller;

import io.engicodes.apricartdemo.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addItem/{cartId}")
    public ResponseEntity<?> addToCart(
            @PathVariable("cartId") Integer cartId,
            @RequestParam("productId")Integer productId){
        cartService.addItemToCart(cartId,productId);
        return ResponseEntity.ok("Item has been added to cart!");
    }

    @PutMapping("/updateItem/{cartId}")
    public ResponseEntity<?> updateCart(
            @PathVariable("cartId") Integer cartId,
            @RequestParam("productId")Integer productId){
        cartService.updateCart(cartId,productId);
        return ResponseEntity.ok("Product quantity has been increased!");
    }

    @DeleteMapping("/removeItem/{cartId}")
    public ResponseEntity<?> removeFromCart(
            @PathVariable("cartId") Integer cartId,
            @RequestParam("productId") Integer productId){
        cartService.removeFromCart(cartId,productId);
        return ResponseEntity.ok("Product has been removed successfully!");
    }

    @DeleteMapping("/clearCart/{cartId}")
    public ResponseEntity<?> clearCart(
            @PathVariable("cartId") Integer cartId
    ) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok("Cart has been cleared!");
    }

    @GetMapping("/getCartItems/{cartId}")
    public ResponseEntity<?> allCartItems(
            @PathVariable("cartId") Integer cartId
    ){
        return ResponseEntity.ok(cartService.getCartItems(cartId));
    }

    @GetMapping("/getCartTotal/{cartId}")
    public ResponseEntity<?> cartTotal(
            @PathVariable("cartId") Integer cartId
    ){
        return ResponseEntity.ok("Total: "+cartService.getCartTotal(cartId));
    }
}
