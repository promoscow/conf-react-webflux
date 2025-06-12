package ru.xpendence.callbackhell.domain

data class Payment(
    val id: String,
    val orderId: String,
    val amount: Double,
    val method: String,
    val status: String
)