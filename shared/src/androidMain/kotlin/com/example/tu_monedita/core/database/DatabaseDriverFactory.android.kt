package com.example.tu_monedita.core.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.tu_monedita.database.TuMoneditaDatabase


actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TuMoneditaDatabase.Schema, context, "tu_monedita.db")
    }
}
