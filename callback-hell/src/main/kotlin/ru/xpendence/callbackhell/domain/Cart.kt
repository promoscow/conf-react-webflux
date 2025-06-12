package ru.xpendence.callbackhell.domain

data class Cart(
    val id: String,
    val userId: String,
    val items: List<CartItem>
)