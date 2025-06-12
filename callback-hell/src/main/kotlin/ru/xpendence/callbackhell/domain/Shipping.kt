package ru.xpendence.callbackhell.domain

import java.time.OffsetDateTime

data class Shipping(
    val id: String,
    val orderId: String,
    val address: String,
    val status: String,
    val estimatedDelivery: OffsetDateTime
)