package io.engicodes.apricartdemo.product.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.engicodes.apricartdemo.product.model.Product;
import io.engicodes.apricartdemo.warehouse.model.Warehouse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductJPADataAccess.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductJPADataAccessTest {
    @Autowired
    private ProductJPADataAccess productJPADataAccess;

    @MockBean
    private ProductRepository productRepository;

    /**
     * Method under test: {@link ProductJPADataAccess#createProduct(Product)}
     */
    @Test
    void testCreateProduct() {
        // Arrange
        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product);

        Warehouse warehouseId2 = new Warehouse();
        warehouseId2.setAvaliableCapacity(42);
        warehouseId2.setCapacity(3);
        warehouseId2.setLocation("Location");
        warehouseId2.setWarehouseId(1);
        warehouseId2.setWarehouseName("Warehouse Name");

        Product product2 = new Product();
        product2.setBrand("Brand");
        product2.setDescription("The characteristics of someone or something");
        product2.setPrice(10.0d);
        product2.setProductId(1);
        product2.setProductName("Product Name");
        product2.setStatus(true);
        product2.setWarehouseId(warehouseId2);

        // Act
        productJPADataAccess.createProduct(product2);

        // Assert that nothing has changed
        verify(productRepository).save(isA(Product.class));
        assertEquals("Brand", product2.getBrand());
        assertEquals("Product Name", product2.getProductName());
        assertEquals("The characteristics of someone or something", product2.getDescription());
        assertEquals(1, product2.getProductId().intValue());
        assertEquals(10.0d, product2.getPrice().doubleValue());
        assertTrue(product2.getStatus());
        assertEquals(warehouseId, product2.getWarehouseId());
    }

    /**
     * Method under test: {@link ProductJPADataAccess#updateProduct(Product)}
     */
    @Test
    void testUpdateProduct() {
        // Arrange
        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product);

        Warehouse warehouseId2 = new Warehouse();
        warehouseId2.setAvaliableCapacity(42);
        warehouseId2.setCapacity(3);
        warehouseId2.setLocation("Location");
        warehouseId2.setWarehouseId(1);
        warehouseId2.setWarehouseName("Warehouse Name");

        Product productUpdate = new Product();
        productUpdate.setBrand("Brand");
        productUpdate.setDescription("The characteristics of someone or something");
        productUpdate.setPrice(10.0d);
        productUpdate.setProductId(1);
        productUpdate.setProductName("Product Name");
        productUpdate.setStatus(true);
        productUpdate.setWarehouseId(warehouseId2);

        // Act
        productJPADataAccess.updateProduct(productUpdate);

        // Assert that nothing has changed
        verify(productRepository).save(isA(Product.class));
        assertEquals("Brand", productUpdate.getBrand());
        assertEquals("Product Name", productUpdate.getProductName());
        assertEquals("The characteristics of someone or something", productUpdate.getDescription());
        assertEquals(1, productUpdate.getProductId().intValue());
        assertEquals(10.0d, productUpdate.getPrice().doubleValue());
        assertTrue(productUpdate.getStatus());
        assertEquals(warehouseId, productUpdate.getWarehouseId());
    }

    /**
     * Method under test: {@link ProductJPADataAccess#deleteProduct(Product)}
     */
    @Test
    void testDeleteProduct() {
        // Arrange
        doNothing().when(productRepository).delete(Mockito.<Product>any());

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId);

        // Act
        productJPADataAccess.deleteProduct(product);

        // Assert that nothing has changed
        verify(productRepository).delete(isA(Product.class));
        assertEquals("Brand", product.getBrand());
        assertEquals("Product Name", product.getProductName());
        assertEquals("The characteristics of someone or something", product.getDescription());
        assertEquals(1, product.getProductId().intValue());
        assertEquals(10.0d, product.getPrice().doubleValue());
        assertEquals(42, product.getWarehouseId().getAvaliableCapacity().intValue());
        assertTrue(product.getStatus());
    }

    /**
     * Method under test: {@link ProductJPADataAccess#getProductById(Integer)}
     */
    @Test
    void testGetProductById() {
        // Arrange
        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId);
        when(productRepository.getProductByProductId(Mockito.<Integer>any())).thenReturn(product);

        // Act
        Product actualProductById = productJPADataAccess.getProductById(1);

        // Assert
        verify(productRepository).getProductByProductId(eq(1));
        assertSame(product, actualProductById);
    }

    /**
     * Method under test: {@link ProductJPADataAccess#getProductByName(String)}
     */
    @Test
    void testGetProductByName() {
        // Arrange
        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId);
        when(productRepository.getProductByProductName(Mockito.<String>any())).thenReturn(product);

        // Act
        Product actualProductByName = productJPADataAccess.getProductByName("Product Name");

        // Assert
        verify(productRepository).getProductByProductName(eq("Product Name"));
        assertSame(product, actualProductByName);
    }
}
