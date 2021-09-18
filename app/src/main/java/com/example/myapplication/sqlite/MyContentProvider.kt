package com.example.myapplication.sqlite

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.example.myapplication.App

public class MyContentProvider : ContentProvider() {
    private lateinit var db: SQLiteDatabase
    override fun onCreate(): Boolean {
        db = (context as App)
            .dbHelper
            .writableDatabase
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?): Cursor? {
        return when (uriMatcher.match(uri)) {
            DATA -> {
                val cursor = db.query("animals", null, selection, selectionArgs, null, null, sortOrder)
                cursor.setNotificationUri(context?.contentResolver, uri)
                cursor
            }
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return when (uriMatcher.match(uri)) {
            DATA -> {
                val id = db.insert("animals", null, values)
                context?.contentResolver?.notifyChange(uri, null)
                Uri.parse("\"animals\"/$id")
            }
            else -> null
        }
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?): Int {
        return when (uriMatcher.match(uri)) {
            DATA -> {
                val rowUpdated = db.update("animals", values, selection, selectionArgs)
                context?.contentResolver?.notifyChange(uri, null)
                rowUpdated
            }
            else -> -1
        }
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String>?): Int {
        return when (uriMatcher.match(uri)) {
            DATA -> {
                val rowDeleted = db.delete("animals", selection, selectionArgs)
                context?.contentResolver?.notifyChange(uri, null)
                rowDeleted
            }
            else -> -1
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            DATA -> "object/uri"
            else -> null
        }
    }

    companion object {
        private const val AUTHORITY = "com.example.myapplication"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/animals")
        private const val DATA = 1
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "animals", DATA)
        }
    }
}