package ru.xpendence.callbackhell.service

import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.Product

interface ProductService {

    fun getRecommendations(userId: String): Mono<List<Product>>
}