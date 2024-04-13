package io.engicodes.apricartdemo.warehouse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "warehouse_seq"
    )
    @SequenceGenerator(
            name = "warehouse_seq",
            allocationSize = 1
    )
    private Integer warehouseId;
    @Column(
            nullable = false,
            unique = true
    )
    private String warehouseName;
    @Column(
            nullable = false
    )
    private String location;
    @Column(
            nullable = false
    )
    private Integer capacity;
    @Column(
            nullable = false
    )
    private Integer avaliableCapacity;

    public Warehouse(String warehouseName, String location, Integer capacity, Integer avaliableCapacity) {
        this.warehouseName = warehouseName;
        this.location = location;
        this.capacity = capacity;
        this.avaliableCapacity = avaliableCapacity;
    }
}
