package com.example.tu_monedita.feature.transactions.data.repository

import com.example.tu_monedita.core.database.DatabaseDriverFactory
import com.example.tu_monedita.database.TuMoneditaDatabase
import com.example.tu_monedita.feature.transactions.domain.model.Transaction
import com.example.tu_monedita.feature.transactions.domain.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionRepositoryImpl(
    driverFactory: DatabaseDriverFactory
) : TransactionRepository {

    // Inicializamos la base de datos generada por SQLDelight
    private val database = TuMoneditaDatabase(driverFactory.createDriver())
    private val queries = database.transactionEntityQueries

    override suspend fun getAllTransactions(): List<Transaction> = withContext(Dispatchers.Default) {
        // SQLDelight nos devuelve "TransactionEntity", lo mapeamos a nuestro modelo de Dominio
        queries.getAllTransactions().executeAsList().map { entity ->
            Transaction(
                id = entity.id,
                amount = entity.amount,
                description = entity.description,
                categoryId = entity.categoryId,
                date = entity.date,
                type = entity.type
            )
        }
    }

    override suspend fun insertTransaction(transaction: Transaction) = withContext(Dispatchers.Default) {
        queries.insertTransaction(
            id = transaction.id,
            amount = transaction.amount,
            description = transaction.description,
            categoryId = transaction.categoryId,
            date = transaction.date,
            type = transaction.type
        )
    }

    override suspend fun deleteTransaction(id: String) = withContext(Dispatchers.Default) {
        queries.deleteTransaction(id)
    }
}
