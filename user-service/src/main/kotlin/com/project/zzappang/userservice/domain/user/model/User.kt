package com.project.zzappang.userservice.domain.user.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Email

@Document(collection = "Users")
@JsonIgnoreProperties(value = ["password"])
data class User(
        @Id
        var id: String = "",
        var password: String = "",
        var name: String = "",
        @Email
        var email: String = "",
        var address: String = "",
        var couponIds: String = "",
        var phone: String = "",
        var roles: List<Role>

)
