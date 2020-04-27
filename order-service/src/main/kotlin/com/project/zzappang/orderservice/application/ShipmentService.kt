package com.project.zzappang.orderservice.application

import com.project.zzappang.orderservice.domain.Shipment
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ShipmentService {
    fun getShipments(userId: Mono<String>): Flux<Shipment>
}