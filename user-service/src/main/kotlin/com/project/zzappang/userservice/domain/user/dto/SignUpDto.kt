package com.project.zzappang.userservice.domain.user.dto

import com.project.zzappang.userservice.domain.user.model.Role

class SignUpDto (
    private val id: String,
    private val password: String,
    private val name: String
)