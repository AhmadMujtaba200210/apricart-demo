package io.engicodes.apricartdemo.product.dao;

import io.engicodes.apricartdemo.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product getProductByProductId(Integer id);
    Product getProductByProductName(String name);

}
