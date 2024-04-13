package io.engicodes.apricartdemo.coupon.dao;

import io.engicodes.apricartdemo.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Coupon findCouponByCouponId(Integer couponId);
    Coupon findCouponByCouponCode(String couponCode);
    @Query(value = """
                select c.discountAmount from Coupon c where c.couponId=?1
            """)
    Double getCouponDiscountAmount(Integer couponId);
}
