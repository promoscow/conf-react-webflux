package ru.xpendence.callbackhell.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.FullOrderDetails
import ru.xpendence.callbackhell.service.OrderService

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService
) {

    @GetMapping("/details/{orderId}")
    fun getFullOrderDetails(
        @PathVariable orderId: String
    ): Mono<FullOrderDetails> = orderService.getFullOrderDetails(orderId)

    @GetMapping("/details/alt/{orderId}")
    fun getFullOrderDetailsAlt(
        @PathVariable orderId: String
    ): Mono<FullOrderDetails> = orderService.getFullOrderDetailsAlt(orderId)
}