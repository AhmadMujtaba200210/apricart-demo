package io.engicodes.apricartdemo.order.dao;

import io.engicodes.apricartdemo.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order getOrderByOrderId(Integer orderId);
    @Query(
            value = """
                    select o from _order o where o.userId=?1
                    """
    )
    Order getOrderByUserId(Integer userId);
}
