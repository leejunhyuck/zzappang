package com.project.zzappang.productservice.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "categories")
data class Category(
        @Id
        var _id: String? = null,
        var name: String,
        var level: Int,
        var parentCategoryId: String? = null
) {
        fun isSiblingOf(category: Category) : Boolean {
                return this.parentCategoryId == category._id
        }
}