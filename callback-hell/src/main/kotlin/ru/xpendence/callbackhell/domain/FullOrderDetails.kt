package ru.xpendence.callbackhell.domain

data class FullOrderDetails(
    val user: User,
    val order: Order,
    val payment: Payment,
    val shipping: Shipping,
    val loyaltyInfo: LoyaltyPoints,
    val recommendedProducts: List<Product>
)