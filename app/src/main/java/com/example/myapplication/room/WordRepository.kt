package com.example.myapplication.room

import androidx.annotation.WorkerThread
import com.example.myapplication.Item
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allName: Flow<List<Item>> = wordDao.getAllName()
    val allAge: Flow<List<Item>> = wordDao.getAllAge()
    val allBreed: Flow<List<Item>> = wordDao.getAllBreed()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Item) {
        wordDao.insert(word)
    }

    suspend fun update(word: Item) {
        wordDao.update(word)
    }

    suspend fun delete(word: Item) {
        wordDao.delete(word)
    }
}