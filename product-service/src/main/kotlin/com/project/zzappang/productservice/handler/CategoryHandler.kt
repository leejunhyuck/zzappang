package com.project.zzappang.productservice.handler

import com.project.zzappang.productservice.application.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class CategoryHandler(
        @Autowired private val categoryService: CategoryService
) {
    fun getCategories(serverRequest: ServerRequest): Mono<ServerResponse> {

    }
}