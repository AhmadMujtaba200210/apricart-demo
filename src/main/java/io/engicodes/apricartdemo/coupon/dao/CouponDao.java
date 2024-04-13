package io.engicodes.apricartdemo.coupon.dao;

import io.engicodes.apricartdemo.coupon.model.Coupon;



public interface CouponDao {
    Coupon validateCoupon(String couponCode);
    Coupon applyCoupon(String couponCode);
    Double getCouponDiscount(String couponCode);
}
