package com.project.zzappang.orderservice.application

import com.project.zzappang.orderservice.domain.Shipment
import com.project.zzappang.orderservice.domain.ShipmentType
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ShipmentService {
    fun getShipments(userId: Mono<String>, type: Mono<ShipmentType>): Flux<Shipment>
    fun getShipmentByOrderId(orderId: Mono<String>): Mono<Shipment>
    fun initializeShipment(orderId: Mono<String>, type: Mono<ShipmentType>): Mono<Void>
}