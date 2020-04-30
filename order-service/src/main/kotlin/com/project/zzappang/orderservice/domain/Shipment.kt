package com.project.zzappang.orderservice.domain

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(value = "shipments")
data class Shipment(
    var _id: String? = null,
    var orderId: String,
    var type: ShipmentType,
    var state: ShipmentState,
    var expiredAt: LocalDate? = null,
    var term: Term? = null
) {
    companion object {
        fun of(type: ShipmentType, orderId: String): Shipment {
            return Shipment(
                orderId = orderId,
                type = type,
                state = ShipmentState.BEFORE
            )
        }
    }

    fun startShipment() {
        this.state = ShipmentState.IN_PROGRESS
    }

    fun completeShipment() {
        this.state = ShipmentState.COMPLETED
    }
}

enum class Term {
    WEEKLY,
    MONTHLY
}

enum class ShipmentState {
    BEFORE,
    IN_PROGRESS,
    COMPLETED
}

enum class ShipmentType {
    REGULAR,
    NORMAL,
    ROCKET
}