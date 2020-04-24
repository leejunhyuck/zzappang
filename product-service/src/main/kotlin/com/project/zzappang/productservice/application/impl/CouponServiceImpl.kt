package com.project.zzappang.productservice.application.impl

import com.project.zzappang.productservice.application.CouponService
import com.project.zzappang.productservice.domain.Coupon
import com.project.zzappang.productservice.dto.CouponDto
import com.project.zzappang.productservice.exception.CouponNotFoundException
import com.project.zzappang.productservice.repository.CouponRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class CouponServiceImpl(
        @Autowired private val couponRepository: CouponRepository
): CouponService {
    override fun getCoupons(): Flux<Coupon> {
        return couponRepository.findAll()
    }

    override fun getCoupon(couponId: String): Mono<Coupon> {
        ObjectId().toHexString()
        return couponRepository.findById(couponId).switchIfEmpty { throw CouponNotFoundException("coupon not found") }
    }

    override fun issueCoupon(issueCouponReq: Mono<CouponDto.IssueCouponReq>): Mono<Unit> {
        return Mono.empty() //Feign Client를 이용해 user service에 요청을 보내야한다.
    }

    override fun createCoupon(coupon: Mono<Coupon>): Mono<Coupon> {
        return coupon.flatMap { couponRepository.save(it) }
    }

    override fun updateCoupon(coupon: Mono<CouponDto.UpdateCouponReq>): Mono<Coupon> {
        return coupon.flatMap { couponDto ->
            getCoupon(couponDto._id).flatMap { couponRepository.save(couponDto.toEntity()) }
        }
    }

    override fun deleteCoupon(couponId: String): Mono<Void> {
        getCoupon(couponId).block()
        return couponRepository.deleteById(couponId)
    }

}