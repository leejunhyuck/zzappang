package com.project.zzappang.productservice.application

import com.project.zzappang.productservice.domain.Category
import reactor.core.publisher.Flux

interface CategoryService {
    fun getCategories(): Flux<Category>
    fun recommendCategories(): Flux<Category>
}