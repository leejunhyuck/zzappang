package com.project.zzappang.productservice.application.impl

import com.project.zzappang.productservice.application.CategoryService
import com.project.zzappang.productservice.domain.Category
import com.project.zzappang.productservice.domain.CategorySet
import com.project.zzappang.productservice.exception.CategoryNotFoundException
import com.project.zzappang.productservice.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class CategoryServiceImpl(
        @Autowired private val categoryRepository: CategoryRepository
): CategoryService {
    override fun getCategories(q: String): Flux<Category> {
        return categoryRepository.findByNameContaining(q)
    }

    override fun getCategory(categoryId: String): Mono<Category> {
        return categoryRepository.findById(categoryId).switchIfEmpty { throw CategoryNotFoundException("category not found") }
    }

    override fun getSiblingCategories(categoryId: String): Flux<Category> {
        return categoryRepository.findByParentCategoryId(categoryId)
    }

    override fun recommendCategories(): Flux<Category> {
        return categoryRepository.findAll()
    }

    override fun isValidCategorySet(categorySet: CategorySet): Mono<Boolean> {
        val smallCategory = getCategory(categorySet.small)
        val mediumCategory = getCategory(categorySet.medium)
        val largeCategory = getCategory(categorySet.large)

        return smallCategory.flatMap {small ->
            mediumCategory.flatMap { medium ->
                largeCategory.flatMap { large ->
                    if (small.isSiblingOf(medium) && medium.isSiblingOf(large)) Mono.just(true)
                    else Mono.just(false)
                }
            }
        }
    }
}