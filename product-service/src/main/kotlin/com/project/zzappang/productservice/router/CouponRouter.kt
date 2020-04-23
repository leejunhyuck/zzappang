package com.project.zzappang.productservice.router

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Component
class CouponRouter(
    @Autowired private val couponHandler: CouponHandler
) {
    @Bean
    fun couponRoutes(): RouterFunction<*> = router {
        "/coupons".nest {
            GET("/", couponHandler::getCoupons)

        }
    }
}