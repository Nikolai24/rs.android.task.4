package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "animals")
data class Item(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
                @ColumnInfo(name = "name") val name: String,
                @ColumnInfo(name = "age") val age: Int,
                @ColumnInfo(name = "breed") val breed: String,
): Serializable
