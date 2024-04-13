package io.engicodes.apricartdemo.coupon.dao;

import io.engicodes.apricartdemo.coupon.model.Coupon;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;



@Repository("jpa-coupon")
@Transactional
public class CouponJPADataAccess implements CouponDao{
    @Override
    public Coupon validateCoupon(String couponCode) {
        return null;
    }

    @Override
    public Coupon applyCoupon(String couponCode) {
        return null;
    }

    @Override
    public Double getCouponDiscount(String couponCode) {
        return null;
    }
}
