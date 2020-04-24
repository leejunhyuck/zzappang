package com.project.zzappang.userservice.global.config.security

import com.project.zzappang.userservice.domain.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserService(@Autowired private val userRepository: UserRepository): UserDetailsService
{
    override fun loadUserByUsername(username: String): UserDetails {
        val user= userRepository.findById(username)
        user?: throw UsernameNotFoundException("cannot find such username: $username")

        return CustomUserDetails(user)
    }
}