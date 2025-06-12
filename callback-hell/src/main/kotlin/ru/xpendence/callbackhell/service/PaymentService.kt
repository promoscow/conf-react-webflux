package ru.xpendence.callbackhell.service

import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.Payment

interface PaymentService {

    fun get(orderId: String): Mono<Payment>
}