package com.project.zzappang.orderservice.application.impl

import com.project.zzappang.orderservice.application.OrderService
import com.project.zzappang.orderservice.application.ReturnService
import com.project.zzappang.orderservice.domain.Return
import com.project.zzappang.orderservice.repository.ReturnRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ReturnServiceImpl(
    @Autowired private val returnRepository: ReturnRepository,
    @Autowired private val orderService: OrderService
): ReturnService {
    override fun getReturns(userId: Mono<String>): Flux<Return> {
        return orderService.getOrders(userId).flatMap {
            returnRepository.findByOrderId(it._id!!)
        }
    }
}