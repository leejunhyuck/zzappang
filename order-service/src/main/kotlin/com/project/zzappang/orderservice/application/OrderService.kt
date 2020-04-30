package com.project.zzappang.orderservice.application

import com.project.zzappang.orderservice.domain.Order
import com.project.zzappang.orderservice.dto.OrderDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface OrderService {
    fun getOrders(userId: Mono<String>): Flux<Order>
    fun placeOrder(placeOrderReq: Mono<OrderDto.PlaceOrderReq>, userId: String): Mono<Void>
    fun startShipment(userId: Mono<String>, orderId: Mono<String>): Mono<Void>
    fun endShipment(userId: Mono<String>, orderId: Mono<String>): Mono<Void>
}