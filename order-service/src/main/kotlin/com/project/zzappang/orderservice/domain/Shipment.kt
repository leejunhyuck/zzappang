package com.project.zzappang.orderservice.domain

data class Shipment(
    var _id: String,
    var orderId: String,
    var type: ShipmentType,
    var state: ShipmentState
)

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