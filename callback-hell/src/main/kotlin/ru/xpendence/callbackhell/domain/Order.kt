package ru.xpendence.callbackhell.domain

import java.time.OffsetDateTime

data class Order(
    val id: String,
    val userId: String,
    val items: List<OrderItem>,
    val total: Double,
    val status: String,
    val createdAt: OffsetDateTime
)