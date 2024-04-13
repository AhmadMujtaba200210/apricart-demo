package io.engicodes.apricartdemo.coupon.service;

import io.engicodes.apricartdemo.cart.model.Cart;
import io.engicodes.apricartdemo.cart.service.CartService;
import io.engicodes.apricartdemo.coupon.dao.CouponDao;
import io.engicodes.apricartdemo.coupon.model.Coupon;
import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CouponService {
    private final CouponDao couponDao;
    private final CartService cartService;
    public CouponService(CouponDao couponDao, CartService cartService) {
        this.couponDao = couponDao;
        this.cartService = cartService;
    }

    public void validateCouponCode(String couponCode) throws ResourceNotFoundException {
        Coupon coupon = couponDao.validateCoupon(couponCode);
        if (coupon==null) throw new ResourceNotFoundException("Coupon not found!");
    }

    public void applyCouponCode(String couponCode, Integer cartId) throws ResourceNotFoundException {
        Cart cart = cartService.getCartByCartId(cartId);
        if (cart==null) throw new ResourceNotFoundException("Cart is empty!");
        Double cartTotal = cartService.getCartTotal(cartId);
        Double couponDiscount =couponDao.getCouponDiscount(couponCode);
        cart.setTotal(cartTotal-couponDiscount);
    }
}
