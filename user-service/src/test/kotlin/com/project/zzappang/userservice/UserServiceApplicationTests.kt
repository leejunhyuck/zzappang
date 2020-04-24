package com.project.zzappang.userservice

import com.project.zzappang.userservice.domain.user.repository.UserRepository
import com.project.zzappang.userservice.global.config.jwt.JwtTokenProvider
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono

@SpringBootTest
class UserServiceApplicationTests(
        @Autowired
       val repo: UserRepository,

        @Autowired
        val tokenprovider : JwtTokenProvider
) {



    @Test
    fun contextLoads() {
    }

    @Test
    fun UserModelTests(){
        var user = repo.findById("346")
        print(user.subscribe{it.name}.toString())

    }
}
