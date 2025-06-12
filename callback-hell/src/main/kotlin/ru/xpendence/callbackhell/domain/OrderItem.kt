package ru.xpendence.callbackhell.domain

data class OrderItem(
    val productId: String,
    val quantity: Int,
    val priceAtPurchase: Double
)