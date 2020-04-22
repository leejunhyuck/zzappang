package com.project.zzappang.userservice.domain.user.router

import com.project.zzappang.userservice.domain.user.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class UserRouter(private val userHandler: UserHandler) {
    @Bean
    fun customerRoutes() = router {
        "/user".nest {
            GET("/{id}", userHandler::get)
            POST("/", userHandler::create)
            DELETE("/{id}", userHandler::delete)
        }
        "/user".nest {
            GET("/", userHandler::search)
        }
        "/userget".nest {
            GET("/" , userHandler::geta)
        }
    }
}