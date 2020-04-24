package com.project.zzappang.productservice.handler

import com.project.zzappang.productservice.application.CouponService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.status
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono

@Component
class CouponHandler(
        @Autowired private val couponService: CouponService
) {
    fun getCoupons(serverRequest: ServerRequest): Mono<ServerResponse> {
        return status(HttpStatus.OK).body(couponService.getCoupons())
    }

    fun getCoupon(serverRequest: ServerRequest): Mono<ServerResponse> {
        return status(HttpStatus.OK).body(couponService.getCoupon(serverRequest.pathVariable("id")))
    }

    fun issueCoupon(serverRequest: ServerRequest): Mono<ServerResponse> {
        return couponService.issueCoupon(serverRequest.bodyToMono()).flatMap {
            status(HttpStatus.NO_CONTENT).build()
        }
    }

    fun createCoupon(serverRequest: ServerRequest): Mono<ServerResponse> {
        return status(HttpStatus.CREATED).body(couponService.createCoupon(serverRequest.bodyToMono()))
    }

    fun updateCoupon(serverRequest: ServerRequest): Mono<ServerResponse> {
        return status(HttpStatus.OK).body(couponService.updateCoupon(serverRequest.bodyToMono()))
    }

    fun deleteCoupon(serverRequest: ServerRequest): Mono<ServerResponse> {
        return couponService.deleteCoupon(serverRequest.pathVariable("id")).flatMap {
            println(HttpStatus.NO_CONTENT.value())
            status(204).build()
        }
    }
}