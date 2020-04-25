package com.project.zzappang.userservice.domain.user.dto

import com.project.zzappang.userservice.domain.user.model.Role

class SignUpRequest (
    private val id: String,
    private val password: String,
    private val name: String,
    var email: String,
    var address: String,
    var couponIds : String,
    var phone : String
)