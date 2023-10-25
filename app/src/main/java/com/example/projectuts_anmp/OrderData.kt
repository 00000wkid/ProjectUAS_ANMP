package com.example.projectuts_anmp

data class OrderData(
    val orders: List<OrderItem>
)

data class Order(
    val orderNumber: Int,
    val price: Double,
    val duration: String,
    val description: String
)
