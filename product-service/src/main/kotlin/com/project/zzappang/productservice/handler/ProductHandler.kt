package com.project.zzappang.productservice.handler

import com.project.zzappang.productservice.application.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ProductHandler(
        @Autowired private val productService: ProductService
) {
    fun getProducts(serverRequest: ServerRequest): Mono<ServerResponse> {

    }

    fun getProduct(serverRequest: ServerRequest): Mono<ServerResponse> {

    }

    fun createProduct(serverRequest: ServerRequest): Mono<ServerResponse> {

    }

    fun updateProduct(serverRequest: ServerRequest): Mono<ServerResponse> {

    }

    fun deleteProduct(serverRequest: ServerRequest): Mono<ServerResponse> {

    }
}