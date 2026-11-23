package com.example.tu_monedita.feature.transactions.domain.repository

import com.example.tu_monedita.feature.transactions.domain.model.Transaction

interface TransactionRepository {
    suspend fun getAllTransactions(): List<Transaction>
    suspend fun insertTransaction(transaction: Transaction)
    suspend fun deleteTransaction(id: String)
}
