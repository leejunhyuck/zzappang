package com.project.zzappang.userservice.domain.user.repository

import com.project.zzappang.userservice.domain.user.model.User
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.core.remove
import org.springframework.data.mongodb.core.find

@Repository
class UserRepository(private val template:ReactiveMongoTemplate) {
    fun create(user: Mono<User>) = template.save(user)
    fun findById(id:String)=template.findById<User>(id)
    fun deleteById(id:String)=template.remove<User>(Query(where("_id").isEqualTo(id)))
    fun findUser(nameFilter: String)=template.find<User>(
            Query(where("name").regex(".*$nameFilter.*","i"))
    )
}