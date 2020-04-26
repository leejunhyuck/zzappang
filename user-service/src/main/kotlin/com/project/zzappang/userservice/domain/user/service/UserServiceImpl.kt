package com.project.zzappang.userservice.domain.user.service

import com.project.zzappang.userservice.domain.user.dto.SignInRequest
import com.project.zzappang.userservice.domain.user.dto.SignInResponse
import com.project.zzappang.userservice.domain.user.dto.SignUpRequest
import com.project.zzappang.userservice.domain.user.model.Role
import com.project.zzappang.userservice.domain.user.model.User
import com.project.zzappang.userservice.domain.user.repository.UserRepository
import com.project.zzappang.userservice.global.config.jwt.JwtTokenProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono


@Service
class UserServiceImpl(
        val passwordEncoder: BCryptPasswordEncoder,
        val repo: UserRepository,
        val tokenProvider: JwtTokenProvider
) : UserService {


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


    override fun signIn(req: Mono<SignInRequest>): Mono<Any> {
        return req.flatMap { login ->
            repo.findById(login.id).flatMap { user ->
                if (passwordEncoder.matches(login.password, user.password))
                    return@flatMap SignInRequest(user.name, tokenProvider.generateToken(user)).toMono()
                else
                    return@flatMap user.toMono() // 전역 예외 처리 하기
            }
        }
    }
}




