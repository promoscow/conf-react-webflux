package ru.xpendence.callbackhell.domain

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val category: String,
    val stock: Int
)