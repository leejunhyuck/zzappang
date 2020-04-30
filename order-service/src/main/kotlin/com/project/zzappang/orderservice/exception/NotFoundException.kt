package com.project.zzappang.orderservice.exception

open class NotFoundException(override val message: String): RuntimeException()

class ShipmentNotFoundException(override val message: String): NotFoundException(message)