package ru.xpendence.callbackhell.service

import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.Shipping

interface ShippingService {

    fun get(orderId: String): Mono<Shipping>
}