package io.engicodes.apricartdemo.cart.dao;

import io.engicodes.apricartdemo.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart getCartByCartId(Integer cartId);
    void deleteCartByCartId(Integer cartId);

}
