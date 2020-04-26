package com.project.zzappang.orderservice.domain

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(value = "shipments")
abstract class Shipment {
    open lateinit var _id: String
    open lateinit var orderId: String
    open lateinit var type: ShipmentType
    open lateinit var state: ShipmentState
}

@Document(value = "shipments")
data class RocketShipment(
    override var _id: String,
    override var orderId: String,
    override var type: ShipmentType = ShipmentType.ROCKET,
    override var state: ShipmentState
): Shipment()

@Document(value = "shipments")
data class NormalShipment(
    override var _id: String,
    override var orderId: String,
    override var type: ShipmentType = ShipmentType.NORMAL,
    override var state: ShipmentState
): Shipment()

@Document(value = "shipments")
data class RegularShipment(
    override var _id: String,
    override var orderId: String,
    override var type: ShipmentType = ShipmentType.REGULAR,
    override var state: ShipmentState,
    var expiredAt: LocalDate,
    var term: Term
): Shipment()

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