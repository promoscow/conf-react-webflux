package ru.xpendence.callbackhell.service.impl

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.Product
import ru.xpendence.callbackhell.service.ProductService
import java.time.Duration

@Service
class ProductServiceImpl : ProductService {

    override fun getRecommendations(userId: String): Mono<List<Product>> =
        Mono
            .just(
                listOf(
                    Product("prod3", "Smart Watch", 199.99, "electronics", 15),
                    Product("prod4", "Wireless Earbuds", 89.99, "electronics", 20)
                )
            )
            .delayElement(Duration.ofMillis(250))
}