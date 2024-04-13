package io.engicodes.apricartdemo.cart.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.engicodes.apricartdemo.cart.model.Cart;
import io.engicodes.apricartdemo.product.dao.ProductRepository;
import io.engicodes.apricartdemo.product.model.Product;
import io.engicodes.apricartdemo.user.User;
import io.engicodes.apricartdemo.warehouse.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CartJPADataAccess.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CartJPADataAccessTest {
    @Autowired
    private CartJPADataAccess cartJPADataAccess;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private ProductRepository productRepository;

    /**
     * Method under test: {@link CartJPADataAccess#addToCart(Integer, Integer)}
     */
    @Test
    void testAddToCart() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setProductId(new ArrayList<>());
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        Warehouse warehouseId2 = new Warehouse();
        warehouseId2.setAvaliableCapacity(42);
        warehouseId2.setCapacity(3);
        warehouseId2.setLocation("Location");
        warehouseId2.setWarehouseId(1);
        warehouseId2.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId2);
        when(productRepository.getProductByProductId(Mockito.<Integer>any())).thenReturn(product);

        // Act
        cartJPADataAccess.addToCart(1, 1);

        // Assert
        verify(cartRepository).getCartByCartId(eq(1));
        verify(productRepository).getProductByProductId(eq(1));
    }

    /**
     * Method under test: {@link CartJPADataAccess#addToCart(Integer, Integer)}
     */
    @Test
    void testAddToCart2() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");
        Cart cart = mock(Cart.class);
        doNothing().when(cart).addProductToCart(Mockito.<Product>any());
        doNothing().when(cart).increaseQuantity();
        doNothing().when(cart).setCartId(Mockito.<Integer>any());
        doNothing().when(cart).setProductId(Mockito.<List<Product>>any());
        doNothing().when(cart).setQuantity(Mockito.<Integer>any());
        doNothing().when(cart).setTotal(Mockito.<Double>any());
        doNothing().when(cart).setUserId(Mockito.<User>any());
        doNothing().when(cart).setWarehouseId(Mockito.<Warehouse>any());
        cart.setCartId(1);
        cart.setProductId(new ArrayList<>());
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        Warehouse warehouseId2 = new Warehouse();
        warehouseId2.setAvaliableCapacity(42);
        warehouseId2.setCapacity(3);
        warehouseId2.setLocation("Location");
        warehouseId2.setWarehouseId(1);
        warehouseId2.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId2);
        when(productRepository.getProductByProductId(Mockito.<Integer>any())).thenReturn(product);

        // Act
        cartJPADataAccess.addToCart(1, 1);

        // Assert that nothing has changed
        verify(cartRepository).getCartByCartId(eq(1));
        verify(cart).addProductToCart(isA(Product.class));
        verify(cart).increaseQuantity();
        verify(cart).setCartId(eq(1));
        verify(cart).setProductId(isA(List.class));
        verify(cart).setQuantity(eq(1));
        verify(cart).setTotal(eq(10.0d));
        verify(cart).setUserId(isA(User.class));
        verify(cart).setWarehouseId(isA(Warehouse.class));
        verify(productRepository).getProductByProductId(eq(1));
    }




    /**
     * Method under test: {@link CartJPADataAccess#removeFromCart(Integer, Integer)}
     */
    @Test
    void testRemoveFromCart() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setProductId(new ArrayList<>());
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        Warehouse warehouseId2 = new Warehouse();
        warehouseId2.setAvaliableCapacity(42);
        warehouseId2.setCapacity(3);
        warehouseId2.setLocation("Location");
        warehouseId2.setWarehouseId(1);
        warehouseId2.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId2);
        when(productRepository.getProductByProductId(Mockito.<Integer>any())).thenReturn(product);

        // Act
        cartJPADataAccess.removeFromCart(1, 1);

        // Assert
        verify(cartRepository).getCartByCartId(eq(1));
        verify(productRepository).getProductByProductId(eq(1));
    }

    /**
     * Method under test: {@link CartJPADataAccess#removeFromCart(Integer, Integer)}
     */
    @Test
    void testRemoveFromCart2() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");
        Cart cart = mock(Cart.class);
        doNothing().when(cart).decreaseQuantity();
        doNothing().when(cart).deleteProductFromCart(Mockito.<Product>any());
        doNothing().when(cart).setCartId(Mockito.<Integer>any());
        doNothing().when(cart).setProductId(Mockito.<List<Product>>any());
        doNothing().when(cart).setQuantity(Mockito.<Integer>any());
        doNothing().when(cart).setTotal(Mockito.<Double>any());
        doNothing().when(cart).setUserId(Mockito.<User>any());
        doNothing().when(cart).setWarehouseId(Mockito.<Warehouse>any());
        cart.setCartId(1);
        cart.setProductId(new ArrayList<>());
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        Warehouse warehouseId2 = new Warehouse();
        warehouseId2.setAvaliableCapacity(42);
        warehouseId2.setCapacity(3);
        warehouseId2.setLocation("Location");
        warehouseId2.setWarehouseId(1);
        warehouseId2.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId2);
        when(productRepository.getProductByProductId(Mockito.<Integer>any())).thenReturn(product);

        // Act
        cartJPADataAccess.removeFromCart(1, 1);

        // Assert that nothing has changed
        verify(cartRepository).getCartByCartId(eq(1));
        verify(cart).decreaseQuantity();
        verify(cart).deleteProductFromCart(isA(Product.class));
        verify(cart).setCartId(eq(1));
        verify(cart).setProductId(isA(List.class));
        verify(cart).setQuantity(eq(1));
        verify(cart).setTotal(eq(10.0d));
        verify(cart).setUserId(isA(User.class));
        verify(cart).setWarehouseId(isA(Warehouse.class));
        verify(productRepository).getProductByProductId(eq(1));
    }

    /**
     * Method under test: {@link CartJPADataAccess#clearCart(Integer)}
     */
    @Test
    void testClearCart() {
        // Arrange
        doNothing().when(cartRepository).deleteCartByCartId(Mockito.<Integer>any());

        // Act
        cartJPADataAccess.clearCart(1);

        // Assert that nothing has changed
        verify(cartRepository).deleteCartByCartId(eq(1));
    }

    /**
     * Method under test: {@link CartJPADataAccess#getCartItems(Integer)}
     */
    @Test
    void testGetCartItems() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Cart cart = new Cart();
        cart.setCartId(1);
        ArrayList<Product> productId = new ArrayList<>();
        cart.setProductId(productId);
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        // Act
        List<Product> actualCartItems = cartJPADataAccess.getCartItems(1);

        // Assert
        verify(cartRepository).getCartByCartId(eq(1));
        assertTrue(actualCartItems.isEmpty());
        assertSame(productId, actualCartItems);
    }

    /**
     * Method under test: {@link CartJPADataAccess#getCartItems(Integer)}
     */
    @Test
    void testGetCartItems2() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");
        Cart cart = mock(Cart.class);
        ArrayList<Product> productList = new ArrayList<>();
        when(cart.getProductId()).thenReturn(productList);
        doNothing().when(cart).setCartId(Mockito.<Integer>any());
        doNothing().when(cart).setProductId(Mockito.<List<Product>>any());
        doNothing().when(cart).setQuantity(Mockito.<Integer>any());
        doNothing().when(cart).setTotal(Mockito.<Double>any());
        doNothing().when(cart).setUserId(Mockito.<User>any());
        doNothing().when(cart).setWarehouseId(Mockito.<Warehouse>any());
        cart.setCartId(1);
        cart.setProductId(new ArrayList<>());
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        // Act
        List<Product> actualCartItems = cartJPADataAccess.getCartItems(1);

        // Assert
        verify(cartRepository).getCartByCartId(eq(1));
        verify(cart).getProductId();
        verify(cart).setCartId(eq(1));
        verify(cart).setProductId(isA(List.class));
        verify(cart).setQuantity(eq(1));
        verify(cart).setTotal(eq(10.0d));
        verify(cart).setUserId(isA(User.class));
        verify(cart).setWarehouseId(isA(Warehouse.class));
        assertTrue(actualCartItems.isEmpty());
        assertSame(productList, actualCartItems);
    }

    /**
     * Method under test: {@link CartJPADataAccess#getCartTotal(Integer)}
     */
    @Test
    void testGetCartTotal() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setProductId(new ArrayList<>());
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        // Act
        Double actualCartTotal = cartJPADataAccess.getCartTotal(1);

        // Assert
        verify(cartRepository).getCartByCartId(eq(1));
        assertEquals(0.0d, actualCartTotal.doubleValue());
    }

    /**
     * Method under test: {@link CartJPADataAccess#getCartTotal(Integer)}
     */
    @Test
    void testGetCartTotal2() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");
        Cart cart = mock(Cart.class);
        when(cart.getProductId()).thenReturn(new ArrayList<>());
        doNothing().when(cart).setCartId(Mockito.<Integer>any());
        doNothing().when(cart).setProductId(Mockito.<List<Product>>any());
        doNothing().when(cart).setQuantity(Mockito.<Integer>any());
        doNothing().when(cart).setTotal(Mockito.<Double>any());
        doNothing().when(cart).setUserId(Mockito.<User>any());
        doNothing().when(cart).setWarehouseId(Mockito.<Warehouse>any());
        cart.setCartId(1);
        cart.setProductId(new ArrayList<>());
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        // Act
        Double actualCartTotal = cartJPADataAccess.getCartTotal(1);

        // Assert
        verify(cartRepository).getCartByCartId(eq(1));
        verify(cart).getProductId();
        verify(cart).setCartId(eq(1));
        verify(cart).setProductId(isA(List.class));
        verify(cart).setQuantity(eq(1));
        verify(cart).setTotal(eq(10.0d));
        verify(cart).setUserId(isA(User.class));
        verify(cart).setWarehouseId(isA(Warehouse.class));
        assertEquals(0.0d, actualCartTotal.doubleValue());
    }

    /**
     * Method under test: {@link CartJPADataAccess#getCartByCartId(Integer)}
     */
    @Test
    void testGetCartByCartId() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setProductId(new ArrayList<>());
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        // Act
        Cart actualCartByCartId = cartJPADataAccess.getCartByCartId(1);

        // Assert
        verify(cartRepository).getCartByCartId(eq(1));
        assertSame(cart, actualCartByCartId);
    }

    /**
     * Method under test:
     * {@link CartJPADataAccess#getItemFromCartById(Integer, Integer)}
     */
    @Test
    void testGetItemFromCartById() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setProductId(new ArrayList<>());
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        Warehouse warehouseId2 = new Warehouse();
        warehouseId2.setAvaliableCapacity(42);
        warehouseId2.setCapacity(3);
        warehouseId2.setLocation("Location");
        warehouseId2.setWarehouseId(1);
        warehouseId2.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId2);
        when(productRepository.getProductByProductId(Mockito.<Integer>any())).thenReturn(product);

        // Act
        Product actualItemFromCartById = cartJPADataAccess.getItemFromCartById(1, 1);

        // Assert
        verify(cartRepository).getCartByCartId(eq(1));
        verify(productRepository).getProductByProductId(eq(1));
        assertNull(actualItemFromCartById);
    }

    /**
     * Method under test:
     * {@link CartJPADataAccess#getItemFromCartById(Integer, Integer)}
     */
    @Test
    void testGetItemFromCartById2() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");
        Cart cart = mock(Cart.class);
        when(cart.getProductId()).thenReturn(new ArrayList<>());
        doNothing().when(cart).setCartId(Mockito.<Integer>any());
        doNothing().when(cart).setProductId(Mockito.<List<Product>>any());
        doNothing().when(cart).setQuantity(Mockito.<Integer>any());
        doNothing().when(cart).setTotal(Mockito.<Double>any());
        doNothing().when(cart).setUserId(Mockito.<User>any());
        doNothing().when(cart).setWarehouseId(Mockito.<Warehouse>any());
        cart.setCartId(1);
        cart.setProductId(new ArrayList<>());
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        Warehouse warehouseId2 = new Warehouse();
        warehouseId2.setAvaliableCapacity(42);
        warehouseId2.setCapacity(3);
        warehouseId2.setLocation("Location");
        warehouseId2.setWarehouseId(1);
        warehouseId2.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId2);
        when(productRepository.getProductByProductId(Mockito.<Integer>any())).thenReturn(product);

        // Act
        Product actualItemFromCartById = cartJPADataAccess.getItemFromCartById(1, 1);

        // Assert
        verify(cartRepository).getCartByCartId(eq(1));
        verify(cart).getProductId();
        verify(cart).setCartId(eq(1));
        verify(cart).setProductId(isA(List.class));
        verify(cart).setQuantity(eq(1));
        verify(cart).setTotal(eq(10.0d));
        verify(cart).setUserId(isA(User.class));
        verify(cart).setWarehouseId(isA(Warehouse.class));
        verify(productRepository).getProductByProductId(eq(1));
        assertNull(actualItemFromCartById);
    }

    /**
     * Method under test:
     * {@link CartJPADataAccess#getItemFromCartById(Integer, Integer)}
     */
    @Test
    void testGetItemFromCartById3() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Warehouse warehouseId2 = new Warehouse();
        warehouseId2.setAvaliableCapacity(42);
        warehouseId2.setCapacity(3);
        warehouseId2.setLocation("Location");
        warehouseId2.setWarehouseId(1);
        warehouseId2.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId2);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        Cart cart = mock(Cart.class);
        when(cart.getProductId()).thenReturn(productList);
        doNothing().when(cart).setCartId(Mockito.<Integer>any());
        doNothing().when(cart).setProductId(Mockito.<List<Product>>any());
        doNothing().when(cart).setQuantity(Mockito.<Integer>any());
        doNothing().when(cart).setTotal(Mockito.<Double>any());
        doNothing().when(cart).setUserId(Mockito.<User>any());
        doNothing().when(cart).setWarehouseId(Mockito.<Warehouse>any());
        cart.setCartId(1);
        cart.setProductId(new ArrayList<>());
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        Warehouse warehouseId3 = new Warehouse();
        warehouseId3.setAvaliableCapacity(42);
        warehouseId3.setCapacity(3);
        warehouseId3.setLocation("Location");
        warehouseId3.setWarehouseId(1);
        warehouseId3.setWarehouseName("Warehouse Name");

        Product product2 = new Product();
        product2.setBrand("Brand");
        product2.setDescription("The characteristics of someone or something");
        product2.setPrice(10.0d);
        product2.setProductId(1);
        product2.setProductName("Product Name");
        product2.setStatus(true);
        product2.setWarehouseId(warehouseId3);
        when(productRepository.getProductByProductId(Mockito.<Integer>any())).thenReturn(product2);

        // Act
        Product actualItemFromCartById = cartJPADataAccess.getItemFromCartById(1, 1);

        // Assert
        verify(cartRepository).getCartByCartId(eq(1));
        verify(cart).getProductId();
        verify(cart).setCartId(eq(1));
        verify(cart).setProductId(isA(List.class));
        verify(cart).setQuantity(eq(1));
        verify(cart).setTotal(eq(10.0d));
        verify(cart).setUserId(isA(User.class));
        verify(cart).setWarehouseId(isA(Warehouse.class));
        verify(productRepository).getProductByProductId(eq(1));
        assertSame(product, actualItemFromCartById);
    }

    /**
     * Method under test:
     * {@link CartJPADataAccess#getItemFromCartById(Integer, Integer)}
     */
    @Test
    void testGetItemFromCartById4() {
        // Arrange
        User userId = new User();
        userId.setActive(true);
        userId.setEmail("jane.doe@example.org");
        userId.setFirstName("Jane");
        userId.setLastName("Doe");
        userId.setPassword("iloveyou");
        userId.setUserId(1);
        userId.setUsername("janedoe");

        Warehouse warehouseId = new Warehouse();
        warehouseId.setAvaliableCapacity(42);
        warehouseId.setCapacity(3);
        warehouseId.setLocation("Location");
        warehouseId.setWarehouseId(1);
        warehouseId.setWarehouseName("Warehouse Name");

        Warehouse warehouseId2 = new Warehouse();
        warehouseId2.setAvaliableCapacity(42);
        warehouseId2.setCapacity(3);
        warehouseId2.setLocation("Location");
        warehouseId2.setWarehouseId(1);
        warehouseId2.setWarehouseName("Warehouse Name");

        Product product = new Product();
        product.setBrand("Brand");
        product.setDescription("The characteristics of someone or something");
        product.setPrice(10.0d);
        product.setProductId(1);
        product.setProductName("Product Name");
        product.setStatus(true);
        product.setWarehouseId(warehouseId2);

        Warehouse warehouseId3 = new Warehouse();
        warehouseId3.setAvaliableCapacity(5);
        warehouseId3.setCapacity(5);
        warehouseId3.setLocation("io.engicodes.apricartdemo.warehouse.model.Warehouse");
        warehouseId3.setWarehouseId(2);
        warehouseId3.setWarehouseName("io.engicodes.apricartdemo.warehouse.model.Warehouse");

        Product product2 = new Product();
        product2.setBrand("io.engicodes.apricartdemo.product.model.Product");
        product2.setDescription("Description");
        product2.setPrice(0.5d);
        product2.setProductId(2);
        product2.setProductName("io.engicodes.apricartdemo.product.model.Product");
        product2.setStatus(false);
        product2.setWarehouseId(warehouseId3);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product2);
        productList.add(product);
        Cart cart = mock(Cart.class);
        when(cart.getProductId()).thenReturn(productList);
        doNothing().when(cart).setCartId(Mockito.<Integer>any());
        doNothing().when(cart).setProductId(Mockito.<List<Product>>any());
        doNothing().when(cart).setQuantity(Mockito.<Integer>any());
        doNothing().when(cart).setTotal(Mockito.<Double>any());
        doNothing().when(cart).setUserId(Mockito.<User>any());
        doNothing().when(cart).setWarehouseId(Mockito.<Warehouse>any());
        cart.setCartId(1);
        cart.setProductId(new ArrayList<>());
        cart.setQuantity(1);
        cart.setTotal(10.0d);
        cart.setUserId(userId);
        cart.setWarehouseId(warehouseId);
        when(cartRepository.getCartByCartId(Mockito.<Integer>any())).thenReturn(cart);

        Warehouse warehouseId4 = new Warehouse();
        warehouseId4.setAvaliableCapacity(42);
        warehouseId4.setCapacity(3);
        warehouseId4.setLocation("Location");
        warehouseId4.setWarehouseId(1);
        warehouseId4.setWarehouseName("Warehouse Name");

        Product product3 = new Product();
        product3.setBrand("Brand");
        product3.setDescription("The characteristics of someone or something");
        product3.setPrice(10.0d);
        product3.setProductId(1);
        product3.setProductName("Product Name");
        product3.setStatus(true);
        product3.setWarehouseId(warehouseId4);
        when(productRepository.getProductByProductId(Mockito.<Integer>any())).thenReturn(product3);

        // Act
        Product actualItemFromCartById = cartJPADataAccess.getItemFromCartById(1, 1);

        // Assert
        verify(cartRepository).getCartByCartId(eq(1));
        verify(cart).getProductId();
        verify(cart).setCartId(eq(1));
        verify(cart).setProductId(isA(List.class));
        verify(cart).setQuantity(eq(1));
        verify(cart).setTotal(eq(10.0d));
        verify(cart).setUserId(isA(User.class));
        verify(cart).setWarehouseId(isA(Warehouse.class));
        verify(productRepository).getProductByProductId(eq(1));
        assertSame(product, actualItemFromCartById);
    }
}
