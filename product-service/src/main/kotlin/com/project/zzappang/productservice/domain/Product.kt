package com.project.zzappang.productservice.domain

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "products")
data class Product(
        var _id: ObjectId? = null,
        var imageIds: List<String>,
        var title: String,
        var content: String,
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