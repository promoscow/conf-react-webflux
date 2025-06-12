package ru.xpendence.callbackhell.service.impl

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.LoyaltyPoints
import ru.xpendence.callbackhell.service.LoyaltyPointsService
import java.time.Duration

@Service
class LoyaltyPointsServiceImpl : LoyaltyPointsService {

    override fun get(userId: String): Mono<LoyaltyPoints> =
        Mono.just(
            LoyaltyPoints(
                userId = userId,
                currentPoints = 450,
                lifetimePoints = 1200
            )
        ).delayElement(Duration.ofMillis(100))
}