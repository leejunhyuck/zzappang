package com.project.zzappang.productservice.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "products")
data class Product(
        @Id
        var _id: String? = null,
        var imageIds: List<String>,
        var title: String,
        var content: String,
        var price: Int,
        var thumbnailImageId: String,
        var categories: CategorySet,
        var seller_id: String,
        var quantity: Int,
        var isRocket: Boolean,
        var isRegular: Boolean
)

data class CategorySet(
        var small: String,
        var medium: String,
        var large: String
)