package com.project.zzappang.productservice.dto

import com.project.zzappang.productservice.domain.Coupon
import java.time.LocalDateTime

class CouponDto {
    data class IssueCouponReq(
            var couponId: String,
            var userId: String
    )

    data class UpdateCouponReq(
            var _id: String,
            var categoryId: String,
            var name: String,
            var discount: Int,
            var validFrom: LocalDateTime,
            var validUntil: LocalDateTime,
            var quantity: Int,
            var imageId: String
    ) {
        fun toEntity() = Coupon(_id, categoryId, name, discount, validFrom, validUntil, quantity, imageId)
    }
}