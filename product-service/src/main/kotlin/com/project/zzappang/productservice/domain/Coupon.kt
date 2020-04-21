package com.project.zzappang.productservice.domain

import java.time.LocalDateTime

data class Coupon(
    var id: String,
    var categoryId: String,
    var name: String,
    var discount: Int,
    var validFrom: LocalDateTime,
    var validUntil: LocalDateTime,
    var quantity: Int,
    var imageId: String
)