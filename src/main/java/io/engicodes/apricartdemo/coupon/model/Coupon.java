package io.engicodes.apricartdemo.coupon.model;

import io.engicodes.apricartdemo.warehouse.model.Warehouse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "coupon_seq"
    )
    @SequenceGenerator(
            name = "coupon_seq",
            allocationSize = 1
    )
    private Integer couponId;
    private String couponCode;
    private String city;
    private Double discountAmount;
    @Enumerated(
            value = EnumType.STRING
    )
    private CouponType couponType;
    private Double minOrderAmount;
    private boolean isActive;
    @OneToOne
    @JoinColumn(name = "warehouseId")
    private Warehouse warehouseId;
}
