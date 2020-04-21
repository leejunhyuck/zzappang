package com.project.zzappang.productservice.domain

data class Category(
    var id: String,
    var name: String,
    var level: Int,
    var parentCategoryId: String? = null
)