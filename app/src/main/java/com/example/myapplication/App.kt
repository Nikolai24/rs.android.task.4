package com.example.myapplication

import android.app.Application
import com.example.myapplication.room.WordRepository
import com.example.myapplication.room.WordRoomDatabase
import com.example.myapplication.sqlite.DBHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App: Application() {
    val  dbHelper: DBHelper = DBHelper(this)

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}