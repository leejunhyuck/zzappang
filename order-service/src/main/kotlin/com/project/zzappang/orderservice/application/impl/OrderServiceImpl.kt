package com.project.zzappang.orderservice.application.impl

import com.project.zzappang.orderservice.application.OrderService
import com.project.zzappang.orderservice.application.ShipmentService
import com.project.zzappang.orderservice.domain.Order
import com.project.zzappang.orderservice.dto.OrderDto
import com.project.zzappang.orderservice.dto.ShipmentDto
import com.project.zzappang.orderservice.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class OrderServiceImpl(
    @Autowired private val orderRepository: OrderRepository,
    @Autowired private val shipmentService: ShipmentService
): OrderService {
    override fun getOrders(userId: Mono<String>): Flux<Order> {
        return userId.flatMapMany { orderRepository.findByUserId(it) }
    }

    override fun placeOrder(placeOrderReq: Mono<OrderDto.PlaceOrderReq>, userId: String): Mono<Void> {
        return placeOrderReq.flatMapMany {
            Flux.fromIterable(it.productInfos.map { productInfo ->
                val order = Order.of(productInfo, it.receiverInfo, it.paymentType, userId)
                orderRepository.save(order).flatMap {order ->
                    shipmentService.initializeShipment(Mono.just(order._id!!), Mono.just(productInfo.type))
                }
            })
        }.then()
    }

    override fun startShipment(userId: Mono<String>, orderId: Mono<String>): Mono<Void> {
    }
}