package com.project.zzappang.productservice.domain

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "categories")
data class Category(
        var _id: ObjectId? = null,
        var name: String,
        var level: Int,
        var parentCategoryId: String? = null
)