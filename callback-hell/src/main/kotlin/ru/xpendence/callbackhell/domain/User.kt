package ru.xpendence.callbackhell.domain

data class User(
    val id: String,
    val name: String,
    val email: String,
    val loyaltyLevel: Int
)