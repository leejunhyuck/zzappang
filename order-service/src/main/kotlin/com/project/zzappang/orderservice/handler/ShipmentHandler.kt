package com.project.zzappang.orderservice.handler

import com.project.zzappang.orderservice.application.ShipmentService
import com.project.zzappang.orderservice.domain.ShipmentType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.status
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class ShipmentHandler(
    @Autowired private val shipmentService: ShipmentService
) {
    fun getShipments(serverRequest: ServerRequest): Mono<ServerResponse> {
        val userId = serverRequest.headers().firstHeader("userId") ?: return status(HttpStatus.UNAUTHORIZED).build()
        val shipmentType = ShipmentType.valueOf(serverRequest.queryParam("type").orElse("NORMAL"))
        return status(HttpStatus.OK)
            .body(shipmentService.getShipments(
                Mono.just(userId),
                Mono.just(shipmentType)
            ))
    }
}