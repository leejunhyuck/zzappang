package com.project.zzappang.productservice.application

import com.project.zzappang.productservice.domain.Product
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductService {
    fun getProducts(): Flux<Product>
    fun getProduct(productId: String): Mono<Product>
    fun recommendProduct(): Flux<Product>
    fun updateProduct(product: Mono<Product>): Mono<Product>
    fun deleteProduct(productId: String): Mono<Void>
    fun createProduct(product: Mono<Product>): Mono<Product>
}