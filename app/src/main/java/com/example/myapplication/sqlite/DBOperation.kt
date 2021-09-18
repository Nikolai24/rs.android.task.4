package com.example.myapplication.sqlite

import android.content.ContentValues
import android.content.Context
import com.example.myapplication.Item
import com.example.myapplication.App


class DBOperation {

    fun getAnimalsFromBD(applicationContext: Context, sort: String) : List<Item> {
        val list = mutableListOf<Item>()
        val coursore = (applicationContext as App)
            .dbHelper
            .readableDatabase
            .query("animals", null, null, null, null, null, sort)
        if(coursore != null) {
            val idIndex = coursore.getColumnIndex("id")
            val nameIndex = coursore.getColumnIndex("name")
            val ageIndex = coursore.getColumnIndex("age")
            val breedIndex = coursore.getColumnIndex("breed")
            while (coursore.moveToNext()) {
                list.add(Item(coursore.getInt(idIndex), coursore.getString(nameIndex), coursore.getInt(ageIndex), coursore.getString(breedIndex)))
            }
            coursore.close()
            return list
        }
        return emptyList()
    }

    fun  saveAnimal(applicationContext: Context, name:String, age:Int, breed:String){
        val contentValues = ContentValues().apply {
            put("name", name)
            put("age", age)
            put("breed", breed)
        }
        (applicationContext as App)
            .dbHelper
            .readableDatabase
            .insert("animals", null, contentValues)
    }

    fun updateAnimal(applicationContext: Context, name:String, age:Int, breed:String, position:Int, sort: String){
        val contentValues = ContentValues().apply {
            put("name", name)
            put("age", age)
            put("breed", breed)
        }
        (applicationContext as App)
            .dbHelper
            .readableDatabase
            .update("animals", contentValues, "" + getAnimalsFromBD(applicationContext, sort)[position].id + " =id", null)
    }

    fun deleteAnimal(applicationContext: Context, position:Int, sort: String){
        (applicationContext as App)
            .dbHelper
            .readableDatabase
            .delete("animals", "" + getAnimalsFromBD(applicationContext, sort)[position].id + " =id", null)
    }
}