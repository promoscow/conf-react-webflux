package ru.xpendence.callbackhell.domain

data class LoyaltyPoints(
    val userId: String,
    val currentPoints: Int,
    val lifetimePoints: Int
)