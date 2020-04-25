package com.project.zzappang.productservice.application.impl

import com.project.zzappang.productservice.application.CategoryService
import com.project.zzappang.productservice.domain.Category
import com.project.zzappang.productservice.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class CategoryServiceImpl(
        @Autowired private val categoryRepository: CategoryRepository
): CategoryService {
    override fun getCategories(q: String): Flux<Category> {
        return categoryRepository.findByNameContaining(q)
    }

    override fun getSiblingCategories(categoryId: String): Flux<Category> {
        return categoryRepository.findByParentCategoryId(categoryId)
    }

    override fun recommendCategories(): Flux<Category> {
        return categoryRepository.findAll()
    }
}