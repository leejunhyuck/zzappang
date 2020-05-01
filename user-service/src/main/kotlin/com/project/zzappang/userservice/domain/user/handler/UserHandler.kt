package com.project.zzappang.userservice.domain.user.handler

import com.project.zzappang.userservice.domain.user.dto.SignInResponse
import com.project.zzappang.userservice.domain.user.model.User
import com.project.zzappang.userservice.domain.user.repository.TempRepository
import com.project.zzappang.userservice.domain.user.service.UserService
import com.project.zzappang.userservice.global.config.jwt.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.net.URI


@Component
class UserHandler(
        val userService: UserService,
        val tempRepository : TempRepository,
        val tokenProvider: JwtTokenProvider
) {
    fun get(serverRequest: ServerRequest) = tempRepository.findById(serverRequest.pathVariable("id"))
            //.flatMap { ok().body(fromObject(it))}
            .flatMap {
                print(it)
                ok().body(BodyInserters.fromObject(SignInResponse(it!!.name,tokenProvider.generateToken(it))))}
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun get1(serverRequest: ServerRequest) = userService.getCustomer(serverRequest.pathVariable("id"))
            //.flatMap { ok().body(fromObject(it))}
            .flatMap {user -> ok().body(BodyInserters.fromObject(SignInResponse(user.name,tokenProvider.generateToken(user))))}
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun create(serverRequest: ServerRequest) : Mono<ServerResponse> = userService.createCustomer(serverRequest.bodyToMono()).flatMap {
        created(URI.create("/customer/${it.id}")).build()
    }

    /*{
        //val helloworldMono = Mono.just(User(1, "hello", "hi"))
        val mono = serverRequest.bodyToMono(User::class.java)
        return ok().body(mono, User::class.java)
    }*/

    fun delete(serverRequest: ServerRequest) = userService.deleteCustomer(serverRequest.pathVariable("id"))
            .flatMap {
                if (it) ok().build()
                else status(HttpStatus.NOT_FOUND).build()
            }

    fun search(serverRequest: ServerRequest) = ok().body(userService.searchCustomers(serverRequest.queryParam("nameFilter").orElse("")), User::class.java)

    fun geta(serverRequest: ServerRequest) = ServerResponse.ok().body("hello world".toMono(),String::class.java)

    fun registerMembership(serverRequest: ServerRequest)  = userService.registerMembership(serverRequest.bodyToMono()).flatMap{ok().build()}
    fun unregisterMembership(serverRequest: ServerRequest) = userService.unregisterMembership(serverRequest.bodyToMono()).flatMap { ok().build()}
    fun getMyinfo(serverRequest: ServerRequest) = userService.getMyinfo().flatMap { ok().build() }
}

