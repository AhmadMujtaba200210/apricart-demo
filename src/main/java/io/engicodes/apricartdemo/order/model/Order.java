package io.engicodes.apricartdemo.order.model;

import io.engicodes.apricartdemo.product.model.Product;
import io.engicodes.apricartdemo.user.User;
import io.engicodes.apricartdemo.warehouse.model.Warehouse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity(name = "_order")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_seq"
    )
    @SequenceGenerator(
            name = "order_seq",
            allocationSize = 1
    )
    private Integer orderId;
    @OneToMany
    @JoinColumn(name = "productId")
    private List<Product> productId;
    @OneToOne
    @JoinColumn(name = "userId")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "warehouseId")
    private Warehouse warehouseId;
    private Integer quantity;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    @Column(
            nullable = false
    )
    private Double totalPrice;

}
