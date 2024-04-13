package io.engicodes.apricartdemo.product.dao;


import io.engicodes.apricartdemo.product.controller.RequestUpdateProduct;
import io.engicodes.apricartdemo.product.model.Product;



public interface ProductDao {
    void createProduct(Product product);
    void updateProduct(Product productUpdate);
    void deleteProduct(Product product);
    Product getProductById(Integer productId);
    Product getProductByName(String productName);
}
