package com.project.zzappang.productservice.dto

import com.project.zzappang.productservice.domain.CategorySet
import com.project.zzappang.productservice.domain.Product

class ProductDto {
    data class UpdateProductReq(
            var _id: String,
            var imageIds: List<String>,
            var title: String,
            var content: String,
            var thumbnailImageId: String,
            var categories: CategorySet,
            var seller_id: String,
            var quantity: Int,
            var isRocket: Boolean,
            var isRegular: Boolean
    ) {
        fun toEntity() = Product(_id, imageIds, title, content, thumbnailImageId, categories, seller_id, quantity, isRocket, isRegular)
    }
}