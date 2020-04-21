package com.project.zzappang.productservice.domain

data class Product(
    var id: String,
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
    var small: Category,
    var medium: Category,
    var large: Category
)