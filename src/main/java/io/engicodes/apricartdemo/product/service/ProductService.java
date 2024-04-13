package io.engicodes.apricartdemo.product.service;

import io.engicodes.apricartdemo.exceptions.ResourceDuplicationException;
import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import io.engicodes.apricartdemo.product.controller.RequestCreateProduct;
import io.engicodes.apricartdemo.product.controller.RequestUpdateProduct;
import io.engicodes.apricartdemo.product.dao.ProductDao;
import io.engicodes.apricartdemo.product.model.Product;
import io.engicodes.apricartdemo.warehouse.dao.WarehouseDao;
import io.engicodes.apricartdemo.warehouse.model.Warehouse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Service layer for handling product-related operations.
 */
@Service
public class ProductService {
    private final ProductDao productDao;
    private final WarehouseDao warehouseDao;
    /**
     * Constructor injection of ProductDao.
     * @param productDao ProductDao instance.
     */
    public ProductService(@Qualifier("jpa") ProductDao productDao, WarehouseDao warehouseDao) {
        this.productDao = productDao;
        this.warehouseDao = warehouseDao;
    }

    /**
     * Retrieves a product by its ID.
     * @param id The ID of the product.
     * @return The product.
     * @throws ResourceNotFoundException if the product is not found.
     */
    public Product getProductById(Integer id) throws ResourceNotFoundException {
        Product product = productDao.getProductById(id);
        if (product == null) throw new ResourceNotFoundException("Product Not Found!");
        return product;
    }

    /**
     * Creates a new product.
     * @param requestCreateProduct The request containing product details.
     * @throws ResourceDuplicationException if the product already exists.
     */
    public void createProduct(RequestCreateProduct requestCreateProduct) throws ResourceDuplicationException {
        Warehouse warehouse = warehouseDao.getWareHouseById(requestCreateProduct.warehouseId());
        Product product = new Product(
                requestCreateProduct.name(),
                requestCreateProduct.description(),
                requestCreateProduct.price(),
                requestCreateProduct.brand(),
                requestCreateProduct.status(),
                warehouse
        );
        productDao.createProduct(product);
    }

    /**
     * Deletes a product by its ID.
     * @param id The ID of the product to delete.
     */
    public void deleteProduct(Integer id) {
        Product product = productDao.getProductById(id);
        productDao.deleteProduct(product);
    }

    /**
     * Updates a product by its ID.
     * @param id The ID of the product to update.
     * @param requestUpdateProduct The request containing updated product details.
     * @throws ResourceNotFoundException if the product is not found.
     */
    public void updateProduct(Integer id, RequestUpdateProduct requestUpdateProduct) throws ResourceNotFoundException {
        Product product = getProductById(id);
        if (product == null) throw new ResourceNotFoundException("Product not found!");

        if (!product.getProductName().equals(requestUpdateProduct.name())){
            product.setProductName(requestUpdateProduct.name());
        }
        if (!product.getBrand().equals(requestUpdateProduct.brand())){
            product.setBrand(requestUpdateProduct.brand());
        }
        if (!product.getPrice().equals(requestUpdateProduct.price())){
            product.setPrice(requestUpdateProduct.price());
        }
        if (!product.getDescription().equals(requestUpdateProduct.description())){
            product.setDescription(requestUpdateProduct.description());
        }
        if (!product.getWarehouseId().equals(requestUpdateProduct.warehouseId())){
            product.setWarehouseId(requestUpdateProduct.warehouseId());
        }
        productDao.updateProduct(product);
    }
}
