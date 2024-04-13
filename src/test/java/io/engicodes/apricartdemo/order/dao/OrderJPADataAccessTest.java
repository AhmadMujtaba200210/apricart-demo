package io.engicodes.apricartdemo.order.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.engicodes.apricartdemo.cart.model.Cart;
import io.engicodes.apricartdemo.order.model.Order;
import io.engicodes.apricartdemo.order.model.OrderStatus;
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

@ContextConfiguration(classes = {OrderJPADataAccess.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class OrderJPADataAccessTest {
    @Autowired
    private OrderJPADataAccess orderJPADataAccess;

    @MockBean
    private OrderRepository orderRepository;

    /**
     * Method under test: {@link OrderJPADataAccess#placeOrder(Cart, Double)}
     */
    @Test
    void testPlaceOrder() {
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
        when(cart.getQuantity()).thenReturn(1);
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

        // Act
        orderJPADataAccess.placeOrder(cart, 10.0d);

        // Assert
        verify(cart).getProductId();
        verify(cart).getQuantity();
        verify(cart).setCartId(eq(1));
        verify(cart).setProductId(isA(List.class));
        verify(cart).setQuantity(eq(1));
        verify(cart).setTotal(eq(10.0d));
        verify(cart).setUserId(isA(User.class));
        verify(cart).setWarehouseId(isA(Warehouse.class));
    }

    /**
     * Method under test: {@link OrderJPADataAccess#cancelOrder(Integer)}
     */
    @Test
    void testCancelOrder() {
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

        Order order = new Order();
        order.setOrderId(1);
        order.setProductId(new ArrayList<>());
        order.setQuantity(1);
        order.setStatus(OrderStatus.INPROCESS);
        order.setTotalPrice(10.0d);
        order.setUserId(userId);
        order.setWarehouseId(warehouseId);
        when(orderRepository.getOrderByOrderId(Mockito.<Integer>any())).thenReturn(order);

        // Act
        orderJPADataAccess.cancelOrder(1);

        // Assert
        verify(orderRepository).getOrderByOrderId(eq(1));
    }

    /**
     * Method under test:
     * {@link OrderJPADataAccess#updateOrderStatus(Integer, OrderStatus)}
     */
    @Test
    void testUpdateOrderStatus() {
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

        Order order = new Order();
        order.setOrderId(1);
        order.setProductId(new ArrayList<>());
        order.setQuantity(1);
        order.setStatus(OrderStatus.INPROCESS);
        order.setTotalPrice(10.0d);
        order.setUserId(userId);
        order.setWarehouseId(warehouseId);

        User userId2 = new User();
        userId2.setActive(true);
        userId2.setEmail("jane.doe@example.org");
        userId2.setFirstName("Jane");
        userId2.setLastName("Doe");
        userId2.setPassword("iloveyou");
        userId2.setUserId(1);
        userId2.setUsername("janedoe");

        Warehouse warehouseId2 = new Warehouse();
        warehouseId2.setAvaliableCapacity(42);
        warehouseId2.setCapacity(3);
        warehouseId2.setLocation("Location");
        warehouseId2.setWarehouseId(1);
        warehouseId2.setWarehouseName("Warehouse Name");

        Order order2 = new Order();
        order2.setOrderId(1);
        order2.setProductId(new ArrayList<>());
        order2.setQuantity(1);
        order2.setStatus(OrderStatus.INPROCESS);
        order2.setTotalPrice(10.0d);
        order2.setUserId(userId2);
        order2.setWarehouseId(warehouseId2);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.getOrderByOrderId(Mockito.<Integer>any())).thenReturn(order);

        // Act
        orderJPADataAccess.updateOrderStatus(1, OrderStatus.INPROCESS);

        // Assert
        verify(orderRepository).getOrderByOrderId(eq(1));
        verify(orderRepository).save(isA(Order.class));
    }

    /**
     * Method under test: {@link OrderJPADataAccess#getOrderByUserId(Integer)}
     */
    @Test
    void testGetOrderByUserId() {
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

        Order order = new Order();
        order.setOrderId(1);
        order.setProductId(new ArrayList<>());
        order.setQuantity(1);
        order.setStatus(OrderStatus.INPROCESS);
        order.setTotalPrice(10.0d);
        order.setUserId(userId);
        order.setWarehouseId(warehouseId);
        when(orderRepository.getOrderByUserId(Mockito.<Integer>any())).thenReturn(order);

        // Act
        Order actualOrderByUserId = orderJPADataAccess.getOrderByUserId(1);

        // Assert
        verify(orderRepository).getOrderByUserId(eq(1));
        assertSame(order, actualOrderByUserId);
    }

    /**
     * Method under test: {@link OrderJPADataAccess#getOrderByOrderId(Integer)}
     */
    @Test
    void testGetOrderByOrderId() {
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

        Order order = new Order();
        order.setOrderId(1);
        order.setProductId(new ArrayList<>());
        order.setQuantity(1);
        order.setStatus(OrderStatus.INPROCESS);
        order.setTotalPrice(10.0d);
        order.setUserId(userId);
        order.setWarehouseId(warehouseId);
        when(orderRepository.getOrderByOrderId(Mockito.<Integer>any())).thenReturn(order);

        // Act
        Order actualOrderByOrderId = orderJPADataAccess.getOrderByOrderId(1);

        // Assert
        verify(orderRepository).getOrderByOrderId(eq(1));
        assertSame(order, actualOrderByOrderId);
    }

    /**
     * Method under test: {@link OrderJPADataAccess#getOrderTotal(Integer)}
     */
    @Test
    void testGetOrderTotal() {
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

        Order order = new Order();
        order.setOrderId(1);
        order.setProductId(new ArrayList<>());
        order.setQuantity(1);
        order.setStatus(OrderStatus.INPROCESS);
        order.setTotalPrice(10.0d);
        order.setUserId(userId);
        order.setWarehouseId(warehouseId);
        when(orderRepository.getOrderByOrderId(Mockito.<Integer>any())).thenReturn(order);

        // Act
        Double actualOrderTotal = orderJPADataAccess.getOrderTotal(1);

        // Assert
        verify(orderRepository).getOrderByOrderId(eq(1));
        assertEquals(10.0d, actualOrderTotal.doubleValue());
    }
}
