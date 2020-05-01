package com.project.zzappang.userservice.domain.user.router

import com.project.zzappang.userservice.domain.user.handler.AuthHandler
import com.project.zzappang.userservice.domain.user.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.security.core.parameters.P
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class UserRouter(private val userHandler: UserHandler,private val authHandler: AuthHandler) {
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
        "/user".nest{
            GET("/me", userHandler::getMyinfo)
            POST("/verify", userHandler::verifyUser)
        }

        "/auth".nest{
            POST("/signup", authHandler::signUp)
            POST("/signin", authHandler::signIn)
        }

        "/membership".nest{
            POST("/register", userHandler::registerMembership)
            POST("/unregister", userHandler::unregisterMembership)
        }
    }
}