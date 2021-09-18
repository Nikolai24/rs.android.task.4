package com.example.myapplication.room

import androidx.room.*
import com.example.myapplication.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM animals_room ORDER BY name")
    fun getAllName(): Flow<List<Item>>

    @Query("SELECT * FROM animals_room ORDER BY age")
    fun getAllAge(): Flow<List<Item>>

    @Query("SELECT * FROM animals_room ORDER BY breed")
    fun getAllBreed(): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Item)

    @Update
    suspend fun update(word: Item)

    @Delete
    suspend fun delete(word: Item)

    @Query("DELETE FROM animals_room")
    suspend fun deleteAll()
}