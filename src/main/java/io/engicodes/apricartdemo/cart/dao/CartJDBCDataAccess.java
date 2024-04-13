package io.engicodes.apricartdemo.cart.dao;

import io.engicodes.apricartdemo.cart.model.Cart;
import io.engicodes.apricartdemo.product.model.Product;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository("jdbc-cart")
public class CartJDBCDataAccess implements CartDao {


    @Override
    public void addToCart(Integer cartId, Integer productId) {

    }

    @Override
    public void updateProductQuantity(Integer cartId, Integer product) {

    }

    @Override
    public void removeFromCart(Integer cartId, Integer productId) {

    }

    @Override
    public void clearCart(Integer cartId) {

    }

    @Override
    public List<Product> getCartItems(Integer cardId) {
        return null;
    }

    @Override
    public Double getCartTotal(Integer cartId) {
        return null;
    }


    @Override
    public Cart getCartByCartId(Integer cartId) {
        return null;
    }

    @Override
    public Product getItemFromCartById(Integer cartId, Integer id) {
        return null;
    }


}
