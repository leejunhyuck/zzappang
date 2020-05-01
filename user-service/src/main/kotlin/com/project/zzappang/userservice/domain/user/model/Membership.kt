package com.project.zzappang.userservice.domain.user.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "membership")
data class Membership(
        var userId : String,
        var isMembership : Boolean
) {
}