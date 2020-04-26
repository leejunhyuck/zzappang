package com.project.zzappang.orderservice.domain

data class Return(
    var _id: String,
    var orderId: String,
    var state: ReturnState
)

enum class ReturnState {
    IN_PROGRESS,
    COMPLETED,
    REFUNDED
}