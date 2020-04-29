package com.project.zzappang.orderservice.handler

import com.project.zzappang.orderservice.application.ReturnService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.status
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class ReturnHandler(
    @Autowired private val returnService: ReturnService
) {
    fun getReturns(serverRequest: ServerRequest): Mono<ServerResponse> {
        val userId = serverRequest.headers().firstHeader("userId") ?: return status(HttpStatus.UNAUTHORIZED).build()
        return status(HttpStatus.OK).body(returnService.getReturns(Mono.just(userId)))
    }
}