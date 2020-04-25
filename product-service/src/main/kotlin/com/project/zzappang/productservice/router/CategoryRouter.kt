package com.project.zzappang.productservice.router

import com.project.zzappang.productservice.handler.CategoryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Component
class CategoryRouter(
    @Autowired private val categoryHandler: CategoryHandler
) {
    @Bean
    fun categoryRoutes(): RouterFunction<*> = router {
        "/categories".nest {
            GET("/", categoryHandler::getCategories)
            GET("/{id}/siblings", categoryHandler::getSiblingCategories)
            GET("/recommend", categoryHandler::recommendCategories)
        }
    }
}