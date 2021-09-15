package com.example.myapplication.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "animals", null, 1) {
    override fun onCreate(database: SQLiteDatabase) {
        database.execSQL("CREATE TABLE animals (id INTEGER Primary key autoincrement, name TEXT, age TEXT, breed TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
}