package com.project.zzappang.orderservice.router

import com.project.zzappang.orderservice.handler.ShipmentHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Component
class ShipmentRouter(
    @Autowired private val shipmentHandler: ShipmentHandler
) {
    @Bean
    fun shipmentRoutes(): RouterFunction<*> = router {
        "/shipments".nest {
            GET("/", shipmentHandler::getShipments)
        }
    }
}