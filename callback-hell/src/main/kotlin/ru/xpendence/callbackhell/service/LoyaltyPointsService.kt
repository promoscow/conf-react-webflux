package ru.xpendence.callbackhell.service

import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.LoyaltyPoints

interface LoyaltyPointsService {

    fun get(userId: String): Mono<LoyaltyPoints>
}