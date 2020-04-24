package com.project.zzappang.userservice.domain.user.dto

data class LoginRequest(
        private val id: String,
        private val password: String
)