package com.example.myapplication.sqlite

import android.app.Application

class App: Application() {
    val  dbHelper: DBHelper = DBHelper(this)
}