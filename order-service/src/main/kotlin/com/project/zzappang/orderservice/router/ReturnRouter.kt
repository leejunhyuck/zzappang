package com.project.zzappang.orderservice.router

import com.project.zzappang.orderservice.handler.ReturnHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Component
class ReturnRouter(
    @Autowired private val returnHandler: ReturnHandler
) {
    fun returnRoutes(): RouterFunction<*> = router {
        "/returns".nest {
            GET("/", returnHandler::getReturns)
        }
    }
}