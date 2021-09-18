package com.example.myapplication

import android.app.Application
import com.example.myapplication.room.WordRepository
import com.example.myapplication.room.WordRoomDatabase
import com.example.myapplication.sqlite.DBHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App: Application() {
    val  dbHelper: DBHelper = DBHelper(this)
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}