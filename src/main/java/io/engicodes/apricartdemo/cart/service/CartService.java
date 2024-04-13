package io.engicodes.apricartdemo.cart.service;

import io.engicodes.apricartdemo.cart.controller.RequestCartAddItem;
import io.engicodes.apricartdemo.cart.controller.RequestUpdateCartItem;
import io.engicodes.apricartdemo.cart.dao.CartDao;
import io.engicodes.apricartdemo.cart.model.Cart;
import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import io.engicodes.apricartdemo.product.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling cart-related operations.
 */
@Service
public class CartService
{
    private final CartDao cartDao;

    /**
     * Constructor injection of CartDao.
     * @param cartDao CartDao instance.
     */
    public CartService(@Qualifier("jpa-cart") CartDao cartDao) {
        this.cartDao = cartDao;
    }

    /**
     * Adds an item to the cart.
     * @param cartId The ID of the cart.
     * @param cartAddItem The ID of the item to add to the cart.
     */
    public void addItemToCart(Integer cartId, Integer cartAddItem) {
        cartDao.addToCart(cartId,cartAddItem);
    }

    /**
     * Updates the quantity of a product in the cart.
     * @param cartId The ID of the cart.
     * @param productId The ID of the product to update.
     */
    public void updateCart(Integer cartId, Integer productId) {
        cartDao.updateProductQuantity(cartId,productId);
    }

    /**
     * Removes a product from the cart.
     * @param cartId The ID of the cart.
     * @param productId The ID of the product to remove.
     */
    public void removeFromCart(Integer cartId, Integer productId) {
        cartDao.removeFromCart(cartId,productId);
    }

    /**
     * Clears the cart.
     * @param cartId The ID of the cart to clear.
     */
    public void clearCart(Integer cartId) {
        cartDao.clearCart(cartId);
    }

    /**
     * Retrieves all items in the cart.
     * @param cartId The ID of the cart.
     * @return List of products in the cart.
     */
    public List<Product> getCartItems(Integer cartId) {
        return cartDao.getCartItems(cartId);
    }

    /**
     * Calculates the total price of items in the cart.
     * @param cartId The ID of the cart.
     * @return The total price of items in the cart.
     */
    public Double getCartTotal(Integer cartId) {
        return cartDao.getCartTotal(cartId);
    }

    /**
     * Retrieves the cart by its ID.
     * @param cartId The ID of the cart.
     * @return The Cart object.
     * @throws ResourceNotFoundException If the cart is not found.
     */
    public Cart getCartByCartId(Integer cartId) throws ResourceNotFoundException {
        Cart cart = cartDao.getCartByCartId(cartId);
        if (cart==null) throw new ResourceNotFoundException("Cart not found!");
        return cart;
    }
}
