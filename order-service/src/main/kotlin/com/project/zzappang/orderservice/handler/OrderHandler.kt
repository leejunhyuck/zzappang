package com.project.zzappang.orderservice.handler

import com.project.zzappang.orderservice.application.OrderService
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
class OrderHandler(
    @Autowired private val orderService: OrderService
) {
    fun getOrders(serverRequest: ServerRequest): Mono<ServerResponse> {
        val userId = serverRequest.headers().firstHeader("userId") ?: return status(HttpStatus.UNAUTHORIZED).build()
        return status(HttpStatus.OK).body(orderService.getOrders(Mono.just(userId)))
    }

    fun placeOrder(serverRequest: ServerRequest): Mono<ServerResponse> {
        val userId = serverRequest.headers().firstHeader("userId") ?: return status(HttpStatus.UNAUTHORIZED).build()
        return orderService.placeOrder(serverRequest.bodyToMono(), userId).flatMap {
            status(HttpStatus.NO_CONTENT).build()
        }
    }

    fun startShipment(serverRequest: ServerRequest): Mono<ServerResponse> {
        val userId = serverRequest.headers().firstHeader("userId") ?: return status(HttpStatus.UNAUTHORIZED).build()
        val orderId = serverRequest.pathVariable("id")
        return orderService.startShipment(Mono.just(userId), Mono.just(orderId)).flatMap {
            status(HttpStatus.NO_CONTENT).build()
        }
    }

    fun endShipment(serverRequest: ServerRequest): Mono<ServerResponse> {
        val userId = serverRequest.headers().firstHeader("userId") ?: return status(HttpStatus.UNAUTHORIZED).build()
        val orderId = serverRequest.pathVariable("id")
        return orderService.endShipment(Mono.just(userId), Mono.just(orderId)).flatMap {
            status(HttpStatus.NO_CONTENT).build()
        }
    }
}