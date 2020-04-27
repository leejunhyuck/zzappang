package com.project.zzappang.orderservice.application.impl

import com.project.zzappang.orderservice.application.OrderService
import com.project.zzappang.orderservice.domain.Shipment
import com.project.zzappang.orderservice.repository.ShipmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ShipmentServiceImpl(
    @Autowired private val shipmentRepository: ShipmentRepository,
    @Autowired private val orderService: OrderService
) {
    fun getShipments(userId: Mono<String>): Flux<Shipment> {
        return orderService.getOrders(userId).flatMap {
            shipmentRepository.findByOrderId(it._id!!)
        }
    }
}