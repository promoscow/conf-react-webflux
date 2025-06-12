package ru.xpendence.callbackhell.service

import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.FullOrderDetails
import ru.xpendence.callbackhell.domain.Order

interface OrderService {

    fun getFullOrderDetails(orderId: String): Mono<FullOrderDetails>

    fun getFullOrderDetailsAlt(orderId: String): Mono<FullOrderDetails>

    fun getOrder(orderId: String): Mono<Order>

    fun validateStatus(status: String): Mono<Boolean>

    fun checkInventory(order: Order): Mono<Boolean>
}