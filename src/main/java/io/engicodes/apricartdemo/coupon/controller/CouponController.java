package io.engicodes.apricartdemo.coupon.controller;

import io.engicodes.apricartdemo.coupon.service.CouponService;
import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {
    private final CouponService service;

    public CouponController(CouponService service) {
        this.service = service;
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateCouponCode(
            @RequestParam("coupon") String couponCode) throws ResourceNotFoundException {
        service.validateCouponCode(couponCode);
        return ResponseEntity.ok("Coupon code is valid.");
    }

    @PostMapping("/apply/{cartId}")
    public ResponseEntity<?> applyCouponDiscount(
            @PathVariable("cartId") Integer cartId,
            @RequestParam("coupon") String couponCode) throws ResourceNotFoundException {
        service.applyCouponCode(couponCode,cartId);
        return ResponseEntity.ok("Discount has been applied!");
    }
}
