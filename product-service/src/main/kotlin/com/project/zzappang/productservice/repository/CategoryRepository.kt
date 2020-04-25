package com.project.zzappang.productservice.repository

import com.project.zzappang.productservice.domain.Category
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface CategoryRepository : ReactiveCrudRepository<Category, String> {
    fun findByNameContaining(name: String): Flux<Category>
    fun findByParentCategoryId(parentCategoryId: String): Flux<Category>
}