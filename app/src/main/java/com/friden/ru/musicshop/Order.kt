package com.friden.ru.musicshop

data class Order(
    val name: String,
    val goodsName: String,
    var quantity: Int = 0,
    val price: Double,
    val orderPrice: Double
)