package com.project.zzappang.userservice.domain.user.service

import com.project.zzappang.userservice.domain.user.model.User
import com.project.zzappang.userservice.domain.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class UserServiceImpl : UserService{
    @Autowired
    lateinit var repo: UserRepository

    override fun getCustomer(id: Int): Mono<User> = repo.findById(id)

    override fun createCustomer(user: Mono<User>): Mono<User> = repo.create(user)
    override fun deleteCustomer(id: Int): Mono<Boolean> = repo.deleteById(id).map { it.deletedCount > 0 }
    override fun searchCustomers(nameFilter: String): Flux<User> = repo.findUser(nameFilter)


}