package com.project.zzappang.userservice.domain.user.service

import com.project.zzappang.userservice.domain.user.dto.ApiResponse
import com.project.zzappang.userservice.domain.user.model.User
import com.project.zzappang.userservice.domain.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono


@Service
class UserServiceImpl(
        val passwordEncoder: BCryptPasswordEncoder,
        val repo: UserRepository
) : UserService {


    override fun getCustomer(id: String): Mono<User> = repo.findById(id)

    override fun createCustomer(user: Mono<User>): Mono<User> = repo.create(user)
    override fun deleteCustomer(id: String): Mono<Boolean> = repo.deleteById(id).map { it.deletedCount > 0 }
    override fun searchCustomers(nameFilter: String): Flux<User> = repo.findUser(nameFilter)
    override fun signUp(user: Mono<User>): Mono<User> {
        return user.map {
            it.password = passwordEncoder.encode(it.password)
            return@map it
        }.flatMap { it ->
            repo.findById(it.id)
                    .flatMap { user }
                    .switchIfEmpty(repo.create(it.toMono()))
        }
    }


}