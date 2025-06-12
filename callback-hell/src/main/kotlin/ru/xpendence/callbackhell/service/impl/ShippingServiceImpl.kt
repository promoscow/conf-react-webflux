package ru.xpendence.callbackhell.service.impl

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.Shipping
import ru.xpendence.callbackhell.service.ShippingService
import java.time.Duration
import java.time.OffsetDateTime

@Service
class ShippingServiceImpl : ShippingService {

    override fun get(orderId: String): Mono<Shipping> =
        Mono
            .just(
                Shipping(
                    id = "ship_$orderId",
                    orderId = orderId,
                    address = "123 Main St, Anytown, USA",
                    status = "shipped",
                    estimatedDelivery = OffsetDateTime.now().plusDays(3)
                )
            )
            .delayElement(Duration.ofMillis(220))
}