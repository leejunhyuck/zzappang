package com.project.zzappang.orderservice.domain

data class Order(
    var _id: String,
    var productId: String,
    var quantity: Int,
    var state: OrderState,
    var userId: String,
    var receiverInfo: ReceiverInfo,
    var paymentType: PaymentType
)

enum class PaymentType {
    REGULAR,
    NORMAL,
    MEMBERSHIP
}

enum class OrderState {
    IN_PROGRESS,
    REFUNDED,
    COMPLETED
}

data class ReceiverInfo(
    var name: String,
    var phone: String,
    var address: String
)