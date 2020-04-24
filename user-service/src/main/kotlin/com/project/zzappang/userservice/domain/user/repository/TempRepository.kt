package com.project.zzappang.userservice.domain.user.repository

import com.project.zzappang.userservice.domain.user.model.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono


interface TempRepository : ReactiveMongoRepository<User?, String?> {
    fun findByName(username: String?): Mono<User>
}
