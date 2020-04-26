package com.project.zzappang.orderservice.dto

import com.project.zzappang.orderservice.domain.PaymentType
import com.project.zzappang.orderservice.domain.ReceiverInfo

class OrderDto {
    data class PlaceOrderReq(
        var productInfos: List<ProductInfo>,
        var receiverInfo: ReceiverInfo,
        var paymentType: PaymentType,
        var totalPayment: Int
    )

    data class ProductInfo(
        var productId: String,
        var quantity: Int
    )
}