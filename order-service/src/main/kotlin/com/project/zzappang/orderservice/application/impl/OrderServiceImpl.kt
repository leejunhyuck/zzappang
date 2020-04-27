package com.project.zzappang.orderservice.application.impl

import com.project.zzappang.orderservice.application.OrderService
import com.project.zzappang.orderservice.domain.Order
import com.project.zzappang.orderservice.dto.OrderDto
import com.project.zzappang.orderservice.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class OrderServiceImpl(
    @Autowired private val orderRepository: OrderRepository
): OrderService {
    override fun getOrders(userId: Mono<String>): Flux<Order> {
        return userId.flatMapMany { orderRepository.findByUserId(it) }
    }

    override fun placeOrder(placeOrderReq: Mono<OrderDto.PlaceOrderReq>, userId: String): Mono<Void> {
        val orders = placeOrderReq.flatMapMany { Order.of(it, userId) }
        return orderRepository.saveAll(orders).then()
    }
}