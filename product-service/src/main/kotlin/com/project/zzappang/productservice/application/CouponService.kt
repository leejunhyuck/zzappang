package com.project.zzappang.productservice.application

import com.project.zzappang.productservice.domain.Coupon
import com.project.zzappang.productservice.dto.CouponDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CouponService {
    fun getCoupons(): Flux<Coupon>
    fun getCoupon(couponId: String): Mono<Coupon>
    fun issueCoupon(issueCouponReq: Mono<CouponDto.IssueCouponReq>): Mono<Unit>
    fun createCoupon(coupon: Mono<Coupon>): Mono<Coupon>
    fun updateCoupon(coupon: Mono<CouponDto.UpdateCouponReq>): Mono<Coupon>
    fun deleteCoupon(couponId: String): Mono<Void>
}