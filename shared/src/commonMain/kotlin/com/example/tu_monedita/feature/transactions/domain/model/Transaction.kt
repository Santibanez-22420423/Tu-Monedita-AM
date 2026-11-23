package com.example.tu_monedita.feature.transactions.domain.model

data class Transaction(
    val id: String,
    val amount: Double,
    val description: String,
    val categoryId: String,
    val date: Long,
    val type: String // "INCOME" o "EXPENSE"
)
