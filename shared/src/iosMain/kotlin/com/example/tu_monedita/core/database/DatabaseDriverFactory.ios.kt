package com.example.tu_monedita.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.tu_monedita.database.TuMoneditaDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(TuMoneditaDatabase.Schema, "tu_monedita.db")
    }
}
