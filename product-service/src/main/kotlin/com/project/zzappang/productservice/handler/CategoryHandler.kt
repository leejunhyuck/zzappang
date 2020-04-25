package com.project.zzappang.productservice.handler

import com.project.zzappang.productservice.application.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class CategoryHandler(
        @Autowired private val categoryService: CategoryService
) {
    fun getCategories(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.status(HttpStatus.OK)
                .body(categoryService.getCategories(serverRequest.queryParam("q").orElse("")))
    }

    fun getSiblingCategories(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.status(HttpStatus.OK)
                .body(categoryService.getSiblingCategories(serverRequest.pathVariable("id")))
    }

    fun recommendCategories(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.status(HttpStatus.OK)
                .body(categoryService.recommendCategories())
    }
}