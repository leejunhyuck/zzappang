package com.project.zzappang.productservice.router

import com.project.zzappang.productservice.handler.ProductHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Component
class ProductRouter(
    @Autowired private val productHandler: ProductHandler
) {
    @Bean
    fun productRoutes(): RouterFunction<*> = router {
        "/products".nest {
            GET("/", productHandler::getProducts)
            GET("/{id}", productHandler::getProduct)
            POST("/", productHandler::createProduct)
            PUT("/{id}", productHandler::updateProduct)
            DELETE("/{id}", productHandler::deleteProduct)
        }
    }

}