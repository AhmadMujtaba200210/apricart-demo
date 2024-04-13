package io.engicodes.apricartdemo.cart.dao;


import io.engicodes.apricartdemo.cart.model.Cart;
import io.engicodes.apricartdemo.product.model.Product;



import java.util.List;

public interface CartDao {
    void addToCart(Integer cartId,Integer productId);
    void updateProductQuantity(Integer cartId,Integer product);
    void removeFromCart(Integer cartId,Integer productId);
    void clearCart(Integer cartId);
    List<Product> getCartItems(Integer cartId);
    Double getCartTotal(Integer cartId);
    Cart getCartByCartId(Integer cartId);
    Product getItemFromCartById(Integer cartId,Integer id);
}
