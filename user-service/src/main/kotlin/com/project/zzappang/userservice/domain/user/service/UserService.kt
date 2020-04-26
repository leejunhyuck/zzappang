package com.project.zzappang.userservice.domain.user.service

import com.project.zzappang.userservice.domain.user.dto.SignInRequest
import com.project.zzappang.userservice.domain.user.dto.SignInResponse
import com.project.zzappang.userservice.domain.user.dto.SignUpRequest
import com.project.zzappang.userservice.domain.user.model.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserService {
    fun getCustomer(id: String): Mono<User>
    fun createCustomer(user: Mono<User>): Mono<User>
    fun deleteCustomer(id: String): Mono<Boolean>
    fun searchCustomers(nameFilter: String): Flux<User>
    fun signUp(user: Mono<SignUpRequest>): Mono<User>
    fun signIn(user: Mono<SignInRequest>): Mono<Any>
}