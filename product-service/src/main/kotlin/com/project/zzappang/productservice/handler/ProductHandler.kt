package com.project.zzappang.productservice.handler

import com.project.zzappang.productservice.application.ProductService
import com.project.zzappang.productservice.domain.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import java.util.*

@Component
class ProductHandler(
        @Autowired private val productService: ProductService
) {
    fun getProducts(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ok().body(productService.getProducts())
    }

    fun getProduct(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ok().body(productService.getProduct(serverRequest.pathVariable("id")))
    }

    fun createProduct(serverRequest: ServerRequest): Mono<ServerResponse> {
        return created(serverRequest.uri()).body(productService.createProduct(serverRequest.bodyToMono()))
    }

    fun updateProduct(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ok().body(productService.updateProduct(serverRequest.bodyToMono()))
    }

    fun deleteProduct(serverRequest: ServerRequest): Mono<ServerResponse> {
        return productService.deleteProduct(serverRequest.pathVariable("id")).flatMap {
            if(it) ok().build()
            else notFound().build()
        }
    }
}