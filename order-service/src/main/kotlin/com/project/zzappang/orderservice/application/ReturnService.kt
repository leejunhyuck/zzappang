package com.project.zzappang.orderservice.application

import com.project.zzappang.orderservice.domain.Return
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ReturnService {
    fun getReturns(userId: Mono<String>): Flux<Return>
}