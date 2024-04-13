package io.engicodes.apricartdemo.product.model;

import io.engicodes.apricartdemo.warehouse.model.Warehouse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_seq"
    )
    @SequenceGenerator(
            name = "product_seq",
            allocationSize = 1
    )
    private Integer productId;
    @Column(
            nullable = false
    )
    private String productName;
    private String description;
    @Column(
            nullable = false
    )
    private Double price;
    private String brand;
    @Column(
            nullable = false
    )
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "warehouseId")
    private Warehouse warehouseId;

    public Product(String productName, String description, Double price, String brand, Boolean status, Warehouse warehouseId) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.status = status;
        this.warehouseId = warehouseId;
    }
}
