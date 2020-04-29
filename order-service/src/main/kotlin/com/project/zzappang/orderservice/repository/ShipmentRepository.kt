package com.project.zzappang.orderservice.repository

import com.project.zzappang.orderservice.domain.Shipment
import com.project.zzappang.orderservice.domain.ShipmentType
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ShipmentRepository : ReactiveCrudRepository<Shipment, String> {
    fun findByType(type: ShipmentType): Flux<Shipment>
    fun findByOrderIdAndType(orderId: String, type: ShipmentType): Mono<Shipment>
}