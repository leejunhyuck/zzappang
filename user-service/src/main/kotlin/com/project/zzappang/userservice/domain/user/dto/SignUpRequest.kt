package com.project.zzappang.userservice.domain.user.dto

import com.project.zzappang.userservice.domain.user.model.Role
import com.project.zzappang.userservice.domain.user.model.User

class SignUpRequest(
        var id: String,
        var password: String,
        var name: String,
        var email: String,
        var address: String,
        var couponIds: String,
        var phone: String
) {
    fun toEntity(roles: List<Role>) = User(this.id, this.password, this.name, this.email, this.address, this.couponIds, this.phone, roles)
}