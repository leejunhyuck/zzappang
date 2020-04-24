package com.project.zzappang.productservice.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(value = "coupons")
data class Coupon(
        @Id
        var _id: String? = null,
        var categoryId: String,
        var name: String,
        var discount: Int,
        var validFrom: LocalDateTime,
        var validUntil: LocalDateTime,
        var quantity: Int,
        var imageId: String
)