package com.project.zzappang.userservice.global.config.security

import com.project.zzappang.userservice.domain.user.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import reactor.core.publisher.Mono

class CustomUserDetails(private val user: Mono<User>): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
        user.subscribe{it.roles.map{ authorities.add(SimpleGrantedAuthority(it.name))}}

        return authorities
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

//    override fun getUsername() = user.name
//
//    override fun getPassword() = user.password
//
//    override fun isEnabled() = true
//
//    override fun isCredentialsNonExpired() = true
//
//    override fun isAccountNonExpired() = true
//
//    override fun isAccountNonLocked() = true

}