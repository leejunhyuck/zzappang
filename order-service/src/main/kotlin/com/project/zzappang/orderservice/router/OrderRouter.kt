package com.project.zzappang.orderservice.router

import com.project.zzappang.orderservice.handler.OrderHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Component
class OrderRouter(
    @Autowired private val orderHandler: OrderHandler
) {
    @Bean
    fun orderRoutes(): RouterFunction<*> = router {
        "/orders".nest {
            GET("/", orderHandler::getOrders)
            POST("/", orderHandler::placeOrder)
        }
    }
}