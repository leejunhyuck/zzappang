package com.project.zzappang.userservice.global.config.security

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

class AuthenticatedUser(
        private var userId : String,
        private var roles : MutableList<SimpleGrantedAuthority>,
        private var authenticated : Boolean,
        private val serialVersionUID: Long = 6861381095901879822L
) : Authentication {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.roles;
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.authenticated = isAuthenticated;
    }

    override fun getName(): String {
        return this.userId;
    }

    override fun getCredentials(): Any {
        return this.userId;
    }

    override fun getPrincipal(): Any {
        return this.userId;
    }

    override fun isAuthenticated(): Boolean {
        return this.authenticated;
    }

    override fun getDetails(): Any? {
        return null;
    }
}