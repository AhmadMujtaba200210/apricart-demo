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

@Service
public class CartService
{
    private final CartDao cartDao;

    public CartService(@Qualifier("jpa-cart") CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public void addItemToCart(Integer cartId, Integer cartAddItem) {
        cartDao.addToCart(cartId,cartAddItem);
    }

    public void updateCart(Integer cartId, Integer productId) {
        cartDao.updateProductQuantity(cartId,productId);
    }

    public void removeFromCart(Integer cartId, Integer productId) {
        cartDao.removeFromCart(cartId,productId);
    }

    public void clearCart(Integer cartId) {
        cartDao.clearCart(cartId);
    }

    public List<Product> getCartItems(Integer cartId) {
        return cartDao.getCartItems(cartId);
    }

    public Double getCartTotal(Integer cartId) {
        return cartDao.getCartTotal(cartId);
    }
    public Cart getCartByCartId(Integer cartId) throws ResourceNotFoundException {
        Cart cart = cartDao.getCartByCartId(cartId);
        if (cart==null) throw new ResourceNotFoundException("Cart not found!");
        return cart;
    }
}
