package io.engicodes.apricartdemo.product.dao;

import io.engicodes.apricartdemo.product.model.Product;
import org.springframework.stereotype.Repository;



@Repository("jpa")
public class ProductJPADataAccess implements ProductDao {
    private final ProductRepository repository;

    public ProductJPADataAccess(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public void createProduct(Product product) {
        repository.save(product);
    }

    @Override
    public void updateProduct(Product productUpdate) {
        repository.save(productUpdate);
    }

    @Override
    public void deleteProduct(Product product) {
        repository.delete(product);
    }

    @Override
    public Product getProductById(Integer productId) {
        return repository.getProductByProductId(productId);
    }

    @Override
    public Product getProductByName(String productName) {
        return repository.getProductByProductName(productName);
    }
}
