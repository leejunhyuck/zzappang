package com.project.zzappang.productservice.repository

import com.project.zzappang.productservice.domain.Product
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ProductRepository : ReactiveCrudRepository<Product, String>