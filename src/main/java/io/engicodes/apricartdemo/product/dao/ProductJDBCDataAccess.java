package io.engicodes.apricartdemo.product.dao;

import io.engicodes.apricartdemo.product.controller.RequestUpdateProduct;
import io.engicodes.apricartdemo.product.model.Product;
import org.springframework.stereotype.Repository;



@Repository("jdbc")
public class ProductJDBCDataAccess implements ProductDao{

    @Override
    public void createProduct(Product product) {

    }

    @Override
    public void updateProduct(Product productUpdate) {

    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public Product getProductById(Integer productId) {
        return null;
    }

    @Override
    public Product getProductByName(String productName) {
        return null;
    }
}
