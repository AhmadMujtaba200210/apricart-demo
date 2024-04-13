package io.engicodes.apricartdemo.product.controller;

import io.engicodes.apricartdemo.exceptions.ResourceDuplicationException;
import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import io.engicodes.apricartdemo.product.model.Product;
import io.engicodes.apricartdemo.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling product-related HTTP requests.
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    /**
     * Constructor injection of ProductService.
     * @param productService ProductService instance.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Endpoint to retrieve a product by its ID.
     * @param id The ID of the product.
     * @return ResponseEntity containing the product.
     * @throws ResourceNotFoundException if the product is not found.
     */
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(
            @PathVariable("productId") Integer id
    ) throws ResourceNotFoundException {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Endpoint to create a new product.
     * @param requestCreateProduct The request containing product details.
     * @return ResponseEntity indicating success.
     * @throws ResourceDuplicationException if the product already exists.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(
            @RequestBody RequestCreateProduct requestCreateProduct
    ) throws ResourceDuplicationException {
        productService.createProduct(requestCreateProduct);
        return ResponseEntity.ok("New product has been added!");
    }

    /**
     * Endpoint to delete a product by its ID.
     * @param id The ID of the product to delete.
     * @return ResponseEntity indicating success.
     */
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProductById(
            @PathVariable("productId") Integer id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product has been deleted!");
    }

    /**
     * Endpoint to update a product by its ID.
     * @param id The ID of the product to update.
     * @param requestUpdateProduct The request containing updated product details.
     * @return ResponseEntity indicating success.
     * @throws ResourceNotFoundException if the product is not found.
     */
    @PutMapping("/update/{productId}")
    public ResponseEntity<?> updateProductById(
            @PathVariable("productId") Integer id,
            @RequestBody RequestUpdateProduct requestUpdateProduct
    ) throws ResourceNotFoundException {
        productService.updateProduct(id, requestUpdateProduct);
        return ResponseEntity.ok("Product has been updated!");
    }
}
