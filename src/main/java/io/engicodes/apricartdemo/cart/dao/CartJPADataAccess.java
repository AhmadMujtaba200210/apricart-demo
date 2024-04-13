package io.engicodes.apricartdemo.cart.dao;

import io.engicodes.apricartdemo.cart.model.Cart;
import io.engicodes.apricartdemo.product.dao.ProductRepository;
import io.engicodes.apricartdemo.product.model.Product;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository("jpa-cart")
public class CartJPADataAccess implements CartDao {
    private final CartRepository repository;
    private final ProductRepository productRepository;

    public CartJPADataAccess(CartRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }


    @Override
    public void addToCart(Integer cartId, Integer productId) {
        Cart cart = repository.getCartByCartId(cartId);
        Product product = productRepository.getProductByProductId(productId);
        cart.addProductToCart(product);
        cart.increaseQuantity();
    }

    @Override
    public void updateProductQuantity(Integer cartId, Integer product) {

    }

    @Override
    public void removeFromCart(Integer cartId, Integer productId) {
        Cart cart = repository.getCartByCartId(cartId);
        Product product = productRepository.getProductByProductId(productId);
        cart.deleteProductFromCart(product);
        cart.decreaseQuantity();
    }
    @Override
    public void clearCart(Integer cartId) {
        repository.deleteCartByCartId(cartId);
    }

    @Override
    public List<Product> getCartItems(Integer cartId) {
        Cart cart = repository.getCartByCartId(cartId);
        return cart.getProductId();
    }

    @Override
    public Double getCartTotal(Integer cartId) {
        Double totalPrice = 0.0d;
        List<Product> products = getCartItems(cartId);
        //TODO: dummy logic as specifications are not completed
//        for (Product product : products) {
//            if (product) {
//                totalPrice = totalPrice.add(product.getPrice());
//            }
//        }

        return totalPrice;
    }

    @Override
    public Cart getCartByCartId(Integer cartId) {
        return repository.getCartByCartId(cartId);
    }

    @Override
    public Product getItemFromCartById(Integer cartId, Integer productId) {
        Cart cart = repository.getCartByCartId(cartId);
        Product product = productRepository.getProductByProductId(productId);

        if (cart != null && product != null) {
            return cart.getProductId()
                    .stream()
                    .filter(productInCart -> productInCart.getProductId().equals(productId))
                    .findFirst()
                    .orElse(null);
        } else {
            return null;
        }
    }

}