package com.project.zzappang.userservice.domain.user.handler

import com.project.zzappang.userservice.domain.user.dto.ApiResponse
import com.project.zzappang.userservice.domain.user.model.User
import com.project.zzappang.userservice.domain.user.repository.TempRepository
import com.project.zzappang.userservice.domain.user.service.UserService
import com.project.zzappang.userservice.global.config.jwt.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import org.springframework.http.MediaType.APPLICATION_JSON

@Component
class AuthHandler(
        val userService: UserService,
        val tokenProvider: JwtTokenProvider,
        val tempRepository: TempRepository
) {
    fun signUp(serverRequest: ServerRequest): Mono<ServerResponse> = userService.signUp(serverRequest.bodyToMono())
            .flatMap { ServerResponse.ok().body(BodyInserters.fromObject(it!!)) }
            .switchIfEmpty(ServerResponse.status(HttpStatus.OK).build())

    fun signIn(serverRequest: ServerRequest) = tempRepository.findById(serverRequest.pathVariable("id"))
            .flatMap { ServerResponse.ok().body(BodyInserters.fromObject(it!!)) }
            .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build())


}