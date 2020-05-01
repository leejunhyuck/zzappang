package com.project.zzappang.userservice.domain.user.service

import com.mongodb.client.result.DeleteResult
import com.project.zzappang.userservice.domain.user.dto.SignInRequest
import com.project.zzappang.userservice.domain.user.dto.SignUpRequest
import com.project.zzappang.userservice.domain.user.model.Membership
import com.project.zzappang.userservice.domain.user.model.Role
import com.project.zzappang.userservice.domain.user.model.User
import com.project.zzappang.userservice.domain.user.repository.UserRepository
import com.project.zzappang.userservice.global.config.jwt.JwtTokenProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono


@Service
class UserServiceImpl(
        val passwordEncoder: BCryptPasswordEncoder,
        val repo: UserRepository,
        val tokenProvider: JwtTokenProvider,
        var webClient: WebClient
) : UserService {
    private val PRODUCT_SERVICE_API = "/coupons/"
    private val ORDER_SERVICE_API = "/shipments/"
    private val MILEAGE_SERVICE_API = "/mileages/"


    override fun getCustomer(id: String): Mono<User> = repo.findById(id)

    override fun createCustomer(user: Mono<User>): Mono<User> = repo.create(user)
    override fun deleteCustomer(id: String): Mono<Boolean> = repo.deleteById(id).map { it.deletedCount > 0 }
    override fun searchCustomers(nameFilter: String): Flux<User> = repo.findUser(nameFilter)

    override fun signUp(req: Mono<SignUpRequest>): Mono<User> =
            req.map {
                it.password = passwordEncoder.encode(it.password)
                return@map it
            }.flatMap { it ->
                repo.findById(it.id)
                        .flatMap { it.toMono() } //전역 예외 처리 하기
                        .switchIfEmpty(repo.create(it.toEntity(listOf(Role("USER"))).toMono()))
            }


    override fun signIn(req: Mono<SignInRequest>): Mono<Any> =
            req.flatMap { login ->
                repo.findById(login.id).flatMap { user ->
                    if (passwordEncoder.matches(login.password, user.password))
                        SignInRequest(user.name, tokenProvider.generateToken(user)).toMono()
                    else
                        user.toMono() // 전역 예외 처리 하기
                }
            }


    override fun getMyinfo(): Mono<Any> {
        var auth = ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication).map(Authentication::getName)
        var list = ArrayList<String>()

        var orders = WebClient.create().get().uri(ORDER_SERVICE_API).header("authorization", auth.subscribe().toString())
                .retrieve().bodyToMono(String.javaClass)

        orders.subscribe { list.add(it.toString()) }

        var coupons = WebClient.create().get().uri(MILEAGE_SERVICE_API).header("authorization", auth.subscribe().toString())
                .retrieve().bodyToMono(String.javaClass)

        coupons.subscribe { list.add(it.toString()) }

        return list.toMono()

    }


    override fun registerMembership(req: Mono<Any>): Mono<Membership> =
            ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication).map(Authentication::getName).flatMap {
                it
                repo.registerMembership(Membership(it, true))
                        .switchIfEmpty(Membership("id", true).toMono()) //전역 예외 처리하기
            }

    override fun unregisterMembership(req: Mono<Any>): Mono<DeleteResult> =
            ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication).map(Authentication::getName).flatMap {
                it
                repo.unregisterMembership(it)
                        .switchIfEmpty(repo.unregisterMembership(it)) //전역 예외 처리하기
            }

    override fun verifyUser(req: Mono<SignInRequest>): Mono<User> = //dto 바꿔야하나?
            ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication).map(Authentication::getName).flatMap {
                req.flatMap { login ->
                    repo.findById(login.id).flatMap { user ->
                        if (passwordEncoder.matches(login.password, user.password))
                            user.toMono()
                        else
                            user.toMono() // 전역 예외 처리 하기
                    }
                }
            }
}





