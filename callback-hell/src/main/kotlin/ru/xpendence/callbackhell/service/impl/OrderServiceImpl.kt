package ru.xpendence.callbackhell.service.impl

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.FullOrderDetails
import ru.xpendence.callbackhell.domain.LoyaltyPoints
import ru.xpendence.callbackhell.domain.Order
import ru.xpendence.callbackhell.domain.OrderItem
import ru.xpendence.callbackhell.domain.Payment
import ru.xpendence.callbackhell.domain.Product
import ru.xpendence.callbackhell.domain.Shipping
import ru.xpendence.callbackhell.domain.User
import ru.xpendence.callbackhell.service.LoyaltyPointsService
import ru.xpendence.callbackhell.service.OrderService
import ru.xpendence.callbackhell.service.PaymentService
import ru.xpendence.callbackhell.service.ProductService
import ru.xpendence.callbackhell.service.ShippingService
import ru.xpendence.callbackhell.service.UserService
import java.time.Duration
import java.time.OffsetDateTime

@Service
class OrderServiceImpl(
    private val loyaltyPointsService: LoyaltyPointsService,
    private val paymentService: PaymentService,
    private val productService: ProductService,
    private val shippingService: ShippingService,
    private val userService: UserService
) : OrderService {

    override fun getFullOrderDetails(orderId: String): Mono<FullOrderDetails> =
        getOrder(orderId)
            .flatMap { order ->
                userService
                    .get(order.userId)
                    .flatMap { user ->
                        paymentService
                            .get(order.id)
                            .flatMap { payment ->
                                shippingService
                                    .get(order.id)
                                    .flatMap { shipping ->
                                        loyaltyPointsService
                                            .get(user.id)
                                            .flatMap { loyaltyPoints ->
                                                productService
                                                    .getRecommendations(user.id)
                                                    .flatMap { recommendations ->
                                                        validateStatus(order.status)
                                                            .flatMap { isValid ->
                                                                if (isValid) {
                                                                    checkInventory(order).flatMap { inStock ->
                                                                        if (inStock) {
                                                                            Mono.just(
                                                                                FullOrderDetails(
                                                                                    user = user,
                                                                                    order = order,
                                                                                    payment = payment,
                                                                                    shipping = shipping,
                                                                                    loyaltyInfo = loyaltyPoints,
                                                                                    recommendedProducts = recommendations
                                                                                )
                                                                            )
                                                                        } else {
                                                                            Mono.error(RuntimeException("Some items are out of stock"))
                                                                        }
                                                                    }
                                                                } else {
                                                                    Mono.error(RuntimeException("Invalid order status"))
                                                                }
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }

    override fun getFullOrderDetailsAlt(orderId: String): Mono<FullOrderDetails> =
        getOrder(orderId)
            .zipWhen { order ->
                Mono.zip(
                    userService.get(order.userId),
                    paymentService.get(order.id),
                    shippingService.get(order.id),
                    validateStatus(order.status),
                    checkInventory(order)
                ).flatMap { tuple ->
                    val user = tuple.t1
                    val payment = tuple.t2
                    val shipping = tuple.t3
                    val isValid = tuple.t4
                    val inStock = tuple.t5
                    if (!isValid) {
                        return@flatMap Mono.error<FullOrderDetails>(RuntimeException("Invalid order status"))
                    }
                    if (!inStock) {
                        return@flatMap Mono.error<FullOrderDetails>(RuntimeException("Some items are out of stock"))
                    }
                    Mono.zip(
                        loyaltyPointsService.get(user.id),
                        productService.getRecommendations(user.id)
                    ).map { loyaltyAndRecommendations ->
                        val loyaltyPoints = loyaltyAndRecommendations.t1
                        val recommendations = loyaltyAndRecommendations.t2
                        Triple(user, payment, shipping) to Pair(loyaltyPoints, recommendations)
                    }
                }.map {
                    val pair = it as Pair<Triple<User, Payment, Shipping>, Pair<LoyaltyPoints, List<Product>>>
                    val (user, payment, shipping) = pair.first
                    val (loyaltyPoints, recommendations) = pair.second
                    order to FullOrderDetails(
                        user = user,
                        order = order,
                        payment = payment,
                        shipping = shipping,
                        loyaltyInfo = loyaltyPoints,
                        recommendedProducts = recommendations
                    )
                }
            }
            .map { it.t2.second }

    override fun getOrder(orderId: String): Mono<Order> = Mono.just(
        Order(
            id = orderId,
            userId = "user123",
            items = listOf(
                OrderItem("prod1", 2, 19.99),
                OrderItem("prod2", 1, 29.99)
            ),
            total = 69.97,
            status = "processed",
            createdAt = OffsetDateTime.now()
        )
    ).delayElement(Duration.ofMillis(200))

    override fun validateStatus(status: String): Mono<Boolean> =
        Mono
            .just(status in listOf("processed", "shipped"))
            .delayElement(Duration.ofMillis(50))

    override fun checkInventory(order: Order): Mono<Boolean> =
        Mono
            .just(true)
            .delayElement(Duration.ofMillis(150))
}