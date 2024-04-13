package io.engicodes.apricartdemo.cart.controller;

import io.engicodes.apricartdemo.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling cart-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    /**
     * Constructor injection of CartService.
     * @param cartService CartService instance.
     */
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Endpoint for adding an item to the cart.
     * @param cartId The ID of the cart to add the item to.
     * @param productId The ID of the product to add to the cart.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/addItem/{cartId}")
    public ResponseEntity<?> addToCart(
            @PathVariable("cartId") Integer cartId,
            @RequestParam("productId") Integer productId){
        cartService.addItemToCart(cartId, productId);
        return ResponseEntity.ok("Item has been added to cart!");
    }

    /**
     * Endpoint for updating the quantity of a product in the cart.
     * @param cartId The ID of the cart to update.
     * @param productId The ID of the product to update in the cart.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PutMapping("/updateItem/{cartId}")
    public ResponseEntity<?> updateCart(
            @PathVariable("cartId") Integer cartId,
            @RequestParam("productId") Integer productId){
        cartService.updateCart(cartId, productId);
        return ResponseEntity.ok("Product quantity has been increased!");
    }

    /**
     * Endpoint for removing a product from the cart.
     * @param cartId The ID of the cart to remove the product from.
     * @param productId The ID of the product to remove from the cart.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @DeleteMapping("/removeItem/{cartId}")
    public ResponseEntity<?> removeFromCart(
            @PathVariable("cartId") Integer cartId,
            @RequestParam("productId") Integer productId){
        cartService.removeFromCart(cartId, productId);
        return ResponseEntity.ok("Product has been removed successfully!");
    }

    /**
     * Endpoint for clearing the cart.
     * @param cartId The ID of the cart to clear.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @DeleteMapping("/clearCart/{cartId}")
    public ResponseEntity<?> clearCart(
            @PathVariable("cartId") Integer cartId
    ) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok("Cart has been cleared!");
    }

    /**
     * Endpoint for retrieving all items in the cart.
     * @param cartId The ID of the cart to retrieve items from.
     * @return ResponseEntity containing the items in the cart.
     */
    @GetMapping("/getCartItems/{cartId}")
    public ResponseEntity<?> allCartItems(
            @PathVariable("cartId") Integer cartId
    ){
        return ResponseEntity.ok(cartService.getCartItems(cartId));
    }

    /**
     * Endpoint for retrieving the total price of items in the cart.
     * @param cartId The ID of the cart to retrieve the total price for.
     * @return ResponseEntity containing the total price of items in the cart.
     */
    @GetMapping("/getCartTotal/{cartId}")
    public ResponseEntity<?> cartTotal(
            @PathVariable("cartId") Integer cartId
    ){
        return ResponseEntity.ok("Total: "+cartService.getCartTotal(cartId));
    }
}
