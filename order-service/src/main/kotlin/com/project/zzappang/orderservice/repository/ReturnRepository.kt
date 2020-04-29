package com.project.zzappang.orderservice.repository

import com.project.zzappang.orderservice.domain.Return
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ReturnRepository : ReactiveCrudRepository<Return, String> {
    fun findByOrderId(orderId: String): Flux<Return>
}