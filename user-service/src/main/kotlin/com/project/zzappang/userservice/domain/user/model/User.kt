package com.project.zzappang.userservice.domain.user.model

import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Email

@Document(collection = "Users")
data class User(
        var id: Int = 0,
        var password : String="",
        var name : String="",
        @Email
        var email: String="",
        var address: String="",
        var couponIds : String="",
        var role : String="",
        var phone : String=""
)
