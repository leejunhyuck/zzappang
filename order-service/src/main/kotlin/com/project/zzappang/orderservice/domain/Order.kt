package com.project.zzappang.orderservice.domain

import com.project.zzappang.orderservice.dto.OrderDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

data class Order(
    var _id: String? = null,
    var productId: String,
    var quantity: Int,
    var state: OrderState,
    var userId: String,
    var receiverInfo: ReceiverInfo,
    var paymentType: PaymentType
) {
    companion object {
        fun of(productInfo: OrderDto.ProductInfo, receiverInfo: ReceiverInfo, paymentType: PaymentType, userId: String): Order {
            return Order(
                    productId = productInfo.productId,
                    quantity = productInfo.quantity,
                    state = OrderState.IN_PROGRESS,
                    userId = userId,
                    receiverInfo = receiverInfo,
                    paymentType = paymentType
            )
        }
    }
}

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