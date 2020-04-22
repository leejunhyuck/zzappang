package com.project.zzappang.productservice.repository

import com.project.zzappang.productservice.domain.Category
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CategoryRepository : ReactiveCrudRepository<Category, String>