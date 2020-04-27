package com.project.zzappang.orderservice.domain

import com.project.zzappang.orderservice.dto.OrderDto
import reactor.core.publisher.Flux

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
        fun of(placeOrderReq: OrderDto.PlaceOrderReq, userId: String): Flux<Order> {
            return Flux.fromIterable(placeOrderReq.productInfos.map {
                Order(
                    productId = it.productId,
                    quantity = it.quantity,
                    state = OrderState.IN_PROGRESS,
                    userId = userId,
                    receiverInfo = placeOrderReq.receiverInfo,
                    paymentType = placeOrderReq.paymentType
                )
            })
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