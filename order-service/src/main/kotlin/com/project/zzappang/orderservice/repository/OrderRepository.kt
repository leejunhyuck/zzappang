package com.project.zzappang.orderservice.repository

import com.project.zzappang.orderservice.domain.Order
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface OrderRepository : ReactiveCrudRepository<Order, String> {
    fun findByUserId(userId: String): Flux<Order>
}