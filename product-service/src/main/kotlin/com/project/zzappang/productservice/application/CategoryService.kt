package com.project.zzappang.productservice.application

import com.project.zzappang.productservice.domain.Category
import reactor.core.publisher.Flux

interface CategoryService {
    fun getCategories(q: String): Flux<Category>
    fun getSiblingCategories(categoryId: String): Flux<Category>
    fun recommendCategories(): Flux<Category>
}