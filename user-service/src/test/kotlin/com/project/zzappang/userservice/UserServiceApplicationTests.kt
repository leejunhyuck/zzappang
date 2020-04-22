package com.project.zzappang.userservice

import com.project.zzappang.userservice.domain.user.model.User
import com.project.zzappang.userservice.domain.user.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    lateinit var repo: UserRepository

    @Test
    fun contextLoads() {
    }

    @Test
    fun UserModelTests(){
        var user : Mono<User>
        //repo.create(user)
    }
}
