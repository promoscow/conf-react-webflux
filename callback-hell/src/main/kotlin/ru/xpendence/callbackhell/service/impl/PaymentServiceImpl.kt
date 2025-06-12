package ru.xpendence.callbackhell.service.impl

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.Payment
import ru.xpendence.callbackhell.service.PaymentService
import java.time.Duration

@Service
class PaymentServiceImpl : PaymentService {

    override fun get(orderId: String): Mono<Payment> = Mono.just(
        Payment(
            id = "pay_$orderId",
            orderId = orderId,
            amount = 69.97,
            method = "credit_card",
            status = "completed"
        )
    ).delayElement(Duration.ofMillis(180))
}