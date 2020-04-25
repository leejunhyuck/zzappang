package com.project.zzappang.productservice.application

import com.project.zzappang.productservice.domain.Category
import com.project.zzappang.productservice.domain.CategorySet
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CategoryService {
    fun getCategories(q: String): Flux<Category>
    fun getCategory(categoryId: String): Mono<Category>
    fun getSiblingCategories(categoryId: String): Flux<Category>
    fun recommendCategories(): Flux<Category>
    fun isValidCategorySet(categorySet: CategorySet): Mono<Boolean>
}